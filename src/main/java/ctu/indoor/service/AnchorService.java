/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.service;

import java.util.List;

import org.neo4j.ogm.session.Session;

import ctu.indoor.model.Anchor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 *
 * @author ADMIN
 */
@ApplicationScoped
public class AnchorService {
    @Inject
    Session session;
 
    // @Inject
    // Driver driver;
    public List<Anchor> findAll() {
        return (List<Anchor>) session.loadAll(Anchor.class);
    }

    // @Transactional
    // public List<Anchor> findAllWithRelation() {
    //     try(org.neo4j.driver.Session session = driver.session()) {
    //         String query = """
    //             MATCH (a:ANCHOR)-[r:NEARBY]->(b:ANCHOR) 
    //             RETURN a AS from, b AS to, r.distance AS distance
    //         """;

    //         Result result = session.run(query);
            
    //         return result.stream()
    //             .map(record -> new AnchorDTO(
    //                 record.get("from"),
    //                 record.get("to").asString(),
    //                 record.get("distance").asDouble()
    //             ))
    //             .collect(Collectors.toList());

    //     }
    // }
}
