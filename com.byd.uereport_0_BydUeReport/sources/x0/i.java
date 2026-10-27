package x0;

import androidx.lifecycle.CompositeGeneratedAdaptersObserver;
import androidx.lifecycle.FullLifecycleObserverAdapter;
import androidx.lifecycle.ReflectiveGenericLifecycleObserver;
import androidx.lifecycle.SingleGeneratedAdapterObserver;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: Lifecycling.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Map<Class<?>, Integer> f9066a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<Class<?>, List<Constructor<? extends c>>> f9067b = new HashMap();

    public static c a(Constructor<? extends c> constructor, Object obj) {
        try {
            return constructor.newInstance(obj);
        } catch (IllegalAccessException e8) {
            throw new RuntimeException(e8);
        } catch (InstantiationException e9) {
            throw new RuntimeException(e9);
        } catch (InvocationTargetException e10) {
            throw new RuntimeException(e10);
        }
    }

    public static Constructor<? extends c> b(Class<?> cls) {
        try {
            Package r02 = cls.getPackage();
            String canonicalName = cls.getCanonicalName();
            String name = r02 != null ? r02.getName() : "";
            if (!name.isEmpty()) {
                canonicalName = canonicalName.substring(name.length() + 1);
            }
            String strC = c(canonicalName);
            if (!name.isEmpty()) {
                strC = name + "." + strC;
            }
            Constructor declaredConstructor = Class.forName(strC).getDeclaredConstructor(cls);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e8) {
            throw new RuntimeException(e8);
        }
    }

    public static String c(String str) {
        return str.replace(".", BydPowerManagerDevice.SEPARATOR_K_V) + "_LifecycleAdapter";
    }

    public static int d(Class<?> cls) {
        Integer num = f9066a.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int iG = g(cls);
        f9066a.put(cls, Integer.valueOf(iG));
        return iG;
    }

    public static boolean e(Class<?> cls) {
        return cls != null && e.class.isAssignableFrom(cls);
    }

    public static d f(Object obj) {
        boolean z7 = obj instanceof d;
        boolean z8 = obj instanceof b;
        if (z7 && z8) {
            return new FullLifecycleObserverAdapter((b) obj, (d) obj);
        }
        if (z8) {
            return new FullLifecycleObserverAdapter((b) obj, null);
        }
        if (z7) {
            return (d) obj;
        }
        Class<?> cls = obj.getClass();
        if (d(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(obj);
        }
        List<Constructor<? extends c>> list = f9067b.get(cls);
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(a(list.get(0), obj));
        }
        c[] cVarArr = new c[list.size()];
        for (int i8 = 0; i8 < list.size(); i8++) {
            cVarArr[i8] = a(list.get(i8), obj);
        }
        return new CompositeGeneratedAdaptersObserver(cVarArr);
    }

    public static int g(Class<?> cls) {
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends c> constructorB = b(cls);
        if (constructorB != null) {
            f9067b.put(cls, Collections.singletonList(constructorB));
            return 2;
        }
        if (a.f9048a.d(cls)) {
            return 1;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        ArrayList arrayList = null;
        if (e(superclass)) {
            if (d(superclass) == 1) {
                return 1;
            }
            arrayList = new ArrayList(f9067b.get(superclass));
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            if (e(cls2)) {
                if (d(cls2) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.addAll(f9067b.get(cls2));
            }
        }
        if (arrayList == null) {
            return 1;
        }
        f9067b.put(cls, arrayList);
        return 2;
    }
}
