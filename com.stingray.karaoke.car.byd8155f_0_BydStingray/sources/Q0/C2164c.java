package q0;

import A7.o;
import H7.H;
import P.C;
import a1.k;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.os.Build;
import m0.AbstractC1792a;
import n0.AbstractC1858H;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1878g;
import n0.C1880i;
import n7.z;
import p0.AbstractC2072c;

/* JADX INFO: renamed from: q0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2164c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2165d f21120a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Outline f21123f;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f21127j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public AbstractC1858H f21128k;
    public C1880i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C1880i f21129m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21130n;
    public C1878g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21131p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f21133r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f21134s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f21135t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long f21136u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21137v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public RectF f21138w;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a1.b f21121b = AbstractC2072c.f20640a;
    public k c = k.l;
    public o d = C2163b.f21119m;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2162a f21122e = new C2162a(this, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21124g = true;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f21125h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f21126i = 9205357640488583168L;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C f21132q = new C();

    static {
        int i7 = AbstractC2166e.f21158a;
        int i10 = AbstractC2166e.f21158a;
    }

    public C2164c(C2165d c2165d) {
        this.f21120a = c2165d;
        c2165d.c(false);
        this.f21134s = 0L;
        this.f21135t = 0L;
        this.f21136u = 9205357640488583168L;
    }

    public final void a() {
        if (this.f21124g) {
            boolean z6 = this.f21137v;
            C2165d c2165d = this.f21120a;
            RenderNode renderNode = c2165d.c;
            if (z6 || c2165d.f21148m > 0.0f) {
                C1880i c1880i = this.l;
                if (c1880i != null) {
                    RectF rectF = this.f21138w;
                    if (rectF == null) {
                        rectF = new RectF();
                        this.f21138w = rectF;
                    }
                    Path path = c1880i.f19436a;
                    path.computeBounds(rectF, false);
                    int i7 = Build.VERSION.SDK_INT;
                    Outline outline = this.f21123f;
                    if (outline == null) {
                        outline = new Outline();
                        this.f21123f = outline;
                    }
                    if (i7 >= 30) {
                        C2167f.f21159a.a(outline, c1880i);
                    } else {
                        outline.setConvexPath(path);
                    }
                    this.f21130n = !outline.canClip();
                    this.l = c1880i;
                    outline.setAlpha(c2165d.f21143g);
                    Math.round(rectF.width());
                    Math.round(rectF.height());
                    renderNode.setOutline(outline);
                    c2165d.f21142f = true;
                    c2165d.a();
                    if (this.f21130n && this.f21137v) {
                        c2165d.c(false);
                        renderNode.discardDisplayList();
                    } else {
                        c2165d.c(this.f21137v);
                    }
                } else {
                    c2165d.c(z6);
                    Outline outline2 = this.f21123f;
                    if (outline2 == null) {
                        outline2 = new Outline();
                        this.f21123f = outline2;
                    }
                    long jS = H.S(this.f21135t);
                    long j3 = this.f21125h;
                    long j10 = this.f21126i;
                    long j11 = j10 == 9205357640488583168L ? jS : j10;
                    outline2.setRoundRect(Math.round(m0.c.d(j3)), Math.round(m0.c.e(j3)), Math.round(m0.f.d(j11) + m0.c.d(j3)), Math.round(m0.f.b(j11) + m0.c.e(j3)), this.f21127j);
                    outline2.setAlpha(c2165d.f21143g);
                    Math.round(m0.f.d(j11));
                    Math.round(m0.f.b(j11));
                    renderNode.setOutline(outline2);
                    c2165d.f21142f = true;
                    c2165d.a();
                }
            } else {
                c2165d.c(false);
                renderNode.setOutline(null);
                c2165d.f21142f = false;
                c2165d.a();
            }
        }
        this.f21124g = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            r15 = this;
            boolean r0 = r15.f21133r
            if (r0 == 0) goto L6b
            int r0 = r15.f21131p
            if (r0 != 0) goto L6b
            P.C r0 = r15.f21132q
            java.lang.Object r1 = r0.f5203b
            q0.c r1 = (q0.C2164c) r1
            if (r1 == 0) goto L16
            r1.d()
            r1 = 0
            r0.f5203b = r1
        L16:
            java.lang.Object r0 = r0.d
            u.A r0 = (u.C2453A) r0
            if (r0 == 0) goto L64
            java.lang.Object[] r1 = r0.f22580b
            long[] r2 = r0.f22579a
            int r3 = r2.length
            int r3 = r3 + (-2)
            if (r3 < 0) goto L61
            r4 = 0
            r5 = r4
        L27:
            r6 = r2[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto L5c
            int r8 = r5 - r3
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r4
        L41:
            if (r10 >= r8) goto L5a
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto L56
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r1[r11]
            q0.c r11 = (q0.C2164c) r11
            r11.d()
        L56:
            long r6 = r6 >> r9
            int r10 = r10 + 1
            goto L41
        L5a:
            if (r8 != r9) goto L61
        L5c:
            if (r5 == r3) goto L61
            int r5 = r5 + 1
            goto L27
        L61:
            r0.b()
        L64:
            q0.d r15 = r15.f21120a
            android.graphics.RenderNode r15 = r15.c
            r15.discardDisplayList()
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q0.C2164c.b():void");
    }

    public final AbstractC1858H c() {
        AbstractC1858H c1856f;
        AbstractC1858H abstractC1858H = this.f21128k;
        C1880i c1880i = this.l;
        if (abstractC1858H != null) {
            return abstractC1858H;
        }
        if (c1880i != null) {
            C1855E c1855e = new C1855E(c1880i);
            this.f21128k = c1855e;
            return c1855e;
        }
        long jS = H.S(this.f21135t);
        long j3 = this.f21125h;
        long j10 = this.f21126i;
        if (j10 != 9205357640488583168L) {
            jS = j10;
        }
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        float fD2 = m0.f.d(jS) + fD;
        float fB = m0.f.b(jS) + fE;
        float f10 = this.f21127j;
        if (f10 > 0.0f) {
            long jA = z.a(f10, f10);
            long jA2 = z.a(AbstractC1792a.b(jA), AbstractC1792a.c(jA));
            c1856f = new C1857G(new m0.e(fD, fE, fD2, fB, jA2, jA2, jA2, jA2));
        } else {
            c1856f = new C1856F(new m0.d(fD, fE, fD2, fB));
        }
        this.f21128k = c1856f;
        return c1856f;
    }

    public final void d() {
        this.f21131p--;
        b();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            r15 = this;
            r0 = 1
            P.C r1 = r15.f21132q
            java.lang.Object r2 = r1.f5203b
            q0.c r2 = (q0.C2164c) r2
            r1.c = r2
            java.lang.Object r2 = r1.d
            u.A r2 = (u.C2453A) r2
            if (r2 == 0) goto L2a
            boolean r3 = r2.h()
            if (r3 == 0) goto L2a
            java.lang.Object r3 = r1.f5204e
            u.A r3 = (u.C2453A) r3
            if (r3 != 0) goto L24
            int r3 = u.AbstractC2457E.f22585a
            u.A r3 = new u.A
            r3.<init>()
            r1.f5204e = r3
        L24:
            r3.i(r2)
            r2.b()
        L2a:
            r1.f5202a = r0
            a1.b r2 = r15.f21121b
            a1.k r3 = r15.c
            q0.a r4 = r15.f21122e
            q0.d r5 = r15.f21120a
            p0.b r6 = r5.f21140b
            android.graphics.RenderNode r7 = r5.c
            android.graphics.RecordingCanvas r8 = r7.beginRecording()
            l3.u r9 = r5.f21139a     // Catch: java.lang.Throwable -> Lc2
            java.lang.Object r10 = r9.f18608m     // Catch: java.lang.Throwable -> Lc2
            n0.b r10 = (n0.C1873b) r10     // Catch: java.lang.Throwable -> Lc2
            android.graphics.Canvas r11 = r10.f19426a     // Catch: java.lang.Throwable -> Lc2
            r10.f19426a = r8     // Catch: java.lang.Throwable -> Lc2
            h3.y r8 = r6.f20638m     // Catch: java.lang.Throwable -> Lc2
            r8.J(r2)     // Catch: java.lang.Throwable -> Lc2
            r8.K(r3)     // Catch: java.lang.Throwable -> Lc2
            r8.f15500n = r15     // Catch: java.lang.Throwable -> Lc2
            long r2 = r5.d     // Catch: java.lang.Throwable -> Lc2
            r8.L(r2)     // Catch: java.lang.Throwable -> Lc2
            r8.I(r10)     // Catch: java.lang.Throwable -> Lc2
            r4.b(r6)     // Catch: java.lang.Throwable -> Lc2
            java.lang.Object r15 = r9.f18608m     // Catch: java.lang.Throwable -> Lc2
            n0.b r15 = (n0.C1873b) r15     // Catch: java.lang.Throwable -> Lc2
            r15.f19426a = r11     // Catch: java.lang.Throwable -> Lc2
            r7.endRecording()
            r15 = 0
            r1.f5202a = r15
            java.lang.Object r2 = r1.c
            q0.c r2 = (q0.C2164c) r2
            if (r2 == 0) goto L70
            r2.d()
        L70:
            java.lang.Object r1 = r1.f5204e
            u.A r1 = (u.C2453A) r1
            if (r1 == 0) goto Lc1
            boolean r2 = r1.h()
            if (r2 == 0) goto Lc1
            java.lang.Object[] r2 = r1.f22580b
            long[] r3 = r1.f22579a
            int r4 = r3.length
            int r4 = r4 + (-2)
            if (r4 < 0) goto Lbe
            r5 = r15
        L86:
            r6 = r3[r5]
            long r8 = ~r6
            r10 = 7
            long r8 = r8 << r10
            long r8 = r8 & r6
            r10 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r8 = r8 & r10
            int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r8 == 0) goto Lba
            int r8 = r5 - r4
            int r8 = ~r8
            int r8 = r8 >>> 31
            r9 = 8
            int r8 = 8 - r8
            r10 = r15
        La0:
            if (r10 >= r8) goto Lb8
            r11 = 255(0xff, double:1.26E-321)
            long r11 = r11 & r6
            r13 = 128(0x80, double:6.3E-322)
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 >= 0) goto Lb5
            int r11 = r5 << 3
            int r11 = r11 + r10
            r11 = r2[r11]
            q0.c r11 = (q0.C2164c) r11
            r11.d()
        Lb5:
            long r6 = r6 >> r9
            int r10 = r10 + r0
            goto La0
        Lb8:
            if (r8 != r9) goto Lbe
        Lba:
            if (r5 == r4) goto Lbe
            int r5 = r5 + r0
            goto L86
        Lbe:
            r1.b()
        Lc1:
            return
        Lc2:
            r15 = move-exception
            r7.endRecording()
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: q0.C2164c.e():void");
    }

    public final void f(float f10) {
        C2165d c2165d = this.f21120a;
        if (c2165d.f21143g == f10) {
            return;
        }
        c2165d.f21143g = f10;
        c2165d.c.setAlpha(f10);
    }

    public final void g(long j3, long j10, float f10) {
        if (m0.c.b(this.f21125h, j3) && m0.f.a(this.f21126i, j10) && this.f21127j == f10 && this.l == null) {
            return;
        }
        this.f21128k = null;
        this.l = null;
        this.f21124g = true;
        this.f21130n = false;
        this.f21125h = j3;
        this.f21126i = j10;
        this.f21127j = f10;
        a();
    }
}
