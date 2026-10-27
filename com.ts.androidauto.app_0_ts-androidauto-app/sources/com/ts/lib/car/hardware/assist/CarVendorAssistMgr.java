package com.ts.lib.car.hardware.assist;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.assist.CarVendorAssistManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorAssistMgr extends CarVendorManagerBase implements CarVendorAssistManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static AssistEventChangeValue sAssistEventChangeValue = new AssistEventChangeValue();
    private final LinkedList<OnCarAssistChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtAssistIds = {557969440};

    public CarVendorAssistMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), "persist.car.vendor.log.assist", 0) == 1;
        Log.info(CarVendorAssistManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorAssistManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorAssistManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.assist.CarVendorAssistManager
    public int[] getRtcTime() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, 557969440, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRtcTime property: 0x");
            sb.append(tenToHex(557969440));
            sb.append(" class: int[] value: ");
            if (value == null) {
                string = null;
            } else {
                string = CastArrayUtil.parseString(value.getValue());
            }
            sb.append(string);
            Log.debug(CarVendorAssistManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.assist.CarVendorAssistManager
    public void setRtcTime(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorAssistManager.TAG, "setRtcTime property: 0x" + tenToHex(557969440) + " class: int[] value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, 557969440, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.assist.CarVendorAssistManager
    public void addCarAssistEventListener(CarVendorAssistManager.OnCarAssistEventListener listener) throws CarNotConnectedException {
        OnCarAssistChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarAssistChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorAssistManager.TAG, "addCarAssistEventListener propId size: " + this.mExtAssistIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtAssistIds) {
                int propId = num.intValue();
                Log.debug(CarVendorAssistManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.assist.CarVendorAssistManager
    public void removeCarAssistEventListener(CarVendorAssistManager.OnCarAssistEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarAssistChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorAssistManager.TAG, "removeCarAssistEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AssistEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sAssistEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorAssistManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId == 557969440) {
            sAssistEventChangeValue.setRtcTime(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
        } else {
            Log.debug(CarVendorAssistManager.TAG, "break other properties ,except assist");
        }
        Log.debug(CarVendorAssistManager.TAG, "convertCarVendorPropertyValue " + sAssistEventChangeValue.toString(), DEBUG_LOGGER);
        return sAssistEventChangeValue;
    }

    private OnCarAssistChangeEventListenerProxy findListenerLocked(CarVendorAssistManager.OnCarAssistEventListener listener) {
        for (OnCarAssistChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarAssistChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorAssistManager.OnCarAssistEventListener listener;

        public OnCarAssistChangeEventListenerProxy(CarVendorAssistManager.OnCarAssistEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorAssistMgr.this.traceCallbackPrepare();
                AssistEventChangeValue newValue = CarVendorAssistMgr.convertCarVendorProperty(value);
                CarVendorAssistMgr.this.traceCallbackBefore();
                this.listener.onAssistEventChange(newValue);
                CarVendorAssistMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorAssistManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorAssistManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onAssistErrorEvent(propertyId, zone);
        }
    }
}
