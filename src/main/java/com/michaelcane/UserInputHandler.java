package com.michaelcane;

import java.util.Scanner;

public class UserInputHandler {

    public static Scanner input = new Scanner(System.in);

    public static String promptUserForString(String msg) {
        promptUser(msg);
        input.next();
        return input.nextLine();
    }

    public static int promptUserForInt(String msg) {
        promptUser(msg);
        return input.nextInt();
    }

    public static double promptUserForDouble(String msg) {
        promptUser(msg);
        return input.nextDouble();
    }

    public static void promptUser(String msg) {
        System.out.println(msg);
    }

    public static void theBrandLogo() {
        System.out.println(" /$$$$$$ /$$$$$$$  /$$      /$$  /$$$$$$ \n" +
                "|_  $$_/| $$__  $$| $$$    /$$$ /$$__  $$\n" +
                "  | $$  | $$  \\ $$| $$$$  /$$$$| $$  \\__/\n" +
                "  | $$  | $$$$$$$ | $$ $$/$$ $$| $$      \n" +
                "  | $$  | $$__  $$| $$  $$$| $$| $$      \n" +
                "  | $$  | $$  \\ $$| $$\\  $ | $$| $$    $$\n" +
                " /$$$$$$| $$$$$$$/| $$ \\/  | $$|  $$$$$$/\n" +
                "|______/|_______/ |__/     |__/ \\______/ ");
    }
}
