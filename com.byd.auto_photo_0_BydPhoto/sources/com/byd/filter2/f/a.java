package com.byd.filter2.f;

import android.content.res.Resources;
import android.graphics.Point;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.byd.util.w;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class a extends com.byd.filter2.e.c implements Runnable {
    private ByteBuffer A;
    private com.byd.filter2.e.b B;
    private com.byd.filter2.a u;
    private EGLSurface v;
    private EGLContext w;
    private b x;
    private InterfaceC0076a y;
    private Point z;

    /* JADX INFO: renamed from: com.byd.filter2.f.a$a, reason: collision with other inner class name */
    public interface InterfaceC0076a {
        void a(ByteBuffer byteBuffer, int i, int i2);
    }

    private class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (a.this.u != null) {
                            a.this.u.h(a.this.v);
                            a.this.u.f();
                            a.this.v = EGL14.EGL_NO_SURFACE;
                            a.this.u = null;
                            return;
                        }
                        return;
                    }
                    if (i == 4) {
                        Looper.myLooper().quit();
                        str = "CaptureRender quit";
                    } else {
                        if (i != 5) {
                            return;
                        }
                        if (a.this.u != null) {
                            a.this.u.g(a.this.v);
                            a.this.v = EGL14.EGL_NO_SURFACE;
                        }
                    }
                } else {
                    if (a.this.u != null) {
                        a.this.u.i(a.this.v);
                        a.this.M();
                        a.this.u.p(a.this.v);
                        return;
                    }
                    str = "capture fail!!!";
                }
                w.c(str);
                return;
            }
            a.this.R(message.arg1, message.arg2);
        }
    }

    public a(Resources resources) {
        super(resources);
        this.z = new Point();
        new Thread(this).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        if (this.y != null) {
            Point point = this.z;
            this.A = ByteBuffer.allocate(point.x * point.y * 4);
            Point point2 = this.z;
            GLES20.glViewport(0, 0, point2.x, point2.y);
            com.byd.filter2.b.b("callback");
            c();
            P();
        }
    }

    private void P() {
        Point point = this.z;
        GLES20.glReadPixels(0, 0, point.x, point.y, 6408, 5121, this.A);
        InterfaceC0076a interfaceC0076a = this.y;
        ByteBuffer byteBuffer = this.A;
        Point point2 = this.z;
        interfaceC0076a.a(byteBuffer, point2.x, point2.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(int i, int i2) {
        w.c("===prepareSurface===");
        com.byd.filter2.a aVar = new com.byd.filter2.a();
        this.u = aVar;
        aVar.b(this.w);
        EGLSurface eGLSurfaceC = this.u.c(i, i2);
        this.v = eGLSurfaceC;
        this.u.i(eGLSurfaceC);
        super.q();
    }

    public void N() {
        this.x.sendEmptyMessage(2);
    }

    public void O() {
        if (this.u != null) {
            this.x.sendEmptyMessage(3);
            Log.e("m_tag", "==capture render destroy==");
        }
    }

    public Point Q() {
        return this.z;
    }

    public void S() {
        O();
        this.x.sendEmptyMessage(4);
    }

    public void T(com.byd.filter2.e.b bVar) {
        this.B = bVar;
    }

    public void U(InterfaceC0076a interfaceC0076a) {
        this.y = interfaceC0076a;
    }

    @Override // com.byd.filter2.e.b
    public void c() {
        com.byd.filter2.e.b bVar = this.B;
        if (bVar == null) {
            super.c();
            return;
        }
        bVar.B(j());
        com.byd.filter2.e.b bVar2 = this.B;
        Point point = this.z;
        bVar2.z(point.x, point.y);
        this.B.a();
        this.B.c();
        B(this.B.h());
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void q() {
        this.w = EGL14.eglGetCurrentContext();
    }

    @Override // com.byd.filter2.e.b
    protected void r() {
        Point point = this.z;
        GLES30.glViewport(0, 0, point.x, point.y);
        super.r();
    }

    @Override // java.lang.Runnable
    public void run() {
        Looper.prepare();
        this.x = new b();
        Looper.loop();
        w.c("CaptureRender run end");
    }

    @Override // com.byd.filter2.e.c, com.byd.filter2.e.b
    protected void t(int i, int i2) {
        String str;
        Point point = this.z;
        if (point.x == i && point.y == i2) {
            str = "capture size no change , return";
        } else {
            point.set(i, i2);
            b bVar = this.x;
            Point point2 = this.z;
            bVar.obtainMessage(1, point2.x, point2.y).sendToTarget();
            str = "==capture render onSizeChanged==" + this.z;
        }
        Log.e("m_tag", str);
    }
}
