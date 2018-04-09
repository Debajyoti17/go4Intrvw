package jdk8Practice;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CandidateCode {

	public static void main(String args[]) throws Exception {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int m = s.nextInt();
		s.nextLine();
		String[] w = new String[n];
		for (int i = 0; i < n; i++) {
			w[i] = s.nextLine();
		}
		s.close();

		List<Integer> leftLevels = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			leftLevels.add(i);
		}
		String weaponStatus = getInitialWeaponStatus(m);
		int l = getLeastWeaponLevel(w);
		leftLevels.remove(Integer.valueOf(l));
		long cost = (long) Math.pow(countOfOnes(w[l]), 2);
		weaponStatus = updateWeaponStatus(weaponStatus, w[l]);

		while (!leftLevels.isEmpty()) {
			int lev = leftLevels.get(0);
			int d = diffOfWeapons(weaponStatus, w[lev]);
			for (Integer i : leftLevels) {
				int d1 = diffOfWeapons(weaponStatus, w[i]);
				if (d1 < d) {
					d = d1;
					lev = i;
				}
			}
			leftLevels.remove(Integer.valueOf(lev));
			cost += (long) Math.pow(d, 2);
			weaponStatus = updateWeaponStatus(weaponStatus, w[lev]);
		}
		System.out.println(cost);
	}

	private static String updateWeaponStatus(String weaponStatus, String weaponsAtALevel) {
		int i1 = Integer.parseInt(weaponStatus, 2);
		int i2 = Integer.parseInt(weaponsAtALevel, 2);
		int i3 = (i1 | i2);
		String s = Integer.toBinaryString(i3);
		int d = weaponStatus.length() - s.length();
		return adjust(s, d);
	}

	private static String adjust(String s, int d) {
		String st = "";
		for (int i = 0; i < d; i++) {
			st += "0";
		}
		return st + s;
	}

	private static int getLeastWeaponLevel(String[] w) {
		int level = 0;
		int onesAtLevel = countOfOnes(w[0]);
		for (int i = 1; i < w.length; i++) {
			if (countOfOnes(w[i]) < onesAtLevel) {
				level = i;
				onesAtLevel = countOfOnes(w[i]);
			}
		}
		return level;
	}

	private static int diffOfWeapons(String currentWeapons, String weaponsAtALevel) {
		int i1 = Integer.parseInt(currentWeapons, 2);
		int i2 = Integer.parseInt(weaponsAtALevel, 2);
		int i3 = (i1 | i2) ^ i1;
		String s = Integer.toBinaryString(i3);
		return countOfOnes(s);
	}

	private static int countOfOnes(String s) {
		int c = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '1')
				c++;
		}
		return c;
	}

	private static String getInitialWeaponStatus(int m) {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < m; i++) {
			s.append("0");
		}
		return s.toString();
	}
}
