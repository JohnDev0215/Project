package com.google.android.exoplayer2.video;

import android.annotation.TargetApi;
import android.content.Context;
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
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(17)
public final class DummySurface extends Surface {
    private static boolean secureSupported;
    private static boolean secureSupportedInitialized;
    public final boolean secure;
    private final DummySurfaceThread thread;
    private boolean threadReleased;

    public static synchronized boolean isSecureSupported(Context context) {
        if (!secureSupportedInitialized) {
            secureSupported = Util.SDK_INT >= 24 && enableSecureDummySurfaceV24(context);
            secureSupportedInitialized = true;
        }
        return secureSupported;
    }

    public static DummySurface newInstanceV17(Context context, boolean z) {
        assertApiLevel17OrHigher();
        Assertions.checkState(!z || isSecureSupported(context));
        return new DummySurfaceThread().init(z);
    }

    private DummySurface(DummySurfaceThread dummySurfaceThread, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.thread = dummySurfaceThread;
        this.secure = z;
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        synchronized (this.thread) {
            if (!this.threadReleased) {
                this.thread.release();
                this.threadReleased = true;
            }
        }
    }

    private static void assertApiLevel17OrHigher() {
        if (Util.SDK_INT < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }

    @TargetApi(24)
    private static boolean enableSecureDummySurfaceV24(Context context) {
        String strEglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
        if (strEglQueryString == null || !strEglQueryString.contains("EGL_EXT_protected_content")) {
            return false;
        }
        int i = Util.SDK_INT;
        if (i == 24 && "samsung".equals(Util.MANUFACTURER)) {
            return false;
        }
        return i >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance");
    }

    private static class DummySurfaceThread extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener, Handler.Callback {
        private EGLContext context;
        private EGLDisplay display;
        private Handler handler;
        private Error initError;
        private RuntimeException initException;
        private EGLSurface pbuffer;
        private DummySurface surface;
        private SurfaceTexture surfaceTexture;
        private final int[] textureIdHolder;

        public DummySurfaceThread() {
            super("dummySurface");
            this.textureIdHolder = new int[1];
        }

        public DummySurface init(boolean z) {
            boolean z2;
            start();
            this.handler = new Handler(getLooper(), this);
            synchronized (this) {
                z2 = false;
                this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
                while (this.surface == null && this.initException == null && this.initError == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z2 = true;
                    }
                }
            }
            if (z2) {
                Thread.currentThread().interrupt();
            }
            RuntimeException runtimeException = this.initException;
            if (runtimeException != null) {
                throw runtimeException;
            }
            Error error = this.initError;
            if (error != null) {
                throw error;
            }
            return this.surface;
        }

        public void release() {
            this.handler.sendEmptyMessage(3);
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            this.handler.sendEmptyMessage(2);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            try {
                if (i != 1) {
                    if (i == 2) {
                        this.surfaceTexture.updateTexImage();
                        return true;
                    }
                    if (i != 3) {
                        return true;
                    }
                    try {
                        releaseInternal();
                    } finally {
                        try {
                        } finally {
                        }
                    }
                    return true;
                }
                try {
                    initInternal(message.arg1 != 0);
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e) {
                    Log.e("DummySurface", "Failed to initialize dummy surface", e);
                    this.initError = e;
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e2) {
                    Log.e("DummySurface", "Failed to initialize dummy surface", e2);
                    this.initException = e2;
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

        private void initInternal(boolean z) {
            EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(0);
            this.display = eGLDisplayEglGetDisplay;
            Assertions.checkState(eGLDisplayEglGetDisplay != null, "eglGetDisplay failed");
            int[] iArr = new int[2];
            Assertions.checkState(EGL14.eglInitialize(this.display, iArr, 0, iArr, 1), "eglInitialize failed");
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            int[] iArr2 = new int[1];
            Assertions.checkState(EGL14.eglChooseConfig(this.display, new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344}, 0, eGLConfigArr, 0, 1, iArr2, 0) && iArr2[0] > 0 && eGLConfigArr[0] != null, "eglChooseConfig failed");
            EGLConfig eGLConfig = eGLConfigArr[0];
            EGLContext eGLContextEglCreateContext = EGL14.eglCreateContext(this.display, eGLConfig, EGL14.EGL_NO_CONTEXT, z ? new int[]{12440, 2, 12992, 1, 12344} : new int[]{12440, 2, 12344}, 0);
            this.context = eGLContextEglCreateContext;
            Assertions.checkState(eGLContextEglCreateContext != null, "eglCreateContext failed");
            EGLSurface eGLSurfaceEglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(this.display, eGLConfig, z ? new int[]{12375, 1, 12374, 1, 12992, 1, 12344} : new int[]{12375, 1, 12374, 1, 12344}, 0);
            this.pbuffer = eGLSurfaceEglCreatePbufferSurface;
            Assertions.checkState(eGLSurfaceEglCreatePbufferSurface != null, "eglCreatePbufferSurface failed");
            EGLDisplay eGLDisplay = this.display;
            EGLSurface eGLSurface = this.pbuffer;
            Assertions.checkState(EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.context), "eglMakeCurrent failed");
            GLES20.glGenTextures(1, this.textureIdHolder, 0);
            SurfaceTexture surfaceTexture = new SurfaceTexture(this.textureIdHolder[0]);
            this.surfaceTexture = surfaceTexture;
            surfaceTexture.setOnFrameAvailableListener(this);
            this.surface = new DummySurface(this, this.surfaceTexture, z);
        }

        private void releaseInternal() {
            try {
                SurfaceTexture surfaceTexture = this.surfaceTexture;
                if (surfaceTexture != null) {
                    surfaceTexture.release();
                    GLES20.glDeleteTextures(1, this.textureIdHolder, 0);
                }
            } finally {
                EGLSurface eGLSurface = this.pbuffer;
                if (eGLSurface != null) {
                    EGL14.eglDestroySurface(this.display, eGLSurface);
                }
                EGLContext eGLContext = this.context;
                if (eGLContext != null) {
                    EGL14.eglDestroyContext(this.display, eGLContext);
                }
                this.pbuffer = null;
                this.context = null;
                this.display = null;
                this.surface = null;
                this.surfaceTexture = null;
            }
        }
    }
}
