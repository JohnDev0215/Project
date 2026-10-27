package defpackage;

import android.hardware.input.InputManager;
import android.view.InputDevice;
import java.util.Objects;
import org.chromium.device.gamepad.GamepadList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LE implements InputManager.InputDeviceListener {
    public final /* synthetic */ GamepadList a;

    public LE(GamepadList gamepadList) {
        this.a = gamepadList;
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceAdded(int i) {
        GamepadList gamepadList = this.a;
        Objects.requireNonNull(gamepadList);
        InputDevice device = InputDevice.getDevice(i);
        boolean z = false;
        if (device != null && (device.getSources() & 16777232) == 16777232) {
            z = true;
        }
        if (z) {
            synchronized (gamepadList.a) {
                gamepadList.d(device);
            }
        }
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceChanged(int i) {
        this.a.c();
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceRemoved(int i) {
        GamepadList gamepadList = this.a;
        synchronized (gamepadList.a) {
            KE keB = gamepadList.b(i);
            if (keB != null) {
                gamepadList.b[keB.b] = null;
            }
        }
    }
}
