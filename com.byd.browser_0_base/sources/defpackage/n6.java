package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import androidx.startup.R$string;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: AppInitializer.java */
/* JADX INFO: loaded from: classes.dex */
public final class n6 {
    public static volatile n6 d;
    public static final Object e = new Object();
    public final Context c;
    public final HashSet b = new HashSet();
    public final HashMap a = new HashMap();

    public n6(Context context) {
        this.c = context.getApplicationContext();
    }

    public static n6 c(Context context) {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new n6(context);
                }
            }
        }
        return d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.c.getString(R$string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = this.b;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (a01.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e2) {
                throw new fs2(e2);
            }
        }
    }

    public final Object b(Class cls, HashSet hashSet) {
        Object objB;
        if (Trace.isEnabled()) {
            try {
                Trace.beginSection(cls.getSimpleName());
            } finally {
                Trace.endSection();
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException(String.format("Cannot initialize %s. Cycle detected.", cls.getName()));
        }
        HashMap map = this.a;
        if (map.containsKey(cls)) {
            objB = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                a01 a01Var = (a01) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                List<Class<? extends a01<?>>> listA = a01Var.a();
                if (!listA.isEmpty()) {
                    for (Class<? extends a01<?>> cls2 : listA) {
                        if (!map.containsKey(cls2)) {
                            b(cls2, hashSet);
                        }
                    }
                }
                objB = a01Var.b(this.c);
                hashSet.remove(cls);
                map.put(cls, objB);
            } catch (Throwable th) {
                throw new fs2(th);
            }
        }
        return objB;
    }
}
