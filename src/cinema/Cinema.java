package cinema;
import java.util.Scanner;
public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();
        String[] seats = new String[numSeats + 1];
        String[] rows = new String[numRows + 1];
        String[][] cinema = new String[rows.length][seats.length];
        seats[0] = " ";
        rows[0] = " ";
        for (int i = 1; i < seats.length; i++) {
            seats[i] = String.format("%d", i);
        }
        for (int i = 1; i < rows.length; i++) {
            rows[i] = String.format("%d", i);
        }
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                if (i == 0) {
                    cinema[i][j] = seats[j];
                } else if (j == 0) {
                    cinema[i][j] = rows[i];
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
        String option1 = "1. Show the seats" ;
        String option2 = "2. Buy a ticket";
        String option3 = "3. Statistics";
        String option0 = "0. Exit";
        String str = String.format("%s%n%s%n%s%n%s", option1, option2, option3, option0 );
        int counter = 0;
        int currIncome = 0;
        System.out.println(str);

        while (scanner.hasNext()) {
            String optionChoose = scanner.next();
            if ("0".equals(optionChoose)) {
                break;
            } else {
                switch (optionChoose) {
                    case "1":
                        System.out.println("Cinema:");
                        displayCinema(cinema);
                        System.out.println(str);
                        break;
                    case "2":
                        System.out.println("Enter a row number:");
                        int ticketRow = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int ticketSeat = scanner.nextInt();
                        while (!checkSeat(ticketSeat, ticketRow, cinema)) {
                            if (checkCinema(ticketSeat, ticketRow, cinema)) {
                                System.out.println("Wrong input!");
                                System.out.println("Enter a row number:");
                                int ticketRow1 = scanner.nextInt();
                                System.out.println("Enter a seat number in that row:");
                                int ticketSeat1 = scanner.nextInt();
                                ticketRow = ticketRow1;
                                ticketSeat = ticketSeat1;
                            } else if ("B".equals(cinema[ticketRow][ticketSeat])) {
                                System.out.println("That ticket has already been purchased!");
                                System.out.println("Enter a row number:");
                                int ticketRow1 = scanner.nextInt();
                                System.out.println("Enter a seat number in that row:");
                                int ticketSeat1 = scanner.nextInt();
                                ticketRow = ticketRow1;
                                ticketSeat = ticketSeat1;
                            }
                        }
                            System.out.println("Ticket price: $" + ticketBuy(ticketRow, cinema));
                            cinema[ticketRow][ticketSeat] = "B";
                            counter++;
                            currIncome += ticketBuy(ticketRow, cinema);
                            System.out.println(str);
                        break;
                    case "3":
                        String purchasedTickets = String.format("Number of purchased tickets: %d" ,counter);
                        String percentage = String.format("Percentage: %.2f%c", (double)(counter * 100) / (numRows * numSeats), '%');
                        String currentIncome = String.format("Current income: $%d", currIncome);
                        String totIncome = String.format("Total income: $%d", totalIncome(numSeats, numRows));
                        String statistics = String.format("%s%n%s%n%s%n%s", purchasedTickets, percentage, currentIncome, totIncome);
                        System.out.println(statistics);
                        System.out.println(str);
                        break;
                }
            }

        }
    }
    public static void displayCinema(String[][] arr) {
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
    public static boolean checkCinema (int seat, int row, String[][] arr) {
        boolean x = true;
        if (row < 1 || row > arr.length - 1) {
            x = false;
        }
        if (seat < 1 || seat > arr[0].length - 1) {
            x = false;
        }
        return !x;
    }
    public static int ticketBuy(int row, String[][] arr) {
        int ticketPrice;
        if ((arr.length - 1) * (arr[0].length - 1) > 60) {
            ticketPrice = row > (arr.length - 1) / 2? 8 : 10;
        } else {
            ticketPrice = 10;
        }
        return ticketPrice;

    }
    public static int totalIncome (int seats, int rows) {
        int totalSum;
        totalSum = seats * rows > 60? (rows / 2) * seats * 10 + (rows / 2 + rows % 2) * seats * 8 : rows * seats * 10;
        return totalSum;
    }
    public static boolean checkSeat(int seat, int row, String[][] arr) {
        boolean check = true;
        if (checkCinema(seat, row, arr)) {
            check = false;
        } else if ("B".equals(arr[row][seat])) {
            check = false;
        }
        return check;
    }
}