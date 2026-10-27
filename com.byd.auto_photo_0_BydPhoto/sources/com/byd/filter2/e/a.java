package com.byd.filter2.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends b {
    private int[] u;
    private int[] v;
    private int w;

    public a(Resources resources) {
        super(resources);
    }

    protected abstract String E();

    protected String F() {
        return "attribute vec4 vPosition;\nattribute vec2 vCoord;\nuniform mat4 vMatrix;\nvarying vec2 textureCoordinate;\nvoid main(){\n    gl_Position = vMatrix*vPosition;\n    textureCoordinate = vCoord;\n}";
    }

    protected abstract String[] G();

    protected abstract int[] H();

    protected Bitmap I(Resources resources, String str) {
        Bitmap bitmapDecodeStream = null;
        try {
            InputStream inputStreamOpen = resources.getAssets().open(str);
            bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
            return bitmapDecodeStream;
        } catch (IOException e) {
            e.printStackTrace();
            return bitmapDecodeStream;
        }
    }

    protected Bitmap J(Resources resources, int i) {
        return ((BitmapDrawable) resources.getDrawable(i)).getBitmap();
    }

    protected abstract String[] K();

    @Override // com.byd.filter2.e.b
    protected void o() {
        super.o();
        GLES30.glUniform1f(this.w, 1.0f);
        if (this.u == null) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr = this.u;
            if (i >= iArr.length) {
                return;
            }
            if (iArr[i] != -1) {
                int i2 = i + 1;
                GLES20.glActiveTexture(33984 + i2);
                GLES20.glBindTexture(3553, this.u[i]);
                GLES20.glUniform1i(this.v[i], i2);
            }
            i++;
        }
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        b(F(), E());
        this.w = GLES30.glGetUniformLocation(this.f2824c, "strength");
        String[] strArrK = K();
        if (strArrK != null) {
            int length = strArrK.length;
            String[] strArrG = G();
            int[] iArrH = H();
            this.u = new int[length];
            this.v = new int[length];
            for (int i = 0; i < length; i++) {
                boolean z = true;
                int[] iArr = new int[1];
                Bitmap bitmapJ = null;
                if (strArrG != null) {
                    bitmapJ = I(this.h, strArrG[i]);
                } else if (iArrH != null) {
                    bitmapJ = J(this.h, iArrH[i]);
                    z = false;
                }
                if (bitmapJ != null) {
                    this.u[i] = com.byd.filter2.b.e(bitmapJ, iArr, z);
                    this.v[i] = GLES20.glGetUniformLocation(this.f2824c, strArrK[i]);
                    Log.e("m_tag", strArrK[i] + " " + this.u[i]);
                }
            }
        }
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
    }

    @Override // com.byd.filter2.e.b
    public void v() {
        int[] iArr = this.u;
        if (iArr == null || iArr.length <= 0) {
            return;
        }
        GLES30.glDeleteTextures(iArr.length, iArr, 0);
        this.u = null;
    }
}
