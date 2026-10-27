package io.sentry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: io.sentry.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1523x {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final HashMap f16981g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f16982a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f16983b = new ArrayList();
    public C1422a c = null;
    public C1422a d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C1422a f16984e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public L0 f16985f = null;

    static {
        HashMap map = new HashMap();
        f16981g = map;
        map.put("boolean", Boolean.class);
        map.put("char", Character.class);
        map.put("byte", Byte.class);
        map.put("short", Short.class);
        map.put("int", Integer.class);
        map.put("long", Long.class);
        map.put("float", Float.class);
        map.put("double", Double.class);
    }

    public final synchronized void a() {
        try {
            Iterator it = this.f16982a.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey() == null || !((String) entry.getKey()).startsWith("sentry:")) {
                    it.remove();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized Object b(Class cls, String str) {
        Object obj = this.f16982a.get(str);
        if (cls.isInstance(obj)) {
            return obj;
        }
        Class cls2 = (Class) f16981g.get(cls.getCanonicalName());
        if (obj != null && cls.isPrimitive() && cls2 != null) {
            if (cls2.isInstance(obj)) {
                return obj;
            }
        }
        return null;
    }

    public final synchronized void c(Object obj, String str) {
        this.f16982a.put(str, obj);
    }
}
