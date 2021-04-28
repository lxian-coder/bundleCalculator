package darcyxian.bundle_calculator.Calculator;

import darcyxian.bundle_calculator.buddleFormats.QueryBundleFormatsMap;
import darcyxian.bundle_calculator.calculator.Calculator;
import darcyxian.bundle_calculator.output.Output;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Darcy Xian  28/4/21  7:03 pm      Bundle_Calculator
 */
@ExtendWith(MockitoExtension.class)
public class CalculatorTest {

    private  Set<Integer> bundles = new HashSet<>();
    private  SortedSet<Integer> descBundles = new TreeSet<>(Collections.reverseOrder());
    private  Set<Integer> complexBundles = new HashSet<>();
    private  SortedSet<Integer> descComplexBundles = new TreeSet<>(Collections.reverseOrder());

    @Mock
    private  QueryBundleFormatsMap queryBundleFormatsMap;

    @Mock
    private  Output output;
    @InjectMocks
    Calculator calculator;

    @BeforeEach
      public  void setup(){
       bundles.add(10);
       bundles.add(5);
       descBundles.addAll(bundles);

       complexBundles.add(3);
       complexBundles.add(5);
       complexBundles.add(9);
       descComplexBundles.addAll(complexBundles);
    }
    @Test
    public void shouldCheckWhetherAStringCanBeConvertedToInteger(){
          String can = "123";
          String canNot = "abc";

          boolean check = calculator.isStrToNum(can);
          assertEquals(true,check);
          boolean check2 = calculator.isStrToNum(canNot);
          assertEquals(false,check2);
    }
    @Test
    public void shouldReturnMapWithFailedContentGivenPostsFailedBundle(){

        Integer postsCannot = 16;
        Map<Integer,Integer> testResult = calculator.getBundleBreakdownMapCalculator(descBundles,postsCannot);

        assertEquals(true,testResult.containsKey(-1));
        assertEquals(1,testResult.get(-1));
    }
    @Test
    public void shouldReturnGoodMapGivenPostsBundledSuccessfully(){

        Integer posts = 15;
        Map<Integer,Integer> testResult = calculator.getBundleBreakdownMapCalculator(descBundles,posts);

        assertEquals(true,testResult.containsKey(10));
        assertEquals(1,testResult.get(10));

        assertEquals(true,testResult.containsKey(5));
        assertEquals(1,testResult.get(5));
    }
    @Test
    public void shouldReturnGoodMapGivenTheCalculatorSuccess(){
         Integer posts = 15;
         String code = "img";
         Map<Integer,Integer> returnMap = calculator.getBundleBreakdownMap(descBundles,posts,code);

        assertEquals(true,returnMap.containsKey(10));
        assertEquals(1,returnMap.get(10));

        assertEquals(true,returnMap.containsKey(5));
        assertEquals(1,returnMap.get(5));
    }
    @Test
    public void shouldTrimBundleSetTryAgainGivenFirstCalculatorFailed(){
        Integer posts = 13;
        String code = "img";
        Map<Integer,Integer> returnMap = calculator.getBundleBreakdownMap(descComplexBundles,posts,code);

        assertEquals(true,returnMap.containsKey(5));
        assertEquals(2,returnMap.get(5));

        assertEquals(true,returnMap.containsKey(3));
        assertEquals(1,returnMap.get(3));
    }
    @Test
    public void shouldChangePostsToTryGivenCalculatorTotallyFailed(){
        Integer posts = 16;
        String code = "img";
        Map<Integer,Integer> returnMap = calculator.getBundleBreakdownMap(descBundles,posts,code);

        assertEquals(true,returnMap.containsKey(10));
        assertEquals(2,returnMap.get(10));
    }
    @Test
    public void shouldReturnANestedMapGivenALegalList(){
        List<String> inputList = new ArrayList<>();
        inputList.add("15");
        inputList.add("img");
        System.out.println(inputList);

        when(queryBundleFormatsMap.getDescendingBundles(anyString())).thenReturn(descBundles);
        Map<String, Map<Integer, Integer>> returnMap = calculator.calculateTheInput(inputList);

        assertEquals(true,returnMap.containsKey("img"));
        assertEquals(1,returnMap.get("img").get(10));
        assertEquals(1,returnMap.get("img").get(5));
    }





}
