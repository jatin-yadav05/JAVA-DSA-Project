package attacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class IPNode {
    String ip;
    IPNode left, right;

    IPNode(String ip) {
        this.ip = ip;
    }
}

class IPBlocklistTree {
    IPNode root;

    void insert(String ip) {
        root = insertRec(root, ip);
    }

    private IPNode insertRec(IPNode node, String ip) {
        if (node == null) return new IPNode(ip);
        if (ip.compareTo(node.ip) < 0)
            node.left = insertRec(node.left, ip);
        else
            node.right = insertRec(node.right, ip);
        return node;
    }

    boolean isBlocked(String ip) {
        return searchRec(root, ip);
    }

    private boolean searchRec(IPNode node, String ip) {
        if (node == null) return false;
        if (node.ip.equals(ip)) return true;
        return ip.compareTo(node.ip) < 0 ?
                searchRec(node.left, ip) : searchRec(node.right, ip);
    }
    
    // Utility to get height (to see how unbalanced)
    int height() {
        return heightRec(root);
    }
    
    private int heightRec(IPNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
    }
}

public class DdosAttack {
    // Generate random IP
    static String randomIP(Random rand) {
        return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
    }

    public static void main(String[] args) {
        int n = 10000; // number of IPs
        Random rand = new Random();
        boolean useSorted = args.length > 0 && args[0].equals("sorted");
        List<String> ips = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ips.add(randomIP(rand));
        }
        if (useSorted) {
            Collections.sort(ips);
        }
        IPBlocklistTree tree = new IPBlocklistTree();
        long startInsert = System.nanoTime();
        for (String ip : ips) {
            tree.insert(ip);
        }
        long endInsert = System.nanoTime();
        long startSearch = System.nanoTime();
        for (String ip : ips) {
            tree.isBlocked(ip);
        }
        long endSearch = System.nanoTime();
        System.out.println((useSorted ? "Sorted" : "Random") + " IPs BST:");
        System.out.println("Inserted " + n + " " + (useSorted ? "sorted" : "random") + " IPs in " + (endInsert - startInsert) / 1_000_000 + " ms");
        System.out.println("Searched " + n + " " + (useSorted ? "sorted" : "random") + " IPs in " + (endSearch - startSearch) / 1_000_000 + " ms");
        System.out.println("BST height: " + tree.height());
        System.out.println();
        if (useSorted) {
            System.out.println("Notice how the height and times are worse with sorted IPs — this shows BST degeneration (performance degradation)!");
        }
    }
}