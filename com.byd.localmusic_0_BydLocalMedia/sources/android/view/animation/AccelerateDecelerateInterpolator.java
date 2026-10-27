package android.view.animation;

import android.content.Context;
import android.graphics.animation.HasNativeInterpolator;
import android.graphics.animation.NativeInterpolator;
import android.graphics.animation.NativeInterpolatorFactory;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes3.dex */
@HasNativeInterpolator
public class AccelerateDecelerateInterpolator extends BaseInterpolator implements NativeInterpolator {
    public AccelerateDecelerateInterpolator() {
    }

    public AccelerateDecelerateInterpolator(Context context, AttributeSet attributeSet) {
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return ((float) (Math.cos(((double) (f + 1.0f)) * 3.141592653589793d) / 2.0d)) + 0.5f;
    }

    @Override // android.graphics.animation.NativeInterpolator
    public long createNativeInterpolator() {
        return NativeInterpolatorFactory.createAccelerateDecelerateInterpolator();
    }
}
