package com.ts.lib.car.hardware.charging;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.charging.CarVendorChargingManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorChargingMgr extends CarVendorManagerBase implements CarVendorChargingManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static ChargingEventChangeValue sChargingEventChangeValue = new ChargingEventChangeValue();
    private final LinkedList<OnCarChargingChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtChargingIds = {557855796, 557855797, 557855744, 557855750, 557855745, 557855798, 557855762, 557855751, Integer.valueOf(CarVendorChargingManager.ID_CHARGING_POWERR), 557855746, 557855747, 557855754, 557855755, 557855758, 557855759, 557855760, 557855757, 557855756, 557855748, 557855766, 557855749, 557855771, 557855772, 557855768, 557855764, 557855774, 557855777, 557855776, Integer.valueOf(CarVendorChargingManager.ID_BATTERY_TYPER), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_BATTERY_LEVEL_BEGINR), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_BATTERY_LIMIT_TEMPR), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_BATTERY_TEMPR), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_ELECTRICR), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_VOLTAGER), Integer.valueOf(CarVendorChargingManager.ID_CHARGE_VOLTAGE_MAXR), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_ELECTRICR), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_GUN_TIMERR), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_OVER_REASONR), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_REMAIN_TIMER), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_TOTAL_TIMER), Integer.valueOf(CarVendorChargingManager.ID_DISCHARGE_VOLTAGER), Integer.valueOf(CarVendorChargingManager.ID_VOLTAGE_MAXR), Integer.valueOf(CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR), Integer.valueOf(CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUM_LIMITR), Integer.valueOf(CarVendorChargingManager.ID_VTOV_DISCHARGE_LOWEST_VALR), Integer.valueOf(CarVendorChargingManager.ID_VTOV_DISCHARGE_QUANTITYR), 557855804, 557855802, 557855806, 557855800, 557855801, 557855810, 557855807, 557855808, 557855809, 557855798, 557856297, 557856298, 557856299, 557856300, 557856301, 557856302, 557856303, 557856304, 557856305, 557856306, 557856288, 557856307, 557856308, 557856309, 557856310, 557856311, 557856312, 557856313, 557856314, 557856315, 557856316, 557856289, 557856317, 557856318, 557856319, 557856320, 557856321, 557856322, 557856290, 557856291, 557856292, 557856293, 557856294, 557856295, 557856296, 557856273, 557856274, 557856272, 557856269, 557856276, 557855820, 557855821, 557855822, 557855823, 557855824, 557855825, 557855826, 557855827, 557855828, 557855829, 557855811, 557855830, 557855831, 557855832, 557855833, 557855834, 557855835, 557855836, 557855837, 557855838, 557855839, 557855812, 557855840, 557855841, 557855842, 557855843, 557855844, 557855845, 557855813, 557855814, 557855815, 557855816, 557855817, 557855818, 557855819, 557856266, 557856268, 557856324, 557856323, 557856327, 557856326, 557856328, 557856329, 557856331, 557856330, Integer.valueOf(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_DAY_R), Integer.valueOf(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R), Integer.valueOf(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R), Integer.valueOf(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R), Integer.valueOf(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R), 557856334, 557856335, 557856336, 557856333, 557856332, 557856338, 557856337, Integer.valueOf(CarVendorChargingManager.ID_H_E_V_CHARGE_PLAN_END_TIME_R), 557856280, 557856339, Integer.valueOf(CarVendorChargingManager.ID_V2_G_CHARGE_STATUS_R), Integer.valueOf(CarVendorChargingManager.ID_V2_G_OUTPUT_POWER_R), Integer.valueOf(CarVendorChargingManager.ID_V2_G_STATUS_R), Integer.valueOf(CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_LEFT_R), Integer.valueOf(CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_RIGHT_R)};

    public CarVendorChargingMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorChargingManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorChargingManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorChargingManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorChargingManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getElectricPortCapStateLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_LEFT_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getElectricPortCapStateLeftR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_LEFT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getElectricPortCapStateRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_RIGHT_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getElectricPortCapStateRightR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_RIGHT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeStopCapacityStateNotLiR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856339, 0);
            Log.debug(CarVendorChargingManager.TAG, "getChargeStopCapacityStateNotLiR value = " + value + " property: 0x" + tenToHex(557856339));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargeStopCapacityStateNotLiS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargeStopCapacityStateNotLiS value = " + value + " property: 0x" + tenToHex(557856340));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856340, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getv2gChargeStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_V2_G_CHARGE_STATUS_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getv2gChargeStatusR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_V2_G_CHARGE_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setv2gCmdS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setv2gCmdS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_V2_G_CMD_S));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorChargingManager.ID_V2_G_CMD_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getv2gOutputPowerR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_V2_G_OUTPUT_POWER_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getv2gOutputPowerR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_V2_G_OUTPUT_POWER_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setv2gPowerS(float value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setv2gPowerS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_V2_G_POWER_S));
        try {
            this.mCarPropertyManager.setProperty(Float.class, CarVendorChargingManager.ID_V2_G_POWER_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getv2gStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_V2_G_STATUS_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getv2gStatusR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_V2_G_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setv2gStatusS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setv2gStatusS value = " + value + " property: 0x" + tenToHex(557856341));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856341, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int[] getHevChargePlanEndTimeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer[]> value = this.mCarPropertyManager.getProperty(Integer[].class, CarVendorChargingManager.ID_H_E_V_CHARGE_PLAN_END_TIME_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_H_E_V_CHARGE_PLAN_END_TIME_R), DEBUG_LOGGER);
            return value != null ? CastArrayUtil.toPrimitive(value.getValue()) : new int[0];
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanEndTimeMonthR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeMonthR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanEndTimeYearDayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_DAY_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeYearDayR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_DAY_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanEndTimeYearHourR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeYearHourR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanEndTimeYearMinuteR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeYearMinuteR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeMonthR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856333, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeMonthR value = " + value + " property: 0x" + tenToHex(557856333), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeYearDayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856334, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeYearDayR value = " + value + " property: 0x" + tenToHex(557856334), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeYearHourR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856335, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeYearHourR value = " + value + " property: 0x" + tenToHex(557856335), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeYearMinuteR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856336, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeYearMinuteR value = " + value + " property: 0x" + tenToHex(557856336), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeYearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856332, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeYearR value = " + value + " property: 0x" + tenToHex(557856332), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlaneEndTimeYearR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlaneEndTimeYearR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getgetPortCapStateLeftR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856331, 0);
            Log.debug(CarVendorChargingManager.TAG, "getgetPortCapStateLeftR value = " + value + " property: 0x" + tenToHex(557856331), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getgetPortCapStateRightR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856330, 0);
            Log.debug(CarVendorChargingManager.TAG, "getgetPortCapStateRightR value = " + value + " property: 0x" + tenToHex(557856330), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargePreheatAllowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856324, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargePreheatAllowR property: 0x");
            sb.append(tenToHex(557856324));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovDischargeLowPowerHintR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856323, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovDischargeLowPowerHintR property: 0x");
            sb.append(tenToHex(557856323));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargerWorkStateFixR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855798, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargerWorkStateFixR property: 0x");
            sb.append(tenToHex(557855798));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingRestTimeHourR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855808, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingRestTimeHourR property: 0x");
            sb.append(tenToHex(557855808));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingRestTimeMinuteR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855809, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingRestTimeMinuteR property: 0x");
            sb.append(tenToHex(557855809));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeStopCapacityStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855804, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeStopCapacityStateR property: 0x");
            sb.append(tenToHex(557855804));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargeStopCapacityStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargeStopCapacityStateS property: 0x" + tenToHex(557855805) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855805, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeTempCtlStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855802, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeTempCtlStateR property: 0x");
            sb.append(tenToHex(557855802));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargeTempCtlStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargeTempCtlStateS property: 0x" + tenToHex(557855803) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855803, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeToaggleGuardMixR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855806, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeToaggleGuardMixR property: 0x");
            sb.append(tenToHex(557855806));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingCapStateLeftR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855800, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingCapStateLeftR property: 0x");
            sb.append(tenToHex(557855800));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingCapStateRightR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855801, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingCapStateRightR property: 0x");
            sb.append(tenToHex(557855801));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovDischargeConnectState() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855810, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovDischargeConnectState property: 0x");
            sb.append(tenToHex(557855810));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovDischargeLimitValR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855807, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovDischargeLimitValR property: 0x");
            sb.append(tenToHex(557855807));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getFullChgRemanTimHR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855796, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFullChgRemanTimHR property: 0x");
            sb.append(tenToHex(557855796));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getFullChgRemanTimMR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855797, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFullChgRemanTimMR property: 0x");
            sb.append(tenToHex(557855797));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBatteryManagementDeviceStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855745, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryManagementDeviceStateR property: 0x");
            sb.append(tenToHex(557855745));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeToggleGuardR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855762, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeToggleGuardR property: 0x");
            sb.append(tenToHex(557855762));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargeToggleGuardS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargeToggleGuardS property: 0x" + tenToHex(557855761) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855761, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargerFaultStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855744, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargerFaultStateR property: 0x");
            sb.append(tenToHex(557855744));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargerStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855750, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargerStateR property: 0x");
            sb.append(tenToHex(557855750));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargerWorkStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855745, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargerWorkStateR property: 0x");
            sb.append(tenToHex(557855745));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingGunStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855751, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingGunStateR property: 0x");
            sb.append(tenToHex(557855751));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getChargingPowerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_CHARGING_POWERR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingPowerR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGING_POWERR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingResetTimeHourR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855746, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingResetTimeHourR property: 0x");
            sb.append(tenToHex(557855746));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingResetTimeMinuteR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855747, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingResetTimeMinuteR property: 0x");
            sb.append(tenToHex(557855747));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleEnableStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855754, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleEnableStateR property: 0x");
            sb.append(tenToHex(557855754));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855755, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleStateR property: 0x");
            sb.append(tenToHex(557855755));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleTimeDayR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855758, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleTimeDayR property: 0x");
            sb.append(tenToHex(557855758));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleTimeHourR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855759, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleTimeHourR property: 0x");
            sb.append(tenToHex(557855759));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleTimeMinuteR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855760, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleTimeMinuteR property: 0x");
            sb.append(tenToHex(557855760));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleTimeMonthR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855757, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleTimeMonthR property: 0x");
            sb.append(tenToHex(557855757));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargingScheduleTimeYearR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855756, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargingScheduleTimeYearR property: 0x");
            sb.append(tenToHex(557855756));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getCharingPortLockRebackStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855748, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCharingPortLockRebackStateR property: 0x");
            sb.append(tenToHex(557855748));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeLimitR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855766, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeLimitR property: 0x");
            sb.append(tenToHex(557855766));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setDischargeLimitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setDischargeLimitS property: 0x" + tenToHex(557855765) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855765, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeRequestStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855749, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeRequestStateR property: 0x");
            sb.append(tenToHex(557855749));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeTimeHourR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855771, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeTimeHourR property: 0x");
            sb.append(tenToHex(557855771));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setDischargeTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setDischargeTimeHourS property: 0x" + tenToHex(557855769) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855769, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeTimeMinuteR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855772, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeTimeMinuteR property: 0x");
            sb.append(tenToHex(557855772));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setDischargeTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setDischargeTimeMinuteS property: 0x" + tenToHex(557855770) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855770, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeToggleR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855768, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeToggleR property: 0x");
            sb.append(tenToHex(557855768));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setDischargeToggleS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setDischargeToggleS property: 0x" + tenToHex(557855767) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855767, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getMovePowerStationToggleR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855764, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMovePowerStationToggleR property: 0x");
            sb.append(tenToHex(557855764));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setMovePowerStationToggleS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setMovePowerStationToggleS property: 0x" + tenToHex(557855763) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855763, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getProducePowerWhenLowPowerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855774, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getProducePowerWhenLowPowerR property: 0x");
            sb.append(tenToHex(557855774));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setProducePowerWhenLowPowerS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setProducePowerWhenLowPowerS property: 0x" + tenToHex(CarVendorChargingManager.ID_PRODUCE_POWER_WHEN_LOW_POWERS) + " class: long value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, CarVendorChargingManager.ID_PRODUCE_POWER_WHEN_LOW_POWERS, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855777, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWirelessChargingStateR property: 0x");
            sb.append(tenToHex(557855777));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingSwitchStateR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855776, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWirelessChargingSwitchStateR property: 0x");
            sb.append(tenToHex(557855776));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setWirelessChargingSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setWirelessChargingSwitchStateS property: 0x" + tenToHex(557855775) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855775, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBatteryTypeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_BATTERY_TYPER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBatteryTypeR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_BATTERY_TYPER));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeBatteryLevelBeginR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_CHARGE_BATTERY_LEVEL_BEGINR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeBatteryLevelBeginR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_BATTERY_LEVEL_BEGINR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeBatteryLimitTempR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_CHARGE_BATTERY_LIMIT_TEMPR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeBatteryLimitTempR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_BATTERY_LIMIT_TEMPR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeBatteryTempR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_CHARGE_BATTERY_TEMPR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeBatteryTempR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_BATTERY_TEMPR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getChargeElectricR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_CHARGE_ELECTRICR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeElectricR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_ELECTRICR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getChargeVoltageMaxR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_CHARGE_VOLTAGE_MAXR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeVoltageMaxR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_VOLTAGE_MAXR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargeVoltageR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_CHARGE_VOLTAGER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeVoltageR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_CHARGE_VOLTAGER));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getDischargeElectricR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_DISCHARGE_ELECTRICR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeElectricR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_ELECTRICR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeGunTimerR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_DISCHARGE_GUN_TIMERR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeGunTimerR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_GUN_TIMERR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeOverReasonR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_DISCHARGE_OVER_REASONR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeOverReasonR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_OVER_REASONR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeRemainTimeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_DISCHARGE_REMAIN_TIMER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeRemainTimeR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_REMAIN_TIMER));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeTotalTimeR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_DISCHARGE_TOTAL_TIMER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeTotalTimeR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_TOTAL_TIMER));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getDischargeVoltageR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_DISCHARGE_VOLTAGER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeVoltageR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_DISCHARGE_VOLTAGER));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getVoltageMaxR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_VOLTAGE_MAXR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVoltageMaxR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_VOLTAGE_MAXR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovChargeBatteryLevelNumLimitR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUM_LIMITR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovChargeBatteryLevelNumLimitR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUM_LIMITR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovChargeBatteryLevelNumR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovChargeBatteryLevelNumR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getVtovDischargeLowestValR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorChargingManager.ID_VTOV_DISCHARGE_LOWEST_VALR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovDischargeLowestValR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_VTOV_DISCHARGE_LOWEST_VALR));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public float getVtovDischargeQuantityR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Float> value = this.mCarPropertyManager.getProperty(Float.class, CarVendorChargingManager.ID_VTOV_DISCHARGE_QUANTITYR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVtovDischargeQuantityR property: 0x");
            sb.append(tenToHex(CarVendorChargingManager.ID_VTOV_DISCHARGE_QUANTITYR));
            sb.append(" class: float value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0.0f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge10R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856297, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge10R property: 0x");
            sb.append(tenToHex(557856297));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge11R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856298, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge11R property: 0x");
            sb.append(tenToHex(557856298));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge12R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856299, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge12R property: 0x");
            sb.append(tenToHex(557856299));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge13R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856300, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge13R property: 0x");
            sb.append(tenToHex(557856300));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge14R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856301, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge14R property: 0x");
            sb.append(tenToHex(557856301));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge15R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856302, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge15R property: 0x");
            sb.append(tenToHex(557856302));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge16R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856303, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge16R property: 0x");
            sb.append(tenToHex(557856303));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge17R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856304, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge17R property: 0x");
            sb.append(tenToHex(557856304));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge18R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856305, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge18R property: 0x");
            sb.append(tenToHex(557856305));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge19R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856306, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge19R property: 0x");
            sb.append(tenToHex(557856306));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856288, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge1R property: 0x");
            sb.append(tenToHex(557856288));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge20R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856307, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge20R property: 0x");
            sb.append(tenToHex(557856307));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge21R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856308, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge21R property: 0x");
            sb.append(tenToHex(557856308));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge22R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856309, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge22R property: 0x");
            sb.append(tenToHex(557856309));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge23R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856310, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge23R property: 0x");
            sb.append(tenToHex(557856310));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge24R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856311, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge24R property: 0x");
            sb.append(tenToHex(557856311));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge25R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856312, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge25R property: 0x");
            sb.append(tenToHex(557856312));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge26R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856313, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge26R property: 0x");
            sb.append(tenToHex(557856313));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge27R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856314, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge27R property: 0x");
            sb.append(tenToHex(557856314));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge28R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856315, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge28R property: 0x");
            sb.append(tenToHex(557856315));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge29R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856316, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge29R property: 0x");
            sb.append(tenToHex(557856316));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856289, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge2R property: 0x");
            sb.append(tenToHex(557856289));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge30R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856317, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge30R property: 0x");
            sb.append(tenToHex(557856317));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge31R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856318, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge31R property: 0x");
            sb.append(tenToHex(557856318));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge32R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856319, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge32R property: 0x");
            sb.append(tenToHex(557856319));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge33R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856320, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge33R property: 0x");
            sb.append(tenToHex(557856320));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge34R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856321, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge34R property: 0x");
            sb.append(tenToHex(557856321));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge35R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856322, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge35R property: 0x");
            sb.append(tenToHex(557856322));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856290, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge3R property: 0x");
            sb.append(tenToHex(557856290));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856291, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge4R property: 0x");
            sb.append(tenToHex(557856291));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856292, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge5R property: 0x");
            sb.append(tenToHex(557856292));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856293, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge6R property: 0x");
            sb.append(tenToHex(557856293));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856294, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge7R property: 0x");
            sb.append(tenToHex(557856294));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge8R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856295, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge8R property: 0x");
            sb.append(tenToHex(557856295));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getBmsWakeNoSenseCharge9R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856296, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBmsWakeNoSenseCharge9R property: 0x");
            sb.append(tenToHex(557856296));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getCarPlanAccOnStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856273, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarPlanAccOnStateR property: 0x");
            sb.append(tenToHex(557856273));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getCarPlanBatteryHeatStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856274, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarPlanBatteryHeatStateR property: 0x");
            sb.append(tenToHex(557856274));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanDayS property: 0x" + tenToHex(557856279) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856279, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanHourS property: 0x" + tenToHex(557856280) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856280, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanMinuteS property: 0x" + tenToHex(557856281) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856281, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanMonthS property: 0x" + tenToHex(557856278) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856278, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getCarPlanStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856272, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarPlanStateR property: 0x");
            sb.append(tenToHex(557856272));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanStateS property: 0x" + tenToHex(557856271) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856271, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setCarPlanYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setCarPlanYearS property: 0x" + tenToHex(557856277) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856277, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargePlanStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargePlanStateS property: 0x" + tenToHex(557856287) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856287, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getChargePreheatStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856269, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargePreheatStateR property: 0x");
            sb.append(tenToHex(557856269));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setChargePreheatStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setChargePreheatStateS property: 0x" + tenToHex(557856270) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856270, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanEndTimeDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanEndTimeDayS property: 0x" + tenToHex(557856263) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856263, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanEndTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanEndTimeHourS property: 0x" + tenToHex(557856264) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856264, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanEndTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanEndTimeMinuteS property: 0x" + tenToHex(557856265) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856265, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanEndTimeMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanEndTimeMonthS property: 0x" + tenToHex(557856262) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856262, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanEndTimeYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanEndTimeYearS property: 0x" + tenToHex(557856261) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856261, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanStartTimeDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanStartTimeDayS property: 0x" + tenToHex(557856258) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856258, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanStartTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanStartTimeHourS property: 0x" + tenToHex(557856259) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856259, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanStartTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanStartTimeMinuteS property: 0x" + tenToHex(557856260) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856260, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanStartTimeMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanStartTimeMonthS property: 0x" + tenToHex(557856257) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856257, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setEvChargePlanStartTimeYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setEvChargePlanStartTimeYearS property: 0x" + tenToHex(557856256) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856256, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargingType(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHEVChargingType property: 0x" + tenToHex(557855799) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855799, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanEndTimeDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanEndTimeDayS property: 0x" + tenToHex(557855901) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855901, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanEndTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanEndTimeHourS property: 0x" + tenToHex(557855902) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855902, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanEndTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanEndTimeMinuteS property: 0x" + tenToHex(557855903) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855903, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanEndTimeMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanEndTimeMonthS property: 0x" + tenToHex(557855900) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855900, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanEndTimeYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanEndTimeYearS property: 0x" + tenToHex(557855899) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855899, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanStartTimeDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanStartTimeDayS property: 0x" + tenToHex(557855896) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855896, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanStartTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanStartTimeHourS property: 0x" + tenToHex(557855897) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855897, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanStartTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanStartTimeMinuteS property: 0x" + tenToHex(557855898) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855898, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanStartTimeMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanStartTimeMonthS property: 0x" + tenToHex(557855895) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855895, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNewHevChargePlanStartTimeMinuteS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNewHevChargePlanStartTimeMinuteS property: 0x" + tenToHex(CarVendorChargingManager.ID_NEW_H_E_V_CHARGE_PLAN_START_TIME_MINUTE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_NEW_H_E_V_CHARGE_PLAN_START_TIME_MINUTE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNewHevChargePlanEndTimeMinuteS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNewHevChargePlanEndTimeMinuteS property: 0x" + tenToHex(CarVendorChargingManager.ID_NEW_H_E_V_CHARGE_PLAN_END_TIME_MINUTE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_NEW_H_E_V_CHARGE_PLAN_END_TIME_MINUTE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNewEvChargePlanStartTimeMinuteS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNewEvChargePlanStartTimeMinuteS property: 0x" + tenToHex(CarVendorChargingManager.ID_NEW_E_V_CHARGE_PLAN_START_TIME_MINUTE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_NEW_E_V_CHARGE_PLAN_START_TIME_MINUTE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNewEvChargePlanEndTimeMinuteS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNewEvChargePlanEndTimeMinuteS property: 0x" + tenToHex(CarVendorChargingManager.ID_NEW_E_V_CHARGE_PLAN_END_TIME_MINUTE_S) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_NEW_E_V_CHARGE_PLAN_END_TIME_MINUTE_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setHevChargePlanStartTimeYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setHevChargePlanStartTimeYearS property: 0x" + tenToHex(557855894) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557855894, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getNoSenseChargeStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856276, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getNoSenseChargeStateR property: 0x");
            sb.append(tenToHex(557856276));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNoSenseChargeStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNoSenseChargeStateS property: 0x" + tenToHex(557856275) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856275, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge10R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855820, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge10R property: 0x");
            sb.append(tenToHex(557855820));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge11R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855821, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge11R property: 0x");
            sb.append(tenToHex(557855821));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge12R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855822, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge12R property: 0x");
            sb.append(tenToHex(557855822));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge13R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855823, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge13R property: 0x");
            sb.append(tenToHex(557855823));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge14R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855824, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge14R property: 0x");
            sb.append(tenToHex(557855824));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge15R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855825, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge15R property: 0x");
            sb.append(tenToHex(557855825));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge16R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855826, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge16R property: 0x");
            sb.append(tenToHex(557855826));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge17R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855827, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge17R property: 0x");
            sb.append(tenToHex(557855827));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge18R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855828, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge18R property: 0x");
            sb.append(tenToHex(557855828));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge19R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855829, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge19R property: 0x");
            sb.append(tenToHex(557855829));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge1R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855811, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge1R property: 0x");
            sb.append(tenToHex(557855811));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge20R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855830, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge20R property: 0x");
            sb.append(tenToHex(557855830));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge21R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855831, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge21R property: 0x");
            sb.append(tenToHex(557855831));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge22R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855832, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge22R property: 0x");
            sb.append(tenToHex(557855832));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge23R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855833, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge23R property: 0x");
            sb.append(tenToHex(557855833));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge24R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855834, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge24R property: 0x");
            sb.append(tenToHex(557855834));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge25R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855835, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge25R property: 0x");
            sb.append(tenToHex(557855835));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge26R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855836, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge26R property: 0x");
            sb.append(tenToHex(557855836));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge27R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855837, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge27R property: 0x");
            sb.append(tenToHex(557855837));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge28R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855838, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge28R property: 0x");
            sb.append(tenToHex(557855838));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge29R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855839, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge29R property: 0x");
            sb.append(tenToHex(557855839));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855812, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge2R property: 0x");
            sb.append(tenToHex(557855812));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge30R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855840, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge30R property: 0x");
            sb.append(tenToHex(557855840));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge31R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855841, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge31R property: 0x");
            sb.append(tenToHex(557855841));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge32R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855842, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge32R property: 0x");
            sb.append(tenToHex(557855842));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge33R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855843, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge33R property: 0x");
            sb.append(tenToHex(557855843));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge34R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855844, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge34R property: 0x");
            sb.append(tenToHex(557855844));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge35R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855845, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge35R property: 0x");
            sb.append(tenToHex(557855845));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge3R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855813, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge3R property: 0x");
            sb.append(tenToHex(557855813));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge4R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855814, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge4R property: 0x");
            sb.append(tenToHex(557855814));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge5R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855815, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge5R property: 0x");
            sb.append(tenToHex(557855815));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge6R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855816, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge6R property: 0x");
            sb.append(tenToHex(557855816));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge7R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855817, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge7R property: 0x");
            sb.append(tenToHex(557855817));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge8R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855818, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge8R property: 0x");
            sb.append(tenToHex(557855818));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getObcWakeNoSenseCharge9R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557855819, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getObcWakeNoSenseCharge9R property: 0x");
            sb.append(tenToHex(557855819));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getSituPowerGenerationStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856266, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSituPowerGenerationStateR property: 0x");
            sb.append(tenToHex(557856266));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSituPowerGenerationStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSituPowerGenerationStateS property: 0x" + tenToHex(557856267) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856267, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getSituPowerGenerationSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856268, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSituPowerGenerationSupportR property: 0x");
            sb.append(tenToHex(557856268));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorChargingManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSyncTimeDayS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSyncTimeDayS property: 0x" + tenToHex(557856284) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856284, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSyncTimeHourS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSyncTimeHourS property: 0x" + tenToHex(557856285) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856285, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingFrStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856327, 0);
            Log.debug(CarVendorChargingManager.TAG, "getWirelessChargingFrStateR value = " + value + " property: 0x" + tenToHex(557856327));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingFrSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856326, 0);
            Log.debug(CarVendorChargingManager.TAG, "getWirelessChargingFrSwitchStateR value = " + value + " property: 0x" + tenToHex(557856326));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setWirelessChargingFrSwitchStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setWirelessChargingFrSwitchStateS value = " + value + " property: 0x" + tenToHex(557856325));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856325, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingPowerFlStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856328, 0);
            Log.debug(CarVendorChargingManager.TAG, "getWirelessChargingPowerFlStateR value = " + value + " property: 0x" + tenToHex(557856328));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getWirelessChargingPowerFrStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856329, 0);
            Log.debug(CarVendorChargingManager.TAG, "getWirelessChargingPowerFrStateR value = " + value + " property: 0x" + tenToHex(557856329));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSyncTimeMinuteS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSyncTimeMinuteS property: 0x" + tenToHex(557856286) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856286, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSyncTimeMonthS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSyncTimeMonthS property: 0x" + tenToHex(557856283) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856283, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setSyncTimeYearS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setSyncTimeYearS property: 0x" + tenToHex(557856282) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856282, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanEndTimeTagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856338, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanEndTimeTagR value = " + value + " property: 0x" + tenToHex(557856338));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getHevChargePlanStartTimeTagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856337, 0);
            Log.debug(CarVendorChargingManager.TAG, "getHevChargePlanStartTimeTagR value = " + value + " property: 0x" + tenToHex(557856337));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setElectricityPriceLeveSixS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setElectricityPriceLeveSixS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_SIX_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_SIX_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setElectricityPriceLevelEightS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setElectricityPriceLevelEightS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_EIGHT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_EIGHT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setElectricityPriceLevelNineS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setElectricityPriceLevelNineS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_NIGHT_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_NIGHT_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setElectricityPriceLevelSevenS(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setElectricityPriceLevelSevenS value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_SEVEN_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_ELECTRICITY_PRICE_LEVEL_SEVEN_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setNewCarPlanR(int[] value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setNewCarPlanR value = " + value + " property: 0x" + tenToHex(CarVendorChargingManager.ID_NEW_CAR_PLAN_MONTH_S));
        try {
            this.mCarPropertyManager.setProperty(Integer[].class, CarVendorChargingManager.ID_NEW_CAR_PLAN_MONTH_S, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public int getAcChargingLimitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856280, 0);
            Log.debug(CarVendorChargingManager.TAG, "getAcChargingLimitR value = " + value + " property: 0x" + tenToHex(557856280));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void setAcChargingLimitS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorChargingManager.TAG, "setAcChargingLimitS value = " + value + " property: 0x" + tenToHex(557856281));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856281, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void addCarChargingEventListener(CarVendorChargingManager.OnCarChargingEventListener listener) throws CarNotConnectedException {
        OnCarChargingChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarChargingChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorChargingManager.TAG, "addCarChargingEventListener propId size: " + this.mExtChargingIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtChargingIds) {
                int propId = num.intValue();
                Log.debug(CarVendorChargingManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.charging.CarVendorChargingManager
    public void removeCarChargingEventListener(CarVendorChargingManager.OnCarChargingEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarChargingChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorChargingManager.TAG, "removeCarChargingEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ChargingEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        sChargingEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorChargingManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557855744:
                sChargingEventChangeValue.setChargerFaultStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855745:
                sChargingEventChangeValue.setChargerWorkStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                sChargingEventChangeValue.setBatteryManagementDeviceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855746:
                sChargingEventChangeValue.setChargingResetTimeHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855747:
                sChargingEventChangeValue.setChargingResetTimeMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855748:
                sChargingEventChangeValue.setCharingPortLockRebackStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855749:
                sChargingEventChangeValue.setDischargeRequestStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855750:
                sChargingEventChangeValue.setChargerStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557855751:
                sChargingEventChangeValue.setChargingGunStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557855754:
                        sChargingEventChangeValue.setChargingScheduleEnableStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855755:
                        sChargingEventChangeValue.setChargingScheduleStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855756:
                        sChargingEventChangeValue.setChargingScheduleTimeYearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855757:
                        sChargingEventChangeValue.setChargingScheduleTimeMonthR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855758:
                        sChargingEventChangeValue.setChargingScheduleTimeDayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855759:
                        sChargingEventChangeValue.setChargingScheduleTimeHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855760:
                        sChargingEventChangeValue.setChargingScheduleTimeMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855761:
                        sChargingEventChangeValue.setChargeToggleGuardS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855762:
                        sChargingEventChangeValue.setChargeToggleGuardR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855763:
                        sChargingEventChangeValue.setMovePowerStationToggleS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855764:
                        sChargingEventChangeValue.setMovePowerStationToggleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855765:
                        sChargingEventChangeValue.setDischargeLimitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855766:
                        sChargingEventChangeValue.setDischargeLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855767:
                        sChargingEventChangeValue.setDischargeToggleS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855768:
                        sChargingEventChangeValue.setDischargeToggleR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855769:
                        sChargingEventChangeValue.setDischargeTimeHourS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855770:
                        sChargingEventChangeValue.setDischargeTimeMinuteS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855771:
                        sChargingEventChangeValue.setDischargeTimeHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855772:
                        sChargingEventChangeValue.setDischargeTimeMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorChargingManager.ID_PRODUCE_POWER_WHEN_LOW_POWERS /* 557855773 */:
                        sChargingEventChangeValue.setProducePowerWhenLowPowerS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855774:
                        sChargingEventChangeValue.setProducePowerWhenLowPowerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855775:
                        sChargingEventChangeValue.setWirelessChargingSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855776:
                        sChargingEventChangeValue.setWirelessChargingSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557855777:
                        sChargingEventChangeValue.setWirelessChargingStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR /* 557855778 */:
                        sChargingEventChangeValue.setVtovChargeBatteryLevelNumR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorChargingManager.ID_VTOV_CHARGE_BATTERY_LEVEL_NUM_LIMITR /* 557855779 */:
                        sChargingEventChangeValue.setVtovChargeBatteryLevelNumLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case CarVendorChargingManager.ID_DISCHARGE_REMAIN_TIMER /* 557855780 */:
                        sChargingEventChangeValue.setDischargeRemainTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case CarVendorChargingManager.ID_DISCHARGE_VOLTAGER /* 557855783 */:
                                sChargingEventChangeValue.setDischargeVoltageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856266:
                                sChargingEventChangeValue.setSituPowerGenerationStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856276:
                                sChargingEventChangeValue.setNoSenseChargeStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856288:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge1R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856289:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856290:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge3R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856291:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge4R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856292:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge5R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856293:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge6R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856294:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge7R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856295:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge8R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856296:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge9R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856297:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge10R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856298:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge11R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856299:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge12R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856300:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge13R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856301:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge14R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856302:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge15R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856303:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge16R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856304:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge17R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856305:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge18R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856306:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge19R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856307:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge20R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856308:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge21R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856309:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge22R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856310:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge23R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856311:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge24R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856312:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge25R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856313:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge26R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856314:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge27R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856315:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge28R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856316:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge29R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856317:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge30R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856318:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge31R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856319:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge32R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856320:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge33R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856321:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge34R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856322:
                                sChargingEventChangeValue.setBmsWakeNoSenseCharge35R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856323:
                                sChargingEventChangeValue.setVtovDischargeLowPowerHintR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856324:
                                sChargingEventChangeValue.setChargePreheatAllowR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856325:
                                sChargingEventChangeValue.setWirelessChargingFrSwitchStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856326:
                                sChargingEventChangeValue.setWirelessChargingFrSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856327:
                                sChargingEventChangeValue.setWirelessChargingFrStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856328:
                                sChargingEventChangeValue.setWirelessChargingPowerFlStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856329:
                                sChargingEventChangeValue.setWirelessChargingPowerFrStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856330:
                                sChargingEventChangeValue.setgetPortCapStateRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856331:
                                sChargingEventChangeValue.setgetPortCapStateLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856332:
                                sChargingEventChangeValue.setHevChargePlanStartTimeYearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856333:
                                sChargingEventChangeValue.setHevChargePlanStartTimeMonthR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856334:
                                sChargingEventChangeValue.setHevChargePlanStartTimeYearDayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856335:
                                sChargingEventChangeValue.setHevChargePlanStartTimeYearHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856336:
                                sChargingEventChangeValue.setHevChargePlanStartTimeYearMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856337:
                                sChargingEventChangeValue.setHevChargePlanStartTimeTagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856338:
                                sChargingEventChangeValue.setHevChargePlanEndTimeTagR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856339:
                                sChargingEventChangeValue.setChargeStopCapacityStateNotLiR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557856341:
                                sChargingEventChangeValue.setv2gStatusS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R /* 557856437 */:
                                sChargingEventChangeValue.setHevChargePlaneEndTimeYearR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R /* 557856438 */:
                                sChargingEventChangeValue.setHevChargePlanEndTimeMonthR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_H_E_V_CHARGE_PLAN_END_TIME_R /* 557921815 */:
                                sChargingEventChangeValue.setHevChargePlanEndTimeR(CastArrayUtil.toPrimitive((Integer[]) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_CHARGING_POWERR /* 559952904 */:
                                sChargingEventChangeValue.setChargingPowerR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_CHARGE_VOLTAGE_MAXR /* 559952933 */:
                                sChargingEventChangeValue.setChargeVoltageMaxR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_VOLTAGE_MAXR /* 559952934 */:
                                sChargingEventChangeValue.setVoltageMaxR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_DISCHARGE_ELECTRICR /* 559952936 */:
                                sChargingEventChangeValue.setDischargeElectricR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_CHARGE_ELECTRICR /* 559952941 */:
                                sChargingEventChangeValue.setChargeElectricR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_VTOV_DISCHARGE_QUANTITYR /* 559952946 */:
                                sChargingEventChangeValue.setVtovDischargeQuantityR(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_V2_G_POWER_S /* 559953494 */:
                                sChargingEventChangeValue.setv2gPowerS(CastArrayUtil.toPrimitive((Float) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_DAY_R /* 567856339 */:
                                sChargingEventChangeValue.setHevChargePlanEndTimeYearDayR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R /* 567856340 */:
                                sChargingEventChangeValue.setHevChargePlanEndTimeYearHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case CarVendorChargingManager.ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R /* 567856341 */:
                                sChargingEventChangeValue.setHevChargePlanEndTimeYearMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case CarVendorChargingManager.ID_DISCHARGE_GUN_TIMERR /* 557855785 */:
                                        sChargingEventChangeValue.setDischargeGunTimerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorChargingManager.ID_DISCHARGE_TOTAL_TIMER /* 557855786 */:
                                        sChargingEventChangeValue.setDischargeTotalTimeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorChargingManager.ID_DISCHARGE_OVER_REASONR /* 557855787 */:
                                        sChargingEventChangeValue.setDischargeOverReasonR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorChargingManager.ID_CHARGE_VOLTAGER /* 557855788 */:
                                        sChargingEventChangeValue.setChargeVoltageR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case CarVendorChargingManager.ID_CHARGE_BATTERY_LEVEL_BEGINR /* 557855790 */:
                                                sChargingEventChangeValue.setChargeBatteryLevelBeginR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case CarVendorChargingManager.ID_CHARGE_BATTERY_LIMIT_TEMPR /* 557855791 */:
                                                sChargingEventChangeValue.setChargeBatteryLimitTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case CarVendorChargingManager.ID_CHARGE_BATTERY_TEMPR /* 557855792 */:
                                                sChargingEventChangeValue.setChargeBatteryTempR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case CarVendorChargingManager.ID_VTOV_DISCHARGE_LOWEST_VALR /* 557855793 */:
                                                sChargingEventChangeValue.setVtovDischargeLowestValR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case CarVendorChargingManager.ID_BATTERY_TYPER /* 557855795 */:
                                                        sChargingEventChangeValue.setBatteryTypeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855796:
                                                        sChargingEventChangeValue.setFullChgRemanTimHR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855797:
                                                        sChargingEventChangeValue.setFullChgRemanTimMR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855798:
                                                        sChargingEventChangeValue.setChargerWorkStateFixR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855799:
                                                        sChargingEventChangeValue.setHevChargingType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855800:
                                                        sChargingEventChangeValue.setChargingCapStateLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855801:
                                                        sChargingEventChangeValue.setChargingCapStateRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855802:
                                                        sChargingEventChangeValue.setChargeTempCtlStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855803:
                                                        sChargingEventChangeValue.setChargeTempCtlStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855804:
                                                        sChargingEventChangeValue.setChargeStopCapacityStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855805:
                                                        sChargingEventChangeValue.setChargeStopCapacityStateS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855806:
                                                        sChargingEventChangeValue.setChargeToaggleGuardMixR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855807:
                                                        sChargingEventChangeValue.setVtovDischargeLimitValR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855808:
                                                        sChargingEventChangeValue.setChargingRestTimeHourR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855809:
                                                        sChargingEventChangeValue.setChargingRestTimeMinuteR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855810:
                                                        sChargingEventChangeValue.setVtovDischargeConnectState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855811:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge1R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855812:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge2R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855813:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge3R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855814:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge4R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855815:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge5R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855816:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge6R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855817:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge7R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855818:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge8R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855819:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge9R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855820:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge10R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855821:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge11R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855822:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge12R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855823:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge13R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855824:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge14R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855825:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge15R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855826:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge16R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855827:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge17R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855828:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge18R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855829:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge19R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855830:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge20R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855831:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge21R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855832:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge22R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855833:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge23R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855834:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge24R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855835:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge25R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855836:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge26R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855837:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge27R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855838:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge28R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855839:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge29R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855840:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge30R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855841:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge31R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855842:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge32R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855843:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge33R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855844:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge34R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557855845:
                                                        sChargingEventChangeValue.setObcWakeNoSenseCharge35R(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557856268:
                                                                sChargingEventChangeValue.setSituPowerGenerationSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856269:
                                                                sChargingEventChangeValue.setChargePreheatStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557856272:
                                                                        sChargingEventChangeValue.setCarPlanStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856273:
                                                                        sChargingEventChangeValue.setCarPlanAccOnStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856274:
                                                                        sChargingEventChangeValue.setCarPlanBatteryHeatStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557856280:
                                                                                sChargingEventChangeValue.setAcChargingLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case 557856281:
                                                                                sChargingEventChangeValue.setAcChargingLimitS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case CarVendorChargingManager.ID_V2_G_CMD_S /* 557856343 */:
                                                                                        sChargingEventChangeValue.setv2gCmdS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorChargingManager.ID_V2_G_STATUS_R /* 557856344 */:
                                                                                        sChargingEventChangeValue.setv2gStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorChargingManager.ID_V2_G_CHARGE_STATUS_R /* 557856345 */:
                                                                                        sChargingEventChangeValue.setv2gChargeStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorChargingManager.ID_V2_G_OUTPUT_POWER_R /* 557856346 */:
                                                                                        sChargingEventChangeValue.setv2gOutputPowerR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_LEFT_R /* 557856347 */:
                                                                                        sChargingEventChangeValue.setElectricPortCapStateLeftR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorChargingManager.ID_ELECTRIC_PORT_CAP_STATE_RIGHT_R /* 557856348 */:
                                                                                        sChargingEventChangeValue.setElectricPortCapStateRightR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorChargingManager.TAG, "break other properties ,except charging");
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
        Log.debug(CarVendorChargingManager.TAG, "convertCarVendorPropertyValue " + sChargingEventChangeValue.toString(), DEBUG_LOGGER);
        return sChargingEventChangeValue;
    }

    private OnCarChargingChangeEventListenerProxy findListenerLocked(CarVendorChargingManager.OnCarChargingEventListener listener) {
        for (OnCarChargingChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarChargingChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorChargingManager.OnCarChargingEventListener listener;

        public OnCarChargingChangeEventListenerProxy(CarVendorChargingManager.OnCarChargingEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorChargingMgr.this.traceCallbackPrepare();
                ChargingEventChangeValue newValue = CarVendorChargingMgr.convertCarVendorProperty(value);
                CarVendorChargingMgr.this.traceCallbackBefore();
                this.listener.onChargingEventChange(newValue);
                CarVendorChargingMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorChargingManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorChargingManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onChargingErrorEvent(propertyId, zone);
        }
    }
}
