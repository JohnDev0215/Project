package com.ts.appservice.settings.impl.appconfig;

import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.LauncherManager;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Xml;
import com.android.common.speech.LoggingEvents;
import com.android.internal.util.XmlUtils;
import com.ts.appservice.settings.impl.language.ILanguageServiceBinder;
import com.ts.appservice.settings.impl.qnx.SettingsQnxService;
import com.ts.lib.settings.appconfig.IAppConfigSettingsCallback;
import com.ts.lib.settings.appconfig.IAppConfigSettingsService;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class IAppConfigServiceBinder extends IAppConfigSettingsService.Stub {
    private static final String AMAZON_PROC_NAME = "com.amazon.mp3.automotiveOS";
    private static final String ANDROIDAUTO_PROC_NAME = "com.ts.androidauto.app";
    private static final String ANDROIDAUTO_SER_PROC_NAME = "com.ts.androidauto.projectionservice";
    private static final String APPSTORE_PROC_NAME = "com.byd.overseaappstore";
    private static final String ATTR_ID = "id";
    private static final String ATTR_LANGUAGE = "language";
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PACKAGE = "package";
    private static final String AUTOMAP_PROC_NAME = "com.byd.automap";
    private static final String AVM_DEFAULT = "-1";
    private static final String AVM_ISP = "persist.vendor.bosch.cfg.supplies.avm.camera.isp";
    private static final String AVM_NAME = "persist.vendor.bosch.cfg.supplies.avm.name";
    private static final String AVM_PROC_NAME = "com.byd.avm";
    private static final String BEFORE_COUNTRY_CODE = "before_country_code";
    private static final String BOOT_COMPLETE_STATUS = "sys.boot_completed";
    private static final String BOOT_COMPLETE_SUCCESS = "1";
    private static final String CAMERA_ISP = "persist.vendor.bosch.cfg.supplies.ims.camera.isp";
    private static final String CAMERA_NAME = "persist.vendor.bosch.cfg.supplies.ims.name";
    private static final String CAMERA_PROC_NAME = "com.byd.auto_camera";
    private static final String CARPLAY_APP_PROC_NAME = "com.ts.carplay.app";
    private static final String CARPLAY_PROC_NAME = "com.ts.carplay";
    private static final String CAR_MEDIA_PROC_NAME = "com.android.car.media";
    private static final String COUNTRYCODE_ERROR = "COUNTRYCODE_ERROR";
    private static final String CPAA_INSTALL_STATE_AA = "2";
    private static final String CPAA_INSTALL_STATE_CP = "1";
    private static final String CPAA_INSTALL_STATE_NONE = "0";
    private static final String CPAA_STATUS = "persist.sys.cpaa.status";
    private static final String DCCONTROLLER_PROC_NAME = "com.ts.car.dcm.dccontroller";
    private static final String DEFAULT_LAUNCHER_NAME = "persist.sys.default_launcher_name";
    private static final String DM_SERVICE_PROC_NAME = "com.ts.dm.service";
    private static final String DVR_CATEGORY = "persist.vendor.bosch.cfg.supplies.dvr.camera.product.category";
    private static final String DVR_DEFAULT = "-1";
    private static final String DVR_ISP = "persist.vendor.bosch.cfg.supplies.dvr.camera.isp";
    private static final String DVR_LENS = "persist.vendor.bosch.cfg.supplies.dvr.camera.lens";
    private static final String DVR_NAME = "persist.vendor.bosch.cfg.supplies.dvr.name";
    private static final String DVR_PROC_NAME = "com.byd.dvr";
    private static final String DVR_RESOLUTION = "persist.vendor.bosch.cfg.supplies.dvr.camera.resolution";
    private static final String DVR_SERIALIZER = "persist.vendor.bosch.cfg.supplies.dvr.camera.serializer";
    private static final String DVR_VIDEO_FORMAT = "persist.vendor.bosch.cfg.supplies.dvr.camera.video.format";
    private static final String DVR_VIDEO_FORMAT_RATE = "persist.vendor.bosch.cfg.supplies.dvr.camera.video.frame.rate";
    private static final String EMANUAL_PROC_NAME = "com.byd.auto_emanual";
    private static final String FIRST_BOOT_KEY = "first_boot";
    private static final String HUAWEI_MAPS_PROC_NAME = "com.huawei.maps.auto.app";
    private static final String INSTALL_APP_CONFIG_PATH = "/system/etc/data/install-app-dev.xml";
    private static final String INSTALL_APP_CONFIG_PATH_MC_LEFT = "/system/etc/data/install-app-dev-mc-left.xml";
    private static final String INSTALL_APP_CONFIG_PATH_MR = "/system/etc/data/install-app-dev-mr.xml";
    private static final String INSTALL_APP_CONFIG_PATH_PA = "/system/etc/data/install-app-dev-pa.xml";
    private static final String INSTALL_APP_CONFIG_PATH_UKE = "/system/etc/data/install-app-dev-uke.xml";
    private static final String INSTALL_APP_CONFIG_PATH_UKE_LEFT = "/system/etc/data/install-app-dev-uke-left.xml";
    private static final String INSTALL_APP_CONFIG_PATH_UKE_RIGHT = "/system/etc/data/install-app-dev-uke-right.xml";
    private static final String JAPAN_NAVI_PROC_NAME = "net.zmap.znav.byd8155";
    private static final String KARAOKE_PROC_NAME = "com.stingray.karaoke.car.byd8155f";
    private static final String KIKA_PROC_NAME = "com.iqqijni.car.keyboard";
    private static final String KOREA_NAVI_COUNTRY_CODE = "82";
    private static final String KOREA_NAVI_PROC_NAME = "com.tmap.auto.byd";
    private static final String LANGUAGE_CONFIG = "/system/etc/data/language-config.xml";
    private static final String LAUNCHER_PROC_NAME = "com.android.launcher3";
    private static final int LOCAL_MAP_AND_CP_AA_INSTALL = 0;
    private static final int LOCAL_MAP_AND_CP_AA_NOT_INSTALL = 3;
    private static final int LOCAL_MAP_INSTALL_AND_CP_AA_NOT_INSTALLED = 1;
    private static final int LOCAL_MAP_NOT_INSTALL_AND_CP_AA_INSTALLED = 2;
    private static final String MAP_INSTALL_STATE = "1";
    private static final String MAP_STATE = "persist.vendor.byd.cfg.huc.mapstate";
    private static final String MC_LEFT = "MC左舵车";
    private static final String MC_LEFT_VALUE = "180";
    private static final String MR_RIGHT = "MR右舵车";
    private static final String MR_RIGHT_VALUE = "123";
    private static final int MSG_CHECK_LAUNCHER_STATUS = 2;
    private static final int MSG_CHECK_LAUNCHER_STATUS_DELAY = 500;
    private static final int MSG_EXECUTE_APP_CONFIG = 1;
    private static final int MSG_EXECUTE_APP_CONFIG_DELAY = 1000;
    private static final String MY_CAR = "com.byd.mycar";
    private static final String MY_CAR_TS = "com.byd.mycar.ts";
    private static final String NAVIGATION_PROC_NAME = "com.neusoft.na.navigation";
    private static final String NA_EHP_PROC_NAME = "com.neusoft.na.ehp";
    private static final String PA_RIGHT = "PA右舵车";
    private static final String PA_RIGHT_VALUE = "143";
    private static final String PERSIST_SYS_AUTO_TYPE = "persist.sys.AutoType";
    private static final String PERSIST_VENDOR_BYD_CFG_HUC_CONFIG_AREA = "persist.vendor.byd.cfg.huc.config.area";
    private static final String SCENE_MODE = "com.byd.scenemode";
    private static final String SPOTIFY_PROC_NAME = "com.spotify.music";
    private static final String SPOTIFY_SDK_PROC_NAME = "com.telenav.scoutivi.spotify.byd";
    private static final String TAG = IAppConfigServiceBinder.class.getSimpleName();
    private static final String TAG_COUNTRY_CODE = "countryCode";
    private static final String TANG = "唐EV";
    private static final String TANG_VALUE = "196";
    private static final int TYPE_ANDROIDAUTO = 2;
    private static final int TYPE_CARPLAY = 1;
    private static final String UEK_RIGHT = "UKE右舵车";
    private static final String UEK_RIGHT_VALUE = "208";
    private static final String UKE_LEFT = "UKE左舵车";
    private static final String UKE_LEFT_VALUE = "182";
    private static final String VRASSISTANT_PROC_NAME = "com.byd.vrassistant";
    private static final String VRDOWNLOAD_PROC_NAME = "com.byd.vrdownload";
    private static final String VRSETTING_PROC_NAME = "com.byd.vrsettings";
    private static final String VR_DOWNLOAD_XF_PROC_NAME = "com.byd.vrdownload.xf";
    private static final String VR_XF_IMAGE_PROC_NAME = "com.byd.vrassistant.xf.image";
    private static final String VR_XF_PROC_NAME = "com.byd.vrassistant.xf";
    private ActivityManager mActivityManager;
    private AppConfigSettingCallback mAppConfigSettingCallback;
    private MyBroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    private String mCountryCode;
    private CountryCodeConfigManager mCountryCodeConfigManager;
    private boolean mCpAaInstallStatus;
    private String mCpaaStatus;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mIsUserUnLocked;
    private File mLanguageConfigFilename;
    private ILanguageServiceBinder mLanguageServiceBinder;
    private String mLanguageValue;
    private LauncherManager mLauncherManager;
    private boolean mLocalMapInstallStatus;
    private String mModelsCode;
    private PackageManager mPackageManager;
    private SettingsQnxService mSettingsQnxService;
    private UserManager mUserManager;
    private File mInstallAppFilename = null;
    private List<String> mInstallAppList = new CopyOnWriteArrayList();
    private List<String> mInstallCpaaAppList = new CopyOnWriteArrayList();
    private List<String> mUnInstallCpaaAppList = new CopyOnWriteArrayList();
    private Map<String, String> mModesMap = new HashMap();

    public IAppConfigServiceBinder(Context context) {
        this.mContext = null;
        this.mPackageManager = null;
        this.mUserManager = null;
        this.mHandlerThread = null;
        this.mHandler = null;
        this.mIsUserUnLocked = false;
        this.mLanguageConfigFilename = null;
        this.mContext = context;
        this.mActivityManager = (ActivityManager) context.getSystemService("activity");
        initModelsMap();
        this.mAppConfigSettingCallback = new AppConfigSettingCallback(this.mContext);
        this.mPackageManager = context.getPackageManager();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_UNLOCKED");
        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        this.mBroadcastReceiver = myBroadcastReceiver;
        this.mContext.registerReceiver(myBroadcastReceiver, intentFilter);
        this.mCpaaStatus = SystemProperties.get(CPAA_STATUS);
        this.mLanguageConfigFilename = new File(LANGUAGE_CONFIG);
        this.mLanguageServiceBinder = new ILanguageServiceBinder(this.mContext);
        this.mSettingsQnxService = SettingsQnxService.getInstance(this.mContext);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mUserManager = (UserManager) this.mContext.getSystemService("user");
        }
        this.mLauncherManager = (LauncherManager) this.mContext.getApplicationContext().getSystemService("launcher");
        CountryCodeConfigManager countryCodeConfigManager = new CountryCodeConfigManager(this.mContext);
        this.mCountryCodeConfigManager = countryCodeConfigManager;
        this.mCountryCode = countryCodeConfigManager.getCountryCode();
        String modelsCode = getModelsCode();
        this.mModelsCode = modelsCode;
        initAppConfigFile(this.mCountryCode, modelsCode);
        this.mIsUserUnLocked = this.mUserManager.isUserUnlocked();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.appservice.settings.impl.appconfig.IAppConfigServiceBinder.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    IAppConfigServiceBinder iAppConfigServiceBinder = IAppConfigServiceBinder.this;
                    iAppConfigServiceBinder.mIsUserUnLocked = iAppConfigServiceBinder.mUserManager.isUserUnlocked();
                    LogUtil.error(IAppConfigServiceBinder.TAG, "executeAppConfig mIsUserUnLocked : " + IAppConfigServiceBinder.this.mIsUserUnLocked);
                    if (!IAppConfigServiceBinder.this.mIsUserUnLocked) {
                        if (IAppConfigServiceBinder.this.mHandler.hasMessages(1)) {
                            IAppConfigServiceBinder.this.mHandler.removeMessages(1);
                        }
                        IAppConfigServiceBinder.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
                        return;
                    }
                    IAppConfigServiceBinder iAppConfigServiceBinder2 = IAppConfigServiceBinder.this;
                    iAppConfigServiceBinder2.executeAppConfig(iAppConfigServiceBinder2.mCountryCode);
                    IAppConfigServiceBinder.this.checkAvmAppStatus();
                    IAppConfigServiceBinder.this.checkCameraAppStatus();
                    IAppConfigServiceBinder.this.checkDvrAppStatus();
                    IAppConfigServiceBinder iAppConfigServiceBinder3 = IAppConfigServiceBinder.this;
                    iAppConfigServiceBinder3.setDefaultLanguage(iAppConfigServiceBinder3.mCountryCode);
                    return;
                }
                if (msg.what == 2) {
                    IAppConfigServiceBinder iAppConfigServiceBinder4 = IAppConfigServiceBinder.this;
                    iAppConfigServiceBinder4.mIsUserUnLocked = iAppConfigServiceBinder4.mUserManager.isUserUnlocked();
                    boolean bootComplete = IAppConfigServiceBinder.this.isBootComplete();
                    LogUtil.error(IAppConfigServiceBinder.TAG, "checkLauncherAppStatus mIsUserUnLocked : " + IAppConfigServiceBinder.this.mIsUserUnLocked + " bootComplete :" + bootComplete);
                    if (IAppConfigServiceBinder.this.mIsUserUnLocked || bootComplete) {
                        IAppConfigServiceBinder.this.checkLauncherAppStatus();
                        return;
                    }
                    if (IAppConfigServiceBinder.this.mHandler.hasMessages(2)) {
                        IAppConfigServiceBinder.this.mHandler.removeMessages(2);
                    }
                    IAppConfigServiceBinder.this.mHandler.sendEmptyMessageDelayed(2, 500L);
                }
            }
        };
        if (this.mIsUserUnLocked || isBootComplete()) {
            checkLauncherAppStatus();
        } else {
            if (this.mHandler.hasMessages(2)) {
                this.mHandler.removeMessages(2);
            }
            this.mHandler.sendEmptyMessageDelayed(2, 500L);
        }
        if (this.mHandler.hasMessages(1)) {
            this.mHandler.removeMessages(1);
        }
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    }

    private void initModelsMap() {
        this.mModesMap.put(TANG_VALUE, TANG);
        this.mModesMap.put(UKE_LEFT_VALUE, UKE_LEFT);
        this.mModesMap.put(UEK_RIGHT_VALUE, UEK_RIGHT);
        this.mModesMap.put(MR_RIGHT_VALUE, MR_RIGHT);
        this.mModesMap.put(PA_RIGHT_VALUE, PA_RIGHT);
        this.mModesMap.put(MC_LEFT_VALUE, MC_LEFT);
    }

    private String getModelsCode() {
        String modelsCode = SystemProperties.get(PERSIST_SYS_AUTO_TYPE);
        if (modelsCode == null) {
            LogUtil.warning(TAG, "getModelsCode is null, return default value");
            return TANG_VALUE;
        }
        if (modelsCode.isEmpty()) {
            LogUtil.warning(TAG, "getModelsCode is empty, return default value");
            return TANG_VALUE;
        }
        if (this.mModesMap.get(modelsCode) == null) {
            LogUtil.warning(TAG, "getModelsCode " + modelsCode + " is invalid, return default value");
            return TANG_VALUE;
        }
        return modelsCode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBootComplete() {
        String propValue = SystemProperties.get(BOOT_COMPLETE_STATUS);
        LogUtil.info(TAG, "isBootComplete :" + propValue);
        return "1".equals(propValue);
    }

    public int setConfigArea(String code) {
        LogUtil.warning(TAG, "setConfigArea value :" + code);
        String configArea = new CountryCodeConfigManager(this.mContext).getCountryCode(code);
        this.mCountryCode = configArea;
        String modelsCode = getModelsCode();
        this.mModelsCode = modelsCode;
        initAppConfigFile(configArea, modelsCode);
        executeAppConfig(configArea);
        this.mAppConfigSettingCallback.onConfigAreaChanged(0);
        return 0;
    }

    public int setCpaaInstall(boolean state) {
        LogUtil.debug(TAG, "setCpaaInstall state :" + state);
        executeCpAaAppConfig(state);
        this.mAppConfigSettingCallback.onCpaaInStallChanged(0);
        return 0;
    }

    public int setCpaaInstallType(int type, boolean state) {
        LogUtil.debug(TAG, "setCpaaInstallType type :" + type + " state : " + state);
        executeCpAaAppConfig(type, state);
        this.mAppConfigSettingCallback.onCpaaInStallChanged(0);
        return 0;
    }

    public int setCarType(int code) {
        LogUtil.warning(TAG, "setCarType code :" + code);
        initAppConfigFile(this.mCountryCode, String.valueOf(code));
        executeAppConfig(this.mCountryCode);
        this.mAppConfigSettingCallback.onCarTypeChanged(true);
        return 0;
    }

    public void setDefaultLanguages(int countryCode) {
        LogUtil.warning(TAG, "setDefaultLanguages countryCode :" + countryCode);
        String configArea = new CountryCodeConfigManager(this.mContext).getCountryCode(String.valueOf(countryCode));
        setCountryCodeLanguage(configArea);
    }

    public void subscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mAppConfigSettingCallback.register(callback);
        }
    }

    public void unsubscribeAppConfigCallback(IAppConfigSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mAppConfigSettingCallback.unregister(callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDefaultLanguage(String countryCode) {
        boolean result = getLanguageConfig(countryCode);
        if (!result) {
            LogUtil.error(TAG, "getLanguageConfig error");
            return;
        }
        if (this.mLanguageValue == null) {
            LogUtil.error(TAG, "mLanguageValue is null");
            return;
        }
        int value = ILanguageServiceBinder.MAP_GET.get(this.mLanguageValue).intValue();
        LogUtil.warning(TAG, "setDefaultLanguage: countryCode is " + countryCode + ", language is " + this.mLanguageValue + " real language value is :" + value);
        boolean isCountryCodeChanged = isCountryCodeChanged();
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("isCountryCodeChanged: ");
        sb.append(isCountryCodeChanged);
        LogUtil.info(str, sb.toString());
        if (isCountryCodeChanged) {
            this.mLanguageServiceBinder.setLanguage(value);
        }
    }

    private void setCountryCodeLanguage(String countryCode) {
        boolean result = getLanguageConfig(countryCode);
        if (!result) {
            LogUtil.error(TAG, "setCountryCodeLanguage getLanguageConfig error");
            return;
        }
        if (this.mLanguageValue == null) {
            LogUtil.error(TAG, "setCountryCodeLanguage mLanguageValue is null");
            return;
        }
        int value = ILanguageServiceBinder.MAP_GET.get(this.mLanguageValue).intValue();
        LogUtil.warning(TAG, "setCountryCodeLanguage: countryCode is " + countryCode + ", language is " + this.mLanguageValue + " real language value is :" + value);
        this.mLanguageServiceBinder.setLanguage(value);
    }

    private void initAppConfigFile(String countryCode, String modelsCode) {
        if (this.mModesMap.get(modelsCode) != null) {
            LogUtil.error(TAG, "[install info] ModelsCode: " + modelsCode + ", Models: " + this.mModesMap.get(modelsCode) + ", Hex: 0x" + Integer.toHexString(Integer.parseInt(modelsCode)));
        }
        LogUtil.error(TAG, "[install info] CountryCode: " + countryCode);
        if (MR_RIGHT_VALUE.equals(modelsCode)) {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH_MR);
        } else if (PA_RIGHT_VALUE.equals(modelsCode)) {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH_PA);
        } else if (UKE_LEFT_VALUE.equals(modelsCode)) {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH_UKE_LEFT);
        } else if (UEK_RIGHT_VALUE.equals(modelsCode)) {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH_UKE_RIGHT);
        } else if (!TANG_VALUE.equals(modelsCode) && MC_LEFT_VALUE.equals(modelsCode)) {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH_MC_LEFT);
        } else {
            this.mInstallAppFilename = new File(INSTALL_APP_CONFIG_PATH);
        }
        LogUtil.error(TAG, "[install info] file: " + this.mInstallAppFilename);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void executeAppConfig(String countryCode) {
        boolean result = getInstallPkgNameList(countryCode);
        if (COUNTRYCODE_ERROR.equals(countryCode)) {
            LogUtil.error(TAG, "countrycode.json does not have this countrycode, return COUNTRYCODE_ERROR");
            return;
        }
        if (!result) {
            LogUtil.error(TAG, "getInstallPkgNameList error");
            return;
        }
        if (this.mInstallAppList == null) {
            LogUtil.error(TAG, "mInstallAppList is null");
            return;
        }
        LogUtil.info(TAG, "mCpaaStatus: " + this.mCpaaStatus);
        String str = this.mCpaaStatus;
        byte b = -1;
        switch (str.hashCode()) {
            case 48:
                if (str.equals(CPAA_INSTALL_STATE_NONE)) {
                    b = 0;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    b = 1;
                }
                break;
            case 50:
                if (str.equals(CPAA_INSTALL_STATE_AA)) {
                    b = 2;
                }
                break;
        }
        if (b == 0) {
            LogUtil.info(TAG, "cpaa installation is not allowed");
            removeCpaaAppList(this.mInstallAppList);
        } else if (b == 1) {
            LogUtil.info(TAG, "aa installation is not allowed");
            removeAndroidAutoAppList(this.mInstallAppList);
        } else if (b == 2) {
            LogUtil.info(TAG, "cp installation is not allowed");
            removeCarplayAppList(this.mInstallAppList);
        }
        String mapType = SystemProperties.get(MAP_STATE);
        LogUtil.warning(TAG, "executeAppConfig get map type : " + mapType);
        if (!mapType.isEmpty() && this.mInstallAppList.contains(NAVIGATION_PROC_NAME) && !mapType.equals("1")) {
            LogUtil.warning(TAG, "executeAppConfig InstallAppList contain neusoft navi but map type no equal 1");
            this.mInstallAppList.remove(NAVIGATION_PROC_NAME);
        }
        for (String installPkgName : this.mInstallAppList) {
            LogUtil.error(TAG, "install app list: " + installPkgName);
        }
        List<String> uninstallAppList = new CopyOnWriteArrayList<>(Arrays.asList(NAVIGATION_PROC_NAME, SPOTIFY_SDK_PROC_NAME, SPOTIFY_PROC_NAME, "com.byd.vrassistant", VRSETTING_PROC_NAME, VRDOWNLOAD_PROC_NAME, ANDROIDAUTO_PROC_NAME, CARPLAY_APP_PROC_NAME, EMANUAL_PROC_NAME, KARAOKE_PROC_NAME, CARPLAY_PROC_NAME, ANDROIDAUTO_SER_PROC_NAME, DCCONTROLLER_PROC_NAME, DM_SERVICE_PROC_NAME, NA_EHP_PROC_NAME, APPSTORE_PROC_NAME, CAR_MEDIA_PROC_NAME, AMAZON_PROC_NAME, AUTOMAP_PROC_NAME, HUAWEI_MAPS_PROC_NAME, "com.byd.vrassistant.xf", VR_XF_IMAGE_PROC_NAME, VR_DOWNLOAD_XF_PROC_NAME, MY_CAR, MY_CAR_TS, KIKA_PROC_NAME, JAPAN_NAVI_PROC_NAME, SCENE_MODE, KOREA_NAVI_PROC_NAME));
        uninstallAppList.removeAll(this.mInstallAppList);
        synchronized (uninstallAppList) {
            LogUtil.error(TAG, "execute uninstall based on " + this.mInstallAppFilename);
            for (String uninstallPkgName : uninstallAppList) {
                boolean isInstalled = checkIsInstall(uninstallPkgName);
                if (isInstalled) {
                    hide(uninstallPkgName);
                }
            }
        }
        synchronized (this.mInstallAppList) {
            LogUtil.error(TAG, "execute install based on " + this.mInstallAppFilename);
            for (String installPkgName2 : this.mInstallAppList) {
                boolean isInstalled2 = checkIsInstall(installPkgName2);
                if (!isInstalled2) {
                    unhide(installPkgName2);
                }
            }
        }
        sendInstallStateMsgToQnx();
    }

    private void sendInstallStateMsgToQnx() {
        this.mCountryCode = this.mCountryCodeConfigManager.getCountryCode();
        LogUtil.warning(TAG, "sendInstallStateMsgToQnx mCountryCode : " + this.mCountryCode + " mModelsCode : " + this.mModelsCode);
        if ((UKE_LEFT_VALUE.equals(this.mModelsCode) || UEK_RIGHT_VALUE.equals(this.mModelsCode)) && this.mCountryCode.equals(KOREA_NAVI_COUNTRY_CODE)) {
            LogUtil.warning(TAG, "sendInstallStateMsgToQnx not install any map because in korea country and uke car type");
            this.mSettingsQnxService.setNaviInstallState(3);
            return;
        }
        if (this.mInstallAppList.contains(NAVIGATION_PROC_NAME) || this.mInstallAppList.contains(HUAWEI_MAPS_PROC_NAME) || this.mInstallAppList.contains(AUTOMAP_PROC_NAME) || this.mInstallAppList.contains(JAPAN_NAVI_PROC_NAME)) {
            LogUtil.warning(TAG, "install app list contain Local Map");
            this.mLocalMapInstallStatus = true;
        } else {
            this.mLocalMapInstallStatus = false;
        }
        if ((this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_PROC_NAME)) || (this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME))) {
            LogUtil.warning(TAG, "install app list contain cp or aa");
            this.mCpAaInstallStatus = true;
        } else {
            this.mCpAaInstallStatus = false;
        }
        if (this.mLocalMapInstallStatus && this.mCpAaInstallStatus) {
            this.mSettingsQnxService.setNaviInstallState(0);
        }
        if (this.mLocalMapInstallStatus && !this.mCpAaInstallStatus) {
            this.mSettingsQnxService.setNaviInstallState(1);
        }
        if (!this.mLocalMapInstallStatus && this.mCpAaInstallStatus) {
            this.mSettingsQnxService.setNaviInstallState(2);
        }
        if (!this.mLocalMapInstallStatus && !this.mCpAaInstallStatus) {
            this.mSettingsQnxService.setNaviInstallState(3);
        }
    }

    private void addCpaaAppList(List<String> appList) {
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME) && this.mInstallAppList.contains(DCCONTROLLER_PROC_NAME) && this.mInstallAppList.contains(DM_SERVICE_PROC_NAME)) {
            appList.add(DCCONTROLLER_PROC_NAME);
            appList.add(DM_SERVICE_PROC_NAME);
        }
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME)) {
            appList.add(CARPLAY_PROC_NAME);
            appList.add(CARPLAY_APP_PROC_NAME);
        }
        if (this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME)) {
            appList.add(ANDROIDAUTO_PROC_NAME);
            appList.add(ANDROIDAUTO_SER_PROC_NAME);
        }
    }

    private void addCarplayAppList(List<String> appList) {
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME)) {
            appList.add(CARPLAY_PROC_NAME);
            appList.add(CARPLAY_APP_PROC_NAME);
        }
    }

    private void addAndroidAutoAppList(List<String> appList) {
        if (this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME)) {
            appList.add(ANDROIDAUTO_PROC_NAME);
            appList.add(ANDROIDAUTO_SER_PROC_NAME);
        }
    }

    private void removeCpaaAppList(List<String> appList) {
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME) && this.mInstallAppList.contains(DCCONTROLLER_PROC_NAME) && this.mInstallAppList.contains(DM_SERVICE_PROC_NAME)) {
            appList.remove(DCCONTROLLER_PROC_NAME);
            appList.remove(DM_SERVICE_PROC_NAME);
        }
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME)) {
            appList.remove(CARPLAY_PROC_NAME);
            appList.remove(CARPLAY_APP_PROC_NAME);
        }
        if (this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME)) {
            appList.remove(ANDROIDAUTO_PROC_NAME);
            appList.remove(ANDROIDAUTO_SER_PROC_NAME);
        }
    }

    private void removeCarplayAppList(List<String> appList) {
        if (this.mInstallAppList.contains(CARPLAY_PROC_NAME) && this.mInstallAppList.contains(CARPLAY_APP_PROC_NAME)) {
            appList.remove(CARPLAY_PROC_NAME);
            appList.remove(CARPLAY_APP_PROC_NAME);
        }
    }

    private void removeAndroidAutoAppList(List<String> appList) {
        if (this.mInstallAppList.contains(ANDROIDAUTO_PROC_NAME) && this.mInstallAppList.contains(ANDROIDAUTO_SER_PROC_NAME)) {
            appList.remove(ANDROIDAUTO_PROC_NAME);
            appList.remove(ANDROIDAUTO_SER_PROC_NAME);
        }
    }

    private void executeCpAaAppConfig(int type, boolean status) {
        String countryCode = this.mCountryCodeConfigManager.getCountryCode();
        this.mCountryCode = countryCode;
        boolean result = getInstallPkgNameList(countryCode);
        if (COUNTRYCODE_ERROR.equals(this.mCountryCode)) {
            LogUtil.error(TAG, "countrycode.json does not have this countrycode, return COUNTRYCODE_ERROR");
            return;
        }
        if (!result) {
            LogUtil.error(TAG, "getInstallPkgNameList error");
            return;
        }
        if (this.mInstallAppList == null) {
            LogUtil.error(TAG, "mInstallAppList is null");
            return;
        }
        if (type == 1 && status) {
            LogUtil.info(TAG, "cp installation is allowed");
            this.mInstallCpaaAppList.clear();
            addCarplayAppList(this.mInstallCpaaAppList);
            if (this.mInstallCpaaAppList.size() > 0) {
                synchronized (this.mInstallCpaaAppList) {
                    for (String installPkgName : this.mInstallCpaaAppList) {
                        boolean isInstalled = checkIsInstall(installPkgName);
                        if (!isInstalled) {
                            unhide(installPkgName);
                        }
                    }
                }
                return;
            }
            return;
        }
        if (type == 1 && !status) {
            LogUtil.info(TAG, "cp installation is not allowed");
            this.mUnInstallCpaaAppList.clear();
            addCarplayAppList(this.mUnInstallCpaaAppList);
            if (this.mUnInstallCpaaAppList.size() > 0) {
                synchronized (this.mUnInstallCpaaAppList) {
                    for (String uninstallPkgName : this.mUnInstallCpaaAppList) {
                        boolean isInstalled2 = checkIsInstall(uninstallPkgName);
                        if (isInstalled2) {
                            hide(uninstallPkgName);
                        }
                    }
                }
                return;
            }
            return;
        }
        if (type == 2 && status) {
            LogUtil.info(TAG, "aa installation is allowed");
            this.mInstallCpaaAppList.clear();
            addAndroidAutoAppList(this.mInstallCpaaAppList);
            if (this.mInstallCpaaAppList.size() > 0) {
                synchronized (this.mInstallCpaaAppList) {
                    for (String installPkgName2 : this.mInstallCpaaAppList) {
                        boolean isInstalled3 = checkIsInstall(installPkgName2);
                        if (!isInstalled3) {
                            unhide(installPkgName2);
                        }
                    }
                }
                return;
            }
            return;
        }
        if (type == 2 && !status) {
            LogUtil.info(TAG, "aa installation is not allowed");
            this.mUnInstallCpaaAppList.clear();
            addAndroidAutoAppList(this.mUnInstallCpaaAppList);
            if (this.mUnInstallCpaaAppList.size() > 0) {
                synchronized (this.mUnInstallCpaaAppList) {
                    for (String uninstallPkgName2 : this.mUnInstallCpaaAppList) {
                        boolean isInstalled4 = checkIsInstall(uninstallPkgName2);
                        if (isInstalled4) {
                            hide(uninstallPkgName2);
                        }
                    }
                }
            }
        }
    }

    private void executeCpAaAppConfig(boolean status) {
        String countryCode = this.mCountryCodeConfigManager.getCountryCode();
        this.mCountryCode = countryCode;
        boolean result = getInstallPkgNameList(countryCode);
        if (COUNTRYCODE_ERROR.equals(this.mCountryCode)) {
            LogUtil.error(TAG, "countrycode.json does not have this countrycode, return COUNTRYCODE_ERROR");
            return;
        }
        if (!result) {
            LogUtil.error(TAG, "getInstallPkgNameList error");
            return;
        }
        if (this.mInstallAppList == null) {
            LogUtil.error(TAG, "mInstallAppList is null");
            return;
        }
        if (!status) {
            LogUtil.info(TAG, "cpaa installation is not allowed");
            this.mUnInstallCpaaAppList.clear();
            addCpaaAppList(this.mUnInstallCpaaAppList);
            if (this.mUnInstallCpaaAppList.size() > 0) {
                synchronized (this.mUnInstallCpaaAppList) {
                    for (String uninstallPkgName : this.mUnInstallCpaaAppList) {
                        boolean isInstalled = checkIsInstall(uninstallPkgName);
                        if (isInstalled) {
                            hide(uninstallPkgName);
                        }
                    }
                }
                return;
            }
            return;
        }
        LogUtil.info(TAG, "cpaa installation is allowed");
        this.mInstallCpaaAppList.clear();
        addCpaaAppList(this.mInstallCpaaAppList);
        if (this.mInstallCpaaAppList.size() > 0) {
            synchronized (this.mInstallCpaaAppList) {
                for (String installPkgName : this.mInstallCpaaAppList) {
                    boolean isInstalled2 = checkIsInstall(installPkgName);
                    if (!isInstalled2) {
                        unhide(installPkgName);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkCameraAppStatus() {
        try {
            String cameraIsp = SystemProperties.get(CAMERA_ISP, "-1");
            String cameraName = SystemProperties.get(CAMERA_NAME, "-1");
            LogUtil.info(TAG, "checkAvmAppStatus : CAMERA_ISP " + cameraIsp + " CAMERA_NAME : " + cameraName);
            if (!"-1".equals(cameraIsp) && !"-1".equals(cameraName)) {
                if (!checkIsInstall(CAMERA_PROC_NAME)) {
                    unhide(CAMERA_PROC_NAME);
                }
                return;
            }
            if (checkIsInstall(CAMERA_PROC_NAME)) {
                hide(CAMERA_PROC_NAME);
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "checkCameraAppStatus failed", ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAvmAppStatus() {
        try {
            String avmIsp = SystemProperties.get(AVM_ISP, "-1");
            String avmName = SystemProperties.get(AVM_NAME, "-1");
            LogUtil.info(TAG, "checkAvmAppStatus : AVM_ISP " + avmIsp + " AVM_NAME : " + avmName);
            if (!"-1".equals(avmIsp) && !"-1".equals(avmName)) {
                if (!checkIsInstall(AVM_PROC_NAME)) {
                    unhide(AVM_PROC_NAME);
                }
                return;
            }
            if (checkIsInstall(AVM_PROC_NAME)) {
                hide(AVM_PROC_NAME);
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "checkAvmAppStatus failed", ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDvrAppStatus() {
        try {
            String dvrIsp = SystemProperties.get(DVR_ISP, "-1");
            String dvrLens = SystemProperties.get(DVR_LENS, "-1");
            String dvrCategory = SystemProperties.get(DVR_CATEGORY, "-1");
            String dvrResolution = SystemProperties.get(DVR_RESOLUTION, "-1");
            String dvrSerializer = SystemProperties.get(DVR_SERIALIZER, "-1");
            String dvrVideoFormat = SystemProperties.get(DVR_VIDEO_FORMAT, "-1");
            String dvrVideoFormatRate = SystemProperties.get(DVR_VIDEO_FORMAT_RATE, "-1");
            String dvrName = SystemProperties.get(DVR_NAME, "-1");
            LogUtil.info(TAG, "checkDvrAppStatus : DVR_ISP " + dvrIsp + " DVR_LENS : " + dvrLens + " DVR_CATEGORY : " + dvrCategory + " DVR_RESOLUTION : " + dvrResolution + " DVR_SERIALIZER : " + dvrSerializer + " DVR_VIDEO_FORMAT : " + dvrVideoFormat + " DVR_VIDEO_FORMAT_RATE : " + dvrVideoFormatRate + " DVR_NAME : " + dvrName);
            if (!"-1".equals(dvrIsp) && !"-1".equals(dvrLens) && !"-1".equals(dvrCategory) && !"-1".equals(dvrResolution) && !"-1".equals(dvrSerializer) && !"-1".equals(dvrVideoFormat) && !"-1".equals(dvrVideoFormatRate) && !"-1".equals(dvrName)) {
                if (!checkIsInstall(DVR_PROC_NAME)) {
                    unhide(DVR_PROC_NAME);
                }
                return;
            }
            if (checkIsInstall(DVR_PROC_NAME)) {
                hide(DVR_PROC_NAME);
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "checkDvrAppStatus failed", ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLauncherAppStatus() {
        LogUtil.info(TAG, "checkLauncherAppStatus ");
        try {
            String defaultLauncherPackage = SystemProperties.get(DEFAULT_LAUNCHER_NAME);
            if (isUserSetup()) {
                if (defaultLauncherPackage != null && defaultLauncherPackage.equals(LAUNCHER_PROC_NAME)) {
                    this.mLauncherManager.setDefaultLauncher(LAUNCHER_PROC_NAME);
                }
                if (defaultLauncherPackage != null && defaultLauncherPackage.equals(LoggingEvents.EXTRA_CALLING_APP_NAME)) {
                    SystemProperties.set(DEFAULT_LAUNCHER_NAME, LAUNCHER_PROC_NAME);
                    this.mLauncherManager.setDefaultLauncher(LAUNCHER_PROC_NAME);
                }
            }
        } catch (Exception ex) {
            LogUtil.error(TAG, "checkLauncherAppStatus ", ex);
        }
    }

    private boolean isUserSetup() {
        ContentResolver cr = this.mContext.getApplicationContext().getContentResolver();
        return (Settings.Global.getInt(cr, "device_provisioned", 0) == 0 || Settings.Secure.getInt(cr, "user_setup_complete", 0) == 0) ? false : true;
    }

    private boolean checkIsInstall(String packageName) {
        if (packageName == null || packageName.isEmpty()) {
            LogUtil.error(TAG, "packageName is null");
            return false;
        }
        PackageManager packageManager = this.mPackageManager;
        if (packageManager == null) {
            LogUtil.error(TAG, "mPackageManager is null");
            return false;
        }
        boolean result = !packageManager.getApplicationHiddenSettingAsUser(packageName, new UserHandle(ActivityManager.getCurrentUser()));
        LogUtil.info(TAG, "checkIsInstall packageName: " + packageName + "; isInstalled: " + result);
        String launcherName = SystemProperties.get(DEFAULT_LAUNCHER_NAME);
        if (!result && packageName.equals(NAVIGATION_PROC_NAME) && launcherName.equals(NAVIGATION_PROC_NAME)) {
            LogUtil.error(TAG, "checkIsInstall packageName is " + packageName + "  result " + result + " to set default launcher");
            SystemProperties.set(DEFAULT_LAUNCHER_NAME, LAUNCHER_PROC_NAME);
            try {
                AppGlobals.getPackageManager().clearPackagePreferredActivities(NAVIGATION_PROC_NAME);
            } catch (Exception ex) {
                LogUtil.error(TAG, "checkIsInstall clearPackagePreferredActivities ", ex);
            }
        }
        if (!result && packageName.equals(HUAWEI_MAPS_PROC_NAME) && launcherName.equals(HUAWEI_MAPS_PROC_NAME)) {
            LogUtil.error(TAG, "checkIsInstall packageName is " + packageName + "  result " + result + " to set default launcher");
            SystemProperties.set(DEFAULT_LAUNCHER_NAME, LAUNCHER_PROC_NAME);
            try {
                AppGlobals.getPackageManager().clearPackagePreferredActivities(HUAWEI_MAPS_PROC_NAME);
            } catch (Exception ex2) {
                LogUtil.error(TAG, "checkIsInstall clearPackagePreferredActivities ", ex2);
            }
        }
        return result;
    }

    public boolean getInstallPkgNameList(String countryCode) {
        int type;
        this.mInstallAppList.clear();
        File file = this.mInstallAppFilename;
        if (file == null || !file.exists()) {
            LogUtil.error(TAG, "xml is not exist");
            return false;
        }
        FileInputStream fis = null;
        try {
            try {
                fis = new FileInputStream(this.mInstallAppFilename);
                XmlPullParser parser = Xml.newPullParser();
                if (Build.VERSION.SDK_INT >= 19) {
                    parser.setInput(fis, StandardCharsets.UTF_8.name());
                }
                do {
                    type = parser.next();
                    if (type == 2) {
                        break;
                    }
                } while (type != 1);
                if (type != 2) {
                    LogUtil.error(TAG, "No start tag found in stopped packages file\n");
                    return false;
                }
                int outerDepth = parser.getDepth();
                while (true) {
                    int type2 = parser.next();
                    if (type2 == 1 || (type2 == 3 && parser.getDepth() <= outerDepth)) {
                        try {
                            fis.close();
                        } catch (Exception ex) {
                            LogUtil.error(TAG, "Error close fail. ", ex);
                        }
                        return true;
                    }
                    if (type2 != 3 && type2 != 4) {
                        String tagName = parser.getName();
                        if (TAG_COUNTRY_CODE.equals(tagName)) {
                            String country = parser.getAttributeValue(null, ATTR_ID);
                            if (!country.equals(countryCode)) {
                                XmlUtils.skipCurrentTag(parser);
                            }
                        } else if (ATTR_PACKAGE.equals(tagName)) {
                            String packageName = parser.getAttributeValue(null, ATTR_NAME);
                            LogUtil.info(TAG, "getInstallPkgNameList packageName = " + packageName);
                            this.mInstallAppList.add(packageName);
                        } else {
                            LogUtil.error(TAG, "Unknown element under <stopped-packages>: " + parser.getName());
                            XmlUtils.skipCurrentTag(parser);
                        }
                    }
                }
            } catch (Exception ex2) {
                LogUtil.error(TAG, "Error reading package manager stopped packages  ", ex2);
                try {
                    fis.close();
                } catch (Exception ex3) {
                    LogUtil.error(TAG, "Error close fail. ", ex3);
                }
                return false;
            }
        } finally {
            try {
                fis.close();
            } catch (Exception ex4) {
                LogUtil.error(TAG, "Error close fail. ", ex4);
            }
        }
    }

    public boolean getLanguageConfig(String countryCode) {
        int type;
        File file = this.mLanguageConfigFilename;
        if (file == null || !file.exists()) {
            LogUtil.error(TAG, "language-config.xml is not exist");
            return false;
        }
        FileInputStream fis = null;
        try {
            try {
                fis = new FileInputStream(this.mLanguageConfigFilename);
                XmlPullParser parser = Xml.newPullParser();
                if (Build.VERSION.SDK_INT >= 19) {
                    parser.setInput(fis, StandardCharsets.UTF_8.name());
                }
                do {
                    type = parser.next();
                    if (type == 2) {
                        break;
                    }
                } while (type != 1);
                if (type != 2) {
                    LogUtil.error(TAG, "No start tag found in stopped packages file\n");
                    return false;
                }
                int outerDepth = parser.getDepth();
                while (true) {
                    int type2 = parser.next();
                    if (type2 == 1 || (type2 == 3 && parser.getDepth() <= outerDepth)) {
                        try {
                            fis.close();
                        } catch (Exception ex) {
                            LogUtil.error(TAG, "Error close fail. ", ex);
                        }
                        return true;
                    }
                    if (type2 != 3 && type2 != 4) {
                        String tagName = parser.getName();
                        if (TAG_COUNTRY_CODE.equals(tagName)) {
                            String country = parser.getAttributeValue(null, ATTR_ID);
                            if (!country.equals(countryCode)) {
                                XmlUtils.skipCurrentTag(parser);
                            }
                        } else if (ATTR_LANGUAGE.equals(tagName)) {
                            this.mLanguageValue = parser.getAttributeValue(null, ATTR_NAME);
                            LogUtil.info(TAG, "getLanguageConfig mLanguageValue = " + this.mLanguageValue);
                        } else {
                            LogUtil.error(TAG, "Unknown element under <stopped-language>: " + parser.getName());
                            XmlUtils.skipCurrentTag(parser);
                        }
                    }
                }
            } catch (Exception ex2) {
                LogUtil.error(TAG, "Error reading language manager stopped language  ", ex2);
                try {
                    fis.close();
                } catch (Exception ex3) {
                    LogUtil.error(TAG, "Error close fail. ", ex3);
                }
                return false;
            }
        } finally {
            try {
                fis.close();
            } catch (Exception ex4) {
                LogUtil.error(TAG, "Error close fail. ", ex4);
            }
        }
    }

    private void hide(String packageName) {
        LogUtil.info(TAG, "hide packageName: " + packageName);
        PackageManager packageManager = this.mPackageManager;
        if (packageManager != null) {
            packageManager.setApplicationHiddenSettingAsUser(packageName, true, new UserHandle(ActivityManager.getCurrentUser()));
        }
        String launcherName = SystemProperties.get(DEFAULT_LAUNCHER_NAME);
        if ((packageName.equals(NAVIGATION_PROC_NAME) && launcherName.equals(NAVIGATION_PROC_NAME)) || (packageName.equals(HUAWEI_MAPS_PROC_NAME) && launcherName.equals(HUAWEI_MAPS_PROC_NAME))) {
            LogUtil.error(TAG, "hide packageName is " + packageName + " to set default launcher");
            SystemProperties.set(DEFAULT_LAUNCHER_NAME, LAUNCHER_PROC_NAME);
            try {
                AppGlobals.getPackageManager().clearPackagePreferredActivities(NAVIGATION_PROC_NAME);
            } catch (Exception e) {
            }
        }
        if (packageName.equals(MY_CAR) || packageName.equals(MY_CAR_TS)) {
            try {
                boolean result = this.mActivityManager.clearApplicationUserData(packageName, null);
                LogUtil.error(TAG, "hide packageName is " + packageName + " to clear data result " + result);
            } catch (Exception ex) {
                LogUtil.error(TAG, "hide packageName clearApplicationUserData ", ex);
            }
        }
    }

    private void unhide(String packageName) {
        LogUtil.info(TAG, "unhide packageName: " + packageName);
        PackageManager packageManager = this.mPackageManager;
        if (packageManager != null) {
            packageManager.setApplicationHiddenSettingAsUser(packageName, false, new UserHandle(ActivityManager.getCurrentUser()));
        }
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        private MyBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.info(IAppConfigServiceBinder.TAG, "onReceive");
            if (intent.getAction().equals("android.intent.action.USER_UNLOCKED")) {
                LogUtil.info(IAppConfigServiceBinder.TAG, "action is ACTION_USER_UNLOCKED");
                IAppConfigServiceBinder.this.mIsUserUnLocked = true;
                if (IAppConfigServiceBinder.this.mHandler.hasMessages(2)) {
                    IAppConfigServiceBinder.this.mHandler.removeMessages(2);
                }
                IAppConfigServiceBinder.this.checkLauncherAppStatus();
                return;
            }
            LogUtil.error(IAppConfigServiceBinder.TAG, "MyBroadcastReceiver other broadcast");
        }
    }

    public void destoryAppConfigServiceBinder() {
        AppConfigSettingCallback appConfigSettingCallback = this.mAppConfigSettingCallback;
        if (appConfigSettingCallback != null) {
            appConfigSettingCallback.kill();
        }
    }

    private boolean isCountryCodeChanged() {
        SharedPreferences ss = this.mContext.getSharedPreferences(BEFORE_COUNTRY_CODE, 0);
        String beforeCountryCode = ss.getString(BEFORE_COUNTRY_CODE, CPAA_INSTALL_STATE_NONE);
        String currentCountryCode = SystemProperties.get(PERSIST_VENDOR_BYD_CFG_HUC_CONFIG_AREA);
        LogUtil.info(TAG, "beforeCountryCode = " + beforeCountryCode + ", currentCountryCode = " + currentCountryCode);
        if (beforeCountryCode.equals(currentCountryCode)) {
            return false;
        }
        SharedPreferences.Editor editor = ss.edit();
        editor.putString(BEFORE_COUNTRY_CODE, currentCountryCode);
        editor.apply();
        return true;
    }
}
