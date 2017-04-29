package exclusion;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main2 {
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		
		ArrayList<Map<String, Object>> v1list = new ArrayList<>();
		ArrayList<Map<String, Object>> v2list = new ArrayList<>();
		ArrayList<Map<String, Object>> v3list = new ArrayList<>();

			ArrayList<Map<String, Object>> vlist = new ArrayList<>();

				Map<String, Object> m1 = new LinkedHashMap<>();
				Map<String, Object> m2 = new LinkedHashMap<>();
				Map<String, Object> m3 = new LinkedHashMap<>();
				Map<String, Object> m4 = new LinkedHashMap<>();
				Map<String, Object> m = new LinkedHashMap<>();
				ArrayList<Map<String, Object>> mlist = new ArrayList<>();

				m1.put("PROTOCOL_ID", "PROTOCOL_ID v1");
				m1.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v1");
				m2.put("PROTOCOL", m1);
				m2.put("DOCUMENT_ID", "DOCUMENT_ID v1");
				m2.put("DOCUMENT_TYPE", "DOCUMENT_TYPE v1");
				m3.put("DOCUMENT", m2);
				m3.put("SETTLEMENT_ID", "SETTLEMENT_ID v1");
				m3.put("PROGRAM_ID", "PROGRAM_ID v1");
				mlist.add(m3);
				m4.put("PROTOCOL_ID", "PROTOCOL_ID v1");
				m4.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v1");

				m.put("_id", "" + 0);
				m.put("NAME", "piyush v1");
				m.put("SETTLEMENT", mlist);
				m.put("PROTOCOL", m4);
				
				vlist.add(m);
				v1list.addAll(vlist);
			
			//////////////////////
				 m1 = new LinkedHashMap<>();
				 m2 = new LinkedHashMap<>();
				 m3 = new LinkedHashMap<>();
				 m4 = new LinkedHashMap<>();
				 m = new LinkedHashMap<>();
				 mlist = new ArrayList<>();
				 vlist = new ArrayList<>();
				 
				m1.put("PROTOCOL_ID", "PROTOCOL_ID v2");
				m1.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v2");
				m2.put("PROTOCOL", m1);
				m2.put("DOCUMENT_ID", "DOCUMENT_ID v2");
				m2.put("DOCUMENT_TYPE", "DOCUMENT_TYPE v2");
				m3.put("DOCUMENT", m2);
				m3.put("SETTLEMENT_ID", "SETTLEMENT_ID v2");
				m3.put("PROGRAM_ID", "PROGRAM_ID v2");
				mlist.add(m3);
				m4.put("PROTOCOL_ID", "PROTOCOL_ID v2");
				m4.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v2");

				m.put("_id", "" + 0);
				m.put("NAME", "piyush v2");
				
				m.put("v2", "should be in final");
				
				m.put("SETTLEMENT", mlist);
				m.put("PROTOCOL", m4);
				
				vlist.add(m);
				v2list.addAll(vlist);

			////////////////////////////
				 m1 = new LinkedHashMap<>();
				 m2 = new LinkedHashMap<>();
				 m3 = new LinkedHashMap<>();
				 m4 = new LinkedHashMap<>();
				 m = new LinkedHashMap<>();
				 mlist = new ArrayList<>();
				 vlist = new ArrayList<>();
				 
				m1.put("PROTOCOL_ID", "PROTOCOL_ID v3");
				m1.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v3");
				m2.put("PROTOCOL", m1);
				m2.put("DOCUMENT_ID", "DOCUMENT_ID v3");
				m2.put("DOCUMENT_TYPE", "DOCUMENT_TYPE v3");
				m3.put("DOCUMENT", m2);
				m3.put("SETTLEMENT_ID", "SETTLEMENT_ID v3");
				m3.put("PROGRAM_ID", "PROGRAM_ID v3");
				mlist.add(m3);
				m4.put("PROTOCOL_ID", "PROTOCOL_ID v3");
				m4.put("PROTOCOL_TYPE", "PROTOCOL_TYPE v3");

				m.put("_id", "" + 0);
				m.put("NAME", "piyush v3");
				
				m.put("v3", "should be in final");
				
				m.put("SETTLEMENT", mlist);
				m.put("PROTOCOL", m4);
				
				vlist.add(m);
				v3list.addAll(vlist);

		ArrayList<Map<String, Object>> serviceData = new ArrayList<Map<String, Object>>();

		Map<String, Object> v1 = new HashMap();
		Map<String, Object> v2 = new HashMap();
		Map<String, Object> v3 = new HashMap();

		ArrayList<String> exv1 = new ArrayList();
		exv1.add("SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_ID");
		v1.put("exclusion", exv1);
		v1.put("serviceData", v1list);
		

		ArrayList<String> exv2 = new ArrayList();
		exv2.add("SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_ID");
		v2.put("exclusion", exv2);
		v2.put("serviceData", v2list);

		ArrayList<String> exv3 = new ArrayList();
		exv3.add("SETTLEMENT.DOCUMENT.DOCUMENT_ID");
		v3.put("exclusion", exv3);
		v3.put("serviceData", v3list);

		ArrayList<Map<String, Object>> priorityServiceDataMap = new ArrayList<>();
		priorityServiceDataMap.add(v1);
		priorityServiceDataMap.add(v2);
		priorityServiceDataMap.add(v3);

		/// segregate

		// reverse the priority data
		// Collections.reverse(priorityServiceDataMap);

		ArrayList<Map<String, Object>> data = mergePriorityListMap(priorityServiceDataMap);

		System.out.println("Before: ");
		// String before = gson.toJson(m);
		// System.out.println(before);

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Map<String, Object>> mergePriorityListMap(
			ArrayList<Map<String, Object>> priorityServiceDataMap) {
		ArrayList<Map<String, Object>> lowPriorityList = null;
		// take a each map from list
		for (Map<String, Object> highPriorityMap : priorityServiceDataMap) {
			// get exclusions from each map
			ArrayList<String> exclusionList = (ArrayList<String>) highPriorityMap.get("exclusion");
			// get serviceData from each map
			ArrayList<Map<String, Object>> serviceList = (ArrayList<Map<String, Object>>) highPriorityMap
					.get("serviceData");
			//
			lowPriorityList = mergeMap(lowPriorityList, serviceList, exclusionList);

		}
		return lowPriorityList;
	}

	public static ArrayList<Map<String, Object>> mergeMap(ArrayList<Map<String, Object>> highPriorityList,
			ArrayList<Map<String, Object>> lowPriorityList, ArrayList<String> exclusionList) {

		ArrayList<Map<String, Object>> newArrayList = new ArrayList<Map<String, Object>>();

		// iterate high priority map
		if (highPriorityList != null) {
			for (Map<String, Object> highPriorityMap : highPriorityList) {

				// get value if "_id" of current map
				String _id = (String) highPriorityMap.get("_id");

				// iterate low priority map
				for (Map<String, Object> lowPriorityMap : lowPriorityList) {

					// exclusion from lowPriority
					if (lowPriorityMap.containsValue(_id)) {
						Map<String, Object> newMap = new HashMap<String, Object>();

						// merge lowPriorityMap and highPriorityMap
						for (String exclusion : exclusionList) {
							String[] globalkeys = exclusion.split("\\.");
							lowPriorityMap = exclusionKeyRemover(lowPriorityMap, globalkeys, 0);
						}
						//todo iterate low priority and high priority then put one by one
						newMap.putAll(lowPriorityMap);
						newMap.putAll(highPriorityMap);
						newArrayList.add(newMap);
					}
				}
			}
		} else {
			// iterate low priority map its a first iteration of map
			for (Map<String, Object> lowPriorityMap : lowPriorityList) {
				Map<String, Object> newMap = new HashMap<String, Object>();
				// exclusion from lowPriority
				for (String exclusion : exclusionList) {
					String[] globalkeys = exclusion.split("\\.");
					lowPriorityMap = exclusionKeyRemover(lowPriorityMap, globalkeys, 0);
				}
				newMap.putAll(lowPriorityMap);
				newArrayList.add(newMap);
			}
		}
		return newArrayList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map exclusionKeyRemover(Map rawMap, String[] exclsuionKeysArray, int exclusionKeysArrayIndex) {
		Iterator it = rawMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			// key will be removed checked ( if current key is the last key )
			if (pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])
					&& (exclsuionKeysArray.length - 1 == exclusionKeysArrayIndex)) {
				if (pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])
						&& (exclsuionKeysArray.length - 1 == exclusionKeysArrayIndex)) {
					it.remove();
				}
			} else if (pair.getValue() instanceof Map
					&& pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])) {
				// if key is matched and corresponding value is Map then move to
				// next key till its last key to remove
				exclusionKeysArrayIndex++;
				exclusionKeyRemover((Map) pair.getValue(), exclsuionKeysArray, exclusionKeysArrayIndex);
			} else if (pair.getValue() instanceof ArrayList
					&& pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])) {
				exclusionKeysArrayIndex++;
				ArrayList nestedMapList = (ArrayList) pair.getValue();
				for (Map eachNestedMap : (ArrayList<Map>) nestedMapList)
					exclusionKeyRemover(eachNestedMap, exclsuionKeysArray, exclusionKeysArrayIndex);
			}
		}
		return rawMap;
	}

}
