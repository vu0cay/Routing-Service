package ctu.indoor.dto;

import java.math.BigDecimal;
import java.util.List;

public class CustomResult {
    public record CustomRes(double totalDistance, List<AnchorPath> paths, String executeTime) {}
    public record AnchorPath(String id, String levelId, BigDecimal geometryX, BigDecimal geometryY, BigDecimal distanceFromStartNode, String direction) {}
    public record Pair(double weight, AnchorDTO anchor) implements Comparable<Pair> {
        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.weight, other.weight);
        }
    }
}
