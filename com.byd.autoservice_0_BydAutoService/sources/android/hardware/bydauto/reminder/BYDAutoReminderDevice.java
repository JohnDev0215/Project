package android.hardware.bydauto.reminder;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.reminder.tsimpl.ReminderManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoReminderDevice extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_REMINDER_COMMON = "android.permission.BYDAUTO_REMINDER_COMMON";
    public static final String PERMISSION_BYDAUTO_REMINDER_GET = "android.permission.BYDAUTO_REMINDER_GET";
    public static final String PERMISSION_BYDAUTO_REMINDER_SET = "android.permission.BYDAUTO_REMINDER_SET";
    public static final int REMINDER_COMMAND_BUSY = -2147482647;
    public static final int REMINDER_COMMAND_FAILED = -2147482648;
    public static final int REMINDER_COMMAND_INVALID = -2147482645;
    public static final int REMINDER_COMMAND_SUCCESS = 0;
    public static final int REMINDER_COMMAND_TIMEOUT = -2147482646;
    public static final int RMD_AC_DECREASE_WIND_LEVEL = 83;
    public static final int RMD_ADAS_ACC_ACTIVATED = 121;
    public static final int RMD_ADAS_ACC_DEACTIVATE = 122;
    public static final int RMD_ADAS_AUTO_BRAKE_ACTIVATED = 120;
    public static final int RMD_ADAS_ICC_ACTIVATE = 123;
    public static final int RMD_ADAS_ICC_DEACTIVATE = 124;
    public static final int RMD_ADAS_LANES_CHANGE_CANCEL = 125;
    public static final int RMD_ADAS_LANES_CHANGE_IMMEDIATE = 127;
    public static final int RMD_ADAS_LANES_CHANGE_SUCCESS = 126;
    public static final int RMD_ADAS_TAKE_OVER = 129;
    public static final int RMD_ADAS_WATCH_OUT_SURROUNDINGS = 128;
    public static final int RMD_ATTTION_DRIVING = 12;
    public static final int RMD_AUTO_LIGHT_OFF = 14;
    public static final int RMD_AUTO_LIGHT_ON = 13;
    public static final int RMD_BEAM_LIGHT_ON = 38;
    public static final int RMD_CHANGE_LOWKEY = 10;
    public static final int RMD_CLOSE_BACKUP_DOOR = 136;
    public static final int RMD_CLOSE_FRONT_DOOR = 135;
    public static final int RMD_DC_SYSTEM_FAILURE = 110;
    public static final int RMD_DISCHARGE_STOP = 104;
    public static final int RMD_DOOR_OK = 9;
    public static final int RMD_DOOR_OPEN_ATTENTION = 111;
    public static final int RMD_FINISH_STUDY = 35;
    public static final int RMD_FST_PWD_BTN = 5;
    public static final int RMD_GEAR_INCORRECT = 101;
    public static final int RMD_GEAR_NOT_P = 103;
    public static final int RMD_HIGHT_TEMPERATURE = 39;
    public static final int RMD_KEYCLSTART_BTN = 6;
    public static final int RMD_KEY_INCAR = 7;
    public static final int RMD_LOCK_SAFEBELT = 36;
    public static final int RMD_LOW_BATT = 1;
    public static final int RMD_LOW_OIL = 37;
    public static final int RMD_LOW_SINGAL_NET = 42;
    public static final int RMD_LOW_TEMPERATURE = 40;
    public static final int RMD_LOW_VOLTAGE_FAULT = 105;
    public static final int RMD_LPSTART_BTN = 4;
    public static final int RMD_NPLEAVE_CAR = 3;
    public static final int RMD_PM2P5_AUTO_INTER_CIRCUL = 82;
    public static final int RMD_PM2P5_INTER_CIRCUL = 81;
    public static final int RMD_PM2P5_OPEN_AC = 80;
    public static final int RMD_RAPID_ACCELERATION_LIMIT = 130;
    public static final int RMD_REAR_AIR_CONDITIONING_OPERATE_INVALID = 131;
    public static final int RMD_REAR_AIR_CONDITIONING_UNLOCK = 132;
    public static final int RMD_RM_BRAKE = 8;
    public static final int RMD_START_CAR = 2;
    public static final int RMD_STEER_START = 11;
    public static final int RMD_STOP_CAR_BTN = 102;
    public static final int RMD_STOP_REMOTECTRL_DRV = 41;
    public static final int RMD_SYS_FATIGUE_DEMO = 33;
    public static final int RMD_SYS_FATIGUE_OFF = 32;
    public static final int RMD_SYS_FATIGUE_ON = 15;
    public static final int RMD_SYS_FATIGUE_WORK = 34;
    public static final int RMD_SYS_POWER_DOWN = 108;
    public static final int RMD_SYS_POWER_DRIVING = 109;
    public static final int RMD_SYS_POWER_EVACUATE = 106;
    public static final int RMD_SYS_POWER_LEAVE = 107;
    public static final int RMD_TAKE_PHONE = 137;
    public static final int RMD_TRAFFIC_LOW = 500;
    private static final String TAG = "BYDAutoReminderDevice";
    private static volatile BYDAutoReminderDevice sBYDAutoReminderDevice;
    private ReminderManagerImpl mReminderManagerImpl;

    public BYDAutoReminderDevice(Context context) {
        super(context);
        this.mReminderManagerImpl = ReminderManagerImpl.getsInstance(context);
    }

    public static synchronized BYDAutoReminderDevice getInstance(Context context) {
        if (sBYDAutoReminderDevice == null) {
            synchronized (BYDAutoReminderDevice.class) {
                if (sBYDAutoReminderDevice == null) {
                    sBYDAutoReminderDevice = new BYDAutoReminderDevice(context.getApplicationContext());
                }
            }
        }
        return sBYDAutoReminderDevice;
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_REMINDER_GET;
    }

    public int getIndicatorValue() {
        return this.mReminderManagerImpl.getIndicatorValue();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_REMINDER_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public boolean isSatisfyReport(int i3) {
        return this.mReminderManagerImpl.isSatisfyReport(i3);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i3, int i4, int i5, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoReminderListener absBYDAutoReminderListener) {
        this.mReminderManagerImpl.registerListener(absBYDAutoReminderListener);
    }

    public void unregisterListener(AbsBYDAutoReminderListener absBYDAutoReminderListener) {
        this.mReminderManagerImpl.unregisterListener(absBYDAutoReminderListener);
    }

    public void registerListener(AbsBYDAutoReminderListener absBYDAutoReminderListener, int[] iArr) {
        this.mReminderManagerImpl.registerListener(absBYDAutoReminderListener, iArr);
    }
}
