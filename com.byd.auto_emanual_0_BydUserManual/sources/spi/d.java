package spi;

import android.content.Context;
import com.byd.foundation.annotation.ServiceImpl;
import com.byd.foundation.utils.LogUtils;
import com.byd.foundation.utils.ObjectUtils;
import com.byd.foundation.utils.ReflectUtil;
import com.byd.foundation.utils.Singleton;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ServiceFactory.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    private static Singleton<d> b = new a();
    private final Map<String, Object> a;

    /* JADX INFO: compiled from: ServiceFactory.java */
    public static class a extends Singleton<d> {
        @Override // com.byd.foundation.utils.Singleton
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d create() {
            return new d(null);
        }
    }

    public /* synthetic */ d(a aVar) {
        this();
    }

    public static d a() {
        return b.get();
    }

    private boolean b(Class<?> cls) {
        ServiceImpl serviceImpl = (ServiceImpl) cls.getAnnotation(ServiceImpl.class);
        return serviceImpl != null && serviceImpl.singleton();
    }

    private d() {
        this.a = new HashMap();
    }

    public Object a(Context context, Class<?> cls) {
        String canonicalName = cls.getCanonicalName();
        boolean zB = b(cls);
        synchronized (this) {
            Object objNewInstance = this.a.get(canonicalName);
            if (zB && objNewInstance != null) {
                return objNewInstance;
            }
            try {
                Constructor<?> constructorA = a(cls);
                objNewInstance = !ObjectUtils.isEmpty(constructorA) ? constructorA.newInstance(context) : ReflectUtil.newInstance(cls);
                if (zB) {
                    this.a.put(canonicalName, objNewInstance);
                }
            } catch (Throwable th) {
                LogUtils.w(spi.a.e, "spi create service instance failed:", th);
            }
            return objNewInstance;
        }
    }

    private Constructor<?> a(Class<?> cls) {
        try {
            return cls.getDeclaredConstructor(Context.class);
        } catch (Throwable unused) {
            return null;
        }
    }
}
