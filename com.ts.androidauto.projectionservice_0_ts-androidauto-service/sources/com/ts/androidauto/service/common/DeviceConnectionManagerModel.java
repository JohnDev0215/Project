package com.ts.androidauto.service.common;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.ts.androidauto.fsdk.common.LogEncrypt;
import com.ts.androidauto.fsdk.common.LogUtil;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import com.ts.androidauto.projectionservice.DataConvert;
import com.ts.androidauto.sdk.aidl.data.ConnectRequest;
import com.ts.androidauto.service.device.AutoConnectManager;
import com.ts.car.core.ServiceProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.dcm.IDeviceConnectionListener;
import ts.car.service.dcm.IDeviceConnectionManager;
import ts.car.service.dcm.data.ConnectionDevice;
import ts.car.service.dcm.data.Device;
import ts.car.service.dcm.data.ProjectionLinkParameter;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceConnectionManagerModel {
    private static final String TAG = "AAPDeviceConnectionManagerModel";
    private static DeviceConnectionManagerModel mDeviceConnectionManagerModelInstance;
    private boolean mAndroidAutoProjectionActive;
    private String mAutoConnectDeviceId;
    private IDeviceConnectionManager mDeviceConnectionManager;
    private boolean mFromUser;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private List<Listener> mListenerList = new Vector();
    private final IServiceConnectionListener mDeviceServiceListener = new IServiceConnectionListener() { // from class: com.ts.androidauto.service.common.DeviceConnectionManagerModel.1
        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "mDeviceServiceListener onServiceConnected.");
            try {
                if (DeviceConnectionManagerModel.this.mDeviceConnectionManager != null) {
                    DeviceConnectionManagerModel.this.mDeviceConnectionManager.registerListener(DeviceConnectionManagerModel.this.mDmServiceListener, 0);
                    DeviceConnectionManagerModel.this.initDeviceList();
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService service) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "mDeviceServiceListener onServiceDisconnected.");
        }
    };
    private IDeviceConnectionListener mDmServiceListener = new AnonymousClass2();

    public interface Listener {
        default void onDeviceStatusChanged(ConnectionDevice device) {
        }

        default void onOtherProjectionStatusChanged(boolean active) {
        }

        default void onDevicesMerged(String oneDevId, String anotherDevId, ConnectionDevice device) {
        }

        default void onRequestProjection(ConnectionDevice device, int type) {
        }

        default void onDeviceDetach(ConnectionDevice device, int connType) {
        }
    }

    public static synchronized DeviceConnectionManagerModel getInstance() {
        if (mDeviceConnectionManagerModelInstance == null) {
            mDeviceConnectionManagerModelInstance = new DeviceConnectionManagerModel();
        }
        return mDeviceConnectionManagerModelInstance;
    }

    private DeviceConnectionManagerModel() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
    }

    public void init(Context context) {
        if (this.mDeviceConnectionManager == null) {
            LogUtil.debug(TAG, "DeviceConnectionManagerModel init start.");
            IDeviceConnectionManager iDeviceConnectionManager = (IDeviceConnectionManager) ServiceProvider.get(IDeviceConnectionManager.class, context);
            this.mDeviceConnectionManager = iDeviceConnectionManager;
            if (iDeviceConnectionManager != null) {
                iDeviceConnectionManager.registerConnectionListener(this.mDeviceServiceListener);
                return;
            }
            return;
        }
        LogUtil.info(TAG, "DeviceConnectionManagerModel already init.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDeviceList() {
        LogUtil.debug(TAG, "initDeviceList.");
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                HashMap<String, ConnectionDevice> deviceList = iDeviceConnectionManager.getDeviceList();
                LogUtil.debug(TAG, "initDeviceList, deviceList:" + deviceList);
                if (deviceList != null && !deviceList.isEmpty()) {
                    for (String name : deviceList.keySet()) {
                        ConnectionDevice device = deviceList.get(name);
                        if (device != null) {
                            this.mDmServiceListener.onDeviceStatusChanged(device);
                            if (device.isAvailableCapability(128)) {
                                this.mDmServiceListener.onProjectionRequested(device, 128);
                            }
                        }
                    }
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    /* JADX INFO: renamed from: com.ts.androidauto.service.common.DeviceConnectionManagerModel$2, reason: invalid class name */
    class AnonymousClass2 implements IDeviceConnectionListener {
        AnonymousClass2() {
        }

        @Override // ts.car.service.dcm.IDeviceConnectionListener
        public void onProjectionRequested(final ConnectionDevice device, final int type) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "onRequestProjection ConnectionDevice:" + device + "type" + type);
            DeviceConnectionManagerModel.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.common.-$$Lambda$DeviceConnectionManagerModel$2$xTtEjRxbhXm3zWg_LW8c6Yqv2pI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onProjectionRequested$0$DeviceConnectionManagerModel$2(device, type);
                }
            });
        }

        public /* synthetic */ void lambda$onProjectionRequested$0$DeviceConnectionManagerModel$2(ConnectionDevice device, int type) {
            if (DeviceConnectionManagerModel.this.mListenerList != null) {
                synchronized (DeviceConnectionManagerModel.this.mListenerList) {
                    for (Listener listener : DeviceConnectionManagerModel.this.mListenerList) {
                        listener.onRequestProjection(device, type);
                    }
                }
            }
            if (DeviceConnectionManagerModel.this.mAndroidAutoProjectionActive && !DeviceConnectionManagerModel.this.mFromUser) {
                DeviceConnectionManagerModel.this.handlerAutoConnectInterupt(device, type);
            }
        }

        @Override // ts.car.service.dcm.IDeviceConnectionListener
        public void onDeviceStatusChanged(final ConnectionDevice device) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "[onDeviceStatusChanged] device : " + device);
            DeviceConnectionManagerModel.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.common.-$$Lambda$DeviceConnectionManagerModel$2$PKYGRksZzGLMzIgG1YlPcyYdYQ8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeviceStatusChanged$1$DeviceConnectionManagerModel$2(device);
                }
            });
        }

        public /* synthetic */ void lambda$onDeviceStatusChanged$1$DeviceConnectionManagerModel$2(ConnectionDevice device) {
            if (DeviceConnectionManagerModel.this.mListenerList != null) {
                synchronized (DeviceConnectionManagerModel.this.mListenerList) {
                    for (Listener listener : DeviceConnectionManagerModel.this.mListenerList) {
                        listener.onDeviceStatusChanged(device);
                    }
                }
            }
        }

        @Override // ts.car.service.dcm.IDeviceConnectionListener
        public void onProjectionServiceStateChanged(ConnectionDevice device, final int capa, final int status, int reason) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "[onProjectionServiceStateChanged] device : " + device + "capa" + capa + NotificationCompat.CATEGORY_STATUS + status + "reason" + reason);
            DeviceConnectionManagerModel.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.common.-$$Lambda$DeviceConnectionManagerModel$2$Ot1Embh9_6IfyXyb-aMvAw5jRe0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onProjectionServiceStateChanged$2$DeviceConnectionManagerModel$2(capa, status);
                }
            });
        }

        public /* synthetic */ void lambda$onProjectionServiceStateChanged$2$DeviceConnectionManagerModel$2(int capa, int status) {
            boolean isOtherProjectionActive = false;
            DeviceConnectionManagerModel.this.mAndroidAutoProjectionActive = false;
            if ((capa == 64 || capa == 256 || capa == 512 || capa == 2048) && (status == 2 || status == 3 || status == 4)) {
                isOtherProjectionActive = true;
            }
            if ((capa == 128 || capa == 1024) && status == 3) {
                DeviceConnectionManagerModel.this.mAndroidAutoProjectionActive = true;
            }
            if (DeviceConnectionManagerModel.this.mListenerList != null) {
                synchronized (DeviceConnectionManagerModel.this.mListenerList) {
                    for (Listener listener : DeviceConnectionManagerModel.this.mListenerList) {
                        listener.onOtherProjectionStatusChanged(isOtherProjectionActive);
                    }
                }
            }
        }

        @Override // ts.car.service.dcm.IDeviceConnectionListener
        public void onDevicesMerged(final String oneDevId, final String anotherDevId, final ConnectionDevice device) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "[onDevicesMerged] oneDevId : " + LogEncrypt.MacAddressEncrypt(oneDevId) + " anotherDevId: " + LogEncrypt.MacAddressEncrypt(anotherDevId) + " device:" + device);
            DeviceConnectionManagerModel.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.common.-$$Lambda$DeviceConnectionManagerModel$2$HHC_WqkWowBDPpPoSSPSHquAWYM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDevicesMerged$3$DeviceConnectionManagerModel$2(oneDevId, anotherDevId, device);
                }
            });
        }

        public /* synthetic */ void lambda$onDevicesMerged$3$DeviceConnectionManagerModel$2(String oneDevId, String anotherDevId, ConnectionDevice device) {
            if (DeviceConnectionManagerModel.this.mListenerList != null) {
                synchronized (DeviceConnectionManagerModel.this.mListenerList) {
                    for (Listener listener : DeviceConnectionManagerModel.this.mListenerList) {
                        listener.onDevicesMerged(oneDevId, anotherDevId, device);
                    }
                }
            }
        }

        @Override // ts.car.service.dcm.IDeviceConnectionListener
        public void onDeviceDetach(final ConnectionDevice device, final int connType) {
            LogUtil.debug(DeviceConnectionManagerModel.TAG, "[onDeviceDetach] device : " + device + " connType: " + connType);
            DeviceConnectionManagerModel.this.mHandler.post(new Runnable() { // from class: com.ts.androidauto.service.common.-$$Lambda$DeviceConnectionManagerModel$2$FBsrIshn2Kli7cmJ0l8EsAtOchk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeviceDetach$4$DeviceConnectionManagerModel$2(device, connType);
                }
            });
        }

        public /* synthetic */ void lambda$onDeviceDetach$4$DeviceConnectionManagerModel$2(ConnectionDevice device, int connType) {
            if (DeviceConnectionManagerModel.this.mListenerList != null) {
                synchronized (DeviceConnectionManagerModel.this.mListenerList) {
                    for (Listener listener : DeviceConnectionManagerModel.this.mListenerList) {
                        listener.onDeviceDetach(device, connType);
                    }
                }
            }
        }
    }

    public void registerListener(Listener listener) {
        List<Listener> list = this.mListenerList;
        if (list != null && listener != null) {
            synchronized (list) {
                if (!this.mListenerList.contains(listener)) {
                    this.mListenerList.add(listener);
                }
            }
        }
    }

    public HashMap<String, ConnectionDevice> getDeviceList() {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                return iDeviceConnectionManager.getDeviceList();
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void userConnectProjectionFromMd(AndroidAutoDevice linkDevice) {
        if (this.mDeviceConnectionManager != null && linkDevice != null) {
            ProjectionLinkParameter plp = new ProjectionLinkParameter(getDevIdByBtAddress(linkDevice.getBtAddr()), 1024);
            plp.setFromUser(true);
            this.mFromUser = true;
            this.mAutoConnectDeviceId = null;
            plp.setAutoConnectDevice(false);
            plp.setAlways(false);
            plp.setCapabilityAlreadyChecked(false);
            Device device = new Device(plp.getDevId());
            try {
                this.mDeviceConnectionManager.connectProjection(device, plp);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.info(TAG, "mDeviceConnectionManager not init.");
    }

    public void autoConnectProjection(String deviceId) {
        if (this.mDeviceConnectionManager != null && deviceId != null) {
            ProjectionLinkParameter plp = new ProjectionLinkParameter(deviceId, 1024);
            plp.setFromUser(false);
            this.mFromUser = false;
            this.mAutoConnectDeviceId = deviceId;
            plp.setAutoConnectDevice(false);
            plp.setAlways(false);
            plp.setCapabilityAlreadyChecked(false);
            Device device = new Device(deviceId);
            try {
                this.mDeviceConnectionManager.connectProjection(device, plp);
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        LogUtil.info(TAG, "mDeviceConnectionManager not init.");
    }

    private String getDevIdByBtAddress(String btAddr) throws RemoteException {
        IDeviceConnectionManager iDeviceConnectionManager;
        if (TextUtils.isEmpty(btAddr) || (iDeviceConnectionManager = this.mDeviceConnectionManager) == null) {
            return null;
        }
        HashMap<String, ConnectionDevice> maps = null;
        try {
            maps = iDeviceConnectionManager.getDeviceList();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        if (maps == null) {
            return null;
        }
        for (ConnectionDevice device : maps.values()) {
            if (device != null && btAddr.equals(device.getBtAddr())) {
                String devId = device.getDeviceId();
                return devId;
            }
        }
        return null;
    }

    public ConnectionDevice getDevice(String devId) {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                return iDeviceConnectionManager.getDevice(devId);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private int getDefaultConnectionType(ConnectionDevice device) {
        if (device.isAvailableCapability(128)) {
            LogUtil.info(TAG, "connectAndroidAuto usb, device:" + device);
            return 128;
        }
        if (!device.isAvailableCapability(1024)) {
            if ((device.getCapability() & 1024) <= 0) {
                if ((device.getCapability() & 128) > 0) {
                    return 128;
                }
                LogUtil.info(TAG, "connectAndroidAuto failed, no available capability for device:" + device);
                return 0;
            }
            return 1024;
        }
        LogUtil.info(TAG, "connectAndroidAuto wireless, device:" + device);
        return 1024;
    }

    public void connectAndroidAuto(String devId, boolean isUserRequest) {
        LogUtil.debug(TAG, "connectAndroidAuto, devId:" + LogEncrypt.MacAddressEncrypt(devId));
        ConnectionDevice device = getDevice(devId);
        if (device == null) {
            LogUtil.debug(TAG, "connectAndroidAuto failed, no device for:" + LogEncrypt.MacAddressEncrypt(devId));
            return;
        }
        int connectionType = getDefaultConnectionType(device);
        if (connectionType != 0) {
            connectProjection(devId, connectionType, isUserRequest, false);
            return;
        }
        LogUtil.info(TAG, "connectAndroidAuto failed, no available capability for device:" + device);
    }

    public void connectAndroidAuto(ConnectRequest request) {
        int connectType = 0;
        if (request.getConnectType() == 1) {
            connectType = 128;
        } else if (request.getConnectType() == 2) {
            connectType = 1024;
        } else {
            ConnectionDevice device = getDevice(request.getDeviceId());
            if (device != null) {
                connectType = getDefaultConnectionType(device);
            }
        }
        if (connectType != 0) {
            connectProjection(request.getDeviceId(), connectType, request.isUserRequest(), request.isForceProjection());
            return;
        }
        LogUtil.info(TAG, "connectAndroidAuto failed, no available capability for device:" + request.getDeviceId());
    }

    public void disconnectAndroidAuto(String devId) {
        LogUtil.debug(TAG, "disconnectAndroidAuto, devId:" + LogEncrypt.MacAddressEncrypt(devId));
        ConnectionDevice device = getDevice(devId);
        if (device == null) {
            return;
        }
        if (device.isActivatedCapability(128)) {
            LogUtil.debug(TAG, "disconnectAndroidAuto usb, devId:" + LogEncrypt.MacAddressEncrypt(devId));
            IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
            if (iDeviceConnectionManager != null) {
                try {
                    iDeviceConnectionManager.disconnectProjection(device, 128, 0);
                    return;
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
            return;
        }
        if (device.isActivatedCapability(1024)) {
            LogUtil.debug(TAG, "disconnectAndroidAuto wireless, devId:" + LogEncrypt.MacAddressEncrypt(devId));
            IDeviceConnectionManager iDeviceConnectionManager2 = this.mDeviceConnectionManager;
            if (iDeviceConnectionManager2 != null) {
                try {
                    iDeviceConnectionManager2.disconnectProjection(device, 1024, 0);
                } catch (RemoteException ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }

    private void connectProjection(String devId, int capability, boolean isUserRequest, boolean forceProjection) {
        ProjectionLinkParameter plp = new ProjectionLinkParameter(devId, capability);
        plp.setFromUser(isUserRequest);
        this.mFromUser = isUserRequest;
        if (isUserRequest) {
            this.mAutoConnectDeviceId = null;
        } else {
            this.mAutoConnectDeviceId = devId;
        }
        plp.setAutoConnectDevice(true);
        plp.setAlways(false);
        plp.setCapabilityAlreadyChecked(false);
        plp.setForceProjection(forceProjection);
        Device device = new Device(devId);
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                iDeviceConnectionManager.connectProjection(device, plp);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void deleteAndroidAutoDevice(String devId) {
        try {
            Device device = new Device(devId);
            if (getAndroidAutoDevice(devId) != null) {
                LogUtil.debug(TAG, "getAndroidAutoDevice, getBtAddr:" + LogEncrypt.MacAddressEncrypt(getAndroidAutoDevice(devId).getBtAddr()));
                AutoConnectManager.getInstance().deleteAndroidAutoDevice(getAndroidAutoDevice(devId));
            }
            if (this.mDeviceConnectionManager != null) {
                this.mDeviceConnectionManager.deleteDevice(device);
            }
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice getAndroidAutoDevice(String devId) {
        LogUtil.debug(TAG, "getAndroidAutoDevice, devId:" + LogEncrypt.MacAddressEncrypt(devId));
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                ConnectionDevice connectionDevice = iDeviceConnectionManager.getDevice(devId);
                return DataConvert.convertAndroidAutoDevice(connectionDevice);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getUsbAndroidAutoDeviceList() {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                HashMap<String, ConnectionDevice> list = iDeviceConnectionManager.getDeviceList();
                ArrayList<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> result = new ArrayList<>();
                if (list != null) {
                    for (String name : list.keySet()) {
                        ConnectionDevice device = list.get(name);
                        if (device != null && device.getConnectedTypeState(1) == 3 && device.isAvailableCapability(128)) {
                            result.add(DataConvert.convertAndroidAutoDevice(device));
                        }
                    }
                }
                LogUtil.info(TAG, "getUsbAndroidAutoDeviceList, result:" + result);
                return result;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public List<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> getAndroidAutoDeviceList() {
        IDeviceConnectionManager iDeviceConnectionManager = this.mDeviceConnectionManager;
        if (iDeviceConnectionManager != null) {
            try {
                HashMap<String, ConnectionDevice> list = iDeviceConnectionManager.getDeviceList();
                ArrayList<com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice> result = new ArrayList<>();
                if (list != null) {
                    for (String name : list.keySet()) {
                        ConnectionDevice device = list.get(name);
                        int capability = device.getCapability();
                        if ((capability & 128) > 0 || (capability & 1024) > 0) {
                            result.add(DataConvert.convertAndroidAutoDevice(device));
                        }
                    }
                }
                return result;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerAutoConnectInterupt(ConnectionDevice device, int type) {
        String devId;
        if (device != null && (devId = device.getDeviceId()) != null) {
            connectProjection(devId, type, true, true);
        }
    }

    public String getAutoConnectDeviceId() {
        return this.mAutoConnectDeviceId;
    }
}
