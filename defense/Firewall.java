package defense;

public class Firewall {
    private boolean isEnabled;
    private String[] allowedIPs;
    private String[] blockedIPs;
    private int maxConnections;
    private boolean loggingEnabled;
    
    public Firewall() {
        this.isEnabled = true;
        this.allowedIPs = new String[0];
        this.blockedIPs = new String[0];
        this.maxConnections = 1000;
        this.loggingEnabled = true;
    }
    
    public void enable() {
        this.isEnabled = true;
        System.out.println("Firewall enabled");
    }
    
    public void disable() {
        this.isEnabled = false;
        System.out.println("Firewall disabled");
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public void addAllowedIP(String ip) {
        String[] newAllowedIPs = new String[allowedIPs.length + 1];
        System.arraycopy(allowedIPs, 0, newAllowedIPs, 0, allowedIPs.length);
        newAllowedIPs[allowedIPs.length] = ip;
        allowedIPs = newAllowedIPs;
        System.out.println("Added " + ip + " to allowed IPs");
    }
    
    public void addBlockedIP(String ip) {
        String[] newBlockedIPs = new String[blockedIPs.length + 1];
        System.arraycopy(blockedIPs, 0, newBlockedIPs, 0, blockedIPs.length);
        newBlockedIPs[blockedIPs.length] = ip;
        blockedIPs = newBlockedIPs;
        System.out.println("Added " + ip + " to blocked IPs");
    }
    
    public boolean isIPAllowed(String ip) {
        for (String allowedIP : allowedIPs) {
            if (allowedIP.equals(ip)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isIPBlocked(String ip) {
        for (String blockedIP : blockedIPs) {
            if (blockedIP.equals(ip)) {
                return true;
            }
        }
        return false;
    }
    
    public void setMaxConnections(int max) {
        this.maxConnections = max;
        System.out.println("Maximum connections set to " + max);
    }
    
    public int getMaxConnections() {
        return this.maxConnections;
    }
    
    public void enableLogging() {
        this.loggingEnabled = true;
        System.out.println("Firewall logging enabled");
    }
    
    public void disableLogging() {
        this.loggingEnabled = false;
        System.out.println("Firewall logging disabled");
    }
    
    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }
    
    public void logConnection(String ip, String port, String protocol) {
        if (loggingEnabled) {
            System.out.println("Connection attempt from " + ip + " on port " + port + " using " + protocol);
        }
    }
    
    public void checkConnection(String ip, String port, String protocol) {
        if (!isEnabled) {
            System.out.println("Firewall is disabled - connection allowed");
            return;
        }
        
        if (isIPBlocked(ip)) {
            System.out.println("Connection blocked - IP is in blocked list");
            return;
        }
        
        if (allowedIPs.length > 0 && !isIPAllowed(ip)) {
            System.out.println("Connection blocked - IP not in allowed list");
            return;
        }
        
        logConnection(ip, port, protocol);
        System.out.println("Connection allowed");
    }
    
    public void displayStatus() {
        System.out.println("\nFirewall Status:");
        System.out.println("Enabled: " + isEnabled);
        System.out.println("Logging: " + loggingEnabled);
        System.out.println("Max Connections: " + maxConnections);
        System.out.println("Allowed IPs: " + allowedIPs.length);
        System.out.println("Blocked IPs: " + blockedIPs.length);
    }
    
    public String[] getAllowedIPs() {
        return this.allowedIPs;
    }

    public String[] getBlockedIPs() {
        return this.blockedIPs;
    }
}
