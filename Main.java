import java.util.*;
import java.util.concurrent.TimeUnit;

import attacks.BruteForceAttack;
import attacks.DdosAttack;
import attacks.SQLInjectionTest;
import defense.PreventionTechniques;

public class Main {
    
    // final keyword -> makes sure once the value is assigned, it cannot be changed.
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE_BOLD = "\u001B[1;37m";
    public static final String RED = "\u001B[31m";

    public static void main(String[]args){
        displayWelcomeScreen();
        printRandomTip();
        String alias = generateHackerAlias();
        System.out.println(YELLOW + "\n[+] Your hacker alias for this session: " + CYAN + alias + RESET);
        runCommandInterface();
    }

    public static void displayWelcomeScreen(){
        System.out.println(WHITE_BOLD +
        "\n" +
        CYAN +
        "   ╔══════════════════════════════════════════════════════════╗\n" +
        "   ║             CYBERSECURITY ATTACK SIMULATION              ║\n" +
        "   ╚══════════════════════════════════════════════════════════╝\n" +
        RESET +
        "\n" +
        YELLOW + "CYBERSECURITY ATTACK SIMULATION" + RESET + "\n" +
        "\n" +
        WHITE_BOLD + "===============================================================\n\n" +
        RESET +
        GREEN + "  Simulate real-world cybersecurity attacks\n" +
        "  Understand how systems respond under threat\n" +
        "  Test and train your defensive strategies\n" +
        "  Built with Java & DSA Algorithms\n" + RESET +
        YELLOW +
        "\n>>> Type '" + CYAN + "start" + YELLOW + "' to begin the simulation\n" +
        ">>> Type '" + CYAN + "help" + YELLOW + "' for available commands\n\n"  + RESET+
        WHITE_BOLD +
        "===============================================================\n" +
        "               Project by FastRender | Version 1.0\n" +
        "===============================================================" + RESET);
    }

    public static void printRandomTip() {
        String[] tips = {
            "Never use 'password' as your password!",
            "Phishing emails love typos. Stay sharp!",
            "Update your software, patch those holes!",
            "2FA: Because one password is never enough!",
            "Cover your webcam, you never know who's watching!",
            "Think before you click!",
            "A strong password is your best friend."
        };
        Random rand = new Random();
        System.out.println(CYAN + "\nCyber Tip: " + tips[rand.nextInt(tips.length)] + RESET);
    }

    public static String generateHackerAlias() {
        String[] prefixes = {"Shadow", "Cyber", "Phantom", "Zero", "Ghost", "Dark", "Neo", "Byte", "Root", "Hex"};
        String[] suffixes = {"Wolf", "Ninja", "Falcon", "Tiger", "Dragon", "Samurai", "Wizard", "Rider", "Hunter", "Fox"};
        Random rand = new Random();
        return prefixes[rand.nextInt(prefixes.length)] + suffixes[rand.nextInt(suffixes.length)];
    }

    public static void loadingAnimation(String message) {
        String[] dots = {"   ", ".  ", ".. ", "..."};
        System.out.print(GREEN + message);
        try {
            for (int i = 0; i < 8; i++) {
                System.out.print("\r" + GREEN + message + dots[i % dots.length] + RESET);
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }

    public static void runCommandInterface(){
        Scanner sc = new Scanner(System.in);
        String input;

        while(true){
            System.out.print(CYAN + "\nCipherX > " + RESET);
            input = sc.nextLine().trim().toLowerCase();

            switch(input){
               case "start":
                    loadingAnimation("Booting up the simulation");
                    System.out.println(GREEN + "Starting Cyber Attack Simulation... " + RESET);
                    loadingAnimation("Loading main menu");
                    System.out.println(GREEN + "Launching Main Menu... " + RESET);
                    displayMainMenu();
                    handleMainMenuSelection();
                    break;

                case "help":
                    System.out.println(YELLOW + "Available Commands: \n" +
                    CYAN + " start " + RESET + "- Begin attack simulation 🚀\n" + 
                    CYAN + " help " + RESET + "- Show this help menu 🆘\n" +
                    CYAN + " exit " + RESET + "- Exit the application 🚪" + RESET);
                    break;

                case "exit":
                  System.out.println(RED + "❌ Exiting Cybersecurity Attack Simulation. Stay secure!" + RESET);
                  return;
                
                default:
                  System.out.println(RED + "⚠️  Unknown command. Type 'help' for assistance." + RESET);
            }
        }
    }


    public static void displayMainMenu(){
        loadingAnimation("Preparing options");
        System.out.println(WHITE_BOLD + 
        "\n==================== Main Menu ====================\n" +
        "    [1] Launch Brute Force Attack \n" +
        "    [2] Simulate DDoS Attack \n" + 
        "    [3] Perform SQL Injection Test\n" +
        "    [4] View Attack Logs\n" + 
        "    [5] Learn Prevention techniques\n" + 
        "    [6] Exit\n" +
        WHITE_BOLD + "===============================================================\n" + RESET);
    }
     
    public static void handleMainMenuSelection(){
        Scanner sc = new Scanner(System.in);
        System.out.print(CYAN + "Enter your choice (1-6): " + RESET);
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline

        String[] witty = {
            "Hack the planet!",
            "That was slick!",
            "You'd make a great movie hacker!",
            "System breached... just kidding!",
            "Access granted... to more fun!",
            "01101000 01101001! (That's 'hi' in binary)"
        };
        Random rand = new Random();

        switch(choice){
            case 1: 
              loadingAnimation("Launching Brute Force Attack");
              BruteForceAttack.attack();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 2:
              loadingAnimation("Simulating DDoS Attack");
              DdosAttack.attack();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 3:
              loadingAnimation("Performing SQL Injection Test");
              SQLInjectionTest.attack();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 5:
              loadingAnimation("Loading Prevention Techniques");
              PreventionTechniques.display();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 6:
              loadingAnimation("Shutting down");
              System.out.println(RED + "Exiting.. Stay safe hacker!!" + RESET);
              break;
            default:
              System.out.println(RED + "Invalid selection. Please choose a valid option." + RESET);
        }
    }

}

