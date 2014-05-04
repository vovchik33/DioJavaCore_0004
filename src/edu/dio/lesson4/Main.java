package edu.dio.lesson4;

import java.util.Arrays;

/**
 * Created by Vladimir V.Kravchenko on 04.05.2014.
 */
public class Main {
    public static void main(String[] args) {
        int[] sourceArray1=new int[]{1,2};
        int[] sourceArray2=new int[]{1,3};

        //ArrayWrapper arrayWrapper=new ArrayWrapper(sourceArray1, sourceArray2);
        ArrayWrapper arrayWrapper=new ArrayWrapper();
        arrayWrapper.setSourceData(sourceArray1);
        arrayWrapper.setAdditionalData(sourceArray2);


        System.out.println("Source Data" + Arrays.toString(arrayWrapper.getSourceData()));
        System.out.println("Additional Data " + Arrays.toString(arrayWrapper.getAdditionalData()));

        System.out.println("Merge Result " + Arrays.toString(arrayWrapper.getMerge()));
        System.out.println("Inner Join Result " + Arrays.toString(arrayWrapper.getInnerJoin()));
        System.out.println("Outer Join Result " + Arrays.toString(arrayWrapper.getOuterJoin()));
    }
}
