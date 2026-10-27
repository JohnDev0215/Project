package org.chromium.ui.events.devices;

import J.N;
import android.hardware.input.InputManager;
import defpackage.Is;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class InputDeviceObserver implements InputManager.InputDeviceListener {
    public static final InputDeviceObserver c = new InputDeviceObserver();
    public InputManager a;
    public int b;

    public static void addObserver() {
        InputDeviceObserver inputDeviceObserver = c;
        int i = inputDeviceObserver.b;
        inputDeviceObserver.b = i + 1;
        if (i == 0) {
            InputManager inputManager = (InputManager) Is.a.getSystemService("input");
            inputDeviceObserver.a = inputManager;
            inputManager.registerInputDeviceListener(inputDeviceObserver, null);
        }
    }

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
    public void onInputDeviceAdded(int i) {
        N.MGCvz8lp(this);
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceChanged(int i) {
        N.MGCvz8lp(this);
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceRemoved(int i) {
        N.MGCvz8lp(this);
    }
}
