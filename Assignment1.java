
import java.util.*;

class Assignment1 {

    public static void main(String[] args) {
        String num1 = "";
        String num2 = "";
        char continueStat;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Please enter the valid options:\nAddition:\nSubtraction:\nMultiplication:\nDivision:");
            int choice = scan.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("Enter the num1 and num2:");
                    num1 = scan.next();
                    num2 = scan.next();
                    System.out.println("Addition of " + num1 + "and " + num2 + " is: " + solveSum(num1, num2));
                    break;
                case 2:
                    System.out.println("Enter the num1 and num2:");
                    num1 = scan.next();
                    num2 = scan.next();
                    System.out.println("Subtraction of " + num1 + "and " + num2 + " is: " + solveSubtract(num1, num2));
                    break;
                case 3:
                    System.out.println("Enter the num1 and num2:");
                    num1 = scan.next();
                    num2 = scan.next();
                    System.out.println("Multiplication of " + num1 + "and " + num2 + " is: " + solveMul(num1, num2));
                    break;
                case 4:
                    System.out.println("Enter the num1 and num2:");
                    num1 = scan.next();
                    num2 = scan.next();
                    System.out.println("Division of " + num1 + "and " + num2 + " is: " + solveDiv(num1, num2));
                    break;
                case 5:
                    System.out.println("Enter the num1 and num2:");
                    num1 = scan.next();
                    num2 = scan.next();
                    System.out.println("Square of " + num1 + "and " + num2 + " is: " + solveSquare(num1, num2));
                    break;
                default:
                    System.out.println("Please try again,enter valid input.");
            }
            System.out.println("Do you wanted to exit then press 'y': ");
            continueStat = scan.next().charAt(0);
        } while (continueStat != 'y');

    }

    private static String solveSum(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        if (a.length() > b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int l1 = a.length();
        int l2 = b.length();
        StringBuilder result = new StringBuilder();
        String str1 = new StringBuilder(a).reverse().toString();
        String str2 = new StringBuilder(b).reverse().toString();
        int carry = 0;
        for (int i = 0; i < l1; i++) {
            int sum = (str1.charAt(i) - '0') + (str2.charAt(i) - '0') + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }
        for (int i = l1; i < l2; i++) {
            int sum = str2.charAt(i)-'0' + carry;//--------------------------------------giving the gqrbage value if not written -'0'//
            result.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) {
            result.append(carry);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private static String solveSubtract(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        if (a.length() < b.length() || a.length() == b.length() && a.compareTo(b) < 0) {
            //swap
            String temp = a;
            a = b;
            b = temp;
        }
        int l1 = a.length();
        int l2 = b.length();
        int borrow = 0;
        StringBuilder result = new StringBuilder();
        String str1 = new StringBuilder(a).reverse().toString();
        String str2 = new StringBuilder(b).reverse().toString();
        for (int i = 0; i < l1; i++) {
            int digit1 = str1.charAt(i) - '0';
            int digit2 = (i < l2) ? str2.charAt(i) - '0' : 0;
            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(diff);
        }
        return result.reverse().toString().replaceFirst("^0+(?!$)", "");
    }

    private static String solveMul(String a, String b) {
        if (a.isEmpty() || b.isEmpty()) {
            return "0";
        }
        if (a.equals("0") || b.equals("0")) {
            return "0";
        }
        if (a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }
        int length = a.length();
        while (b.length() < length) {
            b = "0" + b;
        }
        if (length == 1) {
            int result = Integer.parseInt(a) * Integer.parseInt(b);
            return Integer.toString(result);
        }
        if (length % 2 == 1) {
            a = "0" + a;
            b = "0" + b;
            length++;
        }
        //divide and conquer
        //formula used-------10^n*albl+10^n/2*[(al+ar)(bl+br)-albl-blbr]+arbr
        String al = a.substring(0, length / 2);
        String ar = a.substring(length / 2, length);
        String bl = b.substring(0, length / 2);
        String br = b.substring(length / 2, length);

        String albl = solveMul(al, bl);
        String arbr = solveMul(ar, br);
        String r = solveMul(solveSum(al, ar), solveSum(bl, br));

        String subresult = solveSubtract(r, solveSum(albl, arbr));

        subresult += "0".repeat(length / 2);
        albl += "0".repeat(length);

        String result = solveSum(solveSum(albl, subresult), arbr);

        return result.replaceFirst("^0+(?!$)", "");
    }

    private static String solveDiv(String a, String b) {
        return null;
    }

    private static String solveSquare(String a, String b) {
        return null;
    }
}
