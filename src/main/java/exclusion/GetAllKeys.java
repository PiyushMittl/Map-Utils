package exclusion;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GetAllKeys {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Map<String, Object> m = new LinkedHashMap<>();
		Map<String, Object> m1 = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();
		ArrayList<Map<String, Object>> mlist2 = new ArrayList<>();
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
		mlist2.add(m3);

		m1 = new LinkedHashMap<>();
		m2 = new LinkedHashMap<>();
		m3 = new LinkedHashMap<>();

		m1.put("PROTOCOL_ID", "2");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "2");
		m2.put("DOCUMENT_TYPE", "abc");
		 m2.put("mlist2", mlist2);

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

		
		// System.out.println("Before: "); String before = gson.toJson(m);
		// System.out.println(before);
		
		List<String[]> map = getAllKeys(m);

		System.out.println(map);

	}

	static List<String[]> keysList = new ArrayList();
	static String[] keyStack = new String[1000];
	static String[] keyStackClone = new String[1000];
	static boolean listFlag = false;
	static int i = 0;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String[]> getAllKeys(Map<String, Object> map) {
		for (Map.Entry entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				if (i == -1) {
					i = 0;
				}
				keyStack[i++] = (String) entry.getKey();
				getAllKeys((Map<String, Object>) entry.getValue());
				keyStack[i--] = "";
				// i--;
			} else if (entry.getValue() instanceof ArrayList && listFlag!=true) {
				List<Object> l = (List) entry.getValue();
				listFlag = true;
				for (Object o : l) {
					if (o instanceof Map) {
						keyStack[i++] = (String) entry.getKey();
						getAllKeys((Map<String, Object>) o);
						keyStack[i--] = "";
					} else if (o instanceof String) {
					} else if (o instanceof Integer) {
					}

				}
				if (i > 0)
					keyStack[i--] = "";
				
				listFlag=false;
				
			} else {
				keyStack[i++] = (String) entry.getKey();
				keysList.add(keyStack.clone());
				keyStack[i--] = "";
			}
			// i--;
		}
		keysList.add(keyStack.clone());
		return keysList;
	}
}
