package com.byd.filter2.f;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.opengl.GLES30;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class c extends com.byd.filter2.e.c implements Runnable {
    private int A;
    private Point B;
    private Rect C;
    private com.byd.filter2.a u;
    private EGLSurface v;
    private EGLContext w;
    private a x;
    private boolean y;
    private int z;

    private class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    c.this.u.i(c.this.v);
                    c.this.c();
                    c.this.u.p(c.this.v);
                    return;
                } else if (i == 3) {
                    c.this.u.g(c.this.v);
                    c.this.v = EGL14.EGL_NO_SURFACE;
                    return;
                } else if (i == 4) {
                    Looper.myLooper().quit();
                    c.this.y = false;
                    return;
                } else {
                    if (i != 5) {
                        return;
                    }
                    if (c.this.u != null) {
                        c.this.u.h(c.this.v);
                        c.this.v = EGL14.EGL_NO_SURFACE;
                    }
                }
            } else if (c.this.y) {
                return;
            }
            c.this.P(message.obj);
        }
    }

    public c(Resources resources) {
        super(resources);
        this.C = new Rect();
        new Thread(this).start();
    }

    private void N() {
        Point point = this.B;
        int i = point.x;
        int i2 = this.z;
        int i3 = point.y;
        int i4 = this.A;
        this.C.set((i - i2) / 2, (i3 - i4) / 2, i2, i4);
        w.c("showBound : " + this.C);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(Object obj) {
        w.c("PreviewRender prepare");
        if (this.u == null) {
            this.u = new com.byd.filter2.a();
        }
        if (this.w == null) {
            this.w = EGL14.eglGetCurrentContext();
            w.c("egl context is null , eglGetCurrentContext : " + this.w);
        }
        this.u.b(this.w);
        EGLSurface eGLSurfaceE = this.u.e(obj);
        this.v = eGLSurfaceE;
        if (eGLSurfaceE == null) {
            this.y = false;
            w.c("photo -> no EglSurface , preview fail !!!");
            return;
        }
        boolean zI = this.u.i(eGLSurfaceE);
        super.q();
        if (zI) {
            this.y = true;
        } else {
            this.y = false;
        }
    }

    public void M(Object obj) {
        if (this.y) {
            this.x.obtainMessage(5, obj).sendToTarget();
            w.c("==replace preview surface==");
        } else {
            w.c("==add surface==");
            this.x.obtainMessage(1, obj).sendToTarget();
        }
    }

    public void O() {
        if (this.y) {
            this.x.obtainMessage(2).sendToTarget();
        }
    }

    public void Q() {
        this.x.sendEmptyMessage(4);
    }

    public void R() {
        if (this.y) {
            this.y = false;
            w.c("==remove preview surface==");
            this.x.obtainMessage(3).sendToTarget();
        }
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void q() {
        this.w = EGL14.eglGetCurrentContext();
        w.c("PreviewRender onCreate : " + this.w);
    }

    @Override // com.byd.filter2.e.b
    protected void r() {
        if (this.B != null) {
            Rect rect = this.C;
            GLES30.glViewport(rect.left, rect.top, rect.right, rect.bottom);
        } else {
            GLES30.glViewport(0, 0, this.z, this.A);
        }
        super.r();
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.x = new a();
        Looper.loop();
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void t(int i, int i2) {
        this.z = i;
        this.A = i2;
        w.c("PreviewRender onSizeChange : " + i + " " + i2);
        Point point = this.B;
        if (point == null || point.x <= 0) {
            return;
        }
        N();
    }
}
