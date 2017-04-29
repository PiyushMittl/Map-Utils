package exclusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetAllKeys22 {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Map<String, Object> m = new LinkedHashMap<>();
		Map<String, Object> m1 = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();
		ArrayList<Map<String, Object>> mlist2 = new ArrayList<>();
		ArrayList<Map<String, Object>> listInProtocol = new ArrayList<>();
		
		Map<String, Object> m2 = new LinkedHashMap<>();
		Map<String, Object> m3 = new LinkedHashMap<>();
		Map<String, Object> m4 = new LinkedHashMap<>();
		Map<String, Object> mapForProtocolList1 = new LinkedHashMap<>();
		Map<String, Object> mapForProtocolList2 = new LinkedHashMap<>();
		Map<String, Object> mapForProtocolList3 = new LinkedHashMap<>();
		
		mapForProtocolList1.put("p1", "p1");
		mapForProtocolList2.put("p2", "p2");
		mapForProtocolList3.put("p3", "p3");
		listInProtocol.add(mapForProtocolList1);
		listInProtocol.add(mapForProtocolList2);
		listInProtocol.add(mapForProtocolList3);
		
		
		
		m1.put("PROTOCOL_ID", "1");
		m1.put("PROTOCOL_TYPE", "abc");
	//	m1.put("PROTOCOL_LIST", listInProtocol);
		//add list here 
		
		
		
		
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "1");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "1");
		m3.put("PROGRAM_ID", "1");
		mlist.add(m3);
		mlist2.add(m3);

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
		mlist2.add(m3);

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
		m3.put("nestedList", mlist2);
		
		mlist.add(m3);

		m4.put("PROTOCOL_ID", "1");
		m4.put("PROTOCOL_TYPE", "abc");
		m.put("NAME", "piyush");
		m.put("SETTLEMENT", mlist);
		
		m.put("PROTOCOLL", m4);
		m.put("TEST", "TEST");
		m.put("PROTOCOL2", m4);

		m.put("SETTLEMENT2", mlist);
		
		System.out.println("Before: ");
		String before = gson.toJson(m);
		System.out.println(before);

		List<String> map = getAllKeys(m);

		System.out.println(map);

	}

	static List<String> keysList = new ArrayList();
	static String keyStack = new String("#");
	static boolean listFlag = false;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> getAllKeys(Map<String, Object> map) {
		System.out.println("");
		for (Map.Entry entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				keyStack = keyStack + entry.getKey() + ".";
				getAllKeys((Map<String, Object>) entry.getValue());
				
			} else if (entry.getValue() instanceof ArrayList) {
				keyStack = keyStack + entry.getKey() + ".";
				List<Object> l = (List) entry.getValue();
				for (Object m : l) {
					if (m instanceof HashMap) {
						getAllKeys((Map<String, Object>) m);
					} else {
						if (m instanceof String) {
							keyStack = keyStack + entry.getKey() + ".";
							keysList.add(keyStack + "." + entry.getKey());
						} else if (m instanceof Integer) {
							keyStack = keyStack + entry.getKey() + ".";
							keysList.add(keyStack);
						}
					}
				}
				if (keyStack.length() > 0) {
					if (keyStack.lastIndexOf(".") == keyStack.length() - 1) {

						if (StringUtils.countMatches(keyStack, ".") == 1) {
							keyStack = "";
						} else {

							keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
						}

					} else {
						keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
						keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
					}
					keyStack = keyStack + ".";
				}
			} else {
				if (keyStack.length() > 0) {
					if(keyStack.equals("."))
						keyStack="#";
						
					keysList.add(keyStack + entry.getKey());
				}
			}
		}
		if (keyStack.length() > 0) {
			if (keyStack.lastIndexOf(".") == keyStack.length() - 1 && StringUtils.countMatches(keyStack, ".") == 1) {
				keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
			} else {
				keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
				keyStack = keyStack.substring(0, keyStack.lastIndexOf("."));
			}
			keyStack = keyStack + ".";
		}
		return keysList;
	}
}
