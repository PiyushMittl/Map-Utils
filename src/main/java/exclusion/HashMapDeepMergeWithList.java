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

public class HashMapDeepMergeWithList {
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

		HashMap document = new HashMap();
		document.put("PROTOCOL", protocol);
		document.put("DOCUMENT_ID", "01");

		
		List documentList=new ArrayList(); 
		documentList.add(document);
		documentList.add(document); 
		documentList.add(document);
		

		HashMap settlement = new HashMap();
		settlement.put("DOCUMENT", documentList);
		//settlement.put("DOCUMENT", document);
		settlement.put("SETTLEMENT_ID", "01");
		settlement.put("PROGRAM_ID", "01");

		HashMap protocola = new HashMap();
		protocola.put("PROTOCOL_ID", "01");
		protocola.put("PROTOCOL_TYPE", "abc");

		HashMap d1 = new HashMap();
		d1.put("PROTOCOL", protocola);
		d1.put("SETTLEMENT", settlement);
		d1.put("_id", "0");
		d1.put("NAME", "Piyush");
		d1.put("FIELD", "NEW_FIELD");

		/**
		 * Second row
		 */

		HashMap protocol2 = new HashMap();
		protocol2.put("PROTOCOL_ID", "03");
		protocol2.put("PROTOCOL_TYPE", "03");

		HashMap document2 = new HashMap();
		document2.put("PROTOCOL", protocol2);
		document2.put("DOCUMENT_ID", "03");

		
		List documentList2=new ArrayList(); 
		documentList2.add(document2);
		documentList2.add(document2); 
		documentList2.add(document2);
		

		HashMap settlement2 = new HashMap();
		settlement2.put("DOCUMENT", documentList2);
		//settlement2.put("DOCUMENT", document2);
		settlement2.put("SETTLEMENT_ID", "03");
		settlement2.put("PROGRAM_ID", "03");

		HashMap protocola2 = new HashMap();
		protocola2.put("PROTOCOL_ID", "03");
		protocola2.put("PROTOCOL_TYPE", "PROTOCOL_TYPE");

		HashMap d2 = new HashMap();
		d2.put("PROTOCOL", protocola2);
		d2.put("SETTLEMENT", settlement2);
		d2.put("_id", "0");
		d2.put("NAME", "Piyush");
		d2.put("FIELD", "NEW_FIELD");

		
		/*MapDifference<String, Integer> diff = Maps.difference(d1, d2);

		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesDiffering(); // {"c" => (3, 4)}
		diff.entriesOnlyOnLeft(); // {"a" => 1}
		diff.entriesOnlyOnRight(); // {"d" => 5}
		*/
		
		
		
		System.out.println("d:------------> ");
		String before = gson.toJson(d1);
		System.out.println(before);
		System.out.println("d1:------------> ");
		String bb = gson.toJson(d2);
		System.out.println(bb);

		HashMapDeepMergeWithList hashMapDeepMergeWithListMapMerge = new HashMapDeepMergeWithList();
		// a = hashMapMerge.merge(d1, d2);
		a = (HashMap) hashMapDeepMergeWithListMapMerge.deepMerge(d1, d2);

		System.out.println("result===========================: ");
		String bb1 = gson.toJson(a);
		System.out.println(bb1);

	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public HashMap merge(HashMap a, HashMap b) {
		HashMap c = new HashMap();

		for (Object key : a.keySet()) {
			String key2 = (String) key;
			Object dup = a.get(key2);
			c.put(key2, dup);
		}
		for (Object key : b.keySet()) {
			String key2 = (String) key;
			Object dup = b.get(key2);
			if (dup instanceof HashMap && c.containsKey(key2) && c.get(key2) instanceof HashMap) {
				HashMap kk = (HashMap) c.get(key2);
				HashMap p = merge(kk, (HashMap) dup);
				c.put(key2, p);
			} else if (dup instanceof HashMap && c.containsKey(key2) && !(c.get(key2) instanceof HashMap)) {
				HashMap kk = new HashMap();
				kk.put(key2, c.get(key2));
				HashMap p = merge(kk, (HashMap) dup);
				c.put(key2, p);
			} else if (dup instanceof List) {
				// HashMap kk = new HashMap();
				Iterator it1 = ((List) dup).iterator();
				Iterator it2 = ((List) c.get(key2)).iterator();
				if (it1.hasNext() && it2.hasNext()) {
					HashMap p = merge((HashMap) it1.next(), (HashMap) it2.next());
					/// c.put(key2, p);
				}
			} else {
				c.put(key2, dup);
			}
		}
		return c;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map deepMerge(Map original, Map newMap) {
		for (Object key : newMap.keySet()) {
			if (newMap.get(key) instanceof Map && original.get(key) instanceof Map) {
				Map originalChild = (Map) original.get(key);
				Map newChild = (Map) newMap.get(key);
				original.put(key, deepMerge(originalChild, newChild));
			} else if (newMap.get(key) instanceof List && original.get(key) instanceof List) {
				List<Object> mergedMapList=new ArrayList();
				Iterator newMapIteraror = ((List) newMap.get(key)).iterator();
				Iterator originalMapIterator = ((List) original.get(key)).iterator();
				while (newMapIteraror.hasNext() && originalMapIterator.hasNext()) {
					HashMap p = (HashMap) deepMerge((HashMap) originalMapIterator.next(),(HashMap) newMapIteraror.next());
					mergedMapList.add(p);
				}
				//or
				//map key _id value of original to newMap then merge them
				//if not found then add that map to list as it is
				//mergeMapList.add(p)
				original.put(key, mergedMapList);

			} else {
				original.put(key, newMap.get(key));
			}
		}
		return original;
	}

}