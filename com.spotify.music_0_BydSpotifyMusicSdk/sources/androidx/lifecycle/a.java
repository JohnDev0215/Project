package androidx.lifecycle;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import p.bm0;
import p.eb;
import p.f01;
import p.j91;
import p.l91;
import p.m62;
import p.n70;
import p.p91;
import p.r91;
import p.s91;
import p.t91;
import p.u4;
import p.v03;
import p.v91;

/* JADX INFO: loaded from: classes.dex */
public final class a extends l91 {
    public final boolean a;
    public bm0 b;
    public Lifecycle$State c;
    public final WeakReference d;
    public int e;
    public boolean f;
    public boolean g;
    public final ArrayList h;

    public a(s91 s91Var) {
        v03.j(s91Var, "provider");
        this.a = true;
        this.b = new bm0();
        this.c = Lifecycle$State.e;
        this.h = new ArrayList();
        this.d = new WeakReference(s91Var);
    }

    @Override // p.l91
    public final void a(r91 r91Var) {
        p91 reflectiveGenericLifecycleObserver;
        s91 s91Var;
        v03.j(r91Var, "observer");
        e("addObserver");
        Lifecycle$State lifecycle$State = this.c;
        Lifecycle$State lifecycle$State2 = Lifecycle$State.c;
        if (lifecycle$State != lifecycle$State2) {
            lifecycle$State2 = Lifecycle$State.e;
        }
        t91 t91Var = new t91();
        HashMap map = v91.a;
        boolean z = r91Var instanceof p91;
        boolean z2 = r91Var instanceof n70;
        if (z && z2) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((n70) r91Var, (p91) r91Var);
        } else if (z2) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((n70) r91Var, null);
        } else if (z) {
            reflectiveGenericLifecycleObserver = (p91) r91Var;
        } else {
            Class<?> cls = r91Var.getClass();
            if (v91.b(cls) == 2) {
                Object obj = v91.b.get(cls);
                v03.h(obj);
                List list = (List) obj;
                if (list.size() == 1) {
                    v91.a((Constructor) list.get(0), r91Var);
                    throw null;
                }
                int size = list.size();
                f01[] f01VarArr = new f01[size];
                if (size > 0) {
                    v91.a((Constructor) list.get(0), r91Var);
                    throw null;
                }
                reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(f01VarArr);
            } else {
                reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(r91Var);
            }
        }
        t91Var.b = reflectiveGenericLifecycleObserver;
        t91Var.a = lifecycle$State2;
        if (((t91) this.b.c(r91Var, t91Var)) == null && (s91Var = (s91) this.d.get()) != null) {
            boolean z3 = this.e != 0 || this.f;
            Lifecycle$State lifecycle$StateD = d(r91Var);
            this.e++;
            while (t91Var.a.compareTo(lifecycle$StateD) < 0 && this.b.m.containsKey(r91Var)) {
                this.h.add(t91Var.a);
                j91 j91Var = Lifecycle$Event.Companion;
                Lifecycle$State lifecycle$State3 = t91Var.a;
                j91Var.getClass();
                Lifecycle$Event lifecycle$EventA = j91.a(lifecycle$State3);
                if (lifecycle$EventA == null) {
                    throw new IllegalStateException("no event up from " + t91Var.a);
                }
                t91Var.a(s91Var, lifecycle$EventA);
                ArrayList arrayList = this.h;
                arrayList.remove(arrayList.size() - 1);
                lifecycle$StateD = d(r91Var);
            }
            if (!z3) {
                i();
            }
            this.e--;
        }
    }

    @Override // p.l91
    public final Lifecycle$State b() {
        return this.c;
    }

    @Override // p.l91
    public final void c(r91 r91Var) {
        v03.j(r91Var, "observer");
        e("removeObserver");
        this.b.b(r91Var);
    }

    public final Lifecycle$State d(r91 r91Var) {
        t91 t91Var;
        HashMap map = this.b.m;
        m62 m62Var = map.containsKey(r91Var) ? ((m62) map.get(r91Var)).l : null;
        Lifecycle$State lifecycle$State = (m62Var == null || (t91Var = (t91) m62Var.e) == null) ? null : t91Var.a;
        ArrayList arrayList = this.h;
        Lifecycle$State lifecycle$State2 = arrayList.isEmpty() ^ true ? (Lifecycle$State) arrayList.get(arrayList.size() - 1) : null;
        Lifecycle$State lifecycle$State3 = this.c;
        v03.j(lifecycle$State3, "state1");
        if (lifecycle$State == null || lifecycle$State.compareTo(lifecycle$State3) >= 0) {
            lifecycle$State = lifecycle$State3;
        }
        return (lifecycle$State2 == null || lifecycle$State2.compareTo(lifecycle$State) >= 0) ? lifecycle$State : lifecycle$State2;
    }

    public final void e(String str) {
        if (this.a) {
            eb.w().f.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(u4.j("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void f(Lifecycle$Event lifecycle$Event) {
        v03.j(lifecycle$Event, "event");
        e("handleLifecycleEvent");
        g(lifecycle$Event.a());
    }

    public final void g(Lifecycle$State lifecycle$State) {
        Lifecycle$State lifecycle$State2 = this.c;
        if (lifecycle$State2 == lifecycle$State) {
            return;
        }
        Lifecycle$State lifecycle$State3 = Lifecycle$State.e;
        Lifecycle$State lifecycle$State4 = Lifecycle$State.c;
        if (lifecycle$State2 == lifecycle$State3 && lifecycle$State == lifecycle$State4) {
            throw new IllegalStateException(("no event down from " + this.c + " in component " + this.d.get()).toString());
        }
        this.c = lifecycle$State;
        if (this.f || this.e != 0) {
            this.g = true;
            return;
        }
        this.f = true;
        i();
        this.f = false;
        if (this.c == lifecycle$State4) {
            this.b = new bm0();
        }
    }

    public final void h() {
        Lifecycle$State lifecycle$State = Lifecycle$State.k;
        e("setCurrentState");
        g(lifecycle$State);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r8.g = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i() {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.a.i():void");
    }
}
