package com.ts.lib.car.hardware.tyre;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.tyre.CarVendorTyreManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorTyreMgr extends CarVendorManagerBase implements CarVendorTyreManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static TyreEventChangeValue sTyreEventChangeValue = new TyreEventChangeValue();
    private final LinkedList<OnCarTyreChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtTyreIds = {557861383, 557861376, 557861377, 559958537, 557861378, 557861379, 557861380, 557861381, 557861382, 557861386, 557861387, 557861392, 557861393, 557861388, 557861390, 557861389, 557861391, Integer.valueOf(CarVendorTyreManager.ID_TYRE_AREA_R), Integer.valueOf(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R), Integer.valueOf(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R), Integer.valueOf(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R), Integer.valueOf(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LB_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LF_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RB_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RF_R), Integer.valueOf(CarVendorTyreManager.ID_TYRE_TEMPERATURE_VALUE_R), Integer.valueOf(CarVendorTyreManager.ID_SYSTEM_UNDERVOLTAGE_STATUS_R)};

    public CarVendorTyreMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorTyreManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorTyreManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorTyreManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorTyreManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreTemperatureValueR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_TEMPERATURE_VALUE_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyreTemperatureValue value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_TEMPERATURE_VALUE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getSystemUndervoltageStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_SYSTEM_UNDERVOLTAGE_STATUS_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getSystemUndervoltageStatusR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_SYSTEM_UNDERVOLTAGE_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreColorByTypeLbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyreColorByTypeLbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreColorByTypeLfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyreColorByTypeLfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreColorByTypeRbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyreColorByTypeRbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreColorByTypeRfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyreColorByTypeRfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public void setIndirectTyreConfirm(int value) throws CarNotConnectedException {
        Log.debug(CarVendorTyreManager.TAG, "setIndirectTyreConfirm property: 0x" + tenToHex(557861384) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557861384, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getIndirectTyreSystemState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861383, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIndirectTyreSystemState property: 0x");
            sb.append(tenToHex(557861383));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreAirLeakState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861376, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreAirLeakState property: 0x");
            sb.append(tenToHex(557861376));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreBatteryState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861377, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreBatteryState property: 0x");
            sb.append(tenToHex(557861377));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public float getTyreBatteryValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559958537, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreBatteryValue property: 0x");
            sb.append(tenToHex(559958537));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861378, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureState property: 0x");
            sb.append(tenToHex(557861378));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861379, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureValue property: 0x");
            sb.append(tenToHex(557861379));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreSignalState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861380, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreSignalState property: 0x");
            sb.append(tenToHex(557861380));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreSystemState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861381, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreSystemState property: 0x");
            sb.append(tenToHex(557861381));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreTemperatureState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861382, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreTemperatureState property: 0x");
            sb.append(tenToHex(557861382));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreTurnState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861386, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreTurnState property: 0x");
            sb.append(tenToHex(557861386));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreBodyTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861387, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreBodyTypeR property: 0x");
            sb.append(tenToHex(557861387));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureSysFailHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861392, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureSysFailHintR property: 0x");
            sb.append(tenToHex(557861392));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureWarnHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861393, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureWarnHintR property: 0x");
            sb.append(tenToHex(557861393));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyreAreaR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_TYRE_AREA_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreAreaR property: 0x");
            sb.append(tenToHex(CarVendorTyreManager.ID_TYRE_AREA_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureWarnLeftFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861388, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureWarnLeftFrontR property: 0x");
            sb.append(tenToHex(557861388));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureWarnLeftRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861390, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureWarnLeftRearR property: 0x");
            sb.append(tenToHex(557861390));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureWarnRightFrontR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861389, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureWarnRightFrontR property: 0x");
            sb.append(tenToHex(557861389));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getTyrePressureWarnRightRearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557861391, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyrePressureWarnRightRearR property: 0x");
            sb.append(tenToHex(557861391));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorTyreManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getIndirectTyrePressureStateLbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getIndirectTyrePressureStateLbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getIndirectTyrePressureStateLfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getIndirectTyrePressureStateLfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getIndirectTyrePressureStateRbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getIndirectTyrePressureStateRbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public int getIndirectTyrePressureStateRfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getIndirectTyrePressureStateRfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public float getTyrePressureValueByTypeLbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyrePressureValueByTypeLbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public float getTyrePressureValueByTypeLfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyrePressureValueByTypeLfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public float getTyrePressureValueByTypeRbR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyrePressureValueByTypeRbR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public float getTyrePressureValueByTypeRfR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R, 0);
            Log.debug(CarVendorTyreManager.TAG, "getTyrePressureValueByTypeRfR value = " + value + " property: 0x" + tenToHex(CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public void addCarTyreEventListener(CarVendorTyreManager.OnCarTyreEventListener listener) throws CarNotConnectedException {
        OnCarTyreChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarTyreChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorTyreManager.TAG, "addCarTyreEventListener propId size: " + this.mExtTyreIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtTyreIds) {
                int propId = num.intValue();
                Log.debug(CarVendorTyreManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.tyre.CarVendorTyreManager
    public void removeCarTyreEventListener(CarVendorTyreManager.OnCarTyreEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarTyreChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorTyreManager.TAG, "removeCarTyreEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TyreEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sTyreEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorTyreManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        if (propertyId != 559958537) {
            switch (propertyId) {
                case 557861376:
                    sTyreEventChangeValue.setTyreAirLeakState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861377:
                    sTyreEventChangeValue.setTyreBatteryState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861378:
                    sTyreEventChangeValue.setTyrePressureState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861379:
                    sTyreEventChangeValue.setTyrePressureValue(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861380:
                    sTyreEventChangeValue.setTyreSignalState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861381:
                    sTyreEventChangeValue.setTyreSystemState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861382:
                    sTyreEventChangeValue.setTyreTemperatureState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861383:
                    sTyreEventChangeValue.setIndirectTyreSystemState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                case 557861384:
                    sTyreEventChangeValue.setIndirectTyreConfirm(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                    break;
                default:
                    switch (propertyId) {
                        case 557861386:
                            sTyreEventChangeValue.setTyreTurnState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861387:
                            sTyreEventChangeValue.setTyreBodyTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861388:
                            sTyreEventChangeValue.setTyrePressureWarnLeftFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861389:
                            sTyreEventChangeValue.setTyrePressureWarnRightFrontR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861390:
                            sTyreEventChangeValue.setTyrePressureWarnLeftRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861391:
                            sTyreEventChangeValue.setTyrePressureWarnRightRearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861392:
                            sTyreEventChangeValue.setTyrePressureSysFailHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case 557861393:
                            sTyreEventChangeValue.setTyrePressureWarnHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        case CarVendorTyreManager.ID_TYRE_AREA_R /* 557861394 */:
                            sTyreEventChangeValue.setTyreAreaR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                            break;
                        default:
                            switch (propertyId) {
                                case CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R /* 557869844 */:
                                    sTyreEventChangeValue.setIndirectTyrePressureStateLfR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                case CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R /* 557869845 */:
                                    sTyreEventChangeValue.setIndirectTyrePressureStateRfR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                case CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R /* 557869846 */:
                                    sTyreEventChangeValue.setIndirectTyrePressureStateLbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                case CarVendorTyreManager.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R /* 557869847 */:
                                    sTyreEventChangeValue.setIndirectTyrePressureStateRbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                    break;
                                default:
                                    switch (propertyId) {
                                        case CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LF_R /* 557875234 */:
                                            sTyreEventChangeValue.setTyreColorByTypeLfR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RF_R /* 557875235 */:
                                            sTyreEventChangeValue.setTyreColorByTypeRfR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_LB_R /* 557875236 */:
                                            sTyreEventChangeValue.setTyreColorByTypeLbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case CarVendorTyreManager.ID_TYRE_COLOR_BY_TYPE_RB_R /* 557875237 */:
                                            sTyreEventChangeValue.setTyreColorByTypeRbR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case CarVendorTyreManager.ID_TYRE_TEMPERATURE_VALUE_R /* 557875238 */:
                                            sTyreEventChangeValue.setTyreTemperatureValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        case CarVendorTyreManager.ID_SYSTEM_UNDERVOLTAGE_STATUS_R /* 557875239 */:
                                            sTyreEventChangeValue.setSystemUndervoltageStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                            break;
                                        default:
                                            switch (propertyId) {
                                                case CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R /* 559972381 */:
                                                    sTyreEventChangeValue.setTyrePressureValueByTypeLfR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                    break;
                                                case CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R /* 559972382 */:
                                                    sTyreEventChangeValue.setTyrePressureValueByTypeRfR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                    break;
                                                case CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R /* 559972383 */:
                                                    sTyreEventChangeValue.setTyrePressureValueByTypeLbR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                    break;
                                                case CarVendorTyreManager.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R /* 559972384 */:
                                                    sTyreEventChangeValue.setTyrePressureValueByTypeRbR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                                    break;
                                                default:
                                                    Log.debug(CarVendorTyreManager.TAG, "break other properties ,except tyre");
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            sTyreEventChangeValue.setTyreBatteryValue(CastArrayUtil.toPrimitive((Float) value.getValue()));
        }
        Log.debug(CarVendorTyreManager.TAG, "convertCarVendorPropertyValue " + sTyreEventChangeValue.toString(), DEBUG_LOGGER);
        return sTyreEventChangeValue;
    }

    private OnCarTyreChangeEventListenerProxy findListenerLocked(CarVendorTyreManager.OnCarTyreEventListener listener) {
        for (OnCarTyreChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarTyreChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorTyreManager.OnCarTyreEventListener listener;

        public OnCarTyreChangeEventListenerProxy(CarVendorTyreManager.OnCarTyreEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorTyreMgr.this.traceCallbackPrepare();
                TyreEventChangeValue newValue = CarVendorTyreMgr.convertCarVendorProperty(value);
                CarVendorTyreMgr.this.traceCallbackBefore();
                this.listener.onTyreEventChange(newValue);
                CarVendorTyreMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorTyreManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorTyreManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onTyreErrorEvent(propertyId, zone);
        }
    }
}
