package com.byd.filter2.g;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes2.dex */
public class a extends com.byd.filter2.e.b {
    private String u;

    public a(Resources resources) {
        super(resources);
        this.u = "precision mediump float;\nuniform sampler2D vTexture;\nvarying vec2 textureCoordinate;\nvoid main()\n{\n  vec4 sourceColor = texture2D(vTexture, textureCoordinate.xy);\n  if(sourceColor.a == 0.0){\n    gl_FragColor = sourceColor;\n  }else{\n    float fGrayColor = (sourceColor.r + sourceColor.g + sourceColor.b)/3.0;\n    gl_FragColor = vec4(fGrayColor, fGrayColor, fGrayColor, 1.0);\n  }\n}";
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        b("attribute vec4 vPosition;\nattribute vec2 vCoord;\nuniform mat4 vMatrix;\nvarying vec2 textureCoordinate;\nvoid main(){\n    gl_Position = vMatrix*vPosition;\n    textureCoordinate = vCoord;\n}", this.u);
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
    }
}
