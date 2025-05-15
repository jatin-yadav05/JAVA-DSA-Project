package attacks;
import java.util.*;
public class BruteForceAttack {
    
    public static void attack(){
        Scanner sc = new Scanner(System.in);
        String targetUserName = "admin";
        String targetPassword = "pass12345";
        int maxAttempts = 5;

        System.out.println("💣 Brute Force Simulation Started...");
        System.out.println("Target User : " + targetUserName);
       
        System.out.println("You have " + maxAttempts + " attempts to guess the password.");

        for (int i = 1; i <= maxAttempts; i++){
               System.out.println("Attempt " + i + " - Enter password guess: ");
               String guess = sc.nextLine().trim();

               if (guess.equals(targetPassword)){
                 System.out.println("[✔] Access Granted! You cracked the password: " + targetPassword);
                 return;
               } else{
                System.out.println("[✘] Incorrect password.");
               }
        }

        System.out.println("All attempts used. Password not cracked. Simulation over.");

    }
}

/*
  if we have two strings -> how do I compare them -> String object -> String str1 = new String("")

  if we write directly -> str1 == str2  -> it compares the memory addresses of objects not their content
  so the correct way to compare strings -> str1.equals(str2) -> will compare the actual content of the strings
 */