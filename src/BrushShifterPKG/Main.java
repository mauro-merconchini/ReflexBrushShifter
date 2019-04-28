package BrushShifterPKG;

import java.util.Scanner;

/**
 * A command line application to shift all brushes in a Reflex Arena .map file by a specified amount in each axis.
 * Mauro Merconchini
 * April 28, 2019
 */
public class Main
{

    public static void main(String[] args)
    {
        //argument 0: Map file name
        //argument 1: X shift
        //argument 2: Y shift
        //argument 3: Z shift

        //checkInputValidity();

        //********CODE BELOW CHECKS FOR INPUT VALIDITY********

        //First of all, check that they have the right amount of inputs
        if (args.length > 4 || args.length < 4)
        {
            System.out.println("You have an incorrect number of arguments. The input format is as follows:\n" +
                    "ARGUMENT 0 (String): The name of a map file if present in directory, otherwise provide full path\n" +
                    "ARGUMENT 1 (Integer): The X shift amount\n" +
                    "ARGUMENT 2 (Integer): The Y shift amount\n" +
                    "ARGUMENT 3 (Integer): The Z shift amount\n");
        }

        //Once we confirm that the user has the right amount of inputs, check them individually for validity
        else
        {
            //Create a scanner to check the individual inputs
            Scanner inputScanner;

            //This for loop will check each input
            for (int i = 0; i < args.length; i++) {
                //This string will hold the current argument
                String currentArg = args[i];
                //Set the scanner to scan the current argument
                inputScanner = new Scanner(currentArg);

                //If this is the first argument (argument 0), and it has any value
                if (i == 0 && inputScanner.hasNext()) {
                    //Throw an error if the input does not have the .map file type
                    if (!inputScanner.next().contains(".map")) {
                        System.out.println("ARG 0 Error: This is not a .map file");
                    }

                    //Else, it must be good
                    else {
                        System.out.println("ARG 0 is good");
                    }
                }

                //If this is the second argument (argument 1)
                else if (i == 1) {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt()) {
                        System.out.println("ARG 1 is good");
                    } else {
                        System.out.println("ARG 1 Error: This is not an integer");
                    }
                }

                //If this is the third argument (argument 2)
                else if (i == 2) {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt()) {
                        System.out.println("ARG 2 is good");
                    } else {
                        System.out.println("ARG 2 Error: This is not an integer");
                    }
                }

                //If this is the fourth argument (argument 3)
                else if (i == 3) {
                    //If the argument is an integer, it must be good
                    if (inputScanner.hasNextInt()) {
                        System.out.println("ARG 3 is good");
                    } else {
                        System.out.println("ARG 3 Error: This is not an integer");
                    }
                }
            }
        }
    }
}