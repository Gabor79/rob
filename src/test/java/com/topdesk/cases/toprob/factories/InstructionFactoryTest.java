package com.topdesk.cases.toprob.factories;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.topdesk.cases.toprob.Coordinate;
import com.topdesk.cases.toprob.Instruction;
import com.topdesk.cases.toprob.yoursolution.factories.InstructionFactory;


public class InstructionFactoryTest {

    @Test
    public void shouldStepOnEast() {
        Instruction instruction = InstructionFactory.createInstruction(new Coordinate(1, 1), new Coordinate(2, 1));
        assertEquals(Instruction.EAST, instruction);
    }

    @Test
    public void shouldStepOnWest() {
        Instruction instruction = InstructionFactory.createInstruction(new Coordinate(1, 1), new Coordinate(0, 1));
        assertEquals(Instruction.WEST, instruction);
    }

    @Test
    public void shouldStepOnNorth() {
        Instruction instruction = InstructionFactory.createInstruction(new Coordinate(1, 1), new Coordinate(1, 0));
        assertEquals(Instruction.NORTH, instruction);
    }

    @Test
    public void shouldStepOnSouth() {
        Instruction instruction = InstructionFactory.createInstruction(new Coordinate(1, 1), new Coordinate(1, 2));
        assertEquals(Instruction.SOUTH, instruction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNotNeighbours() {
        InstructionFactory.createInstruction(new Coordinate(1, 1), new Coordinate(2, 2));
    }

}
