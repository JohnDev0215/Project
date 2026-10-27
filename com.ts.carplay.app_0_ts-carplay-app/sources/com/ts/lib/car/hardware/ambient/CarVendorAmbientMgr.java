package com.ts.lib.car.hardware.ambient;

import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.ambient.CarVendorAmbientManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorAmbientMgr extends CarVendorManagerBase implements CarVendorAmbientManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static AmbientEventChangeValue sAmbientEventChangeValue = new AmbientEventChangeValue();
    private final LinkedList<OnCarAmbientChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtAmbientIds = new Integer[0];

    public CarVendorAmbientMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorAmbientManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorAmbientManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorAmbientManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorAmbientManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void setFrequencyPoint(int[] value) throws com.ts.lib.car.CarNotConnectedException {
        Log.debug(CarVendorAmbientManager.TAG, "setFrequencyPoint property: 0x" + tenToHex(CarVendorAmbientManager.ID_FREQUENCY_POINT) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorAmbientManager.ID_FREQUENCY_POINT, 0, CastArrayUtil.toWrap(value));
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new com.ts.lib.car.CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void setKtvResourceS(int value) throws com.ts.lib.car.CarNotConnectedException {
        Log.debug(CarVendorAmbientManager.TAG, "setKtvResourceS value = " + value + " property: 0x" + tenToHex(CarVendorAmbientManager.ID_KTV_RESOURCE_S), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorAmbientManager.ID_KTV_RESOURCE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new com.ts.lib.car.CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void setMediaWorkingModeS(int value) throws com.ts.lib.car.CarNotConnectedException {
        Log.debug(CarVendorAmbientManager.TAG, "setMediaWorkingModeS property: 0x" + tenToHex(CarVendorAmbientManager.ID_MEDIA_WORKING_MODE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorAmbientManager.ID_MEDIA_WORKING_MODE_S, 0, CastArrayUtil.toWrap(value));
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new com.ts.lib.car.CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void setMediaWorkingStatusS(int value) throws com.ts.lib.car.CarNotConnectedException {
        Log.debug(CarVendorAmbientManager.TAG, "setMediaWorkingStatusS property: 0x" + tenToHex(CarVendorAmbientManager.ID_MEDIA_WORKING_STATUS_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorAmbientManager.ID_MEDIA_WORKING_STATUS_S, 0, CastArrayUtil.toWrap(value));
        } catch (CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new com.ts.lib.car.CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void addCarAmbientEventListener(CarVendorAmbientManager.OnCarAmbientEventListener listener) throws com.ts.lib.car.CarNotConnectedException {
        OnCarAmbientChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarAmbientChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorAmbientManager.TAG, "addCarAmbientEventListener propId size: " + this.mExtAmbientIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtAmbientIds) {
                int propId = num.intValue();
                Log.debug(CarVendorAmbientManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (CarNotConnectedException ex) {
            throw new com.ts.lib.car.CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ambient.CarVendorAmbientManager
    public void removeCarAmbientEventListener(CarVendorAmbientManager.OnCarAmbientEventListener listener) throws com.ts.lib.car.CarNotConnectedException {
        synchronized (this) {
            OnCarAmbientChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorAmbientManager.TAG, "removeCarAmbientEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new com.ts.lib.car.CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AmbientEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        value.getAreaId();
        sAmbientEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorAmbientManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case CarVendorAmbientManager.ID_MEDIA_WORKING_MODE_S /* 557880322 */:
                sAmbientEventChangeValue.setMediaWorkingModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorAmbientManager.ID_MEDIA_WORKING_STATUS_S /* 557880323 */:
                sAmbientEventChangeValue.setMediaWorkingStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorAmbientManager.ID_FREQUENCY_POINT /* 557945856 */:
                sAmbientEventChangeValue.setFrequencyPoint(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                break;
            default:
                Log.debug(CarVendorAmbientManager.TAG, "break other properties ,except ambient");
                break;
        }
        Log.debug(CarVendorAmbientManager.TAG, "convertCarVendorPropertyValue " + sAmbientEventChangeValue.toString(), DEBUG_LOGGER);
        return sAmbientEventChangeValue;
    }

    private OnCarAmbientChangeEventListenerProxy findListenerLocked(CarVendorAmbientManager.OnCarAmbientEventListener listener) {
        for (OnCarAmbientChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarAmbientChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorAmbientManager.OnCarAmbientEventListener listener;

        public OnCarAmbientChangeEventListenerProxy(CarVendorAmbientManager.OnCarAmbientEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorAmbientMgr.this.traceCallbackPrepare();
                AmbientEventChangeValue newValue = CarVendorAmbientMgr.convertCarVendorProperty(value);
                CarVendorAmbientMgr.this.traceCallbackBefore();
                this.listener.onAmbientEventChange(newValue);
                CarVendorAmbientMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorAmbientManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorAmbientManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onAmbientErrorEvent(propertyId, zone);
        }
    }
}
