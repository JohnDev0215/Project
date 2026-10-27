package com.ts.carplay.linkadapter.link;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.ts.car.core.FoundationProvider;
import com.ts.car.core.ServiceProvider;
import com.ts.carplay.adapter.common.BaseAdapter;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.linkadapter.device.DeviceManager;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.dcm.IDeviceConnectionListener;
import ts.car.service.dcm.IDeviceConnectionManager;
import ts.car.service.dcm.data.ConnectionDevice;
import ts.car.service.dcm.data.Device;

/* JADX INFO: loaded from: classes.dex */
public class LinkAdapter extends BaseAdapter implements ILinkAdapter {
    private static final int BIND_SERVICE_TIMEOUT_MS = 3000;
    private static final int DCM_SERVICE_LISTENER_STATE_CONNECTED = 1;
    private static final int DCM_SERVICE_LISTENER_STATE_DEFAULT = 0;
    private static final int DCM_SERVICE_LISTENER_STATE_DISCONNECTED = 2;
    private static final int MGS_BIND_SERVICE_TIMEOUT = 103;
    private static final int MGS_DEVICE_SERVICE_STATE_CHANGED = 102;
    private static final int MGS_PROJECTION_SERVICE_STATE_CHANGED = 101;
    private static final String TAG = "LinkAdapter";
    private IProjectionConnectionManager mConnectionManager;
    private Context mContext;
    private int mCurrentDeviceServiceState;
    private int mCurrentProjectionServiceState;
    private IDeviceConnectionListener mDeviceConnectionListener;
    private IDeviceConnectionManager mDeviceConnectionManager;
    private DeviceManager mDeviceManager;
    private final IServiceConnectionListener mDeviceServiceListener;
    public boolean mIsUserConnected;
    private ILinkAdapterListener mListener;
    private IProjectionConnectionListener mProjectionConnectionListener;
    private final IServiceConnectionListener mProjectionServiceListener;

    public LinkAdapter(Looper looper) {
        super(looper);
        this.mCurrentProjectionServiceState = 0;
        this.mCurrentDeviceServiceState = 0;
        this.mIsUserConnected = false;
        this.mProjectionServiceListener = new IServiceConnectionListener() { // from class: com.ts.carplay.linkadapter.link.LinkAdapter.1
            @Override // ts.car.service.IServiceConnectionListener
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(LinkAdapter.TAG, "mProjectionServiceListener onServiceConnected.");
                try {
                    if (LinkAdapter.this.mConnectionManager != null) {
                        LinkAdapter.this.mConnectionManager.registerListener(LinkAdapter.this.mProjectionConnectionListener, 1);
                    }
                    Message message = LinkAdapter.this.obtainMessage(LinkAdapter.MGS_PROJECTION_SERVICE_STATE_CHANGED);
                    message.arg1 = 1;
                    message.sendToTarget();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }

            @Override // ts.car.service.IServiceConnectionListener
            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(LinkAdapter.TAG, "mProjectionServiceListener onServiceDisconnected.");
                Message message = LinkAdapter.this.obtainMessage(LinkAdapter.MGS_PROJECTION_SERVICE_STATE_CHANGED);
                message.arg1 = 2;
                message.sendToTarget();
            }
        };
        this.mDeviceServiceListener = new IServiceConnectionListener() { // from class: com.ts.carplay.linkadapter.link.LinkAdapter.2
            @Override // ts.car.service.IServiceConnectionListener
            public void onServiceConnected(IPlatformService service) {
                LogUtil.debug(LinkAdapter.TAG, "mDeviceServiceListener onServiceConnected.");
                try {
                    if (LinkAdapter.this.mDeviceConnectionManager != null) {
                        LinkAdapter.this.mDeviceConnectionManager.registerListener(LinkAdapter.this.mDeviceConnectionListener, 0);
                        LinkAdapter.this.initDeviceList();
                    }
                    Message message = LinkAdapter.this.obtainMessage(LinkAdapter.MGS_DEVICE_SERVICE_STATE_CHANGED);
                    message.arg1 = 1;
                    message.sendToTarget();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }

            @Override // ts.car.service.IServiceConnectionListener
            public void onServiceDisconnected(IPlatformService service) {
                LogUtil.debug(LinkAdapter.TAG, "mDeviceServiceListener onServiceDisconnected.");
                Message message = LinkAdapter.this.obtainMessage(LinkAdapter.MGS_DEVICE_SERVICE_STATE_CHANGED);
                message.arg1 = 2;
                message.sendToTarget();
            }
        };
        this.mProjectionConnectionListener = new IProjectionConnectionListener() { // from class: com.ts.carplay.linkadapter.link.LinkAdapter.3
            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void connectProjection(CmDevice device, ProjectionLinkParameter plp) {
                LogUtil.debug(LinkAdapter.TAG, "onConnect cmDevice:" + device + " plp:" + plp);
                if (device != null && plp != null) {
                    LinkAdapter.this.connect(device, plp.getCapability(), plp.getFirst(), plp.getFromUser());
                }
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void disconnectProjection(String devId, int capa, int reason) {
                LogUtil.debug(LinkAdapter.TAG, "onDisconnect devId:" + devId + " capa:" + capa + " reason:" + reason);
                LinkAdapter.this.disconnect(devId, capa, reason);
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onDeviceConnectionUpdated(CmDevice device, int connType, int reason, int srvType) {
                LogUtil.debug(LinkAdapter.TAG, "onDeviceConnectionUpdated cmDevice:" + device + " connType:" + connType + " reason:" + reason + " srvType:" + srvType);
                if (srvType == 1 && device != null) {
                    if (LinkAdapter.this.mListener != null) {
                        LinkAdapter.this.mListener.onSameDeviceConnect(ConnectionManagerModel.toCarPlayDevice(device, 64), reason);
                    } else {
                        LogUtil.warning(LinkAdapter.TAG, "onDeviceConnectionUpdated mListener is null.");
                    }
                }
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onUsbRoleSwitchStarting(String devId, int mode, int srvType) {
                LogUtil.debug(LinkAdapter.TAG, "onUsbRoleSwitchStarting devId:" + devId + " mode:" + mode);
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onUsbRoleSwitchFinished(CmDevice dev, int mode, boolean result, int srvType) {
                LogUtil.info(LinkAdapter.TAG, "onUsbRoleSwitchFinished cmDevice:" + dev + " mode: " + mode + " result:" + result + "srvType" + srvType);
                if (dev != null) {
                    LinkAdapter.this.onUsbRoleSwitchFinished(dev, mode, result, srvType);
                }
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onResetUsbBusStarting(String devId, int srvType) {
                LogUtil.debug(LinkAdapter.TAG, "onResetUsbBusStarting devId:" + devId + " srvType:" + srvType);
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onResetUsbFinished(String devId, boolean result, int srvType) {
                LogUtil.debug(LinkAdapter.TAG, "onResetUsbFinished devId:" + devId + " result:" + result + " srvType:" + srvType);
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void onIap2BtDeviceConnected(CmDevice device, int reason, int srvType) {
                if (srvType == 1) {
                    if (LinkAdapter.this.mListener != null) {
                        LinkDevice dev = ConnectionManagerModel.toCarPlayDevice(device, 512);
                        LinkAdapter.this.mListener.onIap2BtDeviceConnected(dev, reason);
                    } else {
                        LogUtil.warning(LinkAdapter.TAG, "onIap2BtDeviceConnected mListener is null.");
                    }
                }
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void checkUsbDeviceName(CmDevice device, int srv) {
                if (srv == 1) {
                    if (LinkAdapter.this.mListener != null) {
                        LinkDevice dev = ConnectionManagerModel.toCarPlayDevice(device, 64);
                        if (dev != null && device != null) {
                            LinkAdapter.this.mListener.checkUsbDeviceName(dev);
                            return;
                        }
                        return;
                    }
                    LogUtil.warning(LinkAdapter.TAG, "onCheckUsbDeviceName mListener is null.");
                }
            }

            @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener
            public void disconnectIapSession(String devId, int srv) {
                if (srv == 1) {
                    if (LinkAdapter.this.mListener != null) {
                        LinkAdapter.this.mListener.disconnectIapSession(devId);
                    } else {
                        LogUtil.warning(LinkAdapter.TAG, "onDisconnectIapSession mListener is null.");
                    }
                }
            }
        };
        this.mDeviceConnectionListener = new IDeviceConnectionListener() { // from class: com.ts.carplay.linkadapter.link.LinkAdapter.4
            @Override // ts.car.service.dcm.IDeviceConnectionListener
            public void onProjectionServiceStateChanged(ConnectionDevice device, int capa, int status, int reason) {
                if (device != null) {
                    if (LinkAdapter.this.mListener != null) {
                        int projectionType = 2;
                        if (64 == capa) {
                            projectionType = 0;
                        } else if (512 == capa) {
                            projectionType = 1;
                        }
                        LinkAdapter.this.mListener.onProjectionConnectionStateChanged(device.getDeviceId(), projectionType, ConnectionManagerModel.convertLinkState(status), reason);
                        return;
                    }
                    return;
                }
                LogUtil.warning(LinkAdapter.TAG, "onProjectionServiceStateChanged device is null.");
            }

            @Override // ts.car.service.dcm.IDeviceConnectionListener
            public void onDeviceCapabilityChecked(String devId, int capability, boolean isSupport, int reason) {
                LogUtil.debug(LinkAdapter.TAG, "onDeviceCapabilityChecked devId:" + devId + " capability:" + capability + " isSupport:" + isSupport + " reason:" + reason);
                if (LinkAdapter.this.mListener != null) {
                    LinkAdapter.this.mListener.onWirelessCarPlayUpdate(devId, isSupport);
                }
            }

            @Override // ts.car.service.dcm.IDeviceConnectionListener
            public void onProjectionRequested(ConnectionDevice device, int type) {
                LogUtil.debug(LinkAdapter.TAG, "onRequestProjection ConnectionDevice:" + device + "type" + type);
                if (type == 64 || type == 128 || type == 256) {
                    boolean supported = (device.getAvailableCapabilitys() & 64) == 64;
                    if (supported || device.getDeviceType() == 2) {
                        LinkAdapter.this.notifyiPhoneAttachedOverUsb(device, supported);
                        return;
                    }
                    return;
                }
                if (type == 512) {
                    if ((device.getAvailableCapabilitys() & 512) == 512) {
                        LinkAdapter.this.notifyRequestProjectionDevice(device, 2);
                    }
                } else {
                    LogUtil.debug(LinkAdapter.TAG, "onRequestProjection default type:" + type);
                }
            }

            @Override // ts.car.service.dcm.IDeviceConnectionListener
            public void onDeviceStatusChanged(ConnectionDevice device) throws RemoteException {
                int linkType;
                LogUtil.debug(LinkAdapter.TAG, "onDeviceStatusChanged device :" + device);
                if (LinkAdapter.this.mListener != null) {
                    if ((device.getCapability() & 512) != 0 || (device.getCapability() & 64) != 0) {
                        CarPlayDevice cpDevice = ConnectionManagerModel.toCarPlayAidlDevice(device);
                        LinkAdapter.this.mListener.onDeviceStatusChanged(cpDevice);
                        LinkDevice linkDevice = LinkAdapter.this.getCarPlayDevice(device.getDeviceId());
                        if ((device.getCapability() & 64) != 0) {
                            linkType = 1;
                        } else {
                            linkType = 2;
                        }
                        linkDevice.setLinkType(linkType);
                        LogUtil.debug(LinkAdapter.TAG, "onDeviceStatusChanged cpDeviceStatus :" + cpDevice.getDeviceStatus() + ",session status:" + LinkAdapter.this.mDeviceManager.getConnectStatus(linkDevice) + ",linkDevice:" + linkDevice);
                        if ((cpDevice.getDeviceStatus() == -1 || cpDevice.getDeviceStatus() == 1) && LinkAdapter.this.mDeviceManager.getConnectStatus(linkDevice) == 1) {
                            LinkAdapter.this.mDeviceManager.saveConnectStatus(linkDevice, 4);
                        }
                    }
                }
            }

            @Override // ts.car.service.dcm.IDeviceConnectionListener
            public void onDeviceDetach(ConnectionDevice device, int connType) {
                LogUtil.debug(LinkAdapter.TAG, "onDeviceDetach ConnectionDevice:" + device + "connType" + connType);
                if (connType == 1 && device.getDeviceType() == 2) {
                    LinkAdapter.this.notifyiPhoneDetachedOverUsb(device);
                }
            }
        };
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MGS_PROJECTION_SERVICE_STATE_CHANGED /* 101 */:
                int i = msg.arg1;
                this.mCurrentProjectionServiceState = i;
                changeServiceState(i, this.mCurrentDeviceServiceState);
                break;
            case MGS_DEVICE_SERVICE_STATE_CHANGED /* 102 */:
                int i2 = msg.arg1;
                this.mCurrentDeviceServiceState = i2;
                changeServiceState(this.mCurrentProjectionServiceState, i2);
                break;
            case MGS_BIND_SERVICE_TIMEOUT /* 103 */:
                LogUtil.warning(TAG, "MGS_BIND_SERVICE_TIMEOUT restart");
                SystemProperties.set("dcm.restart.flag", "0");
                SystemProperties.set("dcm.restart.flag", "1");
                Process.killProcess(Process.myPid());
                break;
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void init(Context context) {
        LogUtil.info(TAG, "init.");
        this.mContext = context;
        bindDcmService(context);
        this.mDeviceManager = DeviceManager.getInstance();
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void uninit() {
    }

    private void changeServiceState(int projectionServiceState, int deviceServiceState) {
        if (projectionServiceState == 0 || deviceServiceState == 0) {
            return;
        }
        if (this.mListener == null) {
            LogUtil.warning(TAG, "changeServiceState mListener is null.");
            return;
        }
        if (projectionServiceState == 2) {
            LogUtil.warning(TAG, "changeServiceState projection service is disconnected.");
            this.mListener.onServiceStateChanged(false);
            return;
        }
        if (deviceServiceState == 2) {
            LogUtil.warning(TAG, "changeServiceState device service is disconnected.");
            this.mListener.onServiceStateChanged(false);
            return;
        }
        if (deviceServiceState == 1 && projectionServiceState == 1) {
            LogUtil.warning(TAG, "changeServiceState service is connected.");
            IProjectionConnectionManager iProjectionConnectionManager = this.mConnectionManager;
            if (iProjectionConnectionManager != null) {
                try {
                    int channel = iProjectionConnectionManager.getSupportedWifiChannel();
                    LogUtil.info(TAG, "getSupportedWifiChannel channel:" + channel);
                    if (channel != 0) {
                        removeMessages(MGS_BIND_SERVICE_TIMEOUT);
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else {
                LogUtil.warning(TAG, "mConnectionManager is null. binder test fail");
            }
            this.mListener.onServiceStateChanged(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDeviceList() {
        LogUtil.debug(TAG, "initDeviceList.");
        try {
            HashMap<String, ConnectionDevice> deviceList = this.mDeviceConnectionManager.getDeviceList();
            LogUtil.debug(TAG, "initDeviceList, deviceList:" + deviceList.size());
            if (deviceList != null && !deviceList.isEmpty()) {
                for (String name : deviceList.keySet()) {
                    ConnectionDevice device = deviceList.get(name);
                    if (device != null) {
                        this.mDeviceConnectionListener.onDeviceStatusChanged(device);
                        if (device.isAvailableCapability(64)) {
                            this.mDeviceConnectionListener.onProjectionRequested(device, 64);
                        }
                    }
                }
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void registerListener(ILinkAdapterListener listener) {
        this.mListener = listener;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void unregisterListener(ILinkAdapterListener listener) {
        this.mListener = null;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void onStatusChanged(LinkDevice device, int status) {
        onCallbackStatusChanged(device, status);
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean doUsbRoleSwitch(LinkDevice device, int purpose) {
        LogUtil.debug(TAG, "doUsbRoleSwitch do nothing, just return false");
        return false;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void mergeDevices(String devId, int keyType, String key) {
        LogUtil.debug(TAG, "mergeDevices devId:" + LogEncrypt.MacAddressEncrypt(devId) + " keyType:" + keyType + " key:" + key);
        try {
            if (this.mConnectionManager == null) {
                LogUtil.warning(TAG, "mergeDevices mConnectionManager is null.");
            } else {
                this.mConnectionManager.mergeDevices(devId, key, keyType);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void wirelessCapabilityUpdate(String mac, boolean flag, int reason) {
        LogUtil.debug(TAG, "wirelessCapabilityUpdate mac:" + LogEncrypt.MacAddressEncrypt(mac) + " flag:" + flag + " reason:" + reason);
        try {
            if (this.mConnectionManager == null) {
                LogUtil.warning(TAG, "wirelessCapabilityUpdate mConnectionManager is null.");
            } else {
                this.mConnectionManager.updateProjectionCapability(mac, 512, flag, reason);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean onDeviceFriendlyNameUpdate(String devId, String name) {
        boolean ret = false;
        try {
            if (this.mConnectionManager != null) {
                ret = this.mConnectionManager.updateDeviceFriendlyName(devId, name);
            } else {
                LogUtil.warning(TAG, "onDeviceFriendlyNameUpdate mConnectionManager is null.");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean onUsbDeviceNameUpdate(String devId, String name) {
        boolean ret = false;
        try {
            if (this.mConnectionManager != null) {
                ret = this.mConnectionManager.updateUsbDeviceName(devId, name);
            } else {
                LogUtil.warning(TAG, "onUsbDeviceNameUpdate mConnectionManager is null.");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void onUpdateIapSessionState(String id, int state, int reason) {
        try {
            if (this.mConnectionManager != null) {
                this.mConnectionManager.updateIapSessionState(id, state, reason);
            } else {
                LogUtil.warning(TAG, "onUpdateIapSessionState mConnectionManager is null.");
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean reconnectWirelessCarPlay(String id) throws RemoteException {
        int ret = -1;
        if (this.mDeviceConnectionManager != null) {
            ts.car.service.dcm.data.ProjectionLinkParameter plp = new ts.car.service.dcm.data.ProjectionLinkParameter(id, 512);
            this.mIsUserConnected = false;
            plp.setFromUser(false);
            plp.setAutoConnectDevice(true);
            plp.setAlways(false);
            plp.setCapabilityAlreadyChecked(true);
            try {
                ret = this.mDeviceConnectionManager.connectProjection(this.mDeviceConnectionManager.getDevice(id), plp);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return ret == 0;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public LinkDevice getCarPlayDevice(String devId) throws RemoteException {
        Device device = null;
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            if (devId != null) {
                try {
                    device = iDeviceConnectionManager.getDevice(devId);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            } else {
                LogUtil.warning(TAG, "getCarPlayDevice devId is null");
            }
        }
        return ConnectionManagerModel.toCarPlayDevice(device);
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public String getConnectionDeviceId(String macAddress) {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            if (macAddress != null) {
                try {
                    Device device = iDeviceConnectionManager.getDevice(macAddress);
                    if (device != null) {
                        return device.getDeviceId();
                    }
                    return null;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            LogUtil.warning(TAG, "getConnectionDeviceId devId is null");
            return null;
        }
        return null;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean confirmWirelessCarPlayAvailable(String id) {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                boolean ret = iDeviceConnectionManager.checkDeviceCapability(iDeviceConnectionManager.getDevice(id), 512);
                return ret;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        LogUtil.warning(TAG, "confirmWirelessCarPlayAvailable: mDeviceConnectionManager is null.");
        return false;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void connectCarPlay(int connectType, String devId, boolean isUserRequest) {
        int connectType2;
        if (devId == null) {
            LogUtil.warning(TAG, "connectCarPlay, devId is null");
            return;
        }
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager == null) {
            LogUtil.warning(TAG, "connectCarPlay, mDeviceConnectionManager is null");
            return;
        }
        try {
            ConnectionDevice device = iDeviceConnectionManager.getDevice(devId);
            if (device == null) {
                LogUtil.info(TAG, "connectCarPlay, device is null");
                return;
            }
            LogUtil.debug(TAG, "connectCarPlay usb, devId:" + LogEncrypt.MacAddressEncrypt(devId) + ", connectType:" + connectType);
            LinkDevice linkDevice = getCarPlayDevice(devId);
            linkDevice.setStatus(1);
            if (connectType == 1) {
                connectType2 = 64;
            } else if (connectType == 3) {
                connectType2 = 512;
            } else if (device.isAvailableCapability(64)) {
                connectType2 = 64;
            } else if (device.isAvailableCapability(512) || (device.getCapability() & 512) > 0) {
                connectType2 = 512;
            } else {
                connectType2 = 0;
            }
            int linkType = 0;
            if (connectType2 == 64) {
                linkType = 1;
            } else if (connectType2 == 512) {
                linkType = 2;
            }
            linkDevice.setLinkType(linkType);
            LogUtil.warning(TAG, "connectCarPlay linkDevice:" + linkDevice);
            saveDevice(linkDevice);
            connectProjection(device, connectType2, isUserRequest);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            LogUtil.info(TAG, "connectCarPlay, RemoteException:" + ex);
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void disconnectCarPlay(String devId) {
        if (devId == null) {
            LogUtil.warning(TAG, "disconnectCarPlay, devId is null");
            return;
        }
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager == null) {
            LogUtil.warning(TAG, "disconnectCarPlay, mDeviceConnectionManager is null");
            return;
        }
        try {
            ConnectionDevice device = iDeviceConnectionManager.getDevice(devId);
            if (device == null) {
                LogUtil.warning(TAG, "disconnectCarPlay, device is null");
                return;
            }
            if (device.isActivatedCapability(64)) {
                LogUtil.debug(TAG, "disconnectCarPlay usb, devId:" + devId);
                this.mDeviceConnectionManager.disconnectProjection(device, 64, 0);
                return;
            }
            if (device.isActivatedCapability(512)) {
                LogUtil.debug(TAG, "disconnectCarPlay wireless, devId:" + devId);
                this.mDeviceConnectionManager.disconnectProjection(device, 512, 0);
                return;
            }
            if (device.getActiveCapabilityState(512) == 3) {
                LogUtil.debug(TAG, "waiting the cpability callback, now disconnectCarPlay wireless with no cpability, devId:" + devId);
                this.mDeviceConnectionManager.disconnectProjection(device, 512, 0);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
            LogUtil.info(TAG, "disconnectCarPlay, RemoteException:" + ex);
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void deleteCarPlayDevice(String devId) {
        if (this.mDeviceConnectionManager != null) {
            try {
                Device device = new Device(devId);
                this.mDeviceConnectionManager.deleteDevice(device);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.warning(TAG, "deleteCarPlayDevice: mDeviceConnectionManager is null.");
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public CarPlayDevice getCarPlayAidlDevice(String devId) {
        ConnectionDevice device = null;
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            if (devId == null) {
                LogUtil.warning(TAG, "getCarPlayDevice aidl, devId is null");
            } else {
                try {
                    device = iDeviceConnectionManager.getDevice(devId);
                    LogUtil.debug(TAG, "getCarPlayAidlDevice device: " + device.toString());
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return ConnectionManagerModel.toCarPlayAidlDevice(device);
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public List<CarPlayDevice> getUsbCarPlayDeviceList() {
        try {
            HashMap<String, ConnectionDevice> list = this.mDeviceConnectionManager.getDeviceList();
            ArrayList<CarPlayDevice> result = new ArrayList<>();
            if (list != null) {
                for (String name : list.keySet()) {
                    ConnectionDevice device = list.get(name);
                    if (device != null && device.getConnectedTypeState(1) == 3) {
                        result.add(ConnectionManagerModel.toCarPlayAidlDevice(device));
                    }
                }
            }
            return result;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public List<CarPlayDevice> getCarPlayDeviceList() {
        try {
            HashMap<String, ConnectionDevice> list = this.mDeviceConnectionManager.getDeviceList();
            ArrayList<CarPlayDevice> result = new ArrayList<>();
            if (list != null) {
                for (String name : list.keySet()) {
                    ConnectionDevice device = list.get(name);
                    int capability = device.getCapability();
                    if ((capability & 512) > 0) {
                        result.add(ConnectionManagerModel.toCarPlayAidlDevice(device));
                    }
                }
            }
            return result;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public int getDeviceConnectionType(String devId) {
        ConnectionDevice device;
        int connectType = 2;
        try {
            device = this.mDeviceConnectionManager.getDevice(devId);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            LogUtil.warning(TAG, "getDeviceConnectionType, RemoteException:" + ex);
        }
        if (device == null) {
            LogUtil.warning(TAG, "getDeviceConnectionType, device is null");
            return 2;
        }
        if (device.isAvailableCapability(64)) {
            connectType = 1;
        } else {
            connectType = 2;
        }
        LogUtil.debug(TAG, "getDeviceConnectionType, devId:" + devId + ", connectType:" + connectType);
        return connectType;
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void onKnownWirelessAttributesChanged(String mac, int state) {
        IDeviceConnectionManager iDeviceConnectionManager;
        ConnectionManagerModel.onKnownWirelessAttributesChanged(mac, state);
        if (!TextUtils.isEmpty(mac) && (iDeviceConnectionManager = this.mDeviceConnectionManager) != null) {
            try {
                ConnectionDevice device = iDeviceConnectionManager.getDevice(mac);
                if (device != null && this.mDeviceConnectionListener != null) {
                    this.mDeviceConnectionListener.onDeviceStatusChanged(device);
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public void saveDevice(LinkDevice device) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            deviceManager.saveDevice(device);
        }
    }

    private int connectProjection(ConnectionDevice connectionDevice, int capability, boolean isUserRequest) {
        if (capability == 0) {
            LogUtil.warning(TAG, "connectProjection failed, no capability");
            return 7;
        }
        ts.car.service.dcm.data.ProjectionLinkParameter plp = new ts.car.service.dcm.data.ProjectionLinkParameter(connectionDevice.getDeviceId(), capability);
        this.mIsUserConnected = isUserRequest;
        plp.setFromUser(isUserRequest);
        plp.setAutoConnectDevice(true);
        plp.setAlways(false);
        plp.setCapabilityAlreadyChecked(false);
        Device device = new Device(connectionDevice.getDeviceId());
        try {
            return this.mDeviceConnectionManager.connectProjection(device, plp);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyiPhoneDetachedOverUsb(ConnectionDevice dev) {
        LogUtil.debug(TAG, "notifyiPhoneDetachedOverUsb");
        CarPlayDevice device = null;
        if (dev != null) {
            device = new CarPlayDevice();
            device.setDeviceName(dev.getFriendlyName());
            device.setUsbSerialNumber(dev.getUsbSerialNumber());
            device.setBtAddr(dev.getBtAddr());
            device.setDeviceId(dev.getDeviceId());
        }
        ILinkAdapterListener iLinkAdapterListener = this.mListener;
        if (iLinkAdapterListener == null) {
            LogUtil.debug(TAG, "notifyiPhoneDetachedOverUsb: mListener is null");
        } else {
            iLinkAdapterListener.oniPhoneDetachedOverUsb(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyiPhoneAttachedOverUsb(ConnectionDevice dev, boolean supported) {
        LogUtil.debug(TAG, "notifyiPhoneAttachedOverUsb supported: " + supported);
        if (dev != null) {
            CarPlayDevice device = new CarPlayDevice();
            device.setDeviceName(dev.getFriendlyName());
            device.setUsbSerialNumber(dev.getUsbSerialNumber());
            device.setBtAddr(dev.getBtAddr());
            device.setDeviceId(dev.getDeviceId());
            device.setIsFirstConntect(dev.isCarplayFirstConn());
            if (supported) {
                device.setDeviceStatus(1);
            } else {
                device.setDeviceStatus(0);
            }
            ILinkAdapterListener iLinkAdapterListener = this.mListener;
            if (iLinkAdapterListener != null) {
                iLinkAdapterListener.oniPhoneAttachedOverUsb(device, supported);
            } else {
                LogUtil.debug(TAG, "notifyiPhoneAttachedOverUsb: mListener is null");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRequestProjectionDevice(ConnectionDevice dev, int type) {
        LogUtil.debug(TAG, "notifyProjectionRequestedDevice");
        CarPlayDevice device = null;
        if (dev != null) {
            device = ConnectionManagerModel.toCarPlayAidlDevice(dev);
        }
        ILinkAdapterListener iLinkAdapterListener = this.mListener;
        if (iLinkAdapterListener == null) {
            LogUtil.debug(TAG, "onRequestProjection: mListener is null");
        } else {
            iLinkAdapterListener.onRequestProjection(device, type);
        }
    }

    private void bindDcmService(Context context) {
        IProjectionConnectionManager iProjectionConnectionManager = (IProjectionConnectionManager) FoundationProvider.create(IProjectionConnectionManager.class, context);
        this.mConnectionManager = iProjectionConnectionManager;
        if (iProjectionConnectionManager != null) {
            iProjectionConnectionManager.registerConnectionListener(this.mProjectionServiceListener);
        }
        IDeviceConnectionManager iDeviceConnectionManager = (IDeviceConnectionManager) ServiceProvider.get(IDeviceConnectionManager.class, context);
        this.mDeviceConnectionManager = iDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            iDeviceConnectionManager.registerConnectionListener(this.mDeviceServiceListener);
        }
        sendEmptyMessageDelayed(MGS_BIND_SERVICE_TIMEOUT, 3000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUsbRoleSwitchFinished(CmDevice cmDevice, int mode, boolean result, int srvType) {
        if (mode == 0) {
            LogUtil.warning(TAG, "onUsbRoleSwitchFinished HOST ABORD ");
            return;
        }
        if (srvType == 1) {
            LogUtil.debug(TAG, "onUsbRoleSwitchFinished enter carplay.");
            if (this.mListener == null) {
                LogUtil.warning(TAG, "onUsbRoleSwitchFinished mListener is null.");
                return;
            } else {
                LinkDevice device = ConnectionManagerModel.toCarPlayDevice(cmDevice, 64);
                this.mListener.onUsbRoleSwitchFinished(device, result);
                return;
            }
        }
        LogUtil.warning(TAG, "onUsbRoleSwitchFinished enter NONE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connect(CmDevice cmDevice, int capability, boolean first, boolean fromUser) {
        if (cmDevice == null) {
            LogUtil.warning(TAG, "connect cmDevice is null.");
            return;
        }
        LinkDevice device = ConnectionManagerModel.toCarPlayDevice(cmDevice, capability);
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            deviceManager.saveDevice(device);
        }
        device.setStatus(1);
        if (capability != 64 && capability != 512) {
            LogUtil.warning(TAG, "connect capability is not support.");
            return;
        }
        ILinkAdapterListener iLinkAdapterListener = this.mListener;
        if (iLinkAdapterListener == null) {
            LogUtil.warning(TAG, "connect mListener is null.");
        } else {
            iLinkAdapterListener.connecting(device, first, fromUser);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disconnect(String deviceId, int capability, int reason) {
        LinkDevice device = getDeviceByDeviceId(deviceId);
        LogUtil.debug(TAG, "disconnect deviceId:" + deviceId + " capability:" + capability);
        if (capability == 64 || capability == 512) {
            ILinkAdapterListener iLinkAdapterListener = this.mListener;
            if (iLinkAdapterListener != null) {
                iLinkAdapterListener.disconnecting(device, reason);
                return;
            } else {
                LogUtil.warning(TAG, "disconnect mListener is null.");
                return;
            }
        }
        LogUtil.warning(TAG, "disConnect capability is not support.");
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public CarPlayDevice getActivedWirelessDevice() throws RemoteException {
        List<ConnectionDevice> list = null;
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                list = iDeviceConnectionManager.getActiveProjections();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    ConnectionDevice dev = list.get(i);
                    if (dev != null) {
                        int clActiveCapState = dev.getActiveCapabilityState(512);
                        LogUtil.debug(TAG, "getActivedWirelessDevice wirelessCapability:  clActiveCapState: " + clActiveCapState);
                        if (4 == clActiveCapState) {
                            LogUtil.debug(TAG, "getActivedWirelessDevice dev:" + dev);
                            CarPlayDevice device = new CarPlayDevice();
                            device.setDeviceName(dev.getFriendlyName());
                            device.setUsbSerialNumber(dev.getUsbSerialNumber());
                            device.setBtAddr(dev.getBtAddr());
                            device.setDeviceStatus(4);
                            LogUtil.debug(TAG, "getActivedWirelessDevice addr:" + LogEncrypt.MacAddressEncrypt(dev.getBtAddr()));
                            return device;
                        }
                    }
                }
                return null;
            }
            return null;
        }
        LogUtil.debug(TAG, "getActivedWirelessDevice: mDeviceConnectionManager is null");
        return null;
    }

    private LinkDevice getDeviceByDeviceId(String deviceId) {
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager == null) {
            return null;
        }
        LinkDevice device = deviceManager.getDeviceByDeviceId(deviceId);
        return device;
    }

    private void onCallbackStatusChanged(LinkDevice linkDevice, int linkStatus) {
        LogUtil.debug(TAG, "onCallbackStatusChanged linkStatus:" + linkStatus + " linkDevice:" + linkDevice);
        DeviceManager deviceManager = this.mDeviceManager;
        if (deviceManager != null) {
            deviceManager.saveConnectStatus(linkDevice, linkStatus);
        }
        if (this.mConnectionManager != null) {
            if (linkDevice != null) {
                if (linkStatus == 1 || linkStatus == 2 || linkStatus == 3 || linkStatus == 4) {
                    try {
                        this.mConnectionManager.updateConnectionStatus(linkDevice.getDeviceId(), ConnectionManagerModel.getCmsCapability(linkDevice), ConnectionManagerModel.getCmsCapabilityState(linkStatus), 0);
                        return;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                LogUtil.warning(TAG, "onCallbackStatusChanged linkStatus:" + linkStatus);
                return;
            }
            return;
        }
        LogUtil.warning(TAG, "onCallbackStatusChanged mConnectionManager is null.");
    }

    @Override // com.ts.carplay.linkadapter.link.ILinkAdapter
    public boolean isUserConnected() {
        return this.mIsUserConnected;
    }
}
