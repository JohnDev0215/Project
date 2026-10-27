package com.ts.carplay.app.service.model.config;

import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import com.ts.carplay.app.service.common.IModel;
import com.ts.carplay.app.service.manager.CarPlayRemoteManager;
import com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener;
import com.ts.carplay.app.ui.display.constants.DisplayConstants;
import com.ts.carplay.utils.LogUtil;
import com.ts.lib.car.Car;
import com.ts.lib.car.CarConnectionCallback;
import com.ts.lib.car.CarNotConnectedException;
import com.ts.lib.car.hardware.light.CarVendorLightManager;
import com.ts.lib.car.hardware.light.CarVendorLightMgr;
import com.ts.lib.car.hardware.light.LightEventChangeValue;
import ts.car.service.carplay.data.config.ButtonInfo;
import ts.car.service.carplay.data.config.CarPlayConfig;
import ts.car.service.carplay.data.config.SafeArea;
import ts.car.service.carplay.data.config.ScreenInfo;
import ts.car.service.carplay.data.config.ViewArea;

/* JADX INFO: loaded from: classes.dex */
public class ConfigModel extends IModel {
    private static final String ACCESSORY_MODEL = "MTCA05";
    private static final String ACCESSORY_MODEL_ID = "MTCA05";
    private static final int CLUSTER_HEIGHT_PIXELS = 720;
    private static final int CLUSTER_WIDTH_PIXELS = 1920;
    private static final String DISPLAY_NAME = "BYD 5.0F";
    private static final int HEIGHT_PHYSICAL = 163;
    private static final int HEIGHT_PHYSICAL_VIEW_AREA = 345;
    private static final int HEIGHT_PIXELS = 906;
    private static final int HEIGHT_PIXELS_VIEW_AREA = 1920;
    private static final String HW_VERSION_NAME = "persist.vendor.bosch.hw.ver";
    private static final String HW_VERSION_NAME_RO = "ro.vendor.bosch.hw.ver";
    private static final int LIGHT_INVALID = 0;
    private static final int LIGHT_OFF = 2;
    private static final int LIGHT_ON = 1;
    private static final int LIGHT_RESERVE = 3;
    private static final String MANUFACTURER = "BYD";
    private static final int MAX_CLUSTER_FPS = 30;
    private static final int MAX_CURRENT_ROAD_NAME_LENGTH = 13;
    private static final int MAX_MAIN_FPS = 30;
    private static final String OEM_ICON_LABEL = "BYD";
    private static final String[] OEM_ICON_PATH = {"/system/etc/carplay/res/OEM_ICON_104.png", "/system/etc/carplay/res/OEM_ICON_120.png", "/system/etc/carplay/res/OEM_ICON_180.png", "/system/etc/carplay/res/OEM_ICON_256.png"};
    private static final boolean OEM_ICON_VISIBLE = true;
    private static final String ORIENTATION_CHANGE_ACTION = "android.intent.action.CONFIGURATION_CHANGED";
    private static final String RIGHT_RUDDER_KEY = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private static final String RIGHT_RUDDER_VALUE = "1";
    private static final int SCREEN_TYPE_ALT1 = 1;
    private static final int SCREEN_TYPE_ALT2 = 2;
    private static final int SCREEN_TYPE_MAIN = 0;
    private static final int SERVICE_DELAY = 100;
    private static final String SW_VERSION_SPLIT = "[.]";
    private static final String SYSTEM_VERSION_NAME = "ro.product.version.soc.name";
    private static final String TAG = "ConfigModel";
    private static final String TANG_ACCESSORY_NAME = "BYD 5.0F";
    private static final int TANG_BIG_CLUSTER_DISPLAY_HEIGHT = 468;
    private static final int TANG_BIG_CLUSTER_DISPLAY_HEIGHT_OFFECT = 130;
    private static final int TANG_BIG_CLUSTER_DISPLAY_SAFE_HEIGHT = 278;
    private static final int TANG_BIG_CLUSTER_DISPLAY_SAFE_HEIGHT_OFFECT = 228;
    private static final int TANG_BIG_CLUSTER_DISPLAY_SAFE_WIDTH = 1084;
    private static final int TANG_BIG_CLUSTER_DISPLAY_SAFE_WIDTH_OFFECT = 218;
    private static final int TANG_BIG_CLUSTER_DISPLAY_WIDTH = 1780;
    private static final int TANG_BIG_CLUSTER_DISPLAY_WIDTH_OFFECT = 74;
    private static final int TANG_CLUSTER_HEIGHT_PHYSICAL = 109;
    private static final int TANG_CLUSTER_WIDTH_PHYSICAL = 292;
    private static final String TANG_PRODUCT_PLAN_UID = "b2020a1ebf234012";
    private static final int TANG_SMALL_CLUSTER_DISPLAY_HEIGHT = 446;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_HEIGHT_OFFECT = 174;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_SAFE_HEIGHT = 372;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_SAFE_HEIGHT_OFFECT = 226;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_SAFE_WIDTH = 660;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_SAFE_WIDTH_OFFECT = 628;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_WIDTH = 760;
    private static final int TANG_SMALL_CLUSTER_DISPLAY_WIDTH_OFFECT = 590;
    private static final int UI_MODE_NIGHT_NO = 19;
    private static final int UI_MODE_NIGHT_YES = 35;
    private static final String UKE_ACCESSORY_NAME = "BYD 5.0F_01";
    private static final int UKE_BIG_CLUSTER_DISPLAY_HEIGHT = 450;
    private static final int UKE_BIG_CLUSTER_DISPLAY_HEIGHT_OFFECT = 148;
    private static final int UKE_BIG_CLUSTER_DISPLAY_SAFE_HEIGHT = 382;
    private static final int UKE_BIG_CLUSTER_DISPLAY_SAFE_HEIGHT_OFFECT = 208;
    private static final int UKE_BIG_CLUSTER_DISPLAY_SAFE_WIDTH = 1700;
    private static final int UKE_BIG_CLUSTER_DISPLAY_SAFE_WIDTH_OFFECT = 110;
    private static final int UKE_BIG_CLUSTER_DISPLAY_WIDTH = 1920;
    private static final int UKE_BIG_CLUSTER_DISPLAY_WIDTH_OFFECT = 0;
    private static final int UKE_CLUSTER_HEIGHT_PHYSICAL = 92;
    private static final int UKE_CLUSTER_WIDTH_PHYSICAL = 244;
    private static final String UKE_PRODUCT_PLAN_UID = "dfe8a088bc9448f9";
    private static final int UKE_SMALL_CLUSTER_DISPLAY_HEIGHT = 536;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_HEIGHT_OFFECT = 130;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_SAFE_HEIGHT = 351;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_SAFE_HEIGHT_OFFECT = 210;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_SAFE_WIDTH = 454;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_SAFE_WIDTH_OFFECT = 1393;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_WIDTH = 600;
    private static final int UKE_SMALL_CLUSTER_DISPLAY_WIDTH_OFFECT = 1320;
    private static final String UKE_VERSION_FLAG_3 = "3";
    private static final String UKE_VERSION_FLAG_4 = "4";
    private static final String UKE_VERSION_FLAG_51 = "51";
    private static final String VEHICLE_SN_VERSION_NAME = "persist.vendor.bosch.cfg.diag.sn";
    private static final int WIDTH_PHYSICAL = 345;
    private static final int WIDTH_PIXELS = 1920;
    private int currentBigClusterDisplayHeight;
    private int currentBigClusterDisplayHeightOffect;
    private int currentBigClusterDisplaySafeHeight;
    private int currentBigClusterDisplaySafeHeightOffect;
    private int currentBigClusterDisplaySafeWidth;
    private int currentBigClusterDisplaySafeWidthOffect;
    private int currentBigClusterDisplayWidth;
    private int currentBigClusterDisplayWidthOffect;
    private int currentSmallClusterDisplayHeight;
    private int currentSmallClusterDisplayHeightOffect;
    private int currentSmallClusterDisplaySafeHeight;
    private int currentSmallClusterDisplaySafeHeightOffect;
    private int currentSmallClusterDisplaySafeWidth;
    private int currentSmallClusterDisplaySafeWidthOffect;
    private int currentSmallClusterDisplayWidth;
    private int currentSmallClusterDisplayWidthOffect;
    private boolean mAlreadySend;
    private CarPlayConfig.Builder mBuilder;
    private Car mCarClient;
    private CarVendorLightManager.OnCarLightEventListener mCarLightEventListener;
    private CarPlayConfig mCarPlayConfig;
    private CarPlayRemoteManager mCarPlayRemoteManager;
    private ICarPlayRemoteManagerListener mCarPlayRemoteManagerListener;
    private CarVendorLightManager mCarVendorLightManager;
    private CarVendorLightMgr mCarVendorLightMgr;
    private Context mContext;
    private String mCurrentAccessoryName;
    private String mCurrentProductPlanUidModel;
    private String mHardwareVersion;
    private boolean mIsCarPlaySessionActive;
    private boolean mIsNightMode;
    private ScreenInfo[] mScreenInfos;
    private String mSerialNumber;
    private String mSoftwareVersion;
    private final ContentObserver mSystemThemeObserver;
    private int mUiMode;
    private UiModeManager mUiModeManager;
    private int mUiSetting;
    private BYDAutoSysPropertiesDevice mbydAutoSysPropertiesDevice;
    private final BroadcastReceiver orientationReceiver;
    private int viewAreaIndex;

    public ConfigModel(Looper looper) {
        super(looper);
        this.mScreenInfos = new ScreenInfo[2];
        this.currentSmallClusterDisplayHeight = 446;
        this.currentSmallClusterDisplayWidth = 760;
        this.currentSmallClusterDisplaySafeHeight = 372;
        this.currentSmallClusterDisplaySafeWidth = 660;
        this.currentSmallClusterDisplayWidthOffect = 590;
        this.currentSmallClusterDisplayHeightOffect = 174;
        this.currentSmallClusterDisplaySafeWidthOffect = 628;
        this.currentSmallClusterDisplaySafeHeightOffect = 226;
        this.currentBigClusterDisplayHeight = 468;
        this.currentBigClusterDisplayWidth = 1780;
        this.currentBigClusterDisplaySafeHeight = 278;
        this.currentBigClusterDisplaySafeWidth = 1084;
        this.currentBigClusterDisplayWidthOffect = 74;
        this.currentBigClusterDisplayHeightOffect = 130;
        this.currentBigClusterDisplaySafeWidthOffect = 218;
        this.currentBigClusterDisplaySafeHeightOffect = 228;
        this.mCurrentProductPlanUidModel = TANG_PRODUCT_PLAN_UID;
        this.mCurrentAccessoryName = "BYD 5.0F";
        this.mHardwareVersion = "";
        this.mSoftwareVersion = "";
        this.mSerialNumber = "";
        this.mUiMode = 0;
        this.mUiSetting = 0;
        this.mAlreadySend = false;
        this.mIsNightMode = false;
        this.mCarPlayConfig = new CarPlayConfig();
        this.mCarPlayRemoteManagerListener = new CarPlayRemoteManagerListener();
        this.mCarLightEventListener = new CarVendorLightManager.OnCarLightEventListener() { // from class: com.ts.carplay.app.service.model.config.ConfigModel.2
            @Override // com.ts.lib.car.hardware.light.CarVendorLightManager.OnCarLightEventListener
            public void onLightEventChange(LightEventChangeValue value) {
                int lightState = value.getLightSideStatusR();
                boolean currentNightMode = ConfigModel.this.convertNightMode(lightState);
                LogUtil.info(ConfigModel.TAG, " OnCarLightEventListener onLightEventChange lightState=" + lightState + " currentNightMode=" + currentNightMode + "mIsNightMode=" + ConfigModel.this.mIsNightMode);
                if (currentNightMode != ConfigModel.this.mIsNightMode) {
                    ConfigModel.this.mIsNightMode = currentNightMode;
                    ConfigModel configModel = ConfigModel.this;
                    configModel.setNightMode(Boolean.valueOf(configModel.mIsNightMode));
                }
            }

            @Override // com.ts.lib.car.hardware.light.CarVendorLightManager.OnCarLightEventListener
            public void onLightErrorEvent(int propertyId, int zone) {
            }
        };
        this.mSystemThemeObserver = new ContentObserver(new Handler()) { // from class: com.ts.carplay.app.service.model.config.ConfigModel.3
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange, Uri uri) {
                int systemUiMode = Settings.Secure.getIntForUser(ConfigModel.this.mContext.getContentResolver(), "ui_night_mode", ConfigModel.this.mUiMode, 0);
                ConfigModel.this.getCurrentUiMode(systemUiMode);
                ConfigModel.this.setUiMode();
            }
        };
        this.orientationReceiver = new BroadcastReceiver() { // from class: com.ts.carplay.app.service.model.config.ConfigModel.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
            }
        };
    }

    public CarPlayConfig getCarPlayConfig() {
        return this.mCarPlayConfig;
    }

    public boolean isNightMode() {
        return this.mIsNightMode;
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void init(Context context) {
        LogUtil.info(TAG, "init");
        this.mContext = context;
        CarPlayRemoteManager carPlayRemoteManager = CarPlayRemoteManager.getInstance();
        this.mCarPlayRemoteManager = carPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.addCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
        }
        this.mbydAutoSysPropertiesDevice = BYDAutoSysPropertiesDevice.getInstance(context);
        connectCarService();
        registerOrientationReceiver();
        try {
            if (this.mCarVendorLightMgr != null) {
                int lightState = this.mCarVendorLightMgr.getLightSideStatusR();
                this.mIsNightMode = convertNightMode(lightState);
                LogUtil.info(TAG, " init lightState=" + lightState + " mIsNightMode=" + this.mIsNightMode);
                setNightMode(Boolean.valueOf(this.mIsNightMode));
            }
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        UiModeManager uiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        this.mUiModeManager = uiModeManager;
        if (uiModeManager != null) {
            int systemUiMode = uiModeManager.getNightMode();
            if (systemUiMode == 0) {
                getCurrentUiModeFromConfig();
            } else {
                getCurrentUiMode(systemUiMode);
            }
        }
        this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("ui_night_mode"), false, this.mSystemThemeObserver, 0);
    }

    @Override // com.ts.carplay.app.service.common.IModel
    public void uninit() {
        LogUtil.info(TAG, "uninit");
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.removeCarPlayRemoteManagerListener(this.mCarPlayRemoteManagerListener);
            this.mCarPlayRemoteManager = null;
        }
        removeCallbacksAndMessages(null);
        LogUtil.info(TAG, " getCarManager");
        try {
            if (this.mCarVendorLightManager != null) {
                this.mCarVendorLightManager.removeCarLightEventListener(this.mCarLightEventListener);
                this.mCarVendorLightManager = null;
            }
        } catch (CarNotConnectedException exception) {
            exception.printStackTrace();
        }
        if (this.mCarVendorLightMgr != null) {
            this.mCarVendorLightMgr = null;
        }
        Car car = this.mCarClient;
        if (car != null) {
            car.disconnect();
            this.mCarClient = null;
        }
        this.mContext.unregisterReceiver(this.orientationReceiver);
    }

    private void connectCarService() {
        Car carCreateCar = Car.createCar(this.mContext, new CarConnectionCallback() { // from class: com.ts.carplay.app.service.model.config.ConfigModel.1
            @Override // com.ts.lib.car.CarConnectionCallback
            public void onConnected(Car car) {
                try {
                    if (car != null) {
                        ConfigModel.this.mCarVendorLightManager = (CarVendorLightManager) car.getCarManager(Car.VENDOR_LIGHT_SERVICE);
                        ConfigModel.this.mCarVendorLightMgr = (CarVendorLightMgr) car.getCarManager(Car.VENDOR_LIGHT_SERVICE);
                        StringBuilder sb = new StringBuilder();
                        sb.append(" mCarVendorLightMgr");
                        boolean z = true;
                        sb.append(ConfigModel.this.mCarVendorLightMgr != null);
                        sb.append(" mCarVendorLightManager");
                        if (ConfigModel.this.mCarVendorLightManager == null) {
                            z = false;
                        }
                        sb.append(z);
                        LogUtil.info(ConfigModel.TAG, sb.toString());
                        if (ConfigModel.this.mCarVendorLightManager != null) {
                            ConfigModel.this.mCarVendorLightManager.addCarLightEventListener(ConfigModel.this.mCarLightEventListener);
                        }
                    } else {
                        LogUtil.info(ConfigModel.TAG, "connectCarService car is null!");
                    }
                } catch (CarNotConnectedException exception) {
                    exception.printStackTrace();
                }
            }

            @Override // com.ts.lib.car.CarConnectionCallback
            public void onDisconnected(Car car) {
                LogUtil.info(ConfigModel.TAG, "onDisconnected");
                if (ConfigModel.this.mCarClient != null) {
                    ConfigModel.this.mCarClient = null;
                }
            }
        });
        this.mCarClient = carCreateCar;
        if (carCreateCar != null) {
            carCreateCar.connect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCarPlayConfig() {
        String bluetoothMacAddress = this.mCarPlayRemoteManager.getBluetoothMacAddress();
        LogUtil.debug(TAG, "getbluetoothMacAddress zhou: " + bluetoothMacAddress);
        String hwVersion = SystemProperties.get(HW_VERSION_NAME, "");
        String hwRoVersion = SystemProperties.get(HW_VERSION_NAME_RO, "");
        String swVersion = SystemProperties.get(SYSTEM_VERSION_NAME, "");
        String vehicleSn = SystemProperties.get(VEHICLE_SN_VERSION_NAME, "");
        LogUtil.debug(TAG, "setCarPlayConfig hwVersion:" + hwVersion + " hwRoVersion:" + hwRoVersion + " swVersion:" + swVersion + " vehicleSn:" + vehicleSn);
        if (!TextUtils.isEmpty(hwVersion)) {
            this.mHardwareVersion = hwVersion;
        }
        if (!TextUtils.isEmpty(hwRoVersion)) {
            this.mHardwareVersion = hwRoVersion;
        }
        if (!TextUtils.isEmpty(swVersion)) {
            this.mSoftwareVersion = swVersion;
        }
        if (TextUtils.isEmpty(vehicleSn)) {
            this.mSerialNumber = "00000000";
        } else {
            this.mSerialNumber = vehicleSn;
        }
        int mediaKey = bit(2) | bit(3) | bit(4);
        int phoneKey = bit(1) | bit(3);
        this.mBuilder = new CarPlayConfig.Builder();
        ButtonInfo[] mButtonInfo = {new ButtonInfo(0, 0, 1)};
        int suggestUrl = bit(1);
        int showUiParameters = bit(0) | bit(1) | bit(2);
        ScreenInfo mainScreen = new ScreenInfo();
        mainScreen.setUuid(DisplayConstants.SCREEN_MAIN_UUID);
        mainScreen.setWidthPhysical(345);
        mainScreen.setWidthPixels(1920);
        mainScreen.setMaxFps(30);
        mainScreen.setIsSupportMap(true);
        mainScreen.setUiSetting(this.mUiSetting);
        mainScreen.setUiMode(this.mUiMode);
        mainScreen.setMapMode(this.mUiMode);
        mainScreen.setMapSetting(this.mUiSetting);
        mainScreen.setHeightPhysical(163);
        mainScreen.setHeightPixels(906);
        this.mScreenInfos[0] = mainScreen;
        ScreenInfo clusterScreen = new ScreenInfo();
        clusterScreen.setUuid(DisplayConstants.SCREEN_CLUSTER_UUID);
        if (!TextUtils.isEmpty(this.mSoftwareVersion)) {
            String[] currentVehicleModel = this.mSoftwareVersion.split(SW_VERSION_SPLIT);
            LogUtil.debug(TAG, "currentSwVersion: " + this.mSoftwareVersion + "currentVehicleModel[2]:" + currentVehicleModel[2]);
            if (UKE_VERSION_FLAG_51.equals(currentVehicleModel[0]) && (UKE_VERSION_FLAG_3.equals(currentVehicleModel[2]) || "4".equals(currentVehicleModel[2]))) {
                clusterScreen.setHeightPhysical(92);
                clusterScreen.setWidthPhysical(244);
                this.mCurrentProductPlanUidModel = UKE_PRODUCT_PLAN_UID;
                this.mCurrentAccessoryName = UKE_ACCESSORY_NAME;
                setUkeDimParam();
            } else {
                clusterScreen.setHeightPhysical(109);
                clusterScreen.setWidthPhysical(292);
                this.mCurrentProductPlanUidModel = TANG_PRODUCT_PLAN_UID;
                this.mCurrentAccessoryName = "BYD 5.0F";
                setTangDimParam();
            }
        } else {
            clusterScreen.setHeightPhysical(109);
            clusterScreen.setWidthPhysical(292);
            this.mCurrentProductPlanUidModel = TANG_PRODUCT_PLAN_UID;
            this.mCurrentAccessoryName = "BYD 5.0F";
            setTangDimParam();
        }
        clusterScreen.setHeightPixels(720);
        clusterScreen.setWidthPixels(1920);
        clusterScreen.setMaxFps(30);
        clusterScreen.setIsSupportMap(true);
        clusterScreen.setUiSetting(this.mUiSetting);
        clusterScreen.setUiMode(this.mUiMode);
        clusterScreen.setMapMode(this.mUiMode);
        clusterScreen.setMapSetting(this.mUiSetting);
        this.mScreenInfos[1] = clusterScreen;
        ViewArea portraitArea = new ViewArea(906, 1920, 0, 0);
        ViewArea landscapeArea = new ViewArea(1920, 906, 0, 0);
        ViewArea[] viewAreas = {landscapeArea};
        ViewArea[] viewAreaArr = {landscapeArea, portraitArea};
        ViewArea alt1ViewArea0 = new ViewArea(this.currentSmallClusterDisplayWidth, this.currentSmallClusterDisplayHeight, this.currentSmallClusterDisplayWidthOffect, this.currentSmallClusterDisplayHeightOffect);
        int i = this.currentBigClusterDisplayWidth;
        int i2 = this.currentBigClusterDisplayHeight;
        int i3 = this.currentBigClusterDisplayWidthOffect;
        int callControlFeature = this.currentBigClusterDisplayHeightOffect;
        ViewArea alt1ViewArea1 = new ViewArea(i, i2, i3, callControlFeature);
        ViewArea[] alt1ViewAreas = {alt1ViewArea0, alt1ViewArea1};
        SafeArea alt1SafeArea0 = new SafeArea(this.currentSmallClusterDisplaySafeWidth, this.currentSmallClusterDisplaySafeHeight, this.currentSmallClusterDisplaySafeWidthOffect, this.currentSmallClusterDisplaySafeHeightOffect);
        SafeArea alt1SafeArea1 = new SafeArea(this.currentBigClusterDisplaySafeWidth, this.currentBigClusterDisplaySafeHeight, this.currentBigClusterDisplaySafeWidthOffect, this.currentBigClusterDisplaySafeHeightOffect);
        SafeArea[] alt1SafeAreas = {alt1SafeArea0, alt1SafeArea1};
        this.mBuilder.accessoryName(this.mCurrentAccessoryName).productPlanUuid(this.mCurrentProductPlanUidModel).viewAreas(viewAreas).alt1ViewAreas(alt1ViewAreas).alt1SafeAreas(alt1SafeAreas).manufacturer("BYD").accessoryModel("MTCA05").maxCurrentRoadNameLength(13).accessoryModelId("MTCA05").displayName("BYD 5.0F").bluetoothMacAddress(bluetoothMacAddress).oemIconLabel("BYD").oemIconPaths(OEM_ICON_PATH).oemIconVisible(true).hardwareRevision(this.mHardwareVersion).firmwareRevision(this.mSoftwareVersion).serialNumber(this.mSerialNumber).rightHandDrive(getDriverPosition(RIGHT_RUDDER_KEY)).engineType(4).nightMode(this.mIsNightMode).buttonInfo(mButtonInfo).supportedFeatures(11).disPlayFeatures(8).screenInfos(this.mScreenInfos).playKeyMask(mediaKey).telKeyMask(phoneKey).iapFeatures(39425).callControlFeature(12).supportedEnhancedSiriType(3).iapPlaybackHid(79).carplayCapability(2).disPlayOffExpected(2).suggestUrl(suggestUrl).showUiParameters(showUiParameters).useMainViewArea(true).useAlt1ViewArea(true);
        CarPlayConfig carPlayConfig = new CarPlayConfig(this.mBuilder);
        this.mCarPlayConfig = carPlayConfig;
        LogUtil.debug(TAG, carPlayConfig.toString());
        this.mCarPlayRemoteManager.setCarPlayConfig(this.mCarPlayConfig);
    }

    private void setUkeDimParam() {
        this.currentSmallClusterDisplayHeight = 536;
        this.currentSmallClusterDisplayWidth = 600;
        this.currentSmallClusterDisplaySafeHeight = 351;
        this.currentSmallClusterDisplaySafeWidth = 454;
        this.currentSmallClusterDisplayWidthOffect = 1320;
        this.currentSmallClusterDisplayHeightOffect = 130;
        this.currentSmallClusterDisplaySafeWidthOffect = 1393;
        this.currentSmallClusterDisplaySafeHeightOffect = 210;
        this.currentBigClusterDisplayHeight = 450;
        this.currentBigClusterDisplayWidth = 1920;
        this.currentBigClusterDisplaySafeHeight = 382;
        this.currentBigClusterDisplaySafeWidth = 1700;
        this.currentBigClusterDisplayWidthOffect = 0;
        this.currentBigClusterDisplayHeightOffect = 148;
        this.currentBigClusterDisplaySafeWidthOffect = 110;
        this.currentBigClusterDisplaySafeHeightOffect = 208;
    }

    private void setTangDimParam() {
        this.currentSmallClusterDisplayHeight = 446;
        this.currentSmallClusterDisplayWidth = 760;
        this.currentSmallClusterDisplaySafeHeight = 372;
        this.currentSmallClusterDisplaySafeWidth = 660;
        this.currentSmallClusterDisplayWidthOffect = 590;
        this.currentSmallClusterDisplayHeightOffect = 174;
        this.currentSmallClusterDisplaySafeWidthOffect = 628;
        this.currentSmallClusterDisplaySafeHeightOffect = 226;
        this.currentBigClusterDisplayHeight = 468;
        this.currentBigClusterDisplayWidth = 1780;
        this.currentBigClusterDisplaySafeHeight = 278;
        this.currentBigClusterDisplaySafeWidth = 1084;
        this.currentBigClusterDisplayWidthOffect = 74;
        this.currentBigClusterDisplayHeightOffect = 130;
        this.currentBigClusterDisplaySafeWidthOffect = 218;
        this.currentBigClusterDisplaySafeHeightOffect = 228;
    }

    private int bit(int bits) {
        int ret = 1 << bits;
        return ret;
    }

    private boolean getDriverPosition(String key) {
        boolean isRightRudder = false;
        String valueOfRudder = SystemProperties.get(key, "");
        LogUtil.info(TAG, "getDriverPosition  valueOfRudder:" + valueOfRudder);
        if (!TextUtils.isEmpty(valueOfRudder) && "1".equals(valueOfRudder)) {
            isRightRudder = true;
        }
        LogUtil.info(TAG, "getDriverPosition  isRightRudder:" + isRightRudder);
        return isRightRudder;
    }

    public class CarPlayRemoteManagerListener implements ICarPlayRemoteManagerListener {
        public CarPlayRemoteManagerListener() {
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onServiceStateChanged(boolean connected) {
            if (connected) {
                if (!ConfigModel.this.mAlreadySend) {
                    ConfigModel.this.mAlreadySend = true;
                    LogUtil.info(ConfigModel.TAG, "Send CarPlayConfig when service is connected");
                    ConfigModel.this.postDelayed(new Runnable() { // from class: com.ts.carplay.app.service.model.config.-$$Lambda$ConfigModel$CarPlayRemoteManagerListener$37GkS1OGDXlQf39z9WVluKmCTs4
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onServiceStateChanged$0$ConfigModel$CarPlayRemoteManagerListener();
                        }
                    }, 100L);
                    return;
                }
                return;
            }
            LogUtil.warning(ConfigModel.TAG, "CarPlay service is disconnected, reset alreadySend flag.");
            ConfigModel.this.mAlreadySend = false;
        }

        public /* synthetic */ void lambda$onServiceStateChanged$0$ConfigModel$CarPlayRemoteManagerListener() {
            ConfigModel.this.setCarPlayConfig();
            LogUtil.info(ConfigModel.TAG, "setCarPlayConfig() delay 100ms");
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onCarPlaySessionStateChanged(int status) {
            if (status == 2) {
                ConfigModel.this.mIsCarPlaySessionActive = true;
                LogUtil.info(ConfigModel.TAG, "Send UiMode and NightMode when carplay is connected");
                ConfigModel.this.setUiMode();
                ConfigModel configModel = ConfigModel.this;
                configModel.setNightMode(Boolean.valueOf(configModel.mIsNightMode));
            } else if (status == 3) {
                ConfigModel.this.mIsCarPlaySessionActive = false;
            }
            LogUtil.debug(ConfigModel.TAG, "onCarPlaySessionStateChanged mIsCarPlaySessionActive:" + ConfigModel.this.mIsCarPlaySessionActive);
        }

        @Override // com.ts.carplay.app.service.manager.ICarPlayRemoteManagerListener
        public void onConfigurationChanged(Configuration newConfig) {
            LogUtil.debug(ConfigModel.TAG, "onConfigurationChanged newConfig" + newConfig);
            int systemUiMode = 0;
            if (ConfigModel.this.mUiModeManager != null) {
                systemUiMode = ConfigModel.this.mUiModeManager.getNightMode();
            }
            LogUtil.debug(ConfigModel.TAG, "onConfigurationChanged systemUiMode:" + systemUiMode);
            if (systemUiMode == 0) {
                int uiModeFromConfig = newConfig.uiMode;
                ConfigModel.this.mUiSetting = 0;
                if (uiModeFromConfig == 19) {
                    ConfigModel.this.mUiMode = 0;
                } else if (uiModeFromConfig == 35) {
                    ConfigModel.this.mUiMode = 1;
                }
                LogUtil.debug(ConfigModel.TAG, "onConfigurationChanged uiModeFromConfig: " + uiModeFromConfig);
                ConfigModel.this.setUiMode();
                StringBuilder sb = new StringBuilder();
                sb.append("onConfigurationChanged UI Mode AUTOMATIC isDARK:");
                sb.append(ConfigModel.this.mUiMode == 1);
                LogUtil.info(ConfigModel.TAG, sb.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean convertNightMode(int lightState) {
        if (lightState == 1) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNightMode(Boolean nightMode) {
        LogUtil.debug(TAG, " setNightMode NightMode =" + nightMode);
        CarPlayRemoteManager carPlayRemoteManager = this.mCarPlayRemoteManager;
        if (carPlayRemoteManager != null) {
            carPlayRemoteManager.setNightMode(nightMode.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUiMode() {
        LogUtil.info(TAG, "setUiMode mUiMode:" + this.mUiMode + "  mUiSetting" + this.mUiSetting);
        this.mCarPlayRemoteManager.setUiAppearanceMode(DisplayConstants.SCREEN_MAIN_UUID, this.mUiMode, this.mUiSetting);
        this.mCarPlayRemoteManager.setMapAppearanceMode(DisplayConstants.SCREEN_MAIN_UUID, this.mUiMode, this.mUiSetting);
        this.mCarPlayRemoteManager.setUiAppearanceMode(DisplayConstants.SCREEN_CLUSTER_UUID, this.mUiMode, this.mUiSetting);
        this.mCarPlayRemoteManager.setMapAppearanceMode(DisplayConstants.SCREEN_CLUSTER_UUID, this.mUiMode, this.mUiSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCurrentUiMode(int uiMode) {
        if (uiMode == 1) {
            this.mUiMode = 0;
            this.mUiSetting = 1;
            LogUtil.info(TAG, "Night Mode OFF!");
        } else {
            if (uiMode == 2) {
                this.mUiMode = 1;
                this.mUiSetting = 1;
                LogUtil.info(TAG, "Night Mode ON!");
                return;
            }
            getCurrentUiModeFromConfig();
        }
    }

    private void getCurrentUiModeFromConfig() {
        Configuration curconfiguration = this.mContext.getResources().getConfiguration();
        int uiModeFromConfig = curconfiguration.uiMode;
        LogUtil.info(TAG, "getCurrentUiModeFromConfig uiModeFromConfig=" + uiModeFromConfig);
        if (uiModeFromConfig == 19) {
            this.mUiMode = 0;
        } else if (uiModeFromConfig == 35) {
            this.mUiMode = 1;
        }
        this.mUiSetting = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("getCurrentUiModeFromConfig UI Mode AUTOMATIC isDARK:");
        sb.append(this.mUiMode == 1);
        LogUtil.info(TAG, sb.toString());
    }

    private void registerOrientationReceiver() {
        if (this.mContext == null) {
            LogUtil.info(TAG, "registerOrientationReceiver context is null ");
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ORIENTATION_CHANGE_ACTION);
        this.mContext.registerReceiver(this.orientationReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewArea(int index) {
        LogUtil.info(TAG, "not support view area");
    }
}
