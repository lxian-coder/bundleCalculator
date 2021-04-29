package darcyxian.bundle_calculator.buddleFormats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Darcy Xian  28/4/21  7:15 am      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class QueryBundleFormatsMap {
    private final BundleFormatsMap bundleFormatsMap;

    // give a format code, this method can return all bundles(Integer type) in descending.
    public Set<Integer> getDescendingBundles(String code) {
        // get all bundle from budleFormats map
        Set<Integer> bundles = bundleFormatsMap.createFormatsMap().get(code).keySet()
                .stream()
                .map(e -> Integer.parseInt(e))
                .collect(Collectors.toSet());
        // revers order of bundle
        SortedSet<Integer> descendingBundles = new TreeSet<Integer>(Collections.reverseOrder());
        descendingBundles.addAll(bundles);
        return descendingBundles;
    }

    // given a format code and a bundles number, this method will return money number(BigDecimal).
    public BigDecimal getBundleMoney(String code, String bundle) {

        String result = bundleFormatsMap.createFormatsMap().get(code).get(bundle);
        BigDecimal money = new BigDecimal(result);

        return money;
    }

}
