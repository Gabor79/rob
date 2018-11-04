package com.topdesk.cases.toprob.yoursolution;

import static java.util.Spliterators.spliteratorUnknownSize;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Grid;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.Solution;
import com.topdesk.cases.toprob.yoursolution.coordinates.AdjacentAxisCoordinate;
import com.topdesk.cases.toprob.yoursolution.factories.InstructionFactory;
import com.topdesk.cases.toprob.yoursolution.pathfinders.AstarPathFinder;
import com.topdesk.cases.toprob.yoursolution.pathfinders.IPathFinder;

public class YourSolution implements Solution {
    private final static int PAUSE_IN_KITCHEN = 5;

    @Override
    public List<Instruction> solve(Grid grid, int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative: " + time);
        }
        if (grid == null) {
            throw new NullPointerException("Grid is null");
        }
        List<Coordinate> path = findPath(grid);
        if (path.isEmpty()) {
            throw new IllegalArgumentException("There isn't any path between rob and the kitchen");
        }
        return getInstructions(grid, time, path);
    }

    private List<Coordinate> findPath(Grid grid) {
        BiFunction<Coordinate, Coordinate, Double> manhattanDistance = (rob, kitchen) -> Double.valueOf((Math.abs(rob.getX() - kitchen.getX()) + Math.abs(rob.getY() - kitchen.getY())));
        AdjacentAxisCoordinate adjacentAxisCoordinate = new AdjacentAxisCoordinate(grid.getHoles(), grid.getWidth(), grid.getHeight());
        IPathFinder<Coordinate> aStar = new AstarPathFinder(manhattanDistance, adjacentAxisCoordinate);
        return aStar.findPath(grid.getRoom(), grid.getKitchen());
    }

    private List<Instruction> getInstructions(Grid grid, int time, List<Coordinate> forwardPath) {
        List<Instruction> instructions = new ArrayList<>();
        forwardPath.add(0, grid.getRoom());
        Iterator<Coordinate> backwardPath = (((LinkedList<Coordinate>) forwardPath).descendingIterator());
        List<Coordinate> mergedPath = merge(forwardPath, backwardPath);
        Iterator<Coordinate> pathIterator = mergedPath.iterator();
        Coordinate rob = pathIterator.next();
        while (pathIterator.hasNext()) {
            time++;
            Coordinate next = pathIterator.next();
            if (grid.getKitchen().equals(rob)) {
                time = time + PAUSE_IN_KITCHEN;
                instructions.addAll(IntStream.range(0, PAUSE_IN_KITCHEN).mapToObj(i -> Instruction.PAUSE).collect(Collectors.toList()));
            }
            while (grid.getBug(time).equals(next)) {
                instructions.add(Instruction.PAUSE);
                time++;
            }
            instructions.add(InstructionFactory.createInstruction(rob, next));
            rob = next;
        }
        return instructions;
    }

    private List<Coordinate> merge(List<Coordinate> path, Iterator<Coordinate> pathBackward) {
        List<Coordinate> forwardBackward = new LinkedList<>();
        forwardBackward.addAll(path);
        forwardBackward.addAll(StreamSupport.stream(spliteratorUnknownSize(pathBackward, 0), false).skip(1).collect(Collectors.toList()));
        return forwardBackward;
    }

}
