package org.chromium.device.gamepad;

import J.N;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import android.view.KeyEvent;
import defpackage.KE;
import defpackage.LE;
import defpackage.ME;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GamepadList {
    public InputManager c;
    public int d;
    public boolean e;
    public final Object a = new Object();
    public final KE[] b = new KE[4];
    public InputManager.InputDeviceListener f = new LE(this);

    public GamepadList(LE le) {
    }

    public static boolean a(KeyEvent keyEvent) {
        boolean zIsGamepadButton;
        boolean zIsGamepadButton2;
        int keyCode = keyEvent.getKeyCode();
        boolean z = true;
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
                zIsGamepadButton = true;
                break;
            default:
                zIsGamepadButton = KeyEvent.isGamepadButton(keyCode);
                break;
        }
        if (!zIsGamepadButton) {
            return false;
        }
        GamepadList gamepadList = ME.a;
        synchronized (gamepadList.a) {
            if (!gamepadList.e) {
                return false;
            }
            KE keB = gamepadList.b(keyEvent.getDeviceId());
            if (keB == null) {
                return false;
            }
            int keyCode2 = keyEvent.getKeyCode();
            switch (keyCode2) {
                case 19:
                case 20:
                case 21:
                case 22:
                    zIsGamepadButton2 = true;
                    break;
                default:
                    zIsGamepadButton2 = KeyEvent.isGamepadButton(keyCode2);
                    break;
            }
            if (zIsGamepadButton2) {
                int keyCode3 = keyEvent.getKeyCode();
                if (keyEvent.getAction() == 0) {
                    keB.f[keyCode3] = 1.0f;
                } else if (keyEvent.getAction() == 1) {
                    keB.f[keyCode3] = 0.0f;
                }
                keB.c = keyEvent.getEventTime();
            } else {
                z = false;
            }
            return z;
        }
    }

    public static void setGamepadAPIActive(boolean z) {
        GamepadList gamepadList = ME.a;
        synchronized (gamepadList.a) {
            gamepadList.e = z;
            if (z) {
                for (int i = 0; i < 4; i++) {
                    KE ke = gamepadList.b[i];
                    if (ke != null) {
                        Arrays.fill(ke.d, 0.0f);
                        Arrays.fill(ke.g, 0.0f);
                        Arrays.fill(ke.e, 0.0f);
                        Arrays.fill(ke.f, 0.0f);
                    }
                }
            }
        }
    }

    public static void updateGamepadData(long j) {
        GamepadList gamepadList = ME.a;
        synchronized (gamepadList.a) {
            for (int i = 0; i < 4; i++) {
                KE ke = gamepadList.b[i];
                if (ke != null) {
                    ke.j.j(ke.d, ke.e, ke.g, ke.f);
                    N.MOkngxPY(gamepadList, j, i, ke.j.i(), true, ke.h, ke.c, ke.d, ke.e);
                } else {
                    N.MOkngxPY(gamepadList, j, i, false, false, null, 0L, null, null);
                }
            }
        }
    }

    public final KE b(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            KE ke = this.b[i2];
            if (ke != null && ke.a == i) {
                return ke;
            }
        }
        return null;
    }

    public final void c() {
    }

    public final boolean d(InputDevice inputDevice) {
        int i = 0;
        while (true) {
            if (i >= 4) {
                i = -1;
                break;
            }
            if (this.b[i] == null) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return false;
        }
        this.b[i] = new KE(i, inputDevice);
        return true;
    }
}
