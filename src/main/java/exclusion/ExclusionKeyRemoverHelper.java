package exclusion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExclusionKeyRemoverHelper {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Map<String, Object> m = new LinkedHashMap<>();
		Map<String, Object> m1 = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();
		Map<String, Object> m2 = new LinkedHashMap<>();
		Map<String, Object> m3 = new LinkedHashMap<>();
		Map<String, Object> m4 = new LinkedHashMap<>();
		
		ArrayList<Map<String, Object>> clist = new ArrayList<>();

		for(int i=0;i<5;i++){
			Map<String, Object> pocument = new LinkedHashMap<>();
			pocument.put("CHOCUMENT_ID", i+"");
			clist.add(pocument);
		}
		
		
		ArrayList<Map<String, Object>> plist = new ArrayList<>();

		for(int i=0;i<5;i++){
			Map<String, Object> pocument = new LinkedHashMap<>();
			pocument.put("POCUMENT_ID", i+"");
			pocument.put("CHOCUMENTS", clist);
			plist.add(pocument);
		}
		
		
		
		m1.put("PROTOCOL_ID", "1");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "1");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "1");
		m3.put("PROGRAM_ID", "1");
		m3.put("POCUMENTS",plist);
		mlist.add(m3);
		

		m1 = new LinkedHashMap<>();
		m2 = new LinkedHashMap<>();
		m3 = new LinkedHashMap<>();
		
		m1.put("PROTOCOL_ID", "2");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "2");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "2");
		m3.put("PROGRAM_ID", "2");
		m3.put("POCUMENTS",plist);
		mlist.add(m3);
		
		
		m1 = new LinkedHashMap<>();
		m2 = new LinkedHashMap<>();
		m3 = new LinkedHashMap<>();
		
		m1.put("PROTOCOL_ID", "3");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "3");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "3");
		m3.put("PROGRAM_ID", "3");
		m3.put("POCUMENTS",plist);
		mlist.add(m3);
		
		
		m4.put("PROTOCOL_ID", "1");
		m4.put("PROTOCOL_TYPE", "abc");
		m.put("NAME","piyush");
		m.put("SETTLEMENT",mlist);
		m.put("PROTOCOL",m4);
		

		System.out.println("Before: ");
		String before = gson.toJson(m);
		System.out.println(before);

		//String globalkeyToRemove = "SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_TYPE";
		//String globalkeyToRemove = "SETTLEMENT.DOCUMENT";
		//String globalkeyToRemove = "SETTLEMENT.DOCUMENT.DOCUMENT_ID";
		String globalkeyToRemove = "SETTLEMENT.POCUMENTS.CHOCUMENTS.CHOCUMENT_ID";
		
		String[] globalkeys = globalkeyToRemove.split("\\.");

		Map map = exclusionKeyRemover(m, globalkeys,0);

		System.out.println("after: ");
		String after = gson.toJson(map);
		System.out.println(after);

	}

	//SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_ID
	//static int keysArrayIndex = 0;

	/**
	 * this method is used to remove the exclusion keys from a map
	 * 
	 * @param rawMap a map with exclusion key included (map before exclusion)
	 * @param exclsuionKeysArray exclusion keys
	 * @param exclusionKeysArrayIndex index of exclusion key 
	 * @return map after exclusion keys removed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map exclusionKeyRemover(Map rawMap, String[] exclsuionKeysArray, int exclusionKeysArrayIndex) {
		Iterator it = rawMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			//key will be removed checked ( if current key is the last key  ) 
			if(pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex]) && (exclsuionKeysArray.length-1==exclusionKeysArrayIndex)){
				if (pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex]) && (exclsuionKeysArray.length-1==exclusionKeysArrayIndex)) {
					it.remove();
				}
			}
			else if (pair.getValue() instanceof Map && pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])) {
				//if key is matched and corresponding value is Map then move to next key till its last key to remove
				exclusionKeysArrayIndex++;
				exclusionKeyRemover((Map) pair.getValue(), exclsuionKeysArray,exclusionKeysArrayIndex);
			} else if (pair.getValue() instanceof ArrayList	&& pair.getKey().equals(exclsuionKeysArray[exclusionKeysArrayIndex])) {
				exclusionKeysArrayIndex++;
				ArrayList nestedMapList = (ArrayList) pair.getValue();
				for (Map eachNestedMap : (ArrayList<Map>) nestedMapList)
					exclusionKeyRemover(eachNestedMap, exclsuionKeysArray,exclusionKeysArrayIndex);
			} /*else {
				if (pair.getKey().equals(keysToRemoveArray[keysArrayIndex]) && (keysToRemoveArray.length-1==keysArrayIndex)) {
					it.remove();
				}
			}*/
		}
		//keysArrayIndex = 0;
		return rawMap;
	}

}
