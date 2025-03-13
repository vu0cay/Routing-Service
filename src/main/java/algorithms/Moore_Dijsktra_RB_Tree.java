/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.neo4j.driver.types.Point;

import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.dto.CustomResult.AnchorPath;
import ctu.indoor.dto.CustomResult.CustomRes;
import ctu.indoor.dto.CustomResult.Pair;

/**
 *
 * @author ADMIN
 */
public class Moore_Dijsktra_RB_Tree extends Algo{
    private final Map<String, AnchorDTO> graph;
    private final AnchorDTO startNode;
    private final AnchorDTO endNode;

    public Moore_Dijsktra_RB_Tree(Map<String, AnchorDTO> graph, AnchorDTO startNode, AnchorDTO endNode) {
        super();
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
    }


    public void execute() {
        
        graph.keySet().forEach(key -> {
            mark.put(key, NOT_VISITED);
            distance.put(key, INF);
            parent.put(key, "");
        });
        
        TreeSet<Pair> RbTree = new TreeSet<>();

        distance.put(startNode.getId(), (double)0);
        RbTree.add(new Pair(distance.get(startNode.getId()), startNode));
        

        for (int i = 0; i<graph.size(); i++) {

            // find min
            if(RbTree.isEmpty()) break;

            AnchorDTO smallestNode = RbTree.first().anchor();

            if(smallestNode == null || smallestNode.getId().equals(endNode.getId())) break;
            
            // remove min
            RbTree.remove(RbTree.first());

            mark.put(smallestNode.getId(), IN_PROGRESS);

            for (var neighbor : smallestNode.getNeigbors()) {
                var isUnvisitNode = mark.get(neighbor.getTarget().getId()) == NOT_VISITED;
                var isBetterPath = distance.get(neighbor.getTarget().getId()) > distance.get(smallestNode.getId()) + neighbor.getDistance();

                if(isUnvisitNode && isBetterPath) {
                    RbTree.remove(new Pair(distance.get(neighbor.getTarget().getId()), new AnchorDTO(neighbor.getTarget())));
                    
                    double optimalPath = distance.get(smallestNode.getId()) + neighbor.getDistance();
                    
                    distance.put(neighbor.getTarget().getId(), optimalPath); 
                    parent.put(neighbor.getTarget().getId(), smallestNode.getId());
                    
                    RbTree.add(new Pair(distance.get(neighbor.getTarget().getId()), new AnchorDTO(neighbor.getTarget())));
                }
            }

            mark.put(smallestNode.getId(), VISITED);


        }


    }

    public CustomRes getPath() {

        List<AnchorPath> anchors = new ArrayList<>();
        AnchorDTO trackNode = new AnchorDTO(endNode);
        while(true) {
            Point point = trackNode.getGeometry();
            anchors.add(new AnchorPath(trackNode.getId(), trackNode.getOrdinal(),BigDecimal.valueOf(point.x()),BigDecimal.valueOf(point.y()), BigDecimal.valueOf(distance.get(trackNode.getId())), "undefined"));
            if(parent.get(trackNode.getId()).equals("")) break;
            trackNode = graph.get(parent.get(trackNode.getId()));
        }

        return new CustomRes(distance.get(endNode.getId()), anchors, "");
    }
}
