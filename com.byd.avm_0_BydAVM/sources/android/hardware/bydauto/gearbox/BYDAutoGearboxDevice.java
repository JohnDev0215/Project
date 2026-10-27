package android.hardware.bydauto.gearbox;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.tsimpl.GearboxManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoGearboxDevice extends AbsBYDAutoDevice {
    public static final int EPB_STATE_APPLIED = 3;
    public static final int EPB_STATE_APPLYING = 2;
    public static final int EPB_STATE_FAULT = 4;
    public static final int EPB_STATE_RELEASED = 1;
    public static final int EPB_STATE_RELEASING = 0;
    public static final int GEARBOX_AUTO_MODE_D = 4;
    public static final int GEARBOX_AUTO_MODE_M = 5;
    public static final int GEARBOX_AUTO_MODE_N = 3;
    public static final int GEARBOX_AUTO_MODE_P = 1;
    public static final int GEARBOX_AUTO_MODE_R = 2;
    public static final int GEARBOX_AUTO_MODE_S = 6;
    public static final int GEARBOX_BRAKE_FLUID_LEVEL_LOW = 2;
    public static final int GEARBOX_BRAKE_FLUID_LEVEL_NORMAL = 1;
    public static final int GEARBOX_BREAK_PADAL_NOT_PRESS = 0;
    public static final int GEARBOX_BREAK_PADAL_PRESS = 1;
    public static final int GEARBOX_BREAK_PADAL_PRESS_DEEP = 2;
    public static final int GEARBOX_BREAK_PADAL_PRESS_LOW = 1;
    public static final String GEARBOX_CODE_0 = "DABS15-41";
    public static final String GEARBOX_CODE_1 = "F4A4";
    public static final String GEARBOX_CODE_10 = "5RT14";
    public static final String GEARBOX_CODE_11 = "6T25";
    public static final String GEARBOX_CODE_12 = "6DT25";
    public static final String GEARBOX_CODE_13 = "6DT33";
    public static final String GEARBOX_CODE_14 = "6T18";
    public static final String GEARBOX_CODE_15 = "6DT35";
    public static final String GEARBOX_CODE_16 = "AF636";
    public static final String GEARBOX_CODE_17 = "AF640";
    public static final String GEARBOX_CODE_18 = "F625";
    public static final String GEARBOX_CODE_19 = "F515";
    public static final String GEARBOX_CODE_2 = "F4A4B";
    public static final String GEARBOX_CODE_20 = "6F18";
    public static final String GEARBOX_CODE_21 = "CF18";
    public static final String GEARBOX_CODE_22 = "CF025";
    public static final String GEARBOX_CODE_23 = "7DT28";
    public static final String GEARBOX_CODE_24 = "6HDT35-3";
    public static final String GEARBOX_CODE_25 = "6HDT35-4";
    public static final String GEARBOX_CODE_26 = "6HDT35-5";
    public static final String GEARBOX_CODE_27 = "T75-1";
    public static final String GEARBOX_CODE_28 = "T75-3";
    public static final String GEARBOX_CODE_29 = "T75-2";
    public static final String GEARBOX_CODE_3 = "VT2-04O";
    public static final String GEARBOX_CODE_30 = "T75-5";
    public static final String GEARBOX_CODE_31 = "7DT30";
    public static final String GEARBOX_CODE_32 = "DHT30";
    public static final String GEARBOX_CODE_33 = "DHT33";
    public static final String GEARBOX_CODE_34 = "7DT20";
    public static final String GEARBOX_CODE_4 = "SSG";
    public static final String GEARBOX_CODE_5 = "5T09";
    public static final String GEARBOX_CODE_6 = "5T14";
    public static final String GEARBOX_CODE_7 = "5T19";
    public static final String GEARBOX_CODE_8 = "5T19-1";
    public static final String GEARBOX_CODE_9 = "5RT10";
    public static final int GEARBOX_COMMAND_BUSY = -2147482647;
    public static final int GEARBOX_COMMAND_FAILED = -2147482648;
    public static final int GEARBOX_COMMAND_INVALID_VALUE = -2147482645;
    public static final int GEARBOX_COMMAND_SUCCESS = 0;
    public static final int GEARBOX_COMMAND_TIMEOUT = -2147482646;
    public static final int GEARBOX_PARK_BREAK_SWITCH_INVALID = 0;
    public static final int GEARBOX_PARK_BREAK_SWITCH_VALID = 1;
    public static final int GEARBOX_REAL_LEVEL_D = 0;
    public static final int GEARBOX_REAL_LEVEL_N = 2;
    public static final int GEARBOX_REAL_LEVEL_R = 1;
    public static final int GEARBOX_STATE_OFF = 0;
    public static final int GEARBOX_STATE_ON = 1;
    public static final int GEARBOX_TYPE_AMT = 1;
    public static final int GEARBOX_TYPE_AT = 2;
    public static final int GEARBOX_TYPE_CVT = 3;
    public static final int GEARBOX_TYPE_DCT = 4;
    public static final int GEARBOX_TYPE_ECVT = 5;
    public static final int GEARBOX_TYPE_INVALID = 255;
    public static final int GEARBOX_TYPE_INVALID2 = 6;
    public static final int GEARBOX_TYPE_MT = 0;
    public static final int GEARBOX_TYPE_NONE = 7;
    public static final int GEAR_D = 2;
    public static final int GEAR_INVALID = 255;
    public static final int GEAR_N = 0;
    public static final int GEAR_P = 3;
    public static final int GEAR_R = 1;
    public static final String PERMISSION_BYDAUTO_GEARBOX_COMMON = "android.permission.BYDAUTO_GEARBOX_COMMON";
    public static final String PERMISSION_BYDAUTO_GEARBOX_GET = "android.permission.BYDAUTO_GEARBOX_GET";
    public static final String PERMISSION_BYDAUTO_GEARBOX_SET = "android.permission.BYDAUTO_GEARBOX_SET";
    protected static final String TAG = "BYDAutoGearboxDevice";
    private static BYDAutoGearboxDevice sDevice;
    private final GearboxManagerImpl mGearboxManagerImpl;

    BYDAutoGearboxDevice(Context context) {
        super(context.getApplicationContext());
        this.mGearboxManagerImpl = GearboxManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoGearboxDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoGearboxDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mGearboxManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
    }

    public int getBrakeFluidLevel() {
        return this.mGearboxManagerImpl.getBrakeFluidLevel();
    }

    public int getBrakePedalState() {
        return this.mGearboxManagerImpl.getBrakePedalState();
    }

    public int getCurrentGear() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getEPBState() {
        return this.mGearboxManagerImpl.getEPBState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getGear() {
        return this.mGearboxManagerImpl.getGear();
    }

    public int getGearboxAutoModeLevel() {
        return this.mGearboxManagerImpl.getGearboxAutoModeLevel();
    }

    public int getGearboxAutoModeType() {
        return this.mGearboxManagerImpl.getGearboxAutoModeType();
    }

    public String getGearboxCode() {
        return this.mGearboxManagerImpl.getGearboxCode();
    }

    public int getGearboxManualModeLevel() {
        return this.mGearboxManagerImpl.getGearboxManualModeLevel();
    }

    public int getGearboxState() {
        return 0;
    }

    public int getGearboxType() {
        return this.mGearboxManagerImpl.getGearboxType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_GEARBOX_GET;
    }

    public int getParkBrakeSwitch() {
        return this.mGearboxManagerImpl.getParkBrakeSwitch();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_GEARBOX_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int isGearboxAuto() {
        return 0;
    }

    public boolean isInReverseGear() {
        return false;
    }

    public int learningEPB() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        this.mGearboxManagerImpl.registerListener(absBYDAutoGearboxListener);
    }

    public void setAllStatus() {
    }

    public int setEPBState(int i) {
        return this.mGearboxManagerImpl.setEPBState(i);
    }

    public void unregisterListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        this.mGearboxManagerImpl.unregisterListener(absBYDAutoGearboxListener);
    }

    public void registerListener(AbsBYDAutoGearboxListener absBYDAutoGearboxListener, int[] iArr) {
        this.mGearboxManagerImpl.registerListener(absBYDAutoGearboxListener, iArr);
    }
}
