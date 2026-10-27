package com.byd.filter2.g;

import android.content.res.Resources;
import android.opengl.GLES30;

/* JADX INFO: loaded from: classes2.dex */
public abstract class e extends com.byd.filter2.e.a {
    private int x;
    protected float[] y;

    public e(Resources resources) {
        super(resources);
        this.y = L();
    }

    @Override // com.byd.filter2.e.a
    protected String E() {
        return "precision highp float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D vTexture;\nuniform float colorMatrix[20]; \nuniform float strength;\nvoid main()\n{\n\tlowp vec4 sourceColor = texture2D(vTexture, textureCoordinate.xy);\n\tif(sourceColor.a == 0.0){\n\t    gl_FragColor = sourceColor;\n\t}else{\n      lowp vec4 textureColor = vec4(sourceColor.r * colorMatrix[0] + sourceColor.g * colorMatrix[1] + sourceColor.b * colorMatrix[2] + colorMatrix[4] / 255.0, sourceColor.r * colorMatrix[5] + sourceColor.g * colorMatrix[6] + sourceColor.b * colorMatrix[7]  + colorMatrix[9] / 255.0, sourceColor.r * colorMatrix[10] + sourceColor.g * colorMatrix[11] + sourceColor.b * colorMatrix[12]  + colorMatrix[14] / 255.0, colorMatrix[18]);\n      gl_FragColor = mix(sourceColor, textureColor, strength);\n\t}\n}";
    }

    @Override // com.byd.filter2.e.a
    protected String[] G() {
        return null;
    }

    @Override // com.byd.filter2.e.a
    protected int[] H() {
        return null;
    }

    @Override // com.byd.filter2.e.a
    protected String[] K() {
        return null;
    }

    protected abstract float[] L();

    @Override // com.byd.filter2.e.a, com.byd.filter2.e.b
    protected void o() {
        super.o();
        GLES30.glUniform1fv(this.x, 20, this.y, 0);
    }

    @Override // com.byd.filter2.e.a, com.byd.filter2.e.b
    protected void q() {
        super.q();
        this.x = GLES30.glGetUniformLocation(this.f2824c, "colorMatrix");
    }
}
