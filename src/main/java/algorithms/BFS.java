/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.neo4j.driver.types.Point;

import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.dto.CustomResult.AnchorPath;
import ctu.indoor.dto.CustomResult.CustomRes;
import ctu.indoor.model.Anchor;

/**
 *
 * @author ADMIN
 */
public class BFS extends Algo{
    private final Map<String, AnchorDTO> graph;
    private final AnchorDTO startNode;
    private final AnchorDTO endNode;

    public BFS(Map<String, AnchorDTO> graph, AnchorDTO startNode, AnchorDTO endNode) {
        super();
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
    }


    public void execute() {
        graph.keySet().forEach(key -> {
            mark.put(key, NOT_VISITED);
            distance.put(key, (double)0);
            parent.put(key, "");
        });
        
        Queue<AnchorDTO> que = new LinkedList<>();
        que.add(startNode);

        while(!que.isEmpty()) {
            AnchorDTO processingNode = que.poll();
            if(processingNode.getId().equals(endNode.getId())) break;

            processingNode.getNeigbors().forEach((nei) -> {
                Anchor neigborNode = nei.getTarget();
                double weight = nei.getDistance();

                if(mark.get(neigborNode.getId()) == NOT_VISITED) {
                    que.add(new AnchorDTO(neigborNode));
                    parent.put(neigborNode.getId(), processingNode.getId());
                    distance.put(neigborNode.getId(), distance.get(processingNode.getId()) + weight);
                }
            });

            mark.put(processingNode.getId(), VISITED);
        }


    }

    public CustomRes getPath() {

        List<AnchorPath> anchors = new ArrayList<>();
        AnchorDTO trackNode = new AnchorDTO(endNode);
        while(true) {
            Point point = trackNode.getGeometry();
            anchors.add(new AnchorPath(trackNode.getId(), trackNode.getLevelId(),BigDecimal.valueOf(point.x()),BigDecimal.valueOf(point.y()), BigDecimal.valueOf(distance.get(trackNode.getId()))));
            if(parent.get(trackNode.getId()).equals("")) break;
            trackNode = graph.get(parent.get(trackNode.getId()));
        }

        return new CustomRes(distance.get(endNode.getId()), anchors, "");
    }
}
