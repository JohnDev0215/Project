package org.chromium.device.power_save_blocker;

import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
class PowerSaveBlocker {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private WeakReference<View> mKeepScreenOnView;

    private static PowerSaveBlocker create() {
        return new PowerSaveBlocker();
    }

    private PowerSaveBlocker() {
    }

    private void applyBlock(ViewAndroidDelegate viewAndroidDelegate) {
        View viewAcquireAnchorView = viewAndroidDelegate.acquireAnchorView();
        this.mKeepScreenOnView = new WeakReference<>(viewAcquireAnchorView);
        viewAndroidDelegate.setAnchorViewPosition(viewAcquireAnchorView, 0.0f, 0.0f, 0.0f, 0.0f);
        viewAcquireAnchorView.setKeepScreenOn(true);
    }

    private void removeBlock(ViewAndroidDelegate viewAndroidDelegate) {
        View view = this.mKeepScreenOnView.get();
        this.mKeepScreenOnView = null;
        if (view == null) {
            return;
        }
        view.setKeepScreenOn(false);
        viewAndroidDelegate.releaseAnchorView(view);
    }
}
