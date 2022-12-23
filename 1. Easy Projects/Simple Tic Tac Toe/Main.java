import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String[][] matrix = splitInput("_________");
		String letter = "X";
		printBoard(matrix);
		while (true) {
			updateMatrix(takeUserInput(matrix), matrix, letter);
			printBoard(matrix);
			if (checkGameState(matrix))
				return;

			if (letter.equals("X"))
				letter = "O";
			else
				letter = "X";
		}
	}

	public static int[] takeUserInput(String[][] matrix) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String line = scanner.nextLine();
			String[] spl = line.split(" ");
			if (!isNumeric(spl[0]) || !isNumeric(spl[1])) {
				System.out.println("You should enter numbers!");
				continue;
			}
			
			int[] ls = {Integer.parseInt(spl[0]), Integer.parseInt(spl[1])};
			if ((ls[0] < 1 || ls[0] > 3) || (ls[1] < 1 || ls[1] > 3)) {
				System.out.println("Coordinates should be from 1 to 3!");
				continue;
			}
			if (!isEmptyCell(matrix, ls))
				System.out.println("This cell is occupied! Choose another one!");
			else {
				ls[0] -= 1;
				ls[1] -= 1;
				return ls;
			}

		}
		
	}

	public static String[][] updateMatrix(int[] coord, String[][] matrix, 
		String letter) {
		matrix[coord[0]][coord[1]] = letter;
		return matrix;
	}

	public static boolean isEmptyCell(String[][] matrix, int[] coord) {
		if (matrix[coord[0] - 1][coord[1] - 1].equals(" "))
			return true;
		return false;
	}

	public static String takeInput() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		return input;
	}


	public static String[][] splitInput(String input) {
		String[] ls = input.split("");
		String[][] matrix = new String[3][3];

		int modifier = 0;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix.length; j++) {
				if (ls[modifier].equals("_"))
					matrix[i][j] = " ";
				else
					matrix[i][j] = ls[modifier];
				modifier++;
			}
			
		return matrix;
	}


	public static void printBoard(String[][] ls) {
		System.out.println("---------");
		System.out.printf("| %s %s %s |%n", ls[0][0], ls[0][1], ls[0][2]);
		System.out.printf("| %s %s %s |%n", ls[1][0], ls[1][1], ls[1][2]);
		System.out.printf("| %s %s %s |%n", ls[2][0], ls[2][1], ls[2][2]);
		System.out.println("---------");
	}


	public static boolean checkGameState(String[][] matrix) {
		int xRows  = checkRows("X", matrix);
		int oRows  = checkRows("O", matrix);
		int totalX = checkTotal("X", matrix);
		int totalO = checkTotal("O", matrix);
		int empty  = checkTotal(" ", matrix);
		if 		(xRows >= 1 && oRows < 1) {
			System.out.println("X wins");
			return true;
		}
		else if (oRows >= 1 && xRows < 1) {
			System.out.println("O wins");
			return true;
		}
		else if (oRows == 0 && xRows == 0 && empty == 0) {
			System.out.println("Draw");
			return true;
		}
		else if ((xRows >= 1 && oRows >= 1) || Math.abs(totalX - totalO) > 1) {
			System.out.println("Impossible");
			return true;
		}
		return false;
	}


	public static int checkRows(String letter, String[][] matrix) {
		int total = 0;
		
		// Horizontal
		for (String[] row : matrix)
			if (row[0].equals(letter) && row[1].equals(letter) && row[2].equals(letter))
				total++;

		// Vertical
		for (int i = 0; i < matrix.length; i++) {
			int num = 0;
			for (int j = 0; j < matrix[i].length; j++)
				if (matrix[j][i].equals(letter))
					num++;
			if (num >= 3)
				total++;
		}

		// Diagonal
		if (matrix[0][0].equals(letter) && matrix[1][1].equals(letter) 
			&& matrix[2][2].equals(letter))
			total++;
		if (matrix[0][2].equals(letter) && matrix[1][1].equals(letter) 
			&& matrix[2][0].equals(letter))
			total++;



		return total;
	}


	public static int checkTotal(String letter, String[][] matrix) {
		int total = 0;
		for (String[] row : matrix)
			for (String item : row)
				if (item.equals(letter))
					total++;
		return total;
	}


	public static boolean isNumeric(String input) {
		try {
			double n = Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}