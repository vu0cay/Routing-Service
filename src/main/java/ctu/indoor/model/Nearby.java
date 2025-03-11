/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@RelationshipEntity(type="NEARBY")
@AllArgsConstructor
@NoArgsConstructor
public class Nearby {
    @Id
    @GeneratedValue
    private Long Id;

    @StartNode
    private  Anchor startNode;
    
    @EndNode
    private  Anchor endNode;


    private double distance;


    public Long getId() {
        return Id;
    }


    public Anchor getStartNode() {
        return startNode;
    }


    public Anchor getEndNode() {
        return endNode;
    }


    public double getDistance() {
        return distance;
    }

    

}
