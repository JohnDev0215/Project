package p2;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.LoudnessCodecController;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import e2.C1195c;
import e2.C1196d;
import e2.C1208p;
import e2.P;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import l3.C1764u;
import l5.C1778f;
import l8.C1789g;
import n2.C1906f;
import n2.C1907g;
import n2.C1913m;
import n2.C1924y;
import n2.H;
import n2.SurfaceHolderCallbackC1920u;
import n2.a0;

/* JADX INFO: loaded from: classes.dex */
public final class x extends s2.q implements H {

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public final Context f20837N0;

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public final C1789g f20838O0;

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public final v f20839P0;

    /* JADX INFO: renamed from: Q0, reason: collision with root package name */
    public final h3.y f20840Q0;

    /* JADX INFO: renamed from: R0, reason: collision with root package name */
    public int f20841R0;

    /* JADX INFO: renamed from: S0, reason: collision with root package name */
    public boolean f20842S0;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public boolean f20843T0;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public C1208p f20844U0;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public C1208p f20845V0;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public long f20846W0;

    /* JADX INFO: renamed from: X0, reason: collision with root package name */
    public boolean f20847X0;

    /* JADX INFO: renamed from: Y0, reason: collision with root package name */
    public boolean f20848Y0;

    /* JADX INFO: renamed from: Z0, reason: collision with root package name */
    public boolean f20849Z0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public int f20850a1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x(Context context, s2.j jVar, Handler handler, SurfaceHolderCallbackC1920u surfaceHolderCallbackC1920u, v vVar) {
        super(1, jVar, 44100.0f);
        h3.y yVar = AbstractC1343A.f15195a >= 35 ? new h3.y(21) : null;
        this.f20837N0 = context.getApplicationContext();
        this.f20839P0 = vVar;
        this.f20840Q0 = yVar;
        this.f20850a1 = -1000;
        this.f20838O0 = new C1789g(handler, surfaceHolderCallbackC1920u);
        vVar.f20826r = new C1764u(this, 13);
    }

    public final int A0(s2.n nVar, C1208p c1208p) {
        int i7;
        if (!"OMX.google.raw.decoder".equals(nVar.f22095a) || (i7 = AbstractC1343A.f15195a) >= 24 || (i7 == 23 && AbstractC1343A.F(this.f20837N0))) {
            return c1208p.o;
        }
        return -1;
    }

    public final void B0() {
        long jMax;
        ArrayDeque arrayDeque;
        long j3;
        long jN;
        boolean zN = n();
        v vVar = this.f20839P0;
        if (!vVar.o() || vVar.f20790M) {
            jMax = Long.MIN_VALUE;
        } else {
            long jMin = Math.min(vVar.f20814g.a(zN), AbstractC1343A.L(vVar.k(), vVar.f20828t.f20765e));
            while (true) {
                arrayDeque = vVar.f20816h;
                if (arrayDeque.isEmpty() || jMin < ((s) arrayDeque.getFirst()).c) {
                    break;
                } else {
                    vVar.B = (s) arrayDeque.remove();
                }
            }
            s sVar = vVar.B;
            long j10 = jMin - sVar.c;
            long jT = AbstractC1343A.t(sVar.f20772a.f13995a, j10);
            boolean zIsEmpty = arrayDeque.isEmpty();
            h3.y yVar = vVar.f20806b;
            if (zIsEmpty) {
                f2.g gVar = (f2.g) yVar.o;
                if (gVar.b()) {
                    if (gVar.o >= 1024) {
                        long j11 = gVar.f14584n;
                        f2.f fVar = gVar.f14581j;
                        fVar.getClass();
                        long j12 = j11 - ((long) ((fVar.f14564k * fVar.f14557b) * 2));
                        int i7 = gVar.f14579h.f14548a;
                        int i10 = gVar.f14578g.f14548a;
                        jN = i7 == i10 ? AbstractC1343A.N(j10, j12, gVar.o, RoundingMode.DOWN) : AbstractC1343A.N(j10, j12 * ((long) i7), gVar.o * ((long) i10), RoundingMode.DOWN);
                    } else {
                        jN = (long) (((double) gVar.c) * j10);
                    }
                    j10 = jN;
                }
                s sVar2 = vVar.B;
                j3 = sVar2.f20773b + j10;
                sVar2.d = j10 - jT;
            } else {
                s sVar3 = vVar.B;
                j3 = sVar3.f20773b + jT + sVar3.d;
            }
            long j13 = ((z) yVar.f15500n).f20860q;
            jMax = AbstractC1343A.L(j13, vVar.f20828t.f20765e) + j3;
            long j14 = vVar.f20815g0;
            if (j13 > j14) {
                long jL = AbstractC1343A.L(j13 - j14, vVar.f20828t.f20765e);
                vVar.f20815g0 = j13;
                vVar.f20817h0 += jL;
                if (vVar.f20819i0 == null) {
                    vVar.f20819i0 = new Handler(Looper.myLooper());
                }
                vVar.f20819i0.removeCallbacksAndMessages(null);
                vVar.f20819i0.postDelayed(new p(vVar, 0), 100L);
            }
        }
        if (jMax != Long.MIN_VALUE) {
            if (!this.f20847X0) {
                jMax = Math.max(this.f20846W0, jMax);
            }
            this.f20846W0 = jMax;
            this.f20847X0 = false;
        }
    }

    @Override // s2.q
    public final C1907g G(s2.n nVar, C1208p c1208p, C1208p c1208p2) {
        C1907g c1907gB = nVar.b(c1208p, c1208p2);
        boolean z6 = this.f22131P == null && u0(c1208p2);
        int i7 = c1907gB.f19660e;
        if (z6) {
            i7 |= 32768;
        }
        if (A0(nVar, c1208p2) > this.f20841R0) {
            i7 |= 64;
        }
        int i10 = i7;
        return new C1907g(nVar.f22095a, c1208p, c1208p2, i10 == 0 ? c1907gB.d : 0, i10);
    }

    @Override // s2.q
    public final float R(float f10, C1208p[] c1208pArr) {
        int iMax = -1;
        for (C1208p c1208p : c1208pArr) {
            int i7 = c1208p.f14296D;
            if (i7 != -1) {
                iMax = Math.max(iMax, i7);
            }
        }
        if (iMax == -1) {
            return -1.0f;
        }
        return iMax * f10;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002b  */
    @Override // s2.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList S(s2.i r3, e2.C1208p r4, boolean r5) {
        /*
            r2 = this;
            java.lang.String r0 = r4.f14316n
            if (r0 != 0) goto L7
            d5.d0 r2 = d5.d0.f13415p
            goto L2f
        L7:
            p2.v r2 = r2.f20839P0
            int r2 = r2.i(r4)
            r0 = 0
            if (r2 == 0) goto L2b
            java.lang.String r2 = "audio/raw"
            java.util.List r2 = s2.v.e(r2, r0, r0)
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto L1e
            r2 = 0
            goto L24
        L1e:
            java.lang.Object r2 = r2.get(r0)
            s2.n r2 = (s2.n) r2
        L24:
            if (r2 == 0) goto L2b
            d5.d0 r2 = d5.I.B(r2)
            goto L2f
        L2b:
            d5.d0 r2 = s2.v.g(r3, r4, r5, r0)
        L2f:
            java.util.HashMap r3 = s2.v.f22170a
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            o2.c r2 = new o2.c
            r5 = 4
            r2.<init>(r4, r5)
            p7.a r4 = new p7.a
            r5 = 1
            r4.<init>(r2, r5)
            java.util.Collections.sort(r3, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.x.S(s2.i, e2.p, boolean):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d8  */
    @Override // s2.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final H0.b T(s2.n r13, e2.C1208p r14, android.media.MediaCrypto r15, float r16) {
        /*
            Method dump skipped, instruction units count: 343
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.x.T(s2.n, e2.p, android.media.MediaCrypto, float):H0.b");
    }

    @Override // s2.q
    public final void U(m2.g gVar) {
        C1208p c1208p;
        r rVar;
        if (AbstractC1343A.f15195a < 29 || (c1208p = gVar.f18954n) == null || !Objects.equals(c1208p.f14316n, "audio/opus") || !this.f22159r0) {
            return;
        }
        ByteBuffer byteBuffer = gVar.f18958s;
        byteBuffer.getClass();
        C1208p c1208p2 = gVar.f18954n;
        c1208p2.getClass();
        if (byteBuffer.remaining() == 8) {
            int i7 = (int) ((byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getLong() * 48000) / 1000000000);
            v vVar = this.f20839P0;
            AudioTrack audioTrack = vVar.f20830v;
            if (audioTrack == null || !v.p(audioTrack) || (rVar = vVar.f20828t) == null || !rVar.f20771k) {
                return;
            }
            vVar.f20830v.setOffloadDelayPadding(c1208p2.f14298F, i7);
        }
    }

    @Override // s2.q
    public final void Z(Exception exc) {
        AbstractC1346c.q("MediaCodecAudioRenderer", "Audio codec error", exc);
        C1789g c1789g = this.f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, exc, 5));
        }
    }

    @Override // n2.H
    public final boolean a() {
        boolean z6 = this.f20849Z0;
        this.f20849Z0 = false;
        return z6;
    }

    @Override // s2.q
    public final void a0(long j3, long j10, String str) {
        C1789g c1789g = this.f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, str, j3, j10));
        }
    }

    @Override // n2.AbstractC1905e, n2.W
    public final void b(int i7, Object obj) {
        C1764u c1764u;
        h3.y yVar;
        v vVar = this.f20839P0;
        if (i7 == 2) {
            obj.getClass();
            float fFloatValue = ((Float) obj).floatValue();
            if (vVar.f20792O != fFloatValue) {
                vVar.f20792O = fFloatValue;
                if (vVar.o()) {
                    vVar.f20830v.setVolume(vVar.f20792O);
                    return;
                }
                return;
            }
            return;
        }
        if (i7 == 3) {
            C1195c c1195c = (C1195c) obj;
            c1195c.getClass();
            if (vVar.f20834z.equals(c1195c)) {
                return;
            }
            vVar.f20834z = c1195c;
            if (vVar.f20805a0) {
                return;
            }
            C2090e c2090e = vVar.f20832x;
            if (c2090e != null) {
                c2090e.f20707i = c1195c;
                c2090e.a(C2087b.c(c2090e.f20701a, c1195c, c2090e.f20706h));
            }
            vVar.g();
            return;
        }
        if (i7 == 6) {
            C1196d c1196d = (C1196d) obj;
            c1196d.getClass();
            if (vVar.f20802Y.equals(c1196d)) {
                return;
            }
            if (vVar.f20830v != null) {
                vVar.f20802Y.getClass();
            }
            vVar.f20802Y = c1196d;
            return;
        }
        if (i7 == 12) {
            if (AbstractC1343A.f15195a >= 23) {
                AudioDeviceInfo audioDeviceInfo = (AudioDeviceInfo) obj;
                if (audioDeviceInfo == null) {
                    c1764u = null;
                } else {
                    vVar.getClass();
                    c1764u = new C1764u(audioDeviceInfo, 11);
                }
                vVar.f20803Z = c1764u;
                C2090e c2090e2 = vVar.f20832x;
                if (c2090e2 != null) {
                    c2090e2.b(audioDeviceInfo);
                }
                AudioTrack audioTrack = vVar.f20830v;
                if (audioTrack != null) {
                    C1764u c1764u2 = vVar.f20803Z;
                    audioTrack.setPreferredDevice(c1764u2 != null ? (AudioDeviceInfo) c1764u2.f18608m : null);
                    return;
                }
                return;
            }
            return;
        }
        if (i7 == 16) {
            obj.getClass();
            this.f20850a1 = ((Integer) obj).intValue();
            s2.k kVar = this.f22137V;
            if (kVar != null && AbstractC1343A.f15195a >= 35) {
                Bundle bundle = new Bundle();
                bundle.putInt("importance", Math.max(0, -this.f20850a1));
                kVar.d(bundle);
                return;
            }
            return;
        }
        if (i7 == 9) {
            obj.getClass();
            vVar.f20781D = ((Boolean) obj).booleanValue();
            s sVar = new s(vVar.x() ? P.d : vVar.f20780C, -9223372036854775807L, -9223372036854775807L);
            if (vVar.o()) {
                vVar.f20779A = sVar;
                return;
            } else {
                vVar.B = sVar;
                return;
            }
        }
        if (i7 != 10) {
            if (i7 == 11) {
                this.f22132Q = (C1924y) obj;
                return;
            }
            return;
        }
        obj.getClass();
        int iIntValue = ((Integer) obj).intValue();
        if (vVar.f20801X != iIntValue) {
            vVar.f20801X = iIntValue;
            vVar.f20800W = iIntValue != 0;
            vVar.g();
        }
        if (AbstractC1343A.f15195a < 35 || (yVar = this.f20840Q0) == null) {
            return;
        }
        LoudnessCodecController loudnessCodecController = (LoudnessCodecController) yVar.o;
        if (loudnessCodecController != null) {
            loudnessCodecController.close();
            yVar.o = null;
        }
        LoudnessCodecController loudnessCodecControllerCreate = LoudnessCodecController.create(iIntValue, h5.r.l, new s2.h(yVar));
        yVar.o = loudnessCodecControllerCreate;
        Iterator it = ((HashSet) yVar.f15499m).iterator();
        while (it.hasNext()) {
            if (!loudnessCodecControllerCreate.addMediaCodec((MediaCodec) it.next())) {
                it.remove();
            }
        }
    }

    @Override // s2.q
    public final void b0(String str) {
        C1789g c1789g = this.f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, str, 9));
        }
    }

    @Override // n2.H
    public final long c() {
        if (this.f19641s == 2) {
            B0();
        }
        return this.f20846W0;
    }

    @Override // s2.q
    public final C1907g c0(C1778f c1778f) {
        C1208p c1208p = (C1208p) c1778f.f18639n;
        c1208p.getClass();
        this.f20844U0 = c1208p;
        C1907g c1907gC0 = super.c0(c1778f);
        C1789g c1789g = this.f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, c1208p, c1907gC0));
        }
        return c1907gC0;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0104 A[Catch: i -> 0x0102, TryCatch #0 {i -> 0x0102, blocks: (B:42:0x00d9, B:45:0x00e1, B:47:0x00e5, B:49:0x00ee, B:53:0x00fc, B:56:0x0104, B:60:0x010b, B:61:0x0110), top: B:65:0x00d9 }] */
    @Override // s2.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d0(e2.C1208p r14, android.media.MediaFormat r15) throws n2.C1913m {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.x.d0(e2.p, android.media.MediaFormat):void");
    }

    @Override // s2.q
    public final void e0() {
        this.f20839P0.getClass();
    }

    @Override // n2.H
    public final void f(P p10) {
        v vVar = this.f20839P0;
        vVar.getClass();
        vVar.f20780C = new P(AbstractC1343A.h(p10.f13995a, 0.1f, 8.0f), AbstractC1343A.h(p10.f13996b, 0.1f, 8.0f));
        if (vVar.x()) {
            vVar.v();
            return;
        }
        s sVar = new s(p10, -9223372036854775807L, -9223372036854775807L);
        if (vVar.o()) {
            vVar.f20779A = sVar;
        } else {
            vVar.B = sVar;
        }
    }

    @Override // s2.q
    public final void g0() {
        this.f20839P0.f20789L = true;
    }

    @Override // n2.H
    public final P j() {
        return this.f20839P0.f20780C;
    }

    @Override // n2.AbstractC1905e
    public final H k() {
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0068  */
    @Override // s2.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean k0(long r1, long r3, s2.k r5, java.nio.ByteBuffer r6, int r7, int r8, int r9, long r10, boolean r12, boolean r13, e2.C1208p r14) throws n2.C1913m {
        /*
            r0 = this;
            r6.getClass()
            e2.p r1 = r0.f20845V0
            r2 = 1
            if (r1 == 0) goto L13
            r1 = r8 & 2
            if (r1 == 0) goto L13
            r5.getClass()
            r5.g(r7)
            return r2
        L13:
            p2.v r1 = r0.f20839P0
            if (r12 == 0) goto L26
            if (r5 == 0) goto L1c
            r5.g(r7)
        L1c:
            n2.f r0 = r0.f22121I0
            int r3 = r0.f19652f
            int r3 = r3 + r9
            r0.f19652f = r3
            r1.f20789L = r2
            return r2
        L26:
            boolean r1 = r1.l(r6, r10, r9)     // Catch: p2.C2096k -> L3b p2.C2095j -> L55
            if (r1 == 0) goto L39
            if (r5 == 0) goto L31
            r5.g(r7)
        L31:
            n2.f r0 = r0.f22121I0
            int r1 = r0.f19651e
            int r1 = r1 + r9
            r0.f19651e = r1
            return r2
        L39:
            r0 = 0
            return r0
        L3b:
            r1 = move-exception
            boolean r2 = r0.f22159r0
            if (r2 == 0) goto L4c
            n2.a0 r2 = r0.o
            r2.getClass()
            int r2 = r2.f19622a
            if (r2 == 0) goto L4c
            r2 = 5003(0x138b, float:7.01E-42)
            goto L4e
        L4c:
            r2 = 5002(0x138a, float:7.009E-42)
        L4e:
            boolean r3 = r1.f20714m
            n2.m r0 = r0.e(r1, r14, r3, r2)
            throw r0
        L55:
            r1 = move-exception
            e2.p r2 = r0.f20844U0
            boolean r3 = r0.f22159r0
            if (r3 == 0) goto L68
            n2.a0 r3 = r0.o
            r3.getClass()
            int r3 = r3.f19622a
            if (r3 == 0) goto L68
            r3 = 5004(0x138c, float:7.012E-42)
            goto L6a
        L68:
            r3 = 5001(0x1389, float:7.008E-42)
        L6a:
            boolean r4 = r1.f20713m
            n2.m r0 = r0.e(r1, r2, r4, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.x.k0(long, long, s2.k, java.nio.ByteBuffer, int, int, int, long, boolean, boolean, e2.p):boolean");
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "MediaCodecAudioRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        if (this.f22114E0) {
            v vVar = this.f20839P0;
            if (!vVar.o() || (vVar.f20796S && !vVar.m())) {
                return true;
            }
        }
        return false;
    }

    @Override // s2.q
    public final void n0() throws C1913m {
        try {
            v vVar = this.f20839P0;
            if (!vVar.f20796S && vVar.o() && vVar.f()) {
                vVar.s();
                vVar.f20796S = true;
            }
        } catch (C2096k e10) {
            throw e(e10, e10.f20715n, e10.f20714m, this.f22159r0 ? 5003 : 5002);
        }
    }

    @Override // s2.q, n2.AbstractC1905e
    public final boolean p() {
        return this.f20839P0.m() || super.p();
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void q() {
        C1789g c1789g = this.f20838O0;
        this.f20848Y0 = true;
        this.f20844U0 = null;
        try {
            this.f20839P0.g();
            try {
                super.q();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.q();
                throw th;
            } finally {
            }
        }
    }

    @Override // n2.AbstractC1905e
    public final void r(boolean z6, boolean z10) {
        C1906f c1906f = new C1906f();
        this.f22121I0 = c1906f;
        C1789g c1789g = this.f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, c1906f, 0));
        }
        a0 a0Var = this.o;
        a0Var.getClass();
        boolean z11 = a0Var.f19623b;
        v vVar = this.f20839P0;
        if (z11) {
            AbstractC1346c.j(vVar.f20800W);
            if (!vVar.f20805a0) {
                vVar.f20805a0 = true;
                vVar.g();
            }
        } else if (vVar.f20805a0) {
            vVar.f20805a0 = false;
            vVar.g();
        }
        o2.l lVar = this.f19639q;
        lVar.getClass();
        vVar.f20825q = lVar;
        h2.v vVar2 = this.f19640r;
        vVar2.getClass();
        vVar.f20814g.f20732I = vVar2;
    }

    @Override // s2.q, n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        super.s(j3, z6);
        this.f20839P0.g();
        this.f20846W0 = j3;
        this.f20849Z0 = false;
        this.f20847X0 = true;
    }

    @Override // n2.AbstractC1905e
    public final void t() {
        h3.y yVar;
        C2088c c2088c;
        C2090e c2090e = this.f20839P0.f20832x;
        if (c2090e != null && c2090e.f20708j) {
            c2090e.f20705g = null;
            int i7 = AbstractC1343A.f15195a;
            Context context = c2090e.f20701a;
            if (i7 >= 23 && (c2088c = c2090e.d) != null) {
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                audioManager.getClass();
                audioManager.unregisterAudioDeviceCallback(c2088c);
            }
            context.unregisterReceiver(c2090e.f20703e);
            C2089d c2089d = c2090e.f20704f;
            if (c2089d != null) {
                c2089d.f20699a.unregisterContentObserver(c2089d);
            }
            c2090e.f20708j = false;
        }
        if (AbstractC1343A.f15195a < 35 || (yVar = this.f20840Q0) == null) {
            return;
        }
        ((HashSet) yVar.f15499m).clear();
        LoudnessCodecController loudnessCodecController = (LoudnessCodecController) yVar.o;
        if (loudnessCodecController != null) {
            loudnessCodecController.close();
        }
    }

    @Override // n2.AbstractC1905e
    public final void u() {
        v vVar = this.f20839P0;
        this.f20849Z0 = false;
        try {
            try {
                I();
                m0();
                q2.h hVar = this.f22131P;
                if (hVar != null) {
                    hVar.d(null);
                }
                this.f22131P = null;
            } catch (Throwable th) {
                q2.h hVar2 = this.f22131P;
                if (hVar2 != null) {
                    hVar2.d(null);
                }
                this.f22131P = null;
                throw th;
            }
        } finally {
            if (this.f20848Y0) {
                this.f20848Y0 = false;
                vVar.u();
            }
        }
    }

    @Override // s2.q
    public final boolean u0(C1208p c1208p) {
        a0 a0Var = this.o;
        a0Var.getClass();
        if (a0Var.f19622a != 0) {
            int iZ0 = z0(c1208p);
            if ((iZ0 & 512) != 0) {
                a0 a0Var2 = this.o;
                a0Var2.getClass();
                if (a0Var2.f19622a == 2 || (iZ0 & 1024) != 0 || (c1208p.f14298F == 0 && c1208p.f14299G == 0)) {
                    return true;
                }
            }
        }
        return this.f20839P0.i(c1208p) != 0;
    }

    @Override // n2.AbstractC1905e
    public final void v() {
        this.f20839P0.r();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a9  */
    @Override // s2.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int v0(s2.i r14, e2.C1208p r15) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p2.x.v0(s2.i, e2.p):int");
    }

    @Override // n2.AbstractC1905e
    public final void w() {
        B0();
        v vVar = this.f20839P0;
        vVar.f20799V = false;
        if (vVar.o()) {
            C2099n c2099n = vVar.f20814g;
            c2099n.d();
            if (c2099n.f20752x == -9223372036854775807L) {
                C2098m c2098m = c2099n.f20735e;
                c2098m.getClass();
                c2098m.a();
            } else {
                c2099n.f20754z = c2099n.b();
                if (!v.p(vVar.f20830v)) {
                    return;
                }
            }
            vVar.f20830v.pause();
        }
    }

    public final int z0(C1208p c1208p) {
        C2091f c2091fH = this.f20839P0.h(c1208p);
        if (!c2091fH.f20709a) {
            return 0;
        }
        int i7 = c2091fH.f20710b ? 1536 : 512;
        return c2091fH.c ? i7 | 2048 : i7;
    }
}
