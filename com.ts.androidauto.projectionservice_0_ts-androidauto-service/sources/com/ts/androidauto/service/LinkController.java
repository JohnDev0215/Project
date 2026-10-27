package com.ts.androidauto.service;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemProperties;
import com.ts.androidauto.aap.common.ConnectionInfo;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.projectionservice.DataConvert;
import com.ts.androidauto.sdk.aidl.data.ConnectRequest;
import com.ts.androidauto.service.common.DeviceConnectionManagerModel;
import com.ts.androidauto.service.device.IconStateManager;
import com.ts.car.core.FoundationProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.dcm.data.ConnectionDevice;

/* JADX INFO: loaded from: classes2.dex */
public class LinkController {
    private static final int MGS_BIND_DCM_FSDK_SUCCESS = 101;
    private static final int MGS_BIND_DCM_FSDK_TIMEOUT = 102;
    private static final int MGS_BIND_DCM_FSDK_TIMEOUT_MS = 5000;
    private static final String TAG = "AAPLinkController";
    private static LinkController mLinkController = new LinkController();
    private IProjectionConnectionManager mConnectionManager;
    private Context mContext;
    private DeviceConnectionManagerModel.Listener mDcmListener;
    private IconStateManager mIconStateManager;
    private IconStateManager.Listener mIconStateManagerListener;
    private LinkControllerListener mLinkControllerListener;
    private boolean mIsDcmServiceConnected = false;
    private boolean mIsNeedUpdateCapability = false;
    private HashMap<String, Boolean> mCapabilityMap = new HashMap<>();
    private Handler mHandler = new Handler() { // from class: com.ts.androidauto.service.LinkController.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 101) {
                if (i == 102) {
                    LogUtil.warning(LinkController.TAG, "MGS_BIND_DCM_FSDK_TIMEOUT restart");
                    SystemProperties.set("dcm.restart.flag", "0");
                    SystemProperties.set("dcm.restart.flag", "1");
                    Process.killProcess(Process.myPid());
                    return;
                }
                return;
            }
            if (LinkController.this.mConnectionManager != null) {
                try {
                    int channel = LinkController.this.mConnectionManager.getSupportedWifiChannel();
                    LogUtil.info(LinkController.TAG, "getSupportedWifiChannel channel:" + channel);
                    if (channel != 0) {
                        removeMessages(102);
                        return;
                    }
                    return;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            LogUtil.warning(LinkController.TAG, "mConnectionManager is null. binder test fail");
        }
    };
    private final IServiceConnectionListener mProjectionServiceListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.service.LinkController.4
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(LinkController.TAG, "mProjectionServiceListener onServiceConnected.");
            LinkController.this.mIsDcmServiceConnected = true;
            try {
                if (LinkController.this.mConnectionManager != null) {
                    LinkController.this.mConnectionManager.registerListener(LinkController.this.mCmServiceListener, 3);
                    LogUtil.debug(LinkController.TAG, "mIsNeedUpdateCapability:" + LinkController.this.mIsNeedUpdateCapability);
                    if (LinkController.this.mIsNeedUpdateCapability && LinkController.this.mCapabilityMap != null && !LinkController.this.mCapabilityMap.isEmpty()) {
                        LinkController.this.mIsNeedUpdateCapability = false;
                        for (Map.Entry<String, Boolean> info : LinkController.this.mCapabilityMap.entrySet()) {
                            LinkController.this.updateProjectionCapability(info.getKey(), AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal(), info.getValue().booleanValue());
                        }
                        LinkController.this.mCapabilityMap.clear();
                    }
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            LinkController.this.mHandler.removeMessages(101);
            LinkController.this.mHandler.sendEmptyMessage(101);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(LinkController.TAG, "mProjectionServiceListener onServiceDisconnected.");
            LinkController.this.mIsDcmServiceConnected = false;
        }
    };
    private IProjectionConnectionListener mCmServiceListener = new IProjectionConnectionListener() { // from class: com.ts.androidauto.service.LinkController.5
        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void disconnectProjection(String devId, int capa, int reason) {
            int linkType;
            LogUtil.debug(LinkController.TAG, "onDisconnect devId:" + LogEncrypt.MacAddressEncrypt(devId) + " capa:" + capa + " reason:" + reason);
            if (capa == 128) {
                linkType = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
            } else {
                if (capa != 1024) {
                    LogUtil.info(LinkController.TAG, "onDisconnect invalid capability, return.");
                    return;
                }
                linkType = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal();
            }
            if (LinkController.this.mLinkControllerListener != null) {
                LinkController.this.mLinkControllerListener.disconnectProjection(devId, linkType, reason);
            }
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onDeviceConnectionUpdated(CmDevice device, int connType, int reason, int srvType) {
            LogUtil.info(LinkController.TAG, "onDeviceConnectionUpdated cmDevice:" + device + " connType:" + connType + " reason:" + reason + " srvType:" + srvType);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onUsbRoleSwitchStarting(String devId, int mode, int srvType) {
            LogUtil.info(LinkController.TAG, "onUsbRoleSwitchStarting devId:" + LogEncrypt.MacAddressEncrypt(devId) + " mode:" + mode);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onUsbRoleSwitchFinished(CmDevice dev, int mode, boolean result, int srvType) {
            LogUtil.info(LinkController.TAG, "onUsbRoleSwitchFinished cmDevice:" + dev + " mode: " + mode + " result:" + result + "srvType" + srvType);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onResetUsbBusStarting(String devId, int srvType) {
            LogUtil.info(LinkController.TAG, "onResetUsbBusStarting devId:" + LogEncrypt.MacAddressEncrypt(devId) + " srvType:" + srvType);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onResetUsbFinished(String devId, boolean result, int srvType) {
            LogUtil.info(LinkController.TAG, "onResetUsbFinished devId:" + LogEncrypt.MacAddressEncrypt(devId) + " result:" + result + " srvType:" + srvType);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onEnableUsbAoaStarting(CmDevice dev, int mode, int srvType) {
            LogUtil.info(LinkController.TAG, "onEnableUsbAoaStarting dev:" + dev + " mode:" + mode + "srvType:" + srvType);
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onEnableUsbAoaFinished(CmDevice dev, int mode, boolean result, int srvType) {
            LogUtil.info(LinkController.TAG, "onEnableUsbAoaFinished 4 param dev:" + dev + " mode:" + mode + " result:" + result + " srvType：" + srvType);
            if (LinkController.this.mLinkControllerListener != null && dev != null) {
                LinkController.this.mLinkControllerListener.onAaEnableUsbAoaFinished(result, dev.getDeviceId(), dev.getUsbDevice());
            }
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onIap2BtDeviceConnected(CmDevice device, int reason, int srvType) {
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onBtOobPairCompleted(String devId, int status, int srvType) {
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onWifiApInfoChanged(WifiConfiguration config, int status, int srvType) {
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void onWifiConnectStatusChanged(String mac, int status, int srvType) {
        }

        @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
        public void connectProjection(CmDevice device, ProjectionLinkParameter plp) {
            LogUtil.info(LinkController.TAG, "onConnectProjection device:" + device + " plp:" + plp);
            if (device == null || plp == null) {
                LogUtil.warning(LinkController.TAG, "onConnectProjection device or plp is null");
                return;
            }
            AndroidAutoDevice androidAutoDevice = LinkController.this.toAndroidAutoDevice(device, plp.getCapability());
            int linkType = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_NULL.ordinal();
            boolean hasAvailableCapability = false;
            if (plp.getCapability() == 128) {
                linkType = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal();
                if (device.isAvailableCapability(128)) {
                    hasAvailableCapability = true;
                }
            } else if (plp.getCapability() == 1024) {
                linkType = AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal();
                if (device.isAvailableCapability(1024)) {
                    hasAvailableCapability = true;
                }
            }
            if (plp.getFromUser()) {
                androidAutoDevice.setConnMode(AndroidAutoConst.ConnMode.MODE_USER_REQUEST.ordinal());
            } else {
                androidAutoDevice.setConnMode(AndroidAutoConst.ConnMode.MODE_AUTO_CONN.ordinal());
            }
            if (LinkController.this.mLinkControllerListener != null) {
                ConnectionInfo connectionInfo = new ConnectionInfo();
                connectionInfo.setAndroidAutoDevice(androidAutoDevice);
                connectionInfo.setLinkType(linkType);
                connectionInfo.setHasAvailableCapability(hasAvailableCapability);
                connectionInfo.setFirstConnect(plp.getFirst());
                connectionInfo.setForceProjection(plp.getForceProjection());
                LinkController.this.mLinkControllerListener.connectProjection(connectionInfo);
            }
        }
    };

    private LinkController() {
    }

    public static LinkController getInstance() {
        return mLinkController;
    }

    public void init(Context context) {
        LogUtil.info(TAG, "SmartProjection versoin number: DMM_v2.0");
        this.mContext = context;
        initConnectionManager(context);
        initDeviceConnectionManager(this.mContext);
        initIconStateManager(context);
        LogUtil.info(TAG, "linkcontroller init end");
    }

    public void registLinkControllerListener(LinkControllerListener linkControllerListener) {
        this.mLinkControllerListener = linkControllerListener;
    }

    public void unRegistLinkControllerListener() {
        this.mLinkControllerListener = null;
    }

    private void initConnectionManager(Context context) {
        if (context != null) {
            IProjectionConnectionManager iProjectionConnectionManager = (IProjectionConnectionManager) FoundationProvider.create(IProjectionConnectionManager.class, new Object[]{context});
            this.mConnectionManager = iProjectionConnectionManager;
            if (iProjectionConnectionManager != null) {
                LogUtil.info(TAG, "initConnectionManager success, mConnectionManager is not null");
                this.mConnectionManager.registerConnectionListener(this.mProjectionServiceListener);
            } else {
                LogUtil.info(TAG, "initConnectionManager fail, mConnectionManager is null");
            }
            this.mHandler.removeMessages(102);
            this.mHandler.sendEmptyMessageDelayed(102, 5000L);
        }
    }

    private void initDeviceConnectionManager(Context context) {
        if (context != null) {
            DeviceConnectionManagerModel.getInstance().init(context);
            this.mDcmListener = new DeviceConnectionManagerModel.Listener() { // from class: com.ts.androidauto.service.LinkController.2
                @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
                public void onOtherProjectionStatusChanged(boolean active) {
                    LogUtil.debug(LinkController.TAG, "onOtherProjectionStatusChanged active " + active);
                    if (LinkController.this.mLinkControllerListener != null) {
                        LinkController.this.mLinkControllerListener.setOtherProjectionActive(active);
                    }
                }

                @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
                public void onRequestProjection(ConnectionDevice device, int type) {
                    LogUtil.debug(LinkController.TAG, "onRequestProjection device:" + device + " capability:" + type);
                    if (type == 64 || type == 128 || type == 256) {
                        if ((device.getAvailableCapabilitys() & 128) == 128) {
                            LinkController.this.notifyRequestProjectionDevice(device, 1);
                        }
                    } else if (type == 1024) {
                        if ((device.getAvailableCapabilitys() & 1024) == 1024) {
                            LinkController.this.notifyRequestProjectionDevice(device, 2);
                        }
                    } else {
                        LogUtil.debug(LinkController.TAG, "onRequestProjection default type:" + type);
                    }
                }

                @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
                public void onDevicesMerged(String oneDevId, String anotherDevId, ConnectionDevice device) {
                    LogUtil.debug(LinkController.TAG, "onDevicesMerged oneDevId: " + oneDevId + ", anotherDevId:" + anotherDevId + ", device:" + device);
                    if (LinkController.this.mLinkControllerListener != null) {
                        LinkController.this.mLinkControllerListener.onDevicesMerged(DataConvert.convertAndroidAutoDevice(device));
                    }
                }

                @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
                public void onDeviceStatusChanged(ConnectionDevice device) {
                    LogUtil.debug(LinkController.TAG, "onDeviceStatusChanged, device:" + device);
                    if (LinkController.this.mLinkControllerListener != null) {
                        if ((device.getCapability() & 128) != 0 || (device.getCapability() & 1024) != 0) {
                            LinkController.this.mLinkControllerListener.onDeviceStatusChanged(DataConvert.convertAndroidAutoDevice(device));
                        }
                    }
                }

                @Override // com.ts.androidauto.service.common.DeviceConnectionManagerModel.Listener
                public void onDeviceDetach(ConnectionDevice device, int connType) {
                    LogUtil.debug(LinkController.TAG, "onDeviceDetach ConnectionDevice:" + device + "connType" + connType);
                    if (connType == 1 && device.getDeviceType() == 1) {
                        LinkController.this.notifyAndroidDetachedOverUsb(device);
                    }
                }
            };
            DeviceConnectionManagerModel.getInstance().registerListener(this.mDcmListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAndroidDetachedOverUsb(ConnectionDevice dev) {
        com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device = null;
        if (dev != null) {
            device = new com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice();
            device.setDeviceName(dev.getFriendlyName());
            device.setUsbSerialNumber(dev.getUsbSerialNumber());
            device.setBtAddr(dev.getBtAddr());
            device.setDeviceStatus(1);
        }
        LinkControllerListener linkControllerListener = this.mLinkControllerListener;
        if (linkControllerListener != null) {
            linkControllerListener.onAndroidDetachedOverUsb(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRequestProjectionDevice(ConnectionDevice dev, int type) {
        com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice device = null;
        if (dev != null) {
            device = DataConvert.convertAndroidAutoDevice(dev);
        }
        LinkControllerListener linkControllerListener = this.mLinkControllerListener;
        if (linkControllerListener != null) {
            linkControllerListener.onRequestProjection(device, type);
        }
    }

    private void initIconStateManager(Context context) {
        if (context != null) {
            IconStateManager iconStateManager = IconStateManager.getInstance();
            this.mIconStateManager = iconStateManager;
            iconStateManager.init(context);
            IconStateManager.Listener listener = new IconStateManager.Listener() { // from class: com.ts.androidauto.service.LinkController.3
                @Override // com.ts.androidauto.service.device.IconStateManager.Listener
                public void onLastAvailableDeviceChanged(String deviceId, int capability) {
                    LogUtil.debug(LinkController.TAG, "onLastAvailableDeviceChanged deviceId: " + LogEncrypt.MacAddressEncrypt(deviceId) + " capability: " + capability);
                    if (LinkController.this.mLinkControllerListener != null) {
                        LinkController.this.mLinkControllerListener.onLastAvailableDeviceChanged(deviceId, capability);
                    }
                }
            };
            this.mIconStateManagerListener = listener;
            this.mIconStateManager.registerListener(listener);
        }
    }

    public void requestAoaMode(AndroidAutoDevice linkDevice, String aoaSerialNumber) throws RemoteException {
        LogUtil.info(TAG, "requesetSetupAoaMode device:" + linkDevice);
        boolean ret = false;
        IProjectionConnectionManager iProjectionConnectionManager = this.mConnectionManager;
        if (iProjectionConnectionManager != null && linkDevice != null) {
            try {
                ret = iProjectionConnectionManager.enableUsbAoa(linkDevice.getDeviceId(), aoaSerialNumber, 1, true);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            LogUtil.info(TAG, "aoa mode serialNumber:" + LogEncrypt.MacAddressEncrypt(aoaSerialNumber));
            if (!ret) {
                LogUtil.error(TAG, "requesetSetupAoaMode device: faild." + linkDevice);
                LinkControllerListener linkControllerListener = this.mLinkControllerListener;
                if (linkControllerListener != null) {
                    linkControllerListener.onAaEnableUsbAoaFinished(false, linkDevice.getDeviceId(), linkDevice.getUsbDevice());
                }
            }
        }
    }

    public void mergeDevice(String deviceId, int mergeType, String mergeInfo) {
        LogUtil.debug(TAG, "mergeDevice deviceId=" + deviceId + " mergeType=" + mergeType + " mergeInfo=" + mergeInfo);
        IProjectionConnectionManager iProjectionConnectionManager = this.mConnectionManager;
        if (iProjectionConnectionManager != null && deviceId != null && mergeInfo != null) {
            if (mergeType == 1) {
                try {
                    iProjectionConnectionManager.mergeDevices(deviceId, mergeInfo, 1);
                    return;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            if (mergeType == 2) {
                try {
                    iProjectionConnectionManager.mergeDevices(deviceId, mergeInfo.toUpperCase(Locale.US), 2);
                    return;
                } catch (RemoteException ex2) {
                    ex2.printStackTrace();
                    return;
                }
            }
            if (mergeType == 3) {
                try {
                    iProjectionConnectionManager.mergeDevices(deviceId, mergeInfo, 5);
                    return;
                } catch (RemoteException ex3) {
                    ex3.printStackTrace();
                    return;
                }
            }
            LogUtil.info(TAG, "mergeDevice unknown type.");
        }
    }

    public String getLastAvailableDevice() {
        IconStateManager iconStateManager = this.mIconStateManager;
        if (iconStateManager != null) {
            return iconStateManager.getLastAvailableDevice();
        }
        return null;
    }

    public int getLastAvailableCapability(String deviceId) {
        LogUtil.info(TAG, "getLastAvailableCapability deviceId: " + deviceId);
        IconStateManager iconStateManager = this.mIconStateManager;
        if (iconStateManager == null || deviceId == null) {
            return 0;
        }
        int capability = iconStateManager.getLastAvailableCapability(deviceId);
        return capability;
    }

    public ArrayList<String> getAvailableDevices() {
        LogUtil.info(TAG, "getAvailableDevices");
        ArrayList<String> availableDeviceList = new ArrayList<>();
        IconStateManager iconStateManager = this.mIconStateManager;
        if (iconStateManager != null) {
            availableDeviceList.addAll(iconStateManager.getAvailableDevices());
        }
        return availableDeviceList;
    }

    public void updateConnectionStatus(AndroidAutoDevice linkDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode errorCode) {
        if (this.mConnectionManager != null && linkDevice != null && linkDevice.getDeviceId() != null) {
            int capability = -1;
            if (linkDevice != null) {
                try {
                    if (linkDevice.getCurrentMode() == AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber()) {
                        capability = getCmsCapability(linkDevice.getLinkType());
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            LogUtil.info(TAG, "updateConnectionStatus linkStatus:" + linkStatus);
            LogUtil.info(TAG, "updateConnectionStatus errorCode: " + errorCode);
            if (linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED || linkStatus == AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR) {
                this.mConnectionManager.updateConnectionStatus(linkDevice.getDeviceId(), capability, getCmsCapabilityState(AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING), getCmsSessionUpdateReason(errorCode));
            }
            this.mConnectionManager.updateConnectionStatus(linkDevice.getDeviceId(), capability, getCmsCapabilityState(linkStatus), getCmsSessionUpdateReason(errorCode));
        }
    }

    public void updateProjectionCapability(String brAddr, int linkType, boolean flag) {
        if (this.mIsDcmServiceConnected && this.mConnectionManager != null) {
            int capability = getCmsCapability(linkType);
            try {
                this.mConnectionManager.updateProjectionCapability(brAddr, capability, flag, 0);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
            this.mCapabilityMap.put(brAddr, Boolean.valueOf(flag));
            this.mIsNeedUpdateCapability = true;
        }
    }

    public void userConnectProjectionFromMd(AndroidAutoDevice linkDevice) {
        DeviceConnectionManagerModel.getInstance().userConnectProjectionFromMd(linkDevice);
    }

    public int getCmsCapability(int linkType) {
        if (linkType == AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal()) {
            return 128;
        }
        if (linkType != AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal()) {
            return -1;
        }
        return 1024;
    }

    public int getCmsCapabilityState(AndroidAutoConst.LinkStatus linkStatus) {
        switch (AnonymousClass6.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[linkStatus.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 3;
            case 7:
                return 4;
            default:
                return 0;
        }
    }

    public static int getCmsSessionUpdateReason(AndroidAutoConst.LinkErrorCode errorCode) {
        if (errorCode == null || AnonymousClass6.$SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkErrorCode[errorCode.ordinal()] != 1) {
            return 0;
        }
        return 16;
    }

    /* JADX INFO: renamed from: com.ts.androidauto.service.LinkController$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkErrorCode;
        static final /* synthetic */ int[] $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus;

        static {
            int[] iArr = new int[AndroidAutoConst.LinkErrorCode.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkErrorCode = iArr;
            try {
                iArr[AndroidAutoConst.LinkErrorCode.ERROR_USER_SELECT_DISCONNECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            int[] iArr2 = new int[AndroidAutoConst.LinkStatus.values().length];
            $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus = iArr2;
            try {
                iArr2[AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATED.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_CONNECT_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_DEACTIVATING.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATING.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_AAP_FRX.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$ts$androidauto$fsdk$common$AndroidAutoConst$LinkStatus[AndroidAutoConst.LinkStatus.LINK_STATUS_ACTIVATED.ordinal()] = 7;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public AndroidAutoDevice toAndroidAutoDevice(CmDevice cmDevice, int capability) {
        AndroidAutoDevice device = null;
        if (cmDevice != null) {
            device = new AndroidAutoDevice();
            device.setDeviceId(cmDevice.getDeviceId());
            device.setStatus(AndroidAutoConst.LinkStatus.LINK_STATUS_AVAILABLE.ordinal());
            if (capability == 128) {
                device.setLinkType(AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRE.ordinal());
                device.setCurrentMode(AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber());
                device.setUsbSerialNumber(cmDevice.getUsbSerialNumber());
                device.setUsbDevice(cmDevice.getUsbDevice());
                device.setBtAddr(cmDevice.getBtAddr());
            } else if (capability == 1024) {
                device.setLinkType(AndroidAutoConst.ConnectionType.CONNECTION_TYPE_WIRELESS.ordinal());
                device.setCurrentMode(AndroidAutoConst.LinkMode.LINK_MODE_ANDROIDAUTO.toNumber());
                device.setUsbSerialNumber(cmDevice.getUsbSerialNumber());
                device.setBtAddr(cmDevice.getBtAddr());
            }
        }
        return device;
    }

    public void connectAndroidAuto(String devId, boolean isUserRequest) {
        DeviceConnectionManagerModel.getInstance().connectAndroidAuto(devId, isUserRequest);
    }

    public void connectAndroidAuto(ConnectRequest request) {
        DeviceConnectionManagerModel.getInstance().connectAndroidAuto(request);
    }

    public void disconnectAndroidAuto(String devId) {
        DeviceConnectionManagerModel.getInstance().disconnectAndroidAuto(devId);
    }

    public void deleteAndroidAutoDevice(String devId) {
        DeviceConnectionManagerModel.getInstance().deleteAndroidAutoDevice(devId);
    }

    public com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice getAndroidAutoDevice(String devId) {
        return DeviceConnectionManagerModel.getInstance().getAndroidAutoDevice(devId);
    }

    public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getUsbAndroidAutoDeviceList() {
        return DeviceConnectionManagerModel.getInstance().getUsbAndroidAutoDeviceList();
    }

    public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getAndroidAutoDeviceList() {
        return DeviceConnectionManagerModel.getInstance().getAndroidAutoDeviceList();
    }

    public String getAutoConnectDeviceId() {
        return DeviceConnectionManagerModel.getInstance().getAutoConnectDeviceId();
    }
}
