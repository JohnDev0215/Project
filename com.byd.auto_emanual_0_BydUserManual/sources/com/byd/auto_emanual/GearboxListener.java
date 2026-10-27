package com.byd.auto_emanual;

import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import com.byd.auto_emanual.util.AutoInfoUtil;
import com.byd.auto_emanual.util.MLog;

/* JADX INFO: loaded from: classes.dex */
public class GearboxListener extends AbsBYDAutoGearboxListener {
    private static final String TAG = "GearboxListener";
    private static GearboxListener instance;
    private IStopOrGo activity;

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearChanged(int level) {
        super.onGearChanged(level);
        MLog.i(TAG, "onGearChanged level = " + level);
        if (level == 1 || this.activity.getStopOrGoDialog() != null) {
            return;
        }
        this.activity.showStopOrGoDialog(true);
    }

    @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
    public void onGearboxAutoModeLevelChanged(int level) {
        super.onGearboxAutoModeLevelChanged(level);
        MLog.i(TAG, "onGearboxAutoModeLevelChanged level = " + level);
        if (level == 1 || this.activity.getStopOrGoDialog() != null) {
            return;
        }
        this.activity.showStopOrGoDialog(true);
    }

    public static int getModeType() {
        int gear;
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(MApp.context);
        int autoPowerType = AutoInfoUtil.getAutoPowerType(MApp.context);
        if (autoPowerType == -1) {
            autoPowerType = AutoInfoUtil.getAutoPowerType(MApp.context);
        }
        if (autoPowerType == 1) {
            if (!Config.CAN_GET_AUTO_INFO) {
                return 1;
            }
            gear = bYDAutoGearboxDevice.getGearboxAutoModeLevel();
        } else {
            if (autoPowerType != 0 || !Config.CAN_GET_AUTO_INFO) {
                return 1;
            }
            gear = bYDAutoGearboxDevice.getGear();
        }
        return gear;
    }

    public static boolean isModeTypeP() {
        boolean z = getModeType() == 1;
        MLog.i(TAG, "isModeTypeP = " + z);
        return z;
    }

    public static GearboxListener getInstance() {
        if (instance == null) {
            instance = new GearboxListener();
        }
        return instance;
    }

    public void setActivity(IStopOrGo activity) {
        this.activity = activity;
    }

    public void register(IStopOrGo activity) {
        if (Config.CAN_GET_AUTO_INFO) {
            BYDAutoGearboxDevice.getInstance(MApp.context).registerListener((AbsBYDAutoGearboxListener) this);
        }
        this.activity = activity;
    }

    public void unregister() {
        if (Config.CAN_GET_AUTO_INFO) {
            BYDAutoGearboxDevice.getInstance(MApp.context).unregisterListener((AbsBYDAutoGearboxListener) this);
        }
    }
}
