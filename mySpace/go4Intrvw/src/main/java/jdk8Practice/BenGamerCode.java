/**
 * 
 */
package jdk8Practice;

	/**
	 * Ben - The Gamer (100 Marks) Ben is one of the best gamers in India. He also
	 * happens to be an excellent programmer. So, he likes to play games which
	 * require use of both gaming skills as well as programming skills. One such
	 * game is SpaceWar.
	 * 
	 * In this game there are N levels and M types of available weapons. The levels
	 * are numbered from 0 to N-1 and the weapons are numbered from 0 to M-1 . Ben
	 * can clear these levels in any order. In each level, some subset of these M
	 * weapons is required to clear this level. If in a particular level, Ben needs
	 * to buy x new weapons, he will pay x2 coins for it. Also note that Ben can
	 * carry all the weapons he has currently to the next level . Initially, Ben has
	 * no weapons. Can you tell the minimum coins required such that Ben can clear
	 * all the levels.
	 * 
	 * 
	 * Input Format The first line of input contains 2 space separated integers; N -
	 * the number of levels in the game and M - the number of types of weapons. N
	 * lines follows. The ith of these lines contains a binary string of length M.
	 * If the jth character of this string is 1 , it means we need a weapon of type
	 * j to clear the ith level.
	 * 
	 * Constraints 1 <= N <=20 1<= M <= 20
	 * 
	 * Output Format Print a single integer which is the answer to the problem.
	 *
	 * Sample TestCase 1 
	 * Input 
	 * 1 4 
	 * 0101 
	 * Output 4 
	 * Explanation There is only one level
	 * in this game. We need 2 types of weapons - 1 and 3. Since, initially Ben has
	 * no weapons he will have to buy these, which will cost him 22 = 4 coins.
	 * 
	 * Sample TestCase 2
	 * Input 3 3 
	 * 111 
	 * 001 
	 * 010 
	 * Output 3 
	 * Explanation There are 3 levels in this game. The 0th level (111) requires all 3 types of weapons. The
	 * 1st level (001) requires only weapon of type 2. The 2nd level requires only
	 * weapon of type 1. If we clear the levels in the given order(0-1-2), total
	 * cost = 32 + 02 + 02 = 9 coins. If we clear the levels in the order 1-2-0, it
	 * will cost = 12 + 12 + 12 = 3 coins which is the optimal way.
	 * 
	 */
	
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Scanner;


	public class BenGamerCode {
			
		private static Integer levelCounter = 0;
		private static Double coinsUsed = 0.0;
		private static String reverse = "";
		public static void main(String[] args) {

			Scanner scanner = new Scanner(System.in);
			Integer levelCount = scanner.nextInt();
			Integer weaponCount = scanner.nextInt();
			Integer listLevelCount = levelCount;
			levelCounter = levelCount;
			ArrayList<Levels> list = new ArrayList<>(levelCount);
			HashMap<Integer, Integer> countMaxMap = new HashMap<>();

			while (listLevelCount > 0) {
				list.add(new Levels(scanner.next()));
				listLevelCount--;
			}

			Map<Integer, Boolean> map = new HashMap<>();

			list.forEach(level -> {
				Boolean flag = true;
				if (level.getLevel().length() != weaponCount)
					flag = false;
				map.put(0, flag);
			});

			if (map.get(0) && (levelCount >= 1 && weaponCount >= 1 && levelCount <= 20 && weaponCount <= 20)) {
				List<Integer> weaponsUsed = new ArrayList<>(weaponCount);
				Collections.sort(list);
				recursiveCoinsCount(weaponCount, list, weaponsUsed);
				countMaxMap.put(1, coinsUsed.intValue());

				coinsUsed = 0.0;
				weaponsUsed.clear();
				levelCounter = levelCount;
				reverse = "1";
				Collections.sort(list);
				recursiveCoinsCount(weaponCount, list, weaponsUsed);
				countMaxMap.put(2, coinsUsed.intValue());
			}
			System.out.println(Collections.min(countMaxMap.values()));
		}

		private static void recursiveCoinsCount(Integer weaponCount, ArrayList<Levels> list,
				List<Integer> weaponsUsed) {
			reverse = "";
			ArrayList<Levels> innerList = new ArrayList<>();
			ArrayList<Levels> finalInnerLevelList = new ArrayList<>();

			list.forEach(level-> innerList.add(level));

			Levels levels = list.get(0);
			levelCounter --;
			innerList.remove(levels);

			if (levels.getLevel().length() == weaponCount) {
				Double innerCoinsUsed = 0.0;
				String level = levels.getLevel();
				char[] weaponChar = level.toCharArray();

				for (int j = 0; j < weaponChar.length; j++) {
					if (weaponChar[j] == '1' && !weaponsUsed.contains(j)) {
						weaponsUsed.add(j);
						innerCoinsUsed = innerCoinsUsed + 1;
					}
				}
				coinsUsed = coinsUsed + Math.pow(innerCoinsUsed, 2);
			}

			for(Levels level1: innerList)
			{
				char[] chLevel = level1.getLevel().toCharArray();
				for (int i = 0; i < chLevel.length; i++)
					if (chLevel[i] == '1' && weaponsUsed.contains(i))
						chLevel[i] = '0';
				finalInnerLevelList.add(new Levels(String.valueOf(chLevel)));
			}
			Collections.sort(finalInnerLevelList);

			while(levelCounter>0)
				recursiveCoinsCount(weaponCount, finalInnerLevelList, weaponsUsed);
		}
		

		static class Levels implements Comparable<Levels> {

			String level;

			public Levels(String level) {
				this.level = level;
			}

			@Override
			public int compareTo(Levels localLevel) {
				int countOf1InLocal = 0;
				int countOf1InClass = 0;

				char[] ch = localLevel.getLevel().toCharArray();
				for (int i = 0; i < ch.length; i++)
					if (ch[i] == '1')
						countOf1InLocal = countOf1InLocal + 1;

				char[] chClass = this.level.toCharArray();
				for (int i = 0; i < chClass.length; i++)
					if (chClass[i] == '1')
						countOf1InClass = countOf1InClass + 1;

				if (countOf1InClass > countOf1InLocal)
					return 1;
				else if (countOf1InClass < countOf1InLocal)
					return -1;
				else {
					int decimal1 = Integer.valueOf(localLevel.getLevel(), 2);
					int decimal2 = Integer.valueOf(this.level, 2);

					if("".equalsIgnoreCase(reverse)){
						if (decimal1 > decimal2)
							return 1;
						else if (decimal1 < decimal2)
							return -1;
					} else if("1".equalsIgnoreCase(reverse)){
						if (decimal1 < decimal2)
							return 1;
						else if (decimal1 > decimal2)
							return -1;
					}
					return 0;
				}
			}

			public String getLevel() {
				return level;
			}

		}
			

		

	}

