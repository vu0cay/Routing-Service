/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ctu.indoor.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import algorithms.BFS;
import algorithms.DFS;
import algorithms.Moore_Dijsktra;
import algorithms.Moore_Dijsktra_RB_Tree;
import ctu.indoor.dto.AnchorDTO;
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
@Path("anchors")
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
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns")).status(200).build();
        
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
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns")).status(200).build();
        
    }


    @POST
    @Path("/Moore_Dijsktra")
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
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns")).status(200).build();
        
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
        
        return Response.ok(new CustomRes(res.totalDistance(),res.paths(),duration + " ns")).status(200).build();
        
    }
}