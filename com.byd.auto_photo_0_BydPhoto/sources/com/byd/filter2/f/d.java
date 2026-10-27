package com.byd.filter2.f;

import android.content.res.Resources;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;

/* JADX INFO: loaded from: classes2.dex */
public class d extends com.byd.filter2.e.b {
    private SurfaceTexture A;
    private float[] B;
    private b u;
    private int v;
    private int w;
    private int[] x;
    private int[] y;
    private int[] z;

    public d(Resources resources) {
        super(resources);
        this.v = 0;
        this.w = 0;
        this.x = new int[1];
        this.y = new int[1];
        this.z = new int[1];
        this.B = new float[16];
        this.u = new b(resources);
    }

    private void E() {
        GLES20.glGenTextures(1, this.z, 0);
    }

    private void F() {
        GLES20.glDeleteFramebuffers(1, this.x, 0);
        GLES20.glDeleteTextures(1, this.y, 0);
    }

    public SurfaceTexture G() {
        return this.A;
    }

    public long H() {
        return this.A.getTimestamp();
    }

    @Override // com.byd.filter2.e.b
    public void c() {
        boolean zGlIsEnabled = GLES20.glIsEnabled(2929);
        if (zGlIsEnabled) {
            GLES20.glDisable(2929);
        }
        SurfaceTexture surfaceTexture = this.A;
        if (surfaceTexture != null) {
            surfaceTexture.updateTexImage();
            this.A.getTransformMatrix(this.B);
            this.u.E(this.B);
        }
        com.byd.filter2.b.a(this.x[0], this.y[0]);
        GLES20.glViewport(0, 0, this.v, this.w);
        this.u.B(this.z[0]);
        this.u.c();
        com.byd.filter2.b.g();
        if (zGlIsEnabled) {
            GLES20.glEnable(2929);
        }
    }

    @Override // com.byd.filter2.e.b
    public int h() {
        return this.y[0];
    }

    @Override // com.byd.filter2.e.b
    protected void n() {
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        this.u.a();
        E();
        this.A = new SurfaceTexture(this.z[0]);
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
        this.u.z(i, i2);
        if (this.v == i && this.w == i2) {
            return;
        }
        this.v = i;
        this.w = i2;
        F();
        GLES20.glGenFramebuffers(1, this.x, 0);
        com.byd.filter2.b.d(1, this.y, 0, 6408, i, i2);
    }
}
