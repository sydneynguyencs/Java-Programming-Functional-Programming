package ch.zhaw.prog2.stepik;

import ch.zhaw.prog2.functional.stepik.ComposingPredicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ComposingPredicateTest {
    private static final IntPredicate isEven = x -> x % 2 == 0;
    private static final IntPredicate isDividableBy3 = x -> x % 3 == 0;
    private static final List<IntPredicate> predicateList = List.of(isEven, isDividableBy3);
    private List<Integer> expected;
    private IntStream testIntegers;

    @Test
    void disjunctAllAnyMatch() {
        IntPredicate dividableBy2Or3 = ComposingPredicate.disjunctAllFaster(predicateList);
        List<Integer> machingInts = testIntegers.filter(dividableBy2Or3).boxed().collect(Collectors.toList());
        assertArrayEquals(expected.toArray(), machingInts.toArray());
    }

    @Test
    void disjunctAll() {
        IntPredicate dividableBy2Or3 = ComposingPredicate.disjunctAll(predicateList);
        List<Integer> machingInts = testIntegers.filter(dividableBy2Or3).boxed().collect(Collectors.toList());
        assertArrayEquals(expected.toArray(), machingInts.toArray());
    }

    @Test
    void disjunctAllNoStream() {
        IntPredicate dividableBy2Or3 = ComposingPredicate.disjunctAllNoStream(predicateList);
        List<Integer> machingInts = testIntegers.filter(dividableBy2Or3).boxed().collect(Collectors.toList());
        assertArrayEquals(expected.toArray(), machingInts.toArray());
    }

    @BeforeEach
    void setUp() {
        testIntegers = IntStream.range(1, 10);
        expected = List.of(2, 3, 4, 6, 8, 9);
    }
}
