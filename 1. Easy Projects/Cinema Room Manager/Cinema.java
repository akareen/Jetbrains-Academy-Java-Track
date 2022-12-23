package cinema;
import java.util.Scanner;

public class Cinema {
	public static void main(String[] args) {
		execution();
	}

	public static void execution() {
		// Setup
		int[] seats = takeCinemaSetup();
		String[][] seatingPlan = makeSeatingPlan(seats);

		// Statistics
		int ticketsSold = 0;
		int totalTickets = seats[0] * seats[1];

		int totalPaid = 0;
		int totalIncome = calculateTotalIncome(seats);
		
		// Loop
		while (true) {
			int menuChoice = takeMenuChoice();
			if (menuChoice == 0)
				return;
			else if (menuChoice == 1)
				printCinema(seatingPlan);
			else if (menuChoice == 2) {
				while (true) {
					int[] choice = takeSeatChoice();
					if (!validSeatChoice(choice, seats))
						System.out.println("\nWrong input!");
					else if (!seatNotTaken(seatingPlan, choice))
						System.out.println("\nThat ticket has already been purchased!");
					else {
						int price = calculatePrice(seats, choice[0]);
						totalPaid += price;
						ticketsSold++;
						printTicketPrice(price);
						seatingPlan = updateSeatingPlan(seatingPlan, choice);
						break;
					}
				}
			}
			else if (menuChoice == 3)
				printStatistics(ticketsSold, totalTickets, totalPaid, totalIncome);
		}
	}

	public static int calculatePrice(int[] seats, int rowChoice) {
		int totalSeats = seats[0] * seats[1];
		if (totalSeats <= 60)
			return 10;

		int frontRows = seats[0] / 2;
		int backRows  = seats[0] - frontRows;
		if (rowChoice <= frontRows)
			return 10;
		return 8;
	}

	public static int calculateTotalIncome(int[] seats) {
		int totalSeats = seats[0] * seats[1];
		if (totalSeats <= 60)
			return (seats[0] * seats[1]) * 10;

		int frontRows = seats[0] / 2;
		int backRows  = seats[0] - frontRows;
			return ((frontRows * seats[1]) * 10) + ((backRows * seats[1]) * 8);
	}

	public static int takeMenuChoice() {
		Scanner scanner = new Scanner(System.in);
		printChoices();
		int choice = scanner.nextInt();
		return choice;
	}

	public static int[] takeCinemaSetup() {
		Scanner scanner = new Scanner(System.in);
		int[] info = new int[2];
		System.out.println("Enter the number of rows:");
		info[0] = scanner.nextInt();
		System.out.println("Enter the number of seats in each row:");
		info[1] = scanner.nextInt();
		return info;
	}

	public static int[] takeSeatChoice() {
		Scanner scanner = new Scanner(System.in);
		int[] ls = new int[2];
		System.out.println("\nEnter a row number:");
		ls[0] = scanner.nextInt();
		System.out.println("Enter a seat number in that row:");
		ls[1] = scanner.nextInt();
		return ls;
	}

	public static boolean validSeatChoice(int[] seatChoice, int[] seating) {
		if ((seatChoice[0] >= 1 && seatChoice[0] <= seating[0]) &&
			(seatChoice[1] >= 1 && seatChoice[1] <= seating[1]))
			return true;
		return false;
	}

	public static boolean seatNotTaken(String[][] seats, int[] choice) {
		if (seats[choice[0] - 1][choice[1] - 1].equals("S"))
			return true;
		return false;
	}

	public static String[][] updateSeatingPlan(String[][] seats, int[] choice) {
		seats[choice[0] - 1][choice[1] - 1] = "B";
		return seats;
	}

	public static String[][] makeSeatingPlan(int[] seats) {
		String[][] matrix = new String[seats[0]][seats[1]];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				matrix[i][j] = "S";
		return matrix;
	}

	public static void printCinema(String[][] ls) {
		System.out.println("\nCinema:");
		
		// Print Top
		System.out.print(" ");
		for (int i = 1; i <= ls[0].length; i++)
			System.out.print(" " + i);
		System.out.println();

		//Print Seating
		for (int i = 0; i < ls.length; i++) {
			System.out.print(i + 1);
			for (int j = 0; j < ls[i].length; j++)
				System.out.print(" " + ls[i][j]);
			System.out.println();
		}	
	}

	public static void printTicketPrice(int ticketPrice) {
		System.out.printf("Ticket price: $%d\n", ticketPrice);
	}

	public static void printIncome(int income) {
		System.out.println("Total income:");
		System.out.printf("$%d%n", income);
	}

	public static void printChoices() {
		System.out.println("\n1. Show the seats");
		System.out.println(  "2. Buy a ticket");
		System.out.println(  "3. Statistics");
		System.out.println(  "0. Exit");
	}

	public static void printStatistics(int ticketsSold, int totalTickets, int income, int totalIncome) {
		double percentage = ((double)ticketsSold / totalTickets) * 100;
		System.out.printf("\nNumber of purchased tickets: %d\n", ticketsSold);
		System.out.printf("Percentage: %.2f%%\n", percentage);
		System.out.printf("Current income: $%d\n", income);
		System.out.printf("Total income: $%d\n", totalIncome);
	}
}