package com.jediq.hashcodeequals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 *
 * Java 8 implementation of hashCode() and equals() builder pattern.
 * 
 * <br/><br/>
 * Usage : 
 * <pre>
 * {@code
 * private LambdaEqualsHashCode <Thing> lehc = new LambdaEqualsHashCode <>(Thing.class)
 *      .with(t -> t.int1)
 *      .with(t -> t.int2)
 *      .with(t -> t.string1)
 *      .with(t -> t.string2);
 *
 * @Override
 * public int hashCode() {
 *   return lehc.hashCodeFor(this);
 * }
 *
 * @Override
 * public boolean equals(Object o) {
 *   return lehc.areEqual(this, o);
 * }
 * }
 * </pre>
 *
 */
public class LambdaHashCodeEquals<T> {

    private static final int SEED = 31;

    private Class<T> clazz;

    private List<Comparator<T>> comparators = new ArrayList<>();

    private List<Function<T, Object> > attributes = new ArrayList<>();

    /**
     * Constructs the object with a single class type which will be used to validate
     * the builder functions passed in and the hashCodeFor and areEqual methods.
     * 
     * @param clazz the class type to use
     */
    public LambdaHashCodeEquals(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * Adds a builder function for use in the hashCodeFor and areEqual methods
     *
     * @param attribute the builder function
     *                 
     * @return this object to allow method chaining
     */
    public LambdaHashCodeEquals<T> with(Function<T, Object> attribute) {
        attributes.add(attribute);
        return this;
    }


    public LambdaHashCodeEquals<T> withComparator(Comparator<T> comparator) {
        comparators.add(comparator);
        return this;
    }

    /**
     * Generates a hashcode for the passed in T object using the builder functions defined previously.
     *
     * @param object the object to generate the hashcode from
     *               *               
     * @return the hashCode
     */
    public int hashCodeFor(T object) {
        int total = SEED;
        for (Function<T, Object>  attribute : attributes) {
            Object attributeValue = attribute.apply(object);
            total *= SEED;
            if (attributeValue != null) {
                total += attributeValue.hashCode();
            }
        }
        return total;
    }

    /**
     * Compares two objects, the first of the genericised type defined in the constructor
     * using the builder functions defined previously.
     *  
     * @param left The genericised lhs object to compare
     * @param right The plain rhs object to compare
     *           
     * @return true if the objects are calculated to be equal else false
     */
    public boolean areEqual(T left, Object right) {
        if (left == right) {
            return true;
        }
        if (!clazz.isInstance(left) || !clazz.isInstance(right)) {
            return false;
        }
        for (Function<T, Object>  attribute : attributes) {
            if (areAttributesDifferent(left, right, attribute)) {
                return false;
            }
        }
        for (Comparator comparator : comparators) {
            if (comparator.compare(left, right) != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean areAttributesDifferent(T left, Object right, Function attribute) {
        Object leftAttribute = attribute.apply(left);
        Object rightAttribute = attribute.apply(right);
        return leftAttribute == null && rightAttribute != null 
                || leftAttribute != null && !leftAttribute.equals(rightAttribute);
    }
}