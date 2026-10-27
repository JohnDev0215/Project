package v2;

import A7.D;
import Y2.e;
import Y2.f;
import Y2.i;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import d5.C1077F;
import d5.I;
import d5.d0;
import e2.C1208p;
import e2.L;
import g2.C1289b;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Objects;
import l3.C1764u;
import l5.C1778f;
import m2.g;
import n2.AbstractC1905e;
import n2.C1923x;
import n2.SurfaceHolderCallbackC1920u;
import u2.C2492C;
import u2.a0;

/* JADX INFO: renamed from: v2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2546d extends AbstractC1905e implements Handler.Callback {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C8.d f22981C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final g f22982D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public InterfaceC2543a f22983E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final C1764u f22984F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f22985G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f22986H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public e f22987I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public i f22988J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public Y2.c f22989K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public Y2.c f22990L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public int f22991M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final Handler f22992N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final SurfaceHolderCallbackC1920u f22993O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final C1778f f22994P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f22995Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f22996R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public C1208p f22997S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public long f22998T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public long f22999U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public IOException f23000V;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2546d(SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u, Looper looper) {
        Handler handler;
        super(3);
        C1764u c1764u = InterfaceC2545c.f22980j;
        this.f22993O = surfaceHolderCallbackC1920u;
        if (looper == null) {
            handler = null;
        } else {
            int i7 = AbstractC1343A.f15195a;
            handler = new Handler(looper, this);
        }
        this.f22992N = handler;
        this.f22984F = c1764u;
        this.f22981C = new C8.d(14);
        this.f22982D = new g(1);
        this.f22994P = new C1778f(1, (byte) 0);
        this.f22999U = -9223372036854775807L;
        this.f22998T = -9223372036854775807L;
    }

    @Override // n2.AbstractC1905e
    public final int D(C1208p c1208p) {
        if (!Objects.equals(c1208p.f14316n, "application/x-media3-cues")) {
            C1764u c1764u = this.f22984F;
            c1764u.getClass();
            if (!((io.sentry.hints.i) c1764u.f18608m).j(c1208p)) {
                String str = c1208p.f14316n;
                if (!Objects.equals(str, "application/cea-608") && !Objects.equals(str, "application/x-mp4-cea-608") && !Objects.equals(str, "application/cea-708")) {
                    return L.j(str) ? AbstractC1905e.d(1, 0, 0, 0) : AbstractC1905e.d(0, 0, 0, 0);
                }
            }
        }
        return AbstractC1905e.d(c1208p.f14304L == 0 ? 4 : 2, 0, 0, 0);
    }

    public final void F() {
        AbstractC1346c.i("Legacy decoding is disabled, can't handle " + this.f22997S.f14316n + " samples (expected application/x-media3-cues).", Objects.equals(this.f22997S.f14316n, "application/cea-608") || Objects.equals(this.f22997S.f14316n, "application/x-mp4-cea-608") || Objects.equals(this.f22997S.f14316n, "application/cea-708"));
    }

    public final long G() {
        if (this.f22991M == -1) {
            return Long.MAX_VALUE;
        }
        this.f22989K.getClass();
        if (this.f22991M >= this.f22989K.h()) {
            return Long.MAX_VALUE;
        }
        return this.f22989K.d(this.f22991M);
    }

    public final long H(long j3) {
        AbstractC1346c.j(j3 != -9223372036854775807L);
        return j3 - this.f19644v;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void I() {
        /*
            r7 = this;
            r0 = 1
            r7.f22985G = r0
            e2.p r1 = r7.f22997S
            r1.getClass()
            l3.u r2 = r7.f22984F
            r2.getClass()
            java.lang.String r3 = r1.f14316n
            if (r3 == 0) goto L4d
            int r4 = r1.f14300H
            r5 = -1
            int r6 = r3.hashCode()
            switch(r6) {
                case 930165504: goto L31;
                case 1566015601: goto L28;
                case 1566016562: goto L1d;
                default: goto L1b;
            }
        L1b:
            r0 = r5
            goto L3b
        L1d:
            java.lang.String r0 = "application/cea-708"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L26
            goto L1b
        L26:
            r0 = 2
            goto L3b
        L28:
            java.lang.String r6 = "application/cea-608"
            boolean r6 = r3.equals(r6)
            if (r6 != 0) goto L3b
            goto L1b
        L31:
            java.lang.String r0 = "application/x-mp4-cea-608"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L3a
            goto L1b
        L3a:
            r0 = 0
        L3b:
            switch(r0) {
                case 0: goto L47;
                case 1: goto L47;
                case 2: goto L3f;
                default: goto L3e;
            }
        L3e:
            goto L4d
        L3f:
            Z2.f r0 = new Z2.f
            java.util.List r1 = r1.f14318q
            r0.<init>(r4, r1)
            goto L6e
        L47:
            Z2.c r0 = new Z2.c
            r0.<init>(r3, r4)
            goto L6e
        L4d:
            java.lang.Object r0 = r2.f18608m
            io.sentry.hints.i r0 = (io.sentry.hints.i) r0
            boolean r2 = r0.j(r1)
            if (r2 == 0) goto L76
            Y2.l r0 = r0.c(r1)
            r2.b r1 = new r2.b
            java.lang.Class r2 = r0.getClass()
            java.lang.String r2 = r2.getSimpleName()
            java.lang.String r3 = "Decoder"
            r2.concat(r3)
            r1.<init>(r0)
            r0 = r1
        L6e:
            r7.f22987I = r0
            long r1 = r7.f19645w
            r0.c(r1)
            return
        L76:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Attempted to create decoder for unsupported MIME type: "
            java.lang.String r0 = M5.b.g(r0, r3)
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: v2.C2546d.I():void");
    }

    public final void J(C1290c c1290c) {
        I i7 = c1290c.f14801a;
        SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u = this.f22993O;
        surfaceHolderCallbackC1920u.l.f19771m.f(27, new G2.a(i7, 28));
        C1923x c1923x = surfaceHolderCallbackC1920u.l;
        c1923x.f19753b0 = c1290c;
        c1923x.f19771m.f(27, new G2.a(c1290c, 25));
    }

    public final void K() {
        this.f22988J = null;
        this.f22991M = -1;
        Y2.c cVar = this.f22989K;
        if (cVar != null) {
            cVar.i();
            this.f22989K = null;
        }
        Y2.c cVar2 = this.f22990L;
        if (cVar2 != null) {
            cVar2.i();
            this.f22990L = null;
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            throw new IllegalStateException();
        }
        J((C1290c) message.obj);
        return true;
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "TextRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        return this.f22996R;
    }

    @Override // n2.AbstractC1905e
    public final boolean p() {
        if (this.f22997S == null) {
            return true;
        }
        if (this.f23000V == null) {
            try {
                a0 a0Var = this.f19642t;
                a0Var.getClass();
                a0Var.r();
            } catch (IOException e10) {
                this.f23000V = e10;
            }
        }
        if (this.f23000V != null) {
            C1208p c1208p = this.f22997S;
            c1208p.getClass();
            if (Objects.equals(c1208p.f14316n, "application/x-media3-cues")) {
                InterfaceC2543a interfaceC2543a = this.f22983E;
                interfaceC2543a.getClass();
                return interfaceC2543a.b(this.f22998T) != Long.MIN_VALUE;
            }
            if (!this.f22996R) {
                if (this.f22995Q) {
                    Y2.c cVar = this.f22989K;
                    long j3 = this.f22998T;
                    if (cVar == null || cVar.d(cVar.h() - 1) <= j3) {
                        Y2.c cVar2 = this.f22990L;
                        long j10 = this.f22998T;
                        if ((cVar2 == null || cVar2.d(cVar2.h() - 1) <= j10) && this.f22988J != null) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // n2.AbstractC1905e
    public final void q() {
        this.f22997S = null;
        this.f22999U = -9223372036854775807L;
        C1290c c1290c = new C1290c(d0.f13415p, H(this.f22998T));
        Handler handler = this.f22992N;
        if (handler != null) {
            handler.obtainMessage(1, c1290c).sendToTarget();
        } else {
            J(c1290c);
        }
        this.f22998T = -9223372036854775807L;
        if (this.f22987I != null) {
            K();
            e eVar = this.f22987I;
            eVar.getClass();
            eVar.a();
            this.f22987I = null;
            this.f22986H = 0;
        }
    }

    @Override // n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        this.f22998T = j3;
        InterfaceC2543a interfaceC2543a = this.f22983E;
        if (interfaceC2543a != null) {
            interfaceC2543a.clear();
        }
        C1290c c1290c = new C1290c(d0.f13415p, H(this.f22998T));
        Handler handler = this.f22992N;
        if (handler != null) {
            handler.obtainMessage(1, c1290c).sendToTarget();
        } else {
            J(c1290c);
        }
        this.f22995Q = false;
        this.f22996R = false;
        this.f22999U = -9223372036854775807L;
        C1208p c1208p = this.f22997S;
        if (c1208p == null || Objects.equals(c1208p.f14316n, "application/x-media3-cues")) {
            return;
        }
        if (this.f22986H == 0) {
            K();
            e eVar = this.f22987I;
            eVar.getClass();
            eVar.flush();
            eVar.c(this.f19645w);
            return;
        }
        K();
        e eVar2 = this.f22987I;
        eVar2.getClass();
        eVar2.a();
        this.f22987I = null;
        this.f22986H = 0;
        I();
    }

    @Override // n2.AbstractC1905e
    public final void x(C1208p[] c1208pArr, long j3, long j10, C2492C c2492c) {
        C1208p c1208p = c1208pArr[0];
        this.f22997S = c1208p;
        if (Objects.equals(c1208p.f14316n, "application/x-media3-cues")) {
            this.f22983E = this.f22997S.f14301I == 1 ? new C2544b() : new D(9, false);
            return;
        }
        F();
        if (this.f22987I != null) {
            this.f22986H = 1;
        } else {
            I();
        }
    }

    @Override // n2.AbstractC1905e
    public final void z(long j3, long j10) {
        boolean z6;
        C1778f c1778f;
        boolean z10;
        long jD;
        if (this.f19647y) {
            long j11 = this.f22999U;
            if (j11 != -9223372036854775807L && j3 >= j11) {
                K();
                this.f22996R = true;
            }
        }
        if (this.f22996R) {
            return;
        }
        C1208p c1208p = this.f22997S;
        c1208p.getClass();
        boolean zEquals = Objects.equals(c1208p.f14316n, "application/x-media3-cues");
        Handler handler = this.f22992N;
        boolean zC = false;
        zC = false;
        zC = false;
        C1778f c1778f2 = this.f22994P;
        if (zEquals) {
            this.f22983E.getClass();
            if (!this.f22995Q) {
                g gVar = this.f22982D;
                if (y(c1778f2, gVar, 0) == -4) {
                    if (gVar.e(4)) {
                        this.f22995Q = true;
                    } else {
                        gVar.k();
                        ByteBuffer byteBuffer = gVar.f18955p;
                        byteBuffer.getClass();
                        long j12 = gVar.f18957r;
                        byte[] bArrArray = byteBuffer.array();
                        int iArrayOffset = byteBuffer.arrayOffset();
                        int iLimit = byteBuffer.limit();
                        this.f22981C.getClass();
                        Parcel parcelObtain = Parcel.obtain();
                        parcelObtain.unmarshall(bArrArray, iArrayOffset, iLimit);
                        parcelObtain.setDataPosition(0);
                        Bundle bundle = parcelObtain.readBundle(Bundle.class.getClassLoader());
                        parcelObtain.recycle();
                        ArrayList parcelableArrayList = bundle.getParcelableArrayList("c");
                        parcelableArrayList.getClass();
                        C1077F c1077fU = I.u();
                        for (int i7 = 0; i7 < parcelableArrayList.size(); i7++) {
                            Bundle bundle2 = (Bundle) parcelableArrayList.get(i7);
                            bundle2.getClass();
                            c1077fU.a(C1289b.b(bundle2));
                        }
                        Y2.a aVar = new Y2.a(c1077fU.h(), j12, bundle.getLong("d"));
                        gVar.f();
                        zC = this.f22983E.c(aVar, j3);
                    }
                }
            }
            long jB = this.f22983E.b(this.f22998T);
            if (jB == Long.MIN_VALUE && this.f22995Q && !zC) {
                this.f22996R = true;
            }
            if (jB != Long.MIN_VALUE && jB <= j3) {
                zC = true;
            }
            if (zC) {
                I iA = this.f22983E.a(j3);
                long jD2 = this.f22983E.d(j3);
                C1290c c1290c = new C1290c(iA, H(jD2));
                if (handler != null) {
                    handler.obtainMessage(1, c1290c).sendToTarget();
                } else {
                    J(c1290c);
                }
                this.f22983E.e(jD2);
            }
            this.f22998T = j3;
            return;
        }
        F();
        this.f22998T = j3;
        if (this.f22990L == null) {
            e eVar = this.f22987I;
            eVar.getClass();
            eVar.d(j3);
            try {
                e eVar2 = this.f22987I;
                eVar2.getClass();
                this.f22990L = (Y2.c) eVar2.e();
            } catch (f e10) {
                AbstractC1346c.q("TextRenderer", "Subtitle decoding failed. streamFormat=" + this.f22997S, e10);
                C1290c c1290c2 = new C1290c(d0.f13415p, H(this.f22998T));
                if (handler != null) {
                    handler.obtainMessage(1, c1290c2).sendToTarget();
                } else {
                    J(c1290c2);
                }
                K();
                e eVar3 = this.f22987I;
                eVar3.getClass();
                eVar3.a();
                this.f22987I = null;
                this.f22986H = 0;
                I();
                return;
            }
        }
        if (this.f19641s != 2) {
            return;
        }
        if (this.f22989K != null) {
            long jG = G();
            z6 = false;
            while (jG <= j3) {
                this.f22991M++;
                jG = G();
                z6 = true;
            }
        } else {
            z6 = false;
        }
        Y2.c cVar = this.f22990L;
        if (cVar == null) {
            c1778f = c1778f2;
            z10 = z6;
        } else if (cVar.e(4)) {
            if (!z6 && G() == Long.MAX_VALUE) {
                if (this.f22986H == 2) {
                    K();
                    e eVar4 = this.f22987I;
                    eVar4.getClass();
                    eVar4.a();
                    this.f22987I = null;
                    this.f22986H = 0;
                    I();
                } else {
                    K();
                    this.f22996R = true;
                }
            }
            c1778f = c1778f2;
            z10 = z6;
        } else {
            c1778f = c1778f2;
            z10 = z6;
            if (cVar.f18960n <= j3) {
                Y2.c cVar2 = this.f22989K;
                if (cVar2 != null) {
                    cVar2.i();
                }
                this.f22991M = cVar.a(j3);
                this.f22989K = cVar;
                this.f22990L = null;
                z10 = true;
            }
        }
        if (z10) {
            this.f22989K.getClass();
            int iA2 = this.f22989K.a(j3);
            if (iA2 == 0 || this.f22989K.h() == 0) {
                jD = this.f22989K.f18960n;
            } else if (iA2 == -1) {
                Y2.c cVar3 = this.f22989K;
                jD = cVar3.d(cVar3.h() - 1);
            } else {
                jD = this.f22989K.d(iA2 - 1);
            }
            C1290c c1290c3 = new C1290c(this.f22989K.g(j3), H(jD));
            if (handler != null) {
                handler.obtainMessage(1, c1290c3).sendToTarget();
            } else {
                J(c1290c3);
            }
        }
        if (this.f22986H == 2) {
            return;
        }
        while (!this.f22995Q) {
            try {
                i iVar = this.f22988J;
                if (iVar == null) {
                    e eVar5 = this.f22987I;
                    eVar5.getClass();
                    iVar = (i) eVar5.f();
                    if (iVar == null) {
                        return;
                    } else {
                        this.f22988J = iVar;
                    }
                }
                if (this.f22986H == 1) {
                    iVar.f15673m = 4;
                    e eVar6 = this.f22987I;
                    eVar6.getClass();
                    eVar6.b(iVar);
                    this.f22988J = null;
                    this.f22986H = 2;
                    return;
                }
                C1778f c1778f3 = c1778f;
                int iY = y(c1778f3, iVar, 0);
                if (iY == -4) {
                    if (iVar.e(4)) {
                        this.f22995Q = true;
                        this.f22985G = false;
                    } else {
                        C1208p c1208p2 = (C1208p) c1778f3.f18639n;
                        if (c1208p2 == null) {
                            return;
                        }
                        iVar.f10646u = c1208p2.f14320s;
                        iVar.k();
                        this.f22985G &= !iVar.e(1);
                    }
                    if (!this.f22985G) {
                        e eVar7 = this.f22987I;
                        eVar7.getClass();
                        eVar7.b(iVar);
                        this.f22988J = null;
                    }
                } else if (iY == -3) {
                    return;
                }
                c1778f = c1778f3;
            } catch (f e11) {
                AbstractC1346c.q("TextRenderer", "Subtitle decoding failed. streamFormat=" + this.f22997S, e11);
                C1290c c1290c4 = new C1290c(d0.f13415p, H(this.f22998T));
                if (handler != null) {
                    handler.obtainMessage(1, c1290c4).sendToTarget();
                } else {
                    J(c1290c4);
                }
                K();
                e eVar8 = this.f22987I;
                eVar8.getClass();
                eVar8.a();
                this.f22987I = null;
                this.f22986H = 0;
                I();
                return;
            }
        }
    }
}
