package galaxy.projectionqry;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;


public class as2 {

	public static void main(String[] args) {

		HashMap<String,String> pjn=new HashMap<String,String>();
		pjn.put("key1","val1");
		pjn.put("key2","val2");
		pjn.put("key3","val3");
		pjn.put("key4","val4");
		pjn.put("key5","val5");
		
		StringBuffer filterQuery=new StringBuffer("$filter=");
		
		
		for (Map.Entry<String,String> entry : pjn.entrySet()) {
			filterQuery.append(entry.getKey()).append(" eq ").append("'"+entry.getValue()+"'").append(" and ");
		}
		filterQuery.delete(filterQuery.length()-5, filterQuery.length());
		
		
		
		/*Iterator entries = pjn.entrySet().iterator();
		 do{ 
		  Entry thisEntry = (Entry) entries.next();
		  Object key = thisEntry.getKey();
		  Object value = thisEntry.getValue();
		  // ...
		}while (entries.hasNext()); */
		
		System.out.println(filterQuery);
		
		
	}

}
