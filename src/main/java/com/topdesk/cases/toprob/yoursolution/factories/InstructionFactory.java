package com.topdesk.cases.toprob.yoursolution.factories;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Instruction;

/**
 * Rob only knows the next {@link Coordinate} but he want to know the next {@link Instruction} as well.
 */
public class InstructionFactory {

    /**
     * Converts two adjacent {@link Coordinate}s into an {@link Instruction}.
     * @param rob or robot who moves.
     * @param next the following step.
     * @return {@link Instruction}
     * @throws IllegalArgumentException  if the two  {@link Coordinate}s are not not adjacent.
     */
    public static Instruction createInstruction(Coordinate rob, Coordinate next) {
        Instruction instruction = null;
        if (Instruction.EAST.execute(rob).equals(next)) {
            instruction = Instruction.EAST;
        } else if (Instruction.NORTH.execute(rob).equals(next)) {
            instruction = Instruction.NORTH;
        } else if (Instruction.WEST.execute(rob).equals(next)) {
            instruction = Instruction.WEST;
        } else if (Instruction.SOUTH.execute(rob).equals(next)) {
            instruction = Instruction.SOUTH;
        } else {
            throw new IllegalArgumentException("The two coordinates are not neighbours :" + rob + ", " + next);
        }
        return instruction;
    }
}
