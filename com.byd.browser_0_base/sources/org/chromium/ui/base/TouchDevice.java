package org.chromium.ui.base;

import android.view.InputDevice;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class TouchDevice {
    public static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    @CalledByNative
    public static int[] availablePointerAndHoverTypes() {
        InputDevice device;
        int[] iArr = {0, 0};
        for (int i : InputDevice.getDeviceIds()) {
            try {
                device = InputDevice.getDevice(i);
            } catch (RuntimeException unused) {
                device = null;
            }
            if (device != null) {
                int sources = device.getSources();
                if (a(sources, 8194) || a(sources, 16386) || a(sources, 1048584) || a(sources, 65540)) {
                    iArr[0] = iArr[0] | 4;
                } else if (a(sources, 4098)) {
                    iArr[0] = iArr[0] | 2;
                }
                if (a(sources, 8194) || a(sources, 1048584) || a(sources, 65540)) {
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

    @CalledByNative
    public static int maxTouchPoints() {
        if (xz.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }
        if (xz.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") || xz.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return 2;
        }
        return xz.a.getPackageManager().hasSystemFeature("android.hardware.touchscreen") ? 1 : 0;
    }
}
