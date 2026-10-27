package org.chromium.device.gamepad;

import J.N;
import android.hardware.input.InputManager;
import android.os.CombinedVibration;
import android.os.VibrationEffect;
import android.view.InputDevice;
import android.view.KeyEvent;
import defpackage.ds0;
import defpackage.es0;
import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class GamepadList {
    public InputManager c;
    public int d;
    public boolean e;
    public final Object a = new Object();
    public final ds0[] b = new ds0[4];
    public final a f = new a();

    public class a implements InputManager.InputDeviceListener {
        public a() {
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceAdded(int i) {
            GamepadList gamepadList = GamepadList.this;
            gamepadList.getClass();
            InputDevice device = InputDevice.getDevice(i);
            if (GamepadList.b(device)) {
                synchronized (gamepadList.a) {
                    gamepadList.d(device);
                }
            }
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceChanged(int i) {
            GamepadList gamepadList = GamepadList.this;
            gamepadList.getClass();
            InputDevice device = InputDevice.getDevice(i);
            if (GamepadList.b(device)) {
                synchronized (gamepadList.a) {
                    ds0 ds0VarA = gamepadList.a(device.getId());
                    if (ds0VarA != null) {
                        gamepadList.b[ds0VarA.b] = null;
                    }
                    gamepadList.d(device);
                }
            }
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public final void onInputDeviceRemoved(int i) {
            GamepadList gamepadList = GamepadList.this;
            synchronized (gamepadList.a) {
                ds0 ds0VarA = gamepadList.a(i);
                if (ds0VarA != null) {
                    gamepadList.b[ds0VarA.b] = null;
                }
            }
        }
    }

    public static class b {
        public static final GamepadList a = new GamepadList();
    }

    public static boolean b(InputDevice inputDevice) {
        return (inputDevice == null || Objects.equals(inputDevice.getName(), "uinput-fpc") || (inputDevice.getSources() & 16777232) != 16777232) ? false : true;
    }

    public static boolean c(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 130) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    int scanCode = keyEvent.getScanCode();
                    if (keyCode != 0 || scanCode < 704 || scanCode > 719) {
                        return KeyEvent.isGamepadButton(keyCode);
                    }
                    return true;
            }
        }
        return true;
    }

    @CalledByNative
    public static void setGamepadAPIActive(boolean z) {
        GamepadList gamepadList = b.a;
        synchronized (gamepadList.a) {
            gamepadList.e = z;
            if (z) {
                for (int i = 0; i < 4; i++) {
                    ds0 ds0Var = gamepadList.b[i];
                    if (ds0Var != null) {
                        Arrays.fill(ds0Var.f, 0.0f);
                        Arrays.fill(ds0Var.i, 0.0f);
                        Arrays.fill(ds0Var.g, 0.0f);
                        Arrays.fill(ds0Var.h, 0.0f);
                    }
                }
            }
        }
    }

    @CalledByNative
    public static void setVibration(int i, double d, double d2) {
        ds0 ds0Var;
        GamepadList gamepadList = b.a;
        synchronized (gamepadList.a) {
            ds0Var = gamepadList.b[i];
        }
        ds0Var.getClass();
        int iRound = (int) Math.round(Math.max(0.0d, Math.min(1.0d, d)) * 255.0d);
        int iRound2 = (int) Math.round(Math.max(0.0d, Math.min(1.0d, d2)) * 255.0d);
        if (iRound == 0 && iRound2 == 0) {
            ds0Var.n.cancel();
            return;
        }
        CombinedVibration.ParallelCombination parallelCombinationStartParallel = CombinedVibration.startParallel();
        if (iRound > 0) {
            parallelCombinationStartParallel.addVibrator(0, VibrationEffect.createOneShot(5000L, iRound));
        }
        if (iRound2 > 0) {
            parallelCombinationStartParallel.addVibrator(1, VibrationEffect.createOneShot(5000L, iRound2));
        }
        ds0Var.n.vibrate(parallelCombinationStartParallel.combine());
    }

    @CalledByNative
    public static void setZeroVibration(int i) {
        ds0 ds0Var;
        GamepadList gamepadList = b.a;
        synchronized (gamepadList.a) {
            ds0Var = gamepadList.b[i];
        }
        ds0Var.n.cancel();
    }

    @CalledByNative
    public static void updateGamepadData(long j) throws Throwable {
        Object obj;
        Throwable th;
        int i;
        GamepadList gamepadList = b.a;
        Object obj2 = gamepadList.a;
        synchronized (obj2) {
            int i2 = 0;
            while (i2 < 4) {
                try {
                    ds0 ds0Var = gamepadList.b[i2];
                    if (ds0Var != null) {
                        ds0Var.l.j(ds0Var.f, ds0Var.g, ds0Var.i, ds0Var.h);
                        ds0Var.l.getClass();
                        i = i2;
                        obj = obj2;
                        try {
                            N.MOkngxPY(gamepadList, j, i2, !(r2 instanceof es0.g), true, ds0Var.j, ds0Var.c, ds0Var.d, ds0Var.e, ds0Var.f, ds0Var.g, ds0Var.l.i(), ds0Var.m);
                        } catch (Throwable th2) {
                            th = th2;
                            while (true) {
                                try {
                                    throw th;
                                } catch (Throwable th3) {
                                    th = th3;
                                }
                            }
                        }
                    } else {
                        i = i2;
                        obj = obj2;
                        N.MOkngxPY(gamepadList, j, i, false, false, null, 0, 0, 0L, null, null, 0, false);
                    }
                    i2 = i + 1;
                    obj2 = obj;
                } catch (Throwable th4) {
                    th = th4;
                    obj = obj2;
                    while (true) {
                        throw th;
                    }
                }
            }
            obj = obj2;
        }
    }

    public final ds0 a(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            ds0 ds0Var = this.b[i2];
            if (ds0Var != null && ds0Var.a == i) {
                return ds0Var;
            }
        }
        return null;
    }

    public final void d(InputDevice inputDevice) {
        ds0[] ds0VarArr;
        int i = 0;
        while (true) {
            ds0VarArr = this.b;
            if (i >= 4) {
                i = -1;
                break;
            } else if (ds0VarArr[i] == null) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        ds0VarArr[i] = new ds0(i, inputDevice);
    }
}
