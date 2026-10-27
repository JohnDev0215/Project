package defpackage;

import J.N;
import android.graphics.SurfaceTexture;

/* JADX INFO: compiled from: SurfaceTextureListener.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tw2 implements SurfaceTexture.OnFrameAvailableListener {
    public final long a;

    public tw2(long j) {
        this.a = j;
    }

    public final void finalize() throws Throwable {
        try {
            N.MUH2vhAN(this.a, this);
        } finally {
            super.finalize();
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        N.MRbPfCZQ(this.a, this);
    }
}
