package org.chromium.device.gamepad;

import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class GamepadDevice {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_RAW_AXIS_VALUES = 256;
    static final int MAX_RAW_BUTTON_VALUES = 256;
    private int[] mAxes;
    private int mDeviceId;
    private int mDeviceIndex;
    private String mDeviceName;
    private boolean mIsStandardGamepad;
    private final float[] mAxisValues = new float[4];
    private final float[] mButtonsValues = new float[17];
    private final float[] mRawButtons = new float[256];
    private final float[] mRawAxes = new float[256];
    private long mTimestamp = SystemClock.uptimeMillis();

    GamepadDevice(int i, InputDevice inputDevice) {
        this.mDeviceIndex = i;
        this.mDeviceId = inputDevice.getId();
        this.mDeviceName = inputDevice.getName();
        List<InputDevice.MotionRange> motionRanges = inputDevice.getMotionRanges();
        this.mAxes = new int[motionRanges.size()];
        int i2 = 0;
        for (InputDevice.MotionRange motionRange : motionRanges) {
            if ((motionRange.getSource() & 16) != 0) {
                this.mAxes[i2] = motionRange.getAxis();
                i2++;
            }
        }
    }

    public void updateButtonsAndAxesMapping() {
        this.mIsStandardGamepad = GamepadMappings.mapToStandardGamepad(this.mAxisValues, this.mButtonsValues, this.mRawAxes, this.mRawButtons, this.mDeviceName);
    }

    public int getId() {
        return this.mDeviceId;
    }

    public boolean isStandardGamepad() {
        return this.mIsStandardGamepad;
    }

    public String getName() {
        return this.mDeviceName;
    }

    public int getIndex() {
        return this.mDeviceIndex;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public float[] getAxes() {
        return this.mAxisValues;
    }

    public float[] getButtons() {
        return this.mButtonsValues;
    }

    public void clearData() {
        Arrays.fill(this.mAxisValues, 0.0f);
        Arrays.fill(this.mRawAxes, 0.0f);
        Arrays.fill(this.mButtonsValues, 0.0f);
        Arrays.fill(this.mRawButtons, 0.0f);
    }

    public boolean handleKeyEvent(KeyEvent keyEvent) {
        if (!GamepadList.isGamepadEvent(keyEvent)) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            this.mRawButtons[keyCode] = 1.0f;
        } else if (keyEvent.getAction() == 1) {
            this.mRawButtons[keyCode] = 0.0f;
        }
        this.mTimestamp = keyEvent.getEventTime();
        return true;
    }

    public boolean handleMotionEvent(MotionEvent motionEvent) {
        int i = 0;
        if (!GamepadList.isGamepadEvent(motionEvent)) {
            return false;
        }
        while (true) {
            int[] iArr = this.mAxes;
            if (i < iArr.length) {
                int i2 = iArr[i];
                this.mRawAxes[i2] = motionEvent.getAxisValue(i2);
                i++;
            } else {
                this.mTimestamp = motionEvent.getEventTime();
                return true;
            }
        }
    }
}
