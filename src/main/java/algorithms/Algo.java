/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class Algo {
    protected Map<String, Integer> mark;
    protected Map<String, Double> distance;
    protected Map<String, String> parent;

    protected  static final int NOT_VISITED = -1;
    protected  static final int IN_PROGRESS = 0;
    protected  static final int VISITED = 1;
    protected  static final double INF = 999999999;
    
    public Algo() {
        this.mark = new HashMap<>();
        this.distance = new HashMap<>();
        this.parent = new HashMap<>();
    }

}
