package com.jediq.hashcodeequals;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class LambdaEqualsHashCodeTest {


    @Test
    public void testEqualsNulls() {

        T1 left = new T1(null, null);
        T1 right = new T1(null, null);
        assertTrue(left.equals(right));

        left = new T1(null, null);
        right = new T1("s", null);
        assertFalse(left.equals(right));

        left = new T1("s", null);
        right = new T1(null, null);
        assertFalse(left.equals(right));
    }

    @Test
    public void testEqualsSameValues() {
        T1 left = new T1("a", 1);
        T1 right = new T1("a", 1);

        assertTrue(left.equals(left));
        assertTrue(left.equals(right));
        assertTrue(right.equals(right));
        assertTrue(right.equals(left));
    }

    @Test
    public void testEqualsOneDifferentValue() {
        T1 left = new T1("a", 1);
        T1 right = new T1("a", 2);
        assertFalse(left.equals(right));
    }

    @Test
    public void testEqualsDifferentValues() {
        T1 left = new T1("a", 1);
        T1 right = new T1("b", 2);
        assertFalse(left.equals(right));
    }

    @Test
    public void testHashCodeNulls() {
        T1 object = new T1(null, null);
        assertEquals(31, object.hashCode());

        object = new T1(null, 2);
        assertEquals(1953, object.hashCode());

        object = new T1("s", null);
        assertEquals(110546, object.hashCode());

        object = new T1("s", 3);
        assertEquals(10391324, object.hashCode());
    }

    @Test
    public void testAreEqual_WrongLeftObject() {
        LambdaEqualsHashCode slehc = new LambdaEqualsHashCode(this.getClass());
        assertFalse(slehc.areEqual("", this));
    }
    @Test
    public void testAreEqual_WrongRightObject() {
        LambdaEqualsHashCode slehc = new LambdaEqualsHashCode(this.getClass());
        assertFalse(slehc.areEqual(this, ""));
    }

    @Test
    public void testHashCodeSameValues() {
        T1 left = new T1("a", 1);
        T1 right = new T1("a", 1);
        assertEquals(left.hashCode(), right.hashCode());
    }

    @Test
    public void testHashCodeOneDifferentValue() {
        T1 left = new T1("a", 1);
        T1 right = new T1("a", 2);
        assertNotEquals(left.hashCode(), right.hashCode());
    }

    @Test
    public void testHashCodeDifferentValues() {
        T1 left = new T1("a", 1);
        T1 right = new T1("b", 2);
        assertNotEquals(left.hashCode(), right.hashCode());
    }

    class T1 {
        String s;
        Integer i;

        private final LambdaEqualsHashCode<T1> SLEQHC = new LambdaEqualsHashCode<>(T1.class)
                .with(a -> a.s)
                .with(a -> a.i);

        T1(String s, Integer i) {
            this.s = s;
            this.i = i;
        }

        @Override
        public boolean equals(Object that) {
            return SLEQHC.areEqual(this, that);
        }

        @Override
        public int hashCode() {
            return SLEQHC.hashCodeFor(this);
        }
    }
}
