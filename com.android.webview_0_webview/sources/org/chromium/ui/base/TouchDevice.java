package org.chromium.ui.base;

import android.view.InputDevice;
import com.android.webview.R;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TouchDevice {
    public static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    public static int[] availablePointerAndHoverTypes() {
        int[] iArr = {0, 0};
        for (int i : InputDevice.getDeviceIds()) {
            InputDevice device = null;
            try {
                device = InputDevice.getDevice(i);
            } catch (RuntimeException unused) {
            }
            if (device != null) {
                int sources = device.getSources();
                if (a(sources, 8194) || a(sources, 16386) || a(sources, 1048584) || a(sources, R.anim.abc_popup_exit)) {
                    iArr[0] = iArr[0] | 4;
                } else if (a(sources, 4098)) {
                    iArr[0] = iArr[0] | 2;
                }
                if (a(sources, 8194) || a(sources, 1048584) || a(sources, R.anim.abc_popup_exit)) {
                    iArr[1] = iArr[1] | 2;
                }
            }
        }
        if (iArr[0] == 0) {
            iArr[0] = 1;
        }
        if (iArr[1] == 0) {
            iArr[1] = 1;
        }
        return iArr;
    }

    public static int maxTouchPoints() {
        if (Is.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }
        if (Is.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") || Is.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return 2;
        }
        return Is.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen") ? 1 : 0;
    }
}
