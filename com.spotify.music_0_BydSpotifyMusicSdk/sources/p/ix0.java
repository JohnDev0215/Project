package p;

import android.content.Context;
import androidx.fragment.app.Fragment$InstantiationException;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class ix0 {
    public static final kd2 b = new kd2();
    public final /* synthetic */ ox0 a;

    public ix0(ox0 ox0Var) {
        this.a = ox0Var;
    }

    public static Class b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        kd2 kd2Var = b;
        kd2 kd2Var2 = (kd2) kd2Var.get(classLoader);
        if (kd2Var2 == null) {
            kd2Var2 = new kd2();
            kd2Var.put(classLoader, kd2Var2);
        }
        Class cls = (Class) kd2Var2.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        kd2Var2.put(str, cls2);
        return cls2;
    }

    public static Class c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e);
        } catch (ClassNotFoundException e2) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": make sure class name exists"), e2);
        }
    }

    public final ww0 a(String str) {
        Context context = this.a.t.r;
        Object obj = ww0.a0;
        try {
            return (ww0) c(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (IllegalAccessException e) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (InstantiationException e2) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new Fragment$InstantiationException(u4.j("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }
}
