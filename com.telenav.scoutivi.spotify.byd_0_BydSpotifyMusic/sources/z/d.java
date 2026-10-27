package z;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import y.a;

/* JADX INFO: compiled from: TypefaceCompatApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
class d extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Class<?> f10029b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Constructor<?> f10030c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f10031d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Method f10032e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f10033f = false;

    d() {
    }

    private static boolean f(Object obj, String str, int i7, boolean z6) throws NoSuchMethodException {
        h();
        try {
            return ((Boolean) f10031d.invoke(obj, str, Integer.valueOf(i7), Boolean.valueOf(z6))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e7) {
            throw new RuntimeException(e7);
        }
    }

    private static Typeface g(Object obj) throws NoSuchMethodException {
        h();
        try {
            Object objNewInstance = Array.newInstance(f10029b, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f10032e.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException e7) {
            throw new RuntimeException(e7);
        }
    }

    private static void h() throws NoSuchMethodException {
        Method method;
        Class<?> cls;
        Method method2;
        if (f10033f) {
            return;
        }
        f10033f = true;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", String.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e7) {
            Log.e("TypefaceCompatApi21Impl", e7.getClass().getName(), e7);
            method = null;
            cls = null;
            method2 = null;
        }
        f10030c = constructor;
        f10029b = cls;
        f10031d = method2;
        f10032e = method;
    }

    private static Object i() throws NoSuchMethodException {
        h();
        try {
            return f10030c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e7) {
            throw new RuntimeException(e7);
        }
    }

    @Override // z.h
    public Typeface a(Context context, a.b bVar, Resources resources, int i7) throws NoSuchMethodException {
        Object objI = i();
        for (a.c cVar : bVar.a()) {
            File fileD = i.d(context);
            if (fileD == null) {
                return null;
            }
            try {
                if (!i.b(fileD, resources, cVar.b())) {
                    return null;
                }
                if (!f(objI, fileD.getPath(), cVar.e(), cVar.f())) {
                    return null;
                }
                fileD.delete();
            } catch (RuntimeException unused) {
                return null;
            } finally {
                fileD.delete();
            }
        }
        return g(objI);
    }
}
