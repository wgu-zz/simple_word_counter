package zhu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordFrequencier {

	public static void main(String[] args) throws Exception {

		String delimiter = " ,<.>/?;:'\"[{]}\\|`~!@#$%^&*()-=1234567890\n\r";
		Map<String, Integer> result = new HashMap<String, Integer>();
		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
		String word = "";
		char[] cbuf = new char[1];
		while (br.read(cbuf) != -1) {
			String c = new String(cbuf).toLowerCase();
			if (!delimiter.contains(c)) {
				word += c;
				continue;
			}
			if (word == "") {
				continue;
			}
			if (result.containsKey(word)) {
				result.put(word, result.get(word) + 1);
			} else {
				result.put(word, 1);
			}
			word = "";
		}
		result = sortByValue(result);
		int i = 0;
		for (String s : result.keySet()) {
			System.out.println(s + ": " + result.get(s));
			i += result.get(s);
		}
		System.out.println(i);

	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return ((o1.getValue()).compareTo(o2.getValue())) * -1;
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
