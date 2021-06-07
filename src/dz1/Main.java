package hours_80.dz1;

import static hours_80.dz1.Root_search.solveAnyEquitation;

public class Main {
    public static void main(String[] args) {

        solveAnyEquitation(1, 12, 36);
        solveAnyEquitation(-1, -2, 15);
        solveAnyEquitation(0, 0, 0);
        solveAnyEquitation(1, -2, -3);
        solveAnyEquitation(1, 0, 0);
        solveAnyEquitation(0, 0, 1);


        for (int a=-5; a<5; a++)
            for (int b=-5; b<5; b++)
                for (int c=-5; c<5; c++)
                    solveAnyEquitation(a,b,c);
    }


}
