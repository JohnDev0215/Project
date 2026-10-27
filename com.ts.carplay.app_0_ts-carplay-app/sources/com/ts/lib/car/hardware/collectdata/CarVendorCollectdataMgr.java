package com.ts.lib.car.hardware.collectdata;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorCollectdataMgr extends CarVendorManagerBase implements CarVendorCollectdataManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static CollectdataEventChangeValue sCollectdataEventChangeValue = new CollectdataEventChangeValue();
    private final LinkedList<OnCarCollectdataChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtCollectdataIds = {557872141, 557872139, 557872128, 557872129, 557872132, 557872133, 557872130, 557872131, 559969290, 557872136, 557872137, 557872134, 557872135, 557872140, 557872142};

    public CarVendorCollectdataMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorCollectdataManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorCollectdataManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorCollectdataManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorCollectdataManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDmVersionCodeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872142, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDmVersionCodeR property: 0x");
            sb.append(tenToHex(557872142));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getCollision() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872141, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCollision property: 0x");
            sb.append(tenToHex(557872141));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDCChargingFault() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872139, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDCChargingFault property: 0x");
            sb.append(tenToHex(557872139));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorSpeedFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872128, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorSpeedFront property: 0x");
            sb.append(tenToHex(557872128));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorSpeedRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872129, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorSpeedRear property: 0x");
            sb.append(tenToHex(557872129));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorTemperatureFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872132, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorTemperatureFront property: 0x");
            sb.append(tenToHex(557872132));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorTemperatureRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872133, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorTemperatureRear property: 0x");
            sb.append(tenToHex(557872133));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorTorqueFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872130, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorTorqueFront property: 0x");
            sb.append(tenToHex(557872130));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getDriverMotorTorqueRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872131, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverMotorTorqueRear property: 0x");
            sb.append(tenToHex(557872131));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public float getEngineSpeed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559969290, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineSpeed property: 0x");
            sb.append(tenToHex(559969290));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getMotorMCUGeneratrixCurrentFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872136, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorMCUGeneratrixCurrentFront property: 0x");
            sb.append(tenToHex(557872136));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getMotorMCUGeneratrixCurrentRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872137, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorMCUGeneratrixCurrentRear property: 0x");
            sb.append(tenToHex(557872137));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getMotorMCUGeneratrixVoltFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872134, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorMCUGeneratrixVoltFront property: 0x");
            sb.append(tenToHex(557872134));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getMotorMCUGeneratrixVoltRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872135, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorMCUGeneratrixVoltRear property: 0x");
            sb.append(tenToHex(557872135));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int getWaterTemperationAlarm() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557872140, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWaterTemperationAlarm property: 0x");
            sb.append(tenToHex(557872140));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorCollectdataManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public int testBlankPlace() {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public void addCarCollectdataEventListener(CarVendorCollectdataManager.OnCarCollectdataEventListener listener) throws CarNotConnectedException {
        OnCarCollectdataChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarCollectdataChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorCollectdataManager.TAG, "addCarCollectdataEventListener propId size: " + this.mExtCollectdataIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtCollectdataIds) {
                int propId = num.intValue();
                Log.debug(CarVendorCollectdataManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.collectdata.CarVendorCollectdataManager
    public void removeCarCollectdataEventListener(CarVendorCollectdataManager.OnCarCollectdataEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarCollectdataChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorCollectdataManager.TAG, "removeCarCollectdataEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CollectdataEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sCollectdataEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorCollectdataManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId != 559969290) {
            switch (propertyId) {
                case 557872128:
                    sCollectdataEventChangeValue.setDriverMotorSpeedFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872129:
                    sCollectdataEventChangeValue.setDriverMotorSpeedRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872130:
                    sCollectdataEventChangeValue.setDriverMotorTorqueFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872131:
                    sCollectdataEventChangeValue.setDriverMotorTorqueRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872132:
                    sCollectdataEventChangeValue.setDriverMotorTemperatureFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872133:
                    sCollectdataEventChangeValue.setDriverMotorTemperatureRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872134:
                    sCollectdataEventChangeValue.setMotorMCUGeneratrixVoltFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872135:
                    sCollectdataEventChangeValue.setMotorMCUGeneratrixVoltRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872136:
                    sCollectdataEventChangeValue.setMotorMCUGeneratrixCurrentFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557872137:
                    sCollectdataEventChangeValue.setMotorMCUGeneratrixCurrentRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557872139:
                            sCollectdataEventChangeValue.setDCChargingFault(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872140:
                            sCollectdataEventChangeValue.setWaterTemperationAlarm(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872141:
                            sCollectdataEventChangeValue.setCollision(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557872142:
                            sCollectdataEventChangeValue.setDmVersionCodeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            Log.debug(CarVendorCollectdataManager.TAG, "break other properties ,except collectdata");
                            break;
                    }
                    break;
            }
        } else {
            sCollectdataEventChangeValue.setEngineSpeed(CastArrayUtil.toPrimitive((Float) value.getValue()));
        }
        Log.debug(CarVendorCollectdataManager.TAG, "convertCarVendorPropertyValue " + sCollectdataEventChangeValue.toString(), DEBUG_LOGGER);
        return sCollectdataEventChangeValue;
    }

    private OnCarCollectdataChangeEventListenerProxy findListenerLocked(CarVendorCollectdataManager.OnCarCollectdataEventListener listener) {
        for (OnCarCollectdataChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarCollectdataChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorCollectdataManager.OnCarCollectdataEventListener listener;

        public OnCarCollectdataChangeEventListenerProxy(CarVendorCollectdataManager.OnCarCollectdataEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorCollectdataMgr.this.traceCallbackPrepare();
                CollectdataEventChangeValue newValue = CarVendorCollectdataMgr.convertCarVendorProperty(value);
                CarVendorCollectdataMgr.this.traceCallbackBefore();
                this.listener.onCollectdataEventChange(newValue);
                CarVendorCollectdataMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorCollectdataManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorCollectdataManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onCollectdataErrorEvent(propertyId, zone);
        }
    }
}
