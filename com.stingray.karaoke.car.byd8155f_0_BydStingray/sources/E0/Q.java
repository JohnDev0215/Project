package E0;

import C0.C0107n;
import java.util.Map;
import u.C2480t;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q extends C0.V implements C0.M, W {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1575q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1576r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1577s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0.H f1578t = new C0.H(this, 0);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public C2480t f1579u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C2480t f1580v;

    public static void C0(d0 d0Var) {
        G g7;
        d0 d0Var2 = d0Var.f1649x;
        F f10 = d0Var2 != null ? d0Var2.f1648w : null;
        F f11 = d0Var.f1648w;
        if (!A7.m.a(f10, f11)) {
            f11.t().f1568r.f1529F.f();
            return;
        }
        InterfaceC0151a interfaceC0151aR = f11.t().f1568r.r();
        if (interfaceC0151aR == null || (g7 = ((L) interfaceC0151aR).f1529F) == null) {
            return;
        }
        g7.f();
    }

    public abstract Q A0();

    public abstract long B0();

    @Override // C0.M
    public final C0.L C(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k) {
        if ((i7 & (-16777216)) == 0 && ((-16777216) & i10) == 0) {
            return new O(i7, i10, map, interfaceC2782k, this);
        }
        l9.d.h0("Size(" + i7 + " x " + i10 + ") is out of range. Each dimension must be between 0 and 16777215.");
        throw null;
    }

    public abstract void D0();

    @Override // E0.W
    public final void H(boolean z6) {
        this.f1575q = z6;
    }

    @Override // C0.V
    public final int e0(C0107n c0107n) {
        int iQ0;
        if (x0() && (iQ0 = q0(c0107n)) != Integer.MIN_VALUE) {
            return iQ0 + ((int) (this.f873p & 4294967295L));
        }
        return Integer.MIN_VALUE;
    }

    public abstract int q0(C0107n c0107n);

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00f0, code lost:
    
        r36 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00fe, code lost:
    
        if (((r5 & ((~r5) << 6)) & (-9187201950435737472L)) == 0) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0100, code lost:
    
        r4 = r2.b(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0106, code lost:
    
        if (r2.f22632f != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x011a, code lost:
    
        if (((r2.f22629a[r4 >> 3] >> ((r4 & 7) << 3)) & 255) != 254) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x011c, code lost:
    
        r49 = r0;
        r1 = r2;
        r37 = r3;
        r42 = r7;
        r14 = r10;
        r47 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0128, code lost:
    
        r4 = r2.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x012c, code lost:
    
        if (r4 <= 8) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x013e, code lost:
    
        if (java.lang.Long.compareUnsigned(((long) r2.f22631e) * 32, ((long) r4) * 25) > 0) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0140, code lost:
    
        r4 = r2.f22629a;
        r5 = r2.d;
        r6 = r2.f22630b;
        r13 = r2.c;
        u.AbstractC2456D.a(r4, r5);
        r37 = r3;
        r3 = -1;
        r15 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x014f, code lost:
    
        if (r15 == r5) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0151, code lost:
    
        r24 = r15 >> 3;
        r29 = (r15 & 7) << 3;
        r39 = (r4[r24] >> r29) & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0163, code lost:
    
        if (r39 != 128) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0165, code lost:
    
        r53 = r15;
        r15 = r15 + 1;
        r3 = r53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0171, code lost:
    
        if (r39 == 254) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0173, code lost:
    
        r15 = r15 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0176, code lost:
    
        r36 = r6[r15];
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0178, code lost:
    
        if (r36 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x017a, code lost:
    
        r36 = r36.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x017f, code lost:
    
        r36 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0181, code lost:
    
        r36 = r36 * (-862048943);
        r36 = r36 ^ (r36 << 16);
        r14 = r36 >>> 7;
        r40 = r2.b(r14);
        r14 = r14 & r5;
        r42 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x01a4, code lost:
    
        if ((((r40 - r14) & r5) / 8) != (((r15 - r14) & r5) / 8)) goto L157;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x01a6, code lost:
    
        r4[r24] = (r4[r24] & (~(255 << r29))) | (((long) (r36 & 127)) << r29);
        r4[r4.length - 1] = (r4[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r10 = r10;
        r7 = r42;
        r11 = r11;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x01d3, code lost:
    
        r49 = r0;
        r14 = r10;
        r47 = r11;
        r0 = r40 >> 3;
        r10 = r4[r0];
        r1 = (r40 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x01ea, code lost:
    
        if (((r10 >> r1) & 255) != 128) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x01ec, code lost:
    
        r41 = r2;
        r4[r0] = ((~(255 << r1)) & r10) | (((long) (r36 & 127)) << r1);
        r4[r24] = (r4[r24] & (~(255 << r29))) | (128 << r29);
        r6[r40] = r6[r15];
        r6[r15] = null;
        r13[r40] = r13[r15];
        r13[r15] = 0.0f;
        r51 = r8;
        r2 = r13;
        r3 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x021c, code lost:
    
        r41 = r2;
        r51 = r8;
        r2 = r13;
        r4[r0] = (((long) (r36 & 127)) << r1) | (r10 & (~(255 << r1)));
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0230, code lost:
    
        if (r3 != (-1)) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0232, code lost:
    
        r3 = u.AbstractC2456D.b(r4, r15 + 1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0238, code lost:
    
        r6[r3] = r6[r40];
        r6[r40] = r6[r15];
        r6[r15] = r6[r3];
        r2[r3] = r2[r40];
        r2[r40] = r2[r15];
        r2[r15] = r2[r3];
        r15 = r15 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0252, code lost:
    
        r4[r4.length - 1] = (r4[0] & 72057594037927935L) | Long.MIN_VALUE;
        r15 = r15 + 1;
        r13 = r2;
        r10 = r14;
        r2 = r41;
        r7 = r42;
        r11 = r47;
        r0 = r49;
        r8 = r51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x026e, code lost:
    
        r49 = r0;
        r1 = r2;
        r42 = r7;
        r51 = r8;
        r14 = r10;
        r47 = r11;
        r1.f22632f = u.AbstractC2456D.c(r1.d) - r1.f22631e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0288, code lost:
    
        r49 = r0;
        r1 = r2;
        r37 = r3;
        r42 = r7;
        r51 = r8;
        r14 = r10;
        r47 = r11;
        r7 = 0;
        r0 = u.AbstractC2456D.d(r1.d);
        r2 = r1.f22629a;
        r3 = r1.f22630b;
        r4 = r1.c;
        r5 = r1.d;
        r1.d(r0);
        r0 = r1.f22629a;
        r6 = r1.f22630b;
        r8 = r1.c;
        r10 = r1.d;
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x02af, code lost:
    
        if (r11 >= r5) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x02c2, code lost:
    
        if (((r2[r11 >> 3] >> ((r11 & 7) << 3)) & 255) >= 128) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x02c4, code lost:
    
        r12 = r3[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x02c6, code lost:
    
        if (r12 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x02c8, code lost:
    
        r13 = r12.hashCode();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x02cd, code lost:
    
        r13 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x02ce, code lost:
    
        r13 = r13 * (-862048943);
        r13 = r13 ^ (r13 << 16);
        r15 = r1.b(r13 >>> 7);
        r24 = r8;
        r7 = r13 & 127;
        r13 = r15 >> 3;
        r29 = (r15 & 7) << 3;
        r36 = r2;
        r39 = r3;
        r2 = (r0[r13] & (~(255 << r29))) | (r7 << r29);
        r0[r13] = r2;
        r0[(((r15 - 7) & r10) + (r10 & 7)) >> 3] = r2;
        r6[r15] = r12;
        r24[r15] = r4[r11];
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0308, code lost:
    
        r36 = r2;
        r39 = r3;
        r24 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x030e, code lost:
    
        r11 = r11 + 1;
        r8 = r24;
        r2 = r36;
        r3 = r39;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0318, code lost:
    
        r4 = r1.b(r51);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x031c, code lost:
    
        r1.f22631e++;
        r0 = r1.f22632f;
        r2 = r1.f22629a;
        r3 = r4 >> 3;
        r5 = r2[r3];
        r7 = (r4 & 7) << 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0337, code lost:
    
        if (((r5 >> r7) & 255) != 128) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x033a, code lost:
    
        r32 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x033c, code lost:
    
        r1.f22632f = r0 - r32;
        r0 = r1.d;
        r5 = (r5 & (~(255 << r7))) | (r49 << r7);
        r2[r3] = r5;
        r2[(((r4 - 7) & r0) + (r0 & 7)) >> 3] = r5;
        r0 = ~r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t0(E0.m0 r55) {
        /*
            Method dump skipped, instruction units count: 1177
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E0.Q.t0(E0.m0):void");
    }

    @Override // C0.InterfaceC0108o
    public boolean v() {
        return false;
    }

    public abstract Q v0();

    public abstract C0.r w0();

    public abstract boolean x0();

    public abstract F y0();

    public abstract C0.L z0();
}
