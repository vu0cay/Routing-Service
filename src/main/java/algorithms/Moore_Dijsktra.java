/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.types.Point;

import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.dto.CustomResult.AnchorPath;
import ctu.indoor.dto.CustomResult.CustomRes;

/**
 *
 * @author ADMIN
 */
public class Moore_Dijsktra extends Algo{
    private final Map<String, AnchorDTO> graph;
    private final AnchorDTO startNode;
    private final AnchorDTO endNode;

    public Moore_Dijsktra(Map<String, AnchorDTO> graph, AnchorDTO startNode, AnchorDTO endNode) {
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
        
        distance.put(startNode.getId(), (double)0);

        for (int i = 0; i<graph.size(); i++) {

            double min = INF;
            AnchorDTO smallestNode = null;

            for (Map.Entry<String, AnchorDTO> entry : graph.entrySet()) {
                String k = entry.getKey();
                AnchorDTO v = entry.getValue();
        
                if (distance.get(k) < min && mark.get(k) == NOT_VISITED) {
                    min = distance.get(k);
                    smallestNode = new AnchorDTO(v);
                }
            }

            if(smallestNode == null || smallestNode.getId().equals(endNode.getId())) break;
            
            mark.put(smallestNode.getId(), IN_PROGRESS);

            for (var neighbor : smallestNode.getNeigbors()) {
                var isUnvisitNode = mark.get(neighbor.getTarget().getId()) == NOT_VISITED;
                var isBetterPath = distance.get(neighbor.getTarget().getId()) > distance.get(smallestNode.getId()) + neighbor.getDistance();

                if(isUnvisitNode && isBetterPath) {
                    double optimalPath = distance.get(smallestNode.getId()) + neighbor.getDistance();

                    distance.put(neighbor.getTarget().getId(), optimalPath); 
                    parent.put(neighbor.getTarget().getId(), smallestNode.getId());

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
            anchors.add(new AnchorPath(trackNode.getId(), trackNode.getLevelId(),BigDecimal.valueOf(point.x()),BigDecimal.valueOf(point.y()), BigDecimal.valueOf(distance.get(trackNode.getId())), "undefined"));
            if(parent.get(trackNode.getId()).equals("")) break;
            trackNode = graph.get(parent.get(trackNode.getId()));
        }

        return new CustomRes(distance.get(endNode.getId()), anchors, "");
    }
}
