package jdk8Practice;

import java.util.Scanner;

/*It is the wedding day of Sanchi, the beautiful princess of Byteland. Her fiance Krishna is planning to gift her an awesome ruby necklace. Krishna has currently b -blue rubies, g -green rubies, r-red rubies and y -yellow rubies.
He has to arrange the rubies next to each other in a straight line to make the necklace. But, there are a couple of rules to be followed while making this necklace:

         A blue ruby should be followed by either a blue ruby or a red ruby
         A green ruby should be followed by either a green ruby or a yellow ruby
         A red ruby should be followed by either a green ruby or a yellow ruby
         A yellow ruby should be followed by either a blue ruby or a red ruby
         If it is possible, we should always start a necklace with a blue or a red ruby

Can you tell what is the maximum possible length of the necklace that Krishna can make. The length of a necklace is the number of rubies in it.

Input Format
The first line contains an integer representing b.
The second line contains an integer representing r.
The third line contains an integer representing y.
The fourth line contains an integer representing g.

Constraints
0 <= b, r, y, g <= 2000
At least one of b, r, y, g is greater than 0

Output Format
A single integer which is the answer to the problem.

Input:
b 	1
r	1
y	1
g	0
Output: 3
b r y
r y b
Input:
b 989
r 147
Output: 990


START:	Blue blue   |  blue red*/

public class MaxNecklaceLength {
	static int bCount, rCount, yCount, gCount;

	public static void main(String[] args) throws Exception {
		String maxString = "";
		Scanner scanner = new Scanner(System.in);
		// System.out.println("Enter no. of stones as blue, red, yellow, green:");
		bCount = scanner.nextInt();
		rCount = scanner.nextInt();
		yCount = scanner.nextInt();
		gCount = scanner.nextInt();
		maxString = appendRule(maxString);
		System.out.println(maxString.length());
	}

	public static String appendRule(String start) {

		if (rCount == 0) {
			if (bCount != 0) {
				start = addBlue(start);
				return start;
			} else
				return start = addGreenYellow(start);
		} else {
			start = addBlue(start);
			start = addRedYellow(start);

			if (rCount != 0)
				start = start + "R";
			else
				return start;
		}
		start = addGreenYellow(start);
		return start;
	}

	private static String addGreenYellow(String start) {
		start = addGreen(start);
		if (yCount > 0)
			start = start + "Y";
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
