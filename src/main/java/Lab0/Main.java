package Lab0;

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

        int[][] arrayMatrix =  { {1, 2},
                                 {4, 5}
                                 };

        int[] resultArray = new int[arrayMatrix.length * arrayMatrix.length];
        int r = 0, k = 0, e = arrayMatrix.length - 1;
        int flag = 1, o = arrayMatrix.length;
        for(int a = 0; a < arrayMatrix.length; a++) {

            for(int i = 0; i < o; i++)
                resultArray[r++] = arrayMatrix[i][k];

            for(int j = flag; j < arrayMatrix.length; j++)
                resultArray[r++] = arrayMatrix[e][j];

            flag++;
            k++;
            e--;
            o--;
        }

        for(int v: resultArray){
            System.out.println(v);
        }
    }

}