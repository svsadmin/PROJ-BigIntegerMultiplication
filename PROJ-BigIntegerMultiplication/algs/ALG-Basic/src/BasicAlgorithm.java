/**
 *
 * @author ...
 */
import java.math.BigInteger;

public class BasicAlgorithm extends BigIntegerMultiplicationAbsAlgorithm {
  // TODO: write adequate signiture of the execute method 
  //       (as defined in BigIntegerMultiplicationAbsAlgorithm.java)   
  //@Override
  //public void execute(...) {
  //  .... write method body
  //} 
    public void execute(byte[] first, byte[] second, byte[] result) {
        BigInteger f = new BigInteger(first);
        BigInteger s = new BigInteger(second);
        BigInteger r = f.multiply(s);
        byte[] nov = (new BigInteger(r.toString(16), 16).toByteArray());
        
        for (int i = 0; i < result.length; i++) {
            result[i] = nov[i];
        }
    }
}