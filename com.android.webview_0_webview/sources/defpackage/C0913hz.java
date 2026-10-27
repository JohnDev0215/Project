package defpackage;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.device.gamepad.GamepadList;

/* JADX INFO: renamed from: hz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0913hz extends Xz implements Yz, InterfaceC1074kt {
    public final Context s;

    public C0913hz(WebContents webContents) {
        this.s = ((WebContentsImpl) webContents).R();
        C0590cA c0590cAP = C0590cA.P(webContents);
        c0590cAP.s.f(this);
        if (c0590cAP.v) {
            onAttachedToWindow();
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onAttachedToWindow() {
        Context context = this.s;
        GamepadList gamepadList = ME.a;
        int i = gamepadList.d;
        gamepadList.d = i + 1;
        if (i == 0) {
            gamepadList.c = (InputManager) context.getSystemService("input");
            synchronized (gamepadList.a) {
                for (int i2 : gamepadList.c.getInputDeviceIds()) {
                    InputDevice device = InputDevice.getDevice(i2);
                    if (device != null && (device.getSources() & 16777232) == 16777232) {
                        gamepadList.d(device);
                    }
                }
            }
            gamepadList.c.registerInputDeviceListener(gamepadList.f, null);
        }
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onDetachedFromWindow() {
        GamepadList gamepadList = ME.a;
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
