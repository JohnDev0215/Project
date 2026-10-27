package com.ts.lib.car.hardware.engine;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.engine.CarVendorEngineManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorEngineMgr extends CarVendorManagerBase implements CarVendorEngineManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static EngineEventChangeValue sEngineEventChangeValue = new EngineEventChangeValue();
    private final LinkedList<OnCarEngineChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtEngineIds = {Integer.valueOf(CarVendorEngineManager.ID_ENGINE_CODER), 557851654, 557851652, Integer.valueOf(CarVendorEngineManager.ID_ENGINE_DISPLACEMENTR), 557851650, 557851651, 557851655, Integer.valueOf(CarVendorEngineManager.ID_SLIDE_RAIL_MOTOR_ELEC_PERCENT_R)};

    public CarVendorEngineMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorEngineManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorEngineManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorEngineManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorEngineManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getSlideRailMotorElecPercentR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorEngineManager.ID_SLIDE_RAIL_MOTOR_ELEC_PERCENT_R, 0);
            Log.debug(CarVendorEngineManager.TAG, "getSlideRailMotorElecPercentR value = " + value + " property: 0x" + tenToHex(CarVendorEngineManager.ID_SLIDE_RAIL_MOTOR_ELEC_PERCENT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getEngineConditionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557851654, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineConditionR property: 0x");
            sb.append(tenToHex(557851654));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getEngineCodeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorEngineManager.ID_ENGINE_CODER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineCodeR property: 0x");
            sb.append(tenToHex(CarVendorEngineManager.ID_ENGINE_CODER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public void setEngineConditionS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorEngineManager.TAG, "setEngineConditionS property: 0x" + tenToHex(557851654) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557851654, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getEngineCoolantLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557851652, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineCoolantLevelR property: 0x");
            sb.append(tenToHex(557851652));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public float getEngineDisplacementR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorEngineManager.ID_ENGINE_DISPLACEMENTR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineDisplacementR property: 0x");
            sb.append(tenToHex(CarVendorEngineManager.ID_ENGINE_DISPLACEMENTR));
            sb.append(" class: Double value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getEnginePowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557851650, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnginePowerR property: 0x");
            sb.append(tenToHex(557851650));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getEngineSpeedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557851651, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineSpeedR property: 0x");
            sb.append(tenToHex(557851651));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public int getOilLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557851655, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLevelR property: 0x");
            sb.append(tenToHex(557851655));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorEngineManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public void addCarEngineEventListener(CarVendorEngineManager.OnCarEngineEventListener listener) throws CarNotConnectedException {
        OnCarEngineChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarEngineChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorEngineManager.TAG, "addCarEngineEventListener propId size: " + this.mExtEngineIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtEngineIds) {
                int propId = num.intValue();
                Log.debug(CarVendorEngineManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.engine.CarVendorEngineManager
    public void removeCarEngineEventListener(CarVendorEngineManager.OnCarEngineEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarEngineChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorEngineManager.TAG, "removeCarEngineEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static EngineEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sEngineEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorEngineManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557851650:
                sEngineEventChangeValue.setEnginePowerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557851651:
                sEngineEventChangeValue.setEngineSpeedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557851652:
                sEngineEventChangeValue.setEngineCoolantLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557851654:
                sEngineEventChangeValue.setEngineConditionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557851655:
                sEngineEventChangeValue.setOilLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorEngineManager.ID_ENGINE_CODER /* 557851657 */:
                sEngineEventChangeValue.setEngineCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorEngineManager.ID_SLIDE_RAIL_MOTOR_ELEC_PERCENT_R /* 557851658 */:
                sEngineEventChangeValue.setSlideRailMotorElecPercentR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorEngineManager.ID_ENGINE_DISPLACEMENTR /* 559948808 */:
                sEngineEventChangeValue.setEngineDisplacementR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                Log.debug(CarVendorEngineManager.TAG, "break other properties ,except engine");
                break;
        }
        Log.debug(CarVendorEngineManager.TAG, "convertCarVendorPropertyValue " + sEngineEventChangeValue.toString(), DEBUG_LOGGER);
        return sEngineEventChangeValue;
    }

    private OnCarEngineChangeEventListenerProxy findListenerLocked(CarVendorEngineManager.OnCarEngineEventListener listener) {
        for (OnCarEngineChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarEngineChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorEngineManager.OnCarEngineEventListener listener;

        public OnCarEngineChangeEventListenerProxy(CarVendorEngineManager.OnCarEngineEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorEngineMgr.this.traceCallbackPrepare();
                EngineEventChangeValue newValue = CarVendorEngineMgr.convertCarVendorProperty(value);
                CarVendorEngineMgr.this.traceCallbackBefore();
                this.listener.onEngineEventChange(newValue);
                CarVendorEngineMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorEngineManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorEngineManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onEngineErrorEvent(propertyId, zone);
        }
    }
}
