package com.ts.lib.car.hardware.sensor;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.sensor.CarVendorSensorManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorSensorMgr extends CarVendorManagerBase implements CarVendorSensorManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static SensorEventChangeValue sSensorEventChangeValue = new SensorEventChangeValue();
    private final LinkedList<OnCarSensorChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtSensorIds = {559958786, 557861632, 557861635, 559958785};

    public CarVendorSensorMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorSensorManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorSensorManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorSensorManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorSensorManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public float getHumiditySensorValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559958786, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHumiditySensorValue property: 0x");
            sb.append(tenToHex(559958786));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSensorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public int getLightIntensity() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861632, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLightIntensity property: 0x");
            sb.append(tenToHex(557861632));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSensorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public int getSlope() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861635, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSlope property: 0x");
            sb.append(tenToHex(557861635));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSensorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public float getTemperatureSensorValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559958785, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTemperatureSensorValue property: 0x");
            sb.append(tenToHex(559958785));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSensorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public void addCarSensorEventListener(CarVendorSensorManager.OnCarSensorEventListener listener) throws CarNotConnectedException {
        OnCarSensorChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarSensorChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorSensorManager.TAG, "addCarSensorEventListener propId size: " + this.mExtSensorIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtSensorIds) {
                int propId = num.intValue();
                Log.debug(CarVendorSensorManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.sensor.CarVendorSensorManager
    public void removeCarSensorEventListener(CarVendorSensorManager.OnCarSensorEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarSensorChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorSensorManager.TAG, "removeCarSensorEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SensorEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sSensorEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorSensorManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557861632:
                sSensorEventChangeValue.setLightIntensity(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557861635:
                sSensorEventChangeValue.setSlope(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 559958785:
                sSensorEventChangeValue.setTemperatureSensorValue(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            case 559958786:
                sSensorEventChangeValue.setHumiditySensorValue(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                Log.debug(CarVendorSensorManager.TAG, "break other properties ,except sensor");
                break;
        }
        Log.debug(CarVendorSensorManager.TAG, "convertCarVendorPropertyValue " + sSensorEventChangeValue.toString(), DEBUG_LOGGER);
        return sSensorEventChangeValue;
    }

    private OnCarSensorChangeEventListenerProxy findListenerLocked(CarVendorSensorManager.OnCarSensorEventListener listener) {
        for (OnCarSensorChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarSensorChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorSensorManager.OnCarSensorEventListener listener;

        public OnCarSensorChangeEventListenerProxy(CarVendorSensorManager.OnCarSensorEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorSensorMgr.this.traceCallbackPrepare();
                SensorEventChangeValue newValue = CarVendorSensorMgr.convertCarVendorProperty(value);
                CarVendorSensorMgr.this.traceCallbackBefore();
                this.listener.onSensorEventChange(newValue);
                CarVendorSensorMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorSensorManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorSensorManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onSensorErrorEvent(propertyId, zone);
        }
    }
}
