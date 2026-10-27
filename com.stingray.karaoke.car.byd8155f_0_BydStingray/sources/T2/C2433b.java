package t2;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import e2.C1208p;
import e2.H;
import e2.I;
import e2.J;
import e2.K;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.o;
import java.util.ArrayList;
import l5.C1778f;
import l9.d;
import n2.AbstractC1905e;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import u2.C2492C;

/* JADX INFO: renamed from: t2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2433b extends AbstractC1905e implements Handler.Callback {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C2432a f22445C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final SurfaceHolderCallbackC1920u f22446D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Handler f22447E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final K2.a f22448F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public d f22449G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public boolean f22450H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f22451I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public long f22452J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public K f22453K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public long f22454L;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2433b(SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u, Looper looper) {
        Handler handler;
        super(5);
        C2432a c2432a = C2432a.f22444a;
        this.f22446D = surfaceHolderCallbackC1920u;
        if (looper == null) {
            handler = null;
        } else {
            int i7 = AbstractC1343A.f15195a;
            handler = new Handler(looper, this);
        }
        this.f22447E = handler;
        this.f22445C = c2432a;
        this.f22448F = new K2.a(1);
        this.f22454L = -9223372036854775807L;
    }

    @Override // n2.AbstractC1905e
    public final int D(C1208p c1208p) {
        if (this.f22445C.b(c1208p)) {
            return AbstractC1905e.d(c1208p.f14304L == 0 ? 4 : 2, 0, 0, 0);
        }
        return AbstractC1905e.d(0, 0, 0, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(e2.K r6, java.util.ArrayList r7) {
        /*
            r5 = this;
            r0 = 0
        L1:
            e2.J[] r1 = r6.l
            int r2 = r1.length
            if (r0 >= r2) goto L46
            r2 = r1[r0]
            e2.p r2 = r2.b()
            if (r2 == 0) goto L3e
            t2.a r3 = r5.f22445C
            boolean r4 = r3.b(r2)
            if (r4 == 0) goto L3e
            l9.d r2 = r3.a(r2)
            r1 = r1[r0]
            byte[] r1 = r1.c()
            r1.getClass()
            K2.a r3 = r5.f22448F
            r3.f()
            int r4 = r1.length
            r3.j(r4)
            java.nio.ByteBuffer r4 = r3.f18955p
            r4.put(r1)
            r3.k()
            e2.K r1 = r2.N(r3)
            if (r1 == 0) goto L43
            r5.F(r1, r7)
            goto L43
        L3e:
            r1 = r1[r0]
            r7.add(r1)
        L43:
            int r0 = r0 + 1
            goto L1
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t2.C2433b.F(e2.K, java.util.ArrayList):void");
    }

    public final long G(long j3) {
        AbstractC1346c.j(j3 != -9223372036854775807L);
        AbstractC1346c.j(this.f22454L != -9223372036854775807L);
        return j3 - this.f22454L;
    }

    public final void H(K k10) {
        SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = this.f22446D;
        C1923x c1923x = surfaceHolderCallbackC1920u.l;
        H hA = c1923x.f19765i0.a();
        int i7 = 0;
        while (true) {
            J[] jArr = k10.l;
            if (i7 >= jArr.length) {
                break;
            }
            jArr[i7].e(hA);
            i7++;
        }
        c1923x.f19765i0 = new I(hA);
        I iC1 = c1923x.c1();
        boolean zEquals = iC1.equals(c1923x.f19740O);
        o oVar = c1923x.f19771m;
        if (!zEquals) {
            c1923x.f19740O = iC1;
            oVar.c(14, new G2.a(surfaceHolderCallbackC1920u, 26));
        }
        oVar.c(28, new G2.a(k10, 27));
        oVar.b();
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            throw new IllegalStateException();
        }
        H((K) message.obj);
        return true;
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "MetadataRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        return this.f22451I;
    }

    @Override // n2.AbstractC1905e
    public final boolean p() {
        return true;
    }

    @Override // n2.AbstractC1905e
    public final void q() {
        this.f22453K = null;
        this.f22449G = null;
        this.f22454L = -9223372036854775807L;
    }

    @Override // n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        this.f22453K = null;
        this.f22450H = false;
        this.f22451I = false;
    }

    @Override // n2.AbstractC1905e
    public final void x(C1208p[] c1208pArr, long j3, long j10, C2492C c2492c) {
        this.f22449G = this.f22445C.a(c1208pArr[0]);
        K k10 = this.f22453K;
        if (k10 != null) {
            long j11 = this.f22454L;
            long j12 = k10.f13981m;
            long j13 = (j11 + j12) - j10;
            if (j12 != j13) {
                k10 = new K(j13, k10.l);
            }
            this.f22453K = k10;
        }
        this.f22454L = j10;
    }

    @Override // n2.AbstractC1905e
    public final void z(long j3, long j10) {
        boolean z6 = true;
        while (z6) {
            if (!this.f22450H && this.f22453K == null) {
                K2.a aVar = this.f22448F;
                aVar.f();
                C1778f c1778f = this.f19637n;
                c1778f.c();
                int iY = y(c1778f, aVar, 0);
                if (iY == -4) {
                    if (aVar.e(4)) {
                        this.f22450H = true;
                    } else if (aVar.f18957r >= this.f19645w) {
                        aVar.f3508u = this.f22452J;
                        aVar.k();
                        d dVar = this.f22449G;
                        int i7 = AbstractC1343A.f15195a;
                        K kN = dVar.N(aVar);
                        if (kN != null) {
                            ArrayList arrayList = new ArrayList(kN.l.length);
                            F(kN, arrayList);
                            if (!arrayList.isEmpty()) {
                                this.f22453K = new K(G(aVar.f18957r), (J[]) arrayList.toArray(new J[0]));
                            }
                        }
                    }
                } else if (iY == -5) {
                    C1208p c1208p = (C1208p) c1778f.f18639n;
                    c1208p.getClass();
                    this.f22452J = c1208p.f14320s;
                }
            }
            K k10 = this.f22453K;
            if (k10 == null || k10.f13981m > G(j3)) {
                z6 = false;
            } else {
                K k11 = this.f22453K;
                Handler handler = this.f22447E;
                if (handler != null) {
                    handler.obtainMessage(1, k11).sendToTarget();
                } else {
                    H(k11);
                }
                this.f22453K = null;
                z6 = true;
            }
            if (this.f22450H && this.f22453K == null) {
                this.f22451I = true;
            }
        }
    }
}
