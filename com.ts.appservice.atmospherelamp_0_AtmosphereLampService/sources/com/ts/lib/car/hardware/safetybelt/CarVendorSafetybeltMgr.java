package com.ts.lib.car.hardware.safetybelt;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes3.dex */
public class CarVendorSafetybeltMgr extends CarVendorManagerBase implements CarVendorSafetybeltManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static SafetybeltEventChangeValue sSafetybeltEventChangeValue = new SafetybeltEventChangeValue();
    private final LinkedList<OnCarSafetybeltChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtSafetybeltIds = {557860865, 557860869, 557860866, 557860864, 557860870, 557860867, 557860871, 557860868, 557860875, 557860876};

    public CarVendorSafetybeltMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorSafetybeltManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorSafetybeltManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorSafetybeltManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorSafetybeltManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getMainSeatStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860876, 0);
            Log.debug(CarVendorSafetybeltManager.TAG, "getMainSeatStateR value = " + value + " property: 0x" + tenToHex(557860876));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getDriverBeltStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860875, 0);
            Log.debug(CarVendorSafetybeltManager.TAG, "getDriverBeltStatusR value = " + value + " property: 0x" + tenToHex(557860875));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusDeputy() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860865, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusDeputy property: 0x");
            sb.append(tenToHex(557860865));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusLeftThreeRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860869, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusLeftThreeRear property: 0x");
            sb.append(tenToHex(557860869));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusLeftTwoRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860866, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusLeftTwoRear property: 0x");
            sb.append(tenToHex(557860866));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusMain() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860864, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusMain property: 0x");
            sb.append(tenToHex(557860864));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusMidThreeRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860870, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusMidThreeRear property: 0x");
            sb.append(tenToHex(557860870));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusMidTwoRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860867, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusMidTwoRear property: 0x");
            sb.append(tenToHex(557860867));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusRightThreeRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860871, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusRightThreeRear property: 0x");
            sb.append(tenToHex(557860871));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public int getSafetyBeltStatusRightTwoRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860868, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSafetyBeltStatusRightTwoRear property: 0x");
            sb.append(tenToHex(557860868));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorSafetybeltManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public void addCarSafetybeltEventListener(CarVendorSafetybeltManager.OnCarSafetybeltEventListener listener) throws CarNotConnectedException {
        OnCarSafetybeltChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarSafetybeltChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorSafetybeltManager.TAG, "addCarSafetybeltEventListener propId size: " + this.mExtSafetybeltIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtSafetybeltIds) {
                int propId = num.intValue();
                Log.debug(CarVendorSafetybeltManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.safetybelt.CarVendorSafetybeltManager
    public void removeCarSafetybeltEventListener(CarVendorSafetybeltManager.OnCarSafetybeltEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarSafetybeltChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorSafetybeltManager.TAG, "removeCarSafetybeltEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SafetybeltEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sSafetybeltEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorSafetybeltManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557860864:
                sSafetybeltEventChangeValue.setSafetyBeltStatusMain(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860865:
                sSafetybeltEventChangeValue.setSafetyBeltStatusDeputy(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860866:
                sSafetybeltEventChangeValue.setSafetyBeltStatusLeftTwoRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860867:
                sSafetybeltEventChangeValue.setSafetyBeltStatusMidTwoRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860868:
                sSafetybeltEventChangeValue.setSafetyBeltStatusRightTwoRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860869:
                sSafetybeltEventChangeValue.setSafetyBeltStatusLeftThreeRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860870:
                sSafetybeltEventChangeValue.setSafetyBeltStatusMidThreeRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860871:
                sSafetybeltEventChangeValue.setSafetyBeltStatusRightThreeRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860872:
            case 557860873:
            case 557860874:
            default:
                Log.debug(CarVendorSafetybeltManager.TAG, "break other properties ,except safetybelt");
                break;
            case 557860875:
                sSafetybeltEventChangeValue.setDriverBeltStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860876:
                sSafetybeltEventChangeValue.setMainSeatStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
        }
        Log.debug(CarVendorSafetybeltManager.TAG, "convertCarVendorPropertyValue " + sSafetybeltEventChangeValue.toString(), DEBUG_LOGGER);
        return sSafetybeltEventChangeValue;
    }

    private OnCarSafetybeltChangeEventListenerProxy findListenerLocked(CarVendorSafetybeltManager.OnCarSafetybeltEventListener listener) {
        for (OnCarSafetybeltChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarSafetybeltChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorSafetybeltManager.OnCarSafetybeltEventListener listener;

        public OnCarSafetybeltChangeEventListenerProxy(CarVendorSafetybeltManager.OnCarSafetybeltEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorSafetybeltMgr.this.traceCallbackPrepare();
                SafetybeltEventChangeValue newValue = CarVendorSafetybeltMgr.convertCarVendorProperty(value);
                CarVendorSafetybeltMgr.this.traceCallbackBefore();
                this.listener.onSafetybeltEventChange(newValue);
                CarVendorSafetybeltMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorSafetybeltManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorSafetybeltManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onSafetybeltErrorEvent(propertyId, zone);
        }
    }
}
