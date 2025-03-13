/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.internal.InternalPoint2D;
import org.neo4j.driver.types.Point;
import org.neo4j.ogm.session.Session;

import algorithms.Moore_Dijsktra;
import algorithms.PathDirectionalChecker;
import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.dto.CustomResult.AnchorPath;
import ctu.indoor.dto.CustomResult.CustomRes;
import ctu.indoor.dto.GraphDTO;
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
 
    public List<Anchor> findAll() {
        return (List<Anchor>) session.loadAll(Anchor.class);
    }
    public List<AnchorPath> pathDirectional(List<AnchorPath> paths) {

        List<AnchorPath> responsePath = new ArrayList<>();
        for(int index = paths.size() - 1; index>=0; index--) {
            String tmp;
            AnchorPath path = paths.get(index);
            if(index == paths.size() - 1) {
                tmp = "Go straight";
            } else if(index == 0) {
                tmp = "Finish";
            }
            else{
                var previous = paths.get(index - 1);
                var pt = paths.get(index);
                var next = paths.get(index + 1);
                Point A = new InternalPoint2D(7203, previous.geometryX().doubleValue(), previous.geometryY().doubleValue());
                Point B = new InternalPoint2D(7203, pt.geometryX().doubleValue(), pt.geometryY().doubleValue());
                Point C = new InternalPoint2D(7203, next.geometryX().doubleValue(), next.geometryY().doubleValue());
                
                var direct = PathDirectionalChecker.checkDirection(A, B , C);
                tmp = direct;
            }
            responsePath.add(new AnchorPath(path.id(), path.ordinal(), path.geometryX(), path.geometryY(), path.distanceFromStartNode(), tmp));
        }

        return responsePath;
    }
    public CustomRes getAllPathMoore_Dijsktra(String startNode, String endNode) {

        List<Anchor> anchors = findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        

        Moore_Dijsktra dijsktra = new Moore_Dijsktra(graph,graph.get(startNode) , graph.get(endNode));
        
        long startTime = System.nanoTime();
        dijsktra.execute();        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        // get row result that doesn't attach duration
        CustomRes result = dijsktra.getPath();
        List<AnchorPath> paths = result.paths();

        // add direction to the reponse's path
        var responsePath = pathDirectional(paths);
        // create new record that have duration and path direction
        return new CustomRes(result.totalDistance(),responsePath,duration + " ns");
        
    }

}
