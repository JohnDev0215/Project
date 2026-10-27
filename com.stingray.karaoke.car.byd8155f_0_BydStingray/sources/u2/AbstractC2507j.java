package u2;

import android.os.Handler;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: renamed from: u2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2507j extends AbstractC2498a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f22842h = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Handler f22843i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k2.E f22844j;

    @Override // u2.AbstractC2498a
    public final void d() {
        for (C2506i c2506i : this.f22842h.values()) {
            c2506i.f22838a.c(c2506i.f22839b);
        }
    }

    @Override // u2.AbstractC2498a
    public void f() {
        for (C2506i c2506i : this.f22842h.values()) {
            c2506i.f22838a.e(c2506i.f22839b);
        }
    }

    @Override // u2.AbstractC2498a
    public void j() {
        Iterator it = this.f22842h.values().iterator();
        while (it.hasNext()) {
            ((C2506i) it.next()).f22838a.j();
        }
    }

    @Override // u2.AbstractC2498a
    public void l(k2.E e10) {
        this.f22844j = e10;
        this.f22843i = AbstractC1343A.l(null);
    }

    @Override // u2.AbstractC2498a
    public void p() {
        HashMap map = this.f22842h;
        for (C2506i c2506i : map.values()) {
            c2506i.f22838a.o(c2506i.f22839b);
            C2505h c2505h = c2506i.c;
            AbstractC2498a abstractC2498a = c2506i.f22838a;
            abstractC2498a.r(c2505h);
            abstractC2498a.q(c2505h);
        }
        map.clear();
    }

    public abstract C2492C t(Object obj, C2492C c2492c);

    public long u(Object obj, long j3, C2492C c2492c) {
        return j3;
    }

    public int v(int i7, Object obj) {
        return i7;
    }

    public abstract void w(Object obj, AbstractC2498a abstractC2498a, e2.f0 f0Var);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [u2.D, u2.g] */
    public final void x(final Object obj, AbstractC2498a abstractC2498a) {
        HashMap map = this.f22842h;
        AbstractC1346c.c(!map.containsKey(obj));
        ?? r12 = new InterfaceC2493D() { // from class: u2.g
            @Override // u2.InterfaceC2493D
            public final void a(AbstractC2498a abstractC2498a2, e2.f0 f0Var) {
                this.f22826a.w(obj, abstractC2498a2, f0Var);
            }
        };
        C2505h c2505h = new C2505h(this, obj);
        map.put(obj, new C2506i(abstractC2498a, r12, c2505h));
        Handler handler = this.f22843i;
        handler.getClass();
        abstractC2498a.getClass();
        B5.u uVar = abstractC2498a.c;
        uVar.getClass();
        C2496G c2496g = new C2496G();
        c2496g.f22671a = handler;
        c2496g.f22672b = c2505h;
        ((CopyOnWriteArrayList) uVar.o).add(c2496g);
        Handler handler2 = this.f22843i;
        handler2.getClass();
        q2.k kVar = abstractC2498a.d;
        kVar.getClass();
        q2.j jVar = new q2.j();
        jVar.f21207a = handler2;
        jVar.f21208b = c2505h;
        kVar.c.add(jVar);
        k2.E e10 = this.f22844j;
        o2.l lVar = this.f22795g;
        AbstractC1346c.k(lVar);
        abstractC2498a.k(r12, e10, lVar);
        if (this.f22792b.isEmpty()) {
            abstractC2498a.c(r12);
        }
    }
}
