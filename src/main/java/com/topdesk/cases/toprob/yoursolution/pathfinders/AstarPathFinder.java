package com.topdesk.cases.toprob.yoursolution.pathfinders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.BiFunction;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.yoursolution.coordinates.IAdjacentCoordinate;

/**
 * Implementation of an <a href = "https://en.wikipedia.org/wiki/A*_search_algorithm">A star</a> algorithm.
 */
public class AstarPathFinder implements IPathFinder<Coordinate> {

    private BiFunction<Coordinate, Coordinate, Double> distanceStrategy;
    private IAdjacentCoordinate<Coordinate> adjacentCoordinate;

    public AstarPathFinder(BiFunction<Coordinate, Coordinate, Double> distanceStrategy, IAdjacentCoordinate<Coordinate> adjacentCoordinate) {
        this.adjacentCoordinate = adjacentCoordinate;
        this.distanceStrategy = distanceStrategy;
    }

    /**
     * A special implementation of an A* <a href = "https://en.wikipedia.org/wiki/A*_search_algorithm">A star</a> algorithm, where the cost of a step is the same.
     *
     * @param rob     the start
     * @param kitchen the destination.
     * @return route between rob and the kitchen.
     */
    @Override
    public List<Coordinate> findPath(Coordinate rob, Coordinate kitchen) {
        Set<Coordinate> closed = new HashSet<>();
        Map<Coordinate, Coordinate> fromMap = new HashMap<>();
        List<Coordinate> route = new LinkedList<>();
        final Map<Coordinate, Double> fScore = new HashMap<>();
        PriorityQueue<Coordinate> open = new PriorityQueue<>(30, (coor1, coor2) -> Double.compare(fScore.get(coor1), fScore.get(coor2)));
        fScore.put(rob, distanceStrategy.apply(rob, kitchen));
        open.offer(rob);
        while (!open.isEmpty()) {
            Coordinate current = open.poll();
            if (current.equals(kitchen)) {
                while (current != null) {
                    if (!current.equals(rob)) {
                        route.add(0, current);
                    }
                    current = fromMap.get(current);
                }
                break;
            } else {
                closed.add(current);
                for (Coordinate neighbour : adjacentCoordinate.getNeighbours(current)) {
                    if (closed.contains(neighbour)) {
                        continue;
                    }
                    double tentF = fScore.get(current);
                    boolean contains = open.contains(neighbour);
                    if (!contains || tentF < fScore.get(neighbour)) {
                        fScore.put(neighbour, tentF + distanceStrategy.apply(neighbour, kitchen));
                        if (contains) {
                            open.remove(neighbour);
                        }
                        open.offer(neighbour);
                        fromMap.put(neighbour, current);
                    }
                }
            }
        }
        return route;
    }
}
