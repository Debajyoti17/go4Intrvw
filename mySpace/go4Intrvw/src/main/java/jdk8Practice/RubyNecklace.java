package jdk8Practice;

import java.util.Arrays;
import java.util.List;
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


START:	Blue blue   |  blue red

START:	red green  |  red yellow

green green  | green yellow
yellow blue  | yellow red

*/
public class RubyNecklace {
	static String maxStr = "";

	public static void main(String[] args) throws Exception {
		String maxString = "";
		List<String> colours = Arrays.asList("Blue", "Red", "Yellow", "Green" );
		Scanner scanner = new Scanner(System.in);
//		System.out.println("Enter no. of stones as blue, red, yellow, green:");
		int blue = scanner.nextInt(), red = scanner.nextInt(), yellow = scanner.nextInt(), green = scanner.nextInt();
		if(blue > 2000 || red > 2000 || yellow > 2000 || green > 2000)
			throw new Exception("stone count cannot be exceeded 2000");
		for (String colour : colours) {
			String finalString = findMaxLenNecklace(colour, blue, red, yellow, green);
//			System.out.println(colour + "-------" + finalString);
			if (maxString.length() < finalString.length())
				maxString = finalString;
		}
		System.out.println("Final output : " + maxString.length());
	}

	public static String findMaxLenNecklace(String colour, int bCount, int rCount, int yCount, int gCount) {
		String start = "";
		if (bCount != 0 || rCount != 0 || yCount != 0 || gCount != 0)
			start = startInput(start, colour, bCount, rCount, yCount, gCount);
		if (start.isEmpty())
			return "";
//		System.out.println("start : " + start);

		if (start == "B")
			bCount--;
		else if (start == "Y")
			yCount--;
		else if (start == "G")
			gCount--;
		else if (start == "R")
			rCount--;

		String finalStr = appendRule(start, bCount, rCount, yCount, gCount);

//		System.out.println("Output : " + finalStr);
//		System.out.println("  String length-------: " + finalStr.length());
		return finalStr;
	}

	public static String appendRule(String start, int bCount, int rCount, int yCount, int gCount) {

		if (((start.endsWith("B") || start.endsWith("Y")) && yCount != 0 && bCount == 0 && rCount == 0)
				|| ((start.endsWith("R") || start.endsWith("G")) && rCount != 0 && yCount == 0 && gCount == 0)
				|| (start.endsWith("B") && bCount == 0 && gCount != 0 && rCount == 0 && yCount == 0)
				|| (start.endsWith("G") && bCount != 0 && gCount == 0 && rCount == 0 && yCount == 0))
			return maxStr = start;

		if (start.endsWith("B") || start.endsWith("Y")) {
			while (bCount > 0) {
				start = start + "B";
				bCount--;
			}
			if (rCount != 0) {
				start = start + "R";
				rCount--;
			}
		}
		if (start.endsWith("R") || start.endsWith("G")) {
			while (gCount > 0) {
				start = start + "G";
				gCount--;
			}
			if (yCount != 0) {
				start = start + "Y";
				yCount--;
			}
		}

		if (bCount != 0 || rCount != 0 || yCount != 0 || gCount != 0) {
			appendRule(start, bCount, rCount, yCount, gCount);
		} else {
//			System.out.println("Appendrule start : " + start);
			maxStr = start;
		}
		return maxStr;

	}

	public static String startInput(String start, String colour, int bCount, int rCount, int yCount, int gCount) {
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
