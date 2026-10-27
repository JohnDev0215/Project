package E0;

import T.InterfaceC0749k;
import T.InterfaceC0769u0;
import android.os.Trace;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.RandomAccess;
import n7.AbstractC1999n;
import u.C2453A;
import u.C2475o;
import u.C2481u;
import u.C2486z;

/* JADX INFO: loaded from: classes.dex */
public final class Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1600a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1601b;
    public final Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f1602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f1603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public RandomAccess f1604g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f1605h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f1606i;

    public Y(F f10) {
        this.f1600a = 0;
        this.f1601b = f10;
        C0170u c0170u = new C0170u(f10);
        this.c = c0170u;
        this.d = c0170u;
        q0 q0Var = c0170u.f1703V;
        this.f1602e = q0Var;
        this.f1603f = q0Var;
    }

    public static final void a(Y y10, g0.n nVar, d0 d0Var) {
        y10.getClass();
        for (g0.n nVar2 = nVar.f14687p; nVar2 != null; nVar2 = nVar2.f14687p) {
            if (nVar2 == a0.f1607a) {
                F fG = ((F) y10.f1601b).G();
                d0Var.f1650y = fG != null ? (C0170u) fG.f1469H.c : null;
                y10.d = d0Var;
                return;
            } else {
                if ((nVar2.f14686n & 2) != 0) {
                    return;
                }
                nVar2.G0(d0Var);
            }
        }
    }

    public static g0.n b(g0.m mVar, g0.n nVar) {
        g0.n nVarI;
        if (mVar instanceof V) {
            nVarI = ((V) mVar).i();
            nVarI.f14686n = e0.g(nVarI);
        } else {
            C0153c c0153c = new C0153c();
            c0153c.f14686n = e0.e(mVar);
            c0153c.f1617y = mVar;
            c0153c.f1616A = new HashSet();
            nVarI = c0153c;
        }
        if (nVarI.f14695x) {
            l9.d.h0("A ModifierNodeElement cannot return an already attached node from create() ");
            throw null;
        }
        nVarI.f14691t = true;
        g0.n nVar2 = nVar.f14688q;
        if (nVar2 != null) {
            nVar2.f14687p = nVarI;
            nVarI.f14688q = nVar2;
        }
        nVar.f14688q = nVarI;
        nVarI.f14687p = nVar;
        return nVarI;
    }

    public static g0.n c(g0.n nVar) {
        boolean z6 = nVar.f14695x;
        if (z6) {
            C2481u c2481u = e0.f1665a;
            if (!z6) {
                l9.d.h0("autoInvalidateRemovedNode called on unattached node");
                throw null;
            }
            e0.b(nVar, -1, 2);
            nVar.E0();
            nVar.y0();
        }
        g0.n nVar2 = nVar.f14688q;
        g0.n nVar3 = nVar.f14687p;
        if (nVar2 != null) {
            nVar2.f14687p = nVar3;
            nVar.f14688q = null;
        }
        if (nVar3 != null) {
            nVar3.f14688q = nVar2;
            nVar.f14687p = null;
        }
        A7.m.c(nVar3);
        return nVar3;
    }

    public static void l(g0.m mVar, g0.m mVar2, g0.n nVar) {
        if ((mVar instanceof V) && (mVar2 instanceof V)) {
            Z z6 = a0.f1607a;
            A7.m.d(nVar, "null cannot be cast to non-null type T of androidx.compose.ui.node.NodeChainKt.updateUnsafe");
            ((V) mVar2).l(nVar);
            if (nVar.f14695x) {
                e0.d(nVar);
                return;
            } else {
                nVar.f14692u = true;
                return;
            }
        }
        if (!(nVar instanceof C0153c)) {
            throw new IllegalStateException("Unknown Modifier.Node type");
        }
        C0153c c0153c = (C0153c) nVar;
        if (c0153c.f14695x) {
            c0153c.I0();
        }
        c0153c.f1617y = mVar2;
        c0153c.f14686n = e0.e(mVar2);
        if (c0153c.f14695x) {
            c0153c.H0(false);
        }
        if (nVar.f14695x) {
            e0.d(nVar);
        } else {
            nVar.f14692u = true;
        }
    }

    public void d() {
        C2486z c2486z = (C2486z) this.f1601b;
        if (c2486z.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:abandons");
        try {
            Iterator it = c2486z.iterator();
            while (true) {
                P8.i iVar = (P8.i) it;
                if (!iVar.hasNext()) {
                    return;
                }
                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) iVar.next();
                iVar.remove();
                interfaceC0769u0.c();
            }
        } finally {
            Trace.endSection();
        }
    }

    public void e() {
        g(Integer.MIN_VALUE);
        ArrayList arrayList = (ArrayList) this.d;
        boolean zIsEmpty = arrayList.isEmpty();
        C2486z c2486z = (C2486z) this.f1601b;
        if (!zIsEmpty) {
            Trace.beginSection("Compose:onForgotten");
            try {
                C2453A c2453a = (C2453A) this.f1603f;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (-1 >= size) {
                        break;
                    }
                    Object obj = arrayList.get(size);
                    if (obj instanceof InterfaceC0769u0) {
                        c2486z.remove(obj);
                        ((InterfaceC0769u0) obj).d();
                    }
                    if (obj instanceof InterfaceC0749k) {
                        if (c2453a == null || !c2453a.c(obj)) {
                            ((InterfaceC0749k) obj).f();
                        } else {
                            ((InterfaceC0749k) obj).e();
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        ArrayList arrayList2 = (ArrayList) this.c;
        if (arrayList2.isEmpty()) {
            return;
        }
        Trace.beginSection("Compose:onRemembered");
        try {
            int size2 = arrayList2.size();
            for (int i7 = 0; i7 < size2; i7++) {
                InterfaceC0769u0 interfaceC0769u0 = (InterfaceC0769u0) arrayList2.get(i7);
                c2486z.remove(interfaceC0769u0);
                interfaceC0769u0.a();
            }
        } finally {
            Trace.endSection();
        }
    }

    public boolean f(int i7) {
        return (((g0.n) this.f1603f).o & i7) != 0;
    }

    public void g(int i7) {
        ArrayList arrayList = (ArrayList) this.f1604g;
        if (arrayList.isEmpty()) {
            return;
        }
        int i10 = 0;
        ArrayList arrayListF = null;
        int i11 = 0;
        C2475o c2475o = null;
        C2475o c2475o2 = null;
        while (true) {
            C2475o c2475o3 = (C2475o) this.f1606i;
            if (i11 >= c2475o3.f22614b) {
                break;
            }
            if (i7 <= c2475o3.c(i11)) {
                Object objRemove = arrayList.remove(i11);
                int iD = c2475o3.d(i11);
                int iD2 = ((C2475o) this.f1605h).d(i11);
                if (arrayListF == null) {
                    arrayListF = AbstractC1999n.F(objRemove);
                    c2475o2 = new C2475o();
                    c2475o2.a(iD);
                    c2475o = new C2475o();
                    c2475o.a(iD2);
                } else {
                    A7.m.d(c2475o, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                    A7.m.d(c2475o2, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                    arrayListF.add(objRemove);
                    c2475o2.a(iD);
                    c2475o.a(iD2);
                }
            } else {
                i11++;
            }
        }
        if (arrayListF != null) {
            A7.m.d(c2475o, "null cannot be cast to non-null type androidx.collection.MutableIntList");
            A7.m.d(c2475o2, "null cannot be cast to non-null type androidx.collection.MutableIntList");
            int size = arrayListF.size() - 1;
            while (i10 < size) {
                int i12 = i10 + 1;
                int size2 = arrayListF.size();
                for (int i13 = i12; i13 < size2; i13++) {
                    int iC = c2475o2.c(i10);
                    int iC2 = c2475o2.c(i13);
                    if (iC < iC2 || (iC2 == iC && c2475o.c(i10) < c2475o.c(i13))) {
                        Object obj = arrayListF.get(i10);
                        arrayListF.set(i10, arrayListF.get(i13));
                        arrayListF.set(i13, obj);
                        int iC3 = c2475o.c(i10);
                        c2475o.e(i10, c2475o.c(i13));
                        c2475o.e(i13, iC3);
                        int iC4 = c2475o2.c(i10);
                        c2475o2.e(i10, c2475o2.c(i13));
                        c2475o2.e(i13, iC4);
                    }
                }
                i10 = i12;
            }
            ((ArrayList) this.d).addAll(arrayListF);
        }
    }

    public void h(int i7, int i10, int i11, Object obj) {
        g(i7);
        if (i11 < 0 || i11 >= i7) {
            ((ArrayList) this.d).add(obj);
            return;
        }
        ((ArrayList) this.f1604g).add(obj);
        ((C2475o) this.f1605h).a(i10);
        ((C2475o) this.f1606i).a(i11);
    }

    public void i() {
        for (g0.n nVar = (g0.n) this.f1603f; nVar != null; nVar = nVar.f14688q) {
            nVar.D0();
            if (nVar.f14691t) {
                e0.a(nVar);
            }
            if (nVar.f14692u) {
                e0.d(nVar);
            }
            nVar.f14691t = false;
            nVar.f14692u = false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v16 ??, still in use, count: 1, list:
          (r11v16 ?? I:java.lang.Object) from 0x0020: IPUT (r11v16 ?? I:java.lang.Object), (r29v0 'this' ?? I:E0.Y A[IMMUTABLE_TYPE, THIS]) E0.Y.i java.lang.Object
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public void j(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v16 ??, still in use, count: 1, list:
          (r11v16 ?? I:java.lang.Object) from 0x0020: IPUT (r11v16 ?? I:java.lang.Object), (r29v0 'this' ?? I:E0.Y A[IMMUTABLE_TYPE, THIS]) E0.Y.i java.lang.Object
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r30v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    public void k() {
        F f10;
        A a10;
        i0 i0Var;
        g0.n nVar = ((q0) this.f1602e).f14687p;
        d0 d0Var = (C0170u) this.c;
        while (true) {
            f10 = (F) this.f1601b;
            if (nVar == null) {
                break;
            }
            InterfaceC0174y interfaceC0174yG = AbstractC0156f.g(nVar);
            if (interfaceC0174yG != null) {
                d0 d0Var2 = nVar.f14690s;
                if (d0Var2 != null) {
                    a10 = (A) d0Var2;
                    InterfaceC0174y interfaceC0174y = a10.f1456V;
                    a10.o1(interfaceC0174yG);
                    if (interfaceC0174y != nVar && (i0Var = a10.f1646O) != null) {
                        i0Var.invalidate();
                    }
                } else {
                    a10 = new A(f10, interfaceC0174yG);
                    nVar.G0(a10);
                }
                d0Var.f1650y = a10;
                a10.f1649x = d0Var;
                d0Var = a10;
            } else {
                nVar.G0(d0Var);
            }
            nVar = nVar.f14687p;
        }
        F fG = f10.G();
        d0Var.f1650y = fG != null ? (C0170u) fG.f1469H.c : null;
        this.d = d0Var;
    }

    public String toString() {
        switch (this.f1600a) {
            case 0:
                StringBuilder sb = new StringBuilder("[");
                g0.n nVar = (g0.n) this.f1603f;
                q0 q0Var = (q0) this.f1602e;
                if (nVar == q0Var) {
                    sb.append("]");
                } else {
                    while (true) {
                        if (nVar != null && nVar != q0Var) {
                            sb.append(String.valueOf(nVar));
                            if (nVar.f14688q == q0Var) {
                                sb.append("]");
                            } else {
                                sb.append(",");
                                nVar = nVar.f14688q;
                            }
                        }
                    }
                }
                String string = sb.toString();
                A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            default:
                return super.toString();
        }
    }

    public Y(C2486z c2486z) {
        this.f1600a = 1;
        this.f1601b = c2486z;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.f1602e = new ArrayList();
        this.f1604g = new ArrayList();
        this.f1605h = new C2475o();
        this.f1606i = new C2475o();
    }
}
