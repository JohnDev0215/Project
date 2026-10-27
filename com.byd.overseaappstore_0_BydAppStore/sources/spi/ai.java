package spi;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class ai {
    public final Context a;

    public ai(Context context) {
        this.a = context;
    }

    public static md a(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                Object objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (objNewInstance instanceof md) {
                    return (md) objNewInstance;
                }
                throw new RuntimeException("Expected instanceof GlideModule, but found: " + objNewInstance);
            } catch (IllegalAccessException e) {
                b(cls, e);
                throw null;
            } catch (InstantiationException e2) {
                b(cls, e2);
                throw null;
            } catch (NoSuchMethodException e3) {
                b(cls, e3);
                throw null;
            } catch (InvocationTargetException e4) {
                b(cls, e4);
                throw null;
            }
        } catch (ClassNotFoundException e5) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e5);
        }
    }

    public static void b(Class<?> cls, Exception exc) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, exc);
    }
}
