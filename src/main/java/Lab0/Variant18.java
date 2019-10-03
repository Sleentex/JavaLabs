package Lab0;

public class Variant18 {

    /**
     *
     * @param a integer
     * @param c integer between [a,b]
     * @param b integer
     * @return multiplication of the lengths of the segments AC and BC
     * @throws IllegalArgumentException if c < a || c > b
     */
    public int inputOutputTask(int a, int c, int b) throws IllegalArgumentException {
        if(c < a || c > b) throw new IllegalArgumentException("Number `c` must be between [a,b]");

        return (c - a) * (b - c);
    }

    /**
     *
     * @param number integer
     * @return number discharge thousands in the record of this number
     * @throws IllegalArgumentException if number < 1000
     */
    public int integerTask(int number) throws IllegalArgumentException {
        if (number < 1000)
            throw new IllegalArgumentException("The number must be greater than 999");
        number = number / 1000 % 10;
        return number;
    }

    /**
     *
     * @param a integer
     * @param b integer
     * @param c integer
     * @return true if there is at least one pair of matching
     */

    public boolean booleanTask(int a, int b, int c) {
        return (a == b || a == c || c == b);
    }

    /**
     *
     * @param first integer
     * @param second integer
     * @param third integer
     * @return serial number of a number other than the rest
     * @throws IllegalArgumentException if don't exist two same number
     */

    public int conditionTask(int first, int second, int third) throws IllegalArgumentException {
        if(first == second && first != third) return 3;
        if(first == third && first != second) return 2;
        if(second == third && second != first) return 1;
        throw new IllegalArgumentException("There should only be two identical numbers");
    }

    /**
     *
     * @param number integer
     * @return description of this number
     * @throws IllegalArgumentException if (number < 100 || number > 999)
     */
    public String switchTask(int number) throws IllegalArgumentException {
        if (number < 100 || number > 999)
            throw new IllegalArgumentException("Parameter must be 3 digit number!");

        String result = "";

        int firstDigit = number / 100;
        switch (firstDigit) {
            case 1: result = "One hundred"; break;
            case 2: result = "Two hundred"; break;
            case 3: result = "Three hundred"; break;
            case 4: result = "Four hundred"; break;
            case 5: result = "Five hundred"; break;
            case 6: result = "Six hundred"; break;
            case 7: result = "Seven hundred"; break;
            case 8: result = "Eight hundred"; break;
            case 9: result = "Nine hundred"; break;
        }

        int secondDigit = number % 100;
        if(secondDigit > 9 && secondDigit < 20) {
            secondDigit = number % 10;
            switch (secondDigit) {
                case 0: result += " ten"; break;
                case 1: result += " eleven"; break;
                case 2: result += " twelve"; break;
                case 3: result += " thirteen"; break;
                case 4: result += " fourteen"; break;
                case 5: result += " fifteen"; break;
                case 6: result += " sixteen"; break;
                case 7: result += " seventeen"; break;
                case 8: result += " eighteen"; break;
                case 9: result += " nineteen"; break;
            }
        }
        else {
            secondDigit /= 10;
            switch (secondDigit) {
                case 2: result += " twenty"; break;
                case 3: result += " thirty"; break;
                case 4: result += " forty"; break;
                case 5: result += " fifty"; break;
                case 6: result += " sixty"; break;
                case 7: result += " seventy"; break;
                case 8: result += " eighty"; break;
                case 9: result += " ninety"; break;
            }

            int thirdDigit = number % 10;
            switch (thirdDigit) {
                case 1: result += " one"; break;
                case 2: result += " two"; break;
                case 3: result += " three"; break;
                case 4: result += " four"; break;
                case 5: result += " five"; break;
                case 6: result += " six"; break;
                case 7: result += " seven"; break;
                case 8: result += " eight"; break;
                case 9: result += " nine"; break;
            }
        }
        return result;
    }

    /**
     *
     * @param A double
     * @param N int
     * @return sum from the formula 1 – A + A^2 – A^3 + … + (–1)^N*A^N.
     * @throws IllegalArgumentException if (N <= 0)
     */
    public double forTask(double A, int N) throws IllegalArgumentException {
        if(N <= 0) {
            throw new IllegalArgumentException("The number must be greater than 0");
        }
        int firstMember = 1;
        int secondMember = 1;
        double sum = 1;
        for(int i = 1; i <= N; i++) {
            firstMember *= -1;
            secondMember *= A;
            sum += firstMember * secondMember;
        }
        return sum;
    }

    /**
     *
     * @param N integer
     * @return quantity and sum of this number
     * @throws IllegalArgumentException if (N <= 0)
     */
    public Pair whileTask(int N) throws IllegalArgumentException {
        if(N <= 0) throw new IllegalArgumentException("The number N must be greater than 0");
        Pair result = new Pair();
        int sum = 0, quantity = 0;
        while (N > 0) {
            sum += N % 10;
            N /= 10;
            quantity++;
        }
        return new Pair(quantity, sum);
    }

    /**
     *
     * @param array is array of integer
     * @return the first element being smaller than the last
     * @throws IllegalArgumentException if (array.length > 10) or element of array equal to 0
     */
    public int arrayTask(int[] array) throws IllegalArgumentException {
        if(array.length > 10) throw new IllegalArgumentException("The length of array must be less than 10");

        for(int i = 0; i < array.length - 1; i++) {
            if (array[i] == 0) throw new IllegalArgumentException("The numbers of array can`t be 0");
            if (array[i] < array[array.length - 1]) return array[i];
        }
        return 0;
    }

    /**
     *
     * @param array is matrix of integer
     * @param k number of cols
     * @return sum and multiplication of elements from k-column
     * @throws IllegalArgumentException if (k < 0 || k > array[0].length)
     */
    public Pair matrixTask(int[][] array, int k) throws IllegalArgumentException {
        if(k < 0 || k > array[0].length)
            throw new IllegalArgumentException("Number `k` must be in the interval (0 <= K <= M)");
        int sum = 0, multiplication = 1;
        for(int i = 0; i < array.length; i++) {
           sum += array[i][k];
           multiplication *= array[i][k];
        }
        return new Pair(sum, multiplication);
    }
}
