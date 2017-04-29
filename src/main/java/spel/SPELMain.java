package spel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import alexh.weak.Dynamic;

public class SPELMain {
public static void main(String[] args) {
	ExpressionParser parser = new SpelExpressionParser();  	
	/*Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");  
	
	String message = exp.getValue(String.class);  
	System.out.println(message);*/  
	
	
	
	Map nestedMap = new HashMap();
	
	nestedMap.put("name", "piyush");
	
	Dynamic message1 = Dynamic.from(nestedMap);
	Dynamic investment1 = message1.get("name");
	investment1.isPresent();
	
	Expression exp1 = parser.parseExpression("Dynamic.from(nestedMap).get(\"name\")");
	
	Dynamic message22 = (Dynamic)exp1.getValue();
	
	//Dynamic investment1 = message1.get("product").get("investment").get("investment-1");
	
}
}
