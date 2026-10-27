package g1;

import C0.J;
import C0.U;
import C0.V;
import h3.y;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import p1.C2074b;
import p1.C2078f;
import p1.InterfaceC2075c;
import u.AbstractC2473m;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class p implements InterfaceC2075c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o1.e f14740a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f14741b;
    public final LinkedHashMap c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q f14742e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f14743f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f14744g;

    public p(a1.b bVar) {
        o1.e eVar = new o1.e(0, 0);
        eVar.f20332r0 = new ArrayList();
        eVar.f20333s0 = new y(eVar);
        eVar.f20334t0 = new C2078f(eVar);
        eVar.f20336v0 = null;
        eVar.f20337w0 = false;
        eVar.f20338x0 = new h1.c();
        eVar.f20321A0 = 0;
        eVar.B0 = 0;
        eVar.f20322C0 = new o1.b[4];
        eVar.f20323D0 = new o1.b[4];
        eVar.f20324E0 = 257;
        eVar.f20325F0 = false;
        eVar.G0 = false;
        eVar.f20326H0 = null;
        eVar.f20327I0 = null;
        eVar.f20328J0 = null;
        eVar.f20329K0 = null;
        eVar.f20330L0 = new HashSet();
        eVar.f20331M0 = new C2074b();
        eVar.f20336v0 = this;
        eVar.f20334t0.f20658h = this;
        this.f14740a = eVar;
        this.f14741b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.f14742e = new q(bVar);
        this.f14743f = new int[2];
        this.f14744g = new int[2];
        new ArrayList();
    }

    public static void d(int i7, int i10, int i11, int i12, boolean z6, boolean z10, int i13, int[] iArr) {
        int iC = AbstractC2641j.c(i7);
        if (iC == 0) {
            iArr[0] = i10;
            iArr[1] = i10;
            return;
        }
        if (iC == 1) {
            iArr[0] = 0;
            iArr[1] = i13;
            return;
        }
        if (iC != 2) {
            if (iC != 3) {
                throw new IllegalStateException((i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? "null" : "MATCH_PARENT" : "MATCH_CONSTRAINT" : "WRAP_CONTENT" : "FIXED").concat(" is not supported").toString());
            }
            iArr[0] = i13;
            iArr[1] = i13;
            return;
        }
        boolean z11 = z10 || ((i12 == 1 || i12 == 2) && (i12 == 2 || i11 != 1 || z6));
        iArr[0] = z11 ? i10 : 0;
        if (!z11) {
            i10 = i13;
        }
        iArr[1] = i10;
    }

    @Override // p1.InterfaceC2075c
    public final void a() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0188  */
    @Override // p1.InterfaceC2075c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(o1.d r25, p1.C2074b r26) {
        /*
            Method dump skipped, instruction units count: 481
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.p.b(o1.d, p1.b):void");
    }

    public final long c(o1.d dVar, long j3) {
        Object obj = dVar.f20293g0;
        String str = dVar.f20300k;
        int i7 = 0;
        if (dVar instanceof o1.k) {
            int i10 = a1.a.f(j3) ? 1073741824 : a1.a.d(j3) ? Integer.MIN_VALUE : 0;
            if (a1.a.e(j3)) {
                i7 = 1073741824;
            } else if (a1.a.c(j3)) {
                i7 = Integer.MIN_VALUE;
            }
            o1.k kVar = (o1.k) dVar;
            kVar.X(i10, a1.a.h(j3), i7, a1.a.g(j3));
            return AbstractC2473m.c(kVar.f20394y0, kVar.f20395z0);
        }
        if (obj instanceof J) {
            V vB = ((J) obj).b(j3);
            this.f14741b.put(obj, vB);
            return AbstractC2473m.c(vB.l, vB.f871m);
        }
        h6.n.t("CCL", "Nothing to measure for widget: " + str);
        return AbstractC2473m.c(0, 0);
    }

    public final void e(U u4, List list) {
        J j3;
        V v10;
        Object next;
        LinkedHashMap linkedHashMap = this.d;
        if (linkedHashMap.isEmpty()) {
            ArrayList arrayList = this.f14740a.f20332r0;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                o1.d dVar = (o1.d) arrayList.get(i7);
                Object obj = dVar.f20293g0;
                if (obj instanceof J) {
                    l1.k kVar = dVar.f20298j;
                    o1.d dVar2 = kVar.f18438a;
                    if (dVar2 != null) {
                        kVar.f18439b = dVar2.s();
                        kVar.c = dVar2.t();
                        dVar2.s();
                        dVar2.t();
                        kVar.a(dVar2.f20298j);
                    }
                    linkedHashMap.put(obj, new l1.k(kVar));
                }
            }
        }
        int size2 = list.size();
        for (int i10 = 0; i10 < size2; i10++) {
            J j10 = (J) list.get(i10);
            if (linkedHashMap.containsKey(j10)) {
                j3 = j10;
            } else {
                Iterator it = linkedHashMap.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    J j11 = (J) next;
                    if (androidx.compose.ui.layout.a.a(j11) != null && A7.m.a(androidx.compose.ui.layout.a.a(j11), androidx.compose.ui.layout.a.a(j10))) {
                        break;
                    }
                }
                j3 = (J) next;
                if (j3 == null) {
                    continue;
                }
            }
            l1.k kVar2 = (l1.k) linkedHashMap.get(j3);
            if (kVar2 == null || (v10 = (V) this.f14741b.get(j3)) == null) {
                return;
            }
            if (linkedHashMap.containsKey(j10)) {
                l9.l.X(u4, v10, kVar2);
            } else {
                int i11 = v10.l;
                int i12 = v10.f871m;
                if (i11 < 0 || i12 < 0) {
                    w0.c.e0("width(" + i11 + ") and height(" + i12 + ") must be >= 0");
                    throw null;
                }
                l9.l.X(u4, j10.b(io.sentry.config.a.U(i11, i11, i12, i12)), kVar2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015f A[EDGE_INSN: B:117:0x015f->B:51:0x015f BREAK  A[LOOP:2: B:43:0x0137->B:119:0x0137], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7 A[LOOP:1: B:39:0x00d1->B:41:0x00d7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0230  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long f(long r16, a1.k r18, g1.C1285l r19, java.util.List r20) {
        /*
            Method dump skipped, instruction units count: 760
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g1.p.f(long, a1.k, g1.l, java.util.List):long");
    }
}
