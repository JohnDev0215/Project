package N5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.player.PlayerService;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import n2.C1923x;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import u2.C2512o;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ q0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i0 f4732m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4733n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(q0 q0Var, i0 i0Var, boolean z6, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = q0Var;
        this.f4732m = i0Var;
        this.f4733n = z6;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new p0(this.l, this.f4732m, this.f4733n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        p0 p0Var = (p0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        p0Var.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        i0 i0Var = this.f4732m;
        boolean z6 = i0Var.d;
        q0 q0Var = this.l;
        q0Var.f4754x = z6;
        if (z6) {
            AbstractC0727y.w(q0Var.f4738f, null, null, new o0(q0Var, i0Var, null), 3);
        }
        boolean z10 = i0Var.c;
        ArrayList arrayList = q0Var.f4740h;
        if (z10) {
            List listZ0 = AbstractC1998m.z0(arrayList);
            V8.f0 f0Var = q0Var.f4745n;
            f0Var.getClass();
            f0Var.m(null, listZ0);
            C1923x c1923x = PlayerService.f13008u;
            List list = (List) ((V8.f0) q0Var.f4752v.l).getValue();
            A7.m.f(list, "newSongList");
            if (list.isEmpty()) {
                C1923x c1923x2 = PlayerService.f13008u;
                if (c1923x2 == null) {
                    A7.m.j("player");
                    throw null;
                }
                c1923x2.D();
                C1923x c1923x3 = PlayerService.f13008u;
                if (c1923x3 == null) {
                    A7.m.j("player");
                    throw null;
                }
                c1923x3.stop();
            } else {
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((f0) it.next()).d);
                }
                ArrayList arrayList3 = new ArrayList();
                C1923x c1923x4 = PlayerService.f13008u;
                if (c1923x4 == null) {
                    A7.m.j("player");
                    throw null;
                }
                int iO = c1923x4.t0().o();
                ArrayList arrayList4 = arrayList2;
                for (int i7 = 0; i7 < iO; i7++) {
                    C1923x c1923x5 = PlayerService.f13008u;
                    if (c1923x5 == null) {
                        A7.m.j("player");
                        throw null;
                    }
                    String str = c1923x5.t0().m(i7, (e2.e0) c1923x5.f14069b, 0L).c.f13878a;
                    A7.m.e(str, "mediaId");
                    arrayList3.add(str);
                }
                int size = arrayList3.size() - 1;
                int i10 = -1;
                while (i10 < size) {
                    ArrayList arrayList5 = arrayList4;
                    if (!arrayList5.contains(arrayList3.get(size))) {
                        C1923x c1923x6 = PlayerService.f13008u;
                        if (c1923x6 == null) {
                            A7.m.j("player");
                            throw null;
                        }
                        c1923x6.v0(size);
                    }
                    i10 = -1;
                    size--;
                    arrayList4 = arrayList5;
                }
                int size2 = arrayList4.size();
                for (int i11 = 0; i11 < size2; i11++) {
                    f0 f0Var2 = (f0) list.get(i11);
                    C1923x c1923x7 = PlayerService.f13008u;
                    if (c1923x7 == null) {
                        A7.m.j("player");
                        throw null;
                    }
                    if (i11 < c1923x7.t0().o()) {
                        C1923x c1923x8 = PlayerService.f13008u;
                        if (c1923x8 == null) {
                            A7.m.j("player");
                            throw null;
                        }
                        String str2 = c1923x8.t0().m(i11, (e2.e0) c1923x8.f14069b, 0L).c.f13878a;
                        A7.m.e(str2, "mediaId");
                        String str3 = f0Var2.d;
                        if (A7.m.a(str3, str2)) {
                            continue;
                        } else if (arrayList3.contains(str3)) {
                            int iIndexOf = arrayList3.indexOf(str3);
                            C1923x c1923x9 = PlayerService.f13008u;
                            if (c1923x9 == null) {
                                A7.m.j("player");
                                throw null;
                            }
                            c1923x9.n0(iIndexOf, i11);
                        } else {
                            C2512o c2512oN = C8.d.n(f0Var2, 0L);
                            C1923x c1923x10 = PlayerService.f13008u;
                            if (c1923x10 == null) {
                                A7.m.j("player");
                                throw null;
                            }
                            c1923x10.A1();
                            c1923x10.a1(i11, Collections.singletonList(c2512oN));
                        }
                    } else {
                        C2512o c2512oN2 = C8.d.n(f0Var2, 0L);
                        C1923x c1923x11 = PlayerService.f13008u;
                        if (c1923x11 == null) {
                            A7.m.j("player");
                            throw null;
                        }
                        c1923x11.A1();
                        List listSingletonList = Collections.singletonList(c2512oN2);
                        c1923x11.A1();
                        c1923x11.a1(c1923x11.f19773p.size(), listSingletonList);
                    }
                }
            }
        }
        boolean z11 = this.f4733n;
        if (z11 || i0Var.f4699a != q0Var.f4743k) {
            V8.f0 f0Var3 = q0Var.l;
            if (z11) {
                Integer num = new Integer(-1);
                f0Var3.getClass();
                f0Var3.m(null, num);
            }
            int i12 = i0Var.f4699a;
            q0Var.f4743k = i12;
            Integer num2 = new Integer(i12);
            f0Var3.getClass();
            f0Var3.m(null, num2);
        }
        if (z11 || !A7.m.a(i0Var.f4700b, q0Var.f4742j)) {
            androidx.lifecycle.J j3 = q0Var.f4744m;
            if (z11) {
                j3.i(null);
            }
            f0 f0Var4 = i0Var.f4700b;
            q0Var.f4742j = f0Var4;
            j3.i(f0Var4);
        }
        q0Var.k();
        ArrayList arrayList6 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList6.add(((f0) it2.next()).f4691a);
        }
        AbstractC0727y.w(q0Var.f4739g, null, null, new n0(q0Var, new j5.m().e(arrayList6), null), 3);
        return m7.q.f19267a;
    }
}
