package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes3.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(@NonNull Canvas canvas, @Nullable RectF rectF, int i) {
        return Build.VERSION.SDK_INT > 21 ? canvas.saveLayerAlpha(rectF, i) : canvas.saveLayerAlpha(rectF, i, 31);
    }

    public static int saveLayerAlpha(@NonNull Canvas canvas, float f2, float f3, float f4, float f5, int i) {
        if (Build.VERSION.SDK_INT > 21) {
            return canvas.saveLayerAlpha(f2, f3, f4, f5, i);
        }
        return canvas.saveLayerAlpha(f2, f3, f4, f5, i, 31);
    }
}
