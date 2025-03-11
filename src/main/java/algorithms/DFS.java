/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.neo4j.driver.types.Point;

import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.model.Anchor;

/**
 *
 * @author ADMIN
 */
public class DFS extends Algo{
    private final Map<String, AnchorDTO> graph;
    private final AnchorDTO startNode;
    private final AnchorDTO endNode;

    public DFS(Map<String, AnchorDTO> graph, AnchorDTO startNode, AnchorDTO endNode) {
        super();
        this.graph = graph;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public record AnchorPath(String id, String levelId, BigDecimal geometryX, BigDecimal geometryY, BigDecimal distanceFromStartNode) {}
    public record CustomRes(double totalDistance, List<AnchorPath> paths) {}

    public CustomRes execute() {
    // public List<AnchorPathDTO> execute() {
    // public Map<String, Double> execute() {
    // public Map<String, AnchorDTO> execute() {
    // public AnchorDTO execute() {
        // return graph;
        graph.keySet().forEach(key -> {
            mark.put(key, NOT_VISITED);
            distance.put(key, (double)0);
            parent.put(key, "");
        });
        // return endNode;
        Stack<AnchorDTO> st = new Stack<>();
        st.push(startNode);

        while(!st.isEmpty()) {
            AnchorDTO processingNode = st.pop();
            if(processingNode.getId().equals(endNode.getId())) break;

            processingNode.getNeigbors().forEach((nei) -> {
                Anchor neigborNode = nei.getTarget();
                double weight = nei.getDistance();

                if(mark.get(neigborNode.getId()) == NOT_VISITED) {
                    st.push(new AnchorDTO(neigborNode));
                    parent.put(neigborNode.getId(), processingNode.getId());
                    distance.put(neigborNode.getId(), distance.get(processingNode.getId()) + weight);
                }
            });

            mark.put(processingNode.getId(), VISITED);
        }

        // return distance;
        // List<AnchorPathDTO> anchors = new ArrayList<>();
        List<AnchorPath> anchors = new ArrayList<>();
        AnchorDTO trackNode = new AnchorDTO(endNode);
        while(true) {
            // anchors.add(new AnchorPathDTO(trackNode.getId(), trackNode.getLevelId(), trackNode.getGeometry(), distance.get(trackNode.getId())));
            Point point = trackNode.getGeometry();
            anchors.add(new AnchorPath(trackNode.getId(), trackNode.getLevelId(),BigDecimal.valueOf(point.x()),BigDecimal.valueOf(point.y()), BigDecimal.valueOf(distance.get(trackNode.getId()))));
            
            if(parent.get(trackNode.getId()).equals("")) break;
            trackNode = graph.get(parent.get(trackNode.getId()));
            
        }

        return new CustomRes(distance.get(endNode.getId()), anchors);

    }

    // public List<AnchorPathDTO> getPath() {

    //     List<AnchorPathDTO> anchors = new ArrayList<>();
    //     AnchorDTO trackNode = new AnchorDTO(endNode);
    //     while(true) {
    //         anchors.add(new AnchorPathDTO(trackNode.getId(), trackNode.getLevelId(), trackNode.getGeometry(), distance.get(trackNode.getId())));
            
    //         if(parent.get(trackNode.getId()).equals("")) break;
    //         trackNode = graph.get(parent.get(trackNode.getId()));
            
    //     }

    //     return anchors;
    // }


}
