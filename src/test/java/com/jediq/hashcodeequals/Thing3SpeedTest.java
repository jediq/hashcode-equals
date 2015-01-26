package com.jediq.hashcodeequals;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 */
public class Thing3SpeedTest extends SpeedTest {

    @Override
    public Thing createThingObject() {
        return new Thing3();
    }

    public class Thing3 extends Thing {

        private EqualsBuilder equalsBuilder = new EqualsBuilder();
        private HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();

        @Override
        public boolean equals(Object o) {
            return equalsBuilder.reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return hashCodeBuilder.reflectionHashCode(this);
        }
    }

}
