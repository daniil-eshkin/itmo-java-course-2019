package expression;

import expression.exceptions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        CommonExpression a = parser.parse("1000000*x*x*x*x*x/(x-1)");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter x: ");
        int x = in.nextInt();
        System.out.println("1000000*x*x*x*x*x/(x-1)=" + a.evaluate(x));
    }
}
