package com.ts.android.carplay;

import com.ts.android.carplay.CarPlayResourceManager;
import java.util.Arrays;
import ts.car.service.carlife.data.CarLifeConfig;

/* JADX INFO: loaded from: classes.dex */
public class CarPlay {
    public static final int ALREADY_CREATED = -100;
    public static final int ALREADY_REGISTERRED = -157;
    public static final int ALREADY_STARTED = -102;
    public static final int ALREADY_STOPPED = -104;
    public static final int APPEARANCEMODE_DARK = 1;
    public static final int APPEARANCEMODE_LIGHT = 0;
    public static final int APPEARANCESETTING_ALWAYS = 2;
    public static final int APPEARANCESETTING_AUTOMATIC = 0;
    public static final int APPEARANCESETTING_USERCHOICE = 1;
    public static final String APP_HOME = "home";
    public static final String APP_MAPS = "maps:";
    public static final String APP_MUSIC = "music:";
    public static final String APP_NOWPLAYING = "nowplaying:";
    public static final String APP_PHONE = "mobilephone:";
    public static final int AUDIO_CREATE_FAILED = -9;
    public static final int AUDIO_MAIN_ALERT = 3;
    public static final int AUDIO_MAIN_DEFAULT = 1;
    public static final int AUDIO_MAIN_MEASUREMENT = 8;
    public static final int AUDIO_MAIN_MEDIA = 4;
    public static final int AUDIO_MAIN_NONE = 0;
    public static final int AUDIO_MAIN_PHONE = 5;
    public static final int AUDIO_MAIN_SIRI_RECOGNIZING = 7;
    public static final int AUDIO_MAIN_SIRI_SPEAKING = 6;
    public static final int CANCELLED = -8;
    public static final int CARPLAY_AUDIO_MAIN_DEFAULT_INPUT = 2;
    public static final int CLIENTTYPE_USB = 1;
    public static final int CLIENTTYPE_USB_WIFI = 3;
    public static final int CLIENTTYPE_WIFI = 2;
    public static final int CLIENT_CONNECTED = 1;
    public static final int CLIENT_DISCONNECTED = 2;
    public static final int CLIENT_SESSION_STARTED = 4;
    public static final int CLIENT_SESSION_STOPPED = 5;
    public static final int CLIENT_STATUS_CONNECTED = 1;
    public static final int CLIENT_STATUS_DISCONNECTED = 3;
    public static final int CLIENT_STATUS_SESSION_ACTIVE = 2;
    public static final int CLIENT_UPDATE = 3;
    public static final int DATA_EOF = -6;
    public static final int DEVICE_BONJOUR_FAILED = -154;
    public static final int DEVICE_IAP2_FAILED = -152;
    public static final int DEVICE_INIT_FAILED = -150;
    public static final int DEVICE_NETWORK_FAILED = -153;
    public static final int DEVICE_NOT_CONNECTED = -156;
    public static final int DEVICE_USB_MODE_FAILED = -151;
    public static final int FAILURE = -1;
    public static final int FEATURE_INVALID = 0;
    public static final int INURL_INSTRUCTION_CARD = 2;
    public static final int INURL_INSTRUMENT_CLUSTER = 3;
    public static final int INURL_INVALID = 0;
    public static final int INURL_MAP = 1;
    public static final int INVALID_ARGS = -3;
    public static final int INVALID_HANDLE = -2;
    public static final int LOCATION_CENTERCONSOLE = 1;
    public static final int LOCATION_STEERINGWHEEL = 0;
    public static final int LOCATION_UNKNOWN = 2;
    public static final int MAX_ALTSCREEN_NUM = 2;
    public static final int NOTIFY_AUDIO_ALT_STARTED = 4;
    public static final int NOTIFY_AUDIO_ALT_STOPPED = 6;
    public static final int NOTIFY_AUDIO_MAIN_STARTED = 3;
    public static final int NOTIFY_AUDIO_MAIN_STOPPED = 5;
    public static final int NOTIFY_SCREEN_STARTED = 1;
    public static final int NOTIFY_SCREEN_STOPPED = 2;
    public static final int NOT_STARTED = -103;
    public static final int NOT_STOPPED = -105;
    public static final int NO_ENOUGH_MEMORY = -4;
    public static final int PLATFORM_INIT_FAILED = -155;
    public static final int PRESS_DURATION_LONG = 1;
    public static final int PRESS_DURATION_SHORT = 0;
    public static final int SCREEN_TYPE_ALT1 = 1;
    public static final int SCREEN_TYPE_ALT2 = 2;
    public static final int SCREEN_TYPE_MAIN = 0;
    public static final int SIRITRIGGERZONE_NOTAPPLICABLE = 0;
    public static final int SIRI_BTNDOWN = 2;
    public static final int SIRI_BTNUP = 3;
    public static final int SIRI_PREWARM = 1;
    public static final int SIRI_VOICEACTIVATION = 4;
    public static final int STATUSBAR_EDGE_BOTTOM = 1;
    public static final int STATUSBAR_EDGE_DRIVERSIDE = 2;
    public static final int STATUSBAR_EDGE_UNDEFINED = 0;
    public static final int STREAMSTATE_BUFFERING = 1;
    public static final int STREAMSTATE_OFF = 0;
    public static final int STREAMSTATE_STREAMING = 2;
    public static final int SUCCESS = 0;
    private static final String TAG = "CarPlay";
    public static final int TIMEOUT = -7;
    public static final int UNKNOWN_ERROR = -158;
    public static final int UNSUPPORTED = -5;
    public static final int VOICEMODE_DISABLE = -1;
    public static final int VOICEMODE_KEYWORD = 1;
    public static final int VOICEMODE_VOICEACTIVITY = 2;
    public static final int ZOOMDIRECTION_IN = 0;
    public static final int ZOOMDIRECTION_OUT = 1;
    private CarPlayNative mNative;
    public static final int EXTENDEDFEATURE_VOCODERINFO = BIT(0);
    public static final int EXTENDEDFEATURE_ENHANCEDREQUESTCARUI = BIT(1);
    public static final int EXTENDEDFEATURE_HIGHACCURACYTIMESTAMPS = BIT(2);
    public static final int DISPLAYFEATURES_KNOBS = BIT(1);
    public static final int DISPLAYFEATURES_LOWFIDELITYTOUCH = BIT(2);
    public static final int DISPLAYFEATURES_HIGHFIDELITYTOUCH = BIT(3);
    public static final int DISPLAYFEATURES_TOUCHPAD = BIT(4);
    public static final int UICONTEXT_MAPS = BIT(1);
    public static final int UICONTEXT_PHONE = BIT(2);
    public static final int UICONTEXT_MESSAGES = BIT(3);
    public static final int UICONTEXT_NOWPLAYING = BIT(4);
    public static final int SIRITRIGGERZONE_DRIVER = BIT(0);
    public static final int EXURL_SHOWCOMPASS = BIT(0);
    public static final int EXURL_SHOWETA = BIT(1);
    public static final int EXURL_SHOESPEEDLIMIT = BIT(2);
    public static final int FEATURE_ENHANCEDSIRI = BIT(0);
    public static final int FEATURE_VIEWAREAS = BIT(1);
    public static final int FEATURE_UICONTEXT = BIT(2);
    public static final int FEATURE_ALTSCREEN = BIT(3);
    public static final int FEATURE_CORNERMASKS = BIT(4);
    public static final int FEATURE_FOCUSTRANSFER = BIT(5);

    public static final class AccessoryInfo {
        public String mFwRevision;
        public String mHwRevision;
        public String mManufacturer;
        public String mModel;
        public String mName;
        public String mOsInfo;
        public String mSerialNo;
        public String mUniqueId;
        public String mbtId;
    }

    public static final class ButtonInfo {
        public int location;
        public int pressDuration;
    }

    public static final class GenericHidInfo {
        public int countryCode;
        public byte[] descriptor;
        public int productId;
        public int vendorId;
    }

    public static final class KnobInfo {
        public int countryCode;
        public int max;
        public int min;
        public int productId;
        public int vendorId;
    }

    public static final class OemInfo {
        public boolean isEnabled;
        public String lable;
        public String path;
    }

    public static final class PlayKeyInfo {
        public int countryCode;
        public int keyMask;
        public int productId;
        public int vendorId;
    }

    public static final class TelephoneKeyInfo {
        public int countryCode;
        public int keyMask;
        public int productId;
        public int vendorId;
    }

    public static final class TouchPadInfo {
        public int countryCode;
        public boolean gestureSupported;
        public int heightPhysical;
        public int logicalMaxX;
        public int logicalMaxY;
        public int logicalMinX;
        public int logicalMinY;
        public int maxAlternatives;
        public int productId;
        public int vendorId;
        public int widthPhysical;
    }

    public static final class TouchScreenInfo {
        public int countryCode;
        public int logicalMaxX;
        public int logicalMaxY;
        public int logicalMinX;
        public int logicalMinY;
        public int productId;
        public int vendorId;
    }

    public static final class ViewAreaInfo {
        public int initViewAreaIndex;
        public boolean needSafeAreas;
        public SafeArea[] safeAreas;
        public int viewAreaCount;
        public ViewArea[] viewAreas;
    }

    public CarPlay() {
        this.mNative = null;
        this.mNative = CarPlayNative.getInstance();
        LogUtil.info("CarPlay", "CarPlay constructor");
        this.mNative.init();
    }

    public static final String APP_DIAL(String number) {
        String dial = "tel:" + number;
        LogUtil.info("CarPlay", "dial = " + dial);
        return dial;
    }

    public static final int BIT(int bits) {
        int ret = 1 << bits;
        return ret;
    }

    public int create(CarPlayDelegate delegate, Object userContext) throws Exception {
        LogUtil.info("CarPlay", "CarPlay create is called");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.create(delegate, userContext);
        }
        throw new Exception("mNative is null");
    }

    public int refreshClients() {
        return -1;
    }

    public int destroy() throws Exception {
        LogUtil.info("CarPlay", "destroy");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.destroy();
        }
        throw new Exception("mNative is null");
    }

    public int setAAudioDeviceId(CarPlayAudioDeviceInfo audioInfo) throws Exception {
        LogUtil.info("CarPlay", "setAAudioDeviceId");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.setAAudioDeviceId(audioInfo);
        }
        throw new Exception("mNative is null");
    }

    public int getListenPort() throws Exception {
        LogUtil.info("CarPlay", "getListenPort");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.getListenPort();
        }
        throw new Exception("mNative is null");
    }

    public String getPeerAddress() throws Exception {
        LogUtil.info("CarPlay", "getPeerAddress");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.getPeerAddress();
        }
        throw new Exception("mNative is null");
    }

    public String getPairingIdentity() throws Exception {
        LogUtil.info("CarPlay", "getPairingIdentity");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.getPairingIdentity();
        }
        throw new Exception("mNative is null");
    }

    public String getSourceVersion() throws Exception {
        LogUtil.info("CarPlay", "getSourceVersion");
        CarPlayNative carPlayNative = this.mNative;
        if (carPlayNative != null) {
            return carPlayNative.getSourceVersion();
        }
        throw new Exception("mNative is null");
    }

    public static String notificationToString(int notification) {
        return "";
    }

    public static final class NewViewAreaInfo {
        public int[] adjacentViewAreas;
        public int initViewAreaIndex = 0;
        public SafeArea[] safeAreas;
        public ViewArea[] viewAreas;

        public String toString() {
            return "NewViewAreaInfo{initViewAreaIndex=" + this.initViewAreaIndex + ", viewAreas=" + Arrays.toString(this.viewAreas) + ", safeAreas=" + Arrays.toString(this.safeAreas) + ", adjacentViewAreas=" + Arrays.toString(this.adjacentViewAreas) + '}';
        }
    }

    public static final class NewDisPlayInfo {
        public boolean accessoryGiveFocus;
        public boolean cornerMasks;
        public int heightPhysical;
        public int heightPixel;
        public String initialURL;
        public int mDisplayFeatures;
        public int mEnabledFeatures;
        public int maxFPS;
        public int primaryInputDevice;
        public int type;
        public String uuid;
        public int widthPhysical;
        public int widthPixel;
        public boolean mEnable = false;
        public NewViewAreaInfo mViewAreaInfo = new NewViewAreaInfo();
        public AppearanceMode mAppearanceMode = new AppearanceMode(0, 0, 0, 0);

        public String toString() {
            return "NewDisPlayInfo{mEnable=" + this.mEnable + ", mDisplayFeatures=" + this.mDisplayFeatures + ", mEnabledFeatures=" + this.mEnabledFeatures + ", maxFPS=" + this.maxFPS + ", heightPixel=" + this.heightPixel + ", widthPixel=" + this.widthPixel + ", heightPhysical=" + this.heightPhysical + ", widthPhysical=" + this.widthPhysical + ", uuid='" + this.uuid + "', primaryInputDevice=" + this.primaryInputDevice + ", mViewAreaInfo=" + this.mViewAreaInfo + ", cornerMasks=" + this.cornerMasks + ", initialURL=" + this.initialURL + ", type=" + this.type + ", accessoryGiveFocus=" + this.accessoryGiveFocus + ", mAppearanceMode=" + this.mAppearanceMode + '}';
        }
    }

    public static final class InfoMsg {
        public static final int DISPLAY_ALT1_IDX = 1;
        public static final int DISPLAY_ALT2_IDX = 2;
        public static final int DISPLAY_MAIN_IDX = 0;
        public static final int DISPLAY_MAX_SIZE = 3;
        public int mAltScreenSuggestUiUrls;
        public String mDeviceId;
        public int mEnabledFeatures;
        public int mEnabledUiContextUrls;
        public int mExtendedFeature;
        public String mFiremwareRevision;
        public String mHardwareRevision;
        public String[] mHidLaunguages;
        public int mLimitedUiElements;
        public String mManufacturer;
        public String mModelName;
        public int mNightMode;
        public int mNowOnDisPlayUiContextUrls;
        public String mOemIconLabel;
        public String mOemIconPath;
        public boolean mOemIconVisible;
        public OemIcon[] mOemIcons;
        public String mOsInfo;
        public boolean mRightHandDrive;
        public int mSupportLimitedUi;
        public String mbluetoothId;
        public int mDisPlayVersion = 0;
        public NewDisPlayInfo[] mNewScreenDisplay = {new NewDisPlayInfo(), new NewDisPlayInfo(), new NewDisPlayInfo()};
        public DisPlayInfo mDisPlayInfo = new DisPlayInfo();
        public HidDevicesInfo mHidDevicesInfo = new HidDevicesInfo();
        public EnhancedSiriInfo mEnhancedSiriInfo = new EnhancedSiriInfo();
        public ButtonInfo mButtonInfo = new ButtonInfo();
        public ModesInfo mModesInfo = new ModesInfo();

        public NewDisPlayInfo getDisplayMain() {
            return this.mNewScreenDisplay[0];
        }

        public NewDisPlayInfo getDisplayAlt1() {
            return this.mNewScreenDisplay[1];
        }

        public NewDisPlayInfo getDisplayAlt2() {
            return this.mNewScreenDisplay[2];
        }

        public void setDisplayMain(NewDisPlayInfo info) {
            this.mNewScreenDisplay[0] = info;
        }

        public void setDisplayAlt1(NewDisPlayInfo info) {
            this.mNewScreenDisplay[1] = info;
        }

        public void setDisplayAlt2(NewDisPlayInfo info) {
            this.mNewScreenDisplay[2] = info;
        }

        public void setMainScreenDemo() {
            this.mDisPlayVersion = 1;
            if (getDisplayMain() != null) {
                getDisplayMain().mEnable = true;
                getDisplayMain().mDisplayFeatures = 0;
                getDisplayMain().mEnabledFeatures = 0;
                getDisplayMain().maxFPS = 60;
                getDisplayMain().heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayMain().widthPixel = 1920;
                getDisplayMain().heightPhysical = 194;
                getDisplayMain().widthPhysical = 345;
                getDisplayMain().uuid = "e5f7a68d-7b0f-4305-984b-974f677a150b";
                getDisplayMain().primaryInputDevice = 1;
                getDisplayMain().mViewAreaInfo.initViewAreaIndex = 0;
                getDisplayMain().mViewAreaInfo.viewAreas = new ViewArea[1];
                getDisplayMain().mViewAreaInfo.viewAreas[0] = new ViewArea();
                getDisplayMain().mViewAreaInfo.viewAreas[0].widthPixel = 1920;
                getDisplayMain().mViewAreaInfo.viewAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayMain().mViewAreaInfo.viewAreas[0].originX = 0;
                getDisplayMain().mViewAreaInfo.viewAreas[0].originY = 0;
                getDisplayMain().mViewAreaInfo.viewAreas[0].transitionControl = false;
                getDisplayMain().mViewAreaInfo.viewAreas[0].statusBarEdge = 0;
                getDisplayMain().mViewAreaInfo.viewAreas[0].supportsFocusTransfer = false;
                getDisplayMain().mViewAreaInfo.safeAreas = new SafeArea[1];
                getDisplayMain().mViewAreaInfo.safeAreas[0] = new SafeArea();
                getDisplayMain().mViewAreaInfo.safeAreas[0].widthPixel = 1920;
                getDisplayMain().mViewAreaInfo.safeAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayMain().mViewAreaInfo.safeAreas[0].originX = 0;
                getDisplayMain().mViewAreaInfo.safeAreas[0].originY = 0;
                getDisplayMain().mViewAreaInfo.safeAreas[0].drawUIOutsideSafeArea = false;
                getDisplayMain().mViewAreaInfo.adjacentViewAreas = new int[1];
                getDisplayMain().mViewAreaInfo.adjacentViewAreas[0] = 0;
                getDisplayMain().cornerMasks = false;
                getDisplayMain().initialURL = "";
                getDisplayMain().cornerMasks = false;
                getDisplayMain().type = 110;
                getDisplayMain().accessoryGiveFocus = false;
                getDisplayMain().mAppearanceMode.mUiMode = 0;
                getDisplayMain().mAppearanceMode.mUiSetting = 0;
                getDisplayMain().mAppearanceMode.mIsSupportMap = false;
                getDisplayMain().mAppearanceMode.mMapMode = 0;
                getDisplayMain().mAppearanceMode.mMapSetting = 0;
                LogUtil.info("CarPlay", "setMainScreenDemo" + getDisplayMain());
                return;
            }
            LogUtil.info("CarPlay", "getDisplayMain is null!");
        }

        public void setAlt1ScreenDemo() {
            this.mDisPlayVersion = 1;
            if (getDisplayAlt1() != null) {
                getDisplayAlt1().mEnable = true;
                getDisplayAlt1().mDisplayFeatures = 0;
                getDisplayAlt1().mEnabledFeatures = 0;
                getDisplayAlt1().maxFPS = 60;
                getDisplayAlt1().widthPixel = 1920;
                getDisplayAlt1().heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt1().heightPhysical = 194;
                getDisplayAlt1().widthPhysical = 345;
                getDisplayAlt1().uuid = "e5f7a68d-7b0f-4305-984b-b051a776f479";
                getDisplayAlt1().primaryInputDevice = 1;
                getDisplayAlt1().mViewAreaInfo.initViewAreaIndex = 0;
                getDisplayAlt1().mViewAreaInfo.viewAreas = new ViewArea[1];
                getDisplayAlt1().mViewAreaInfo.viewAreas[0] = new ViewArea();
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].widthPixel = 1920;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].originX = 0;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].originY = 0;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].transitionControl = false;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].statusBarEdge = 0;
                getDisplayAlt1().mViewAreaInfo.viewAreas[0].supportsFocusTransfer = false;
                getDisplayAlt1().mViewAreaInfo.safeAreas = new SafeArea[1];
                getDisplayAlt1().mViewAreaInfo.safeAreas[0] = new SafeArea();
                getDisplayAlt1().mViewAreaInfo.safeAreas[0].widthPixel = 1920;
                getDisplayAlt1().mViewAreaInfo.safeAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt1().mViewAreaInfo.safeAreas[0].originX = 0;
                getDisplayAlt1().mViewAreaInfo.safeAreas[0].originY = 0;
                getDisplayAlt1().mViewAreaInfo.safeAreas[0].drawUIOutsideSafeArea = false;
                getDisplayAlt1().mViewAreaInfo.adjacentViewAreas = new int[1];
                getDisplayAlt1().mViewAreaInfo.adjacentViewAreas[0] = 0;
                getDisplayAlt1().cornerMasks = false;
                getDisplayAlt1().initialURL = "";
                getDisplayAlt1().cornerMasks = false;
                getDisplayAlt1().type = 111;
                getDisplayAlt1().accessoryGiveFocus = false;
                getDisplayAlt1().mAppearanceMode.mUiMode = 0;
                getDisplayAlt1().mAppearanceMode.mUiSetting = 0;
                getDisplayAlt1().mAppearanceMode.mIsSupportMap = false;
                getDisplayAlt1().mAppearanceMode.mMapMode = 0;
                getDisplayAlt1().mAppearanceMode.mMapSetting = 0;
                LogUtil.info("CarPlay", "setAlt1ScreenDemo" + getDisplayAlt1());
                return;
            }
            LogUtil.info("CarPlay", "getDisplayAlt1 is null!");
        }

        public void setAlt2ScreenDemo() {
            this.mDisPlayVersion = 1;
            if (getDisplayAlt2() != null) {
                getDisplayAlt2().mEnable = true;
                getDisplayAlt2().mDisplayFeatures = 0;
                getDisplayAlt2().mEnabledFeatures = 0;
                getDisplayAlt2().maxFPS = 60;
                getDisplayAlt2().widthPixel = 1920;
                getDisplayAlt2().heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt2().heightPhysical = 194;
                getDisplayAlt2().widthPhysical = 345;
                getDisplayAlt2().uuid = "e5f7a68d-7b0f-4305-984b-b051a776f472";
                getDisplayAlt2().primaryInputDevice = 1;
                getDisplayAlt2().mViewAreaInfo.initViewAreaIndex = 0;
                getDisplayAlt2().mViewAreaInfo.viewAreas = new ViewArea[1];
                getDisplayAlt2().mViewAreaInfo.viewAreas[0] = new ViewArea();
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].widthPixel = 1920;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].originX = 0;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].originY = 0;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].transitionControl = false;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].statusBarEdge = 0;
                getDisplayAlt2().mViewAreaInfo.viewAreas[0].supportsFocusTransfer = false;
                getDisplayAlt2().mViewAreaInfo.safeAreas = new SafeArea[1];
                getDisplayAlt2().mViewAreaInfo.safeAreas[0] = new SafeArea();
                getDisplayAlt2().mViewAreaInfo.safeAreas[0].widthPixel = 1920;
                getDisplayAlt2().mViewAreaInfo.safeAreas[0].heightPixel = CarLifeConfig.DefaultValue.SURFACE_VIEW_HEIGHT;
                getDisplayAlt2().mViewAreaInfo.safeAreas[0].originX = 0;
                getDisplayAlt2().mViewAreaInfo.safeAreas[0].originY = 0;
                getDisplayAlt2().mViewAreaInfo.safeAreas[0].drawUIOutsideSafeArea = false;
                getDisplayAlt2().mViewAreaInfo.adjacentViewAreas = new int[1];
                getDisplayAlt2().mViewAreaInfo.adjacentViewAreas[0] = 0;
                getDisplayAlt2().cornerMasks = false;
                getDisplayAlt2().initialURL = "";
                getDisplayAlt2().cornerMasks = false;
                getDisplayAlt2().type = 111;
                getDisplayAlt2().accessoryGiveFocus = false;
                getDisplayAlt2().mAppearanceMode.mUiMode = 0;
                getDisplayAlt2().mAppearanceMode.mUiSetting = 0;
                getDisplayAlt2().mAppearanceMode.mIsSupportMap = false;
                getDisplayAlt2().mAppearanceMode.mMapMode = 0;
                getDisplayAlt2().mAppearanceMode.mMapSetting = 0;
                LogUtil.info("CarPlay", "setAlt2ScreenDemo" + getDisplayAlt1());
                return;
            }
            LogUtil.info("CarPlay", "getDisplayAlt2 is null!");
        }

        public void setHidLaunguages(String[] hidLaunguages) {
            LogUtil.info("CarPlay", "setHidLaunguages");
            this.mHidLaunguages = new String[hidLaunguages.length];
            for (int i = 0; i < hidLaunguages.length; i++) {
                this.mHidLaunguages[i] = hidLaunguages[i];
            }
        }

        public void setButtonInfo(int location, int duration) {
            this.mButtonInfo.location = location;
            this.mButtonInfo.pressDuration = duration;
        }
    }

    public static final class OemIcon {
        public int mHeight;
        public String mPath;
        public boolean mPreRender;
        public int mWidth;

        public OemIcon() {
            this.mHeight = 0;
            this.mWidth = 0;
            this.mPreRender = false;
            this.mPath = "";
        }

        public OemIcon(int height, int width, boolean isOemIconPreRender, String oemIconPath) {
            this.mHeight = height;
            this.mWidth = width;
            this.mPreRender = isOemIconPreRender;
            this.mPath = oemIconPath;
        }

        public void copyIcon(OemIcon icon) {
            this.mHeight = icon.mHeight;
            this.mWidth = icon.mWidth;
            this.mPreRender = icon.mPreRender;
            this.mPath = icon.mPath;
        }
    }

    public static final class EnhancedSiriInfo {
        public boolean enhancedSiriButton;
        public boolean enhancedSiriVoice;
        public int siriTriggerZones;
        public String voiceModelCurrentLanguage;
        public String[] voiceModelSupportedLanguages;

        public EnhancedSiriInfo() {
            this.enhancedSiriButton = false;
            this.enhancedSiriVoice = false;
            this.siriTriggerZones = 0;
        }

        public EnhancedSiriInfo(boolean siriButton) {
            this.enhancedSiriButton = siriButton;
            this.enhancedSiriVoice = false;
            this.siriTriggerZones = 0;
        }

        public EnhancedSiriInfo(boolean siriButton, int triggerZones, String curLanguage, String[] supportLanguages) {
            this.enhancedSiriButton = siriButton;
            this.enhancedSiriVoice = true;
            this.siriTriggerZones = triggerZones;
            this.voiceModelCurrentLanguage = curLanguage;
            this.voiceModelSupportedLanguages = new String[supportLanguages.length];
            for (int i = 0; i < supportLanguages.length; i++) {
                this.voiceModelSupportedLanguages[i] = supportLanguages[i];
            }
        }
    }

    public static final class ViewArea {
        public int heightPixel;
        public int originX;
        public int originY;
        public int statusBarEdge;
        public boolean supportsFocusTransfer;
        public boolean transitionControl;
        public int widthPixel;

        public String toString() {
            return "ViewArea{widthPixel=" + this.widthPixel + ", heightPixel=" + this.heightPixel + ", originX=" + this.originX + ", originY=" + this.originY + ", transitionControl=" + this.transitionControl + ", statusBarEdge=" + this.statusBarEdge + ", supportsFocusTransfer=" + this.supportsFocusTransfer + '}';
        }
    }

    public static final class SafeArea {
        public boolean drawUIOutsideSafeArea;
        public int heightPixel;
        public int originX;
        public int originY;
        public int widthPixel;

        public String toString() {
            return "SafeArea{widthPixel=" + this.widthPixel + ", heightPixel=" + this.heightPixel + ", originX=" + this.originX + ", originY=" + this.originY + ", drawUIOutsideSafeArea=" + this.drawUIOutsideSafeArea + '}';
        }
    }

    public static final class DisPlayInfo {
        public AltScreenInfo[] mAltScreenInfo;
        public int mDisplayFeatures;
        public int mEnabledFeatures;
        public ScreenInfo mScreenInfo = new ScreenInfo();
        public ViewAreaInfo mViewAreaInfo = new ViewAreaInfo();

        public void setScreenInfo(int heightPixel, int widthPixel, int heightPhysical, int widthPhysical, int framesPerSecond, AppearanceMode appearanceMode) {
            LogUtil.info("CarPlay", "DisPlayInfo setScreenInfo");
            this.mScreenInfo.heightPhysical = heightPhysical;
            this.mScreenInfo.widthPhysical = widthPhysical;
            this.mScreenInfo.heightPixel = heightPixel;
            this.mScreenInfo.widthPixel = widthPixel;
            this.mScreenInfo.framesPerSecond = framesPerSecond;
            this.mScreenInfo.mAppearanceMode = new AppearanceMode(appearanceMode);
        }

        public void setAltScreenInfo(AltScreenInfo[] altScreenInfo) {
            LogUtil.info("CarPlay", "DisPlayInfo setAltScreenInfo");
            if (altScreenInfo == null || altScreenInfo.length == 0) {
                LogUtil.error("CarPlay", "altScreenInfo is NULL or length is 0");
                return;
            }
            int length = altScreenInfo.length <= 2 ? altScreenInfo.length : 2;
            this.mAltScreenInfo = new AltScreenInfo[length];
            for (int i = 0; i < length; i++) {
                this.mAltScreenInfo[i] = new AltScreenInfo(altScreenInfo[i]);
            }
        }

        public void setViewAreaInfo(int viewAreaCount, int initViewAreaIndex, boolean needSafeAreas, ViewArea[] viewAreas, ViewArea[] safeAreas) {
            LogUtil.info("CarPlay", "DisPlayInfo setViewAreaInfo old");
            SafeArea[] safeAreas1 = new SafeArea[safeAreas.length];
            for (int i = 0; i < safeAreas.length; i++) {
                safeAreas1[i] = new SafeArea();
                safeAreas1[i].widthPixel = safeAreas[i].widthPixel;
                safeAreas1[i].heightPixel = safeAreas[i].heightPixel;
                safeAreas1[i].originX = safeAreas[i].originX;
                safeAreas1[i].originY = safeAreas[i].originY;
                safeAreas1[i].drawUIOutsideSafeArea = false;
            }
            setViewAreaInfo(viewAreaCount, initViewAreaIndex, needSafeAreas, viewAreas, safeAreas1);
        }

        public void setViewAreaInfo(int viewAreaCount, int initViewAreaIndex, boolean needSafeAreas, ViewArea[] viewAreas, SafeArea[] safeAreas) {
            LogUtil.info("CarPlay", "DisPlayInfo setViewAreaInfo new");
            this.mViewAreaInfo.viewAreaCount = viewAreaCount;
            this.mViewAreaInfo.initViewAreaIndex = initViewAreaIndex;
            this.mViewAreaInfo.needSafeAreas = needSafeAreas;
            if (needSafeAreas && viewAreaCount != 0 && safeAreas.length != viewAreas.length) {
                LogUtil.error("CarPlay", "DisPlayInfo safeAreas.length(" + safeAreas.length + ") != viewAreas.length(" + viewAreas.length + ") return");
                return;
            }
            this.mViewAreaInfo.viewAreas = new ViewArea[viewAreas.length];
            this.mViewAreaInfo.safeAreas = new SafeArea[viewAreas.length];
            for (int i = 0; i < viewAreaCount; i++) {
                this.mViewAreaInfo.viewAreas[i] = viewAreas[i];
                if (needSafeAreas) {
                    this.mViewAreaInfo.safeAreas[i] = safeAreas[i];
                }
            }
        }
    }

    public static final class ModesInfo {
        CarPlayResourceManager.ResourceChange mAudioResource;
        CarPlayResourceManager.PermanentResource mPermAudioResource;
        CarPlayResourceManager.PermanentResource mPermScreenResource;
        public boolean mPhoneActive;
        CarPlayResourceManager.ResourceChange mScreenResource;
        public int mSpeechMode;
        public boolean mTurnByTurnActive;

        public ModesInfo() {
            this.mAudioResource = new CarPlayResourceManager.ResourceChange();
            this.mScreenResource = new CarPlayResourceManager.ResourceChange();
            this.mPermAudioResource = new CarPlayResourceManager.PermanentResource();
            this.mPermScreenResource = new CarPlayResourceManager.PermanentResource();
        }

        public ModesInfo(boolean phoneActive, boolean turnByturnActive, int speechMode, CarPlayResourceManager.ResourceChange audioResource, CarPlayResourceManager.ResourceChange screenResource, CarPlayResourceManager.PermanentResource permAudioResource, CarPlayResourceManager.PermanentResource permScreenResource) {
            this.mPhoneActive = phoneActive;
            this.mTurnByTurnActive = turnByturnActive;
            this.mSpeechMode = speechMode;
            this.mAudioResource = new CarPlayResourceManager.ResourceChange(audioResource);
            this.mScreenResource = new CarPlayResourceManager.ResourceChange(screenResource);
            this.mPermAudioResource = new CarPlayResourceManager.PermanentResource(permAudioResource);
            this.mPermScreenResource = new CarPlayResourceManager.PermanentResource(permScreenResource);
        }

        public void setAppState(boolean phoneActive, boolean turnByturnActive, int speechMode) {
            this.mPhoneActive = phoneActive;
            this.mTurnByTurnActive = turnByturnActive;
            this.mSpeechMode = speechMode;
        }

        public void initialModeChange(CarPlayResourceManager.ResourceChange audioResource, CarPlayResourceManager.ResourceChange screenResource) {
            this.mAudioResource = new CarPlayResourceManager.ResourceChange(audioResource);
            this.mScreenResource = new CarPlayResourceManager.ResourceChange(screenResource);
        }

        public void initialPermanentResource(CarPlayResourceManager.PermanentResource permAudioResource, CarPlayResourceManager.PermanentResource permScreenResource) {
            this.mPermAudioResource = new CarPlayResourceManager.PermanentResource(permAudioResource);
            this.mPermScreenResource = new CarPlayResourceManager.PermanentResource(permScreenResource);
        }
    }

    public static final class HidDevicesInfo {
        public int keyMask;
        public TouchScreenInfo mTouchScreenInfo = new TouchScreenInfo();
        public KnobInfo mKnobInfo = new KnobInfo();
        public PlayKeyInfo mPlayKeyInfo = new PlayKeyInfo();
        public TelephoneKeyInfo mTelKeyInfo = new TelephoneKeyInfo();
        public TouchPadInfo mTouchPadInfo = new TouchPadInfo();

        public void setTouchScreenInfo(int countryCode, int vendorId, int productId, int logicalMinX, int logicalMaxX, int logicalMinY, int logicalMaxY) {
            LogUtil.info("CarPlay", "setTouchScreenInfo");
            this.mTouchScreenInfo.countryCode = countryCode;
            this.mTouchScreenInfo.productId = productId;
            this.mTouchScreenInfo.vendorId = vendorId;
            this.mTouchScreenInfo.logicalMinX = logicalMinX;
            this.mTouchScreenInfo.logicalMaxX = logicalMaxX;
            this.mTouchScreenInfo.logicalMinY = logicalMinY;
            this.mTouchScreenInfo.logicalMaxY = logicalMaxY;
        }

        public void setKnobInfo(int countryCode, int vendorId, int productId, int min, int max) {
            LogUtil.info("CarPlay", "setKnobInfo");
            this.mKnobInfo.countryCode = countryCode;
            this.mKnobInfo.vendorId = vendorId;
            this.mKnobInfo.productId = productId;
            this.mKnobInfo.max = max;
            this.mKnobInfo.min = min;
        }

        public void setPlayKeyInfo(int countryCode, int vendorId, int productId, int keyMask) {
            LogUtil.info("CarPlay", "setPlayKeyInfo");
            this.mPlayKeyInfo.countryCode = countryCode;
            this.mPlayKeyInfo.vendorId = vendorId;
            this.mPlayKeyInfo.productId = productId;
            this.mPlayKeyInfo.keyMask = keyMask;
        }

        public void setTelephoneKeyInfo(int countryCode, int vendorId, int productId, int keyMask) {
            LogUtil.info("CarPlay", "setTelephoneKeyInfo");
            this.mTelKeyInfo.countryCode = countryCode;
            this.mTelKeyInfo.vendorId = vendorId;
            this.mTelKeyInfo.productId = productId;
            this.mTelKeyInfo.keyMask = keyMask;
        }

        public void setTouchPadInfo(int countryCode, int vendorId, int productId, int logicalMinX, int logicalMaxX, int logicalMinY, int logicalMaxY, int heightPhysical, int widthPhysical, boolean gestureSupported, int maxAlternatives) {
            LogUtil.info("CarPlay", "setTouchPadInfo");
            this.mTouchPadInfo.countryCode = countryCode;
            this.mTouchPadInfo.vendorId = vendorId;
            this.mTouchPadInfo.productId = productId;
            this.mTouchPadInfo.logicalMaxX = logicalMaxX;
            this.mTouchPadInfo.logicalMaxY = logicalMaxY;
            this.mTouchPadInfo.logicalMinX = logicalMinX;
            this.mTouchPadInfo.logicalMinY = logicalMinY;
            this.mTouchPadInfo.heightPhysical = heightPhysical;
            this.mTouchPadInfo.widthPhysical = widthPhysical;
            this.mTouchPadInfo.gestureSupported = gestureSupported;
            this.mTouchPadInfo.maxAlternatives = maxAlternatives;
        }
    }

    public static final class AppearanceMode {
        public boolean mIsSupportMap;
        public int mMapMode;
        public int mMapSetting;
        public int mUiMode;
        public int mUiSetting;

        public AppearanceMode(int uiMode, int uiSetting) {
            this.mUiMode = uiMode;
            this.mUiSetting = uiSetting;
            this.mIsSupportMap = false;
        }

        public AppearanceMode(int uiMode, int uiSetting, int mapMode, int mapSetting) {
            this.mUiMode = uiMode;
            this.mUiSetting = uiSetting;
            this.mIsSupportMap = true;
            this.mMapMode = mapMode;
            this.mMapSetting = mapSetting;
        }

        public AppearanceMode(AppearanceMode that) {
            this.mUiMode = that.mUiMode;
            this.mUiSetting = that.mUiSetting;
            this.mIsSupportMap = that.mIsSupportMap;
            this.mMapMode = that.mMapMode;
            this.mMapSetting = that.mMapSetting;
        }

        public String toString() {
            return "AppearanceMode{mUiMode=" + this.mUiMode + ", mUiSetting=" + this.mUiSetting + ", mIsSupportMap=" + this.mIsSupportMap + ", mMapMode=" + this.mMapMode + ", mMapSetting=" + this.mMapSetting + '}';
        }
    }

    public static final class ScreenInfo {
        public int framesPerSecond;
        public int heightPhysical;
        public int heightPixel;
        public AppearanceMode mAppearanceMode;
        public int widthPhysical;
        public int widthPixel;

        public ScreenInfo() {
        }

        public ScreenInfo(int heightPixel, int widthPixel, int heightPhysical, int widthPhysical, int fps, AppearanceMode appearance) {
            this.heightPixel = heightPixel;
            this.widthPixel = widthPixel;
            this.heightPhysical = heightPhysical;
            this.widthPhysical = widthPhysical;
            this.framesPerSecond = fps;
            this.mAppearanceMode = new AppearanceMode(appearance);
        }
    }

    public static final class AltScreenInfo {
        public int framesPerSecond;
        public int heightPhysical;
        public int heightPixel;
        public int inUrl;
        public AppearanceMode mAppearanceMode;
        public int widthPhysical;
        public int widthPixel;

        public AltScreenInfo(int heightPixel, int widthPixel, int heightPhysical, int widthPhysical, int fps, int inUrl, AppearanceMode appearanceMode) {
            this.heightPixel = heightPixel;
            this.widthPixel = widthPixel;
            this.heightPhysical = heightPhysical;
            this.widthPhysical = widthPhysical;
            this.framesPerSecond = fps;
            this.inUrl = inUrl;
            this.mAppearanceMode = new AppearanceMode(appearanceMode);
        }

        public AltScreenInfo(AltScreenInfo that) {
            this.heightPixel = that.heightPixel;
            this.widthPixel = that.widthPixel;
            this.heightPhysical = that.heightPhysical;
            this.widthPhysical = that.widthPhysical;
            this.framesPerSecond = that.framesPerSecond;
            this.inUrl = that.inUrl;
        }
    }
}
