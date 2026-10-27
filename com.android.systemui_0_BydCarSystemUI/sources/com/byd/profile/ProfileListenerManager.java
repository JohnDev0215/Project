package com.byd.profile;

import android.content.Context;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ProfileListenerManager {
    private static volatile ProfileListenerManager sInstance;
    private BYDAutoAudioDevice mBYDAutoAudioDevice;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoGearboxDevice mBYDAutoGearboxDevice;
    private BYDAutoPowerDevice mBYDAutoPowerDevice;
    private BYDAutoStatisticDevice mBYDAutoStatisticDevice;
    private Context mContext;
    private final AbsBYDAutoGearboxListener mAbsBYDAutoGearboxListener = new AbsBYDAutoGearboxListener() { // from class: com.byd.profile.ProfileListenerManager.1
        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int value) {
            super.onGearChanged(value);
            BydLogHelper.logD("onGearChanged value = " + value);
            if (value != 1) {
                ProfileManager.getInstance(ProfileListenerManager.this.mContext).onGearboxChange();
            }
        }
    };
    private final AbsBYDAutoBodyworkListener mAbsBYDAutoBodyworkListener = new AbsBYDAutoBodyworkListener() { // from class: com.byd.profile.ProfileListenerManager.2
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDoorStateChanged(int area, int state) {
            super.onDoorStateChanged(area, state);
            BydLogHelper.logD("onDoorStateChanged area = " + area + " ,state = " + state);
            if (state == 1) {
                if (area == 1 || area == 2 || area == 3 || area == 4 || area == 6) {
                    ProfileManager.getInstance(ProfileListenerManager.this.mContext).onDoorOpened();
                }
            }
        }
    };
    private final AbsBYDAutoStatisticListener mAbsBYDAutoStatisticListener = new AbsBYDAutoStatisticListener() { // from class: com.byd.profile.ProfileListenerManager.3
        @Override // android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener
        public void onElecPercentageChanged(double value) {
            super.onElecPercentageChanged(value);
            BydLogHelper.logD("onElecPercentageChanged");
            ProfileManager.getInstance(ProfileListenerManager.this.mContext).onBatteryChange();
        }
    };
    private final AbsBYDAutoAudioListener mAbsBYDAutoAudioListener = new AbsBYDAutoAudioListener() { // from class: com.byd.profile.ProfileListenerManager.4
        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVolumeChanged(int value, int flag) {
            super.onVolumeChanged(value, flag);
            BydLogHelper.logD("onVolumeChanged value = " + value + " ,flag = " + flag);
        }
    };
    private AbsBYDAutoPowerListener mAbsBYDAutoPowerListener = new AbsBYDAutoPowerListener() { // from class: com.byd.profile.ProfileListenerManager.5
        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
            BydLogHelper.logD("onPowerLevelChanged level = " + level);
            if (level == 2) {
                ProfileManager.getInstance(ProfileListenerManager.this.mContext).stopMode();
            }
        }
    };

    public static ProfileListenerManager getInstance() {
        if (sInstance == null) {
            synchronized (ProfileListenerManager.class) {
                if (sInstance == null) {
                    sInstance = new ProfileListenerManager();
                }
            }
        }
        return sInstance;
    }

    private ProfileListenerManager() {
        BydLogHelper.logD("init");
    }

    public void registerListener(Context context) {
        this.mContext = context;
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            this.mBYDAutoBodyworkDevice = bYDAutoBodyworkDevice;
            bYDAutoBodyworkDevice.registerListener(this.mAbsBYDAutoBodyworkListener);
            BYDAutoStatisticDevice bYDAutoStatisticDevice = BYDAutoStatisticDevice.getInstance(context);
            this.mBYDAutoStatisticDevice = bYDAutoStatisticDevice;
            bYDAutoStatisticDevice.registerListener(this.mAbsBYDAutoStatisticListener);
            BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(context);
            this.mBYDAutoGearboxDevice = bYDAutoGearboxDevice;
            bYDAutoGearboxDevice.registerListener(this.mAbsBYDAutoGearboxListener);
            BYDAutoAudioDevice bYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(context);
            this.mBYDAutoAudioDevice = bYDAutoAudioDevice;
            bYDAutoAudioDevice.registerListener(this.mAbsBYDAutoAudioListener);
            BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(context);
            this.mBYDAutoPowerDevice = bYDAutoPowerDevice;
            bYDAutoPowerDevice.registerListener(this.mAbsBYDAutoPowerListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void unregisterListener() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.unregisterListener(this.mAbsBYDAutoBodyworkListener);
        }
        BYDAutoStatisticDevice bYDAutoStatisticDevice = this.mBYDAutoStatisticDevice;
        if (bYDAutoStatisticDevice != null) {
            bYDAutoStatisticDevice.unregisterListener(this.mAbsBYDAutoStatisticListener);
        }
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.mBYDAutoGearboxDevice;
        if (bYDAutoGearboxDevice != null) {
            bYDAutoGearboxDevice.unregisterListener(this.mAbsBYDAutoGearboxListener);
        }
        BYDAutoAudioDevice bYDAutoAudioDevice = this.mBYDAutoAudioDevice;
        if (bYDAutoAudioDevice != null) {
            bYDAutoAudioDevice.unregisterListener(this.mAbsBYDAutoAudioListener);
        }
        BYDAutoPowerDevice bYDAutoPowerDevice = this.mBYDAutoPowerDevice;
        if (bYDAutoPowerDevice != null) {
            bYDAutoPowerDevice.unregisterListener(this.mAbsBYDAutoPowerListener);
        }
    }
}
