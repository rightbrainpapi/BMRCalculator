/*
 * @author Darnell Simon
 *
 * DESCRIPTION:
 * This Java program calculates a person's Basmal Metabolic Rate (BMR)
 *
 * Using the following Basmal Metabolic Rate (BMR) method:
 * For Men: BMR=(10*weight in kg)+(6.25*height in cm)-(5 * age in years) + 5
 * For Women: BMR=(10*weight in kg)+(6.25*height in cm)-(5*age in years) + 161
 * 
 * and their Total Energy Expenditure (TEE)
 * Depending on their level of activity level on a scale from (1 - 3)
 * Using the following TEE method:
 *
 * avtivity level 1: BMR * 1.53
 * avtivity level 2: BMR * 1.76
 * avtivity level 3: BMR * 2.25
 *
 * SCIENCE of Basal Metabolic Rate
 * Your BMR (Basal Metabolic Rate) is an estimate of how many calories your
 * body burns at rest. It represents the minimum amount of energy needed to keep 
 * your body functioning, including breathing and keeping your heart beating. 
 * Your BMR uses up about two-thirds of your daily calories. Your caloric intake
 * to lose, maintain, or gain weight will be based on your BMR, but will not be 
 * the same figure.
 * 
 *
 * SCIENCE of Total Energy Expenditure
 * Total  energy expenditure varies from person to person, depending on 
 * body size, sex, body composition, genetics, and activity level. The total energy 
 * expenditure for a small, sedentary person who is a female, for example, might
 * be 1800 calories or less per day. The TEE for a larger person who is an 
 * active male, on the other hand, can easily be over 2000 calories. Because 
 * the man in this example has more muscle mass, a higher daily activity level, 
 * and is a larger person, his TEE is greater.
 *
 *
 * CHALLENGES
 * A few of my bigger challenges were:
 *
 *  1: Contructing a while loop to check for errors
 *  2: Constructing a while loop to allow continual imput entry until X
 *  3: Passing the PrintStream Ps variable through the params
 *
 *
 * SUCCESSES
 * In working through this program I was able to learn how to create reusable 
 * functions. I also learned how to pass a packages from one method
 * to another through the parameters.
 */
package bmr.calculator;

import java.io.PrintStream;
import java.util.Scanner;

public class BMRCalculator {

    public static void main(String[] args) throws Exception {
        promptUser();

    }

    //THIS METHOD PROMPTS THE USER FOR THE REQUIRED INFO
    public static void promptUser() throws Exception {
        // Print the Instructions for next incoming data
        char gender;
        int age, feet, inches, weight, activityLevel;
        double heightInCm, weightInKg, BMR, TEE;

        //////////////////////////////////////
        // Prompts user and waits for input //
        //////////////////////////////////////
        Scanner input = new Scanner(System.in);
        PrintStream ps = new PrintStream("bmrOutput.txt");

        System.out.println("\nBMR/TEE Calculator");
        System.out.println("Enter X at any point to stop");

        System.out.println("\nEnter your gender:");
        System.out.println("For example: M for male or F for female.");
        gender = input.next().charAt(0);

        while (gender != 'X') {

            // Check Age
            System.out.println("\nEnter your age: ");
            int limit1 = 0;
            int limit2 = 135;
            age = input.nextInt();

            while ((age < limit1 || age > limit2)) {
                System.out.println("Out of Limits. Try again!");
                age = input.nextInt();
            }

            // Check Height
            System.out.println("\nEnter your height in feet and inches.");
            System.out.println("For example: Enter 5 9  for 5ft 9in.");
            int feetLimit1 = 2;
            int feetLimit2 = 9;
            int inchesLimit1 = 0;
            int inchesLimit2 = 11;
            feet = input.nextInt();
            inches = input.nextInt();

            while ((feet < feetLimit1 || feet > feetLimit2)
                    || (inches < inchesLimit1 || inches > inchesLimit2)) {
                System.out.println("You entered an invalid height. Please, try again!");
                feet = input.nextInt();
                inches = input.nextInt();
            }

            // Check weight
            System.out.println("\nEnter your weight.");
            System.out.println("For example: Enter 140 for 140lbs.");
            int weightLimit1 = 50;
            int weightLimit2 = 900;
            weight = input.nextInt();

            while (weight < weightLimit1 || weight > weightLimit2) {
                System.out.println("You entered an invalid weight. Please, try again!");
                weight = input.nextInt();
            }

            // Check Choice
            System.out.println("\nEnter the corresponding digit that represents your "
                    + "activity level.");
            System.out.println("Enter 1 if : You are not very active.");
            System.out.println("Enter 2 if : You are somewhat active.");
            System.out.println("Enter 3 if : You are very active.");
            int choiceLimit1 = 1;
            int choiceLimit2 = 3;
            activityLevel = input.nextInt();

            while (activityLevel < choiceLimit1 || activityLevel > choiceLimit2) {
                System.out.println("You entered an invalid choice. Please, try again!");
                activityLevel = input.nextInt();
            }

            // Convert Height Method 
            convertFtToIn(feet, inches);
            heightInCm = convertInToCm(feet, inches);
            // Convert Weight Method 
            convertLbToKg(weight);
            weightInKg = convertLbToKg(weight);

            BMR = calculateBMR(gender, age, heightInCm, weightInKg);
            TEE = calculateTEE(BMR, activityLevel);

            System.out.println("\nThis is the user information program!");

            // Calling display User info & Passing the PrintStream variable
            displayUserInfo(ps, gender, age, feet, inches, weight, activityLevel, BMR, TEE);

            System.out.println("\nEnter your gender:");
            System.out.println("For example: M for male or F for female.");
            gender = input.next().charAt(0);

        }
    }

    // Method to convert the height to In
    public static int convertFtToIn(int feet, int inches) {
        int heightInInches;

        heightInInches = (feet * 12) + inches;

        return heightInInches;
    }

    // Method to convert the height to cm
    public static double convertInToCm(int feet, int inches) {

        int heightInInches;
        double heightInCm;

        heightInInches = (feet * 12) + inches;

        heightInCm = (heightInInches * 2.54);

        return heightInCm;
    }

    // Method to convert the pounds to Kg
    public static double convertLbToKg(int weight) {

        double weightInKg;

        weightInKg = (weight / 2.2046);

        return weightInKg;
    }

    // Method to Calculate the BMR
    public static double calculateBMR(char gender, int age,
            double heightInCm, double weightInKg) {

        double BMR;

        if (gender == 'F') {
            BMR = (((10 * weightInKg) + (6.25 * heightInCm)) - (5 * age) - 161);
        } else {
            BMR = (((10 * weightInKg) + (6.25 * heightInCm)) - (5 * age) + 5);
        }

        return BMR;
    }

    // Method to Calculate the TEE
    public static double calculateTEE(double BMR, int activityLevel) {

        double TEE;

        switch (activityLevel) {
            case 1:
                // code block
                TEE = (BMR * 1.53);
                break;
            case 2:
                // code block
                TEE = (BMR * 1.76);
                break;
            default:
                // code block
                TEE = (BMR * 2.25);
        }

        return TEE;
    }

    // Method to display all of the users data
    public static void displayUserInfo(PrintStream ps, char gender, int age, int feet,
            int inches, int weight, int activityLevel, double BMR, double TEE) throws Exception {

        if (gender == 'F') {
            String userGender = "female";

            System.out.println("Gender: " + userGender);
            ps.print(System.lineSeparator());
            ps.println("\nGender: " + userGender);
        } else if (gender == 'M') {
            String userGender = "male";
            ps.print(System.lineSeparator());
            System.out.println("Gender: " + userGender);
            ps.println("\nGender: " + userGender);
        }

        ps.println("Age: " + age);
        ps.println("Height: " + feet + "ft, " + inches + "in");
        ps.println("Weight: " + weight);
        ps.println("Activity Level: " + activityLevel);
        ps.print(System.lineSeparator());
        ps.printf("Your BMR is %.6g calories/day.\n",
                BMR);
        ps.printf("Your TEE is %.6g calories/day.\n",
                TEE);

        System.out.println("Age: " + age);
        System.out.println("Height: " + feet + "ft, " + inches + "in");
        System.out.println("Weight: " + weight);
        System.out.println("Activity Level: " + activityLevel);
        System.out.print(System.lineSeparator());
        System.out.printf("Your BMR is %.6g calories/day.\n",
                BMR);
        System.out.printf("Your TEE is %.6g calories/day.\n",
                TEE);
    }
}
