package com.byd.filter2.e;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    public c(Resources resources) {
        super(resources);
    }

    protected String E() {
        return "precision mediump float;\nvarying vec2 textureCoordinate;\nuniform sampler2D vTexture;\nvoid main() {\n    gl_FragColor = texture2D(vTexture, textureCoordinate);\n}";
    }

    protected String F() {
        return "attribute vec4 vPosition;\nattribute vec2 vCoord;\nuniform mat4 vMatrix;\nvarying vec2 textureCoordinate;\nvoid main(){\n    gl_Position = vMatrix*vPosition;\n    textureCoordinate = vCoord;\n}";
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        b(F(), E());
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
    }
}
