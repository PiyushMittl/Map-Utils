package exclusion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RemoveEmptyMap {

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

		//mapForProtocolList1.put("p1", "p1");
		//mapForProtocolList2.put("p2", "p2");
		//mapForProtocolList3.put("p3", "p3");
		listInProtocol.add(mapForProtocolList1);
		listInProtocol.add(mapForProtocolList2);
		listInProtocol.add(mapForProtocolList3);

		//m1.put("PROTOCOL_ID", "1");
		//m1.put("PROTOCOL_TYPE", "abc");
		m1.put("PROTOCOL_LIST", listInProtocol);
		// add list here

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

	//	m4.put("PROTOCOL_ID", "1");
	//	m4.put("PROTOCOL_TYPE", "abc");
		m.put("NAME", "piyush");
		m.put("SETTLEMENT", mlist);

		m.put("PROTOCOLL", m4);
		m.put("TEST", "TEST");
		m.put("PROTOCOL2", m4);

		m.put("SETTLEMENT2", mlist);

		System.out.println("Before: ");
		String before = gson.toJson(m);
		System.out.println(before);

		Map map = removeEmptyMap(m);
	//	map = removeEmptyMap(map);

		System.out.println(map);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map removeEmptyMap(Map<String, Object> rawMap) {
		Iterator it = rawMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			//if key is an instance of map and its an empty then remove
			if(pair.getValue() instanceof Map && ((Map)pair.getValue()).isEmpty()){
					it.remove();
					break;
			}else if(pair.getValue() instanceof Map && !((Map)pair.getValue()).isEmpty()){
				removeEmptyMap((Map<String, Object>) pair.getValue());
				pair.getValue();
			}
			else if(pair.getValue() instanceof ArrayList && ((ArrayList) pair.getValue()).isEmpty()){
				it.remove();
				//break;
			}
			else if (pair.getValue() instanceof ArrayList) {
				ArrayList nestedMapList = (ArrayList) pair.getValue();
				Iterator<Map<String,Object>> listIt = nestedMapList.iterator();
				while (listIt.hasNext()) {
					Map eachNestedMap = listIt.next();
					if(eachNestedMap.isEmpty())
					{
						listIt.remove();
					}
					else{
						removeEmptyMap(eachNestedMap);
					}
				}
			} 
		}
		//keysArrayIndex = 0;
		return rawMap;
	}
}
