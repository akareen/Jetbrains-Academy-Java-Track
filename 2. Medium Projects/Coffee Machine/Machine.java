import java.util.Scanner;

public class Machine {
    int water, milk, coffee, money, cups;
    int[] recipe = {200, 50, 15};
    int[][] coffeeRecipes = {{250, 0, 16, 4}, {350, 75, 20, 7}, {200, 100, 12, 6}};

    public Machine() {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.money = 550;
        this.cups = 9;
    }

    public void action() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String line = scanner.nextLine();
            if (line.equals("buy"))
                buy();
            if (line.equals("fill"))
                fill();
            if (line.equals("take"))
                take();
            if (line.equals("remaining"))
                printState();
            if (line.equals("exit"))
                break;
        }
        scanner.close();

    }

    private void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String line = scanner.nextLine();
        if (line.equals("back")) {
            System.out.println();
            scanner.close();
            return;
        }

        int choice = Integer.parseInt(line) - 1;
        if (cupsAvailable()) {
            System.out.println("I have enough resources, making you a coffee!");
            this.water  -= coffeeRecipes[choice][0];
            this.milk   -= coffeeRecipes[choice][1];
            this.coffee -= coffeeRecipes[choice][2];
            this.money  += coffeeRecipes[choice][3];
            this.cups--;
        }
        System.out.println();
        scanner.close();
    }

    private void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add: ");
        this.water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        this.milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        this.coffee += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        this.cups += scanner.nextInt();
        System.out.println();
        scanner.close();
    }

    private void take() {
        System.out.printf("I gave you $%d%n", this.money);
        this.money = 0;
        System.out.println();
    }

    private boolean cupsAvailable() {
        if ((this.water / this.recipe[0]) < 1)
            System.out.println("Sorry, not enough water!");
        else if ((this.milk / this.recipe[1]) < 1)
            System.out.println("Sorry, not enough milk!");
        else if ((this.coffee / this.recipe[2]) < 1)
            System.out.println("Sorry, not enough coffee!");
        else if ((this.cups <= 0))
            System.out.println("Sorry, not enough cups!");
        else
            return true;
        return false;


    }

    public void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", this.water);
        System.out.printf("%d ml of milk\n", this.milk);
        System.out.printf("%d g of coffee beans\n", this.coffee);
        System.out.printf("%d disposable cups\n", this.cups);
        System.out.printf("$%d of money\n\n", this.money);
    }

}