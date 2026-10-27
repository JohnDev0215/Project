package com.ts.car.sdk.dcm;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.listener.LocalListenerHelper;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import ts.car.dcm.common.aidl.IConnectionManagementService;
import ts.car.dcm.common.aidl.IDmListener;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.WifiConfigInfo;
import ts.car.service.dcm.IDeviceConnectionListener;
import ts.car.service.dcm.IDeviceConnectionManager;
import ts.car.service.dcm.data.CmsConstants;
import ts.car.service.dcm.data.ConnectionDevice;
import ts.car.service.dcm.data.Device;
import ts.car.service.dcm.data.LogEncrypt;
import ts.car.service.dcm.data.ProjectionLinkParameter;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformDeviceConnectionManager extends SimpleServiceBase implements IDeviceConnectionManager {
    private static final String REMOTE_SERVICE_NAME = "CmService";
    private static final String SERVICE_COMPONENT = "com.ts.car.dcm.dccontroller/.connectionmanagerservice.CmService";
    private static final String TAG = PlatformDeviceConnectionManager.class.getSimpleName();
    private IDmListener.Stub mDmCallback;
    private final LocalListenerHelper<IDeviceConnectionListener> mListenerHelper;
    private int mRegisterObjectType;
    private IConnectionManagementService mService;

    /* JADX INFO: renamed from: com.ts.car.sdk.dcm.PlatformDeviceConnectionManager$1, reason: invalid class name */
    class AnonymousClass1 extends IDmListener.Stub {
        AnonymousClass1() {
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceAccept(final ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDeviceAccept");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$TIV502AyyWkOjazQ_m6ImUSpf1w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onDeviceAccepted(Utils.covertAidlToConnectionDevice(device));
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onRequestProjection(final ConnDevice device, final int type) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onRequestProjection");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$RCMZQoWnPO-SGqpizxIRxIYiKxU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onProjectionServiceRequested(Utils.covertAidlToConnectionDevice(device));
                }
            });
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$8wipAT7snU81GSm3k_SdeW55eVc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onProjectionRequested(Utils.covertAidlToConnectionDevice(device), type);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceStatusChanged(final ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDeviceStatusChanged");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$hyo7C8X17nZiSgon_9bvQuATg60
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onDeviceStatusChanged(Utils.covertAidlToConnectionDevice(device));
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDevicesMerged(final String oneDevId, final String anotherDevId, final ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDevicesMerged");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$5jw-3n1aPFpHLsY1yz1EUME_pE8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onDevicesMerged(oneDevId, anotherDevId, Utils.covertAidlToConnectionDevice(device));
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onOutgoingPhoneChanged(ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onOutgoingPhoneChanged");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onIncomingPhoneChanged(ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onIncomingPhoneChanged");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionConnectionChanged(final ConnDevice device, final int capa, final int status, final int reason) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onProjectionConnectionChanged");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$EV4SNtgTVxGN_1YhQTUbLgeRMxw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onProjectionServiceStateChanged(Utils.covertAidlToConnectionDevice(device), capa, status);
                }
            });
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$nfvGvb1XIi0MLEYzwIKdyzZaebQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onProjectionServiceStateChanged(Utils.covertAidlToConnectionDevice(device), capa, status, reason);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onSelectOutgoingPhone(List<ConnDevice> devs) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onSelectOutgoingPhone");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onNotifyMessage(final int msgId, final String msg) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onNotifyMessage");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$4mYHlLGk7rRkUdx_VLd3yQRuRjU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onMessageSend(msgId, msg);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtStateChanged(int state, int srvType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onBtStateChanged");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtDisconnected(String devId, int result, int srvType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onBtDisconnected");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtConnected(String devId, int result, int srvType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onBtConnected");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onBtProfileChanged(final String mac, final int profile, final int state, int srvType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onBtProfileChanged");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$3871MbWkuWTFFxas4I756yJYfz4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onBtProfileChanged(mac, profile, state);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onPhoneStateChanged(String devId, int state) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onPhoneStateChanged, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", state:" + state);
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWirelessCarplayDetected(final String mac, final int status) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onWirelessCarplayDetected, devId:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$6cvHoGEPca7gLwDxhpNy1n7vps0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onWirelessCarplayDetected(mac, status);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWirelessProjectionFound(final ConnDevice device) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onWirelessProjectionFound");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$8F5s5MVxhzn49FpWf4vrJMJ0yaw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onWirelessProjectionFound(Utils.covertAidlToConnectionDevice(device));
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onWifiApInfo(final WifiConfigInfo config, final int status, int srvType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onWifiApInfo, status:" + status);
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$dBN-S8v4yNZWLAU0YDi9kl2Y8Ow
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onWifiApInfoChanged(config.getWifiConfiguration(), status);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceAttach(final ConnDevice device, final int connType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDeviceAttach");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$HeSUYP_E2aTKe_ftjARMS6AT-Go
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onDeviceAttach(Utils.covertAidlToConnectionDevice(device), connType);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceDetach(final ConnDevice device, final int connType) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDeviceDetach");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$LJN0X48CoRIVHxjnInWS7aK6pK4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onDeviceDetach(Utils.covertAidlToConnectionDevice(device), connType);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionServiceReady() {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onProjectionServiceReady");
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onProjectionServiceStatus(final int serviceType, final boolean status) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onProjectionServiceStatus, serviceType:" + serviceType + ", status:" + status);
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$CAeWFBSUCK5yQRcwOcsxKwK4FlY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onProjectionServiceStatus(serviceType, status);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onAbnormalDeviceStatusChanged(final AbnormalDevice device, final int status) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onAbnormalDeviceStatusChanged");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$RUvtB7iKwO_LiU6ZohMbZbcqcM0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IDeviceConnectionListener iDeviceConnectionListener = (IDeviceConnectionListener) obj;
                    iDeviceConnectionListener.onAbnormalDeviceStatusChanged(Utils.covertAidlToAbnormalDevice(device), status);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void onDeviceCapabilityChecked(final String devId, final int capability, final boolean isSupport, final int reason) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- onDeviceCapabilityChecked");
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$DKhwXfDIkpXelqnQ2Uqp1NaZ4Oo
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onDeviceCapabilityChecked(devId, capability, isSupport, reason);
                }
            });
        }

        @Override // ts.car.dcm.common.aidl.IDmListener
        public void notifyBtOobPairComplete(final String mac, final int status) {
            Log.d(PlatformDeviceConnectionManager.TAG, "mDmCallback --- notifyBtOobPairComplete, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            PlatformDeviceConnectionManager.this.mListenerHelper.asyncNotifyListener(new Consumer() { // from class: com.ts.car.sdk.dcm.-$$Lambda$PlatformDeviceConnectionManager$1$zSArLvjfd7tp61z_u1W2MZiXUPU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IDeviceConnectionListener) obj).onBtOobPairCompleted(mac, status);
                }
            });
        }
    }

    @PlatformConstruction
    public PlatformDeviceConnectionManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mRegisterObjectType = 0;
        this.mService = null;
        this.mListenerHelper = new LocalListenerHelper<>();
        this.mDmCallback = new AnonymousClass1();
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder binder) {
        Log.e(TAG, "onServiceConnected: " + binder);
        setService(binder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.e(TAG, "onServiceDied");
        this.mService = null;
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-DcmService_4.5.0.0";
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public void registerListener(IDeviceConnectionListener listener, int registerType) {
        Log.d(TAG, "registerListener listener: " + listener + ", registerType: " + registerType);
        if (listener == null) {
            Log.e(TAG, "registerListener: parameter is null");
            return;
        }
        boolean isEmpty = this.mListenerHelper.isEmpty();
        boolean result = this.mListenerHelper.addListener(listener);
        if (!result) {
            Log.e(TAG, "registerListener: listener has existed");
        }
        this.mRegisterObjectType = registerType;
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "registerListener: mService is null");
            return;
        }
        if (isEmpty) {
            try {
                iConnectionManagementService.addDmCallback(this.mDmCallback, registerType);
            } catch (RemoteException ex) {
                Log.e(TAG, "registerListener: addDmCallback failed, " + ex.toString());
                ex.printStackTrace();
            }
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public void unregisterListener(IDeviceConnectionListener listener, int registerType) {
        Log.d(TAG, "unregisterListener listener: " + listener + ", registerType: " + registerType);
        if (listener == null) {
            Log.e(TAG, "unregisterListener: parameter is null");
            return;
        }
        this.mListenerHelper.removeListener(listener);
        if (this.mListenerHelper.isEmpty()) {
            try {
                this.mService.rmDmCallback(this.mDmCallback, registerType);
                this.mRegisterObjectType = registerType;
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public ConnectionDevice getLastDevice() {
        Log.d(TAG, "getLastDevice");
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "getLastDevice: mService is null");
            return null;
        }
        try {
            return Utils.covertAidlToConnectionDevice(iConnectionManagementService.getLastDevice());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean setLastDevice(Device device) {
        Log.d(TAG, "setLastDevice device: " + device);
        if (this.mService == null || !isAvailableDevice(device)) {
            Log.e(TAG, "setLastDevice: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.setLastDevice(device.getDeviceId());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public HashMap<String, ConnectionDevice> getDeviceList() {
        Log.d(TAG, "getDeviceList");
        if (this.mService == null) {
            Log.e(TAG, "getDeviceList: mService is null");
            return null;
        }
        HashMap<String, ConnectionDevice> result = new HashMap<>();
        Bundle bundle = new Bundle();
        try {
            this.mService.getDevices(bundle);
            bundle.setClassLoader(getClass().getClassLoader());
            for (String name : bundle.keySet()) {
                result.put(name, Utils.covertAidlToConnectionDevice((ConnDevice) bundle.get(name)));
            }
            return result;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public HashMap<String, ConnectionDevice> getOnlineDeviceList() {
        Log.d(TAG, "getOnlineDeviceList");
        if (this.mService == null) {
            Log.e(TAG, "getOnlineDeviceList: mService is null");
            return null;
        }
        HashMap<String, ConnectionDevice> result = new HashMap<>();
        Bundle bundle = new Bundle();
        try {
            this.mService.getOnlineDevices(bundle);
            bundle.setClassLoader(getClass().getClassLoader());
            for (String name : bundle.keySet()) {
                result.put(name, Utils.covertAidlToConnectionDevice((ConnDevice) bundle.get(name)));
            }
            return result;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean deleteDevice(Device device) {
        Log.d(TAG, "deleteDevice device: " + device);
        if (this.mService == null || !isAvailableDevice(device)) {
            Log.e(TAG, "deleteDevice: failed for invalid parameter.");
            return false;
        }
        try {
            return this.mService.deleteDevice(device.getDeviceId());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean deleteAllDevices() {
        Log.d(TAG, "deleteAllDevice");
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "deleteAllDevices: mService is null");
            return false;
        }
        try {
            return iConnectionManagementService.deleteAllDevices(null);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public ConnectionDevice getDevice(String devId) {
        Log.d(TAG, "getDevice devId: " + LogEncrypt.IpAddressEncrypt(devId));
        if (this.mService == null || devId == null || devId.isEmpty()) {
            Log.e(TAG, "getDevice: failed for invalid parameter");
            return null;
        }
        try {
            return Utils.covertAidlToConnectionDevice(this.mService.getDevice(devId));
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public List<ts.car.service.dcm.data.AbnormalDevice> getAbnormalDeviceList() {
        Log.d(TAG, "getAbnormalDeviceList");
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "getAbnormalDeviceList: mService is null");
            return null;
        }
        try {
            return Utils.covertAidlToAbnormalDevice(iConnectionManagementService.getAbnormalDeviceList());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean isProjectionSupported(int serviceType) {
        Log.d(TAG, "isProjectionSupported serviceType: " + serviceType);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(serviceType)) {
            Log.e(TAG, "isProjectionSupported: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.isProjectionSupport(serviceType);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean isProjectionEnabled(int serviceType) {
        Log.d(TAG, "isProjectionEnabled serviceType: " + serviceType);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(serviceType)) {
            Log.e(TAG, "isProjectionEnabled: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.isEnableProjection(serviceType);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean enableProjection(int serviceType, boolean enable) {
        Log.d(TAG, "enableProjection serviceType: " + serviceType + ", enable: " + enable);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(serviceType)) {
            Log.e(TAG, "enableProjection:failed for invalid parameter.");
            return false;
        }
        try {
            return this.mService.enableProjection(serviceType, enable);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean enableProjection(int serviceType, int deviceType, boolean enable) {
        Log.d(TAG, "enableProjection serviceType: " + serviceType + ", deviceType: " + deviceType + ", enable: " + enable);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(serviceType)) {
            Log.e(TAG, "enableProjection:failed for invalid parameter.");
            return false;
        }
        try {
            return this.mService.enableProjectionByDevType(serviceType, deviceType, enable);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public int connectProjection(Device device, ProjectionLinkParameter parameter) {
        Log.d(TAG, "connectProjection device: " + device + ", link para: " + parameter);
        if (this.mService == null || !isAvailableDevice(device) || parameter == null) {
            Log.e(TAG, "connectProjection: failed for invalid parameter");
            return 5;
        }
        parameter.setDevId(device.getDeviceId());
        try {
            return this.mService.connectProjection(Utils.covertProjectionLinkParameterToAidl(parameter));
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return 4;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean disconnectProjection(Device device, int capa, int reason) {
        Log.d(TAG, "disconnectProjection device: " + device + ", capa: " + capa + ", reason: " + reason);
        if (this.mService == null || !isAvailableDevice(device) || !CmsConstants.Capability.isProjectionCapability(capa)) {
            Log.e(TAG, "disconnectProjection: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.disconnectProjection(device.getDeviceId(), capa, reason);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean setAutoProjectionConnectCapability(Device device, int capa, boolean always) {
        Log.d(TAG, "projectionAlwaysSetting device: " + device + ", capa: " + capa + ", always: " + always);
        if (this.mService == null || !isAvailableDevice(device) || !CmsConstants.Capability.isProjectionCapability(capa)) {
            Log.e(TAG, "setAutoProjectionConnectCapability: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.setAlwaysProjection(device.getDeviceId(), capa, always);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public List<ConnectionDevice> getActiveProjections() {
        Log.d(TAG, "getActiveProjections");
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "getActiveProjections: mService is null");
            return null;
        }
        try {
            return Utils.covertAidlToConnectionDevice(iConnectionManagementService.getActiveProjections());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean cancelProjectConnection(Device device, int type) {
        Log.d(TAG, "cancelProjectConnection device: " + device + ", type: " + type);
        if (this.mService == null || !isAvailableDevice(device)) {
            Log.e(TAG, "cancelProjectConnection: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.cancelWirelessCarplay(device.getDeviceId());
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean setDeviceConnectionDetection(boolean enable, boolean persistent) {
        Log.d(TAG, "setDeviceConnectionDetection enable: " + enable + ", persistent:" + persistent);
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "setDeviceConnectionDetection: mService is null");
            return false;
        }
        try {
            return iConnectionManagementService.setDeviceConnectionDetection(enable, persistent);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public boolean checkDeviceCapability(Device device, int capability) {
        Log.d(TAG, "checkDeviceCapability device: " + device + ", capability: " + capability);
        if (this.mService == null || !isAvailableDevice(device)) {
            Log.e(TAG, "checkDeviceCapability: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.checkDeviceCapability(device.getDeviceId(), capability);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.dcm.IDeviceConnectionManager
    public int getSupportedWifiChannel() {
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "getSupportedWifiChannel: service is null.");
            return 0;
        }
        try {
            int channel = iConnectionManagementService.getSupportedWifiChannel();
            return channel;
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private synchronized void setService(IBinder binder) {
        if (binder != null) {
            IConnectionManagementService iConnectionManagementServiceAsInterface = IConnectionManagementService.Stub.asInterface(binder);
            this.mService = iConnectionManagementServiceAsInterface;
            if (iConnectionManagementServiceAsInterface != null && this.mListenerHelper != null && !this.mListenerHelper.isEmpty()) {
                try {
                    this.mService.addDmCallback(this.mDmCallback, this.mRegisterObjectType);
                } catch (RemoteException ex) {
                    Log.d(TAG, "addDmCallback failed," + ex.toString());
                    ex.printStackTrace();
                }
            }
        }
    }

    private boolean isAvailableDevice(Device device) {
        return (device == null || device.getDeviceId() == null || device.getDeviceId().isEmpty()) ? false : true;
    }
}
