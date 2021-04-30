package darcyxian.bundlesCalculator.toolsBarn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Darcy Xian  29/4/21  8:42 pm      Bundle_Calculator
 */

class ToolsBarnTest {

   private ToolsBarn toolsBarn = new ToolsBarn();
   List<String> inputString = new ArrayList<>();

   @BeforeEach
   public void setup() {
       inputString.add("12");
       inputString.add("img");
       inputString.add("2");
       inputString.add("flac");
   }
    @Test
    void isStrToNum() {
        String can = "123";
        String canNot = "abc";

        boolean check = toolsBarn.isStrToNum(can);
        assertEquals(true,check);
        boolean check2 = toolsBarn.isStrToNum(canNot);
        assertEquals(false,check2);
    }
    @Test
     void ShouPickOutAllStrToNumElementReturnAnIntegerListGivenAStringList(){

        List<Integer> targetList = new ArrayList<>();
        targetList.add(12);
        targetList.add(2);
       List<Integer> testList = toolsBarn.createPostList(inputString);

       assertEquals(testList,testList);
    }
    @Test
    void ShouPickOutAllCodeElementsReturnAStringListGivenAStringList(){
        List<String> targetCodeList = new ArrayList<>();
        targetCodeList.add("img");
        targetCodeList.add("flac");
        List<String> testCodeList = toolsBarn.createFormatCodeList(inputString);

        assertEquals(testCodeList,targetCodeList);
    }
}