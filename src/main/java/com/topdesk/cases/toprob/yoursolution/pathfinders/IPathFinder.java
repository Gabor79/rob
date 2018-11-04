package com.topdesk.cases.toprob.yoursolution.pathfinders;

import java.util.List;

/**
 * Finds a path between two elements.
 * @param <T> A container for two integer values, called x and y.
 */
public interface IPathFinder<T> {

    /**
     * Finds a shortest path between start and goal.
     * @param start starting point.
     * @param goal destination point.
     * @return route between two points.
     */
    List<T> findPath(T start, T goal);
}
