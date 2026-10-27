package m2;

import A7.k;
import A7.m;
import F1.C0254m;
import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.C0269d;
import F8.C0278m;
import F8.K;
import F8.O;
import F8.P;
import F8.a0;
import G8.t;
import Q7.Q;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.os.Handler;
import android.os.SystemClock;
import android.util.SparseIntArray;
import d5.C1078G;
import d5.I;
import e2.q0;
import g8.C1317g;
import h2.AbstractC1343A;
import h4.InterfaceC1359a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import l3.HandlerC1768y;
import n2.RunnableC1918s;
import n4.o;
import n4.w;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import q2.C2170a;
import q2.C2171b;
import q2.r;
import r.C2218F;
import r.InterfaceC2216D;
import s3.U;
import s3.c0;
import s8.C2413a;
import s8.InterfaceC2414b;
import u.AbstractC2473m;
import u.C2458F;
import u.C2472l;
import u2.C2516t;

/* JADX INFO: loaded from: classes.dex */
public class b implements o, InterfaceC2216D, G8.b {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f18945m;

    public /* synthetic */ b(Object obj, Object obj2) {
        this.l = obj;
        this.f18945m = obj2;
    }

    public static int r0(int i7, int i10) {
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < i7; i13++) {
            i11++;
            if (i11 == i10) {
                i12++;
                i11 = 0;
            } else if (i11 > i10) {
                i12++;
                i11 = 1;
            }
        }
        return i11 + 1 > i10 ? i12 + 1 : i12;
    }

    @Override // G8.b
    public a0 A(I8.c cVar) {
        return G8.g.P(cVar);
    }

    @Override // G8.b
    public boolean B(I8.c cVar) {
        m.f(cVar, "<this>");
        A aH = G8.g.h(cVar);
        return (aH != null ? G8.g.f(aH) : null) != null;
    }

    @Override // G8.b
    public I8.c C(I8.c cVar) {
        return G8.g.a0(this, cVar);
    }

    @Override // G8.b
    public O D(InterfaceC2414b interfaceC2414b) {
        return G8.g.T(interfaceC2414b);
    }

    @Override // G8.b
    public C0278m E(I8.d dVar) {
        return G8.g.f(dVar);
    }

    @Override // G8.b
    public boolean F(I8.f fVar) {
        return G8.g.A(fVar);
    }

    @Override // G8.b
    public boolean G(O o) {
        return G8.g.K(o);
    }

    @Override // G8.b
    public int H(I8.b bVar) {
        return G8.g.k(bVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // G8.b
    public O I(I8.e eVar, int i7) {
        m.f(eVar, "<this>");
        if (eVar instanceof I8.d) {
            return G8.g.p((I8.c) eVar, i7);
        }
        if (eVar instanceof I8.a) {
            E e10 = ((I8.a) eVar).get(i7);
            m.e(e10, "get(index)");
            return (O) e10;
        }
        throw new IllegalStateException(("unknown type argument list type: " + eVar + ", " + A7.A.f443a.b(eVar.getClass())).toString());
    }

    @Override // G8.b
    public G8.i J(I8.b bVar) {
        return G8.g.X(bVar);
    }

    @Override // G8.b
    public void K(I8.d dVar, I8.f fVar) {
    }

    @Override // G8.b
    public int L(I8.c cVar) {
        return G8.g.c(cVar);
    }

    @Override // G8.b
    public boolean M(I8.d dVar) {
        m.f(dVar, "<this>");
        A aH = G8.g.h(dVar);
        return (aH != null ? G8.g.e(this, aH) : null) != null;
    }

    @Override // G8.b
    public boolean N(I8.d dVar) {
        m.f(dVar, "<this>");
        return G8.g.y(G8.g.W(dVar));
    }

    @Override // G8.b
    public I8.e O(I8.d dVar) {
        return G8.g.d(dVar);
    }

    @Override // G8.b
    public a0 P(I8.b bVar) {
        return G8.g.O(bVar);
    }

    @Override // G8.b
    public I8.d Q(I8.d dVar) {
        A aQ;
        m.f(dVar, "<this>");
        C0278m c0278mF = G8.g.f(dVar);
        return (c0278mF == null || (aQ = G8.g.Q(c0278mF)) == null) ? dVar : aQ;
    }

    @Override // G8.b
    public int R(O o) {
        return G8.g.t(o);
    }

    @Override // G8.b
    public boolean S(I8.c cVar) {
        m.f(cVar, "$receiver");
        return cVar instanceof C1317g;
    }

    @Override // r.InterfaceC2216D
    public void T(int i7) {
        super/*android.widget.TextView*/.setFirstBaselineToTopHeight(i7);
    }

    @Override // G8.b
    public boolean U(I8.f fVar) {
        return G8.g.G(fVar);
    }

    @Override // G8.b
    public boolean V(I8.f fVar) {
        return G8.g.y(fVar);
    }

    @Override // G8.b
    public A W(I8.c cVar) {
        A aY;
        m.f(cVar, "<this>");
        AbstractC0282q abstractC0282qG = G8.g.g(cVar);
        if (abstractC0282qG != null && (aY = G8.g.Y(abstractC0282qG)) != null) {
            return aY;
        }
        A aH = G8.g.h(cVar);
        m.c(aH);
        return aH;
    }

    @Override // G8.b
    public O X(I8.c cVar, int i7) {
        return G8.g.p(cVar, i7);
    }

    @Override // G8.b
    public void Y(I8.c cVar) {
        m.f(cVar, "<this>");
        G8.g.g(cVar);
    }

    @Override // G8.b
    public void Z(I8.d dVar) {
        G8.g.L(dVar);
    }

    @Override // G8.b
    public A a(C0278m c0278m) {
        return G8.g.Q(c0278m);
    }

    @Override // G8.b
    public A a0(I8.c cVar) {
        A aN;
        m.f(cVar, "<this>");
        AbstractC0282q abstractC0282qG = G8.g.g(cVar);
        if (abstractC0282qG != null && (aN = G8.g.N(abstractC0282qG)) != null) {
            return aN;
        }
        A aH = G8.g.h(cVar);
        m.c(aH);
        return aH;
    }

    @Override // G8.b
    public boolean b(I8.d dVar) {
        m.f(dVar, "<this>");
        return G8.g.D(G8.g.W(dVar));
    }

    @Override // G8.b
    public boolean b0(Q q4, I8.f fVar) {
        return G8.g.v(q4, fVar);
    }

    @Override // G8.b
    public A c(AbstractC0282q abstractC0282q) {
        return G8.g.N(abstractC0282q);
    }

    @Override // G8.b
    public I8.b c0(I8.d dVar) {
        return G8.g.e(this, dVar);
    }

    @Override // G8.b
    public AbstractC0282q d(I8.c cVar) {
        return G8.g.g(cVar);
    }

    @Override // G8.b
    public A d0(I8.d dVar) {
        return G8.g.j(dVar);
    }

    @Override // G8.b
    public a0 e(O o) {
        return G8.g.r(o);
    }

    @Override // G8.b
    public K e0(I8.c cVar) {
        m.f(cVar, "<this>");
        A aH = G8.g.h(cVar);
        if (aH == null) {
            aH = a0(cVar);
        }
        return G8.g.W(aH);
    }

    @Override // G8.b
    public A f(AbstractC0282q abstractC0282q) {
        return G8.g.Y(abstractC0282q);
    }

    @Override // G8.b
    public boolean f0(I8.d dVar) {
        return G8.g.B(dVar);
    }

    @Override // G8.b
    public int g(I8.e eVar) {
        m.f(eVar, "<this>");
        if (eVar instanceof I8.d) {
            return G8.g.c((I8.c) eVar);
        }
        if (eVar instanceof I8.a) {
            return ((I8.a) eVar).size();
        }
        throw new IllegalStateException(("unknown type argument list type: " + eVar + ", " + A7.A.f443a.b(eVar.getClass())).toString());
    }

    @Override // G8.b
    public boolean g0(I8.d dVar) {
        return G8.g.F(dVar);
    }

    @Override // G8.b
    public boolean h(I8.f fVar) {
        return G8.g.x(fVar);
    }

    @Override // G8.b
    public P h0(I8.c cVar) {
        return G8.g.i(cVar);
    }

    @Override // G8.b
    public boolean i(I8.f fVar, I8.f fVar2) {
        m.f(fVar, "c1");
        m.f(fVar2, "c2");
        if (!(fVar instanceof K)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (!(fVar2 instanceof K)) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (!G8.g.b(fVar, fVar2)) {
            K k10 = (K) fVar;
            K k11 = (K) fVar2;
            if (!((G8.c) this.f18945m).a(k10, k11)) {
                HashMap map = (HashMap) this.l;
                if (map != null) {
                    K k12 = (K) map.get(k10);
                    K k13 = (K) map.get(k11);
                    if ((k12 == null || !k12.equals(k11)) && (k13 == null || !k13.equals(k10))) {
                    }
                }
                return false;
            }
        }
        return true;
    }

    @Override // r.InterfaceC2216D
    public void i0(int i7, float f10) {
    }

    @Override // G8.b
    public boolean j(a0 a0Var) {
        m.f(a0Var, "<this>");
        return G8.g.F(a0(a0Var)) != G8.g.F(W(a0Var));
    }

    @Override // G8.b
    public boolean j0(I8.b bVar) {
        return G8.g.J(bVar);
    }

    @Override // G8.b
    public Collection k(I8.f fVar) {
        return G8.g.V(fVar);
    }

    @Override // G8.b
    public int k0(I8.f fVar) {
        return G8.g.R(fVar);
    }

    @Override // G8.b
    public G8.a l(I8.d dVar) {
        return G8.g.U(this, dVar);
    }

    @Override // G8.b
    public boolean l0(I8.f fVar) {
        return G8.g.E(fVar);
    }

    @Override // G8.b
    public boolean m(I8.d dVar) {
        m.f(dVar, "<this>");
        return G8.g.G(e0(dVar)) && !G8.g.H(dVar);
    }

    @Override // G8.b
    public A m0(I8.c cVar) {
        return G8.g.h(cVar);
    }

    @Override // G8.b
    public boolean n(I8.d dVar, I8.d dVar2) {
        return G8.g.w(dVar, dVar2);
    }

    @Override // G8.b
    public A n0(I8.d dVar, boolean z6) {
        return G8.g.Z(dVar, z6);
    }

    @Override // G8.b
    public boolean o(I8.f fVar) {
        return G8.g.z(fVar);
    }

    @Override // G8.b
    public boolean o0(I8.b bVar) {
        m.f(bVar, "$receiver");
        return bVar instanceof C2413a;
    }

    @Override // G8.b
    public Collection p(I8.d dVar) {
        return G8.g.S(this, dVar);
    }

    @Override // n4.o
    public void p0() {
        w wVar = (w) this.l;
        synchronized (wVar) {
            wVar.f19969n = wVar.l.length;
        }
    }

    @Override // G8.b
    public Q q(I8.f fVar, int i7) {
        return G8.g.q(fVar, i7);
    }

    public void q0(U u4, C0254m c0254m) {
        C2458F c2458f = (C2458F) this.l;
        c0 c0VarA = (c0) c2458f.get(u4);
        if (c0VarA == null) {
            c0VarA = c0.a();
            c2458f.put(u4, c0VarA);
        }
        c0VarA.c = c0254m;
        c0VarA.f22264a |= 8;
    }

    @Override // G8.b
    public void r(I8.d dVar) {
        G8.g.M(dVar);
    }

    @Override // G8.b
    public O s(I8.d dVar, int i7) {
        m.f(dVar, "<this>");
        if (i7 < 0 || i7 >= G8.g.c(dVar)) {
            return null;
        }
        return G8.g.p(dVar, i7);
    }

    public void s0() {
        ((SparseIntArray) this.l).clear();
    }

    @Override // G8.b
    public int t(Q q4) {
        m.f(q4, "$receiver");
        int iE0 = q4.e0();
        k.s(iE0, "this.variance");
        return l9.d.K(iE0);
    }

    public void t0(Exception exc, boolean z6) {
        this.f18945m = null;
        HashSet hashSet = (HashSet) this.l;
        I iW = I.w(hashSet);
        hashSet.clear();
        C1078G c1078gY = iW.listIterator(0);
        while (c1078gY.hasNext()) {
            C2171b c2171b = (C2171b) c1078gY.next();
            c2171b.getClass();
            c2171b.j(exc, z6 ? 1 : 3);
        }
    }

    @Override // r.InterfaceC2216D
    public void u(int i7) {
        super/*android.widget.TextView*/.setLastBaselineToBottomHeight(i7);
    }

    public C0254m u0(U u4, int i7) {
        c0 c0Var;
        C0254m c0254m;
        C2458F c2458f = (C2458F) this.l;
        int iE = c2458f.e(u4);
        if (iE >= 0 && (c0Var = (c0) c2458f.k(iE)) != null) {
            int i10 = c0Var.f22264a;
            if ((i10 & i7) != 0) {
                int i11 = i10 & (~i7);
                c0Var.f22264a = i11;
                if (i7 == 4) {
                    c0254m = c0Var.f22265b;
                } else {
                    if (i7 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    c0254m = c0Var.c;
                }
                if ((i11 & 12) == 0) {
                    c2458f.i(iE);
                    c0Var.f22264a = 0;
                    c0Var.f22265b = null;
                    c0Var.c = null;
                    c0.d.c(c0Var);
                }
                return c0254m;
            }
        }
        return null;
    }

    @Override // n4.o
    public void v(Bitmap bitmap, InterfaceC1359a interfaceC1359a) throws IOException {
        IOException iOException = ((A4.f) this.f18945m).f418m;
        if (iOException != null) {
            if (bitmap == null) {
                throw iOException;
            }
            interfaceC1359a.k(bitmap);
            throw iOException;
        }
    }

    public void v0(C2171b c2171b) {
        ((HashSet) this.l).add(c2171b);
        if (((C2171b) this.f18945m) != null) {
            return;
        }
        this.f18945m = c2171b;
        r rVarH = c2171b.f21164b.h();
        c2171b.f21182x = rVarH;
        HandlerC1768y handlerC1768y = c2171b.f21176r;
        int i7 = AbstractC1343A.f15195a;
        rVarH.getClass();
        handlerC1768y.getClass();
        handlerC1768y.obtainMessage(1, new C2170a(C2516t.f22900a.getAndIncrement(), true, SystemClock.elapsedRealtime(), rVarH)).sendToTarget();
    }

    @Override // G8.b
    public a0 w(ArrayList arrayList) {
        A a10;
        int size = arrayList.size();
        if (size == 0) {
            throw new IllegalStateException("Expected some types");
        }
        if (size == 1) {
            return (a0) AbstractC1998m.r0(arrayList);
        }
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it = arrayList.iterator();
        boolean z6 = false;
        boolean z10 = false;
        while (it.hasNext()) {
            a0 a0Var = (a0) it.next();
            z6 = z6 || AbstractC0268c.i(a0Var);
            if (a0Var instanceof A) {
                a10 = (A) a0Var;
            } else {
                if (!(a0Var instanceof AbstractC0282q)) {
                    throw new A9.k();
                }
                m.f(a0Var, "<this>");
                a10 = ((AbstractC0282q) a0Var).f2477m;
                z10 = true;
            }
            arrayList2.add(a10);
        }
        if (z6) {
            return H8.i.c(H8.h.f2873I, arrayList.toString());
        }
        t tVar = t.f2743a;
        if (!z10) {
            return tVar.b(arrayList2);
        }
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList3.add(AbstractC0268c.y((a0) it2.next()));
        }
        return C0269d.j(tVar.b(arrayList2), tVar.b(arrayList3));
    }

    public void w0(U u4) {
        c0 c0Var = (c0) ((C2458F) this.l).get(u4);
        if (c0Var == null) {
            return;
        }
        c0Var.f22264a &= -2;
    }

    @Override // G8.b
    public a0 x(I8.d dVar, I8.d dVar2) {
        return G8.g.m(this, dVar, dVar2);
    }

    public void x0(U u4) {
        C2472l c2472l = (C2472l) this.f18945m;
        int iG = c2472l.g() - 1;
        while (true) {
            if (iG < 0) {
                break;
            }
            if (u4 == c2472l.h(iG)) {
                Object[] objArr = c2472l.f22606n;
                Object obj = objArr[iG];
                Object obj2 = AbstractC2473m.f22607a;
                if (obj != obj2) {
                    objArr[iG] = obj2;
                    c2472l.l = true;
                }
            } else {
                iG--;
            }
        }
        c0 c0Var = (c0) ((C2458F) this.l).remove(u4);
        if (c0Var != null) {
            c0Var.f22264a = 0;
            c0Var.f22265b = null;
            c0Var.c = null;
            c0.d.c(c0Var);
        }
    }

    @Override // G8.b
    public K y(I8.d dVar) {
        return G8.g.W(dVar);
    }

    public void y0(q0 q0Var) {
        Handler handler = (Handler) this.l;
        if (handler != null) {
            handler.post(new RunnableC1918s(this, 8, q0Var));
        }
    }

    @Override // G8.b
    public boolean z(I8.f fVar) {
        return G8.g.D(fVar);
    }

    public b(HashMap map, G8.c cVar) {
        m.f(cVar, "equalityAxioms");
        this.l = map;
        this.f18945m = cVar;
    }

    public b(Context context) {
        this.l = context;
    }

    public b(MediaCodec.CryptoInfo cryptoInfo) {
        this.l = cryptoInfo;
        this.f18945m = new MediaCodec.CryptoInfo.Pattern(0, 0);
    }

    public b(ArrayList arrayList, ArrayList arrayList2) {
        int size = arrayList.size();
        this.l = new int[size];
        this.f18945m = new float[size];
        for (int i7 = 0; i7 < size; i7++) {
            ((int[]) this.l)[i7] = ((Integer) arrayList.get(i7)).intValue();
            ((float[]) this.f18945m)[i7] = ((Float) arrayList2.get(i7)).floatValue();
        }
    }

    public b(int i7, int i10) {
        this.l = new int[]{i7, i10};
        this.f18945m = new float[]{0.0f, 1.0f};
    }

    public b(int i7, int i10, int i11) {
        this.l = new int[]{i7, i10, i11};
        this.f18945m = new float[]{0.0f, 0.5f, 1.0f};
    }

    public b(int i7) {
        switch (i7) {
            case 6:
                this.l = new SparseIntArray();
                this.f18945m = new SparseIntArray();
                break;
            case 7:
                this.l = new C2458F(0);
                this.f18945m = new C2472l((Object) null);
                break;
            default:
                this.l = new HashSet();
                break;
        }
    }

    public b(C2218F c2218f) {
        this.f18945m = c2218f;
        this.l = c2218f;
    }
}
