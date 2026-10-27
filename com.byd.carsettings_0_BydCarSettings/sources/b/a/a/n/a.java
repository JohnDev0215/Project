package b.a.a.n;

import b.a.a.j.j.o;
import b.a.a.j.j.t;
import b.a.a.k.a1;
import b.a.a.k.b1;
import b.a.a.k.c1;
import b.a.a.k.d1;
import b.a.a.k.g1;
import b.a.a.k.i0;
import b.a.a.k.j0;
import b.a.a.k.l0;
import b.a.a.k.m0;
import b.a.a.k.r0;
import b.a.a.k.t0;
import b.a.a.k.u;
import b.a.a.k.v;
import b.a.a.k.v0;
import b.a.a.k.w0;
import b.a.a.k.y0;
import b.a.a.k.z0;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: ASMClassLoader.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends ClassLoader {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<String, Class<?>> f426b = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static ProtectionDomain f425a = (ProtectionDomain) AccessController.doPrivileged(new C0019a());

    /* JADX INFO: renamed from: b.a.a.n.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ASMClassLoader.java */
    public static class C0019a implements PrivilegedAction<Object> {
        @Override // java.security.PrivilegedAction
        public Object run() {
            return a.class.getProtectionDomain();
        }
    }

    static {
        Class<?>[] clsArr = {b.a.a.a.class, JSONObject.class, JSONArray.class, JSONPath.class, b.a.a.b.class, JSONException.class, JSONPathException.class, b.a.a.d.class, b.a.a.e.class, b.a.a.f.class, b.a.a.g.class, c.class, l.class, e.class, f.class, h.class, g.class, t0.class, j0.class, c1.class, z0.class, i0.class, d1.class, b1.class, m0.class, l0.class, v.class, b.a.a.k.c.class, b.a.a.k.k.class, r0.class, v0.class, w0.class, g1.class, SerializerFeature.class, u.class, y0.class, a1.class, o.class, b.a.a.j.h.class, b.a.a.j.a.class, b.a.a.j.b.class, b.a.a.j.c.class, b.a.a.j.g.class, b.a.a.j.f.class, b.a.a.j.i.class, Feature.class, b.a.a.j.e.class, b.a.a.j.d.class, b.a.a.j.j.d.class, t.class, b.a.a.j.j.j.class, b.a.a.j.j.i.class, b.a.a.j.j.k.class, b.a.a.k.j.class, b.a.a.j.j.l.class, b.a.a.j.j.f.class};
        for (int i = 0; i < 56; i++) {
            Class<?> cls = clsArr[i];
            f426b.put(cls.getName(), cls);
        }
    }

    public a() {
        super(b());
    }

    public static ClassLoader b() {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                contextClassLoader.loadClass(b.a.a.a.class.getName());
                return contextClassLoader;
            } catch (ClassNotFoundException unused) {
            }
        }
        return b.a.a.a.class.getClassLoader();
    }

    public Class<?> a(String str, byte[] bArr, int i, int i2) throws ClassFormatError {
        return defineClass(str, bArr, i, i2, f425a);
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
    public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
        Class<?> cls = f426b.get(str);
        if (cls != null) {
            return cls;
        }
        try {
            return super.loadClass(str, z);
        } catch (ClassNotFoundException e2) {
            throw e2;
        }
    }

    public a(ClassLoader classLoader) {
        super(classLoader);
    }
}
