package f.a.a;

import android.os.Looper;
import f.a.a.h;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static volatile c s;
    public static final d t = new d();
    public static final Map<Class<?>, List<Class<?>>> u = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Class<?>, CopyOnWriteArrayList<q>> f2539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<Object, List<Class<?>>> f2540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<Class<?>, Object> f2541c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ThreadLocal<b> f2542d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f2543e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f2544f;
    public final f.a.a.b g;
    public final f.a.a.a h;
    public final p i;
    public final ExecutorService j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final int q;
    public final g r;

    public class a extends ThreadLocal<b> {
        public a(c cVar) {
        }

        @Override // java.lang.ThreadLocal
        public b initialValue() {
            return new b();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Object> f2545a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2546b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f2547c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Object f2548d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f2549e;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c() {
        /*
            r5 = this;
            f.a.a.d r0 = f.a.a.c.t
            r5.<init>()
            f.a.a.c$a r1 = new f.a.a.c$a
            r1.<init>(r5)
            r5.f2542d = r1
            r1 = 0
            if (r0 == 0) goto La6
            boolean r2 = f.a.a.s.a.f2597b
            if (r2 == 0) goto L23
            android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch: java.lang.RuntimeException -> L18
            goto L19
        L18:
            r2 = r1
        L19:
            if (r2 == 0) goto L23
            f.a.a.s.a r2 = new f.a.a.s.a
            java.lang.String r3 = "EventBus"
            r2.<init>(r3)
            goto L28
        L23:
            f.a.a.g$a r2 = new f.a.a.g$a
            r2.<init>()
        L28:
            r5.r = r2
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r5.f2539a = r2
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r5.f2540b = r2
            java.util.concurrent.ConcurrentHashMap r2 = new java.util.concurrent.ConcurrentHashMap
            r2.<init>()
            r5.f2541c = r2
            boolean r2 = f.a.a.s.a.f2597b
            if (r2 == 0) goto L52
            android.os.Looper r2 = android.os.Looper.getMainLooper()     // Catch: java.lang.RuntimeException -> L48
            goto L49
        L48:
            r2 = r1
        L49:
            if (r2 != 0) goto L4c
            goto L52
        L4c:
            f.a.a.h$a r3 = new f.a.a.h$a
            r3.<init>(r2)
            goto L53
        L52:
            r3 = r1
        L53:
            r5.f2543e = r3
            if (r3 == 0) goto L60
            f.a.a.f r1 = new f.a.a.f
            android.os.Looper r2 = r3.f2560a
            r3 = 10
            r1.<init>(r5, r2, r3)
        L60:
            r5.f2544f = r1
            f.a.a.b r1 = new f.a.a.b
            r1.<init>(r5)
            r5.g = r1
            f.a.a.a r1 = new f.a.a.a
            r1.<init>(r5)
            r5.h = r1
            java.util.List<f.a.a.t.b> r1 = r0.j
            if (r1 == 0) goto L79
            int r1 = r1.size()
            goto L7a
        L79:
            r1 = 0
        L7a:
            r5.q = r1
            f.a.a.p r1 = new f.a.a.p
            java.util.List<f.a.a.t.b> r2 = r0.j
            boolean r3 = r0.h
            boolean r4 = r0.g
            r1.<init>(r2, r3, r4)
            r5.i = r1
            boolean r1 = r0.f2550a
            r5.l = r1
            boolean r1 = r0.f2551b
            r5.m = r1
            boolean r1 = r0.f2552c
            r5.n = r1
            boolean r1 = r0.f2553d
            r5.o = r1
            boolean r1 = r0.f2554e
            r5.k = r1
            boolean r1 = r0.f2555f
            r5.p = r1
            java.util.concurrent.ExecutorService r0 = r0.i
            r5.j = r0
            return
        La6:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: f.a.a.c.<init>():void");
    }

    public static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    public static c b() {
        c cVar = s;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = s;
                if (cVar == null) {
                    cVar = new c();
                    s = cVar;
                }
            }
        }
        return cVar;
    }

    public void c(j jVar) {
        Object obj = jVar.f2563a;
        q qVar = jVar.f2564b;
        jVar.f2563a = null;
        jVar.f2564b = null;
        jVar.f2565c = null;
        synchronized (j.f2562d) {
            if (j.f2562d.size() < 10000) {
                j.f2562d.add(jVar);
            }
        }
        if (qVar.f2590c) {
            d(qVar, obj);
        }
    }

    public void d(q qVar, Object obj) {
        try {
            qVar.f2589b.f2571a.invoke(qVar.f2588a, obj);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (!(obj instanceof n)) {
                if (this.k) {
                    throw new e("Invoking subscriber failed", cause);
                }
                if (this.l) {
                    g gVar = this.r;
                    Level level = Level.SEVERE;
                    StringBuilder sbD = b.a.a.a.a.d("Could not dispatch event: ");
                    sbD.append(obj.getClass());
                    sbD.append(" to subscribing class ");
                    sbD.append(qVar.f2588a.getClass());
                    gVar.b(level, sbD.toString(), cause);
                }
                if (this.n) {
                    f(new n(this, cause, obj, qVar.f2588a));
                    return;
                }
                return;
            }
            if (this.l) {
                g gVar2 = this.r;
                Level level2 = Level.SEVERE;
                StringBuilder sbD2 = b.a.a.a.a.d("SubscriberExceptionEvent subscriber ");
                sbD2.append(qVar.f2588a.getClass());
                sbD2.append(" threw an exception");
                gVar2.b(level2, sbD2.toString(), cause);
                n nVar = (n) obj;
                g gVar3 = this.r;
                Level level3 = Level.SEVERE;
                StringBuilder sbD3 = b.a.a.a.a.d("Initial event ");
                sbD3.append(nVar.f2569b);
                sbD3.append(" caused exception in ");
                sbD3.append(nVar.f2570c);
                gVar3.b(level3, sbD3.toString(), nVar.f2568a);
            }
        }
    }

    public final boolean e() {
        h hVar = this.f2543e;
        if (hVar != null) {
            if (!(((h.a) hVar).f2560a == Looper.myLooper())) {
                return false;
            }
        }
        return true;
    }

    public void f(Object obj) {
        b bVar = this.f2542d.get();
        List<Object> list = bVar.f2545a;
        list.add(obj);
        if (bVar.f2546b) {
            return;
        }
        bVar.f2547c = e();
        bVar.f2546b = true;
        if (bVar.f2549e) {
            throw new e("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    g(list.remove(0), bVar);
                }
            } finally {
                bVar.f2546b = false;
                bVar.f2547c = false;
            }
        }
    }

    public final void g(Object obj, b bVar) {
        boolean zH;
        List list;
        Class<?> cls = obj.getClass();
        if (this.p) {
            synchronized (u) {
                List<Class<?>> list2 = u.get(cls);
                list = list2;
                if (list2 == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                        arrayList.add(superclass);
                        a(arrayList, superclass.getInterfaces());
                    }
                    u.put(cls, arrayList);
                    list = arrayList;
                }
            }
            int size = list.size();
            zH = false;
            for (int i = 0; i < size; i++) {
                zH |= h(obj, bVar, (Class) list.get(i));
            }
        } else {
            zH = h(obj, bVar, cls);
        }
        if (zH) {
            return;
        }
        if (this.m) {
            this.r.a(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.o || cls == i.class || cls == n.class) {
            return;
        }
        f(new i(this, obj));
    }

    public final boolean h(Object obj, b bVar, Class<?> cls) {
        CopyOnWriteArrayList<q> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.f2539a.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (q qVar : copyOnWriteArrayList) {
            bVar.f2548d = obj;
            try {
                i(qVar, obj, bVar.f2547c);
                if (bVar.f2549e) {
                    return true;
                }
            } finally {
                bVar.f2549e = false;
            }
        }
        return true;
    }

    public final void i(q qVar, Object obj, boolean z) {
        int iOrdinal = qVar.f2589b.f2572b.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                if (iOrdinal == 2) {
                    l lVar = this.f2544f;
                    if (lVar != null) {
                        lVar.a(qVar, obj);
                        return;
                    }
                } else {
                    if (iOrdinal != 3) {
                        if (iOrdinal == 4) {
                            this.h.a(qVar, obj);
                            return;
                        } else {
                            StringBuilder sbD = b.a.a.a.a.d("Unknown thread mode: ");
                            sbD.append(qVar.f2589b.f2572b);
                            throw new IllegalStateException(sbD.toString());
                        }
                    }
                    if (z) {
                        this.g.a(qVar, obj);
                        return;
                    }
                }
            } else if (!z) {
                this.f2544f.a(qVar, obj);
                return;
            }
        }
        d(qVar, obj);
    }

    public final void j(Object obj, o oVar) {
        Object value;
        Class<?> cls = oVar.f2573c;
        q qVar = new q(obj, oVar);
        CopyOnWriteArrayList<q> copyOnWriteArrayList = this.f2539a.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.f2539a.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(qVar)) {
            StringBuilder sbD = b.a.a.a.a.d("Subscriber ");
            sbD.append(obj.getClass());
            sbD.append(" already registered to event ");
            sbD.append(cls);
            throw new e(sbD.toString());
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || oVar.f2574d > copyOnWriteArrayList.get(i).f2589b.f2574d) {
                copyOnWriteArrayList.add(i, qVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.f2540b.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f2540b.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (oVar.f2575e) {
            if (!this.p) {
                Object obj2 = this.f2541c.get(cls);
                if (obj2 != null) {
                    i(qVar, obj2, e());
                    return;
                }
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.f2541c.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey()) && (value = entry.getValue()) != null) {
                    i(qVar, value, e());
                }
            }
        }
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("EventBus[indexCount=");
        sbD.append(this.q);
        sbD.append(", eventInheritance=");
        sbD.append(this.p);
        sbD.append("]");
        return sbD.toString();
    }
}
