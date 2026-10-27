package org.xwalk.core;

import java.lang.reflect.Field;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: loaded from: classes4.dex */
class ReflectField {
    private Class<?> mClass;
    private Field mField;
    private Object mInstance;
    private String mName;

    public ReflectField() {
    }

    public ReflectField(Object obj, String str) {
        init(obj, null, str);
    }

    public ReflectField(Class<?> cls, String str) {
        init(null, cls, str);
    }

    public boolean init(Object obj, Class<?> cls, String str) {
        this.mInstance = obj;
        if (cls == null) {
            cls = obj != null ? obj.getClass() : null;
        }
        this.mClass = cls;
        this.mName = str;
        this.mField = null;
        if (cls == null) {
            return false;
        }
        try {
            this.mField = cls.getField(str);
        } catch (NoSuchFieldException unused) {
            for (Class<?> superclass = this.mClass; superclass != null; superclass = superclass.getSuperclass()) {
                try {
                    Field declaredField = superclass.getDeclaredField(this.mName);
                    this.mField = declaredField;
                    declaredField.setAccessible(true);
                    break;
                } catch (NoSuchFieldException unused2) {
                }
            }
        }
        return this.mField != null;
    }

    public Object get() {
        Field field = this.mField;
        if (field == null) {
            throw new UnsupportedOperationException(toString());
        }
        try {
            return field.get(this.mInstance);
        } catch (ExceptionInInitializerError e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw new RejectedExecutionException(e);
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (NullPointerException e4) {
            e = e4;
            throw new RejectedExecutionException(e);
        }
    }

    public boolean isNull() {
        return this.mField == null;
    }

    public String toString() {
        Field field = this.mField;
        if (field != null) {
            return field.toString();
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
}
