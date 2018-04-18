package jdk8Practice;

import java.util.Scanner;

public class MaxNecklaceLength {
	static int bCount, rCount, yCount, gCount;

	public static void main(String[] args) throws Exception {
		String maxString = "";
		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter no. of stones as blue, red, yellow, green:");
		bCount = scanner.nextInt();
		rCount = scanner.nextInt();
		yCount = scanner.nextInt();
		gCount = scanner.nextInt();
		maxString = appendRule(maxString);
		System.out.println("Final output : "+ maxString.length());
	}

	public static String appendRule(String start) {

		if (rCount == 0) {
			start = addGreen(start);
			if (yCount != 0)
				start = start + "Y";
			else
				return start;
			start = addBlue(start);
		} else {
			start = addBlue(start);
			start = addRedYellow(start);

			if (rCount != 0)
				start = start + "R";
			else
				return start;
			start = addGreen(start);
			if (yCount > 0)
				start = start + "Y";
		}
		return start;
	}

	private static String addRedYellow(String start) {
		while (rCount > 0 & yCount > 0) {
			if (gCount != 0 && rCount == 1) {
				break;
			}
			start = start + "RY";
			rCount--;
			yCount--;
		}
		return start;
	}

	private static String addBlue(String start) {
		while (bCount > 0) {
			start = start + "B";
			bCount--;
		}
		return start;
	}

	private static String addGreen(String start) {
		while (gCount > 0) {
			start = start + "G";
			gCount--;
		}
		return start;
	}

}
