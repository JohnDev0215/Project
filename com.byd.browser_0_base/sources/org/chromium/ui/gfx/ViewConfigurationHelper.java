package org.chromium.ui.gfx;

import android.content.res.Resources;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import defpackage.pm3;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.R$dimen;

/* JADX INFO: loaded from: classes3.dex */
public class ViewConfigurationHelper {
    public ViewConfiguration a;
    public float b;

    public ViewConfigurationHelper() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
        try {
            this.a = ViewConfiguration.get(xz.a);
            if (vmPolicy != null) {
                StrictMode.setVmPolicy(vmPolicy);
            }
            this.b = xz.a.getResources().getDisplayMetrics().density;
        } catch (Throwable th) {
            if (vmPolicy != null) {
                try {
                    StrictMode.setVmPolicy(vmPolicy);
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @CalledByNative
    public static ViewConfigurationHelper createWithListener() {
        ViewConfigurationHelper viewConfigurationHelper = new ViewConfigurationHelper();
        xz.a.registerComponentCallbacks(new pm3(viewConfigurationHelper));
        return viewConfigurationHelper;
    }

    @CalledByNative
    public static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    @CalledByNative
    public static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    @CalledByNative
    public static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    @CalledByNative
    public final float getDoubleTapSlop() {
        return this.a.getScaledDoubleTapSlop() / this.b;
    }

    @CalledByNative
    public final float getMaximumFlingVelocity() {
        return this.a.getScaledMaximumFlingVelocity() / this.b;
    }

    @CalledByNative
    public final float getMinScalingSpan() {
        int iApplyDimension;
        Resources resources = xz.a.getResources();
        try {
            iApplyDimension = resources.getDimensionPixelSize(R$dimen.config_min_scaling_span);
        } catch (Resources.NotFoundException unused) {
            iApplyDimension = (int) TypedValue.applyDimension(5, 12.0f, resources.getDisplayMetrics());
        }
        return iApplyDimension / this.b;
    }

    @CalledByNative
    public final float getMinimumFlingVelocity() {
        return this.a.getScaledMinimumFlingVelocity() / this.b;
    }

    @CalledByNative
    public final float getTouchSlop() {
        return this.a.getScaledTouchSlop() / this.b;
    }
}
