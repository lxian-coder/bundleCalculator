package darcyxian.bundlesCalculator.calculator;

import darcyxian.bundlesCalculator.bundleFormatsMap.BundleFormatsMap;
import darcyxian.bundlesCalculator.output.Output;
import darcyxian.bundlesCalculator.toolsBarn.ToolsBarn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Darcy Xian  27/4/21  5:35 pm      Bundle_Calculator
 */
@Component
@AllArgsConstructor
@Slf4j
public class Calculator {
    private BundleFormatsMap bundleFormatsMap;
    private  Output output;
    private ToolsBarn toolsBarn;

    public Map<String, Map<Integer, Integer>> getCalculationResultMap(List<String> inputList) {
        Map<String, Map<Integer, Integer>> calculationResultMap = new HashMap<>();
        List<String> orderedFormatCodes = toolsBarn.createFormatCodeList(inputList);
        List<Integer> orderedPosts = toolsBarn.createPostList(inputList);

        for (int i = 0; i < orderedFormatCodes.size(); i++) {
            Set<Integer> descendingBundles = bundleFormatsMap.getDescendingBundles(orderedFormatCodes.get(i));
            Map<Integer, Integer> bundlesBreakDown = getCalculationResultMapController(descendingBundles, orderedPosts.get(i), orderedFormatCodes.get(i));
            calculationResultMap.put(orderedFormatCodes.get(i), bundlesBreakDown);
        }
        // System.out.println("calculationMap :"+calculationResultMap);
        output.displayTheFinalResult(calculationResultMap, orderedFormatCodes, orderedPosts);
        return calculationResultMap;
    }

    public Map<Integer,Integer> getCalculationResultMapController(Set<Integer> descendingBundles, Integer posts, String code){
        boolean failedMap = false;
        int changedPosts = 0;
      Map<Integer,Integer> bundleBreakDownMap;
      bundleBreakDownMap = getBundleBreakdownMap( descendingBundles, posts, code);
        failedMap = bundleBreakDownMap.containsKey(-1);
        // here, we still can not get the posts bundled, we need to add 1 to posts to try to bundle.
        if(failedMap) {
            for(int i = 1;i <= descendingBundles.iterator().next().intValue();i++) {
                changedPosts = posts.intValue() + i ;
                bundleBreakDownMap = getBundleBreakdownMapCalculator(descendingBundles, changedPosts);
                failedMap = bundleBreakDownMap.containsKey(-1);
                if (!failedMap) break;
            }
        }

        if(changedPosts != 0){
            System.out.println("Please notice: " + code + " basis is " + descendingBundles + " and " + posts + " posts you entered can not be bundled without remainder.\n" +
                    "so in order to get smallest bundles, your posts has been bundled with " + changedPosts + ".\n" +
                    "Your are free to re-enter your order if you want.\n");
        }

        return bundleBreakDownMap;
    }


    public Map<Integer,Integer> getBundleBreakdownMap(Set<Integer> descendingBundles, Integer posts, String code) {
        int bundlesSize = descendingBundles.size();

        Map<Integer, Integer> bundleBreakDownMap = new HashMap<>();
        Iterator<Integer> it = descendingBundles.iterator();
        Set<Integer> copyDescendingBundles = new HashSet<>(descendingBundles);
        SortedSet<Integer> smallDescendingBundles = new TreeSet<Integer>(Collections.reverseOrder());
        // get a decendingBundles copy
        smallDescendingBundles.addAll(copyDescendingBundles);
        for (int i = 0; i < bundlesSize - 1; i++) {

            bundleBreakDownMap = getBundleBreakdownMapCalculator(smallDescendingBundles, posts);
            // check if the return is a successful map
            if (bundleBreakDownMap.containsKey(-1)) {

                // faild. posts  can not be exactly divided
                Integer maxBundles = it.next();

                // remove max bundle and calculate again.
                smallDescendingBundles.remove(maxBundles);
            } else {
                // success, finish and return result map
                return bundleBreakDownMap;
            }
        }
        return bundleBreakDownMap;
    }

    public Map<Integer, Integer> getBundleBreakdownMapCalculator(Set<Integer> descendingBundles, Integer posts) {

        int postsInt = posts.intValue();
        int bundle = 0;
        Map<Integer, Integer> bundleBreakDownMap = new HashMap<>();
        Iterator<Integer> it = descendingBundles.iterator();

        for (int i = 0; i < descendingBundles.size(); i++) {
            bundle = it.next().intValue();
            if (postsInt / bundle > 0) {
                bundleBreakDownMap.put(bundle, postsInt / bundle);
            }
            if (postsInt % bundle == 0) {
                postsInt = 0;
                break;
            } else {
                postsInt = postsInt % bundle;
            }
        }
        // return a different map;
        if (postsInt != 0) {
            int wrong = -1;
            bundleBreakDownMap = new HashMap<>();
            bundleBreakDownMap.put(wrong, postsInt);
            return bundleBreakDownMap;
        }
        return bundleBreakDownMap;
    }
}

