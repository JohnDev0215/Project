package com.ts.carplay.lib.connect;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.ts.carplay.common.data.config.CarPlayConfig;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayIapListener;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import com.ts.carplay.linkadapter.iap.data.IapConfig;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayIapSessionManager {
    private static final String DEFAULT_BT_MAC = "b6:53:2d:67:39:26";
    private static final String IAP_DEVICE_NODE = "/dev/ipodout0";
    private static final String IAP_DEVICE_NODE_BT = "/dev/bluetooth";
    private static final int IAP_FEATURE_OPTION = 0;
    public static final int IAP_INIT_FAILED = 1;
    public static final int IAP_INIT_OK = 0;
    private static final String IAP_USB_OVER_DEVICE_NODE = "/dev/hidraw1";
    public static final int LOCAL_WIFI_NOTREQUESTED = 0;
    public static final int LOCAL_WIFI_REQUESTED = 1;
    public static final int LOCAL_WIFI_WIFIDISABLED = 0;
    public static final int LOCAL_WIFI_WIFIENABLED = 1;
    private static final String TAG = "CarPlayIapSessionManager";
    public static final int TMPDATA_PID = 4608;
    public static final int TMPDATA_VID = 1452;
    public static final int TRANSPORT_TYPE_BT = 2;
    public static final int TRANSPORT_TYPE_USB_DEVICE = 0;
    public static final int TRANSPORT_TYPE_USB_HOST = 1;
    public static final int TRANSPORT_TYPE_WIFI = 3;
    public static final boolean WIRELESS_NOT_ACTIVED = false;
    private static Context mContext;
    private static CarPlayIapSessionManager mIapSessionMng;
    private CarPlayIapListener mBtListener;
    private CarPlayConnect mConnect;
    private CarPlayManager mManager;
    private CarPlayIapListener mUsbDeviceListener;
    private CarPlayIapListener mUsbListener;
    private CarPlayIapListener mWiFiListener;
    private volatile AccessoryWiFiInfo mAccessoryWiFiInfoBackUp = null;
    private volatile int iWifiStatusFlag = 0;
    private volatile int iApReqWifiInfoFlag = 0;
    private String mCurIapSessionMacAddress = "";
    private int mIapFeature = 0;
    private Handler handlerDestory = new Handler() { // from class: com.ts.carplay.lib.connect.CarPlayIapSessionManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            LogUtil.info(CarPlayIapSessionManager.TAG, "handleMessage start");
            int destoryIap = msg.what;
            if (destoryIap == 1) {
                CarPlayIapSessionManager.this.mManager.onDestroyIapSession(17);
            } else if (destoryIap == 2) {
                CarPlayIapSessionManager.this.mManager.onDestroyIapSession(18);
            } else if (destoryIap == 4) {
                CarPlayIapSessionManager.this.mManager.onDestroyIapSession(19);
            } else if (destoryIap == 64) {
                CarPlayIapSessionManager.this.mManager.onDestroyIapSession(16);
            }
            LogUtil.info(CarPlayIapSessionManager.TAG, "handleMessage end");
        }
    };
    private int mIapAuthingType = 0;

    public CarPlayIapSessionManager(CarPlayManager manager, CarPlayConnect connect) {
        this.mManager = manager;
        this.mConnect = connect;
        mIapSessionMng = this;
        this.mUsbListener = new CarPlayIapListener(manager, 1);
        this.mUsbDeviceListener = new CarPlayIapListener(this.mManager, 64);
        this.mWiFiListener = new CarPlayIapListener(this.mManager, 4);
        this.mBtListener = new CarPlayIapListener(this.mManager, 2);
    }

    public static CarPlayIapSessionManager getInstance() {
        CarPlayIapSessionManager carPlayIapSessionManager = mIapSessionMng;
        if (carPlayIapSessionManager == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayTransport before construct was called.");
        }
        return carPlayIapSessionManager;
    }

    public void init(Context context) {
        mContext = context;
    }

    public CarPlayIapListener getCarPlayIapListener(int clientType) {
        switch (clientType) {
            case 16:
                return this.mUsbDeviceListener;
            case 17:
                return this.mUsbListener;
            case 18:
                return this.mBtListener;
            case 19:
                return this.mWiFiListener;
            default:
                return null;
        }
    }

    public void notifyIap2Result(int iapsessiontype, int result) {
        LogUtil.info(TAG, "notifyIap2Result result = " + result + ", iapsessiontype = " + iapsessiontype);
        this.mManager.notifyIap2Result(iapsessiontype, result);
    }

    public void notifyIap2Destory(int sessionType) {
        LogUtil.info(TAG, "notifyIap2Destory sessionType = " + sessionType);
        Message message = Message.obtain();
        message.what = sessionType;
        this.handlerDestory.sendMessage(message);
    }

    public void startIapSession(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "startIapSession sessionType = " + sessionType + "mIapAuthingType=" + this.mIapAuthingType);
        if (sessionType != 1) {
            if (sessionType == 2) {
                this.iApReqWifiInfoFlag = 0;
                LogUtil.info(TAG, "startIapSession setIsCarplayOldConnection=false");
                this.mManager.setIsCarplayOldConnection(false);
                createIapSessionOverBt(device, sessionType);
                this.mIapAuthingType += 2;
                return;
            }
            if (sessionType == 4) {
                createIapSessionOverWifi(device, sessionType);
                this.mIapAuthingType += 4;
                return;
            }
            if (sessionType == 8) {
                destoryIapSession(1);
                createIapSessionOverUsbSameDevice(device, 1);
                this.mIapAuthingType++;
                return;
            } else if (sessionType == 32) {
                destoryIapSession(1);
                createIapSessionOverUsbGetName(device, 1);
                this.mIapAuthingType++;
                return;
            } else {
                if (sessionType == 64) {
                    destoryIapSession(64);
                    createIapSessionOverUsbDevice(device, 64);
                    this.mIapAuthingType += 64;
                    return;
                }
                return;
            }
        }
        destoryIapSession(2);
        createIapSessionOverUsb(device, sessionType);
        this.mIapAuthingType++;
    }

    public String getCurBtMacAddress() {
        return this.mCurIapSessionMacAddress;
    }

    public int destoryIapSession(int sessionType) {
        LogUtil.info(TAG, "destoryIapSession sessionType = " + sessionType + "mIapAuthingType=" + this.mIapAuthingType);
        if (sessionType == 1) {
            int i = this.mIapAuthingType;
            if ((i & 1) > 0) {
                this.mIapAuthingType = i - 1;
                return this.mManager.onDisconnectIapSession(17);
            }
            return -1;
        }
        if (sessionType == 2) {
            int i2 = this.mIapAuthingType;
            if ((i2 & 2) > 0) {
                this.mIapAuthingType = i2 - 2;
                return this.mManager.onDisconnectIapSession(18);
            }
            return -1;
        }
        if (sessionType == 4) {
            int i3 = this.mIapAuthingType;
            if ((i3 & 4) > 0) {
                this.mIapAuthingType = i3 - 4;
                return this.mManager.onDisconnectIapSession(19);
            }
            return -1;
        }
        if (sessionType == 8 || sessionType == 32) {
            int i4 = this.mIapAuthingType;
            if ((i4 & 1) > 0) {
                this.mIapAuthingType = i4 - 1;
                return this.mManager.onDisconnectIapSession(17);
            }
            return -1;
        }
        if (sessionType == 64) {
            int i5 = this.mIapAuthingType;
            if ((i5 & 64) > 0) {
                this.mIapAuthingType = i5 - 64;
                return this.mManager.onDisconnectIapSession(16);
            }
            return -1;
        }
        return -1;
    }

    public void setIapFeature(int iapFeature) {
        LogUtil.info(TAG, "setIapFeature is " + iapFeature);
        if (CarPlayConfig.IapFeature.isIapFeatureValid(iapFeature)) {
            this.mIapFeature = iapFeature;
        }
    }

    private void setCommonIapConfig(IapConfig iapConfig) {
        if (iapConfig != null) {
            setVehicleIapConfig(iapConfig);
            iapConfig.setIapAppMode(1);
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null) {
                String mac = carPlayManager.getBluetoothMacAddress();
                if (TextUtils.isEmpty(mac)) {
                    LogUtil.warning(TAG, "setCommonIapConfig, Bt mac address is null.");
                    iapConfig.setBtMacAddress(DEFAULT_BT_MAC);
                    return;
                } else {
                    iapConfig.setBtMacAddress(mac);
                    return;
                }
            }
            LogUtil.warning(TAG, "setCommonIapConfig, mManager is null.");
            iapConfig.setBtMacAddress(DEFAULT_BT_MAC);
        }
    }

    private void setVehicleIapConfig(IapConfig iapConfig) {
        String phoneDisplayName;
        LogUtil.info(TAG, "setVehicleStaticIapConfig begin! iapConfig:" + iapConfig);
        if (iapConfig != null) {
            CarPlayConfig carPlayConfig = new CarPlayConfig();
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null) {
                carPlayConfig = carPlayManager.getCarPlayConfig();
            }
            if (carPlayConfig == null) {
                carPlayConfig = new CarPlayConfig();
            }
            String serialNumber = carPlayConfig.getSerialNumber();
            if (TextUtils.isEmpty(serialNumber)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, serialNumber is null.");
                serialNumber = "00000000";
            }
            iapConfig.setSerialNumber(serialNumber);
            String manufacturer = carPlayConfig.getManufacturer();
            if (TextUtils.isEmpty(manufacturer)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, manufacturer is null.");
                manufacturer = "OEM";
            }
            iapConfig.setManufacturer(manufacturer);
            String firmwareVersion = carPlayConfig.getFirmwareRevision();
            if (TextUtils.isEmpty(firmwareVersion)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, firmwareVersion is null.");
                firmwareVersion = "0.0.1";
            }
            iapConfig.setFwVersion(firmwareVersion);
            String hardwareVersion = carPlayConfig.getHardwareRevision();
            if (TextUtils.isEmpty(hardwareVersion)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, hardwareVersion is null.");
                hardwareVersion = "0.0.1";
            }
            iapConfig.setHwVersion(hardwareVersion);
            String disPlayName = carPlayConfig.getDisPlayName();
            if (TextUtils.isEmpty(disPlayName)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, disPlayName is null.");
                disPlayName = CarPlayConfig.DEFAULT_DISPLAY_NAME;
            }
            iapConfig.setDisplayName(disPlayName);
            iapConfig.setRouteguidanceDisplayName(disPlayName);
            String accessoryName = carPlayConfig.getAccessoryName();
            if (TextUtils.isEmpty(accessoryName)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, accessoryName is null.");
                accessoryName = CarPlayConfig.DEFAULT_ACCESSORY_NAME;
            }
            try {
                phoneDisplayName = BluetoothAdapter.getDefaultAdapter().getName();
                if (TextUtils.isEmpty(phoneDisplayName)) {
                    LogUtil.warning(TAG, "setVehicleStaticIapConfig, accessoryName is null.");
                    phoneDisplayName = accessoryName;
                }
            } catch (Exception ex) {
                LogUtil.error(TAG, ex.toString());
                phoneDisplayName = accessoryName;
            }
            iapConfig.setAccessoryName(phoneDisplayName);
            String accessoryModelId = carPlayConfig.getAccessoryModelId();
            if (TextUtils.isEmpty(accessoryModelId)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, accessoryModel is null.");
                accessoryModelId = CarPlayConfig.DEFAULT_ACCESSORY_MODEL;
            }
            iapConfig.setAccessoryModel(accessoryModelId);
            String productPlanUuid = carPlayConfig.getProductPlanUuid();
            if (TextUtils.isEmpty(productPlanUuid)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, productPlanUuid is null.");
                productPlanUuid = "00000000";
            }
            iapConfig.setProductPlanUuid(productPlanUuid);
            int engineType = carPlayConfig.getEngineType();
            if (!CarPlayConfig.EngineType.isEngineTypeValid(engineType)) {
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, engineType is valid:" + engineType);
                engineType = 1;
            }
            iapConfig.setEngineType(engineType);
            int usbHostTransportInterfaceNum = carPlayConfig.getUsbHostTransportInterfaceNum();
            if (usbHostTransportInterfaceNum == 0) {
                usbHostTransportInterfaceNum = 1;
                LogUtil.warning(TAG, "setVehicleStaticIapConfig, NcmInterfaceNum is 0.");
            }
            iapConfig.setUsbHostTransportInterfaceNum(usbHostTransportInterfaceNum);
            iapConfig.setVid(carPlayConfig.getVendorId());
            iapConfig.setPid(carPlayConfig.getProductId());
            setVehicleStaticIapConfig(carPlayConfig, iapConfig);
        }
    }

    private void setVehicleStaticIapConfig(CarPlayConfig carPlayConfig, IapConfig iapConfig) {
        int callControlFeature = carPlayConfig.getCallControlFeature();
        if (CarPlayConfig.CallControlFeature.isCallControlFeatureValid(callControlFeature)) {
            iapConfig.setIapFeatureOption(callControlFeature);
            LogUtil.info(TAG, "setVehicleStaticIapConfig, callControlFeature is " + callControlFeature);
        }
        int availableCurrent = carPlayConfig.getAvailableCurrentForDevice();
        if (!CarPlayConfig.AvailableCurrentForDevice.isAvailableCurrentValid(availableCurrent)) {
            LogUtil.warning(TAG, "setVehicleStaticIapConfig, availableCurrent is invalid:" + availableCurrent);
            availableCurrent = 1000;
        }
        iapConfig.setAvailableCurrent(availableCurrent);
        int maxDestinationNameLength = carPlayConfig.getMaxDestinationNameLength();
        LogUtil.warning(TAG, "setVehicleStaticIapConfig, maxDestinationNameLength = " + maxDestinationNameLength);
        if (maxDestinationNameLength < 0) {
            maxDestinationNameLength = 10;
        }
        iapConfig.setMaxDestinationNameLength(maxDestinationNameLength);
        int maxAfterManeuverRoadNameLength = carPlayConfig.getMaxAfterManeuverRoadNameLength();
        LogUtil.warning(TAG, "setVehicleStaticIapConfig, maxAfterManeuverRoadNameLength = " + maxAfterManeuverRoadNameLength);
        if (maxAfterManeuverRoadNameLength < 0) {
            maxAfterManeuverRoadNameLength = 10;
        }
        iapConfig.setMaxAfterManeuverRoadNameLength(maxAfterManeuverRoadNameLength);
        int maxCurrentRoadNameLength = carPlayConfig.getMaxCurrentRoadNameLength();
        LogUtil.warning(TAG, "setVehicleStaticIapConfig, maxCurrentRoadNameLength = " + maxCurrentRoadNameLength);
        if (maxCurrentRoadNameLength < 0) {
            maxCurrentRoadNameLength = 10;
        }
        iapConfig.setMaxCurrentRoadNameLength(maxCurrentRoadNameLength);
        iapConfig.setMaxGuidanceManeuverCapacity(1);
        iapConfig.setMaxLaneGuidanceCapacity(1);
        int maxLaneGuidanceDescriptionLength = carPlayConfig.getMaxLaneGuidanceDescriptionLength();
        LogUtil.warning(TAG, "setVehicleStaticIapConfig, maxLaneGuidanceDescriptionLength = " + maxLaneGuidanceDescriptionLength);
        if (maxLaneGuidanceDescriptionLength < 0) {
            maxLaneGuidanceDescriptionLength = 10;
        }
        iapConfig.setMaxLaneGuidanceDescriptionLength(maxLaneGuidanceDescriptionLength);
        int maxManeuverDescriptionLength = carPlayConfig.getMaxManeuverDescriptionLength();
        LogUtil.warning(TAG, "setVehicleStaticIapConfig, maxManeuverDescriptionLength = " + maxManeuverDescriptionLength);
        if (maxManeuverDescriptionLength < 0) {
            maxManeuverDescriptionLength = 10;
        }
        iapConfig.setMaxManeuverDescriptionLength(maxManeuverDescriptionLength);
        String currentLang = carPlayConfig.getCurrentLanguage();
        if (TextUtils.isEmpty(currentLang)) {
            LogUtil.warning(TAG, "setVehicleStaticIapConfig, currentLang is null.");
            currentLang = CarPlayConfig.DEFAULT_CURRENT_LANGUAGE;
        }
        iapConfig.setCurrentLang(currentLang);
        String[] supportedLangList = carPlayConfig.getSupportedLanguage();
        if (supportedLangList == null) {
            LogUtil.warning(TAG, "setVehicleStaticIapConfig, supportedLangList is null.");
            supportedLangList = CarPlayConfig.DEFAULT_SUPPORTED_LANGUAGE_LIST;
        }
        iapConfig.setSupportedLangList(supportedLangList);
    }

    private int getConfig(int config) {
        CarPlayManager carPlayManager = this.mManager;
        if (carPlayManager == null) {
            return 0;
        }
        if (config == 2097152) {
            if (!carPlayManager.isWiredCapabilitySupported() || !this.mManager.isWirelessCapabilitySupported()) {
                return 0;
            }
            return 2097152;
        }
        if (config != 1048576 || !carPlayManager.isWiredCapabilitySupported() || !this.mManager.isWirelessCapabilitySupported()) {
            return 0;
        }
        return 1048576;
    }

    private String getIapDeviceNode() {
        CarPlayManager carPlayManager = this.mManager;
        if (carPlayManager == null) {
            return IAP_DEVICE_NODE;
        }
        CarPlayConfig carPlayConfig = carPlayManager.getCarPlayConfig();
        if (carPlayConfig == null || !this.mManager.isWiredCapabilitySupported()) {
            LogUtil.info(TAG, "getIapDeviceNode IAP_DEVICE_NODE== /dev/ipodout0");
            return IAP_DEVICE_NODE;
        }
        String iapDeviceNode = carPlayConfig.getIapDeviceNode();
        if (TextUtils.isEmpty(iapDeviceNode)) {
            LogUtil.warning(TAG, "setDeviceNode, iapDeviceNode is null.");
            iapDeviceNode = IAP_DEVICE_NODE;
        }
        LogUtil.info(TAG, "getIapDeviceNode iapDeviceNode== " + iapDeviceNode);
        return iapDeviceNode;
    }

    private void createIapSessionOverBt(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverBt begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setIapFeature(getConfig(1048576) | 4734977);
        iapConfig.setPlaybackHid(0);
        iapConfig.setNmeaSentence(0);
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setTransportType(2);
        iapConfig.setDeviceNode(IAP_DEVICE_NODE_BT);
        iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        setCommonIapConfig(iapConfig);
        iapConfig.setIsVehicleInformationComponentDeleted(true);
        this.mCurIapSessionMacAddress = device.getBtAddr();
        this.mBtListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(18, iapConfig);
        this.mManager.onConnectIapSession(18);
    }

    private void createIapSessionOverUsb(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverUsb begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setIapFeature(getConfig(1048576) | 6593191 | this.mIapFeature);
        iapConfig.setNmeaSentence(11);
        iapConfig.setTransportType(1);
        iapConfig.setDeviceNode(getIapDeviceNode());
        if (this.mConnect.isWiFiConnected()) {
            iapConfig.setIsWirelessCarPlayActive(true);
        } else {
            iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        }
        setIapPlaybackHid(iapConfig);
        setCommonIapConfig(iapConfig);
        this.mUsbListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(17, iapConfig);
        this.mManager.onConnectIapSession(17);
    }

    private void createIapSessionOverUsbSameDevice(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverUsbSameDevice begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setIapFeature(getConfig(8192) | 3);
        iapConfig.setTransportType(1);
        iapConfig.setDeviceNode(getIapDeviceNode());
        if (this.mConnect.isWiFiConnected()) {
            iapConfig.setIsWirelessCarPlayActive(true);
        } else {
            iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        }
        setCommonIapConfig(iapConfig);
        this.mUsbListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(17, iapConfig);
        this.mManager.onConnectIapSession(17);
    }

    private void createIapSessionOverUsbGetName(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverUsbGetName begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setIapFeature(getConfig(8192) | 301731 | this.mIapFeature);
        iapConfig.setNmeaSentence(11);
        iapConfig.setTransportType(1);
        iapConfig.setDeviceNode(getIapDeviceNode());
        if (this.mConnect.isWiFiConnected()) {
            iapConfig.setIsWirelessCarPlayActive(true);
        } else {
            iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        }
        setIapPlaybackHid(iapConfig);
        setCommonIapConfig(iapConfig);
        this.mUsbListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(17, iapConfig);
        this.mManager.onConnectIapSession(17);
    }

    private void createIapSessionOverUsbDevice(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverUsbDevice begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setIapFeature(3145730);
        iapConfig.setTransportType(0);
        iapConfig.setDeviceNode(IAP_USB_OVER_DEVICE_NODE);
        if (this.mConnect.isWiFiConnected()) {
            iapConfig.setIsWirelessCarPlayActive(true);
        } else {
            iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        }
        setCommonIapConfig(iapConfig);
        iapConfig.setAvailableCurrent(1500);
        iapConfig.setIapAppMode(5);
        this.mUsbDeviceListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(16, iapConfig);
        this.mManager.onConnectIapSession(16);
    }

    private void createIapSessionOverWifi(LinkDevice device, int sessionType) {
        LogUtil.info(TAG, "createIapSessionOverWifi begin!");
        IapConfig iapConfig = new IapConfig();
        iapConfig.setIapFeature(this.mIapFeature | 563873);
        iapConfig.setNmeaSentence(11);
        iapConfig.setDeviceId(device.getDeviceId());
        iapConfig.setTransportType(3);
        iapConfig.setDeviceNode(IAP_DEVICE_NODE);
        iapConfig.setIsWirelessCarPlayActive(device.isWireLessLink());
        setIapPlaybackHid(iapConfig);
        setCommonIapConfig(iapConfig);
        this.mWiFiListener.setDeviceId(device.getDeviceId());
        this.mManager.onCreateIapSession(19, iapConfig);
        this.mManager.onConnectIapSession(19);
    }

    private void setIapPlaybackHid(IapConfig iapConfig) {
        LogUtil.debug(TAG, "setIapPlaybackHid.");
        if (iapConfig != null) {
            CarPlayConfig carPlayConfig = new CarPlayConfig();
            CarPlayManager carPlayManager = this.mManager;
            if (carPlayManager != null) {
                carPlayConfig = carPlayManager.getCarPlayConfig();
            }
            if (carPlayConfig == null) {
                carPlayConfig = new CarPlayConfig();
            }
            int iapHidPlayback = carPlayConfig.getIapPlaybackHid();
            if (iapHidPlayback == 0) {
                iapHidPlayback = 127;
            }
            LogUtil.debug(TAG, "setIapPlaybackHid: iapHidPlayback = " + iapHidPlayback);
            iapConfig.setPlaybackHid(iapHidPlayback);
        }
    }

    public void clearWifiConfiguration() {
        LogUtil.info(TAG, "clearWifiConfiguration");
        this.mAccessoryWiFiInfoBackUp = null;
        this.iWifiStatusFlag = 0;
    }

    public void setWifiConfiguration(AccessoryWiFiInfo wifiConf) {
        LogUtil.info(TAG, "setWifiConfiguration wifiConf = " + wifiConf);
        if (wifiConf != null) {
            this.mAccessoryWiFiInfoBackUp = wifiConf;
            this.iWifiStatusFlag = 1;
            sendAccWiFiConfigInfo();
        }
    }

    public void iapReqWifiInfo() {
        LogUtil.info(TAG, "iapReqWifiInfo");
        this.iApReqWifiInfoFlag = 1;
        sendAccWiFiConfigInfo();
    }

    public void sendAccWiFiConfigInfo() {
        LogUtil.info(TAG, "sendAccWiFiConfigInfo");
        if (this.mAccessoryWiFiInfoBackUp != null && this.iWifiStatusFlag == 1 && this.iApReqWifiInfoFlag == 1) {
            this.iApReqWifiInfoFlag = 0;
            LogUtil.debug(TAG, "sendAccWiFiConfigInfo" + this.mAccessoryWiFiInfoBackUp);
            this.mManager.onSendAccessoryWiFiConfigurationInformation(18, this.mAccessoryWiFiInfoBackUp);
        }
    }

    public String getMacAddress() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return null;
        }
        String mac = this.mAccessoryWiFiInfoBackUp.getMacAddress();
        return mac;
    }

    public String getIpAddress() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return null;
        }
        String ipAddress = this.mAccessoryWiFiInfoBackUp.getIpAddress();
        return ipAddress;
    }

    public String getSsid() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return null;
        }
        String ssid = this.mAccessoryWiFiInfoBackUp.getSsid();
        return ssid;
    }

    public String getPassphrase() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return null;
        }
        String passphrase = this.mAccessoryWiFiInfoBackUp.getPassphrase();
        return passphrase;
    }

    public int getChannel() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return -1;
        }
        int channel = this.mAccessoryWiFiInfoBackUp.getChannel();
        return channel;
    }

    public int getSecurity() {
        if (this.mAccessoryWiFiInfoBackUp == null) {
            return -1;
        }
        int security = this.mAccessoryWiFiInfoBackUp.getSecurity();
        return security;
    }

    public AccessoryWiFiInfo getWiFiInfoBackUp() {
        return this.mAccessoryWiFiInfoBackUp;
    }
}
