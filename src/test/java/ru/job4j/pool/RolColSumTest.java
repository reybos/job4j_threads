package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RolColSumTest {
    @Test
    public void sum() {
        int[][] input = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(RolColSum.sum(input), is(expected));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] input = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = new RolColSum.Sums[] {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)
        };
        assertThat(RolColSum.asyncSum(input), is(expected));
    }
}