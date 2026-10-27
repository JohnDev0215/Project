package com.ts.lib.car.hardware.power;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.power.CarVendorPowerManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorPowerMgr extends CarVendorManagerBase implements CarVendorPowerManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static PowerEventChangeValue sPowerEventChangeValue = new PowerEventChangeValue();
    private final LinkedList<OnCarPowerChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtPowerIds = {557969412, Integer.valueOf(CarVendorPowerManager.ID_AP_POWER_STATE_REQ), Integer.valueOf(CarVendorPowerManager.ID_AP_GET_OFF_SCREEN_FLAG_RES)};

    public CarVendorPowerMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorPowerManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorPowerManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorPowerManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorPowerManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void queryOffScreenFlag(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "queryOffScreenFlag property: 0x" + tenToHex(CarVendorPowerManager.ID_AP_GET_OFF_SCREEN_FLAG_REQ) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorPowerManager.ID_AP_GET_OFF_SCREEN_FLAG_REQ, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setOffScreenFlag(int[] arr) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "setOffScreenFlag property: 0x" + tenToHex(CarVendorPowerManager.ID_AP_SET_OFF_SCREEN_FLAG) + " class: int[] value: " + CastArrayUtil.parseString(arr), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorPowerManager.ID_AP_SET_OFF_SCREEN_FLAG, 0, CastArrayUtil.toWrap(arr));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public int[] getIviPowerModeChanged() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557969412, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIviPowerModeChanged property: 0x");
            sb.append(tenToHex(557969412));
            sb.append(" class: int[] value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPowerManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public int[] getIviPowerStateChanged() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, CarVendorPowerManager.ID_AP_POWER_STATE_REQ, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIviPowerStateChanged property: 0x");
            sb.append(tenToHex(CarVendorPowerManager.ID_AP_POWER_STATE_REQ));
            sb.append(" class: int[] value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPowerManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setVehicleBcallStatusReq(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "setVehicleBcallStatusReq property: 0x" + tenToHex(557903875) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557903875, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void reset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "reset property: 0x" + tenToHex(CarVendorPowerManager.ID_SYS_BOSCH_RESET) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorPowerManager.ID_SYS_BOSCH_RESET, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setVehiclePowerShutdownRequest(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "setVehiclePowerShutdownRequest property: 0x" + tenToHex(557903877) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557903877, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setVehiclePowerStateReport(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorPowerManager.TAG, "setVehiclePowerStateReport property: 0x" + tenToHex(CarVendorPowerManager.ID_AP_POWER_STATE_REPORT) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorPowerManager.ID_AP_POWER_STATE_REPORT, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setDisplayState(int type, int state) throws CarNotConnectedException {
        int[] displayState = {type, state};
        Log.debug(CarVendorPowerManager.TAG, "setDisplayState property: 0x" + tenToHex(CarVendorPowerManager.ID_AP_CONTROL_DISPLAY_STATE_REQ) + " class: int[] value: " + CastArrayUtil.parseString(displayState), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorPowerManager.ID_AP_CONTROL_DISPLAY_STATE_REQ, 0, CastArrayUtil.toWrap(displayState));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void setDisplayState(int type, int state, int action) throws CarNotConnectedException {
        int[] displayState = {type, state, action};
        Log.debug(CarVendorPowerManager.TAG, "setDisplayState property: 0x" + tenToHex(CarVendorPowerManager.ID_AP_CONTROL_DISPLAY_STATE_REQ) + " class: int[] value: " + CastArrayUtil.parseString(displayState), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorPowerManager.ID_AP_CONTROL_DISPLAY_STATE_REQ, 0, CastArrayUtil.toWrap(displayState));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void addCarPowerEventListener(CarVendorPowerManager.OnCarPowerEventListener listener) throws CarNotConnectedException {
        OnCarPowerChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarPowerChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorPowerManager.TAG, "addCarPowerEventListener propId size: " + this.mExtPowerIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtPowerIds) {
                int propId = num.intValue();
                Log.debug(CarVendorPowerManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.power.CarVendorPowerManager
    public void removeCarPowerEventListener(CarVendorPowerManager.OnCarPowerEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarPowerChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorPowerManager.TAG, "removeCarPowerEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static PowerEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sPowerEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorPowerManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case CarVendorPowerManager.ID_AP_POWER_STATE_REQ /* 289475072 */:
                sPowerEventChangeValue.setIviPowerStateChanged(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorPowerManager.ID_AP_POWER_STATE_REPORT /* 289475073 */:
                sPowerEventChangeValue.setVehiclePowerStateReport(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557903875:
                sPowerEventChangeValue.setVehicleBcallStatusReq(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557903877:
                sPowerEventChangeValue.setVehiclePowerShutdownRequest(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorPowerManager.ID_AP_GET_OFF_SCREEN_FLAG_REQ /* 557903891 */:
                sPowerEventChangeValue.setQueryOffScreenFlag(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case 557969412:
                sPowerEventChangeValue.setIviPowerModeChanged(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            case CarVendorPowerManager.ID_AP_GET_OFF_SCREEN_FLAG_RES /* 557969428 */:
                sPowerEventChangeValue.setGetOffScreenFlagResult(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            default:
                Log.debug(CarVendorPowerManager.TAG, "break other properties ,except power");
                break;
        }
        Log.debug(CarVendorPowerManager.TAG, "convertCarVendorPropertyValue " + sPowerEventChangeValue.toString(), DEBUG_LOGGER);
        return sPowerEventChangeValue;
    }

    private OnCarPowerChangeEventListenerProxy findListenerLocked(CarVendorPowerManager.OnCarPowerEventListener listener) {
        for (OnCarPowerChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarPowerChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorPowerManager.OnCarPowerEventListener listener;

        public OnCarPowerChangeEventListenerProxy(CarVendorPowerManager.OnCarPowerEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorPowerMgr.this.traceCallbackPrepare();
                PowerEventChangeValue newValue = CarVendorPowerMgr.convertCarVendorProperty(value);
                CarVendorPowerMgr.this.traceCallbackBefore();
                this.listener.onPowerEventChange(newValue);
                CarVendorPowerMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorPowerManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorPowerManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onPowerErrorEvent(propertyId, zone);
        }
    }
}
