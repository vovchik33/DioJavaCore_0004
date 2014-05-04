package edu.dio.lesson4;

import java.util.Arrays;

/**
 * Created by Vladimir V. Kravchenko on 04.05.2014.
 */
public class ArrayWrapper {
    private int[] sourceData;
    private int[] additionalData;

    private int[] data;
    private int[] indexes;
    private boolean indexed=false;

    public ArrayWrapper() {
        this(new int[0], new int[0]);
    }

    public ArrayWrapper(int[] sourceData, int[] additionalData) {
        setSourceData(sourceData);
        setAdditionalData(additionalData);
    }

    public int[] getSourceData() {
        return sourceData;
    }

    public void setSourceData(int[] data) {
        indexed=false;
        this.sourceData = makeCopy(data);
    }

    public int[] getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(int[] data) {
        indexed=false;
        this.additionalData = makeCopy(data);
    }

    public int[] getMerge() {
        calculate();
        int[] result=new int[getUniqueCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE || indexes[i]==TypeOfElement.DUPLICATED) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    public int[] getInnerJoin() {
        calculate();
        int[] result=new int[getDuplicatedCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.DUPLICATED) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    public int[] getOuterJoin() {
        calculate();
        int[] result=new int[getNotRepeatableCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    private void calculate() {
        if (!indexed) {
            System.out.println("Calculating...");
            data = joinArrays();
            indexDataArray();
            System.out.println("Calculation completed!");
        }
    }

    private int getUniqueCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE || indexes[i]==TypeOfElement.DUPLICATED) result++;
        }
        return result;
    }

    private int getDuplicatedCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.DUPLICATED) result++;
        }
        return result;
    }

    private int getNotRepeatableCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE) result++;
        }
        return result;
    }

    private int[] joinArrays() { // join with external array
        if (null == sourceData && null == additionalData) return new int[0];
        if (null == sourceData) return sourceData;

        int[] temp=new int[sourceData.length+additionalData.length];
        System.arraycopy(sourceData, 0, temp, 0, sourceData.length);                                    // copy first array
        System.arraycopy(additionalData, 0, temp, sourceData.length, additionalData.length);        // copy second array
        return temp;
    }

    private void indexDataArray() {
        indexes = new int[data.length];
        for (int i = 0; i < data.length-1; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (indexes[j]==TypeOfElement.UNIQUE) {
                    if (data[j]==data[i]) {
                        indexes[i]=TypeOfElement.DUPLICATED;
                        indexes[j]=TypeOfElement.DUPLICATE;
                    }
                }
            }
        }
        indexed = true;
    }

    private int[] makeCopy(int[] data){
        if (null==data) return new int[0];

        // first way
        return  Arrays.copyOf(data, data.length);

        // second way
        // int[] temp = new int[data.length];
        // System.arraycopy(data, 0, temp, 0, data.length);
        // return temp;

        // third way
        // return data.clone();

        // forth way
           // return Arrays.copyOfRange(data, 0, data.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayWrapper)) return false;

        ArrayWrapper that = (ArrayWrapper) o;

        if (!Arrays.equals(data, that.data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? Arrays.hashCode(data) : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArrayWrapper{");
        sb.append("data=").append(Arrays.toString(data));
        sb.append("indexes=").append(Arrays.toString(indexes));
        sb.append('}');
        return sb.toString();
    }
}
