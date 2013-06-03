package foo.bar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test
{

    public static void main(String[] args) throws Exception{

        Test t = new Test();

        Class clazz = t.testClassLookup();
        RandomUtil instance = t.testInstantiation(clazz);
        Method method = t.testMethodLookup(clazz);
        t.testInvocation(method, instance);
    }

    private Class testClassLookup() throws ClassNotFoundException {
        Class clazz = null;

        long startTimeEpoch = System.currentTimeMillis();
        for (int i=0; i<10_000_000; i++) {
            clazz = Class.forName("foo.bar.RandomUtil");
        }
        long endTimeEpoch = System.currentTimeMillis();
        System.out.println("[Class load]: reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");

        return clazz;
    }

    private RandomUtil testInstantiation(Class clazz) throws InstantiationException, IllegalAccessException {
        Object instance = null;

        long startTimeEpoch = System.currentTimeMillis();
        for (int i=0; i<10_000_000; i++) {
            instance = clazz.newInstance();
        }
        long endTimeEpoch = System.currentTimeMillis();
        System.out.println("[New instance]: reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");


        startTimeEpoch = System.currentTimeMillis();
        for (int i=0; i<10_000_000; i++) {
            instance = new RandomUtil();
        }
        endTimeEpoch = System.currentTimeMillis();
        System.out.println("[New instance]: no-reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");

        return (RandomUtil)instance;
    }

    private Method testMethodLookup(Class clazz) throws NoSuchMethodException {
        Method method = null;

        long startTimeEpoch = System.currentTimeMillis();
        for (int i=0; i<10_000_000; i++) {
            method = clazz.getMethod("getRandomLong");
        }
        long endTimeEpoch = System.currentTimeMillis();
        System.out.println("[member find]: reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");

        return method;
    }

    private void testInvocation(Method method, RandomUtil instance)
            throws InvocationTargetException, IllegalAccessException {

        long startTimeEpoch = System.currentTimeMillis();
        for (int i=0; i<10_000_000; i++) {
            method.invoke(instance);
        }
        long endTimeEpoch = System.currentTimeMillis();
        System.out.println("[Invoke]: reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");

        startTimeEpoch = System.currentTimeMillis();
        RandomUtil u = new RandomUtil();
        for (int i=0; i<10_000_000; i++) {
            u.getRandomLong();
        }
        endTimeEpoch = System.currentTimeMillis();
        System.out.println("[Invoke]: no-reflection: " + (endTimeEpoch-startTimeEpoch) + " ms");
    }
}


