package l2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import g2.o;
import g2.t;
import h2.a1;
import h2.b1;
import h2.c1;
import h2.d1;
import h2.g1;
import h2.i0;
import h2.j0;
import h2.l0;
import h2.m0;
import h2.r0;
import h2.t0;
import h2.u;
import h2.v;
import h2.v0;
import h2.w0;
import h2.y0;
import h2.z0;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ASMClassLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends ClassLoader {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<String, Class<?>> f6166b = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ProtectionDomain f6165a = (ProtectionDomain) AccessController.doPrivileged(new C0081a());

    /* JADX INFO: renamed from: l2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMClassLoader.java */
    public static class C0081a implements PrivilegedAction<Object> {
        @Override // java.security.PrivilegedAction
        public Object run() {
            return a.class.getProtectionDomain();
        }
    }

    static {
        Class<?>[] clsArr = {c2.a.class, JSONObject.class, JSONArray.class, JSONPath.class, c2.b.class, JSONException.class, JSONPathException.class, c2.d.class, c2.e.class, c2.f.class, c2.g.class, c.class, l.class, e.class, f.class, h.class, g.class, t0.class, j0.class, c1.class, z0.class, i0.class, d1.class, b1.class, m0.class, l0.class, v.class, h2.c.class, h2.k.class, r0.class, v0.class, w0.class, g1.class, SerializerFeature.class, u.class, y0.class, a1.class, o.class, f2.h.class, f2.a.class, f2.b.class, f2.c.class, f2.g.class, f2.f.class, f2.i.class, Feature.class, f2.e.class, f2.d.class, g2.d.class, t.class, g2.j.class, g2.i.class, g2.k.class, h2.j.class, g2.l.class, g2.f.class};
        for (int i8 = 0; i8 < 56; i8++) {
            Class<?> cls = clsArr[i8];
            f6166b.put(cls.getName(), cls);
        }
    }

    public a() {
        super(b());
    }

    public static ClassLoader b() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                contextClassLoader.loadClass(c2.a.class.getName());
                return contextClassLoader;
            } catch (ClassNotFoundException unused) {
            }
        }
        return c2.a.class.getClassLoader();
    }

    public Class<?> a(String str, byte[] bArr, int i8, int i9) throws ClassFormatError {
        return defineClass(str, bArr, i8, i9, f6165a);
    }

    public boolean c(Class<?> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            return false;
        }
        for (ClassLoader parent = this; parent != null; parent = parent.getParent()) {
            if (parent == classLoader) {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.ClassLoader
    public Class<?> loadClass(String str, boolean z7) throws ClassNotFoundException {
        Class<?> cls = f6166b.get(str);
        if (cls != null) {
            return cls;
        }
        try {
            return super.loadClass(str, z7);
        } catch (ClassNotFoundException e8) {
            throw e8;
        }
    }

    public a(ClassLoader classLoader) {
        super(classLoader);
    }
}
