package com.jediq.hashcodeequals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 */
public class LambdaEqualsHashCode<T> {

    private static final int SEED = 31;

    private Class<T> clazz;

    private List<Function<T, Object> > attributes = new ArrayList<>();

    public LambdaEqualsHashCode(Class<T> clazz) {
        this.clazz = clazz;
    }

    public LambdaEqualsHashCode<T> with(Function<T, Object> attribute) {
        attributes.add(attribute);
        return this;
    }

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
        return true;
    }

    private boolean areAttributesDifferent(T left, Object right, Function attribute) {
        Object leftAttribute = attribute.apply(left);
        Object rightAttribute = attribute.apply(right);
        return leftAttribute == null && rightAttribute != null 
                || leftAttribute != null && !leftAttribute.equals(rightAttribute);
    }

    public int hashCodeFor(T object) {
        int value = SEED;
        for (Function<T, Object>  attribute : attributes) {
            Object attributeValue = attribute.apply(object);
            if (attributeValue != null) {
                value += attributeValue.hashCode() * SEED * value;
            }
        }
        return value;
    }
}