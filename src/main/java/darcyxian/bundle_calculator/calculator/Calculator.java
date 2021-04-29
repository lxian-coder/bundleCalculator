package darcyxian.bundle_calculator.calculator;

import darcyxian.bundle_calculator.buddleFormatsMap.BundleFormatsMap;
import darcyxian.bundle_calculator.output.Output;
import darcyxian.bundle_calculator.toolsBarn.ToolsBarn;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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

    public Map<String, Map<Integer, Integer>> calculateTheInput(List<String> inputList) {
        Map<String, Map<Integer, Integer>> calculationResultMap = new HashMap<>();
        // put posts number entered by users into a list
        List<Integer> orderedPosts = inputList.stream()
                .filter(element -> toolsBarn.isStrToNum(element) == true)
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        // put format code entered by users into a list
        List<String> orderedFormatCodes = inputList.stream()
                .filter(element -> toolsBarn.isStrToNum(element) == false)
                .collect(Collectors.toList());

        for (int i = 0; i < orderedFormatCodes.size(); i++) {
            Set<Integer> descendingBundles = bundleFormatsMap.getDescendingBundles(orderedFormatCodes.get(i));
            Map<Integer, Integer> bundlesBreakDown = getBundleBreakdownMap(descendingBundles, orderedPosts.get(i), orderedFormatCodes.get(i));
            calculationResultMap.put(orderedFormatCodes.get(i), bundlesBreakDown);
        }
        // System.out.println("calculationMap :"+calculationResultMap);
        output.displayTheFinalResult(calculationResultMap, orderedFormatCodes, orderedPosts);
        return calculationResultMap;
    }

    public Map<Integer,Integer> getBundleBreakdownMap(Set<Integer> descendingBundles, Integer posts, String code) {
        int bundlesSize = descendingBundles.size();
        int changedPosts = 0;
        boolean failedMap;
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
        failedMap = bundleBreakDownMap.containsKey(-1);
        // here, we still can not get the posts bundled, we need to add 1 to posts to try to bundle.
          if(failedMap){

              changedPosts = posts.intValue() + 1 ;
              bundleBreakDownMap= getBundleBreakdownMapCalculator(descendingBundles, changedPosts);
          }

        System.out.println("Please notice: " + code + " basis is " + descendingBundles + " and " + posts + " posts you entered can not be bundled without remainder.\n" +
                "so in order to get smallest bundles, your posts has been bundled with " + changedPosts + ".\n" +
                "Your are free to re-enter your order if you want.\n");
        return bundleBreakDownMap;
    }

    public Map<Integer, Integer> getBundleBreakdownMapCalculator(Set<Integer> descendingBundles, Integer posts) {

        int postsInt = posts.intValue();
        int bundle = 0;
        Map<Integer, Integer> bundleBreakDownMap = new HashMap<>();

        Iterator<Integer> it = descendingBundles.iterator();
        for (int i = 0; i < descendingBundles.size(); i++) {
            // get a bundle from set
            bundle = it.next().intValue();
            // if  posts bigger than bundle, store the result
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
        // if posts  can not be exactly divided,return a different map;
        if (postsInt != 0) {
            int wrong = -1;
            bundleBreakDownMap = new HashMap<>();
            bundleBreakDownMap.put(wrong, postsInt);
            return bundleBreakDownMap;
        }
        return bundleBreakDownMap;
    }

}

