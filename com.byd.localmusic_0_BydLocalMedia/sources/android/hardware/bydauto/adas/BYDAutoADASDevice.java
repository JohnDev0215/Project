package android.hardware.bydauto.adas;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.adas.tsimpl.AdasManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoADASDevice extends AbsBYDAutoDevice {
    public static final int ABS_TCS_ESP_ACC_AEB_ACTIVE = 9;
    public static final int ADAS_COMMAND_BUSY = -2147482647;
    public static final int ADAS_COMMAND_FAILED = -2147482648;
    public static final int ADAS_COMMAND_INVALID_VALUE = -2147482645;
    public static final int ADAS_COMMAND_SUCCESS = 0;
    public static final int ADAS_COMMAND_TIMEOUT = -2147482646;
    public static final int ADAS_INVALID = 0;
    protected static final int ADAS_SLA_STATE_DEFECT = 4;
    protected static final int ADAS_SLA_STATE_FUSION_MODE = 1;
    protected static final int ADAS_SLA_STATE_NV_ONLY_MODE = 3;
    protected static final int ADAS_SLA_STATE_OFF = 0;
    protected static final int ADAS_SLA_STATE_VISION_MODE = 2;
    public static final int ADAS_VALID = 1;
    public static final int ANY_DOOR_OPEN = 3;
    public static final int APS_FAILURE = 5;
    public static final int APS_TIME_OUT = 16;
    public static final int AUTO_ESP_STATE_OFF = 1;
    public static final int AUTO_ESP_STATE_ON = 0;
    protected static final int AUTO_HOLD_STATE1 = 0;
    protected static final int AUTO_HOLD_STATE2 = 1;
    protected static final int AUTO_HOLD_STATE3 = 2;
    protected static final int AUTO_HOLD_STATE4 = 3;
    public static final int AUTO_IBOOSTER_STATE_COMFORTABLE = 2;
    public static final int AUTO_IBOOSTER_STATE_SPORT = 1;
    public static final int AUTO_IBOOSTER_STATE_STANDARD = 0;
    public static final int AVM_APA_FAULT = 3;
    public static final int AVM_FUNCTION_OFF = 1;
    public static final int AVM_FUNCTION_ON = 2;
    public static final int BLUETOOTH_DISCONNECT = 7;
    public static final int BLUETOOTH_DISCONNECT_TIMEOUT = 19;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE = 2;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE_GET = 0;
    public static final int BRAKE_FOOT_SENSE_COMFORTABLE_SET = 2;
    public static final int BRAKE_FOOT_SENSE_INVALID = 3;
    public static final int BRAKE_FOOT_SENSE_INVALID_SET = 0;
    public static final int BRAKE_FOOT_SENSE_SPORT = 1;
    public static final int BRAKE_FOOT_SENSE_STANDARD = 0;
    public static final int BRAKE_FOOT_SENSE_STANDARD_GET = 1;
    public static final int BRAKE_FOOT_SENSE_STANDARD_SET = 1;
    public static final int BRAKING_PEDAL = 4;
    public static final int BSD_STATIC_CALIBRATION_INVALID = 0;
    public static final int BSD_STATIC_CALIBRATION_START = 1;
    public static final int CHARGING_GUN_ACTIVE = 12;
    public static final int CHOOSE_PARK_MODE = 7;
    public static final int CLEANING_CAMERA = 9;
    public static final int CLEAR_AHEAD = 5;
    public static final int CLOSE_HOOD = 8;
    public static final int CLOSE_TRUNK = 9;
    public static final int CONFIRM_PARKING_OUT_DIRECTION = 3;
    public static final int CONNECT_BLUETOOTH_TO_CONTROL = 8;
    public static final int CST_STATE_ACTIVE_GET = 2;
    public static final int CST_STATE_DISABLED_GET = 0;
    public static final int CST_STATE_FAILURE_GET = 3;
    public static final int CST_STATE_STANDBY_GET = 1;
    protected static final int CST_SWITCH_INVALID_SET = 0;
    protected static final int CST_SWITCH_OFF_SET = 2;
    protected static final int CST_SWITCH_ON_SET = 1;
    public static final int CURRENT_STEP_NUMBER_OVER_THRESHOLD = 14;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DRIVER_OVERRIDE = 11;
    public static final int EPB_ACTIVE = 10;
    public static final int EPB_FAILURE = 20;
    public static final int EPS_FAILURE = 1;
    public static final int ESC_FAILURE = 2;
    public static final int ESP_KEY_NO_ACTION = 0;
    public static final int ESP_KEY_PRESSED = 1;
    public static final int ESP_OFFLINE = 3;
    public static final int ESP_WITHOUT_HARD_SWITCH = 2;
    public static final int ESP_WITH_HARD_SWITCH = 1;
    public static final String FEATURE_ADAS_AVH = "AVH";
    public static final String FEATURE_ADAS_BRAKE_FOOT_SENSE = "BrakeFootSense";
    public static final String FEATURE_ADAS_CST = "CST";
    public static final String FEATURE_ADAS_ESP = "ESP";
    public static final String FEATURE_ADAS_HDC = "HDC";
    public static final String FEATURE_ADAS_LDW = "LDW";
    public static final String FEATURE_ADAS_LKS = "LKS";
    public static final String FEATURE_ADAS_TJA = "TJA";
    public static final String FEATURE_AEB = "AutomaticEmergencyBrake";
    public static final String FEATURE_BSD = "BlindSpotDetection";
    public static final String FEATURE_HMA = "IntelligentFarAndNearLight";
    public static final String FEATURE_LDSW = "LaneDepartureWarning";
    public static final String FEATURE_LKS_MODE = "LaneKeepingAssistMode";
    public static final String FEATURE_LKS_SENSITIVITY = "LaneKeepingAssistSensitivity";
    public static final String FEATURE_PCW = "PredictionCollisionWarning";
    public static final String FEATURE_SLA = "TrafficSignRecognition";
    public static final int FINISH_PARKING = 2;
    public static final int FUNCATION_DEFECT = 2;
    public static final int FUNCATION_OFF = 0;
    public static final int FUNCATION_ON = 1;
    public static final int HDC_FUNCTION_INVALID = 0;
    public static final int HDC_FUNCTION_OFF = 2;
    public static final int HDC_FUNCTION_ON = 1;
    protected static final int HDC_STATE_OFFLINE = 3;
    protected static final int HDC_STATE_OFF_NO_LAMP = 0;
    protected static final int HDC_STATE_ON_LAMP_FLASH = 2;
    protected static final int HDC_STATE_ON_LAMP_ON = 1;
    public static final int HMA_STATE_ACTIVE = 3;
    public static final int HMA_STATE_CAMERA_BLOCKED = 5;
    public static final int HMA_STATE_FAULT = 4;
    public static final int HMA_STATE_OFF = 0;
    public static final int HMA_STATE_PASSIVE = 1;
    public static final int HMA_STATE_STANDBY = 2;
    public static final int INTERRUPT_NUMBER_OVER_THRESHOLD = 15;
    public static final int INVALID_BRAKE_PEDAL_SWITCH_STATE = 7;
    public static final int INVALID_VEHICLE_SPEED = 6;
    public static final int IS_PARKING = 1;
    public static final int IS_PARKING_OUT = 4;
    public static final int LDSW_TYPE_ALL = 2;
    public static final int LDSW_TYPE_SOUND = 1;
    public static final int LDSW_TYPE_VIBRATE = 0;
    public static final int LDW_STATIC_CALIBRATION_INVALID = 0;
    public static final int LDW_STATIC_CALIBRATION_START = 1;
    public static final int LKS_MODE_ALL = 3;
    public static final int LKS_MODE_DIVERGE = 1;
    public static final int LKS_MODE_KEEPING = 2;
    public static final int LKS_MODE_OFF = 0;
    public static final int LKS_MODE_OPEN = 4;
    public static final int LKS_SENSITIVITY_INTELLIGENCE = 0;
    public static final int LKS_SENSITIVITY_STANDARD = 1;
    public static final int MIRROR_FOLD = 5;
    public static final int NOT_READY = 0;
    public static final int NO_FAULT = 0;
    public static final int NO_INTERRUPTION = 0;
    public static final int OBSTACLE_ON_PATH = 2;
    public static final int OPEN_MAIN_DOOR = 29;
    public static final int PAUSE_AUTO_PARKING_ON = 1;
    public static final int PRESS_INVALID = 0;
    public static final int PRESS_VALID = 1;
    public static final int READY = 1;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_DRIVE = 2;
    public static final int RESET_ITEM_LKS_LDWS = 1;
    public static final int RESET_ITEM_SAFETY = 0;
    public static final int RESET_VALID = 1;
    public static final int RETAIN = 6;
    public static final int ROUTE_PLANNING_FAILURE = 18;
    public static final int SAS_FAILURE = 4;
    public static final int SAS_NOT_CALIBRATED = 8;
    public static final int SCU_FAILURE = 3;
    public static final int SEAT_BELT_RELEASED = 6;
    public static final int SLOT_SEARCHING = 1;
    public static final int SPACE_IN_PARKOUT_MODE_LIMITED = 13;
    protected static final String TAG = "BYDAutoADASDevice";
    public static final int TJA_INVALID = 0;
    public static final int TJA_OFF = 1;
    public static final int TJA_ON = 2;
    public static final int TJA_STATE_ACTIVE1 = 2;
    public static final int TJA_STATE_ACTIVE2 = 3;
    public static final int TJA_STATE_FAULT = 4;
    public static final int TJA_STATE_OFF = 0;
    public static final int TJA_STATE_PASSIVE = 1;
    public static final int UNFASTEN_SEAT_BELT = 21;
    public static final int VEHICLE_SPEED_OVER_THRESHOL = 17;
    public static final int WAITING_FOR_DRIVER_OPERATE_GEAR = 4;
    public static final int WAITING_FOR_VEHICLE_DRIVER_CONFIRM_PARK = 5;
    public static final int WAITING_FOR_VEHICLE_SLOW_DOWN = 3;
    public static final int WAITING_FOR_VEHICLE_STOP = 2;
    private static BYDAutoADASDevice sDevice;
    protected final int HDC_SWITCH_INVALID;
    protected final int HDC_SWITCH_OFF;
    protected final int HDC_SWITCH_ON;
    private final AdasManagerImpl mAdasManagerImpl;

    BYDAutoADASDevice(Context context) {
        super(context);
        this.HDC_SWITCH_INVALID = 0;
        this.HDC_SWITCH_OFF = 2;
        this.HDC_SWITCH_ON = 1;
        this.mAdasManagerImpl = AdasManagerImpl.getsInstance(context);
    }

    public static synchronized BYDAutoADASDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoADASDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mAdasManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mAdasManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mAdasManagerImpl.getGetPermission();
    }

    public int hasFeature(String str) {
        return this.mAdasManagerImpl.hasFeature(str);
    }

    public int hasFeature2(String str) {
        return this.mAdasManagerImpl.hasFeature2(str);
    }

    public int setHMAState(int i) {
        return this.mAdasManagerImpl.setHMAState(i);
    }

    public int getHMAState() {
        return this.mAdasManagerImpl.getHMAState();
    }

    public int getLaneOffsetState() {
        return this.mAdasManagerImpl.getLaneOffsetState();
    }

    public int setPCWState(int i) {
        return this.mAdasManagerImpl.setPCWState(i);
    }

    public int getPCWState() {
        return this.mAdasManagerImpl.getPCWState();
    }

    public int setAEBState(int i) {
        return this.mAdasManagerImpl.setAEBState(i);
    }

    public int getAEBState() {
        return this.mAdasManagerImpl.getAEBState();
    }

    public int setESPState(int i) {
        return this.mAdasManagerImpl.setESPState(i);
    }

    public int getESPState() {
        return this.mAdasManagerImpl.getESPState();
    }

    public int setLKSMode(int i) {
        return this.mAdasManagerImpl.setLKSMode(i);
    }

    public int getLKSMode() {
        return this.mAdasManagerImpl.getLKSMode();
    }

    public int setLKSSensitivity(int i) {
        return this.mAdasManagerImpl.setLKSSensitivity(i);
    }

    public int getLKSSensitivity() {
        return this.mAdasManagerImpl.getLKSSensitivity();
    }

    public int setLDSWType(int i) {
        return this.mAdasManagerImpl.setLDSWType(i);
    }

    public int getLDSWType() {
        return this.mAdasManagerImpl.getLDSWType();
    }

    public int setBSDState(int i) {
        return this.mAdasManagerImpl.setBSDState(i);
    }

    public int getBSDState() {
        return this.mAdasManagerImpl.getBSDState();
    }

    public int setSLAState(int i) {
        return this.mAdasManagerImpl.setSLAState(i);
    }

    public int getSLAState() {
        return this.mAdasManagerImpl.getSLAState();
    }

    public int setAVHState(int i) {
        return this.mAdasManagerImpl.setAVHState(i);
    }

    public int getAVHState() {
        return this.mAdasManagerImpl.getAVHState();
    }

    public int setIboosterState(int i) {
        return this.mAdasManagerImpl.setIboosterState(i);
    }

    public int getIboosterState() {
        return this.mAdasManagerImpl.getIboosterState();
    }

    public int setBSDStaticCalibration(int i) {
        return this.mAdasManagerImpl.setBSDStaticCalibration(i);
    }

    public int setLDWStaticCalibration(int i) {
        return this.mAdasManagerImpl.setLDWStaticCalibration(i);
    }

    public int setTJAState(int i) {
        return this.mAdasManagerImpl.setTJAState(i);
    }

    public int getTJAState() {
        return this.mAdasManagerImpl.getTJAState();
    }

    public int reset(int i, int i2) {
        return this.mAdasManagerImpl.reset(i, i2);
    }

    public int getESPOnlineState() {
        return this.mAdasManagerImpl.getESPOnlineState();
    }

    public int setHDCState(int i) {
        return this.mAdasManagerImpl.setHDCState(i);
    }

    public int getHDCState() {
        return this.mAdasManagerImpl.getHDCState();
    }

    public int get123State() {
        return this.mAdasManagerImpl.get123State();
    }

    public int setBrakeFootSenseState(int i) {
        return this.mAdasManagerImpl.setBrakeFootSenseState(i);
    }

    public int getBrakeFootSenseState() {
        return this.mAdasManagerImpl.getBrakeFootSenseState();
    }

    public int setCSTState(int i) {
        return this.mAdasManagerImpl.setCSTState(i);
    }

    public int getCSTState() {
        return this.mAdasManagerImpl.getCSTState();
    }

    public int getCSTData() {
        return this.mAdasManagerImpl.getCSTData();
    }

    public int getSuspen() {
        return this.mAdasManagerImpl.getSuspen();
    }

    public int getInterActiveHint() {
        return this.mAdasManagerImpl.getInterActiveHint();
    }

    public int getAutoSearchState() {
        return this.mAdasManagerImpl.getAutoSearchState();
    }

    public int getAbortReason() {
        return this.mAdasManagerImpl.getAbortReason();
    }

    public int getActive() {
        return this.mAdasManagerImpl.getActive();
    }

    public int getAutoParkButtonState() {
        return this.mAdasManagerImpl.getAutoParkButtonState();
    }

    public int setAVMSwitchState(int i) {
        return this.mAdasManagerImpl.setAVMSwitchState(i);
    }

    public int getAVMSwitchState() {
        return this.mAdasManagerImpl.getAVMSwitchState();
    }

    public int setAdasReset(int i, int i2, int i3) {
        return this.mAdasManagerImpl.setAdasReset(i, i2, i3);
    }

    public int getHDCKeyState() {
        return this.mAdasManagerImpl.getHDCKeyState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return this.mAdasManagerImpl.postEvent(i, i2, i3, obj);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mAdasManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mAdasManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoADASListener absBYDAutoADASListener) {
        this.mAdasManagerImpl.registerListener(absBYDAutoADASListener);
    }

    public void registerListener(AbsBYDAutoADASListener absBYDAutoADASListener, int[] iArr) {
        this.mAdasManagerImpl.registerListener(absBYDAutoADASListener, iArr);
    }

    public void unregisterListener(AbsBYDAutoADASListener absBYDAutoADASListener) {
        this.mAdasManagerImpl.unregisterListener(absBYDAutoADASListener);
    }
}
