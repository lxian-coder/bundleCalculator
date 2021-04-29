package darcyxian.bundle_calculator.toolsBarn;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Darcy Xian  29/4/21  8:42 pm      Bundle_Calculator
 */

class ToolsBarnTest {

   private ToolsBarn toolsBarn = new ToolsBarn();

    @Test
    void isStrToNum() {
        String can = "123";
        String canNot = "abc";

        boolean check = toolsBarn.isStrToNum(can);
        assertEquals(true,check);
        boolean check2 = toolsBarn.isStrToNum(canNot);
        assertEquals(false,check2);
    }
}