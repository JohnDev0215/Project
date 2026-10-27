package org.greenrobot.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile c f3290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final d f3291b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Map<Class<?>, List<Class<?>>> f3292c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, CopyOnWriteArrayList<q>> f3293d;
    private final Map<Object, List<Class<?>>> e;
    private final Map<Class<?>, Object> f;
    private final ThreadLocal<C0117c> g;
    private final h h;
    private final l i;
    private final org.greenrobot.eventbus.b j;
    private final org.greenrobot.eventbus.a k;
    private final p l;
    private final ExecutorService m;
    private final boolean n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final int t;
    private final g u;

    class a extends ThreadLocal<C0117c> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0117c initialValue() {
            return new C0117c();
        }
    }

    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3295a;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            f3295a = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3295a[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3295a[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3295a[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3295a[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: org.greenrobot.eventbus.c$c, reason: collision with other inner class name */
    static final class C0117c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<Object> f3296a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f3297b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3298c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        q f3299d;
        Object e;
        boolean f;

        C0117c() {
        }
    }

    public c() {
        this(f3291b);
    }

    c(d dVar) {
        this.g = new a();
        this.u = dVar.b();
        this.f3293d = new HashMap();
        this.e = new HashMap();
        this.f = new ConcurrentHashMap();
        h hVarC = dVar.c();
        this.h = hVarC;
        this.i = hVarC != null ? hVarC.a(this) : null;
        this.j = new org.greenrobot.eventbus.b(this);
        this.k = new org.greenrobot.eventbus.a(this);
        List<org.greenrobot.eventbus.s.b> list = dVar.k;
        this.t = list != null ? list.size() : 0;
        this.l = new p(dVar.k, dVar.i, dVar.h);
        this.o = dVar.f3301b;
        this.p = dVar.f3302c;
        this.q = dVar.f3303d;
        this.r = dVar.e;
        this.n = dVar.f;
        this.s = dVar.g;
        this.m = dVar.j;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    private void b(q qVar, Object obj) {
        if (obj != null) {
            n(qVar, obj, i());
        }
    }

    public static c c() {
        c cVar = f3290a;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = f3290a;
                if (cVar == null) {
                    cVar = new c();
                    f3290a = cVar;
                }
            }
        }
        return cVar;
    }

    private void f(q qVar, Object obj, Throwable th) {
        if (!(obj instanceof n)) {
            if (this.n) {
                throw new e("Invoking subscriber failed", th);
            }
            if (this.o) {
                this.u.b(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + qVar.f3332a.getClass(), th);
            }
            if (this.q) {
                k(new n(this, th, obj, qVar.f3332a));
                return;
            }
            return;
        }
        if (this.o) {
            g gVar = this.u;
            Level level = Level.SEVERE;
            gVar.b(level, "SubscriberExceptionEvent subscriber " + qVar.f3332a.getClass() + " threw an exception", th);
            n nVar = (n) obj;
            this.u.b(level, "Initial event " + nVar.f3318c + " caused exception in " + nVar.f3319d, nVar.f3317b);
        }
    }

    private boolean i() {
        h hVar = this.h;
        return hVar == null || hVar.b();
    }

    private static List<Class<?>> j(Class<?> cls) {
        List<Class<?>> arrayList;
        Map<Class<?>, List<Class<?>>> map = f3292c;
        synchronized (map) {
            arrayList = map.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    arrayList.add(superclass);
                    a(arrayList, superclass.getInterfaces());
                }
                f3292c.put(cls, arrayList);
            }
        }
        return arrayList;
    }

    private void l(Object obj, C0117c c0117c) {
        boolean zM;
        Class<?> cls = obj.getClass();
        if (this.s) {
            List<Class<?>> listJ = j(cls);
            int size = listJ.size();
            zM = false;
            for (int i = 0; i < size; i++) {
                zM |= m(obj, c0117c, listJ.get(i));
            }
        } else {
            zM = m(obj, c0117c, cls);
        }
        if (zM) {
            return;
        }
        if (this.p) {
            this.u.a(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.r || cls == i.class || cls == n.class) {
            return;
        }
        k(new i(this, obj));
    }

    private boolean m(Object obj, C0117c c0117c, Class<?> cls) {
        CopyOnWriteArrayList<q> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.f3293d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (q qVar : copyOnWriteArrayList) {
            c0117c.e = obj;
            c0117c.f3299d = qVar;
            try {
                n(qVar, obj, c0117c.f3298c);
                if (c0117c.f) {
                    return true;
                }
            } finally {
                c0117c.e = null;
                c0117c.f3299d = null;
                c0117c.f = false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        if (r5 != null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void n(org.greenrobot.eventbus.q r3, java.lang.Object r4, boolean r5) {
        /*
            r2 = this;
            int[] r0 = org.greenrobot.eventbus.c.b.f3295a
            org.greenrobot.eventbus.o r1 = r3.f3333b
            org.greenrobot.eventbus.ThreadMode r1 = r1.f3321b
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L52
            r1 = 2
            if (r0 == r1) goto L49
            r1 = 3
            if (r0 == r1) goto L44
            r1 = 4
            if (r0 == r1) goto L3c
            r5 = 5
            if (r0 != r5) goto L21
            org.greenrobot.eventbus.a r5 = r2.k
            r5.a(r3, r4)
            goto L55
        L21:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Unknown thread mode: "
            r5.append(r0)
            org.greenrobot.eventbus.o r3 = r3.f3333b
            org.greenrobot.eventbus.ThreadMode r3 = r3.f3321b
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r4.<init>(r3)
            throw r4
        L3c:
            if (r5 == 0) goto L52
            org.greenrobot.eventbus.b r5 = r2.j
            r5.a(r3, r4)
            goto L55
        L44:
            org.greenrobot.eventbus.l r5 = r2.i
            if (r5 == 0) goto L52
            goto L4e
        L49:
            if (r5 == 0) goto L4c
            goto L52
        L4c:
            org.greenrobot.eventbus.l r5 = r2.i
        L4e:
            r5.a(r3, r4)
            goto L55
        L52:
            r2.h(r3, r4)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.eventbus.c.n(org.greenrobot.eventbus.q, java.lang.Object, boolean):void");
    }

    private void p(Object obj, o oVar) {
        Class<?> cls = oVar.f3322c;
        q qVar = new q(obj, oVar);
        CopyOnWriteArrayList<q> copyOnWriteArrayList = this.f3293d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.f3293d.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(qVar)) {
            throw new e("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || oVar.f3323d > copyOnWriteArrayList.get(i).f3333b.f3323d) {
                copyOnWriteArrayList.add(i, qVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.e.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.e.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (oVar.e) {
            if (!this.s) {
                b(qVar, this.f.get(cls));
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.f.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    b(qVar, entry.getValue());
                }
            }
        }
    }

    private void r(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<q> copyOnWriteArrayList = this.f3293d.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                q qVar = copyOnWriteArrayList.get(i);
                if (qVar.f3332a == obj) {
                    qVar.f3334c = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    ExecutorService d() {
        return this.m;
    }

    public g e() {
        return this.u;
    }

    void g(j jVar) {
        Object obj = jVar.f3311b;
        q qVar = jVar.f3312c;
        j.b(jVar);
        if (qVar.f3334c) {
            h(qVar, obj);
        }
    }

    void h(q qVar, Object obj) {
        try {
            qVar.f3333b.f3320a.invoke(qVar.f3332a, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            f(qVar, obj, e2.getCause());
        }
    }

    public void k(Object obj) {
        C0117c c0117c = this.g.get();
        List<Object> list = c0117c.f3296a;
        list.add(obj);
        if (c0117c.f3297b) {
            return;
        }
        c0117c.f3298c = i();
        c0117c.f3297b = true;
        if (c0117c.f) {
            throw new e("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    l(list.remove(0), c0117c);
                }
            } finally {
                c0117c.f3297b = false;
                c0117c.f3298c = false;
            }
        }
    }

    public void o(Object obj) {
        List<o> listA = this.l.a(obj.getClass());
        synchronized (this) {
            Iterator<o> it = listA.iterator();
            while (it.hasNext()) {
                p(obj, it.next());
            }
        }
    }

    public synchronized void q(Object obj) {
        List<Class<?>> list = this.e.get(obj);
        if (list != null) {
            Iterator<Class<?>> it = list.iterator();
            while (it.hasNext()) {
                r(obj, it.next());
            }
            this.e.remove(obj);
        } else {
            this.u.a(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public String toString() {
        return "EventBus[indexCount=" + this.t + ", eventInheritance=" + this.s + "]";
    }
}
