package androidx.lifecycle;

import android.app.Application;
import h8.AbstractC1387d;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1999n;

/* JADX INFO: loaded from: classes.dex */
public abstract class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f11982a = AbstractC1999n.D(Application.class, X.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final List f11983b = AbstractC1387d.j(X.class);

    public static final Constructor a(Class cls, List list) {
        A7.m.f(list, "signature");
        Constructor<?>[] constructors = cls.getConstructors();
        A7.m.e(constructors, "modelClass.constructors");
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            A7.m.e(parameterTypes, "constructor.parameterTypes");
            List listB0 = AbstractC1996k.b0(parameterTypes);
            if (list.equals(listB0)) {
                return constructor;
            }
            if (list.size() == listB0.size() && listB0.containsAll(list)) {
                throw new UnsupportedOperationException("Class " + cls.getSimpleName() + " must have parameters in the proper order: " + list);
            }
        }
        return null;
    }

    public static final f0 b(Class cls, Constructor constructor, Object... objArr) {
        try {
            return (f0) constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("Failed to access " + cls, e10);
        } catch (InstantiationException e11) {
            throw new RuntimeException("A " + cls + " cannot be instantiated.", e11);
        } catch (InvocationTargetException e12) {
            throw new RuntimeException("An exception happened in constructor of " + cls, e12.getCause());
        }
    }
}
