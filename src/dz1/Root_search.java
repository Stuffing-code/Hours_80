package hours_80.dz1;

public class Root_search {
    //    (a*(x**2)) + (b*x) + c == 0
    public static void solveAnyEquitation(int a, int b, int c) {

        if (a == 0 && b == 0 && c == 0) {
            System.out.println("Root_search: solveAnyEquitation(): result any number");
        } else if (a != 0) {
            solveRootEquitation(a, b, c);
        } else {
            solveLinearEquitation(b, c);
        }
    }

//    Если уравнение квадратное
    public static void solveRootEquitation(int a, int b, int c) {
        int discriminant = ((b * b) - (4 * a * c));
        int result_1 = (int) ((-b + Math.sqrt(discriminant)) / (2 * a));
        int result_2 = (int) ((-b - Math.sqrt(discriminant)) / (2 * a));
        if (discriminant == 0) {
            System.out.println("Root_search: solveRootEquitation(): result = " + result_1);
        } else if (discriminant > 0) {
            System.out.println("Root_search: solveRootEquitation(): result's = " + result_1 + ", " + result_2 + ".");
        } else {
            System.out.println("Root_search: solveRootEquitation(): No rational solution");
        }
    }

//    При a == 0
    public static void solveLinearEquitation(int b, int c) {
        if (b != 0) {
            double result = ((double) -c / b);
            System.out.println("Root_search: solveLinearEquitation(): " + result);
        } else {
            System.out.println("Root_search: solveLinearEquitation(): No rational solution, cannot be divisible by 0");
        }
    }

}
