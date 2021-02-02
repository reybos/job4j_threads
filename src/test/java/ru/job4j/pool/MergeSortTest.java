package ru.job4j.pool;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MergeSortTest {
    @Test
    public void sort() {
        int[] input = new int[] {4, 1, 8, 9, 2, 2};
        int[] expected = new int[] {1, 2, 2, 4, 8, 9};
        int[] rsl = MergeSort.sort(input);
        assertThat(rsl, is(expected));
    }
}