package com.byd.filter2.g;

import android.content.res.Resources;
import android.opengl.GLES30;

/* JADX INFO: loaded from: classes2.dex */
public class l extends com.byd.filter2.e.b {
    private int u;
    private int v;
    private int w;
    private float x;
    private float y;
    private String z;

    public l(Resources resources) {
        super(resources);
        this.x = 1.0f;
        this.y = 1.0f;
        this.z = "precision mediump float;\nvarying highp vec2 textureCoordinate;\nuniform sampler2D vTexture;\nuniform float texelWidthOffset;\nuniform float texelHeightOffset;\nuniform float strength;\nconst highp vec3 W = vec3(0.299,0.587,0.114);\nvoid main()\n{\n    float threshold = 0.0;\n    vec4 sourceColor = texture2D(vTexture, textureCoordinate);\n    vec3 maxValue = vec3(0.0, 0.0, 0.0);\n    vec2 singleStepOffset = vec2(texelWidthOffset, texelHeightOffset);\n    for(int i = -2; i<=2; i++) {\n        for(int j = -2; j<=2; j++) {\n            vec4 tempColor = texture2D(vTexture,\n                                       textureCoordinate + singleStepOffset * vec2(i, j));\n            maxValue.r = max(maxValue.r, tempColor.r);\n            maxValue.g = max(maxValue.g, tempColor.g);\n            maxValue.b = max(maxValue.b, tempColor.b);\n            threshold += dot(tempColor.rgb, W);\n        }\n    }\n    float gray1 = dot(sourceColor.rgb, W);\n    float gray2 = dot(maxValue, W);\n    float contour = gray1 / gray2;\n    threshold = threshold / 25.;\n    float alpha = max(1.0,gray1>threshold?1.0:(gray1/threshold));\n    float result = contour * alpha + (1.0-alpha)*gray1;\n    gl_FragColor = mix(sourceColor, vec4(vec3(result, result, result), sourceColor.w), strength);\n}";
    }

    @Override // com.byd.filter2.e.b
    protected void o() {
        super.o();
        GLES30.glUniform1f(this.w, 1.0f);
        GLES30.glUniform1f(this.u, this.x);
        GLES30.glUniform1f(this.v, this.y);
    }

    @Override // com.byd.filter2.e.b
    protected void q() {
        b("attribute vec4 vPosition;\nattribute vec2 vCoord;\nuniform mat4 vMatrix;\nvarying vec2 textureCoordinate;\nvoid main(){\n    gl_Position = vMatrix*vPosition;\n    textureCoordinate = vCoord;\n}", this.z);
        this.u = GLES30.glGetUniformLocation(this.f2824c, "texelWidthOffset");
        this.v = GLES30.glGetUniformLocation(this.f2824c, "texelHeightOffset");
        this.w = GLES30.glGetUniformLocation(this.f2824c, "strength");
    }

    @Override // com.byd.filter2.e.b
    protected void t(int i, int i2) {
        this.x = 1.0f / i;
        this.y = 1.0f / i2;
    }
}
