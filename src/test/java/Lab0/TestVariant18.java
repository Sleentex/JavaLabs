package Lab0;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestVariant18 {

    private Variant18 variant18 = new Variant18();

//Start InputOutput Test////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] inputOutputTaskProvider() {
        return new Object[][] { {1, 3, 5, 4}, {4, 7, 10, 9} };
    }

    @Test(dataProvider = "inputOutputTaskProvider")
    public void inputOutputTaskTest(int a, int c, int b, int expected) {
        Assert.assertEquals(variant18.inputOutputTask(a, c, b), expected);
    }

//Start Integer Test////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] integerTaskProvider() {
        return new Object[][]{{23000, 3}, {14540, 4}, {101000, 1}, {10000, 0}};
    }

    @Test(dataProvider = "integerTaskProvider")
    public void integerTaskTest(int number, int expectedNumber) {
        Assert.assertEquals(variant18.integerTask(number), expectedNumber);
    }

//Start Boolean Test////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] booleanTaskProvider() {
        return new Object[][] {{1, 1, 2, true}, {1, 2, 1, true}, {2, 1, 1, true}, {1, 1, 1, true}, {1, 2, 3, false}};
    }

    @Test(dataProvider = "booleanTaskProvider")
    public void booleanTaskTest(int a, int b, int c, boolean expectedResult) {
        Assert.assertEquals(variant18.booleanTask(a, b, c), expectedResult);
    }

//Start Condition Test//////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] conditionTaskProvider() {
        return new Object[][] {{1, 1, 2, 3}, {1, 2, 1, 2}, {2, 1, 1, 1}};
    }

    @Test(dataProvider = "conditionTaskProvider")
    public void conditionTaskTest(int first, int second, int third, int expectedNumber) {
        Assert.assertEquals(variant18.conditionTask(first, second, third), expectedNumber);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void negativeConditionProvider() {
        new Variant18().conditionTask(1, 1, 1);
    }

//Start Switch Test/////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] switchTaskProvider() {
        return new Object[][] { {100, "One hundred"}, {105, "One hundred five"}, {953, "Nine hundred fifty three"}};
    }
    @Test(dataProvider = "switchTaskProvider")
    public void switchTaskTest(int number, String expectedString) {
        Assert.assertEquals(variant18.switchTask(number), expectedString);
    }

//Start For Test////////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] forTaskProvider() {
        return new Object[][] {{1, 1, 0}, {2, 2, 3}, {2, 3, -5}};
    }

    @Test(dataProvider = "forTaskProvider")
    public void forTaskTest(double A, int N, double expectedSum) {
        Assert.assertEquals(variant18.forTask(A, N), expectedSum);
    }

//Start While Test//////////////////////////////////////////////////////////////////////////////////////////////////////

    @DataProvider
    public Object[][] whileTaskProvider() {
        return new Object[][] { {1675, 4, 19}, {100, 3, 1} };
    }

    @Test(dataProvider = "whileTaskProvider")
    public void whileTaskTest(int N, int expectedQuantity, int expectedSum) {
        //Pair result = new Pair(expectedQuantity, expectedSum);
        Assert.assertEquals(variant18.whileTask(N), new Pair(expectedQuantity, expectedSum) );
    }

//Start Array Test//////////////////////////////////////////////////////////////////////////////////////////////////////

   @DataProvider
   public Object[][] arrayTaskProvider() {
        return new Object[][] { {new int[] {10, 11, 15, 20, 1, 7, 6, 7, 10, 5}, 1},
                                {new int[] {4, 5, 3, 2, 1, 6, 10, 28, 10, 3}, 2} };
   }
   @Test(dataProvider = "arrayTaskProvider")
    public void arrayTaskTest(int[] array, int expectedNum) {
        Assert.assertEquals(variant18.arrayTask(array), expectedNum);
   }

//Start Matrix Test//////////////////////////////////////////////////////////////////////////////////////////////////////
    @DataProvider
    public Object[][] matrixTaskProvider() {
        return new Object[][] { {new int [][]{ {1, 2, 3}, {4, 5, 6}, {7, 8, 9} }, 0, 12, 28},
                {new int[][] {{2, 5, 7}, {5, 1, 3}, {1, 9, 0} }, 1, 15, 45}};
    }

    @Test(dataProvider = "matrixTaskProvider")
    public void matrixTaskTest(int[][] array, int k, int expectedSum, int expectedMult) {
        Assert.assertEquals(variant18.matrixTask(array, k), new Pair(expectedSum, expectedMult));
    }
}
