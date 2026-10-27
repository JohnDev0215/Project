package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import com.android.systemui.autodevices.DeviceManager;
import com.android.systemui.statusbar.policy.HvacController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class HvacControllerImpl implements HvacController {
    private static final String TAG = "HvacControllerImpl";
    private static BYDAutoAcDevice mBYDAutoAcDevice;
    private static BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private final Context mContext;
    private final ArrayList<HvacController.Callback> mCallbacks = new ArrayList<>();
    private int mUnit = 1;
    private final DeviceManager.DeviceManagerListener deviceManagerListener = new DeviceManager.DeviceManagerListener() { // from class: com.android.systemui.statusbar.policy.HvacControllerImpl.1
        @Override // com.android.systemui.autodevices.DeviceManager.DeviceManagerListener
        public void onRefreshHvacView() {
            BydLogHelper.logW(HvacControllerImpl.TAG, "onRefreshHvacView car adapter is connect update hvac");
            BYDAutoBodyworkDevice unused = HvacControllerImpl.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(HvacControllerImpl.this.mContext);
            BYDAutoAcDevice unused2 = HvacControllerImpl.mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(HvacControllerImpl.this.mContext);
            HvacControllerImpl.this.mUnit = HvacControllerImpl.mBYDAutoAcDevice.getTemperatureUnit();
            HvacControllerImpl.this.refreshHvac();
        }
    };
    private final AbsBYDAutoAcListener absBYDAutoAcListener = new AbsBYDAutoAcListener() { // from class: com.android.systemui.statusbar.policy.HvacControllerImpl.2
        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcStarted() {
            HvacControllerImpl.this.onHvacPowerChanged(1);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcStoped() {
            HvacControllerImpl.this.onHvacPowerChanged(0);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onTemperatureChanged(int area, int value) {
            BydLogHelper.logW("AbsBYDAutoAcListener", "onTemperatureChanged", "area : " + area + ", value : " + value);
            if (area == 1) {
                HvacControllerImpl.this.onFrontLeftTempChanged(value);
            } else if (area == 2) {
                HvacControllerImpl.this.onFrontRightTempChanged(value);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onTemperatureUnitChanged(int unit) {
            HvacControllerImpl.this.onUnitChanged(unit);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindModeChanged(int mode) {
            HvacControllerImpl.this.onWindModeChanged(mode);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindLevelChanged(int level) {
            HvacControllerImpl.this.onWindLevelChanged(level);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcDefrostStateChanged(int area, int state) {
            BydLogHelper.logV("onAcDefrostStateChanged", "area = " + area + " , state : " + state);
            if (area != 1) {
                if (area == 2) {
                    HvacControllerImpl.this.onRearDefrostChanged(state);
                }
            } else if (state == 1 || state == 0) {
                HvacControllerImpl.this.onFrontDefrostChanged(state);
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcVentilationStateChanged(int state) {
            HvacControllerImpl.this.onVentilationStateChanged(state);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcTemperatureControlModeChanged(int mode) {
            HvacControllerImpl.this.onControlModeChanged(mode);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcCycleModeChanged(int mode) {
            HvacControllerImpl.this.onCycleModeChanged(mode);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcFaultNumShownStateChanged(int state) {
            HvacControllerImpl.this.onFaultNumShownStateChanged(state);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcMaxCoolingStateChanged(int state) {
            HvacControllerImpl.this.onMaxCoolingStateChanged(state);
        }
    };
    private final AbsBYDAutoBodyworkListener bodyworkDeviceCallback = new AbsBYDAutoBodyworkListener() { // from class: com.android.systemui.statusbar.policy.HvacControllerImpl.3
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int level) {
            BydLogHelper.logW("AbsBYDAutoBodyworkListener", "HvacControllerImpl onPowerLevelChanged level = " + level);
            HvacControllerImpl.this.onPowerChanged(level);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onMessage5sOnlineStateChanged(int id, int state) {
            HvacControllerImpl.this.notifyMessage5sOnlineStateChanged(id, state);
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int id, int state) {
            HvacControllerImpl.this.notifyHasMessageChanged(id, state);
        }
    };

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public HvacControllerImpl(Context context) {
        BydLogHelper.logW(TAG, "HvacControllerImpl context");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        initHvacControl();
        mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(applicationContext);
        mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(applicationContext);
        registerHvacCallback();
    }

    public final void initHvacControl() {
        DeviceManager.getInstance().addCallback(this.deviceManagerListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrontDefrostChanged(int value) {
        BydLogHelper.logW(TAG, "onFrontDefrostChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            for (HvacController.Callback callback : this.mCallbacks) {
                boolean z = true;
                if (value != 1) {
                    z = false;
                }
                callback.onFrontDefrostChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRearDefrostChanged(int value) {
        BydLogHelper.logW(TAG, "onRearDefrostChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            for (HvacController.Callback callback : this.mCallbacks) {
                boolean z = true;
                if (value != 1) {
                    z = false;
                }
                callback.onRearDefrostChanged(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onVentilationStateChanged(int value) {
        BydLogHelper.logW(TAG, "onVentilationStateChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAcVentilationStateChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onControlModeChanged(int value) {
        BydLogHelper.logW(TAG, "onControlModeChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onAcTemperatureControlModeChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCycleModeChanged(int value) {
        BydLogHelper.logW(TAG, "onSyncChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onCycleModeChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFaultNumShownStateChanged(int value) {
        BydLogHelper.logW(TAG, "onFaultNumShownStateChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onFaultNumShownStateChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onMaxCoolingStateChanged(int value) {
        BydLogHelper.logW(TAG, "onMaxCoolingStateChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onMaxCoolingStateChanged(value);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrontLeftTempChanged(int leftTemp) {
        BydLogHelper.logW(TAG, "onFrontLeftTempChanged", "leftTemp = " + leftTemp);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onFrontLeftTempChanged(leftTemp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFrontRightTempChanged(int rightTemp) {
        BydLogHelper.logW(TAG, "onFrontRightTempChanged", "rightTemp = " + rightTemp);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onFrontRightTempChanged(rightTemp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindLevelChanged(int windLevel) {
        BydLogHelper.logW(TAG, "onWindLevelChanged", "windLevel = " + windLevel);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWindLevelChanged(windLevel);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWindModeChanged(int mode) {
        BydLogHelper.logW(TAG, "onWindModeChanged", "mode = " + mode);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onWindModeChanged(mode);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onHvacPowerChanged(int value) {
        BydLogHelper.logW(TAG, "onHvacPowerChanged", "value = " + value);
        synchronized (this.mCallbacks) {
            for (HvacController.Callback callback : this.mCallbacks) {
                boolean z = true;
                if (value != 1) {
                    z = false;
                }
                callback.onHvacPower(z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUnitChanged(int value) {
        BydLogHelper.logW(TAG, "onUnitChanged", "value = " + value);
        this.mUnit = value;
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onTemperatureUnitChanged(value);
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getFrontLeftTempValue() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getFrontLeftTempValue", "mBYDAutoAcDevice == null ");
            return 0;
        }
        int temprature = bYDAutoAcDevice.getTemprature(1);
        BydLogHelper.logW(TAG, "getTemprature", "主驾温度 = " + temprature);
        if (temprature == 0) {
            int i = this.mUnit;
            if (i == 1) {
                temprature = 25;
            } else if (i == 0) {
                temprature = 77;
            }
        }
        BydLogHelper.logW(TAG, "getTemprature", "value = " + temprature);
        return temprature;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setFrontLeftTempValue(int value, int unit) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setFrontLeftTempValue", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setFrontLeftTempValue", "value = " + value);
        return mBYDAutoAcDevice.setAcTemperature(1, value, 0, unit);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getFrontRightTempValue() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getFrontRightTempValue", "mBYDAutoAcDevice == null ");
            return 0;
        }
        int temprature = bYDAutoAcDevice.getTemprature(2);
        BydLogHelper.logW(TAG, "getTemprature", "副驾温度 = " + temprature);
        if (temprature == 0) {
            int i = this.mUnit;
            if (i == 1) {
                temprature = 25;
            } else if (i == 0) {
                temprature = 77;
            }
        }
        BydLogHelper.logW(TAG, "getTemprature", "value = " + temprature);
        return temprature;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setFrontRightTempValue(int value, int unit) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setFrontRightTempValue", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setFrontRightTempValue", "value = " + value);
        return mBYDAutoAcDevice.setAcTemperature(2, value, 0, unit);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcWindLevel() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcWindLevel", "mBYDAutoAcDevice == null ");
            return 0;
        }
        int acWindLevel = bYDAutoAcDevice.getAcWindLevel();
        BydLogHelper.logW(TAG, "getAirWindLevel", "windLevel = " + acWindLevel);
        if (acWindLevel < 0) {
            return 3;
        }
        return acWindLevel;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setAcWindLevel(int level) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setAcWindLevel", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setAirWindLevel", "level = " + level);
        return mBYDAutoAcDevice.setAcWindLevel(0, level);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcCycleMode() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcCycleMode", "mBYDAutoAcDevice == null ");
            return 0;
        }
        int acCycleMode = bYDAutoAcDevice.getAcCycleMode();
        BydLogHelper.logW(TAG, "getAcCycleMode", "cycleMode = " + acCycleMode);
        return acCycleMode;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setAcCycleMode(int mode) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setAcCycleMode", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setAcCycleMode", "mode = " + mode);
        return mBYDAutoAcDevice.setAcCycleMode(0, mode);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public boolean isFrontDefrostEnabled() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("isFrontDefrostEnabled", "mBYDAutoAcDevice == null ");
            return false;
        }
        int acDefrostState = bYDAutoAcDevice.getAcDefrostState(1);
        BydLogHelper.logW(TAG, "isFrontDefrostEnabled", "value = " + acDefrostState);
        return acDefrostState == 1;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setFrontDefrostEnabled(boolean z) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setFrontDefrostEnabled", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setFrontDefrostEnabled", "enabled = " + z);
        return mBYDAutoAcDevice.setAcDefrostState(0, 1, z ? 1 : 0);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcWindMode() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcWindMode", "mBYDAutoAcDevice == null ");
            return 0;
        }
        int acWindMode = bYDAutoAcDevice.getAcWindMode();
        BydLogHelper.logW(TAG, "getAirWindMode", "windMode = " + acWindMode);
        return acWindMode;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getUnit() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getUnit", "mBYDAutoAcDevice == null ");
            return 0;
        }
        this.mUnit = bYDAutoAcDevice.getTemperatureUnit();
        BydLogHelper.logW(TAG, "getUnit", "mUnit = " + this.mUnit);
        return this.mUnit;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getPowerLevel() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = mBYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            BydLogHelper.logE("getPowerLevel", "mBYDAutoBodyworkDevice == null ");
            return 0;
        }
        return bYDAutoBodyworkDevice.getPowerLevel();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setAcTemperatureControlMode(int mode) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setAcTemperatureControlMode", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setAcTemperatureControlMode", "mode = " + mode);
        return mBYDAutoAcDevice.setAcTemperatureControlMode(0, mode);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcTemperatureControlMode() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcTemperatureControlMode", "mBYDAutoAcDevice == null ");
            return 0;
        }
        return bYDAutoAcDevice.getAcTemperatureControlMode();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setAcVentilationState(int state) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setAcVentilationState", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setAcVentilationState", "state = " + state);
        return mBYDAutoAcDevice.setAcVentilationState(0, state);
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcVentilationState() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcVentilationState", "mBYDAutoAcDevice == null ");
            return 0;
        }
        return bYDAutoAcDevice.getAcVentilationState();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcFaultNumShownState() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcFaultNumShownState", "mBYDAutoAcDevice == null ");
            return 1;
        }
        return bYDAutoAcDevice.getAcFaultNumShownState();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public void refreshHvac() {
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onRefreshHvac();
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcWindModeNum() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcWindModeNum", "mBYDAutoAcDevice == null ");
            return 1;
        }
        return bYDAutoAcDevice.getAcWindModeNum();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcMaxCoolingState() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcMaxCoolingState", "mBYDAutoAcDevice == null ");
            return 0;
        }
        return bYDAutoAcDevice.getAcMaxCoolingState();
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcHeatingButtonConfig() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(TAG, "getAcHeatingState mBYDAutoAcDevice == null !!! ");
            return 0;
        }
        int acHeatingButtonConfig = bYDAutoAcDevice.getAcHeatingButtonConfig();
        BydLogHelper.logW(TAG, "getAcHeatingButtonConfig acHeatingButtonConfig = " + acHeatingButtonConfig);
        return acHeatingButtonConfig;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getTempRegulatingAccuracy() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logW(TAG, "getTempRegulatingAccuracy mBYDAutoAcDevice == null !!! ");
            return 0;
        }
        int tempRegulatingAccuracy = bYDAutoAcDevice.getTempRegulatingAccuracy();
        BydLogHelper.logW(TAG, "getTempRegulatingAccuracy tempRegulatingAccuracy = " + tempRegulatingAccuracy);
        return tempRegulatingAccuracy;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int getAcType() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("getAcType", "mBYDAutoAcDevice == null ");
            return 1;
        }
        int acType = bYDAutoAcDevice.getAcType();
        BydLogHelper.logW(TAG, "getAcType", "acType = " + acType);
        if (acType == 0) {
            return 1;
        }
        return acType;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public boolean isHvacPower() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice == null) {
            BydLogHelper.logE("isHvacPower", "mBYDAutoAcDevice == null ");
            return false;
        }
        int acStartState = bYDAutoAcDevice.getAcStartState();
        BydLogHelper.logW(TAG, "isHvacPower", "value = " + acStartState);
        return acStartState == 1;
    }

    @Override // com.android.systemui.statusbar.policy.HvacController
    public int setHvacPower(boolean enabled) {
        if (mBYDAutoAcDevice == null) {
            BydLogHelper.logE("setHvacPower", "mBYDAutoAcDevice == null ");
            return 0;
        }
        BydLogHelper.logW(TAG, "setHvacPower", "enabled = " + enabled);
        if (enabled) {
            return mBYDAutoAcDevice.start(0);
        }
        return mBYDAutoAcDevice.stop(0);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(HvacController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    BydLogHelper.logW(TAG, "addCallback", "callback = " + callback);
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(HvacController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback == null) {
                return;
            }
            BydLogHelper.logW(TAG, "removeCallback", "callback = " + callback);
            this.mCallbacks.remove(callback);
        }
    }

    private void registerHvacCallback() {
        BYDAutoAcDevice bYDAutoAcDevice = mBYDAutoAcDevice;
        if (bYDAutoAcDevice != null) {
            bYDAutoAcDevice.registerListener(this.absBYDAutoAcListener);
            mBYDAutoBodyworkDevice.registerListener(this.bodyworkDeviceCallback);
        }
    }

    private void unregisterHvacCallback() {
        mBYDAutoAcDevice.unregisterListener(this.absBYDAutoAcListener);
        mBYDAutoBodyworkDevice.unregisterListener(this.bodyworkDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPowerChanged(int level) {
        BydLogHelper.logW(TAG, "onPowerChanged", "level = " + level);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onPowerLevelChanged(level);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMessage5sOnlineStateChanged(int id, int state) {
        BydLogHelper.logV("notifyMessage5sOnlineStateChanged", "id = " + id + " , state : " + state);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onMessage5sOnlineStateChanged(id, state);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHasMessageChanged(int id, int state) {
        BydLogHelper.logV("notifyHasMessageChanged", "id = " + id + " , state : " + state);
        synchronized (this.mCallbacks) {
            Iterator<HvacController.Callback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onHasMessageChanged(id, state);
            }
        }
    }
}
