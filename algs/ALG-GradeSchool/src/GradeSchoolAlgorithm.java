/**
 *
 * @author ...
 */
public class GradeSchoolAlgorithm extends BigIntegerMultiplicationAbsAlgorithm {

  // TODO: write adequate signiture of the execute method 
  //       (as defined in BigIntegerMultiplicationAbsAlgorithm.java)   
  //@Override
  //public void execute(...) {
  //  .... write method body
  //}
    @Override
    public void execute(byte[] first, byte[] second, byte[] result) {
        byte[] done = multiply(first, second);
        
        for (int i = 0; i < result.length; i++) {
            result[result.length - 1 - i] = done[done.length - 1 - i];
        }
        
    }
    
    
    public static byte[] multiply(byte[] f1, byte[] f2) {
        //System.out.println("Multiply");

        int[][] t = new int[f2.length][f1.length + f2.length];
        int dolzina = f1.length + f2.length;

        for (int i = 0; i<f2.length; i++) {
            int prenos = 0;

            for (int j = 1; j<f1.length + 1; j++) {

                int f2_i = 0xFF&f2[i];
                int f1_j = 0xFF&f1[f1.length - j];
                int produkt = f2_i * f1_j;
                int trenutniPrenos = prenos;
                prenos = 0;

                while (produkt >= 256) {
                    prenos++;
                    produkt = produkt-256;
                }
                int vstavi = produkt + trenutniPrenos;
                if (vstavi >= 256) {
                    prenos++;
                    vstavi = vstavi-256;
                }
                t[i][f1.length - j + i + 1] = vstavi;
            }
            t[i][0 + i] = prenos;
        }

        /*
        for (int i = 0; i<t.length; i++) {
            System.out.println();
            for (int j = 0; j<t[i].length; j++) {
                System.out.print(t[i][j]);
                System.out.print(" ");
            }
        }*/

        int[] result = new int [dolzina];
        int prenos = 0;

        for (int j = dolzina-1; j>= 0; j--) {
            result[j] = prenos;
            prenos = 0;
            for (int i = f2.length-1; i>= 0; i--) {
                result[j] = result[j] + t[i][j];

                while (result[j] >= 256) {
                    result[j]-=256;
                    prenos++;
                }
            }
        }


        byte[] res = new byte [dolzina];
        for (int i = 0; i < result.length; i++) {
            res[i] = (byte)result[i];
        }
        return res;
    }
}