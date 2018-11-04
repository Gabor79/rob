package com.topdesk.cases.toprob.coordinates;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.yoursolution.coordinates.AdjacentAxisCoordinate;

public class AdjacentAxisCoordinateTest {

    @Test
    public void whenHasWithoutNeighbours() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 1, 1);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 0));
        assertEquals(0,neighbours.size());
    }

    @Test
    public void whenHasOneNeighbourOnLeft() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 2, 1);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(1, 0));
        assertEquals(1,neighbours.size());
        assertEquals(new Coordinate(0,0),neighbours.iterator().next());
    }
    @Test
    public void whenHasOneNeighbourOnLeftWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(0, 0))), 2, 1);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(1, 0));
        assertEquals(0,neighbours.size());
    }
    @Test
    public void whenHasOneNeighbourOnRight() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 2, 1);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 0));
        assertEquals(1,neighbours.size());
        assertEquals(new Coordinate(1,0),neighbours.iterator().next());
    }

    @Test
    public void whenHasOneNeighbourOnRightWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(1, 0))), 2, 1);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 0));
        assertEquals(0,neighbours.size());
    }


    @Test
    public void whenHasOneNeighbourOnTop() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 1, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 1));
        assertEquals(1,neighbours.size());
        assertEquals(new Coordinate(0,0),neighbours.iterator().next());
    }

    @Test
    public void whenHasOneNeighbourOnTopWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(0, 1))), 1, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 0));
        assertEquals(0,neighbours.size());

    }

    @Test
    public void whenHasTwoNeighboursOnTopAndRight() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(5, 5))), 2, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(1, 1));
        assertEquals(2,neighbours.size());
    }

    @Test
    public void whenHasTwoNeighboursOnTopAndRightWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(0, 1))), 2, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(1, 1));
        assertEquals(1,neighbours.size());
    }


    @Test
    public void whenHasOneNeighbourOnBottom() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 1, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 0));
        assertEquals(1,neighbours.size());
        assertEquals(new Coordinate(0,1),neighbours.iterator().next());
    }
    @Test
    public void whenHasOneNeighbourOnBottomWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(0, 0))), 1, 2);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(0, 1));
        assertEquals(0,neighbours.size());

    }

    @Test
    public void whenHasFourNeighbours() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(4, 4))), 5, 5);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(3, 3));
        assertEquals(4,neighbours.size());
    }


    @Test
    public void whenHasFourNeighboursWithHole() {
        AdjacentAxisCoordinate underTest = new AdjacentAxisCoordinate(new HashSet<>(Arrays.asList(new Coordinate(3, 4))), 5, 5);
        Set<Coordinate> neighbours = underTest.getNeighbours(new Coordinate(3, 3));
        assertEquals(3,neighbours.size());
    }
}
