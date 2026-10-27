package h2;

import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class i implements SurfaceTexture.OnFrameAvailableListener, Runnable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f15214r = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    public final Handler l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int[] f15215m = new int[1];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EGLDisplay f15216n;
    public EGLContext o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public EGLSurface f15217p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public SurfaceTexture f15218q;

    public i(Handler handler) {
        this.l = handler;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.l.post(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        SurfaceTexture surfaceTexture = this.f15218q;
        if (surfaceTexture != null) {
            try {
                surfaceTexture.updateTexImage();
            } catch (RuntimeException unused) {
            }
        }
    }
}
