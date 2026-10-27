package defpackage;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.device.gamepad.GamepadList;

/* JADX INFO: compiled from: Gamepad.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cs0 implements jt3, lk3 {
    public final Context e;

    /* JADX INFO: compiled from: Gamepad.java */
    public static final class a {
        public static final qh1 a = new qh1(19);
    }

    public cs0(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl.j0();
        kt3.e(webContentsImpl).b(this);
    }

    @Override // defpackage.jt3
    public final void onAttachedToWindow() {
        Context context = this.e;
        GamepadList gamepadList = GamepadList.b.a;
        int i = gamepadList.d;
        gamepadList.d = i + 1;
        if (i == 0) {
            gamepadList.c = (InputManager) context.getSystemService("input");
            synchronized (gamepadList.a) {
                for (int i2 : gamepadList.c.getInputDeviceIds()) {
                    InputDevice device = InputDevice.getDevice(i2);
                    if (GamepadList.b(device)) {
                        gamepadList.d(device);
                    }
                }
            }
            gamepadList.c.registerInputDeviceListener(gamepadList.f, null);
        }
    }

    @Override // defpackage.jt3
    public final void onDetachedFromWindow() {
        GamepadList gamepadList = GamepadList.b.a;
        int i = gamepadList.d - 1;
        gamepadList.d = i;
        if (i == 0) {
            synchronized (gamepadList.a) {
                for (int i2 = 0; i2 < 4; i2++) {
                    gamepadList.b[i2] = null;
                }
            }
            gamepadList.c.unregisterInputDeviceListener(gamepadList.f);
            gamepadList.c = null;
        }
    }
}
