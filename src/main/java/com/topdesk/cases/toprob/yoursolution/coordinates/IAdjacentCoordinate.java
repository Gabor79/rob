package com.topdesk.cases.toprob.yoursolution.coordinates;

import java.util.Set;

/**
 * Collect the neighbours of a given point or coordinate.
 * Point or coordinate can be a wrapper representation of x and y.
 */
public interface IAdjacentCoordinate<T> {

    /**
     * Returns adjacent elements of a point or coordinate
     *
     * @param t any point or coordinate
     * @return {@link Set} of point or coordinate
     */
    Set<T> getNeighbours(T t);

}
