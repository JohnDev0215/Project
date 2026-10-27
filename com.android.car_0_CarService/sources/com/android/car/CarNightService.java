package com.android.car;

import android.app.UiModeManager;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyEvent;
import android.car.hardware.property.ICarPropertyEventListener;
import android.content.Context;
import android.hardware.automotive.vehicle.V2_0.VehicleProperty;
import android.os.RemoteException;
import android.util.Log;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CarNightService implements CarServiceBase {
    public static final boolean DBG = false;
    public static final int FORCED_DAY_MODE = 1;
    public static final int FORCED_NIGHT_MODE = 2;
    public static final int FORCED_SENSOR_MODE = 0;
    private final CarPropertyService mCarPropertyService;
    private final Context mContext;
    private final UiModeManager mUiModeManager;
    private final Object mLock = new Object();
    private int mNightSetting = 2;
    private int mForcedMode = 0;
    private long mLastSensorEventTime = -1;
    private final ICarPropertyEventListener mICarPropertyEventListener = new ICarPropertyEventListener.Stub() { // from class: com.android.car.CarNightService.1
        public void onEvent(List<CarPropertyEvent> events) throws RemoteException {
            synchronized (CarNightService.this.mLock) {
                for (CarPropertyEvent event : events) {
                    CarNightService.this.onNightModeCarPropertyEventLocked(event);
                }
            }
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface DayNightSensorMode {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNightModeCarPropertyEventLocked(CarPropertyEvent event) {
        if (event != null && event.getEventType() == 0) {
            CarPropertyValue value = event.getCarPropertyValue();
            if (value.getPropertyId() == 287310855 && value.getTimestamp() > this.mLastSensorEventTime) {
                this.mLastSensorEventTime = value.getTimestamp();
                boolean nightMode = ((Boolean) value.getValue()).booleanValue();
                Log.i(CarLog.TAG_SENSOR, "Set dayNight Mode as " + nightMode + " at timestamp: " + this.mLastSensorEventTime);
                setNightModeLocked(nightMode);
            }
        }
    }

    private void setNightModeLocked(boolean nightMode) {
        if (nightMode) {
            this.mNightSetting = 2;
        } else {
            this.mNightSetting = 1;
        }
        UiModeManager uiModeManager = this.mUiModeManager;
        if (uiModeManager != null && this.mForcedMode == 0) {
            uiModeManager.setNightMode(this.mNightSetting);
        }
    }

    public int forceDayNightMode(int mode) {
        int resultMode;
        synchronized (this.mLock) {
            if (this.mUiModeManager == null) {
                return -1;
            }
            if (mode == 0) {
                resultMode = this.mNightSetting;
                this.mForcedMode = 0;
            } else if (mode == 1) {
                resultMode = 1;
                this.mForcedMode = 1;
            } else if (mode == 2) {
                resultMode = 2;
                this.mForcedMode = 2;
            } else {
                Log.e(CarLog.TAG_SENSOR, "Unknown forced day/night mode " + mode);
                return -1;
            }
            this.mUiModeManager.setNightMode(resultMode);
            return this.mUiModeManager.getNightMode();
        }
    }

    CarNightService(Context context, CarPropertyService propertyService) {
        this.mContext = context;
        this.mCarPropertyService = propertyService;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        this.mUiModeManager = uiModeManager;
        if (uiModeManager == null) {
            Log.w(CarLog.TAG_SENSOR, "Failed to get UI_MODE_SERVICE");
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        synchronized (this.mLock) {
            this.mCarPropertyService.registerListener(VehicleProperty.NIGHT_MODE, 0.0f, this.mICarPropertyEventListener);
            CarPropertyValue propertyValue = this.mCarPropertyService.getProperty(VehicleProperty.NIGHT_MODE, 0);
            if (propertyValue != null && propertyValue.getTimestamp() != 0) {
                this.mLastSensorEventTime = propertyValue.getTimestamp();
                setNightModeLocked(((Boolean) propertyValue.getValue()).booleanValue());
            } else {
                Log.w(CarLog.TAG_SENSOR, "Failed to get value of NIGHT_MODE");
                setNightModeLocked(true);
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        String str;
        synchronized (this.mLock) {
            writer.println("*DAY NIGHT POLICY*");
            StringBuilder sb = new StringBuilder();
            sb.append("Mode:");
            sb.append(this.mNightSetting == 2 ? "night" : "day");
            writer.println(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Forced Mode? ");
            if (this.mForcedMode == 0) {
                str = "false, timestamp of dayNight sensor is: " + this.mLastSensorEventTime;
            } else {
                str = this.mForcedMode == 1 ? "day" : "night";
            }
            sb2.append(str);
            writer.println(sb2.toString());
        }
    }
}
