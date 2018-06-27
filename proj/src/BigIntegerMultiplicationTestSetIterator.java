import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import si.fri.algotest.entities.EVariable;
import si.fri.algotest.entities.VariableType;
import si.fri.algotest.entities.EResult;
import si.fri.algotest.entities.ETestSet;
import si.fri.algotest.entities.TestCase;
import si.fri.algotest.execute.DefaultTestSetIterator;
import si.fri.algotest.global.ATLog;
import si.fri.algotest.tools.ATTools;
import si.fri.algotest.global.ErrorStatus;


/**
 *
 * @author admin
 */
public class BigIntegerMultiplicationTestSetIterator extends DefaultTestSetIterator {
   
  
  @Override
  public TestCase getCurrent() {
    if (currentInputLine == null) {
      ErrorStatus.setLastErrorMessage(ErrorStatus.ERROR, "The input is not valid!");
      return null;
    }
        
    // sort-project specific: line contains at least 4 fileds: testName, n1, n2 and result
    String [] fields = currentInputLine.split(":"); 
    if (fields.length < 4) {
      reportInvalidDataFormat("to few fields");
      return null;
    }
    
    String testName = fields[0];
    String group = fields[1];
    String faktor_1 = fields[2];
    String faktor_2 = fields[3];
    String result = fields[4];
    /**
    int probSize;
    try {
      probSize = Integer.parseInt(fields[1]);
    } catch (Exception e) {
      reportInvalidDataFormat("'n' is not a number");
      return null;
    }
    * 
    */
    
    // do not delete the following lines; test-id parameter is compulsory
    
    EVariable testIDVar = EResult.getTestIDParameter("Test-" + Integer.toString(lineNumber));
    EVariable parameter1 = new EVariable("Test",  "Test name",                    VariableType.STRING, testName);
    EVariable parameter0 = new EVariable("N",     "The size of the text",           VariableType.INT,    3);
    EVariable parameter2 = new EVariable("Factor1",     "First factor",           VariableType.STRING, faktor_1);
    EVariable parameter3 = new EVariable("Factor2",     "Second factor",           VariableType.STRING, faktor_2);
    EVariable parameter4 = new EVariable("Result",     "Result of the multiplication",       VariableType.STRING, result);
    EVariable parameter5 = new EVariable("Group", "A name of a group of tests",   VariableType.STRING, group);
    
    BigIntegerMultiplicationTestCase tCase = new BigIntegerMultiplicationTestCase();
    //ID
    tCase.addParameter(testIDVar);
    //input parameters
    tCase.addParameter(parameter0);
    //tCase.addParameter(parameter2);
    //tCase.addParameter(parameter3);
    //tCase.addParameter(parameter4);
    //tCase.addParameter(parameter5);

    
    // TODO: evaluate input parameters and add them to the test case
    // ...
    
    int i = 0;
    switch (group) {
        case "INLINE":
            if (fields.length < 4) {
                reportInvalidDataFormat("to few fields");
                return null;
            }
            
            try {
                int a = 0;
                for (i = 2; i < 5; i++) {
                    for (int j = 0; j < fields[i].length(); j++) {
                        a = Integer.parseInt(fields[i].substring(j));
                    }
                }
            } catch (Exception e) {
                reportInvalidDataFormat("invalid type of inline data - data " + (i+1));
                return null;
            }
            break;
        
        case "FILE":
            break;
    }
    // 
    // Example:
    // EVariable parameter1 = new EVariable("N", VariableType.INT, getNFromInputLine(currentInputline));
    // tCase.addParameter(parameter1);
    //
    // General: 
    // EVariable parameter1 = new EVariable(<paramter_name>, <parameter_type>, <parameter_value>);
    // tCase.addParameter(parameter1);
    tCase.firstFactor = new BigInteger(new BigInteger(faktor_1).toString(16), 16).toByteArray();
    tCase.secondFactor = new BigInteger(new BigInteger(faktor_2).toString(16), 16).toByteArray();
    tCase.result = new BigInteger(new BigInteger(result).toString(16), 16).toByteArray();
    
    return tCase;
  }
}
 