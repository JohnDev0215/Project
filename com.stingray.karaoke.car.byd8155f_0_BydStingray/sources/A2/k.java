package A2;

import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.opengl.GLES20;
import e2.C1208p;
import h2.AbstractC1346c;
import h2.t;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class k implements z2.n, a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f339t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public SurfaceTexture f340u;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public byte[] f343x;
    public final AtomicBoolean l = new AtomicBoolean();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicBoolean f333m = new AtomicBoolean(true);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final i f334n = new i();
    public final c o = new c(0);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final g f335p = new g(6, (byte) 0);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final g f336q = new g(6, (byte) 0);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float[] f337r = new float[16];

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float[] f338s = new float[16];

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public volatile int f341v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f342w = -1;

    @Override // A2.a
    public final void a(long j3, float[] fArr) {
        ((g) this.o.f312e).b(j3, fArr);
    }

    public final SurfaceTexture b() {
        try {
            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
            AbstractC1346c.d();
            this.f334n.a();
            AbstractC1346c.d();
            int[] iArr = new int[1];
            GLES20.glGenTextures(1, iArr, 0);
            AbstractC1346c.d();
            int i7 = iArr[0];
            GLES20.glBindTexture(36197, i7);
            AbstractC1346c.d();
            GLES20.glTexParameteri(36197, 10240, 9729);
            AbstractC1346c.d();
            GLES20.glTexParameteri(36197, 10241, 9729);
            AbstractC1346c.d();
            GLES20.glTexParameteri(36197, 10242, 33071);
            AbstractC1346c.d();
            GLES20.glTexParameteri(36197, 10243, 33071);
            AbstractC1346c.d();
            this.f339t = i7;
        } catch (h2.j e10) {
            AbstractC1346c.q("SceneRenderer", "Failed to initialize the renderer", e10);
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.f339t);
        this.f340u = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: A2.j
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                this.l.l.set(true);
            }
        });
        return this.f340u;
    }

    @Override // A2.a
    public final void c() {
        this.f335p.d();
        c cVar = this.o;
        ((g) cVar.f312e).d();
        cVar.f311b = false;
        this.f333m.set(true);
    }

    @Override // z2.n
    public final void d(long j3, long j10, C1208p c1208p, MediaFormat mediaFormat) {
        int i7;
        float[] fArr;
        int i10;
        ArrayList arrayListQ;
        int iH;
        this.f335p.b(j10, Long.valueOf(j3));
        byte[] bArr = c1208p.f14327z;
        int i11 = c1208p.f14294A;
        byte[] bArr2 = this.f343x;
        int i12 = this.f342w;
        this.f343x = bArr;
        if (i11 == -1) {
            i11 = this.f341v;
        }
        this.f342w = i11;
        if (i12 == i11 && Arrays.equals(bArr2, this.f343x)) {
            return;
        }
        byte[] bArr3 = this.f343x;
        h hVar = null;
        if (bArr3 != null) {
            int i13 = this.f342w;
            t tVar = new t(bArr3);
            try {
                tVar.I(4);
                iH = tVar.h();
                tVar.H(0);
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
            if (iH == 1886547818) {
                tVar.I(8);
                int i14 = tVar.f15242b;
                int i15 = tVar.c;
                while (i14 < i15) {
                    int iH2 = tVar.h() + i14;
                    if (iH2 > i14 && iH2 <= i15) {
                        int iH3 = tVar.h();
                        if (iH3 != 2037673328 && iH3 != 1836279920) {
                            tVar.H(iH2);
                            i14 = iH2;
                        }
                        tVar.G(iH2);
                        arrayListQ = P8.n.Q(tVar);
                        break;
                    }
                    break;
                }
                arrayListQ = null;
            } else {
                arrayListQ = P8.n.Q(tVar);
            }
            if (arrayListQ != null) {
                int size = arrayListQ.size();
                if (size == 1) {
                    f fVar = (f) arrayListQ.get(0);
                    hVar = new h(fVar, fVar, i13);
                } else if (size == 2) {
                    hVar = new h((f) arrayListQ.get(0), (f) arrayListQ.get(1), i13);
                }
            }
        }
        if (hVar == null || !i.b(hVar)) {
            int i16 = this.f342w;
            float radians = (float) Math.toRadians(180.0f);
            float radians2 = (float) Math.toRadians(360.0f);
            float f10 = radians / 36;
            float f11 = radians2 / 72;
            float[] fArr2 = new float[15984];
            float[] fArr3 = new float[10656];
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            for (int i20 = 36; i17 < i20; i20 = 36) {
                float f12 = radians / 2.0f;
                float f13 = (i17 * f10) - f12;
                int i21 = i17 + 1;
                float f14 = (i21 * f10) - f12;
                int i22 = 0;
                while (i22 < 73) {
                    float f15 = f14;
                    int i23 = i21;
                    float f16 = f13;
                    int i24 = i18;
                    int i25 = i19;
                    int i26 = 2;
                    int i27 = 0;
                    while (i27 < i26) {
                        float f17 = i22 * f11;
                        float f18 = f11;
                        int i28 = i22;
                        double d = 50.0f;
                        int i29 = i16;
                        double d10 = (3.1415927f + f17) - (radians2 / 2.0f);
                        float f19 = radians;
                        double d11 = i27 == 0 ? f16 : f15;
                        int i30 = i27;
                        float f20 = f10;
                        fArr2[i24] = -((float) (Math.cos(d11) * Math.sin(d10) * d));
                        float[] fArr4 = fArr3;
                        int i31 = i17;
                        fArr2[i24 + 1] = (float) (Math.sin(d11) * d);
                        int i32 = i24 + 3;
                        fArr2[i24 + 2] = (float) (Math.cos(d11) * Math.cos(d10) * d);
                        fArr4[i25] = f17 / radians2;
                        int i33 = i25 + 2;
                        fArr4[i25 + 1] = ((i31 + i30) * f20) / f19;
                        if (i28 == 0 && i30 == 0) {
                            i10 = i30;
                            i7 = i28;
                        } else {
                            i7 = i28;
                            if (i7 == 72) {
                                i10 = i30;
                                if (i10 != 1) {
                                    fArr = fArr4;
                                }
                            } else {
                                fArr = fArr4;
                                i10 = i30;
                            }
                            i26 = 2;
                            i25 = i33;
                            i24 = i32;
                            int i34 = i10 + 1;
                            i22 = i7;
                            fArr3 = fArr;
                            f11 = f18;
                            i16 = i29;
                            radians = f19;
                            i17 = i31;
                            f10 = f20;
                            i27 = i34;
                        }
                        System.arraycopy(fArr2, i24, fArr2, i32, 3);
                        i24 += 6;
                        fArr = fArr4;
                        i26 = 2;
                        System.arraycopy(fArr, i25, fArr, i33, 2);
                        i25 += 4;
                        int i342 = i10 + 1;
                        i22 = i7;
                        fArr3 = fArr;
                        f11 = f18;
                        i16 = i29;
                        radians = f19;
                        i17 = i31;
                        f10 = f20;
                        i27 = i342;
                    }
                    i22++;
                    i18 = i24;
                    i19 = i25;
                    f14 = f15;
                    i21 = i23;
                    f13 = f16;
                    i16 = i16;
                }
                i17 = i21;
            }
            f fVar2 = new f(new g(0, 1, fArr2, fArr3));
            hVar = new h(fVar2, fVar2, i16);
        }
        this.f336q.b(j10, hVar);
    }
}
