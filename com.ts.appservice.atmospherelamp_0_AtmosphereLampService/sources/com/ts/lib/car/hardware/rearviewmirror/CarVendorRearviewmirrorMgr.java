package com.ts.lib.car.hardware.rearviewmirror;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorRearviewmirrorMgr extends CarVendorManagerBase implements CarVendorRearviewmirrorManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static RearviewmirrorEventChangeValue sRearviewmirrorEventChangeValue = new RearviewmirrorEventChangeValue();
    private final LinkedList<OnCarRearviewmirrorChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtRearviewmirrorIds = {557858049, 557858048};

    public CarVendorRearviewmirrorMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorRearviewmirrorManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorRearviewmirrorManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorRearviewmirrorManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorRearviewmirrorManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager
    public int getAutoExternalRearMirrorFoldActionStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858048, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFoldActionStateR property: 0x");
            sb.append(tenToHex(557858048));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRearviewmirrorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager
    public void setAutoExternalRearMirrorFoldStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorRearviewmirrorManager.TAG, "setAutoExternalRearMirrorFoldStateS property: 0x" + tenToHex(557858050) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557858050, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager
    public int getAutoExternalRearMirrorFoldedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557858049, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFoldedStateR property: 0x");
            sb.append(tenToHex(557858049));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorRearviewmirrorManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager
    public void addCarRearviewmirrorEventListener(CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener listener) throws CarNotConnectedException {
        OnCarRearviewmirrorChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarRearviewmirrorChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorRearviewmirrorManager.TAG, "addCarRearviewmirrorEventListener propId size: " + this.mExtRearviewmirrorIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtRearviewmirrorIds) {
                int propId = num.intValue();
                Log.debug(CarVendorRearviewmirrorManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.rearviewmirror.CarVendorRearviewmirrorManager
    public void removeCarRearviewmirrorEventListener(CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarRearviewmirrorChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorRearviewmirrorManager.TAG, "removeCarRearviewmirrorEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static RearviewmirrorEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sRearviewmirrorEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorRearviewmirrorManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557858048:
                sRearviewmirrorEventChangeValue.setAutoExternalRearMirrorFoldActionStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858049:
                sRearviewmirrorEventChangeValue.setAutoExternalRearMirrorFoldedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557858050:
                sRearviewmirrorEventChangeValue.setAutoExternalRearMirrorFoldStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                Log.debug(CarVendorRearviewmirrorManager.TAG, "break other properties ,except rearviewmirror");
                break;
        }
        Log.debug(CarVendorRearviewmirrorManager.TAG, "convertCarVendorPropertyValue " + sRearviewmirrorEventChangeValue.toString(), DEBUG_LOGGER);
        return sRearviewmirrorEventChangeValue;
    }

    private OnCarRearviewmirrorChangeEventListenerProxy findListenerLocked(CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener listener) {
        for (OnCarRearviewmirrorChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarRearviewmirrorChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener listener;

        public OnCarRearviewmirrorChangeEventListenerProxy(CarVendorRearviewmirrorManager.OnCarRearviewmirrorEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorRearviewmirrorMgr.this.traceCallbackPrepare();
                RearviewmirrorEventChangeValue newValue = CarVendorRearviewmirrorMgr.convertCarVendorProperty(value);
                CarVendorRearviewmirrorMgr.this.traceCallbackBefore();
                this.listener.onRearviewmirrorEventChange(newValue);
                CarVendorRearviewmirrorMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorRearviewmirrorManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorRearviewmirrorManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onRearviewmirrorErrorEvent(propertyId, zone);
        }
    }
}
