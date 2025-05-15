package defense;

public class PreventionTechniques {
    public static void display() {
        System.out.println("Prevention Techniques:");
        System.out.println("1. Input Validation");
        System.out.println("   - Validate all user inputs against expected format and type");
        System.out.println("   - Implement whitelist validation for allowed characters");
        System.out.println("   - Sanitize inputs to remove potentially harmful content");
        System.out.println("   - Use parameterized queries to prevent SQL injection");
        
        System.out.println("2. Output Encoding");
        System.out.println("   - Encode output to prevent XSS attacks");
        System.out.println("   - Use HTML encoding for web content");
        System.out.println("   - Implement proper character encoding (UTF-8)");
        System.out.println("   - Escape special characters in output");
        
        System.out.println("3. Authentication & Authorization");
        System.out.println("   - Implement strong password policies");
        System.out.println("   - Use multi-factor authentication");
        System.out.println("   - Implement role-based access control (RBAC)");
        System.out.println("   - Regular password rotation and account reviews");
        
        System.out.println("4. Session Management");
        System.out.println("   - Use secure session tokens");
        System.out.println("   - Implement session timeout");
        System.out.println("   - Regenerate session IDs after login");
        System.out.println("   - Secure session storage and transmission");
        
        System.out.println("5. Error Handling");
        System.out.println("   - Implement custom error pages");
        System.out.println("   - Log errors securely");
        System.out.println("   - Don't expose sensitive information in errors");
        System.out.println("   - Use proper exception handling");
        
        System.out.println("6. Secure Communication");
        System.out.println("   - Use HTTPS/TLS for all communications");
        System.out.println("   - Implement proper SSL/TLS configuration");
        System.out.println("   - Use strong encryption algorithms");
        System.out.println("   - Regular certificate management");
        
        System.out.println("7. Security Headers");
        System.out.println("   - Implement Content Security Policy (CSP)");
        System.out.println("   - Use X-Frame-Options");
        System.out.println("   - Enable X-XSS-Protection");
        System.out.println("   - Set secure and HTTP-only cookies");
        
        System.out.println("8. Regular Security Updates");
        System.out.println("   - Keep all software and dependencies updated");
        System.out.println("   - Monitor security advisories");
        System.out.println("   - Implement automated update processes");
        System.out.println("   - Regular security audits and penetration testing");
    }
}
