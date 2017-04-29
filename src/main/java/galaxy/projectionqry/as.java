package galaxy.projectionqry;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;


public class as {

	public static void main(String[] args) {

		List<String> pjn=new ArrayList<>();
		pjn.add("a");
		pjn.add("b");
		pjn.add("c");
		pjn.add("d");
		pjn.add("e");
		
		System.out.println(StringUtils.join(pjn, ','));
		
		
	}

}
