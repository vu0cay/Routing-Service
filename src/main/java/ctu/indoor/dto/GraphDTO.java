/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ctu.indoor.model.Anchor;

/**
 *
 * @author ADMIN
 */
public class GraphDTO {
    private Map<String, AnchorDTO> graph;

    public GraphDTO(List<Anchor> anchors) {
        this.graph = new HashMap<>();
        anchors.forEach(anchor -> {
            graph.put(anchor.getId(), new AnchorDTO(anchor));
        });
    }

    public Map<String, AnchorDTO> getGraph() {
        return graph;
    }

    public void setGraph(Map<String, AnchorDTO> graph) {
        this.graph = graph;
    }
     
}
