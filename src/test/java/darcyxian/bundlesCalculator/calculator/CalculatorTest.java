package darcyxian.bundlesCalculator.calculator;

import darcyxian.bundlesCalculator.bundleFormatsMap.BundleFormatsMap;
import darcyxian.bundlesCalculator.output.Output;
import darcyxian.bundlesCalculator.toolsBarn.ToolsBarn;
import manifold.ext.rt.api.Jailbreak;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
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
    private BundleFormatsMap bundleFormatsMap;

    @Mock
    private ToolsBarn toolsBarn;

    @Jailbreak
    @InjectMocks
    Calculator calculator;

    @BeforeEach
      public void setup(){
       bundles.add(10);
       bundles.add(5);
       descBundles.addAll(bundles);

       complexBundles.add(3);
       complexBundles.add(5);
       complexBundles.add(9);
       descComplexBundles.addAll(complexBundles);
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
    public void shouldReturnANestedMapGivenALegalList(){
        List<Integer> inputPosts = new ArrayList<>();
        inputPosts.add(15);
        List<String> inputCodes = new ArrayList<>();
        inputCodes.add("img");

        List<String> inputList = new ArrayList<>();
        inputList.add("15");
        inputList.add("img");

        System.out.println(inputList);
        when(toolsBarn.createFormatCodeList(any())).thenReturn(inputCodes);
        when(toolsBarn.createPostList(any())).thenReturn(inputPosts);
        when(bundleFormatsMap.getDescendingBundles(any())).thenReturn(bundles);
        Map<String, Map<Integer, Integer>> returnMap = calculator.getCalculationResultMap(inputList);

        assertEquals(true,returnMap.containsKey("img"));
        assertEquals(1,returnMap.get("img").get(Integer.valueOf(10)));
        assertEquals(1,returnMap.get("img").get(5));
    }





}
