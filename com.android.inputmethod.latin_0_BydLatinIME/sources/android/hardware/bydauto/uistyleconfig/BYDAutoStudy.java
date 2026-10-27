package android.hardware.bydauto.uistyleconfig;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoStudy extends AbsBYDAutoDevice {
    public static final int AC_AUTO_KEY_CANNOT_CLOSE = 0;
    public static final int AC_AUTO_KEY_CAN_CLOSE = 1;
    public static final int AC_COMMAND_HAVE = 2;
    public static final int AC_COMMAND_NONE = 1;
    public static final int AC_CONTROL_TYPE_AUTO = 1;
    public static final int AC_CONTROL_TYPE_ELECTRIC = 2;
    public static final int AC_TEMPERATURE_AREA_DOUBLE = 1;
    public static final int AC_TEMPERATURE_AREA_SINGLE = 2;
    public static final int AC_WARM_TYPE_FLAG_FUEL = 2;
    public static final int AC_WARM_TYPE_FLAG_HEAT_PUMP = 6;
    public static final int AC_WARM_TYPE_FLAG_INVALID = 0;
    public static final int AC_WARM_TYPE_FLAG_LOW_TENSION_PTC = 5;
    public static final int AC_WARM_TYPE_FLAG_PTC = 1;
    public static final int AC_WARM_TYPE_FLAG_PTCFUEL = 3;
    public static final int AC_WARM_TYPE_FLAG_WIND = 4;
    public static final int AC_WIND_MODE_COUNT5 = 5;
    public static final int AC_WIND_MODE_COUNT7 = 7;
    public static final int AREA_ALL = 2;
    public static final int AREA_INVALID = 0;
    public static final int AREA_NULL = 1;
    public static final int AREA_OTHER = 3;
    public static final int AUTO_REPORT_0 = 0;
    public static final int AUTO_REPORT_1 = 1;
    public static final int AUTO_REPORT_10 = 10;
    public static final int AUTO_REPORT_2 = 2;
    public static final int AUTO_REPORT_3 = 3;
    public static final int AUTO_REPORT_4 = 4;
    public static final int AUTO_REPORT_5 = 5;
    public static final int AUTO_REPORT_6 = 6;
    public static final int AUTO_REPORT_7 = 7;
    public static final int AUTO_REPORT_8 = 8;
    public static final int AUTO_REPORT_9 = 9;
    public static final int BACK_HOME_DELAY_LEVEL_CLOSE = 7;
    public static final int BACK_HOME_DELAY_LEVEL_FIFTH = 5;
    public static final int BACK_HOME_DELAY_LEVEL_FIRST = 1;
    public static final int BACK_HOME_DELAY_LEVEL_FOURTH = 4;
    public static final int BACK_HOME_DELAY_LEVEL_INVALID = 0;
    public static final int BACK_HOME_DELAY_LEVEL_SECOND = 2;
    public static final int BACK_HOME_DELAY_LEVEL_SIXTH = 6;
    public static final int BACK_HOME_DELAY_LEVEL_THIRD = 3;
    public static final int CAST_SUNROOF_AND_SUNSHADE = 1;
    public static final int CHARGE_GURAD_NOT_SUPPORT = 1;
    public static final int CHARGE_GURAD_SUPPORT = 0;
    public static final int CHARGE_LIMIT_NOT_SUPPORT = 1;
    public static final int CHARGE_LIMIT_SUPPORT = 2;
    public static final int CHARGE_STATE_SUPPORT = 1;
    public static final int CHARGE_STATE_UNSUPPORT = 0;
    public static final int CHILD_LOCK = 2;
    public static final int CONFIGURE_FULL_POWER_UNLOCK_FUNCTION = 0;
    public static final int DISCHARGE_NOT_SUPPORT = 0;
    public static final int DISCHARGE_SUPPORT = 1;
    public static final int DRIVE_CONFIG_EV_1 = 1;
    public static final int DRIVE_CONFIG_EV_2 = 2;
    public static final int DRIVE_CONFIG_EV_3 = 3;
    public static final int DRIVE_CONFIG_EV_4 = 4;
    public static final int DRIVE_CONFIG_HEV_1 = 1;
    public static final int DRIVE_CONFIG_HEV_2 = 2;
    public static final int DRIVE_CONFIG_HEV_3 = 3;
    public static final int DRIVE_CONFIG_HEV_4 = 4;
    public static final int DRIVE_CONFIG_HEV_5 = 5;
    public static final int DRIVE_MODE_SUPPORT = 2;
    public static final int DRIVE_MODE_UNSUPPORT = 1;
    public static final int DRIVE_PLATFORM_DM_TW0_ZERO = 3;
    public static final int DRIVE_PLATFORM_DM_TWO_FIVE = 2;
    public static final int DRIVE_PLATFORM_EV = 1;
    public static final int DRIVE_STANDARD_NOT_SUPPORT = 1;
    public static final int DRIVE_STANDARD_SUPPORT = 0;
    public static final int DUAL_TEMP_AC_AND_4_FR_AIR_OUTLET = 2;
    public static final int DYNAMIC_MODE_COMFORTABLE = 1;
    public static final int DYNAMIC_MODE_SPORT = 2;
    public static final int EIGHT_DIRECTION_ELECTRIC_ADJUSTMENT = 3;
    public static final int ELECTRIC_AIR_OUTLET_INVALID = 0;
    public static final int ELECTRIC_DEFROST_CONFIG1 = 1;
    public static final int ELECTRIC_DEFROST_CONFIG2 = 2;
    public static final int ELECTRIC_DEFROST_CONFIG3 = 3;
    public static final int ELECTRONIC_LOCK_CHILD = 2;
    public static final int ELECTRONIC_LOCK_WINDOW = 1;
    public static final int ENERGY_STATUS_EV_1 = 1;
    public static final int ENERGY_STATUS_EV_2 = 2;
    public static final int ENERGY_STATUS_EV_3 = 3;
    public static final int ENERGY_STATUS_EV_4 = 4;
    public static final int ENERGY_STATUS_EV_5 = 5;
    public static final int ENERGY_STATUS_EV_6 = 6;
    public static final int ENERGY_STATUS_HEV_1 = 1;
    public static final int EPEDAL_NOT_SUPPORT = 1;
    public static final int EPEDAL_SUPPORT = 0;
    public static final int ESP_NO_BUTTON = 2;
    public static final int ESP_RESERVED = 0;
    public static final int ESP_WITH_BUTTON = 1;
    public static final int FIFTEEN_DIRECTION_ELECTRIC_ADJUSTMENT = 6;
    public static final int FOURTEEN_DIRECTION_ELECTRIC_ADJUSTMENT = 5;
    public static final int FOUR_DIRECTION_ELECTRIC_ADJUSTMENT = 1;
    public static final int FOUR_DOOR_WINDOW_ANTIPINCH = 4;
    public static final int FOUR_ZONE_AC_AND_4_FR_AIR_OUTLET = 3;
    public static final int FOUR_ZONE_AC_AND_4_FR_AIR_OUTLET_AND_REGULAR_AIR_OUTLET = 4;
    public static final int FRAGRANCE_COMMAND_HAVE = 1;
    public static final int FRAGRANCE_COMMAND_NONE = 0;
    public static final int FRONT_DOOR_WINDOW_ANTIPINCH = 3;
    public static final int HAS_PRE_SALE_MODE = 0;
    public static final int HAVE_CONFIGURATION = 2;
    public static final int HEIGHT_ADJUSTMENT_CONFIGURATION = 2;
    public static final int IAL_COLOR_MODE_SUPPORT = 1;
    public static final int IAL_COLOR_MODE_UNSUPPORT = 2;
    public static final int IAL_COLOR_NONE = 1;
    public static final int IAL_COLOR_ONE = 2;
    public static final int IAL_COLOR_RESERVED = 5;
    public static final int IAL_COLOR_SEVEN = 3;
    public static final int IAL_COLOR_THIRTYONE = 4;
    public static final int IAL_MUSIC_MODE_CLOSE = 2;
    public static final int IAL_MUSIC_MODE_OPEN = 1;
    public static final int INTELLIGENT_KEEP_TEM_NOT_SUPPORT = 2;
    public static final int INTELLIGENT_KEEP_TEM_SUPPORT = 1;
    public static final int LAMPSIGNAL_SUPPORT = 1;
    public static final int LAMPSIGNAL_UNSUPPORT = 0;
    public static final int LEAVE_HOME_DELAY_LEVEL_CLOSE = 7;
    public static final int LEAVE_HOME_DELAY_LEVEL_FIFTH = 5;
    public static final int LEAVE_HOME_DELAY_LEVEL_FIRST = 1;
    public static final int LEAVE_HOME_DELAY_LEVEL_FOURTH = 4;
    public static final int LEAVE_HOME_DELAY_LEVEL_INVALID = 0;
    public static final int LEAVE_HOME_DELAY_LEVEL_SECOND = 2;
    public static final int LEAVE_HOME_DELAY_LEVEL_SIXTH = 6;
    public static final int LEAVE_HOME_DELAY_LEVEL_THIRD = 3;
    public static final int LEFT_FRONT = 0;
    public static final int LEFT_FRONT_WINDOW_ANTIPINCH = 2;
    public static final int LEFT_REAR = 2;
    public static final int LEVEL_ADJUSTMENT_CONFIGURATION = 1;
    public static final int LEVEL_HEIGHT_ADJUSTMENT_CONFIGURATION = 3;
    public static final int LIGHT_INVALID = 0;
    public static final int LIGHT_NULL = 1;
    public static final int LIGHT_OFF = 2;
    public static final int LIGHT_ON = 1;
    public static final int LIGHT_OTHER = 3;
    public static final int LIGHT_SIX = 2;
    public static final int LOCK_FAULT = 3;
    public static final int MODE_BACK = 0;
    public static final int MODE_LEAVE = 1;
    public static final int NO_CONFIGURATION = 1;
    public static final int NO_ELEVTRIC_AIR_OUTLET = 1;
    public static final int NO_ESP = 3;
    public static final int NO_PANORAMIC_SUNROOF = 5;
    public static final int NO_PRE_SALE_MODE = 1;
    public static final int NO_WINDOW_ANTIPINCH = 1;
    public static final int OBC_TYPE_ISOLATION_NOT = 0;
    public static final int OBC_TYPE_ISOLATION_ONE = 2;
    public static final int OBC_TYPE_ISOLATION_TWO = 1;
    public static final int PANORAMIC_IMAGE_2D = 1;
    public static final int PANORAMIC_IMAGE_3D = 5;
    public static final int PANORAMIC_IMAGE_BACK = 3;
    public static final int PANORAMIC_IMAGE_BACK_RIGHT_FRONT = 2;
    public static final int PANORAMIC_IMAGE_INVALID = 0;
    public static final int PANORAMIC_IMAGE_RESERVED = 6;
    public static final int PANORAMIC_IMAGE_STANDARD = 4;
    public static final int PANORAMIC_SUN_BLINDS = 2;
    public static final int PM2P5_AISLE_MODE1 = 1;
    public static final int PM2P5_AISLE_MODE2 = 2;
    public static final int PM2P5_COMMAND_HAVE = 2;
    public static final int PM2P5_COMMAND_NONE = 1;
    public static final int PM2P5_DUAL_AISLE_MODE = 1;
    public static final int PM2P5_SINGLE_AISLE_MODE = 2;
    public static final int PREDICT_CRASH_ALERT_MODE_SELECT = 0;
    public static final int PREDICT_CRASH_ALERT_MODE_SWITCH = 1;
    public static final int PRESALE_MODEL_NOT_SUPPORT = 1;
    public static final int PRESALE_MODEL_SUPPORT = 0;
    public static final int REAR_AC_WIND_LEVEL_COUNT4 = 4;
    public static final int REAR_AC_WIND_LEVEL_COUNT7 = 7;
    public static final int REPORT_AUTOMATIC_PARK = 16;
    public static final int REPORT_CARE_MODE = 9;
    public static final int REPORT_DRIVE_DOOR_CLOSE = 2;
    public static final int REPORT_KEY_POWER_LOW = 5;
    public static final int REPORT_LEAVE_CAR = 4;
    public static final int REPORT_LOW_OIL = 8;
    public static final int REPORT_MISS_KEY = 6;
    public static final int REPORT_NIGHT_DR_LIGHT_ON = 7;
    public static final int REPORT_PARK_BRAKE = 1;
    public static final int REPORT_SAFE_BELT = 3;
    public static final int REPORT_START_OR_POWER = 0;
    public static final int RESET_CANCEL = 2;
    public static final int RESET_CONFIRM = 1;
    public static final int RIGHT_FRONT = 1;
    public static final int RIGHT_REAR = 3;
    public static final int SEAT_COMMAND_HAVE = 1;
    public static final int SEAT_COMMAND_NONE = 0;
    public static final int SEAT_MASSAGE_DEPUTY = 2;
    public static final int SEAT_MASSAGE_LEFT_REAR = 3;
    public static final int SEAT_MASSAGE_MAIN = 1;
    public static final int SEAT_MASSAGE_RIGHT_REAR = 4;
    public static final int SET_OFF = 0;
    public static final int SET_ON = 1;
    public static final int SIX_DIRECTION_ELECTRIC_ADJUSTMENT = 2;
    public static final int SMAIL_BUILT_IN_SKYLIGHT = 4;
    public static final int SMAIL_BUILT_IN_SKYLIGHT_AND_ANTI_PINCH = 3;
    public static final int STATE_127_ATMOSPHERE_LAMP = 6;
    public static final int STATE_31_ATMOSPHERE_LAMP = 4;
    public static final int STATE_3R1V = 4;
    public static final int STATE_5R10V = 3;
    public static final int STATE_5R13V = 2;
    public static final int STATE_5R1V = 1;
    public static final int STATE_64_ATMOSPHERE_LAMP = 5;
    public static final int STATE_7_ATMOSPHERE_LAMP = 3;
    public static final int STATE_AC_AUTO_MODE_ONE = 1;
    public static final int STATE_AC_AUTO_MODE_TWO = 2;
    public static final int STATE_AC_TUNNEL_CYCLE_CLOSE = 2;
    public static final int STATE_AC_TUNNEL_CYCLE_OPEN = 1;
    public static final int STATE_ALL_FRONT_REAR = 2;
    public static final int STATE_ALL_FRONT_REAR_RING = 3;
    public static final int STATE_ALL_FRONT_REAR_STARRY_SKY = 4;
    public static final int STATE_AUTO_PURIFICATION_SUPPORT = 1;
    public static final int STATE_AUTO_PURIFICATION_UNSUPPORT = 2;
    public static final int STATE_AUTO_WIPER_SUPPORT = 1;
    public static final int STATE_AUTO_WIPER_UNSUPPORT = 0;
    public static final int STATE_BACK_DOOR_HEIGHT_SUPPORT = 1;
    public static final int STATE_BACK_DOOR_HEIGHT_UNSUPPORT = 0;
    public static final int STATE_CANCEL = 2;
    public static final int STATE_CHARGE_LIMIT_NOSUPPORT = 1;
    public static final int STATE_CHARGE_LIMIT_SUPPORT = 2;
    public static final int STATE_CLOSE = 2;
    public static final int STATE_CONFIRM = 1;
    public static final int STATE_DAYLIGHT_CLOSE = 2;
    public static final int STATE_DAYLIGHT_OPEN = 1;
    public static final int STATE_DIGITAL_KEY_UNSUPPORT = 0;
    public static final int STATE_DOMAIN_CONTROLLER = 2;
    public static final int STATE_ELECTRIC_OPEN = 3;
    public static final int STATE_ELECTRIC_OPEN_ABSORPTION = 2;
    public static final int STATE_ELECTRIC_UNLOCK = 1;
    public static final int STATE_ENGINE_SOUND_SUPPORT = 1;
    public static final int STATE_ENGINE_SOUND_UNSUPPORT = 2;
    public static final int STATE_GEARS_11 = 0;
    public static final int STATE_GEARS_6 = 1;
    public static final int STATE_HAS = 1;
    public static final int STATE_HAS_DC = 2;
    public static final int STATE_HEADLAMP_SUPPORT = 1;
    public static final int STATE_HEADLAMP_UNSUPPORT = 0;
    public static final int STATE_INDOOR_SUPPORT = 1;
    public static final int STATE_INDOOR_UNSUPPORT = 0;
    public static final int STATE_INTELLIGENT_LOCK_CLOSE = 2;
    public static final int STATE_INTELLIGENT_LOCK_INVALID = 0;
    public static final int STATE_INTELLIGENT_LOCK_OPEN = 1;
    public static final int STATE_INTELLIGENT_LOCK_RESERVED = 3;
    public static final int STATE_INTELLIGENT_UNLOCK_CLOSE = 2;
    public static final int STATE_INTELLIGENT_UNLOCK_INVALID = 0;
    public static final int STATE_INTELLIGENT_UNLOCK_OPEN = 1;
    public static final int STATE_INTELLIGENT_UNLOCK_RESERVED = 3;
    public static final int STATE_INVALID = 0;
    public static final int STATE_INVISIABLE = 0;
    public static final int STATE_INVISIABLE_ALL = 0;
    public static final int STATE_INVISIABLE_ROTATION = 1;
    public static final int STATE_KEEPTEM_SUPPORT = 2;
    public static final int STATE_KEEPTEM_UNSUPPORT = 1;
    public static final int STATE_L1 = 1;
    public static final int STATE_L2 = 2;
    public static final int STATE_L3 = 3;
    public static final int STATE_L4 = 4;
    public static final int STATE_LOCK_CAR_RISE_WINDOW_CLOSE = 2;
    public static final int STATE_LOCK_CAR_RISE_WINDOW_OPEN = 1;
    public static final int STATE_LOCK_OFF_INVALID = 0;
    public static final int STATE_LOCK_OFF_LOCK_CLOSE = 2;
    public static final int STATE_LOCK_OFF_LOCK_OPEN = 1;
    public static final int STATE_LOCK_OFF_LOCK_RESERVED = 3;
    public static final int STATE_MICRO_SWITCH_CLOSE = 2;
    public static final int STATE_MICRO_SWITCH_OPEN = 1;
    public static final int STATE_MIRROR_FILP_SET_MODE_SELECTED = 2;
    public static final int STATE_MIRROR_FILP_SWITCH_MODE_SWITCH = 1;
    public static final int STATE_MIRROR_FOLLOWUP_SUPPORT = 2;
    public static final int STATE_MIRROR_FOLLOWUP_UNSUPPORT = 1;
    public static final int STATE_NO = 2;
    public static final int STATE_NONE = 0;
    public static final int STATE_NORMAL = 0;
    public static final int STATE_NOT_DOMAIN_CONTROLLER = 1;
    public static final int STATE_NO_ATMOSPHERE_LAMP = 1;
    public static final int STATE_NO_DC = 1;
    public static final int STATE_OBC_NON_ISOLATED = 0;
    public static final int STATE_OBC_ONE_WAY = 2;
    public static final int STATE_OBC_TWO_WAY = 1;
    public static final int STATE_ONE_ATMOSPHERE_LAMP = 2;
    public static final int STATE_ONE_CHANNEL_DISMISS = 2;
    public static final int STATE_OPEN = 1;
    public static final int STATE_OVER_SPEED_LOCK_CLOSE = 2;
    public static final int STATE_OVER_SPEED_LOCK_OPEN = 1;
    public static final int STATE_POWER_OFF_CLOSE = 2;
    public static final int STATE_POWER_OFF_OPEN = 1;
    public static final int STATE_POWER_ON_CLOSE = 2;
    public static final int STATE_POWER_ON_OPEN = 1;
    public static final int STATE_RAIN_AUTO_LOCK_CLOSE = 2;
    public static final int STATE_RAIN_AUTO_LOCK_OPEN = 1;
    public static final int STATE_REAR_PANNEL_TIME_LOCK_SUPPORT = 1;
    public static final int STATE_REAR_PANNEL_TIME_LOCK_UNSUPPORT = 0;
    public static final int STATE_REMOTE_CONTROL_CLOSE = 2;
    public static final int STATE_REMOTE_CONTROL_OPEN = 1;
    public static final int STATE_RESERVED = 3;
    public static final int STATE_SEAT_AUTO_RETURN_CLOSE = 2;
    public static final int STATE_SEAT_AUTO_RETURN_OPEN = 1;
    public static final int STATE_SIX_BRIGHTNESS = 2;
    public static final int STATE_STEER_POSITION_CLOSE = 2;
    public static final int STATE_STEER_POSITION_OPEN = 1;
    public static final int STATE_SUPPORT = 2;
    public static final int STATE_SUPPORT_1 = 1;
    public static final int STATE_SUPPORT_2 = 2;
    public static final int STATE_SUPPORT_3 = 3;
    public static final int STATE_SUPPORT_NFC = 1;
    public static final int STATE_SUPPORT_NFC_UWB = 2;
    public static final int STATE_TRUNK = 1;
    public static final int STATE_TRUNK_OPEN = 0;
    public static final int STATE_TWO_CHANNEL_SHOW = 1;
    public static final int STATE_UNSUPPORT = 1;
    public static final int STATE_UNSUPPORT_0 = 0;
    public static final int STATE_UNSUPPORT_NFC = 0;
    public static final int STATE_USHERLIGHT_SUPPORT = 2;
    public static final int STATE_USHERLIGHT_UNSUPPORT = 1;
    public static final int STATE_VISIABLE = 1;
    public static final int STATE_VISIABLE_ROTATION = 2;
    public static final int STATE_YES = 1;
    public static final int SUPPORT_ALL = 1;
    public static final int SUPPORT_NONE = 3;
    public static final int SUPPORT_ONLY_CODE = 2;
    protected static final String TAG = "UiStyleConfig";
    public static final int THROW_OUT_SKYLIGHT_AND_CANOPY_SUNSHADE = 6;
    public static final int THROW_OUT_SKYLIGHT_AND_SUNSHADE_CANOPY_SUNSHADE = 7;
    public static final int TWELVE_DIRECTION_ELECTRIC_ADJUSTMENT = 4;
    public static final int TYPE_INVALID = 0;
    public static final int TYPE_NOT_YU = 1;
    public static final int TYPE_NULL = 1;
    public static final int TYPE_OTHER = 6;
    public static final int TYPE_RESERVED = 3;
    public static final int TYPE_SEVEN = 4;
    public static final int TYPE_SINGLE = 3;
    public static final int TYPE_SUPPORT = 2;
    public static final int TYPE_THIRYONE = 5;
    public static final int TYPE_YU = 2;
    public static final int UNCONFIGURE_FULL_POWER_UNLOCK_FUNCTION = 1;
    public static final int VIA_DM_20 = 3;
    public static final int VIA_DM_25 = 2;
    public static final int VIA_EV = 1;
    public static final int VIA_INVALID = 0;
    public static final int WINDOW_LOCK = 1;
    private static BYDAutoStudy sUiStyleConfig;
    private UiStyleConfigManagerImpl mUiStyleConfigManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    BYDAutoStudy(Context context) {
        super(context.getApplicationContext());
        this.mUiStyleConfigManagerImpl = UiStyleConfigManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoStudy getInstance(Context context) {
        if (sUiStyleConfig == null) {
            sUiStyleConfig = new BYDAutoStudy(context.getApplicationContext());
        }
        return sUiStyleConfig;
    }

    public int getPreSaleModelSupport() {
        return this.mUiStyleConfigManagerImpl.getPreSaleModelSupport();
    }

    public int getCarNum() {
        return this.mUiStyleConfigManagerImpl.getCarNum();
    }

    @Deprecated
    public int getEnergyStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyStatus();
    }

    public int getDriveConfig() {
        return this.mUiStyleConfigManagerImpl.getDriveConfig();
    }

    public int getDMType() {
        return this.mUiStyleConfigManagerImpl.getDMType();
    }

    @Deprecated
    public int getAcType() {
        return this.mUiStyleConfigManagerImpl.getAcType();
    }

    public int getSupportRearAc() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAc();
    }

    public int getSupportRearAcSwitch() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcSwitch();
    }

    @Deprecated
    public int getRearAcMaxWindLevel() {
        return this.mUiStyleConfigManagerImpl.getRearAcMaxWindLevel();
    }

    public int getSupportRearAcKeyLock() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcKeyLock();
    }

    public int getSupportRearAcWindModeControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindModeControl();
    }

    public int getSupportRearAcAutoControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcAutoControl();
    }

    public int getSupportRearAcCompressor() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcCompressor();
    }

    public int getSupportRearAcTempertureControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcTempertureControl();
    }

    public int getSupportRearAcWindLevelControl() {
        return this.mUiStyleConfigManagerImpl.getSupportRearAcWindLevelControl();
    }

    @Deprecated
    public int getAcFaultNumShownState() {
        return this.mUiStyleConfigManagerImpl.getAcFaultNumShownState();
    }

    public int getSupportSingleDoubleTempArea() {
        return this.mUiStyleConfigManagerImpl.getSupportSingleDoubleTempArea();
    }

    @Deprecated
    public int getAcWindModeNum() {
        return this.mUiStyleConfigManagerImpl.getAcWindModeNum();
    }

    public int getSupportAcAutoKeyCanClose() {
        return this.mUiStyleConfigManagerImpl.getSupportAcAutoKeyCanClose();
    }

    @Deprecated
    public int getDefrostRearConfig() {
        return this.mUiStyleConfigManagerImpl.getDefrostRearConfig();
    }

    @Deprecated
    public int getAcWarmTypeOnlineState() {
        return this.mUiStyleConfigManagerImpl.getAcWarmTypeOnlineState();
    }

    @Deprecated
    public int getSupportSteeringWheelWarm() {
        return this.mUiStyleConfigManagerImpl.getSupportSteeringWheelWarm();
    }

    public int getSupportSeatMassage(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatMassage(i);
    }

    public int getHeadlampLevelConfig() {
        return this.mUiStyleConfigManagerImpl.getHeadlampLevelConfig();
    }

    public int[] getSupportSeatAdjust(int i) {
        return this.mUiStyleConfigManagerImpl.getSupportSeatAdjust(i);
    }

    public int getLeftPlatformConfigs() {
        return this.mUiStyleConfigManagerImpl.getLeftPlatformConfigs();
    }

    public int getSupportPM25Mode() {
        return this.mUiStyleConfigManagerImpl.getSupportPM25Mode();
    }

    public int getSupportNegativeIon() {
        return this.mUiStyleConfigManagerImpl.getSupportNegativeIon();
    }

    public int getSupportHighTemperatureSterilize() {
        return this.mUiStyleConfigManagerImpl.getSupportHighTemperatureSterilize();
    }

    public int getSupportFragrance() {
        return this.mUiStyleConfigManagerImpl.getSupportFragrance();
    }

    public int getSupportFragranceType(int i) {
        return this.mUiStyleConfigManagerImpl.getFragranceInstallState(i);
    }

    public int getCarType() {
        return this.mUiStyleConfigManagerImpl.getCarType();
    }

    public int getSupportLampSingnal() {
        return this.mUiStyleConfigManagerImpl.getSupportLampSingnal();
    }

    public int getSupportAFS() {
        return this.mUiStyleConfigManagerImpl.getSupportAFS();
    }

    public int getAutoReportContentSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getAutoReportContentSupport(i);
    }

    public int getHeadlampSupport() {
        return this.mUiStyleConfigManagerImpl.getHeadlampSupport();
    }

    public int resetHeadlamp(int i) {
        return this.mUiStyleConfigManagerImpl.resetHeadlamp(i);
    }

    public int getBackHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getBackHomeDelaySupport();
    }

    @Deprecated
    public int getLeaveHomeDelaySupport() {
        return this.mUiStyleConfigManagerImpl.getLeaveHomeDelaySupport();
    }

    public int resetBackAndLeaveDelaySupport(int i) {
        return this.mUiStyleConfigManagerImpl.resetBackAndLeaveDelaySupport(i);
    }

    @Deprecated
    public int backAndLeaveDelayFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.backAndLeaveDelayFactoryMode(i);
    }

    public int getUsherLightSupport() {
        return this.mUiStyleConfigManagerImpl.getUsherLightSupport();
    }

    @Deprecated
    public int getDayLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDayLightSupport();
    }

    public int resetDayLight(int i) {
        return this.mUiStyleConfigManagerImpl.resetDayLight(i);
    }

    @Deprecated
    public int dayLightFactory(int i) {
        return this.mUiStyleConfigManagerImpl.dayLightFactory(i);
    }

    public int getIndoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getIndoorLightSupport();
    }

    public int getDoorLightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorLightSupport();
    }

    public int getSupportReverseRadar() {
        return this.mUiStyleConfigManagerImpl.getSupportReverseRadar();
    }

    public int getIntelligentKeepTemSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentKeepTemSupport();
    }

    public int getChargeToaggleGuardSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeToaggleGuardSupport();
    }

    public int getChargeLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getChargeLimitSupport();
    }

    public int getOBCType() {
        return this.mUiStyleConfigManagerImpl.getOBCType();
    }

    @Deprecated
    public int getRearViewMirrorFlipSupport() {
        return this.mUiStyleConfigManagerImpl.getRearViewMirrorFlipSupport();
    }

    @Deprecated
    public int getAutoPowerOnStateSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOnStateSupport();
    }

    @Deprecated
    public int getAutoPowerOffSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPowerOffSupport();
    }

    public int getAutoExternalRearMirrorFollowUpSwitchSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoExternalRearMirrorFollowUpSwitchSupport();
    }

    @Deprecated
    public int getDriveSeatAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveSeatAutoReturnSupport();
    }

    @Deprecated
    public int getSteerPositionAutoReturnSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerPositionAutoReturnSupport();
    }

    public int getBrakePedalModeSupport() {
        return this.mUiStyleConfigManagerImpl.getBrakePedalModeSupport();
    }

    public int getComfortableParkingSupport() {
        return this.mUiStyleConfigManagerImpl.getComfortableParkingSupport();
    }

    public int getMotorPretensionerSeatbeltSupport() {
        return this.mUiStyleConfigManagerImpl.getMotorPretensionerSeatbeltSupport();
    }

    public int getSteerAssisSupport() {
        return this.mUiStyleConfigManagerImpl.getSteerAssisSupport();
    }

    public int getAutoWiperSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoWiperSupport();
    }

    public int getDynamicSuspensionModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDynamicSuspensionModeSupport();
    }

    public int getLandAdvanceAtSupport() {
        return this.mUiStyleConfigManagerImpl.getLandAdvanceAtSupport();
    }

    @Deprecated
    public int getRemoteKeyStartAcTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteKeyStartAcTimeSupport();
    }

    @Deprecated
    public int getACAutoAirSupport() {
        return this.mUiStyleConfigManagerImpl.getACAutoAirSupport();
    }

    public int getACBTWindSupport() {
        return this.mUiStyleConfigManagerImpl.getACBTWindSupport();
    }

    public int getACPauseCycleSupport() {
        return this.mUiStyleConfigManagerImpl.getACPauseCycleSupport();
    }

    public int getAutoPurificationSupport() {
        return this.mUiStyleConfigManagerImpl.getAutoPurificationSupport();
    }

    public int getPM25PowerSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25PowerSupport();
    }

    @Deprecated
    public int getPM25SwitchCheckSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25SwitchCheckSupport();
    }

    @Deprecated
    public int getPM25TimeCkeckSupport() {
        return this.mUiStyleConfigManagerImpl.getPM25TimeCkeckSupport();
    }

    @Deprecated
    public int getACTunnelCycleSupport() {
        return this.mUiStyleConfigManagerImpl.getACTunnelCycleSupport();
    }

    public int getHudShowParamsSupport() {
        return this.mUiStyleConfigManagerImpl.getHudShowParamsSupport();
    }

    public int getRearPanelLockTimeSupport() {
        return this.mUiStyleConfigManagerImpl.getRearPanelLockTimeSupport();
    }

    public int getAcWarmSupport() {
        return this.mUiStyleConfigManagerImpl.getAcWarmSupport();
    }

    public int getIntelligentAcAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentAcAreaSupport();
    }

    public int getDoorHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorHeightSupport();
    }

    public int getSupportIntelligentNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigation();
    }

    public int getSupportIntelligentNavigationBy432() {
        return this.mUiStyleConfigManagerImpl.getSupportIntelligentNavigationBy432();
    }

    public int getSupportShakeAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportShakeAlertR();
    }

    public int getSupportTrafficSignsRecognition() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignsRecognition();
    }

    public int getSupportPredictCrashAlertMode() {
        return this.mUiStyleConfigManagerImpl.getSupportPredictCrashAlertMode();
    }

    public int getSupportAiEmergencyBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportAiEmergencyBrake();
    }

    public int getSupportAiCare() {
        return this.mUiStyleConfigManagerImpl.getSupportAiCare();
    }

    public int getSupportMistakeGas() {
        return this.mUiStyleConfigManagerImpl.getSupportMistakeGas();
    }

    public int getSupportAutoParking() {
        return this.mUiStyleConfigManagerImpl.getSupportAutoParking();
    }

    public int getSupportBlindSpotAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportBlindSpotAssist();
    }

    public int getSupportBlindSpotAssistBy418() {
        return this.mUiStyleConfigManagerImpl.getSupportBlindSpotAssistBy418();
    }

    public int getSupportRearCrossBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCrossBrake();
    }

    public int getSupportAiDistanceLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiDistanceLight();
    }

    public int getSupportAiMatrixLight() {
        return this.mUiStyleConfigManagerImpl.getSupportAiMatrixLight();
    }

    public int getSupportPanoramaImage() {
        return this.mUiStyleConfigManagerImpl.getSupportPanoramaImage();
    }

    public int getSupportESP() {
        return this.mUiStyleConfigManagerImpl.getSupportESP();
    }

    public int getSupportDigitalKey() {
        return this.mUiStyleConfigManagerImpl.getSupportDigitalKey();
    }

    public int getSupportShowCarModel() {
        return this.mUiStyleConfigManagerImpl.getSupportShowCarModel();
    }

    @Deprecated
    public int getRemoteControlLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteControlLockWindowSupport();
    }

    @Deprecated
    public int getRemoteControlUnlockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRemoteControlUnlockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchLockWindowSupport();
    }

    @Deprecated
    public int getMicroSwitchUnlockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getMicroSwitchUnlockWindowSupport();
    }

    @Deprecated
    public int getLockCarRiseWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getLockCarRiseWindowSupport();
    }

    @Deprecated
    public int getOverspeedLockSupport() {
        return this.mUiStyleConfigManagerImpl.getOverspeedLockSupport();
    }

    @Deprecated
    public int getRainAutoLockWindowSupport() {
        return this.mUiStyleConfigManagerImpl.getRainAutoLockWindowSupport();
    }

    @Deprecated
    public int getDoorknobAutoShrinkSupport() {
        return this.mUiStyleConfigManagerImpl.getDoorknobAutoShrinkSupport();
    }

    @Deprecated
    public int getLockOffSupport() {
        return this.mUiStyleConfigManagerImpl.getLockOffSupport();
    }

    public int getDigtalKeySupport() {
        return this.mUiStyleConfigManagerImpl.getDigtalKeySupport();
    }

    @Deprecated
    public int getIntelligentUnlockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentUnlockSupport();
    }

    @Deprecated
    public int getIntelligentLockSupport() {
        return this.mUiStyleConfigManagerImpl.getIntelligentLockSupport();
    }

    public int getAppUseLimitSupport() {
        return this.mUiStyleConfigManagerImpl.getAppUseLimitSupport();
    }

    public int getRemindSpeedSupport() {
        return this.mUiStyleConfigManagerImpl.getRemindSpeedSupport();
    }

    public int getReportVolumeSupport() {
        return this.mUiStyleConfigManagerImpl.getReportVolumeSupport();
    }

    public int getEngineSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getEngineSoundSupport();
    }

    public int getCarTipsSoundSupport() {
        return this.mUiStyleConfigManagerImpl.getCarTipsSoundSupport();
    }

    public int getReportContentSetSupport() {
        return this.mUiStyleConfigManagerImpl.getReportContentSetSupport();
    }

    public int getReportItemsSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getReportItemsSetSupport(i);
    }

    public int getSeatSetSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatSetSupport(i);
    }

    public int getSteeringWheelSupport() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelSupport();
    }

    public int getRearviewMirrorSupport() {
        return this.mUiStyleConfigManagerImpl.getRearviewMirrorSupport();
    }

    public int getSeatTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHorizatialSupport(i);
    }

    public int getSeatTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatTurnHeightSupport(i);
    }

    public int getHeadrestTurnHorizatialSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHorizatialSupport(i);
    }

    public int getSeatbackrestSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getSeatbackrestSupport(i);
    }

    public int getHeadrestTurnHeightSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getHeadrestTurnHeightSupport(i);
    }

    public int getRefrigeratorModeSupport() {
        return this.mUiStyleConfigManagerImpl.getRefrigeratorModeSupport();
    }

    @Deprecated
    public int getSupportRemenberPowerOff() {
        return this.mUiStyleConfigManagerImpl.getSupportRemenberPowerOff();
    }

    @Deprecated
    public int getDisinfSupport() {
        return this.mUiStyleConfigManagerImpl.getDisinfSupport();
    }

    @Deprecated
    public int getTrayControlSupport() {
        return this.mUiStyleConfigManagerImpl.getTrayControlSupport();
    }

    public int getCushionSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCushionSupport(i);
    }

    public int getCaliperSplintSupport(int i) {
        return this.mUiStyleConfigManagerImpl.getCaliperSplintSupport(i);
    }

    @Deprecated
    public int getAmbientLightColorSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightColorSupport();
    }

    @Deprecated
    public int getAmbientLightSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientLightSupport();
    }

    @Deprecated
    public int getAmbientAreaSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientAreaSupport();
    }

    @Deprecated
    public int getAmbientMusicModeSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientMusicModeSupport();
    }

    @Deprecated
    public int getAmbientColorsSupport() {
        return this.mUiStyleConfigManagerImpl.getAmbientColorsSupport();
    }

    public int getHasPredictCrashAlertMode() {
        return this.mUiStyleConfigManagerImpl.getHasPredictCrashAlertMode();
    }

    public int getAmplifierConfiguration() {
        return this.mUiStyleConfigManagerImpl.getAmplifierConfiguration();
    }

    public int getPABSwitchState() {
        return this.mUiStyleConfigManagerImpl.getPabSwitchState();
    }

    public int getFrontLightDimmingCtrlConfigBy3AC() {
        return this.mUiStyleConfigManagerImpl.getFrontLightDimmingCtrlConfigBy3ac();
    }

    public int getFrontLightDimmingCtrlConfigBy3AD() {
        return this.mUiStyleConfigManagerImpl.getFrontLightDimmingCtrlConfigBy3ad();
    }

    public int getTSRLitSpdChgSwitchState() {
        return this.mUiStyleConfigManagerImpl.getTsrLitSpdChgSwitchState();
    }

    public int resetAmbientSettings(int i) {
        return this.mUiStyleConfigManagerImpl.resetAmbientSettings(i);
    }

    @Deprecated
    public int ambientFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.ambientFactoryMode(i);
    }

    @Deprecated
    public int getHudParamsStateSupport() {
        return this.mUiStyleConfigManagerImpl.getHudParamsStateSupport();
    }

    public int getHudHeightAdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudHeightAdjustSupport();
    }

    public int getHudLightSdjustSupport() {
        return this.mUiStyleConfigManagerImpl.getHudLightSdjustSupport();
    }

    public int getInstrumentThemeSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentThemeSupport();
    }

    public int getInstrumentHeightSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentHeightSupport();
    }

    public int getInstrumentContentSupport() {
        return this.mUiStyleConfigManagerImpl.getInstrumentContentSupport();
    }

    public int getDischargeSupport() {
        return this.mUiStyleConfigManagerImpl.getDischargeSupport();
    }

    public int getDriveDistanceShowStandardSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowStandardSupport();
    }

    public int getEPedalStateSupport() {
        return this.mUiStyleConfigManagerImpl.getEPedalStateSupport();
    }

    public int getDriveDistanceShowModeSupport() {
        return this.mUiStyleConfigManagerImpl.getDriveDistanceShowModeSupport();
    }

    @Deprecated
    public int getMainSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getMainSeatRememberSupport();
    }

    public int getSecondSeatRememberSupport() {
        return this.mUiStyleConfigManagerImpl.getSecondSeatRememberSupport();
    }

    public int getGuestLeftSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestLeftSeatRememeSupport();
    }

    public int getGuestRightSeatRememeSupport() {
        return this.mUiStyleConfigManagerImpl.getGuestRightSeatRememeSupport();
    }

    @Deprecated
    public int resetDoorAndIndoor(int i) {
        return this.mUiStyleConfigManagerImpl.resetDoorAndIndoor(i);
    }

    public int getAdaptiveHeadlightsSupport() {
        return this.mUiStyleConfigManagerImpl.getAdaptiveHeadlightsSupport();
    }

    @Deprecated
    public int resetAutoPurificationFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.resetAutoPurificationFactoryMode(i);
    }

    @Deprecated
    public int setAcWarmFactoryMode(int i) {
        return this.mUiStyleConfigManagerImpl.setAcWarmFactoryMode(i);
    }

    public int getMoonRoofConfig() {
        return this.mUiStyleConfigManagerImpl.getMoonRoofConfig();
    }

    public int getSupportHighwayNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportHighwayNavigation();
    }

    public int getSupportUrbanNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportUrbanNavigation();
    }

    public int getSupportEndToEndNavigation() {
        return this.mUiStyleConfigManagerImpl.getSupportEndToEndNavigation();
    }

    public int getSupportLaneChangeAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneChangeAssist();
    }

    public int getSupportLaneDepartureWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureWarning();
    }

    public int getSupportLaneKeep() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneKeep();
    }

    public int getSupportLaneDepartureStop() {
        return this.mUiStyleConfigManagerImpl.getSupportLaneDepartureStop();
    }

    public int getSupportEmergencyLaneKeepAssist() {
        return this.mUiStyleConfigManagerImpl.getSupportEmergencyLaneKeepAssist();
    }

    public int getSupportTrafficSignRecogniton() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficSignRecogniton();
    }

    public int getSupportIntellignetSpeedLimitAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitAlert();
    }

    public int getSupportIntellignetSpeedLimitControl() {
        return this.mUiStyleConfigManagerImpl.getSupportIntellignetSpeedLimitControl();
    }

    public int getSupportTrafficLightAttention() {
        return this.mUiStyleConfigManagerImpl.getSupportTrafficLightAttention();
    }

    public int getSupportDoorOpenWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportDoorOpenWarning();
    }

    public int getSupportRearCollisionWarning() {
        return this.mUiStyleConfigManagerImpl.getSupportRearCollisionWarning();
    }

    public int getSupportRearTrafficCrossingAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportRearTrafficCrossingAlert();
    }

    public int getSupportFrontCrossingTrafficAlert() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficAlert();
    }

    public int getSupportFrontCrossingTrafficBrake() {
        return this.mUiStyleConfigManagerImpl.getSupportFrontCrossingTrafficBrake();
    }

    public int getElectroniChildLockConfig() {
        return this.mUiStyleConfigManagerImpl.getElectroniChildLockConfig();
    }

    public int getWindowAntiPinchConfig() {
        return this.mUiStyleConfigManagerImpl.getWindowAntiPinchConfig();
    }

    public int getComfortableSpeedUp() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedUp();
    }

    public int getComfortableSpeedDown() {
        return this.mUiStyleConfigManagerImpl.getComfortableSpeedDown();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mUiStyleConfigManagerImpl.get(iArr, cls);
    }

    public int getSteeringWheelHeatingConfiguration() {
        return this.mUiStyleConfigManagerImpl.getSteeringWheelHeatingConfiguration();
    }

    public int restoreFactorySettings() {
        return this.mUiStyleConfigManagerImpl.restoreFactorySettings();
    }

    public int getValetParking() {
        return this.mUiStyleConfigManagerImpl.getValetParking();
    }

    public int getMemoryParking() {
        return this.mUiStyleConfigManagerImpl.getMemoryParking();
    }

    public int getPassengerSeatMemoryFunctionCan() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatMemoryFunctionCan();
    }

    public int getTirePressureInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getTirePressureInterfaceStatus();
    }

    public int getFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageEnergyConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAverageEnergyConsumptionInterfaceStatus();
    }

    public int getCumulativeAverageFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAverageFuelConsumptionInterfaceStatus();
    }

    public int getCumulativeAveragePowerConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getCumulativeAveragePowerConsumptionInterfaceStatus();
    }

    public int getTheLast50KMAverageEnergyConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAverageEnergyConsumptionInterfaceState();
    }

    public int getTheLast50KMAverageFuelConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAverageFuelConsumptionInterfaceState();
    }

    public int getTheLast50KMAveragePowerConsumptionInterfaceState() {
        return this.mUiStyleConfigManagerImpl.getTheLast50KMAveragePowerConsumptionInterfaceState();
    }

    public int getInstantaneousFuelConsumptionInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getInstantaneousFuelConsumptionInterfaceStatus();
    }

    public int getExternalChargingPowerInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getExternalChargingPowerInterfaceStatus();
    }

    public int getEnergyFlowDiagramInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getEnergyFlowDiagramInterfaceStatus();
    }

    public int getDrivingRangeInterfaceStatusForFuel() {
        return this.mUiStyleConfigManagerImpl.getDrivingRangeInterfaceStatusForFuel();
    }

    public int getAverageSpeedInterfaceStatus() {
        return this.mUiStyleConfigManagerImpl.getAverageSpeedInterfaceStatus();
    }

    public int getDriveTimeInterfaceStatusForSingleTrip() {
        return this.mUiStyleConfigManagerImpl.getDriveTimeInterfaceStatusForSingleTrip();
    }

    public int getRightRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatReclineConfiguration();
    }

    public int getLeftRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatReclineConfiguration();
    }

    public int getRearSeatReclineConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRearSeatReclineConfiguration();
    }

    public int getSupportExtreme() {
        return this.mUiStyleConfigManagerImpl.getSupportExtreme();
    }

    public int getSupportSmartChargingPHEV() {
        return this.mUiStyleConfigManagerImpl.getSupportSmartChargingPHEV();
    }

    public int getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriverSeatAdjustmentConfigurationByLeftBodyCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriveElectricLegSupportAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriveElectricHeadrestAdjustmentByLeftBodyCtrl();
    }

    public int getMainDriverSeatAdjustmentConfigurationByGCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriverSeatAdjustmentConfigurationByGCtrl();
    }

    public int getMainDriveElectricLegSupportAdjustmentByGCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriveElectricLegSupportAdjustmentByGCtrl();
    }

    public int getMainDriveElectricHeadrestAdjustmentByGCtrl() {
        return this.mUiStyleConfigManagerImpl.getMainDriveElectricHeadrestAdjustmentByGCtrl();
    }

    public int getPassengerSeatMemoryFunctionCanByECU() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatMemoryFunctionCanByECU();
    }

    public int getPassengerSeatElectricAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatElectricAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatLegSupportAdjustmentConfigurationByECU();
    }

    public int getPassengerSeatHeadrestConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatHeadrestConfigurationByECU();
    }

    public int getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatLegSupportAdjustmentConfigurationByGCtrl();
    }

    public int getPassengerSeatHeadrestConfigurationByGCtrl() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatHeadrestConfigurationByGCtrl();
    }

    public int getRearLeftLevelAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftBackrestAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftBackrestAdjustmentConfigurationByECU();
    }

    public int getRearLeftLegSupportAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearLeftBidetAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftBidetAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeightAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearLeftHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearLeftMemorySwitchConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearLeftMemorySwitchConfigurationByECU();
    }

    public int getLeftRearSeatReclineConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatReclineConfigurationByECU();
    }

    public int getLeftRearSeatBackAdjustmentConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatBackAdjustmentConfiguration();
    }

    public int getLeftRearSeatHorizontalAdjustmentConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getLeftRearSeatLegBracket() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatLegBracket();
    }

    public int getLeftRearMemoryConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRearMemoryConfiguration();
    }

    public int getRearRightLevelAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightBackrestAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightBackrestAdjustmentConfigurationByECU();
    }

    public int getRearRightLegSupportAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightLegSupportAdjustmentConfigurationByECU();
    }

    public int getRearRightBidetAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightBidetAdjustmentConfigurationByECU();
    }

    public int getRearRightHeightAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestLevelAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightHeadrestLevelAdjustmentConfigurationByECU();
    }

    public int getRearRightHeadrestHeightAdjustmentConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightHeadrestHeightAdjustmentConfigurationByECU();
    }

    public int getRearRightMemorySwitchConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRearRightMemorySwitchConfigurationByECU();
    }

    public int getRightRearSeatReclineConfigurationByEUC() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatReclineConfigurationByEUC();
    }

    public int getRightRearSeatBackAdjustmentConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatBackAdjustmentConfiguration();
    }

    public int getRightRearSeatHorizontalAdjustmentConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatHorizontalAdjustmentConfiguration();
    }

    public int getRightRearSeatLegBracket() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatLegBracket();
    }

    public int getRightRearMemoryConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRearMemoryConfiguration();
    }

    public int getADASSystemState() {
        return this.mUiStyleConfigManagerImpl.getADASSystemState();
    }

    public int getCarPlanSupport() {
        return this.mUiStyleConfigManagerImpl.getCarPlanSupport();
    }

    public int getNoSenseChargeSupport() {
        return this.mUiStyleConfigManagerImpl.getNoSenseChargeSupport();
    }

    public int getElectricFrontHatchConfiguration() {
        return this.mUiStyleConfigManagerImpl.getElectricFrontHatchConfiguration();
    }

    public int getChargePreheatSupport() {
        return this.mUiStyleConfigManagerImpl.getChargePreheatSupport();
    }

    public int getDMSAssembleStatus() {
        return this.mUiStyleConfigManagerImpl.getDMSAssembleStatus();
    }

    public int getDrivingVoiceBroadcastSwitchConfig() {
        return this.mUiStyleConfigManagerImpl.getDrivingVoiceBroadcastSwitchConfig();
    }

    public int getRestModeConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRestModeConfiguration();
    }

    public int getLeftRESConfiguration() {
        return this.mUiStyleConfigManagerImpl.getLeftRESConfiguration();
    }

    public int getRightRESConfiguration() {
        return this.mUiStyleConfigManagerImpl.getRightRESConfiguration();
    }

    public int getHUDModeConfiguration() {
        return this.mUiStyleConfigManagerImpl.getHUDModeConfiguration();
    }

    public int getIntelligentScreenCastingSwitchConfiguration() {
        return this.mUiStyleConfigManagerImpl.getIntelligentScreenCastingSwitchConfiguration();
    }

    public int getSupportFRElectricAirOutlet() {
        return this.mUiStyleConfigManagerImpl.getSupportFRElectricAirOutlet();
    }

    public int getLeftRearMassageChairConfigurationForECU() {
        return this.mUiStyleConfigManagerImpl.getLeftRearMassageChairConfigurationForECU();
    }

    public int getRightRearMassageChairConfigurationForECU() {
        return this.mUiStyleConfigManagerImpl.getRightRearMassageChairConfigurationForECU();
    }

    public int getElectronicParkingBrakeConfigutarion() {
        return this.mUiStyleConfigManagerImpl.getElectronicParkingBrakeConfigutarion();
    }

    public int getTrunkConfigutarion() {
        return this.mUiStyleConfigManagerImpl.getTrunkConfigutarion();
    }

    public int getPanoramicSunroofConfigutarion() {
        return this.mUiStyleConfigManagerImpl.getPanoramicSunroofConfigutarion();
    }

    public int getDriverSeatHotStoneMassageConfigurationByLeftCtrl() {
        return this.mUiStyleConfigManagerImpl.getDriverSeatHotStoneMassageConfigurationByLeftCtrl();
    }

    public int getDriverSeatHotStoneMassageConfigurationByUniversalCtrl() {
        return this.mUiStyleConfigManagerImpl.getDriverSeatHotStoneMassageConfigurationByUniversalCtrl();
    }

    public int getPassengerSeatHotStoneMassageConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getPassengerSeatHotStoneMassageConfigurationByECU();
    }

    public int getLeftRearSeatHotStoneMassageConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getLeftRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRightRearSeatHotStoneMassageConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getRightRearSeatHotStoneMassageConfigurationByECU();
    }

    public int getRuningCloseDoorSupport() {
        return this.mUiStyleConfigManagerImpl.getRuningCloseDoorSupport();
    }

    public int getCVCConfig() {
        return this.mUiStyleConfigManagerImpl.getCvcConfig();
    }

    public int getDiSusShowStateByCan() {
        return this.mUiStyleConfigManagerImpl.getDiSusShowStateByCan();
    }

    public int getDiSusShowStateByCanFD() {
        return this.mUiStyleConfigManagerImpl.getDiSusShowStateByCanFd();
    }

    public int getWarningAuxiliarySwitchSetting() {
        return this.mUiStyleConfigManagerImpl.getWarningAuxiliarySwitchSetting();
    }

    public int getWelcomeCeremonySwitchSetting() {
        return this.mUiStyleConfigManagerImpl.getWelcomeCeremonySwitchSetting();
    }

    public int getAmbientModeSwitchSettings() {
        return this.mUiStyleConfigManagerImpl.getAmbientModeSwitchSettings();
    }

    public int getStarTopSwitchSetting() {
        return this.mUiStyleConfigManagerImpl.getStarTopSwitchSetting();
    }

    public int getComfortableParkingBy37F() {
        return this.mUiStyleConfigManagerImpl.getComfortableParkingBy37F();
    }

    public int getLowSpeedEmergencyBrakingConfig() {
        return this.mUiStyleConfigManagerImpl.getLowSpeedEmergencyBrakingConfig();
    }

    public int getSideDistanceWarningConfig() {
        return this.mUiStyleConfigManagerImpl.getSideDistanceWarningConfig();
    }

    public int getV2XConfig() {
        return this.mUiStyleConfigManagerImpl.getV2xConfig();
    }

    public int getTrailerModeConfig() {
        return this.mUiStyleConfigManagerImpl.getTrailerModeConfig();
    }

    public int getFoldDownTheSecondRowOfSeatsConfig() {
        return this.mUiStyleConfigManagerImpl.getFoldDownTheSecondRowOfSeatsConfig();
    }

    public int getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getBackRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getLegRestAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getPotAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeadrestLevelAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeadrestHightAdjustmentSwitchConfigOnTheLeftSideOfTheThirdRow();
    }

    public int getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat() {
        return this.mUiStyleConfigManagerImpl.getOneButtonDownConfigurationOfTheThirdRowLeftRearSeat();
    }

    public int getOneButtonDownSwitchDisplayForTheThirdRowOfSeats() {
        return this.mUiStyleConfigManagerImpl.getOneButtonDownSwitchDisplayForTheThirdRowOfSeats();
    }

    public int getAllRearSeatOneButtonDownSwitchDisplayConfig() {
        return this.mUiStyleConfigManagerImpl.getAllRearSeatOneButtonDownSwitchDisplayConfig();
    }

    public int getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getBackRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getLegRestAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getPotAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeadrestLevelAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow() {
        return this.mUiStyleConfigManagerImpl.getHeadrestHeightAdjustmentSwitchConfigOnTheRightSideOfTheThirdRow();
    }

    public int getOneButtonDownConfigurationOfTheThirdRowRightRearSeat() {
        return this.mUiStyleConfigManagerImpl.getOneButtonDownConfigurationOfTheThirdRowRightRearSeat();
    }

    public int getIntelligentSpeedLimitMapVersionDisplay() {
        return this.mUiStyleConfigManagerImpl.getIntelligentSpeedLimitMapVersionDisplay();
    }

    public int getDriverSeatHotStoneMassageConfigurationByECU() {
        return this.mUiStyleConfigManagerImpl.getDriverSeatHotStoneMassageConfigurationByEcu();
    }

    public int getCameraEerRomDeviceAddress() {
        return this.mUiStyleConfigManagerImpl.getCameraEerRomDeviceAddress();
    }

    public int getCameraSupplierAndTestBoardInfo() {
        return this.mUiStyleConfigManagerImpl.getCameraSupplierAndTestBoardInfo();
    }

    public int getCameraVideoResolution() {
        return this.mUiStyleConfigManagerImpl.getCameraVideoResolution();
    }

    public int getCameraVideoFrameRate() {
        return this.mUiStyleConfigManagerImpl.getCameraVideoFrameRate();
    }

    public int getCameraVideoFormat() {
        return this.mUiStyleConfigManagerImpl.getCameraVideoFormat();
    }

    public int getCameraLensID() {
        return this.mUiStyleConfigManagerImpl.getCameraLensId();
    }

    public int getCameraSerializerID() {
        return this.mUiStyleConfigManagerImpl.getCameraSerializerId();
    }

    public int getCameraISPID() {
        return this.mUiStyleConfigManagerImpl.getCameraIspId();
    }

    public int getSupportAVH() {
        return this.mUiStyleConfigManagerImpl.getSupportAvh();
    }

    public int getSupportRearControlPanelSign() {
        return this.mUiStyleConfigManagerImpl.getSupportRearControlPanelSign();
    }

    public int getSupportDimmingCanopyConfiguration() {
        return this.mUiStyleConfigManagerImpl.getSupportDimmingCanopyConfiguration();
    }

    public int getBSISSupport() {
        return this.mUiStyleConfigManagerImpl.getBsisSupportR();
    }

    public int getMOISSupport() {
        return this.mUiStyleConfigManagerImpl.getMoisSupportR();
    }

    public int getFeaturesState() {
        return this.mUiStyleConfigManagerImpl.getFeaturesStateR();
    }

    public int getSupportAlarmMode() {
        return this.mUiStyleConfigManagerImpl.getSupportShakeAlert();
    }

    public int getCpdSupport() {
        return this.mUiStyleConfigManagerImpl.getCpdSupportR();
    }

    public int getSupportSmartChargingPHEVPhaseII() {
        return this.mUiStyleConfigManagerImpl.getSupportSmartChargingPhevPhase2R();
    }

    public int getDynamicSuspensionModeByCan() {
        return this.mUiStyleConfigManagerImpl.getDynamicSuspensionModeByCanR();
    }

    public int getDynamicSuspensionModeByCanFD() {
        return this.mUiStyleConfigManagerImpl.getDynamicSuspensionModeByCanFdR();
    }

    public int getSupportManualModel() {
        return this.mUiStyleConfigManagerImpl.getSupportManualModelR();
    }

    public int getTailgateTypeMarking() {
        return this.mUiStyleConfigManagerImpl.getTailgateTypeMarking();
    }

    public int getSupportTSRFlow() {
        return this.mUiStyleConfigManagerImpl.getSupportTsrFlow();
    }

    public int getSupportDoubleConfirm() {
        return this.mUiStyleConfigManagerImpl.getSupportDoubleConfirm();
    }

    public int getSupportSLW() {
        return this.mUiStyleConfigManagerImpl.getSupportSlw();
    }

    public int getSupportAcChargingLimit() {
        return this.mUiStyleConfigManagerImpl.getSupportAcChargingLimit();
    }

    public int getSupportRoadMode() {
        return this.mUiStyleConfigManagerImpl.getSupportRoadMode();
    }

    public void registerConnectListener(BYDAutoStudyListener bYDAutoStudyListener) {
        this.mUiStyleConfigManagerImpl.registerConnectListener(bYDAutoStudyListener);
    }

    public int getSupportInsUiMode() {
        return this.mUiStyleConfigManagerImpl.getSupportInsUiMode();
    }

    public int getSupportInsUiSize() {
        return this.mUiStyleConfigManagerImpl.getSupportInsUiSize();
    }

    public int getSupportInsUiShape() {
        return this.mUiStyleConfigManagerImpl.getSupportInsUiShape();
    }

    public int setRncState(int i) {
        return this.mUiStyleConfigManagerImpl.setRncState(i);
    }

    public int getCpdSwMemStatus() {
        return this.mUiStyleConfigManagerImpl.getCpdSwMemStatus();
    }

    public int getSupportRnc() {
        return this.mUiStyleConfigManagerImpl.getSupportRnc();
    }
}
