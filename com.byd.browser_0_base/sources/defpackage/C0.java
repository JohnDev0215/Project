package defpackage;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class C0 {
    public static final B0 a;

    static {
        B0 b0;
        try {
            Object objNewInstance = C0194k0.class.newInstance();
            C0186g0.d(objNewInstance, "forName(\"kotlin.internal…entations\").newInstance()");
            try {
                try {
                    b0 = (B0) objNewInstance;
                } catch (ClassCastException e) {
                    ClassLoader classLoader = objNewInstance.getClass().getClassLoader();
                    ClassLoader classLoader2 = B0.class.getClassLoader();
                    if (C0186g0.a(classLoader, classLoader2)) {
                        throw e;
                    }
                    throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader + ", base type classloader: " + classLoader2, e);
                }
            } catch (ClassNotFoundException unused) {
                Object objNewInstance2 = C0190i0.class.newInstance();
                C0186g0.d(objNewInstance2, "forName(\"kotlin.internal…entations\").newInstance()");
                try {
                    try {
                        b0 = (B0) objNewInstance2;
                    } catch (ClassNotFoundException unused2) {
                        b0 = new B0();
                    }
                } catch (ClassCastException e2) {
                    ClassLoader classLoader3 = objNewInstance2.getClass().getClassLoader();
                    ClassLoader classLoader4 = B0.class.getClassLoader();
                    if (C0186g0.a(classLoader3, classLoader4)) {
                        throw e2;
                    }
                    throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader3 + ", base type classloader: " + classLoader4, e2);
                }
            }
        } catch (ClassNotFoundException unused3) {
            Object objNewInstance3 = Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
            C0186g0.d(objNewInstance3, "forName(\"kotlin.internal…entations\").newInstance()");
            try {
                try {
                    b0 = (B0) objNewInstance3;
                } catch (ClassNotFoundException unused4) {
                    Object objNewInstance4 = Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
                    C0186g0.d(objNewInstance4, "forName(\"kotlin.internal…entations\").newInstance()");
                    try {
                        b0 = (B0) objNewInstance4;
                    } catch (ClassCastException e3) {
                        ClassLoader classLoader5 = objNewInstance4.getClass().getClassLoader();
                        ClassLoader classLoader6 = B0.class.getClassLoader();
                        if (C0186g0.a(classLoader5, classLoader6)) {
                            throw e3;
                        }
                        throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader5 + ", base type classloader: " + classLoader6, e3);
                    }
                }
            } catch (ClassCastException e4) {
                ClassLoader classLoader7 = objNewInstance3.getClass().getClassLoader();
                ClassLoader classLoader8 = B0.class.getClassLoader();
                if (C0186g0.a(classLoader7, classLoader8)) {
                    throw e4;
                }
                throw new ClassNotFoundException("Instance class was loaded from a different classloader: " + classLoader7 + ", base type classloader: " + classLoader8, e4);
            }
        }
        a = b0;
    }
}
