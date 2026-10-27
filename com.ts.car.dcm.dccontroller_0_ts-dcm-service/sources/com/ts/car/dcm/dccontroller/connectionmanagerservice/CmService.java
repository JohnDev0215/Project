package com.ts.car.dcm.dccontroller.connectionmanagerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.dcm.common.data.DcmConsumer;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.projectionmanager.ProjectionManager;
import com.ts.car.dcm.dccontroller.storemanager.Device;
import com.ts.car.dcm.dccontroller.storemanager.DeviceManager;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import ts.car.dcm.common.aidl.IConnectionManagementService;
import ts.car.dcm.common.aidl.IConnectionManagementServiceCallback;
import ts.car.dcm.common.aidl.IDmListener;
import ts.car.dcm.common.aidl.IPcmListener;
import ts.car.dcm.common.data.AbnormalDevice;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.CmDevice;
import ts.car.dcm.common.data.CmsConstants;
import ts.car.dcm.common.data.ConnDevice;
import ts.car.dcm.common.data.ProjectionLinkParameter;
import ts.car.dcm.common.data.WifiApConfiguration;
import ts.car.dcm.common.data.WifiConfigInfo;

/* JADX INFO: loaded from: classes.dex */
public class CmService extends Service {
    public static final int EVENT_PROJECTION_DEATH = 0;
    private static final String TAG = "CmService";
    private ICmCommandstub mCmCommandBinder;
    public ConnectionManagerCallback mCmsCallback;
    private CmsListenerManager mCmsListenerManager;
    private ConfigManager mConfigManager;
    private DcController mDcController;
    private DeviceManager mDeviceManager;
    CmServiceHandler mHandler;
    private HandlerThread mHandlerThread;
    private ProjectionManager mProjectionManager;

    public CmService() {
        this.mCmCommandBinder = new ICmCommandstub();
        this.mCmsCallback = new ConnectionManagerCallback();
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("CmsHandlerThread");
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        Looper looper = this.mHandlerThread.getLooper();
        this.mHandler = new CmServiceHandler(looper);
        DcController dcController = DcController.getInstance(this);
        this.mDcController = dcController;
        ConfigManager configManager = dcController.getConfigManager();
        this.mConfigManager = configManager;
        this.mCmsListenerManager = new CmsListenerManager(this.mHandler, configManager);
        this.mDeviceManager = this.mDcController.getDeviceManager();
        this.mProjectionManager = this.mDcController.getProjectionManager();
        this.mCmsListenerManager.init();
        this.mDcController.registerCmsCallback(this.mCmsCallback);
        this.mProjectionManager.registerCmsCallback(this.mCmsCallback);
        if (this.mConfigManager.isDeviceDetectionAllowed()) {
            Log.e(TAG, "startConnectionMonitor.");
            this.mDcController.startConnectionMonitor();
        }
    }

    private class CmServiceHandler extends Handler {
        public CmServiceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                CmService.this.mProjectionManager.handleProjectionServiceDeath(msg.arg1);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind.");
        return this.mCmCommandBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate.");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy.");
        DcController dcController = this.mDcController;
        if (dcController != null) {
            dcController.registerCmsCallback(null);
            this.mProjectionManager.registerCmsCallback(null);
            this.mDcController = null;
        }
        this.mCmsListenerManager.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ConnectionManagerCallback implements ICmsCallback {
        private ConnectionManagerCallback() {
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceAccept(final Device device) {
            Log.d(CmService.TAG, "onDeviceAccept, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$nfC1HKjkaxlhBf-oFWJYKCfgAnI
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onDeviceAccept(device.getConnDevice());
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onRequestProjection(final Device device, final int projectionType) {
            Log.d(CmService.TAG, "onRequestProjection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$Loke9ZqMREeZi7GBLgaJHMQ_2nE
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IDmListener iDmListener = (IDmListener) obj;
                    iDmListener.onRequestProjection(device.getConnDevice(), projectionType);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceStatusChanged(Device device) {
            Log.d(CmService.TAG, "onDeviceStatusChanged, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final ConnDevice connDev = CmService.this.mDeviceManager.getChangedConnDevice(device);
            if (connDev != null) {
                CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$nYg0Y1Jy9oMhP5bM3YkfXuFga0E
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IDmListener) obj).onDeviceStatusChanged(connDev);
                    }
                });
                Log.d(CmService.TAG, "onDeviceStatusChanged, device: " + LogEncrypt.IpAddressEncrypt(connDev.getDeviceUuid()) + " changedMask: " + Long.toHexString(connDev.getChangedMask()));
                return;
            }
            Log.e(CmService.TAG, "onDeviceStatusChanged, no change for the ConnDevice.");
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDevicesMerged(final Device device) {
            Log.d(CmService.TAG, "onDevicesMerged, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$rLkE0a95ygQznYfjjFCUBcB3T2c
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    Device device2 = device;
                    ((IDmListener) obj).onDevicesMerged(device2.getPairedDevId(), device2.getId(), device2.getConnDevice());
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onProjectionConnectionChanged(final Device device, final int capa, final int status, final int reason) {
            Log.d(CmService.TAG, "onProjectionConnectionChanged, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", capa:" + capa + ", status:" + status + ", reason:" + reason);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$NjXdnzOhLXSnSDgSaS77_KMHXK4
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IDmListener iDmListener = (IDmListener) obj;
                    iDmListener.onProjectionConnectionChanged(device.getConnDevice(), capa, status, reason);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onProjectionFunctionChanged(Device device, int funId, int status) {
            Log.d(CmService.TAG, "onProjectionFunctionChanged, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", funId:" + funId + ", status:" + status);
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onEnableFunction(final int srvType, final int funId, final boolean status) {
            Log.d(CmService.TAG, "onEnableFunction, service:" + srvType + ", funId:" + funId + ", status:" + status);
            CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$A0pyrHpzLFUtXd6zPwm_BoRSpyk
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IPcmListener) obj).onEnableFunction(funId, status, srvType);
                }
            }, srvType);
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceConnectionUpdated(final Device device, final int connType, final int reason, final int service) {
            Log.d(CmService.TAG, "onDeviceConnectionUpdated, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$y15SoPeClpNg2YoHsvtlNQ_Rlgw
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IPcmListener iPcmListener = (IPcmListener) obj;
                    iPcmListener.onDeviceConnectionUpdated(device.getCmDevice(), connType, reason, service);
                }
            }, service);
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onConnectProjection(final Device device, final ProjectionLinkParameter plp) {
            Log.d(CmService.TAG, "onConnectProjection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int service = DcmUtils.getSrvTypeByCapability(plp.getCapability());
            if (CmService.this.mCmsListenerManager.isAvailablePmService(service)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$S0A1TDstAv3FSKCD1fY0rzimK90
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onConnectProjection(device.getCmDevice(), plp, service);
                    }
                }, service);
                return true;
            }
            Log.e(CmService.TAG, "onConnectProjection, projection service was not registered. service:" + service);
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onDisconnectProjection(final Device device, final int capability, final int reason) {
            Log.d(CmService.TAG, "onDisconnectProjection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", reason: " + reason);
            final int service = DcmUtils.getSrvTypeByCapability(capability);
            if (CmService.this.mCmsListenerManager.isAvailablePmService(service)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$3PFl_e4sXOct3RV-SNa7Nyo8p58
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onDisconnectProjection(device.getId(), capability, service, reason);
                    }
                }, service);
                return true;
            }
            Log.e(CmService.TAG, "onDisconnectProjection, projection service was not registered. service:" + service);
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onSwitchProjectionConnection(final Device device, final int capability) {
            Log.d(CmService.TAG, "onSwitchProjectionConnection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int service = DcmUtils.getSrvTypeByCapability(capability);
            if (CmService.this.mCmsListenerManager.isAvailablePmService(service)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$X2VRVkb4zBBJj4z8qfF44qcBUhM
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onSwitchProjectionConnection(device.getCmDevice(), capability, service);
                    }
                }, service);
                return true;
            }
            Log.e(CmService.TAG, "onSwitchProjectionConnection, projection service was not registered. service:" + service);
            return true;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onSwitchAaConnection(final Device device, final Device device2) {
            Log.d(CmService.TAG, "onSwitchAaConnection, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", devId2:" + LogEncrypt.IpAddressEncrypt(device2.getId()));
            if (CmService.this.mCmsListenerManager.isAvailablePmService(3)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$nVtKrQWWA3OJrc9gCHsAsYcL2XI
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onSwitchAaConnection(device.getCmDevice(), device2.getCmDevice(), 3);
                    }
                }, 3);
                return true;
            }
            Log.e(CmService.TAG, "onSwitchAaConnection, projection service was not registered. service:android auto");
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onDeleteProjectionData(final Device device, final int service) {
            Log.d(CmService.TAG, "onDeleteProjectionData, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            if (CmService.this.mCmsListenerManager.isAvailablePmService(service)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$a5G5fT8bUrlRaqvZ5Ow4_2ch3SE
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onDeleteProjectionData(device.getId(), service);
                    }
                }, service);
                return true;
            }
            Log.e(CmService.TAG, "onDeleteProjectionData, projection service was not registered. service:" + service);
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onRoleSwitchStarting(final Device device, final int mode) {
            Log.d(CmService.TAG, "onRoleSwitchStarting, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int srv = CmService.this.mProjectionManager.getActiveService(device.getId());
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$w-AbgoabRQ3tgLKa-7bj12SrWr4
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onUsbRoleSwitchStarting(device.getId(), mode, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onRoleSwitchFinished(final Device device, final int mode, final int result) {
            Log.d(CmService.TAG, "onRoleSwitchFinished, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", mode:" + mode + ", result:" + result);
            final int service = CmService.this.mDcController.getServiceTypeByRoleswitchPurpose(device.getUsbRoleSwitchPurpose());
            if (service != 0 && mode == 1) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$rjVdE15NjQ51hmfUx1gVt3g49zw
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        Device device2 = device;
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onUsbRoleSwitchFinished(device2.getCmDevice(), mode, result == 1, service);
                    }
                }, service);
            }
            if (result != 1) {
                device.setUsbRoleSwitchPurpose(0);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onResetUsbBusStarting(final Device device) {
            Log.d(CmService.TAG, "onResetUsbBusStarting, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int srv = CmService.this.mProjectionManager.getActiveService(device.getId());
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$_qXo9tx91W6AEqG5brs-v9SSEcc
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onResetUsbStarting(device.getId(), srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onResetUsbFinished(final Device device, final boolean result) {
            Log.d(CmService.TAG, "onResetUsbFinished, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", result:" + result);
            final int srv = CmService.this.mProjectionManager.getActiveService(device.getId());
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$zndTK26ctjcl1UtbSY7adZB_L3s
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onResetUsbFinished(device.getId(), result, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onEnableUsbAoAStarting(final Device device, final int mode) {
            Log.d(CmService.TAG, "onEnableUsbAoAStarting, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int srv = CmService.this.mProjectionManager.getActiveService(device.getId());
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$h9Zrp1pyI_myF6NCezllSi-FYsE
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onEnableUsbAoaStarting(device.getCmDevice(), mode, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onEnableUsbAoAFinished(final Device device, final int mode, final boolean result) {
            Log.d(CmService.TAG, "onEnableUsbAoAFinished, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", mode:" + mode + ", result:" + result);
            final int srv = CmService.this.mProjectionManager.getActiveService(device.getId());
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$BzaHBk1jWHR2KaumKohPAo7p-J8
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onEnableUsbAoaFinished(device.getCmDevice(), mode, result, srv);
                    }
                }, srv);
            } else {
                Log.e(CmService.TAG, "onEnableUsbAoAFinished, srv is NONE");
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onBtProfileChanged(final String mac, final int profile, final int reason) {
            Log.d(CmService.TAG, "onBtProfileChanged, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", profile:" + profile + ", reason:" + reason);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$tEjeoje3zSJyOdUCvLmkeZouK_Q
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onBtProfileChanged(mac, profile, reason, srv);
                    }
                }, srv);
            }
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$zNQW6zTlpA4CTObAc4y1-kQ3dd0
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onBtProfileChanged(mac, profile, reason, 0);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onBtStateChanged(final int status) {
            Log.d(CmService.TAG, "onBtStateChanged, status:" + status);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$WQIXa6JwXR2qTcWJV8KExcvF89s
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onBtStateChanged(status, srv);
                    }
                }, srv);
            }
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$yCz9-sP-GPQPpGIdx_fnfPURDqQ
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onBtStateChanged(status, 0);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onBtConnected(final String mac, final int status) {
            Log.d(CmService.TAG, "onBtConnected, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$6DSbx4xNOoseUbjDeWnNlmyLixg
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onBtConnected(mac, status, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onBtDisconnected(final String mac, final int status) {
            Log.d(CmService.TAG, "onBtDisconnected, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$zWH-NFtZK863ELhzx9qZ2Tcio7Q
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onBtDisconnected(mac, status, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDisconnectedOtherBtDevices(final String mac, final int status) {
            Log.d(CmService.TAG, "onDisconnectedOtherBtDevices, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$TedNei1sXzRiKrRGoWZZuZB4PRM
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onDisconnectedOtherBtDevices(mac, status, srv);
                    }
                }, srv);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onIap2BtDeviceConnected(final Device device, final int reason) {
            Log.d(CmService.TAG, "onIap2BtDeviceConnected, BtMac:" + LogEncrypt.MacAddressEncrypt(device.getBtAddr()) + ", devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            final int srv = 1;
            if (!CmService.this.mCmsListenerManager.isAvailablePmService(1)) {
                Log.e(CmService.TAG, "onIap2BtDeviceConnected, projection service was not registered. service:carplay");
                return false;
            }
            CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$7LAmgd3FO10SadkflSno0mzILJM
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IPcmListener iPcmListener = (IPcmListener) obj;
                    iPcmListener.onIap2BtDeviceConnected(device.getCmDevice(), reason, srv);
                }
            }, 1);
            return true;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void notifyBtOobPairComplete(final String mac, final int status) {
            Log.d(CmService.TAG, "notifyBtOobPairComplete, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", status:" + status);
            final int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv != 0) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$jiynJ7rY8rHt0Tu3-C6gXZJ5OQY
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).notifyBtOobPairComplete(mac, status, srv);
                    }
                }, srv);
            }
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$ogyGJIZZ_EHAK9Ey0X1f9J2xuJI
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).notifyBtOobPairComplete(mac, status);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onWifiApInfo(final WifiConfigInfo config, final int status) {
            Log.d(CmService.TAG, "onWifiApInfo, status:" + status);
            if (CmService.this.mCmsListenerManager.isAvailablePmService(1)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$5aBGWvGKTZuL2i19KB3QNmyWBsY
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onWifiApInfo(config, status, 1);
                    }
                }, 1);
            }
            if (CmService.this.mCmsListenerManager.isAvailablePmService(3)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$GjNZdnnWRVpaKrHQwUS4LHbSmJc
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onWifiApInfo(config, status, 3);
                    }
                }, 3);
            }
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$dwHfBsdydDAMM58CNvDJx205R6Y
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onWifiApInfo(config, status, 0);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onWifiConnectStatus(final Device device, final int status) {
            Log.d(CmService.TAG, "onWifiConnectStatus, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", status:" + status);
            List<Integer> srvTypes = CmService.this.mConfigManager.getProjectionReadyCase();
            Iterator<Integer> it = srvTypes.iterator();
            while (it.hasNext()) {
                final int srvType = it.next().intValue();
                if (CmService.this.mCmsListenerManager.isAvailablePmService(srvType)) {
                    CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$TUFE70k6diGfxBodxFCA2IF9dOo
                        @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                        public final void accept(Object obj) throws RemoteException {
                            IPcmListener iPcmListener = (IPcmListener) obj;
                            iPcmListener.onWifiConnectStatus(device.getWifiAddr(), status, srvType);
                        }
                    }, srvType);
                } else {
                    Log.d(CmService.TAG, "onWifiConnectStatus, srvType:" + srvType + " is unavailable");
                }
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onShowProjection(final int service, final boolean flag) {
            Log.e(CmService.TAG, "onShowProjection, service:" + service + ", flag:" + flag);
            int srv = CmService.this.mProjectionManager.getActiveProjectionService();
            if (srv == service) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$PCE_NnRSNPPcI5nPX4MyjtD5FSI
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onShowProjection(flag, service);
                    }
                }, service);
            }
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onWirelessCarplayDetected(final String mac, final int status) {
            Log.d(CmService.TAG, "onWirelessCarplayDetected, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", state:" + status);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$Qk3-je-KkVUniTtbIhL4JCzeDxg
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onWirelessCarplayDetected(mac, status);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onWirelessProjectionFound(final Device device) {
            Log.e(CmService.TAG, "onWirelessProjectionFound, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$6wKfCZG4m1lwPg-HCulxmePHQIM
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onWirelessProjectionFound(device.getConnDevice());
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceAttach(final Device device, final int connType) {
            Log.d(CmService.TAG, "onDeviceAttach, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", connType:" + connType);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$ACkRJie46uZydyrPCfPp1dfUhVI
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IDmListener iDmListener = (IDmListener) obj;
                    iDmListener.onDeviceAttach(device.getConnDevice(), connType);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceDetach(final Device device, final int connType) {
            Log.d(CmService.TAG, "onDeviceDetach, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()) + ", connType:" + connType);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$Y31KplQ1C0PvKDNeLvfmz65_svE
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    IDmListener iDmListener = (IDmListener) obj;
                    iDmListener.onDeviceDetach(device.getConnDevice(), connType);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onProjectionServiceReady() {
            Log.e(CmService.TAG, "onProjectionServiceReady.");
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$bMb3PK_3wrXx-bBUSuAGcG80Cdc
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onProjectionServiceReady();
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onProjectionServiceStatus(int serviceType, boolean status) {
            Log.e(CmService.TAG, "onProjectionServiceStatus, serviceType: " + serviceType + ", status: " + status);
            CmService.this.mCmsListenerManager.broadcastProjectionServiceStatus(serviceType, status);
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onAbnormalDeviceStatusChanged(final AbnormalDevice device, final int status) {
            Log.d(CmService.TAG, "onAbnormalDeviceStatusChanged, devId:" + LogEncrypt.IpAddressEncrypt(device.getDeviceId()) + ", status:" + status);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$EOMY4534V2htpVQSMk6EFW8j1bI
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onAbnormalDeviceStatusChanged(device, status);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onCheckUsbDeviceName(final Device device, final int srv) {
            Log.d(CmService.TAG, "onCheckUsbDeviceName, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
            if (CmService.this.mCmsListenerManager.isAvailablePmService(srv)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$RZnO7P4YoQays6vTkDgaSajrAU8
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        IPcmListener iPcmListener = (IPcmListener) obj;
                        iPcmListener.onCheckUsbDeviceName(device.getCmDevice(), srv);
                    }
                }, srv);
                return true;
            }
            Log.e(CmService.TAG, "onCheckUsbDeviceName, projection service was not registered. service:" + srv);
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean onDisconnectIapSession(final String devId, final int srv) {
            Log.d(CmService.TAG, "onDisconnectIapSession, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", srv:" + srv);
            if (CmService.this.mCmsListenerManager.isAvailablePmService(srv)) {
                CmService.this.mCmsListenerManager.broadcast2PcmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$vIxN2mEXtmq7Zeby5BmL_Zj76lc
                    @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                    public final void accept(Object obj) throws RemoteException {
                        ((IPcmListener) obj).onDisconnectIapSession(devId, srv);
                    }
                }, srv);
                return true;
            }
            Log.e(CmService.TAG, "onDisconnectIapSession, projection service was not registered. service:" + srv);
            return false;
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public void onDeviceCapabilityChecked(final String devId, final int capability, final boolean isSupport, final int reason) {
            Log.d(CmService.TAG, "onDeviceCapabilityChecked, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capability" + capability + ", isSupport:" + isSupport + ", reason:" + reason);
            CmService.this.mCmsListenerManager.broadcast2DmListener(new DcmConsumer.ReConsumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ConnectionManagerCallback$N-ocRLhLeORz-KmXufY3Tusu7l0
                @Override // com.ts.car.dcm.common.data.DcmConsumer.ReConsumer
                public final void accept(Object obj) throws RemoteException {
                    ((IDmListener) obj).onDeviceCapabilityChecked(devId, capability, isSupport, reason);
                }
            });
        }

        @Override // com.ts.car.dcm.dccontroller.connectionmanagerservice.ICmsCallback
        public boolean isAvailablePmService(int serviceType) {
            return CmService.this.mCmsListenerManager.isAvailablePmService(serviceType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ICmCommandstub extends IConnectionManagementService.Stub {
        private ICmCommandstub() {
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void addDmCallback(IDmListener cb, int objectType) throws RemoteException {
            CmService.this.mCmsListenerManager.registerDmListener(cb, objectType);
            List<Integer> srvTypes = CmService.this.mConfigManager.getProjectionReadyCase();
            Iterator<Integer> it = srvTypes.iterator();
            while (it.hasNext()) {
                int srvType = it.next().intValue();
                if (CmService.this.mCmsListenerManager.isAvailablePmService(srvType)) {
                    CmService.this.mCmsCallback.onProjectionServiceStatus(srvType, true);
                }
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void addPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
            CmService.this.mCmsListenerManager.registerPcmListener(cb, services);
            if (services.contains(String.valueOf(1)) && CmService.this.mCmsListenerManager.isAvailablePmService(1)) {
                CmService.this.mProjectionManager.onProjectionServiceRegister(1, true);
            }
            for (String service : services) {
                int srvType = Integer.parseInt(service);
                if (CmsConstants.ServiceType.isAvailableType(srvType)) {
                    CmService.this.mCmsCallback.onProjectionServiceStatus(srvType, true);
                } else {
                    Log.e(CmService.TAG, "addPcmCallback, unknown service:" + service);
                }
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void rmDmCallback(IDmListener cb, int objectType) throws RemoteException {
            CmService.this.mCmsListenerManager.unregisterDmListener(cb, objectType);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void rmPcmCallback(IPcmListener cb, List<String> services) throws RemoteException {
            CmService.this.mCmsListenerManager.unregisterPcmListener(cb, services);
            if (services.contains(String.valueOf(1)) && !CmService.this.mCmsListenerManager.isAvailablePmService(1)) {
                CmService.this.mProjectionManager.onProjectionServiceRegister(1, false);
            }
            for (String service : services) {
                int srvType = Integer.parseInt(service);
                if (CmsConstants.ServiceType.isAvailableType(srvType)) {
                    CmService.this.mCmsCallback.onProjectionServiceStatus(srvType, false);
                } else {
                    Log.e(CmService.TAG, "rmPcmCallback, unknown service:" + service);
                }
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectDevice(String devId, int type) {
            Log.d(CmService.TAG, "connectDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", type:" + type);
            return CmService.this.mDcController.connectDevice(devId, type);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectDevice(String devId, int type) {
            Log.d(CmService.TAG, "disconnectDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", type:" + type);
            return CmService.this.mDcController.disconnectDevice(devId, type);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean updateDeviceFriendlyName(String devId, String name) {
            int result = CmService.this.mDeviceManager.updateDeviceFriendlyName(devId, name);
            if (result == 0) {
                Device dev = CmService.this.mDeviceManager.getDevice(devId);
                CmService.this.mCmsCallback.onDeviceStatusChanged(dev);
                return true;
            }
            Log.d(CmService.TAG, "updateDeviceFriendlyName, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", name:" + name + ", result:" + result);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setLastDevice(String devId) {
            Device deviceOld = CmService.this.mDeviceManager.getLastDevice();
            int result = CmService.this.mDeviceManager.setLastDevice(devId);
            if (result == 0) {
                if (deviceOld != null) {
                    CmService.this.mCmsCallback.onDeviceStatusChanged(deviceOld);
                }
                Device lastDev = CmService.this.mDeviceManager.getLastDevice();
                CmService.this.mCmsCallback.onDeviceStatusChanged(lastDev);
            }
            Log.d(CmService.TAG, "setLastDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", result:" + result);
            return result == 0;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getLastDevice() {
            Device device = CmService.this.mDeviceManager.getLastDevice();
            if (device == null) {
                return null;
            }
            return device.getConnDevice();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setFavoritePhone(String devId) {
            Device deviceOld = CmService.this.mDeviceManager.getFavoritePhone();
            int result = CmService.this.mDeviceManager.setFavoritePhone(devId);
            if (result == 0) {
                if (deviceOld != null) {
                    CmService.this.mCmsCallback.onDeviceStatusChanged(deviceOld);
                }
                Device favDev = CmService.this.mDeviceManager.getFavoritePhone();
                CmService.this.mCmsCallback.onDeviceStatusChanged(favDev);
            }
            Log.d(CmService.TAG, "setFavoritePhone, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", result:" + result);
            return result == 0;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getFavoritePhone() {
            Device device = CmService.this.mDeviceManager.getFavoritePhone();
            if (device == null) {
                return null;
            }
            return device.getConnDevice();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean isProjectionSupport(int type) {
            if (type == 1) {
                return CmService.this.mConfigManager.isSupportCarPlay();
            }
            if (type == 2) {
                return CmService.this.mConfigManager.isSupportCarLife();
            }
            if (type == 3) {
                return CmService.this.mConfigManager.isSupportAndroidAuto();
            }
            Log.d(CmService.TAG, "isProjectionSupport, unknown type:" + type);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean isEnableProjection(int type) {
            if (type == 1) {
                return CmService.this.mConfigManager.isEnableCarPlay();
            }
            if (type == 2) {
                return CmService.this.mConfigManager.isEnableCarLife();
            }
            if (type == 3) {
                return CmService.this.mConfigManager.isEnableAndroidAuto();
            }
            Log.d(CmService.TAG, "isEnableProjection, unknown type:" + type);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableProjection(int type, boolean flag) {
            if (!isProjectionSupport(type)) {
                Log.e(CmService.TAG, "enableProjection, type " + type + " not support");
                return false;
            }
            Log.e(CmService.TAG, "enableProjection, type:" + type + ", flag:" + flag);
            boolean resultCode = false;
            int lastCapa = CmService.this.mConfigManager.getPlatformCapabilitys();
            if (type == 1) {
                resultCode = CmService.this.mConfigManager.setEnableCarPlay(flag);
            } else if (type == 2) {
                resultCode = CmService.this.mConfigManager.setEnableCarLife(flag);
            } else if (type == 3) {
                resultCode = CmService.this.mConfigManager.setEnableAndroidAuto(flag);
            } else {
                Log.e(CmService.TAG, "enableProjection, unknown projection type:" + type);
            }
            if (resultCode) {
                int nowCapa = CmService.this.mConfigManager.getPlatformCapabilitys();
                if (lastCapa != nowCapa) {
                    CmService.this.mDcController.setPlatformCapability(nowCapa);
                }
                return true;
            }
            Log.e(CmService.TAG, "enableProjection, failed. type:" + type + ", flag:" + flag + ", resultCode:" + resultCode);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableProjectionByDevType(int serviceType, int deviceType, boolean flag) throws RemoteException {
            Log.e(CmService.TAG, "enableProjectionByDevType, serviceType:" + serviceType + ", deviceType:" + deviceType + ", flag:" + flag);
            int resultCode = 1;
            if (2 != serviceType) {
                return false;
            }
            if (deviceType == 1) {
                resultCode = CmService.this.mConfigManager.setEnableAndroidCarlife(flag);
            } else if (deviceType == 2) {
                resultCode = CmService.this.mConfigManager.setEnableIosCarlife(flag);
            }
            if (resultCode == 0) {
                CmService.this.mDcController.updateCarlifeSupportStatus(deviceType, flag);
                return true;
            }
            Log.e(CmService.TAG, "enableProjectionByDevType, failed. type:, flag:" + flag + ", resultCode:" + resultCode);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean scanWirelessProjection(boolean enable) {
            Log.d(CmService.TAG, "scanWirelessProjection, enable:" + enable);
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void getDevices(Bundle devices) {
            CmService.this.mDeviceManager.getConnDevices(devices);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void getOnlineDevices(Bundle devices) {
            CmService.this.mDeviceManager.getOnlineDevices(devices);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteDevice(String devId) {
            Log.d(CmService.TAG, "deleteDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId));
            Device device = CmService.this.mDeviceManager.getDevice(devId);
            if (device != null) {
                Log.e(CmService.TAG, "deleteDevice, set disconnect by user action flag.");
                device.setDisconnectByUserAction(true);
                if (!CmService.this.mDcController.removeDevice(device)) {
                    return false;
                }
                if (device.hasCapability(576)) {
                    CmService.this.mCmsCallback.onDeleteProjectionData(device, 1);
                }
                if (device.hasCapability(2304)) {
                    CmService.this.mCmsCallback.onDeleteProjectionData(device, 2);
                }
                if (device.hasCapability(1152)) {
                    CmService.this.mCmsCallback.onDeleteProjectionData(device, 3);
                }
                if (device.hasCapability(8)) {
                    CmService.this.mCmsCallback.onDeleteProjectionData(device, 4);
                }
                return true;
            }
            Log.d(CmService.TAG, "deleteDevice, invalid devId:" + LogEncrypt.IpAddressEncrypt(devId));
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteAllDevices(final IConnectionManagementServiceCallback cb) {
            Log.e(CmService.TAG, "deleteAllDevice, thId:" + Thread.currentThread().getId());
            CmService.this.mHandler.post(new Runnable() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ICmCommandstub$PCSugokR_jkWQURsHvgZDJQZUAo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$deleteAllDevices$1$CmService$ICmCommandstub(cb);
                }
            });
            return true;
        }

        public /* synthetic */ void lambda$deleteAllDevices$1$CmService$ICmCommandstub(IConnectionManagementServiceCallback cb) {
            Map<String, Device> deleteAll = CmService.this.mDeviceManager.getDeviceMap();
            if (deleteAll != null) {
                deleteAll.values().forEach(new Consumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ICmCommandstub$97_H5D7iKQQpGFxTgakoIP3yheM
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$deleteAllDevices$0$CmService$ICmCommandstub((Device) obj);
                    }
                });
                if (cb != null) {
                    try {
                        cb.onFinish(0);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        public /* synthetic */ void lambda$deleteAllDevices$0$CmService$ICmCommandstub(Device l) {
            if (!deleteDevice(l.getId())) {
                Log.d(CmService.TAG, "deleteAllDevice, delete device failed. devId:" + l.getId());
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean deleteProjectionData(String devId, int service) {
            Device device = CmService.this.mDeviceManager.getDevice(devId);
            if (device == null) {
                Log.d(CmService.TAG, "deleteProjectionData, invalid devId:" + LogEncrypt.IpAddressEncrypt(devId));
                return false;
            }
            if (!device.isProjectionFirstConnecting(service)) {
                device.setProjectionFirstConnStatus(service, true);
                CmService.this.mDeviceManager.saveDevice(device);
            }
            return CmService.this.mCmsCallback.onDeleteProjectionData(device, service);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<String> getProjectionPriorityList() {
            return CmService.this.mConfigManager.getProjectionPriority();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setProjectionPriorityList(List<String> list) {
            Log.d(CmService.TAG, "setProjectionPriorityList, list:" + list);
            return CmService.this.mConfigManager.setProjectionPriority(list);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public int connectProjection(ProjectionLinkParameter plp) {
            Device device;
            int result = 5;
            if (plp == null) {
                Log.e(CmService.TAG, "connectProjection, the plp is null.");
                return 5;
            }
            Log.e(CmService.TAG, "connectProjection, plp:" + plp);
            if (CmsConstants.Capability.isProjectionCapability(plp.getCapability()) && (device = CmService.this.mDeviceManager.getDevice(plp.getDevId())) != null) {
                if (CmService.this.mConfigManager.isSupportServiceByCapability(plp.getCapability())) {
                    if (512 == plp.getCapability() && !device.isCpwlOverIapDetectedNotified() && device.isPaired()) {
                        Log.d(CmService.TAG, "connectProjection, notify Wireless Carplay enable, Btmac:" + LogEncrypt.MacAddressEncrypt(device.getBtAddr()));
                        CmService.this.mCmsCallback.onWirelessCarplayDetected(device.getBtAddr(), 1);
                        device.setCpwlOverIapDetectedNotified(true);
                    }
                    result = CmService.this.mProjectionManager.connectProjection(device, plp);
                    if (result == 0) {
                        if (plp.getAlways()) {
                            device.setSaveAlways(plp.getAlways());
                            device.setAlwaysCapability(plp.getCapability());
                            CmService.this.mDeviceManager.saveDevice(device);
                            CmService.this.mCmsCallback.onDeviceStatusChanged(device);
                        }
                        device.setQuickReconnectCapability(plp.getCapability());
                    }
                } else {
                    Log.e(CmService.TAG, "connectProjection, no enable service, capability:" + plp.getCapability());
                    return 7;
                }
            }
            Log.e(CmService.TAG, "connectProjection, result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectProjection(String devId, int capa, int reason) {
            boolean result = false;
            if (devId != null && CmsConstants.Capability.isProjectionCapability(capa)) {
                Device device = CmService.this.mDeviceManager.getDevice(devId);
                if (device != null) {
                    Log.e(CmService.TAG, "disconnectProjection, set disconnect by user action flag.");
                    device.setDisconnectByUserAction(true);
                }
                result = CmService.this.mProjectionManager.disconnectProjection(devId, capa, reason);
            }
            Log.d(CmService.TAG, "disconnectProjection, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa + ", reason:" + reason + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<CmDevice> getProjectionDevices(int service) {
            Log.d(CmService.TAG, "getProjectionDevices, service:" + service);
            if (CmsConstants.ServiceType.isAvailableType(service)) {
                return CmService.this.mDeviceManager.getCmDeviceList(service);
            }
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateProjectionConnectionStatus(String devId, int capa, int status, int reason) {
            if (devId == null || !CmsConstants.Capability.isProjectionCapability(capa)) {
                Log.d(CmService.TAG, "updateProjectionConnectionStatus, invalid parameter. devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa);
                return;
            }
            Log.d(CmService.TAG, "updateProjectionConnectionStatus, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", service:" + capa + ", status:" + status + ", reason:" + reason);
            CmService.this.mProjectionManager.updateProjectionConnectionStatus(devId, capa, status, reason);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean doUsbRoleSwitch(String devId, int mode, int purpose) {
            boolean result = false;
            if (mode != 1 && mode != 0) {
                return false;
            }
            if (devId != null) {
                result = CmService.this.mDcController.doUsbRoleSwitch(devId, mode, purpose);
            }
            Log.d(CmService.TAG, "doUsbRoleSwitch, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mode:" + mode + ", purpose:" + purpose + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean resetUsb(String devId) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mDcController.resetUsb(devId);
            }
            Log.d(CmService.TAG, "resetUsb, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableUsbAoa(String devId, int mode, boolean flag) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mDcController.enableUsbAoA(devId, mode, flag);
            }
            Log.d(CmService.TAG, "enableUsbAoa, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mode:" + mode + ", flag:" + flag + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableUsbAoaForAndroidAuto(String devId, String serialNumber, int mode, boolean flag) {
            boolean result = false;
            if (devId != null && serialNumber != null) {
                result = CmService.this.mDcController.enableUsbAoA(devId, serialNumber, mode, flag);
            }
            Log.d(CmService.TAG, "enableUsbAoa, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", serialNumber:" + LogEncrypt.IpAddressEncrypt(serialNumber) + ", mode:" + mode + ", flag:" + flag + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectBtDevice(String devId, String mac) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mProjectionManager.disconnectBtDevice(devId, mac);
            }
            Log.d(CmService.TAG, "disconnectBtDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectOtherBtDevices(String devId, String mac) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mProjectionManager.disconnectOtherBtDevices(devId, mac);
            }
            Log.d(CmService.TAG, "disconnectOtherBtDevices, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean enableBluetooth(boolean flag) {
            Log.d(CmService.TAG, "enableBluetooth, flag:" + flag);
            return CmService.this.mProjectionManager.enableBluetooth(flag);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectBtDevice(String devId, String mac) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mProjectionManager.connectBtDevice(devId, mac);
            }
            Log.d(CmService.TAG, "connectBtDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean connectBtProfile(String devId, String mac, int profile) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mProjectionManager.connectBtProfile(devId, mac, profile);
            }
            Log.d(CmService.TAG, "connectBtProfile, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean disconnectBtProfile(String devId, String mac, int profile) {
            boolean result = false;
            if (devId != null) {
                result = CmService.this.mProjectionManager.disconnectBtProfile(devId, mac, profile);
            }
            Log.d(CmService.TAG, "disconnectBtProfile, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", profile:" + profile + ", result:" + result);
            return result;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public BtOobInfo getBtOobInfo() {
            return CmService.this.mDcController.getBtOobInfo();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) {
            Log.d(CmService.TAG, "setBtOobInfo, name:" + name + ", linkKey:" + linkKey + ", mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", linkKeyType:" + linkKey);
            CmService.this.mDcController.setBtOobInfo(name, linkKey, mac, linkKeyType);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean mergeDevices(String devId, String key, int keyType) {
            Log.d(CmService.TAG, "mergeDevices, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", key:" + key + ", keyType:" + keyType);
            if (devId != null && key != null && !devId.isEmpty() && !key.isEmpty() && !devId.equals(key) && CmsConstants.MergeKeyType.isAvailableType(keyType)) {
                return CmService.this.mDcController.mergeDevices(devId, key, keyType);
            }
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean mergeDevicesByUsbAndBt(String usb, String bt) {
            return CmService.this.mDcController.mergeDevices(usb, bt);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void enableWifiAp(boolean enable, WifiApConfiguration config) {
            boolean result = CmService.this.mDcController.enableWifiAp(enable, config);
            Log.e(CmService.TAG, "enableWifiAp, enable:" + enable + ", result:" + result);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateProjectionCapability(String mac, int capability, boolean flag, int reason) throws RemoteException {
            Device device;
            Log.d(CmService.TAG, "updateProjectionCapability, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", capability:" + capability + ", flag:" + flag + ", reason:" + reason);
            if (!flag && capability == 512 && (device = CmService.this.mDeviceManager.getDeviceByConntype(2, mac)) != null && !device.isCpwlOverIapDetectedNotified()) {
                CmService.this.mCmsCallback.onWirelessCarplayDetected(device.getBtAddr(), 0);
                device.setCpwlOverIapDetectedNotified(true);
            }
            CmService.this.mProjectionManager.updateProjectionCapability(mac, capability, flag, reason);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setAlwaysProjection(String devId, int capa, boolean always) throws RemoteException {
            Log.d(CmService.TAG, "setAlwaysProjection, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capa:" + capa + ", always:" + always);
            Device device = CmService.this.mDeviceManager.getDevice(devId);
            if (device != null) {
                device.setAlwaysCapability(capa);
                device.setSaveAlways(always);
                if (always) {
                    CmService.this.mDeviceManager.saveDevice(device);
                }
                CmService.this.mCmsCallback.onDeviceStatusChanged(device);
                return true;
            }
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setAlwaysProjectionByDevType(final int devType, final int capa, final boolean always) throws RemoteException {
            Map<String, Device> devices = CmService.this.mDeviceManager.getDeviceMap();
            if (devices != null) {
                devices.values().forEach(new Consumer() { // from class: com.ts.car.dcm.dccontroller.connectionmanagerservice.-$$Lambda$CmService$ICmCommandstub$-1n6wX_3QeqOBO8tpOjYKcQ2jN4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$setAlwaysProjectionByDevType$2$CmService$ICmCommandstub(devType, capa, always, (Device) obj);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$setAlwaysProjectionByDevType$2$CmService$ICmCommandstub(int devType, int capa, boolean always, Device dev) {
            if (dev.getDeviceType() == devType && dev.getAlwaysCapability() != 0 && dev.getAlwaysCapability() != capa) {
                Log.e(CmService.TAG, "setAlwaysProjectionByDevType, devType:" + devType + ", capa:" + capa + ", always:" + always);
                dev.setAlwaysCapability(capa);
                dev.setSaveAlways(always);
                if (always) {
                    CmService.this.mDeviceManager.saveDevice(dev);
                }
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<ConnDevice> getActiveProjections() throws RemoteException {
            return CmService.this.mProjectionManager.getProjectionDevices();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean cancelWirelessCarplay(String devId) throws RemoteException {
            Device device = CmService.this.mDeviceManager.getDevice(devId);
            if (device != null && device.getBtAddr() != null) {
                Log.d(CmService.TAG, "cancelWirelessCarplay, notify Wireless Carplay canceled. devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", Btmac:" + LogEncrypt.MacAddressEncrypt(device.getBtAddr()));
                if (!device.isCpwlOverIapDetectedNotified()) {
                    CmService.this.mCmsCallback.onWirelessCarplayDetected(device.getBtAddr(), 2);
                    device.setCpwlOverIapDetectedNotified(true);
                    CmService.this.mCmsCallback.onIap2BtDeviceConnected(device, 3);
                }
                return true;
            }
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public ConnDevice getDevice(String devId) throws RemoteException {
            Log.d(CmService.TAG, "getDevice, devId:" + LogEncrypt.IpAddressEncrypt(devId));
            Device device = CmService.this.mDeviceManager.getDevice(devId);
            if (device != null) {
                return device.getConnDevice();
            }
            return null;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean setDeviceConnectionDetection(boolean on, boolean persistent) throws RemoteException {
            if (!CmService.this.mConfigManager.isEnableDeviceDetectionControl()) {
                Log.e(CmService.TAG, "setDeviceConnectionDetection, not allowed to be set.");
                return false;
            }
            Log.e(CmService.TAG, "setDeviceConnectionDetection, on:" + on + ", persistent:" + persistent);
            boolean oldResult = CmService.this.mConfigManager.isDeviceDetectionAllowed();
            if (!CmService.this.mConfigManager.setDeviceConnectionDetection(on, persistent)) {
                return false;
            }
            if (on && !oldResult) {
                CmService.this.mDcController.startConnectionMonitor();
                return true;
            }
            if (!on && oldResult) {
                CmService.this.mDcController.stopConnectionMonitor();
                return true;
            }
            return true;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public List<AbnormalDevice> getAbnormalDeviceList() throws RemoteException {
            return CmService.this.mDeviceManager.getAbnormalDeviceList();
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean updateUsbDeviceName(String devId, String name) throws RemoteException {
            int result = CmService.this.mDeviceManager.updateDeviceFriendlyName(devId, name);
            Device dev = CmService.this.mDeviceManager.getDevice(devId);
            if (result == 0) {
                CmService.this.mCmsCallback.onDeviceStatusChanged(dev);
            }
            Log.d(CmService.TAG, "updateUsbDeviceName, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", name:" + name + ", result:" + result);
            if (dev != null && dev.isAvailableCapability(64)) {
                CmService.this.mProjectionManager.notifyUserSelectionProjection(dev, 1);
            }
            if (result == 0) {
                return true;
            }
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void updateIapSessionState(String id, int state, int reason) throws RemoteException {
            Device device = CmService.this.mDeviceManager.getDeviceByConntype(1, id);
            if (device == null) {
                Log.d(CmService.TAG, "updateIapSessionState, device is null. usb serial:" + LogEncrypt.IpAddressEncrypt(id));
                return;
            }
            if (state == 1) {
                Log.d(CmService.TAG, "updateIapSessionState, iap session connected, devId:" + LogEncrypt.IpAddressEncrypt(device.getId()));
                return;
            }
            if (state == 2) {
                Log.d(CmService.TAG, "updateIapSessionState, iap session disconnected, devId:" + device.getId());
                CmService.this.mProjectionManager.handleIapSessionDisconnected(device);
            }
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public boolean checkDeviceCapability(String devId, int capability) throws RemoteException {
            Log.d(CmService.TAG, "checkDeviceCapability, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", capability:" + capability);
            if (capability == 512) {
                return CmService.this.mProjectionManager.checkWirelessCarplayCapability(devId, 4);
            }
            return false;
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public int getSupportedWifiChannel() {
            return CmService.this.mConfigManager.getWifiApChannel(2, 3);
        }

        @Override // ts.car.dcm.common.aidl.IConnectionManagementService
        public void setMobileDeviceId(String devId, String mobileDeviceId) {
            Device dev = CmService.this.mDeviceManager.getDevice(devId);
            if (dev == null) {
                Log.e(CmService.TAG, "setMobileDeviceId, device is null");
            } else {
                dev.setMobileDeviceId(mobileDeviceId);
                CmService.this.mDeviceManager.saveDevice(dev);
            }
        }
    }
}
