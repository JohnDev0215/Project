package com.ts.lib.car.hardware.ota;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.ota.CarVendorOtaManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorOtaMgr extends CarVendorManagerBase implements CarVendorOtaManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static OtaEventChangeValue sOtaEventChangeValue = new OtaEventChangeValue();
    private final LinkedList<OnCarOtaChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtOtaIds = {Integer.valueOf(CarVendorOtaManager.ID_BATTERY_VOLTAGE), 557860370, 557860355, 557860365, 557860362, 557860366, 557860368, Integer.valueOf(CarVendorOtaManager.ID_ENVIRONMENT_TEMP), 557860358, 557857290, 557860369, 557843465, 557860367, 557860361, 557860360, Integer.valueOf(CarVendorOtaManager.ID_SMART_POWER_USABLE), 557860363, Integer.valueOf(CarVendorOtaManager.ID_TURN_OFF_STATE), 559957505, 557860352, 557860379, 557860378, 557860382, 557860380, 557860383, 557860381, 557860384, 557862951, 557862927, 557860386, 557860385, Integer.valueOf(CarVendorOtaManager.ID_DOOR_LOCK_AREA_LEFT_FRONT), 557860389, 557860388, Integer.valueOf(CarVendorOtaManager.ID_IG1_RELAY_STATE_R), Integer.valueOf(CarVendorOtaManager.ID_IG4_RELAY_STATE_R), Integer.valueOf(CarVendorOtaManager.ID_AP_GET_STR_RESET_CYCLE_REQ)};

    public CarVendorOtaMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorOtaManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorOtaManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorOtaManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorOtaManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setUsbUpdateStateS(int state) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setUsbUpdateStateS state = " + state + " property: 0x" + tenToHex(CarVendorOtaManager.ID_USB_UPDATE_STATE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_USB_UPDATE_STATE_S, 0, CastArrayUtil.toWrap(state));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getIg1RelayStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_IG1_RELAY_STATE_R, 0);
            Log.debug(CarVendorOtaManager.TAG, "getIg1RelayStateR value = " + value + " property: 0x" + tenToHex(CarVendorOtaManager.ID_IG1_RELAY_STATE_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getStrResetCycleReqR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_AP_GET_STR_RESET_CYCLE_REQ, 0);
            Log.debug(CarVendorOtaManager.TAG, "getStrResetCycleReqR value = " + value + " property: 0x" + tenToHex(CarVendorOtaManager.ID_AP_GET_STR_RESET_CYCLE_REQ), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getIg4RelayStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_IG4_RELAY_STATE_R, 0);
            Log.debug(CarVendorOtaManager.TAG, "getIg4RelayStateR value = " + value + " property: 0x" + tenToHex(CarVendorOtaManager.ID_IG4_RELAY_STATE_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setMultimediaSelfUpdateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setMultimediaSelfUpdateS value = " + value + " property: 0x" + tenToHex(557860390));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860390, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getProhibitPowerOnStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860389, 0);
            Log.debug(CarVendorOtaManager.TAG, "getProhibitPowerOnStateR value = " + value + " property: 0x" + tenToHex(557860389));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getStartKeyStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860388, 0);
            Log.debug(CarVendorOtaManager.TAG, "getStartKeyStateR value = " + value + " property: 0x" + tenToHex(557860388));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getDoorLockStatusAreaLeftFront() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_DOOR_LOCK_AREA_LEFT_FRONT, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLockStatusAreaLeftFront property: 0x");
            sb.append(tenToHex(CarVendorOtaManager.ID_DOOR_LOCK_AREA_LEFT_FRONT));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getOtaEnergyTypeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862951, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOtaEnergyTypeR property: 0x");
            sb.append(tenToHex(557862951));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBackupAreaDownloadStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860385, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackupAreaDownloadStateR property: 0x");
            sb.append(tenToHex(557860385));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getOtaPowerLevel() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862927, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOtaPowerLevel property: 0x");
            sb.append(tenToHex(557862927));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSocValueR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860386, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSocValueR property: 0x");
            sb.append(tenToHex(557860386));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getDischargeMainContactorState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860379, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeMainContactorState property: 0x");
            sb.append(tenToHex(557860379));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBatteryVoltage() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_BATTERY_VOLTAGE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryVoltage property: 0x");
            sb.append(tenToHex(CarVendorOtaManager.ID_BATTERY_VOLTAGE));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBcmOtaState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860370, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBcmOtaState property: 0x");
            sb.append(tenToHex(557860370));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBurglarState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860355, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBurglarState property: 0x");
            sb.append(tenToHex(557860355));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getChargeState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860365, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeState property: 0x");
            sb.append(tenToHex(557860365));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getDcState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860362, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDcState property: 0x");
            sb.append(tenToHex(557860362));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getDischargeState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860366, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeState property: 0x");
            sb.append(tenToHex(557860366));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setEclPower(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setEclPower property: 0x" + tenToHex(CarVendorOtaManager.ID_ECL_POWER) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_ECL_POWER, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getEngineHoodState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860368, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineHoodState property: 0x");
            sb.append(tenToHex(557860368));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getEnvironmentTemp() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_ENVIRONMENT_TEMP, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnvironmentTemp property: 0x");
            sb.append(tenToHex(CarVendorOtaManager.ID_ENVIRONMENT_TEMP));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getEpbState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860358, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEpbState property: 0x");
            sb.append(tenToHex(557860358));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getGear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557857290, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGear property: 0x");
            sb.append(tenToHex(557857290));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setHighVoltageCmd(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setHighVoltageCmd property: 0x" + tenToHex(CarVendorOtaManager.ID_HIGH_VOLTAGE_CMD) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_HIGH_VOLTAGE_CMD, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getIg3RelayState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860369, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIg3RelayState property: 0x");
            sb.append(tenToHex(557860369));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getIgnitionState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557843465, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIgnitionState property: 0x");
            sb.append(tenToHex(557843465));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setOtaModel(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setOtaModel property: 0x" + tenToHex(557860375) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557860375, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setOtaPower(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setOtaPower property: 0x" + tenToHex(CarVendorOtaManager.ID_OTA_POWER) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_OTA_POWER, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setQuitChargeAndDischarge(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setQuitChargeAndDischarge property: 0x" + tenToHex(CarVendorOtaManager.ID_QUIT_CHARGE_AND_DISCHARGE) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_QUIT_CHARGE_AND_DISCHARGE, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setQuitSmartCharge(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setQuitSmartCharge property: 0x" + tenToHex(CarVendorOtaManager.ID_QUIT_SMART_CHARGE) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_QUIT_SMART_CHARGE, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSmartChargeState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860367, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartChargeState property: 0x");
            sb.append(tenToHex(557860367));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setSmartPower(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setSmartPower property: 0x" + tenToHex(CarVendorOtaManager.ID_SMART_POWER) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_SMART_POWER, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSmartPowerException() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860361, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartPowerException property: 0x");
            sb.append(tenToHex(557860361));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSmartPowerState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860360, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartPowerState property: 0x");
            sb.append(tenToHex(557860360));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSmartPowerUsable() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_SMART_POWER_USABLE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartPowerUsable property: 0x");
            sb.append(tenToHex(CarVendorOtaManager.ID_SMART_POWER_USABLE));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSocValue() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860363, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSocValue property: 0x");
            sb.append(tenToHex(557860363));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getTurnOffState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorOtaManager.ID_TURN_OFF_STATE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTurnOffState property: 0x");
            sb.append(tenToHex(CarVendorOtaManager.ID_TURN_OFF_STATE));
            sb.append(" class: boolean value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public float getVehicleSpeed() throws CarNotConnectedException {
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, 559957505, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVehicleSpeed property: 0x");
            sb.append(tenToHex(559957505));
            sb.append(" class: float value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getVehicleType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860352, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVehicleType property: 0x");
            sb.append(tenToHex(557860352));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void setBackupAreaDownload(int value) throws CarNotConnectedException {
        Log.debug(CarVendorOtaManager.TAG, "setBackupAreaDownload property: 0x" + tenToHex(CarVendorOtaManager.ID_BACKUP_AREA_DOWNLOAD) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorOtaManager.ID_BACKUP_AREA_DOWNLOAD, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBatteryManagementDeviceState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860378, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryManagementDeviceState property: 0x");
            sb.append(tenToHex(557860378));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getBatteryVoltageSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860382, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryVoltageSecond property: 0x");
            sb.append(tenToHex(557860382));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getChargeAndDischargeSystemState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860380, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeAndDischargeSystemState property: 0x");
            sb.append(tenToHex(557860380));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getDischargeStateVtol() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860383, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeStateVtol property: 0x");
            sb.append(tenToHex(557860383));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getEnvironmentTempSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860381, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnvironmentTempSecond property: 0x");
            sb.append(tenToHex(557860381));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public int getSmartChargeStateSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557860384, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSmartChargeStateSecond property: 0x");
            sb.append(tenToHex(557860384));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorOtaManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void addCarOtaEventListener(CarVendorOtaManager.OnCarOtaEventListener listener) throws CarNotConnectedException {
        OnCarOtaChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarOtaChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorOtaManager.TAG, "addCarOtaEventListener propId size: " + this.mExtOtaIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtOtaIds) {
                int propId = num.intValue();
                Log.debug(CarVendorOtaManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.ota.CarVendorOtaManager
    public void removeCarOtaEventListener(CarVendorOtaManager.OnCarOtaEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarOtaChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorOtaManager.TAG, "removeCarOtaEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static OtaEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sOtaEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorOtaManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557843465:
                sOtaEventChangeValue.setIgnitionState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorOtaManager.ID_BACKUP_AREA_DOWNLOAD /* 557843610 */:
                sOtaEventChangeValue.setBackupAreaDownload(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557857290:
                sOtaEventChangeValue.setGear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557860352:
                sOtaEventChangeValue.setVehicleType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862927:
                sOtaEventChangeValue.setOtaPowerLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557862951:
                sOtaEventChangeValue.setOtaEnergyTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorOtaManager.ID_AP_GET_STR_RESET_CYCLE_REQ /* 557903886 */:
                sOtaEventChangeValue.setStrResetCycleReqR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 559957505:
                sOtaEventChangeValue.setVehicleSpeed(CastArrayUtil.toPrimitive((Float) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case CarVendorOtaManager.ID_TURN_OFF_STATE /* 557860354 */:
                        sOtaEventChangeValue.setTurnOffState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557860355:
                        sOtaEventChangeValue.setBurglarState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorOtaManager.ID_ENVIRONMENT_TEMP /* 557860356 */:
                        sOtaEventChangeValue.setEnvironmentTemp(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557860358:
                                sOtaEventChangeValue.setEpbState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_SMART_POWER_USABLE /* 557860359 */:
                                sOtaEventChangeValue.setSmartPowerUsable(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860360:
                                sOtaEventChangeValue.setSmartPowerState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860361:
                                sOtaEventChangeValue.setSmartPowerException(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860362:
                                sOtaEventChangeValue.setDcState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860363:
                                sOtaEventChangeValue.setSocValue(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_BATTERY_VOLTAGE /* 557860364 */:
                                sOtaEventChangeValue.setBatteryVoltage(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860365:
                                sOtaEventChangeValue.setChargeState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860366:
                                sOtaEventChangeValue.setDischargeState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860367:
                                sOtaEventChangeValue.setSmartChargeState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860368:
                                sOtaEventChangeValue.setEngineHoodState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860369:
                                sOtaEventChangeValue.setIg3RelayState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860370:
                                sOtaEventChangeValue.setBcmOtaState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_SMART_POWER /* 557860371 */:
                                sOtaEventChangeValue.setSmartPower(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_ECL_POWER /* 557860372 */:
                                sOtaEventChangeValue.setEclPower(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_QUIT_SMART_CHARGE /* 557860373 */:
                                sOtaEventChangeValue.setQuitSmartCharge(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_QUIT_CHARGE_AND_DISCHARGE /* 557860374 */:
                                sOtaEventChangeValue.setQuitChargeAndDischarge(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860375:
                                sOtaEventChangeValue.setOtaModel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_OTA_POWER /* 557860376 */:
                                sOtaEventChangeValue.setOtaPower(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorOtaManager.ID_HIGH_VOLTAGE_CMD /* 557860377 */:
                                sOtaEventChangeValue.setHighVoltageCmd(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860378:
                                sOtaEventChangeValue.setBatteryManagementDeviceState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860379:
                                sOtaEventChangeValue.setDischargeMainContactorState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860380:
                                sOtaEventChangeValue.setChargeAndDischargeSystemState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860381:
                                sOtaEventChangeValue.setEnvironmentTempSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860382:
                                sOtaEventChangeValue.setBatteryVoltageSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860383:
                                sOtaEventChangeValue.setDischargeStateVtol(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860384:
                                sOtaEventChangeValue.setSmartChargeStateSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860385:
                                sOtaEventChangeValue.setBackupAreaDownloadStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557860386:
                                sOtaEventChangeValue.setSocValueR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557860388:
                                        sOtaEventChangeValue.setStartKeyStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557860389:
                                        sOtaEventChangeValue.setProhibitPowerOnStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557860390:
                                        sOtaEventChangeValue.setMultimediaSelfUpdateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorOtaManager.ID_DOOR_LOCK_AREA_LEFT_FRONT /* 557860391 */:
                                        sOtaEventChangeValue.setDoorLockStatusAreaLeftFront(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorOtaManager.ID_IG4_RELAY_STATE_R /* 557860392 */:
                                        sOtaEventChangeValue.setIg4RelayStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorOtaManager.ID_IG1_RELAY_STATE_R /* 557860393 */:
                                        sOtaEventChangeValue.setIg1RelayStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        Log.debug(CarVendorOtaManager.TAG, "break other properties ,except ota");
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
        Log.debug(CarVendorOtaManager.TAG, "convertCarVendorPropertyValue " + sOtaEventChangeValue.toString(), DEBUG_LOGGER);
        return sOtaEventChangeValue;
    }

    private OnCarOtaChangeEventListenerProxy findListenerLocked(CarVendorOtaManager.OnCarOtaEventListener listener) {
        for (OnCarOtaChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarOtaChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorOtaManager.OnCarOtaEventListener listener;

        public OnCarOtaChangeEventListenerProxy(CarVendorOtaManager.OnCarOtaEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorOtaMgr.this.traceCallbackPrepare();
                OtaEventChangeValue newValue = CarVendorOtaMgr.convertCarVendorProperty(value);
                CarVendorOtaMgr.this.traceCallbackBefore();
                this.listener.onOtaEventChange(newValue);
                CarVendorOtaMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorOtaManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorOtaManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onOtaErrorEvent(propertyId, zone);
        }
    }
}
