package com.byd.vrassistant.manager;

import android.content.Context;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.yun.AbsBYDAutoYunListener;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.text.TextUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.utils.ExecutorUtils;
import com.byd.vrassistant.utils.RLog;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveManager {
    private static final String AnaLogSample = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String REGEX_VIN = "^[A-Z0-9]+$";
    private static final String TAG = "NEU_ActiveManager";
    private static final String VIN_PREFIX = "BYD";
    private boolean isAnalog = false;
    private String mVirtualVIN = null;
    private String mDeviceId = null;
    private String mUserId = null;
    private boolean willQuit = false;
    private BYDAutoYunDevice mAutoYunDevice = null;
    private BYDAutoTestDevice mAutoTestDevice = null;
    private BYDAutoPowerDevice mBYDAutoPowerDevice = null;
    private Context mContext = null;
    private boolean mActivatedSuccess = false;
    private AbsBYDAutoPowerListener mAbsBYDAutoPowerListener = new AbsBYDAutoPowerListener() { // from class: com.byd.vrassistant.manager.ActiveManager.1
        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            if (i == 1) {
                RLog.i("AbsBYDAutoPowerListener", "POWER_CTL_STATE_OFF");
            } else if (i == 3) {
                RLog.i("AbsBYDAutoPowerListener", "POWER_CTL_STATE_ON mActivatedSuccess=" + ActiveManager.this.mActivatedSuccess);
                if (ActiveManager.this.mActivatedSuccess) {
                    return;
                }
                ActiveManager.this.executeActivate();
            }
        }
    };
    private AbsBYDAutoYunListener mAutoYunlistener = new AbsBYDAutoYunListener() { // from class: com.byd.vrassistant.manager.ActiveManager.2
        @Override // android.hardware.bydauto.yun.AbsBYDAutoYunListener
        public void onAppActivateResult(int i, int i2) {
            super.onAppActivateResult(i, i2);
            if (i == 1) {
                ActiveManager.this.mActivatedSuccess = true;
                RLog.i("AbsBYDAutoYunListener", "BYDAutoYun activate success");
                SPUtils.getInstance().put(Constant.SP_KEY_ACTIVE, true);
                SPUtils.getInstance().put(Constant.SP_KEY_VIRTUALVIN, ActiveManager.this.mVirtualVIN);
                SPUtils.getInstance().put(Constant.SP_KEY_ANALOG, false);
                return;
            }
            RLog.i("AbsBYDAutoYunListener", "BYDAutoYun activate fail");
        }
    };
    private AbsBYDAutoTestListener mAutoTestListener = new AbsBYDAutoTestListener() { // from class: com.byd.vrassistant.manager.ActiveManager.3
        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onCloudServiceConnectStateChanged(int i) {
            super.onCloudServiceConnectStateChanged(i);
            RLog.i("AbsBYDAutoTestListener", "AbsBYDAutoTestListener change");
            if (i != 2 || ActiveManager.this.mActivatedSuccess) {
                return;
            }
            ActiveManager.this.activateAssistant(BydVoiceApplication.getContext());
        }
    };

    private static final class SingletonHolder {
        private static final ActiveManager instance = new ActiveManager();

        private SingletonHolder() {
        }
    }

    public static ActiveManager get() {
        return SingletonHolder.instance;
    }

    private void init() {
        BydVoiceApplication context = BydVoiceApplication.getContext();
        this.mContext = context;
        try {
            BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(context);
            this.mBYDAutoPowerDevice = bYDAutoPowerDevice;
            bYDAutoPowerDevice.registerListener(this.mAbsBYDAutoPowerListener);
            BYDAutoYunDevice bYDAutoYunDevice = BYDAutoYunDevice.getInstance(this.mContext);
            this.mAutoYunDevice = bYDAutoYunDevice;
            bYDAutoYunDevice.registerListener(this.mAutoYunlistener);
            BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(this.mContext);
            this.mAutoTestDevice = bYDAutoTestDevice;
            bYDAutoTestDevice.registerListener(this.mAutoTestListener);
        } catch (Exception e) {
            RLog.e(TAG, "ActiveManager init error", e);
        }
    }

    public void active() {
        this.mVirtualVIN = SPUtils.getInstance().getString(Constant.SP_KEY_VIRTUALVIN);
        if (SPUtils.getInstance().getBoolean(Constant.SP_KEY_ACTIVE)) {
            RLog.i(TAG, "active already finish,exit active.");
            return;
        }
        init();
        RLog.i(TAG, "enter active process >>>>>");
        executeActivate();
    }

    public synchronized String getDeviceId() {
        boolean zIsMatch = RegexUtils.isMatch(REGEX_VIN, this.mVirtualVIN);
        if (TextUtils.isEmpty(this.mVirtualVIN)) {
            RLog.i(TAG, "mVirtualVIN======null");
        } else {
            RLog.i(TAG, "mVirtualVIN======" + this.mVirtualVIN);
            RLog.i(TAG, "mVirtualVIN.length()=====" + this.mVirtualVIN.length());
            RLog.i(TAG, "mVirtualVIN.isRegex=====" + zIsMatch);
        }
        if (TextUtils.isEmpty(this.mVirtualVIN) || "00000000000000000".equals(this.mVirtualVIN) || this.mVirtualVIN.length() > 64 || !zIsMatch) {
            this.mVirtualVIN = createAnalogVirtualVIN();
            this.isAnalog = true;
            SPUtils.getInstance().put(Constant.SP_KEY_VIRTUALVIN, this.mVirtualVIN);
            SPUtils.getInstance().put(Constant.SP_KEY_ANALOG, this.isAnalog);
        }
        return this.mVirtualVIN;
    }

    public synchronized String getUserId() {
        boolean zIsMatch = RegexUtils.isMatch(REGEX_VIN, this.mVirtualVIN);
        if (TextUtils.isEmpty(this.mVirtualVIN)) {
            RLog.i(TAG, "mVirtualVIN======null");
        } else {
            RLog.i(TAG, "mVirtualVIN======" + this.mVirtualVIN);
            RLog.i(TAG, "mVirtualVIN.length()=====" + this.mVirtualVIN.length());
            RLog.i(TAG, "mVirtualVIN.isRegex=====" + zIsMatch);
        }
        if (TextUtils.isEmpty(this.mVirtualVIN) || "00000000000000000".equals(this.mVirtualVIN) || this.mVirtualVIN.length() > 64 || !zIsMatch) {
            this.mVirtualVIN = createAnalogVirtualVIN();
            this.isAnalog = true;
            SPUtils.getInstance().put(Constant.SP_KEY_VIRTUALVIN, this.mVirtualVIN);
            SPUtils.getInstance().put(Constant.SP_KEY_ANALOG, this.isAnalog);
        }
        return this.mVirtualVIN;
    }

    public String createAnalogVirtualVIN() {
        String string = Long.toString(System.currentTimeMillis());
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <= 32 - string.length(); i++) {
            stringBuffer.append(AnaLogSample.charAt(random.nextInt(36)));
        }
        RLog.i(TAG, "createAnalogVirtualVIN======BYD" + string + stringBuffer.toString());
        return VIN_PREFIX + string + stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00eb, code lost:
    
        r1 = r4.getString("internationaldomain");
        r15 = android.hardware.bydauto.yun.ActivateInfo.NationalRegion.valueOf(r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void activateAssistant(android.content.Context r15) {
        /*
            Method dump skipped, instruction units count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.manager.ActiveManager.activateAssistant(android.content.Context):void");
    }

    public void executeActivate() {
        ExecutorUtils.execute(new Runnable() { // from class: com.byd.vrassistant.manager.ActiveManager.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ActiveManager.this.activateAssistant(BydVoiceApplication.getContext());
            }
        });
    }

    public void forceQuit() {
        RLog.i(TAG, "ActiveManager forceQuit");
        this.mAutoTestDevice.unregisterListener(this.mAutoTestListener);
        this.mAutoYunDevice.unregisterListener(this.mAutoYunlistener);
        this.mBYDAutoPowerDevice.unregisterListener(this.mAbsBYDAutoPowerListener);
        this.willQuit = true;
    }
}
