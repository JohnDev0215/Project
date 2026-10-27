package o5;

import h6.r;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: o5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2046b extends r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f20527b = Class.class.getMethod("isRecord", null);
    public final Method c = Class.class.getMethod("getRecordComponents", null);
    public final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f20528e;

    public C2046b() throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
        this.d = cls.getMethod("getName", null);
        this.f20528e = cls.getMethod("getType", null);
    }

    @Override // h6.r
    public final Method f(Class cls, Field field) {
        try {
            return cls.getMethod(field.getName(), null);
        } catch (ReflectiveOperationException e10) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.11.0). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e10);
        }
    }

    @Override // h6.r
    public final Constructor g(Class cls) {
        try {
            Object[] objArr = (Object[]) this.c.invoke(cls, null);
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i7 = 0; i7 < objArr.length; i7++) {
                clsArr[i7] = (Class) this.f20528e.invoke(objArr[i7], null);
            }
            return cls.getDeclaredConstructor(clsArr);
        } catch (ReflectiveOperationException e10) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.11.0). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e10);
        }
    }

    @Override // h6.r
    public final String[] j(Class cls) {
        try {
            Object[] objArr = (Object[]) this.c.invoke(cls, null);
            String[] strArr = new String[objArr.length];
            for (int i7 = 0; i7 < objArr.length; i7++) {
                strArr[i7] = (String) this.d.invoke(objArr[i7], null);
            }
            return strArr;
        } catch (ReflectiveOperationException e10) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.11.0). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e10);
        }
    }

    @Override // h6.r
    public final boolean m(Class cls) {
        try {
            return ((Boolean) this.f20527b.invoke(cls, null)).booleanValue();
        } catch (ReflectiveOperationException e10) {
            throw new RuntimeException("Unexpected ReflectiveOperationException occurred (Gson 2.11.0). To support Java records, reflection is utilized to read out information about records. All these invocations happens after it is established that records exist in the JVM. This exception is unexpected behavior.", e10);
        }
    }
}
