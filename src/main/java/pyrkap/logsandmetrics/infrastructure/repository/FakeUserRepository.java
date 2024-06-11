package pyrkap.logsandmetrics.infrastructure.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Random;

@Repository
public class FakeUserRepository {
    private final Random random = new Random();
    private final ArrayList<Long> onlineUsersOverTime = new ArrayList<>();

    public long getOnlineUsersCount() {
        var usersOnline = random.nextLong(100, 1000);
        onlineUsersOverTime.add(usersOnline);
        return usersOnline;
    }

    public double getOnlineUsersAverageOverTime() {
        return onlineUsersOverTime.stream().mapToLong(Long::longValue).average().orElse(0d);
    }
}
