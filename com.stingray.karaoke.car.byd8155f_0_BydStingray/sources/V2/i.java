package V2;

import B2.J;
import B2.L;
import B2.t;
import B5.u;
import android.util.SparseArray;
import d5.C1078G;
import d5.I;
import d5.d0;
import e2.C1203k;
import e2.C1204l;
import e2.C1207o;
import e2.C1208p;
import e2.M;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class i implements B2.r {

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final byte[] f9350J = {-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, -12};

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final C1208p f9351K;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f9352A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f9353C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public boolean f9354D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f9355E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public t f9356F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public L[] f9357G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public L[] f9358H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f9359I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y2.j f9360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f9361b;
    public final List c;
    public final SparseArray d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h2.t f9362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h2.t f9363f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h2.t f9364g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final byte[] f9365h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h2.t f9366i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C1489n1 f9367j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final h2.t f9368k;
    public final ArrayDeque l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayDeque f9369m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i2.q f9370n;
    public d0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9371p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9372q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f9373r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f9374s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public h2.t f9375t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f9376u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f9377v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f9378w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f9379x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f9380y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public h f9381z;

    static {
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l("application/x-emsg");
        f9351K = new C1208p(c1207o);
    }

    public i(Y2.j jVar, int i7) {
        C1078G c1078g = I.f13384m;
        d0 d0Var = d0.f13415p;
        this.f9360a = jVar;
        this.f9361b = i7;
        this.c = Collections.unmodifiableList(d0Var);
        this.f9367j = new C1489n1(8);
        this.f9368k = new h2.t(16);
        this.f9362e = new h2.t(i2.n.f15713a);
        this.f9363f = new h2.t(5);
        this.f9364g = new h2.t();
        byte[] bArr = new byte[16];
        this.f9365h = bArr;
        this.f9366i = new h2.t(bArr);
        this.l = new ArrayDeque();
        this.f9369m = new ArrayDeque();
        this.d = new SparseArray();
        this.o = d0Var;
        this.f9379x = -9223372036854775807L;
        this.f9378w = -9223372036854775807L;
        this.f9380y = -9223372036854775807L;
        this.f9356F = t.f599a;
        this.f9357G = new L[0];
        this.f9358H = new L[0];
        this.f9370n = new i2.q(new f(this));
    }

    public static C1204l d(ArrayList arrayList) {
        int size = arrayList.size();
        ArrayList arrayList2 = null;
        for (int i7 = 0; i7 < size; i7++) {
            i2.c cVar = (i2.c) arrayList.get(i7);
            if (cVar.f15673m == 1886614376) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList();
                }
                byte[] bArr = cVar.f15672n.f15241a;
                u uVarH = o.h(bArr);
                UUID uuid = uVarH == null ? null : (UUID) uVarH.f736n;
                if (uuid == null) {
                    AbstractC1346c.z("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList2.add(new C1203k(uuid, null, "video/mp4", bArr));
                }
            }
        }
        if (arrayList2 == null) {
            return null;
        }
        return new C1204l(null, false, (C1203k[]) arrayList2.toArray(new C1203k[0]));
    }

    public static void h(h2.t tVar, int i7, r rVar) throws M {
        tVar.H(i7 + 8);
        int iH = tVar.h();
        byte[] bArr = d.f9336a;
        if ((iH & 1) != 0) {
            throw M.b("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z6 = (iH & 2) != 0;
        int iZ = tVar.z();
        if (iZ == 0) {
            Arrays.fill(rVar.l, 0, rVar.f9434e, false);
            return;
        }
        if (iZ != rVar.f9434e) {
            StringBuilder sbJ = M5.b.j("Senc sample count ", iZ, " is different from fragment sample count");
            sbJ.append(rVar.f9434e);
            throw M.a(null, sbJ.toString());
        }
        Arrays.fill(rVar.l, 0, iZ, z6);
        int iA = tVar.a();
        h2.t tVar2 = rVar.f9442n;
        tVar2.E(iA);
        rVar.f9440k = true;
        rVar.o = true;
        tVar.f(0, tVar2.c, tVar2.f15241a);
        tVar2.H(0);
        rVar.o = false;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        int i7;
        int i10 = this.f9361b;
        if ((i10 & 32) == 0) {
            tVar = new P(tVar, this.f9360a);
        }
        this.f9356F = tVar;
        int i11 = 0;
        this.f9371p = 0;
        this.f9374s = 0;
        L[] lArr = new L[2];
        this.f9357G = lArr;
        int i12 = 100;
        if ((i10 & 4) != 0) {
            lArr[0] = tVar.u(100, 5);
            i7 = 1;
            i12 = 101;
        } else {
            i7 = 0;
        }
        L[] lArr2 = (L[]) AbstractC1343A.J(i7, this.f9357G);
        this.f9357G = lArr2;
        for (L l : lArr2) {
            l.d(f9351K);
        }
        List list = this.c;
        this.f9358H = new L[list.size()];
        while (i11 < this.f9358H.length) {
            L lU = this.f9356F.u(i12, 3);
            lU.d((C1208p) list.get(i11));
            this.f9358H[i11] = lU;
            i11++;
            i12++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01fb, code lost:
    
        if (e2.L.a(r6, "video/avc") == false) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01fe, code lost:
    
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0202, code lost:
    
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0207, code lost:
    
        if ((r2 & 31) == 6) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x020f, code lost:
    
        if (java.util.Objects.equals(r7.f14316n, "video/hevc") != false) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x0215, code lost:
    
        if (e2.L.a(r6, "video/hevc") == false) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x021d, code lost:
    
        if (((r2 & 126) >> 1) != 39) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x021f, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0221, code lost:
    
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0224, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0225, code lost:
    
        r31.f9355E = r2;
        r31.B += 5;
        r31.f9352A += r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0234, code lost:
    
        if (r31.f9354D != false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0242, code lost:
    
        if (java.util.Objects.equals(r3.d.f9445a.f9424g.f14316n, "video/avc") == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x024b, code lost:
    
        if (i2.n.c(r5[4]) == false) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x024d, code lost:
    
        r31.f9354D = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0250, code lost:
    
        r7 = r17;
        r4 = r20;
        r6 = r21;
        r15 = r22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0258, code lost:
    
        r14 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0263, code lost:
    
        throw e2.M.a(null, "Invalid NAL length");
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x0264, code lost:
    
        r20 = r4;
        r21 = r6;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x026d, code lost:
    
        if (r31.f9355E == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x026f, code lost:
    
        r4 = r31.f9364g;
        r4.E(r2);
        r23 = r5;
        ((B2.C0073m) r32).c(r4.f15241a, 0, r31.f9353C, false);
        r13.a(r4, r31.f9353C, 0);
        r2 = r31.f9353C;
        r5 = i2.n.k(r4.c, r4.f15241a);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0296, code lost:
    
        if (java.util.Objects.equals(r7.f14316n, "video/hevc") != false) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x029e, code lost:
    
        if (e2.L.a(r7.f14314k, "video/hevc") == false) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x02a1, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x02a3, code lost:
    
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02a4, code lost:
    
        r4.H(r6);
        r4.G(r5);
        r5 = r7.f14317p;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x02ad, code lost:
    
        if (r5 != (-1)) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02b1, code lost:
    
        if (r9.f15716a == 0) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x02b3, code lost:
    
        r9.f15716a = 0;
        r9.c(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02bc, code lost:
    
        if (r9.f15716a == r5) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02be, code lost:
    
        if (r5 < 0) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02c0, code lost:
    
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02c2, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02c3, code lost:
    
        h2.AbstractC1346c.j(r6);
        r9.f15716a = r5;
        r9.c(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02cb, code lost:
    
        r9.a(r10, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02d4, code lost:
    
        if ((r3.a() & 4) == 0) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02d6, code lost:
    
        r9.c(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02dd, code lost:
    
        r23 = r5;
        r2 = r13.c(r32, r2, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x02e4, code lost:
    
        r31.B += r2;
        r31.f9353C -= r2;
        r7 = r17;
        r4 = r20;
        r6 = r21;
        r15 = r22;
        r5 = r23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x02fa, code lost:
    
        r17 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02fd, code lost:
    
        r17 = r7;
        r29 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x0301, code lost:
    
        r2 = r31.B;
        r4 = r31.f9352A;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0305, code lost:
    
        if (r2 >= r4) goto L418;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0307, code lost:
    
        r31.B += r13.c(r32, r4 - r2, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0313, code lost:
    
        r1 = r3.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0319, code lost:
    
        if ((r17 & 64) == 0) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x031d, code lost:
    
        if (r31.f9354D != false) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x031f, code lost:
    
        r1 = r1 | 67108864;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0322, code lost:
    
        r25 = r1;
        r1 = r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0328, code lost:
    
        if (r1 == null) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x032a, code lost:
    
        r28 = r1.c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x032f, code lost:
    
        r28 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0331, code lost:
    
        r13.b(r10, r25, r31.f9352A, 0, r28);
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0342, code lost:
    
        if (r29.isEmpty() != false) goto L414;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0344, code lost:
    
        r1 = (V2.g) r29.removeFirst();
        r31.f9377v -= r1.c;
        r2 = r1.f9340b;
        r4 = r1.f9339a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0355, code lost:
    
        if (r2 == false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0357, code lost:
    
        r4 = r4 + r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0358, code lost:
    
        r2 = r31.f9357G;
        r6 = r2.length;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x035c, code lost:
    
        if (r7 >= r6) goto L417;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x035e, code lost:
    
        r2[r7].b(r4, 1, r1.c, r31.f9377v, null);
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0376, code lost:
    
        if (r3.c() != false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0378, code lost:
    
        r31.f9381z = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x037b, code lost:
    
        r31.f9371p = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x037f, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00b4, code lost:
    
        r4 = r31.f9371p;
        r7 = r31.f9361b;
        r10 = r3.f9342b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00be, code lost:
    
        if (r4 != 3) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c2, code lost:
    
        if (r3.l != false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c4, code lost:
    
        r4 = r3.d.d[r3.f9344f];
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00cd, code lost:
    
        r4 = r10.f9437h[r3.f9344f];
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d3, code lost:
    
        r31.f9352A = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d7, code lost:
    
        if ((r7 & 64) == 0) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e5, code lost:
    
        if (java.util.Objects.equals(r3.d.f9445a.f9424g.f14316n, "video/avc") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e8, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ea, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00eb, code lost:
    
        r31.f9354D = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f1, code lost:
    
        if (r3.f9344f >= r3.f9347i) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00f3, code lost:
    
        ((B2.C0073m) r32).i(r31.f9352A);
        r1 = r3.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00fe, code lost:
    
        if (r1 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0101, code lost:
    
        r2 = r10.f9442n;
        r1 = r1.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0105, code lost:
    
        if (r1 == 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0107, code lost:
    
        r2.I(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x010a, code lost:
    
        r1 = r3.f9344f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x010e, code lost:
    
        if (r10.f9440k == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0114, code lost:
    
        if (r10.l[r1] == false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0116, code lost:
    
        r2.I(r2.B() * 6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0122, code lost:
    
        if (r3.c() != false) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0124, code lost:
    
        r31.f9381z = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0127, code lost:
    
        r31.f9371p = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0133, code lost:
    
        if (r3.d.f9445a.f9425h != r2) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0135, code lost:
    
        r31.f9352A -= 8;
        ((B2.C0073m) r32).i(8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0150, code lost:
    
        if ("audio/ac4".equals(r3.d.f9445a.f9424g.f14316n) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0152, code lost:
    
        r31.B = r3.d(r31.f9352A, 7);
        r4 = r31.f9352A;
        r12 = r31.f9366i;
        B2.AbstractC0062b.g(r4, r12);
        r3.f9341a.a(r12, 7, 0);
        r31.B += 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x016d, code lost:
    
        r31.B = r3.d(r31.f9352A, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0175, code lost:
    
        r31.f9352A += r31.B;
        r31.f9371p = 4;
        r31.f9353C = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0181, code lost:
    
        r4 = r3.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0185, code lost:
    
        if (r3.l != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0187, code lost:
    
        r10 = r4.f9448f[r3.f9344f];
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x018e, code lost:
    
        r10 = r10.f9438i[r3.f9344f];
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0194, code lost:
    
        r4 = r4.f9445a;
        r12 = r4.f9428k;
        r13 = r3.f9341a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x019a, code lost:
    
        if (r12 == 0) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x019c, code lost:
    
        r15 = r31.f9363f;
        r5 = r15.f15241a;
        r5[0] = 0;
        r5[r2] = 0;
        r5[2] = 0;
        r6 = r12 + 1;
        r12 = 4 - r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01ae, code lost:
    
        r29 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b4, code lost:
    
        if (r31.B >= r31.f9352A) goto L410;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b6, code lost:
    
        r2 = r31.f9353C;
        r17 = r7;
        r7 = r4.f9424g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01be, code lost:
    
        if (r2 != 0) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01c0, code lost:
    
        r20 = r4;
        ((B2.C0073m) r32).c(r5, r12, r6, false);
        r15.H(0);
        r2 = r15.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01d1, code lost:
    
        if (r2 < 1) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01d3, code lost:
    
        r31.f9353C = r2 - 1;
        r2 = r31.f9362e;
        r2.H(0);
        r21 = r6;
        r13.a(r2, 4, 0);
        r13.a(r15, 1, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01e9, code lost:
    
        if (r31.f9358H.length <= 0) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01eb, code lost:
    
        r2 = r5[4];
        r4 = java.util.Objects.equals(r7.f14316n, "video/avc");
        r6 = r7.f14314k;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01f5, code lost:
    
        if (r4 != false) goto L103;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r32, B2.v r33) throws e2.M, java.io.EOFException, java.io.InterruptedIOException {
        /*
            Method dump skipped, instruction units count: 2104
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.i.c(B2.s, B2.v):int");
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        SparseArray sparseArray = this.d;
        int size = sparseArray.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((h) sparseArray.valueAt(i7)).e();
        }
        this.f9369m.clear();
        this.f9377v = 0;
        this.f9370n.c(0);
        this.f9378w = j10;
        this.l.clear();
        this.f9371p = 0;
        this.f9374s = 0;
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) {
        d0 d0VarB;
        J jK = o.k(sVar, true, false);
        if (jK != null) {
            d0VarB = I.B(jK);
        } else {
            C1078G c1078g = I.f13384m;
            d0VarB = d0.f13415p;
        }
        this.o = d0VarB;
        return jK == null;
    }

    @Override // B2.r
    public final List g() {
        return this.o;
    }

    /* JADX WARN: Code restructure failed: missing block: B:341:0x07c7, code lost:
    
        r5 = r0;
        r5.f9371p = 0;
        r5.f9374s = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x07cd, code lost:
    
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:310:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0288  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(long r54) throws e2.M {
        /*
            Method dump skipped, instruction units count: 1998
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.i.i(long):void");
    }
}
