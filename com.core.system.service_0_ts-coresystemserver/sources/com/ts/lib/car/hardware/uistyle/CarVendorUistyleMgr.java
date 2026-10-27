package com.ts.lib.car.hardware.uistyle;

import android.car.Car;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.Settings;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.CarVendorManagerBase;
import com.ts.lib.car.CastArrayUtil;
import com.ts.lib.car.Log;
import com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class CarVendorUistyleMgr extends CarVendorManagerBase implements CarVendorUistyleManager {
    private static final int VEHICLE_PROPERTY_ON_CHANGE = 1;
    private CarPropertyManager mCarPropertyManager;
    private Car mEmbeddedCar;
    private static boolean DEBUG_LOGGER = false;
    private static UistyleEventChangeValue sUistyleEventChangeValue = new UistyleEventChangeValue();
    private final LinkedList<OnCarUistyleChangeEventListenerProxy> mListeners = new LinkedList<>();
    private Integer[] mExtUistyleIds = {557846542, 557846529, 557856074, 557856017, 557856015, 557856186, 557856184, 557856185, 557856187, 557856124, 557856053, 557856052, 557856051, 557856067, 557856060, 557856064, 557856065, 557856066, 557856071, 557856037, 557856056, 557856182, 557856183, 557856181, 557856178, 557856179, 557856180, 557856001, 557856128, 557856031, 557856047, 557856046, 557856057, 557856176, 557856177, 557856175, 557856174, 557846594, 557856121, 557856196, 557856172, 557856119, 557856076, 557856043, 557856003, 557856199, 557856197, 557856054, 557856061, 557856004, 557856206, 557856207, 557856002, 557856127, 557856198, 557856202, 557879608, Integer.valueOf(CarVendorUistyleManager.ID_DOOR_LIGHT_SUPPORT_R), 557859113, 557859114, 557859108, 557859112, 557859108, 557859115, 557856204, 557856205, 557856035, 557856034, 557856168, 557856169, 557856167, 557856165, 557856166, 557856159, 557856160, 557856158, 557856156, 557856157, 557856191, 557856192, 557856190, 557856072, 557856195, Integer.valueOf(CarVendorUistyleManager.ID_INSTRUMENT_HEIGHT_SUPPORT), 557856193, 557856075, 557856045, 557856123, 557856122, 557856062, 557856038, 557856023, 557856116, 557856120, 557856200, 557856114, 557856115, 557856058, 557856117, 557856048, 557856000, 557856068, 557856069, 557856070, 557856118, 557856147, 557856007, 557856073, 557856049, 557856050, 557856170, 557856125, 557856113, 557856063, 557856129, 557856133, 557856131, 557856134, 557856132, 557856136, 557856135, 557856139, 557856138, 557856137, 557856140, 557856130, 557856126, 557856036, 557856163, 557856164, 557856161, 557856162, 557856144, 557856145, 557856143, 557856141, 557856142, 557856154, 557856155, 557856153, 557856152, 557856150, 557856151, 557856149, 557856148, 557856201, 557856146, 557856059, 557856055, 557856016, Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_AI_CARE), 557856106, 557856099, 557856107, 557856108, 557856102, 557856033, 557856103, 557856100, 557856091, 557856086, Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_END_TO_END_NAVIGATION), 557856110, 557856027, 557856028, 557856030, 557856029, 557856094, 557856095, 557856079, 557856026, 557856077, 557856078, 557856088, 557856089, 557856032, 557856082, 557856085, 557856083, 557856084, 557856101, 557856025, 557856109, 557856097, 557856098, 557856024, 557846530, 557856010, 557846536, 557846533, 557846531, 557846537, 557846538, 557846534, 557856092, 557856105, 557856093, 557856171, 557856044, 557856020, 557856021, 557856019, 557856022, 557856096, 557856111, 557856014, 557856018, 557856090, 557856087, Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_URBAN_NAVIGATION), 557856173, 557856040, 557856208, 557856209, 557856211, Integer.valueOf(CarVendorUistyleManager.ID_ADAS_SYSTEM_STATER), Integer.valueOf(CarVendorUistyleManager.ID_AVERAGE_SPEED_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_DRIVE_TIME_INTERFACE_STATUS_FOR_SINGLE_TRIPR), Integer.valueOf(CarVendorUistyleManager.ID_DRIVING_RANGE_INTERFACE_STATUS_FOR_FUELR), Integer.valueOf(CarVendorUistyleManager.ID_ENERGY_FLOW_DIAGRAM_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_EXTERNAL_CHARGING_POWER_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_FUEL_CONSUMPTION_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_INSTANTANEOUS_FUEL_CONSUMPTION_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATIONR), Integer.valueOf(CarVendorUistyleManager.ID_MEMORY_PARKINGR), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CANR), Integer.valueOf(CarVendorUistyleManager.ID_REAR_SEAT_RECLINE_CONFIGURATIONR), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATIONR), Integer.valueOf(CarVendorUistyleManager.ID_STEERING_WHEEL_HEATING_CONFIGURATIONR), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_EXTREMER), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_SMART_CHARGING_PHEVR), Integer.valueOf(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATER), Integer.valueOf(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATER), Integer.valueOf(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATER), Integer.valueOf(CarVendorUistyleManager.ID_TIRE_PRESSURE_INTERFACE_STATUSR), Integer.valueOf(CarVendorUistyleManager.ID_VALET_PARKINGR), Integer.valueOf(CarVendorUistyleManager.ID_ADAS_SYSTEM_STATE_R), Integer.valueOf(CarVendorUistyleManager.ID_CAR_PLAN_SUPPORT_R), Integer.valueOf(CarVendorUistyleManager.ID_CHARGE_PREHEAT_SUPPORT_R), Integer.valueOf(CarVendorUistyleManager.ID_DMS_ASSEMBLE_STATUS_R), Integer.valueOf(CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_LEFT_CTRL_R), 557859101, 557870336, Integer.valueOf(CarVendorUistyleManager.ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_THREER), Integer.valueOf(CarVendorUistyleManager.ID_ELECTRIC_FRONT_HATCH_CONFIGURATION_R), 557850645, Integer.valueOf(CarVendorUistyleManager.ID_HUD_MODE_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_INTELLIGENT_SCREEN_CASTING_SWITCH_CONFIGURATION_R), 557856203, Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_LEG_BRACKET_R), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_LEFT_RES_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_G_CTRL_R), 557856225, Integer.valueOf(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_G_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_LEFT_BODY_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_G_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_LEFT_BODY_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_NO_SENSE_CHARGE_SUPPORT_R), 557862942, Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_ELECTRIC_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_G_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_G_CTRL_R), Integer.valueOf(CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CAN_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_LEFT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_REAR_RIGHT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R), 557856204, Integer.valueOf(CarVendorUistyleManager.ID_REST_MODE_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_MEMORY_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_LEG_BRACKET_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATION_BY_EUC_R), Integer.valueOf(CarVendorUistyleManager.ID_RIGHT_RES_CONFIGURATION_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_ESP_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_FR_ELECTRIC_AIR_OUTLET_R), Integer.valueOf(CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_LEFT_BACK_ECU), Integer.valueOf(CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_RIGHT_BACK_ECU), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_DOUBLE_CONFIRM_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_T_S_R_FLOW_R), 557859175, Integer.valueOf(CarVendorUistyleManager.ID_RUNNING_CLOSE_DOOR_SUPPORT_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_S_L_W_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_A_C_CHARGING_LIMIT_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_ROAD_MODE_R), Integer.valueOf(CarVendorUistyleManager.ID_CPD_SW_MEM_STATUS_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_MODE_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SHAPE_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SIZE_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_R_N_C_R), Integer.valueOf(CarVendorUistyleManager.ID_CABIN_MOVEMENT_MONITORING_FUNCTION_R), Integer.valueOf(CarVendorUistyleManager.ID_ENERGY_SHOW_SUPPORT_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_V_C_U_R), Integer.valueOf(CarVendorUistyleManager.ID_S_O_C_TARGET_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_MILEAGE_RESET_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_CROSS_DOMAIN_PLATFORM_CONFIGS_R), Integer.valueOf(CarVendorUistyleManager.ID_CURRENT_TRIP_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_ENERGY_CONTRAST_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_ENERGY_SHOW_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_HISTORY_ENERGY_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_HISTORY_ENERGY_STATISTICS_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_RECENTLY50KM_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_BABY_MODE_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_I_O_P_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_S_A_O_C_R), Integer.valueOf(CarVendorUistyleManager.ID_VCU_ENCODED_R), Integer.valueOf(CarVendorUistyleManager.ID_A_E_B_DOUBLE_CONFIRM_R), Integer.valueOf(CarVendorUistyleManager.ID_CHARGE_LIMIT_SUPPORT_NOT_LI_R), Integer.valueOf(CarVendorUistyleManager.ID_ELEC_WHERE_CONFIG_R), Integer.valueOf(CarVendorUistyleManager.ID_E_L_K_A_DOUBLE_CONFIRM_R), Integer.valueOf(CarVendorUistyleManager.ID_F_C_W_DOUBLE_CONFIRM_R), Integer.valueOf(CarVendorUistyleManager.ID_L_D_A_DOUBLE_CONFIRM_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_A_C_C_R), Integer.valueOf(CarVendorUistyleManager.ID_SUPPORT_F_C_S_R_R), 557859257, 557859258, 557859259, 557859261, 557859260, 557859262};

    public CarVendorUistyleMgr(Car embeddedCar) {
        this.mEmbeddedCar = embeddedCar;
        initManagers();
        checkLogLogger();
    }

    public void checkLogLogger() {
        Context context = this.mEmbeddedCar.getContext();
        DEBUG_LOGGER = Settings.System.getInt(context.getContentResolver(), CarVendorUistyleManager.PERSIST_DEBUG_LOGGER, 0) == 1;
        Log.info(CarVendorUistyleManager.TAG, "CAR.VENDOR.LOGGER is " + DEBUG_LOGGER);
    }

    private void initManagers() {
        try {
            Log.debug(CarVendorUistyleManager.TAG, "initManagers start " + this.mCarPropertyManager, DEBUG_LOGGER);
            this.mCarPropertyManager = (CarPropertyManager) this.mEmbeddedCar.getCarManager("property");
            Log.debug(CarVendorUistyleManager.TAG, "initManagers end " + this.mCarPropertyManager, DEBUG_LOGGER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarDoorConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859259, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCarDoorConfigR value = " + value + " property: 0x" + tenToHex(557859259));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTowingCruiseConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859261, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getTowingCruiseConfigurationR value = " + value + " property: 0x" + tenToHex(557859261));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTrailerDrModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859260, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getTrailerDrModeConfigR value = " + value + " property: 0x" + tenToHex(557859260));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTrlrCrsConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859262, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getTrlrCrsConfigR value = " + value + " property: 0x" + tenToHex(557859262));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getNonElecLeftPortConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859257, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getNonElecLeftPortConfigR value = " + value + " property: 0x" + tenToHex(557859257));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getNonElecRightPortConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859258, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getNonElecRightPortConfigR value = " + value + " property: 0x" + tenToHex(557859258));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFcsrR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_F_C_S_R_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportFcsrR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_F_C_S_R_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAccR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_A_C_C_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportAccR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_A_C_C_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAebDoubleConfirmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_A_E_B_DOUBLE_CONFIRM_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getAebDoubleConfirmR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_A_E_B_DOUBLE_CONFIRM_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getChargeLimitSupportNotLiR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CHARGE_LIMIT_SUPPORT_NOT_LI_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getChargeLimitSupportNotLiR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CHARGE_LIMIT_SUPPORT_NOT_LI_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElecWhereConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ELEC_WHERE_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getElecWhereConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ELEC_WHERE_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElkaDoubleConfirmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_E_L_K_A_DOUBLE_CONFIRM_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getElkaDoubleConfirmR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_E_L_K_A_DOUBLE_CONFIRM_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFcwDoubleConfirmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_F_C_W_DOUBLE_CONFIRM_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFcwDoubleConfirmR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_F_C_W_DOUBLE_CONFIRM_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLdaDoubleConfirmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_L_D_A_DOUBLE_CONFIRM_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getLdaDoubleConfirmR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_L_D_A_DOUBLE_CONFIRM_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public byte[] getVcuEncodedR() throws CarNotConnectedException {
        try {
            CarPropertyValue<byte[]> value = this.mCarPropertyManager.getProperty(byte[].class, CarVendorUistyleManager.ID_VCU_ENCODED_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getVcuEncodedR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_VCU_ENCODED_R));
            sb.append(" class: byte[] value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSlifR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_S_L_I_F_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportSlifR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_S_L_I_F_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCrossDomainPlatformConfigsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CROSS_DOMAIN_PLATFORM_CONFIGS_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCrossDomainPlatformConfigsR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CROSS_DOMAIN_PLATFORM_CONFIGS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCurrentTripConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CURRENT_TRIP_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCurrentTripConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CURRENT_TRIP_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyContrastConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ENERGY_CONTRAST_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getEnergyContrastConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ENERGY_CONTRAST_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyShowConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ENERGY_SHOW_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getEnergyShowConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ENERGY_SHOW_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHistoryEnergyConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HISTORY_ENERGY_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getHistoryEnergyConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_HISTORY_ENERGY_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHistoryEnergyStatisticsConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HISTORY_ENERGY_STATISTICS_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getHistoryEnergyStatisticsConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_HISTORY_ENERGY_STATISTICS_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRecently50kmConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RECENTLY50KM_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRecently50kmConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_RECENTLY50KM_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportBabyModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_BABY_MODE_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportBabyModeConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_BABY_MODE_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportIopR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_I_O_P_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportIopR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_I_O_P_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSaocR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_S_A_O_C_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportSaocR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_S_A_O_C_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRunningCodingConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RUNNING_CODING_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRunningCodingConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_RUNNING_CODING_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCuraAssembleStatusFunctionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CURA_ASSEMBLE_STATUS_FUNCTION_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCuraAssembleStatusFunctionR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CURA_ASSEMBLE_STATUS_FUNCTION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDiDynaConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DI_DYNA_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getDiDynaConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_DI_DYNA_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSandModeAbsConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SAND_MODE_ABS_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSandModeAbsConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SAND_MODE_ABS_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportCampModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_CAMP_MODE_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportCampModeConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_CAMP_MODE_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRestModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_REST_MODE_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportRestModeConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_REST_MODE_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMileageResetConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MILEAGE_RESET_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getMileageResetConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_MILEAGE_RESET_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getWadingModeSetFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_WADING_MODE_SET_FLAG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getWadingModeSetFlagR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_WADING_MODE_SET_FLAG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcceleratorPedalSuppressionSystemR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ACCELERATOR_PEDAL_SUPPRESSION_SYSTEM_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getAcceleratorPedalSuppressionSystemR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ACCELERATOR_PEDAL_SUPPRESSION_SYSTEM_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBatteryHealthConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_BATTERY_HEALTH_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getBatteryHealthConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_BATTERY_HEALTH_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyFeedbackSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ENERGY_FEEDBACK_SUPPORT_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getEnergyFeedbackSupportR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ENERGY_FEEDBACK_SUPPORT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCabinMovementMonitoringFunctionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CABIN_MOVEMENT_MONITORING_FUNCTION_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCabinMovementMonitoringFunctionR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CABIN_MOVEMENT_MONITORING_FUNCTION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyShowSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ENERGY_SHOW_SUPPORT_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getEnergyShowSupportR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_ENERGY_SHOW_SUPPORT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportChargeHEVThreeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportChargeHEVThreeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportChargeHEVThreeVCUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_V_C_U_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportChargeHEVThreeVCUR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_V_C_U_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getsocTargetConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_S_O_C_TARGET_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getsocTargetConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_S_O_C_TARGET_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeMinWagePowerGenerationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859198, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeMinWagePowerGenerationR value = " + value + " property: 0x" + tenToHex(557859198));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeTemperatureRegulationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859196, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeTemperatureRegulationR value = " + value + " property: 0x" + tenToHex(557859196));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportOilBladeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_OIL_BLADE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportOilBladeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_OIL_BLADE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportOilBrakeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_OIL_BRAKE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportOilBrakeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_OIL_BRAKE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportOilCoolR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_OIL_COOL_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportOilCoolR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_OIL_COOL_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportOilEngineR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_OIL_ENGINE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportOilEngineR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_OIL_ENGINE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportOilTransmissionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_OIL_TRANSMISSION_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportOilTransmissionR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_OIL_TRANSMISSION_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSmrmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_S_M_R_M_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportSmrmR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_S_M_R_M_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportiTacConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORTI_T_A_C_CONFIG_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportiTacConfigR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORTI_T_A_C_CONFIG_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeChildLockFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859201, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeChildLockFlagR value = " + value + " property: 0x" + tenToHex(557859201));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeThreePositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859212, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeThreePositionR value = " + value + " property: 0x" + tenToHex(557859212));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportiTacR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORTI_T_A_C_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportiTacR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORTI_T_A_C_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int gethevCarPlanSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_H_E_V_CAR_PLAN_SUPPORT_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "gethevCarPlanSupportR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_H_E_V_CAR_PLAN_SUPPORT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeWorkingModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859195, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeWorkingModeR value = " + value + " property: 0x" + tenToHex(557859195));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeExpansionAndContractionFunctionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859197, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeExpansionAndContractionFunctionR value = " + value + " property: 0x" + tenToHex(557859197));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarFridgeTypeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859199, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCarFridgeTypeR value = " + value + " property: 0x" + tenToHex(557859199));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getExclusiveSaveFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859200, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getExclusiveSaveFlagR value = " + value + " property: 0x" + tenToHex(557859200));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeScreenFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859202, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeScreenFlagR value = " + value + " property: 0x" + tenToHex(557859202));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeDelayedPowerOutageR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859203, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeDelayedPowerOutageR value = " + value + " property: 0x" + tenToHex(557859203));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFrontFridgeDisinfectFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859204, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFrontFridgeDisinfectFlagR value = " + value + " property: 0x" + tenToHex(557859204));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeNumR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859205, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeNumR value = " + value + " property: 0x" + tenToHex(557859205));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearFridgeDisinfectFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859206, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRearFridgeDisinfectFlagR value = " + value + " property: 0x" + tenToHex(557859206));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearFridgeKeyTypeFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859207, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRearFridgeKeyTypeFlagR value = " + value + " property: 0x" + tenToHex(557859207));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearFridgeWorkTypeFlagR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859208, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRearFridgeWorkTypeFlagR value = " + value + " property: 0x" + tenToHex(557859208));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightFridgeHeatingSchemeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859209, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getRearRightFridgeHeatingSchemeR value = " + value + " property: 0x" + tenToHex(557859209));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeOnePositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859210, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeOnePositionR value = " + value + " property: 0x" + tenToHex(557859210));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFridgeTwoPositionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859211, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFridgeTwoPositionR value = " + value + " property: 0x" + tenToHex(557859211));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCpdSwMemStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CPD_SW_MEM_STATUS_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCpdSwMemStatusR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_CPD_SW_MEM_STATUS_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setRncStateS(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setRncStateS value = " + value + " property: 0x" + tenToHex(557859185));
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557859185, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportInsUIModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_INS_U_I_MODE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportInsUIModeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_MODE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportInsUIShapeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SHAPE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportInsUIShapeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SHAPE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportInsUISizeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SIZE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportInsUISizeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_INS_U_I_SIZE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRNCR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_R_N_C_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportRNCR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_R_N_C_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportDoubleConfirmR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_DOUBLE_CONFIRM_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportDoubleConfirmR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_DOUBLE_CONFIRM_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRunningCloseDoorSupportR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RUNNING_CLOSE_DOOR_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRunningCloseDoorSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RUNNING_CLOSE_DOOR_SUPPORT_R));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportTsrFlowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_T_S_R_FLOW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportTsrFlowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_T_S_R_FLOW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTailgateTypeMarkingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859175, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTailgateTypeMarkingR property: 0x");
            sb.append(tenToHex(557859175));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDynamicSuspensionModeByCanFdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_FD_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getDynamicSuspensionModeByCanFdR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_FD_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDynamicSuspensionModeByCanR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getDynamicSuspensionModeByCanR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_DYNAMIC_SUSPENSION_MODE_BY_CAN_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportManualModelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_MANUAL_MODE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportManualModelR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_MANUAL_MODE_R), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCpdSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859169, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getCpdSupportR value = " + value + " property: 0x" + tenToHex(557859169), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSmartChargingPhevPhase2R() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859170, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportSmartChargingPhevPhase2R value = " + value + " property: 0x" + tenToHex(557859170), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBsisSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859167, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getBsisSupportR value = " + value + " property: 0x" + tenToHex(557859167), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFeaturesStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870415, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getFeaturesStateR value = " + value + " property: 0x" + tenToHex(557870415), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMoisSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859168, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getMoisSupportR value = " + value + " property: 0x" + tenToHex(557859168), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportShakeAlertR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856083, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportShakeAlertR value = " + value + " property: 0x" + tenToHex(557856083), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAllRearSeatOneButtonDownSwitchDisplayConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ALL_REAR_SEAT_ONE_BUTTON_DOWN_SWITCH_DISPLAY_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAllRearSeatOneButtonDownSwitchDisplayConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ALL_REAR_SEAT_ONE_BUTTON_DOWN_SWITCH_DISPLAY_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientModeSwitchSettingsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_AMBIENT_MODE_SWITCH_SETTINGS_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientModeSwitchSettingsR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_AMBIENT_MODE_SWITCH_SETTINGS_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859134, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(557859134));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859144, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(557859144));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraEerRomDeviceAddressR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_EER_ROM_DEVICE_ADDRESS_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraEerRomDeviceAddressR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_EER_ROM_DEVICE_ADDRESS_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraIspIdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_ISP_ID_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraIspIdR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_ISP_ID_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraLensIdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_LENS_ID_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraLensIdR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_LENS_ID_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraSerializerIdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_SERIALIZER_ID_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraSerializerIdR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_SERIALIZER_ID_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraSupplierAndTestBoardInfoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_SUPPLIER_AND_TEST_BOARD_INFO_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraSupplierAndTestBoardInfoR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_SUPPLIER_AND_TEST_BOARD_INFO_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraVideoFormatR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_VIDEO_FORMAT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraVideoFormatR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_VIDEO_FORMAT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraVideoFrameRateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_VIDEO_FRAME_RATE_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraVideoFrameRateR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_VIDEO_FRAME_RATE_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCameraVideoResolutionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAMERA_VIDEO_RESOLUTION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCameraVideoResolutionR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAMERA_VIDEO_RESOLUTION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getComfortableParkingBy37fR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_COMFORTABLE_PARKING_BY37_F_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComfortableParkingBy37FR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_COMFORTABLE_PARKING_BY37_F_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCvcConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CVC_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCvcConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CVC_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDiSusShowStateByCanFdR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DI_SUS_SHOW_STATE_BY_CAN_F_D_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDiSusShowStateByCanFDR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DI_SUS_SHOW_STATE_BY_CAN_F_D_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDiSusShowStateByCanR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DI_SUS_SHOW_STATE_BY_CAN_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDiSusShowStateByCanR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DI_SUS_SHOW_STATE_BY_CAN_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriverSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverSeatHotStoneMassageConfigurationByEcuR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFoldDownTheSecondRowOfSeatsConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_FOLD_DOWN_THE_SECOND_ROW_OF_SEATS_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFoldDownTheSecondRowOfSeatsConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_FOLD_DOWN_THE_SECOND_ROW_OF_SEATS_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEADREST_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEADREST_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEADREST_HIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEADREST_HIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEADREST_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HEIGHT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentSpeedLimitMapVersionDisplayR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856845, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentSpeedLimitMapVersionDisplayR property: 0x");
            sb.append(tenToHex(557856845));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatHotStoneMassageConfigurationByEcuR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEG_REST_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEVEL_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLowSpeedEmergencyBrakingConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LOW_SPEED_EMERGENCY_BRAKING_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLowSpeedEmergencyBrakingConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LOW_SPEED_EMERGENCY_BRAKING_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeatR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_LEFT_REAR_SEAT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOneButtonDownConfigurationOfTheThirdRowLeftRearSeatR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_LEFT_REAR_SEAT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeatR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_RIGHT_REAR_SEAT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOneButtonDownConfigurationOfTheThirdRowRightRearSeatR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_CONFIGURATION_OF_THE_THIRD_ROW_RIGHT_REAR_SEAT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeatsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_SWITCH_DISPLAY_FOR_THE_THIRD_ROW_OF_SEATS_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOneButtonDownSwitchDisplayForTheThirdRowOfSeatsR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ONE_BUTTON_DOWN_SWITCH_DISPLAY_FOR_THE_THIRD_ROW_OF_SEATS_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatHotStoneMassageConfigurationByEcuR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_LEFT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRowR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_POT_ADJUSTMENT_SWITCH_CONFIG_ON_THE_RIGHT_SIDE_OF_THE_THIRD_ROW_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRemindSpeedSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REMIND_SPEED_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemindSpeedSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REMIND_SPEED_SUPPORT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatHotStoneMassageConfigurationByEcuR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatHotStoneMassageConfigurationByEcuR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSideDistanceWarningConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SIDE_DISTANCE_WARNING_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSideDistanceWarningConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SIDE_DISTANCE_WARNING_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getStarTopSwitchSettingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_STAR_TOP_SWITCH_SETTING_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getStarTopSwitchSettingR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_STAR_TOP_SWITCH_SETTING_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTrailerModeConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_TRAILER_MODE_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrailerModeConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_TRAILER_MODE_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getV2xConfigR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_V2X_CONFIG_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getV2xConfigR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_V2X_CONFIG_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getWarningAuxiliarySwitchSettingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_WARNING_AUXILIARY_SWITCH_SETTING_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWarningAuxiliarySwitchSettingR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_WARNING_AUXILIARY_SWITCH_SETTING_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getWelcomeCeremonySwitchSettingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_WELCOME_CEREMONY_SWITCH_SETTING_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWelcomeCeremonySwitchSettingR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_WELCOME_CEREMONY_SWITCH_SETTING_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getComfortableSpeedUp() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856211, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComfortableSpeedUp property: 0x");
            sb.append(tenToHex(557856211));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getACAutoAirSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856064, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACAutoAirSupport property: 0x");
            sb.append(tenToHex(557856064));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getACBTWindSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856065, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACBTWindSupport property: 0x");
            sb.append(tenToHex(557856065));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getACPauseCycleSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856066, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACPauseCycleSupport property: 0x");
            sb.append(tenToHex(557856066));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getACTunnelCycleSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856071, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getACTunnelCycleSupport property: 0x");
            sb.append(tenToHex(557856071));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcFaultNumShownState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846542, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcFaultNumShownState property: 0x");
            sb.append(tenToHex(557846542));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846529, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcType property: 0x");
            sb.append(tenToHex(557846529));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcWarmSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856074, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWarmSupport property: 0x");
            sb.append(tenToHex(557856074));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcWarmTypeOnlineState() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856017, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWarmTypeOnlineState property: 0x");
            sb.append(tenToHex(557856017));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAcWindModeNum() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856015, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAcWindModeNum property: 0x");
            sb.append(tenToHex(557856015));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientAreaSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856186, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientAreaSupport property: 0x");
            sb.append(tenToHex(557856186));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientColorsSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856188, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientColorsSupport property: 0x");
            sb.append(tenToHex(557856188));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientLightColorSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856184, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientLightColorSupport property: 0x");
            sb.append(tenToHex(557856184));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856185, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientLightSupport property: 0x");
            sb.append(tenToHex(557856185));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmbientMusicModeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856187, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmbientMusicModeSupport property: 0x");
            sb.append(tenToHex(557856187));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAppUseLimitSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856124, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAppUseLimitSupport property: 0x");
            sb.append(tenToHex(557856124));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAutoExternalRearMirrorFollowUpSwitchSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856053, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoExternalRearMirrorFollowUpSwitchSupport property: 0x");
            sb.append(tenToHex(557856053));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAutoPowerOffSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856052, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPowerOffSupport property: 0x");
            sb.append(tenToHex(557856052));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAutoPowerOnStateSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856051, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPowerOnStateSupport property: 0x");
            sb.append(tenToHex(557856051));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAutoPurificationSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856067, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoPurificationSupport property: 0x");
            sb.append(tenToHex(557856067));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAutoWiperSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856060, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAutoWiperSupport property: 0x");
            sb.append(tenToHex(557856060));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBackHomeDelaySupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856037, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBackHomeDelaySupport property: 0x");
            sb.append(tenToHex(557856037));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getBrakePedalModeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856056, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getBrakePedalModeSupport property: 0x");
            sb.append(tenToHex(557856056));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856182, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportBackL property: 0x");
            sb.append(tenToHex(557856182));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856183, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportBackR property: 0x");
            sb.append(tenToHex(557856183));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856181, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportCopilot property: 0x");
            sb.append(tenToHex(557856181));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856178, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportFirst property: 0x");
            sb.append(tenToHex(557856178));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856179, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportSecond property: 0x");
            sb.append(tenToHex(557856179));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCaliperSplintSupportThird() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856180, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCaliperSplintSupportThird property: 0x");
            sb.append(tenToHex(557856180));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarNum() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856001, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarNum property: 0x");
            sb.append(tenToHex(557856001));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarTipsSoundSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856128, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarTipsSoundSupport property: 0x");
            sb.append(tenToHex(557856128));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856031, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarType property: 0x");
            sb.append(tenToHex(557856031));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getChargeLimitSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856047, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeLimitSupport property: 0x");
            sb.append(tenToHex(557856047));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getChargeToaggleGuardSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856046, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargeToaggleGuardSupport property: 0x");
            sb.append(tenToHex(557856046));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getComfortableParkingSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856057, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getComfortableParkingSupport property: 0x");
            sb.append(tenToHex(557856057));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCushionSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856176, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCushionSupportBackL property: 0x");
            sb.append(tenToHex(557856176));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCushionSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856177, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCushionSupportBackR property: 0x");
            sb.append(tenToHex(557856177));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCushionSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856175, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCushionSupportCopilot property: 0x");
            sb.append(tenToHex(557856175));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCushionSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856174, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCushionSupportSecond property: 0x");
            sb.append(tenToHex(557856174));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDMType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856004, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDMType property: 0x");
            sb.append(tenToHex(557856004));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDayLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856041, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDayLightSupport property: 0x");
            sb.append(tenToHex(557856041));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDefrostRearConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846594, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDefrostRearConfig property: 0x");
            sb.append(tenToHex(557846594));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDigtalKeySupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856121, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDigtalKeySupport property: 0x");
            sb.append(tenToHex(557856121));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDischargeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856196, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDischargeSupport property: 0x");
            sb.append(tenToHex(557856196));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDisinfSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856172, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDisinfSupport property: 0x");
            sb.append(tenToHex(557856172));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDoorHeightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856076, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorHeightSupport property: 0x");
            sb.append(tenToHex(557856076));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDoorLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856043, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLightSupport property: 0x");
            sb.append(tenToHex(557856043));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDoorknobAutoShrinkSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856119, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorknobAutoShrinkSupport property: 0x");
            sb.append(tenToHex(557856119));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriveConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856003, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveConfig property: 0x");
            sb.append(tenToHex(557856003));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriveDistanceShowModeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856199, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveDistanceShowModeSupport property: 0x");
            sb.append(tenToHex(557856199));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriveDistanceShowStandardSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856197, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveDistanceShowStandardSupport property: 0x");
            sb.append(tenToHex(557856197));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriveSeatAutoReturnSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856054, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveSeatAutoReturnSupport property: 0x");
            sb.append(tenToHex(557856054));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDynamicSuspensionModeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856061, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDynamicSuspensionModeSupport property: 0x");
            sb.append(tenToHex(557856061));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEPedalStateSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856198, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEPedalStateSupport property: 0x");
            sb.append(tenToHex(557856198));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElectroniChildLockConfigFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856206, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectroniChildLockConfigFirst property: 0x");
            sb.append(tenToHex(557856206));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElectroniChildLockConfigSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856207, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectroniChildLockConfigSecond property: 0x");
            sb.append(tenToHex(557856207));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyStatus() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856002, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyStatus property: 0x");
            sb.append(tenToHex(557856002));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEngineSoundSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856127, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEngineSoundSupport property: 0x");
            sb.append(tenToHex(557856127));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getGuestLeftSeatRememeSupportFirstL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856202, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGuestLeftSeatRememeSupportFirstL property: 0x");
            sb.append(tenToHex(557856202));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getGuestLeftSeatRememeSupportSecondL() {
        return 0;
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getGuestRightSeatRememeSupportFirstR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856204, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGuestRightSeatRememeSupportFirstR property: 0x");
            sb.append(tenToHex(557856204));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getGuestRightSeatRememeSupportSecondR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856205, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getGuestRightSeatRememeSupportSecondR property: 0x");
            sb.append(tenToHex(557856205));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadlampLevelConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856035, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadlampLevelConfig property: 0x");
            sb.append(tenToHex(557856035));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadlampSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856034, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadlampSupport property: 0x");
            sb.append(tenToHex(557856034));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHeightSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856168, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightSupportBackL property: 0x");
            sb.append(tenToHex(557856168));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHeightSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856169, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightSupportBackR property: 0x");
            sb.append(tenToHex(557856169));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHeightSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856167, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightSupportCopilot property: 0x");
            sb.append(tenToHex(557856167));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHeightSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856165, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightSupportSecond property: 0x");
            sb.append(tenToHex(557856165));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHeightSupportThird() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856166, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHeightSupportThird property: 0x");
            sb.append(tenToHex(557856166));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHorizatialSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856159, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHorizatialSupportBackL property: 0x");
            sb.append(tenToHex(557856159));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHorizatialSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856160, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHorizatialSupportBackR property: 0x");
            sb.append(tenToHex(557856160));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHorizatialSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856158, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHorizatialSupportCopilot property: 0x");
            sb.append(tenToHex(557856158));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHorizatialSupportFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856156, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHorizatialSupportFirst property: 0x");
            sb.append(tenToHex(557856156));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHeadrestTurnHorizatialSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856157, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHeadrestTurnHorizatialSupportSecond property: 0x");
            sb.append(tenToHex(557856157));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHudHeightAdjustSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856191, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudHeightAdjustSupport property: 0x");
            sb.append(tenToHex(557856191));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHudLightSdjustSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856192, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudLightSdjustSupport property: 0x");
            sb.append(tenToHex(557856192));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHudParamsStateSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856190, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudParamsStateSupport property: 0x");
            sb.append(tenToHex(557856190));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHudShowParamsSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856072, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHudShowParamsSupport property: 0x");
            sb.append(tenToHex(557856072));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getInstrumentContentSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856195, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentContentSupport property: 0x");
            sb.append(tenToHex(557856195));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public boolean getInstrumentHeightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Boolean> value = this.mCarPropertyManager.getProperty(Boolean.class, CarVendorUistyleManager.ID_INSTRUMENT_HEIGHT_SUPPORT, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentHeightSupport property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_INSTRUMENT_HEIGHT_SUPPORT));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getInstrumentThemeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856193, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstrumentThemeSupport property: 0x");
            sb.append(tenToHex(557856193));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentAcAreaSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856075, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentAcAreaSupport property: 0x");
            sb.append(tenToHex(557856075));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentKeepTemSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856045, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentKeepTemSupport property: 0x");
            sb.append(tenToHex(557856045));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentLockSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856123, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentLockSupport property: 0x");
            sb.append(tenToHex(557856123));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentUnlockSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856122, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentUnlockSupport property: 0x");
            sb.append(tenToHex(557856122));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLandAdvanceAtSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856062, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLandAdvanceAtSupport property: 0x");
            sb.append(tenToHex(557856062));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeaveHomeDelaySupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856038, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeaveHomeDelaySupport property: 0x");
            sb.append(tenToHex(557856038));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftPlatformConfigs() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856023, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftPlatformConfigs property: 0x");
            sb.append(tenToHex(557856023));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLockCarRiseWindowSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856116, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLockCarRiseWindowSupport property: 0x");
            sb.append(tenToHex(557856116));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLockOffSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856120, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLockOffSupport property: 0x");
            sb.append(tenToHex(557856120));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainSeatRememberSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856200, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainSeatRememberSupportSecond property: 0x");
            sb.append(tenToHex(557856200));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMicroSwitchLockWindowSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856114, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMicroSwitchLockWindowSupport property: 0x");
            sb.append(tenToHex(557856114));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMicroSwitchUnlockWindowSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856115, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMicroSwitchUnlockWindowSupport property: 0x");
            sb.append(tenToHex(557856115));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMotorPretensionerSeatbeltSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856058, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMotorPretensionerSeatbeltSupport property: 0x");
            sb.append(tenToHex(557856058));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getOBCType() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856048, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOBCType property: 0x");
            sb.append(tenToHex(557856048));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getOverspeedLockSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856117, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getOverspeedLockSupport property: 0x");
            sb.append(tenToHex(557856117));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPM25PowerSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856068, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25PowerSupport property: 0x");
            sb.append(tenToHex(557856068));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPM25SwitchCheckSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856069, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25SwitchCheckSupport property: 0x");
            sb.append(tenToHex(557856069));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPM25TimeCkeckSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856070, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPM25TimeCkeckSupport property: 0x");
            sb.append(tenToHex(557856070));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPreSaleModelSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856000, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPreSaleModelSupport property: 0x");
            sb.append(tenToHex(557856000));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRainAutoLockWindowSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856118, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRainAutoLockWindowSupport property: 0x");
            sb.append(tenToHex(557856118));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearAcMaxWindLevel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856007, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearAcMaxWindLevel property: 0x");
            sb.append(tenToHex(557856007));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearPanelLockTimeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856073, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearPanelLockTimeSupport property: 0x");
            sb.append(tenToHex(557856073));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearViewMirrorFlipSupportFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856049, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewMirrorFlipSupportFirst property: 0x");
            sb.append(tenToHex(557856049));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearViewMirrorFlipSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856050, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearViewMirrorFlipSupportSecond property: 0x");
            sb.append(tenToHex(557856050));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearviewMirrorSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856147, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearviewMirrorSupport property: 0x");
            sb.append(tenToHex(557856147));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRefrigeratorModeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856170, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRefrigeratorModeSupport property: 0x");
            sb.append(tenToHex(557856170));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRemindSpeedSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856125, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemindSpeedSupport property: 0x");
            sb.append(tenToHex(557856125));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRemoteControlUnlockWindowSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856113, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteControlUnlockWindowSupport property: 0x");
            sb.append(tenToHex(557856113));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRemoteKeyStartAcTimeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856063, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRemoteKeyStartAcTimeSupport property: 0x");
            sb.append(tenToHex(557856063));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportContentSetSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856129, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportContentSetSupport property: 0x");
            sb.append(tenToHex(557856129));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportBelt() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856133, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportBelt property: 0x");
            sb.append(tenToHex(557856133));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportBrake() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856131, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportBrake property: 0x");
            sb.append(tenToHex(557856131));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportCar() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856134, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportCar property: 0x");
            sb.append(tenToHex(557856134));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportClose() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856132, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportClose property: 0x");
            sb.append(tenToHex(557856132));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportKey() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856136, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportKey property: 0x");
            sb.append(tenToHex(557856136));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportLow() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856135, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportLow property: 0x");
            sb.append(tenToHex(557856135));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportMode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856139, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportMode property: 0x");
            sb.append(tenToHex(557856139));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportOil() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856138, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportOil property: 0x");
            sb.append(tenToHex(557856138));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportOn() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856137, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportOn property: 0x");
            sb.append(tenToHex(557856137));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportPark() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856140, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportPark property: 0x");
            sb.append(tenToHex(557856140));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportItemsSetSupportPower() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856130, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportItemsSetSupportPower property: 0x");
            sb.append(tenToHex(557856130));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getReportVolumeSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856126, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getReportVolumeSupport property: 0x");
            sb.append(tenToHex(557856126));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setResetAmbientSettings(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setResetAmbientSettings property: 0x" + tenToHex(557856042) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856042, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setResetBackAndLeaveDelaySupport(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setResetBackAndLeaveDelaySupport property: 0x" + tenToHex(557856039) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856039, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setResetDayLight(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setResetDayLight property: 0x" + tenToHex(557856042) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856042, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatSetSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856144, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatSetSupportBackL property: 0x");
            sb.append(tenToHex(557856144));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatSetSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856145, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatSetSupportBackR property: 0x");
            sb.append(tenToHex(557856145));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatSetSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856143, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatSetSupportCopilot property: 0x");
            sb.append(tenToHex(557856143));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatSetSupportFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856141, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatSetSupportFirst property: 0x");
            sb.append(tenToHex(557856141));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatSetSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856142, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatSetSupportSecond property: 0x");
            sb.append(tenToHex(557856142));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHeightSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856154, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightSupportBackL property: 0x");
            sb.append(tenToHex(557856154));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHeightSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856155, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightSupportBackR property: 0x");
            sb.append(tenToHex(557856155));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHeightSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856153, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightSupportCopilot property: 0x");
            sb.append(tenToHex(557856153));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHeightSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856152, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHeightSupportSecond property: 0x");
            sb.append(tenToHex(557856152));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHorizatialSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856150, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHorizatialSupportBackL property: 0x");
            sb.append(tenToHex(557856150));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHorizatialSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856151, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHorizatialSupportBackR property: 0x");
            sb.append(tenToHex(557856151));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHorizatialSupportCopilot() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856149, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHorizatialSupportCopilot property: 0x");
            sb.append(tenToHex(557856149));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatTurnHorizatialSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856148, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatTurnHorizatialSupportSecond property: 0x");
            sb.append(tenToHex(557856148));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatbackrestSupportBackL() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856163, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestSupportBackL property: 0x");
            sb.append(tenToHex(557856163));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatbackrestSupportBackR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856164, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestSupportBackR property: 0x");
            sb.append(tenToHex(557856164));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatbackrestSupportSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856161, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestSupportSecond property: 0x");
            sb.append(tenToHex(557856161));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSeatbackrestSupportThird() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856162, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSeatbackrestSupportThird property: 0x");
            sb.append(tenToHex(557856162));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSecondSeatRememberSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856201, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSecondSeatRememberSupport property: 0x");
            sb.append(tenToHex(557856201));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSteerAssisSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856059, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteerAssisSupport property: 0x");
            sb.append(tenToHex(557856059));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSteerPositionAutoReturnSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856055, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteerPositionAutoReturnSupport property: 0x");
            sb.append(tenToHex(557856055));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSteeringWheelSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856146, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelSupport property: 0x");
            sb.append(tenToHex(557856146));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAFS() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856033, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAFS property: 0x");
            sb.append(tenToHex(557856033));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAcAutoKeyCanClose() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856016, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAcAutoKeyCanClose property: 0x");
            sb.append(tenToHex(557856016));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAiCare() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_AI_CARE, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAiCare property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_AI_CARE));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAiDistanceLight() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856106, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAiDistanceLight property: 0x");
            sb.append(tenToHex(557856106));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAiEmergencyBrake() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856099, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAiEmergencyBrake property: 0x");
            sb.append(tenToHex(557856099));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAiMatrixLightFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856107, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAiMatrixLightFirst property: 0x");
            sb.append(tenToHex(557856107));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAiMatrixLightSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856108, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAiMatrixLightSecond property: 0x");
            sb.append(tenToHex(557856108));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAutoParking() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856102, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAutoParking property: 0x");
            sb.append(tenToHex(557856102));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportBlindSpotAssistFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856103, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportBlindSpotAssistFirst property: 0x");
            sb.append(tenToHex(557856103));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportBlindSpotAssistSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856100, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportBlindSpotAssistSecond property: 0x");
            sb.append(tenToHex(557856100));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportDoorOpenWarning() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856091, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportDoorOpenWarning property: 0x");
            sb.append(tenToHex(557856091));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportESP() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856110, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportESP property: 0x");
            sb.append(tenToHex(557856110));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportEmergencyLaneKeepAssist() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856086, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportEmergencyLaneKeepAssist property: 0x");
            sb.append(tenToHex(557856086));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportEndToEndNavigation() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_END_TO_END_NAVIGATION, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportEndToEndNavigation property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_END_TO_END_NAVIGATION));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFragrance() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856027, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragrance property: 0x");
            sb.append(tenToHex(557856027));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFragranceTypeOne() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856028, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeOne property: 0x");
            sb.append(tenToHex(557856028));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFragranceTypeThree() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856030, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeThree property: 0x");
            sb.append(tenToHex(557856030));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFragranceTypeTwo() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856029, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFragranceTypeTwo property: 0x");
            sb.append(tenToHex(557856029));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFrontCrossingTrafficAlert() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856094, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFrontCrossingTrafficAlert property: 0x");
            sb.append(tenToHex(557856094));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFrontCrossingTrafficBrake() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856095, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFrontCrossingTrafficBrake property: 0x");
            sb.append(tenToHex(557856095));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportHighTemperatureSterilize() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856026, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportHighTemperatureSterilize property: 0x");
            sb.append(tenToHex(557856026));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportHighwayNavigation() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856079, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportHighwayNavigation property: 0x");
            sb.append(tenToHex(557856079));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportIntelligentNavigationFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856077, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportIntelligentNavigationFirst property: 0x");
            sb.append(tenToHex(557856077));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportIntelligentNavigationSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856078, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportIntelligentNavigationSecond property: 0x");
            sb.append(tenToHex(557856078));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAvhR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_A_V_H_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportAvhR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_A_V_H_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportDimmingCanopyConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859165, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportDimmingCanopyConfigurationR value = " + value + " property: 0x" + tenToHex(557859165));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearControlPanelSignR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_REAR_CONTROL_PANEL_SIGN_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportRearControlPanelSignR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_REAR_CONTROL_PANEL_SIGN_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportIntellignetSpeedLimitAlert() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856088, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportIntellignetSpeedLimitAlert property: 0x");
            sb.append(tenToHex(557856088));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportIntellignetSpeedLimitControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856089, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportIntellignetSpeedLimitControl property: 0x");
            sb.append(tenToHex(557856089));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportLampSingnal() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856032, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportLampSingnal property: 0x");
            sb.append(tenToHex(557856032));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportLaneChangeAssist() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856082, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportLaneChangeAssist property: 0x");
            sb.append(tenToHex(557856082));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportLaneDepartureStop() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856085, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportLaneDepartureStop property: 0x");
            sb.append(tenToHex(557856085));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportLaneDepartureWarning() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856083, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportLaneDepartureWarning property: 0x");
            sb.append(tenToHex(557856083));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportLaneKeep() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856084, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportLaneKeep property: 0x");
            sb.append(tenToHex(557856084));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportMistakeGas() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856101, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportMistakeGas property: 0x");
            sb.append(tenToHex(557856101));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportNegativeIon() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856025, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportNegativeIon property: 0x");
            sb.append(tenToHex(557856025));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportPM25Mode() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856024, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportPM25Mode property: 0x");
            sb.append(tenToHex(557856024));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportPanoramaImage() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856109, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportPanoramaImage property: 0x");
            sb.append(tenToHex(557856109));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportPredictCrashAlertModeFirst() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856097, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportPredictCrashAlertModeFirst property: 0x");
            sb.append(tenToHex(557856097));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportPredictCrashAlertModeSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856098, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportPredictCrashAlertModeSecond property: 0x");
            sb.append(tenToHex(557856098));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAc() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846530, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAc property: 0x");
            sb.append(tenToHex(557846530));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcAutoControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856010, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcAutoControl property: 0x");
            sb.append(tenToHex(557856010));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcCompressor() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846536, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcCompressor property: 0x");
            sb.append(tenToHex(557846536));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcKeyLock() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846533, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcKeyLock property: 0x");
            sb.append(tenToHex(557846533));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcSwitch() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846531, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcSwitch property: 0x");
            sb.append(tenToHex(557846531));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcTempertureControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846537, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcTempertureControl property: 0x");
            sb.append(tenToHex(557846537));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcWindLevelControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846538, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcWindLevelControl property: 0x");
            sb.append(tenToHex(557846538));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearAcWindModeControl() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557846534, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearAcWindModeControl property: 0x");
            sb.append(tenToHex(557846534));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearCollisionWarning() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856092, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearCollisionWarning property: 0x");
            sb.append(tenToHex(557856092));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearCrossBrake() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856105, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearCrossBrake property: 0x");
            sb.append(tenToHex(557856105));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRearTrafficCrossingAlert() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856093, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRearTrafficCrossingAlert property: 0x");
            sb.append(tenToHex(557856093));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRemenberPowerOff() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856171, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportRemenberPowerOff property: 0x");
            sb.append(tenToHex(557856171));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportReverseRadar() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856044, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportReverseRadar property: 0x");
            sb.append(tenToHex(557856044));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageDeputy() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856020, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageDeputy property: 0x");
            sb.append(tenToHex(557856020));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageLeftRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856021, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageLeftRear property: 0x");
            sb.append(tenToHex(557856021));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageMain() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856019, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageMain property: 0x");
            sb.append(tenToHex(557856019));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageRightRear() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856022, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageRightRear property: 0x");
            sb.append(tenToHex(557856022));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportShakeAlertSecond() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856096, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportShakeAlertSecond property: 0x");
            sb.append(tenToHex(557856096));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportShowCarModel() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856111, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportShowCarModel property: 0x");
            sb.append(tenToHex(557856111));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSingleDoubleTempArea() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856014, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSingleDoubleTempArea property: 0x");
            sb.append(tenToHex(557856014));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSteeringWheelWarm() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856018, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSteeringWheelWarm property: 0x");
            sb.append(tenToHex(557856018));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportTrafficLightAttention() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856090, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportTrafficLightAttention property: 0x");
            sb.append(tenToHex(557856090));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportTrafficSignRecogniton() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856087, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportTrafficSignRecogniton property: 0x");
            sb.append(tenToHex(557856087));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportUrbanNavigation() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_URBAN_NAVIGATION, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportUrbanNavigation property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_URBAN_NAVIGATION));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTrayControlSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856173, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrayControlSupport property: 0x");
            sb.append(tenToHex(557856173));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getUsherLightSupport() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856040, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getUsherLightSupport property: 0x");
            sb.append(tenToHex(557856040));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getADASSystemStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ADAS_SYSTEM_STATER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getADASSystemStateR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ADAS_SYSTEM_STATER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAverageSpeedInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_AVERAGE_SPEED_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAverageSpeedInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_AVERAGE_SPEED_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCumulativeAverageEnergyConsumptionInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCumulativeAverageEnergyConsumptionInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCumulativeAverageFuelConsumptionInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCumulativeAverageFuelConsumptionInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCumulativeAveragePowerConsumptionInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCumulativeAveragePowerConsumptionInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriveTimeInterfaceStatusForSingleTripR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DRIVE_TIME_INTERFACE_STATUS_FOR_SINGLE_TRIPR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriveTimeInterfaceStatusForSingleTripR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DRIVE_TIME_INTERFACE_STATUS_FOR_SINGLE_TRIPR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDrivingRangeInterfaceStatusForFuelR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DRIVING_RANGE_INTERFACE_STATUS_FOR_FUELR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingRangeInterfaceStatusForFuelR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DRIVING_RANGE_INTERFACE_STATUS_FOR_FUELR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getEnergyFlowDiagramInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ENERGY_FLOW_DIAGRAM_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getEnergyFlowDiagramInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ENERGY_FLOW_DIAGRAM_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getExternalChargingPowerInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_EXTERNAL_CHARGING_POWER_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getExternalChargingPowerInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_EXTERNAL_CHARGING_POWER_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFuelConsumptionInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_FUEL_CONSUMPTION_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFuelConsumptionInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_FUEL_CONSUMPTION_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getInstantaneousFuelConsumptionInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_INSTANTANEOUS_FUEL_CONSUMPTION_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getInstantaneousFuelConsumptionInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_INSTANTANEOUS_FUEL_CONSUMPTION_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatReclineConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATIONR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatReclineConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATIONR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMemoryParkingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MEMORY_PARKINGR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMemoryParkingR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MEMORY_PARKINGR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatMemoryFunctionCanR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CANR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatMemoryFunctionCanR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CANR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearSeatReclineConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_SEAT_RECLINE_CONFIGURATIONR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearSeatReclineConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_SEAT_RECLINE_CONFIGURATIONR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatReclineConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATIONR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatReclineConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATIONR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getAmplifierConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557879608, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getAmplifierConfigurationR property: 0x");
            sb.append(tenToHex(557879608));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDoorLightSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DOOR_LIGHT_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDoorLightSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DOOR_LIGHT_SUPPORT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFrontLightDimmingCtrlConfigBy3acR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859113, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontLightDimmingCtrlConfigBy3acR property: 0x");
            sb.append(tenToHex(557859113));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getFrontLightDimmingCtrlConfigBy3adR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859114, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getFrontLightDimmingCtrlConfigBy3adR property: 0x");
            sb.append(tenToHex(557859114));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHasPredictCrashAlertModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859108, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHasPredictCrashAlertModeR property: 0x");
            sb.append(tenToHex(557859108));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPabSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859112, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPabSwitchStateR property: 0x");
            sb.append(tenToHex(557859112));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAfsR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859108, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportAfsR property: 0x");
            sb.append(tenToHex(557859108));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTsrLitSpdChgSwitchStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859115, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTsrLitSpdChgSwitchStateR property: 0x");
            sb.append(tenToHex(557859115));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSteeringWheelHeatingConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_STEERING_WHEEL_HEATING_CONFIGURATIONR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSteeringWheelHeatingConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_STEERING_WHEEL_HEATING_CONFIGURATIONR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportExtremeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_EXTREMER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportExtremeR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_EXTREMER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSmartChargingPHEVR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_SMART_CHARGING_PHEVR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSmartChargingPHEVR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_SMART_CHARGING_PHEVR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTheLast50KMAverageEnergyConsumptionInterfaceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTheLast50KMAverageEnergyConsumptionInterfaceStateR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTheLast50KMAverageFuelConsumptionInterfaceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTheLast50KMAverageFuelConsumptionInterfaceStateR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTheLast50KMAveragePowerConsumptionInterfaceStateR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTheLast50KMAveragePowerConsumptionInterfaceStateR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTirePressureInterfaceStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_TIRE_PRESSURE_INTERFACE_STATUSR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTirePressureInterfaceStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_TIRE_PRESSURE_INTERFACE_STATUSR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getValetParkingR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_VALET_PARKINGR, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getValetParkingR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_VALET_PARKINGR));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setrestoreFactorySettings(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setrestoreFactorySettings property: 0x" + tenToHex(557857573) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557857573, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void setSysFactoryReset(int value) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "setSysFactoryReset property: 0x" + tenToHex(557903888) + " class: int value: " + CastArrayUtil.parseString(value), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557903888, 0, CastArrayUtil.toWrap(value));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getWindowAntiPinchConfig() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856209, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getWindowAntiPinchConfigFirst property: 0x");
            sb.append(tenToHex(557856209));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getresetHeadlamp() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856036, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getresetHeadlamp property: 0x");
            sb.append(tenToHex(557856036));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getCarPlanSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CAR_PLAN_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getCarPlanSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CAR_PLAN_SUPPORT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getChargePreheatSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_CHARGE_PREHEAT_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getChargePreheatSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_CHARGE_PREHEAT_SUPPORT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDMSAssembleStatusR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DMS_ASSEMBLE_STATUS_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDMSAssembleStatusR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DMS_ASSEMBLE_STATUS_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_LEFT_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverSeatHotStoneMassageConfigurationByLeftCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_LEFT_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557859101, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDriverSeatHotStoneMassageConfigurationByUniversalCtrlR property: 0x");
            sb.append(tenToHex(557859101));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDrivingVoiceBroadcastSwitchConfigOneR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557870336, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingVoiceBroadcastSwitchConfigOneR property: 0x");
            sb.append(tenToHex(557870336));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDrivingVoiceBroadcastSwitchConfigThreeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_THREER, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingVoiceBroadcastSwitchConfigThreeR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_THREER));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getDrivingVoiceBroadcastSwitchConfigTwoR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856077, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getDrivingVoiceBroadcastSwitchConfigTwoR property: 0x");
            sb.append(tenToHex(557856077));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElectricFrontHatchConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_ELECTRIC_FRONT_HATCH_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectricFrontHatchConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_ELECTRIC_FRONT_HATCH_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getElectronicParkingBrakeConfigutarionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557850645, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getElectronicParkingBrakeConfigutarionR property: 0x");
            sb.append(tenToHex(557850645));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getHUDModeConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_HUD_MODE_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getHUDModeConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_HUD_MODE_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getIntelligentScreenCastingSwitchConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_INTELLIGENT_SCREEN_CASTING_SWITCH_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getIntelligentScreenCastingSwitchConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_INTELLIGENT_SCREEN_CASTING_SWITCH_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRESConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_RES_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRESConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_RES_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearMemoryConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856203, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearMemoryConfigurationR property: 0x");
            sb.append(tenToHex(557856203));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatBackAdjustmentConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatBackAdjustmentConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatHorizontalAdjustmentConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatHorizontalAdjustmentConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatHotStoneMassageConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatLegBracketR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_LEG_BRACKET_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatLegBracketR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_LEG_BRACKET_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getLeftRearSeatReclineConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getLeftRearSeatReclineConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriveElectricHeadrestAdjustmentByGCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_G_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriveElectricHeadrestAdjustmentByGCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_G_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_LEFT_BODY_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_LEFT_BODY_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriveElectricLegSupportAdjustmentByGCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_G_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriveElectricLegSupportAdjustmentByGCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_G_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_LEFT_BODY_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_LEFT_BODY_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriverSeatAdjustmentConfigurationByGCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_G_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriverSeatAdjustmentConfigurationByGCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_G_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856225, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrlR property: 0x");
            sb.append(tenToHex(557856225));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getNoSenseChargeSupportR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_NO_SENSE_CHARGE_SUPPORT_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getNoSenseChargeSupportR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_NO_SENSE_CHARGE_SUPPORT_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPanoramicSunroofConfigutarionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557862942, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPanoramicSunroofConfigutarionR property: 0x");
            sb.append(tenToHex(557862942));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatElectricAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_ELECTRIC_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatElectricAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_ELECTRIC_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatHeadrestConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatHeadrestConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatHeadrestConfigurationByGCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_G_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatHeadrestConfigurationByGCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_G_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatHotStoneMassageConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatLegSupportAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrlR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_G_CTRL_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatLegSupportAdjustmentConfigurationByGCtrlR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_G_CTRL_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getPassengerSeatMemoryFunctionCanByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CAN_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getPassengerSeatMemoryFunctionCanByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CAN_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftBackrestAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftBackrestAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftBidetAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftBidetAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftHeadrestHeightAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftHeadrestLevelAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftHeightAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftLegSupportAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftLevelAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearLeftMemorySwitchConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_LEFT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearLeftMemorySwitchConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_LEFT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightBackrestAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightBackrestAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightBidetAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightBidetAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightHeadrestHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightHeadrestHeightAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightHeadrestLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightHeadrestLevelAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightHeightAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightHeightAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightLegSupportAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightLegSupportAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightLevelAdjustmentConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REAR_RIGHT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightLevelAdjustmentConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REAR_RIGHT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRearRightMemorySwitchConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856204, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRearRightMemorySwitchConfigurationByECUR property: 0x");
            sb.append(tenToHex(557856204));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRestModeConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_REST_MODE_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRestModeConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_REST_MODE_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRESConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_RES_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRESConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_RES_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearMemoryConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_MEMORY_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearMemoryConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_MEMORY_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatBackAdjustmentConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatBackAdjustmentConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatHorizontalAdjustmentConfigurationR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatHorizontalAdjustmentConfigurationR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatHotStoneMassageConfigurationByECUR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatHotStoneMassageConfigurationByECUR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatLegBracketR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_LEG_BRACKET_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatLegBracketR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_LEG_BRACKET_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getRightRearSeatReclineConfigurationByEUCR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATION_BY_EUC_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getRightRearSeatReclineConfigurationByEUCR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATION_BY_EUC_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportESPR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_ESP_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportESPR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_ESP_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportFRElectricAirOutletR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_FR_ELECTRIC_AIR_OUTLET_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportFRElectricAirOutletR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_FR_ELECTRIC_AIR_OUTLET_R));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getTrunkConfigutarionR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, 557856076, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getTrunkConfigutarionR property: 0x");
            sb.append(tenToHex(557856076));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void addCarUistyleEventListener(CarVendorUistyleManager.OnCarUistyleEventListener listener) throws CarNotConnectedException {
        OnCarUistyleChangeEventListenerProxy proxy = findListenerLocked(listener);
        synchronized (this) {
            if (proxy == null) {
                proxy = new OnCarUistyleChangeEventListenerProxy(listener);
                this.mListeners.add(proxy);
            }
        }
        Log.info(CarVendorUistyleManager.TAG, "addCarUistyleEventListener propId size: " + this.mExtUistyleIds.length + " listener size: " + this.mListeners.size());
        try {
            for (Integer num : this.mExtUistyleIds) {
                int propId = num.intValue();
                Log.debug(CarVendorUistyleManager.TAG, "Registering CarProperty Listener" + propId, DEBUG_LOGGER);
                this.mCarPropertyManager.registerCallback(proxy, propId, 1.0f);
            }
        } catch (Exception ex) {
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void removeCarUistyleEventListener(CarVendorUistyleManager.OnCarUistyleEventListener listener) throws CarNotConnectedException {
        synchronized (this) {
            OnCarUistyleChangeEventListenerProxy proxy = findListenerLocked(listener);
            if (proxy == null) {
                return;
            }
            this.mListeners.remove(proxy);
            Log.info(CarVendorUistyleManager.TAG, "removeCarUistyleEventListener listener size: " + this.mListeners.size());
            try {
                this.mCarPropertyManager.unregisterCallback(proxy);
            } catch (Exception ex) {
                throw new CarNotConnectedException(ex);
            }
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageLeftBackECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_LEFT_BACK_ECU, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageLeftBackECU property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_LEFT_BACK_ECU));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSeatMassageRightBackECU() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_RIGHT_BACK_ECU, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSeatMassageRightBackECU property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_RIGHT_BACK_ECU));
            sb.append(" class: int value: ");
            sb.append(value != null ? CastArrayUtil.parseString(value.getValue()) : null);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public void sendDLinkCountryCodeS(int countryCode) throws CarNotConnectedException {
        Log.debug(CarVendorUistyleManager.TAG, "sendDLinkCountryCodeS property: 0x" + tenToHex(557856104) + " class: int value: " + CastArrayUtil.parseString(countryCode), DEBUG_LOGGER);
        try {
            this.mCarPropertyManager.setProperty(Integer.class, 557856104, 0, CastArrayUtil.toWrap(countryCode));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportSlwR() throws CarNotConnectedException {
        String string;
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_S_L_W_R, 0);
            StringBuilder sb = new StringBuilder();
            sb.append("getSupportSlwR property: 0x");
            sb.append(tenToHex(CarVendorUistyleManager.ID_SUPPORT_S_L_W_R));
            sb.append(" class: int value: ");
            if (value != null) {
                string = CastArrayUtil.parseString(value.getValue());
            } else {
                string = null;
            }
            sb.append(string);
            Log.debug(CarVendorUistyleManager.TAG, sb.toString(), DEBUG_LOGGER);
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (android.car.CarNotConnectedException ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportAcChargingLimitR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_A_C_CHARGING_LIMIT_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportAcChargingLimitR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_A_C_CHARGING_LIMIT_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    @Override // com.ts.lib.car.hardware.uistyle.CarVendorUistyleManager
    public int getSupportRoadModeR() throws CarNotConnectedException {
        try {
            CarPropertyValue<Integer> value = this.mCarPropertyManager.getProperty(Integer.class, CarVendorUistyleManager.ID_SUPPORT_ROAD_MODE_R, 0);
            Log.debug(CarVendorUistyleManager.TAG, "getSupportRoadModeR value = " + value + " property: 0x" + tenToHex(CarVendorUistyleManager.ID_SUPPORT_ROAD_MODE_R));
            if (value != null) {
                return CastArrayUtil.toPrimitive(value.getValue());
            }
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CarNotConnectedException(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static UistyleEventChangeValue convertCarVendorProperty(CarPropertyValue value) {
        int propertyId = value.getPropertyId();
        value.getAreaId();
        sUistyleEventChangeValue.setCarPropertyValue(value);
        Log.debug(CarVendorUistyleManager.TAG, "CarPropertyValue " + value.toString(), DEBUG_LOGGER);
        switch (propertyId) {
            case 557846529:
                sUistyleEventChangeValue.setAcType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846530:
                sUistyleEventChangeValue.setSupportRearAc(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            case 557846531:
                sUistyleEventChangeValue.setSupportRearAcSwitch(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                break;
            default:
                switch (propertyId) {
                    case 557846533:
                        sUistyleEventChangeValue.setSupportRearAcKeyLock(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    case 557846534:
                        sUistyleEventChangeValue.setSupportRearAcWindModeControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                        break;
                    default:
                        switch (propertyId) {
                            case 557846536:
                                sUistyleEventChangeValue.setSupportRearAcCompressor(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846537:
                                sUistyleEventChangeValue.setSupportRearAcTempertureControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            case 557846538:
                                sUistyleEventChangeValue.setSupportRearAcWindLevelControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                break;
                            default:
                                switch (propertyId) {
                                    case 557846542:
                                        sUistyleEventChangeValue.setAcFaultNumShownState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557846594:
                                        sUistyleEventChangeValue.setDefrostRearConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557850645:
                                        sUistyleEventChangeValue.setElectronicParkingBrakeConfigutarionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856007:
                                        sUistyleEventChangeValue.setRearAcMaxWindLevel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856010:
                                        sUistyleEventChangeValue.setSupportRearAcAutoControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856082:
                                        sUistyleEventChangeValue.setSupportLaneChangeAssist(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856083:
                                        sUistyleEventChangeValue.setSupportLaneDepartureWarning(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856084:
                                        sUistyleEventChangeValue.setSupportLaneKeep(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856085:
                                        sUistyleEventChangeValue.setSupportLaneDepartureStop(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856086:
                                        sUistyleEventChangeValue.setSupportEmergencyLaneKeepAssist(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856087:
                                        sUistyleEventChangeValue.setSupportTrafficSignRecogniton(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856088:
                                        sUistyleEventChangeValue.setSupportIntellignetSpeedLimitAlert(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856089:
                                        sUistyleEventChangeValue.setSupportIntellignetSpeedLimitControl(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856090:
                                        sUistyleEventChangeValue.setSupportTrafficLightAttention(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856091:
                                        sUistyleEventChangeValue.setSupportDoorOpenWarning(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856092:
                                        sUistyleEventChangeValue.setSupportRearCollisionWarning(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856093:
                                        sUistyleEventChangeValue.setSupportRearTrafficCrossingAlert(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856094:
                                        sUistyleEventChangeValue.setSupportFrontCrossingTrafficAlert(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856095:
                                        sUistyleEventChangeValue.setSupportFrontCrossingTrafficBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856096:
                                        sUistyleEventChangeValue.setSupportShakeAlertSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856097:
                                        sUistyleEventChangeValue.setSupportPredictCrashAlertModeFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856098:
                                        sUistyleEventChangeValue.setSupportPredictCrashAlertModeSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856099:
                                        sUistyleEventChangeValue.setSupportAiEmergencyBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856100:
                                        sUistyleEventChangeValue.setSupportBlindSpotAssistSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856101:
                                        sUistyleEventChangeValue.setSupportMistakeGas(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856102:
                                        sUistyleEventChangeValue.setSupportAutoParking(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856103:
                                        sUistyleEventChangeValue.setSupportBlindSpotAssistFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856104:
                                        sUistyleEventChangeValue.setsendDLinkCountryCodeS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856105:
                                        sUistyleEventChangeValue.setSupportRearCrossBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856106:
                                        sUistyleEventChangeValue.setSupportAiDistanceLight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856107:
                                        sUistyleEventChangeValue.setSupportAiMatrixLightFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856108:
                                        sUistyleEventChangeValue.setSupportAiMatrixLightSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856109:
                                        sUistyleEventChangeValue.setSupportPanoramaImage(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856110:
                                        sUistyleEventChangeValue.setSupportESP(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856111:
                                        sUistyleEventChangeValue.setSupportShowCarModel(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_S_L_W_R /* 557856112 */:
                                        sUistyleEventChangeValue.setSupportSlwS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856113:
                                        sUistyleEventChangeValue.setRemoteControlUnlockWindowSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856114:
                                        sUistyleEventChangeValue.setMicroSwitchLockWindowSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856115:
                                        sUistyleEventChangeValue.setMicroSwitchUnlockWindowSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856116:
                                        sUistyleEventChangeValue.setLockCarRiseWindowSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856117:
                                        sUistyleEventChangeValue.setOverspeedLockSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856118:
                                        sUistyleEventChangeValue.setRainAutoLockWindowSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856119:
                                        sUistyleEventChangeValue.setDoorknobAutoShrinkSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856120:
                                        sUistyleEventChangeValue.setLockOffSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856121:
                                        sUistyleEventChangeValue.setDigtalKeySupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856122:
                                        sUistyleEventChangeValue.setIntelligentUnlockSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856123:
                                        sUistyleEventChangeValue.setIntelligentLockSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856124:
                                        sUistyleEventChangeValue.setAppUseLimitSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856125:
                                        sUistyleEventChangeValue.setRemindSpeedSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856126:
                                        sUistyleEventChangeValue.setReportVolumeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856127:
                                        sUistyleEventChangeValue.setEngineSoundSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856128:
                                        sUistyleEventChangeValue.setCarTipsSoundSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856129:
                                        sUistyleEventChangeValue.setReportContentSetSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856130:
                                        sUistyleEventChangeValue.setReportItemsSetSupportPower(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856131:
                                        sUistyleEventChangeValue.setReportItemsSetSupportBrake(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856132:
                                        sUistyleEventChangeValue.setReportItemsSetSupportClose(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856133:
                                        sUistyleEventChangeValue.setReportItemsSetSupportBelt(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856134:
                                        sUistyleEventChangeValue.setReportItemsSetSupportCar(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856135:
                                        sUistyleEventChangeValue.setReportItemsSetSupportLow(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856136:
                                        sUistyleEventChangeValue.setReportItemsSetSupportKey(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856137:
                                        sUistyleEventChangeValue.setReportItemsSetSupportOn(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856138:
                                        sUistyleEventChangeValue.setReportItemsSetSupportOil(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856139:
                                        sUistyleEventChangeValue.setReportItemsSetSupportMode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856140:
                                        sUistyleEventChangeValue.setReportItemsSetSupportPark(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856141:
                                        sUistyleEventChangeValue.setSeatSetSupportFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856142:
                                        sUistyleEventChangeValue.setSeatSetSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856143:
                                        sUistyleEventChangeValue.setSeatSetSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856144:
                                        sUistyleEventChangeValue.setSeatSetSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856145:
                                        sUistyleEventChangeValue.setSeatSetSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856146:
                                        sUistyleEventChangeValue.setSteeringWheelSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856147:
                                        sUistyleEventChangeValue.setRearviewMirrorSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856148:
                                        sUistyleEventChangeValue.setSeatTurnHorizatialSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856149:
                                        sUistyleEventChangeValue.setSeatTurnHorizatialSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856150:
                                        sUistyleEventChangeValue.setSeatTurnHorizatialSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856151:
                                        sUistyleEventChangeValue.setSeatTurnHorizatialSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856152:
                                        sUistyleEventChangeValue.setSeatTurnHeightSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856153:
                                        sUistyleEventChangeValue.setSeatTurnHeightSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856154:
                                        sUistyleEventChangeValue.setSeatTurnHeightSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856155:
                                        sUistyleEventChangeValue.setSeatTurnHeightSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856156:
                                        sUistyleEventChangeValue.setHeadrestTurnHorizatialSupportFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856157:
                                        sUistyleEventChangeValue.setHeadrestTurnHorizatialSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856158:
                                        sUistyleEventChangeValue.setHeadrestTurnHorizatialSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856159:
                                        sUistyleEventChangeValue.setHeadrestTurnHorizatialSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856160:
                                        sUistyleEventChangeValue.setHeadrestTurnHorizatialSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856161:
                                        sUistyleEventChangeValue.setSeatbackrestSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856162:
                                        sUistyleEventChangeValue.setSeatbackrestSupportThird(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856163:
                                        sUistyleEventChangeValue.setSeatbackrestSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856164:
                                        sUistyleEventChangeValue.setSeatbackrestSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856165:
                                        sUistyleEventChangeValue.setHeadrestTurnHeightSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856166:
                                        sUistyleEventChangeValue.setHeadrestTurnHeightSupportThird(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856167:
                                        sUistyleEventChangeValue.setHeadrestTurnHeightSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856168:
                                        sUistyleEventChangeValue.setHeadrestTurnHeightSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856169:
                                        sUistyleEventChangeValue.setHeadrestTurnHeightSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856170:
                                        sUistyleEventChangeValue.setRefrigeratorModeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856171:
                                        sUistyleEventChangeValue.setSupportRemenberPowerOff(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856172:
                                        sUistyleEventChangeValue.setDisinfSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856173:
                                        sUistyleEventChangeValue.setTrayControlSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856174:
                                        sUistyleEventChangeValue.setCushionSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856175:
                                        sUistyleEventChangeValue.setCushionSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856176:
                                        sUistyleEventChangeValue.setCushionSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856177:
                                        sUistyleEventChangeValue.setCushionSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856178:
                                        sUistyleEventChangeValue.setCaliperSplintSupportFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856179:
                                        sUistyleEventChangeValue.setCaliperSplintSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856180:
                                        sUistyleEventChangeValue.setCaliperSplintSupportThird(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856181:
                                        sUistyleEventChangeValue.setCaliperSplintSupportCopilot(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856182:
                                        sUistyleEventChangeValue.setCaliperSplintSupportBackL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856183:
                                        sUistyleEventChangeValue.setCaliperSplintSupportBackR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856184:
                                        sUistyleEventChangeValue.setAmbientLightColorSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856185:
                                        sUistyleEventChangeValue.setAmbientLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856186:
                                        sUistyleEventChangeValue.setAmbientAreaSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856187:
                                        sUistyleEventChangeValue.setAmbientMusicModeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856188:
                                        sUistyleEventChangeValue.setAmbientColorsSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_EXTREMER /* 557856223 */:
                                        sUistyleEventChangeValue.setSupportExtremeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_SMART_CHARGING_PHEVR /* 557856224 */:
                                        sUistyleEventChangeValue.setSupportSmartChargingPHEVR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557856225:
                                        sUistyleEventChangeValue.setMainDriverSeatAdjustmentConfigurationByLeftBodyCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_LEFT_BODY_CTRL_R /* 557856226 */:
                                        sUistyleEventChangeValue.setMainDriveElectricLegSupportAdjustmentByLeftBodyCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_LEFT_BODY_CTRL_R /* 557856227 */:
                                        sUistyleEventChangeValue.setMainDriveElectricHeadrestAdjustmentByLeftBodyCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_MAIN_DRIVER_SEAT_ADJUSTMENT_CONGIGURATION_BY_G_CTRL_R /* 557856228 */:
                                        sUistyleEventChangeValue.setMainDriverSeatAdjustmentConfigurationByGCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_LEG_SUPPORT_ADJUSTMENT_BY_G_CTRL_R /* 557856229 */:
                                        sUistyleEventChangeValue.setMainDriveElectricLegSupportAdjustmentByGCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_MAIN_DRIVE_ELECTRIC_HEADREST_ADJUSTMENT_BY_G_CTRL_R /* 557856230 */:
                                        sUistyleEventChangeValue.setMainDriveElectricHeadrestAdjustmentByGCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CAN_BY_ECU_R /* 557856231 */:
                                        sUistyleEventChangeValue.setPassengerSeatMemoryFunctionCanByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_ELECTRIC_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856232 */:
                                        sUistyleEventChangeValue.setPassengerSeatElectricAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856233 */:
                                        sUistyleEventChangeValue.setPassengerSeatLegSupportAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_ECU_R /* 557856234 */:
                                        sUistyleEventChangeValue.setPassengerSeatHeadrestConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_G_CTRL_R /* 557856235 */:
                                        sUistyleEventChangeValue.setPassengerSeatLegSupportAdjustmentConfigurationByGCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_HEADREST_CONFIGURATION_BY_G_CTRL_R /* 557856236 */:
                                        sUistyleEventChangeValue.setPassengerSeatHeadrestConfigurationByGCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856237 */:
                                        sUistyleEventChangeValue.setRearLeftLevelAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856238 */:
                                        sUistyleEventChangeValue.setRearLeftBackrestAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856239 */:
                                        sUistyleEventChangeValue.setRearLeftLegSupportAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856240 */:
                                        sUistyleEventChangeValue.setRearLeftBidetAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856241 */:
                                        sUistyleEventChangeValue.setRearLeftHeightAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856242 */:
                                        sUistyleEventChangeValue.setRearLeftHeadrestLevelAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856243 */:
                                        sUistyleEventChangeValue.setRearLeftHeadrestHeightAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_LEFT_MEMORY_SWITCH_CONFIGURATION_BY_ECU_R /* 557856244 */:
                                        sUistyleEventChangeValue.setRearLeftMemorySwitchConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATION_BY_ECU_R /* 557856245 */:
                                        sUistyleEventChangeValue.setLeftRearSeatReclineConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R /* 557856246 */:
                                        sUistyleEventChangeValue.setLeftRearSeatBackAdjustmentConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R /* 557856247 */:
                                        sUistyleEventChangeValue.setLeftRearSeatHorizontalAdjustmentConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_LEG_BRACKET_R /* 557856248 */:
                                        sUistyleEventChangeValue.setLeftRearSeatLegBracketR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_SEAT_RECLINE_CONFIGURATIONR /* 557856250 */:
                                        sUistyleEventChangeValue.setRearSeatReclineConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856251 */:
                                        sUistyleEventChangeValue.setRearRightLevelAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_BACKREST_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856252 */:
                                        sUistyleEventChangeValue.setRearRightBackrestAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_LEG_SUPPORT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856253 */:
                                        sUistyleEventChangeValue.setRearRightLegSupportAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_BIDET_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856254 */:
                                        sUistyleEventChangeValue.setRearRightBidetAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557856255 */:
                                        sUistyleEventChangeValue.setRearRightHeightAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_LEVEL_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557859072 */:
                                        sUistyleEventChangeValue.setRearRightHeadrestLevelAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REAR_RIGHT_HEADREST_HEIGHT_ADJUSTMENT_CONFIGURATION_BY_ECU_R /* 557859073 */:
                                        sUistyleEventChangeValue.setRearRightHeadrestHeightAdjustmentConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATION_BY_EUC_R /* 557859075 */:
                                        sUistyleEventChangeValue.setRightRearSeatReclineConfigurationByEUCR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_BACK_ADJUSTMENT_CONFIGURATION_R /* 557859076 */:
                                        sUistyleEventChangeValue.setRightRearSeatBackAdjustmentConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HORIZONTAL_ADJUSTMENT_CONFIGURATION_R /* 557859077 */:
                                        sUistyleEventChangeValue.setRightRearSeatHorizontalAdjustmentConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_LEG_BRACKET_R /* 557859078 */:
                                        sUistyleEventChangeValue.setRightRearSeatLegBracketR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_MEMORY_CONFIGURATION_R /* 557859079 */:
                                        sUistyleEventChangeValue.setRightRearMemoryConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ADAS_SYSTEM_STATE_R /* 557859080 */:
                                        sUistyleEventChangeValue.setADASSystemStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CAR_PLAN_SUPPORT_R /* 557859081 */:
                                        sUistyleEventChangeValue.setCarPlanSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_NO_SENSE_CHARGE_SUPPORT_R /* 557859082 */:
                                        sUistyleEventChangeValue.setNoSenseChargeSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ELECTRIC_FRONT_HATCH_CONFIGURATION_R /* 557859083 */:
                                        sUistyleEventChangeValue.setElectricFrontHatchConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CHARGE_PREHEAT_SUPPORT_R /* 557859084 */:
                                        sUistyleEventChangeValue.setChargePreheatSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_DRIVING_VOICE_BROADCAST_SWITCH_CONFIG_THREER /* 557859088 */:
                                        sUistyleEventChangeValue.setDrivingVoiceBroadcastSwitchConfigThreeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_REST_MODE_CONFIGURATION_R /* 557859089 */:
                                        sUistyleEventChangeValue.setRestModeConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_RES_CONFIGURATION_R /* 557859090 */:
                                        sUistyleEventChangeValue.setLeftRESConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_RES_CONFIGURATION_R /* 557859091 */:
                                        sUistyleEventChangeValue.setRightRESConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_HUD_MODE_CONFIGURATION_R /* 557859092 */:
                                        sUistyleEventChangeValue.setHUDModeConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_INTELLIGENT_SCREEN_CASTING_SWITCH_CONFIGURATION_R /* 557859093 */:
                                        sUistyleEventChangeValue.setIntelligentScreenCastingSwitchConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_FR_ELECTRIC_AIR_OUTLET_R /* 557859094 */:
                                        sUistyleEventChangeValue.setSupportFRElectricAirOutletR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_DRIVER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_LEFT_CTRL_R /* 557859100 */:
                                        sUistyleEventChangeValue.setDriverSeatHotStoneMassageConfigurationByLeftCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859101:
                                        sUistyleEventChangeValue.setDriverSeatHotStoneMassageConfigurationByUniversalCtrlR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_ESP_R /* 557859107 */:
                                        sUistyleEventChangeValue.setSupportESPR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859108:
                                        sUistyleEventChangeValue.setHasPredictCrashAlertModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859112:
                                        sUistyleEventChangeValue.setPabSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859113:
                                        sUistyleEventChangeValue.setFrontLightDimmingCtrlConfigBy3acR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859114:
                                        sUistyleEventChangeValue.setFrontLightDimmingCtrlConfigBy3adR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859115:
                                        sUistyleEventChangeValue.setTsrLitSpdChgSwitchStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_DOOR_LIGHT_SUPPORT_R /* 557859116 */:
                                        sUistyleEventChangeValue.setDoorLightSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_AI_CARE /* 557859117 */:
                                        sUistyleEventChangeValue.setSupportAiCare(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_URBAN_NAVIGATION /* 557859118 */:
                                        sUistyleEventChangeValue.setSupportUrbanNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R /* 557859152 */:
                                        sUistyleEventChangeValue.setPassengerSeatHotStoneMassageConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R /* 557859153 */:
                                        sUistyleEventChangeValue.setLeftRearSeatHotStoneMassageConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_HOT_STONE_MASSAGE_CONFIGURATION_BY_ECU_R /* 557859154 */:
                                        sUistyleEventChangeValue.setRightRearSeatHotStoneMassageConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_RUNNING_CLOSE_DOOR_SUPPORT_R /* 557859171 */:
                                        sUistyleEventChangeValue.setRunningCloseDoorSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859175:
                                        sUistyleEventChangeValue.setTailgateTypeMarkingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_T_S_R_FLOW_R /* 557859176 */:
                                        sUistyleEventChangeValue.setSupportTsrFlowR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_DOUBLE_CONFIRM_R /* 557859177 */:
                                        sUistyleEventChangeValue.setSupportDoubleConfirmR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_A_C_CHARGING_LIMIT_R /* 557859178 */:
                                        sUistyleEventChangeValue.setSupportAcChargingLimitR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_ROAD_MODE_R /* 557859179 */:
                                        sUistyleEventChangeValue.setSupportRoadModeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_R /* 557859213 */:
                                        sUistyleEventChangeValue.setSupportChargeHEVThreeR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_CHARGE_H_E_V_THREE_V_C_U_R /* 557859214 */:
                                        sUistyleEventChangeValue.setSupportChargeHEVThreeVCUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_S_O_C_TARGET_CONFIG_R /* 557859215 */:
                                        sUistyleEventChangeValue.setsocTargetConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ENERGY_SHOW_SUPPORT_R /* 557859216 */:
                                        sUistyleEventChangeValue.setEnergyShowSupportR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CABIN_MOVEMENT_MONITORING_FUNCTION_R /* 557859217 */:
                                        sUistyleEventChangeValue.setCabinMovementMonitoringFunctionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CHARGE_LIMIT_SUPPORT_NOT_LI_R /* 557859243 */:
                                        sUistyleEventChangeValue.setChargeLimitSupportNotLiR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ELEC_WHERE_CONFIG_R /* 557859244 */:
                                        sUistyleEventChangeValue.setElecWhereConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_L_D_A_DOUBLE_CONFIRM_R /* 557859245 */:
                                        sUistyleEventChangeValue.setLdaDoubleConfirmR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_E_L_K_A_DOUBLE_CONFIRM_R /* 557859246 */:
                                        sUistyleEventChangeValue.setElkaDoubleConfirmR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_F_C_W_DOUBLE_CONFIRM_R /* 557859247 */:
                                        sUistyleEventChangeValue.setFcwDoubleConfirmR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_A_E_B_DOUBLE_CONFIRM_R /* 557859248 */:
                                        sUistyleEventChangeValue.setAebDoubleConfirmR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_F_C_S_R_R /* 557859256 */:
                                        sUistyleEventChangeValue.setSupportFcsrR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859257:
                                        sUistyleEventChangeValue.setNonElecLeftPortConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859258:
                                        sUistyleEventChangeValue.setNonElecRightPortConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859259:
                                        sUistyleEventChangeValue.setCarDoorConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859260:
                                        sUistyleEventChangeValue.setTrailerDrModeConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859261:
                                        sUistyleEventChangeValue.setTowingCruiseConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557859262:
                                        sUistyleEventChangeValue.setTrlrCrsConfigR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557862942:
                                        sUistyleEventChangeValue.setPanoramicSunroofConfigutarionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557870336:
                                        sUistyleEventChangeValue.setDrivingVoiceBroadcastSwitchConfigOneR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ADAS_SYSTEM_STATER /* 557870364 */:
                                        sUistyleEventChangeValue.setADASSystemStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_SUPPORT_END_TO_END_NAVIGATION /* 557870367 */:
                                        sUistyleEventChangeValue.setSupportEndToEndNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_DMS_ASSEMBLE_STATUS_R /* 557870369 */:
                                        sUistyleEventChangeValue.setDMSAssembleStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATER /* 557875200 */:
                                        sUistyleEventChangeValue.setTheLast50KMAveragePowerConsumptionInterfaceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATER /* 557875203 */:
                                        sUistyleEventChangeValue.setTheLast50KMAverageFuelConsumptionInterfaceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_THE_LAST50_K_M_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATER /* 557875206 */:
                                        sUistyleEventChangeValue.setTheLast50KMAverageEnergyConsumptionInterfaceStateR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_ENERGY_CONSUMPTION_INTERFACE_STATUSR /* 557875208 */:
                                        sUistyleEventChangeValue.setCumulativeAverageEnergyConsumptionInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_POWER_CONSUMPTION_INTERFACE_STATUSR /* 557875209 */:
                                        sUistyleEventChangeValue.setCumulativeAveragePowerConsumptionInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_CUMULATIVE_AVERAGE_FUEL_CONSUMPTION_INTERFACE_STATUSR /* 557875210 */:
                                        sUistyleEventChangeValue.setCumulativeAverageFuelConsumptionInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_INSTANTANEOUS_FUEL_CONSUMPTION_INTERFACE_STATUSR /* 557875211 */:
                                        sUistyleEventChangeValue.setInstantaneousFuelConsumptionInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_DRIVING_RANGE_INTERFACE_STATUS_FOR_FUELR /* 557875214 */:
                                        sUistyleEventChangeValue.setDrivingRangeInterfaceStatusForFuelR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_FUEL_CONSUMPTION_INTERFACE_STATUSR /* 557875216 */:
                                        sUistyleEventChangeValue.setFuelConsumptionInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_AVERAGE_SPEED_INTERFACE_STATUSR /* 557875218 */:
                                        sUistyleEventChangeValue.setAverageSpeedInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_EXTERNAL_CHARGING_POWER_INTERFACE_STATUSR /* 557875220 */:
                                        sUistyleEventChangeValue.setExternalChargingPowerInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_ENERGY_FLOW_DIAGRAM_INTERFACE_STATUSR /* 557875223 */:
                                        sUistyleEventChangeValue.setEnergyFlowDiagramInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case CarVendorUistyleManager.ID_TIRE_PRESSURE_INTERFACE_STATUSR /* 557875228 */:
                                        sUistyleEventChangeValue.setTirePressureInterfaceStatusR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    case 557879608:
                                        sUistyleEventChangeValue.setAmplifierConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                        break;
                                    default:
                                        switch (propertyId) {
                                            case 557856000:
                                                sUistyleEventChangeValue.setPreSaleModelSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856001:
                                                sUistyleEventChangeValue.setCarNum(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856002:
                                                sUistyleEventChangeValue.setEnergyStatus(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856003:
                                                sUistyleEventChangeValue.setDriveConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            case 557856004:
                                                sUistyleEventChangeValue.setDMType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                break;
                                            default:
                                                switch (propertyId) {
                                                    case 557856014:
                                                        sUistyleEventChangeValue.setSupportSingleDoubleTempArea(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856015:
                                                        sUistyleEventChangeValue.setAcWindModeNum(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856016:
                                                        sUistyleEventChangeValue.setSupportAcAutoKeyCanClose(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856017:
                                                        sUistyleEventChangeValue.setAcWarmTypeOnlineState(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856018:
                                                        sUistyleEventChangeValue.setSupportSteeringWheelWarm(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856019:
                                                        sUistyleEventChangeValue.setSupportSeatMassageMain(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856020:
                                                        sUistyleEventChangeValue.setSupportSeatMassageDeputy(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856021:
                                                        sUistyleEventChangeValue.setSupportSeatMassageLeftRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856022:
                                                        sUistyleEventChangeValue.setSupportSeatMassageRightRear(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856023:
                                                        sUistyleEventChangeValue.setLeftPlatformConfigs(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856024:
                                                        sUistyleEventChangeValue.setSupportPM25Mode(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856025:
                                                        sUistyleEventChangeValue.setSupportNegativeIon(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856026:
                                                        sUistyleEventChangeValue.setSupportHighTemperatureSterilize(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856027:
                                                        sUistyleEventChangeValue.setSupportFragrance(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856028:
                                                        sUistyleEventChangeValue.setSupportFragranceTypeOne(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856029:
                                                        sUistyleEventChangeValue.setSupportFragranceTypeTwo(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856030:
                                                        sUistyleEventChangeValue.setSupportFragranceTypeThree(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856031:
                                                        sUistyleEventChangeValue.setCarType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856032:
                                                        sUistyleEventChangeValue.setSupportLampSingnal(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856033:
                                                        sUistyleEventChangeValue.setSupportAFS(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856034:
                                                        sUistyleEventChangeValue.setHeadlampSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856035:
                                                        sUistyleEventChangeValue.setHeadlampLevelConfig(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856036:
                                                        sUistyleEventChangeValue.setresetHeadlamp(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856037:
                                                        sUistyleEventChangeValue.setBackHomeDelaySupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856038:
                                                        sUistyleEventChangeValue.setLeaveHomeDelaySupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856039:
                                                        sUistyleEventChangeValue.setResetBackAndLeaveDelaySupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856040:
                                                        sUistyleEventChangeValue.setUsherLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856041:
                                                        sUistyleEventChangeValue.setDayLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856042:
                                                        sUistyleEventChangeValue.setResetDayLight(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        sUistyleEventChangeValue.setResetAmbientSettings(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856043:
                                                        sUistyleEventChangeValue.setDoorLightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856044:
                                                        sUistyleEventChangeValue.setSupportReverseRadar(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856045:
                                                        sUistyleEventChangeValue.setIntelligentKeepTemSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856046:
                                                        sUistyleEventChangeValue.setChargeToaggleGuardSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856047:
                                                        sUistyleEventChangeValue.setChargeLimitSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856048:
                                                        sUistyleEventChangeValue.setOBCType(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856049:
                                                        sUistyleEventChangeValue.setRearViewMirrorFlipSupportFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856050:
                                                        sUistyleEventChangeValue.setRearViewMirrorFlipSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856051:
                                                        sUistyleEventChangeValue.setAutoPowerOnStateSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856052:
                                                        sUistyleEventChangeValue.setAutoPowerOffSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856053:
                                                        sUistyleEventChangeValue.setAutoExternalRearMirrorFollowUpSwitchSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856054:
                                                        sUistyleEventChangeValue.setDriveSeatAutoReturnSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856055:
                                                        sUistyleEventChangeValue.setSteerPositionAutoReturnSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856056:
                                                        sUistyleEventChangeValue.setBrakePedalModeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856057:
                                                        sUistyleEventChangeValue.setComfortableParkingSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856058:
                                                        sUistyleEventChangeValue.setMotorPretensionerSeatbeltSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856059:
                                                        sUistyleEventChangeValue.setSteerAssisSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856060:
                                                        sUistyleEventChangeValue.setAutoWiperSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856061:
                                                        sUistyleEventChangeValue.setDynamicSuspensionModeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856062:
                                                        sUistyleEventChangeValue.setLandAdvanceAtSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856063:
                                                        sUistyleEventChangeValue.setRemoteKeyStartAcTimeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856064:
                                                        sUistyleEventChangeValue.setACAutoAirSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856065:
                                                        sUistyleEventChangeValue.setACBTWindSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856066:
                                                        sUistyleEventChangeValue.setACPauseCycleSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856067:
                                                        sUistyleEventChangeValue.setAutoPurificationSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856068:
                                                        sUistyleEventChangeValue.setPM25PowerSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856069:
                                                        sUistyleEventChangeValue.setPM25SwitchCheckSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856070:
                                                        sUistyleEventChangeValue.setPM25TimeCkeckSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856071:
                                                        sUistyleEventChangeValue.setACTunnelCycleSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856072:
                                                        sUistyleEventChangeValue.setHudShowParamsSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856073:
                                                        sUistyleEventChangeValue.setRearPanelLockTimeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856074:
                                                        sUistyleEventChangeValue.setAcWarmSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856075:
                                                        sUistyleEventChangeValue.setIntelligentAcAreaSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856076:
                                                        sUistyleEventChangeValue.setDoorHeightSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        sUistyleEventChangeValue.setTrunkConfigutarionR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856077:
                                                        sUistyleEventChangeValue.setSupportIntelligentNavigationFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856078:
                                                        sUistyleEventChangeValue.setSupportIntelligentNavigationSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    case 557856079:
                                                        sUistyleEventChangeValue.setSupportHighwayNavigation(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                        break;
                                                    default:
                                                        switch (propertyId) {
                                                            case 557856190:
                                                                sUistyleEventChangeValue.setHudParamsStateSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856191:
                                                                sUistyleEventChangeValue.setHudHeightAdjustSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856192:
                                                                sUistyleEventChangeValue.setHudLightSdjustSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            case 557856193:
                                                                sUistyleEventChangeValue.setInstrumentThemeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                break;
                                                            default:
                                                                switch (propertyId) {
                                                                    case 557856195:
                                                                        sUistyleEventChangeValue.setInstrumentContentSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856196:
                                                                        sUistyleEventChangeValue.setDischargeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856197:
                                                                        sUistyleEventChangeValue.setDriveDistanceShowStandardSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856198:
                                                                        sUistyleEventChangeValue.setEPedalStateSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856199:
                                                                        sUistyleEventChangeValue.setDriveDistanceShowModeSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856200:
                                                                        sUistyleEventChangeValue.setMainSeatRememberSupportSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856201:
                                                                        sUistyleEventChangeValue.setSecondSeatRememberSupport(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856202:
                                                                        sUistyleEventChangeValue.setGuestLeftSeatRememeSupportFirstL(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856203:
                                                                        sUistyleEventChangeValue.setLeftRearMemoryConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856204:
                                                                        sUistyleEventChangeValue.setRearRightMemorySwitchConfigurationByECUR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        sUistyleEventChangeValue.setGuestRightSeatRememeSupportFirstR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856205:
                                                                        sUistyleEventChangeValue.setGuestRightSeatRememeSupportSecondR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856206:
                                                                        sUistyleEventChangeValue.setElectroniChildLockConfigFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856207:
                                                                        sUistyleEventChangeValue.setElectroniChildLockConfigSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856208:
                                                                        sUistyleEventChangeValue.setWindowAntiPinchConfigFirst(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    case 557856209:
                                                                        sUistyleEventChangeValue.setWindowAntiPinchConfigSecond(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                        break;
                                                                    default:
                                                                        switch (propertyId) {
                                                                            case 557856211:
                                                                                sUistyleEventChangeValue.setComfortableSpeedUp(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_LEFT_BACK_ECU /* 557856212 */:
                                                                                sUistyleEventChangeValue.setSupportSeatMassageLeftBackECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            case CarVendorUistyleManager.ID_UPPORT_SEAT_MASSAGE_RIGHT_BACK_ECU /* 557856213 */:
                                                                                sUistyleEventChangeValue.setSupportSeatMassageRightBackECU(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                break;
                                                                            default:
                                                                                switch (propertyId) {
                                                                                    case CarVendorUistyleManager.ID_STEERING_WHEEL_HEATING_CONFIGURATIONR /* 557856215 */:
                                                                                        sUistyleEventChangeValue.setSteeringWheelHeatingConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_VALET_PARKINGR /* 557856216 */:
                                                                                        sUistyleEventChangeValue.setValetParkingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_MEMORY_PARKINGR /* 557856217 */:
                                                                                        sUistyleEventChangeValue.setMemoryParkingR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_PASSENGER_SEAT_MEMORY_FUNCTION_CANR /* 557856218 */:
                                                                                        sUistyleEventChangeValue.setPassengerSeatMemoryFunctionCanR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_DRIVE_TIME_INTERFACE_STATUS_FOR_SINGLE_TRIPR /* 557856219 */:
                                                                                        sUistyleEventChangeValue.setDriveTimeInterfaceStatusForSingleTripR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_RIGHT_REAR_SEAT_RECLINE_CONFIGURATIONR /* 557856220 */:
                                                                                        sUistyleEventChangeValue.setRightRearSeatReclineConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    case CarVendorUistyleManager.ID_LEFT_REAR_SEAT_RECLINE_CONFIGURATIONR /* 557856221 */:
                                                                                        sUistyleEventChangeValue.setLeftRearSeatReclineConfigurationR(CastArrayUtil.toPrimitive((Integer) value.getValue()));
                                                                                        break;
                                                                                    default:
                                                                                        Log.debug(CarVendorUistyleManager.TAG, "break other properties ,except uistyle");
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
        Log.debug(CarVendorUistyleManager.TAG, "convertCarVendorPropertyValue " + sUistyleEventChangeValue.toString(), DEBUG_LOGGER);
        return sUistyleEventChangeValue;
    }

    private OnCarUistyleChangeEventListenerProxy findListenerLocked(CarVendorUistyleManager.OnCarUistyleEventListener listener) {
        for (OnCarUistyleChangeEventListenerProxy proxy : this.mListeners) {
            if (proxy.listener == listener) {
                return proxy;
            }
        }
        return null;
    }

    private class OnCarUistyleChangeEventListenerProxy implements CarPropertyManager.CarPropertyEventCallback {
        private final CarVendorUistyleManager.OnCarUistyleEventListener listener;

        public OnCarUistyleChangeEventListenerProxy(CarVendorUistyleManager.OnCarUistyleEventListener listener) {
            this.listener = listener;
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onChangeEvent(CarPropertyValue value) {
            if (value.getStatus() == 2) {
                return;
            }
            try {
                CarVendorUistyleMgr.this.traceCallbackPrepare();
                UistyleEventChangeValue newValue = CarVendorUistyleMgr.convertCarVendorProperty(value);
                CarVendorUistyleMgr.this.traceCallbackBefore();
                this.listener.onUistyleEventChange(newValue);
                CarVendorUistyleMgr.this.traceCallbackEnd();
            } catch (Exception exception) {
                android.util.Log.e(CarVendorUistyleManager.TAG, "id is " + value.getPropertyId() + " convert car vendor property error!!!! " + exception);
            }
        }

        @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
        public void onErrorEvent(int propertyId, int zone) {
            Log.error(CarVendorUistyleManager.TAG, "onErrorEvent  propertyId: " + propertyId + " zone: " + zone);
            this.listener.onUistyleErrorEvent(propertyId, zone);
        }
    }
}
