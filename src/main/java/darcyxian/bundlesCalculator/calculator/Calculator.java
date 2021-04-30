package darcyxian.bundlesCalculator.calculator;

import darcyxian.bundlesCalculator.bundleFormatsMap.BundleFormatsMap;
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
    private ToolsBarn toolsBarn;

    public Map<String, Map<Integer, Integer>> getCalculationResultMap(List<String> inputList) {
        Map<String, Map<Integer, Integer>> calculationResultMap = new HashMap<>();
        List<String> orderedFormatCodes = toolsBarn.createFormatCodeList(inputList);
        List<Integer> orderedPosts = toolsBarn.createPostList(inputList);

        for (int i = 0; i < orderedFormatCodes.size(); i++) {
            Set<Integer> descendingBundles = bundleFormatsMap.getDescendingBundles(orderedFormatCodes.get(i));
            Map<Integer, Integer> bundlesBreakDown = getCalculationResultMapController(descendingBundles, orderedPosts.get(i).intValue(), orderedFormatCodes.get(i));
            calculationResultMap.put(orderedFormatCodes.get(i), bundlesBreakDown);
        }
        return calculationResultMap;
    }

    private Map<Integer, Integer> getCalculationResultMapController(Set<Integer> descendingBundles, int posts, String code) {
        boolean failedMap = false;
        int changedPosts = posts;
        Map<Integer, Integer> bundleBreakDownMap;
        bundleBreakDownMap = getBundleBreakdownMap(descendingBundles, posts, code);
        failedMap = bundleBreakDownMap.containsKey(-1);
        while (failedMap) {
            changedPosts++;
            bundleBreakDownMap = getBundleBreakdownMapCalculator(descendingBundles, changedPosts);
            failedMap = bundleBreakDownMap.containsKey(-1);
        }
        if (changedPosts != posts) {
            System.out.println("Please notice: " + code + " basis is " + descendingBundles + " and " + posts + " posts you entered can not be bundled without remainder.\n" +
                    "so in order to get smallest bundles, your posts has been bundled with " + changedPosts + ".\n" +
                    "Your are free to re-enter your order if you want.\n");
        }
        return bundleBreakDownMap;
    }

    private Map<Integer, Integer> getBundleBreakdownMap(Set<Integer> descendingBundles, Integer posts, String code) {
        int bundlesSize = descendingBundles.size();

        Map<Integer, Integer> bundleBreakDownMap = new HashMap<>();
        Iterator<Integer> it = descendingBundles.iterator();
        Set<Integer> copyDescendingBundles = new HashSet<>(descendingBundles);
        SortedSet<Integer> smallDescendingBundles = new TreeSet<Integer>(Collections.reverseOrder());
        smallDescendingBundles.addAll(copyDescendingBundles);
        for (int i = 0; i < bundlesSize - 1; i++) {
            bundleBreakDownMap = getBundleBreakdownMapCalculator(smallDescendingBundles, posts);
            if (bundleBreakDownMap.containsKey(-1)) {
                Integer maxBundles = it.next();
                smallDescendingBundles.remove(maxBundles);
            } else {
                return bundleBreakDownMap;
            }
        }
        return bundleBreakDownMap;
    }

    private Map<Integer, Integer> getBundleBreakdownMapCalculator(Set<Integer> descendingBundles, Integer posts) {
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
        if (postsInt != 0) {
            int wrong = -1;
            bundleBreakDownMap = new HashMap<>();
            bundleBreakDownMap.put(wrong, postsInt);
            return bundleBreakDownMap;
        }
        return bundleBreakDownMap;
    }
}

