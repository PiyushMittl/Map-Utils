package exclusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NestedMapCopy{

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

int i=0;
int j=1;

		Map<String, Object> m1 = new LinkedHashMap<>();
		Map<String, Object> m2 = new LinkedHashMap<>();
		Map<String, Object> m3 = new LinkedHashMap<>();
		Map<String, Object> m4 = new LinkedHashMap<>();
		Map<String, Object> m = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();

		m1.put("PROTOCOL_ID", "PROTOCOL_ID" + i + "" + j);
		m1.put("PROTOCOL_TYPE", "PROTOCOL_TYPE" + i + "" + j);
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "" + i + "" + j);
		m2.put("DOCUMENT_TYPE", "DOCUMENT_TYPE" + i + "" + j);
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "SETTLEMENT_ID" + i + "" + j);
		m3.put("PROGRAM_ID", "PROGRAM_ID" + i + "" + j);
		mlist.add(m3);
		m4.put("PROTOCOL_ID", "1");
		m4.put("PROTOCOL_TYPE", "abc");

		m.put("_id", "" + i);
		m.put("NAME", "piyush");
		m.put("SETTLEMENT", mlist);
		m.put("PROTOCOL", m4);
	
		/*Map<String,Object> copyMap=*/nestedCopy(m);
		
		
		Map<String, Object> m11 = new LinkedHashMap<>();
		Map<String, Object> m21 = new LinkedHashMap<>();
		Map<String, Object> m31 = new LinkedHashMap<>();
		Map<String, Object> m41 = new LinkedHashMap<>();
		Map<String, Object> mm = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist1 = new ArrayList<>();

		m11.put("PROTOCOL_ID", "PROTOCOL_ID" + i + "" + j);
		m11.put("PROTOCOL_TYPE", "PROTOCOL_TYPE" + i + "" + j);
		m21.put("PROTOCOL", m11);
		m21.put("DOCUMENT_ID", "" + i + "" + j);
		m21.put("DOCUMENT_TYPE", "DOCUMENT_TYPE" + i + "" + j);
		m31.put("DOCUMENT", m21);
		m31.put("SETTLEMENT_ID", "SETTLEMENT_ID" + i + "" + j);
		m31.put("PROGRAM_ID", "PROGRAM_ID" + i + "" + j);
		mlist1.add(m31);
		m41.put("PROTOCOL_ID", "1");
		m41.put("PROTOCOL_TYPE", "abc");

		mm.put("_id", "" + i);
		mm.put("NAME", "piyush");
		mm.put("SETTLEMENT", mlist1);
		mm.put("PROTOCOL", m41);
		
		
		//System.out.println(mm);
		System.out.println(copyMap);
		}

	static Map<String,Object> copyMap=new HashMap();
	static int mapflag=0;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String nestedCopy(Map<String, Object> map) {
		for (Map.Entry<String, Object> entryProject : map.entrySet()) {
			
			if(entryProject.getValue() instanceof Map)
			{
				mapflag=1;
				copyMap.put(entryProject.getKey(),nestedCopy((Map<String, Object>) entryProject.getValue()));
			}
			else if(entryProject.getValue() instanceof List){
				ArrayList<Map> al=(ArrayList<Map>) entryProject.getValue();
				for(Map<String,Object> m:al){
					nestedCopy(m);
				}
			}
			else{
				copyMap.put(entryProject.getKey(),entryProject.getValue());
				//return (String) entryProject.getValue();
			}

		}
		System.out.println(copyMap);
		return null;
	}

}
