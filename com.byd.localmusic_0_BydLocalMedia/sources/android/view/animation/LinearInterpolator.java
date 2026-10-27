package android.view.animation;

import android.content.Context;
import android.graphics.animation.HasNativeInterpolator;
import android.graphics.animation.NativeInterpolator;
import android.graphics.animation.NativeInterpolatorFactory;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes3.dex */
@HasNativeInterpolator
public class LinearInterpolator extends BaseInterpolator implements NativeInterpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return f;
    }

    public LinearInterpolator() {
    }

    public LinearInterpolator(Context context, AttributeSet attributeSet) {
    }

    @Override // android.graphics.animation.NativeInterpolator
    public long createNativeInterpolator() {
        return NativeInterpolatorFactory.createLinearInterpolator();
    }
}
