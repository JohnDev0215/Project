package defpackage;

import J.N;
import android.graphics.SurfaceTexture;

/* JADX INFO: renamed from: mO, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1162mO implements SurfaceTexture.OnFrameAvailableListener {
    public final long a;

    public C1162mO(long j) {
        this.a = j;
    }

    public void finalize() throws Throwable {
        try {
            N.MUH2vhAN(this.a, this);
        } finally {
            super.finalize();
        }
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        N.MRbPfCZQ(this.a, this);
    }
}
