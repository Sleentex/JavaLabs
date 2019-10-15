package Lab0;

import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String [] args) {
        /*Variant18 variant18 = new Variant18();
        System.out.println(variant18.inputOutputTask(1, 3, 5));
        System.out.println(variant18.integerTask(23000));
        System.out.println(variant18.booleanTask(2, 4, 4));
        System.out.println(variant18.conditionTask(2, 3, 2));
        System.out.println(variant18.switchTask(100));
        System.out.println(variant18.forTask(2, 3));
        System.out.println(variant18.whileTask(1675));
        int[] array = {10, 11, 15, 20, 1, 7, 6, 7, 10, 5};
        System.out.println(variant18.arrayTask(array));
        int[][] arrayMatrix =  { {10, 11, 2},
                            {15, 20, 0},
                            {1, 6, 100},
                            {10, 5, 10} };
        System.out.println(variant18.matrixTask(arrayMatrix, 1));
        System.out.println(arrayMatrix.length);
        System.out.println(arrayMatrix[0].length);
        System.out.println(arrayMatrix[0][2]);
        */
        HashSet myHashSet = new HashSet();
        myHashSet.add(1);
        myHashSet.add(23);
        myHashSet.add(45);
        myHashSet.add(12);

        TreeSet myTreeSet = new TreeSet();
        myTreeSet.addAll(myHashSet);
        System.out.println(myTreeSet);






    }

}