package exclusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HashMapDeepMergeWithListCombinedLogic {
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		HashMap a = new HashMap();
		/**
		 * First row
		 */

		HashMap protocol = new HashMap();
		protocol.put("PROTOCOL_ID", "01");
		protocol.put("PROTOCOL_TYPE", "01");
		protocol.put("NEW_PROTOCOL_TYPE", "test");

		HashMap casepii = new HashMap();
		casepii.put("_id", "1");
		casepii.put("CLAIMENT", "01");
		HashMap protocol_case = new HashMap();
		protocol_case.put("PROTOCOL_ID", "01");
		protocol_case.put("PROTOCOL_TYPE", "01");
		protocol_case.put("NEW_PROTOCOL_TYPE", "test");
		protocol_case.put("CASE", casepii);

		HashMap document1 = new HashMap();
		document1.put("PROTOCOL", protocol_case);
		document1.put("_id", "1");
		HashMap document2 = new HashMap();
		document2.put("PROTOCOL", protocol);
		document2.put("_id", "2");
		HashMap document3 = new HashMap();
		document3.put("PROTOCOL", protocol);
		document3.put("_id", "3");
		HashMap document4 = new HashMap();
		document4.put("PROTOCOL", protocol);
		document4.put("_id", "4");
		HashMap document5 = new HashMap();
		document5.put("PROTOCOL", protocol);
		document5.put("_id", "5");

		List documentList = new ArrayList();
		documentList.add(document1);
		documentList.add(document2);
		documentList.add(document3);
		documentList.add(document4);
		documentList.add(document5);

		HashMap settlement = new HashMap();
		settlement.put("DOCUMENT", documentList);
		settlement.put("SETTLEMENT_ID", "01");
		settlement.put("PROGRAM_ID", "01");

		HashMap protocola = new HashMap();
		protocola.put("PROTOCOL_ID", "01");
		protocola.put("PROTOCOL_TYPE", "abc");

		HashMap lowPriorityMap = new HashMap();
		lowPriorityMap.put("PROTOCOL", protocola);
		lowPriorityMap.put("SETTLEMENT", settlement);
		lowPriorityMap.put("_id", "0");
		lowPriorityMap.put("NAME", "Piyush");
		lowPriorityMap.put("FIELD", "NEW_FIELD");

		List<Integer> ls = new ArrayList<>();
		ls.add(11);

		lowPriorityMap.put("ARRAY", ls);

		/**
		 * Second row
		 */

		/*
		 * HashMap casepii = new HashMap(); casepii.put("_id", "1");
		 * casepii.put("CLAIMENT", "03");
		 */
		HashMap protocol2 = new HashMap();
		protocol2.put("PROTOCOL_ID", "03");
		protocol2.put("PROTOCOL_TYPE", "03");

		HashMap casepii2 = new HashMap();
		casepii2.put("_id", "1");
		casepii2.put("CLAIMENT", "03");
		casepii2.put("ENABLE", "true");
		HashMap protocol_case2 = new HashMap();
		protocol_case2.put("PROTOCOL_ID", "01");
		protocol_case2.put("PROTOCOL_TYPE", "01");
		protocol_case2.put("NEW_PROTOCOL_TYPE", "test");
		protocol_case2.put("CASE", casepii2);

		HashMap document21 = new HashMap();
		document21.put("PROTOCOL", protocol_case2);
		// document21.put("CAEPII", casepii);
		document21.put("_id", "1");
		HashMap document22 = new HashMap();
		document22.put("PROTOCOL", protocol2);
		document22.put("_id", "2");
		HashMap document23 = new HashMap();
		document23.put("PROTOCOL", protocol2);
		document23.put("_id", "3");
		HashMap document24 = new HashMap();
		document24.put("PROTOCOL", protocol2);
		document24.put("_id", "9");

		List documentList2 = new ArrayList();
		documentList2.add(document21);
		documentList2.add(document22);
		documentList2.add(document23);
		documentList2.add(document24);

		HashMap settlement2 = new HashMap();
		settlement2.put("DOCUMENT", documentList2);
		settlement2.put("SETTLEMENT_ID", "03");
		settlement2.put("PROGRAM_ID", "03");

		HashMap protocola2 = new HashMap();
		protocola2.put("PROTOCOL_ID", "03");
		protocola2.put("PROTOCOL_TYPE", "PROTOCOL_TYPE");

		HashMap highPriorityMap = new HashMap();
		highPriorityMap.put("PROTOCOL", protocola2);
		highPriorityMap.put("SETTLEMENT", settlement2);
		highPriorityMap.put("_id", "0");
		highPriorityMap.put("NAME", "Piyush");
		highPriorityMap.put("FIELD", "NEW_FIELD");
		List<Integer> ls2 = new ArrayList<>();
		ls2.add(22);

		highPriorityMap.put("ARRAY", ls2);

		System.out.println("d:------------> ");
		String before = gson.toJson(lowPriorityMap);
		System.out.println(before);
		System.out.println("d1:------------> ");
		String bb = gson.toJson(highPriorityMap);
		System.out.println(bb);

		
		/*MapDifference<String, Integer> diff = Maps.difference(lowPriorityMap, highPriorityMap);
		bb = gson.toJson(diff);
		System.out.println("diffffffffffffffffffffffffffffffffffffff"+bb);*/
		
		HashMapDeepMergeWithListCombinedLogic hashMapDeepMergeWithListMapMerge = new HashMapDeepMergeWithListCombinedLogic();
		// a = hashMapMerge.merge(d1, d2);
		a = (HashMap) hashMapDeepMergeWithListMapMerge.deepMerge(lowPriorityMap, highPriorityMap);

		System.out.println("result===========================: ");
		String bb1 = gson.toJson(a);
		System.out.println(bb1);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map deepMerge(Map original, Map newMap) {
		for (Object key : newMap.keySet()) {
			if (newMap.get(key) instanceof Map && original.get(key) instanceof Map) {
				Map originalChild = (Map) original.get(key);
				Map newChild = (Map) newMap.get(key);
				original.put(key, deepMerge(originalChild, newChild));
			} else if (newMap.get(key) instanceof List && original.get(key) instanceof List) {

				// Step1.Get List of Map v1
				// Step2.Get List of Map v2

				List<Map<String, Object>> newMapList = (List<Map<String, Object>>) newMap.get(key);
				List<Map<String, Object>> originalList = (List<Map<String, Object>>) original.get(key);

				// Step3.Create a list x which will hold the elements merged or
				// non merged

				if ((!newMapList.isEmpty() && newMapList.get(0) instanceof Map)
						|| (!originalList.isEmpty() && originalList.get(0) instanceof Map)) {
					List<Object> mergedMapList = new ArrayList();
					// Step4.Get all the maps of v1 which are not available in
					// v2 on
					// the basis of "_id"
					boolean found = false;
					for (Map<String, Object> newEachMap : newMapList) {
						found = false;
						for (Map<String, Object> newEachOriginal : originalList) {
							if (newEachOriginal.containsValue(newEachMap.get("_id"))) {
								found = true;
							}
						}
						if (found != true) {
							mergedMapList.add(newEachMap);
						}
						if (!newEachMap.containsKey("_id")) {
							mergedMapList.add(newEachMap);
						}
					}

					// Step5.Get all the maps of v2 which are not available in
					// v1 on
					// the basis of "_id"
					for (Map<String, Object> newEachOriginal : originalList) {
						found = false;
						for (Map<String, Object> newEachMap : newMapList) {
							if (newEachMap.containsValue(newEachOriginal.get("_id"))) {
								found = true;
							}
						}
						if (found != true) {
							mergedMapList.add(newEachOriginal);
						}
						if (!newEachOriginal.containsKey("_id")) {
							mergedMapList.add(newEachOriginal);
						}
					}

					// Step6.Get all the maps of Map v2 that has same “_id” in
					// Map
					// v1
					for (Map<String, Object> newEachOriginal : originalList) {
						for (Map<String, Object> newEachMap : newMapList) {
							if (newEachMap.containsValue(newEachOriginal.get("_id"))) {
								HashMap p = (HashMap) deepMerge(newEachOriginal, newEachMap);
								mergedMapList.add(p);
							}
						}
					}
					original.put(key, mergedMapList);
				}else{
					original.put(key, newMap.get(key));
				}
			} else {
				original.put(key, newMap.get(key));
			}
		}
		return original;
	}

}