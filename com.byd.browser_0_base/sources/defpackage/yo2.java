package defpackage;

import android.graphics.drawable.Drawable;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: SkinCompatVersionUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yo2 {
    public static final Class<?> a;
    public static Method b;
    public static Method c;
    public static final Class<?> d;
    public static Method e;
    public static Method f;

    static {
        try {
            d = Class.forName("android.support.v4.graphics.drawable.WrappedDrawable");
        } catch (ClassNotFoundException unused) {
        }
        try {
            a = Class.forName("android.support.v4.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException unused2) {
        }
        try {
            Class.forName("android.support.v7.graphics.drawable.DrawableWrapper");
        } catch (ClassNotFoundException unused3) {
        }
    }

    public static Drawable a(Drawable drawable) {
        Class<?> cls = a;
        if (cls != null) {
            if (b == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("getWrappedDrawable", new Class[0]);
                    b = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception unused) {
                }
            }
            Method method = b;
            if (method != null) {
                try {
                    return (Drawable) method.invoke(drawable, new Object[0]);
                } catch (Exception unused2) {
                }
            }
        }
        return drawable;
    }

    public static Drawable b(Drawable drawable) {
        Class<?> cls = d;
        if (cls != null) {
            if (e == null) {
                try {
                    Method declaredMethod = cls.getDeclaredMethod("getWrappedDrawable", new Class[0]);
                    e = declaredMethod;
                    declaredMethod.setAccessible(true);
                } catch (Exception unused) {
                }
            }
            Method method = e;
            if (method != null) {
                try {
                    return (Drawable) method.invoke(drawable, new Object[0]);
                } catch (Exception unused2) {
                }
            }
        }
        return drawable;
    }
}
