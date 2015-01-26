package com.jediq.hashcodeequals;

/**
 *
 */
public class Thing4SpeedTest extends SpeedTest {

    @Override
    public Thing createThingObject() {
        return new Thing4();
    }

    public class Thing4 extends Thing {

        private LambdaEqualsHashCode <Thing4> lehc = new LambdaEqualsHashCode <>(Thing4.class)
                .with(t -> t.int1)
                .with(t -> t.int2)
                .with(t -> t.string1)
                .with(t -> t.string2);

        @Override
        public int hashCode() {
            return lehc.hashCodeFor(this);
        }

        @Override
        public boolean equals(Object o) {
            return lehc.areEqual(this, o);
        }

    }

}
