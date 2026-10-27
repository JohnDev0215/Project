package spi;

import android.os.Looper;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: EventBus.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o3 {
    public static volatile o3 q;
    public static final p3 r = new p3();
    public static final Map<Class<?>, List<Class<?>>> s = new HashMap();
    public final Map<Class<?>, CopyOnWriteArrayList<pa>> a;
    public final Map<Object, List<Class<?>>> b;
    public final Map<Class<?>, Object> c;
    public final ThreadLocal<c> d;
    public final l6 e;
    public final m4 f;
    public final v g;
    public final s h;
    public final oa i;
    public final ExecutorService j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final j6 p;

    /* JADX INFO: compiled from: EventBus.java */
    public class a extends ThreadLocal<c> {
        public a(o3 o3Var) {
        }

        @Override // java.lang.ThreadLocal
        public final c initialValue() {
            return new c();
        }
    }

    /* JADX INFO: compiled from: EventBus.java */
    public static /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            a = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: compiled from: EventBus.java */
    public static final class c {
        public final List<Object> a = new ArrayList();
        public boolean b;
        public boolean c;
        public Object d;
    }

    public o3() {
        this(r);
    }

    public static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    public static o3 b() {
        o3 o3Var = q;
        if (o3Var == null) {
            synchronized (o3.class) {
                o3Var = q;
                if (o3Var == null) {
                    o3Var = new o3();
                    q = o3Var;
                }
            }
        }
        return o3Var;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<spi.g8>] */
    public final void c(g8 g8Var) {
        Object obj = g8Var.a;
        pa paVar = g8Var.b;
        g8Var.a = null;
        g8Var.b = null;
        g8Var.c = null;
        ?? r2 = g8.d;
        synchronized (r2) {
            if (r2.size() < 10000) {
                r2.add(g8Var);
            }
        }
        if (paVar.c) {
            d(paVar, obj);
        }
    }

    public final void d(pa paVar, Object obj) {
        try {
            paVar.b.a.invoke(paVar.a, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (!(obj instanceof ka)) {
                if (this.k) {
                    j6 j6Var = this.p;
                    Level level = Level.SEVERE;
                    StringBuilder sbK = spi.c.k("Could not dispatch event: ");
                    sbK.append(obj.getClass());
                    sbK.append(" to subscribing class ");
                    sbK.append(paVar.a.getClass());
                    j6Var.b(level, sbK.toString(), cause);
                }
                if (this.m) {
                    f(new ka(this, cause, obj, paVar.a));
                    return;
                }
                return;
            }
            if (this.k) {
                j6 j6Var2 = this.p;
                Level level2 = Level.SEVERE;
                StringBuilder sbK2 = spi.c.k("SubscriberExceptionEvent subscriber ");
                sbK2.append(paVar.a.getClass());
                sbK2.append(" threw an exception");
                j6Var2.b(level2, sbK2.toString(), cause);
                ka kaVar = (ka) obj;
                j6 j6Var3 = this.p;
                StringBuilder sbK3 = spi.c.k("Initial event ");
                sbK3.append(kaVar.b);
                sbK3.append(" caused exception in ");
                sbK3.append(kaVar.c);
                j6Var3.b(level2, sbK3.toString(), kaVar.a);
            }
        }
    }

    public final boolean e() {
        l6 l6Var = this.e;
        if (l6Var != null) {
            if (!(l6Var.a == Looper.myLooper())) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List, java.util.List<java.lang.Object>] */
    public final void f(Object obj) {
        c cVar = this.d.get();
        ?? r1 = cVar.a;
        r1.add(obj);
        if (cVar.b) {
            return;
        }
        cVar.c = e();
        cVar.b = true;
        while (true) {
            try {
                if (r1.isEmpty()) {
                    return;
                } else {
                    g(r1.remove(0), cVar);
                }
            } finally {
                cVar.b = false;
                cVar.c = false;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, java.util.List<java.lang.Class<?>>>] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, java.util.List<java.lang.Class<?>>>] */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void g(Object obj, c cVar) {
        boolean zH;
        List list;
        Class<?> cls = obj.getClass();
        if (this.o) {
            ?? r1 = s;
            synchronized (r1) {
                List list2 = (List) r1.get(cls);
                list = list2;
                if (list2 == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                        arrayList.add(superclass);
                        a(arrayList, superclass.getInterfaces());
                    }
                    s.put(cls, arrayList);
                    list = arrayList;
                }
            }
            int size = list.size();
            zH = false;
            for (int i = 0; i < size; i++) {
                zH |= h(obj, cVar, (Class) list.get(i));
            }
        } else {
            zH = h(obj, cVar, cls);
        }
        if (zH) {
            return;
        }
        if (this.l) {
            this.p.a(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.n || cls == a8.class || cls == ka.class) {
            return;
        }
        f(new a8(this, obj));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, java.util.concurrent.CopyOnWriteArrayList<spi.pa>>] */
    public final boolean h(Object obj, c cVar, Class<?> cls) {
        CopyOnWriteArrayList<pa> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = (CopyOnWriteArrayList) this.a.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (pa paVar : copyOnWriteArrayList) {
            cVar.d = obj;
            i(paVar, obj, cVar.c);
        }
        return true;
    }

    public final void i(pa paVar, Object obj, boolean z) {
        int i = b.a[paVar.b.b.ordinal()];
        if (i == 1) {
            d(paVar, obj);
            return;
        }
        if (i == 2) {
            if (z) {
                d(paVar, obj);
                return;
            } else {
                this.f.a(paVar, obj);
                return;
            }
        }
        if (i == 3) {
            m4 m4Var = this.f;
            if (m4Var != null) {
                m4Var.a(paVar, obj);
                return;
            } else {
                d(paVar, obj);
                return;
            }
        }
        if (i == 4) {
            if (z) {
                this.g.a(paVar, obj);
                return;
            } else {
                d(paVar, obj);
                return;
            }
        }
        if (i == 5) {
            this.h.a(paVar, obj);
        } else {
            StringBuilder sbK = spi.c.k("Unknown thread mode: ");
            sbK.append(paVar.b.b);
            throw new IllegalStateException(sbK.toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005d  */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.Map<java.lang.Class<?>, java.util.List<spi.na>>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.Map<java.lang.Class<?>, java.util.List<spi.na>>, java.util.concurrent.ConcurrentHashMap] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.util.ArrayList, java.util.List<spi.na>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o3.j(java.lang.Object):void");
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public final void k(Object obj, na naVar) {
        Object value;
        Class<?> cls = naVar.c;
        pa paVar = new pa(obj, naVar);
        CopyOnWriteArrayList<pa> copyOnWriteArrayList = this.a.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.a.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(paVar)) {
            StringBuilder sbK = spi.c.k("Subscriber ");
            sbK.append(obj.getClass());
            sbK.append(" already registered to event ");
            sbK.append(cls);
            throw new q3(sbK.toString());
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || naVar.d > copyOnWriteArrayList.get(i).b.d) {
                copyOnWriteArrayList.add(i, paVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.b.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.b.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (naVar.e) {
            if (!this.o) {
                Object obj2 = this.c.get(cls);
                if (obj2 != null) {
                    i(paVar, obj2, e());
                    return;
                }
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.c.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey()) && (value = entry.getValue()) != null) {
                    i(paVar, value, e());
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<java.lang.Object, java.util.List<java.lang.Class<?>>>] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.HashMap, java.util.Map<java.lang.Object, java.util.List<java.lang.Class<?>>>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<java.lang.Class<?>, java.util.concurrent.CopyOnWriteArrayList<spi.pa>>] */
    public final synchronized void l(Object obj) {
        List list = (List) this.b.get(obj);
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                List list2 = (List) this.a.get((Class) it.next());
                if (list2 != null) {
                    int size = list2.size();
                    int i = 0;
                    while (i < size) {
                        pa paVar = (pa) list2.get(i);
                        if (paVar.a == obj) {
                            paVar.c = false;
                            list2.remove(i);
                            i--;
                            size--;
                        }
                        i++;
                    }
                }
            }
            this.b.remove(obj);
        } else {
            this.p.a(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public final String toString() {
        StringBuilder sbL = spi.c.l("EventBus[indexCount=", 0, ", eventInheritance=");
        sbL.append(this.o);
        sbL.append("]");
        return sbL.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public o3(spi.p3 r5) {
        /*
            r4 = this;
            r4.<init>()
            spi.o3$a r0 = new spi.o3$a
            r0.<init>(r4)
            r4.d = r0
            java.util.Objects.requireNonNull(r5)
            boolean r0 = spi.j.b
            r1 = 0
            if (r0 == 0) goto L22
            android.os.Looper r0 = android.os.Looper.getMainLooper()     // Catch: java.lang.RuntimeException -> L17
            goto L18
        L17:
            r0 = r1
        L18:
            if (r0 == 0) goto L22
            spi.j r0 = new spi.j
            java.lang.String r2 = "EventBus"
            r0.<init>(r2)
            goto L27
        L22:
            spi.j6$a r0 = new spi.j6$a
            r0.<init>()
        L27:
            r4.p = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r4.a = r0
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r4.b = r0
            java.util.concurrent.ConcurrentHashMap r0 = new java.util.concurrent.ConcurrentHashMap
            r0.<init>()
            r4.c = r0
            boolean r0 = spi.j.b
            if (r0 == 0) goto L51
            android.os.Looper r0 = android.os.Looper.getMainLooper()     // Catch: java.lang.RuntimeException -> L47
            goto L48
        L47:
            r0 = r1
        L48:
            if (r0 != 0) goto L4b
            goto L51
        L4b:
            spi.l6 r2 = new spi.l6
            r2.<init>(r0)
            goto L52
        L51:
            r2 = r1
        L52:
            r4.e = r2
            if (r2 == 0) goto L60
            spi.m4 r0 = new spi.m4
            android.os.Looper r2 = r2.a
            r3 = 10
            r0.<init>(r4, r2, r3)
            goto L61
        L60:
            r0 = r1
        L61:
            r4.f = r0
            spi.v r0 = new spi.v
            r0.<init>(r4)
            r4.g = r0
            spi.s r0 = new spi.s
            r0.<init>(r4)
            r4.h = r0
            r0 = 0
            spi.oa r2 = new spi.oa
            r2.<init>(r1, r0, r0)
            r4.i = r2
            boolean r0 = r5.a
            r4.k = r0
            boolean r0 = r5.b
            r4.l = r0
            boolean r0 = r5.c
            r4.m = r0
            boolean r0 = r5.d
            r4.n = r0
            boolean r0 = r5.e
            r4.o = r0
            java.util.concurrent.ExecutorService r5 = r5.f
            r4.j = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o3.<init>(spi.p3):void");
    }
}
