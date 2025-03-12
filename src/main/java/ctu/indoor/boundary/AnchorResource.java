/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.internal.InternalPoint2D;
import org.neo4j.driver.types.Point;

import algorithms.BFS;
import algorithms.DFS;
import algorithms.Moore_Dijsktra;
import algorithms.Moore_Dijsktra_RB_Tree;
import algorithms.PathDirectionalChecker;
import ctu.indoor.dto.AnchorDTO;
import ctu.indoor.dto.CustomResult.AnchorPath;
import ctu.indoor.dto.CustomResult.CustomRes;
import ctu.indoor.dto.GraphDTO;
import ctu.indoor.dto.PathRequestDTO;
import ctu.indoor.model.Anchor;
import ctu.indoor.service.AnchorService;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author ADMIN
 */
@ApplicationScoped
@Path("Graph")
@PermitAll
// @Transactional(SUPPORTS)
public class AnchorResource {
    private  final AnchorService anchorService;
    
    @Inject
    public AnchorResource(AnchorService anchorService) {
        this.anchorService = anchorService;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AnchorDTO> getAllThings() {
        List<Anchor> anchors = anchorService.findAll();
        List<AnchorDTO> anchorDTOs = new ArrayList<>();
        anchors.forEach(anchor -> anchorDTOs.add(new AnchorDTO(anchor)));
        return anchorDTOs;
    }


    @POST
    @Path("/DFS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPathDFS(PathRequestDTO requestDTO) {
        
        if (requestDTO == null || requestDTO.getStartNode() == null || requestDTO.getEndNode() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid request payload")
                           .build();
        }


        List<Anchor> anchors = anchorService.findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        
        
        DFS dfs = new DFS(graph,graph.get(requestDTO.getStartNode()) , graph.get(requestDTO.getEndNode()));
        
        long startTime = System.nanoTime();
        dfs.execute();        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        CustomRes res = dfs.getPath();
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns", null)).status(200).build();
        
    }

    @POST
    @Path("/BFS")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPathBFS(PathRequestDTO requestDTO) {
        
        if (requestDTO == null || requestDTO.getStartNode() == null || requestDTO.getEndNode() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid request payload")
                           .build();
        }


        List<Anchor> anchors = anchorService.findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        
        
        BFS bfs = new BFS(graph,graph.get(requestDTO.getStartNode()) , graph.get(requestDTO.getEndNode()));
        
        long startTime = System.nanoTime();
        bfs.execute();        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        CustomRes res = bfs.getPath();
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns", null)).status(200).build();
        
    }


    @POST
    @Path("/moore-dijsktra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPathMoore_Dijsktra(PathRequestDTO requestDTO) {
        
        if (requestDTO == null || requestDTO.getStartNode() == null || requestDTO.getEndNode() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid request payload")
                           .build();
        }


        List<Anchor> anchors = anchorService.findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        
        
        Moore_Dijsktra dijsktra = new Moore_Dijsktra(graph,graph.get(requestDTO.getStartNode()) , graph.get(requestDTO.getEndNode()));
        
        long startTime = System.nanoTime();
        dijsktra.execute();        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        CustomRes res = dijsktra.getPath();

        List<AnchorPath> paths = res.paths();
        List<String> direction = new ArrayList<>();
        // paths.forEach((pt) -> {
        for(int index = paths.size() - 1; index>=0; index--) {
            // int index = paths.indexOf(pt);
            if(index == paths.size() - 1) {
                direction.add("Go straight");
            } else if(index == 0) {
                direction.add("Finish");
            }
            else{
                var previous = paths.get(index - 1);
                var pt = paths.get(index);
                var next = paths.get(index + 1);
                Point A = new InternalPoint2D(7203, previous.geometryX().doubleValue(), previous.geometryY().doubleValue());
                Point B = new InternalPoint2D(7203, pt.geometryX().doubleValue(), pt.geometryY().doubleValue());
                Point C = new InternalPoint2D(7203, next.geometryX().doubleValue(), next.geometryY().doubleValue());

                var direct = PathDirectionalChecker.checkDirection(A, B , C);
                direction.add(direct);
            }
        }

        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns",direction)).status(200).build();
        
    }

    @POST
    @Path("/Moore_Dijsktra_with_direction")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPathMoore_Dijsktra_Direction(PathRequestDTO requestDTO) {
        
        if (requestDTO == null || requestDTO.getStartNode() == null || requestDTO.getEndNode() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid request payload")
                           .build();
        }

        List<Anchor> anchors = anchorService.findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        
        
        Moore_Dijsktra dijsktra = new Moore_Dijsktra(graph,graph.get(requestDTO.getStartNode()) , graph.get(requestDTO.getEndNode()));
        
        dijsktra.execute();        

        CustomRes res = dijsktra.getPath();
        List<AnchorPath> paths = res.paths();

        List<String> direction = new ArrayList<>();
        // paths.forEach((pt) -> {
        for(int index = paths.size() - 1; index>=0; index--) {
            // int index = paths.indexOf(pt);
            if(index == paths.size() - 1) {
                direction.add("Go straight");
            } else if(index == 0) {
                direction.add("Finish");
            }
            else{
                var previous = paths.get(index - 1);
                var pt = paths.get(index);
                var next = paths.get(index + 1);
                Point A = new InternalPoint2D(7203, previous.geometryX().doubleValue(), previous.geometryY().doubleValue());
                Point B = new InternalPoint2D(7203, pt.geometryX().doubleValue(), pt.geometryY().doubleValue());
                Point C = new InternalPoint2D(7203, next.geometryX().doubleValue(), next.geometryY().doubleValue());

                var direct = PathDirectionalChecker.checkDirection(A, B , C);
                direction.add(direct);
            }
        }
        
        return Response.ok(direction).status(200).build();
        
    }


    @POST
    @Path("/Moore_Dijsktra_RBT")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPathMoore_Dijsktra_RBT(PathRequestDTO requestDTO) {
        
        if (requestDTO == null || requestDTO.getStartNode() == null || requestDTO.getEndNode() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Invalid request payload")
                           .build();
        }


        List<Anchor> anchors = anchorService.findAll();
        GraphDTO graphIns = new GraphDTO(anchors);
        Map<String,AnchorDTO> graph = graphIns.getGraph();
        
        
        Moore_Dijsktra_RB_Tree dijsktra = new Moore_Dijsktra_RB_Tree(graph,graph.get(requestDTO.getStartNode()) , graph.get(requestDTO.getEndNode()));
        
        long startTime = System.nanoTime();
        dijsktra.execute();        
        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        CustomRes res = dijsktra.getPath();
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns", null)).status(200).build();
        
    }
}