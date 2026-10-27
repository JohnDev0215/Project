package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2135j;
import p8.AbstractC2139n;

/* JADX INFO: loaded from: classes.dex */
public final class F extends AbstractC2135j {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f17098A;
    public int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public List f17099C;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f17100p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f17101q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f17102r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Q f17103s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f17104t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f17105u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Q f17106v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f17107w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public List f17108x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public List f17109y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Z f17110z;

    public static F h() {
        F f10 = new F();
        f10.f17100p = 518;
        f10.f17101q = 2054;
        Q q4 = Q.f17178E;
        f10.f17103s = q4;
        f10.f17105u = Collections.emptyList();
        f10.f17106v = q4;
        f10.f17108x = Collections.emptyList();
        f10.f17109y = Collections.emptyList();
        f10.f17110z = Z.f17252w;
        f10.f17099C = Collections.emptyList();
        return f10;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        G g7 = g();
        if (g7.b()) {
            return g7;
        }
        throw new A9.k();
    }

    public final Object clone() {
        F fH = h();
        fH.i(g());
        return fH;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r3, p8.C2132g r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            j8.a r1 = j8.G.f17112G     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.G r1 = new j8.G     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.i(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.G r4 = (j8.G) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.i(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.F.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        i((G) abstractC2139n);
        return this;
    }

    public final G g() {
        G g7 = new G(this);
        int i7 = this.o;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        g7.o = this.f17100p;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        g7.f17119p = this.f17101q;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        g7.f17120q = this.f17102r;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        g7.f17121r = this.f17103s;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        g7.f17122s = this.f17104t;
        if ((i7 & 32) == 32) {
            this.f17105u = Collections.unmodifiableList(this.f17105u);
            this.o &= -33;
        }
        g7.f17123t = this.f17105u;
        if ((i7 & 64) == 64) {
            i10 |= 32;
        }
        g7.f17124u = this.f17106v;
        if ((i7 & 128) == 128) {
            i10 |= 64;
        }
        g7.f17125v = this.f17107w;
        if ((this.o & 256) == 256) {
            this.f17108x = Collections.unmodifiableList(this.f17108x);
            this.o &= -257;
        }
        g7.f17126w = this.f17108x;
        if ((this.o & 512) == 512) {
            this.f17109y = Collections.unmodifiableList(this.f17109y);
            this.o &= -513;
        }
        g7.f17127x = this.f17109y;
        if ((i7 & 1024) == 1024) {
            i10 |= 128;
        }
        g7.f17129z = this.f17110z;
        if ((i7 & 2048) == 2048) {
            i10 |= 256;
        }
        g7.f17113A = this.f17098A;
        if ((i7 & 4096) == 4096) {
            i10 |= 512;
        }
        g7.B = this.B;
        if ((this.o & 8192) == 8192) {
            this.f17099C = Collections.unmodifiableList(this.f17099C);
            this.o &= -8193;
        }
        g7.f17114C = this.f17099C;
        g7.f17118n = i10;
        return g7;
    }

    public final void i(G g7) {
        Z z6;
        Q q4;
        Q q10;
        if (g7 == G.f17111F) {
            return;
        }
        int i7 = g7.f17118n;
        if ((i7 & 1) == 1) {
            int i10 = g7.o;
            this.o = 1 | this.o;
            this.f17100p = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = g7.f17119p;
            this.o = 2 | this.o;
            this.f17101q = i11;
        }
        if ((i7 & 4) == 4) {
            int i12 = g7.f17120q;
            this.o = 4 | this.o;
            this.f17102r = i12;
        }
        if ((i7 & 8) == 8) {
            Q q11 = g7.f17121r;
            if ((this.o & 8) != 8 || (q10 = this.f17103s) == Q.f17178E) {
                this.f17103s = q11;
            } else {
                P pR = Q.r(q10);
                pR.i(q11);
                this.f17103s = pR.g();
            }
            this.o |= 8;
        }
        if ((g7.f17118n & 16) == 16) {
            int i13 = g7.f17122s;
            this.o = 16 | this.o;
            this.f17104t = i13;
        }
        if (!g7.f17123t.isEmpty()) {
            if (this.f17105u.isEmpty()) {
                this.f17105u = g7.f17123t;
                this.o &= -33;
            } else {
                if ((this.o & 32) != 32) {
                    this.f17105u = new ArrayList(this.f17105u);
                    this.o |= 32;
                }
                this.f17105u.addAll(g7.f17123t);
            }
        }
        if (g7.p()) {
            Q q12 = g7.f17124u;
            if ((this.o & 64) != 64 || (q4 = this.f17106v) == Q.f17178E) {
                this.f17106v = q12;
            } else {
                P pR2 = Q.r(q4);
                pR2.i(q12);
                this.f17106v = pR2.g();
            }
            this.o |= 64;
        }
        if ((g7.f17118n & 64) == 64) {
            int i14 = g7.f17125v;
            this.o |= 128;
            this.f17107w = i14;
        }
        if (!g7.f17126w.isEmpty()) {
            if (this.f17108x.isEmpty()) {
                this.f17108x = g7.f17126w;
                this.o &= -257;
            } else {
                if ((this.o & 256) != 256) {
                    this.f17108x = new ArrayList(this.f17108x);
                    this.o |= 256;
                }
                this.f17108x.addAll(g7.f17126w);
            }
        }
        if (!g7.f17127x.isEmpty()) {
            if (this.f17109y.isEmpty()) {
                this.f17109y = g7.f17127x;
                this.o &= -513;
            } else {
                if ((this.o & 512) != 512) {
                    this.f17109y = new ArrayList(this.f17109y);
                    this.o |= 512;
                }
                this.f17109y.addAll(g7.f17127x);
            }
        }
        if ((g7.f17118n & 128) == 128) {
            Z z10 = g7.f17129z;
            if ((this.o & 1024) != 1024 || (z6 = this.f17110z) == Z.f17252w) {
                this.f17110z = z10;
            } else {
                Y y10 = new Y();
                Q q13 = Q.f17178E;
                y10.f17248r = q13;
                y10.f17250t = q13;
                y10.h(z6);
                y10.h(z10);
                this.f17110z = y10.g();
            }
            this.o |= 1024;
        }
        int i15 = g7.f17118n;
        if ((i15 & 256) == 256) {
            int i16 = g7.f17113A;
            this.o |= 2048;
            this.f17098A = i16;
        }
        if ((i15 & 512) == 512) {
            int i17 = g7.B;
            this.o |= 4096;
            this.B = i17;
        }
        if (!g7.f17114C.isEmpty()) {
            if (this.f17099C.isEmpty()) {
                this.f17099C = g7.f17114C;
                this.o &= -8193;
            } else {
                if ((this.o & 8192) != 8192) {
                    this.f17099C = new ArrayList(this.f17099C);
                    this.o |= 8192;
                }
                this.f17099C.addAll(g7.f17114C);
            }
        }
        f(g7);
        this.l = this.l.d(g7.f17117m);
    }
}
