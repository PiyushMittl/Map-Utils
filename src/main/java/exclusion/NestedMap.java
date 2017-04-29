package exclusion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import alexh.weak.Dynamic;

public class NestedMap {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Map<String,Object> m=new HashMap<>();
		Map<String,Object> m1=new HashMap<>();
		Map<String,Object> m2=new HashMap<>();
		Map<String,Object> m3=new HashMap<>();
		Map<String,Object> m4=new HashMap<>();
		
		m2.put("PROGRAM_ID", "1");
		m2.put("SETTLEMENT_ID", "2");
		
		m1.put("SETTLEMENT", m2);
		
		
		m.put("NAME", "piyush");
		m.put("SETTLEMENT", m2);
	
		System.out.println(((Map<String, Object>) m.get("SETTLEMENT")).get("PROGRAM_ID"));
		((Map<String, Object>) m.get("SETTLEMENT")).remove("PROGRAM_ID");
		System.out.println(((Map<String, Object>) m.get("SETTLEMENT")).get("PROGRAM_ID"));
	
		
		String keyToRemove = "SETTLEMENT.SETTLEMENT_ID";
		String[] keys = keyToRemove.split("\\.");
		printMap(m,keys);

		
		Iterator<String> it = m.keySet().iterator();
		int i=0;
		while (it.hasNext())
		{
			String key = it.next();
			if (keys[i].equals(key) && i<keys.length-1)
		    it.remove();
		}
		
		
		nestedRemove(m,"SETTLEMENT.PROGRAM_ID");
		
		
	}

	
	
	public static void nestedRemove(Map map, String keyToRemove)
		{
	//String keyToRemove = "SETTLEMENT.PROGRAM_ID";
	String[] keys = keyToRemove.split("\\.");
	Map subMap = null;
	for(int i = 0; i < keys.length -1; ++i)
	{
		subMap = (Map) map.get(keys[i]);
	}
	subMap.remove(keys[1]);
	System.out.println(map);
	}
	
	
	static int size=2;
	static int i=0;
	@SuppressWarnings("rawtypes")
	public static void printMap(Map mp,String[] removeKey) {
		Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	      //  it.remove();
	        if(pair.getKey().equals(removeKey[i++]))
	        {
	        	//removeKey=ArrayUtils.removeElement(removeKey, removeKey[i-1]);
	        	//printMap((Map) pair.getValue(),removeKey);
	        	Map mm=(Map)pair.getValue();
	        	it=mm.entrySet().iterator();
	        }
	    }
	}
	
}
