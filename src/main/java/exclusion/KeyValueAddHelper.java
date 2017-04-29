package exclusion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class KeyValueAddHelper {

	
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Map<String, Object> m = new LinkedHashMap<>();
		Map<String, Object> m1 = new LinkedHashMap<>();
		ArrayList<Map<String, Object>> mlist = new ArrayList<>();
		Map<String, Object> m2 = new LinkedHashMap<>();
		Map<String, Object> m3 = new LinkedHashMap<>();
		Map<String, Object> m4 = new LinkedHashMap<>();
		
		
		Map<String, Object> mm1 = new LinkedHashMap<>();
		Map<String, Object> mm2 = new LinkedHashMap<>();
		
		

		
		m1.put("PROTOCOL_ID", "1");
		m1.put("PROTOCOL_TYPE", "abc");
		m2.put("PROTOCOL", m1);
		m2.put("DOCUMENT_ID", "1");
		m2.put("DOCUMENT_TYPE", "abc");
		m3.put("DOCUMENT", m2);
		m3.put("SETTLEMENT_ID", "1");
		m3.put("PROGRAM_ID", "1");
		mlist.add(m3);
		
		mm1.putAll(m3);
		
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
		
		mm2.putAll(m3);
		
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

		String globalkeyToRemove = "SETTLEMENT.DOCUMENT.DOCUMENT_ID";
		
		
		String[] globalkeys = globalkeyToRemove.split("\\.");

		Map map = merge(mm1,mm2);

		System.out.println("after: ");
		String after = gson.toJson(map);
		System.out.println(after);

	}

	//SETTLEMENT.DOCUMENT.PROTOCOL.PROTOCOL_ID
	//static int keysArrayIndex = 0;

	 public static HashMap merge(Map<String, Object> mm1, Map<String, Object> mm2) {
	        HashMap c = new HashMap();
	        
	        for(Object key : mm1.keySet()) {
	            String key2 = (String) key;
	            Object dup = mm1.get(key2);
	            c.put(key2, dup);
	        }
	        for(Object key : mm2.keySet()) {
	            String key2 = (String) key;
	            Object dup = mm2.get(key2);
	            if(dup instanceof HashMap && c.containsKey(key2) 
	                    && c.get(key2) instanceof HashMap) {
	                HashMap kk = (HashMap) c.get(key2);
	                HashMap p = merge(kk, (HashMap) dup);
	                c.put(key2, p);
	            } else if(dup instanceof HashMap && c.containsKey(key2) 
	                    && !(c.get(key2) instanceof HashMap)) {
	                HashMap kk = new HashMap();
	                kk.put(key2, c.get(key2));
	                HashMap p = merge(kk, (HashMap) dup);
	                c.put(key2, p);
	            } else {
	                c.put(key2, dup);
	            } 
	        }
	        return c;
	    }
}
