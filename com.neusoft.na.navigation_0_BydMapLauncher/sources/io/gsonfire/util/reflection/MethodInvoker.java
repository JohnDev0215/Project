package io.gsonfire.util.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MethodInvoker {
    private final List<Class> argsOrder;
    private final Method method;

    public interface ValueSupplier {
        Object getValueForType(Class cls);
    }

    public MethodInvoker(Method method, Set<Class> set) {
        this.method = method;
        this.argsOrder = new ArrayList(set.size());
        for (Class<?> cls : method.getParameterTypes()) {
            if (set.contains(cls)) {
                this.argsOrder.add(cls);
            } else {
                throw new IllegalArgumentException("Cannot auto inject type: " + cls);
            }
        }
    }

    public void invoke(Object obj, ValueSupplier valueSupplier) throws IllegalAccessException, InvocationTargetException {
        int length = this.method.getParameterTypes().length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = valueSupplier.getValueForType(this.argsOrder.get(i));
        }
        this.method.invoke(obj, objArr);
    }
}
