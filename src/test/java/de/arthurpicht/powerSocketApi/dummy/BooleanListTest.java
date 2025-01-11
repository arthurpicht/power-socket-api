package de.arthurpicht.powerSocketApi.dummy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BooleanListTest {

    @Test
    public void reduceNoAction() {
        List<Boolean> booleanListInput = Arrays.asList(true, false, true);
        List<Boolean> booleanListOutput = BooleanList.reduce(booleanListInput);

        List<Boolean> booleanListExpected = Arrays.asList(true, false, true);
        assertEquals(booleanListExpected, booleanListOutput);
    }

    @Test
    public void reduceAction() {
        List<Boolean> booleanListInput = Arrays.asList(true, false, true, false, false);
        List<Boolean> booleanListOutput = BooleanList.reduce(booleanListInput);

        List<Boolean> booleanListExpected = Arrays.asList(true, false, true);
        assertEquals(booleanListExpected, booleanListOutput);
    }

    @Test
    public void expandWithLastElementAsTrue_noAction() {
        List<Boolean> booleanListInput = Arrays.asList(true, false, true);
        List<Boolean> booleanListOutput = BooleanList.expandWithLastElementAsTrue(booleanListInput, 2);

        List<Boolean> booleanListExpected = Arrays.asList(true, false, true);
        assertEquals(booleanListExpected, booleanListOutput);
    }

    @Test
    public void expandWithLastElementAsTrue_addOne() {
        List<Boolean> booleanListInput = Arrays.asList(true, false, true);
        List<Boolean> booleanListOutput = BooleanList.expandWithLastElementAsTrue(booleanListInput, 3);

        List<Boolean> booleanListExpected = Arrays.asList(true, false, true, true);
        assertEquals(booleanListExpected, booleanListOutput);
    }

    @Test
    public void expandWithLastElementAsTrue_addTwo() {
        List<Boolean> booleanListInput = Arrays.asList(true, false, true);
        List<Boolean> booleanListOutput = BooleanList.expandWithLastElementAsTrue(booleanListInput, 4);

        List<Boolean> booleanListExpected = Arrays.asList(true, false, true, false, true);
        assertEquals(booleanListExpected, booleanListOutput);
    }

    @Test
    public void initializeAndGet() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        assertEquals(3, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setFirst() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(0, false);

        assertEquals(3, booleanList.getReducedSize());

        assertFalse(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setSecond() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(1, true);

        assertEquals(3, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertTrue(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setLast() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(2, false);

        assertEquals(1, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertFalse(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setLastVar2() {
        List<String> booleanListInput = Arrays.asList("true", "true", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(2, false);

        assertEquals(2, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertTrue(booleanList.get(1));
        assertFalse(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setFirstBehindRangeToTrue() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(3, true);

        assertEquals(4, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertTrue(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setFirstBehindRangeToFalse() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(3, false);

        assertEquals(3, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
    }

    @Test
    public void setSecondBehindRangeToTrue() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(4, true);

        assertEquals(5, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertTrue(booleanList.get(4));
        assertFalse(booleanList.get(5));
    }

    @Test
    public void setSecondBehindRangeToFalse() {
        List<String> booleanListInput = Arrays.asList("true", "false", "true");
        BooleanList booleanList = new BooleanList(booleanListInput);

        booleanList.set(4, false);

        assertEquals(3, booleanList.getReducedSize());

        assertTrue(booleanList.get(0));
        assertFalse(booleanList.get(1));
        assertTrue(booleanList.get(2));
        assertFalse(booleanList.get(3));
        assertFalse(booleanList.get(4));
        assertFalse(booleanList.get(5));
    }



}