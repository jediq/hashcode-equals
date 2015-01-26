package com.jediq.hashcodeequals;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 */
public class Thing2SpeedTest extends SpeedTest {
    
    @Override
    public Thing createThingObject() {
        return new Thing2();
    }

    public class Thing2 extends Thing {

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Thing2)) {
                return false;
            }
            Thing2 thing2 = (Thing2) o;
            return new EqualsBuilder()
                    .append(int1, thing2.int1)
                    .append(int2, thing2.int2)
                    .append(string1, thing2.string1)
                    .append(string2, thing2.string2)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(int1)
                    .append(int2)
                    .append(string1)
                    .append(string2)
                    .toHashCode();
        }
    }
}
