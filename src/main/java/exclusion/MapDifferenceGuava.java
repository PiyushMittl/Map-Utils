package exclusion;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
public class MapDifferenceGuava {
	
	public static void main(String[] args) {

		
		Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		Map<String, Integer> right = ImmutableMap.of("b", 2, "c", 4, "d", 5);
		
		
		MapDifference<String, Integer> diff = Maps.difference(left, right);

		diff.entriesInCommon(); // {"b" => 2}
		diff.entriesDiffering(); // {"c" => (3, 4)}
		diff.entriesOnlyOnLeft(); // {"a" => 1}
		diff.entriesOnlyOnRight(); // {"d" => 5}
		
		
	}
}
