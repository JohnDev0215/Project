package u2;

import android.os.Looper;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: renamed from: u2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2498a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f22791a = new ArrayList(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashSet f22792b = new HashSet(1);
    public final B5.u c = new B5.u(new CopyOnWriteArrayList(), 0, (C2492C) null);
    public final q2.k d = new q2.k(new CopyOnWriteArrayList(), 0, null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Looper f22793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e2.f0 f22794f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public o2.l f22795g;

    public boolean a(e2.F f10) {
        return this instanceof C2512o;
    }

    public abstract InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3);

    public final void c(InterfaceC2493D interfaceC2493D) {
        HashSet hashSet = this.f22792b;
        boolean zIsEmpty = hashSet.isEmpty();
        hashSet.remove(interfaceC2493D);
        if (zIsEmpty || !hashSet.isEmpty()) {
            return;
        }
        d();
    }

    public void d() {
    }

    public final void e(InterfaceC2493D interfaceC2493D) {
        this.f22793e.getClass();
        HashSet hashSet = this.f22792b;
        boolean zIsEmpty = hashSet.isEmpty();
        hashSet.add(interfaceC2493D);
        if (zIsEmpty) {
            f();
        }
    }

    public void f() {
    }

    public e2.f0 g() {
        return null;
    }

    public abstract e2.F h();

    public boolean i() {
        return true;
    }

    public abstract void j();

    public final void k(InterfaceC2493D interfaceC2493D, k2.E e10, o2.l lVar) {
        Looper looperMyLooper = Looper.myLooper();
        Looper looper = this.f22793e;
        AbstractC1346c.c(looper == null || looper == looperMyLooper);
        this.f22795g = lVar;
        e2.f0 f0Var = this.f22794f;
        this.f22791a.add(interfaceC2493D);
        if (this.f22793e == null) {
            this.f22793e = looperMyLooper;
            this.f22792b.add(interfaceC2493D);
            l(e10);
        } else if (f0Var != null) {
            e(interfaceC2493D);
            interfaceC2493D.a(this, f0Var);
        }
    }

    public abstract void l(k2.E e10);

    public final void m(e2.f0 f0Var) {
        this.f22794f = f0Var;
        Iterator it = this.f22791a.iterator();
        while (it.hasNext()) {
            ((InterfaceC2493D) it.next()).a(this, f0Var);
        }
    }

    public abstract void n(InterfaceC2490A interfaceC2490A);

    public final void o(InterfaceC2493D interfaceC2493D) {
        ArrayList arrayList = this.f22791a;
        arrayList.remove(interfaceC2493D);
        if (!arrayList.isEmpty()) {
            c(interfaceC2493D);
            return;
        }
        this.f22793e = null;
        this.f22794f = null;
        this.f22795g = null;
        this.f22792b.clear();
        p();
    }

    public abstract void p();

    public final void q(q2.l lVar) {
        CopyOnWriteArrayList<q2.j> copyOnWriteArrayList = this.d.c;
        for (q2.j jVar : copyOnWriteArrayList) {
            if (jVar.f21208b == lVar) {
                copyOnWriteArrayList.remove(jVar);
            }
        }
    }

    public final void r(InterfaceC2497H interfaceC2497H) {
        CopyOnWriteArrayList<C2496G> copyOnWriteArrayList = (CopyOnWriteArrayList) this.c.o;
        for (C2496G c2496g : copyOnWriteArrayList) {
            if (c2496g.f22672b == interfaceC2497H) {
                copyOnWriteArrayList.remove(c2496g);
            }
        }
    }

    public void s(e2.F f10) {
    }
}
