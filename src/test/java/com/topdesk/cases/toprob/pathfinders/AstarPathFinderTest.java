package com.topdesk.cases.toprob.pathfinders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.helper.GridFactory;
import com.topdesk.cases.toprob.yoursolution.coordinates.AdjacentAxisCoordinate;
import com.topdesk.cases.toprob.yoursolution.pathfinders.AstarPathFinder;

public class AstarPathFinderTest {

    @Test
    public void shouldFindPath() {
        Grid grid = GridFactory.create(
                ".rBA",
                "...o",
                ".ooo",
                "...k");

        List<Coordinate> path = getCoordinates(grid);
        assertTrue(path.size() == 7);
        assertEquals(new Coordinate(0, 0), path.get(0));
        assertEquals(new Coordinate(0, 1), path.get(1));
        assertEquals(new Coordinate(0, 2), path.get(2));
        assertEquals(new Coordinate(0, 3), path.get(3));
        assertEquals(new Coordinate(1, 3), path.get(4));
        assertEquals(new Coordinate(2, 3), path.get(5));
        assertEquals(new Coordinate(3, 3), path.get(6));
    }


    @Test
    public void whenPathNoFound() {
        Grid grid = GridFactory.create(
                "o.BAk",
                "ro...",
                ".ooo.",
                "..o.o");
        List<Coordinate> path = getCoordinates(grid);
        assertTrue(path.isEmpty());
    }


    private List<Coordinate> getCoordinates(Grid grid) {
        BiFunction<Coordinate, Coordinate, Double> manhattanDistance = (rob, kitchen) -> Double.valueOf((Math.abs(rob.getX() - kitchen.getX()) + Math.abs(rob.getY() - kitchen.getY())));
        AdjacentAxisCoordinate adjacentAxisCoordinate = new AdjacentAxisCoordinate(grid.getHoles(), grid.getWidth(), grid.getHeight());
        AstarPathFinder astarPathFinder = new AstarPathFinder(manhattanDistance, adjacentAxisCoordinate);
        return astarPathFinder.findPath(grid.getRoom(), grid.getKitchen());
    }
}
