package com.byd.filter2.h;

import android.content.Context;
import android.graphics.Rect;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.byd.filter2.h.k;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class g extends com.byd.filter2.e.c implements Runnable {
    private EGLContext A;
    private int B;
    private int C;
    private Context D;
    private int E;
    private e F;
    private k u;
    private String v;
    private Handler w;
    private boolean x;
    private com.byd.filter2.a y;
    private EGLSurface z;

    class a implements k.a {
        a() {
        }

        @Override // com.byd.filter2.h.k.a
        public void a(int i) {
            if (g.this.F != null) {
                g.this.F.c(g.this.v, i);
            }
        }
    }

    private class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                g.this.U((EGLContext) message.obj, message.arg1, message.arg2);
                return;
            }
            if (i == 2) {
                g.this.a0();
                return;
            }
            if (i == 5) {
                g.this.S(((Long) message.obj).longValue());
                return;
            }
            if (i != 6) {
                return;
            }
            if (g.this.y != null) {
                g.this.y.g(g.this.z);
            }
            g.this.A = EGL14.EGL_NO_CONTEXT;
            w.c("RecordRenderVideoEdit quit");
            g.this.W();
        }
    }

    public g(Context context) {
        super(context.getResources());
        this.D = context;
        new Thread(this).start();
    }

    private void P(Rect rect) {
        if (rect == null || rect.isEmpty()) {
            return;
        }
        w.c("checkClipZone : " + rect + " " + this.B + " " + this.C);
        if (rect.left == 0 && rect.top == 0 && rect.right == this.B && rect.bottom == this.C) {
            return;
        }
        float fWidth = this.B / rect.width();
        float fHeight = this.C / rect.height();
        w.c("scaleX : " + fWidth + " scaleY : " + fHeight + " width : " + this.B + " height : " + this.C + " " + rect);
        com.byd.filter2.c.d(this.k, fWidth, fHeight);
        int iCenterX = rect.centerX();
        int iCenterY = rect.centerY();
        int i = this.B / 2;
        int i2 = this.C / 2;
        if (i == iCenterX || i2 == iCenterY) {
            return;
        }
        float f = 1.0f - (iCenterX / i);
        float f2 = -(1.0f - (iCenterY / i2));
        com.byd.filter2.c.e(this.k, f, f2);
        w.c("translate dx : " + f + " dy : " + f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(long j) {
        this.y.i(this.z);
        c();
        this.y.o(this.z, j);
        GLES20.glFinish();
        this.y.p(this.z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U(EGLContext eGLContext, int i, int i2) {
        com.byd.filter2.a aVar = new com.byd.filter2.a();
        this.y = aVar;
        aVar.b(eGLContext);
        k kVar = new k(this.D, i, i2, this.v, null, this.E);
        this.u = kVar;
        kVar.y(new a());
        EGLSurface eGLSurfaceE = this.y.e(this.u.r());
        this.z = eGLSurfaceE;
        if (!this.y.i(eGLSurfaceE)) {
            Log.e("m_tag", "prepareVideoEncoder: make current error");
        }
        w.c("RecordRender prepareVideoEncoder");
        super.q();
        this.u.z();
        e eVar = this.F;
        if (eVar != null) {
            eVar.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        Looper.myLooper().quit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a0() {
        this.u.A();
    }

    public void Q(int i) {
        this.E = i;
        w.c("configVideoInfo frameRate : " + i);
    }

    public void R(long j) {
        if (!this.x || this.u == null) {
            return;
        }
        this.w.obtainMessage(5, Long.valueOf(j)).sendToTarget();
    }

    public boolean T() {
        return this.x;
    }

    public void V() {
        this.w.sendEmptyMessage(6);
    }

    public void X(e eVar) {
        this.F = eVar;
    }

    public void Y(int i, int i2, String str, Rect rect) {
        if (this.x) {
            Log.e("m_tag", "startRecord in RecordRender --> recording");
            return;
        }
        this.B = i;
        this.C = i2;
        P(rect);
        this.v = str;
        Log.e("m_tag", "startRecord context : " + this.A.toString());
        this.w.obtainMessage(1, i, i2, this.A).sendToTarget();
        this.x = true;
    }

    public void Z() {
        if (this.x) {
            Log.e("m_tag", "stopRecord");
            this.x = false;
            this.w.removeCallbacksAndMessages(null);
            this.w.sendEmptyMessage(2);
        }
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void q() {
        this.A = EGL14.eglGetCurrentContext();
    }

    @Override // com.byd.filter2.e.b
    protected void r() {
        GLES30.glViewport(0, 0, this.B, this.C);
        super.r();
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.w = new b();
        Looper.loop();
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void t(int i, int i2) {
    }
}
