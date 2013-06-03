package foo.bar;

import java.util.Random;

public class RandomUtil {

    Random random;
    public RandomUtil() {
        random = new Random();
    }

    public long getRandomLong() {
        return random.nextLong();
    }
}
