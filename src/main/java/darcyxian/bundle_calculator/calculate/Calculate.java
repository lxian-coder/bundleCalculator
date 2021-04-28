package darcyxian.bundle_calculator.calculate;

import darcyxian.bundle_calculator.buddleFormats.BundleFormatsMap;
import darcyxian.bundle_calculator.buddleFormats.QueryBundleFormatsMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Darcy Xian  27/4/21  5:35 pm      Bundle_Calculator
 */
@Component
@AllArgsConstructor
public class Calculate {
    private BundleFormatsMap bundleFormatsMap;
    private QueryBundleFormatsMap queryBundleFormatsMap;

    public Map<String,Map<Integer,Integer>> calculateTheInput(List<String> inputList) {
        Map<String,Map<Integer,Integer>> calculationResultMap = new HashMap<>();
          // put posts number entered by users into a list
           List<Integer> orderedPosts = inputList.stream()
                    .filter(element->isStrToNum(element)== true)
                    .map(e -> Integer.parseInt(e))
                    .collect(Collectors.toList());
           // put format code entered by users into a list
           List<String> orderedFormatCodes = inputList.stream()
                   .filter(element->isStrToNum(element)== false)
                   .collect(Collectors.toList());

           for(int i=0; i<orderedFormatCodes.size(); i++){
              Set<Integer> descendingBundles = queryBundleFormatsMap.getDescendingBundles(orderedFormatCodes.get(i));
              Map<Integer,Integer> bundlesBreakDown = getBundleBreakdownMap(descendingBundles,orderedPosts.get(i),orderedFormatCodes.get(i));
              calculationResultMap.put(orderedFormatCodes.get(i),bundlesBreakDown);
           }
        System.out.println("calculationMap :"+calculationResultMap);
           return calculationResultMap;
    }

    public Map<Integer,Integer> getBundleBreakdownMap(Set<Integer> descendingBundles, Integer posts,String code ){
        int postsInt = posts.intValue();
        int bundle =0;
        Map<Integer,Integer> bundleBreakDownMap = new HashMap<>();
        Iterator<Integer> it = descendingBundles.iterator();
        for (int i =0; i < descendingBundles.size(); i++){
            // get bundle from set
             bundle =  it.next().intValue();
            // if  posts bigger than bundle, store the result
            System.out.println("for bundle:"+ bundle);
            if(postsInt / bundle > 0){

              bundleBreakDownMap.put(bundle,postsInt/bundle);
                System.out.println("bundleMap :"+bundleBreakDownMap);
            }
            if(postsInt%bundle == 0){
                postsInt = 0;
                 break;
            }else {
                postsInt = postsInt%bundle;
                System.out.println("tails :"+postsInt);
            }
        }
        // if postsInt is still not 0 after above calculation, change bundles and calculate again.
        if(postsInt != 0){
         System.out.println("fuck not Zero!!!!"+ postsInt);
         int changedPosts =  posts.intValue() - postsInt + bundle;
            System.out.println("Changed posts:"+changedPosts);
            System.out.println("Please notice: "+code+" basis is "+descendingBundles+" and "+posts+" posts you entered can not be bundled\n" +
                               "remainder is "+postsInt+ " so your posts has ben changed to "+changedPosts+". If you do not like it, you can re-enter you order.");
         return  getBundleBreakdownMap(descendingBundles,changedPosts,code);
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
