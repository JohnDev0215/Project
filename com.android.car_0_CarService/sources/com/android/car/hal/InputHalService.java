package com.android.car.hal;

import android.car.input.RotaryEvent;
import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import com.android.car.CarLog;
import com.android.car.CarServiceUtils;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

/* JADX INFO: loaded from: classes3.dex */
public class InputHalService extends HalServiceBase {
    private static final boolean DBG = true;
    public static final int DISPLAY_INSTRUMENT_CLUSTER = 1;
    public static final int DISPLAY_MAIN = 0;
    private static final int KEY_ACTION_EXT_SHORT_PRESS = 3;
    private static final int KEY_CODE_POWER = 501;
    private static final int KEY_CODE_POWER_RESET = 504;
    private static final int KEY_CODE_POWER_ROLL_DOWN = 503;
    private static final int KEY_CODE_POWER_ROLL_UP = 502;
    private static final int KEY_CODE_ROLL_DOWN = 18;
    private static final int KEY_CODE_ROLL_UP = 17;
    private static final int MAX_KEY_REPEAT_COUNT = 3;
    private static final int MIN_KEY_FREQUENCY = 300;
    private static final int[] SUPPORTED_PROPERTIES = {VehicleProperty.HW_KEY_INPUT, VehicleProperty.HW_ROTARY_INPUT};
    private HashMap<Integer, KeyPressInfo> keyPressInfoHashMap;
    private final VehicleHal mHal;
    private boolean mKeyInputSupported;
    private final SparseArray<KeyState> mKeyStates;
    private InputListener mListener;
    private final Object mLock;
    private boolean mRotaryInputSupported;
    private final LongSupplier mUptimeSupplier;

    public interface InputListener {
        void onKeyEvent(KeyEvent keyEvent, int i);

        void onRotaryEvent(RotaryEvent rotaryEvent, int i);
    }

    private static class KeyState {
        public long mLastKeyDownTimestamp;
        public int mRepeatCount;

        private KeyState() {
            this.mLastKeyDownTimestamp = -1L;
            this.mRepeatCount = 0;
        }
    }

    public InputHalService(VehicleHal hal) {
        this(hal, new LongSupplier() { // from class: com.android.car.hal.-$$Lambda$2-WG14V_pUY_jRzlz8ohJ-pqdJA
            @Override // java.util.function.LongSupplier
            public final long getAsLong() {
                return SystemClock.uptimeMillis();
            }
        });
    }

    InputHalService(VehicleHal hal, LongSupplier uptimeSupplier) {
        this.mLock = new Object();
        this.mKeyInputSupported = false;
        this.mRotaryInputSupported = false;
        this.mKeyStates = new SparseArray<>();
        this.keyPressInfoHashMap = new HashMap<>();
        this.mHal = hal;
        this.mUptimeSupplier = uptimeSupplier;
    }

    public void setInputListener(InputListener listener) {
        synchronized (this.mLock) {
            if (!this.mKeyInputSupported && !this.mRotaryInputSupported) {
                Log.w(CarLog.TAG_INPUT, "input listener set while rotary and key input not supported");
                return;
            }
            this.mListener = listener;
            boolean keyInputSupported = this.mKeyInputSupported;
            boolean rotaryInputSupported = this.mRotaryInputSupported;
            if (keyInputSupported) {
                this.mHal.subscribeProperty(this, VehicleProperty.HW_KEY_INPUT);
            }
            if (rotaryInputSupported) {
                this.mHal.subscribeProperty(this, VehicleProperty.HW_ROTARY_INPUT);
            }
        }
    }

    public boolean isKeyInputSupported() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mKeyInputSupported;
        }
        return z;
    }

    public boolean isRotaryInputSupported() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mRotaryInputSupported;
        }
        return z;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void init() {
    }

    @Override // com.android.car.hal.HalServiceBase
    public void release() {
        synchronized (this.mLock) {
            this.mListener = null;
            this.mKeyInputSupported = false;
            this.mRotaryInputSupported = false;
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public int[] getAllSupportedProperties() {
        return SUPPORTED_PROPERTIES;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void takeProperties(Collection<VehiclePropConfig> properties) {
        for (VehiclePropConfig property : properties) {
            int i = property.prop;
            if (i == 289475088) {
                synchronized (this.mLock) {
                    this.mKeyInputSupported = true;
                }
            } else if (i != 289475104) {
                continue;
            } else {
                synchronized (this.mLock) {
                    this.mRotaryInputSupported = true;
                }
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onHalEvents(List<VehiclePropValue> values) throws Throwable {
        InputListener listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener == null) {
            Log.w(CarLog.TAG_INPUT, "Input event while listener is null");
            return;
        }
        for (VehiclePropValue value : values) {
            int i = value.prop;
            if (i == 289475088) {
                dispatchKeyInput(listener, value);
            } else if (i == 289475104) {
                dispatchRotaryInput(listener, value);
            } else {
                Log.e(CarLog.TAG_INPUT, "Wrong event dispatched, prop:0x" + Integer.toHexString(value.prop));
            }
        }
    }

    private void dispatchKeyInput(InputListener listener, VehiclePropValue value) throws Throwable {
        int valueAction = value.value.int32Values.get(0).intValue();
        int code = value.value.int32Values.get(1).intValue();
        if (shouldProcessKeyData(code) && 3 == valueAction) {
            Log.d(CarLog.TAG_INPUT, "change key action from " + valueAction + " to 0, keycode:" + code);
            valueAction = 0;
        }
        if (valueAction != 0 && 1 != valueAction) {
            Log.w(CarLog.TAG_INPUT, "intercept illegal action:" + valueAction + ", keycode:" + code);
            return;
        }
        int action = valueAction;
        int display = value.value.int32Values.get(2).intValue();
        int indentsCount = value.value.int32Values.size() < 4 ? 1 : value.value.int32Values.get(3).intValue();
        if (interceptKeyBeforeDispatching(code, action)) {
            Log.i(CarLog.TAG_INPUT, "intercept key:" + code + ", action:" + action);
            return;
        }
        Log.i(CarLog.TAG_INPUT, "hal event code:" + code + ", action:" + action + ", display: " + display + ", number of indents: " + indentsCount);
        while (indentsCount > 0) {
            indentsCount--;
            dispatchKeyEvent(listener, action, code, display);
            if (action == 0 && shouldProcessKeyData(code)) {
                Log.d(CarLog.TAG_INPUT, "append key event up for key:" + code);
                dispatchKeyEvent(listener, 1, code, display);
            }
        }
    }

    private synchronized boolean interceptKeyBeforeDispatching(int keyCode, int action) {
        if (action != 0) {
            return false;
        }
        if (keyCode != KEY_CODE_POWER_ROLL_UP && keyCode != KEY_CODE_POWER_ROLL_DOWN) {
            return false;
        }
        KeyPressInfo keyPressInfo = this.keyPressInfoHashMap.get(Integer.valueOf(keyCode));
        if (keyPressInfo == null) {
            this.keyPressInfoHashMap.put(Integer.valueOf(keyCode), new KeyPressInfo());
            return false;
        }
        if (keyPressInfo.keyTime == 0) {
            keyPressInfo.keyTime = SystemClock.uptimeMillis();
            return false;
        }
        keyPressInfo.keyCodeCount++;
        long timeDiff = SystemClock.uptimeMillis() - keyPressInfo.keyTime;
        if (timeDiff > 300 || keyPressInfo.keyCodeCount > 3) {
            if (timeDiff > 300 || keyPressInfo.keyCodeCount >= 3) {
                keyPressInfo.reset();
            }
            return false;
        }
        if (keyPressInfo.keyCodeCount >= 3) {
            keyPressInfo.reset();
            keyPressInfo.keyTime = 0L;
        }
        return true;
    }

    private class KeyPressInfo {
        int keyCodeCount;
        long keyTime;

        private KeyPressInfo() {
            this.keyCodeCount = 1;
            this.keyTime = SystemClock.uptimeMillis();
        }

        public void reset() {
            this.keyCodeCount = 1;
            this.keyTime = SystemClock.uptimeMillis();
        }
    }

    private boolean shouldProcessKeyData(int keyCode) {
        if (keyCode == 17 || keyCode == 18) {
            return true;
        }
        switch (keyCode) {
            case KEY_CODE_POWER /* 501 */:
            case KEY_CODE_POWER_ROLL_UP /* 502 */:
            case KEY_CODE_POWER_ROLL_DOWN /* 503 */:
            case KEY_CODE_POWER_RESET /* 504 */:
                return true;
            default:
                return false;
        }
    }

    private void dispatchRotaryInput(InputListener listener, VehiclePropValue value) {
        int carInputManagerType;
        VehiclePropValue vehiclePropValue = value;
        int timeValuesIndex = 3;
        if (vehiclePropValue.value.int32Values.size() < 3) {
            Log.e(CarLog.TAG_INPUT, "Wrong int32 array size for RotaryInput from vhal:" + vehiclePropValue.value.int32Values.size());
            return;
        }
        int rotaryInputType = vehiclePropValue.value.int32Values.get(0).intValue();
        int i = 1;
        int detentCount = vehiclePropValue.value.int32Values.get(1).intValue();
        int display = vehiclePropValue.value.int32Values.get(2).intValue();
        long timestamp = vehiclePropValue.timestamp;
        Log.i(CarLog.TAG_INPUT, "hal rotary input type: " + rotaryInputType + ", number of detents:" + detentCount + ", display: " + display);
        boolean clockwise = detentCount > 0;
        int timeValuesIndex2 = Math.abs(detentCount);
        if (timeValuesIndex2 == 0) {
            Log.e(CarLog.TAG_INPUT, "Zero detentCount from vhal, ignore the event");
            return;
        }
        if (display != 0 && display != 1) {
            Log.e(CarLog.TAG_INPUT, "Wrong display type for RotaryInput from vhal:" + display);
            return;
        }
        if (vehiclePropValue.value.int32Values.size() != (3 + timeValuesIndex2) - 1) {
            Log.e(CarLog.TAG_INPUT, "Wrong int32 array size for RotaryInput from vhal:" + vehiclePropValue.value.int32Values.size());
            return;
        }
        if (rotaryInputType == 0) {
            carInputManagerType = 10;
        } else if (rotaryInputType == 1) {
            carInputManagerType = 11;
        } else {
            Log.e(CarLog.TAG_INPUT, "Unknown rotary input type: " + rotaryInputType);
            return;
        }
        long[] timestamps = new long[timeValuesIndex2];
        long uptimeToElapsedTimeDelta = CarServiceUtils.getUptimeToElapsedTimeDeltaInMillis();
        long startUptime = TimeUnit.NANOSECONDS.toMillis(timestamp) - uptimeToElapsedTimeDelta;
        timestamps[0] = startUptime;
        int i2 = 0;
        while (true) {
            int rotaryInputType2 = rotaryInputType;
            if (i2 < timestamps.length - i) {
                timestamps[i2 + 1] = timestamps[i2] + TimeUnit.NANOSECONDS.toMillis(vehiclePropValue.value.int32Values.get(timeValuesIndex + i2).intValue());
                i2++;
                vehiclePropValue = value;
                timeValuesIndex = timeValuesIndex;
                rotaryInputType = rotaryInputType2;
                timeValuesIndex2 = timeValuesIndex2;
                i = 1;
            } else {
                RotaryEvent event = new RotaryEvent(carInputManagerType, clockwise, timestamps);
                listener.onRotaryEvent(event, display);
                return;
            }
        }
    }

    private void dispatchKeyEvent(InputListener listener, int action, int code, int display) throws Throwable {
        dispatchKeyEvent(listener, action, code, display, this.mUptimeSupplier.getAsLong());
    }

    private void dispatchKeyEvent(InputListener listener, int action, int code, int display, long eventTime) throws Throwable {
        long downTime;
        long downTime2;
        int repeat;
        synchronized (this.mKeyStates) {
            try {
                KeyState state = this.mKeyStates.get(code);
                if (state == null) {
                    state = new KeyState();
                    this.mKeyStates.put(code, state);
                }
                if (action == 0) {
                    try {
                        int repeat2 = state.mRepeatCount;
                        state.mRepeatCount = repeat2 + 1;
                        state.mLastKeyDownTimestamp = eventTime;
                        downTime2 = eventTime;
                        repeat = repeat2;
                    } catch (Throwable th) {
                        th = th;
                        while (true) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        }
                    }
                } else {
                    if (state.mLastKeyDownTimestamp == -1) {
                        downTime = eventTime;
                    } else {
                        downTime = state.mLastKeyDownTimestamp;
                    }
                    state.mRepeatCount = 0;
                    downTime2 = downTime;
                    repeat = 0;
                }
                KeyEvent event = new KeyEvent(downTime2, eventTime, action, code, repeat, 0, 0, 0, 0, 1);
                if (display == 0) {
                    event.setDisplayId(0);
                }
                listener.onKeyEvent(event, display);
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*Input HAL*");
        writer.println("mKeyInputSupported:" + this.mKeyInputSupported);
        writer.println("mRotaryInputSupported:" + this.mRotaryInputSupported);
    }
}
