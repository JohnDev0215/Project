package V2;

import B2.F;
import B2.J;
import B2.M;
import d5.C1078G;
import d5.I;
import d5.d0;
import h2.AbstractC1343A;
import h2.t;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class l implements B2.r, F {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public k[] f9387A;
    public long[][] B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f9388C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public long f9389D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f9390E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Q2.a f9391F;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y2.j f9392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f9393b;
    public final t c;
    public final t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t f9394e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t f9395f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayDeque f9396g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final n f9397h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f9398i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d0 f9399j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9400k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f9401m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9402n;
    public t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9403p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9404q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9405r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f9406s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f9407t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f9408u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f9409v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f9410w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f9411x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f9412y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public B2.t f9413z;

    public l(Y2.j jVar, int i7) {
        this.f9392a = jVar;
        this.f9393b = i7;
        C1078G c1078g = I.f13384m;
        this.f9399j = d0.f13415p;
        this.f9400k = (i7 & 4) != 0 ? 3 : 0;
        this.f9397h = new n();
        this.f9398i = new ArrayList();
        this.f9395f = new t(16);
        this.f9396g = new ArrayDeque();
        this.c = new t(i2.n.f15713a);
        this.d = new t(5);
        this.f9394e = new t();
        this.f9403p = -1;
        this.f9413z = B2.t.f599a;
        this.f9387A = new k[0];
        this.f9407t = (i7 & 32) == 0;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        if ((this.f9393b & 16) == 0) {
            tVar = new P(tVar, this.f9392a);
        }
        this.f9413z = tVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01c7, code lost:
    
        r44.f600a = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01cd, code lost:
    
        r11.f9418b = r4;
        r44.f600a = ((V2.m) r14.get(0)).f9414a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01db, code lost:
    
        r4 = new h2.t(8);
        r43.readFully(r4.f15241a, r7, 8);
        r11.c = r4.j() + 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f6, code lost:
    
        if (r4.h() == 1397048916) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01f8, code lost:
    
        r44.f600a = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01fe, code lost:
    
        r44.f600a = r43.o() - ((long) (r11.c - 12));
        r11.f9418b = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x020f, code lost:
    
        r3 = r43.d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0215, code lost:
    
        if (r3 == (-1)) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0219, code lost:
    
        if (r3 >= 8) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
    
        if (r11 != r4) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x021c, code lost:
    
        r3 = r3 - 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x021f, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0221, code lost:
    
        r44.f600a = r3;
        r1 = 1;
        r11.f9418b = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x022c, code lost:
    
        if (r44.f600a != 0) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x022e, code lost:
    
        r42.f9400k = 0;
        r42.f9402n = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0233, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0239, code lost:
    
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        r3 = r42.f9398i;
        r11 = r42.f9397h;
        r12 = r11.f9418b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x023a, code lost:
    
        r4 = r43.o();
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x0241, code lost:
    
        if (r42.f9403p != (-1)) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0243, code lost:
    
        r8 = -1;
        r9 = -1;
        r10 = true;
        r11 = true;
        r12 = 0;
        r16 = Long.MAX_VALUE;
        r25 = Long.MAX_VALUE;
        r27 = Long.MAX_VALUE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0257, code lost:
    
        r3 = r42.f9387A;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x025a, code lost:
    
        if (r12 >= r3.length) goto L398;
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x025c, code lost:
    
        r3 = r3[r12];
        r13 = r3.f9386e;
        r3 = r3.f9385b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0264, code lost:
    
        if (r13 != r3.f9446b) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0268, code lost:
    
        r6 = r3.c[r13];
        r3 = r42.B;
        r18 = h2.AbstractC1343A.f15195a;
        r33 = r3[r12][r13];
        r6 = r6 - r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0279, code lost:
    
        if (r6 < 0) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
    
        if (r12 == 0) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x027d, code lost:
    
        if (r6 < 262144) goto L133;
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x0280, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0282, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0283, code lost:
    
        if (r3 != false) goto L137;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0285, code lost:
    
        if (r11 != false) goto L140;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0287, code lost:
    
        if (r3 != r11) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x028b, code lost:
    
        if (r6 >= r27) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        if (r12 == 1) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x028d, code lost:
    
        r11 = r3;
        r27 = r6;
        r9 = r12;
        r25 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0295, code lost:
    
        if (r33 >= r16) goto L127;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0297, code lost:
    
        r10 = r3;
        r8 = r12;
        r16 = r33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x029c, code lost:
    
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02a6, code lost:
    
        if (r16 == Long.MAX_VALUE) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02a8, code lost:
    
        if (r10 == false) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02b1, code lost:
    
        if (r25 >= (r16 + 10485760)) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r14 = r11.f9417a;
        r6 = 2816;
        r5 = 2192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x02b3, code lost:
    
        r8 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x02b4, code lost:
    
        r42.f9403p = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02b7, code lost:
    
        if (r8 != (-1)) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x02b9, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02bc, code lost:
    
        r3 = r42.f9387A[r42.f9403p];
        r13 = r3.c;
        r12 = r3.f9386e;
        r11 = r3.f9385b;
        r6 = r11.c[r12] + r42.f9412y;
        r8 = r11.d[r12];
        r4 = (r6 - r4) + ((long) r42.f9404q);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02dd, code lost:
    
        if (r4 < 0) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02e1, code lost:
    
        if (r4 < 262144) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02e5, code lost:
    
        r2 = r3.f9384a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
    
        if (r12 == r8) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02ea, code lost:
    
        if (r2.f9425h != 1) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x02ec, code lost:
    
        r4 = r4 + 8;
        r8 = r8 - 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02f0, code lost:
    
        r43.i((int) r4);
        r4 = r2.f9424g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02fe, code lost:
    
        if (java.util.Objects.equals(r4.f14316n, "video/avc") != false) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0300, code lost:
    
        r5 = 1;
        r42.f9407t = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0304, code lost:
    
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0305, code lost:
    
        r2 = r2.f9428k;
        r10 = r3.d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0309, code lost:
    
        if (r2 == 0) goto L184;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x030b, code lost:
    
        r4 = r42.d;
        r6 = r4.f15241a;
        r6[0] = 0;
        r6[r5] = 0;
        r6[2] = 0;
        r7 = r2 + 1;
        r9 = 4 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
    
        if (r12 != r4) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x031e, code lost:
    
        if (r42.f9405r >= r8) goto L400;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0320, code lost:
    
        r2 = r42.f9406s;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0322, code lost:
    
        if (r2 != 0) goto L401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x0324, code lost:
    
        r43.readFully(r6, r9, r7);
        r42.f9404q += r7;
        r4.H(0);
        r5 = r4.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0335, code lost:
    
        if (r5 < 1) goto L402;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0337, code lost:
    
        r42.f9406s = r5 - 1;
        r5 = r42.c;
        r5.H(0);
        r13.a(r5, 4, 0);
        r13.a(r4, 1, 0);
        r42.f9405r += 5;
        r8 = r8 + r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0350, code lost:
    
        if (r42.f9407t != false) goto L407;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0358, code lost:
    
        if (i2.n.c(r6[4]) == false) goto L408;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x035a, code lost:
    
        r42.f9407t = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        r17 = r43.o();
        r11 = (int) ((r43.d() - r43.o()) - ((long) r11.c));
        r12 = new h2.t(r11);
        r43.readFully(r12.f15241a, r7, r11);
        r1 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0365, code lost:
    
        throw e2.M.a(null, "Invalid NAL length");
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0366, code lost:
    
        r2 = r13.c(r43, r2, false);
        r42.f9404q += r2;
        r42.f9405r += r2;
        r42.f9406s -= r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x037b, code lost:
    
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0385, code lost:
    
        if ("audio/ac4".equals(r4.f14316n) == false) goto L191;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0389, code lost:
    
        if (r42.f9405r != 0) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x038b, code lost:
    
        B2.AbstractC0062b.g(r8, r15);
        r5 = 7;
        r13.a(r15, 7, 0);
        r42.f9405r += 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0399, code lost:
    
        r5 = 7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x039a, code lost:
    
        r8 = r8 + r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x039c, code lost:
    
        if (r10 == null) goto L411;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x039e, code lost:
    
        r10.c(r43);
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x03a1, code lost:
    
        r2 = r42.f9405r;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x03a3, code lost:
    
        if (r2 >= r8) goto L410;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x03a5, code lost:
    
        r2 = r13.c(r43, r8 - r2, false);
        r42.f9404q += r2;
        r42.f9405r += r2;
        r42.f9406s -= r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x03bc, code lost:
    
        r4 = r11.f9448f[r12];
        r2 = r11.f9449g[r12];
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x03c6, code lost:
    
        if (r42.f9407t != false) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x03c8, code lost:
    
        r2 = r2 | 67108864;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x03cb, code lost:
    
        if (r10 == null) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005c, code lost:
    
        if (r1 >= r14.size()) goto L393;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x03cd, code lost:
    
        r10.b(r13, r4, r2, r1, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x03e4, code lost:
    
        if ((r12 + 1) != r11.f9446b) goto L204;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x03e6, code lost:
    
        r10.a(r13, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x03eb, code lost:
    
        r13.b(r4, r2, r1, 0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x03f5, code lost:
    
        r3.f9386e++;
        r42.f9403p = -1;
        r42.f9404q = 0;
        r42.f9405r = 0;
        r42.f9406s = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0407, code lost:
    
        if ((r14 & 32) != 0) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x0409, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x040b, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x040c, code lost:
    
        r42.f9407t = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0410, code lost:
    
        r44.f600a = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005e, code lost:
    
        r8 = (V2.m) r14.get(r1);
        r12.H((int) (r8.f9414a - r17));
        r12.I(r9);
        r10 = r12.j();
        r11 = java.nio.charset.StandardCharsets.UTF_8;
        r9 = r12.t(r10, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0413, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x0414, code lost:
    
        r6 = r42.f9401m - ((long) r42.f9402n);
        r8 = r43.o() + r6;
        r3 = r42.o;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0422, code lost:
    
        if (r3 == null) goto L239;
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0424, code lost:
    
        r43.readFully(r3.f15241a, r42.f9402n, (int) r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0431, code lost:
    
        if (r42.l != 1718909296) goto L236;
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0433, code lost:
    
        r42.f9408u = true;
        r3.H(8);
        r6 = r3.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0445, code lost:
    
        if (r6 == 1751476579) goto L220;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0447, code lost:
    
        if (r6 == 1903435808) goto L219;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0449, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x044b, code lost:
    
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007d, code lost:
    
        switch(r9.hashCode()) {
            case -1711564334: goto L39;
            case -1332107749: goto L35;
            case -1251387154: goto L31;
            case -830665521: goto L27;
            case 1760745220: goto L23;
            default: goto L22;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x044d, code lost:
    
        r6 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:221:0x044e, code lost:
    
        if (r6 == 0) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0451, code lost:
    
        r3.I(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0459, code lost:
    
        if (r3.a() <= 0) goto L386;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x045b, code lost:
    
        r6 = r3.h();
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x045f, code lost:
    
        if (r6 == 1751476579) goto L231;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0461, code lost:
    
        if (r6 == 1903435808) goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0463, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0080, code lost:
    
        r7 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x0465, code lost:
    
        r6 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:231:0x0467, code lost:
    
        r6 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0468, code lost:
    
        if (r6 == 0) goto L391;
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x046b, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x046c, code lost:
    
        r42.f9390E = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x0473, code lost:
    
        if (r12.isEmpty() != false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x0475, code lost:
    
        ((i2.b) r12.peek()).o.add(new i2.c(r42.l, r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x048a, code lost:
    
        if (r42.f9408u != false) goto L244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:242:0x0491, code lost:
    
        if (r42.l != 1835295092) goto L244;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x0493, code lost:
    
        r42.f9390E = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x0498, code lost:
    
        if (r6 >= 262144) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x049a, code lost:
    
        r43.i((int) r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x049e, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:248:0x04a0, code lost:
    
        r44.f600a = r43.o() + r6;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x04a8, code lost:
    
        m(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0088, code lost:
    
        if (r9.equals("Super_SlowMotion_BGM") != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:250:0x04ad, code lost:
    
        if (r42.f9409v == false) goto L252;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x04af, code lost:
    
        r42.f9411x = true;
        r44.f600a = r42.f9410w;
        r42.f9409v = false;
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:252:0x04ba, code lost:
    
        if (r3 == false) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x04bf, code lost:
    
        if (r42.f9400k == 2) goto L256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x04c1, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:256:0x04c3, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008b, code lost:
    
        r7 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0093, code lost:
    
        if (r9.equals("Super_SlowMotion_Deflickering_On") != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0096, code lost:
    
        r7 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009e, code lost:
    
        if (r9.equals("Super_SlowMotion_Data") != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a1, code lost:
    
        r7 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a9, code lost:
    
        if (r9.equals("Super_SlowMotion_Edit_Data") != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ac, code lost:
    
        r7 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b4, code lost:
    
        if (r9.equals("SlowMotion_Data") != false) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:?, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b7, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b8, code lost:
    
        switch(r7) {
            case 0: goto L51;
            case 1: goto L50;
            case 2: goto L49;
            case 3: goto L48;
            case 4: goto L46;
            default: goto L394;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00c1, code lost:
    
        throw e2.M.a(null, "Invalid SEF name");
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c2, code lost:
    
        r7 = 2817;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00c6, code lost:
    
        r7 = 2820;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c9, code lost:
    
        r7 = 2816;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00cb, code lost:
    
        r7 = 2819;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ce, code lost:
    
        r7 = 2192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d0, code lost:
    
        r8 = r8.f9415b - (r10 + 8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00d4, code lost:
    
        if (r7 == 2192) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00d6, code lost:
    
        if (r7 == 2816) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00d8, code lost:
    
        if (r7 == 2817) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00dc, code lost:
    
        if (r7 == 2819) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e0, code lost:
    
        if (r7 != 2820) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e8, code lost:
    
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00eb, code lost:
    
        r7 = new java.util.ArrayList();
        r8 = V2.n.f9416e.t(r12.t(r8, r11));
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ff, code lost:
    
        if (r9 >= r8.size()) goto L396;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0101, code lost:
    
        r10 = V2.n.d.t((java.lang.CharSequence) r8.get(r9));
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0111, code lost:
    
        if (r10.size() != r4) goto L392;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0114, code lost:
    
        r7.add(new Q2.b(1 << (java.lang.Integer.parseInt((java.lang.String) r10.get(2)) - 1), java.lang.Long.parseLong((java.lang.String) r10.get(0)), java.lang.Long.parseLong((java.lang.String) r10.get(1))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0142, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0144, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0149, code lost:
    
        throw e2.M.a(r0, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x014e, code lost:
    
        throw e2.M.a(null, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x014f, code lost:
    
        r3.add(new Q2.c(r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0158, code lost:
    
        r1 = r1 + 1;
        r9 = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x015d, code lost:
    
        r44.f600a = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001b, code lost:
    
        if (r11 == 1) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0161, code lost:
    
        r1 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0169, code lost:
    
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x016a, code lost:
    
        r7 = r43.d();
        r3 = r11.c - 20;
        r9 = new h2.t(r3);
        r43.readFully(r9.f15241a, 0, r3);
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0180, code lost:
    
        if (r1 >= (r3 / 12)) goto L397;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0182, code lost:
    
        r9.I(2);
        r10 = r9.l();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x018a, code lost:
    
        if (r10 == r5) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x018c, code lost:
    
        if (r10 == r6) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x018e, code lost:
    
        if (r10 == 2817) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0194, code lost:
    
        if (r10 == 2819) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0196, code lost:
    
        if (r10 == 2820) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0198, code lost:
    
        r9.I(8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01a3, code lost:
    
        r14.add(new V2.m((r7 - ((long) r11.c)) - ((long) r9.j()), r9.j()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01bb, code lost:
    
        r1 = r1 + 1;
        r5 = 2192;
        r6 = 2816;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01c5, code lost:
    
        if (r14.isEmpty() == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
    
        if (r11 == r8) goto L120;
     */
    /* JADX WARN: Removed duplicated region for block: B:373:0x06d0 A[LOOP:1: B:4:0x000a->B:373:0x06d0, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:381:0x06ce A[SYNTHETIC] */
    @Override // B2.r
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c(B2.s r43, B2.v r44) throws e2.M {
        /*
            Method dump skipped, instruction units count: 1794
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.l.c(B2.s, B2.v):int");
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        this.f9396g.clear();
        this.f9402n = 0;
        this.f9403p = -1;
        this.f9404q = 0;
        this.f9405r = 0;
        this.f9406s = 0;
        this.f9407t = (this.f9393b & 32) == 0;
        if (j3 == 0) {
            if (this.f9400k != 3) {
                this.f9400k = 0;
                this.f9402n = 0;
                return;
            } else {
                n nVar = this.f9397h;
                nVar.f9417a.clear();
                nVar.f9418b = 0;
                this.f9398i.clear();
                return;
            }
        }
        for (k kVar : this.f9387A) {
            s sVar = kVar.f9385b;
            int iE = AbstractC1343A.e(sVar.f9448f, j10, false);
            while (true) {
                if (iE < 0) {
                    iE = -1;
                    break;
                } else if ((sVar.f9449g[iE] & 1) != 0) {
                    break;
                } else {
                    iE--;
                }
            }
            if (iE == -1) {
                iE = sVar.a(j10);
            }
            kVar.f9386e = iE;
            M m10 = kVar.d;
            if (m10 != null) {
                m10.f520b = false;
                m10.c = 0;
            }
        }
    }

    @Override // B2.r
    public final boolean f(B2.s sVar) {
        d0 d0VarB;
        J jK = o.k(sVar, false, (this.f9393b & 2) != 0);
        if (jK != null) {
            d0VarB = I.B(jK);
        } else {
            C1078G c1078g = I.f13384m;
            d0VarB = d0.f13415p;
        }
        this.f9399j = d0VarB;
        return jK == null;
    }

    @Override // B2.r
    public final List g() {
        return this.f9399j;
    }

    @Override // B2.F
    public final boolean h() {
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00df A[EDGE_INSN: B:71:0x00df->B:64:0x00df BREAK  A[LOOP:1: B:29:0x006c->B:63:0x00d7], SYNTHETIC] */
    @Override // B2.F
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final B2.E j(long r22) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.l.j(long):B2.E");
    }

    @Override // B2.F
    public final long l() {
        return this.f9389D;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x021a, code lost:
    
        r6.H(r7);
        r7 = r7 + r9;
        r6.I(8);
        r8 = new java.util.ArrayList();
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0228, code lost:
    
        r9 = r6.f15242b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x022a, code lost:
    
        if (r9 >= r7) goto L456;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x022c, code lost:
    
        r9 = r6.h() + r9;
        r4 = r6.h();
        r17 = r7;
        r7 = (r4 >> 24) & 255;
        r29 = r1;
        r30 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x0248, code lost:
    
        if (r7 == 169) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x024c, code lost:
    
        if (r7 != 253) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x024e, code lost:
    
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0258, code lost:
    
        if (r4 != 1735291493) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x025a, code lost:
    
        r1 = P2.j.a(V2.o.f(r6) - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0264, code lost:
    
        if (r1 == null) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0266, code lost:
    
        r3 = new P2.n("TCON", null, d5.I.B(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0271, code lost:
    
        h2.AbstractC1346c.z("MetadataUtil", "Failed to parse standard genre code");
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0277, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0278, code lost:
    
        r6.H(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x027b, code lost:
    
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0286, code lost:
    
        if (r4 != 1684632427) goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x0288, code lost:
    
        r3 = V2.o.e(r4, r6, "TPOS");
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x028f, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0295, code lost:
    
        if (r4 != 1953655662) goto L125;
     */
    /* JADX WARN: Code restructure failed: missing block: B:124:0x0297, code lost:
    
        r3 = V2.o.e(r4, r6, "TRCK");
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x02a1, code lost:
    
        if (r4 != 1953329263) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x02a3, code lost:
    
        r0 = V2.o.g(r4, "TBPM", r6, true, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x02ab, code lost:
    
        r6.H(r9);
        r3 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x02b3, code lost:
    
        if (r4 != 1668311404) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x02b5, code lost:
    
        r3 = V2.o.g(r4, "TCMP", r6, true, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x02c0, code lost:
    
        if (r4 != 1668249202) goto L135;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x02c2, code lost:
    
        r3 = V2.o.d(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x02ca, code lost:
    
        if (r4 != 1631670868) goto L138;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x02cc, code lost:
    
        r3 = V2.o.j(r4, r6, "TPE2");
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x02d6, code lost:
    
        if (r4 != 1936682605) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02d8, code lost:
    
        r3 = V2.o.j(r4, r6, "TSOT");
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02e2, code lost:
    
        if (r4 != 1936679276) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02e4, code lost:
    
        r3 = V2.o.j(r4, r6, "TSOA");
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02ee, code lost:
    
        if (r4 != 1936679282) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02f0, code lost:
    
        r3 = V2.o.j(r4, r6, "TSOP");
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02fa, code lost:
    
        if (r4 != 1936679265) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02fc, code lost:
    
        r3 = V2.o.j(r4, r6, "TSO2");
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0307, code lost:
    
        if (r4 != 1936679791) goto L153;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0309, code lost:
    
        r3 = V2.o.j(r4, r6, "TSOC");
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0314, code lost:
    
        if (r4 != 1920233063) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0316, code lost:
    
        r3 = V2.o.g(r4, "ITUNESADVISORY", r6, false, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0322, code lost:
    
        if (r4 != 1885823344) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0324, code lost:
    
        r0 = V2.o.g(r4, "ITUNESGAPLESS", r6, false, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0331, code lost:
    
        if (r4 != 1936683886) goto L162;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0333, code lost:
    
        r3 = V2.o.j(r4, r6, "TVSHOWSORT");
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x033e, code lost:
    
        if (r4 != 1953919848) goto L165;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0340, code lost:
    
        r3 = V2.o.j(r4, r6, "TVSHOW");
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x034b, code lost:
    
        if (r4 != 757935405) goto L192;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x034d, code lost:
    
        r3 = null;
        r4 = null;
        r0 = -1;
        r1 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0351, code lost:
    
        r11 = r6.f15242b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0353, code lost:
    
        if (r11 >= r9) goto L459;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0355, code lost:
    
        r20 = r6.h();
        r7 = r6.h();
        r6.I(4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0366, code lost:
    
        if (r7 != 1835360622) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0368, code lost:
    
        r3 = r6.r(r20 - 12);
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0376, code lost:
    
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x037d, code lost:
    
        if (r7 != 1851878757) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x037f, code lost:
    
        r4 = r6.r(r20 - 12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0389, code lost:
    
        if (r7 != 1684108385) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x038b, code lost:
    
        r1 = r20;
        r0 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x038f, code lost:
    
        r6.I(r20 - 12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0394, code lost:
    
        r2 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0398, code lost:
    
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x039d, code lost:
    
        if (r3 == null) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x039f, code lost:
    
        if (r4 == null) goto L190;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x03a2, code lost:
    
        if (r0 != (-1)) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x03a5, code lost:
    
        r6.H(r0);
        r6.I(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x03b7, code lost:
    
        r3 = new P2.k(r3, r4, r6.r(r1 - 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x03b9, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x03ba, code lost:
    
        r6.H(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x03bf, code lost:
    
        r32 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x03c6, code lost:
    
        r1 = 16777215 & r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x03cd, code lost:
    
        if (r1 != 6516084) goto L196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x03cf, code lost:
    
        r3 = V2.o.c(r4, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x03d7, code lost:
    
        if (r1 == 7233901) goto L230;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x03dc, code lost:
    
        if (r1 != 7631467) goto L201;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x03e3, code lost:
    
        if (r1 == 6516589) goto L229;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x03e8, code lost:
    
        if (r1 != 7828084) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x03ef, code lost:
    
        if (r1 != 6578553) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x03f1, code lost:
    
        r3 = V2.o.j(r4, r6, "TDRC");
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x03fb, code lost:
    
        if (r1 != 4280916) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x03fd, code lost:
    
        r3 = V2.o.j(r4, r6, "TPE1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0407, code lost:
    
        if (r1 != 7630703) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0409, code lost:
    
        r3 = V2.o.j(r4, r6, "TSSE");
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0413, code lost:
    
        if (r1 != 6384738) goto L218;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0415, code lost:
    
        r3 = V2.o.j(r4, r6, "TALB");
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x041f, code lost:
    
        if (r1 != 7108978) goto L221;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x0421, code lost:
    
        r3 = V2.o.j(r4, r6, "USLT");
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x042b, code lost:
    
        if (r1 != 6776174) goto L224;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x042d, code lost:
    
        r3 = V2.o.j(r4, r6, "TCON");
     */
    /* JADX WARN: Code restructure failed: missing block: B:225:0x0435, code lost:
    
        if (r1 != 6779504) goto L227;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x0437, code lost:
    
        r3 = V2.o.j(r4, r6, "TIT1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:227:0x043f, code lost:
    
        h2.AbstractC1346c.n("MetadataUtil", "Skipped unknown metadata entry: " + i2.d.c(r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x0452, code lost:
    
        r6.H(r9);
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x0457, code lost:
    
        r3 = V2.o.j(r4, r6, "TCOM");
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x045f, code lost:
    
        r3 = V2.o.j(r4, r6, "TIT2");
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x0467, code lost:
    
        if (r3 == null) goto L458;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x0469, code lost:
    
        r8.add(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:234:0x046c, code lost:
    
        r7 = r17;
        r1 = r29;
        r3 = r30;
        r2 = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:235:0x047b, code lost:
    
        r6.H(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x047e, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:237:0x047f, code lost:
    
        r29 = r1;
        r32 = r2;
        r30 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:238:0x048c, code lost:
    
        if (r8.isEmpty() == false) goto L240;
     */
    /* JADX WARN: Code restructure failed: missing block: B:239:0x048e, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:240:0x0490, code lost:
    
        r0 = new e2.K(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:241:0x0495, code lost:
    
        r7 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:289:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x08b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0175  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m(long r34) {
        /*
            Method dump skipped, instruction units count: 2253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: V2.l.m(long):void");
    }
}
