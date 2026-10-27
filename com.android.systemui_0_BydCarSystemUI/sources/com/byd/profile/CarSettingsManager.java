package com.byd.profile;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto.fragrance.BYDAutoFragranceDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.PowerManager;
import com.byd.profile.utils.SharedPreferencesUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class CarSettingsManager {
    private static final int DEFAULT_BRIGHTNESS = 3;
    private static final int DOOR_LOCK_STATE_LOCK = 0;
    private static final int DOOR_LOCK_STATE_UNLOCK = 1;
    private static final int HAS_PASSENGER = 2;
    private static final int ORIENTATION_BACK = 2;
    private static final int ORIENTATION_FRONT = 1;
    private static int sCarType;
    private static volatile CarSettingsManager sInstance;
    private int mAcStateOrigin;
    private int mAcWindOrigin;
    private int mAreaOrigin;
    private BYDAutoSettingDevice mBYDAutoSettingDevice;
    private int mBrightnessOrigin;
    private int mChildLeftOrigin;
    private int mChildRightOrigin;
    private Context mContext;
    private int mDayLightOrigin;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(2);
    private int mFootOrigin;
    private int mFragLevelOrigin;
    private int mFragTypeOrigin;
    private float mHeadOrigin;
    private int mInstrumentBrightnessOrigin;
    private int mInstrumentModeOrigin;
    private int mSeatOrigin;
    private int mVolumeOrigin;
    private PowerManager.WakeLock mWakeLock;

    public void restoreDoorLock() {
    }

    public void restoreSunshade() {
    }

    public void restoreWindow() {
    }

    public static CarSettingsManager getInstance() {
        if (sInstance == null) {
            synchronized (CarSettingsManager.class) {
                if (sInstance == null) {
                    sInstance = new CarSettingsManager();
                }
            }
        }
        return sInstance;
    }

    private CarSettingsManager() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void setVolume(int volume) {
        BYDAutoAudioDevice.getInstance(this.mContext).setVolume(volume);
    }

    public int getVolume() {
        return BYDAutoAudioDevice.getInstance(this.mContext).getVolume();
    }

    public void restoreVolume() {
        BydLogHelper.logD("restoreVolume");
        BYDAutoAudioDevice.getInstance(this.mContext).setVolume(this.mVolumeOrigin);
    }

    public void setAir() {
        BydLogHelper.logD("setAir");
        BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this.mContext);
        int acStartState = bYDAutoAcDevice.getAcStartState();
        this.mAcStateOrigin = acStartState;
        if (acStartState == 0) {
            bYDAutoAcDevice.start(0);
        }
        if (bYDAutoAcDevice.getAcControlMode() == 1) {
            this.mAcWindOrigin = bYDAutoAcDevice.getAcWindLevel();
            bYDAutoAcDevice.setAcWindLevel(0, 2);
        }
    }

    public void restoreAir() {
        BydLogHelper.logD("restoreAir");
        BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this.mContext);
        bYDAutoAcDevice.setAcWindLevel(0, this.mAcWindOrigin);
        if (this.mAcStateOrigin == 0) {
            bYDAutoAcDevice.stop(0);
        }
    }

    public void setWindow() {
        BydLogHelper.logD("setWindow");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        bYDAutoBodyworkDevice.setWindowControlState(1, 2);
        bYDAutoBodyworkDevice.setWindowControlState(3, 2);
        bYDAutoBodyworkDevice.setWindowControlState(2, 2);
        bYDAutoBodyworkDevice.setWindowControlState(4, 2);
    }

    public void setSunshade() {
        BydLogHelper.logD("setSunshade");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        bYDAutoBodyworkDevice.setWindowOpenPercent(5, 0);
        bYDAutoBodyworkDevice.setWindowOpenPercent(6, 0);
    }

    public void setDayLight() {
        BydLogHelper.logD("setDayLight");
        BYDAutoLightDevice bYDAutoLightDevice = BYDAutoLightDevice.getInstance(this.mContext);
        int dayTimeLightState = bYDAutoLightDevice.getDayTimeLightState();
        this.mDayLightOrigin = dayTimeLightState;
        if (dayTimeLightState == 1) {
            bYDAutoLightDevice.setDayTimeLightState(2);
        }
    }

    public void restoreDayLight() {
        BydLogHelper.logD("restoreDayLight");
        if (this.mDayLightOrigin == 1) {
            BYDAutoLightDevice.getInstance(this.mContext).setDayTimeLightState(1);
        }
    }

    public void setAtmosphere(boolean isOn) {
        BydLogHelper.logD("setAtmosphere isOn = " + isOn);
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
        int iALArea = bYDAutoSettingDevice.getIALArea();
        this.mAreaOrigin = iALArea;
        if (iALArea != 0) {
            this.mBrightnessOrigin = bYDAutoSettingDevice.getIALBrightness(iALArea);
        } else {
            this.mBrightnessOrigin = 0;
            bYDAutoSettingDevice.setIALArea(3, 0);
        }
        bYDAutoSettingDevice.getIALBrightness(isOn ? 3 : 0);
    }

    public void restoreAtmosphere() {
        BydLogHelper.logD("restoreAtmosphere");
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
        int i = this.mAreaOrigin;
        if (i == 0) {
            bYDAutoSettingDevice.getIALBrightness(0);
        } else {
            bYDAutoSettingDevice.setIALArea(i, 0);
            bYDAutoSettingDevice.getIALBrightness(this.mBrightnessOrigin);
        }
    }

    public void setScreen() {
        BydLogHelper.logD("setScreen");
    }

    public void restoreScreen() {
        BydLogHelper.logD("restoreScreen");
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    public void setDoorLock() {
        BydLogHelper.logD("setDoorLock");
        BYDAutoDoorLockDevice bYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(this.mContext);
        bYDAutoDoorLockDevice.setDoorLockStatus(1, 1);
        bYDAutoDoorLockDevice.setDoorLockStatus(2, 1);
        bYDAutoDoorLockDevice.setDoorLockStatus(3, 1);
        bYDAutoDoorLockDevice.setDoorLockStatus(4, 1);
    }

    public void setChildLock() {
        BydLogHelper.logD("setChildLock");
        BYDAutoDoorLockDevice bYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(this.mContext);
        this.mChildLeftOrigin = bYDAutoDoorLockDevice.getDoorLockStatus(6);
        this.mChildRightOrigin = bYDAutoDoorLockDevice.getDoorLockStatus(7);
        bYDAutoDoorLockDevice.setDoorLockStatus(6, 1);
        bYDAutoDoorLockDevice.setDoorLockStatus(7, 1);
    }

    public void restoreChildLock() {
        BydLogHelper.logD("restoreChildLock");
        BYDAutoDoorLockDevice bYDAutoDoorLockDevice = BYDAutoDoorLockDevice.getInstance(this.mContext);
        bYDAutoDoorLockDevice.setDoorLockStatus(6, this.mChildLeftOrigin);
        bYDAutoDoorLockDevice.setDoorLockStatus(7, this.mChildRightOrigin);
    }

    public void setSeat(int percent) {
        BydLogHelper.logD("setSeat");
        try {
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
            this.mHeadOrigin = bYDAutoSettingDevice.getSeatbackrestPostion(1);
            this.mSeatOrigin = bYDAutoSettingDevice.getSeatHorization(1);
            bYDAutoSettingDevice.turnSeatHorizationPercent(1, percent);
            bYDAutoSettingDevice.turnSeatbackrestPercent(1, percent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setSeat(int seatPercent, int headPercent, int footPercent) {
        BydLogHelper.logD("setSeat");
        try {
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
            this.mHeadOrigin = bYDAutoSettingDevice.getSeatbackrestPostion(1);
            this.mSeatOrigin = bYDAutoSettingDevice.getSeatHorization(1);
            bYDAutoSettingDevice.turnSeatHorizationPercent(1, seatPercent);
            bYDAutoSettingDevice.turnSeatbackrestPercent(1, headPercent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setSeat(int area, int seatPercent, int headPercent, int footPercent) {
        BydLogHelper.logD("setSeat");
        try {
            BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
            if (seatPercent != -1) {
                this.mHeadOrigin = bYDAutoSettingDevice.getSeatbackrestPostion(area);
                bYDAutoSettingDevice.turnSeatHorizationPercent(area, seatPercent);
            }
            if (headPercent != -1) {
                this.mSeatOrigin = bYDAutoSettingDevice.getSeatHorization(area);
                bYDAutoSettingDevice.turnSeatbackrestPercent(area, headPercent);
            }
            if (this.mFootOrigin != -1) {
                bYDAutoSettingDevice.getTurnCaliperSplintHeight(area);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setSeat(int area, int percent) {
        BYDAutoSettingDevice.getInstance(this.mContext).turnSeatHorizationPercent(area, percent);
    }

    public int getSeat(int area) {
        return BYDAutoSettingDevice.getInstance(this.mContext).getSeatHorization(area);
    }

    public void setHead(int area, int percent) {
        BYDAutoSettingDevice.getInstance(this.mContext).turnSeatbackrestPercent(area, percent);
    }

    public int getHead(int area) {
        return ((int) BYDAutoSettingDevice.getInstance(this.mContext).getSeatbackrestPostion(area)) * 100;
    }

    public void setFoot(int area, int percent) {
        BYDAutoSettingDevice.getInstance(this.mContext);
    }

    public int getFoot(int area) {
        return ((int) BYDAutoSettingDevice.getInstance(this.mContext).getSeatbackrestPostion(area)) * 100;
    }

    public void restoreSeat() {
        BydLogHelper.logD("restoreSeat");
        BYDAutoSettingDevice bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(this.mContext);
        bYDAutoSettingDevice.turnSeatHorizationPercent(1, this.mSeatOrigin);
        bYDAutoSettingDevice.turnSeatbackrestPercent(1, (int) this.mHeadOrigin);
    }

    public void setFragrance() {
        BydLogHelper.logD("setFragrance");
        BYDAutoFragranceDevice bYDAutoFragranceDevice = BYDAutoFragranceDevice.getInstance(this.mContext);
        int curFragranceType = bYDAutoFragranceDevice.getCurFragranceType();
        this.mFragTypeOrigin = curFragranceType;
        this.mFragLevelOrigin = bYDAutoFragranceDevice.getFragranceConcentration(curFragranceType);
        int i = SharedPreferencesUtil.getInt(this.mContext, Constants.SP_KEY_TYPE_FRAGRANCE_ROMANTIC, 0);
        int i2 = SharedPreferencesUtil.getInt(this.mContext, Constants.SP_KEY_LEVEL_FRAGRANCE_ROMANTIC, 0);
        bYDAutoFragranceDevice.setFragranceType(i);
        bYDAutoFragranceDevice.setFragranceConcentration(i2);
    }

    public void setFragrance(int type, int level) {
        BYDAutoFragranceDevice bYDAutoFragranceDevice = BYDAutoFragranceDevice.getInstance(this.mContext);
        bYDAutoFragranceDevice.setFragranceType(type);
        bYDAutoFragranceDevice.setFragranceConcentration(level);
    }

    public void restoreFragrance() {
        BydLogHelper.logD("restoreFragrance");
        BYDAutoFragranceDevice bYDAutoFragranceDevice = BYDAutoFragranceDevice.getInstance(this.mContext);
        bYDAutoFragranceDevice.setFragranceType(this.mFragTypeOrigin);
        bYDAutoFragranceDevice.setFragranceConcentration(this.mFragLevelOrigin);
    }

    public void setInstrument() {
        BydLogHelper.logD("setFragrance");
        BYDAutoInstrumentDevice bYDAutoInstrumentDevice = BYDAutoInstrumentDevice.getInstance(this.mContext);
        this.mInstrumentBrightnessOrigin = bYDAutoInstrumentDevice.getInstrumentBrightness();
        bYDAutoInstrumentDevice.setInstrumentBrightness(0);
    }

    public void restoreInstrument() {
        BydLogHelper.logD("restoreFragrance");
        BYDAutoInstrumentDevice.getInstance(this.mContext).setInstrumentBrightness(this.mInstrumentBrightnessOrigin);
    }

    public void clear() {
        this.mExecutorService.shutdown();
    }

    public static boolean isDoorsClosed(Context context, int... areas) {
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            boolean z = true;
            for (int i : areas) {
                int doorState = bYDAutoBodyworkDevice.getDoorState(i);
                BydLogHelper.logD("area: " + i + " doorState = " + doorState);
                z = z && doorState != 1;
            }
            BydLogHelper.logD("isDoorsClosed = " + z);
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isBatteryLow(Context context) {
        try {
            double elecPercentageValue = BYDAutoStatisticDevice.getInstance(context).getElecPercentageValue();
            BydLogHelper.logD("elecValue = " + elecPercentageValue);
            boolean z = Double.compare(elecPercentageValue, 0.2d) < 0;
            BydLogHelper.logD("isBatteryLow = " + z);
            return z;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static int getAutoType(Context context) {
        return BYDAutoBodyworkDevice.getInstance(context).getAutoType();
    }

    public static boolean isCarEV(Context context) {
        int i = sCarType;
        if (i == -1 || i == 0) {
            sCarType = getAutoType(context);
        }
        BydLogHelper.logD("isCarEV car autoType = " + sCarType);
        switch (sCarType) {
            case 0:
            case 14:
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017 A[PHI: r5
  0x0017: PHI (r5v4 int) = (r5v3 int), (r5v11 int) binds: [B:10:0x0021, B:6:0x0014] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isP(android.content.Context r5) {
        /*
            r0 = 0
            boolean r1 = isCarEV(r5)     // Catch: java.lang.Throwable -> L4e
            boolean r2 = isCarEV(r5)     // Catch: java.lang.Throwable -> L4e
            r3 = 1
            if (r2 == 0) goto L19
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r5 = android.hardware.bydauto.gearbox.BYDAutoGearboxDevice.getInstance(r5)     // Catch: java.lang.Throwable -> L4e
            int r5 = r5.getGear()     // Catch: java.lang.Throwable -> L4e
            if (r5 != r3) goto L17
            goto L23
        L17:
            r3 = r0
            goto L23
        L19:
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r5 = android.hardware.bydauto.gearbox.BYDAutoGearboxDevice.getInstance(r5)     // Catch: java.lang.Throwable -> L4e
            int r5 = r5.getGearboxAutoModeLevel()     // Catch: java.lang.Throwable -> L4e
            if (r5 != r3) goto L17
        L23:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r2.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r4 = "isCarEv = "
            java.lang.StringBuilder r2 = r2.append(r4)     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = ", gear = "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r5 = r1.append(r5)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r1 = ", isP = "
            java.lang.StringBuilder r5 = r5.append(r1)     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r5 = r5.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L4e
            com.bydux.api.common.tools.log.BydLogHelper.logD(r5)     // Catch: java.lang.Throwable -> L4e
            return r3
        L4e:
            r5 = move-exception
            r5.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.profile.CarSettingsManager.isP(android.content.Context):boolean");
    }

    public static boolean isHasPassenger(Context context, int area) {
        int passengerState = BYDAutoSettingDevice.getInstance(context).getPassengerState(area);
        BydLogHelper.logD("passengerState = " + passengerState);
        if (passengerState == 2) {
            BydLogHelper.logD("has passenger");
        }
        return passengerState == 2;
    }
}
