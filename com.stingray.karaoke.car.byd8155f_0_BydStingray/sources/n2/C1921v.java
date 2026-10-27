package n2;

import android.media.MediaFormat;
import e2.C1208p;

/* JADX INFO: renamed from: n2.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1921v implements z2.n, A2.a, W {
    public z2.n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public A2.a f19723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public z2.n f19724n;
    public A2.a o;

    @Override // A2.a
    public final void a(long j3, float[] fArr) {
        A2.a aVar = this.o;
        if (aVar != null) {
            aVar.a(j3, fArr);
        }
        A2.a aVar2 = this.f19723m;
        if (aVar2 != null) {
            aVar2.a(j3, fArr);
        }
    }

    @Override // n2.W
    public final void b(int i7, Object obj) {
        if (i7 == 7) {
            this.l = (z2.n) obj;
            return;
        }
        if (i7 == 8) {
            this.f19723m = (A2.a) obj;
            return;
        }
        if (i7 != 10000) {
            return;
        }
        A2.o oVar = (A2.o) obj;
        if (oVar == null) {
            this.f19724n = null;
            this.o = null;
        } else {
            this.f19724n = oVar.getVideoFrameMetadataListener();
            this.o = oVar.getCameraMotionListener();
        }
    }

    @Override // A2.a
    public final void c() {
        A2.a aVar = this.o;
        if (aVar != null) {
            aVar.c();
        }
        A2.a aVar2 = this.f19723m;
        if (aVar2 != null) {
            aVar2.c();
        }
    }

    @Override // z2.n
    public final void d(long j3, long j10, C1208p c1208p, MediaFormat mediaFormat) {
        z2.n nVar = this.f19724n;
        if (nVar != null) {
            nVar.d(j3, j10, c1208p, mediaFormat);
        }
        z2.n nVar2 = this.l;
        if (nVar2 != null) {
            nVar2.d(j3, j10, c1208p, mediaFormat);
        }
    }
}
