package com.ts.lib.car.hardware.health;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.health.CarVendorHealthManager;
import com.ts.lib.settings.common.constant.Constants;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorHealthMgr extends CarVendorManagerBase implements CarVendorHealthManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static HealthEventChangeValue sHealthEventChangeValue = new HealthEventChangeValue();
    private final LinkedList<OnCarHealthChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtHealthIds = {557850636, 557850641, 557850624, 557850635, 557850628, 557850627, 557850629, 557850625, 557850626, 557850638, 557850642, Integer.valueOf(CarVendorHealthManager.ID_EPB_CONFIG), 557850647, 557850645, 557850657, 557850655, 557850656, 557850653, 557850654, 557850651, 557850650, 557850652, 557850648, 557850649, 557850659, 557850660, 557850662, 557850666, 557850665, 557850667, 557850663, 557850664, 557850671, 557850673, 557850672, 557850668, 557850669, 557850679};

    public CarVendorHealthMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorHealthManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorHealthManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorHealthManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager(Constants.PROPERTY_SERVICE);
            Log.debug(CarVendorHealthManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getBatteryHealthStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850679, 0);
            Log.debug(CarVendorHealthManager.TAG, "getBatteryHealthStatusR value = " + value + " property: 0x" + tenToHex(557850679));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeNewBladeBatteryCoolantR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850666, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilLifetimeNewBladeBatteryCoolantR value = " + value + " property: 0x" + tenToHex(557850666));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850665, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR value = " + value + " property: 0x" + tenToHex(557850665));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeNewLongLifeCoolantLongLifeCoolantR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850667, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilLifetimeNewLongLifeCoolantLongLifeCoolantR value = " + value + " property: 0x" + tenToHex(557850667));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeNewOilLifeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850663, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilLifetimeNewOilLifeR value = " + value + " property: 0x" + tenToHex(557850663));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeNewTransmissionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850664, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilLifetimeNewTransmissionR value = " + value + " property: 0x" + tenToHex(557850664));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceDialogHintBladeBatteryCoolantR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850671, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilMaintainanceDialogHintBladeBatteryCoolantR value = " + value + " property: 0x" + tenToHex(557850671));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceDialogHintBrakeFluidLifeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850673, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilMaintainanceDialogHintBrakeFluidLifeR value = " + value + " property: 0x" + tenToHex(557850673));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceDialogHintLongLifeCoolantR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850672, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilMaintainanceDialogHintLongLifeCoolantR value = " + value + " property: 0x" + tenToHex(557850672));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceDialogHintOilLifeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850668, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilMaintainanceDialogHintOilLifeR value = " + value + " property: 0x" + tenToHex(557850668));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceDialogHintTransmissionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850669, 0);
            Log.debug(CarVendorHealthManager.TAG, "getOilMaintainanceDialogHintTransmissionR value = " + value + " property: 0x" + tenToHex(557850669));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilMaintainanceNotHintBladeBatteryCoolantS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilMaintainanceNotHintBladeBatteryCoolantS value = " + value + " property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BLADE_BATTERY_COOLANT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BLADE_BATTERY_COOLANT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilMaintainanceNotHintBrakeFluidLifeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilMaintainanceNotHintBrakeFluidLifeS value = " + value + " property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BRAKE_FLUID_LIFE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BRAKE_FLUID_LIFE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilMaintainanceNotHintLongLifeCoolantS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilMaintainanceNotHintLongLifeCoolantS value = " + value + " property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_LONG_LIFE_COOLANT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_LONG_LIFE_COOLANT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilMaintainanceNotHintOilLifeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilMaintainanceNotHintOilLifeS value = " + value + " property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_OIL_LIFE_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_OIL_LIFE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilMaintainanceNotHintTransmissionS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilMaintainanceNotHintTransmissionS value = " + value + " property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_TRANSMISSION_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_TRANSMISSION_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getEpbHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850662, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEpbHintR property: 0x");
            sb.append(tenToHex(557850662));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getTransportModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850659, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTransportModeR property: 0x");
            sb.append(tenToHex(557850659));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setTransportModeS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setTransportModeS property: 0x" + tenToHex(557850658) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850658, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getTransportModeStateHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850660, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTransportModeStateHintR property: 0x");
            sb.append(tenToHex(557850660));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setTransportModeStateHintS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setTransportModeStateHintS property: 0x" + tenToHex(557850661) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850661, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceHintBladeBatteryCoolant() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850657, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilMaintainanceHintBladeBatteryCoolant property: 0x");
            sb.append(tenToHex(557850657));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceHintBrakeFluidLife() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850655, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilMaintainanceHintBrakeFluidLife property: 0x");
            sb.append(tenToHex(557850655));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceHintLongLifeCoolant() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850656, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilMaintainanceHintLongLifeCoolant property: 0x");
            sb.append(tenToHex(557850656));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceHintOilLife() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850653, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilMaintainanceHintOilLife property: 0x");
            sb.append(tenToHex(557850653));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilMaintainanceHintTransmissionOilLife() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850654, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilMaintainanceHintTransmissionOilLife property: 0x");
            sb.append(tenToHex(557850654));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getResetOilLifetimeChangedBatteryR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850651, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getResetOilLifetimeChangedBatteryR property: 0x");
            sb.append(tenToHex(557850651));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getResetOilLifetimeChangedBreakR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850650, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getResetOilLifetimeChangedBreakR property: 0x");
            sb.append(tenToHex(557850650));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getResetOilLifetimeChangedElecR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850652, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getResetOilLifetimeChangedElecR property: 0x");
            sb.append(tenToHex(557850652));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getResetOilLifetimeChangedEngineR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850648, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getResetOilLifetimeChangedEngineR property: 0x");
            sb.append(tenToHex(557850648));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getResetOilLifetimeChangedTransMissionR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850649, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getResetOilLifetimeChangedTransMissionR property: 0x");
            sb.append(tenToHex(557850649));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getFrontWiperCheckStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850636, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontWiperCheckStateR property: 0x");
            sb.append(tenToHex(557850636));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setFrontWiperCheckStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setFrontWiperCheckStateS property: 0x" + tenToHex(557850637) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850637, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilHint() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850624, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilHint property: 0x");
            sb.append(tenToHex(557850624));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850635, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLevel property: 0x");
            sb.append(tenToHex(557850635));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeBattery() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850628, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLifetimeBattery property: 0x");
            sb.append(tenToHex(557850628));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeBrake() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850627, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLifetimeBrake property: 0x");
            sb.append(tenToHex(557850627));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeElec() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850629, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLifetimeElec property: 0x");
            sb.append(tenToHex(557850629));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeEngine() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850625, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLifetimeEngine property: 0x");
            sb.append(tenToHex(557850625));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilLifetimeTransmission() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850626, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilLifetimeTransmission property: 0x");
            sb.append(tenToHex(557850626));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getOilUpdateState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850641, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOilUpdateState property: 0x");
            sb.append(tenToHex(557850641));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getRearWiperCheckStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850638, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearWiperCheckStateR property: 0x");
            sb.append(tenToHex(557850638));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setRearWiperCheckStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setRearWiperCheckStateS property: 0x" + tenToHex(557850639) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850639, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getTyreFaultLocation() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850642, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTyreFaultLocation property: 0x");
            sb.append(tenToHex(557850642));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetOilLifetimeBattery(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetOilLifetimeBattery property: 0x" + tenToHex(557850633) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850633, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetOilLifetimeBrake(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetOilLifetimeBrake property: 0x" + tenToHex(557850632) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850632, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetOilLifetimeELec(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetOilLifetimeELec property: 0x" + tenToHex(557850634) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850634, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetOilLifetimeEngine(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetOilLifetimeEngine property: 0x" + tenToHex(557850630) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850630, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetOilLifetimeTransmission(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetOilLifetimeTransmission property: 0x" + tenToHex(557850631) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850631, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void resetTirePressure(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setresetTirePressure property: 0x" + tenToHex(557850640) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557850640, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getEPBConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorHealthManager.ID_EPB_CONFIG, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEPBConfig property: 0x");
            sb.append(tenToHex(CarVendorHealthManager.ID_EPB_CONFIG));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getEPBStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850647, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEPBStateR property: 0x");
            sb.append(tenToHex(557850647));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setEPBStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setEPBStateS property: 0x" + tenToHex(CarVendorHealthManager.ID_EPB_STATE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_EPB_STATE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public int getEPBTtpe() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850645, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEPBTtpe property: 0x");
            sb.append(tenToHex(557850645));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorHealthManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void setOilUpdateTagS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorHealthManager.TAG, "setOilUpdateTagS property: 0x" + tenToHex(CarVendorHealthManager.ID_OIL_UPDATE_TAG_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorHealthManager.ID_OIL_UPDATE_TAG_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void addCarHealthEventListener(CarVendorHealthManager.OnCarHealthEventListener listener) throws CarNotConnectedException {
        OnCarHealthChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarHealthChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorHealthManager.TAG, "addCarHealthEventListener propId size: " + this.mExtHealthIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtHealthIds) {
                int propId = num.intValue();
                Log.debug(CarVendorHealthManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.health.CarVendorHealthManager
    public void removeCarHealthEventListener(CarVendorHealthManager.OnCarHealthEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarHealthChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorHealthManager.TAG, "removeCarHealthEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static HealthEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sHealthEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorHealthManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557850624:
                sHealthEventChangeValue.setOilHint(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850625:
                sHealthEventChangeValue.setOilLifetimeEngine(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850626:
                sHealthEventChangeValue.setOilLifetimeTransmission(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850627:
                sHealthEventChangeValue.setOilLifetimeBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850628:
                sHealthEventChangeValue.setOilLifetimeBattery(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850629:
                sHealthEventChangeValue.setOilLifetimeElec(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850630:
                sHealthEventChangeValue.setresetOilLifetimeEngine(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850631:
                sHealthEventChangeValue.setresetOilLifetimeTransmission(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850632:
                sHealthEventChangeValue.setresetOilLifetimeBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850633:
                sHealthEventChangeValue.setresetOilLifetimeBattery(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850634:
                sHealthEventChangeValue.setresetOilLifetimeELec(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850635:
                sHealthEventChangeValue.setOilLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850636:
                sHealthEventChangeValue.setFrontWiperCheckStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850637:
                sHealthEventChangeValue.setFrontWiperCheckStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850638:
                sHealthEventChangeValue.setRearWiperCheckStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850639:
                sHealthEventChangeValue.setRearWiperCheckStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850640:
                sHealthEventChangeValue.setresetTirePressure(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850641:
                sHealthEventChangeValue.setOilUpdateState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850642:
                sHealthEventChangeValue.setTyreFaultLocation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_UPDATE_TAG_S /* 557850643 */:
                sHealthEventChangeValue.setOilUpdateTagS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_EPB_CONFIG /* 557850644 */:
                sHealthEventChangeValue.setEPBConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850645:
                sHealthEventChangeValue.setEPBTtpe(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_EPB_STATE_S /* 557850646 */:
                sHealthEventChangeValue.setEPBStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850647:
                sHealthEventChangeValue.setEPBStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850648:
                sHealthEventChangeValue.setResetOilLifetimeChangedEngineR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850649:
                sHealthEventChangeValue.setResetOilLifetimeChangedTransMissionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850650:
                sHealthEventChangeValue.setResetOilLifetimeChangedBreakR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850651:
                sHealthEventChangeValue.setResetOilLifetimeChangedBatteryR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850652:
                sHealthEventChangeValue.setResetOilLifetimeChangedElecR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850653:
                sHealthEventChangeValue.setOilMaintainanceHintOilLife(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850654:
                sHealthEventChangeValue.setOilMaintainanceHintTransmissionOilLife(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850655:
                sHealthEventChangeValue.setOilMaintainanceHintBrakeFluidLife(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850656:
                sHealthEventChangeValue.setOilMaintainanceHintLongLifeCoolant(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850657:
                sHealthEventChangeValue.setOilMaintainanceHintBladeBatteryCoolant(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850658:
                sHealthEventChangeValue.setTransportModeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850659:
                sHealthEventChangeValue.setTransportModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850660:
                sHealthEventChangeValue.setTransportModeStateHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850661:
                sHealthEventChangeValue.setTransportModeStateHintS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850662:
                sHealthEventChangeValue.setEpbHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850663:
                sHealthEventChangeValue.setOilLifetimeNewOilLifeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850664:
                sHealthEventChangeValue.setOilLifetimeNewTransmissionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850665:
                sHealthEventChangeValue.setOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850666:
                sHealthEventChangeValue.setOilLifetimeNewBladeBatteryCoolantR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850667:
                sHealthEventChangeValue.setOilLifetimeNewLongLifeCoolantLongLifeCoolantR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850668:
                sHealthEventChangeValue.setOilMaintainanceDialogHintOilLifeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850669:
                sHealthEventChangeValue.setOilMaintainanceDialogHintTransmissionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850670:
            default:
                Log.debug(CarVendorHealthManager.TAG, "break other properties ,except health");
                break;
            case 557850671:
                sHealthEventChangeValue.setOilMaintainanceDialogHintBladeBatteryCoolantR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850672:
                sHealthEventChangeValue.setOilMaintainanceDialogHintLongLifeCoolantR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850673:
                sHealthEventChangeValue.setOilMaintainanceDialogHintBrakeFluidLifeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_OIL_LIFE_S /* 557850674 */:
                sHealthEventChangeValue.setOilMaintainanceNotHintOilLifeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_TRANSMISSION_S /* 557850675 */:
                sHealthEventChangeValue.setOilMaintainanceNotHintTransmissionS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BRAKE_FLUID_LIFE_S /* 557850676 */:
                sHealthEventChangeValue.setOilMaintainanceNotHintBrakeFluidLifeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_BLADE_BATTERY_COOLANT_S /* 557850677 */:
                sHealthEventChangeValue.setOilMaintainanceNotHintBladeBatteryCoolantS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case CarVendorHealthManager.ID_OIL_MAINTENANCE_NOT_HINT_LONG_LIFE_COOLANT_S /* 557850678 */:
                sHealthEventChangeValue.setOilMaintainanceNotHintLongLifeCoolantS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557850679:
                sHealthEventChangeValue.setBatteryHealthStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
        }
        Log.debug(CarVendorHealthManager.TAG, "convertCarVendorPropertyValue " + sHealthEventChangeValue.toString(), DEBUG_LOGGER);
        return sHealthEventChangeValue;
    }

    private OnCarHealthChangeEventListenerProxy findListenerLocked(CarVendorHealthManager.OnCarHealthEventListener listener) {
        for (OnCarHealthChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarHealthChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorHealthManager.OnCarHealthEventListener listener;

        public OnCarHealthChangeEventListenerProxy(CarVendorHealthManager.OnCarHealthEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2 || value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorHealthMgr.this.traceCallbackPrepare();
                HealthEventChangeValue newValue = CarVendorHealthMgr.convertCarVendorProperty(value);
                CarVendorHealthMgr.this.traceCallbackBefore();
                this.listener.onHealthEventChange(newValue);
                CarVendorHealthMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorHealthManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorHealthManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onHealthErrorEvent(propertyId, zone);
        }
    }
}
