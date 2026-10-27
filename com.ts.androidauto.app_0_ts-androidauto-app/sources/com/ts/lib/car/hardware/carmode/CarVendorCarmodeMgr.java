package com.ts.lib.car.hardware.carmode;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorCarmodeMgr extends CarVendorManagerBase implements CarVendorCarmodeManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static CarmodeEventChangeValue sCarmodeEventChangeValue = new CarmodeEventChangeValue();
    private final LinkedList<OnCarCarmodeChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtCarmodeIds = {557871105, Integer.valueOf(CarVendorCarmodeManager.ID_SHOW_CAR_GEAR_CHANGER), 557871104, 557871106, 557871107};

    public CarVendorCarmodeMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorCarmodeManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorCarmodeManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorCarmodeManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorCarmodeManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public int getCarEngineTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557871105, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarEngineTypeR property: 0x");
            sb.append(tenToHex(557871105));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarmodeManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public void setEngineEnableS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorCarmodeManager.TAG, "setEngineEnableS property: 0x" + tenToHex(557871108) + " class: boolean value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557871108, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public int getShowCarGearChangeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorCarmodeManager.ID_SHOW_CAR_GEAR_CHANGER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getShowCarGearChangeR property: 0x");
            sb.append(tenToHex(CarVendorCarmodeManager.ID_SHOW_CAR_GEAR_CHANGER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarmodeManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public int getShowCarModelTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557871104, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getShowCarModelTypeR property: 0x");
            sb.append(tenToHex(557871104));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarmodeManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public int getAirCtrlChangeEventR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557871106, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirCtrlChangeEventR property: 0x");
            sb.append(tenToHex(557871106));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarmodeManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public int getAirCtrlPressEventR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557871107, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAirCtrlPressEventR property: 0x");
            sb.append(tenToHex(557871107));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCarmodeManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public void addCarCarmodeEventListener(CarVendorCarmodeManager.OnCarCarmodeEventListener listener) throws CarNotConnectedException {
        OnCarCarmodeChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarCarmodeChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorCarmodeManager.TAG, "addCarCarmodeEventListener propId size: " + this.mExtCarmodeIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtCarmodeIds) {
                int propId = num.intValue();
                Log.debug(CarVendorCarmodeManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.carmode.CarVendorCarmodeManager
    public void removeCarCarmodeEventListener(CarVendorCarmodeManager.OnCarCarmodeEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarCarmodeChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorCarmodeManager.TAG, "removeCarCarmodeEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CarmodeEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sCarmodeEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorCarmodeManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557871104:
                sCarmodeEventChangeValue.setShowCarModelTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557871105:
                sCarmodeEventChangeValue.setCarEngineTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557871106:
                sCarmodeEventChangeValue.setAirCtrlChangeEventR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557871107:
                sCarmodeEventChangeValue.setAirCtrlPressEventR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557871108:
                sCarmodeEventChangeValue.setEngineEnableS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorCarmodeManager.ID_SHOW_CAR_GEAR_CHANGER /* 557871109 */:
                sCarmodeEventChangeValue.setShowCarGearChangeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                Log.debug(CarVendorCarmodeManager.TAG, "break other properties ,except carmode");
                break;
        }
        Log.debug(CarVendorCarmodeManager.TAG, "convertCarVendorPropertyValue " + sCarmodeEventChangeValue.toString(), DEBUG_LOGGER);
        return sCarmodeEventChangeValue;
    }

    private OnCarCarmodeChangeEventListenerProxy findListenerLocked(CarVendorCarmodeManager.OnCarCarmodeEventListener listener) {
        for (OnCarCarmodeChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarCarmodeChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorCarmodeManager.OnCarCarmodeEventListener listener;

        public OnCarCarmodeChangeEventListenerProxy(CarVendorCarmodeManager.OnCarCarmodeEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorCarmodeMgr.this.traceCallbackPrepare();
                CarmodeEventChangeValue newValue = CarVendorCarmodeMgr.convertCarVendorProperty(value);
                CarVendorCarmodeMgr.this.traceCallbackBefore();
                this.listener.onCarmodeEventChange(newValue);
                CarVendorCarmodeMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorCarmodeManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorCarmodeManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onCarmodeErrorEvent(propertyId, zone);
        }
    }
}
