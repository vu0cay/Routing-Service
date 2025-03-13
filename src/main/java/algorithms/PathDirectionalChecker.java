/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package algorithms;

import org.neo4j.driver.types.Point;

/**
 *
 * @author ADMIN
 */
public class PathDirectionalChecker {
    public static String checkDirection(Point A, Point B, Point C) {
        double crossProduct = (B.x() - A.x()) * (C.y() - B.y()) - (B.y() - A.y()) * (C.x() - B.x());

        if (Math.abs(crossProduct) < 5) {
            return "Go Straight";  // Within the tolerance range
        } else if (crossProduct < 0) {
            return "Turn Left";  // Counterclockwise
        } else {
            return "Turn Right"; // Clockwise
        }
    }

}
