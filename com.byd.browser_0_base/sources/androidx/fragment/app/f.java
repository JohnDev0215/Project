package androidx.fragment.app;

import androidx.fragment.app.Fragment;
import defpackage.gq2;
import defpackage.wm2;

/* JADX INFO: compiled from: FragmentFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static final wm2<ClassLoader, wm2<String, Class<?>>> a = new wm2<>();

    public static Class<?> b(ClassLoader classLoader, String str) throws ClassNotFoundException {
        wm2<ClassLoader, wm2<String, Class<?>>> wm2Var = a;
        wm2<String, Class<?>> wm2Var2 = wm2Var.get(classLoader);
        if (wm2Var2 == null) {
            wm2Var2 = new wm2<>();
            wm2Var.put(classLoader, wm2Var2);
        }
        Class<?> cls = wm2Var2.get(str);
        if (cls != null) {
            return cls;
        }
        Class<?> cls2 = Class.forName(str, false, classLoader);
        wm2Var2.put(str, cls2);
        return cls2;
    }

    public static Class<? extends Fragment> c(ClassLoader classLoader, String str) {
        try {
            return b(classLoader, str);
        } catch (ClassCastException e) {
            throw new Fragment.d(gq2.g("Unable to instantiate fragment ", str, ": make sure class is a valid subclass of Fragment"), e);
        } catch (ClassNotFoundException e2) {
            throw new Fragment.d(gq2.g("Unable to instantiate fragment ", str, ": make sure class name exists"), e2);
        }
    }

    public Fragment a(String str) {
        throw null;
    }
}
