package o;

import java.util.Arrays;
import java.util.HashMap;
import o.i;
import r.d;

/* JADX INFO: compiled from: LinearSystem.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f8569r = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static boolean f8570s = true;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static boolean f8571t = true;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static boolean f8572u = true;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static boolean f8573v = false;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private static int f8574w = 1000;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static long f8575x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static long f8576y;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f8580d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    o.b[] f8583g;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final c f8590n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private a f8593q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f8577a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f8578b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, i> f8579c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8581e = 32;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f8582f = 32;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8584h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8585i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean[] f8586j = new boolean[32];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f8587k = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f8588l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f8589m = 32;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private i[] f8591o = new i[f8574w];

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f8592p = 0;

    /* JADX INFO: compiled from: LinearSystem.java */
    interface a {
        void a(i iVar);

        i b(d dVar, boolean[] zArr);

        void c(a aVar);

        void clear();

        i getKey();

        boolean isEmpty();
    }

    /* JADX INFO: compiled from: LinearSystem.java */
    class b extends o.b {
        public b(c cVar) {
            this.f8563e = new j(this, cVar);
        }
    }

    public d() {
        this.f8583g = null;
        this.f8583g = new o.b[32];
        C();
        c cVar = new c();
        this.f8590n = cVar;
        this.f8580d = new h(cVar);
        if (f8573v) {
            this.f8593q = new b(cVar);
        } else {
            this.f8593q = new o.b(cVar);
        }
    }

    private final int B(a aVar, boolean z6) {
        for (int i7 = 0; i7 < this.f8587k; i7++) {
            this.f8586j[i7] = false;
        }
        boolean z7 = false;
        int i8 = 0;
        while (!z7) {
            i8++;
            if (i8 >= this.f8587k * 2) {
                return i8;
            }
            if (aVar.getKey() != null) {
                this.f8586j[aVar.getKey().f8610h] = true;
            }
            i iVarB = aVar.b(this, this.f8586j);
            if (iVarB != null) {
                boolean[] zArr = this.f8586j;
                int i9 = iVarB.f8610h;
                if (zArr[i9]) {
                    return i8;
                }
                zArr[i9] = true;
            }
            if (iVarB != null) {
                float f7 = Float.MAX_VALUE;
                int i10 = -1;
                for (int i11 = 0; i11 < this.f8588l; i11++) {
                    o.b bVar = this.f8583g[i11];
                    if (bVar.f8559a.f8617o != i.a.UNRESTRICTED && !bVar.f8564f && bVar.t(iVarB)) {
                        float f8 = bVar.f8563e.f(iVarB);
                        if (f8 < 0.0f) {
                            float f9 = (-bVar.f8560b) / f8;
                            if (f9 < f7) {
                                i10 = i11;
                                f7 = f9;
                            }
                        }
                    }
                }
                if (i10 > -1) {
                    o.b bVar2 = this.f8583g[i10];
                    bVar2.f8559a.f8611i = -1;
                    bVar2.x(iVarB);
                    i iVar = bVar2.f8559a;
                    iVar.f8611i = i10;
                    iVar.h(this, bVar2);
                }
            } else {
                z7 = true;
            }
        }
        return i8;
    }

    private void C() {
        int i7 = 0;
        if (f8573v) {
            while (i7 < this.f8588l) {
                o.b bVar = this.f8583g[i7];
                if (bVar != null) {
                    this.f8590n.f8565a.a(bVar);
                }
                this.f8583g[i7] = null;
                i7++;
            }
            return;
        }
        while (i7 < this.f8588l) {
            o.b bVar2 = this.f8583g[i7];
            if (bVar2 != null) {
                this.f8590n.f8566b.a(bVar2);
            }
            this.f8583g[i7] = null;
            i7++;
        }
    }

    private i a(i.a aVar, String str) {
        i iVarB = this.f8590n.f8567c.b();
        if (iVarB == null) {
            iVarB = new i(aVar, str);
            iVarB.g(aVar, str);
        } else {
            iVarB.e();
            iVarB.g(aVar, str);
        }
        int i7 = this.f8592p;
        int i8 = f8574w;
        if (i7 >= i8) {
            int i9 = i8 * 2;
            f8574w = i9;
            this.f8591o = (i[]) Arrays.copyOf(this.f8591o, i9);
        }
        i[] iVarArr = this.f8591o;
        int i10 = this.f8592p;
        this.f8592p = i10 + 1;
        iVarArr[i10] = iVarB;
        return iVarB;
    }

    private final void l(o.b bVar) {
        int i7;
        if (f8571t && bVar.f8564f) {
            bVar.f8559a.f(this, bVar.f8560b);
        } else {
            o.b[] bVarArr = this.f8583g;
            int i8 = this.f8588l;
            bVarArr[i8] = bVar;
            i iVar = bVar.f8559a;
            iVar.f8611i = i8;
            this.f8588l = i8 + 1;
            iVar.h(this, bVar);
        }
        if (f8571t && this.f8577a) {
            int i9 = 0;
            while (i9 < this.f8588l) {
                if (this.f8583g[i9] == null) {
                    System.out.println("WTF");
                }
                o.b[] bVarArr2 = this.f8583g;
                if (bVarArr2[i9] != null && bVarArr2[i9].f8564f) {
                    o.b bVar2 = bVarArr2[i9];
                    bVar2.f8559a.f(this, bVar2.f8560b);
                    if (f8573v) {
                        this.f8590n.f8565a.a(bVar2);
                    } else {
                        this.f8590n.f8566b.a(bVar2);
                    }
                    this.f8583g[i9] = null;
                    int i10 = i9 + 1;
                    int i11 = i10;
                    while (true) {
                        i7 = this.f8588l;
                        if (i10 >= i7) {
                            break;
                        }
                        o.b[] bVarArr3 = this.f8583g;
                        int i12 = i10 - 1;
                        bVarArr3[i12] = bVarArr3[i10];
                        if (bVarArr3[i12].f8559a.f8611i == i10) {
                            bVarArr3[i12].f8559a.f8611i = i12;
                        }
                        i11 = i10;
                        i10++;
                    }
                    if (i11 < i7) {
                        this.f8583g[i11] = null;
                    }
                    this.f8588l = i7 - 1;
                    i9--;
                }
                i9++;
            }
            this.f8577a = false;
        }
    }

    private void n() {
        for (int i7 = 0; i7 < this.f8588l; i7++) {
            o.b bVar = this.f8583g[i7];
            bVar.f8559a.f8613k = bVar.f8560b;
        }
    }

    public static o.b s(d dVar, i iVar, i iVar2, float f7) {
        return dVar.r().j(iVar, iVar2, f7);
    }

    private int u(a aVar) {
        boolean z6;
        int i7 = 0;
        while (true) {
            if (i7 >= this.f8588l) {
                z6 = false;
                break;
            }
            o.b[] bVarArr = this.f8583g;
            if (bVarArr[i7].f8559a.f8617o != i.a.UNRESTRICTED && bVarArr[i7].f8560b < 0.0f) {
                z6 = true;
                break;
            }
            i7++;
        }
        if (!z6) {
            return 0;
        }
        boolean z7 = false;
        int i8 = 0;
        while (!z7) {
            i8++;
            float f7 = Float.MAX_VALUE;
            int i9 = -1;
            int i10 = -1;
            int i11 = 0;
            for (int i12 = 0; i12 < this.f8588l; i12++) {
                o.b bVar = this.f8583g[i12];
                if (bVar.f8559a.f8617o != i.a.UNRESTRICTED && !bVar.f8564f && bVar.f8560b < 0.0f) {
                    int i13 = 9;
                    if (f8572u) {
                        int iK = bVar.f8563e.k();
                        int i14 = 0;
                        while (i14 < iK) {
                            i iVarG = bVar.f8563e.g(i14);
                            float f8 = bVar.f8563e.f(iVarG);
                            if (f8 > 0.0f) {
                                int i15 = 0;
                                while (i15 < i13) {
                                    float f9 = iVarG.f8615m[i15] / f8;
                                    if ((f9 < f7 && i15 == i11) || i15 > i11) {
                                        i10 = iVarG.f8610h;
                                        i11 = i15;
                                        i9 = i12;
                                        f7 = f9;
                                    }
                                    i15++;
                                    i13 = 9;
                                }
                            }
                            i14++;
                            i13 = 9;
                        }
                    } else {
                        for (int i16 = 1; i16 < this.f8587k; i16++) {
                            i iVar = this.f8590n.f8568d[i16];
                            float f10 = bVar.f8563e.f(iVar);
                            if (f10 > 0.0f) {
                                for (int i17 = 0; i17 < 9; i17++) {
                                    float f11 = iVar.f8615m[i17] / f10;
                                    if ((f11 < f7 && i17 == i11) || i17 > i11) {
                                        i10 = i16;
                                        i9 = i12;
                                        i11 = i17;
                                        f7 = f11;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (i9 != -1) {
                o.b bVar2 = this.f8583g[i9];
                bVar2.f8559a.f8611i = -1;
                bVar2.x(this.f8590n.f8568d[i10]);
                i iVar2 = bVar2.f8559a;
                iVar2.f8611i = i9;
                iVar2.h(this, bVar2);
            } else {
                z7 = true;
            }
            if (i8 > this.f8587k / 2) {
                z7 = true;
            }
        }
        return i8;
    }

    public static e w() {
        return null;
    }

    private void y() {
        int i7 = this.f8581e * 2;
        this.f8581e = i7;
        this.f8583g = (o.b[]) Arrays.copyOf(this.f8583g, i7);
        c cVar = this.f8590n;
        cVar.f8568d = (i[]) Arrays.copyOf(cVar.f8568d, this.f8581e);
        int i8 = this.f8581e;
        this.f8586j = new boolean[i8];
        this.f8582f = i8;
        this.f8589m = i8;
    }

    void A(a aVar) {
        u(aVar);
        B(aVar, false);
        n();
    }

    public void D() {
        c cVar;
        int i7 = 0;
        while (true) {
            cVar = this.f8590n;
            i[] iVarArr = cVar.f8568d;
            if (i7 >= iVarArr.length) {
                break;
            }
            i iVar = iVarArr[i7];
            if (iVar != null) {
                iVar.e();
            }
            i7++;
        }
        cVar.f8567c.c(this.f8591o, this.f8592p);
        this.f8592p = 0;
        Arrays.fill(this.f8590n.f8568d, (Object) null);
        HashMap<String, i> map = this.f8579c;
        if (map != null) {
            map.clear();
        }
        this.f8578b = 0;
        this.f8580d.clear();
        this.f8587k = 1;
        for (int i8 = 0; i8 < this.f8588l; i8++) {
            o.b[] bVarArr = this.f8583g;
            if (bVarArr[i8] != null) {
                bVarArr[i8].f8561c = false;
            }
        }
        C();
        this.f8588l = 0;
        if (f8573v) {
            this.f8593q = new b(this.f8590n);
        } else {
            this.f8593q = new o.b(this.f8590n);
        }
    }

    public void b(r.e eVar, r.e eVar2, float f7, int i7) {
        d.b bVar = d.b.LEFT;
        i iVarQ = q(eVar.q(bVar));
        d.b bVar2 = d.b.TOP;
        i iVarQ2 = q(eVar.q(bVar2));
        d.b bVar3 = d.b.RIGHT;
        i iVarQ3 = q(eVar.q(bVar3));
        d.b bVar4 = d.b.BOTTOM;
        i iVarQ4 = q(eVar.q(bVar4));
        i iVarQ5 = q(eVar2.q(bVar));
        i iVarQ6 = q(eVar2.q(bVar2));
        i iVarQ7 = q(eVar2.q(bVar3));
        i iVarQ8 = q(eVar2.q(bVar4));
        o.b bVarR = r();
        double d7 = f7;
        double d8 = i7;
        bVarR.q(iVarQ2, iVarQ4, iVarQ6, iVarQ8, (float) (Math.sin(d7) * d8));
        d(bVarR);
        o.b bVarR2 = r();
        bVarR2.q(iVarQ, iVarQ3, iVarQ5, iVarQ7, (float) (Math.cos(d7) * d8));
        d(bVarR2);
    }

    public void c(i iVar, i iVar2, int i7, float f7, i iVar3, i iVar4, int i8, int i9) {
        o.b bVarR = r();
        bVarR.h(iVar, iVar2, i7, f7, iVar3, iVar4, i8);
        if (i9 != 8) {
            bVarR.d(this, i9);
        }
        d(bVarR);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(o.b r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L3
            return
        L3:
            int r0 = r5.f8588l
            r1 = 1
            int r0 = r0 + r1
            int r2 = r5.f8589m
            if (r0 >= r2) goto L12
            int r0 = r5.f8587k
            int r0 = r0 + r1
            int r2 = r5.f8582f
            if (r0 < r2) goto L15
        L12:
            r5.y()
        L15:
            r0 = 0
            boolean r2 = r6.f8564f
            if (r2 != 0) goto L84
            r6.D(r5)
            boolean r2 = r6.isEmpty()
            if (r2 == 0) goto L24
            return
        L24:
            r6.r()
            boolean r2 = r6.f(r5)
            if (r2 == 0) goto L7b
            o.i r2 = r5.p()
            r6.f8559a = r2
            int r3 = r5.f8588l
            r5.l(r6)
            int r4 = r5.f8588l
            int r3 = r3 + r1
            if (r4 != r3) goto L7b
            o.d$a r0 = r5.f8593q
            r0.c(r6)
            o.d$a r0 = r5.f8593q
            r5.B(r0, r1)
            int r0 = r2.f8611i
            r3 = -1
            if (r0 != r3) goto L7c
            o.i r0 = r6.f8559a
            if (r0 != r2) goto L59
            o.i r0 = r6.v(r2)
            if (r0 == 0) goto L59
            r6.x(r0)
        L59:
            boolean r0 = r6.f8564f
            if (r0 != 0) goto L62
            o.i r0 = r6.f8559a
            r0.h(r5, r6)
        L62:
            boolean r0 = o.d.f8573v
            if (r0 == 0) goto L6e
            o.c r0 = r5.f8590n
            o.f<o.b> r0 = r0.f8565a
            r0.a(r6)
            goto L75
        L6e:
            o.c r0 = r5.f8590n
            o.f<o.b> r0 = r0.f8566b
            r0.a(r6)
        L75:
            int r0 = r5.f8588l
            int r0 = r0 - r1
            r5.f8588l = r0
            goto L7c
        L7b:
            r1 = r0
        L7c:
            boolean r0 = r6.s()
            if (r0 != 0) goto L83
            return
        L83:
            r0 = r1
        L84:
            if (r0 != 0) goto L89
            r5.l(r6)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: o.d.d(o.b):void");
    }

    public o.b e(i iVar, i iVar2, int i7, int i8) {
        if (f8570s && i8 == 8 && iVar2.f8614l && iVar.f8611i == -1) {
            iVar.f(this, iVar2.f8613k + i7);
            return null;
        }
        o.b bVarR = r();
        bVarR.n(iVar, iVar2, i7);
        if (i8 != 8) {
            bVarR.d(this, i8);
        }
        d(bVarR);
        return bVarR;
    }

    public void f(i iVar, int i7) {
        if (f8570s && iVar.f8611i == -1) {
            float f7 = i7;
            iVar.f(this, f7);
            for (int i8 = 0; i8 < this.f8578b + 1; i8++) {
                i iVar2 = this.f8590n.f8568d[i8];
                if (iVar2 != null && iVar2.f8621s && iVar2.f8622t == iVar.f8610h) {
                    iVar2.f(this, iVar2.f8623u + f7);
                }
            }
            return;
        }
        int i9 = iVar.f8611i;
        if (i9 == -1) {
            o.b bVarR = r();
            bVarR.i(iVar, i7);
            d(bVarR);
            return;
        }
        o.b bVar = this.f8583g[i9];
        if (bVar.f8564f) {
            bVar.f8560b = i7;
            return;
        }
        if (bVar.f8563e.k() == 0) {
            bVar.f8564f = true;
            bVar.f8560b = i7;
        } else {
            o.b bVarR2 = r();
            bVarR2.m(iVar, i7);
            d(bVarR2);
        }
    }

    public void g(i iVar, i iVar2, int i7, boolean z6) {
        o.b bVarR = r();
        i iVarT = t();
        iVarT.f8612j = 0;
        bVarR.o(iVar, iVar2, iVarT, i7);
        d(bVarR);
    }

    public void h(i iVar, i iVar2, int i7, int i8) {
        o.b bVarR = r();
        i iVarT = t();
        iVarT.f8612j = 0;
        bVarR.o(iVar, iVar2, iVarT, i7);
        if (i8 != 8) {
            m(bVarR, (int) (bVarR.f8563e.f(iVarT) * (-1.0f)), i8);
        }
        d(bVarR);
    }

    public void i(i iVar, i iVar2, int i7, boolean z6) {
        o.b bVarR = r();
        i iVarT = t();
        iVarT.f8612j = 0;
        bVarR.p(iVar, iVar2, iVarT, i7);
        d(bVarR);
    }

    public void j(i iVar, i iVar2, int i7, int i8) {
        o.b bVarR = r();
        i iVarT = t();
        iVarT.f8612j = 0;
        bVarR.p(iVar, iVar2, iVarT, i7);
        if (i8 != 8) {
            m(bVarR, (int) (bVarR.f8563e.f(iVarT) * (-1.0f)), i8);
        }
        d(bVarR);
    }

    public void k(i iVar, i iVar2, i iVar3, i iVar4, float f7, int i7) {
        o.b bVarR = r();
        bVarR.k(iVar, iVar2, iVar3, iVar4, f7);
        if (i7 != 8) {
            bVarR.d(this, i7);
        }
        d(bVarR);
    }

    void m(o.b bVar, int i7, int i8) {
        bVar.e(o(i8, null), i7);
    }

    public i o(int i7, String str) {
        if (this.f8587k + 1 >= this.f8582f) {
            y();
        }
        i iVarA = a(i.a.ERROR, str);
        int i8 = this.f8578b + 1;
        this.f8578b = i8;
        this.f8587k++;
        iVarA.f8610h = i8;
        iVarA.f8612j = i7;
        this.f8590n.f8568d[i8] = iVarA;
        this.f8580d.a(iVarA);
        return iVarA;
    }

    public i p() {
        if (this.f8587k + 1 >= this.f8582f) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i7 = this.f8578b + 1;
        this.f8578b = i7;
        this.f8587k++;
        iVarA.f8610h = i7;
        this.f8590n.f8568d[i7] = iVarA;
        return iVarA;
    }

    public i q(Object obj) {
        i iVarI = null;
        if (obj == null) {
            return null;
        }
        if (this.f8587k + 1 >= this.f8582f) {
            y();
        }
        if (obj instanceof r.d) {
            r.d dVar = (r.d) obj;
            iVarI = dVar.i();
            if (iVarI == null) {
                dVar.s(this.f8590n);
                iVarI = dVar.i();
            }
            int i7 = iVarI.f8610h;
            if (i7 == -1 || i7 > this.f8578b || this.f8590n.f8568d[i7] == null) {
                if (i7 != -1) {
                    iVarI.e();
                }
                int i8 = this.f8578b + 1;
                this.f8578b = i8;
                this.f8587k++;
                iVarI.f8610h = i8;
                iVarI.f8617o = i.a.UNRESTRICTED;
                this.f8590n.f8568d[i8] = iVarI;
            }
        }
        return iVarI;
    }

    public o.b r() {
        o.b bVarB;
        if (f8573v) {
            bVarB = this.f8590n.f8565a.b();
            if (bVarB == null) {
                bVarB = new b(this.f8590n);
                f8576y++;
            } else {
                bVarB.y();
            }
        } else {
            bVarB = this.f8590n.f8566b.b();
            if (bVarB == null) {
                bVarB = new o.b(this.f8590n);
                f8575x++;
            } else {
                bVarB.y();
            }
        }
        i.c();
        return bVarB;
    }

    public i t() {
        if (this.f8587k + 1 >= this.f8582f) {
            y();
        }
        i iVarA = a(i.a.SLACK, null);
        int i7 = this.f8578b + 1;
        this.f8578b = i7;
        this.f8587k++;
        iVarA.f8610h = i7;
        this.f8590n.f8568d[i7] = iVarA;
        return iVarA;
    }

    public c v() {
        return this.f8590n;
    }

    public int x(Object obj) {
        i iVarI = ((r.d) obj).i();
        if (iVarI != null) {
            return (int) (iVarI.f8613k + 0.5f);
        }
        return 0;
    }

    public void z() {
        if (this.f8580d.isEmpty()) {
            n();
            return;
        }
        if (!this.f8584h && !this.f8585i) {
            A(this.f8580d);
            return;
        }
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= this.f8588l) {
                z6 = true;
                break;
            } else if (!this.f8583g[i7].f8564f) {
                break;
            } else {
                i7++;
            }
        }
        if (z6) {
            n();
        } else {
            A(this.f8580d);
        }
    }
}
