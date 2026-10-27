package org.chromium.device.gamepad;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.core.view.InputDeviceCompat;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("device")
public class GamepadList {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_GAMEPADS = 4;
    private int mAttachedToWindowCounter;
    private final GamepadDevice[] mGamepadDevices;
    private InputManager.InputDeviceListener mInputDeviceListener;
    private InputManager mInputManager;
    private boolean mIsGamepadAPIActive;
    private final Object mLock;

    private native void nativeSetGamepadData(long j, int i, boolean z, boolean z2, String str, long j2, float[] fArr, float[] fArr2);

    /* JADX INFO: Access modifiers changed from: private */
    public void onInputDeviceChangedImpl(int i) {
    }

    private GamepadList() {
        this.mLock = new Object();
        this.mGamepadDevices = new GamepadDevice[4];
        this.mInputDeviceListener = new InputManager.InputDeviceListener() { // from class: org.chromium.device.gamepad.GamepadList.1
            @Override // android.hardware.input.InputManager.InputDeviceListener
            public void onInputDeviceChanged(int i) {
                GamepadList.this.onInputDeviceChangedImpl(i);
            }

            @Override // android.hardware.input.InputManager.InputDeviceListener
            public void onInputDeviceRemoved(int i) {
                GamepadList.this.onInputDeviceRemovedImpl(i);
            }

            @Override // android.hardware.input.InputManager.InputDeviceListener
            public void onInputDeviceAdded(int i) {
                GamepadList.this.onInputDeviceAddedImpl(i);
            }
        };
    }

    private void initializeDevices() {
        for (int i : this.mInputManager.getInputDeviceIds()) {
            InputDevice device = InputDevice.getDevice(i);
            if (isGamepadDevice(device)) {
                registerGamepad(device);
            }
        }
    }

    public static void onAttachedToWindow(Context context) {
        getInstance().attachedToWindow(context);
    }

    private void attachedToWindow(Context context) {
        int i = this.mAttachedToWindowCounter;
        this.mAttachedToWindowCounter = i + 1;
        if (i == 0) {
            this.mInputManager = (InputManager) context.getSystemService("input");
            synchronized (this.mLock) {
                initializeDevices();
            }
            this.mInputManager.registerInputDeviceListener(this.mInputDeviceListener, null);
        }
    }

    public static void onDetachedFromWindow() {
        getInstance().detachedFromWindow();
    }

    private void detachedFromWindow() {
        int i = this.mAttachedToWindowCounter - 1;
        this.mAttachedToWindowCounter = i;
        if (i == 0) {
            synchronized (this.mLock) {
                for (int i2 = 0; i2 < 4; i2++) {
                    this.mGamepadDevices[i2] = null;
                }
            }
            this.mInputManager.unregisterInputDeviceListener(this.mInputDeviceListener);
            this.mInputManager = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInputDeviceRemovedImpl(int i) {
        synchronized (this.mLock) {
            unregisterGamepad(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onInputDeviceAddedImpl(int i) {
        InputDevice device = InputDevice.getDevice(i);
        if (isGamepadDevice(device)) {
            synchronized (this.mLock) {
                registerGamepad(device);
            }
        }
    }

    private static GamepadList getInstance() {
        return LazyHolder.INSTANCE;
    }

    private int getDeviceCount() {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            if (getDevice(i2) != null) {
                i++;
            }
        }
        return i;
    }

    private boolean isDeviceConnected(int i) {
        return i < 4 && getDevice(i) != null;
    }

    private GamepadDevice getDeviceById(int i) {
        for (int i2 = 0; i2 < 4; i2++) {
            GamepadDevice gamepadDevice = this.mGamepadDevices[i2];
            if (gamepadDevice != null && gamepadDevice.getId() == i) {
                return gamepadDevice;
            }
        }
        return null;
    }

    private GamepadDevice getDevice(int i) {
        return this.mGamepadDevices[i];
    }

    public static boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (isGamepadEvent(keyEvent)) {
            return getInstance().handleKeyEvent(keyEvent);
        }
        return false;
    }

    private boolean handleKeyEvent(KeyEvent keyEvent) {
        synchronized (this.mLock) {
            if (!this.mIsGamepadAPIActive) {
                return false;
            }
            GamepadDevice gamepadForEvent = getGamepadForEvent(keyEvent);
            if (gamepadForEvent == null) {
                return false;
            }
            return gamepadForEvent.handleKeyEvent(keyEvent);
        }
    }

    public static boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (isGamepadEvent(motionEvent)) {
            return getInstance().handleMotionEvent(motionEvent);
        }
        return false;
    }

    private boolean handleMotionEvent(MotionEvent motionEvent) {
        synchronized (this.mLock) {
            if (!this.mIsGamepadAPIActive) {
                return false;
            }
            GamepadDevice gamepadForEvent = getGamepadForEvent(motionEvent);
            if (gamepadForEvent == null) {
                return false;
            }
            return gamepadForEvent.handleMotionEvent(motionEvent);
        }
    }

    private int getNextAvailableIndex() {
        for (int i = 0; i < 4; i++) {
            if (getDevice(i) == null) {
                return i;
            }
        }
        return -1;
    }

    private boolean registerGamepad(InputDevice inputDevice) {
        int nextAvailableIndex = getNextAvailableIndex();
        if (nextAvailableIndex == -1) {
            return false;
        }
        this.mGamepadDevices[nextAvailableIndex] = new GamepadDevice(nextAvailableIndex, inputDevice);
        return true;
    }

    private void unregisterGamepad(int i) {
        GamepadDevice deviceById = getDeviceById(i);
        if (deviceById == null) {
            return;
        }
        this.mGamepadDevices[deviceById.getIndex()] = null;
    }

    private static boolean isGamepadDevice(InputDevice inputDevice) {
        return inputDevice != null && (inputDevice.getSources() & InputDeviceCompat.SOURCE_JOYSTICK) == 16777232;
    }

    private GamepadDevice getGamepadForEvent(InputEvent inputEvent) {
        return getDeviceById(inputEvent.getDeviceId());
    }

    public static boolean isGamepadAPIActive() {
        return getInstance().mIsGamepadAPIActive;
    }

    public static boolean isGamepadEvent(MotionEvent motionEvent) {
        return (motionEvent.getSource() & InputDeviceCompat.SOURCE_JOYSTICK) == 16777232;
    }

    public static boolean isGamepadEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        switch (keyCode) {
            case 19:
            case 20:
            case 21:
            case 22:
                return true;
            default:
                return KeyEvent.isGamepadButton(keyCode);
        }
    }

    static void updateGamepadData(long j) {
        getInstance().grabGamepadData(j);
    }

    private void grabGamepadData(long j) {
        synchronized (this.mLock) {
            for (int i = 0; i < 4; i++) {
                GamepadDevice device = getDevice(i);
                if (device != null) {
                    device.updateButtonsAndAxesMapping();
                    nativeSetGamepadData(j, i, device.isStandardGamepad(), true, device.getName(), device.getTimestamp(), device.getAxes(), device.getButtons());
                } else {
                    nativeSetGamepadData(j, i, false, false, null, 0L, null, null);
                }
            }
        }
    }

    static void setGamepadAPIActive(boolean z) {
        getInstance().setIsGamepadActive(z);
    }

    private void setIsGamepadActive(boolean z) {
        synchronized (this.mLock) {
            this.mIsGamepadAPIActive = z;
            if (z) {
                for (int i = 0; i < 4; i++) {
                    GamepadDevice device = getDevice(i);
                    if (device != null) {
                        device.clearData();
                    }
                }
            }
        }
    }

    private static class LazyHolder {
        private static final GamepadList INSTANCE = new GamepadList();

        private LazyHolder() {
        }
    }
}
