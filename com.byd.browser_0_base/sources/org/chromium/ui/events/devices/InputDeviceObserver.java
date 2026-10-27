package org.chromium.ui.events.devices;

import J.N;
import android.hardware.input.InputManager;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class InputDeviceObserver implements InputManager.InputDeviceListener {
    public static final InputDeviceObserver c = new InputDeviceObserver();
    public InputManager a;
    public int b;

    @CalledByNative
    public static void addObserver() {
        InputDeviceObserver inputDeviceObserver = c;
        int i = inputDeviceObserver.b;
        inputDeviceObserver.b = i + 1;
        if (i == 0) {
            InputManager inputManager = (InputManager) xz.a.getSystemService("input");
            inputDeviceObserver.a = inputManager;
            inputManager.registerInputDeviceListener(inputDeviceObserver, null);
        }
    }

    @CalledByNative
    public static void removeObserver() {
        InputDeviceObserver inputDeviceObserver = c;
        int i = inputDeviceObserver.b - 1;
        inputDeviceObserver.b = i;
        if (i == 0) {
            inputDeviceObserver.a.unregisterInputDeviceListener(inputDeviceObserver);
            inputDeviceObserver.a = null;
        }
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceAdded(int i) {
        N.MGCvz8lp(this);
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceChanged(int i) {
        N.MGCvz8lp(this);
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public final void onInputDeviceRemoved(int i) {
        N.MGCvz8lp(this);
    }
}
