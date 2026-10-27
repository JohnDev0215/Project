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

/* JADX INFO: compiled from: EventBus.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static volatile c f4675a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final d f4676b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Map<Class<?>, List<Class<?>>> f4677c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<Class<?>, CopyOnWriteArrayList<p>> f4678d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Map<Object, List<Class<?>>> f4679e;
    private final Map<Class<?>, Object> f;
    private final ThreadLocal<C0117c> g;
    private final g h;
    private final k i;
    private final org.greenrobot.eventbus.b j;
    private final org.greenrobot.eventbus.a k;
    private final o l;
    private final ExecutorService m;
    private final boolean n;
    private final boolean o;
    private final boolean p;
    private final boolean q;
    private final boolean r;
    private final boolean s;
    private final int t;
    private final f u;

    /* JADX INFO: compiled from: EventBus.java */
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

    /* JADX INFO: compiled from: EventBus.java */
    static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4681a;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            f4681a = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4681a[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4681a[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4681a[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4681a[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: org.greenrobot.eventbus.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EventBus.java */
    static final class C0117c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final List<Object> f4682a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4683b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4684c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        p f4685d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Object f4686e;
        boolean f;

        C0117c() {
        }
    }

    public c() {
        this(f4676b);
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    private void b(p pVar, Object obj) {
        if (obj != null) {
            n(pVar, obj, i());
        }
    }

    public static c c() {
        c cVar = f4675a;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = f4675a;
                if (cVar == null) {
                    cVar = new c();
                    f4675a = cVar;
                }
            }
        }
        return cVar;
    }

    private void f(p pVar, Object obj, Throwable th) {
        if (!(obj instanceof m)) {
            if (this.n) {
                throw new EventBusException("Invoking subscriber failed", th);
            }
            if (this.o) {
                this.u.b(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + pVar.f4724a.getClass(), th);
            }
            if (this.q) {
                k(new m(this, th, obj, pVar.f4724a));
                return;
            }
            return;
        }
        if (this.o) {
            f fVar = this.u;
            Level level = Level.SEVERE;
            fVar.b(level, "SubscriberExceptionEvent subscriber " + pVar.f4724a.getClass() + " threw an exception", th);
            m mVar = (m) obj;
            this.u.b(level, "Initial event " + mVar.f4707c + " caused exception in " + mVar.f4708d, mVar.f4706b);
        }
    }

    private boolean i() {
        g gVar = this.h;
        return gVar == null || gVar.b();
    }

    private static List<Class<?>> j(Class<?> cls) {
        List<Class<?>> arrayList;
        Map<Class<?>, List<Class<?>>> map = f4677c;
        synchronized (map) {
            arrayList = map.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    arrayList.add(superclass);
                    a(arrayList, superclass.getInterfaces());
                }
                f4677c.put(cls, arrayList);
            }
        }
        return arrayList;
    }

    private void l(Object obj, C0117c c0117c) throws Error {
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
        if (!this.r || cls == h.class || cls == m.class) {
            return;
        }
        k(new h(this, obj));
    }

    private boolean m(Object obj, C0117c c0117c, Class<?> cls) {
        CopyOnWriteArrayList<p> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.f4678d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (p pVar : copyOnWriteArrayList) {
            c0117c.f4686e = obj;
            c0117c.f4685d = pVar;
            try {
                n(pVar, obj, c0117c.f4684c);
                if (c0117c.f) {
                    return true;
                }
            } finally {
                c0117c.f4686e = null;
                c0117c.f4685d = null;
                c0117c.f = false;
            }
        }
        return true;
    }

    private void n(p pVar, Object obj, boolean z) {
        int i = b.f4681a[pVar.f4725b.f4710b.ordinal()];
        if (i == 1) {
            h(pVar, obj);
            return;
        }
        if (i == 2) {
            if (z) {
                h(pVar, obj);
                return;
            } else {
                this.i.a(pVar, obj);
                return;
            }
        }
        if (i == 3) {
            k kVar = this.i;
            if (kVar != null) {
                kVar.a(pVar, obj);
                return;
            } else {
                h(pVar, obj);
                return;
            }
        }
        if (i == 4) {
            if (z) {
                this.j.a(pVar, obj);
                return;
            } else {
                h(pVar, obj);
                return;
            }
        }
        if (i == 5) {
            this.k.a(pVar, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + pVar.f4725b.f4710b);
    }

    private void p(Object obj, n nVar) {
        Class<?> cls = nVar.f4711c;
        p pVar = new p(obj, nVar);
        CopyOnWriteArrayList<p> copyOnWriteArrayList = this.f4678d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.f4678d.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(pVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || nVar.f4712d > copyOnWriteArrayList.get(i).f4725b.f4712d) {
                copyOnWriteArrayList.add(i, pVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.f4679e.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f4679e.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (nVar.f4713e) {
            if (!this.s) {
                b(pVar, this.f.get(cls));
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.f.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    b(pVar, entry.getValue());
                }
            }
        }
    }

    private void r(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<p> copyOnWriteArrayList = this.f4678d.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                p pVar = copyOnWriteArrayList.get(i);
                if (pVar.f4724a == obj) {
                    pVar.f4726c = false;
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

    public f e() {
        return this.u;
    }

    void g(i iVar) {
        Object obj = iVar.f4700b;
        p pVar = iVar.f4701c;
        i.b(iVar);
        if (pVar.f4726c) {
            h(pVar, obj);
        }
    }

    void h(p pVar, Object obj) {
        try {
            pVar.f4725b.f4709a.invoke(pVar.f4724a, obj);
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        } catch (InvocationTargetException e3) {
            f(pVar, obj, e3.getCause());
        }
    }

    public void k(Object obj) {
        C0117c c0117c = this.g.get();
        List<Object> list = c0117c.f4682a;
        list.add(obj);
        if (c0117c.f4683b) {
            return;
        }
        c0117c.f4684c = i();
        c0117c.f4683b = true;
        if (c0117c.f) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    l(list.remove(0), c0117c);
                }
            } finally {
                c0117c.f4683b = false;
                c0117c.f4684c = false;
            }
        }
    }

    public void o(Object obj) {
        List<n> listA = this.l.a(obj.getClass());
        synchronized (this) {
            Iterator<n> it = listA.iterator();
            while (it.hasNext()) {
                p(obj, it.next());
            }
        }
    }

    public synchronized void q(Object obj) {
        List<Class<?>> list = this.f4679e.get(obj);
        if (list != null) {
            Iterator<Class<?>> it = list.iterator();
            while (it.hasNext()) {
                r(obj, it.next());
            }
            this.f4679e.remove(obj);
        } else {
            this.u.a(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public String toString() {
        return "EventBus[indexCount=" + this.t + ", eventInheritance=" + this.s + "]";
    }

    c(d dVar) {
        this.g = new a();
        this.u = dVar.b();
        this.f4678d = new HashMap();
        this.f4679e = new HashMap();
        this.f = new ConcurrentHashMap();
        g gVarC = dVar.c();
        this.h = gVarC;
        this.i = gVarC != null ? gVarC.a(this) : null;
        this.j = new org.greenrobot.eventbus.b(this);
        this.k = new org.greenrobot.eventbus.a(this);
        List<org.greenrobot.eventbus.r.b> list = dVar.k;
        this.t = list != null ? list.size() : 0;
        this.l = new o(dVar.k, dVar.i, dVar.h);
        this.o = dVar.f4688b;
        this.p = dVar.f4689c;
        this.q = dVar.f4690d;
        this.r = dVar.f4691e;
        this.n = dVar.f;
        this.s = dVar.g;
        this.m = dVar.j;
    }
}
