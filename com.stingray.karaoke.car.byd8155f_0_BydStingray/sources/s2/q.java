package s2;

import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import e2.C1208p;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import l5.C1778f;
import m2.InterfaceC1794a;
import n2.AbstractC1905e;
import n2.C1906f;
import n2.C1907g;
import n2.C1913m;
import n2.C1924y;
import p2.y;
import u2.a0;

/* JADX INFO: loaded from: classes.dex */
public abstract class q extends AbstractC1905e {

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public static final byte[] f22107M0 = {0, 0, 1, 103, 66, -64, 11, -38, 37, -112, 0, 0, 1, 104, -50, 15, 19, 32, 0, 0, 1, 101, -120, -124, 13, -50, 113, 24, -96, 0, 47, -65, 28, 49, -61, 39, 93, 120};

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public boolean f22108A0;
    public long B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final j f22109C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public long f22110C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final i f22111D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public boolean f22112D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final float f22113E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public boolean f22114E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final m2.g f22115F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public boolean f22116F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final m2.g f22117G;
    public boolean G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final m2.g f22118H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public C1913m f22119H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final g f22120I;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public C1906f f22121I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final MediaCodec.BufferInfo f22122J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public p f22123J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final ArrayDeque f22124K;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public long f22125K0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final y f22126L;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public boolean f22127L0;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public C1208p f22128M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public C1208p f22129N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public q2.h f22130O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public q2.h f22131P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public C1924y f22132Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public MediaCrypto f22133R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final long f22134S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public float f22135T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public float f22136U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public k f22137V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public C1208p f22138W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public MediaFormat f22139X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public boolean f22140Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public float f22141Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public ArrayDeque f22142a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public o f22143b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public n f22144c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public int f22145d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f22146e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f22147f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f22148g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public boolean f22149h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public boolean f22150i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public boolean f22151j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public long f22152k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public long f22153l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public int f22154m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public int f22155n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public ByteBuffer f22156o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public boolean f22157p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public boolean f22158q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public boolean f22159r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public boolean f22160s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public boolean f22161t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public boolean f22162u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f22163v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f22164w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f22165x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public boolean f22166y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public boolean f22167z0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(int i7, j jVar, float f10) {
        super(i7);
        i iVar = i.f22094m;
        this.f22109C = jVar;
        this.f22111D = iVar;
        this.f22113E = f10;
        this.f22115F = new m2.g(0);
        this.f22117G = new m2.g(0);
        this.f22118H = new m2.g(2);
        g gVar = new g(2);
        gVar.f22092w = 32;
        this.f22120I = gVar;
        this.f22122J = new MediaCodec.BufferInfo();
        this.f22135T = 1.0f;
        this.f22136U = 1.0f;
        this.f22134S = -9223372036854775807L;
        this.f22124K = new ArrayDeque();
        this.f22123J0 = p.f22104e;
        gVar.j(0);
        gVar.f18955p.order(ByteOrder.nativeOrder());
        y yVar = new y();
        yVar.f20852a = f2.d.f14550a;
        yVar.c = 0;
        yVar.f20853b = 2;
        this.f22126L = yVar;
        this.f22141Z = -1.0f;
        this.f22145d0 = 0;
        this.f22163v0 = 0;
        this.f22154m0 = -1;
        this.f22155n0 = -1;
        this.f22153l0 = -9223372036854775807L;
        this.B0 = -9223372036854775807L;
        this.f22110C0 = -9223372036854775807L;
        this.f22125K0 = -9223372036854775807L;
        this.f22152k0 = -9223372036854775807L;
        this.f22164w0 = 0;
        this.f22165x0 = 0;
        this.f22121I0 = new C1906f();
    }

    @Override // n2.AbstractC1905e
    public void C(float f10, float f11) {
        this.f22135T = f10;
        this.f22136U = f11;
        w0(this.f22138W);
    }

    @Override // n2.AbstractC1905e
    public final int D(C1208p c1208p) throws C1913m {
        try {
            return v0(this.f22111D, c1208p);
        } catch (s e10) {
            throw this.e(e10, c1208p, false, 4002);
        }
    }

    @Override // n2.AbstractC1905e
    public final int E() {
        return 8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b0, code lost:
    
        r7 = r1;
        r1 = r2;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x031a  */
    /* JADX WARN: Type inference failed for: r14v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r26v0, types: [n2.e, s2.q] */
    /* JADX WARN: Type inference failed for: r6v20, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r6v31, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean F(long r27, long r29) {
        /*
            Method dump skipped, instruction units count: 842
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.F(long, long):boolean");
    }

    public abstract C1907g G(n nVar, C1208p c1208p, C1208p c1208p2);

    public m H(IllegalStateException illegalStateException, n nVar) {
        return new m(illegalStateException, nVar);
    }

    public final void I() {
        this.f22161t0 = false;
        this.f22120I.f();
        this.f22118H.f();
        this.f22160s0 = false;
        this.f22159r0 = false;
        y yVar = this.f22126L;
        yVar.getClass();
        yVar.f20852a = f2.d.f14550a;
        yVar.c = 0;
        yVar.f20853b = 2;
    }

    public final boolean J() throws C1913m {
        if (this.f22166y0) {
            this.f22164w0 = 1;
            if (this.f22147f0) {
                this.f22165x0 = 3;
                return false;
            }
            this.f22165x0 = 2;
        } else {
            x0();
        }
        return true;
    }

    public final boolean K(long j3, long j10) throws C1913m {
        boolean z6;
        boolean z10;
        MediaCodec.BufferInfo bufferInfo;
        boolean zK0;
        ByteBuffer byteBuffer;
        int i7;
        int i10;
        long j11;
        boolean z11;
        boolean z12;
        C1208p c1208p;
        int iE;
        k kVar = this.f22137V;
        kVar.getClass();
        boolean z13 = this.f22155n0 >= 0;
        MediaCodec.BufferInfo bufferInfo2 = this.f22122J;
        if (!z13) {
            if (this.f22148g0 && this.f22167z0) {
                try {
                    iE = kVar.e(bufferInfo2);
                } catch (IllegalStateException unused) {
                    j0();
                    if (this.f22114E0) {
                        m0();
                    }
                    return false;
                }
            } else {
                iE = kVar.e(bufferInfo2);
            }
            if (iE < 0) {
                if (iE == -2) {
                    this.f22108A0 = true;
                    k kVar2 = this.f22137V;
                    kVar2.getClass();
                    MediaFormat mediaFormatJ = kVar2.j();
                    if (this.f22145d0 != 0 && mediaFormatJ.getInteger("width") == 32 && mediaFormatJ.getInteger("height") == 32) {
                        this.f22150i0 = true;
                    } else {
                        this.f22139X = mediaFormatJ;
                        this.f22140Y = true;
                    }
                    return true;
                }
                if (this.f22151j0 && (this.f22112D0 || this.f22164w0 == 2)) {
                    j0();
                }
                long j12 = this.f22152k0;
                if (j12 != -9223372036854775807L) {
                    long j13 = j12 + 100;
                    this.f19640r.getClass();
                    if (j13 < System.currentTimeMillis()) {
                        j0();
                    }
                }
                return false;
            }
            if (this.f22150i0) {
                this.f22150i0 = false;
                kVar.g(iE);
                return true;
            }
            if (bufferInfo2.size == 0 && (bufferInfo2.flags & 4) != 0) {
                j0();
                return false;
            }
            this.f22155n0 = iE;
            ByteBuffer byteBufferO = kVar.o(iE);
            this.f22156o0 = byteBufferO;
            if (byteBufferO != null) {
                byteBufferO.position(bufferInfo2.offset);
                this.f22156o0.limit(bufferInfo2.offset + bufferInfo2.size);
            }
            long j14 = bufferInfo2.presentationTimeUs;
            this.f22157p0 = j14 < this.f19645w;
            long j15 = this.f22110C0;
            this.f22158q0 = j15 != -9223372036854775807L && j15 <= j14;
            y0(j14);
        }
        if (this.f22148g0 && this.f22167z0) {
            try {
                byteBuffer = this.f22156o0;
                i7 = this.f22155n0;
                i10 = bufferInfo2.flags;
                j11 = bufferInfo2.presentationTimeUs;
                z11 = this.f22157p0;
                z12 = this.f22158q0;
                c1208p = this.f22129N;
                c1208p.getClass();
                z6 = true;
                z10 = false;
            } catch (IllegalStateException unused2) {
                z10 = false;
            }
            try {
                zK0 = k0(j3, j10, kVar, byteBuffer, i7, i10, 1, j11, z11, z12, c1208p);
                bufferInfo = bufferInfo2;
            } catch (IllegalStateException unused3) {
                j0();
                if (this.f22114E0) {
                    m0();
                }
                return z10;
            }
        } else {
            z6 = true;
            z10 = false;
            ByteBuffer byteBuffer2 = this.f22156o0;
            int i11 = this.f22155n0;
            int i12 = bufferInfo2.flags;
            long j16 = bufferInfo2.presentationTimeUs;
            boolean z14 = this.f22157p0;
            boolean z15 = this.f22158q0;
            C1208p c1208p2 = this.f22129N;
            c1208p2.getClass();
            bufferInfo = bufferInfo2;
            zK0 = k0(j3, j10, kVar, byteBuffer2, i11, i12, 1, j16, z14, z15, c1208p2);
        }
        if (zK0) {
            f0(bufferInfo.presentationTimeUs);
            boolean z16 = (bufferInfo.flags & 4) != 0 ? z6 : z10;
            if (!z16 && this.f22167z0 && this.f22158q0) {
                this.f19640r.getClass();
                this.f22152k0 = System.currentTimeMillis();
            }
            this.f22155n0 = -1;
            this.f22156o0 = null;
            if (!z16) {
                return z6;
            }
            j0();
        }
        return z10;
    }

    public final boolean L() throws C1913m {
        k kVar = this.f22137V;
        if (kVar == null || this.f22164w0 == 2 || this.f22112D0) {
            return false;
        }
        int i7 = this.f22154m0;
        m2.g gVar = this.f22117G;
        if (i7 < 0) {
            int iR = kVar.r();
            this.f22154m0 = iR;
            if (iR < 0) {
                return false;
            }
            gVar.f18955p = kVar.l(iR);
            gVar.f();
        }
        if (this.f22164w0 == 1) {
            if (!this.f22151j0) {
                this.f22167z0 = true;
                kVar.c(this.f22154m0, 0, 4, 0L);
                this.f22154m0 = -1;
                gVar.f18955p = null;
            }
            this.f22164w0 = 2;
            return false;
        }
        if (this.f22149h0) {
            this.f22149h0 = false;
            ByteBuffer byteBuffer = gVar.f18955p;
            byteBuffer.getClass();
            byteBuffer.put(f22107M0);
            kVar.c(this.f22154m0, 38, 0, 0L);
            this.f22154m0 = -1;
            gVar.f18955p = null;
            this.f22166y0 = true;
            return true;
        }
        if (this.f22163v0 == 1) {
            int i10 = 0;
            while (true) {
                C1208p c1208p = this.f22138W;
                c1208p.getClass();
                if (i10 >= c1208p.f14318q.size()) {
                    break;
                }
                byte[] bArr = (byte[]) this.f22138W.f14318q.get(i10);
                ByteBuffer byteBuffer2 = gVar.f18955p;
                byteBuffer2.getClass();
                byteBuffer2.put(bArr);
                i10++;
            }
            this.f22163v0 = 2;
        }
        ByteBuffer byteBuffer3 = gVar.f18955p;
        byteBuffer3.getClass();
        int iPosition = byteBuffer3.position();
        C1778f c1778f = this.f19637n;
        c1778f.c();
        try {
            int iY = y(c1778f, gVar, 0);
            if (iY == -3) {
                if (m()) {
                    this.f22110C0 = this.B0;
                }
                return false;
            }
            if (iY == -5) {
                if (this.f22163v0 == 2) {
                    gVar.f();
                    this.f22163v0 = 1;
                }
                c0(c1778f);
                return true;
            }
            if (gVar.e(4)) {
                this.f22110C0 = this.B0;
                if (this.f22163v0 == 2) {
                    gVar.f();
                    this.f22163v0 = 1;
                }
                this.f22112D0 = true;
                if (!this.f22166y0) {
                    j0();
                    return false;
                }
                if (!this.f22151j0) {
                    this.f22167z0 = true;
                    kVar.c(this.f22154m0, 0, 4, 0L);
                    this.f22154m0 = -1;
                    gVar.f18955p = null;
                }
                return false;
            }
            if (!this.f22166y0 && !gVar.e(1)) {
                gVar.f();
                if (this.f22163v0 == 2) {
                    this.f22163v0 = 1;
                }
                return true;
            }
            if (t0(gVar)) {
                gVar.f();
                this.f22121I0.d++;
                return true;
            }
            boolean zE = gVar.e(TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            if (zE) {
                m2.c cVar = gVar.o;
                if (iPosition == 0) {
                    cVar.getClass();
                } else {
                    if (cVar.d == null) {
                        int[] iArr = new int[1];
                        cVar.d = iArr;
                        cVar.f18952i.numBytesOfClearData = iArr;
                    }
                    int[] iArr2 = cVar.d;
                    iArr2[0] = iArr2[0] + iPosition;
                }
            }
            long j3 = gVar.f18957r;
            if (this.f22116F0) {
                ArrayDeque arrayDeque = this.f22124K;
                if (arrayDeque.isEmpty()) {
                    A2.g gVar2 = this.f22123J0.d;
                    C1208p c1208p2 = this.f22128M;
                    c1208p2.getClass();
                    gVar2.b(j3, c1208p2);
                } else {
                    A2.g gVar3 = ((p) arrayDeque.peekLast()).d;
                    C1208p c1208p3 = this.f22128M;
                    c1208p3.getClass();
                    gVar3.b(j3, c1208p3);
                }
                this.f22116F0 = false;
            }
            this.B0 = Math.max(this.B0, j3);
            if (m() || gVar.e(536870912)) {
                this.f22110C0 = this.B0;
            }
            gVar.k();
            if (gVar.e(268435456)) {
                U(gVar);
            }
            h0(gVar);
            int iP = P(gVar);
            if (zE) {
                kVar.b(this.f22154m0, gVar.o, j3, iP);
            } else {
                int i11 = this.f22154m0;
                ByteBuffer byteBuffer4 = gVar.f18955p;
                byteBuffer4.getClass();
                kVar.c(i11, byteBuffer4.limit(), iP, j3);
            }
            this.f22154m0 = -1;
            gVar.f18955p = null;
            this.f22166y0 = true;
            this.f22163v0 = 0;
            this.f22121I0.c++;
            return true;
        } catch (m2.f e10) {
            Z(e10);
            l0(0);
            M();
            return true;
        }
    }

    public final void M() {
        try {
            k kVar = this.f22137V;
            AbstractC1346c.k(kVar);
            kVar.flush();
        } finally {
            o0();
        }
    }

    public final boolean N() {
        if (this.f22137V == null) {
            return false;
        }
        int i7 = this.f22165x0;
        if (i7 == 3 || ((this.f22146e0 && !this.f22108A0) || (this.f22147f0 && this.f22167z0))) {
            m0();
            return true;
        }
        if (i7 == 2) {
            int i10 = AbstractC1343A.f15195a;
            AbstractC1346c.j(i10 >= 23);
            if (i10 >= 23) {
                try {
                    x0();
                } catch (C1913m e10) {
                    AbstractC1346c.A("MediaCodecRenderer", "Failed to update the DRM session, releasing the codec instead.", e10);
                    m0();
                    return true;
                }
            }
        }
        M();
        return false;
    }

    public final List O(boolean z6) {
        C1208p c1208p = this.f22128M;
        c1208p.getClass();
        i iVar = this.f22111D;
        ArrayList arrayListS = S(iVar, c1208p, z6);
        if (arrayListS.isEmpty() && z6) {
            arrayListS = S(iVar, c1208p, false);
            if (!arrayListS.isEmpty()) {
                AbstractC1346c.z("MediaCodecRenderer", "Drm session requires secure decoder for " + c1208p.f14316n + ", but no secure decoder available. Trying to proceed with " + arrayListS + ".");
            }
        }
        return arrayListS;
    }

    public int P(m2.g gVar) {
        return 0;
    }

    public boolean Q() {
        return false;
    }

    public abstract float R(float f10, C1208p[] c1208pArr);

    public abstract ArrayList S(i iVar, C1208p c1208p, boolean z6);

    public abstract H0.b T(n nVar, C1208p c1208p, MediaCrypto mediaCrypto, float f10);

    public abstract void U(m2.g gVar);

    /* JADX WARN: Removed duplicated region for block: B:32:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void V(s2.n r13, android.media.MediaCrypto r14) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.V(s2.n, android.media.MediaCrypto):void");
    }

    public final boolean W(long j3, long j10) {
        C1208p c1208p;
        return j10 < j3 && ((c1208p = this.f22129N) == null || !Objects.equals(c1208p.f14316n, "audio/opus") || j3 - j10 > 80000);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void X() {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.X():void");
    }

    public final void Y(MediaCrypto mediaCrypto, boolean z6) throws o {
        C1208p c1208p = this.f22128M;
        c1208p.getClass();
        if (this.f22142a0 == null) {
            try {
                List listO = O(z6);
                this.f22142a0 = new ArrayDeque();
                if (!listO.isEmpty()) {
                    this.f22142a0.add((n) listO.get(0));
                }
                this.f22143b0 = null;
            } catch (s e10) {
                throw new o(c1208p, e10, z6, -49998);
            }
        }
        if (this.f22142a0.isEmpty()) {
            throw new o(c1208p, null, z6, -49999);
        }
        ArrayDeque arrayDeque = this.f22142a0;
        arrayDeque.getClass();
        while (this.f22137V == null) {
            n nVar = (n) arrayDeque.peekFirst();
            nVar.getClass();
            if (!s0(nVar)) {
                return;
            }
            try {
                V(nVar, mediaCrypto);
            } catch (Exception e11) {
                AbstractC1346c.A("MediaCodecRenderer", "Failed to initialize decoder: " + nVar, e11);
                arrayDeque.removeFirst();
                o oVar = new o("Decoder init failed: " + nVar.f22095a + ", " + c1208p, e11, c1208p.f14316n, z6, nVar, e11 instanceof MediaCodec.CodecException ? ((MediaCodec.CodecException) e11).getDiagnosticInfo() : null);
                Z(oVar);
                o oVar2 = this.f22143b0;
                if (oVar2 == null) {
                    this.f22143b0 = oVar;
                } else {
                    this.f22143b0 = new o(oVar2.getMessage(), oVar2.getCause(), oVar2.l, oVar2.f22102m, oVar2.f22103n, oVar2.o);
                }
                if (arrayDeque.isEmpty()) {
                    throw this.f22143b0;
                }
            }
        }
        this.f22142a0 = null;
    }

    public abstract void Z(Exception exc);

    public abstract void a0(long j3, long j10, String str);

    public abstract void b0(String str);

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e3, code lost:
    
        if (r4.e(r3) != false) goto L134;
     */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public n2.C1907g c0(l5.C1778f r14) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.c0(l5.f):n2.g");
    }

    public abstract void d0(C1208p c1208p, MediaFormat mediaFormat);

    public void e0() {
    }

    public void f0(long j3) {
        this.f22125K0 = j3;
        while (true) {
            ArrayDeque arrayDeque = this.f22124K;
            if (arrayDeque.isEmpty() || j3 < ((p) arrayDeque.peek()).f22105a) {
                return;
            }
            p pVar = (p) arrayDeque.poll();
            pVar.getClass();
            r0(pVar);
            g0();
        }
    }

    public abstract void g0();

    public void h0(m2.g gVar) {
    }

    public void i0(C1208p c1208p) {
    }

    public final void j0() throws C1913m {
        int i7 = this.f22165x0;
        if (i7 == 1) {
            M();
            return;
        }
        if (i7 == 2) {
            M();
            x0();
        } else if (i7 != 3) {
            this.f22114E0 = true;
            n0();
        } else {
            m0();
            X();
        }
    }

    public abstract boolean k0(long j3, long j10, k kVar, ByteBuffer byteBuffer, int i7, int i10, int i11, long j11, boolean z6, boolean z10, C1208p c1208p);

    public final boolean l0(int i7) throws C1913m {
        C1778f c1778f = this.f19637n;
        c1778f.c();
        m2.g gVar = this.f22115F;
        gVar.f();
        int iY = y(c1778f, gVar, i7 | 4);
        if (iY == -5) {
            c0(c1778f);
            return true;
        }
        if (iY != -4 || !gVar.e(4)) {
            return false;
        }
        this.f22112D0 = true;
        j0();
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void m0() {
        try {
            k kVar = this.f22137V;
            if (kVar != null) {
                kVar.a();
                this.f22121I0.f19650b++;
                n nVar = this.f22144c0;
                nVar.getClass();
                b0(nVar.f22095a);
            }
            this.f22137V = null;
            try {
                MediaCrypto mediaCrypto = this.f22133R;
                if (mediaCrypto != null) {
                    mediaCrypto.release();
                }
            } finally {
            }
        } catch (Throwable th) {
            this.f22137V = null;
            try {
                MediaCrypto mediaCrypto2 = this.f22133R;
                if (mediaCrypto2 != null) {
                    mediaCrypto2.release();
                }
                throw th;
            } finally {
            }
        }
    }

    public void n0() {
    }

    public void o0() {
        this.f22154m0 = -1;
        this.f22117G.f18955p = null;
        this.f22155n0 = -1;
        this.f22156o0 = null;
        this.f22153l0 = -9223372036854775807L;
        this.f22167z0 = false;
        this.f22152k0 = -9223372036854775807L;
        this.f22166y0 = false;
        this.f22149h0 = false;
        this.f22150i0 = false;
        this.f22157p0 = false;
        this.f22158q0 = false;
        this.B0 = -9223372036854775807L;
        this.f22110C0 = -9223372036854775807L;
        this.f22125K0 = -9223372036854775807L;
        this.f22164w0 = 0;
        this.f22165x0 = 0;
        this.f22163v0 = this.f22162u0 ? 1 : 0;
    }

    @Override // n2.AbstractC1905e
    public boolean p() {
        boolean zK;
        if (this.f22128M == null) {
            return false;
        }
        if (m()) {
            zK = this.f19647y;
        } else {
            a0 a0Var = this.f19642t;
            a0Var.getClass();
            zK = a0Var.k();
        }
        if (!zK) {
            if (!(this.f22155n0 >= 0)) {
                if (this.f22153l0 == -9223372036854775807L) {
                    return false;
                }
                this.f19640r.getClass();
                if (SystemClock.elapsedRealtime() >= this.f22153l0) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void p0() {
        o0();
        this.f22119H0 = null;
        this.f22142a0 = null;
        this.f22144c0 = null;
        this.f22138W = null;
        this.f22139X = null;
        this.f22140Y = false;
        this.f22108A0 = false;
        this.f22141Z = -1.0f;
        this.f22145d0 = 0;
        this.f22146e0 = false;
        this.f22147f0 = false;
        this.f22148g0 = false;
        this.f22151j0 = false;
        this.f22162u0 = false;
        this.f22163v0 = 0;
    }

    @Override // n2.AbstractC1905e
    public void q() {
        this.f22128M = null;
        r0(p.f22104e);
        this.f22124K.clear();
        N();
    }

    public final void q0(q2.h hVar) {
        q2.h hVar2 = this.f22130O;
        if (hVar2 != hVar) {
            if (hVar != null) {
                hVar.c(null);
            }
            if (hVar2 != null) {
                hVar2.d(null);
            }
        }
        this.f22130O = hVar;
    }

    public final void r0(p pVar) {
        this.f22123J0 = pVar;
        if (pVar.c != -9223372036854775807L) {
            this.f22127L0 = true;
            e0();
        }
    }

    @Override // n2.AbstractC1905e
    public void s(long j3, boolean z6) {
        this.f22112D0 = false;
        this.f22114E0 = false;
        this.G0 = false;
        if (this.f22159r0) {
            this.f22120I.f();
            this.f22118H.f();
            this.f22160s0 = false;
            y yVar = this.f22126L;
            yVar.getClass();
            yVar.f20852a = f2.d.f14550a;
            yVar.c = 0;
            yVar.f20853b = 2;
        } else if (N()) {
            X();
        }
        if (this.f22123J0.d.A() > 0) {
            this.f22116F0 = true;
        }
        this.f22123J0.d.d();
        this.f22124K.clear();
    }

    public boolean s0(n nVar) {
        return true;
    }

    public boolean t0(m2.g gVar) {
        return false;
    }

    public boolean u0(C1208p c1208p) {
        return false;
    }

    public abstract int v0(i iVar, C1208p c1208p);

    public final boolean w0(C1208p c1208p) {
        if (AbstractC1343A.f15195a >= 23 && this.f22137V != null && this.f22165x0 != 3 && this.f19641s != 0) {
            float f10 = this.f22136U;
            c1208p.getClass();
            C1208p[] c1208pArr = this.f19643u;
            c1208pArr.getClass();
            float fR = R(f10, c1208pArr);
            float f11 = this.f22141Z;
            if (f11 == fR) {
                return true;
            }
            if (fR == -1.0f) {
                if (this.f22166y0) {
                    this.f22164w0 = 1;
                    this.f22165x0 = 3;
                    return false;
                }
                m0();
                X();
                return false;
            }
            if (f11 == -1.0f && fR <= this.f22113E) {
                return true;
            }
            Bundle bundle = new Bundle();
            bundle.putFloat("operating-rate", fR);
            k kVar = this.f22137V;
            kVar.getClass();
            kVar.d(bundle);
            this.f22141Z = fR;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r7 >= r5) goto L13;
     */
    @Override // n2.AbstractC1905e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void x(e2.C1208p[] r13, long r14, long r16, u2.C2492C r18) {
        /*
            r12 = this;
            r0 = r12
            s2.p r1 = r0.f22123J0
            long r1 = r1.c
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L20
            s2.p r1 = new s2.p
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.r0(r1)
            goto L63
        L20:
            java.util.ArrayDeque r1 = r0.f22124K
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L55
            long r5 = r0.B0
            int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r2 == 0) goto L38
            long r7 = r0.f22125K0
            int r2 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r2 == 0) goto L55
            int r2 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r2 < 0) goto L55
        L38:
            s2.p r1 = new s2.p
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.r0(r1)
            s2.p r1 = r0.f22123J0
            long r1 = r1.c
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L63
            r12.g0()
            goto L63
        L55:
            s2.p r9 = new s2.p
            long r3 = r0.B0
            r2 = r9
            r5 = r14
            r7 = r16
            r2.<init>(r3, r5, r7)
            r1.add(r9)
        L63:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.x(e2.p[], long, long, u2.C):void");
    }

    public final void x0() throws C1913m {
        q2.h hVar = this.f22131P;
        hVar.getClass();
        InterfaceC1794a interfaceC1794aG = hVar.g();
        if (interfaceC1794aG instanceof q2.t) {
            try {
                MediaCrypto mediaCrypto = this.f22133R;
                mediaCrypto.getClass();
                mediaCrypto.setMediaDrmSession(((q2.t) interfaceC1794aG).f21219b);
            } catch (MediaCryptoException e10) {
                throw e(e10, this.f22128M, false, 6006);
            }
        }
        q0(this.f22131P);
        this.f22164w0 = 0;
        this.f22165x0 = 0;
    }

    public final void y0(long j3) {
        C1208p c1208p = (C1208p) this.f22123J0.d.w(j3);
        if (c1208p == null && this.f22127L0 && this.f22139X != null) {
            c1208p = (C1208p) this.f22123J0.d.v();
        }
        if (c1208p != null) {
            this.f22129N = c1208p;
        } else if (!this.f22140Y || this.f22129N == null) {
            return;
        }
        C1208p c1208p2 = this.f22129N;
        c1208p2.getClass();
        d0(c1208p2, this.f22139X);
        this.f22140Y = false;
        this.f22127L0 = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0078 A[LOOP:1: B:31:0x0053->B:41:0x0078, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0099 A[LOOP:2: B:42:0x0079->B:52:0x0099, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0079 A[EDGE_INSN: B:87:0x0079->B:90:? BREAK  A[LOOP:1: B:31:0x0053->B:41:0x0078], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x009a A[EDGE_INSN: B:88:0x009a->B:53:0x009a BREAK  A[LOOP:2: B:42:0x0079->B:52:0x0099], SYNTHETIC] */
    @Override // n2.AbstractC1905e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void z(long r12, long r14) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s2.q.z(long, long):void");
    }
}
