package ts.car.service.carplay.data.config;

import android.util.Log;
import java.util.Arrays;
import ts.car.service.input.HardkeyConstrants;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayConfig {
    private static final String TAG = "CarPlayConfig";
    private String mAccessoryModel;
    private String mAccessoryModelId;
    private String mAccessoryName;
    private String mAccessoryOsInfo;
    private SafeArea[] mAlt1SafeAreas;
    private ViewArea[] mAlt1ViewAreas;
    private String mAppearanceDefault;
    private int mAvailableCurrentForDevice;
    private String mBluetoothMacAddress;
    private ButtonInfo[] mButtonInfo;
    private int mCallControlFeature;
    private int mCarPlayCapability;
    private String mCurrentLanguage;
    private int mDisPlayFeatures;
    private int mDisPlayOffExpected;
    private String mDisplayName;
    private int mEngineType;
    private int mExtendedFeatures;
    private String mFeatures;
    private String mFirmwareRevision;
    private String mHardwareRevision;
    private String[] mHidLanguages;
    private String mIapDeviceNode;
    private int mIapFeatures;
    private int mIapPlaybackHid;
    private boolean mIsUseAlt1ViewArea;
    private boolean mIsUseMainViewArea;
    private boolean mKeepAliveLowPower;
    private boolean mKeepAliveSendStatsAsBody;
    private boolean mLimitedUi;
    private int mLimitedUiElements;
    private String mManufacturer;
    private int mMaxAfterManeuverRoadNameLength;
    private int mMaxCurrentRoadNameLength;
    private int mMaxDestinationNameLength;
    private int mMaxGuidanceManeuverCapacity;
    private int mMaxLaneGuidanceCapacity;
    private int mMaxLaneGuidanceDescriptionLength;
    private int mMaxManeuverDescriptionLength;
    private boolean mNightMode;
    private String mOemIconLabel;
    private String[] mOemIconPaths;
    private boolean mOemIconVisible;
    private int mPlayKeyMask;
    private int mPrimaryInputDevice;
    private int mProductId;
    private String mProductPlanUuid;
    private int mProtocolVersion;
    private boolean mRightHandDrive;
    private ScreenInfo[] mScreenInfos;
    private String mSerialNumber;
    private int mShowUiParameters;
    private String mSourceVersion;
    private int mStatusFlags;
    private int mSuggestUrl;
    private int mSupportedEnhancedSiriType;
    private int mSupportedFeatures;
    private String[] mSupportedLanguage;
    private int mTelKeyMask;
    private String[] mUiContextLastOnDisplayUrls;
    private String[] mUiContextNowOnDisplayUrls;
    private int mUsbHostTransportInterfaceNum;
    private int mVendorId;
    private int mViewAreaIndex;
    private ViewArea[] mViewAreas;

    public static class ActivateEnhancedSiriType {
        public static final int ENHANCED_SIRI_BUTTON = 2;
        public static final int ENHANCED_SIRI_VOICE = 1;
    }

    public static class ButtonLocation {
        public static final int LOCATION_CENTERCONSOLE = 1;
        public static final int LOCATION_STEERINGWHEEL = 0;
        public static final int LOCATION_UNKNOWN = 2;
    }

    public static class ButtonPressDuration {
        public static final int PRESS_DURATION_LONG = 1;
        public static final int PRESS_DURATION_SHORT = 0;
    }

    public static class ButtonType {
        public static final int SIRI = 0;
    }

    public static class DisplayFeature {
        public static final int DISPLAYFEATURES_HIGHFIDELITYTOUCH = 8;
        public static final int DISPLAYFEATURES_KNOBS = 2;
        public static final int DISPLAYFEATURES_LOWFIDELITYTOUCH = 4;
        public static final int DISPLAYFEATURES_TOUCHPAD = 16;
    }

    public static class Extfeature {
        public static final int EXTFEATURE_ENHANCEDREQUESTCARUI = 2;
        public static final int EXTFEATURE_VOCODERINFO = 1;
    }

    public static class IapPlaybackHid {
        public static final int IAP_HID_PLAYBACK_NEXT = 4;
        public static final int IAP_HID_PLAYBACK_PAUSE = 2;
        public static final int IAP_HID_PLAYBACK_PLAY = 1;
        public static final int IAP_HID_PLAYBACK_PLAY_PAUSE = 64;
        public static final int IAP_HID_PLAYBACK_PREV = 8;
        public static final int IAP_HID_PLAYBACK_REPEAT = 32;
        public static final int IAP_HID_PLAYBACK_SHUFFLE = 16;
    }

    public static class MediaKey {
        public static final int KEY_ACBACK = 6;
        public static final int KEY_ACHOME = 5;
        public static final int KEY_ACNAVI = 7;
        public static final int KEY_NEXTTRACK = 2;
        public static final int KEY_PAUSE = 1;
        public static final int KEY_PLAY = 0;
        public static final int KEY_PLAYPAUSE = 4;
        public static final int KEY_PREVTRACK = 3;

        public static boolean isMediaKeyValid(int mediaKey) {
            if (mediaKey > CarPlayConfig.bit(7) || mediaKey < 0) {
                return false;
            }
            return true;
        }
    }

    public static class PhoneKey {
        public static final int KEY_0 = 5;
        public static final int KEY_1 = 6;
        public static final int KEY_2 = 7;
        public static final int KEY_3 = 8;
        public static final int KEY_4 = 9;
        public static final int KEY_5 = 10;
        public static final int KEY_6 = 11;
        public static final int KEY_7 = 12;
        public static final int KEY_8 = 13;
        public static final int KEY_9 = 14;
        public static final int KEY_DELETE = 0;
        public static final int KEY_DROP = 3;
        public static final int KEY_FLASH = 2;
        public static final int KEY_HOOKSWITCH = 1;
        public static final int KEY_MUTE = 4;
        public static final int KEY_POUND = 16;
        public static final int KEY_STAR = 15;

        public static boolean isPhoneKeyValid(int phoneKey) {
            if (phoneKey > CarPlayConfig.bit(16) || phoneKey < 0) {
                return false;
            }
            return true;
        }
    }

    public static class IapFeature {
        public static final int IAP_FEATURE_ACC_WIFI_INFO_SHARING = 16384;
        public static final int IAP_FEATURE_APP_LAUNCH = 131072;
        public static final int IAP_FEATURE_BT_CONNECTION_UPDATES = 4;
        public static final int IAP_FEATURE_CALL_CONTROLS = 2048;
        public static final int IAP_FEATURE_CALL_STATE_UPDATES = 512;
        public static final int IAP_FEATURE_COMMUNICATIONS_UPDATES = 1024;
        public static final int IAP_FEATURE_DEVICE_NOTIFICATION = 1;
        public static final int IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY = 1048576;
        public static final int IAP_FEATURE_EAP_OVER_IAP2 = 65536;
        public static final int IAP_FEATURE_HID_OVER_IAP2 = 32;
        public static final int IAP_FEATURE_LIST_UPDATES = 262144;
        public static final int IAP_FEATURE_LOCATION = 4096;
        public static final int IAP_FEATURE_MEDIA_LIBRARY_PLAYBACK = 16;
        public static final int IAP_FEATURE_MEDIA_LIBRARY_UPDATES = 8;
        public static final int IAP_FEATURE_NOWPLAYING_PLAYBACK = 256;
        public static final int IAP_FEATURE_NOWPLAYING_UPDATES = 128;
        public static final int IAP_FEATURE_OOB_BT_PAIRING = 8192;
        public static final int IAP_FEATURE_POWER_UPDATES = 2;
        public static final int IAP_FEATURE_ROUTE_GUIDANCE = 32768;
        public static final int IAP_FEATURE_USB_DEVICE_AUDIO = 64;
        public static final int IAP_FEATURE_WIRELESS_CARPLAY_UPDATE = 524288;

        public static boolean isIapFeatureValid(int iapFeature) {
            return iapFeature > 0 && (iapFeature & 2097151) == iapFeature;
        }
    }

    public static class SupportedFeature {
        public static final int FEATURE_ALTSCREEN = 8;
        public static final int FEATURE_ENHANCEDSIRI = 1;
        public static final int FEATURE_UICONTEXT = 4;
        public static final int FEATURE_VIEWAREAS = 2;

        public static boolean isFeatureSupported(int setFeature, int feature) {
            return (feature & setFeature) == feature;
        }
    }

    public static class EngineType {
        public static final int CNG = 8;
        public static final int DIESEL = 2;
        public static final int ELECTRIC = 4;
        public static final int GASOLINE = 1;

        public static boolean isEngineTypeValid(int engineType) {
            if (engineType > 0 && engineType <= 15) {
                return true;
            }
            return false;
        }
    }

    public static class AvailableCurrentForDevice {
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_0 = 0;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_1000 = 1000;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_1500 = 1500;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_2100 = 2100;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_2400 = 2400;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_3000 = 3000;
        public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_500 = 500;

        public static boolean isAvailableCurrentValid(int availableCurrent) {
            if (availableCurrent != 0 && availableCurrent != 500 && availableCurrent != 1000 && availableCurrent != 1500 && availableCurrent != 2100 && availableCurrent != 2400 && availableCurrent != 3000) {
                return false;
            }
            return true;
        }
    }

    public static class CallControlFeature {
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_ACCEPT = 4;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_END = 8;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_HOLD = 64;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_INIT = 2;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_MERGE = 32;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_MUTE = 128;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_SENDDTMF = 256;
        public static final int IAP_FEATURE_OPTION_CALL_CONTROLS_SWAP = 16;

        public static boolean isCallControlFeatureValid(int callControlFeature) {
            return callControlFeature > 0 && (callControlFeature & HardkeyConstrants.SCENE_CARPLAY_PHONE) == callControlFeature;
        }
    }

    public static class DisPlayAction {
        public static final int DISPLAYOFF_CANNOT_INTERRUPT = 0;
        public static final int RETURN_DISPLAYOFF = 1;
        public static final int SHOW_LAST_VISIBLE_SCREEN = 2;

        public static boolean isDisPlayActionValid(int disPlayAction) {
            return disPlayAction >= 0 && disPlayAction <= 2;
        }
    }

    public static class CarPlayCapability {
        public static final int WIRED_CARPLAY_CAPABILITY = 1;
        public static final int WIRELESS_CARPLAY_CAPABILITY = 2;

        public static boolean isCarPlayCapabilityValid(int carplayCapability) {
            if (carplayCapability > 0 && carplayCapability < CarPlayConfig.bit(2)) {
                return true;
            }
            return false;
        }

        public static boolean isCarPlayCapabilitySupported(int setCarPlayCapability, int carplayCapability) {
            return isCarPlayCapabilityValid(setCarPlayCapability) && (carplayCapability & setCarPlayCapability) == carplayCapability;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int bit(int bits) {
        int ret = 1 << bits;
        return ret;
    }

    public int getSuggestUrl() {
        return this.mSuggestUrl;
    }

    public int getShowUiParameters() {
        return this.mShowUiParameters;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public String getAccessoryModel() {
        return this.mAccessoryModel;
    }

    public String getAccessoryModelId() {
        return this.mAccessoryModelId;
    }

    public String getHardwareRevision() {
        return this.mHardwareRevision;
    }

    public String getFirmwareRevision() {
        return this.mFirmwareRevision;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public String getDisPlayName() {
        return this.mDisplayName;
    }

    public ScreenInfo[] getScreenInfos() {
        return this.mScreenInfos;
    }

    public int getEngineType() {
        return this.mEngineType;
    }

    public int getVendorId() {
        return this.mVendorId;
    }

    public int getProductId() {
        return this.mProductId;
    }

    public String getBluetoothMacAddress() {
        return this.mBluetoothMacAddress;
    }

    public String getAccessoryName() {
        return this.mAccessoryName;
    }

    public String getProductPlanUuid() {
        return this.mProductPlanUuid;
    }

    public String getOemIconLabel() {
        return this.mOemIconLabel;
    }

    public String[] getOemIconPaths() {
        return this.mOemIconPaths;
    }

    public String getAccessoryOsInfo() {
        return this.mAccessoryOsInfo;
    }

    public boolean getRightHandDrive() {
        return this.mRightHandDrive;
    }

    public boolean isNightMode() {
        return this.mNightMode;
    }

    public boolean isOemIconVisible() {
        return this.mOemIconVisible;
    }

    public int getDisPlayFeatures() {
        return this.mDisPlayFeatures;
    }

    public int getPrimaryInputDevice() {
        return this.mPrimaryInputDevice;
    }

    public int getExtendedFeatures() {
        return this.mExtendedFeatures;
    }

    public int getIapFeatures() {
        return this.mIapFeatures;
    }

    public int getSupportedFeatures() {
        return this.mSupportedFeatures;
    }

    public ViewArea[] getViewAreas() {
        return this.mViewAreas;
    }

    public ViewArea[] getAlt1ViewAreas() {
        return this.mAlt1ViewAreas;
    }

    public SafeArea[] getAlt1SafeAreas() {
        return this.mAlt1SafeAreas;
    }

    public int getViewAreaIndex() {
        return this.mViewAreaIndex;
    }

    public String getFeatures() {
        return this.mFeatures;
    }

    public String[] getHidLanguages() {
        return this.mHidLanguages;
    }

    public boolean isKeepAliveLowPower() {
        return this.mKeepAliveLowPower;
    }

    public boolean isKeepAliveSendStatsAsBody() {
        return this.mKeepAliveSendStatsAsBody;
    }

    public int getProtocolVersion() {
        return this.mProtocolVersion;
    }

    public boolean isLimitedUi() {
        return this.mLimitedUi;
    }

    public int getLimitedUiElements() {
        return this.mLimitedUiElements;
    }

    public String getSourceVersion() {
        return this.mSourceVersion;
    }

    public int getStatusFlags() {
        return this.mStatusFlags;
    }

    public String getAppearanceDefault() {
        return this.mAppearanceDefault;
    }

    public ButtonInfo[] getButtonInfo() {
        return this.mButtonInfo;
    }

    public String[] getUiContextLastOnDisplayUrls() {
        return this.mUiContextLastOnDisplayUrls;
    }

    public String[] getUiContextNowOnDisplayUrls() {
        return this.mUiContextNowOnDisplayUrls;
    }

    public int getPlayKeyMask() {
        return this.mPlayKeyMask;
    }

    public int getTelKeyMask() {
        return this.mTelKeyMask;
    }

    public int getMaxDestinationNameLength() {
        return this.mMaxDestinationNameLength;
    }

    public int getMaxGuidanceManeuverCapacity() {
        return this.mMaxGuidanceManeuverCapacity;
    }

    public int getMaxLaneGuidanceCapacity() {
        return this.mMaxLaneGuidanceCapacity;
    }

    public int getMaxLaneGuidanceDescriptionLength() {
        return this.mMaxLaneGuidanceDescriptionLength;
    }

    public int getAvailableCurrentForDevice() {
        return this.mAvailableCurrentForDevice;
    }

    public int getMaxCurrentRoadNameLength() {
        return this.mMaxCurrentRoadNameLength;
    }

    public int getMaxAfterManeuverRoadNameLength() {
        return this.mMaxAfterManeuverRoadNameLength;
    }

    public int getMaxManeuverDescriptionLength() {
        return this.mMaxManeuverDescriptionLength;
    }

    public int getCallControlFeature() {
        return this.mCallControlFeature;
    }

    public int getIapPlaybackHid() {
        return this.mIapPlaybackHid;
    }

    public int getCarPlayCapability() {
        return this.mCarPlayCapability;
    }

    public String getCurrentLanguage() {
        return this.mCurrentLanguage;
    }

    public String[] getSupportedLanguage() {
        return this.mSupportedLanguage;
    }

    public int getUsbHostTransportInterfaceNum() {
        return this.mUsbHostTransportInterfaceNum;
    }

    public String getIapDeviceNode() {
        return this.mIapDeviceNode;
    }

    public int getDisPlayOffExpected() {
        return this.mDisPlayOffExpected;
    }

    public int getSupportedEnhancedSiriType() {
        return this.mSupportedEnhancedSiriType;
    }

    public boolean isUseMainViewArea() {
        return this.mIsUseMainViewArea;
    }

    public boolean isUseAlt1ViewArea() {
        return this.mIsUseAlt1ViewArea;
    }

    public CarPlayConfig() {
    }

    public CarPlayConfig(Builder builder) {
        this.mManufacturer = builder.mManufacturer;
        this.mAccessoryModel = builder.mAccessoryModel;
        this.mAccessoryModelId = builder.mAccessoryModelId;
        this.mHardwareRevision = builder.mHardwareRevision;
        this.mFirmwareRevision = builder.mFirmwareRevision;
        this.mSerialNumber = builder.mSerialNumber;
        this.mDisplayName = builder.mDisplayName;
        this.mEngineType = builder.mEngineType;
        this.mVendorId = builder.mVendorId;
        this.mProductId = builder.mProductId;
        this.mBluetoothMacAddress = builder.mBluetoothMacAddress;
        this.mAccessoryName = builder.mAccessoryName;
        this.mProductPlanUuid = builder.mProductPlanUuid;
        this.mOemIconLabel = builder.mOemIconLabel;
        this.mOemIconVisible = builder.mOemIconVisible;
        this.mOemIconPaths = builder.mOemIconPaths;
        this.mRightHandDrive = builder.mRightHandDrive;
        this.mNightMode = builder.mNightMode;
        this.mAccessoryOsInfo = builder.mAccessoryOsInfo;
        this.mDisPlayFeatures = builder.mDisPlayFeatures;
        this.mPrimaryInputDevice = builder.mPrimaryInputDevice;
        this.mExtendedFeatures = builder.mExtendedFeatures;
        this.mScreenInfos = builder.mScreenInfos;
        this.mIapFeatures = builder.mIapFeatures;
        this.mSupportedFeatures = builder.mSupportedFeatures;
        this.mViewAreas = builder.mViewAreas;
        this.mAlt1ViewAreas = builder.mAlt1ViewAreas;
        this.mAlt1SafeAreas = builder.mAlt1SafeAreas;
        this.mViewAreaIndex = builder.mViewAreaIndex;
        this.mFeatures = builder.mFeatures;
        this.mHidLanguages = builder.mHidLanguages;
        this.mKeepAliveLowPower = builder.mKeepAliveLowPower;
        this.mKeepAliveSendStatsAsBody = builder.mKeepAliveSendStatsAsBody;
        this.mProtocolVersion = builder.mProtocolVersion;
        this.mLimitedUi = builder.mLimitedUi;
        this.mLimitedUiElements = builder.mLimitedUiElements;
        this.mSourceVersion = builder.mSourceVersion;
        this.mStatusFlags = builder.mStatusFlags;
        this.mAppearanceDefault = builder.mAppearanceDefault;
        this.mButtonInfo = builder.mButtonInfo;
        this.mUiContextLastOnDisplayUrls = builder.mUiContextLastOnDisplayUrls;
        this.mUiContextNowOnDisplayUrls = builder.mUiContextNowOnDisplayUrls;
        this.mPlayKeyMask = builder.mPlayKeyMask;
        this.mTelKeyMask = builder.mTelKeyMask;
        this.mMaxDestinationNameLength = builder.mMaxDestinationNameLength;
        this.mMaxGuidanceManeuverCapacity = builder.mMaxGuidanceManeuverCapacity;
        this.mMaxLaneGuidanceCapacity = builder.mMaxLaneGuidanceCapacity;
        this.mMaxLaneGuidanceDescriptionLength = builder.mMaxLaneGuidanceDescriptionLength;
        this.mAvailableCurrentForDevice = builder.mAvailableCurrentForDevice;
        this.mMaxCurrentRoadNameLength = builder.mMaxCurrentRoadNameLength;
        this.mMaxAfterManeuverRoadNameLength = builder.mMaxAfterManeuverRoadNameLength;
        this.mMaxManeuverDescriptionLength = builder.mMaxManeuverDescriptionLength;
        this.mCallControlFeature = builder.mCallControlFeature;
        this.mIapPlaybackHid = builder.mIapPlaybackHid;
        this.mCarPlayCapability = builder.mCarPlayCapability;
        this.mCurrentLanguage = builder.mCurrentLanguage;
        this.mSupportedLanguage = builder.mSupportedLanguage;
        this.mUsbHostTransportInterfaceNum = builder.mUsbHostTransportInterfaceNum;
        this.mIapDeviceNode = builder.mIapDeviceNode;
        this.mDisPlayOffExpected = builder.mDisPlayOffExpected;
        this.mSupportedEnhancedSiriType = builder.mSupportedEnhancedSiriType;
        this.mSuggestUrl = builder.mSuggestUrl;
        this.mShowUiParameters = builder.mShowUiParameters;
        this.mIsUseMainViewArea = builder.mIsUseMainViewArea;
        this.mIsUseAlt1ViewArea = builder.mIsUseAlt1ViewArea;
    }

    public static class Builder {
        private String mAccessoryModel;
        private String mAccessoryModelId;
        private String mAccessoryName;
        private String mAccessoryOsInfo;
        private SafeArea[] mAlt1SafeAreas;
        private ViewArea[] mAlt1ViewAreas;
        private String mAppearanceDefault;
        private int mAvailableCurrentForDevice;
        private String mBluetoothMacAddress;
        private ButtonInfo[] mButtonInfo;
        private int mCallControlFeature;
        private int mCarPlayCapability;
        private String mCurrentLanguage;
        private int mDisPlayFeatures;
        private int mDisPlayOffExpected;
        private String mDisplayName;
        private int mEngineType;
        private int mExtendedFeatures;
        private String mFeatures;
        private String mFirmwareRevision;
        private String mHardwareRevision;
        private String[] mHidLanguages;
        private String mIapDeviceNode;
        private int mIapFeatures;
        private int mIapPlaybackHid;
        private boolean mIsUseAlt1ViewArea;
        private boolean mIsUseMainViewArea;
        private boolean mKeepAliveLowPower;
        private boolean mKeepAliveSendStatsAsBody;
        private boolean mLimitedUi;
        private int mLimitedUiElements;
        private String mManufacturer;
        private int mMaxAfterManeuverRoadNameLength;
        private int mMaxCurrentRoadNameLength;
        private int mMaxDestinationNameLength;
        private int mMaxGuidanceManeuverCapacity;
        private int mMaxLaneGuidanceCapacity;
        private int mMaxLaneGuidanceDescriptionLength;
        private int mMaxManeuverDescriptionLength;
        private boolean mNightMode;
        private String mOemIconLabel;
        private String[] mOemIconPaths;
        private boolean mOemIconVisible;
        private int mPlayKeyMask;
        private int mPrimaryInputDevice;
        private int mProductId;
        private String mProductPlanUuid;
        private int mProtocolVersion;
        private boolean mRightHandDrive;
        private ScreenInfo[] mScreenInfos;
        private String mSerialNumber;
        private int mShowUiParameters;
        private String mSourceVersion;
        private int mStatusFlags;
        private int mSuggestUrl;
        private int mSupportedEnhancedSiriType;
        private int mSupportedFeatures;
        private String[] mSupportedLanguage;
        private int mTelKeyMask;
        private String[] mUiContextLastOnDisplayUrls;
        private String[] mUiContextNowOnDisplayUrls;
        private int mUsbHostTransportInterfaceNum;
        private int mVendorId;
        private int mViewAreaIndex;
        private ViewArea[] mViewAreas;

        public Builder screenInfos(ScreenInfo[] screenInfos) {
            this.mScreenInfos = screenInfos;
            return this;
        }

        public Builder manufacturer(String manufacturer) {
            this.mManufacturer = manufacturer;
            return this;
        }

        public Builder accessoryModel(String model) {
            this.mAccessoryModel = model;
            return this;
        }

        public Builder accessoryModelId(String modelId) {
            this.mAccessoryModelId = modelId;
            return this;
        }

        public Builder hardwareRevision(String version) {
            this.mHardwareRevision = version;
            return this;
        }

        public Builder firmwareRevision(String version) {
            this.mFirmwareRevision = version;
            return this;
        }

        public Builder serialNumber(String id) {
            this.mSerialNumber = id;
            return this;
        }

        public Builder displayName(String name) {
            this.mDisplayName = name;
            return this;
        }

        public Builder engineType(int engineType) {
            this.mEngineType = engineType;
            return this;
        }

        public Builder vendorId(int id) {
            this.mVendorId = id;
            return this;
        }

        public Builder productId(int id) {
            this.mProductId = id;
            return this;
        }

        public Builder bluetoothMacAddress(String bluetoothMacAddress) {
            Log.d(CarPlayConfig.TAG, "zhouzhou2:" + bluetoothMacAddress);
            this.mBluetoothMacAddress = bluetoothMacAddress;
            return this;
        }

        public Builder accessoryName(String accessoryName) {
            this.mAccessoryName = accessoryName;
            return this;
        }

        public Builder productPlanUuid(String productPlanUuid) {
            this.mProductPlanUuid = productPlanUuid;
            return this;
        }

        public Builder oemIconLabel(String oemIconName) {
            this.mOemIconLabel = oemIconName;
            return this;
        }

        public Builder oemIconPaths(String[] iconPathList) {
            this.mOemIconPaths = iconPathList;
            return this;
        }

        public Builder accessoryOsInfo(String accessoryOs) {
            this.mAccessoryOsInfo = accessoryOs;
            return this;
        }

        public Builder rightHandDrive(boolean rightHandDrive) {
            this.mRightHandDrive = rightHandDrive;
            return this;
        }

        public Builder nightMode(boolean nightMode) {
            this.mNightMode = nightMode;
            return this;
        }

        public Builder oemIconVisible(boolean oemIconVisible) {
            this.mOemIconVisible = oemIconVisible;
            return this;
        }

        public Builder disPlayFeatures(int disPlayFeatures) {
            this.mDisPlayFeatures = disPlayFeatures;
            return this;
        }

        public Builder primaryInputDevice(int primaryInputDevice) {
            this.mPrimaryInputDevice = primaryInputDevice;
            return this;
        }

        public Builder extendedFeatures(int extendedFeatures) {
            this.mExtendedFeatures = extendedFeatures;
            return this;
        }

        public Builder iapFeatures(int iapFeatures) {
            this.mIapFeatures = iapFeatures;
            return this;
        }

        public Builder supportedFeatures(int supportedFeatures) {
            this.mSupportedFeatures = supportedFeatures;
            return this;
        }

        public Builder viewAreas(ViewArea[] viewAreas) {
            this.mViewAreas = viewAreas;
            return this;
        }

        public Builder alt1ViewAreas(ViewArea[] viewAreas) {
            this.mAlt1ViewAreas = viewAreas;
            return this;
        }

        public Builder alt1SafeAreas(SafeArea[] safeAreas) {
            this.mAlt1SafeAreas = safeAreas;
            return this;
        }

        public Builder viewAreaIndex(int viewAreaIndex) {
            this.mViewAreaIndex = viewAreaIndex;
            return this;
        }

        public Builder features(String features) {
            this.mFeatures = features;
            return this;
        }

        public Builder hidLanguages(String[] hidLanguages) {
            this.mHidLanguages = hidLanguages;
            return this;
        }

        public Builder keepAliveLowPower(boolean keepAliveLowPower) {
            this.mKeepAliveLowPower = keepAliveLowPower;
            return this;
        }

        public Builder keepAliveSendStatsAsBody(boolean keepAliveSendStatsAsBody) {
            this.mKeepAliveSendStatsAsBody = keepAliveSendStatsAsBody;
            return this;
        }

        public Builder protocolVersion(int protocolVersion) {
            this.mProtocolVersion = protocolVersion;
            return this;
        }

        public Builder limitedUi(boolean limitedUi) {
            this.mLimitedUi = limitedUi;
            return this;
        }

        public Builder limitedUiElements(int limitedUiElements) {
            this.mLimitedUiElements = limitedUiElements;
            return this;
        }

        public Builder sourceVersion(String sourceVersion) {
            this.mSourceVersion = sourceVersion;
            return this;
        }

        public Builder statusFlags(int statusFlags) {
            this.mStatusFlags = statusFlags;
            return this;
        }

        public Builder appearanceDefault(String appearanceDefault) {
            this.mAppearanceDefault = appearanceDefault;
            return this;
        }

        public Builder buttonInfo(ButtonInfo[] buttonInfo) {
            this.mButtonInfo = buttonInfo;
            return this;
        }

        public Builder uiContextLastOnDisplayUrls(String[] uiContextLastOnDisplayUrls) {
            this.mUiContextLastOnDisplayUrls = uiContextLastOnDisplayUrls;
            return this;
        }

        public Builder uiContextNowOnDisplayUrls(String[] uiContextNowOnDisplayUrls) {
            this.mUiContextNowOnDisplayUrls = uiContextNowOnDisplayUrls;
            return this;
        }

        public Builder playKeyMask(int playKeyMask) {
            this.mPlayKeyMask = playKeyMask;
            return this;
        }

        public Builder telKeyMask(int telKeyMask) {
            this.mTelKeyMask = telKeyMask;
            return this;
        }

        public Builder maxDestinationNameLength(int maxDestinationNameLength) {
            this.mMaxDestinationNameLength = maxDestinationNameLength;
            return this;
        }

        public Builder maxGuidanceManeuverCapacity(int maxGuidanceManeuverCapacity) {
            this.mMaxGuidanceManeuverCapacity = maxGuidanceManeuverCapacity;
            return this;
        }

        public Builder maxLaneGuidanceCapacity(int maxLaneGuidanceCapacity) {
            this.mMaxLaneGuidanceCapacity = maxLaneGuidanceCapacity;
            return this;
        }

        public Builder maxLaneGuidanceDescriptionLength(int maxLaneGuidanceDescriptionLength) {
            this.mMaxLaneGuidanceDescriptionLength = maxLaneGuidanceDescriptionLength;
            return this;
        }

        public Builder availableCurrentForDevice(int availableCurrentForDevice) {
            this.mAvailableCurrentForDevice = availableCurrentForDevice;
            return this;
        }

        public Builder maxCurrentRoadNameLength(int maxCurrentRoadNameLength) {
            this.mMaxCurrentRoadNameLength = maxCurrentRoadNameLength;
            return this;
        }

        public Builder maxAfterManeuverRoadNameLength(int maxAfterManeuverRoadNameLength) {
            this.mMaxAfterManeuverRoadNameLength = maxAfterManeuverRoadNameLength;
            return this;
        }

        public Builder maxManeuverDescriptionLength(int maxManeuverDescriptionLength) {
            this.mMaxManeuverDescriptionLength = maxManeuverDescriptionLength;
            return this;
        }

        public Builder callControlFeature(int callControlFeature) {
            this.mCallControlFeature = callControlFeature;
            return this;
        }

        public Builder iapPlaybackHid(int iapPlaybackHid) {
            this.mIapPlaybackHid = iapPlaybackHid;
            return this;
        }

        public Builder carplayCapability(int carplayCapability) {
            this.mCarPlayCapability = carplayCapability;
            return this;
        }

        public Builder currentLanguage(String currentLanguage) {
            this.mCurrentLanguage = currentLanguage;
            return this;
        }

        public Builder supportedLanguage(String[] supportedLanguage) {
            this.mSupportedLanguage = supportedLanguage;
            return this;
        }

        public Builder usbHostTransportInterfaceNum(int number) {
            this.mUsbHostTransportInterfaceNum = number;
            return this;
        }

        public Builder iapDeviceNode(String deviceNode) {
            this.mIapDeviceNode = deviceNode;
            return this;
        }

        public Builder disPlayOffExpected(int disPlayOffExpected) {
            this.mDisPlayOffExpected = disPlayOffExpected;
            return this;
        }

        public Builder supportedEnhancedSiriType(int type) {
            this.mSupportedEnhancedSiriType = type;
            return this;
        }

        public Builder suggestUrl(int suggestUrl) {
            this.mSuggestUrl = suggestUrl;
            return this;
        }

        public Builder showUiParameters(int showUiParameters) {
            this.mShowUiParameters = showUiParameters;
            return this;
        }

        public Builder useMainViewArea(boolean useMainViewArea) {
            this.mIsUseMainViewArea = useMainViewArea;
            return this;
        }

        public Builder useAlt1ViewArea(boolean useAlt1ViewArea) {
            this.mIsUseAlt1ViewArea = useAlt1ViewArea;
            return this;
        }

        public CarPlayConfig build() {
            return new CarPlayConfig(this);
        }
    }

    public String toString() {
        return "CarPlayConfig{mManufacturer='" + this.mManufacturer + "', mAccessoryModel='" + this.mAccessoryModel + "', mAccessoryModelId='" + this.mAccessoryModelId + "', mHardwareRevision='" + this.mHardwareRevision + "', mFirmwareRevision='" + this.mFirmwareRevision + "', mSerialNumber='" + this.mSerialNumber + "', mDisplayName='" + this.mDisplayName + "', mEngineType=" + this.mEngineType + ", mVendorId=" + this.mVendorId + ", mProductId=" + this.mProductId + ", mBluetoothMacAddress=" + this.mBluetoothMacAddress + "', mAccessoryName='" + this.mAccessoryName + "', mProductPlanUuid='" + this.mProductPlanUuid + "', mOemIconLabel='" + this.mOemIconLabel + "', mOemIconVisible=" + this.mOemIconVisible + ", mOemIconPaths=" + Arrays.toString(this.mOemIconPaths) + ", mRightHandDrive=" + this.mRightHandDrive + ", mNightMode=" + this.mNightMode + ", mAccessoryOsInfo='" + this.mAccessoryOsInfo + "', mDisPlayFeatures=" + this.mDisPlayFeatures + ", mScreenInfos=" + Arrays.toString(this.mScreenInfos) + ", mPrimaryInputDevice=" + this.mPrimaryInputDevice + ", mExtendedFeatures=" + this.mExtendedFeatures + ", mIapFeatures=" + this.mIapFeatures + ", mSupportedFeatures=" + this.mSupportedFeatures + ", mViewAreas=" + Arrays.toString(this.mViewAreas) + ", mViewAreaIndex=" + this.mViewAreaIndex + ", mFeatures='" + this.mFeatures + "', mHidLanguages=" + Arrays.toString(this.mHidLanguages) + ", mKeepAliveLowPower=" + this.mKeepAliveLowPower + ", mKeepAliveSendStatsAsBody=" + this.mKeepAliveSendStatsAsBody + ", mProtocolVersion=" + this.mProtocolVersion + ", mLimitedUi=" + this.mLimitedUi + ", mLimitedUiElements=" + this.mLimitedUiElements + ", mSourceVersion='" + this.mSourceVersion + "', mStatusFlags=" + this.mStatusFlags + ", mAppearanceDefault='" + this.mAppearanceDefault + "', mButtonInfo=" + Arrays.toString(this.mButtonInfo) + ", mUiContextLastOnDisplayUrls=" + Arrays.toString(this.mUiContextLastOnDisplayUrls) + ", mUiContextNowOnDisplayUrls=" + Arrays.toString(this.mUiContextNowOnDisplayUrls) + ", mPlayKeyMask=" + this.mPlayKeyMask + ", mTelKeyMask=" + this.mTelKeyMask + ", mMaxDestinationNameLength=" + this.mMaxDestinationNameLength + ", mMaxCurrentRoadNameLength=" + this.mMaxCurrentRoadNameLength + ", mMaxAfterManeuverRoadNameLength=" + this.mMaxAfterManeuverRoadNameLength + ", mMaxManeuverDescriptionLength=" + this.mMaxManeuverDescriptionLength + ", mMaxGuidanceManeuverCapacity=" + this.mMaxGuidanceManeuverCapacity + ", mMaxLaneGuidanceCapacity=" + this.mMaxLaneGuidanceCapacity + ", mMaxLaneGuidanceDescriptionLength=" + this.mMaxLaneGuidanceDescriptionLength + ", mAvailableCurrentForDevice=" + this.mAvailableCurrentForDevice + ", mCallControlFeature=" + this.mCallControlFeature + ", mCarPlayCapability=" + this.mCarPlayCapability + ", mCurrentLanguage=" + this.mCurrentLanguage + ", mSupportedLanguage=" + Arrays.toString(this.mSupportedLanguage) + ", mUsbHostTransportInterfaceNum=" + this.mUsbHostTransportInterfaceNum + ", mIapDeviceNode=" + this.mIapDeviceNode + ", mDisPlayOffExpected=" + this.mDisPlayOffExpected + ", mSupportedEnhancedSiriType=" + this.mSupportedEnhancedSiriType + ", mSuggestUrl=" + this.mSuggestUrl + ", mShowUiParameters=" + this.mShowUiParameters + '}';
    }

    public void copy(CarPlayConfig carPlayConfig) {
        if (carPlayConfig == null) {
            return;
        }
        this.mManufacturer = carPlayConfig.mManufacturer;
        this.mAccessoryModel = carPlayConfig.mAccessoryModel;
        this.mAccessoryModelId = carPlayConfig.mAccessoryModelId;
        this.mHardwareRevision = carPlayConfig.mHardwareRevision;
        this.mFirmwareRevision = carPlayConfig.mFirmwareRevision;
        this.mSerialNumber = carPlayConfig.mSerialNumber;
        this.mDisplayName = carPlayConfig.mDisplayName;
        this.mEngineType = carPlayConfig.mEngineType;
        this.mVendorId = carPlayConfig.mVendorId;
        this.mProductId = carPlayConfig.mProductId;
        this.mBluetoothMacAddress = carPlayConfig.mBluetoothMacAddress;
        this.mAccessoryName = carPlayConfig.mAccessoryName;
        this.mProductPlanUuid = carPlayConfig.mProductPlanUuid;
        this.mOemIconLabel = carPlayConfig.mOemIconLabel;
        this.mOemIconPaths = carPlayConfig.mOemIconPaths;
        this.mOemIconVisible = carPlayConfig.mOemIconVisible;
        this.mRightHandDrive = carPlayConfig.mRightHandDrive;
        this.mNightMode = carPlayConfig.mNightMode;
        this.mAccessoryOsInfo = carPlayConfig.mAccessoryOsInfo;
        this.mDisPlayFeatures = carPlayConfig.mDisPlayFeatures;
        this.mScreenInfos = carPlayConfig.mScreenInfos;
        this.mPrimaryInputDevice = carPlayConfig.mPrimaryInputDevice;
        this.mExtendedFeatures = carPlayConfig.mExtendedFeatures;
        this.mIapFeatures = carPlayConfig.mIapFeatures;
        this.mSupportedFeatures = carPlayConfig.mSupportedFeatures;
        this.mViewAreas = carPlayConfig.mViewAreas;
        this.mAlt1ViewAreas = carPlayConfig.mAlt1ViewAreas;
        this.mAlt1SafeAreas = carPlayConfig.mAlt1SafeAreas;
        this.mViewAreaIndex = carPlayConfig.mViewAreaIndex;
        this.mFeatures = carPlayConfig.mFeatures;
        this.mHidLanguages = carPlayConfig.mHidLanguages;
        this.mKeepAliveLowPower = carPlayConfig.mKeepAliveLowPower;
        this.mKeepAliveSendStatsAsBody = carPlayConfig.mKeepAliveSendStatsAsBody;
        this.mProtocolVersion = carPlayConfig.mProtocolVersion;
        this.mLimitedUi = carPlayConfig.mLimitedUi;
        this.mLimitedUiElements = carPlayConfig.mLimitedUiElements;
        this.mSourceVersion = carPlayConfig.mSourceVersion;
        this.mStatusFlags = carPlayConfig.mStatusFlags;
        this.mAppearanceDefault = carPlayConfig.mAppearanceDefault;
        this.mButtonInfo = carPlayConfig.mButtonInfo;
        this.mUiContextLastOnDisplayUrls = carPlayConfig.mUiContextLastOnDisplayUrls;
        this.mUiContextNowOnDisplayUrls = carPlayConfig.mUiContextNowOnDisplayUrls;
        this.mPlayKeyMask = carPlayConfig.mPlayKeyMask;
        this.mTelKeyMask = carPlayConfig.mTelKeyMask;
        this.mMaxDestinationNameLength = carPlayConfig.mMaxDestinationNameLength;
        this.mMaxCurrentRoadNameLength = carPlayConfig.mMaxCurrentRoadNameLength;
        this.mMaxAfterManeuverRoadNameLength = carPlayConfig.mMaxAfterManeuverRoadNameLength;
        this.mMaxManeuverDescriptionLength = carPlayConfig.mMaxManeuverDescriptionLength;
        this.mMaxGuidanceManeuverCapacity = carPlayConfig.mMaxGuidanceManeuverCapacity;
        this.mAvailableCurrentForDevice = carPlayConfig.mAvailableCurrentForDevice;
        this.mCallControlFeature = carPlayConfig.mCallControlFeature;
        this.mIapPlaybackHid = carPlayConfig.mIapPlaybackHid;
        this.mCarPlayCapability = carPlayConfig.mCarPlayCapability;
        this.mCurrentLanguage = carPlayConfig.mCurrentLanguage;
        this.mSupportedLanguage = carPlayConfig.mSupportedLanguage;
        this.mUsbHostTransportInterfaceNum = carPlayConfig.mUsbHostTransportInterfaceNum;
        this.mIapDeviceNode = carPlayConfig.mIapDeviceNode;
        this.mDisPlayOffExpected = carPlayConfig.mDisPlayOffExpected;
        this.mSupportedEnhancedSiriType = carPlayConfig.mSupportedEnhancedSiriType;
        this.mSuggestUrl = carPlayConfig.mSuggestUrl;
        this.mShowUiParameters = carPlayConfig.mShowUiParameters;
        this.mIsUseMainViewArea = carPlayConfig.mIsUseMainViewArea;
        this.mIsUseAlt1ViewArea = carPlayConfig.mIsUseAlt1ViewArea;
    }
}
