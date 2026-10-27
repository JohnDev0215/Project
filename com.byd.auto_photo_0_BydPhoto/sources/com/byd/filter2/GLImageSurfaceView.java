package com.byd.filter2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;
import com.byd.filter2.f.a;
import com.byd.util.w;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes2.dex */
public class GLImageSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f2815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected com.byd.filter2.e.b f2816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.filter2.e.b f2817c;
    private com.byd.filter2.f.a d;
    protected int e;
    protected int f;
    protected int g;
    protected int h;
    private Bitmap i;
    protected Handler j;
    private boolean k;
    private a.InterfaceC0076a l;
    private Context m;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GLImageSurfaceView.this.b();
        }
    }

    public GLImageSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2815a = -1;
        this.m = context;
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-3);
        setZOrderOnTop(true);
        setRenderer(this);
        setRenderMode(0);
        this.j = new Handler(Looper.getMainLooper());
        this.d = new com.byd.filter2.f.a(this.m.getResources());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.e == 0 || this.f == 0 || this.g == 0 || this.h == 0) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int i = marginLayoutParams.width;
        int i2 = this.g;
        if (i == i2 && marginLayoutParams.height == this.h) {
            w.c("size no change");
            Point pointQ = this.d.Q();
            if (pointQ.x == 0 || pointQ.y == 0) {
                this.d.z(this.e, this.f);
                return;
            }
            return;
        }
        float f = (this.e * 1.0f) / this.f;
        int i3 = this.h;
        if (f < ((double) i2) / ((double) i3)) {
            this.g = (int) (i3 * f);
        } else {
            this.h = (int) (i2 / f);
        }
        marginLayoutParams.width = this.g;
        marginLayoutParams.height = this.h;
        setLayoutParams(marginLayoutParams);
    }

    private void c() {
        this.f2815a = d(this.i, this.f2815a);
        Log.e("m_tag", "createTexture " + this.f2815a);
        if (this.f2815a == -1) {
            return;
        }
        this.j.post(new a());
    }

    private int d(Bitmap bitmap, int i) {
        int[] iArr = new int[1];
        if (i == -1) {
            w.c("GLView createTexture 111");
            iArr[0] = b.e(bitmap, iArr, false);
        } else {
            iArr[0] = i;
            if (bitmap != null && !bitmap.isRecycled()) {
                w.c("GLView createTexture 222");
                GLES30.glBindTexture(3553, iArr[0]);
                GLUtils.texSubImage2D(3553, 0, 0, 0, bitmap);
            }
        }
        return iArr[0];
    }

    private void e() {
        com.byd.filter2.e.c cVar = new com.byd.filter2.e.c(this.m.getResources());
        this.f2816b = cVar;
        cVar.a();
    }

    private void f() {
        com.byd.filter2.e.b bVar = this.f2816b;
        if (bVar != null) {
            bVar.z(this.g, this.h);
        }
        if (this.d != null) {
            Log.e("m_tag", "captureRender set size : " + this.e + " " + this.f);
            this.d.z(this.e, this.f);
        }
        com.byd.filter2.e.b bVar2 = this.f2817c;
        if (bVar2 != null) {
            bVar2.z(this.g, this.h);
        }
    }

    public void g() {
        com.byd.filter2.f.a aVar = this.d;
        if (aVar != null) {
            aVar.S();
            this.d.v();
            this.d = null;
        }
    }

    public com.byd.filter2.e.b getEffectFilter() {
        return this.f2817c;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(com.byd.filter2.e.b r3) {
        /*
            r2 = this;
            com.byd.filter2.e.b r0 = r2.f2817c
            if (r0 == 0) goto L19
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L10
            java.lang.String r3 = "filter no change!!!"
        Lc:
            com.byd.util.w.c(r3)
            return
        L10:
            com.byd.filter2.e.b r0 = r2.f2817c
            r0.v()
            r0 = 0
            r2.f2817c = r0
            goto L1e
        L19:
            if (r3 != 0) goto L1e
            java.lang.String r3 = "filter is null"
            goto Lc
        L1e:
            r2.f2817c = r3
            if (r3 == 0) goto L29
            int r0 = r2.g
            int r1 = r2.h
            r3.z(r0, r1)
        L29:
            r2.requestRender()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filter2.GLImageSurfaceView.h(com.byd.filter2.e.b):void");
    }

    public synchronized void i() {
        if (this.k) {
            Toast.makeText(getContext(), "正在保存图片", 0).show();
            return;
        }
        w.c("GLImageSurfaceView saveBitmap");
        this.k = true;
        requestRender();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onDrawFrame(GL10 gl10) {
        boolean z;
        if (this.f2815a == -1 && this.i != null) {
            c();
            if (this.f2815a == -1) {
                w.c("illegal texture id : -1");
                return;
            }
        }
        int iH = this.f2815a;
        GLES20.glViewport(0, 0, this.g, this.h);
        com.byd.filter2.e.b bVar = this.f2817c;
        if (bVar != null) {
            bVar.B(iH);
            this.f2817c.a();
            this.f2817c.c();
            iH = this.f2817c.h();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            this.f2816b.B(iH);
            this.f2816b.c();
        }
        if (this.k) {
            this.d.T(this.f2817c);
            this.d.B(iH);
            this.d.N();
            this.k = false;
        }
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        this.f2815a = -1;
        this.f2816b = null;
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.g = i;
        this.h = i2;
        Log.e("m_tag", "==onSurfaceChanged==" + Thread.currentThread().getName() + " width:" + this.g + " height:" + this.h);
        GLES30.glViewport(0, 0, i, i2);
        if (this.i != null) {
            c();
            Log.e("m_tag", "onSurfaceChanged:" + i + " " + i2 + " " + this.f2815a);
            f();
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES30.glDisable(3024);
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES30.glEnable(2884);
        GLES30.glEnable(2929);
        e();
        this.d.a();
    }

    public void setBitmap(Bitmap bitmap) {
        a.InterfaceC0076a interfaceC0076a;
        this.i = bitmap;
        this.e = bitmap.getWidth();
        this.f = this.i.getHeight();
        com.byd.filter2.f.a aVar = this.d;
        if (aVar != null && (interfaceC0076a = this.l) != null) {
            aVar.U(interfaceC0076a);
        }
        Log.e("m_tag", "Filter2 GlView setBitmap " + this.e + " " + this.f + " " + Thread.currentThread().getName() + " " + this.g + " " + this.h);
        if (this.g <= 0 || this.h <= 0) {
            return;
        }
        b();
    }

    public void setCaptureCallback(a.InterfaceC0076a interfaceC0076a) {
        this.l = interfaceC0076a;
        com.byd.filter2.f.a aVar = this.d;
        if (aVar != null) {
            aVar.U(interfaceC0076a);
        }
    }
}
