package com.jediq.hashcodeequals;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LambdaEqualsHashCode<T> {

    private static final int SEED = 31;

    private Class<T> clazz;

    private List<Attribute<T>> attributes = new ArrayList<>();

    public LambdaEqualsHashCode(Class<T> clazz) {
        this.clazz = clazz;
    }

    public LambdaEqualsHashCode<T> with(Attribute<T> attribute) {
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
        for (Attribute attribute : attributes) {
            if (areAttributesDifferent(left, right, attribute)) {
                return false;
            }
        }
        return true;
    }

    private boolean areAttributesDifferent(T left, Object right, Attribute attribute) {
        Object leftAttribute = attribute.value(left);
        Object rightAttribute = attribute.value(right);
        if (leftAttribute == null && rightAttribute != null) {
            return true;
        }
        if (leftAttribute != null && !leftAttribute.equals(rightAttribute)) {
            return true;
        }
        return false;
    }

    public int hashCodeFor(T object) {
        int value = SEED;
        for (Attribute attribute : attributes) {
            Object attributeValue = attribute.value(object);
            if (attributeValue != null) {
                value += attributeValue.hashCode() * SEED * value;
            }
        }
        return value;
    }

    public interface Attribute<U> {
        Object value(U t);
    }
}