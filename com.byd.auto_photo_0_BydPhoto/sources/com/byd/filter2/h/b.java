package com.byd.filter2.h;

import android.opengl.EGL14;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.byd.util.w;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b extends HandlerThread {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f2829a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f2830b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2831c;
    private int d;
    private EGL10 e;
    private EGLConfig f;
    private EGLDisplay g;
    private EGLSurface h;
    private EGLContext i;
    private GL10 j;
    private EGLContext k;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            b.this.o();
            long jLongValue = ((Long) message.obj).longValue();
            b bVar = b.this;
            bVar.n(bVar.j, jLongValue);
        }
    }

    /* JADX INFO: renamed from: com.byd.filter2.h.b$b, reason: collision with other inner class name */
    class RunnableC0077b implements Runnable {
        RunnableC0077b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = b.this;
            if (bVar.j(bVar.f2831c, b.this.d, true) != 0) {
                w.c("video edit -> gl init fail");
            } else {
                b bVar2 = b.this;
                bVar2.l(bVar2.e.eglGetCurrentContext(), b.this.j, b.this.f);
            }
        }
    }

    public b(int i, int i2) {
        super("GLRenderer");
        this.k = EGL10.EGL_NO_CONTEXT;
        this.f2829a = false;
        this.f2831c = i;
        this.d = i2;
    }

    private EGLSurface g(int[] iArr) {
        try {
            return this.e.eglCreatePbufferSurface(this.g, this.f, iArr);
        } catch (Exception unused) {
            return EGL10.EGL_NO_SURFACE;
        }
    }

    private void h(int i, int i2) {
        this.h = g(new int[]{12375, i, 12374, i2, 12344});
    }

    private void i() {
        EGL10 egl10 = this.e;
        EGLDisplay eGLDisplay = this.g;
        EGLSurface eGLSurface = EGL10.EGL_NO_SURFACE;
        egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, EGL10.EGL_NO_CONTEXT);
        EGLSurface eGLSurface2 = this.h;
        if (eGLSurface2 != EGL10.EGL_NO_SURFACE) {
            try {
                this.e.eglDestroySurface(this.g, eGLSurface2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.e.eglTerminate(this.g);
        this.i = EGL10.EGL_NO_CONTEXT;
        this.g = EGL10.EGL_NO_DISPLAY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int j(int i, int i2, boolean z) {
        StringBuilder sb;
        String str;
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        this.e = egl10;
        EGLDisplay eGLDisplayEglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        this.g = eGLDisplayEglGetDisplay;
        if (eGLDisplayEglGetDisplay == EGL10.EGL_NO_DISPLAY) {
            w.c("video edit -> egl get display fail " + EGL14.eglGetError());
        }
        if (!this.e.eglInitialize(this.g, new int[2])) {
            w.c("video edit -> egl init error" + EGL14.eglGetError());
        }
        int[] iArr = new int[1];
        int[] iArr2 = {12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12352, 4, 12344};
        this.e.eglChooseConfig(this.g, iArr2, null, 0, iArr);
        if (iArr[0] == 0) {
            sb = new StringBuilder();
            str = "video edit -> egl config error ";
        } else {
            EGLConfig[] eGLConfigArr = new EGLConfig[iArr[0]];
            this.e.eglChooseConfig(this.g, iArr2, eGLConfigArr, iArr[0], iArr);
            EGLConfig eGLConfig = eGLConfigArr[0];
            this.f = eGLConfig;
            EGLContext eGLContextEglCreateContext = this.e.eglCreateContext(this.g, eGLConfig, this.k, new int[]{12440, 3, 12344});
            this.i = eGLContextEglCreateContext;
            if (eGLContextEglCreateContext != EGL10.EGL_NO_CONTEXT) {
                this.j = (GL10) eGLContextEglCreateContext.getGL();
                if (z) {
                    h(i, i2);
                    k();
                }
                return 0;
            }
            sb = new StringBuilder();
            str = "video edit -> egl create context fail : ";
        }
        sb.append(str);
        sb.append(EGL14.eglGetError());
        w.c(sb.toString());
        return 101;
    }

    private boolean k() {
        EGL10 egl10 = this.e;
        EGLDisplay eGLDisplay = this.g;
        EGLSurface eGLSurface = this.h;
        return egl10.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, this.i);
    }

    public abstract void l(EGLContext eGLContext, GL10 gl10, EGLConfig eGLConfig);

    public abstract void m();

    public abstract void n(GL10 gl10, long j);

    public abstract void o();

    public void p() {
        if (this.f2829a) {
            return;
        }
        this.f2830b.getLooper().quitSafely();
        while (isAlive()) {
            try {
                join(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.f2829a = true;
    }

    public void q(long j) {
        this.f2830b.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    public void r() {
        Log.e("m_tag", "start");
        super.start();
        a aVar = new a(getLooper());
        this.f2830b = aVar;
        aVar.post(new RunnableC0077b());
    }

    @Override // android.os.HandlerThread, java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        i();
        m();
        Log.e("m_tag", "video edit -> GLRenderer thread run end");
    }

    @Override // java.lang.Thread
    public void start() {
        Log.w("m_tag", "Don't call this function");
    }
}
