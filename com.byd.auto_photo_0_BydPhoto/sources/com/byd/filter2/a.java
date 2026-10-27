package com.byd.filter2;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.util.Log;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private EGLConfig f2820b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private EGLDisplay f2819a = EGL14.EGL_NO_DISPLAY;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private EGLContext f2821c = EGL14.EGL_NO_CONTEXT;

    public void a(EGLContext eGLContext) {
        int[] iArr = {12440, 2, 12344};
        w.c("mEglDisplay : " + this.f2819a + " mEglConfig : " + this.f2820b + " context : " + eGLContext);
        try {
            this.f2821c = EGL14.eglCreateContext(this.f2819a, this.f2820b, eGLContext, iArr, 0);
        } catch (Exception e) {
            e.printStackTrace();
            w.c("eglCreateContext exception : " + e.getMessage());
        }
        if (this.f2821c == EGL14.EGL_NO_CONTEXT) {
            w.c("EGL error " + EGL14.eglGetError());
        }
        w.c("createContext : " + EGL14.eglGetError());
    }

    public void b(EGLContext eGLContext) {
        n(0);
        l();
        a(eGLContext);
    }

    public EGLSurface c(int i, int i2) {
        return EGL14.eglCreatePbufferSurface(this.f2819a, this.f2820b, new int[]{12375, i, 12374, i2, 12344}, 0);
    }

    public EGLSurface d(EGLConfig eGLConfig, Object obj, int[] iArr) {
        EGLSurface eGLSurfaceEglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.f2819a, eGLConfig, obj, iArr, 0);
        if (eGLSurfaceEglCreateWindowSurface != EGL14.EGL_NO_SURFACE) {
            return eGLSurfaceEglCreateWindowSurface;
        }
        w.c("createWindowSurface : " + EGL14.eglGetError());
        return null;
    }

    public EGLSurface e(Object obj) {
        return d(this.f2820b, obj, new int[]{12344});
    }

    public void f() {
        try {
            EGL14.eglDestroyContext(this.f2819a, this.f2821c);
        } catch (Exception e) {
            w.c("destroyGL exception:" + e.getMessage());
        }
        this.f2821c = EGL14.EGL_NO_CONTEXT;
        this.f2819a = EGL14.EGL_NO_DISPLAY;
    }

    public boolean g(EGLSurface eGLSurface) {
        EGLDisplay eGLDisplay = this.f2819a;
        EGLSurface eGLSurface2 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, EGL14.EGL_NO_CONTEXT);
        if (eGLSurface != null) {
            EGL14.eglDestroySurface(this.f2819a, eGLSurface);
        }
        EGLContext eGLContext = this.f2821c;
        if (eGLContext != null) {
            EGL14.eglDestroyContext(this.f2819a, eGLContext);
        }
        EGL14.eglTerminate(this.f2819a);
        return true;
    }

    public void h(EGLSurface eGLSurface) {
        try {
            EGL14.eglDestroySurface(this.f2819a, eGLSurface);
        } catch (Exception e) {
            w.c("destroySurface exception:" + e.getMessage());
        }
    }

    public boolean i(EGLSurface eGLSurface) {
        return j(eGLSurface, this.f2821c);
    }

    public boolean j(EGLSurface eGLSurface, EGLContext eGLContext) {
        return k(eGLSurface, eGLSurface, eGLContext);
    }

    public boolean k(EGLSurface eGLSurface, EGLSurface eGLSurface2, EGLContext eGLContext) {
        if (EGL14.eglMakeCurrent(this.f2819a, eGLSurface, eGLSurface2, eGLContext)) {
            return true;
        }
        w.c("makeCurrent:" + EGL14.eglGetError());
        return false;
    }

    public void l() {
        m(new int[]{12339, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12352, 4, 12344});
    }

    public void m(int[] iArr) {
        int[] iArr2 = new int[1];
        EGL14.eglChooseConfig(this.f2819a, iArr, 0, null, 0, 0, iArr2, 0);
        if (iArr2[0] == 0) {
            Log.e("m_tag", "----->no config");
            return;
        }
        int i = iArr2[0];
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        if (!EGL14.eglChooseConfig(this.f2819a, iArr, 0, eGLConfigArr, 0, i, iArr2, 0)) {
            Log.e("m_tag", "config error");
        }
        this.f2820b = eGLConfigArr[0];
        w.c("setConfig : " + EGL14.eglGetError());
    }

    public void n(int i) {
        EGLDisplay eGLDisplayEglGetDisplay = EGL14.eglGetDisplay(i);
        this.f2819a = eGLDisplayEglGetDisplay;
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(eGLDisplayEglGetDisplay, iArr, 0, iArr, 1)) {
            throw new RuntimeException("EGL error" + EGL14.eglGetError());
        }
        w.c(EGL14.eglQueryString(this.f2819a, 12371));
        w.c(EGL14.eglQueryString(this.f2819a, 12372));
        w.c(EGL14.eglQueryString(this.f2819a, 12373));
        w.c("setDisplay : " + EGL14.eglGetError());
    }

    public boolean o(EGLSurface eGLSurface, long j) {
        if (EGLExt.eglPresentationTimeANDROID(this.f2819a, eGLSurface, j)) {
            return true;
        }
        w.c("setPresentationTime:" + EGL14.eglGetError());
        return false;
    }

    public boolean p(EGLSurface eGLSurface) {
        if (EGL14.eglSwapBuffers(this.f2819a, eGLSurface)) {
            return true;
        }
        w.c("swapBuffers:" + EGL14.eglGetError());
        return false;
    }
}
