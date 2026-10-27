package com.ts.lib.car.hardware.pm2p5;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorPm2p5Mgr extends CarVendorManagerBase implements CarVendorPm2p5Manager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static Pm2p5EventChangeValue sPm2p5EventChangeValue = new Pm2p5EventChangeValue();
    private final LinkedList<OnCarPm2p5ChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtPm2p5Ids = {557854722, 557854723, 557854720, 557854728, 557854729, 557854725, 557854726, 557854731, 557854727};

    public CarVendorPm2p5Mgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorPm2p5Manager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorPm2p5Manager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorPm2p5Manager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorPm2p5Manager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5AnionState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854727, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5AnionState property: 0x");
            sb.append(tenToHex(557854727));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5WarningInfo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854731, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5WarningInfo property: 0x");
            sb.append(tenToHex(557854731));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public void setPm2p5AnionDetectState(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPm2p5Manager.TAG, "setPm2p5AnionDetectState property: 0x" + tenToHex(557854730) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557854730, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public void setPm2p5CheckState(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPm2p5Manager.TAG, "setPm2p5CheckState property: 0x" + tenToHex(557854724) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557854724, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5CheckStateIn() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854722, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5CheckStateIn property: 0x");
            sb.append(tenToHex(557854722));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5CheckStateOut() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854723, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5CheckStateOut property: 0x");
            sb.append(tenToHex(557854723));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5DualChannelDetectStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854720, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5DualChannelDetectStateR property: 0x");
            sb.append(tenToHex(557854720));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public void setPm2p5DualChannelDetectStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorPm2p5Manager.TAG, "setPm2p5DualChannelDetectStateS property: 0x" + tenToHex(557854721) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557854721, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5LevelIn() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854728, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5LevelIn property: 0x");
            sb.append(tenToHex(557854728));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5LevelOut() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854729, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5LevelOut property: 0x");
            sb.append(tenToHex(557854729));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5ValueIn() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854725, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5ValueIn property: 0x");
            sb.append(tenToHex(557854725));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public int getPm2p5ValueOut() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557854726, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPm2p5ValueOut property: 0x");
            sb.append(tenToHex(557854726));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorPm2p5Manager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public void addCarPm2p5EventListener(CarVendorPm2p5Manager.OnCarPm2p5EventListener listener) throws CarNotConnectedException {
        OnCarPm2p5ChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarPm2p5ChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorPm2p5Manager.TAG, "addCarPm2p5EventListener propId size: " + this.mExtPm2p5Ids.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtPm2p5Ids) {
                int propId = num.intValue();
                Log.debug(CarVendorPm2p5Manager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.pm2p5.CarVendorPm2p5Manager
    public void removeCarPm2p5EventListener(CarVendorPm2p5Manager.OnCarPm2p5EventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarPm2p5ChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorPm2p5Manager.TAG, "removeCarPm2p5EventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Pm2p5EventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sPm2p5EventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorPm2p5Manager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557854720:
                sPm2p5EventChangeValue.setPm2p5DualChannelDetectStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854721:
                sPm2p5EventChangeValue.setPm2p5DualChannelDetectStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854722:
                sPm2p5EventChangeValue.setPm2p5CheckStateIn(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854723:
                sPm2p5EventChangeValue.setPm2p5CheckStateOut(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854724:
                sPm2p5EventChangeValue.setPm2p5CheckState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854725:
                sPm2p5EventChangeValue.setPm2p5ValueIn(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854726:
                sPm2p5EventChangeValue.setPm2p5ValueOut(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854727:
                sPm2p5EventChangeValue.setPm2p5AnionState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854728:
                sPm2p5EventChangeValue.setPm2p5LevelIn(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854729:
                sPm2p5EventChangeValue.setPm2p5LevelOut(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854730:
                sPm2p5EventChangeValue.setPm2p5AnionDetectState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557854731:
                sPm2p5EventChangeValue.setPm2p5WarningInfo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                Log.debug(CarVendorPm2p5Manager.TAG, "break other properties ,except pm2p5");
                break;
        }
        Log.debug(CarVendorPm2p5Manager.TAG, "convertCarVendorPropertyValue " + sPm2p5EventChangeValue.toString(), DEBUG_LOGGER);
        return sPm2p5EventChangeValue;
    }

    private OnCarPm2p5ChangeEventListenerProxy findListenerLocked(CarVendorPm2p5Manager.OnCarPm2p5EventListener listener) {
        for (OnCarPm2p5ChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarPm2p5ChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorPm2p5Manager.OnCarPm2p5EventListener listener;

        public OnCarPm2p5ChangeEventListenerProxy(CarVendorPm2p5Manager.OnCarPm2p5EventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorPm2p5Mgr.this.traceCallbackPrepare();
                Pm2p5EventChangeValue newValue = CarVendorPm2p5Mgr.convertCarVendorProperty(value);
                CarVendorPm2p5Mgr.this.traceCallbackBefore();
                this.listener.onPm2p5EventChange(newValue);
                CarVendorPm2p5Mgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorPm2p5Manager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorPm2p5Manager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onPm2p5ErrorEvent(propertyId, zone);
        }
    }
}
