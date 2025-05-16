package attacks;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ManInTheMiddleAttack {
    private static final String[] COMMON_PACKETS = {
        "GET /login HTTP/1.1",
        "POST /api/data HTTP/1.1",
        "GET /user/profile HTTP/1.1",
        "POST /payment/process HTTP/1.1",
        "GET /api/session HTTP/1.1"
    };

    public static void attack() {
        Scanner sc = new Scanner(System.in);
        System.out.println("🔍 Man-in-the-Middle Attack Simulation Started...");
        
        // Simulate network setup
        System.out.println("\n[1] Setting up network interface...");
        simulateLoading("Initializing packet capture");
        
        System.out.println("\n[2] Starting packet interception...");
        System.out.println("Intercepted packets:");
        
        // Simulate packet interception
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            String packet = COMMON_PACKETS[rand.nextInt(COMMON_PACKETS.length)];
            System.out.println("📦 Packet " + (i + 1) + ": " + packet);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Simulate session hijacking
        System.out.println("\n[3] Attempting session hijacking...");
        simulateLoading("Analyzing session tokens");
        
        System.out.println("\n[4] Select attack type:");
        System.out.println("1. Packet Sniffing");
        System.out.println("2. Session Hijacking");
        System.out.println("3. SSL Stripping");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = sc.nextInt();
        sc.nextLine(); // consume newline
        
        switch (choice) {
            case 1:
                performPacketSniffing();
                break;
            case 2:
                performSessionHijacking();
                break;
            case 3:
                performSSLStripping();
                break;
            default:
                System.out.println("Invalid choice. Defaulting to packet sniffing.");
                performPacketSniffing();
        }
    }

    private static void performPacketSniffing() {
        System.out.println("\n🔍 Performing packet sniffing...");
        simulateLoading("Capturing network traffic");
        System.out.println("Found sensitive data:");
        System.out.println("- Username: user123");
        System.out.println("- Session ID: SESS_123456789");
        System.out.println("- API Key: API_987654321");
    }

    private static void performSessionHijacking() {
        System.out.println("\n🔑 Attempting session hijacking...");
        simulateLoading("Analyzing session cookies");
        System.out.println("Session token intercepted: SESS_123456789");
        System.out.println("Attempting to impersonate user...");
        System.out.println("Session hijacking successful!");
    }

    private static void performSSLStripping() {
        System.out.println("\n🔒 Attempting SSL stripping...");
        simulateLoading("Analyzing HTTPS connections");
        System.out.println("Intercepted HTTPS request");
        System.out.println("Downgrading to HTTP...");
        System.out.println("SSL stripping successful!");
    }

    private static void simulateLoading(String message) {
        String[] dots = {"   ", ".  ", ".. ", "..."};
        System.out.print(message);
        try {
            for (int i = 0; i < 4; i++) {
                System.out.print("\r" + message + dots[i % dots.length]);
                Thread.sleep(250);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }
}