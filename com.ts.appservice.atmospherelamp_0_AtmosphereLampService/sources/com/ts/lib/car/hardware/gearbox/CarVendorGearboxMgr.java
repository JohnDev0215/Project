package com.ts.lib.car.hardware.gearbox;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorGearboxMgr extends CarVendorManagerBase implements CarVendorGearboxManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static GearboxEventChangeValue sGearboxEventChangeValue = new GearboxEventChangeValue();
    private final LinkedList<OnCarGearboxChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtGearboxIds = {557857283, 557857285, 557860357, 557857284, 557857291, 557857292, 557857286, 557857287, 557857288, 557857290, 557857293};

    public CarVendorGearboxMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorGearboxManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorGearboxManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorGearboxManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorGearboxManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getBrakePedalStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857293, 0);
            Log.debug(CarVendorGearboxManager.TAG, "getBrakePedalStatusR value = " + value + " property: 0x" + tenToHex(557857293));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getEpbStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857288, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEpbStateR property: 0x");
            sb.append(tenToHex(557857288));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public void setEpbStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorGearboxManager.TAG, "setEpbStateS property: 0x" + tenToHex(557857289) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857289, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearboxAutoModeTypeTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857286, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearboxAutoModeTypeTwo property: 0x");
            sb.append(tenToHex(557857286));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getBrakeFluidLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857283, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakeFluidLevel property: 0x");
            sb.append(tenToHex(557857283));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getBrakePedalState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857285, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakePedalState property: 0x");
            sb.append(tenToHex(557857285));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearboxAutoModeType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860357, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearboxAutoModeType property: 0x");
            sb.append(tenToHex(557860357));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getParkBrakeSwitch() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857284, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getParkBrakeSwitch property: 0x");
            sb.append(tenToHex(557857284));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearboxCode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857291, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearboxCode property: 0x");
            sb.append(tenToHex(557857291));
            sb.append(" class: Integer value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            return (value != null ? Integer.valueOf(CastArrayUtil.toPrimitive(value.getValue())) : null).intValue();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearboxManualModeLevelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857287, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearboxManualModeLevelR property: 0x");
            sb.append(tenToHex(557857287));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearboxType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857292, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearboxType property: 0x");
            sb.append(tenToHex(557857292));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public int getGearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857290, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGearR property: 0x");
            sb.append(tenToHex(557857290));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorGearboxManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public void addCarGearboxEventListener(CarVendorGearboxManager.OnCarGearboxEventListener listener) throws CarNotConnectedException {
        OnCarGearboxChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarGearboxChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorGearboxManager.TAG, "addCarGearboxEventListener propId size: " + this.mExtGearboxIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtGearboxIds) {
                int propId = num.intValue();
                Log.debug(CarVendorGearboxManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.gearbox.CarVendorGearboxManager
    public void removeCarGearboxEventListener(CarVendorGearboxManager.OnCarGearboxEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarGearboxChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorGearboxManager.TAG, "removeCarGearboxEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GearboxEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sGearboxEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorGearboxManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        android.util.Log.e(CarVendorGearboxManager.TAG, "carlib ota GearboxEventChangeValue value is " + value.getPropertyId());
        if (propertyId != 557860357) {
            switch (propertyId) {
                case 557857283:
                    sGearboxEventChangeValue.setBrakeFluidLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557857284:
                    sGearboxEventChangeValue.setParkBrakeSwitch(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557857285:
                    sGearboxEventChangeValue.setBrakePedalState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557857286:
                    sGearboxEventChangeValue.setGearboxAutoModeTypeTwo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557857287:
                    sGearboxEventChangeValue.setGearboxManualModeLevelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557857288:
                    sGearboxEventChangeValue.setEpbStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557857290:
                            sGearboxEventChangeValue.setGearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            android.util.Log.e(CarVendorGearboxManager.TAG, "carlib gearbox ID_GEAR value is " + value.getValue());
                            break;
                        case 557857291:
                            sGearboxEventChangeValue.setGearboxCode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557857292:
                            sGearboxEventChangeValue.setGearboxType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557857293:
                            sGearboxEventChangeValue.setBrakePedalStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            Log.debug(CarVendorGearboxManager.TAG, "break other properties ,except gearbox");
                            break;
                    }
                    break;
            }
        } else {
            sGearboxEventChangeValue.setGearboxAutoModeType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
        }
        android.util.Log.e(CarVendorGearboxManager.TAG, "convertCarVendorProperty is end");
        Log.debug(CarVendorGearboxManager.TAG, "convertCarVendorPropertyValue " + sGearboxEventChangeValue.toString(), DEBUG_LOGGER);
        return sGearboxEventChangeValue;
    }

    private OnCarGearboxChangeEventListenerProxy findListenerLocked(CarVendorGearboxManager.OnCarGearboxEventListener listener) {
        for (OnCarGearboxChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarGearboxChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorGearboxManager.OnCarGearboxEventListener listener;

        public OnCarGearboxChangeEventListenerProxy(CarVendorGearboxManager.OnCarGearboxEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorGearboxMgr.this.traceCallbackPrepare();
                GearboxEventChangeValue newValue = CarVendorGearboxMgr.convertCarVendorProperty(value);
                CarVendorGearboxMgr.this.traceCallbackBefore();
                android.util.Log.e(CarVendorGearboxManager.TAG, "traceCallbackBefore is strat");
                this.listener.onGearboxEventChange(newValue);
                android.util.Log.e(CarVendorGearboxManager.TAG, "traceCallbackBefore is end");
                CarVendorGearboxMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorGearboxManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorGearboxManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onGearboxErrorEvent(propertyId, zone);
        }
    }
}
