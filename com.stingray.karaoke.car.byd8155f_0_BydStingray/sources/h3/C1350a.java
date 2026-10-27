package h3;

import android.os.Looper;
import d5.C1078G;
import d5.d0;
import e2.U;
import h2.AbstractC1346c;
import i3.C1405d;
import io.sentry.EnumC1469h;
import io.sentry.InterfaceC1464f0;
import io.sentry.N0;
import io.sentry.O0;
import io.sentry.Q;
import io.sentry.W0;
import io.sentry.Y1;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import k3.C1628b0;
import k3.C1672v;
import k3.C1679y0;
import k3.H0;
import k3.I0;
import k3.InterfaceC1656m0;
import k3.InterfaceC1677x0;
import k3.X0;
import k3.l1;
import k3.u1;

/* JADX INFO: renamed from: h3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1350a implements B2.u, O0, InterfaceC1464f0, io.sentry.util.c, h2.l, InterfaceC1656m0, H0, l1, h2.g {
    public final /* synthetic */ int l;

    public /* synthetic */ C1350a(int i7) {
        this.l = i7;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        switch (this.l) {
            case 26:
                i02.getClass();
                throw new ClassCastException();
            default:
                i02.getClass();
                throw new ClassCastException();
        }
    }

    @Override // h2.g
    public void accept(Object obj) {
        u1 u1Var = (u1) obj;
        switch (this.l) {
            case 28:
                u1Var.b();
                break;
            default:
                u1Var.G();
                break;
        }
    }

    @Override // h2.l
    public void b(Object obj) {
        ((U) obj).x(1);
    }

    @Override // k3.H0
    public void c(InterfaceC1677x0 interfaceC1677x0, int i7) {
        switch (this.l) {
            case 23:
                interfaceC1677x0.getClass();
                break;
            case 24:
                interfaceC1677x0.getClass();
                break;
            default:
                interfaceC1677x0.f(i7);
                break;
        }
    }

    @Override // k3.InterfaceC1656m0
    public void e(C1628b0 c1628b0) {
        switch (this.l) {
            case 17:
                if (c1628b0.v()) {
                    C1672v c1672v = c1628b0.f17904a;
                    c1672v.getClass();
                    AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
                    c1672v.d.getClass();
                    return;
                }
                return;
            case 18:
                c1628b0.f17910i.f(26, new X0(23));
                return;
            case 19:
                C1672v c1672v2 = c1628b0.f17904a;
                Objects.requireNonNull(c1672v2);
                c1672v2.X0(new A2.l(c1672v2, 21));
                return;
            case 20:
                throw new ClassCastException();
            case 21:
                if (c1628b0.v()) {
                    C1672v c1672v3 = c1628b0.f17904a;
                    c1672v3.getClass();
                    AbstractC1346c.j(Looper.myLooper() == c1672v3.f18137e.getLooper());
                    c1672v3.d.getClass();
                    return;
                }
                return;
            default:
                if (c1628b0.v()) {
                    C1672v c1672v4 = c1628b0.f17904a;
                    c1672v4.getClass();
                    AbstractC1346c.j(Looper.myLooper() == c1672v4.f18137e.getLooper());
                    c1672v4.d.getClass();
                    return;
                }
                return;
        }
    }

    @Override // B2.u
    public B2.r[] f() {
        switch (this.l) {
            case 0:
                return new B2.r[]{new C1351b()};
            case 1:
                return new B2.r[]{new C1353d()};
            case 2:
                return new B2.r[]{new C1354e()};
            case 3:
                return new B2.r[]{new C()};
            case 4:
                C8.d dVar = Y2.j.f10647f;
                h2.y yVar = new h2.y(0L);
                C1078G c1078g = d5.I.f13384m;
                return new B2.r[]{new F(1, dVar, yVar, new e7.f(d0.f13415p, 5))};
            default:
                return new B2.r[]{new C1405d()};
        }
    }

    @Override // io.sentry.util.c
    public Object g() {
        switch (this.l) {
            case 8:
                return new W0();
            case 9:
                return new io.sentry.metrics.b();
            default:
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                for (io.sentry.clientreport.d dVar : io.sentry.clientreport.d.values()) {
                    for (EnumC1469h enumC1469h : EnumC1469h.values()) {
                        concurrentHashMap.put(new io.sentry.clientreport.c(dVar.getReason(), enumC1469h.getCategory()), new AtomicLong(0L));
                    }
                }
                return Collections.unmodifiableMap(concurrentHashMap);
        }
    }

    @Override // io.sentry.O0
    public void i(N0 n02) {
        switch (this.l) {
            case 6:
                n02.getClass();
                n02.f15894b = null;
                n02.d = null;
                n02.c = null;
                n02.f15895e.clear();
                Y1 y12 = n02.f15896f;
                y12.clear();
                Iterator<Q> it = n02.f15900j.getScopeObservers().iterator();
                while (it.hasNext()) {
                    it.next().h(y12);
                }
                n02.f15897g.clear();
                n02.f15898h.clear();
                n02.f15899i.clear();
                n02.a();
                n02.f15904p.clear();
                break;
            case 12:
                A7.m.f(n02, "it");
                n02.b(io.sentry.protocol.t.f16774m);
                break;
            default:
                n02.f(new G2.a(n02, 18));
                break;
        }
    }

    @Override // io.sentry.InterfaceC1464f0
    public Object k() {
        return null;
    }
}
