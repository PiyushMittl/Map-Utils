package exclusion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class asdas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date result;
		try {
		    result = df.parse("2017-04-04T11:18:22.931Z");
		    System.out.println("date:"+result); //prints date in current locale
		}catch(Exception e){
			e.printStackTrace();
		} 
		
	
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
					   2017-04-04T11:18:22.93Z
			sdf.parse("2013-09-29T18:46:19Z");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	
	}

}
