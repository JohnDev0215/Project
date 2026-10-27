package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ServiceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kd {
    public static fa<kd> b = new a();
    public final Map<String, Object> a;

    /* JADX INFO: compiled from: ServiceFactory.java */
    public static class a extends fa<kd> {
        @Override // spi.fa
        public final kd a() {
            return new kd(null);
        }
    }

    public /* synthetic */ kd(a aVar) {
        this();
    }

    public static kd b() {
        return b.b();
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Object>] */
    public final Object a(Context context, @NonNull Class<?> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?> declaredConstructor;
        String canonicalName = cls.getCanonicalName();
        ca caVar = (ca) cls.getAnnotation(ca.class);
        boolean z = caVar != null && caVar.singleton();
        synchronized (this) {
            Object objNewInstance = this.a.get(canonicalName);
            if (z && objNewInstance != null) {
                return objNewInstance;
            }
            Object objCast = null;
            try {
                declaredConstructor = cls.getDeclaredConstructor(Context.class);
            } catch (Throwable unused) {
                declaredConstructor = null;
            }
            try {
                if (d8.a(declaredConstructor)) {
                    try {
                        Constructor<?> declaredConstructor2 = cls.getDeclaredConstructor(new Class[0]);
                        declaredConstructor2.setAccessible(true);
                        objCast = cls.cast(declaredConstructor2.newInstance(new Object[0]));
                    } catch (Throwable th) {
                        i6.a("ReflectUtil", th.getMessage());
                    }
                    objNewInstance = objCast;
                } else {
                    objNewInstance = declaredConstructor.newInstance(context);
                }
                if (z) {
                    this.a.put(canonicalName, objNewInstance);
                }
            } catch (Throwable th2) {
                i6.f("foundation.spi", "spi create service instance failed:", th2);
            }
            return objNewInstance;
        }
    }

    private kd() {
        this.a = new HashMap();
    }
}
