package G;

import T.InterfaceC0769u0;
import android.view.Choreographer;
import android.view.View;

/* JADX INFO: renamed from: G.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0291a implements j0, InterfaceC0769u0, Runnable, Choreographer.FrameCallback {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static long f2533r;
    public final View l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2535n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2536p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f2537q;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final V.d f2534m = new V.d(new h0[16]);
    public final Choreographer o = Choreographer.getInstance();

    /* JADX WARN: Removed duplicated region for block: B:10:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public RunnableC0291a(android.view.View r5) {
        /*
            r4 = this;
            r4.<init>()
            r4.l = r5
            V.d r0 = new V.d
            r1 = 16
            G.h0[] r1 = new G.h0[r1]
            r0.<init>(r1)
            r4.f2534m = r0
            android.view.Choreographer r0 = android.view.Choreographer.getInstance()
            r4.o = r0
            long r0 = G.RunnableC0291a.f2533r
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L3f
            android.view.Display r4 = r5.getDisplay()
            boolean r5 = r5.isInEditMode()
            if (r5 != 0) goto L35
            if (r4 == 0) goto L35
            float r4 = r4.getRefreshRate()
            r5 = 1106247680(0x41f00000, float:30.0)
            int r5 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r5 < 0) goto L35
            goto L37
        L35:
            r4 = 1114636288(0x42700000, float:60.0)
        L37:
            r5 = 1000000000(0x3b9aca00, float:0.0047237873)
            float r5 = (float) r5
            float r5 = r5 / r4
            long r4 = (long) r5
            G.RunnableC0291a.f2533r = r4
        L3f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: G.RunnableC0291a.<init>(android.view.View):void");
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
        this.f2536p = true;
    }

    @Override // G.j0
    public final void b(h0 h0Var) {
        this.f2534m.b(h0Var);
        if (this.f2535n) {
            return;
        }
        this.f2535n = true;
        this.l.post(this);
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        this.f2536p = false;
        this.l.removeCallbacks(this);
        this.o.removeFrameCallback(this);
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j3) {
        if (this.f2536p) {
            this.f2537q = j3;
            this.l.post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        V.d dVar = this.f2534m;
        if (dVar.l() || !this.f2535n || !this.f2536p || this.l.getWindowVisibility() != 0) {
            this.f2535n = false;
            return;
        }
        long j3 = this.f2537q + f2533r;
        B2.v vVar = new B2.v();
        vVar.f600a = j3;
        boolean z6 = false;
        while (dVar.m() && !z6) {
            if (vVar.a() <= 0 || ((h0) dVar.l[0]).b(vVar)) {
                z6 = true;
            } else {
                dVar.o(0);
            }
        }
        if (z6) {
            this.o.postFrameCallback(this);
        } else {
            this.f2535n = false;
        }
    }
}
