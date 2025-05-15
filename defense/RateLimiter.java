package defense;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {
    private final Map<String, AtomicInteger> requestCounts;
    private final Map<String, Long> lastResetTime;
    private final int maxRequests;
    private final long timeWindow;

    public RateLimiter(int maxRequests, long timeWindow, TimeUnit timeUnit) {
        this.requestCounts = new ConcurrentHashMap<>();
        this.lastResetTime = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.timeWindow = timeUnit.toMillis(timeWindow);
    }
    
    public boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        AtomicInteger count = requestCounts.computeIfAbsent(clientId, k -> new AtomicInteger(0));
        Long lastReset = lastResetTime.computeIfAbsent(clientId, k -> currentTime);

        if (currentTime - lastReset >= timeWindow) {
            count.set(0);
            lastResetTime.put(clientId, currentTime);
        }

        return count.incrementAndGet() <= maxRequests;
    }

    public void resetLimit(String clientId) {
        requestCounts.remove(clientId);
        lastResetTime.remove(clientId);
    }

    public int getRemainingRequests(String clientId) {
        AtomicInteger count = requestCounts.get(clientId);
        if (count == null) {
            return maxRequests;
        }
        return Math.max(0, maxRequests - count.get());
    }

    public long getTimeUntilReset(String clientId) {
        Long lastReset = lastResetTime.get(clientId);
        if (lastReset == null) {
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        long timeElapsed = currentTime - lastReset;
        return Math.max(0, timeWindow - timeElapsed);
    }

    public boolean isRateLimited(String clientId) {
        return !allowRequest(clientId);
    }

    public void setNewLimit(String clientId, int newMaxRequests) {
        requestCounts.put(clientId, new AtomicInteger(0));
        lastResetTime.put(clientId, System.currentTimeMillis());
    }
}
