package a.n;

import androidx.lifecycle.CompositeGeneratedAdaptersObserver;
import androidx.lifecycle.FullLifecycleObserverAdapter;
import androidx.lifecycle.ReflectiveGenericLifecycleObserver;
import androidx.lifecycle.SingleGeneratedAdapterObserver;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<Class<?>, Integer> f1101a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<Class<?>, List<Constructor<? extends e>>> f1102b = new HashMap();

    public static e a(Constructor<? extends e> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    public static String b(String str) {
        return str.replace(".", "_") + "_LifecycleAdapter";
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0117 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int c(java.lang.Class<?> r11) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.n.n.c(java.lang.Class):int");
    }

    public static i d(Object obj) {
        boolean z = obj instanceof i;
        boolean z2 = obj instanceof d;
        if (z && z2) {
            return new FullLifecycleObserverAdapter((d) obj, (i) obj);
        }
        if (z2) {
            return new FullLifecycleObserverAdapter((d) obj, null);
        }
        if (z) {
            return (i) obj;
        }
        Class<?> cls = obj.getClass();
        if (c(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends e>> list = f1102b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
        }
        e[] eVarArr = new e[list.size()];
        for (int i = 0; i < list.size(); i++) {
            eVarArr[i] = a(list.get(i), obj);
        }
        return new CompositeGeneratedAdaptersObserver(eVarArr);
    }
}
