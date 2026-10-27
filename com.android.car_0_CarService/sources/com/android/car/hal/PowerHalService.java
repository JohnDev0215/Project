package com.android.car.hal;

import android.hardware.automotive.vehicle.V2_0.VehiclePropConfig;
import android.hardware.automotive.vehicle.V2_0.VehiclePropValue;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.ServiceSpecificException;
import android.util.Log;
import com.android.car.CarLog;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PowerHalService extends HalServiceBase {
    public static final int MAX_BRIGHTNESS = 100;
    public static final int SET_DEEP_SLEEP_ENTRY = 2;
    public static final int SET_DEEP_SLEEP_EXIT = 3;
    public static final int SET_ON = 6;
    public static final int SET_SHUTDOWN_CANCELLED = 8;
    public static final int SET_SHUTDOWN_POSTPONE = 4;
    public static final int SET_SHUTDOWN_PREPARE = 7;
    public static final int SET_SHUTDOWN_START = 5;
    public static final int SET_WAIT_FOR_VHAL = 1;
    public static final int SHUTDOWN_CAN_SLEEP = 2;
    public static final int SHUTDOWN_IMMEDIATELY = 1;
    public static final int SHUTDOWN_ONLY = 3;
    private static final int[] SUPPORTED_PROPERTIES = {VehicleProperty.AP_POWER_STATE_REQ, VehicleProperty.AP_POWER_STATE_REPORT, VehicleProperty.DISPLAY_BRIGHTNESS};
    private static final String TAG = "M_STR_CAR.POWER";
    private final VehicleHal mHal;
    private PowerEventListener mListener;
    private int mMaxDisplayBrightness;
    private LinkedList<VehiclePropValue> mQueuedEvents;
    private final Object mLock = new Object();
    private final HashMap<Integer, VehiclePropConfig> mProperties = new HashMap<>();

    public interface PowerEventListener {
        void onApPowerStateChange(PowerState powerState);

        void onDisplayBrightnessChange(int i);
    }

    private static String powerStateReportName(int state) {
        String baseName;
        switch (state) {
            case 1:
                baseName = "WAIT_FOR_VHAL";
                break;
            case 2:
                baseName = "DEEP_SLEEP_ENTRY";
                break;
            case 3:
                baseName = "DEEP_SLEEP_EXIT";
                break;
            case 4:
                baseName = "SHUTDOWN_POSTPONE";
                break;
            case 5:
                baseName = "SHUTDOWN_START";
                break;
            case 6:
                baseName = "ON";
                break;
            case 7:
                baseName = "SHUTDOWN_PREPARE";
                break;
            case 8:
                baseName = "SHUTDOWN_CANCELLED";
                break;
            default:
                baseName = "<unknown>";
                break;
        }
        return baseName + "(" + state + ")";
    }

    private static String powerStateReqName(int state) {
        String baseName;
        if (state == 0) {
            baseName = "ON";
        } else if (state == 1) {
            baseName = "SHUTDOWN_PREPARE";
        } else if (state == 2) {
            baseName = "CANCEL_SHUTDOWN";
        } else if (state == 3) {
            baseName = "FINISHED";
        } else {
            baseName = "<unknown>";
        }
        return baseName + "(" + state + ")";
    }

    public static final class PowerState {
        public final int mParam;
        public final int mState;

        public PowerState(int state, int param) {
            this.mState = state;
            this.mParam = param;
        }

        public boolean canEnterDeepSleep() {
            if (this.mState != 1) {
                throw new IllegalStateException("wrong state");
            }
            int i = this.mParam;
            return i == 2 || i == 4;
        }

        public boolean canPostponeShutdown() {
            if (this.mState != 1) {
                throw new IllegalStateException("wrong state");
            }
            int i = this.mParam;
            return (i == 1 || i == 4) ? false : true;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PowerState)) {
                return false;
            }
            PowerState that = (PowerState) o;
            return this.mState == that.mState && this.mParam == that.mParam;
        }

        public String toString() {
            return "PowerState state:" + this.mState + ", param:" + this.mParam;
        }
    }

    public PowerHalService(VehicleHal hal) {
        this.mHal = hal;
    }

    public void setListener(PowerEventListener listener) {
        LinkedList<VehiclePropValue> eventsToDispatch = null;
        synchronized (this.mLock) {
            this.mListener = listener;
            if (this.mQueuedEvents != null && this.mQueuedEvents.size() > 0) {
                eventsToDispatch = this.mQueuedEvents;
            }
            this.mQueuedEvents = null;
        }
        if (eventsToDispatch != null) {
            dispatchEvents(eventsToDispatch, listener);
        }
    }

    public void sendWaitForVhal() {
        Log.w(TAG, "send to VHAL: send wait for vhal");
        setPowerState(1, 0);
    }

    public void sendSleepEntry(int wakeupTimeSec) {
        Log.w(TAG, "send to VHAL: send sleep entry, time: " + wakeupTimeSec);
        setPowerState(2, wakeupTimeSec);
    }

    public void sendSleepExit() {
        Log.w(TAG, "send to VHAL: send sleep exit");
        setPowerState(3, 0);
    }

    public void sendShutdownPostpone(int postponeTimeMs) {
        Log.w(TAG, "send to VHAL: send shutdown postpone, time:" + postponeTimeMs);
        setPowerState(4, postponeTimeMs);
    }

    public void sendShutdownStart(int wakeupTimeSec) {
        Log.w(TAG, "send to VHAL: send shutdown start, time : " + wakeupTimeSec);
        setPowerState(5, wakeupTimeSec);
    }

    public void sendOn() {
        Log.w(TAG, "send to VHAL: send on");
        setPowerState(6, 0);
    }

    public void sendShutdownPrepare() {
        Log.w(TAG, "send to VHAL: send shutdown prepare");
        setPowerState(7, 0);
    }

    public void sendShutdownCancel() {
        Log.w(TAG, "send to VHAL: send shutdown cancel");
        setPowerState(8, 0);
    }

    public void sendDisplayBrightness(int brightness) {
        if (brightness < 0) {
            brightness = 0;
        } else if (brightness > 100) {
            brightness = 100;
        }
        VehiclePropConfig prop = this.mProperties.get(Integer.valueOf(VehicleProperty.DISPLAY_BRIGHTNESS));
        if (prop == null) {
            return;
        }
        try {
            this.mHal.set(VehicleProperty.DISPLAY_BRIGHTNESS, 0).to(brightness);
            Log.i(CarLog.TAG_POWER, "send display brightness = " + brightness);
        } catch (ServiceSpecificException | IllegalArgumentException e) {
            Log.e(CarLog.TAG_POWER, "cannot set DISPLAY_BRIGHTNESS", e);
        }
    }

    private void setPowerState(int state, int additionalParam) {
        if (isPowerStateSupported()) {
            int[] values = {state, additionalParam};
            try {
                this.mHal.set(VehicleProperty.AP_POWER_STATE_REPORT, 0).to(values);
                Log.e(TAG, "set AP_POWER_STATE_REPORT setPowerState, state= " + state + " , is " + powerStateReportName(state) + " , param=" + additionalParam);
            } catch (ServiceSpecificException e) {
                Log.e(TAG, "cannot set to AP_POWER_STATE_REPORT", e);
            }
        }
    }

    public PowerState getCurrentPowerState() {
        try {
            int[] state = (int[]) this.mHal.get(int[].class, VehicleProperty.AP_POWER_STATE_REQ);
            return new PowerState(state[0], state[1]);
        } catch (ServiceSpecificException e) {
            Log.e(TAG, "Cannot get AP_POWER_STATE_REQ", e);
            return null;
        }
    }

    public boolean isPowerStateSupported() {
        boolean z;
        synchronized (this.mLock) {
            z = (this.mProperties.get(Integer.valueOf(VehicleProperty.AP_POWER_STATE_REQ)) == null || this.mProperties.get(Integer.valueOf(VehicleProperty.AP_POWER_STATE_REPORT)) == null) ? false : true;
        }
        return z;
    }

    private boolean isConfigFlagSet(int flag) {
        VehiclePropConfig config;
        synchronized (this.mLock) {
            config = this.mProperties.get(Integer.valueOf(VehicleProperty.AP_POWER_STATE_REQ));
        }
        return (config == null || config.configArray.size() < 1 || (config.configArray.get(0).intValue() & flag) == 0) ? false : true;
    }

    public boolean isDeepSleepAllowed() {
        return isConfigFlagSet(1);
    }

    public boolean isTimedWakeupAllowed() {
        return isConfigFlagSet(2);
    }

    @Override // com.android.car.hal.HalServiceBase
    public void init() {
        synchronized (this.mLock) {
            for (VehiclePropConfig config : this.mProperties.values()) {
                if (VehicleHal.isPropertySubscribable(config)) {
                    this.mHal.subscribeProperty(this, config.prop);
                }
            }
            VehiclePropConfig brightnessProperty = this.mProperties.get(Integer.valueOf(VehicleProperty.DISPLAY_BRIGHTNESS));
            if (brightnessProperty != null) {
                int i = brightnessProperty.areaConfigs.size() > 0 ? brightnessProperty.areaConfigs.get(0).maxInt32Value : 0;
                this.mMaxDisplayBrightness = i;
                if (i <= 0) {
                    Log.w(CarLog.TAG_POWER, "Max display brightness from vehicle HAL is invalid:" + this.mMaxDisplayBrightness);
                    this.mMaxDisplayBrightness = 1;
                }
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void release() {
        synchronized (this.mLock) {
            this.mProperties.clear();
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public int[] getAllSupportedProperties() {
        return SUPPORTED_PROPERTIES;
    }

    @Override // com.android.car.hal.HalServiceBase
    public void takeProperties(Collection<VehiclePropConfig> properties) {
        if (properties.isEmpty()) {
            return;
        }
        synchronized (this.mLock) {
            for (VehiclePropConfig config : properties) {
                this.mProperties.put(Integer.valueOf(config.prop), config);
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void onHalEvents(List<VehiclePropValue> values) {
        synchronized (this.mLock) {
            if (this.mListener == null) {
                if (this.mQueuedEvents == null) {
                    this.mQueuedEvents = new LinkedList<>();
                }
                this.mQueuedEvents.addAll(values);
            } else {
                PowerEventListener listener = this.mListener;
                dispatchEvents(values, listener);
            }
        }
    }

    private void dispatchEvents(List<VehiclePropValue> values, PowerEventListener listener) {
        int maxBrightness;
        for (VehiclePropValue v : values) {
            int i = v.prop;
            if (i == 289409539) {
                synchronized (this.mLock) {
                    maxBrightness = this.mMaxDisplayBrightness;
                }
                int brightness = (v.value.int32Values.get(0).intValue() * 100) / maxBrightness;
                if (brightness < 0) {
                    Log.e(CarLog.TAG_POWER, "invalid brightness: " + brightness + ", set to 0");
                    brightness = 0;
                } else if (brightness > 100) {
                    Log.e(CarLog.TAG_POWER, "invalid brightness: " + brightness + ", set to 100");
                    brightness = 100;
                }
                Log.i(CarLog.TAG_POWER, "Received DISPLAY_BRIGHTNESS=" + brightness);
                listener.onDisplayBrightnessChange(brightness);
            } else if (i == 289475072) {
                int state = v.value.int32Values.get(0).intValue();
                int param = v.value.int32Values.get(1).intValue();
                Log.w(TAG, "Received HAL signal , AP_POWER_STATE_REQ= " + state + " , is " + powerStateReqName(state) + " , param=" + param);
                listener.onApPowerStateChange(new PowerState(state, param));
            }
        }
    }

    @Override // com.android.car.hal.HalServiceBase
    public void dump(PrintWriter writer) {
        writer.println("*Power HAL*");
        writer.println("isPowerStateSupported:" + isPowerStateSupported() + ",isDeepSleepAllowed:" + isDeepSleepAllowed());
    }
}
