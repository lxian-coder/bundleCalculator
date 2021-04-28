package darcyxian.bundle_calculator.calculator;

import darcyxian.bundle_calculator.buddleFormats.QueryBundleFormatsMap;
import darcyxian.bundle_calculator.output.Output;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Darcy Xian  27/4/21  5:35 pm      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class Calculator {
    private  QueryBundleFormatsMap queryBundleFormatsMap;
    private  Output output;

    public Map<String, Map<Integer, Integer>> calculateTheInput(List<String> inputList) {
        Map<String, Map<Integer, Integer>> calculationResultMap = new HashMap<>();
        // put posts number entered by users into a list
        List<Integer> orderedPosts = inputList.stream()
                .filter(element -> isStrToNum(element) == true)
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toList());
        // put format code entered by users into a list
        List<String> orderedFormatCodes = inputList.stream()
                .filter(element -> isStrToNum(element) == false)
                .collect(Collectors.toList());

        for (int i = 0; i < orderedFormatCodes.size(); i++) {
            Set<Integer> descendingBundles = queryBundleFormatsMap.getDescendingBundles(orderedFormatCodes.get(i));
            Map<Integer, Integer> bundlesBreakDown = getBundleBreakdownMap(descendingBundles, orderedPosts.get(i), orderedFormatCodes.get(i));
            calculationResultMap.put(orderedFormatCodes.get(i), bundlesBreakDown);
        }
        // System.out.println("calculationMap :"+calculationResultMap);
        output.displayTheFinalResult(calculationResultMap, orderedFormatCodes, orderedPosts);
        return calculationResultMap;
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

        // here, we still can not get the posts bundled, we need to change posts and calculate again.
        int postsInt = bundleBreakDownMap.get(-1).intValue();
        int bundle = it.next();
        int changedPosts = posts.intValue() - postsInt + bundle;

        System.out.println("Please notice: " + code + " basis is " + descendingBundles + " and " + posts + " posts you entered can not be bundled without remainder.\n" +
                "Remainder is " + postsInt + " so according to the minimal bundle, your posts has been bundled with " + changedPosts + ".\n" +
                "Your are free to re-enter your order if you want.\n");
        return getBundleBreakdownMapCalculator(descendingBundles, changedPosts);
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

    public boolean isStrToNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
