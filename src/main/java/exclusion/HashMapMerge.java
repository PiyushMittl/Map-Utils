package exclusion;

import java.util.HashMap;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HashMapMerge {
    public static void main(String[] args) {
    	
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HashMap a = new HashMap();
        /**
         * First row
         */
        HashMap b = new HashMap();
        b.put("name", "name-1");
        b.put("roll_0", "roll-11");
        b.put("roll_1", "roll-12");
        HashMap b10 = new HashMap();
        b10.put("item_0", 0);
        b10.put("item_1", 1);
        b.put("item_0", b10);
        
        HashMap c = new HashMap();
        c.put("name", "name-2");
        c.put("roll", "roll-2");
        
        HashMap d = new HashMap();
        d.put("student_0", b);
        d.put("student_1", c);  
        d.put("student_2", "TATA");
        
        /**
         * Second row
         */
        HashMap b1 = new HashMap();
        b1.put("grade", "grade-1");
        HashMap c10 = new HashMap();
        c10.put("item_2", 33);
        c10.put("item_3", 44);
        b1.put("item_0", c10);
        
        HashMap c1 = new HashMap();
        c1.put("grade", "grade-2");
        
        HashMap d1 = new HashMap();
        d1.put("student_0", b1);
        d1.put("student_1", c1); 
        d1.put("student_2", b1);
        
        System.out.println("d:------------> ");
		String before = gson.toJson(d);
		System.out.println(before);
        System.out.println("d1:------------> ");
		String bb = gson.toJson(d1);
		System.out.println(bb);
        
        HashMapMerge hashMapMerge = new HashMapMerge();
        a = hashMapMerge.merge(d, d1);
        System.out.println("result===========================: ");
		String bb1 = gson.toJson(a);
		System.out.println(bb1);
        
        
    }
    
    public HashMap merge(HashMap a, HashMap b) {
        HashMap c = new HashMap();
        
        for(Object key : a.keySet()) {
            String key2 = (String) key;
            Object dup = a.get(key2);
            c.put(key2, dup);
        }
        for(Object key : b.keySet()) {
            String key2 = (String) key;
            Object dup = b.get(key2);
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