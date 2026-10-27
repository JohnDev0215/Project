package com.ts.car.dcm.fsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.FoundationComponent;
import com.ts.car.core.annotation.FoundationConstruction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import ts.car.dcm.common.aidl.IConnectionManagementService;
import ts.car.dcm.common.aidl.IPcmListener;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.CmsConstants;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiApConfiguration;
import ts.car.dcm.common.data.WifiConfigInfo;
import ts.car.dcm.common.util.LogEncrypt;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionListener;
import ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager;

/* JADX INFO: loaded from: classes.dex */
@FoundationComponent
public class ProjectionConnectionManager extends SimpleServiceBase implements IProjectionConnectionManager {
    private static final String REMOTE_SERVICE_NAME = "CmService";
    private static final String SERVICE_COMPONENT = "com.ts.car.dcm.dccontroller/.connectionmanagerservice.CmService";
    private static final String TAG = ProjectionConnectionManager.class.getSimpleName();
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private final HashMap<Integer, CopyOnWriteArrayList<IProjectionConnectionListener>> mListeners;
    private IPcmListener.Stub mPcmCallback;
    private IConnectionManagementService mService;

    /* JADX INFO: renamed from: com.ts.car.dcm.fsdk.ProjectionConnectionManager$1, reason: invalid class name */
    class AnonymousClass1 extends IPcmListener.Stub {
        AnonymousClass1() {
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableFunction(int funId, boolean status, int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onEnableFunction, funId: " + funId + ", status: " + status + ", srv: " + srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDeviceConnectionUpdated(final CmDevice device, final int connType, final int reason, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onDeviceConnectionUpdated, device: " + device + ", connType: " + connType + ", reason: " + reason + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$cQarSQm9BTFDoZ1UAVg235txDbw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onDeviceConnectionUpdated(device, connType, reason, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onConnectProjection(final CmDevice device, final ProjectionLinkParameter lp, int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onConnectProjection, device: " + device + ", plp: " + lp + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$5Cej5lQAPiOBzCYUbAkusuN8xHA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).connectProjection(device, lp);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectProjection(final String devId, final int capa, int srv, final int reason) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onDisconnectProjection, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", capa: " + capa + ", srv: " + srv + ",reason: " + reason);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$5zhYygwxubgJJZ1ioKQILH9XsKA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).disconnectProjection(devId, capa, reason);
                }
            }, srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$CxGsbCVwrXsLiJFd_5SwMocH-ng
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).disconnectProjection(devId, capa);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchProjectionConnection(final CmDevice device, final int capa, int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onSwitchProjectionConnection, device: " + device + ", capa: " + capa + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$iuTmRlZrhTISDjAs9dfKKpPxYiw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).switchConnection(device, capa);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchAaConnection(final CmDevice device, final CmDevice device2, int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onSwitchAaConnection, device: " + device + ", device2: " + device2 + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$4yzTD3Kh3Ex29TIqV7frwtDBBYw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).switchAaConnection(device, device2);
                }
            }, 3);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDeleteProjectionData(final String devId, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onDeleteProjectionData, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$GmRS0B1wU5KIRdOhSYoiu2sunU4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).deleteDevice(devId, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onShowProjection(final boolean flag, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onShowProjection, flag: " + flag + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$iQX3F3Mu0HfTJKdwnH40QOciBSg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).showProjection(flag, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onUsbRoleSwitchStarting(final String devId, final int mode, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onUsbRoleSwitchStarting, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", mode: " + mode + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$zG3gTCGQ5qFAnQLQNsMpB5kzmVE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onUsbRoleSwitchStarting(devId, mode, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onUsbRoleSwitchFinished(final CmDevice dev, final int mode, final boolean result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onUsbRoleSwitchFinished, dev: " + dev + ", mode: " + mode + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$dTbcGEvhdRXn9Ecn1ATTBMDhWFk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onUsbRoleSwitchFinished(dev, mode, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onResetUsbStarting(final String devId, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onResetUsbBusStarting, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$OmjmwigkkH4ANrpbap3jjJfsagY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onResetUsbBusStarting(devId, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onResetUsbFinished(final String devId, final boolean result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onResetUsbFinished, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$ox_UY_OW0QP-laxdkOfsjBmNP6Q
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onResetUsbFinished(devId, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableUsbAoaStarting(final CmDevice dev, final int mode, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onEnableUsbAoaStarting, dev: " + dev + ", mode: " + mode + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$wcZjy0ZvnMgsU4F3Uxxf8lE9_Rs
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onEnableUsbAoaStarting(dev, mode, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onEnableUsbAoaFinished(final CmDevice dev, final int mode, final boolean result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onEnableUsbAoaFinished, dev: " + dev + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$ye9K1XN4gxPMdVpTOyCVnz665I0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onEnableUsbAoaFinished(dev, mode, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtStateChanged(final int state, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onBtStateChanged, state: " + state + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$_DrJqXLk8h0AgpVO5gZeJWkkeE8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onBtStateChanged(state, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtDisconnected(final String devId, final int result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onBtDisconnected, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$MnNFPrFjT5GgDCMOvj7kE6bJchI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onBtDisconnected(devId, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtConnected(final String devId, final int result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onBtConnected, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$HKukP32Z3YVam2H32qktXTxGKbM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onBtConnected(devId, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onBtProfileChanged(final String id, final int profile, final int state, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onBtProfileChanged, id: " + LogEncrypt.IpAddressEncrypt(id) + ", profile: " + profile + ", state: " + state + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$qxElXuJb0-pPGuDM1-c6eFsI69s
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onBtProfileChanged(id, profile, state, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectedOtherBtDevices(final String devId, final int result, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onDisconnectedOtherBtDevices, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", result: " + result + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$yO49lmq5GCxmnKR-vo-MBLOvuBI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onOtherBtDevicesDisconnected(devId, result, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onIap2BtDeviceConnected(final CmDevice dev, final int reason, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onIap2BtDeviceConnected, dev: " + dev + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$ixvhTt-A6RZBB7eVnPcj14tiuec
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onIap2BtDeviceConnected(dev, reason, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void notifyBtOobPairComplete(final String devId, final int status, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- notifyBtOobPairComplete, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", status: " + status + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$OQCWZ_ty6WVHrC8AmTEh6ICy7JI
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onBtOobPairCompleted(devId, status, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onWifiApInfo(final WifiConfigInfo config, final int status, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onWifiApInfo, status: " + status + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$zelmOrYPKDymXTuY52cEBVk2Xxk
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    IProjectionConnectionListener iProjectionConnectionListener = (IProjectionConnectionListener) obj;
                    iProjectionConnectionListener.onWifiApInfoChanged(config.getWifiConfiguration(), status, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onWifiConnectStatus(final String mac, final int status, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onWifiConnectStatus, mac: " + LogEncrypt.MacAddressEncrypt(mac) + ", status: " + status + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$LfL1T_07bmbV8t0eJdMDQi7hdgA
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).onWifiConnectStatusChanged(mac, status, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onSwitchConnection(final CmDevice device, final int capa, int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onSwitchConnection, dev: " + device + ", capa: " + capa + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$3XC7kE2vyvM1cX1HZI9scLJFg4s
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).switchConnection(device, capa);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onCheckUsbDeviceName(final CmDevice device, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onCheckUsbDeviceName, device: " + device + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$NUQ3dxUcZQpC9hdofMbGOqP5hzQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).checkUsbDeviceName(device, srv);
                }
            }, srv);
        }

        @Override // ts.car.dcm.common.aidl.IPcmListener
        public void onDisconnectIapSession(final String devId, final int srv) {
            Log.d(ProjectionConnectionManager.TAG, "mPcmCallback --- onDisconnectIapSession, devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", srv: " + srv);
            ProjectionConnectionManager.this.broadcast(new Consumer() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$1$t82ScUm4G9Ks7AKBULRq4-F06fM
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IProjectionConnectionListener) obj).disconnectIapSession(devId, srv);
                }
            }, srv);
        }
    }

    @FoundationConstruction
    public ProjectionConnectionManager(Context context) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, null);
        this.mService = null;
        this.mListeners = new HashMap<>();
        this.mPcmCallback = new AnonymousClass1();
        if (this.mHandler == null) {
            if (this.mHandlerThread == null) {
                HandlerThread handlerThread = new HandlerThread(TAG);
                this.mHandlerThread = handlerThread;
                handlerThread.start();
            }
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-DCM-PROJECTION-CONNECTION-MANAGER_1.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder binder) {
        Log.d(TAG, "onServiceConnected");
        setService(binder);
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied");
        this.mService = null;
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void registerListener(IProjectionConnectionListener listener, int type) {
        Log.d(TAG, "registerListener listener: " + listener + ", type: " + type);
        synchronized (this.mListeners) {
            HashMap<Integer, IProjectionConnectionListener> listeners = new HashMap<>();
            listeners.put(Integer.valueOf(type), listener);
            addListener(listeners);
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void unregisterListener(IProjectionConnectionListener listener, int type) {
        Log.d(TAG, "unregisterListener listener: " + listener + ", type: " + type);
        synchronized (this.mListeners) {
            HashMap<Integer, IProjectionConnectionListener> listeners = new HashMap<>();
            listeners.put(Integer.valueOf(type), listener);
            rmListener(listeners);
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean isEnableProjection(int service) {
        Log.d(TAG, "isEnableProjection service: " + service);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(service)) {
            Log.e(TAG, "isEnableProjection: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.isEnableProjection(service);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public List<CmDevice> getDeviceList(int service) {
        Log.d(TAG, "getDeviceList service: " + service);
        if (this.mService == null || !CmsConstants.ServiceType.isAvailableType(service)) {
            Log.e(TAG, "getDeviceList: failed for invalid parameter");
            return null;
        }
        try {
            return this.mService.getProjectionDevices(service);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void updateConnectionStatus(String devId, int capa, int status, int code) {
        Log.d(TAG, "updateConnectionStatus devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", capa: " + capa + ", status: " + status + ", code: " + code);
        if (this.mService == null || devId == null || devId.isEmpty()) {
            Log.e(TAG, "updateConnectionStatus: failed for invalid parameter");
            return;
        }
        try {
            this.mService.updateProjectionConnectionStatus(devId, capa, status, code);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean doUsbRoleSwitch(String devId, int mode, int purpose) {
        Log.d(TAG, "doUsbRoleSwitch devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", mode: " + mode + ", purpose: " + purpose);
        if (this.mService == null || devId == null || devId.isEmpty() || mode < 0 || mode > 1) {
            Log.e(TAG, "doUsbRoleSwitch: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.doUsbRoleSwitch(devId, mode, purpose);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean resetUsb(String devId) {
        Log.d(TAG, "resetUsb devId: " + LogEncrypt.IpAddressEncrypt(devId));
        if (this.mService == null || devId == null || devId.isEmpty()) {
            Log.e(TAG, "resetUsb: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.resetUsb(devId);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean enableUsbAoa(String devId, int mode, boolean flag) {
        Log.d(TAG, "enableUsbAoa devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", mode: " + mode + ", flag: " + flag);
        if (this.mService == null || devId == null || devId.isEmpty()) {
            Log.e(TAG, "enableUsbAoa: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.enableUsbAoa(devId, mode, flag);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean enableUsbAoa(String devId, String serialNumber, int mode, boolean flag) {
        Log.d(TAG, "enableUsbAoa devId: " + LogEncrypt.IpAddressEncrypt(devId) + " serialNumber: " + LogEncrypt.IpAddressEncrypt(serialNumber) + " mode: " + mode + " flag: " + flag);
        if (this.mService != null && devId != null && !devId.isEmpty() && serialNumber != null && !serialNumber.isEmpty()) {
            try {
                return this.mService.enableUsbAoaForAndroidAuto(devId, serialNumber, mode, flag);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean disableBluetooth(String devId, String mac) {
        Log.d(TAG, "disableBluetooth devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", mac: " + LogEncrypt.MacAddressEncrypt(mac));
        if (this.mService == null || devId == null || devId.isEmpty() || mac == null || mac.isEmpty()) {
            Log.e(TAG, "disconnectBtDevice: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.disconnectBtDevice(devId, mac);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public BtOobInfo getBtOobInfo() {
        Log.d(TAG, "getBtOobInfo");
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "getBtOobInfo: mService is null");
            return null;
        }
        try {
            return iConnectionManagementService.getBtOobInfo();
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "getBtOobInfo remote exception");
            return null;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void setBtOobInfo(String name, String mac, String linkKey, int linkKeyType) {
        Log.d(TAG, "setBtOobInfo name: " + name + ", mac: " + LogEncrypt.MacAddressEncrypt(mac) + ", linkKey: " + linkKey + ", linkKeyType: " + linkKeyType);
        if (this.mService == null || name == null || name.isEmpty() || mac == null || mac.isEmpty() || linkKey == null || linkKey.isEmpty()) {
            Log.e(TAG, "setBtOobInfo: failed for invalid parameter");
            return;
        }
        try {
            this.mService.setBtOobInfo(name, linkKey, mac, linkKeyType);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "setBluetoothOobInfo remote exception");
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean mergeDevices(String devId, String key, int keyType) {
        Log.d(TAG, "mergeDevices devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", key: " + key + ", keyType: " + keyType);
        if (this.mService == null || devId == null || devId.isEmpty() || key == null || key.isEmpty()) {
            Log.e(TAG, "mergeDevices: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.mergeDevices(devId, key, keyType);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "mergeDevices remote exception");
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean mergeDevicesByUsbAndBt(String usb, String bt) {
        Log.d(TAG, "mergeDevices usb: " + usb + ", bt: " + bt);
        if (this.mService == null || usb == null || usb.isEmpty() || bt == null || bt.isEmpty()) {
            Log.e(TAG, "mergeDevices: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.mergeDevicesByUsbAndBt(usb, bt);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "mergeDevices remote exception");
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void enableWifiAp(boolean enable, WifiApConfiguration config) {
        Log.d(TAG, "enableWifiAp enable: " + enable + ", config: " + config);
        IConnectionManagementService iConnectionManagementService = this.mService;
        if (iConnectionManagementService == null) {
            Log.e(TAG, "enableWifiAp: mService is null");
            return;
        }
        try {
            iConnectionManagementService.enableWifiAp(enable, config);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void updateProjectionCapability(String id, int capability, boolean flag, int reason) {
        Log.d(TAG, "projectionCapabilityUpdate id: " + LogEncrypt.IpAddressEncrypt(id) + ", capa: " + capability + ", flag: " + flag + ", reason: " + reason);
        if (this.mService == null || id == null || id.isEmpty()) {
            Log.e(TAG, "updateProjectionCapability: failed for invalid parameter");
            return;
        }
        try {
            this.mService.updateProjectionCapability(id, capability, flag, reason);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "projectionCapabilityUpdate remote exception");
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean updateDeviceFriendlyName(String devId, String name) {
        Log.d(TAG, "updateDeviceFriendlyName devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", name: " + name);
        if (this.mService == null || devId == null || devId.isEmpty() || name == null || name.isEmpty() || name.length() > 64) {
            Log.e(TAG, "updateDeviceFriendlyName: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.updateDeviceFriendlyName(devId, name);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public boolean updateUsbDeviceName(String devId, String name) {
        Log.d(TAG, "updateUsbDeviceName devId: " + LogEncrypt.IpAddressEncrypt(devId) + ", name: " + name);
        if (this.mService == null || devId == null || devId.isEmpty() || name == null || name.isEmpty() || name.length() > 64) {
            Log.e(TAG, "updataUsbDeviceName: failed for invalid parameter");
            return false;
        }
        try {
            return this.mService.updateUsbDeviceName(devId, name);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void updateIapSessionState(String id, int state, int reason) {
        Log.d(TAG, "updateIapSessionState id:" + LogEncrypt.IpAddressEncrypt(id) + ", state:" + state + ", reason:" + reason);
        if (this.mService == null || id == null || id.isEmpty()) {
            Log.e(TAG, "updateIapSessionState: failed for invalid parameter");
            return;
        }
        try {
            this.mService.updateIapSessionState(id, state, reason);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "updateIapSessionState remote exception");
        }
    }

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public int getSupportedWifiChannel() {
        Log.d(TAG, "getSupportedWifiChannel");
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

    @Override // ts.car.dcm.fsdk.interfaces.IProjectionConnectionManager
    public void setMobileDeviceId(String devId, String mobileDeviceId) {
        Log.d(TAG, "setMobileDeviceId devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mobileDeviceId:" + LogEncrypt.IpAddressEncrypt(mobileDeviceId));
        if (this.mService == null || devId == null || devId.isEmpty()) {
            Log.e(TAG, "setMobileDeviceId: failed for invalid parameter");
            return;
        }
        try {
            this.mService.setMobileDeviceId(devId, mobileDeviceId);
        } catch (RemoteException ex) {
            ex.fillInStackTrace();
            Log.e(TAG, "setMobileDeviceId remote exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcast(final Consumer<IProjectionConnectionListener> c, final int srv) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.fsdk.-$$Lambda$ProjectionConnectionManager$D5mjsP48xGnIN3vDo5vR_PCpNvo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$broadcast$0$ProjectionConnectionManager(srv, c);
            }
        });
    }

    public /* synthetic */ void lambda$broadcast$0$ProjectionConnectionManager(int srv, Consumer c) {
        synchronized (this.mListeners) {
            CopyOnWriteArrayList<IProjectionConnectionListener> listener = this.mListeners.get(Integer.valueOf(srv));
            if (listener == null) {
                Log.e(TAG, "broadcast can't find listener");
                return;
            }
            CopyOnWriteArrayList<IProjectionConnectionListener> listeners = new CopyOnWriteArrayList<>(listener);
            Iterator<IProjectionConnectionListener> it = listeners.iterator();
            while (it.hasNext()) {
                c.accept(it.next());
            }
        }
    }

    private synchronized void setService(IBinder service) {
        if (service != null) {
            IConnectionManagementService iConnectionManagementServiceAsInterface = IConnectionManagementService.Stub.asInterface(service);
            this.mService = iConnectionManagementServiceAsInterface;
            if (iConnectionManagementServiceAsInterface != null && this.mListeners != null && this.mListeners.size() != 0) {
                List<String> types = new ArrayList<>();
                Iterator<Integer> it = this.mListeners.keySet().iterator();
                while (it.hasNext()) {
                    int type = it.next().intValue();
                    types.add(String.valueOf(type));
                }
                try {
                    this.mService.addPcmCallback(this.mPcmCallback, types);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private void addListener(HashMap<Integer, IProjectionConnectionListener> listeners) {
        if (this.mService != null) {
            Log.d(TAG, "addListener listeners: " + listeners.toString());
            List<String> services = new ArrayList<>();
            for (Map.Entry<Integer, IProjectionConnectionListener> entry : listeners.entrySet()) {
                int type = entry.getKey().intValue();
                IProjectionConnectionListener listener = entry.getValue();
                if (CmsConstants.ServiceType.isAvailableType(type)) {
                    CopyOnWriteArrayList<IProjectionConnectionListener> srvListenerList = this.mListeners.get(Integer.valueOf(type));
                    if (srvListenerList == null) {
                        CopyOnWriteArrayList<IProjectionConnectionListener> srvListenerList2 = new CopyOnWriteArrayList<>();
                        this.mListeners.put(Integer.valueOf(type), srvListenerList2);
                        srvListenerList2.add(listener);
                        services.add(String.valueOf(type));
                    } else if (!srvListenerList.contains(listener)) {
                        srvListenerList.add(listener);
                    } else {
                        Log.d(TAG, "The same listener already exist: " + type);
                    }
                } else {
                    Log.d(TAG, "addListener unknow type: " + type);
                }
            }
            try {
                if (services.size() > 0) {
                    this.mService.addPcmCallback(this.mPcmCallback, services);
                    return;
                }
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "IBinder service object is null");
    }

    private void rmListener(HashMap<Integer, IProjectionConnectionListener> listeners) {
        if (this.mService != null) {
            Log.d(TAG, "rmListener types: " + listeners.toString());
            List<String> services = new ArrayList<>();
            for (Map.Entry<Integer, IProjectionConnectionListener> entry : listeners.entrySet()) {
                int type = entry.getKey().intValue();
                IProjectionConnectionListener listener = entry.getValue();
                if (CmsConstants.ServiceType.isAvailableType(type)) {
                    CopyOnWriteArrayList<IProjectionConnectionListener> srvListenerList = this.mListeners.get(Integer.valueOf(type));
                    if (srvListenerList != null && srvListenerList.contains(listener)) {
                        srvListenerList.remove(listener);
                        if (srvListenerList.isEmpty()) {
                            services.add(String.valueOf(type));
                            this.mListeners.remove(Integer.valueOf(type));
                        }
                    } else {
                        Log.d(TAG, "The listener is not exist: " + type);
                    }
                } else {
                    Log.d(TAG, "rmListener unknow type:" + type);
                }
            }
            try {
                if (services.size() > 0) {
                    this.mService.rmPcmCallback(this.mPcmCallback, services);
                    return;
                }
                return;
            } catch (RemoteException ex) {
                ex.printStackTrace();
                return;
            }
        }
        Log.e(TAG, "IBinder service object is null");
    }
}
