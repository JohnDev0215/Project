package com.ts.appservice.settings.impl.qnx;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.ts.lib.qnx.IQnxCallBack;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.qnxmanager.QnxManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsQnxService {
    private static final String ADAS_CENTER_DISPLAY_MODE = "adasCenterDisplay";
    private static final String ADAS_STATUS_NUMBER = "adasStatusNumber";
    private static final String CENTER_DISPLAY_MODE = "centerDisplay";
    private static final String CHIME_SETTING_VALUE = "SoundType";
    private static final int CHIME_SOUND = 10;
    private static final int CHIME_SOUND_CMD = 6;
    private static final String CHIME_SOUND_TYPE = "chimeSound";
    private static final String CLOCK_FORMAT_VALUE = "ClockFormat";
    private static final String CLUSTER_SETTING = "clusterSetting";
    private static final int CLUSTER_SETTING_MSG_TYPE = 9;
    private static final String CLUSTER_THEME_NUMBER = "clusterThemeNumber";
    private static final String CPAA_CONNECT_STATE = "cpaaConnectState";
    private static final int CPAA_CONNECT_STATE_KEY = 22;
    private static final String DAY_NIGHT_MODE = "dayNightMode";
    private static final String INS_DISPLAY_NUMBER = "insDisPlayNumber";
    private static final String LANGUAGE_VALUE = "LanguageSet";
    private static final String NAVI_INSTALL_STATE = "naviInstallState";
    private static final int NAVI_INSTALL_STATE_KEY = 21;
    private static final String OVERSEAS_MAP_SUPPLIER = "overseasMapSupplier";
    private static final int OVERSEAS_MAP_SUPPLIER_KEY = 19;
    private static final String QNX_MSG_TYPE = "msgType";
    private static final String QNX_SUB_TYPE = "subtype";
    private static final String SCREEN_ORIENTATION_MODE = "screenOrientation";
    private static final int SETTING_CLOCK = 1;
    private static final int SETTING_HOTKEY = 3;
    private static final int SETTING_HOTKEYADAS_CMD = 3;
    private static final int SETTING_HOTKEYINSDISPLAY_CMD = 2;
    private static final int SETTING_HOTKEYTHEME_CMD = 4;
    private static final int SETTING_LANGUAGE = 8;
    private static final int SETTING_SCREEN_ORIENTATION = 17;
    private static final int SETTING_THEME = 0;
    private static final int SETTING_THEME_CMD = 0;
    private static final String SETTING_THEME_VALUE = "SettingTheme";
    private static final int SETTING_VERSATILE_THEME = 18;
    private static final String TAG = SettingsQnxService.class.getSimpleName();
    private static final String THEME_MODE = "themeMode";
    private static final String THEME_SETTING = "ThemeSetting";
    private static final String THEME_SET_NUMBER = "themeSetNumber";
    private static final String VEHICLE_ACC_TIMER = "VehicleAccTimer";
    private static final String VEHICLE_ADAS_STAT = "VehicleAdasStat";
    private static final String VEHICLE_INS_DISPLAY = "VehicleINSDisplay";
    private static final String VEHICLE_THEME_TYPE = "VehicleThemeType";
    private static final String VERSATILE_THEME_MODE = "versatileTheme";
    private static volatile SettingsQnxService sInstance;
    private Context mContext;
    private QnxManager mQnxManager;
    private List<SettingsQnxListener> mSettingsQnxListenerList = new ArrayList();
    private IQnxCallBack.Stub mQnxCallback = new IQnxCallBack.Stub() { // from class: com.ts.appservice.settings.impl.qnx.SettingsQnxService.1
        public void onMessageChanged(Bundle bundle) {
            LogUtil.debug(SettingsQnxService.TAG, "register onMessageChanged bundle= " + bundle.getInt(SettingsQnxService.QNX_SUB_TYPE));
            if (bundle != null && bundle.containsKey(SettingsQnxService.QNX_SUB_TYPE)) {
                int i = bundle.getInt(SettingsQnxService.QNX_SUB_TYPE);
                if (i == 0) {
                    if (bundle.containsKey(SettingsQnxService.THEME_SET_NUMBER)) {
                        int dayNightTheme = bundle.getInt(SettingsQnxService.THEME_SET_NUMBER);
                        LogUtil.debug(SettingsQnxService.TAG, "onMessageChanged dayNightTheme= " + dayNightTheme);
                        SettingsQnxService.this.setSharedPreferencesValue(SettingsQnxService.CLUSTER_SETTING, SettingsQnxService.DAY_NIGHT_MODE, dayNightTheme);
                        for (SettingsQnxListener listener : SettingsQnxService.this.mSettingsQnxListenerList) {
                            listener.onQnxMessageChanged(1, dayNightTheme);
                        }
                        return;
                    }
                    return;
                }
                if (i == 6) {
                    if (bundle.containsKey(SettingsQnxService.CHIME_SOUND_TYPE)) {
                        int chimeSoundType = bundle.getInt(SettingsQnxService.CHIME_SOUND_TYPE);
                        LogUtil.debug(SettingsQnxService.TAG, "onMessageChanged chimeSoundType= " + chimeSoundType);
                        SettingsQnxService.this.setSharedPreferencesValue(SettingsQnxService.CLUSTER_SETTING, SettingsQnxService.CHIME_SOUND_TYPE, chimeSoundType);
                        for (SettingsQnxListener listener2 : SettingsQnxService.this.mSettingsQnxListenerList) {
                            listener2.onQnxMessageChanged(5, chimeSoundType);
                        }
                        return;
                    }
                    return;
                }
                if (i == 2) {
                    if (bundle.containsKey(SettingsQnxService.INS_DISPLAY_NUMBER)) {
                        int insDisPlayMode = bundle.getInt(SettingsQnxService.INS_DISPLAY_NUMBER);
                        LogUtil.debug(SettingsQnxService.TAG, "onMessageChanged insDisPlayMode= " + insDisPlayMode);
                        SettingsQnxService.this.setSharedPreferencesValue(SettingsQnxService.CLUSTER_SETTING, SettingsQnxService.CENTER_DISPLAY_MODE, insDisPlayMode);
                        for (SettingsQnxListener listener3 : SettingsQnxService.this.mSettingsQnxListenerList) {
                            listener3.onQnxMessageChanged(3, insDisPlayMode);
                        }
                        return;
                    }
                    return;
                }
                if (i != 3) {
                    if (i == 4 && bundle.containsKey(SettingsQnxService.CLUSTER_THEME_NUMBER)) {
                        int clusterTheme = bundle.getInt(SettingsQnxService.CLUSTER_THEME_NUMBER);
                        LogUtil.debug(SettingsQnxService.TAG, "onMessageChanged clusterTheme= " + clusterTheme);
                        SettingsQnxService.this.setSharedPreferencesValue(SettingsQnxService.THEME_SETTING, SettingsQnxService.THEME_MODE, clusterTheme);
                        for (SettingsQnxListener listener4 : SettingsQnxService.this.mSettingsQnxListenerList) {
                            listener4.onQnxMessageChanged(2, clusterTheme);
                        }
                        return;
                    }
                    return;
                }
                if (bundle.containsKey(SettingsQnxService.ADAS_STATUS_NUMBER)) {
                    int adasCenterDisMode = bundle.getInt(SettingsQnxService.ADAS_STATUS_NUMBER);
                    LogUtil.debug(SettingsQnxService.TAG, "onMessageChanged adasCenterDisMode= " + adasCenterDisMode);
                    SettingsQnxService.this.setSharedPreferencesValue(SettingsQnxService.CLUSTER_SETTING, SettingsQnxService.ADAS_CENTER_DISPLAY_MODE, adasCenterDisMode);
                    for (SettingsQnxListener listener5 : SettingsQnxService.this.mSettingsQnxListenerList) {
                        listener5.onQnxMessageChanged(4, adasCenterDisMode);
                    }
                }
            }
        }
    };

    public SettingsQnxService(Context context) {
        this.mContext = context;
        initQnxManager();
    }

    public static SettingsQnxService getInstance(Context context) {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (SettingsQnxService.class) {
            if (sInstance == null) {
                sInstance = new SettingsQnxService(context);
            }
        }
        return sInstance;
    }

    private void initQnxManager() {
        QnxManager qnxManager = QnxManager.getInstance();
        this.mQnxManager = qnxManager;
        qnxManager.registerCallback(9, 0, this.mQnxCallback);
    }

    public void subscribeClusterCallback(SettingsQnxListener listener) {
        synchronized (this.mSettingsQnxListenerList) {
            if (!this.mSettingsQnxListenerList.contains(listener) && listener != null) {
                this.mSettingsQnxListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeClusterCallback ok --> listener size: " + this.mSettingsQnxListenerList.size());
            }
        }
    }

    public void unSubscribeClusterCallback(SettingsQnxListener listener) {
        synchronized (this.mSettingsQnxListenerList) {
            if (listener != null) {
                if (this.mSettingsQnxListenerList.contains(listener)) {
                    this.mSettingsQnxListenerList.remove(listener);
                    LogUtil.debug(TAG, "subscribeClusterCallback ok --> listener size: " + this.mSettingsQnxListenerList.size());
                }
            }
        }
    }

    public int setINSTheme(int value) {
        LogUtil.info(TAG, "setINSTheme value" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(VEHICLE_THEME_TYPE, value);
        boolean status = this.mQnxManager.sendMessage(9, 3, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setINSAdasCenterDisplay(int value) {
        LogUtil.info(TAG, "setINSAdasCenterDisplay value :" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(VEHICLE_ADAS_STAT, value);
        boolean status = this.mQnxManager.sendMessage(9, 3, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setINSCenterDisplay(int value) {
        LogUtil.info(TAG, "setINSCenterDisplay value :" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(VEHICLE_INS_DISPLAY, value);
        boolean status = this.mQnxManager.sendMessage(9, 3, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setDayNightMode(int mode) {
        LogUtil.info(TAG, "setDayNightMode mode :" + mode);
        Bundle bundle = new Bundle();
        bundle.putInt(SETTING_THEME_VALUE, mode);
        boolean status = this.mQnxManager.sendMessage(9, 0, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setTimeFormat(int value) {
        LogUtil.info(TAG, "setTimeFormat value :" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(CLOCK_FORMAT_VALUE, value);
        boolean status = this.mQnxManager.sendMessage(9, 1, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setCarTipsSoundType(int value) {
        LogUtil.info(TAG, "setCarTipsSoundType value :" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(CHIME_SETTING_VALUE, value);
        boolean status = this.mQnxManager.sendMessage(9, 10, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setLanguage(int value) {
        LogUtil.info(TAG, "setLanguage value :" + value);
        Bundle bundle = new Bundle();
        bundle.putInt(LANGUAGE_VALUE, value);
        boolean status = this.mQnxManager.sendMessage(9, 8, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setSpeedUpCountState(int state) {
        LogUtil.info(TAG, "setSpeedUpCountState value :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(VEHICLE_ACC_TIMER, state);
        boolean status = this.mQnxManager.sendMessage(9, 3, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setScreenOrientationState(int state) {
        LogUtil.warning(TAG, "setScreenOrientationState state :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(SCREEN_ORIENTATION_MODE, state);
        boolean status = this.mQnxManager.sendMessage(9, 17, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setVersatileThemeState(int state) {
        LogUtil.warning(TAG, "setVersatileThemeState state :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(VERSATILE_THEME_MODE, state);
        boolean status = this.mQnxManager.sendMessage(9, 18, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setOverseasMapSupplier(int state) {
        LogUtil.info(TAG, "setOverseasMapSupplier value :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(OVERSEAS_MAP_SUPPLIER, state);
        boolean status = this.mQnxManager.sendMessage(9, 19, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setNaviInstallState(int state) {
        LogUtil.warning(TAG, "setNaviInstallState state :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(NAVI_INSTALL_STATE, state);
        boolean status = this.mQnxManager.sendMessage(9, 21, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int setCpaaConnectState(int state) {
        LogUtil.warning(TAG, "setCpaaConnectState state :" + state);
        Bundle bundle = new Bundle();
        bundle.putInt(CPAA_CONNECT_STATE, state);
        boolean status = this.mQnxManager.sendMessage(9, 22, (String) null, bundle);
        return status ? 0 : -1;
    }

    public int getInsAdasCenterDisplay() {
        return getSharedPreferencesValue(CLUSTER_SETTING, ADAS_CENTER_DISPLAY_MODE);
    }

    public int getDayNightMode() {
        return getSharedPreferencesValue(CLUSTER_SETTING, DAY_NIGHT_MODE);
    }

    public int getInsCenterDisplay() {
        return getSharedPreferencesValue(CLUSTER_SETTING, CENTER_DISPLAY_MODE);
    }

    public int getCarTipsSoundType() {
        return getSharedPreferencesValue(CLUSTER_SETTING, CHIME_SOUND_TYPE);
    }

    public int getInsTheme() {
        return getSharedPreferencesValue(THEME_SETTING, THEME_MODE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSharedPreferencesValue(String name, String keyName, int value) {
        SharedPreferences sp = this.mContext.getSharedPreferences(name, 0);
        sp.edit().putInt(keyName, value).commit();
    }

    public int getSharedPreferencesValue(String name, String keyName) {
        SharedPreferences sp = this.mContext.getSharedPreferences(name, 0);
        return sp.getInt(keyName, 0);
    }
}
