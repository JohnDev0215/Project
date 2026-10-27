package org.xwalk.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
class ReflectMethod {
    private Object[] mArguments;
    private Class<?> mClass;
    private Object mInstance;
    private Method mMethod;
    private String mName;
    private Class<?>[] mParameterTypes;

    public ReflectMethod() {
    }

    public ReflectMethod(Object obj, String str, Class<?>... clsArr) {
        init(obj, null, str, clsArr);
    }

    public ReflectMethod(Class<?> cls, String str, Class<?>... clsArr) {
        init(null, cls, str, clsArr);
    }

    public boolean init(Object obj, Class<?> cls, String str, Class<?>... clsArr) {
        this.mInstance = obj;
        if (cls == null) {
            cls = obj != null ? obj.getClass() : null;
        }
        this.mClass = cls;
        this.mName = str;
        this.mParameterTypes = clsArr;
        this.mMethod = null;
        if (cls == null) {
            return false;
        }
        try {
            this.mMethod = cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            for (Class<?> superclass = this.mClass; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    Method declaredMethod = superclass.getDeclaredMethod(this.mName, this.mParameterTypes);
                    this.mMethod = declaredMethod;
                    declaredMethod.setAccessible(true);
                    break;
                } catch (NoSuchMethodException unused2) {
                }
            }
        }
        return this.mMethod != null;
    }

    public Object invoke(Object... objArr) {
        Method method = this.mMethod;
        if (method == null) {
            throw new UnsupportedOperationException(toString());
        }
        try {
            return method.invoke(this.mInstance, objArr);
        } catch (IllegalAccessException e) {
            e = e;
            throw new RejectedExecutionException(e);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (NullPointerException e3) {
            e = e3;
            throw new RejectedExecutionException(e);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4.getCause());
        }
    }

    public boolean isNull() {
        return this.mMethod == null;
    }

    public String toString() {
        Method method = this.mMethod;
        if (method != null) {
            return method.toString();
        }
        String str = this.mClass != null ? "" + this.mClass.toString() + "." : "";
        return this.mName != null ? str + this.mName : str;
    }

    public String getName() {
        return this.mName;
    }

    public Object getInstance() {
        return this.mInstance;
    }

    public Object[] getArguments() {
        return this.mArguments;
    }

    public void setArguments(Object... objArr) {
        this.mArguments = objArr;
    }

    public Object invokeWithArguments() {
        return invoke(this.mArguments);
    }
}
