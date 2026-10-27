package com.byd.music.view.cubeRotate;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;

/* JADX INFO: loaded from: classes5.dex */
public class CubeInAnimation extends Animation {
    private static final int sFinalDegree = 90;
    private Camera mCamera;
    private int mHeight;
    private Matrix mMatrix;
    private int mWidth;
    private int outDirect;

    public CubeInAnimation(int i) {
        this.outDirect = i;
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.mCamera = new Camera();
        this.mMatrix = new Matrix();
        this.mWidth = i;
        this.mHeight = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ca  */
    @Override // android.view.animation.Animation
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void applyTransformation(float r9, android.view.animation.Transformation r10) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.cubeRotate.CubeInAnimation.applyTransformation(float, android.view.animation.Transformation):void");
    }
}
