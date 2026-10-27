package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class lt1 {
    public static final kt1 a;

    static {
        kt1 kt1Var;
        Object objNewInstance;
        try {
            objNewInstance = m61.class.newInstance();
            v03.i(objNewInstance, "forName(\"kotlin.internal…entations\").newInstance()");
        } catch (ClassNotFoundException unused) {
            Object objNewInstance2 = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
            v03.i(objNewInstance2, "forName(\"kotlin.internal…entations\").newInstance()");
            try {
                try {
                    kt1Var = (kt1) objNewInstance2;
                } catch (ClassCastException e) {
                    ClassLoader classLoader = objNewInstance2.getClass().getClassLoader();
                    ClassLoader classLoader2 = kt1.class.getClassLoader();
                    if (v03.b(classLoader, classLoader2)) {
                        throw e;
                    }
                    throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader + ", base type classloader: " + classLoader2, e);
                }
            } catch (ClassNotFoundException unused2) {
                Object objNewInstance3 = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                v03.i(objNewInstance3, "forName(\"kotlin.internal…entations\").newInstance()");
                try {
                    kt1Var = (kt1) objNewInstance3;
                } catch (ClassCastException e2) {
                    ClassLoader classLoader3 = objNewInstance3.getClass().getClassLoader();
                    ClassLoader classLoader4 = kt1.class.getClassLoader();
                    if (v03.b(classLoader3, classLoader4)) {
                        throw e2;
                    }
                    throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader3 + ", base type classloader: " + classLoader4, e2);
                }
            }
        }
        try {
            try {
                kt1Var = (kt1) objNewInstance;
            } catch (ClassNotFoundException unused3) {
                Object objNewInstance4 = k61.class.newInstance();
                v03.i(objNewInstance4, "forName(\"kotlin.internal…entations\").newInstance()");
                try {
                    try {
                        kt1Var = (kt1) objNewInstance4;
                    } catch (ClassCastException e3) {
                        ClassLoader classLoader5 = objNewInstance4.getClass().getClassLoader();
                        ClassLoader classLoader6 = kt1.class.getClassLoader();
                        if (v03.b(classLoader5, classLoader6)) {
                            throw e3;
                        }
                        throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader5 + ", base type classloader: " + classLoader6, e3);
                    }
                } catch (ClassNotFoundException unused4) {
                    kt1Var = new kt1();
                }
            }
            a = kt1Var;
        } catch (ClassCastException e4) {
            ClassLoader classLoader7 = objNewInstance.getClass().getClassLoader();
            ClassLoader classLoader8 = kt1.class.getClassLoader();
            if (v03.b(classLoader7, classLoader8)) {
                throw e4;
            }
            throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader7 + ", base type classloader: " + classLoader8, e4);
        }
    }
}
