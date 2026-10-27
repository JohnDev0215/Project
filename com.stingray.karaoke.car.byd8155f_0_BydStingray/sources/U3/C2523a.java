package u3;

import A9.k;
import android.content.Context;
import android.os.Bundle;
import android.os.Trace;
import com.stingray.drivemodedetector.R;
import h6.e;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import v3.AbstractC2547a;

/* JADX INFO: renamed from: u3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2523a {
    public static volatile C2523a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f22919e = new Object();
    public final Context c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f22921b = new HashSet();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f22920a = new HashMap();

    public C2523a(Context context) {
        this.c = context.getApplicationContext();
    }

    public static C2523a c(Context context) {
        if (d == null) {
            synchronized (f22919e) {
                try {
                    if (d == null) {
                        d = new C2523a(context);
                    }
                } finally {
                }
            }
        }
        return d;
    }

    public final void a(Bundle bundle) {
        HashSet hashSet;
        String string = this.c.getString(R.string.androidx_startup);
        if (bundle != null) {
            try {
                HashSet hashSet2 = new HashSet();
                Iterator<String> it = bundle.keySet().iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    hashSet = this.f22921b;
                    if (!zHasNext) {
                        break;
                    }
                    String next = it.next();
                    if (string.equals(bundle.getString(next, null))) {
                        Class<?> cls = Class.forName(next);
                        if (InterfaceC2524b.class.isAssignableFrom(cls)) {
                            hashSet.add(cls);
                        }
                    }
                }
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    b((Class) it2.next(), hashSet2);
                }
            } catch (ClassNotFoundException e10) {
                throw new k(e10);
            }
        }
    }

    public final Object b(Class cls, HashSet hashSet) {
        Object objB;
        if (AbstractC2547a.a()) {
            try {
                e.f(cls.getSimpleName());
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        if (hashSet.contains(cls)) {
            throw new IllegalStateException("Cannot initialize " + cls.getName() + ". Cycle detected.");
        }
        HashMap map = this.f22920a;
        if (map.containsKey(cls)) {
            objB = map.get(cls);
        } else {
            hashSet.add(cls);
            try {
                InterfaceC2524b interfaceC2524b = (InterfaceC2524b) cls.getDeclaredConstructor(null).newInstance(null);
                List<Class> listA = interfaceC2524b.a();
                if (!listA.isEmpty()) {
                    for (Class cls2 : listA) {
                        if (!map.containsKey(cls2)) {
                            b(cls2, hashSet);
                        }
                    }
                }
                objB = interfaceC2524b.b(this.c);
                hashSet.remove(cls);
                map.put(cls, objB);
            } catch (Throwable th2) {
                throw new k(th2);
            }
        }
        Trace.endSection();
        return objB;
    }
}
