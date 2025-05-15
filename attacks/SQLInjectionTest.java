// A package in Java is basically a folder (or namespace) that helps group related classes together. It helps organize your project and avoid name conflicts.

// Hashmaps -> user try to use malicious username or password -> which 

package attacks;
import java.util.*;

public class SQLInjectionTest {
    // Terminal color codes
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE_BOLD = "\u001B[1;37m";
    public static final String RED = "\u001B[31m";

    public static void attack(){
        Scanner sc = new Scanner(System.in);
        Map<String,String> database = new HashMap<>();
        Set <String> blockedIps = new HashSet<>();

        database.put("admin", "admin123");
        database.put("user", "userpass");

        System.out.println(WHITE_BOLD + "\n==================== " + CYAN + "SQL Injection Test" + WHITE_BOLD + " ====================" + RESET);
        System.out.println(GREEN + "\u25B6 This module simulates SQL Injection attacks and defenses." + RESET);
        System.out.println(WHITE_BOLD + "===============================================================\n" + RESET);

        while(true){
            System.out.println(YELLOW + "Choose mode:" + RESET);
            System.out.println("  [1] " + RED + "\u26A0 Vulnearable login" + RESET);
            System.out.println("  [2] " + GREEN + "\uD83D\uDEE1️ Secure login" + RESET);
            System.out.println("  [3] " + CYAN + "\uD83D\uDEAA Exit" + RESET);
            System.out.print(CYAN + "Enter your choice (1-3): " + RESET);

            String choice = sc.nextLine();

            switch(choice){
                case "1":
                  vulnerableLogin(sc, database);
                  break;
                case "2":
                  secureLogin(sc, database, blockedIps);
                  break;
                case "3":
                  System.out.println(RED + "\u274C Exiting SQL Injection Simulation. Stay safe!" + RESET);
                  return;
                default:
                  System.out.println(RED + "\u26A0 Invalid choice. Try again." + RESET);
            }
            System.out.println(WHITE_BOLD + "---------------------------------------------------------------" + RESET);
        }
    }

    public static void vulnerableLogin(Scanner sc, Map<String, String> database){
        System.out.println(RED + "\n[!] Vulnearable Login Mode (No Injection Protection)" + RESET);

        System.out.print(CYAN + "Enter IP address: " + RESET);
        String ip = sc.nextLine();

        System.out.print(CYAN + "Enter username: " + RESET);
        String username = sc.nextLine();

        System.out.print(CYAN + "Enter password: " + RESET);
        String password = sc.nextLine();

        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        System.out.println(YELLOW + "\u25B6 Executing query: " + query + RESET);

        if (database.containsKey(username) && database.get(username).equals(password)){
            System.out.println(GREEN + "\u2714 Login successful!" + RESET);
        } else if (isInjection(username) || isInjection(password)){
            System.out.println(YELLOW + "\u26A0 Login granted (bypass authentication) - SQL Injection detected!" + RESET);
        } else {
            System.out.println(RED + "\u274C Invalid credentials. Login failed." + RESET);
        }
    }

    public static void secureLogin(Scanner sc, Map<String, String>database, Set<String>blockedIps){
        System.out.println(GREEN + "\n[+] Secure Login Mode (Injection Protected)" + RESET);

        System.out.print(CYAN + "Enter IP address: " + RESET);
        String ip = sc.nextLine();

        if (blockedIps.contains(ip)){
            System.out.println(RED + "\u274C Access denied! This IP has been blocked due to previous SQL injection attempt." + RESET);
            return;
        }

        System.out.print(CYAN + "Enter username: " + RESET);
        String username = sc.nextLine();

        System.out.print(CYAN + "Enter password: " + RESET);
        String password = sc.nextLine();

        if (isInjection(username) || isInjection(password)){
            System.out.println(RED + "\u26A0 SQL Injection attempt detected! Blocking IP." + RESET);
            blockedIps.add(ip); // add the ip to blockedIps
            return;
        }

        if (database.containsKey(username) && database.get(username).equals(password)){
            System.out.println(GREEN + "\u2714 Login successful" + RESET);
        } else{
            System.out.println(RED + "\u274C Invalid credentials." + RESET);
        }
    }

    public static boolean isInjection(String input){
        String lower = input.toLowerCase();
        return lower.contains("or") || lower.contains("--") || lower.contains("and") || lower.contains(";")|| lower.contains("1=1");
    }
}