package org.chromium.ui.gfx;

import J.N;
import android.content.res.Resources;
import android.os.StrictMode;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0299Qm;
import defpackage.ComponentCallbacksC1106lO;
import defpackage.Is;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewConfigurationHelper {
    public ViewConfiguration a;
    public float b;

    public ViewConfigurationHelper() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
        try {
            this.a = ViewConfiguration.get(Is.a);
            if (vmPolicy != null) {
                StrictMode.setVmPolicy(vmPolicy);
            }
            this.b = Is.a.getResources().getDisplayMetrics().density;
        } catch (Throwable th) {
            if (vmPolicy != null) {
                try {
                    StrictMode.setVmPolicy(vmPolicy);
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    public static void a(ViewConfigurationHelper viewConfigurationHelper) {
        Objects.requireNonNull(viewConfigurationHelper);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(Is.a);
        if (viewConfigurationHelper.a == viewConfiguration) {
            return;
        }
        viewConfigurationHelper.a = viewConfiguration;
        viewConfigurationHelper.b = Is.a.getResources().getDisplayMetrics().density;
        N.MtrEpb2R(viewConfigurationHelper, viewConfigurationHelper.getMaximumFlingVelocity(), viewConfigurationHelper.getMinimumFlingVelocity(), viewConfigurationHelper.getTouchSlop(), viewConfigurationHelper.getDoubleTapSlop(), viewConfigurationHelper.getMinScalingSpan());
    }

    public static ViewConfigurationHelper createWithListener() {
        ViewConfigurationHelper viewConfigurationHelper = new ViewConfigurationHelper();
        Is.a.registerComponentCallbacks(new ComponentCallbacksC1106lO(viewConfigurationHelper));
        return viewConfigurationHelper;
    }

    private float getDoubleTapSlop() {
        return b(this.a.getScaledDoubleTapSlop());
    }

    public static int getDoubleTapTimeout() {
        return ViewConfiguration.getDoubleTapTimeout();
    }

    public static int getLongPressTimeout() {
        return ViewConfiguration.getLongPressTimeout();
    }

    private float getMaximumFlingVelocity() {
        return b(this.a.getScaledMaximumFlingVelocity());
    }

    private float getMinScalingSpan() {
        int iApplyDimension;
        Resources resources = Is.a.getResources();
        try {
            iApplyDimension = resources.getDimensionPixelSize(AbstractC0299Qm.o1);
        } catch (Resources.NotFoundException unused) {
            iApplyDimension = (int) TypedValue.applyDimension(5, 12.0f, resources.getDisplayMetrics());
        }
        return b(iApplyDimension);
    }

    private float getMinimumFlingVelocity() {
        return b(this.a.getScaledMinimumFlingVelocity());
    }

    public static int getTapTimeout() {
        return ViewConfiguration.getTapTimeout();
    }

    private float getTouchSlop() {
        return b(this.a.getScaledTouchSlop());
    }

    public final float b(int i) {
        return i / this.b;
    }
}
