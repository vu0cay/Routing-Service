/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@AllArgsConstructor
@NoArgsConstructor
public class PathRequestDTO {
    private  String startNode; 
    private  String endNode;
    public String getStartNode() {
        return startNode;
    }
    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }
    public String getEndNode() {
        return endNode;
    }
    public void setEndNode(String endNode) {
        this.endNode = endNode;
    }

    
   
    
    
}
