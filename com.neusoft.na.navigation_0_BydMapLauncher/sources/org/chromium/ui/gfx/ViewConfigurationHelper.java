package org.chromium.ui.gfx;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("gfx")
public class ViewConfigurationHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final float MIN_SCALING_SPAN_MM = 12.0f;
    private final Context mAppContext;
    private float mDensity;
    private ViewConfiguration mViewConfiguration;

    private native void nativeUpdateSharedViewConfiguration(float f, float f2, float f3, float f4, float f5);

    private ViewConfigurationHelper(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mViewConfiguration = ViewConfiguration.get(applicationContext);
        this.mDensity = applicationContext.getResources().getDisplayMetrics().density;
    }

    private void registerListener() {
        this.mAppContext.registerComponentCallbacks(new ComponentCallbacks() { // from class: org.chromium.ui.gfx.ViewConfigurationHelper.1
            @Override // android.content.ComponentCallbacks
            public void onLowMemory() {
            }

            @Override // android.content.ComponentCallbacks
            public void onConfigurationChanged(Configuration configuration) {
                ViewConfigurationHelper.this.updateNativeViewConfigurationIfNecessary();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNativeViewConfigurationIfNecessary() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mAppContext);
        if (this.mViewConfiguration == viewConfiguration) {
            return;
        }
        this.mViewConfiguration = viewConfiguration;
        this.mDensity = this.mAppContext.getResources().getDisplayMetrics().density;
        nativeUpdateSharedViewConfiguration(getMaximumFlingVelocity(), getMinimumFlingVelocity(), getTouchSlop(), getDoubleTapSlop(), getMinScalingSpan());
    }

    private static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    private static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    private static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    private static float getScrollFriction() {
        return ViewConfiguration.getScrollFriction();
    }

    private float getMaximumFlingVelocity() {
        return toDips(this.mViewConfiguration.getScaledMaximumFlingVelocity());
    }

    private float getMinimumFlingVelocity() {
        return toDips(this.mViewConfiguration.getScaledMinimumFlingVelocity());
    }

    private float getTouchSlop() {
        return toDips(this.mViewConfiguration.getScaledTouchSlop());
    }

    private float getDoubleTapSlop() {
        return toDips(this.mViewConfiguration.getScaledDoubleTapSlop());
    }

    private float getMinScalingSpan() {
        return toDips(getScaledMinScalingSpan());
    }

    private int getScaledMinScalingSpan() {
        Resources resources = this.mAppContext.getResources();
        try {
            return resources.getDimensionPixelSize(R.dimen.config_min_scaling_span);
        } catch (Resources.NotFoundException unused) {
            return (int) TypedValue.applyDimension(5, MIN_SCALING_SPAN_MM, resources.getDisplayMetrics());
        }
    }

    private float toDips(int i) {
        return i / this.mDensity;
    }

    private static ViewConfigurationHelper createWithListener(Context context) {
        ViewConfigurationHelper viewConfigurationHelper = new ViewConfigurationHelper(context);
        viewConfigurationHelper.registerListener();
        return viewConfigurationHelper;
    }
}
