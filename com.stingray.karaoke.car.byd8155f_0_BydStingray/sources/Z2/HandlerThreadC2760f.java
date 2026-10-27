package z2;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Locale;

/* JADX INFO: renamed from: z2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class HandlerThreadC2760f extends HandlerThread implements Handler.Callback {
    public h2.i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Handler f24311m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Error f24312n;
    public RuntimeException o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2761g f24313p;

    public final void a(int i7) throws h2.j {
        EGLSurface eGLSurfaceEglCreatePbufferSurface;
        this.l.getClass();
        h2.i iVar = this.l;
        iVar.getClass();
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
        AbstractC1346c.e("eglGetDisplay failed", eGLDisplayEglGetDisplay != null);
        int[] iArr = new int[2];
        AbstractC1346c.e("eglInitialize failed", EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1));
        iVar.f15216n = eGLDisplayEglGetDisplay;
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr2 = new int[1];
        boolean zEglChooseConfig = EGL14.eglChooseConfig(eGLDisplayEglGetDisplay, h2.i.f15214r, 0, eGLConfigArr, 0, 1, iArr2, 0);
        boolean z6 = zEglChooseConfig && iArr2[0] > 0 && eGLConfigArr[0] != null;
        Object[] objArr = {Boolean.valueOf(zEglChooseConfig), Integer.valueOf(iArr2[0]), eGLConfigArr[0]};
        int i10 = AbstractC1343A.f15195a;
        AbstractC1346c.e(String.format(Locale.US, "eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", objArr), z6);
        EGLConfig eGLConfig = eGLConfigArr[0];
        EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(iVar.f15216n, eGLConfig, EGL14.EGL_NO_CONTEXT, i7 == 0 ? new int[]{12440, 2, 12344} : new int[]{12440, 2, 12992, 1, 12344}, 0);
        AbstractC1346c.e("eglCreateContext failed", eGLContextEglCreateContext != null);
        iVar.o = eGLContextEglCreateContext;
        EGLDisplay eGLDisplay = iVar.f15216n;
        if (i7 == 1) {
            eGLSurfaceEglCreatePbufferSurface = EGL14.EGL_NO_SURFACE;
        } else {
            eGLSurfaceEglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, i7 == 2 ? new int[]{12375, 1, 12374, 1, 12992, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344}, 0);
            AbstractC1346c.e("eglCreatePbufferSurface failed", eGLSurfaceEglCreatePbufferSurface != null);
        }
        AbstractC1346c.e("eglMakeCurrent failed", EGL14.eglMakeCurrent(eGLDisplay, eGLSurfaceEglCreatePbufferSurface, eGLSurfaceEglCreatePbufferSurface, eGLContextEglCreateContext));
        iVar.f15217p = eGLSurfaceEglCreatePbufferSurface;
        int[] iArr3 = iVar.f15215m;
        GLES20.glGenTextures(1, iArr3, 0);
        AbstractC1346c.d();
        SurfaceTexture surfaceTexture = new SurfaceTexture(iArr3[0]);
        iVar.f15218q = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(iVar);
        SurfaceTexture surfaceTexture2 = this.l.f15218q;
        surfaceTexture2.getClass();
        this.f24313p = new C2761g(this, surfaceTexture2, i7 != 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b() {
        this.l.getClass();
        h2.i iVar = this.l;
        iVar.l.removeCallbacks(iVar);
        try {
            SurfaceTexture surfaceTexture = iVar.f15218q;
            if (surfaceTexture != null) {
                surfaceTexture.release();
                GLES20.glDeleteTextures(1, iVar.f15215m, 0);
            }
        } finally {
            EGLDisplay eGLDisplay = iVar.f15216n;
            if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
                EGLDisplay eGLDisplay2 = iVar.f15216n;
                EGLSurface eGLSurface = EGL14.EGL_NO_SURFACE;
                EGL14.eglMakeCurrent(eGLDisplay2, eGLSurface, eGLSurface, EGL14.EGL_NO_CONTEXT);
            }
            EGLSurface eGLSurface2 = iVar.f15217p;
            if (eGLSurface2 != null && !eGLSurface2.equals(EGL14.EGL_NO_SURFACE)) {
                EGL14.eglDestroySurface(iVar.f15216n, iVar.f15217p);
            }
            EGLContext eGLContext = iVar.o;
            if (eGLContext != null) {
                EGL14.eglDestroyContext(iVar.f15216n, eGLContext);
            }
            EGL14.eglReleaseThread();
            EGLDisplay eGLDisplay3 = iVar.f15216n;
            if (eGLDisplay3 != null && !eGLDisplay3.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglTerminate(iVar.f15216n);
            }
            iVar.f15216n = null;
            iVar.o = null;
            iVar.f15217p = null;
            iVar.f15218q = null;
        }
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i7 = message.what;
        try {
            if (i7 != 1) {
                if (i7 != 2) {
                    return true;
                }
                try {
                    b();
                } finally {
                    try {
                    } finally {
                    }
                }
                return true;
            }
            try {
                a(message.arg1);
                synchronized (this) {
                    notify();
                }
            } catch (h2.j e10) {
                AbstractC1346c.q("PlaceholderSurface", "Failed to initialize placeholder surface", e10);
                this.o = new IllegalStateException(e10);
                synchronized (this) {
                    notify();
                }
            } catch (Error e11) {
                AbstractC1346c.q("PlaceholderSurface", "Failed to initialize placeholder surface", e11);
                this.f24312n = e11;
                synchronized (this) {
                    notify();
                }
            } catch (RuntimeException e12) {
                AbstractC1346c.q("PlaceholderSurface", "Failed to initialize placeholder surface", e12);
                this.o = e12;
                synchronized (this) {
                    notify();
                }
            }
            return true;
        } catch (Throwable th) {
            synchronized (this) {
                notify();
                throw th;
            }
        }
    }
}
