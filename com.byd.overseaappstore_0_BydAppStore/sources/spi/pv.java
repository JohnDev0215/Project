package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class pv {
    public static op<pv> b = new a();
    public final Map<String, Object> a;

    public static class a extends op<pv> {
        @Override // spi.op
        public final pv a() {
            return new pv(null);
        }
    }

    private pv() {
        this.a = new HashMap();
    }

    public /* synthetic */ pv(a aVar) {
        this();
    }

    public static pv b() {
        return b.b();
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Object>] */
    /* JADX WARN: Type inference failed for: r9v8, types: [java.util.HashMap, java.util.Map<java.lang.String, java.lang.Object>] */
    public final Object a(Context context, @NonNull Class<?> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?> declaredConstructor;
        String canonicalName = cls.getCanonicalName();
        zo zoVar = (zo) cls.getAnnotation(zo.class);
        boolean z = zoVar != null && zoVar.singleton();
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
                if (xj.a(declaredConstructor)) {
                    try {
                        Constructor<?> declaredConstructor2 = cls.getDeclaredConstructor(new Class[0]);
                        declaredConstructor2.setAccessible(true);
                        objCast = cls.cast(declaredConstructor2.newInstance(new Object[0]));
                    } catch (Throwable th) {
                        dh.a("ReflectUtil", th.getMessage());
                    }
                    objNewInstance = objCast;
                } else {
                    objNewInstance = declaredConstructor.newInstance(context);
                }
                if (z) {
                    this.a.put(canonicalName, objNewInstance);
                }
            } catch (Throwable th2) {
                dh.f("foundation.spi", "spi create service instance failed:", th2);
            }
            return objNewInstance;
        }
    }
}
