import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Game {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int menuOption = 0;
        do {
            showMenu();
            if (scanner.hasNextInt()) {
                menuOption = scanner.nextInt();
                if (menuOption >= 1 && menuOption <= 4) {
                    switch (menuOption) {
                        case 1:
                            numberOfUser();
                            break;
                        case 2:
                            PlayWithAFriend();
                            break;
                        case 3:
                            System.out.println();
                            ReadFromFile();
                            System.out.println();
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Sorry,please enter valid option");
                            break;
                    }
                } else
                    System.out.println("Invalid number.Try again.");
            } else {
                scanner.next();
                System.out.println("Invalid number.Try again.");
            }

        }
        while (menuOption != 4);
    }

    public static void showMenu() {
        System.out.println("--------WELCOME TO THE GAME BILLS AND COWS----------");
        System.out.println("Menu: ");
        System.out.println("Enter an option: ");
        System.out.println("1.Single Player");
        System.out.println("2.Two Players");
        System.out.println("3.Instructions of the game");
        System.out.println("4.Exit.");
    }

    public static void numberOfUser() {
        int secretNumber = generateNum();
        System.out.println(secretNumber);
        int units, tens, hundreds, thousands;
        int unitsSecret, tensSecret, hundredsSecret, thousandsSecret;
        unitsSecret = secretNumber % 10;
        tensSecret = (secretNumber % 100) / 10;
        hundredsSecret = (secretNumber % 1000) / 100;
        thousandsSecret = secretNumber / 1000;
        int enteredNumber;
        int bulls = 0;
        int cows = 0;
        while (true) {
            System.out.println("Enter numbers: ");
            enteredNumber = isValidNumber();
            units = enteredNumber % 10;
            tens = (enteredNumber % 100) / 10;
            hundreds = (enteredNumber % 1000) / 100;
            thousands = enteredNumber / 1000;
            bulls = bulls(units, tens, hundreds, thousands, unitsSecret, tensSecret, hundredsSecret, thousandsSecret);
            cows = cows(units, tens, hundreds, thousands, unitsSecret, tensSecret, hundredsSecret, thousandsSecret);
            if (secretNumber == enteredNumber)
                break;
            System.out.println("Bills->" + bulls + " Cows->" + cows);
        }

        System.out.println("Congratulations!You guess the number!");
    }

    public static int generateNum() {
        Random random = new Random();
        int number;
        int units, tens, hundreds, thousands;
        int secretNumber = 0;
        boolean flag = false;
        while (!flag) {
            number = random.nextInt(8999) + 1000;//za da moje da generira cifra mejdu tozi interval
            secretNumber = number;
            units = number % 10;
            tens = (number % 100) / 10;
            hundreds = (number % 1000) / 100;
            thousands = number / 1000;
            if ((secretNumber < 10000 && secretNumber > 999) && units != tens && units != hundreds && units != thousands && tens != hundreds && tens != thousands && hundreds != thousands) {
                flag = true;
            }
        }
        return secretNumber;
    }

    public static int Score(int count) {
        int formula = 100 - (count * 2);
        return formula;
    }

    public static int isValidNumber() {
        int num;
        while (true) {
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num < 1023 || num > 9876)
                    System.out.println("Wrong number.Try again");
                else {
                    int units = num % 10;
                    int tens = (num % 100) / 10;
                    int hundreds = (num % 1000) / 100;
                    int thousands = num / 1000;
                    if ((units != tens) && (units != hundreds) && (units != thousands) && (tens != hundreds) && (tens != thousands) && (hundreds != thousands))
                        return num;
                    else
                        System.out.println("Wrong number.Try again.Each digit must be unique.");
                }
            } else {
                scanner.next();
                System.out.println("It is not a number.Try again.");
            }
        }
    }

    public static int bulls(int units, int tens, int hundreds, int thousands, int unitsSecret, int tensSecret, int hundredsSecret, int thousandsSecret) {
        int bulls = 0;
        if (units == unitsSecret)
            bulls++;
        if (tens == tensSecret)
            bulls++;
        if (hundreds == hundredsSecret)
            bulls++;
        if (thousands == thousandsSecret)
            bulls++;
        return bulls;
    }

    public static int cows(int units, int tens, int hundreds, int thousands, int unitsSecret, int tensSecret, int hundredsSecret, int thousandsSecret) {
        int cows = 0;
        if (units == tensSecret || units == hundredsSecret || units == thousandsSecret)
            cows++;
        if (tens == unitsSecret || tens == hundredsSecret || tens == thousandsSecret)
            cows++;
        if (hundreds == unitsSecret || hundreds == tensSecret || hundreds == thousandsSecret)
            cows++;
        if (thousands == unitsSecret || thousands == tensSecret || thousands == hundredsSecret)
            cows++;
        return cows;
    }

    public static void PlayWithAFriend() {
        int number = 0;
        int units1, tens1, hundreds1, thousands1;
        boolean flag = false;
        while (!flag) {
            System.out.println("Enter a 4-digit number");
            number = scanner.nextInt();
            units1 = number % 10;
            tens1 = (number % 100) / 10;
            hundreds1 = (number % 1000) / 100;
            thousands1 = number / 1000;
            if ((number < 10000 && number > 999) && (units1 != tens1) && (units1 != hundreds1) && (units1 != thousands1) && (tens1 != hundreds1) &&
                    (tens1 != thousands1) && (hundreds1 != thousands1)) {
                flag = true;
            }
        }
        int secretNumber = number;
        int units, tens, hundreds, thousands;
        int unitsSecret, tensSecret, hundredsSecret, thousandsSecret;
        unitsSecret = secretNumber % 10;
        tensSecret = (secretNumber % 100) / 10;
        hundredsSecret = (secretNumber % 1000) / 100;
        thousandsSecret = secretNumber / 1000;
        int enteredNumber;
        int bulls = 0;
        int cows = 0;
        int br = 0;
        for (int i = 0; i < 15; i++) {
            System.out.println();
        }
        while (true) {
            System.out.println("Try to guess your friend's number: ");
            enteredNumber = isValidNumber();
            units = enteredNumber % 10;
            tens = (enteredNumber % 100) / 10;
            hundreds = (enteredNumber % 1000) / 100;
            thousands = enteredNumber / 1000;
            bulls = bulls(units, tens, hundreds, thousands, unitsSecret, tensSecret, hundredsSecret, thousandsSecret);
            cows = cows(units, tens, hundreds, thousands, unitsSecret, tensSecret, hundredsSecret, thousandsSecret);
            if (secretNumber == enteredNumber)
                break;
            System.out.println("Bills->" + bulls + " Cows->" + cows);
            br++;
        }
        System.out.println("The score is: " + Score(br));
        System.out.println("Congratulations!You guess the number!");

    }

    public static void ReadFromFile() {
        String FILENAME = "C:\\Users\\Iweto\\IdeaProjects\\Project1BullsAndCows\\src\\BullsAndCows.txt";
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }
}

