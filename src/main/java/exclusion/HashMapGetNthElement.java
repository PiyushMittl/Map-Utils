package exclusion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oracle.jrockit.jfr.DataType;

public class HashMapGetNthElement {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Map<String, Object> m = new LinkedHashMap<>();
		Map<String, Object> m1 = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();
		Map<String, Object> m2 = new LinkedHashMap<>();
		Map<String, Object> m3 = new LinkedHashMap<>();
		Map<String, Object> m4 = new LinkedHashMap<>();
		

		
		m1.put("PROTOCOL_ID", "1");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "1");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "1");
		m3.put("PROGRAM_ID", "1");
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
		//String globalkeyToRemove = "NAME";
		String globalkeyToRemove = "SETTLEMENT.DOCUMENT.DOCUMENT_ID";
		
		
		String[] globalkeys = globalkeyToRemove.split("\\.");

		Map map = checkNthElementDatatype(m, globalkeys,0,"STRING");

		//System.out.println("after: ");
		//String after = gson.toJson(map);
		//System.out.println(after);

	}

	//SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_ID
	//static int keysArrayIndex = 0;

	/**
	 * this method is used to check the data type of given keys
	 * 
	 * @param 
	 * @param checkKeys exclusion keys
	 * @param checkKeysArrayIndex index of exclusion key 
	 * @return map after exclusion keys removed
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map checkNthElementDatatype(Map rawMap, String[] checkKeys, int checkKeysArrayIndex,String dataType) {
		Iterator it = rawMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			//key will be removed checked ( if current key is the last key  ) 
			if(pair.getKey().equals(checkKeys[checkKeysArrayIndex]) && (checkKeys.length-1==checkKeysArrayIndex)){
				//checking data type 
				if(dataType.equals("INTEGER")){
					if(pair.getValue() instanceof String)
					{
						System.out.println("yes value is an instance of INTEGER===============>"+pair.getValue());
					}
					else{
						System.out.println("No value is not an instance of INTEGER===============>"+pair.getValue());
					}
				}
				if(dataType.equals("STRING")){
					if(pair.getValue() instanceof String)
					{
						System.out.println("yes value is an instance of STRING===============>"+pair.getValue());
					}
					else{
						System.out.println("No value is not an instance of STRING===============>"+pair.getValue());
					}
				}
			}
			else if (pair.getValue() instanceof Map && pair.getKey().equals(checkKeys[checkKeysArrayIndex])) {
				//if key is matched and corresponding value is Map then move to next key till its last key to remove
				checkKeysArrayIndex++;
				checkNthElementDatatype((Map) pair.getValue(), checkKeys,checkKeysArrayIndex,dataType);
			} else if (pair.getValue() instanceof ArrayList	&& pair.getKey().equals(checkKeys[checkKeysArrayIndex])) {
				checkKeysArrayIndex++;
				ArrayList nestedMapList = (ArrayList) pair.getValue();
				for (Map eachNestedMap : (ArrayList<Map>) nestedMapList)
					checkNthElementDatatype(eachNestedMap, checkKeys,checkKeysArrayIndex,dataType);
			} 
		}
		return rawMap;
	}

}
