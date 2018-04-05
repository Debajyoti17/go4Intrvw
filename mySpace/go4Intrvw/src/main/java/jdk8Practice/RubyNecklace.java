package jdk8Practice;

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


START:	Blue blue   |  blue red

START:	red green  |  red yellow


yellow blue  | yellow red
green green  | green yellow
*/
public class RubyNecklace {
	static int bCount = 1, rCount = 1, yCount = 1, gCount = 0;
	static String maxStr = "", colour = "";

	public static void main(String[] args) {
		// System.out.println(maxStr.length());

		String start = "";
		if (bCount != 0 || rCount != 0 || yCount != 0 || gCount != 0)
			start = startInput(start, colour);
		System.out.println("start : " + start);
		String finalStr = appendRule(start);

		if (maxStr.length() <= finalStr.length())
			maxStr = finalStr;

		System.out.println("Output : " + maxStr);
		System.out.println("  String length-------: " + maxStr.length());
	}

	private static String appendRule(String start) {

		if ((start.endsWith("Y") && yCount != 0 && bCount == 0 && rCount == 0 && gCount == 0) || (start.endsWith("R") && rCount != 0 && yCount == 0 && bCount == 0 && gCount == 0))
			return start;

		if (start.endsWith("B") || start.endsWith("Y")) {
			while(bCount > 0) {
				start = start + "B";
				bCount--;
			} 
			if (rCount != 0) {
				start = start + "R";
				rCount--;
			}
		} 
		if (start.endsWith("R") || start.endsWith("G")) {
			while(gCount > 0) {
				start = start + "G";
				gCount--;
			}
			if (yCount != 0) {
				start = start + "Y";
				yCount--;
			}
		}

		if (bCount != 0 || rCount != 0 || yCount != 0 || gCount != 0) {
			appendRule(start);
		}
		return start;
	}

	public static String startInput(String start, String colour) {
		if (bCount != 0)
			colour = "Blue";
		else if (rCount != 0)
			colour = "Red";
		else if (yCount != 0)
			colour = "Yellow";
		else if (gCount != 0)
			colour = "Green";

		switch (colour) {

		case "Blue": {

			if (bCount != 0) {
				start = "B";
				bCount--;
			}
			break;
		}

		case "Red": {

			if (rCount != 0) {
				start = "R";
				rCount--;
			}
			break;
		}

		case "Yellow": {

			if (yCount != 0) {
				start = "Y";
				yCount--;
			}
			break;
		}

		case "Green": {

			if (gCount != 0) {
				start = "G";
				gCount--;
			}
			break;
		}

		}

		return start;
	}
}
