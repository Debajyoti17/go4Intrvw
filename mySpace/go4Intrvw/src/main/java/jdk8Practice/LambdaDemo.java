package jdk8Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LambdaDemo extends Thread{

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("devfj");
		list.add("djevkh");
		list.add("dfval");
		list.add("kev");
		list.add("aev");
				
		// () -> {} replaces anonymous class
		new Thread(()->{
			System.out.println("Before forEach impl-------");
			list.forEach(n-> System.out.println(n));

			System.out.println("Running thread..");
			Collections.sort(list, (String o1, String o2)-> o1.compareTo(o2));
			System.out.println("After forEach impl-------");
			list.forEach(System.out:: println);
			System.err.println("Filter is used for matching elements through test() of @FunctionalInterface Predicate :");
			Predicate<String> predicate = (str)-> ((String) str).startsWith("d");
			list.stream().filter((name) -> predicate.test(name)).forEach(System.out :: println);
			
			System.err.println("Names starts with 'd' n fixed size: ");
			Predicate<String> startsWithJ = (n) -> n.startsWith("d"); 
			Predicate<String> fiveLetterLong = (n) -> n.length() == 5;
			
			list.stream().filter(startsWithJ.and(fiveLetterLong)).forEach(System.out :: println);
			
			
			
			Map<Integer, String> mapObj = new HashMap<Integer, String>(); 
			mapObj.put(2,"Dev");
			mapObj.put(5,"adn");
			mapObj.put(3,"ojhev");
			mapObj.put(1,"xyz");
			
			List<String> list2 = mapObj.values().stream().collect(Collectors.toList());
			System.out.println("map list --- "+list2);
		});
//		.start();	
		
		
		mapReduceOperation();
		
//		mapList(list);
		
		
	}
//GroupBy
	private static void mapListGropuByType(List<Object> list) {
		
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		
		for (Object obj: list) {
			String type = "TYPE"; //obj.getType 
			if(map.containsKey(type)) {
				List<Object> listObj = map.get(type);
				listObj.add(obj);
			} else {
				List<Object> listObj = new ArrayList<Object>();
				listObj.add(obj);
				map.put(type, listObj);
			}
		}
	}

	private static void mapReduceOperation() {
		List<Integer> costBeforeGST = Arrays.asList(100, 200, 300, 400, 500);
		System.out.println("costAfterGST -----:");
		costBeforeGST.stream().map((cost) -> cost + .12*cost).forEach(System.out :: println);
		
//		System.out.println(costBeforeGST.stream().map((cost) -> cost + .12*cost)));
	}

}
