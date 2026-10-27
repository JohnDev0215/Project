package g8;

import F8.A;
import F8.AbstractC0287w;
import F8.Y;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0658k;
import Z7.EnumC0898a;
import Z7.u;
import c8.C1036a;
import java.util.ArrayList;
import java.util.Iterator;
import o8.C2060e;

/* JADX INFO: renamed from: g8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1325o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0658k f15023a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f15024b;
    public final N0.q c;
    public final EnumC0898a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f15025e;

    public C1325o(InterfaceC0658k interfaceC0658k, boolean z6, N0.q qVar, EnumC0898a enumC0898a, boolean z10) {
        A7.m.f(qVar, "containerContext");
        this.f15023a = interfaceC0658k;
        this.f15024b = z6;
        this.c = qVar;
        this.d = enumC0898a;
        this.f15025e = z10;
    }

    public static void a(Object obj, ArrayList arrayList, S0.p pVar) {
        arrayList.add(obj);
        Iterable iterable = (Iterable) pVar.b(obj);
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                a(it.next(), arrayList, pVar);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00bf  */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.lang.Iterable, java.util.Collection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static g8.C1319i b(Q7.Q r4) {
        /*
            java.lang.String r0 = "<this>"
            A7.m.f(r4, r0)
            boolean r1 = r4 instanceof d8.C1113E
            r2 = 0
            if (r1 != 0) goto Lb
            return r2
        Lb:
            java.util.List r4 = r4.getUpperBounds()
            java.lang.String r1 = "this.upperBounds"
            A7.m.e(r4, r1)
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L1c
            goto Lc3
        L1c:
            java.util.Iterator r1 = r4.iterator()
        L20:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto Lc3
            java.lang.Object r3 = r1.next()
            I8.c r3 = (I8.c) r3
            boolean r3 = G8.g.B(r3)
            if (r3 != 0) goto L20
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L39
            goto L51
        L39:
            java.util.Iterator r1 = r4.iterator()
        L3d:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L51
            java.lang.Object r3 = r1.next()
            I8.c r3 = (I8.c) r3
            g8.h r3 = d(r3)
            if (r3 == 0) goto L3d
            r1 = r4
            goto L97
        L51:
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L58
            goto Lc3
        L58:
            java.util.Iterator r1 = r4.iterator()
        L5c:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto Lc3
            java.lang.Object r3 = r1.next()
            I8.c r3 = (I8.c) r3
            A7.m.f(r3, r0)
            F8.w r3 = (F8.AbstractC0287w) r3
            F8.w r3 = F8.AbstractC0268c.e(r3)
            if (r3 == 0) goto L5c
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r4.iterator()
        L7c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L97
            java.lang.Object r3 = r2.next()
            I8.c r3 = (I8.c) r3
            A7.m.f(r3, r0)
            F8.w r3 = (F8.AbstractC0287w) r3
            F8.w r3 = F8.AbstractC0268c.e(r3)
            if (r3 == 0) goto L7c
            r1.add(r3)
            goto L7c
        L97:
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L9e
            goto Lb7
        L9e:
            java.util.Iterator r0 = r1.iterator()
        La2:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Lb7
            java.lang.Object r2 = r0.next()
            I8.c r2 = (I8.c) r2
            boolean r2 = G8.g.H(r2)
            if (r2 != 0) goto La2
            g8.h r0 = g8.EnumC1318h.f15008n
            goto Lb9
        Lb7:
            g8.h r0 = g8.EnumC1318h.f15007m
        Lb9:
            g8.i r2 = new g8.i
            if (r1 == r4) goto Lbf
            r4 = 1
            goto Lc0
        Lbf:
            r4 = 0
        Lc0:
            r2.<init>(r0, r4)
        Lc3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.C1325o.b(Q7.Q):g8.i");
    }

    public static C2060e c(A a10) {
        H8.f fVar = Y.f2457a;
        InterfaceC0654g interfaceC0654gN = a10.B0().n();
        InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
        if (interfaceC0652e != null) {
            return r8.d.g(interfaceC0652e);
        }
        return null;
    }

    public static EnumC1318h d(I8.c cVar) {
        G8.e eVar = G8.e.f2723m;
        if (G8.g.F(eVar.a0(cVar))) {
            return EnumC1318h.f15007m;
        }
        if (G8.g.F(eVar.W(cVar))) {
            return null;
        }
        return EnumC1318h.f15008n;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, m7.f] */
    public final ArrayList e(I8.c cVar) {
        N0.q qVar = this.c;
        u uVar = (u) qVar.f4401p.getValue();
        C1036a c1036a = (C1036a) qVar.f4399m;
        A7.m.f(cVar, "<this>");
        C1311a c1311a = new C1311a(cVar, c1036a.f12423q.b(uVar, ((AbstractC0287w) cVar).k()), null);
        S0.p pVar = new S0.p(this, 19);
        ArrayList arrayList = new ArrayList(1);
        a(c1311a, arrayList, pVar);
        return arrayList;
    }
}
