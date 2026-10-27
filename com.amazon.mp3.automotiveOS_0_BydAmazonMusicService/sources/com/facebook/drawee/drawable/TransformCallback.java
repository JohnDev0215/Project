package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes5.dex */
public interface TransformCallback {
    void getRootBounds(RectF bounds);

    void getTransform(Matrix transform);
}
