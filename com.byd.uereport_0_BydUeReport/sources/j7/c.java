package j7;

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
import org.greenrobot.eventbus.EventBusException;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: EventBus.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c f5607a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d f5608b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Map<Class<?>, List<Class<?>>> f5609c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map<Class<?>, CopyOnWriteArrayList<p>> f5610d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<Object, List<Class<?>>> f5611e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Map<Class<?>, Object> f5612f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ThreadLocal<C0068c> f5613g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final g f5614h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k f5615i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final j7.b f5616j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final j7.a f5617k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o f5618l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ExecutorService f5619m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f5620n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f5621o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f5622p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f5623q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f5624r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f5625s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f5626t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final f f5627u;

    /* JADX INFO: compiled from: EventBus.java */
    public class a extends ThreadLocal<C0068c> {
        public a() {
        }

        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C0068c initialValue() {
            return new C0068c();
        }
    }

    /* JADX INFO: compiled from: EventBus.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5629a;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            f5629a = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5629a[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5629a[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5629a[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5629a[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: renamed from: j7.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EventBus.java */
    public static final class C0068c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<Object> f5630a = new ArrayList();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f5631b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f5632c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public p f5633d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f5634e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f5635f;
    }

    public c() {
        this(f5608b);
    }

    public static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    public static c c() {
        c cVar = f5607a;
        if (cVar == null) {
            synchronized (c.class) {
                cVar = f5607a;
                if (cVar == null) {
                    cVar = new c();
                    f5607a = cVar;
                }
            }
        }
        return cVar;
    }

    public static List<Class<?>> j(Class<?> cls) {
        List<Class<?>> arrayList;
        Map<Class<?>, List<Class<?>>> map = f5609c;
        synchronized (map) {
            arrayList = map.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    arrayList.add(superclass);
                    a(arrayList, superclass.getInterfaces());
                }
                f5609c.put(cls, arrayList);
            }
        }
        return arrayList;
    }

    public final void b(p pVar, Object obj) {
        if (obj != null) {
            n(pVar, obj, i());
        }
    }

    public ExecutorService d() {
        return this.f5619m;
    }

    public f e() {
        return this.f5627u;
    }

    public final void f(p pVar, Object obj, Throwable th) {
        if (!(obj instanceof m)) {
            if (this.f5620n) {
                throw new EventBusException("Invoking subscriber failed", th);
            }
            if (this.f5621o) {
                this.f5627u.b(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + pVar.f5685a.getClass(), th);
            }
            if (this.f5623q) {
                k(new m(this, th, obj, pVar.f5685a));
                return;
            }
            return;
        }
        if (this.f5621o) {
            f fVar = this.f5627u;
            Level level = Level.SEVERE;
            fVar.b(level, "SubscriberExceptionEvent subscriber " + pVar.f5685a.getClass() + " threw an exception", th);
            m mVar = (m) obj;
            this.f5627u.b(level, "Initial event " + mVar.f5664c + " caused exception in " + mVar.f5665d, mVar.f5663b);
        }
    }

    public void g(i iVar) {
        Object obj = iVar.f5657b;
        p pVar = iVar.f5658c;
        i.b(iVar);
        if (pVar.f5687c) {
            h(pVar, obj);
        }
    }

    public void h(p pVar, Object obj) {
        try {
            pVar.f5686b.f5666a.invoke(pVar.f5685a, obj);
        } catch (IllegalAccessException e8) {
            throw new IllegalStateException("Unexpected exception", e8);
        } catch (InvocationTargetException e9) {
            f(pVar, obj, e9.getCause());
        }
    }

    public final boolean i() {
        g gVar = this.f5614h;
        return gVar == null || gVar.b();
    }

    public void k(Object obj) {
        C0068c c0068c = this.f5613g.get();
        List<Object> list = c0068c.f5630a;
        list.add(obj);
        if (c0068c.f5631b) {
            return;
        }
        c0068c.f5632c = i();
        c0068c.f5631b = true;
        if (c0068c.f5635f) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    l(list.remove(0), c0068c);
                }
            } finally {
                c0068c.f5631b = false;
                c0068c.f5632c = false;
            }
        }
    }

    public final void l(Object obj, C0068c c0068c) throws Error {
        boolean zM;
        Class<?> cls = obj.getClass();
        if (this.f5625s) {
            List<Class<?>> listJ = j(cls);
            int size = listJ.size();
            zM = false;
            for (int i8 = 0; i8 < size; i8++) {
                zM |= m(obj, c0068c, listJ.get(i8));
            }
        } else {
            zM = m(obj, c0068c, cls);
        }
        if (zM) {
            return;
        }
        if (this.f5622p) {
            this.f5627u.a(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.f5624r || cls == h.class || cls == m.class) {
            return;
        }
        k(new h(this, obj));
    }

    public final boolean m(Object obj, C0068c c0068c, Class<?> cls) {
        CopyOnWriteArrayList<p> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.f5610d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (p pVar : copyOnWriteArrayList) {
            c0068c.f5634e = obj;
            c0068c.f5633d = pVar;
            try {
                n(pVar, obj, c0068c.f5632c);
                if (c0068c.f5635f) {
                    return true;
                }
            } finally {
                c0068c.f5634e = null;
                c0068c.f5633d = null;
                c0068c.f5635f = false;
            }
        }
        return true;
    }

    public final void n(p pVar, Object obj, boolean z7) {
        int i8 = b.f5629a[pVar.f5686b.f5667b.ordinal()];
        if (i8 == 1) {
            h(pVar, obj);
            return;
        }
        if (i8 == 2) {
            if (z7) {
                h(pVar, obj);
                return;
            } else {
                this.f5615i.a(pVar, obj);
                return;
            }
        }
        if (i8 == 3) {
            k kVar = this.f5615i;
            if (kVar != null) {
                kVar.a(pVar, obj);
                return;
            } else {
                h(pVar, obj);
                return;
            }
        }
        if (i8 == 4) {
            if (z7) {
                this.f5616j.a(pVar, obj);
                return;
            } else {
                h(pVar, obj);
                return;
            }
        }
        if (i8 == 5) {
            this.f5617k.a(pVar, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + pVar.f5686b.f5667b);
    }

    public void o(Object obj) {
        List<n> listA = this.f5618l.a(obj.getClass());
        synchronized (this) {
            Iterator<n> it = listA.iterator();
            while (it.hasNext()) {
                p(obj, it.next());
            }
        }
    }

    public final void p(Object obj, n nVar) {
        Class<?> cls = nVar.f5668c;
        p pVar = new p(obj, nVar);
        CopyOnWriteArrayList<p> copyOnWriteArrayList = this.f5610d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.f5610d.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(pVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i8 = 0; i8 <= size; i8++) {
            if (i8 == size || nVar.f5669d > copyOnWriteArrayList.get(i8).f5686b.f5669d) {
                copyOnWriteArrayList.add(i8, pVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.f5611e.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.f5611e.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (nVar.f5670e) {
            if (!this.f5625s) {
                b(pVar, this.f5612f.get(cls));
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.f5612f.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    b(pVar, entry.getValue());
                }
            }
        }
    }

    public synchronized void q(Object obj) {
        List<Class<?>> list = this.f5611e.get(obj);
        if (list != null) {
            Iterator<Class<?>> it = list.iterator();
            while (it.hasNext()) {
                r(obj, it.next());
            }
            this.f5611e.remove(obj);
        } else {
            this.f5627u.a(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public final void r(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<p> copyOnWriteArrayList = this.f5610d.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i8 = 0;
            while (i8 < size) {
                p pVar = copyOnWriteArrayList.get(i8);
                if (pVar.f5685a == obj) {
                    pVar.f5687c = false;
                    copyOnWriteArrayList.remove(i8);
                    i8--;
                    size--;
                }
                i8++;
            }
        }
    }

    public String toString() {
        return "EventBus[indexCount=" + this.f5626t + ", eventInheritance=" + this.f5625s + "]";
    }

    public c(d dVar) {
        this.f5613g = new a();
        this.f5627u = dVar.b();
        this.f5610d = new HashMap();
        this.f5611e = new HashMap();
        this.f5612f = new ConcurrentHashMap();
        g gVarC = dVar.c();
        this.f5614h = gVarC;
        this.f5615i = gVarC != null ? gVarC.a(this) : null;
        this.f5616j = new j7.b(this);
        this.f5617k = new j7.a(this);
        List<l7.b> list = dVar.f5646k;
        this.f5626t = list != null ? list.size() : 0;
        this.f5618l = new o(dVar.f5646k, dVar.f5644i, dVar.f5643h);
        this.f5621o = dVar.f5637b;
        this.f5622p = dVar.f5638c;
        this.f5623q = dVar.f5639d;
        this.f5624r = dVar.f5640e;
        this.f5620n = dVar.f5641f;
        this.f5625s = dVar.f5642g;
        this.f5619m = dVar.f5645j;
    }
}
