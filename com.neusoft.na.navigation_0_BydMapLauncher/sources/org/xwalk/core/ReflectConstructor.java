package org.xwalk.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
class ReflectConstructor {
    private Class<?> mClass;
    private Constructor<?> mConstructor;
    private Class<?>[] mParameterTypes;

    public ReflectConstructor() {
    }

    public ReflectConstructor(Class<?> cls, Class<?>... clsArr) {
        init(cls, clsArr);
    }

    public boolean init(Class<?> cls, Class<?>... clsArr) {
        this.mClass = cls;
        this.mParameterTypes = clsArr;
        this.mConstructor = null;
        if (cls == null) {
            return false;
        }
        try {
            try {
                this.mConstructor = cls.getConstructor(clsArr);
            } catch (NoSuchMethodException unused) {
                Constructor<?> declaredConstructor = this.mClass.getDeclaredConstructor(this.mParameterTypes);
                this.mConstructor = declaredConstructor;
                declaredConstructor.setAccessible(true);
            }
        } catch (NoSuchMethodException unused2) {
        }
        return this.mConstructor != null;
    }

    public Object newInstance(Object... objArr) {
        Constructor<?> constructor = this.mConstructor;
        if (constructor == null) {
            throw new UnsupportedOperationException(toString());
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException e) {
            e = e;
            throw new RejectedExecutionException(e);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InstantiationException e3) {
            e = e3;
            throw new RejectedExecutionException(e);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4.getCause());
        }
    }

    public boolean isNull() {
        return this.mConstructor == null;
    }

    public String toString() {
        Constructor<?> constructor = this.mConstructor;
        return constructor != null ? constructor.toString() : this.mClass != null ? "" + this.mClass.toString() : "";
    }
}
