package j8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import l8.C1789g;
import p8.AbstractC2126a;
import p8.AbstractC2129d;
import p8.AbstractC2134i;
import p8.AbstractC2136k;
import p8.C2128c;
import p8.C2130e;
import p8.C2132g;
import p8.C2142q;

/* JADX INFO: renamed from: j8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1565j extends AbstractC2136k {

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final C1565j f17372U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public static final C1556a f17373V = new C1556a(3);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public List f17374A;
    public List B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public List f17375C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public List f17376D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public List f17377E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public List f17378F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f17379G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f17380H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Q f17381I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f17382J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public List f17383K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f17384L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public List f17385M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public List f17386N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f17387O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public X f17388P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public List f17389Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public e0 f17390R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public byte f17391S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public int f17392T;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AbstractC2129d f17393m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f17394n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17395p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17396q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f17397r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f17398s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public List f17399t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f17400u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public List f17401v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17402w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17403x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f17404y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f17405z;

    static {
        C1565j c1565j = new C1565j();
        f17372U = c1565j;
        c1565j.p();
    }

    public C1565j(C1563h c1563h) {
        super(c1563h);
        this.f17400u = -1;
        this.f17402w = -1;
        this.f17405z = -1;
        this.f17379G = -1;
        this.f17384L = -1;
        this.f17387O = -1;
        this.f17391S = (byte) -1;
        this.f17392T = -1;
        this.f17393m = c1563h.l;
    }

    @Override // p8.u
    public final AbstractC2126a a() {
        return f17372U;
    }

    @Override // p8.u
    public final boolean b() {
        byte b10 = this.f17391S;
        if (b10 == 1) {
            return true;
        }
        if (b10 == 0) {
            return false;
        }
        if ((this.f17394n & 2) != 2) {
            this.f17391S = (byte) 0;
            return false;
        }
        for (int i7 = 0; i7 < this.f17397r.size(); i7++) {
            if (!((W) this.f17397r.get(i7)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i10 = 0; i10 < this.f17398s.size(); i10++) {
            if (!((Q) this.f17398s.get(i10)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i11 = 0; i11 < this.f17403x.size(); i11++) {
            if (!((Q) this.f17403x.get(i11)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i12 = 0; i12 < this.f17374A.size(); i12++) {
            if (!((C1567l) this.f17374A.get(i12)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i13 = 0; i13 < this.B.size(); i13++) {
            if (!((C1579y) this.B.get(i13)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i14 = 0; i14 < this.f17375C.size(); i14++) {
            if (!((G) this.f17375C.get(i14)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i15 = 0; i15 < this.f17376D.size(); i15++) {
            if (!((T) this.f17376D.get(i15)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        for (int i16 = 0; i16 < this.f17377E.size(); i16++) {
            if (!((C1574t) this.f17377E.get(i16)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        if ((this.f17394n & 16) == 16 && !this.f17381I.b()) {
            this.f17391S = (byte) 0;
            return false;
        }
        for (int i17 = 0; i17 < this.f17385M.size(); i17++) {
            if (!((Q) this.f17385M.get(i17)).b()) {
                this.f17391S = (byte) 0;
                return false;
            }
        }
        if ((this.f17394n & 64) == 64 && !this.f17388P.b()) {
            this.f17391S = (byte) 0;
            return false;
        }
        if (i()) {
            this.f17391S = (byte) 1;
            return true;
        }
        this.f17391S = (byte) 0;
        return false;
    }

    @Override // p8.AbstractC2126a
    public final int c() {
        int i7 = this.f17392T;
        if (i7 != -1) {
            return i7;
        }
        int iF = (this.f17394n & 1) == 1 ? A2.g.f(1, this.o) : 0;
        int iG = 0;
        for (int i10 = 0; i10 < this.f17399t.size(); i10++) {
            iG += A2.g.g(((Integer) this.f17399t.get(i10)).intValue());
        }
        int iH = iF + iG;
        if (!this.f17399t.isEmpty()) {
            iH = iH + 1 + A2.g.g(iG);
        }
        this.f17400u = iG;
        if ((this.f17394n & 2) == 2) {
            iH += A2.g.f(3, this.f17395p);
        }
        if ((this.f17394n & 4) == 4) {
            iH += A2.g.f(4, this.f17396q);
        }
        for (int i11 = 0; i11 < this.f17397r.size(); i11++) {
            iH += A2.g.h(5, (AbstractC2126a) this.f17397r.get(i11));
        }
        for (int i12 = 0; i12 < this.f17398s.size(); i12++) {
            iH += A2.g.h(6, (AbstractC2126a) this.f17398s.get(i12));
        }
        int iG2 = 0;
        for (int i13 = 0; i13 < this.f17401v.size(); i13++) {
            iG2 += A2.g.g(((Integer) this.f17401v.get(i13)).intValue());
        }
        int iH2 = iH + iG2;
        if (!this.f17401v.isEmpty()) {
            iH2 = iH2 + 1 + A2.g.g(iG2);
        }
        this.f17402w = iG2;
        for (int i14 = 0; i14 < this.f17374A.size(); i14++) {
            iH2 += A2.g.h(8, (AbstractC2126a) this.f17374A.get(i14));
        }
        for (int i15 = 0; i15 < this.B.size(); i15++) {
            iH2 += A2.g.h(9, (AbstractC2126a) this.B.get(i15));
        }
        for (int i16 = 0; i16 < this.f17375C.size(); i16++) {
            iH2 += A2.g.h(10, (AbstractC2126a) this.f17375C.get(i16));
        }
        for (int i17 = 0; i17 < this.f17376D.size(); i17++) {
            iH2 += A2.g.h(11, (AbstractC2126a) this.f17376D.get(i17));
        }
        for (int i18 = 0; i18 < this.f17377E.size(); i18++) {
            iH2 += A2.g.h(13, (AbstractC2126a) this.f17377E.get(i18));
        }
        int iG3 = 0;
        for (int i19 = 0; i19 < this.f17378F.size(); i19++) {
            iG3 += A2.g.g(((Integer) this.f17378F.get(i19)).intValue());
        }
        int iH3 = iH2 + iG3;
        if (!this.f17378F.isEmpty()) {
            iH3 = iH3 + 2 + A2.g.g(iG3);
        }
        this.f17379G = iG3;
        if ((this.f17394n & 8) == 8) {
            iH3 += A2.g.f(17, this.f17380H);
        }
        if ((this.f17394n & 16) == 16) {
            iH3 += A2.g.h(18, this.f17381I);
        }
        if ((this.f17394n & 32) == 32) {
            iH3 += A2.g.f(19, this.f17382J);
        }
        for (int i20 = 0; i20 < this.f17403x.size(); i20++) {
            iH3 += A2.g.h(20, (AbstractC2126a) this.f17403x.get(i20));
        }
        int iG4 = 0;
        for (int i21 = 0; i21 < this.f17404y.size(); i21++) {
            iG4 += A2.g.g(((Integer) this.f17404y.get(i21)).intValue());
        }
        int iG5 = iH3 + iG4;
        if (!this.f17404y.isEmpty()) {
            iG5 = iG5 + 2 + A2.g.g(iG4);
        }
        this.f17405z = iG4;
        int iG6 = 0;
        for (int i22 = 0; i22 < this.f17383K.size(); i22++) {
            iG6 += A2.g.g(((Integer) this.f17383K.get(i22)).intValue());
        }
        int iH4 = iG5 + iG6;
        if (!this.f17383K.isEmpty()) {
            iH4 = iH4 + 2 + A2.g.g(iG6);
        }
        this.f17384L = iG6;
        for (int i23 = 0; i23 < this.f17385M.size(); i23++) {
            iH4 += A2.g.h(23, (AbstractC2126a) this.f17385M.get(i23));
        }
        int iG7 = 0;
        for (int i24 = 0; i24 < this.f17386N.size(); i24++) {
            iG7 += A2.g.g(((Integer) this.f17386N.get(i24)).intValue());
        }
        int iH5 = iH4 + iG7;
        if (!this.f17386N.isEmpty()) {
            iH5 = iH5 + 2 + A2.g.g(iG7);
        }
        this.f17387O = iG7;
        if ((this.f17394n & 64) == 64) {
            iH5 += A2.g.h(30, this.f17388P);
        }
        int iG8 = 0;
        for (int i25 = 0; i25 < this.f17389Q.size(); i25++) {
            iG8 += A2.g.g(((Integer) this.f17389Q.get(i25)).intValue());
        }
        int size = (this.f17389Q.size() * 2) + iH5 + iG8;
        if ((this.f17394n & 128) == 128) {
            size += A2.g.h(32, this.f17390R);
        }
        int size2 = this.f17393m.size() + j() + size;
        this.f17392T = size2;
        return size2;
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i d() {
        return C1563h.h();
    }

    @Override // p8.AbstractC2126a
    public final AbstractC2134i e() {
        C1563h c1563hH = C1563h.h();
        c1563hH.i(this);
        return c1563hH;
    }

    @Override // p8.AbstractC2126a
    public final void f(A2.g gVar) throws IOException {
        c();
        C1789g c1789g = new C1789g(this);
        if ((this.f17394n & 1) == 1) {
            gVar.C(1, this.o);
        }
        if (this.f17399t.size() > 0) {
            gVar.L(18);
            gVar.L(this.f17400u);
        }
        for (int i7 = 0; i7 < this.f17399t.size(); i7++) {
            gVar.D(((Integer) this.f17399t.get(i7)).intValue());
        }
        if ((this.f17394n & 2) == 2) {
            gVar.C(3, this.f17395p);
        }
        if ((this.f17394n & 4) == 4) {
            gVar.C(4, this.f17396q);
        }
        for (int i10 = 0; i10 < this.f17397r.size(); i10++) {
            gVar.E(5, (AbstractC2126a) this.f17397r.get(i10));
        }
        for (int i11 = 0; i11 < this.f17398s.size(); i11++) {
            gVar.E(6, (AbstractC2126a) this.f17398s.get(i11));
        }
        if (this.f17401v.size() > 0) {
            gVar.L(58);
            gVar.L(this.f17402w);
        }
        for (int i12 = 0; i12 < this.f17401v.size(); i12++) {
            gVar.D(((Integer) this.f17401v.get(i12)).intValue());
        }
        for (int i13 = 0; i13 < this.f17374A.size(); i13++) {
            gVar.E(8, (AbstractC2126a) this.f17374A.get(i13));
        }
        for (int i14 = 0; i14 < this.B.size(); i14++) {
            gVar.E(9, (AbstractC2126a) this.B.get(i14));
        }
        for (int i15 = 0; i15 < this.f17375C.size(); i15++) {
            gVar.E(10, (AbstractC2126a) this.f17375C.get(i15));
        }
        for (int i16 = 0; i16 < this.f17376D.size(); i16++) {
            gVar.E(11, (AbstractC2126a) this.f17376D.get(i16));
        }
        for (int i17 = 0; i17 < this.f17377E.size(); i17++) {
            gVar.E(13, (AbstractC2126a) this.f17377E.get(i17));
        }
        if (this.f17378F.size() > 0) {
            gVar.L(130);
            gVar.L(this.f17379G);
        }
        for (int i18 = 0; i18 < this.f17378F.size(); i18++) {
            gVar.D(((Integer) this.f17378F.get(i18)).intValue());
        }
        if ((this.f17394n & 8) == 8) {
            gVar.C(17, this.f17380H);
        }
        if ((this.f17394n & 16) == 16) {
            gVar.E(18, this.f17381I);
        }
        if ((this.f17394n & 32) == 32) {
            gVar.C(19, this.f17382J);
        }
        for (int i19 = 0; i19 < this.f17403x.size(); i19++) {
            gVar.E(20, (AbstractC2126a) this.f17403x.get(i19));
        }
        if (this.f17404y.size() > 0) {
            gVar.L(170);
            gVar.L(this.f17405z);
        }
        for (int i20 = 0; i20 < this.f17404y.size(); i20++) {
            gVar.D(((Integer) this.f17404y.get(i20)).intValue());
        }
        if (this.f17383K.size() > 0) {
            gVar.L(178);
            gVar.L(this.f17384L);
        }
        for (int i21 = 0; i21 < this.f17383K.size(); i21++) {
            gVar.D(((Integer) this.f17383K.get(i21)).intValue());
        }
        for (int i22 = 0; i22 < this.f17385M.size(); i22++) {
            gVar.E(23, (AbstractC2126a) this.f17385M.get(i22));
        }
        if (this.f17386N.size() > 0) {
            gVar.L(194);
            gVar.L(this.f17387O);
        }
        for (int i23 = 0; i23 < this.f17386N.size(); i23++) {
            gVar.D(((Integer) this.f17386N.get(i23)).intValue());
        }
        if ((this.f17394n & 64) == 64) {
            gVar.E(30, this.f17388P);
        }
        for (int i24 = 0; i24 < this.f17389Q.size(); i24++) {
            gVar.C(31, ((Integer) this.f17389Q.get(i24)).intValue());
        }
        if ((this.f17394n & 128) == 128) {
            gVar.E(32, this.f17390R);
        }
        c1789g.H(19000, gVar);
        gVar.H(this.f17393m);
    }

    public final void p() {
        this.o = 6;
        this.f17395p = 0;
        this.f17396q = 0;
        this.f17397r = Collections.emptyList();
        this.f17398s = Collections.emptyList();
        this.f17399t = Collections.emptyList();
        this.f17401v = Collections.emptyList();
        this.f17403x = Collections.emptyList();
        this.f17404y = Collections.emptyList();
        this.f17374A = Collections.emptyList();
        this.B = Collections.emptyList();
        this.f17375C = Collections.emptyList();
        this.f17376D = Collections.emptyList();
        this.f17377E = Collections.emptyList();
        this.f17378F = Collections.emptyList();
        this.f17380H = 0;
        this.f17381I = Q.f17178E;
        this.f17382J = 0;
        this.f17383K = Collections.emptyList();
        this.f17385M = Collections.emptyList();
        this.f17386N = Collections.emptyList();
        this.f17388P = X.f17240r;
        this.f17389Q = Collections.emptyList();
        this.f17390R = e0.f17330p;
    }

    public C1565j() {
        this.f17400u = -1;
        this.f17402w = -1;
        this.f17405z = -1;
        this.f17379G = -1;
        this.f17384L = -1;
        this.f17387O = -1;
        this.f17391S = (byte) -1;
        this.f17392T = -1;
        this.f17393m = AbstractC2129d.l;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v23, types: [j8.m] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v12, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r9v51, types: [j8.P] */
    /* JADX WARN: Type inference failed for: r9v82, types: [j8.f] */
    public C1565j(C2130e c2130e, C2132g c2132g) {
        boolean z6;
        this.f17400u = -1;
        this.f17402w = -1;
        this.f17405z = -1;
        this.f17379G = -1;
        this.f17384L = -1;
        this.f17387O = -1;
        this.f17391S = (byte) -1;
        this.f17392T = -1;
        p();
        C2128c c2128cT = AbstractC2129d.t();
        A2.g gVarT = A2.g.t(c2128cT, 1);
        boolean z10 = false;
        int i7 = 0;
        while (true) {
            ?? N8 = 64;
            if (!z10) {
                try {
                    try {
                        try {
                            int iN = c2130e.n();
                            switch (iN) {
                                case 0:
                                    z6 = true;
                                    z10 = true;
                                    break;
                                case 8:
                                    z6 = true;
                                    this.f17394n |= 1;
                                    this.o = c2130e.f();
                                    break;
                                case 16:
                                    if ((i7 & 32) != 32) {
                                        this.f17399t = new ArrayList();
                                        i7 |= 32;
                                    }
                                    this.f17399t.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 18:
                                    int iD = c2130e.d(c2130e.k());
                                    if ((i7 & 32) != 32 && c2130e.b() > 0) {
                                        this.f17399t = new ArrayList();
                                        i7 |= 32;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17399t.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD);
                                    z6 = true;
                                    break;
                                case 24:
                                    this.f17394n |= 2;
                                    this.f17395p = c2130e.f();
                                    z6 = true;
                                    break;
                                case 32:
                                    this.f17394n |= 4;
                                    this.f17396q = c2130e.f();
                                    z6 = true;
                                    break;
                                case 42:
                                    if ((i7 & 8) != 8) {
                                        this.f17397r = new ArrayList();
                                        i7 |= 8;
                                    }
                                    this.f17397r.add(c2130e.g(W.f17229y, c2132g));
                                    z6 = true;
                                    break;
                                case 50:
                                    if ((i7 & 16) != 16) {
                                        this.f17398s = new ArrayList();
                                        i7 |= 16;
                                    }
                                    this.f17398s.add(c2130e.g(Q.f17179F, c2132g));
                                    z6 = true;
                                    break;
                                case 56:
                                    if ((i7 & 64) != 64) {
                                        this.f17401v = new ArrayList();
                                        i7 |= 64;
                                    }
                                    this.f17401v.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 58:
                                    int iD2 = c2130e.d(c2130e.k());
                                    if ((i7 & 64) != 64 && c2130e.b() > 0) {
                                        this.f17401v = new ArrayList();
                                        i7 |= 64;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17401v.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD2);
                                    z6 = true;
                                    break;
                                case 66:
                                    if ((i7 & 512) != 512) {
                                        this.f17374A = new ArrayList();
                                        i7 |= 512;
                                    }
                                    this.f17374A.add(c2130e.g(C1567l.f17410u, c2132g));
                                    z6 = true;
                                    break;
                                case 74:
                                    if ((i7 & 1024) != 1024) {
                                        this.B = new ArrayList();
                                        i7 |= 1024;
                                    }
                                    this.B.add(c2130e.g(C1579y.f17483G, c2132g));
                                    z6 = true;
                                    break;
                                case 82:
                                    if ((i7 & 2048) != 2048) {
                                        this.f17375C = new ArrayList();
                                        i7 |= 2048;
                                    }
                                    this.f17375C.add(c2130e.g(G.f17112G, c2132g));
                                    z6 = true;
                                    break;
                                case 90:
                                    if ((i7 & 4096) != 4096) {
                                        this.f17376D = new ArrayList();
                                        i7 |= 4096;
                                    }
                                    this.f17376D.add(c2130e.g(T.f17205A, c2132g));
                                    z6 = true;
                                    break;
                                case 106:
                                    if ((i7 & 8192) != 8192) {
                                        this.f17377E = new ArrayList();
                                        i7 |= 8192;
                                    }
                                    this.f17377E.add(c2130e.g(C1574t.f17443s, c2132g));
                                    z6 = true;
                                    break;
                                case 128:
                                    if ((i7 & 16384) != 16384) {
                                        this.f17378F = new ArrayList();
                                        i7 |= 16384;
                                    }
                                    this.f17378F.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 130:
                                    int iD3 = c2130e.d(c2130e.k());
                                    if ((i7 & 16384) != 16384 && c2130e.b() > 0) {
                                        this.f17378F = new ArrayList();
                                        i7 |= 16384;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17378F.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD3);
                                    z6 = true;
                                    break;
                                case 136:
                                    this.f17394n |= 8;
                                    this.f17380H = c2130e.f();
                                    z6 = true;
                                    break;
                                case 146:
                                    ?? S4 = (this.f17394n & 16) == 16 ? this.f17381I.e() : null;
                                    Q q4 = (Q) c2130e.g(Q.f17179F, c2132g);
                                    this.f17381I = q4;
                                    if (S4 != 0) {
                                        S4.i(q4);
                                        this.f17381I = S4.g();
                                    }
                                    this.f17394n |= 16;
                                    z6 = true;
                                    break;
                                case 152:
                                    this.f17394n |= 32;
                                    this.f17382J = c2130e.f();
                                    z6 = true;
                                    break;
                                case 162:
                                    if ((i7 & 128) != 128) {
                                        this.f17403x = new ArrayList();
                                        i7 |= 128;
                                    }
                                    this.f17403x.add(c2130e.g(Q.f17179F, c2132g));
                                    z6 = true;
                                    break;
                                case 168:
                                    if ((i7 & 256) != 256) {
                                        this.f17404y = new ArrayList();
                                        i7 |= 256;
                                    }
                                    this.f17404y.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 170:
                                    int iD4 = c2130e.d(c2130e.k());
                                    if ((i7 & 256) != 256 && c2130e.b() > 0) {
                                        this.f17404y = new ArrayList();
                                        i7 |= 256;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17404y.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD4);
                                    z6 = true;
                                    break;
                                case 176:
                                    if ((i7 & 262144) != 262144) {
                                        this.f17383K = new ArrayList();
                                        i7 |= 262144;
                                    }
                                    this.f17383K.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 178:
                                    int iD5 = c2130e.d(c2130e.k());
                                    if ((i7 & 262144) != 262144 && c2130e.b() > 0) {
                                        this.f17383K = new ArrayList();
                                        i7 |= 262144;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17383K.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD5);
                                    z6 = true;
                                    break;
                                case 186:
                                    if ((i7 & 524288) != 524288) {
                                        this.f17385M = new ArrayList();
                                        i7 |= 524288;
                                    }
                                    this.f17385M.add(c2130e.g(Q.f17179F, c2132g));
                                    z6 = true;
                                    break;
                                case 192:
                                    if ((i7 & 1048576) != 1048576) {
                                        this.f17386N = new ArrayList();
                                        i7 |= 1048576;
                                    }
                                    this.f17386N.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 194:
                                    int iD6 = c2130e.d(c2130e.k());
                                    if ((i7 & 1048576) != 1048576 && c2130e.b() > 0) {
                                        this.f17386N = new ArrayList();
                                        i7 |= 1048576;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17386N.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD6);
                                    z6 = true;
                                    break;
                                case 242:
                                    ?? J6 = (this.f17394n & 64) == 64 ? this.f17388P.j() : null;
                                    X x8 = (X) c2130e.g(X.f17241s, c2132g);
                                    this.f17388P = x8;
                                    if (J6 != 0) {
                                        J6.l(x8);
                                        this.f17388P = J6.h();
                                    }
                                    this.f17394n |= 64;
                                    z6 = true;
                                    break;
                                case 248:
                                    if ((i7 & 4194304) != 4194304) {
                                        this.f17389Q = new ArrayList();
                                        i7 |= 4194304;
                                    }
                                    this.f17389Q.add(Integer.valueOf(c2130e.f()));
                                    z6 = true;
                                    break;
                                case 250:
                                    int iD7 = c2130e.d(c2130e.k());
                                    if ((i7 & 4194304) != 4194304 && c2130e.b() > 0) {
                                        this.f17389Q = new ArrayList();
                                        i7 |= 4194304;
                                    }
                                    while (c2130e.b() > 0) {
                                        this.f17389Q.add(Integer.valueOf(c2130e.f()));
                                    }
                                    c2130e.c(iD7);
                                    z6 = true;
                                    break;
                                case 258:
                                    ?? I2 = (this.f17394n & 128) == 128 ? this.f17390R.i() : null;
                                    e0 e0Var = (e0) c2130e.g(e0.f17331q, c2132g);
                                    this.f17390R = e0Var;
                                    if (I2 != 0) {
                                        I2.m(e0Var);
                                        this.f17390R = I2.i();
                                    }
                                    this.f17394n |= 128;
                                    z6 = true;
                                    break;
                                default:
                                    N8 = n(c2130e, gVarT, c2132g, iN);
                                    if (N8 == 0) {
                                        z10 = true;
                                    }
                                    z6 = true;
                                    break;
                            }
                        } catch (C2142q e10) {
                            e10.l = this;
                            throw e10;
                        }
                    } catch (IOException e11) {
                        C2142q c2142q = new C2142q(e11.getMessage());
                        c2142q.l = this;
                        throw c2142q;
                    }
                } catch (Throwable th) {
                    if ((i7 & 32) == 32) {
                        this.f17399t = Collections.unmodifiableList(this.f17399t);
                    }
                    if ((i7 & 8) == 8) {
                        this.f17397r = Collections.unmodifiableList(this.f17397r);
                    }
                    if ((i7 & 16) == 16) {
                        this.f17398s = Collections.unmodifiableList(this.f17398s);
                    }
                    if ((i7 & 64) == N8) {
                        this.f17401v = Collections.unmodifiableList(this.f17401v);
                    }
                    if ((i7 & 512) == 512) {
                        this.f17374A = Collections.unmodifiableList(this.f17374A);
                    }
                    if ((i7 & 1024) == 1024) {
                        this.B = Collections.unmodifiableList(this.B);
                    }
                    if ((i7 & 2048) == 2048) {
                        this.f17375C = Collections.unmodifiableList(this.f17375C);
                    }
                    if ((i7 & 4096) == 4096) {
                        this.f17376D = Collections.unmodifiableList(this.f17376D);
                    }
                    if ((i7 & 8192) == 8192) {
                        this.f17377E = Collections.unmodifiableList(this.f17377E);
                    }
                    if ((i7 & 16384) == 16384) {
                        this.f17378F = Collections.unmodifiableList(this.f17378F);
                    }
                    if ((i7 & 128) == 128) {
                        this.f17403x = Collections.unmodifiableList(this.f17403x);
                    }
                    if ((i7 & 256) == 256) {
                        this.f17404y = Collections.unmodifiableList(this.f17404y);
                    }
                    if ((i7 & 262144) == 262144) {
                        this.f17383K = Collections.unmodifiableList(this.f17383K);
                    }
                    if ((i7 & 524288) == 524288) {
                        this.f17385M = Collections.unmodifiableList(this.f17385M);
                    }
                    if ((i7 & 1048576) == 1048576) {
                        this.f17386N = Collections.unmodifiableList(this.f17386N);
                    }
                    if ((i7 & 4194304) == 4194304) {
                        this.f17389Q = Collections.unmodifiableList(this.f17389Q);
                    }
                    try {
                        gVarT.n();
                    } catch (IOException unused) {
                    } catch (Throwable th2) {
                        this.f17393m = c2128cT.i();
                        throw th2;
                    }
                    this.f17393m = c2128cT.i();
                    m();
                    throw th;
                }
            } else {
                if ((i7 & 32) == 32) {
                    this.f17399t = Collections.unmodifiableList(this.f17399t);
                }
                if ((i7 & 8) == 8) {
                    this.f17397r = Collections.unmodifiableList(this.f17397r);
                }
                if ((i7 & 16) == 16) {
                    this.f17398s = Collections.unmodifiableList(this.f17398s);
                }
                if ((i7 & 64) == 64) {
                    this.f17401v = Collections.unmodifiableList(this.f17401v);
                }
                if ((i7 & 512) == 512) {
                    this.f17374A = Collections.unmodifiableList(this.f17374A);
                }
                if ((i7 & 1024) == 1024) {
                    this.B = Collections.unmodifiableList(this.B);
                }
                if ((i7 & 2048) == 2048) {
                    this.f17375C = Collections.unmodifiableList(this.f17375C);
                }
                if ((i7 & 4096) == 4096) {
                    this.f17376D = Collections.unmodifiableList(this.f17376D);
                }
                if ((i7 & 8192) == 8192) {
                    this.f17377E = Collections.unmodifiableList(this.f17377E);
                }
                if ((i7 & 16384) == 16384) {
                    this.f17378F = Collections.unmodifiableList(this.f17378F);
                }
                if ((i7 & 128) == 128) {
                    this.f17403x = Collections.unmodifiableList(this.f17403x);
                }
                if ((i7 & 256) == 256) {
                    this.f17404y = Collections.unmodifiableList(this.f17404y);
                }
                if ((i7 & 262144) == 262144) {
                    this.f17383K = Collections.unmodifiableList(this.f17383K);
                }
                if ((i7 & 524288) == 524288) {
                    this.f17385M = Collections.unmodifiableList(this.f17385M);
                }
                if ((i7 & 1048576) == 1048576) {
                    this.f17386N = Collections.unmodifiableList(this.f17386N);
                }
                if ((i7 & 4194304) == 4194304) {
                    this.f17389Q = Collections.unmodifiableList(this.f17389Q);
                }
                try {
                    gVarT.n();
                } catch (IOException unused2) {
                } catch (Throwable th3) {
                    this.f17393m = c2128cT.i();
                    throw th3;
                }
                this.f17393m = c2128cT.i();
                m();
                return;
            }
        }
    }
}
