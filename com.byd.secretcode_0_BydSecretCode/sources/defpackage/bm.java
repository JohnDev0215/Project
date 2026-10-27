package defpackage;

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
/* JADX INFO: loaded from: classes.dex */
public class bm {
    public static volatile bm a;
    public static final cm b = new cm();
    public static final Map<Class<?>, List<Class<?>>> c = new HashMap();
    public final Map<Class<?>, CopyOnWriteArrayList<om>> d;
    public final Map<Object, List<Class<?>>> e;
    public final Map<Class<?>, Object> f;
    public final ThreadLocal<c> g;
    public final fm h;
    public final jm i;
    public final am j;
    public final zl k;
    public final nm l;
    public final ExecutorService m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final boolean q;
    public final boolean r;
    public final boolean s;
    public final int t;
    public final em u;

    /* JADX INFO: compiled from: EventBus.java */
    public class a extends ThreadLocal<c> {
        public a() {
        }

        @Override // java.lang.ThreadLocal
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c initialValue() {
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
        public om d;
        public Object e;
        public boolean f;
    }

    public bm() {
        this(b);
    }

    public static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a(list, cls.getInterfaces());
            }
        }
    }

    public static bm c() {
        bm bmVar = a;
        if (bmVar == null) {
            synchronized (bm.class) {
                bmVar = a;
                if (bmVar == null) {
                    bmVar = new bm();
                    a = bmVar;
                }
            }
        }
        return bmVar;
    }

    public static List<Class<?>> j(Class<?> cls) {
        List<Class<?>> arrayList;
        Map<Class<?>, List<Class<?>>> map = c;
        synchronized (map) {
            arrayList = map.get(cls);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
                    arrayList.add(superclass);
                    a(arrayList, superclass.getInterfaces());
                }
                c.put(cls, arrayList);
            }
        }
        return arrayList;
    }

    public final void b(om omVar, Object obj) {
        if (obj != null) {
            n(omVar, obj, i());
        }
    }

    public ExecutorService d() {
        return this.m;
    }

    public em e() {
        return this.u;
    }

    public final void f(om omVar, Object obj, Throwable th) {
        if (!(obj instanceof lm)) {
            if (this.n) {
                throw new EventBusException("Invoking subscriber failed", th);
            }
            if (this.o) {
                this.u.b(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + omVar.a.getClass(), th);
            }
            if (this.q) {
                k(new lm(this, th, obj, omVar.a));
                return;
            }
            return;
        }
        if (this.o) {
            em emVar = this.u;
            Level level = Level.SEVERE;
            emVar.b(level, "SubscriberExceptionEvent subscriber " + omVar.a.getClass() + " threw an exception", th);
            lm lmVar = (lm) obj;
            this.u.b(level, "Initial event " + lmVar.c + " caused exception in " + lmVar.d, lmVar.b);
        }
    }

    public void g(hm hmVar) {
        Object obj = hmVar.b;
        om omVar = hmVar.c;
        hm.b(hmVar);
        if (omVar.c) {
            h(omVar, obj);
        }
    }

    public void h(om omVar, Object obj) {
        try {
            omVar.b.a.invoke(omVar.a, obj);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (InvocationTargetException e2) {
            f(omVar, obj, e2.getCause());
        }
    }

    public final boolean i() {
        fm fmVar = this.h;
        return fmVar == null || fmVar.b();
    }

    public void k(Object obj) {
        c cVar = this.g.get();
        List<Object> list = cVar.a;
        list.add(obj);
        if (cVar.b) {
            return;
        }
        cVar.c = i();
        cVar.b = true;
        if (cVar.f) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                if (list.isEmpty()) {
                    return;
                } else {
                    l(list.remove(0), cVar);
                }
            } finally {
                cVar.b = false;
                cVar.c = false;
            }
        }
    }

    public final void l(Object obj, c cVar) throws Error {
        boolean zM;
        Class<?> cls = obj.getClass();
        if (this.s) {
            List<Class<?>> listJ = j(cls);
            int size = listJ.size();
            zM = false;
            for (int i = 0; i < size; i++) {
                zM |= m(obj, cVar, listJ.get(i));
            }
        } else {
            zM = m(obj, cVar, cls);
        }
        if (zM) {
            return;
        }
        if (this.p) {
            this.u.a(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.r || cls == gm.class || cls == lm.class) {
            return;
        }
        k(new gm(this, obj));
    }

    public final boolean m(Object obj, c cVar, Class<?> cls) {
        CopyOnWriteArrayList<om> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.d.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return false;
        }
        for (om omVar : copyOnWriteArrayList) {
            cVar.e = obj;
            cVar.d = omVar;
            try {
                n(omVar, obj, cVar.c);
                if (cVar.f) {
                    return true;
                }
            } finally {
                cVar.e = null;
                cVar.d = null;
                cVar.f = false;
            }
        }
        return true;
    }

    public final void n(om omVar, Object obj, boolean z) {
        int i = b.a[omVar.b.b.ordinal()];
        if (i == 1) {
            h(omVar, obj);
            return;
        }
        if (i == 2) {
            if (z) {
                h(omVar, obj);
                return;
            } else {
                this.i.a(omVar, obj);
                return;
            }
        }
        if (i == 3) {
            jm jmVar = this.i;
            if (jmVar != null) {
                jmVar.a(omVar, obj);
                return;
            } else {
                h(omVar, obj);
                return;
            }
        }
        if (i == 4) {
            if (z) {
                this.j.a(omVar, obj);
                return;
            } else {
                h(omVar, obj);
                return;
            }
        }
        if (i == 5) {
            this.k.a(omVar, obj);
            return;
        }
        throw new IllegalStateException("Unknown thread mode: " + omVar.b.b);
    }

    public void o(Object obj) {
        List<mm> listA = this.l.a(obj.getClass());
        synchronized (this) {
            Iterator<mm> it = listA.iterator();
            while (it.hasNext()) {
                p(obj, it.next());
            }
        }
    }

    public final void p(Object obj, mm mmVar) {
        Class<?> cls = mmVar.c;
        om omVar = new om(obj, mmVar);
        CopyOnWriteArrayList<om> copyOnWriteArrayList = this.d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.d.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(omVar)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || mmVar.d > copyOnWriteArrayList.get(i).b.d) {
                copyOnWriteArrayList.add(i, omVar);
                break;
            }
        }
        List<Class<?>> arrayList = this.e.get(obj);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.e.put(obj, arrayList);
        }
        arrayList.add(cls);
        if (mmVar.e) {
            if (!this.s) {
                b(omVar, this.f.get(cls));
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.f.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    b(omVar, entry.getValue());
                }
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

    public final void r(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<om> copyOnWriteArrayList = this.d.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                om omVar = copyOnWriteArrayList.get(i);
                if (omVar.a == obj) {
                    omVar.c = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public String toString() {
        return "EventBus[indexCount=" + this.t + ", eventInheritance=" + this.s + "]";
    }

    public bm(cm cmVar) {
        this.g = new a();
        this.u = cmVar.b();
        this.d = new HashMap();
        this.e = new HashMap();
        this.f = new ConcurrentHashMap();
        fm fmVarC = cmVar.c();
        this.h = fmVarC;
        this.i = fmVarC != null ? fmVarC.a(this) : null;
        this.j = new am(this);
        this.k = new zl(this);
        List<rm> list = cmVar.k;
        this.t = list != null ? list.size() : 0;
        this.l = new nm(cmVar.k, cmVar.i, cmVar.h);
        this.o = cmVar.b;
        this.p = cmVar.c;
        this.q = cmVar.d;
        this.r = cmVar.e;
        this.n = cmVar.f;
        this.s = cmVar.g;
        this.m = cmVar.j;
    }
}
