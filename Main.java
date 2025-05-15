import java.util.*;
import java.util.concurrent.TimeUnit;

import attacks.BruteForceAttack;
import attacks.SQLInjectionTest;
import defense.PreventionTechniques;
import defense.Firewall;
import attacks.IPBlocklistAttack;
import defense.RateLimiter;

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
        "    [2] Perform SQL Injection Test\n" +
        "    [3] Learn Prevention techniques\n" + 
        "    [4] Configure Firewall\n" +
        "    [5] Exit\n" +
        "    [6] Simulate IP Blocklist Attack\n" +
        "    [7] Configure Rate Limiter\n" +
        WHITE_BOLD + "===============================================================\n" + RESET);
    }
     
    public static void handleMainMenuSelection(){
        Scanner sc = new Scanner(System.in);
        System.out.print(CYAN + "Enter your choice (1-7): " + RESET);
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
              loadingAnimation("Performing SQL Injection Test");
              SQLInjectionTest.attack();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 3:
              loadingAnimation("Loading Prevention Techniques");
              PreventionTechniques.display();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 4:
              loadingAnimation("Configuring Firewall");
              configureFirewall();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 5:
              loadingAnimation("Shutting down");
              System.out.println(RED + "Exiting.. Stay safe hacker!!" + RESET);
              break;
            case 6:
              loadingAnimation("Simulating IP Blocklist Attack");
              System.out.print(CYAN + "Enter first input (sorted or random) for IP generation: " + RESET);
              String ipChoice1 = sc.nextLine().trim().toLowerCase();
              IPBlocklistAttack.main(new String[]{ipChoice1});
              System.out.print(CYAN + "Enter second input (sorted or random) for IP generation: " + RESET);
              String ipChoice2 = sc.nextLine().trim().toLowerCase();
              IPBlocklistAttack.main(new String[]{ipChoice2});
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            case 7:
              loadingAnimation("Configuring Rate Limiter");
              configureRateLimiter();
              System.out.println(YELLOW + witty[rand.nextInt(witty.length)] + RESET);
              break;
            default:
              System.out.println(RED + "Invalid selection. Please choose a valid option." + RESET);
        }
    }

    public static void configureFirewall() {
        Firewall firewall = new Firewall();
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            System.out.println(WHITE_BOLD + "\n==================== Firewall Configuration ====================\n" +
                "    [1] Enable Firewall\n" +
                "    [2] Disable Firewall\n" +
                "    [3] Add Allowed IP\n" +
                "    [4] Add Blocked IP\n" +
                "    [5] Display Firewall Status\n" +
                "    [6] Test Connection\n" +
                "    [7] Return to Main Menu\n" +
                "===============================================================\n" + RESET);
            
            System.out.print(CYAN + "Enter your choice (1-7): " + RESET);
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            
            switch(choice) {
                case 1:
                    firewall.enable();
                    System.out.println(GREEN + "Firewall enabled successfully!" + RESET);
                    break;
                case 2:
                    firewall.disable();
                    System.out.println(RED + "Firewall disabled successfully!" + RESET);
                    break;
                case 3:
                    System.out.print(CYAN + "Enter IP to allow: " + RESET);
                    String allowedIP = sc.nextLine();
                    firewall.addAllowedIP(allowedIP);
                    System.out.println(GREEN + "IP " + allowedIP + " added to allowed list!" + RESET);
                    break;
                case 4:
                    System.out.print(CYAN + "Enter IP to block: " + RESET);
                    String blockedIP = sc.nextLine();
                    firewall.addBlockedIP(blockedIP);
                    System.out.println(RED + "IP " + blockedIP + " added to blocked list!" + RESET);
                    break;
                case 5:
                    System.out.println(GREEN + "Firewall Status: " + (firewall.isEnabled() ? "Enabled" : "Disabled") + RESET);
                    System.out.println("Allowed IPs: " + Arrays.toString(firewall.getAllowedIPs()));
                    System.out.println("Blocked IPs: " + Arrays.toString(firewall.getBlockedIPs()));
                    break;
                case 6:
                    System.out.print(CYAN + "Enter IP to test connection: " + RESET);
                    String testIP = sc.nextLine();
                    if (firewall.isIPAllowed(testIP)) {
                        System.out.println(GREEN + "Connection allowed for IP: " + testIP + RESET);
                    } else if (firewall.isIPBlocked(testIP)) {
                        System.out.println(RED + "Connection blocked for IP: " + testIP + RESET);
                    } else {
                        System.out.println(YELLOW + "No specific rules for IP: " + testIP + ". Connection allowed." + RESET);
                    }
                    break;
                case 7:
                    System.out.println(GREEN + "Returning to Main Menu..." + RESET);
                    return;
                default:
                    System.out.println(RED + "Invalid selection. Please choose a valid option." + RESET);
            }
            
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
    }

    public static void configureRateLimiter() {
        Scanner sc = new Scanner(System.in);
        System.out.print(CYAN + "Enter max requests per time window: " + RESET);
        int maxRequests = sc.nextInt();
        System.out.print(CYAN + "Enter time window (in seconds): " + RESET);
        long timeWindow = sc.nextLong();
        sc.nextLine(); // consume newline
        RateLimiter rateLimiter = new RateLimiter(maxRequests, timeWindow, TimeUnit.SECONDS);
        String clientId = "testClient";
        while(true) {
            System.out.println(WHITE_BOLD + "\n==================== Rate Limiter Configuration ====================\n" +
                "    [1] Display Current Rate Limiter Settings\n" +
                "    [2] Test Rate Limiting\n" +
                "    [3] Reset Rate Limiter for Client\n" +
                "    [4] Return to Main Menu\n" +
                "===============================================================\n" + RESET);
            System.out.print(CYAN + "Enter your choice (1-4): " + RESET);
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch(choice) {
                case 1:
                    int remaining = rateLimiter.getRemainingRequests(clientId);
                    long untilReset = rateLimiter.getTimeUntilReset(clientId) / 1000;
                    System.out.println(GREEN + "Max Requests: " + maxRequests + ", Time Window: " + timeWindow + " seconds" + RESET);
                    System.out.println(GREEN + "Remaining Requests for '" + clientId + "': " + remaining + RESET);
                    System.out.println(GREEN + "Time until reset: " + untilReset + " seconds" + RESET);
                    break;
                case 2:
                    System.out.print(CYAN + "Enter number of requests to simulate: " + RESET);
                    int requests = sc.nextInt();
                    for (int i = 0; i < requests; i++) {
                        if (rateLimiter.allowRequest(clientId)) {
                            System.out.println(GREEN + "Request " + (i+1) + " allowed" + RESET);
                        } else {
                            System.out.println(RED + "Request " + (i+1) + " blocked (rate limit exceeded)" + RESET);
                        }
                    }
                    break;
                case 3:
                    rateLimiter.resetLimit(clientId);
                    System.out.println(GREEN + "Rate limiter reset for client '" + clientId + "'!" + RESET);
                    break;
                case 4:
                    System.out.println(GREEN + "Returning to Main Menu..." + RESET);
                    return;
                default:
                    System.out.println(RED + "Invalid selection. Please choose a valid option." + RESET);
            }
            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
    }
}