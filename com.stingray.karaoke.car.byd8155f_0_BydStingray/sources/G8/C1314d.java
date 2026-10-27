package g8;

import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0282q;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.a0;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import T.C0741g;
import Z7.EnumC0898a;
import b8.InterfaceC1006a;
import e8.C1240e;
import java.util.ArrayList;
import java.util.Collection;
import n7.AbstractC2000o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1314d {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d7  */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static g8.C1313c c(F8.A r17, T.C0741g r18, int r19, int r20, boolean r21, boolean r22) {
        /*
            Method dump skipped, instruction units count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.C1314d.c(F8.A, T.g, int, int, boolean, boolean):g8.c");
    }

    public static C1.j d(a0 a0Var, C0741g c0741g, int i7, boolean z6) {
        a0 a0VarJ;
        Object objA = null;
        if (AbstractC0268c.i(a0Var)) {
            return new C1.j((Object) null, 1);
        }
        if (!(a0Var instanceof AbstractC0282q)) {
            if (!(a0Var instanceof A)) {
                throw new A9.k();
            }
            C1313c c1313cC = c((A) a0Var, c0741g, i7, 3, false, z6);
            boolean z10 = c1313cC.c;
            AbstractC0287w abstractC0287wA = c1313cC.f14999a;
            if (z10) {
                abstractC0287wA = AbstractC0268c.A(a0Var, abstractC0287wA);
            }
            return new C1.j(abstractC0287wA, c1313cC.f15000b);
        }
        boolean z11 = a0Var instanceof C1240e;
        AbstractC0282q abstractC0282q = (AbstractC0282q) a0Var;
        C1313c c1313cC2 = c(abstractC0282q.f2477m, c0741g, i7, 1, z11, z6);
        C1313c c1313cC3 = c(abstractC0282q.f2478n, c0741g, i7, 2, z11, z6);
        A a10 = c1313cC3.f14999a;
        A a11 = c1313cC2.f14999a;
        if (a11 != null || a10 != null) {
            if (c1313cC2.c || c1313cC3.c) {
                if (a10 != null) {
                    if (a11 == null) {
                        a11 = a10;
                    }
                    a0VarJ = C0269d.j(a11, a10);
                } else {
                    A7.m.c(a11);
                    a0VarJ = a11;
                }
                objA = AbstractC0268c.A(a0Var, a0VarJ);
            } else {
                A a12 = abstractC0282q.f2478n;
                A a13 = abstractC0282q.f2477m;
                A a14 = a11;
                if (z11) {
                    A a15 = a11;
                    if (a11 == null) {
                        a15 = a13;
                    }
                    if (a10 == null) {
                        a10 = a12;
                    }
                    objA = new C1240e(a15, a10);
                } else {
                    if (a11 == null) {
                        a14 = a13;
                    }
                    if (a10 == null) {
                        a10 = a12;
                    }
                    objA = C0269d.j(a14, a10);
                }
            }
        }
        return new C1.j(objA, c1313cC2.f15000b);
    }

    public AbstractC0287w a(InterfaceC1006a interfaceC1006a, InterfaceC0649b interfaceC0649b, boolean z6, N0.q qVar, EnumC0898a enumC0898a, C1326p c1326p, boolean z10, InterfaceC2782k interfaceC2782k) {
        C1325o c1325o = new C1325o(interfaceC0649b, z6, qVar, enumC0898a, false);
        AbstractC0287w abstractC0287w = (AbstractC0287w) interfaceC2782k.b(interfaceC1006a);
        Collection collectionQ = interfaceC1006a.q();
        A7.m.e(collectionQ, "overriddenDescriptors");
        Collection<InterfaceC0650c> collection = collectionQ;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(collection, 10));
        for (InterfaceC0650c interfaceC0650c : collection) {
            A7.m.e(interfaceC0650c, "it");
            arrayList.add((AbstractC0287w) interfaceC2782k.b(interfaceC0650c));
        }
        return b(c1325o, abstractC0287w, arrayList, c1326p, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0425  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public F8.AbstractC0287w b(g8.C1325o r30, F8.AbstractC0287w r31, java.util.List r32, g8.C1326p r33, boolean r34) {
        /*
            Method dump skipped, instruction units count: 1119
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.C1314d.b(g8.o, F8.w, java.util.List, g8.p, boolean):F8.w");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x029d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0319 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0208  */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v9, types: [b8.a] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [Q7.b] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r25v0, types: [g8.d] */
    /* JADX WARN: Type inference failed for: r4v3, types: [Q7.b, Q7.c, Q7.j] */
    /* JADX WARN: Type inference failed for: r6v38 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.ArrayList e(N0.q r26, java.util.Collection r27) {
        /*
            Method dump skipped, instruction units count: 817
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g8.C1314d.e(N0.q, java.util.Collection):java.util.ArrayList");
    }
}
