package org.chromium.ui.base;

import android.content.Context;
import android.view.InputDevice;
import androidx.core.view.InputDeviceCompat;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class TouchDevice {
    private static boolean hasSource(int i, int i2) {
        return (i & i2) == i2;
    }

    private TouchDevice() {
    }

    private static int maxTouchPoints(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand")) {
            return 5;
        }
        if (context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") || context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch")) {
            return 2;
        }
        return context.getPackageManager().hasSystemFeature("android.hardware.touchscreen") ? 1 : 0;
    }

    private static int availablePointerTypes(Context context) {
        int i = 0;
        for (int i2 : InputDevice.getDeviceIds()) {
            InputDevice device = InputDevice.getDevice(i2);
            if (device != null) {
                int sources = device.getSources();
                if (hasSource(sources, 8194) || hasSource(sources, InputDeviceCompat.SOURCE_STYLUS) || hasSource(sources, InputDeviceCompat.SOURCE_TOUCHPAD) || hasSource(sources, InputDeviceCompat.SOURCE_TRACKBALL)) {
                    i |= 4;
                } else if (hasSource(sources, 4098)) {
                    i |= 2;
                }
            }
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    private static int availableHoverTypes(Context context) {
        int i = 0;
        for (int i2 : InputDevice.getDeviceIds()) {
            InputDevice device = InputDevice.getDevice(i2);
            if (device != null) {
                int sources = device.getSources();
                if (hasSource(sources, 8194) || hasSource(sources, InputDeviceCompat.SOURCE_TOUCHPAD) || hasSource(sources, InputDeviceCompat.SOURCE_TRACKBALL)) {
                    i |= 4;
                } else if (hasSource(sources, InputDeviceCompat.SOURCE_STYLUS) || hasSource(sources, 4098)) {
                    i |= 2;
                }
            }
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }
}
