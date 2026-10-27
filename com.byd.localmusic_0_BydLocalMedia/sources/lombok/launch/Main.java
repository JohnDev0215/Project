package lombok.launch;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
class Main {
    private static ShadowClassLoader classLoader;

    Main() {
    }

    static synchronized ClassLoader getShadowClassLoader() {
        if (classLoader == null) {
            classLoader = new ShadowClassLoader(Main.class.getClassLoader(), "lombok", null, Arrays.asList(new String[0]), Arrays.asList("lombok.patcher.Symbols"));
        }
        return classLoader;
    }

    static synchronized void prependClassLoader(ClassLoader classLoader2) {
        getShadowClassLoader();
        classLoader.prependParent(classLoader2);
    }

    public static void main(String[] strArr) throws Throwable {
        try {
            getShadowClassLoader().loadClass("lombok.core.Main").getMethod("main", String[].class).invoke(null, strArr);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
