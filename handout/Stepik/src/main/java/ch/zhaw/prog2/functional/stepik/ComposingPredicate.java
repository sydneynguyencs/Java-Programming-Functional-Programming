package ch.zhaw.prog2.functional.stepik;

import java.util.List;
import java.util.function.IntPredicate;

public class ComposingPredicate {

    /**
     * The method represents a disjunct operator for a list of predicates.
     * For an empty list it returns the always false predicate.
     */
    public static IntPredicate disjunctAll(List<IntPredicate> predicates) {
        return x -> false; // TODO: Implement
    }

    /**
     * alternate implementation to reduce compute time if possible
     */
    public static IntPredicate disjunctAllFaster(List<IntPredicate> predicates) {
        return x -> false; // TODO: Implement with less computing steps
    }

    /**
     * classical implementation
     */
    public static IntPredicate disjunctAllNoStream(List<IntPredicate> predicates) {
        IntPredicate disjunct = x -> false;
        for (IntPredicate currentPredicate : predicates) {
            disjunct = disjunct.or(currentPredicate);
        }
        return disjunct;
    }
}
