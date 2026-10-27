package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class so2 extends hk0 {
    public static Class k = null;
    public static Constructor l = null;
    public static Method m = null;
    public static Method n = null;
    public static boolean o = false;

    public so2() {
        super(8);
    }

    public static boolean D(int i, Object obj, String str, boolean z) {
        E();
        try {
            return ((Boolean) m.invoke(obj, str, Integer.valueOf(i), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void E() {
        Class<?> cls;
        Method method;
        Constructor<?> constructor;
        Method method2;
        if (o) {
            return;
        }
        o = true;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            constructor = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e("TypefaceCompatApi21Impl", e.getClass().getName(), e);
            cls = null;
            method = null;
            constructor = null;
            method2 = null;
        }
        l = constructor;
        k = cls;
        m = method2;
        n = method;
    }

    @Override // p.hk0
    public Typeface l(Context context, zv0 zv0Var, Resources resources, int i) {
        E();
        try {
            Object objNewInstance = l.newInstance(new Object[0]);
            for (aw0 aw0Var : zv0Var.a) {
                File fileU = f60.u(context);
                if (fileU == null) {
                    return null;
                }
                try {
                    if (!f60.k(fileU, resources, aw0Var.f)) {
                        return null;
                    }
                    if (!D(aw0Var.b, objNewInstance, fileU.getPath(), aw0Var.c)) {
                        return null;
                    }
                    fileU.delete();
                } catch (RuntimeException unused) {
                    return null;
                } finally {
                    fileU.delete();
                }
            }
            E();
            try {
                Object objNewInstance2 = Array.newInstance((Class<?>) k, 1);
                Array.set(objNewInstance2, 0, objNewInstance);
                return (Typeface) n.invoke(null, objNewInstance2);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e2) {
            throw new RuntimeException(e2);
        }
    }
}
