package androidx.compose.ui.focus;

import A7.m;
import A7.z;
import E.x;
import E0.V;
import F0.C0221p;
import F0.C0223q;
import F0.C0226s;
import F0.C0234w;
import F0.r;
import V.d;
import X5.F;
import g0.n;
import g0.o;
import l0.AbstractC1713d;
import l0.C1711b;
import l0.C1714e;
import l0.C1715f;
import l0.C1720k;
import l0.C1727r;
import l0.InterfaceC1716g;
import t4.e;
import u.C2479s;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC1716g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f11566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0223q f11567b;
    public final C0221p c;
    public final C0221p d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0226s f11568e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C1714e f11570g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public C2479s f11573j;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C1727r f11569f = new C1727r();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f11571h = new e(8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o f11572i = new FocusPropertiesElement(new C1720k()).k(new V() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$2
        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return this.f11562a.f11569f.hashCode();
        }

        @Override // E0.V
        public final n i() {
            return this.f11562a.f11569f;
        }

        @Override // E0.V
        public final /* bridge */ /* synthetic */ void l(n nVar) {
        }
    });

    public b(C0223q c0223q, r rVar, C0223q c0223q2, C0221p c0221p, C0221p c0221p2, C0226s c0226s) {
        this.f11566a = rVar;
        this.f11567b = c0223q2;
        this.c = c0221p;
        this.d = c0221p2;
        this.f11568e = c0226s;
        this.f11570g = new C1714e(c0223q, new F(0, this, b.class, "invalidateOwnerFocusState", "invalidateOwnerFocusState()V", 0, 9));
    }

    public final boolean b(int i7, boolean z6, boolean z10) {
        int iC;
        e eVar = this.f11571h;
        C1715f c1715f = C1715f.o;
        try {
            if (eVar.f22462m) {
                e.b(eVar);
            }
            eVar.f22462m = true;
            ((d) eVar.o).b(c1715f);
            C1727r c1727r = this.f11569f;
            boolean zE = (z6 || !((iC = AbstractC2641j.c(AbstractC1713d.t(c1727r, i7))) == 1 || iC == 2 || iC == 3)) ? AbstractC1713d.e(c1727r, z6, true) : false;
            if (zE && z10) {
                this.c.a();
            }
            return zE;
        } finally {
            e.c(eVar);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ab, code lost:
    
        if (((((~r10) << 6) & r10) & (-9187201950435737472L)) == 0) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00ad, code lost:
    
        r2 = r6.b(r7);
        r8 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00b5, code lost:
    
        if (r6.f22628e != 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00c9, code lost:
    
        if (((r6.f22626a[r2 >> 3] >> ((r2 & 7) << 3)) & 255) != 254) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00cb, code lost:
    
        r35 = r4;
        r37 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d1, code lost:
    
        r2 = r6.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00d5, code lost:
    
        if (r2 <= 8) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00e5, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r6.d) * 32, ((long) r2) * 25) > 0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00e7, code lost:
    
        r2 = r6.f22626a;
        r3 = r6.c;
        r10 = r6.f22627b;
        u.AbstractC2456D.a(r2, r3);
        r11 = 0;
        r15 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00f2, code lost:
    
        if (r11 == r3) goto L450;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00f4, code lost:
    
        r16 = r11 >> 3;
        r17 = (r11 & 7) << 3;
        r20 = (r2[r16] >> r17) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0105, code lost:
    
        if (r20 != r8) goto L449;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0107, code lost:
    
        r15 = r11;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0113, code lost:
    
        if (r20 == 254) goto L452;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0115, code lost:
    
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0118, code lost:
    
        r20 = java.lang.Long.hashCode(r10[r11]) * (-862048943);
        r20 = r20 ^ (r20 << 16);
        r14 = r20 >>> 7;
        r21 = r6.b(r14);
        r14 = r14 & r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x013f, code lost:
    
        if ((((r21 - r14) & r3) / 8) != (((r11 - r14) & r3) / 8)) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0141, code lost:
    
        r2[r16] = (r2[r16] & (~(255 << r17))) | (((long) (r20 & 127)) << r17);
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0164, code lost:
    
        r8 = 128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0167, code lost:
    
        r0 = r21 >> 3;
        r8 = r2[r0];
        r1 = (r21 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0179, code lost:
    
        if (((r8 >> r1) & 255) != 128) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x017b, code lost:
    
        r35 = r4;
        r2[r0] = ((~(255 << r1)) & r8) | (((long) (r20 & 127)) << r1);
        r2[r16] = (r2[r16] & (~(255 << r17))) | (128 << r17);
        r10[r21] = r10[r11];
        r10[r11] = 0;
        r15 = r11;
        r37 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x01a0, code lost:
    
        r35 = r4;
        r37 = r12;
        r2[r0] = (((long) (r20 & 127)) << r1) | (r8 & (~(255 << r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x01b2, code lost:
    
        if (r15 != (-1)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x01b4, code lost:
    
        r15 = u.AbstractC2456D.b(r2, r11 + 1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x01ba, code lost:
    
        r10[r15] = r10[r21];
        r10[r21] = r10[r11];
        r10[r11] = r10[r15];
        r11 = r11 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x01c8, code lost:
    
        r2[r2.length - 1] = (r2[0] & 72057594037927935L) | Long.MIN_VALUE;
        r11 = r11 + 1;
        r4 = r35;
        r12 = r37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x01de, code lost:
    
        r35 = r4;
        r37 = r12;
        r6.f22628e = u.AbstractC2456D.c(r6.c) - r6.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x01ef, code lost:
    
        r35 = r4;
        r37 = r12;
        r0 = u.AbstractC2456D.d(r6.c);
        r1 = r6.f22626a;
        r2 = r6.f22627b;
        r3 = r6.c;
        r6.c(r0);
        r0 = r6.f22626a;
        r4 = r6.f22627b;
        r5 = r6.c;
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0209, code lost:
    
        if (r8 >= r3) goto L458;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x021b, code lost:
    
        if (((r1[r8 >> 3] >> ((r8 & 7) << 3)) & 255) >= 128) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x021d, code lost:
    
        r9 = r2[r8];
        r11 = java.lang.Long.hashCode(r9) * (-862048943);
        r11 = r11 ^ (r11 << 16);
        r12 = r6.b(r11 >>> 7);
        r14 = r11 & 127;
        r11 = r12 >> 3;
        r13 = (r12 & 7) << 3;
        r21 = r1;
        r22 = r2;
        r1 = (r0[r11] & (~(255 << r13))) | (r14 << r13);
        r0[r11] = r1;
        r0[(((r12 - 7) & r5) + (r5 & 7)) >> 3] = r1;
        r4[r12] = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0258, code lost:
    
        r21 = r1;
        r22 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x025c, code lost:
    
        r8 = r8 + 1;
        r1 = r21;
        r2 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0263, code lost:
    
        r31 = r6.b(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x026a, code lost:
    
        r31 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x026c, code lost:
    
        r6.d++;
        r0 = r6.f22628e;
        r1 = r6.f22626a;
        r2 = r31 >> 3;
        r3 = r1[r2];
        r5 = (r31 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0287, code lost:
    
        if (((r3 >> r5) & 255) != 128) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0289, code lost:
    
        r7 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x028b, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x028c, code lost:
    
        r6.f22628e = r0 - r7;
        r0 = r6.c;
        r3 = (r3 & (~(255 << r5))) | (r37 << r5);
        r1[r2] = r3;
        r1[(((r31 - 7) & r0) + (r0 & 7)) >> 3] = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x032f, code lost:
    
        if (((r8 & ((~r8) << 6)) & (-9187201950435737472L)) == 0) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0331, code lost:
    
        r12 = -1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x064e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x037d  */
    /* JADX WARN: Type inference failed for: r0v105 */
    /* JADX WARN: Type inference failed for: r0v106 */
    /* JADX WARN: Type inference failed for: r0v19, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v20, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v44, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v48 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v50 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v24, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v30, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r1v31 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v37, types: [V.d] */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v42, types: [V.d] */
    /* JADX WARN: Type inference failed for: r6v43 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v48 */
    /* JADX WARN: Type inference failed for: r6v49 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v62 */
    /* JADX WARN: Type inference failed for: r6v63 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [V.d] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r7v33 */
    /* JADX WARN: Type inference failed for: r7v34, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v40 */
    /* JADX WARN: Type inference failed for: r7v43 */
    /* JADX WARN: Type inference failed for: r7v44 */
    /* JADX WARN: Type inference failed for: r7v45 */
    /* JADX WARN: Type inference failed for: r7v46, types: [V.d] */
    /* JADX WARN: Type inference failed for: r7v73 */
    /* JADX WARN: Type inference failed for: r7v74 */
    /* JADX WARN: Type inference failed for: r7v75 */
    /* JADX WARN: Type inference failed for: r7v76 */
    /* JADX WARN: Type inference failed for: r7v77 */
    /* JADX WARN: Type inference failed for: r7v78 */
    /* JADX WARN: Type inference failed for: r7v79 */
    /* JADX WARN: Type inference failed for: r7v80 */
    /* JADX WARN: Type inference failed for: r7v81 */
    /* JADX WARN: Type inference failed for: r7v82 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v25, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v79 */
    /* JADX WARN: Type inference failed for: r8v80 */
    /* JADX WARN: Type inference failed for: r8v81 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c(android.view.KeyEvent r41, z7.InterfaceC2772a r42) {
        /*
            Method dump skipped, instruction units count: 1629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.b.c(android.view.KeyEvent, z7.a):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070 A[PHI: r11
  0x0070: PHI (r11v16 l0.n) = (r11v13 l0.n), (r11v20 l0.n) binds: [B:43:0x0090, B:28:0x006b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object, z7.k] */
    /* JADX WARN: Type inference failed for: r11v8, types: [java.lang.Object, z7.k] */
    /* JADX WARN: Type inference failed for: r15v4, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9, types: [V.d] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Boolean d(int r19, m0.d r20, z7.InterfaceC2782k r21) {
        /*
            Method dump skipped, instruction units count: 572
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.b.d(int, m0.d, z7.k):java.lang.Boolean");
    }

    public final boolean e(int i7) {
        z zVar = new z();
        zVar.l = Boolean.FALSE;
        Boolean boolD = d(i7, (m0.d) this.d.a(), new x(i7, 3, zVar));
        if (boolD == null || zVar.l == null) {
            return false;
        }
        Boolean bool = Boolean.TRUE;
        if (boolD.equals(bool) && m.a(zVar.l, bool)) {
            return true;
        }
        if (!(C1711b.a(i7, 1) ? true : C1711b.a(i7, 2))) {
            return ((Boolean) this.f11567b.b(new C1711b(i7))).booleanValue();
        }
        if (!b(i7, false, false)) {
            return false;
        }
        Boolean boolD2 = d(i7, null, new C0234w(i7, 1));
        return boolD2 != null ? boolD2.booleanValue() : false;
    }
}
