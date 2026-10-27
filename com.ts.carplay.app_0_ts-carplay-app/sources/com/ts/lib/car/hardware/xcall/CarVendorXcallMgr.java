package com.ts.lib.car.hardware.xcall;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.xcall.CarVendorXcallManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorXcallMgr extends CarVendorManagerBase implements CarVendorXcallManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static XcallEventChangeValue sXcallEventChangeValue = new XcallEventChangeValue();
    private final LinkedList<OnCarXcallChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtXcallIds = {557872644, 557872643, 557872642};

    public CarVendorXcallMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorXcallManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorXcallManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorXcallManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorXcallManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public int getCallBackModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872644, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCallBackModeR property: 0x");
            sb.append(tenToHex(557872644));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorXcallManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public int getCallStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872643, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCallStatusR property: 0x");
            sb.append(tenToHex(557872643));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorXcallManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public int getCallTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872642, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCallTypeR property: 0x");
            sb.append(tenToHex(557872642));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorXcallManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public void setCallTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorXcallManager.TAG, "setCallTypeS property: 0x" + tenToHex(557872640) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872640, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public void setRequestTypeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorXcallManager.TAG, "setRequestTypeS property: 0x" + tenToHex(557872641) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557872641, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public void addCarXcallEventListener(CarVendorXcallManager.OnCarXcallEventListener listener) throws CarNotConnectedException {
        OnCarXcallChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarXcallChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorXcallManager.TAG, "addCarXcallEventListener propId size: " + this.mExtXcallIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtXcallIds) {
                int propId = num.intValue();
                Log.debug(CarVendorXcallManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.xcall.CarVendorXcallManager
    public void removeCarXcallEventListener(CarVendorXcallManager.OnCarXcallEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarXcallChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorXcallManager.TAG, "removeCarXcallEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static XcallEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sXcallEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorXcallManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557872640:
                sXcallEventChangeValue.setCallTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872641:
                sXcallEventChangeValue.setRequestTypeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872642:
                sXcallEventChangeValue.setCallTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872643:
                sXcallEventChangeValue.setCallStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557872644:
                sXcallEventChangeValue.setCallBackModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                Log.debug(CarVendorXcallManager.TAG, "break other properties ,except xcall");
                break;
        }
        Log.debug(CarVendorXcallManager.TAG, "convertCarVendorPropertyValue " + sXcallEventChangeValue.toString(), DEBUG_LOGGER);
        return sXcallEventChangeValue;
    }

    private OnCarXcallChangeEventListenerProxy findListenerLocked(CarVendorXcallManager.OnCarXcallEventListener listener) {
        for (OnCarXcallChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarXcallChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorXcallManager.OnCarXcallEventListener listener;

        public OnCarXcallChangeEventListenerProxy(CarVendorXcallManager.OnCarXcallEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            try {
                CarVendorXcallMgr.this.traceCallbackPrepare();
                XcallEventChangeValue newValue = CarVendorXcallMgr.convertCarVendorProperty(value);
                CarVendorXcallMgr.this.traceCallbackBefore();
                this.listener.onXcallEventChange(newValue);
                CarVendorXcallMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorXcallManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorXcallManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onXcallErrorEvent(propertyId, zone);
        }
    }
}
