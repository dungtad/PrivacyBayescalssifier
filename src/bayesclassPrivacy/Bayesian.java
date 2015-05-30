package bayesclassPrivacy;
public class Bayesian {
    
    
        public static int[] Count_classVL(String a[], String b, int n) {
                        int d1[] = new int[n];
                        for (int i = 0; i < n; ++i) {
                            if (a[i].equals(b)) {
                                d1[i] = 1;
                            } else {
                                d1[i] = 0;
                            }
                            // System.out.println("\ndi "+ d[i][0]);
                        }
                        return d1;
                    }

         public static int[][] Count_D(String tableDB[][], String stadin[], String b, int column, int num) {
                int d[][] = new int[num][column];

                for (int j = 0; j < num; ++j) {
                    for (int i = 0; i < column - 1; ++i) {

                        if (tableDB[j][i].equals(stadin[i]) && tableDB[j][column - 1].equals(b)) {
                            d[j][i] = 1;
                        } else {
                            d[j][i] = 0;
                        }
                        if (tableDB[j][column - 1].equals(b)) {
                            d[j][column - 1] = 1;
                        } else {
                            d[j][column - 1] = 0;
                        }
                    }
                }
                return d;
            }

    
}