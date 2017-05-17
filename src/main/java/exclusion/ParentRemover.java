package exclusion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ParentRemover {
	public static void main(String[] args) {
        ArrayList<String> exlusionKeyList = new ArrayList<>(Arrays
                     .asList(new String[] { "settlement", "settlement.id", "settlement.project_id", "settlement.contacts.id",
                                   "settlement.contacts.project_id", "settlement.Documents", "settlement.Documents.id" }));
        Set<String> parentKeySetToSkip = new HashSet<>();
        for (String key : exlusionKeyList) {
               innerCheck(key, parentKeySetToSkip);
        }
        System.out.println("parentKeySetToSkip" + parentKeySetToSkip);

        if (parentKeySetToSkip.size() > 0) {
               exlusionKeyList.removeAll(exlusionKeyList.stream().filter(p -> parentKeySetToSkip.contains(p))
                            .collect(Collectors.toCollection(ArrayList::new)));
        }
        System.out.println("exlusionKeyList" + exlusionKeyList);
 }

 public static void innerCheck(String key, Set<String> parentKeySetToSkip) {
        if (key.contains(".")) {
               String[] skippedKeys = key.split("\\.");
               if (skippedKeys.length > 0) {
                     if (skippedKeys.length == 2) {
                            parentKeySetToSkip.add(skippedKeys[0]);
                     } else if (skippedKeys.length > 2) {
                            String nxtKey = skippedKeys[0] + "." + skippedKeys[1];
                            parentKeySetToSkip.add(nxtKey);
                            innerCheck(nxtKey, parentKeySetToSkip);
                            return;
                     }
               }
        }
 }

}
