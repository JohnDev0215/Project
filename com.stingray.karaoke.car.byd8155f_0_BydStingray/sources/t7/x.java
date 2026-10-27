package T7;

import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.C0273h;
import F8.C0275j;
import F8.T;
import F8.W;
import F8.Y;
import Q7.C0661n;
import Q7.C0667u;
import Q7.C0672z;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0658k;
import Q7.InterfaceC0659l;
import h8.AbstractC1387d;
import io.sentry.C1489n1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class x extends y {
    public final y l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final W f8917m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public W f8918n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f8919p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C0275j f8920q;

    public x(y yVar, W w10) {
        this.l = yVar;
        this.f8917m = w10;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void W(int r15) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T7.x.W(int):void");
    }

    @Override // T7.y
    public final y8.n A(G8.f fVar) {
        y8.n nVarA = this.l.A(fVar);
        if (!this.f8917m.f2455a.e()) {
            return new y8.s(nVarA, i0());
        }
        if (nVarA != null) {
            return nVarA;
        }
        W(14);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final List A0() {
        List listEmptyList = Collections.emptyList();
        if (listEmptyList != null) {
            return listEmptyList;
        }
        W(17);
        throw null;
    }

    @Override // Q7.InterfaceC0654g
    public final F8.K C() {
        F8.K kC = this.l.C();
        if (this.f8917m.f2455a.e()) {
            if (kC != null) {
                return kC;
            }
            W(0);
            throw null;
        }
        if (this.f8920q == null) {
            W wI0 = i0();
            Collection collectionO = kC.o();
            ArrayList arrayList = new ArrayList(collectionO.size());
            Iterator it = collectionO.iterator();
            while (it.hasNext()) {
                arrayList.add(wI0.i(1, (AbstractC0287w) it.next()));
            }
            this.f8920q = new C0275j(this, this.o, arrayList, E8.l.f1859e);
        }
        C0275j c0275j = this.f8920q;
        if (c0275j != null) {
            return c0275j;
        }
        W(1);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n C0(T t10) {
        AbstractC2564d.i(r8.d.d(this));
        return i(t10, G8.f.f2724a);
    }

    @Override // Q7.InterfaceC0652e
    public final int E() {
        int iE = this.l.E();
        if (iE != 0) {
            return iE;
        }
        W(25);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean F() {
        return this.l.F();
    }

    @Override // Q7.InterfaceC0652e
    public final Collection H() {
        Collection<C0790i> collectionH = this.l.H();
        ArrayList arrayList = new ArrayList(collectionH.size());
        for (C0790i c0790i : collectionH) {
            C0790i c0790i2 = c0790i;
            c0790i2.getClass();
            C0799s c0799sI1 = c0790i2.i1(W.f2454b);
            c0799sI1.f8873p = c0790i.a();
            c0799sI1.F(c0790i2.h());
            c0799sI1.u(c0790i2.e());
            c0799sI1.t(c0790i2.N());
            c0799sI1.f8881x = false;
            arrayList.add(((C0790i) c0799sI1.f8870I.f1(c0799sI1)).f(i0()));
        }
        return arrayList;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean H0() {
        return this.l.H0();
    }

    @Override // Q7.InterfaceC0652e
    public final u J0() {
        throw new UnsupportedOperationException();
    }

    @Override // Q7.InterfaceC0652e
    public final boolean L() {
        return this.l.L();
    }

    @Override // Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.I(this, obj);
    }

    @Override // Q7.InterfaceC0652e
    public final Collection X() {
        Collection collectionX = this.l.X();
        if (collectionX != null) {
            return collectionX;
        }
        W(31);
        throw null;
    }

    @Override // T7.y, Q7.InterfaceC0652e, Q7.InterfaceC0657j, Q7.InterfaceC0654g
    /* JADX INFO: renamed from: a */
    public final InterfaceC0652e b1() {
        InterfaceC0652e interfaceC0652eB1 = this.l.b1();
        if (interfaceC0652eB1 != null) {
            return interfaceC0652eB1;
        }
        W(21);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final boolean b0() {
        return this.l.b0();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean c0() {
        return this.l.c0();
    }

    @Override // Q7.InterfaceC0655h
    public final boolean d0() {
        return this.l.d0();
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0660m, Q7.InterfaceC0670x
    public final C0661n e() {
        C0661n c0661nE = this.l.e();
        if (c0661nE != null) {
            return c0661nE;
        }
        W(27);
        throw null;
    }

    @Override // Q7.P
    public final InterfaceC0658k f(W w10) {
        if (w10 != null) {
            return w10.f2455a.e() ? this : new x(this, W.e(w10.f(), i0().f()));
        }
        W(23);
        throw null;
    }

    @Override // Q7.InterfaceC0658k
    public final Q7.N g() {
        return Q7.N.c;
    }

    @Override // Q7.InterfaceC0657j
    public final C2061f getName() {
        C2061f name = this.l.getName();
        if (name != null) {
            return name;
        }
        W(20);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0670x
    public final int h() {
        int iH = this.l.h();
        if (iH != 0) {
            return iH;
        }
        W(26);
        throw null;
    }

    @Override // T7.y
    public final y8.n i(T t10, G8.f fVar) {
        y8.n nVarI = this.l.i(t10, fVar);
        if (!this.f8917m.f2455a.e()) {
            return new y8.s(nVarI, i0());
        }
        if (nVarI != null) {
            return nVarI;
        }
        W(7);
        throw null;
    }

    public final W i0() {
        if (this.f8918n == null) {
            W w10 = this.f8917m;
            if (w10.f2455a.e()) {
                this.f8918n = w10;
            } else {
                List listP = this.l.C().p();
                this.o = new ArrayList(listP.size());
                this.f8918n = AbstractC0268c.u(listP, w10.f(), this, this.o);
                ArrayList arrayList = this.o;
                A7.m.f(arrayList, "<this>");
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (!((Q7.Q) obj).Q()) {
                        arrayList2.add(obj);
                    }
                }
                this.f8919p = arrayList2;
            }
        }
        return this.f8918n;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0654g
    public final F8.A j() {
        F8.H hK;
        List listD = Y.d(C().p());
        R7.h hVarK = k();
        if (hVarK.isEmpty()) {
            F8.H.f2437m.getClass();
            hK = F8.H.f2438n;
        } else {
            C1489n1 c1489n1 = F8.H.f2437m;
            List listJ = AbstractC1387d.j(new C0273h(hVarK));
            c1489n1.getClass();
            hK = C1489n1.k(listJ);
        }
        return C0269d.s(hK, C(), listD, w0(), false);
    }

    @Override // R7.a
    public final R7.h k() {
        R7.h hVarK = this.l.k();
        if (hVarK != null) {
            return hVarK;
        }
        W(19);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n l0() {
        y8.n nVarL0 = this.l.l0();
        if (nVarL0 != null) {
            return nVarL0;
        }
        W(28);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final Q7.S m0() {
        Q7.S sM0 = this.l.m0();
        if (sM0 == null) {
            return null;
        }
        boolean z6 = sM0 instanceof C0667u;
        W w10 = this.f8917m;
        if (z6) {
            C0667u c0667u = (C0667u) sM0;
            F8.A a10 = (F8.A) c0667u.f7208b;
            if (a10 != null && !w10.f2455a.e()) {
                a10 = (F8.A) i0().i(1, a10);
            }
            return new C0667u(c0667u.f7207a, a10);
        }
        if (!(sM0 instanceof C0672z)) {
            throw new A9.k();
        }
        ArrayList<m7.i> arrayList = ((C0672z) sM0).f7210a;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        for (m7.i iVar : arrayList) {
            C2061f c2061f = (C2061f) iVar.l;
            F8.A a11 = (F8.A) ((I8.d) iVar.f19259m);
            if (a11 != null && !w10.f2455a.e()) {
                a11 = (F8.A) i0().i(1, a11);
            }
            arrayList2.add(new m7.i(c2061f, a11));
        }
        return new C0672z(arrayList2);
    }

    @Override // Q7.InterfaceC0652e
    public final boolean n() {
        return this.l.n();
    }

    @Override // Q7.InterfaceC0657j
    public final InterfaceC0657j o() {
        InterfaceC0657j interfaceC0657jO = this.l.o();
        if (interfaceC0657jO != null) {
            return interfaceC0657jO;
        }
        W(22);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final C0790i p0() {
        return this.l.p0();
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n q0() {
        y8.n nVarQ0 = this.l.q0();
        if (nVarQ0 != null) {
            return nVarQ0;
        }
        W(15);
        throw null;
    }

    @Override // Q7.InterfaceC0652e, Q7.InterfaceC0655h
    public final List s() {
        i0();
        ArrayList arrayList = this.f8919p;
        if (arrayList != null) {
            return arrayList;
        }
        W(30);
        throw null;
    }

    @Override // Q7.InterfaceC0652e
    public final y8.n w0() {
        AbstractC2564d.i(r8.d.d(this.l));
        return A(G8.f.f2724a);
    }

    @Override // Q7.InterfaceC0670x
    public final boolean y0() {
        return this.l.y0();
    }

    @Override // Q7.InterfaceC0670x
    public final boolean z() {
        return this.l.z();
    }
}
