package com.jediq.hashcodeequals;

/**
 *
 */
public class Thing1SpeedTest extends SpeedTest {

    @Override
    public Thing createThingObject() {
        return new Thing1();
    }
    
    public class Thing1 extends Thing {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Thing1 thing1 = (Thing1) o;

            if (int1 != thing1.int1) return false;
            if (int2 != thing1.int2) return false;
            if (string1 != null ? !string1.equals(thing1.string1) : thing1.string1 != null) return false;
            if (string2 != null ? !string2.equals(thing1.string2) : thing1.string2 != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = int1;
            result = 31 * result + int2;
            result = 31 * result + (string1 != null ? string1.hashCode() : 0);
            result = 31 * result + (string2 != null ? string2.hashCode() : 0);
            return result;
        }
        
    }
}
