package r2;

import android.graphics.Bitmap;
import android.os.Trace;
import androidx.media3.exoplayer.image.ImageOutput;
import e2.C1208p;
import h2.AbstractC1346c;
import java.util.ArrayDeque;
import l3.C1764u;
import l5.C1778f;
import n2.AbstractC1905e;
import n2.C1913m;

/* JADX INFO: renamed from: r2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2286h extends AbstractC1905e {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C1764u f21789C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final m2.g f21790D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ArrayDeque f21791E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f21792F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f21793G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C2284f f21794H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public long f21795I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f21796J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f21797K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public int f21798L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public C1208p f21799M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C2280b f21800N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public m2.g f21801O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public ImageOutput f21802P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Bitmap f21803Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f21804R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public C2285g f21805S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public C2285g f21806T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public int f21807U;

    public C2286h(C1764u c1764u) {
        super(4);
        this.f21789C = c1764u;
        this.f21802P = ImageOutput.f12018a;
        this.f21790D = new m2.g(0);
        this.f21794H = C2284f.c;
        this.f21791E = new ArrayDeque();
        this.f21796J = -9223372036854775807L;
        this.f21795I = -9223372036854775807L;
        this.f21797K = 0;
        this.f21798L = 1;
    }

    @Override // n2.AbstractC1905e
    public final int D(C1208p c1208p) {
        return this.f21789C.W(c1208p);
    }

    /* JADX WARN: Removed duplicated region for block: B:78:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean F(long r13) throws n2.C1913m {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r2.C2286h.F(long):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0105  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean G(long r13) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r2.C2286h.G(long):boolean");
    }

    public final void H() throws C1913m {
        C1208p c1208p = this.f21799M;
        C1764u c1764u = this.f21789C;
        int iW = c1764u.W(c1208p);
        if (iW != AbstractC1905e.d(4, 0, 0, 0) && iW != AbstractC1905e.d(3, 0, 0, 0)) {
            throw e(new C2282d("Provided decoder factory can't create decoder for format."), this.f21799M, false, 4005);
        }
        C2280b c2280b = this.f21800N;
        if (c2280b != null) {
            c2280b.a();
        }
        this.f21800N = new C2280b((o2.d) c1764u.f18608m);
    }

    public final void I() {
        this.f21801O = null;
        this.f21797K = 0;
        this.f21796J = -9223372036854775807L;
        C2280b c2280b = this.f21800N;
        if (c2280b != null) {
            c2280b.a();
            this.f21800N = null;
        }
    }

    @Override // n2.AbstractC1905e, n2.W
    public final void b(int i7, Object obj) {
        if (i7 != 15) {
            return;
        }
        ImageOutput imageOutput = obj instanceof ImageOutput ? (ImageOutput) obj : null;
        if (imageOutput == null) {
            imageOutput = ImageOutput.f12018a;
        }
        this.f21802P = imageOutput;
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "ImageRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        return this.f21793G;
    }

    @Override // n2.AbstractC1905e
    public final boolean p() {
        int i7 = this.f21798L;
        return i7 == 3 || (i7 == 0 && this.f21804R);
    }

    @Override // n2.AbstractC1905e
    public final void q() {
        this.f21799M = null;
        this.f21794H = C2284f.c;
        this.f21791E.clear();
        I();
        this.f21802P.a();
    }

    @Override // n2.AbstractC1905e
    public final void r(boolean z6, boolean z10) {
        this.f21798L = z10 ? 1 : 0;
    }

    @Override // n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        this.f21798L = Math.min(this.f21798L, 1);
        this.f21793G = false;
        this.f21792F = false;
        this.f21803Q = null;
        this.f21805S = null;
        this.f21806T = null;
        this.f21804R = false;
        this.f21801O = null;
        C2280b c2280b = this.f21800N;
        if (c2280b != null) {
            c2280b.flush();
        }
        this.f21791E.clear();
    }

    @Override // n2.AbstractC1905e
    public final void t() {
        I();
    }

    @Override // n2.AbstractC1905e
    public final void u() {
        I();
        this.f21798L = Math.min(this.f21798L, 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0023, code lost:
    
        if (r2 >= r6) goto L14;
     */
    @Override // n2.AbstractC1905e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x(e2.C1208p[] r5, long r6, long r8, u2.C2492C r10) {
        /*
            r4 = this;
            r2.f r5 = r4.f21794H
            long r5 = r5.f21786b
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r5 == 0) goto L31
            java.util.ArrayDeque r5 = r4.f21791E
            boolean r6 = r5.isEmpty()
            if (r6 == 0) goto L26
            long r6 = r4.f21796J
            int r10 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r10 == 0) goto L31
            long r2 = r4.f21795I
            int r10 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r10 == 0) goto L26
            int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r6 < 0) goto L26
            goto L31
        L26:
            r2.f r6 = new r2.f
            long r0 = r4.f21796J
            r6.<init>(r0, r8)
            r5.add(r6)
            goto L38
        L31:
            r2.f r5 = new r2.f
            r5.<init>(r0, r8)
            r4.f21794H = r5
        L38:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: r2.C2286h.x(e2.p[], long, long, u2.C):void");
    }

    @Override // n2.AbstractC1905e
    public final void z(long j3, long j10) throws C1913m {
        if (this.f21793G) {
            return;
        }
        if (this.f21799M == null) {
            C1778f c1778f = this.f19637n;
            c1778f.c();
            m2.g gVar = this.f21790D;
            gVar.f();
            int iY = y(c1778f, gVar, 2);
            if (iY != -5) {
                if (iY == -4) {
                    AbstractC1346c.j(gVar.e(4));
                    this.f21792F = true;
                    this.f21793G = true;
                    return;
                }
                return;
            }
            C1208p c1208p = (C1208p) c1778f.f18639n;
            AbstractC1346c.k(c1208p);
            this.f21799M = c1208p;
            H();
        }
        try {
            Trace.beginSection("drainAndFeedDecoder");
            while (F(j3)) {
            }
            while (G(j3)) {
            }
            Trace.endSection();
        } catch (C2282d e10) {
            throw e(e10, null, false, 4003);
        }
    }
}
