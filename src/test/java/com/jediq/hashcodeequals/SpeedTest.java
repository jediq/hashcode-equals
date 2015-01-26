package com.jediq.hashcodeequals;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 */
public abstract class SpeedTest {

    protected static TestData [] testDatas;
    protected static int sampleSize = 10;
    protected static int numberInTestSet = 1000000;

    @BeforeClass
    public static void beforeClass() {
        if (testDatas == null) {
            testDatas = getTestDatas();
        }
        System.gc();
    }

    @Test
    public void testHashCode() {
        Object [] objects = generateObjects(testDatas);

        int total = 0;
        for (int i=0; i< sampleSize; i++) {
            long hashCodeStart = System.currentTimeMillis();
            Arrays.stream(objects).forEach(Object::hashCode);
            long hashCodeStop = System.currentTimeMillis();
            total += hashCodeStop - hashCodeStart;
        }
        int average = total / sampleSize;

        System.out.println(this.getClass().getName() + " : hashCode() : time = " + average);
    }

    @Test
    public void testEquals() {
        Object [] controlObjects = generateControlObjects();
        Object [] objects = generateObjects(testDatas);
        int total = 0;
        for (int i=0; i< sampleSize; i++) {
            long hashCodeStart = System.currentTimeMillis();
            Arrays.stream(objects).forEach(t -> Arrays.stream(controlObjects).forEach(c -> t.equals(c)));
            long hashCodeStop = System.currentTimeMillis();
            total += hashCodeStop - hashCodeStart;
        }
        int average = total / sampleSize;
        System.out.println(this.getClass().getName() + " : equals() : time = " + average);
    }
    
    protected Object[] generateObjects(TestData[] testDatas) {
        Thing [] things = new Thing[testDatas.length];
        for (int i=0; i<testDatas.length; i++) {
            Thing thing = createThingObject();
            thing.int1 = testDatas[i].int1;
            thing.int1 = testDatas[i].int2;
            thing.string1 = testDatas[i].string1;
            thing.string1 = testDatas[i].string2;
            things[i]=thing;
        }
        return things;
    }

    private Object [] generateControlObjects() {
        List<Object> controls = new ArrayList<>();

        Thing thing = createThingObject();
        thing.int1 = 24332525;
        thing.int2 = 432364;
        thing.string1 = "my first control string";
        thing.string2 = "my second control string";
        controls.add(thing);

        controls.add("some string");
        controls.add(31);
        controls.add(null);


        Thing anotherThing = createThingObject();
        anotherThing.int1 = 5643963;
        anotherThing.int2 = 78957;
        anotherThing.string1 = "another first control string";
        anotherThing.string2 = "another second control string";
        controls.add(thing);

        controls.add(createThingObject());


        return controls.toArray();
    }
    
    protected static TestData[] getTestDatas() {
        testDatas = new TestData[numberInTestSet];

        Random random = new Random();
        for (int i = 0; i < numberInTestSet; i++) {
            TestData testData = new TestData();
            testData.int1 = random.nextInt();
            testData.int2 = random.nextInt();
            testData.string1 = RandomStringUtils.random(100);
            testData.string2 = RandomStringUtils.random(100);
            testDatas[i] = testData;
        }

        System.out.println("Generated test data");
        return testDatas;
    }

    public abstract Thing createThingObject();

    public static class TestData {
        int int1;
        int int2;
        String string1;
        String string2;
    }
}
