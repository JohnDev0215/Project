package com.ts.lib.car.hardware.doorlock;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes2.dex */
public class CarVendorDoorlockMgr extends CarVendorManagerBase implements CarVendorDoorlockManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static DoorlockEventChangeValue sDoorlockEventChangeValue = new DoorlockEventChangeValue();
    private final LinkedList<OnCarDoorlockChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtDoorlockIds = {557856513, 557856525, 557856526, 557856514, 557856521, 557856516, 557856523, 557856515, 557856522, 557856517, 557856524, 557856512, 557856528, 557856531};

    public CarVendorDoorlockMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorDoorlockManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorDoorlockManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorDoorlockManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorDoorlockManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockCockpitHatchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856531, 0);
            Log.debug(CarVendorDoorlockManager.TAG, "getDoorLockCockpitHatchStateR value = " + value + " property: 0x" + tenToHex(557856531));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getLeftChildLockStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDoorlockManager.ID_LEFT_CHILD_LOCK_STATE_R, 0);
            Log.debug(CarVendorDoorlockManager.TAG, "getLeftChildLockStateR value = " + value + " property: 0x" + tenToHex(CarVendorDoorlockManager.ID_LEFT_CHILD_LOCK_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getRightChildLockStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorDoorlockManager.ID_RIGHT_CHILD_LOCK_STATE_R, 0);
            Log.debug(CarVendorDoorlockManager.TAG, "getRightChildLockStateR value = " + value + " property: 0x" + tenToHex(CarVendorDoorlockManager.ID_RIGHT_CHILD_LOCK_STATE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusAreaChildLockLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856512, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaChildLockLeftR property: 0x");
            sb.append(tenToHex(557856512));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void setAllDoorsLock(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDoorlockManager.TAG, "setAllDoorsLock property: 0x" + tenToHex(557856519) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856519, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusAreaBack() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856513, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaBack property: 0x");
            sb.append(tenToHex(557856513));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusAreaChildLockRight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856526, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaChildLockRight property: 0x");
            sb.append(tenToHex(557856526));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void setDoorLockStatusAreaChildlockLeftS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDoorlockManager.TAG, "setDoorLockStatusAreaChildlockLeftS property: 0x" + tenToHex(557856518) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856518, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void setRearWindowLockS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDoorlockManager.TAG, "setRearWindowLockS value = " + value + " property: 0x" + tenToHex(557856527));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856527, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void setDoorLockStatusAreaChildlockRightStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorDoorlockManager.TAG, "setDoorLockStatusAreaChildlockRightStatusS property: 0x" + tenToHex(557856520) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856520, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusAreaLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856514, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaLeftFront property: 0x");
            sb.append(tenToHex(557856514));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusAreaRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856517, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaRightRear property: 0x");
            sb.append(tenToHex(557856517));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856516, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusLeftRear property: 0x");
            sb.append(tenToHex(557856516));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorLockStatusRightFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856515, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusRightFront property: 0x");
            sb.append(tenToHex(557856515));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorStatusAreaBackStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856525, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStatusAreaBackStatusR property: 0x");
            sb.append(tenToHex(557856525));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorStatusAreaLeftFrontStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856521, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStatusAreaLeftFrontStatusR property: 0x");
            sb.append(tenToHex(557856521));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorStatusAreaLeftRearStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856523, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStatusAreaLeftRearStatusR property: 0x");
            sb.append(tenToHex(557856523));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorStatusAreaRightFrontStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856522, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStatusAreaRightFrontStatusR property: 0x");
            sb.append(tenToHex(557856522));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getDoorStatusAreaRightRearStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856524, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorStatusAreaRightRearStatusR property: 0x");
            sb.append(tenToHex(557856524));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorDoorlockManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public int getLockHeatProtectedStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856528, 0);
            Log.debug(CarVendorDoorlockManager.TAG, "getLockHeatProtectedStateR value = " + value + " property: 0x" + tenToHex(557856528), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void addCarDoorlockEventListener(CarVendorDoorlockManager.OnCarDoorlockEventListener listener) throws CarNotConnectedException {
        OnCarDoorlockChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarDoorlockChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorDoorlockManager.TAG, "addCarDoorlockEventListener propId size: " + this.mExtDoorlockIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtDoorlockIds) {
                int propId = num.intValue();
                Log.debug(CarVendorDoorlockManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.doorlock.CarVendorDoorlockManager
    public void removeCarDoorlockEventListener(CarVendorDoorlockManager.OnCarDoorlockEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarDoorlockChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorDoorlockManager.TAG, "removeCarDoorlockEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DoorlockEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sDoorlockEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorDoorlockManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557856512:
                sDoorlockEventChangeValue.setDoorLockStatusAreaChildLockLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856513:
                sDoorlockEventChangeValue.setDoorLockStatusAreaBack(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856514:
                sDoorlockEventChangeValue.setDoorLockStatusAreaLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856515:
                sDoorlockEventChangeValue.setDoorLockStatusRightFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856516:
                sDoorlockEventChangeValue.setDoorLockStatusLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856517:
                sDoorlockEventChangeValue.setDoorLockStatusAreaRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856518:
                sDoorlockEventChangeValue.setDoorLockStatusAreaChildlockLeftS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856519:
                sDoorlockEventChangeValue.setAllDoorsLock(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856520:
                sDoorlockEventChangeValue.setDoorLockStatusAreaChildlockRightStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856521:
                sDoorlockEventChangeValue.setDoorStatusAreaLeftFrontStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856522:
                sDoorlockEventChangeValue.setDoorStatusAreaRightFrontStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856523:
                sDoorlockEventChangeValue.setDoorStatusAreaLeftRearStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856524:
                sDoorlockEventChangeValue.setDoorStatusAreaRightRearStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856525:
                sDoorlockEventChangeValue.setDoorStatusAreaBackStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856526:
                sDoorlockEventChangeValue.setDoorLockStatusAreaChildLockRight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856527:
                sDoorlockEventChangeValue.setRearWindowLockS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557856528:
                sDoorlockEventChangeValue.setLockHeatProtectedStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorDoorlockManager.ID_LEFT_CHILD_LOCK_STATE_R /* 557856529 */:
            case CarVendorDoorlockManager.ID_RIGHT_CHILD_LOCK_STATE_R /* 557856530 */:
            default:
                Log.debug(CarVendorDoorlockManager.TAG, "break other properties ,except doorlock");
                break;
            case 557856531:
                sDoorlockEventChangeValue.setDoorLockCockpitHatchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
        }
        Log.debug(CarVendorDoorlockManager.TAG, "convertCarVendorPropertyValue " + sDoorlockEventChangeValue.toString(), DEBUG_LOGGER);
        return sDoorlockEventChangeValue;
    }

    private OnCarDoorlockChangeEventListenerProxy findListenerLocked(CarVendorDoorlockManager.OnCarDoorlockEventListener listener) {
        for (OnCarDoorlockChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarDoorlockChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorDoorlockManager.OnCarDoorlockEventListener listener;

        public OnCarDoorlockChangeEventListenerProxy(CarVendorDoorlockManager.OnCarDoorlockEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorDoorlockMgr.this.traceCallbackPrepare();
                DoorlockEventChangeValue newValue = CarVendorDoorlockMgr.convertCarVendorProperty(value);
                CarVendorDoorlockMgr.this.traceCallbackBefore();
                this.listener.onDoorlockEventChange(newValue);
                CarVendorDoorlockMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorDoorlockManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorDoorlockManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onDoorlockErrorEvent(propertyId, zone);
        }
    }
}
