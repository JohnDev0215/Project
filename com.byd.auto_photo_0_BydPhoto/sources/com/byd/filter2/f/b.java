package com.byd.filter2.f;

import android.content.res.Resources;
import android.opengl.GLES20;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class b extends com.byd.filter2.e.b {
    protected int u;
    private float[] v;

    public b(Resources resources) {
        super(resources);
        this.v = Arrays.copyOf(com.byd.filter2.e.b.f2823b, 16);
    }

    public void E(float[] fArr) {
        this.v = fArr;
    }

    @Override // com.byd.filter2.e.b
    protected void o() {
        GLES20.glActiveTexture(k() + 33984);
        GLES20.glBindTexture(36197, j());
        GLES20.glUniform1i(this.g, k());
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        b("attribute vec4 vPosition;\nattribute vec2 vCoord;\nuniform mat4 vMatrix;\nuniform mat4 vCoordMatrix;\nvarying vec2 textureCoordinate;\nvoid main(){\n    gl_Position = vMatrix*vPosition;\n    textureCoordinate = (vCoordMatrix*vec4(vCoord,0,1)).xy;\n}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 textureCoordinate;\nuniform samplerExternalOES vTexture;\nvoid main() {\n    gl_FragColor = texture2D(vTexture, textureCoordinate );\n}");
        this.u = GLES20.glGetUniformLocation(this.f2824c, "vCoordMatrix");
    }

    @Override // com.byd.filter2.e.b
    protected void s() {
        super.s();
        GLES20.glUniformMatrix4fv(this.u, 1, false, this.v, 0);
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
    }
}
