package A2;

import e2.C1208p;
import h2.AbstractC1343A;
import h2.t;
import java.nio.ByteBuffer;
import l5.C1778f;
import n2.AbstractC1905e;

/* JADX INFO: loaded from: classes.dex */
public final class b extends AbstractC1905e {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final m2.g f306C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final t f307D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public a f308E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public long f309F;

    public b() {
        super(6);
        this.f306C = new m2.g(1);
        this.f307D = new t();
    }

    @Override // n2.AbstractC1905e
    public final int D(C1208p c1208p) {
        return "application/x-camera-motion".equals(c1208p.f14316n) ? AbstractC1905e.d(4, 0, 0, 0) : AbstractC1905e.d(0, 0, 0, 0);
    }

    @Override // n2.AbstractC1905e, n2.W
    public final void b(int i7, Object obj) {
        if (i7 == 8) {
            this.f308E = (a) obj;
        }
    }

    @Override // n2.AbstractC1905e
    public final String l() {
        return "CameraMotionRenderer";
    }

    @Override // n2.AbstractC1905e
    public final boolean n() {
        return m();
    }

    @Override // n2.AbstractC1905e
    public final boolean p() {
        return true;
    }

    @Override // n2.AbstractC1905e
    public final void q() {
        a aVar = this.f308E;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Override // n2.AbstractC1905e
    public final void s(long j3, boolean z6) {
        this.f309F = Long.MIN_VALUE;
        a aVar = this.f308E;
        if (aVar != null) {
            aVar.c();
        }
    }

    @Override // n2.AbstractC1905e
    public final void z(long j3, long j10) {
        float[] fArr;
        while (!m() && this.f309F < 100000 + j3) {
            m2.g gVar = this.f306C;
            gVar.f();
            C1778f c1778f = this.f19637n;
            c1778f.c();
            if (y(c1778f, gVar, 0) != -4 || gVar.e(4)) {
                return;
            }
            long j11 = gVar.f18957r;
            this.f309F = j11;
            boolean z6 = j11 < this.f19645w;
            if (this.f308E != null && !z6) {
                gVar.k();
                ByteBuffer byteBuffer = gVar.f18955p;
                int i7 = AbstractC1343A.f15195a;
                if (byteBuffer.remaining() != 16) {
                    fArr = null;
                } else {
                    byte[] bArrArray = byteBuffer.array();
                    int iLimit = byteBuffer.limit();
                    t tVar = this.f307D;
                    tVar.F(iLimit, bArrArray);
                    tVar.H(byteBuffer.arrayOffset() + 4);
                    float[] fArr2 = new float[3];
                    for (int i10 = 0; i10 < 3; i10++) {
                        fArr2[i10] = Float.intBitsToFloat(tVar.j());
                    }
                    fArr = fArr2;
                }
                if (fArr != null) {
                    this.f308E.a(this.f309F - this.f19644v, fArr);
                }
            }
        }
    }
}
