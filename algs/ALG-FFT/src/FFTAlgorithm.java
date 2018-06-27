import java.math.BigInteger;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
/**
 *
 * @author ...
 */
public class FFTAlgorithm extends BigIntegerMultiplicationAbsAlgorithm {
    
    @Override
    public void execute(byte[] first, byte[] second, byte[] result) {
        double[][] prepared = prepareForFourier(first, second);
        
        int[] done = multiply(prepared[0], prepared[1]);
        
        for (int i = 0; i < result.length; i++) {
            result[result.length - i - 1] = (byte)done[i];
        }
        
    }
    
    static int [] multiply (double[] first, double[] second) {

        FastFourierTransformer f1 = new FastFourierTransformer(DftNormalization.STANDARD);
        Complex [] prvi = f1.transform(first, TransformType.FORWARD);
        Complex [] drugi = f1.transform(second, TransformType.FORWARD);

        Complex[] zmnozek = new Complex[prvi.length];

        for (int i= 0; i<prvi.length; i++) {
            zmnozek[i] = prvi[i].multiply(drugi[i]);
        }

        Complex[] z = f1.transform(zmnozek, TransformType.INVERSE);


        for (int i = 0; i < z.length; i++) {
            System.out.println(z[i].getReal());
            System.out.println(z[i].getImaginary());
        }

        int [] rezultat = new int[z.length];
        int prenos = 0;
        for (int i = 0; i <rezultat.length; i++) {
            int trenutniPrenos = prenos;
            prenos = 0;

            double koeficient = 0;
            if (i < z.length) {
                koeficient = z[i].getReal();
            }

            while (koeficient >= 256) {
                koeficient-=256;
                prenos++;
            }

            double pristevek = koeficient + trenutniPrenos;
            while (pristevek >= 256) {
                pristevek-=256;
                prenos++;
            }

            rezultat[i] = (byte)(int)pristevek;
        }

        return rezultat;
    }
    
    static double [][] prepareForFourier (byte[] first, byte[] second) {

        //For FFT to work the length of arrays must be 2^n
        int power = 0;
        while (Math.pow(2, power) < first.length) {
            power++;
        }
        int first_len = (int)Math.pow(2, power);

        power = 0;
        while (Math.pow(2, power) < second.length) {
            power++;
        }
        int second_len = (int)Math.pow(2, power);

        //See which one is longer, double the length
        int len = Math.max(first_len, second_len) * 2;
        System.out.println(first.length);


        double prepared[][] = new double[2][len];
        for (int i = 0; i < first.length; i++) {
            prepared[0][i] = 0xFF&first[first.length - 1 - i];
        }
        for (int i = 0; i < second.length; i++) {
            prepared[1][i] = 0xFF&second[second.length - 1 - i];
        }

        return prepared;
    }

  // TODO: write adequate signiture of the execute method 
  //       (as defined in BigIntegerMultiplicationAbsAlgorithm.java)   
  //@Override
  //public void execute(...) {
  //  .... write method body
  //} 
    
}