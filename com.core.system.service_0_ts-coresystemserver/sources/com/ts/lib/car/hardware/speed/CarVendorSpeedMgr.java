package com.ts.lib.car.hardware.speed;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.speed.CarVendorSpeedManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorSpeedMgr extends CarVendorManagerBase implements CarVendorSpeedManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static SpeedEventChangeValue sSpeedEventChangeValue = new SpeedEventChangeValue();
    private final LinkedList<OnCarSpeedChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtSpeedIds = {Integer.valueOf(CarVendorSpeedManager.ID_ACCELERATE_DEEPNESSR), Integer.valueOf(CarVendorSpeedManager.ID_BRAKE_DEEPNESSR), Integer.valueOf(CarVendorSpeedManager.ID_CURRENT_SPEEDR), 559957505, Integer.valueOf(CarVendorSpeedManager.ID_IPB_VEHICLE_SPEED_STATUS_R), Integer.valueOf(CarVendorSpeedManager.ID_SPEED_ALIVE_COUNTER_R), Integer.valueOf(CarVendorSpeedManager.ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R)};

    public CarVendorSpeedMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorSpeedManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorSpeedManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorSpeedManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorSpeedManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public int getAccelerateDeepnessSignificantBitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSpeedManager.ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccelerateDeepnessSignificantBitR property: 0x");
            sb.append(tenToHex(CarVendorSpeedManager.ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSpeedManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public float getSpeedValueR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957505, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSpeedValueR property: 0x");
            sb.append(tenToHex(559957505));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSpeedManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public int getAccelerateDeepnessR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSpeedManager.ID_ACCELERATE_DEEPNESSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAccelerateDeepnessR property: 0x");
            sb.append(tenToHex(CarVendorSpeedManager.ID_ACCELERATE_DEEPNESSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSpeedManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public int getBrakeDeepnessR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSpeedManager.ID_BRAKE_DEEPNESSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakeDeepnessR property: 0x");
            sb.append(tenToHex(CarVendorSpeedManager.ID_BRAKE_DEEPNESSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSpeedManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public int getCurrentSpeedR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorSpeedManager.ID_CURRENT_SPEEDR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCurrentSpeedR property: 0x");
            sb.append(tenToHex(CarVendorSpeedManager.ID_CURRENT_SPEEDR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorSpeedManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public float getIpbVehicleSpeedStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorSpeedManager.ID_IPB_VEHICLE_SPEED_STATUS_R, 0);
            Log.debug(CarVendorSpeedManager.TAG, "getIPBVehicleSpeedStatusR value = " + value + " property: 0x" + tenToHex(CarVendorSpeedManager.ID_IPB_VEHICLE_SPEED_STATUS_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public float getSpeedAliveCounterR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorSpeedManager.ID_SPEED_ALIVE_COUNTER_R, 0);
            Log.debug(CarVendorSpeedManager.TAG, "getSpeedAliveCounterR value = " + value + " property: 0x" + tenToHex(CarVendorSpeedManager.ID_SPEED_ALIVE_COUNTER_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public void addCarSpeedEventListener(CarVendorSpeedManager.OnCarSpeedEventListener listener) throws CarNotConnectedException {
        OnCarSpeedChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarSpeedChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorSpeedManager.TAG, "addCarSpeedEventListener propId size: " + this.mExtSpeedIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtSpeedIds) {
                int propId = num.intValue();
                Log.debug(CarVendorSpeedManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.speed.CarVendorSpeedManager
    public void removeCarSpeedEventListener(CarVendorSpeedManager.OnCarSpeedEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarSpeedChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorSpeedManager.TAG, "removeCarSpeedEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SpeedEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sSpeedEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorSpeedManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case CarVendorSpeedManager.ID_ACCELERATE_DEEPNESSR /* 557845760 */:
                sSpeedEventChangeValue.setAccelerateDeepnessR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSpeedManager.ID_BRAKE_DEEPNESSR /* 557845761 */:
                sSpeedEventChangeValue.setBrakeDeepnessR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSpeedManager.ID_ACCELERATE_DEEPNESS_SIGNIFICANT_BIT_R /* 557845764 */:
                sSpeedEventChangeValue.setAccelerateDeepnessSignificantBitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSpeedManager.ID_CURRENT_SPEEDR /* 557867014 */:
                sSpeedEventChangeValue.setCurrentSpeedR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorSpeedManager.ID_IPB_VEHICLE_SPEED_STATUS_R /* 559942915 */:
                sSpeedEventChangeValue.setIpbVehicleSpeedStatusR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case CarVendorSpeedManager.ID_SPEED_ALIVE_COUNTER_R /* 559942916 */:
                sSpeedEventChangeValue.setSpeedAliveCounterR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559957505:
                sSpeedEventChangeValue.setSpeedValueR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                Log.debug(CarVendorSpeedManager.TAG, "break other properties ,except speed");
                break;
        }
        Log.debug(CarVendorSpeedManager.TAG, "convertCarVendorPropertyValue " + sSpeedEventChangeValue.toString(), DEBUG_LOGGER);
        return sSpeedEventChangeValue;
    }

    private OnCarSpeedChangeEventListenerProxy findListenerLocked(CarVendorSpeedManager.OnCarSpeedEventListener listener) {
        for (OnCarSpeedChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarSpeedChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorSpeedManager.OnCarSpeedEventListener listener;

        public OnCarSpeedChangeEventListenerProxy(CarVendorSpeedManager.OnCarSpeedEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorSpeedMgr.this.traceCallbackPrepare();
                SpeedEventChangeValue newValue = CarVendorSpeedMgr.convertCarVendorProperty(value);
                CarVendorSpeedMgr.this.traceCallbackBefore();
                this.listener.onSpeedEventChange(newValue);
                CarVendorSpeedMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorSpeedManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorSpeedManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onSpeedErrorEvent(propertyId, zone);
        }
    }
}
