package com.ts.carplay.linkadapter.iap.data;

import com.ts.carplay.common.util.LogUtil;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class IapConfig {
    private static final String DEFAULT_CURRENT_LANGUAGE = "zh";
    public static final int ENGINE_TYPE_CNG = 3;
    public static final int ENGINE_TYPE_DIESEL = 1;
    public static final int ENGINE_TYPE_ELECTRIC = 2;
    public static final int ENGINE_TYPE_GASOLINE = 0;
    public static final int IAP_APP_MODE_CARLIFE = 2;
    public static final int IAP_APP_MODE_CARPLAY = 1;
    public static final int IAP_APP_MODE_IPOD = 0;
    public static final int IAP_APP_MODE_MIRRORLINK = 4;
    public static final int IAP_APP_MODE_OOB = 5;
    public static final int IAP_APP_MODE_OTHER = 255;
    public static final int IAP_APP_MODE_WELINK = 3;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_0 = 0;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_1000 = 1000;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_1500 = 1500;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_2100 = 2100;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_2400 = 2400;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_3000 = 3000;
    public static final int IAP_AVAIL_CURRENT_FOR_DEVICE_500 = 500;
    public static final int IAP_FEATURE_ACC_WIFI_INFO_SHARING = 16384;
    public static final int IAP_FEATURE_APP_LAUNCH = 131072;
    public static final int IAP_FEATURE_BT_CONNECTION_UPDATES = 4;
    public static final int IAP_FEATURE_CALL_CONTROLS = 2048;
    public static final int IAP_FEATURE_CALL_STATE_UPDATES = 512;
    public static final int IAP_FEATURE_CARPLAY_CONNECTION = 4194304;
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
    public static final int IAP_FEATURE_OOB_BLUETOOTH_PAIRING = 2097152;
    public static final int IAP_FEATURE_OOB_BT_PAIRING = 8192;
    public static final int IAP_FEATURE_OPTION_OLD_IPOD = 1;
    public static final int IAP_FEATURE_POWER_UPDATES = 2;
    public static final int IAP_FEATURE_ROUTE_GUIDANCE = 32768;
    public static final int IAP_FEATURE_USB_DEVICE_AUDIO = 64;
    public static final int IAP_FEATURE_WIRELESS_CARPLAY_UPDATE = 524288;
    public static final int IAP_HID_PLAYBACK_NEXT = 4;
    public static final int IAP_HID_PLAYBACK_PAUSE = 2;
    public static final int IAP_HID_PLAYBACK_PLAY = 1;
    public static final int IAP_HID_PLAYBACK_PLAY_PAUSE = 64;
    public static final int IAP_HID_PLAYBACK_PREV = 8;
    public static final int IAP_HID_PLAYBACK_REPEAT = 32;
    public static final int IAP_HID_PLAYBACK_SHUFFLE = 16;
    public static final int IAP_NMEA_SENTENCE_GPGGA = 1;
    public static final int IAP_NMEA_SENTENCE_GPGSV = 4;
    public static final int IAP_NMEA_SENTENCE_GPHDT = 64;
    public static final int IAP_NMEA_SENTENCE_GPRMC = 2;
    public static final int IAP_NMEA_SENTENCE_PAACD = 32;
    public static final int IAP_NMEA_SENTENCE_PAGCD = 16;
    public static final int IAP_NMEA_SENTENCE_PASCD = 8;
    public static final int TRANSPORT_TYPE_BT = 2;
    public static final int TRANSPORT_TYPE_USB_DEVICE = 0;
    public static final int TRANSPORT_TYPE_USB_HOST = 1;
    public static final int TRANSPORT_TYPE_WIFI = 3;
    private String mAccessoryModel;
    private String mAccessoryName;
    private int mAvailCurrent;
    private String mBtMacAddress;
    private String mDeviceId;
    private String mDeviceNode;
    private String mDisplayName;
    private ArrayList<EapInfo> mEapInfoList;
    private int mEapInfoNum;
    private int mEngineType;
    private int mFeatureMask;
    private int mFeatureOption;
    private String mFwVersion;
    private String mHwVersion;
    private boolean mIsVehicleInformationComponentDeleted;
    private boolean mIsWirelessCarPlayActive;
    private String mManufacturer;
    private int mMaxAfterManeuverRoadNameLength;
    private int mMaxCurrentRoadNameLength;
    private int mMaxDestinationNameLength;
    private int mMaxGuidanceManeuverCapacity;
    private int mMaxLaneGuidanceCapacity;
    private int mMaxLaneGuidanceDescriptionLength;
    private int mMaxManeuverDescriptionLength;
    private int mNmeaSentenceMask;
    private int mPid;
    private int mPlaybackHidMask;
    private boolean mPlaybackQueueListAvailable;
    private String mPpUuid;
    private String mRouteGuidanceDisplayName;
    private String mSerialNumber;
    private String[] mSupportedLangList;
    private int mSupportedLangNum;
    private int mUsbHostTransportInterfaceNum;
    private int mVid;
    private static final String TAG = IapConfig.class.getSimpleName();
    private static final String[] DEFAULR_SUPPORTED_LANGUAGE_LIST = {"zh"};
    private int mTransportType = -1;
    private int mIapAppMode = -1;
    private String mCurrentLang = "zh";

    public IapConfig() {
        String[] strArr = DEFAULR_SUPPORTED_LANGUAGE_LIST;
        this.mSupportedLangList = strArr;
        this.mSupportedLangNum = strArr.length;
        this.mVid = -1;
        this.mPid = -1;
        this.mEngineType = -1;
        this.mMaxCurrentRoadNameLength = -1;
        this.mMaxDestinationNameLength = -1;
        this.mMaxAfterManeuverRoadNameLength = -1;
        this.mMaxManeuverDescriptionLength = -1;
        this.mMaxGuidanceManeuverCapacity = -1;
        this.mMaxLaneGuidanceCapacity = -1;
        this.mMaxLaneGuidanceDescriptionLength = -1;
        this.mPlaybackQueueListAvailable = false;
        this.mIsVehicleInformationComponentDeleted = false;
    }

    public String getProductPlanUuid() {
        return this.mPpUuid;
    }

    public void setProductPlanUuid(String uuid) {
        this.mPpUuid = uuid;
    }

    public int getIapFeature() {
        return this.mFeatureMask;
    }

    public void setIapFeature(int feature) {
        this.mFeatureMask = feature;
    }

    public boolean isIapFeatureEnabled(int feature) {
        return (this.mFeatureMask & feature) != 0;
    }

    public int getIapFeatureOption() {
        return this.mFeatureOption;
    }

    public void setIapFeatureOption(int option) {
        this.mFeatureOption = option;
    }

    public int getPlaybackHid() {
        return this.mPlaybackHidMask;
    }

    public void setPlaybackHid(int hidMask) {
        this.mPlaybackHidMask = hidMask & 127;
    }

    public boolean isPlaybackHidEnabled(int hid) {
        return (hid == 1 || hid == 2 || hid == 4 || hid == 8 || hid == 16 || hid == 32 || hid == 64) && (this.mPlaybackHidMask & hid) != 0;
    }

    public int getNmeaSentence() {
        return this.mNmeaSentenceMask;
    }

    public void setNmeaSentence(int sentenceMask) {
        this.mNmeaSentenceMask = sentenceMask;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public int getTransportType() {
        return this.mTransportType;
    }

    public void setTransportType(int type) {
        this.mTransportType = type;
    }

    public String getDeviceNode() {
        return this.mDeviceNode;
    }

    public void setDeviceNode(String node) {
        this.mDeviceNode = node;
    }

    public int getIapAppMode() {
        return this.mIapAppMode;
    }

    public void setIapAppMode(int mode) {
        this.mIapAppMode = mode;
    }

    public boolean getIsWirelessCarPlayActive() {
        return this.mIsWirelessCarPlayActive;
    }

    public void setIsWirelessCarPlayActive(boolean isActive) {
        this.mIsWirelessCarPlayActive = isActive;
    }

    public String getBtMacAddress() {
        return this.mBtMacAddress;
    }

    public void setBtMacAddress(String address) {
        this.mBtMacAddress = address;
    }

    public String getAccessoryName() {
        return this.mAccessoryName;
    }

    public void setAccessoryName(String name) {
        this.mAccessoryName = name;
    }

    public String getAccessoryModel() {
        return this.mAccessoryModel;
    }

    public void setAccessoryModel(String model) {
        this.mAccessoryModel = model;
    }

    public String getManufacturer() {
        return this.mManufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.mManufacturer = manufacturer;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.mSerialNumber = serialNumber;
    }

    public String getCurrentLang() {
        return this.mCurrentLang;
    }

    public void setCurrentLang(String currentLang) {
        this.mCurrentLang = currentLang;
    }

    public String[] getSupportedLangList() {
        return this.mSupportedLangList;
    }

    public void setSupportedLangList(String[] supportedLang) {
        this.mSupportedLangList = supportedLang;
        if (supportedLang == null) {
            this.mSupportedLangNum = 0;
        } else {
            this.mSupportedLangNum = supportedLang.length;
        }
    }

    public String getFwVersion() {
        return this.mFwVersion;
    }

    public void setFwVersion(String version) {
        this.mFwVersion = version;
    }

    public String getHwVersion() {
        return this.mHwVersion;
    }

    public void setHwVersion(String version) {
        this.mHwVersion = version;
    }

    public int getVid() {
        return this.mVid;
    }

    public void setVid(int vid) {
        this.mVid = vid;
    }

    public int getPid() {
        return this.mPid;
    }

    public void setPid(int pid) {
        this.mPid = pid;
    }

    public int getAvailableCurrent() {
        return this.mAvailCurrent;
    }

    public void setAvailableCurrent(int current) {
        this.mAvailCurrent = current;
    }

    public int getEngineType() {
        return this.mEngineType;
    }

    public void setEngineType(int type) {
        this.mEngineType = type;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String name) {
        this.mDisplayName = name;
    }

    public String getRouteGuidanceDisplayName() {
        return this.mRouteGuidanceDisplayName;
    }

    public void setRouteguidanceDisplayName(String name) {
        this.mRouteGuidanceDisplayName = name;
    }

    public int getMaxCurrentRoadNameLength() {
        return this.mMaxCurrentRoadNameLength;
    }

    public void setMaxCurrentRoadNameLength(int length) {
        this.mMaxCurrentRoadNameLength = length;
    }

    public int getMaxDestinationNameLength() {
        return this.mMaxDestinationNameLength;
    }

    public void setMaxDestinationNameLength(int length) {
        this.mMaxDestinationNameLength = length;
    }

    public int getMaxAfterManeuverRoadNameLength() {
        return this.mMaxAfterManeuverRoadNameLength;
    }

    public void setMaxAfterManeuverRoadNameLength(int length) {
        this.mMaxAfterManeuverRoadNameLength = length;
    }

    public int getMaxManeuverDescriptionLength() {
        return this.mMaxManeuverDescriptionLength;
    }

    public void setMaxManeuverDescriptionLength(int length) {
        this.mMaxManeuverDescriptionLength = length;
    }

    public int getMaxGuidanceManeuverCapacity() {
        return this.mMaxGuidanceManeuverCapacity;
    }

    public void setMaxGuidanceManeuverCapacity(int capacity) {
        this.mMaxGuidanceManeuverCapacity = capacity;
    }

    public int getMaxLaneGuidanceCapacity() {
        return this.mMaxLaneGuidanceCapacity;
    }

    public void setMaxLaneGuidanceCapacity(int capacity) {
        this.mMaxLaneGuidanceCapacity = capacity;
    }

    public int getMaxLaneGuidanceDescriptionLength() {
        return this.mMaxLaneGuidanceDescriptionLength;
    }

    public void setMaxLaneGuidanceDescriptionLength(int length) {
        this.mMaxLaneGuidanceDescriptionLength = length;
    }

    public int getUsbHostTransportInterfaceNum() {
        return this.mUsbHostTransportInterfaceNum;
    }

    public void setUsbHostTransportInterfaceNum(int num) {
        this.mUsbHostTransportInterfaceNum = num;
    }

    public ArrayList<EapInfo> getEapInfoList() {
        return this.mEapInfoList;
    }

    public void addEapInfo(EapInfo info) {
        if (this.mEapInfoList == null) {
            this.mEapInfoList = new ArrayList<>();
        }
        if (this.mEapInfoList.contains(info)) {
            LogUtil.error(TAG, "Failed to add duplicated eap info!");
        } else {
            this.mEapInfoList.add(info);
            this.mEapInfoNum++;
        }
    }

    public boolean checkValidity() {
        return checkGenericInfo() && checkFeatureMask() && checkTransportType() && checkAccessoryInfo() && checkVehicleInfo() && checkRouteGuidanceInfo() && checkEapInfo();
    }

    private boolean checkGenericInfo() {
        if (this.mIapAppMode == 1 && this.mPpUuid == null) {
            LogUtil.error(TAG, "PpUuid shouldn't be null for CarPlay!");
            return false;
        }
        if (this.mDeviceId == null) {
            LogUtil.error(TAG, "invalid null DeviceId!");
            return false;
        }
        if (this.mDeviceNode == null) {
            LogUtil.error(TAG, "invalid null DeviceNode!");
            return false;
        }
        int i = this.mIapAppMode;
        if (i < 0 || (i > 5 && i != 255)) {
            LogUtil.error(TAG, "invalid IapAppMode value " + this.mIapAppMode + "!");
            return false;
        }
        if (this.mBtMacAddress != null) {
            return true;
        }
        LogUtil.error(TAG, "invalid null BtMacAddress!");
        return false;
    }

    private boolean checkFeatureMask() {
        int i = this.mFeatureMask;
        if (i == 0) {
            LogUtil.error(TAG, "iap feature required but not set!");
            return false;
        }
        if ((i & 32) != 0 && this.mPlaybackHidMask == 0) {
            LogUtil.error(TAG, "playback hid required by not set!");
            return false;
        }
        if ((this.mFeatureMask & 4096) != 0 && this.mNmeaSentenceMask == 0) {
            LogUtil.error(TAG, "nmea sentence required but not set!");
            return false;
        }
        return true;
    }

    private boolean checkTransportType() {
        int i = this.mTransportType;
        if (i < 0 || i > 3) {
            LogUtil.error(TAG, "invalid TransportType value " + this.mTransportType + "!");
            return false;
        }
        if ((i != 1 || this.mIapAppMode >= 1) && (this.mTransportType != 3 || this.mIapAppMode == 1)) {
            return true;
        }
        LogUtil.error(TAG, "transportType " + this.mTransportType + " and iapAppMode " + this.mIapAppMode + " are conflicted!");
        return false;
    }

    private boolean checkAccessoryInfo() {
        if (this.mAccessoryName == null) {
            LogUtil.error(TAG, "invalid null AccessoryName!");
            return false;
        }
        if (this.mAccessoryModel == null) {
            LogUtil.error(TAG, "invalid null AccessoryModel!");
            return false;
        }
        if (this.mManufacturer == null) {
            LogUtil.error(TAG, "invalid null Manufacturer!");
            return false;
        }
        if (this.mSerialNumber == null) {
            LogUtil.error(TAG, "invalid null SerialNumber!");
            return false;
        }
        if (this.mCurrentLang == null) {
            LogUtil.error(TAG, "invalid null CurrentLang!");
            return false;
        }
        if (this.mSupportedLangList == null || this.mSupportedLangNum == 0) {
            LogUtil.error(TAG, "invalid null SupportedLang!");
            return false;
        }
        if (this.mFwVersion == null) {
            LogUtil.error(TAG, "invalid null FwVersion!");
            return false;
        }
        if (this.mHwVersion == null) {
            LogUtil.error(TAG, "invalid null HwVersion!");
            return false;
        }
        if (this.mVid < 0 || this.mPid < 0) {
            LogUtil.error(TAG, "invalid Vid " + this.mVid + " or Pid " + this.mPid + "!");
            return false;
        }
        int i = this.mAvailCurrent;
        if (i != 0 && i != 500 && i != 1000 && i != 1500 && i != 2100 && i != 2400 && i != 3000) {
            LogUtil.error(TAG, "invalid available current " + this.mAvailCurrent + " for device!");
            return false;
        }
        return true;
    }

    private boolean checkVehicleInfo() {
        int i = this.mEngineType;
        if (i < 0 || i > 3) {
            LogUtil.error(TAG, "invalid EngineType value " + this.mEngineType + "!");
            return false;
        }
        if (this.mDisplayName == null) {
            LogUtil.error(TAG, "invalid null DisplayName!");
            return false;
        }
        return true;
    }

    private boolean checkRouteGuidanceInfo() {
        if (this.mRouteGuidanceDisplayName != null) {
            if (this.mMaxCurrentRoadNameLength <= 0 || this.mMaxDestinationNameLength <= 0 || this.mMaxAfterManeuverRoadNameLength <= 0 || this.mMaxManeuverDescriptionLength <= 0 || this.mMaxGuidanceManeuverCapacity <= 0) {
                LogUtil.error(TAG, "invalid RouteGuidance config(value<=0 not allowed)!");
                return false;
            }
            return true;
        }
        return true;
    }

    private boolean checkEapInfo() {
        ArrayList<EapInfo> arrayList = this.mEapInfoList;
        if (arrayList != null) {
            for (EapInfo info : arrayList) {
                if (!info.checkValidity()) {
                    LogUtil.error(TAG, "invalid EAP config: " + info.toString());
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public static final class EapInfo {
        public static final int EAP_MATCH_NO_ACTION = 0;
        public static final int EAP_MATCH_NO_ALERT = 2;
        public static final int EAP_MATCH_NO_COMM_PROTOCOL = 3;
        public static final int EAP_MATCH_OPTIONAL_ACTION = 1;
        private int mEapIdentifier;
        private int mEapMatchAction;
        private String mEapName;
        private boolean mIsEapCarPlay;
        private boolean mIsNativeTransport;
        private int mNativeTransportId;

        public EapInfo(int id, String name, int matchAction, boolean isNative, int nativeTransportId, boolean isCarPlay) {
            this.mEapIdentifier = id;
            this.mEapName = name;
            this.mEapMatchAction = matchAction;
            this.mIsNativeTransport = isNative;
            this.mNativeTransportId = nativeTransportId;
            this.mIsEapCarPlay = isCarPlay;
        }

        public EapInfo(int id, String name, int matchAction) {
            this(id, name, matchAction, false, 0, false);
        }

        public int getEapIdentifier() {
            return this.mEapIdentifier;
        }

        public void setEapIdentifier(int id) {
            this.mEapIdentifier = id;
        }

        public String getEapName() {
            return this.mEapName;
        }

        public void setEapName(String name) {
            this.mEapName = name;
        }

        public int getEapMatchAction() {
            return this.mEapMatchAction;
        }

        public void setEapMatchAction(int matchAction) {
            this.mEapMatchAction = matchAction;
        }

        public boolean getIsNativeTransport() {
            return this.mIsNativeTransport;
        }

        public void setIsNativeTransport(boolean isNative) {
            this.mIsNativeTransport = isNative;
        }

        public int getNativeTransportId() {
            return this.mNativeTransportId;
        }

        public void setNativeTransportId(int nativeTransportId) {
            this.mNativeTransportId = nativeTransportId;
        }

        public boolean getIsEapCarPlay() {
            return this.mIsEapCarPlay;
        }

        public void setIsEapCarPlay(boolean isCarPlay) {
            this.mIsEapCarPlay = isCarPlay;
        }

        public boolean checkValidity() {
            if (this.mEapIdentifier < 0) {
                LogUtil.error(IapConfig.TAG, "invalid negative EapIdentifier!");
                return false;
            }
            if (this.mEapName == null) {
                LogUtil.error(IapConfig.TAG, "invalid null EapName!");
                return false;
            }
            int i = this.mEapMatchAction;
            if (i < 0 || i > 3) {
                LogUtil.error(IapConfig.TAG, "invalid EapMatchAction " + this.mEapMatchAction + "!");
                return false;
            }
            if (this.mIsNativeTransport && this.mNativeTransportId < 0) {
                LogUtil.error(IapConfig.TAG, "invalid negative NativeTransportId!");
                return false;
            }
            return true;
        }

        public String toString() {
            return "EapInfo {mEapIdentifier='" + this.mEapIdentifier + "', mEapName='" + this.mEapName + "', mEapMatchAction='" + this.mEapMatchAction + "', mIsNativeTransport='" + this.mIsNativeTransport + "', mNativeTransportId='" + this.mNativeTransportId + "', mIsEapCarPlay='" + this.mIsEapCarPlay + "'}";
        }
    }

    public boolean getPlaybackQueueListAvailable() {
        return this.mPlaybackQueueListAvailable;
    }

    public void setPlaybackQueueListAvailable(boolean isAvailable) {
        this.mPlaybackQueueListAvailable = isAvailable;
    }

    public boolean getIsVehicleInformationComponentDeleted() {
        return this.mIsVehicleInformationComponentDeleted;
    }

    public void setIsVehicleInformationComponentDeleted(boolean isDeleted) {
        this.mIsVehicleInformationComponentDeleted = isDeleted;
    }
}
