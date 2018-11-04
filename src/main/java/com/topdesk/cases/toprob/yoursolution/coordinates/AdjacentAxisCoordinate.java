package com.topdesk.cases.toprob.yoursolution.coordinates;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Instruction;

/**
 * Rob need valid coordinates to decide the next step, which can only be {@link Coordinate}s of the {@link com.topdesk.cases.toprob.Grid} and no holes.
 */
public class AdjacentAxisCoordinate implements IAdjacentCoordinate<Coordinate> {
    private final Set<Coordinate> holes;
    private final int gridHeight;
    private final int gridWith;


    public AdjacentAxisCoordinate(Set<Coordinate> holes, int gridWith, int gridHeight) {
        this.holes = holes;
        this.gridHeight = gridHeight;
        this.gridWith = gridWith;
    }

    /**
     * Neighbours of a given {@link Coordinate}.
     *
     * @param c {@link Coordinate}
     * @return valid {@link Coordinate}s
     */
    @Override
    public Set<Coordinate> getNeighbours(Coordinate c) {
        return Arrays.asList(Instruction.EAST.execute(c), Instruction.NORTH.execute(c), Instruction.SOUTH.execute(c), Instruction.WEST.execute(c)).stream()
                .filter(coo -> coo.getX() >= 0)
                .filter(coo -> coo.getX() <= gridWith - 1)
                .filter(coo -> coo.getY() >= 0)
                .filter(coo -> coo.getY() <= gridHeight - 1)
                .filter(coo -> !holes.contains(coo))
                .collect(Collectors.toSet());
    }
}
