package edu.dio.lesson4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testNullArrays() throws Exception {
        int[] sourceArray1=null;
        int[] sourceArray2=null;

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[0]);
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[0]);
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[0]);
    }

    @Test
    public void testEmptyArrays() throws Exception {
        int[] sourceArray1=new int[0];
        int[] sourceArray2=new int[0];

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[0]);
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[0]);
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[0]);
    }

    @Test
    public void testDuplicateArrays() throws Exception {
        int[] sourceArray1=new int[]{1,1,1,1,1,1,1,1};
        int[] sourceArray2=new int[]{1,1,1,1,1,1,1,1};

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[]{1});
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[]{1});
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[0]);
    }

    @Test
    public void testNormalArrays() throws Exception {
        int[] sourceArray1=new int[]{1,2};
        int[] sourceArray2=new int[]{1,3};

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[]{1,2,3});
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[]{1});
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[]{2,3});

        arrayWrapper.setSourceData(new int[]{1,5,4,23,65,32,78});
        arrayWrapper.setAdditionalData(new int[]{3,5,24,54,1,2,34,45,32});
        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[]{1,5,4,23,65,32,78, 3,24,54,2,34,45});

        arrayWrapper.setSourceData(new int[]{1,5,4,23,65,32,78});
        arrayWrapper.setAdditionalData(new int[]{3,5,24,4,1,2,34,45,32,5});
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[]{1,5,4,32});

        arrayWrapper.setSourceData(new int[]{1,5,4,23,65,32,78});
        arrayWrapper.setAdditionalData(new int[]{3,5,24,4,1,2,34,45,32,5});
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[]{23,65,78,3,24,2,34,45});
    }

    @Test
    public void testEqualArrays() throws Exception {
        int[] sourceArray1=new int[]{1,2};
        int[] sourceArray2=new int[]{1,2};

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[]{1,2});
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[]{1,2});
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[]{});
    }

    @Test
    public void testNotSortedEqualArrays() throws Exception {
        int[] sourceArray1=new int[]{1,2};
        int[] sourceArray2=new int[]{2,1};

        ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);

        Assert.assertArrayEquals(arrayWrapper.getMerge(), new int[]{1,2});
        Assert.assertArrayEquals(arrayWrapper.getInnerJoin(), new int[]{1,2});
        Assert.assertArrayEquals(arrayWrapper.getOuterJoin(), new int[]{});
    }
}