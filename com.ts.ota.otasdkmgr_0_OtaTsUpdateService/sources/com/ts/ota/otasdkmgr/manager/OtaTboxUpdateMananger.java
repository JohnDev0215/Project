package com.ts.ota.otasdkmgr.manager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.RemoteCallbackList;
import android.util.Log;
import android.util.Pools;
import com.byd.ota.master.interfaces.ITBOXUpdateManager;
import com.byd.ota.master.listener.UpdateBaseListener;
import com.engine.tbox.TboxManager;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.DigitalKeyBean;
import com.engine.tbox.data.NetworkFlow;
import com.engine.tbox.data.NetworkOperator;
import com.engine.tbox.data.NetworkQuery;
import com.engine.tbox.data.OtaDeleteNotification;
import com.engine.tbox.data.OtaFactoryNotification;
import com.engine.tbox.data.OtaNotification;
import com.engine.tbox.data.OtaPhoneOtaNotification;
import com.engine.tbox.data.TboxStaticInfo;
import com.engine.tbox.iface.ITboxListener;
import com.ts.ota.otasdkmgr.DigitalKeyBeanSdk;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.IAbsMessageCallback;
import com.ts.ota.otasdkmgr.IOtaTsUpdateServiceProxy;
import com.ts.ota.otasdkmgr.ITestCallback;
import com.ts.ota.otasdkmgr.NetworkOperatorAidl;
import com.ts.ota.otasdkmgr.TestInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class OtaTboxUpdateMananger implements ITBOXUpdateManager, OtaUpdateManager {
    private static final int NUM_1 = 1;
    private static final int OTA_NOTIFY_TYPE = 1;
    private static final String TAG = "OtaTboxUpdateMananger";
    private static OtaTboxUpdateMananger tboxManager;
    private UpdateBaseListener exportLogListener;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private Pools.SynchronizedPool<Bundle> otaNtyPool;
    private IOtaTsUpdateServiceProxy proxy;
    private TboxManager tboxMgr = null;
    private UpdateBaseListener updateBaseListener = null;
    private TboxListener tboxListener = new TboxListener();
    private RemoteCallbackList<IAbsMessageCallback> absMsgCallback = new RemoteCallbackList<>();
    private RemoteCallbackList<IAbsBYDTBoxListener> appActivateListener = new RemoteCallbackList<>();
    private RemoteCallbackList<ITestCallback> mTestCallback = new RemoteCallbackList<>();
    private RemoteCallbackList<IAbsBYDTBoxListener> mTboxCallback = new RemoteCallbackList<>();
    private TestInfo mTestInfo = new TestInfo();
    private final Object mOtaNtyMutex = new Object();
    private final int TBOX_STATE_CONNECTED = 0;
    private final int TBOX_STATE_DISCONNECTED = 1;

    public static synchronized OtaTboxUpdateMananger getInstance(Context mContext, IOtaTsUpdateServiceProxy proxy) {
        if (tboxManager == null) {
            tboxManager = new OtaTboxUpdateMananger(mContext, proxy);
        }
        return tboxManager;
    }

    private OtaTboxUpdateMananger(Context mContext, IOtaTsUpdateServiceProxy proxy) {
        this.mContext = null;
        this.proxy = null;
        Log.w(TAG, "OtaTboxUpdateMananger create mContext is " + mContext + ", proxy is " + proxy);
        this.mContext = mContext;
        this.proxy = proxy;
        create();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.otaNtyPool = new Pools.SynchronizedPool<>(5);
    }

    public void tboxUpgrade(String s, UpdateBaseListener updateBaseListener, String... strings) {
        Log.w(TAG, "tboxUpgrade updateBaseListener = " + updateBaseListener);
        this.updateBaseListener = updateBaseListener;
        checkManager("tboxUpgrade");
        boolean start = this.tboxMgr.upgrade(s);
        Log.w(TAG, "tboxUpgrade: start is " + start);
    }

    public String tboxGetSWCode(String... strings) {
        checkManager("tboxGetSWCode");
        String softcode = this.tboxMgr.getSoftCode();
        Log.w(TAG, "tboxGetSWCode: iccid is " + softcode);
        return softcode;
    }

    public String tboxGetSWVer(String... strings) {
        checkManager("tboxGetSWVer");
        TboxStaticInfo tboxStaticInfo = this.tboxMgr.getTboxStaticInfo();
        if (tboxStaticInfo == null) {
            Log.e(TAG, "tboxGetSWVer: tboxStaticInfo is null , no value");
            return null;
        }
        String softVersion = tboxStaticInfo.getTboxSoftVersion();
        Log.w(TAG, "tboxGetSWVer: softVersion is " + softVersion);
        return softVersion;
    }

    public String tboxGetHWVer(String... strings) {
        checkManager("tboxGetHWVer");
        TboxStaticInfo tboxStaticInfo = this.tboxMgr.getTboxStaticInfo();
        if (tboxStaticInfo == null) {
            Log.e(TAG, "tboxGetHWVer: tboxStaticInfo is null , no value");
            return null;
        }
        String hardVersion = tboxStaticInfo.getTboxHardVersion();
        Log.w(TAG, "tboxGetHWVer: hardVersion is " + hardVersion);
        return hardVersion;
    }

    public void tboxGetLog(String... strings) {
        if (strings == null) {
            Log.e(TAG, "tboxGetLog strings is null");
            return;
        }
        if (strings.length <= 0) {
            Log.e(TAG, "tboxGetLog strings length less 0");
            return;
        }
        String path = strings[0];
        checkManager("tboxGetLog");
        boolean status = this.tboxMgr.exportLog(path);
        Log.e(TAG, "tboxGetLog: path is " + path + ", status is " + status);
    }

    @Override // com.ts.ota.otasdkmgr.manager.OtaUpdateManager
    public void create() {
        Log.e(TAG, "create");
        IOtaTsUpdateServiceProxy iOtaTsUpdateServiceProxy = this.proxy;
        if (iOtaTsUpdateServiceProxy != null) {
            this.tboxMgr = iOtaTsUpdateServiceProxy.getTboxManager();
        } else {
            this.tboxMgr = TboxManager.getInstance(this.mContext);
        }
        this.tboxMgr.registerListener(this.tboxListener);
        this.tboxMgr.start();
    }

    @Override // com.ts.ota.otasdkmgr.manager.OtaUpdateManager
    public void destroy() {
        checkManager("destroy");
        this.tboxMgr.unregisterListener();
    }

    private void checkManager(String methodName) {
        if (this.tboxMgr == null) {
            Log.e(TAG, methodName + ": tboxMgr is null , will reassign");
            create();
            Log.e(TAG, methodName + ": reassign manager is " + this.tboxMgr);
        }
    }

    public void registerExportLogListener(UpdateBaseListener listener) {
        this.exportLogListener = listener;
    }

    public void otaNotificationCallback(IAbsMessageCallback callback) {
        synchronized (this.mOtaNtyMutex) {
            this.absMsgCallback.register(callback);
            if (this.otaNtyPool == null) {
                Log.e(TAG, "otaNotificationCallback: otaNtyPool is null");
                return;
            }
            final Bundle bundle = (Bundle) this.otaNtyPool.acquire();
            if (bundle != null) {
                final int otaType = bundle.getInt("type");
                Log.w(TAG, "otaNotificationCallback: has cached object,callback now! OtaType:" + otaType);
                this.otaNtyPool.release(bundle);
                this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int size = OtaTboxUpdateMananger.this.absMsgCallback.beginBroadcast();
                        for (int i = 0; i < size; i++) {
                            try {
                                ((IAbsMessageCallback) OtaTboxUpdateMananger.this.absMsgCallback.getBroadcastItem(i)).onCloudServiceListener(otaType, bundle);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        OtaTboxUpdateMananger.this.absMsgCallback.finishBroadcast();
                    }
                });
            } else {
                Log.w(TAG, "otaNotificationCallback: otaNtyPool is empty");
            }
        }
    }

    public void unregisterOtaNtyCallback(IAbsMessageCallback callback) {
        this.absMsgCallback.unregister(callback);
    }

    public void registerTboxListener(IAbsBYDTBoxListener listener) {
        this.mTboxCallback.register(listener);
    }

    public void appActivateCallback(IAbsBYDTBoxListener listener) {
        this.appActivateListener.register(listener);
    }

    public void unregisterTboxListener(IAbsBYDTBoxListener listener) {
        this.mTboxCallback.unregister(listener);
    }

    public void registerTextTboxListener(ITestCallback callback) {
        this.mTestCallback.register(callback);
    }

    public void unregisterTextTboxListener(ITestCallback callback) {
        this.mTestCallback.unregister(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFactoryBundle(Bundle bundle, OtaFactoryNotification otaFacNty) {
        int upgradeType = otaFacNty.getUpgradeType();
        int minSoc = otaFacNty.getMinSoc();
        int minVoltage = otaFacNty.getMinVoltage();
        int timeOut = otaFacNty.getTimeout();
        String uuid = otaFacNty.getUuidString();
        bundle.putInt("silence", upgradeType);
        bundle.putInt("soc", minSoc);
        bundle.putInt("voltage", minVoltage);
        bundle.putInt("overtime", timeOut);
        bundle.putString("uuid", uuid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createDelPkgBundle(Bundle bundle, OtaDeleteNotification otaDelNty) {
        int deleteType = otaDelNty.getDelete();
        String uuid = otaDelNty.getUuidString();
        bundle.putInt("deleteType", deleteType);
        bundle.putString("uuid", uuid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createPhoneOtaBundle(Bundle bundle, OtaPhoneOtaNotification otaPhoneNty) {
        int updateType = otaPhoneNty.getUpgradeType();
        long timestamp = otaPhoneNty.getTimestamp();
        String uuid = otaPhoneNty.getUuidString();
        bundle.putInt("updateType", updateType);
        bundle.putLong("timestamp", timestamp);
        bundle.putString("uuid", uuid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class TboxListener implements ITboxListener {
        private TboxListener() {
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void upgradeResult(int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "upgradeResult  result is " + result);
            if (OtaTboxUpdateMananger.this.updateBaseListener != null) {
                OtaTboxUpdateMananger.this.updateBaseListener.onTBOXUpgradeResult(result);
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "upgradeResult updateBaseListener  is null");
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void upgradeProgress(int progress) {
            Log.w(OtaTboxUpdateMananger.TAG, "upgradeResult  progress is " + progress);
            if (OtaTboxUpdateMananger.this.updateBaseListener != null) {
                OtaTboxUpdateMananger.this.updateBaseListener.onTboxUpdateProgress(progress);
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "upgradeProgress updateBaseListener  is null");
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void tboxConnectState(int tboxState) {
            Log.w(OtaTboxUpdateMananger.TAG, "tboxConnectState  tboxState is " + tboxState);
            if (OtaTboxUpdateMananger.this.updateBaseListener != null) {
                OtaTboxUpdateMananger.this.updateBaseListener.onTBOXConnectState(tboxState);
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "tboxConnectState updateBaseListener  is null");
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void exportLogProgress(int progress) {
            Log.w(OtaTboxUpdateMananger.TAG, "exportLogProgress  progress is " + progress);
            if (OtaTboxUpdateMananger.this.exportLogListener != null) {
                OtaTboxUpdateMananger.this.exportLogListener.onTboxExportLogProgress(progress);
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "exportLogProgress updateBaseListener is null");
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void exportLogResult(int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "exportLogResult  result is " + result);
            if (OtaTboxUpdateMananger.this.exportLogListener != null) {
                OtaTboxUpdateMananger.this.exportLogListener.onTboxExportLogResult(result);
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "exportLogResult updateBaseListener is null");
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void otaNotifcaticationCallback(OtaNotification nty) {
            Log.w(OtaTboxUpdateMananger.TAG, "onCloudServiceListener  nty is " + nty + "! details : " + nty.toString());
            if (OtaTboxUpdateMananger.this.absMsgCallback == null) {
                Log.e(OtaTboxUpdateMananger.TAG, "onCloudServiceListener absMsgCallback is null");
                return;
            }
            final int otaType = nty.getType();
            final Bundle bundle = new Bundle();
            bundle.putInt("type", otaType);
            if (otaType != 1) {
                if (otaType != 2) {
                    if (otaType == 3) {
                        if (nty.getPhoneNty() != null) {
                            OtaTboxUpdateMananger.this.createPhoneOtaBundle(bundle, nty.getPhoneNty());
                        } else {
                            Log.e(OtaTboxUpdateMananger.TAG, "OtaNotification.getPhoneNty() is null");
                            return;
                        }
                    } else {
                        Log.e(OtaTboxUpdateMananger.TAG, "No such otaType : " + otaType);
                        return;
                    }
                } else if (nty.getDeleteNty() != null) {
                    OtaTboxUpdateMananger.this.createDelPkgBundle(bundle, nty.getDeleteNty());
                } else {
                    Log.e(OtaTboxUpdateMananger.TAG, "OtaNotification.getDeleteNty() is null");
                    return;
                }
            } else if (nty.getFactoryNty() != null) {
                OtaTboxUpdateMananger.this.createFactoryBundle(bundle, nty.getFactoryNty());
            } else {
                Log.e(OtaTboxUpdateMananger.TAG, "OtaNotification.getFactoryNty() is null");
                return;
            }
            synchronized (OtaTboxUpdateMananger.this.mOtaNtyMutex) {
                OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.TboxListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int size = OtaTboxUpdateMananger.this.absMsgCallback.beginBroadcast();
                        if (size == 0) {
                            Log.w(OtaTboxUpdateMananger.TAG, "onCloudServiceListener: register not yet,recycle the object");
                            OtaTboxUpdateMananger.this.otaNtyPool.release(bundle);
                            OtaTboxUpdateMananger.this.absMsgCallback.finishBroadcast();
                            return;
                        }
                        for (int i = 0; i < size; i++) {
                            try {
                                ((IAbsMessageCallback) OtaTboxUpdateMananger.this.absMsgCallback.getBroadcastItem(i)).onCloudServiceListener(otaType, bundle);
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        OtaTboxUpdateMananger.this.absMsgCallback.finishBroadcast();
                    }
                });
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void appActivityResult(final int apptag, final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "appActiveResult result is " + result + " appTag is " + apptag);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.TboxListener.2
                @Override // java.lang.Runnable
                public void run() {
                    int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onAppActivateResult(result, apptag);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
                }
            });
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onTboxRestartChanged(final int state) {
            Log.w(OtaTboxUpdateMananger.TAG, "onTboxRestartChanged state is " + state);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.TboxListener.3
                @Override // java.lang.Runnable
                public void run() {
                    int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onTboxRestartChanged(state);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
                }
            });
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onNetworkOperatorList(final ArrayList<String> codeList) {
            Log.w(OtaTboxUpdateMananger.TAG, "onNetworkOperatorList codeList is " + codeList);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.TboxListener.4
                @Override // java.lang.Runnable
                public void run() {
                    OtaTboxUpdateMananger.this.mTestInfo.setData(codeList);
                    int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
                    for (int i = 0; i < size; i++) {
                        try {
                            ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onNetworkOperatorList(OtaTboxUpdateMananger.this.mTestInfo);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                    OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
                }
            });
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void networkTrafficeReqCallback(NetworkQuery query) {
            Log.w(OtaTboxUpdateMananger.TAG, "networkTrafficeReqCallback query is " + query.toNumber());
            if (query.toNumber() == 1) {
                OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.OtaTboxUpdateMananger.TboxListener.5
                    @Override // java.lang.Runnable
                    public void run() {
                        int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
                        for (int i = 0; i < size; i++) {
                            try {
                                ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onUpdateTrafficData();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                        OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
                    }
                });
            }
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onKeyListResponse(final int total, ArrayList<DigitalKeyBean> beans) {
            Log.w(OtaTboxUpdateMananger.TAG, "onKeyListResponse.beans->" + beans);
            final List<DigitalKeyBeanSdk> digitalKeyBeanList = new ArrayList<>();
            for (DigitalKeyBean digitalKeyBean : beans) {
                DigitalKeyBeanSdk digitalKeyBeanSdk = new DigitalKeyBeanSdk();
                digitalKeyBeanSdk.init(digitalKeyBean.generateForOta());
                digitalKeyBeanList.add(digitalKeyBeanSdk);
            }
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$Rs85JOumz6-RbnFuSZOVFldIpHI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onKeyListResponse$0$OtaTboxUpdateMananger$TboxListener(total, digitalKeyBeanList);
                }
            });
        }

        public /* synthetic */ void lambda$onKeyListResponse$0$OtaTboxUpdateMananger$TboxListener(int total, List digitalKeyBeanList) {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onKeyListResponse(total, digitalKeyBeanList);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onDeleteNfcKeyResult(final int op, final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "onDeleteNfcKeyResult: op->" + op + " result->" + result);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$z4-hmyI72XTW5VNFBGT0fu-OLQg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeleteNfcKeyResult$1$OtaTboxUpdateMananger$TboxListener(op, result);
                }
            });
        }

        public /* synthetic */ void lambda$onDeleteNfcKeyResult$1$OtaTboxUpdateMananger$TboxListener(int op, int result) {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onDeleteNfcKeyResult(op, result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onTBoxCmd2Ivi(final int cmd) {
            Log.w(OtaTboxUpdateMananger.TAG, "onTBoxCmd2Ivi: cmd " + cmd);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$vvtLqrV6z_KnirrmSPJgVZOe_Bc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTBoxCmd2Ivi$2$OtaTboxUpdateMananger$TboxListener(cmd);
                }
            });
        }

        public /* synthetic */ void lambda$onTBoxCmd2Ivi$2$OtaTboxUpdateMananger$TboxListener(int cmd) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onTboxCmd2Ivi(cmd);
                } catch (Exception exception) {
                    Log.e(OtaTboxUpdateMananger.TAG, "onTBoxCmd2Ivi: " + exception);
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void switchCloudEnvCallback(final int env) {
            Log.w(OtaTboxUpdateMananger.TAG, "onCloudServiceConnectStateChanged: env " + env);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$IZ48_M-g37aPBx6PrbiweN7Zj_Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$switchCloudEnvCallback$3$OtaTboxUpdateMananger$TboxListener(env);
                }
            });
        }

        public /* synthetic */ void lambda$switchCloudEnvCallback$3$OtaTboxUpdateMananger$TboxListener(int env) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onCloudServiceConnectStateChanged(env);
                } catch (Exception exception) {
                    Log.e(OtaTboxUpdateMananger.TAG, "onCloudServiceConnectStateChanged: " + exception);
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onDeleteAllNfcKeyResult(final int type, final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "onDeleteAllNfcKeyResult: type->" + type + " result->" + result);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$esCdvGOITkQqRrSQkWhdaqdmamY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeleteAllNfcKeyResult$4$OtaTboxUpdateMananger$TboxListener(type, result);
                }
            });
        }

        public /* synthetic */ void lambda$onDeleteAllNfcKeyResult$4$OtaTboxUpdateMananger$TboxListener(int type, int result) {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onDeleteAllNfcKeyResult(type, result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onDeleteNfcKeyNoByKeyNoResult(final String keyNo, final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "onDeleteNfcKeyByKeyNoResult: keyNo->" + keyNo + " result->" + result);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$OQ1FnpVsyUkyVmTMfH83zxvSVKI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeleteNfcKeyNoByKeyNoResult$5$OtaTboxUpdateMananger$TboxListener(keyNo, result);
                }
            });
        }

        public /* synthetic */ void lambda$onDeleteNfcKeyNoByKeyNoResult$5$OtaTboxUpdateMananger$TboxListener(String keyNo, int result) {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onDeleteNfcKeyByKeyNoResult(keyNo, result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onDeleteOwnIphoneKeyResult(final String keyNo, final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "onDeleteOwnIphoneKeyResult: keyNo->" + keyNo + " result->" + result);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$nH9dgTdVrAUPEfmv-PjgLyFYsl0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDeleteOwnIphoneKeyResult$6$OtaTboxUpdateMananger$TboxListener(keyNo, result);
                }
            });
        }

        public /* synthetic */ void lambda$onDeleteOwnIphoneKeyResult$6$OtaTboxUpdateMananger$TboxListener(String keyNo, int result) {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onDeleteOwnIphoneKeyResult(keyNo, result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onKeyListPageRefresh() {
            Log.w(OtaTboxUpdateMananger.TAG, "onKeyListPageRefresh");
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$xMu5NWkf466tK_P8IThMAKElqdM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onKeyListPageRefresh$7$OtaTboxUpdateMananger$TboxListener();
                }
            });
        }

        public /* synthetic */ void lambda$onKeyListPageRefresh$7$OtaTboxUpdateMananger$TboxListener() {
            int size = OtaTboxUpdateMananger.this.mTboxCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((IAbsBYDTBoxListener) OtaTboxUpdateMananger.this.mTboxCallback.getBroadcastItem(i)).onKeyListPageRefresh();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTboxCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onSimCardStateChange(final int status) {
            Log.w(OtaTboxUpdateMananger.TAG, "onSimCardStateChange: status->" + status);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$Hd9FhNO6HVP0EoKRiGE229N_J8g
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSimCardStateChange$8$OtaTboxUpdateMananger$TboxListener(status);
                }
            });
        }

        public /* synthetic */ void lambda$onSimCardStateChange$8$OtaTboxUpdateMananger$TboxListener(int status) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onSimCardStateChange(status);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onAutomaticNetworkChange(final int state) {
            Log.w(OtaTboxUpdateMananger.TAG, "onAutomaticNetworkChange: state->" + state);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$TfMdSn3ZRaBbqTHGU9vog8cPBpM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onAutomaticNetworkChange$9$OtaTboxUpdateMananger$TboxListener(state);
                }
            });
        }

        public /* synthetic */ void lambda$onAutomaticNetworkChange$9$OtaTboxUpdateMananger$TboxListener(int state) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onAutomaticNetworkChange(state);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onManuallyStationedNetworkChange(final String mccMnc, final int curNetType, final int state, final String lastMccMnc, final int lastNetType) {
            Log.w(OtaTboxUpdateMananger.TAG, "onManuallyStationedNetworkChange: mccMnc->" + mccMnc + "curNetType->" + curNetType + " state->" + state + " lastMccMnc->" + lastMccMnc + " lastNetType->" + lastNetType);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$U2v_zEE-4GDNKsKqfYqU5Ym5LXs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onManuallyStationedNetworkChange$10$OtaTboxUpdateMananger$TboxListener(mccMnc, curNetType, state, lastMccMnc, lastNetType);
                }
            });
        }

        public /* synthetic */ void lambda$onManuallyStationedNetworkChange$10$OtaTboxUpdateMananger$TboxListener(String mccMnc, int curNetType, int state, String lastMccMnc, int lastNetType) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onManuallyStationedNetworkChange(mccMnc, curNetType, state, lastMccMnc, lastNetType);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onOperatorInfoChange(ArrayList<NetworkOperator> list) {
            Log.w(OtaTboxUpdateMananger.TAG, "onOperatorInfoChange: list->" + list);
            final ArrayList<NetworkOperatorAidl> aidlList = getNetworkOperatorAidls(list);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$FgV9SJdOeX6cSAmFwWphOfMWtcs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onOperatorInfoChange$11$OtaTboxUpdateMananger$TboxListener(aidlList);
                }
            });
        }

        public /* synthetic */ void lambda$onOperatorInfoChange$11$OtaTboxUpdateMananger$TboxListener(ArrayList aidlList) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onOperatorInfoChange(aidlList);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        private ArrayList<NetworkOperatorAidl> getNetworkOperatorAidls(ArrayList<NetworkOperator> list) {
            ArrayList<NetworkOperatorAidl> aidlList = new ArrayList<>();
            for (NetworkOperator networkOperator : list) {
                NetworkOperatorAidl networkOperatorAidl = new NetworkOperatorAidl();
                networkOperatorAidl.setEnable(networkOperator.getEnable());
                networkOperatorAidl.setMccMnc(networkOperator.getMccMnc());
                networkOperatorAidl.setSigLevel(networkOperator.getSigLevel());
                aidlList.add(networkOperatorAidl);
            }
            return aidlList;
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onNetworkOperatorModeChange(final int state) {
            Log.w(OtaTboxUpdateMananger.TAG, "onNetworkOperatorModeChange: state->" + state);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$hNpWRXdak063jtRbcqPC_TAxlXA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onNetworkOperatorModeChange$12$OtaTboxUpdateMananger$TboxListener(state);
                }
            });
        }

        public /* synthetic */ void lambda$onNetworkOperatorModeChange$12$OtaTboxUpdateMananger$TboxListener(int state) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onNetworkOperatorModeChange(state);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void sendApnArgsResult(final int result) {
            Log.w(OtaTboxUpdateMananger.TAG, "sendApnArgsResult <-> onSyncApnParamResult");
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$EwgW4zrOMtZP0u08z6fpt_Xg71k
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$sendApnArgsResult$13$OtaTboxUpdateMananger$TboxListener(result);
                }
            });
        }

        public /* synthetic */ void lambda$sendApnArgsResult$13$OtaTboxUpdateMananger$TboxListener(int result) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onSyncApnParamResult(result);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onApnArgsNty(final ApnArgs args) {
            Log.w(OtaTboxUpdateMananger.TAG, "onApnArgsNty <-> onGetApnParamFromTbox");
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$RjZGg6NicBB9CNxzrftlljDOmzA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onApnArgsNty$14$OtaTboxUpdateMananger$TboxListener(args);
                }
            });
        }

        public /* synthetic */ void lambda$onApnArgsNty$14$OtaTboxUpdateMananger$TboxListener(ApnArgs args) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onGetApnParamFromTbox(args.getApnName(), args.getUser(), args.getPass(), String.valueOf(args.getApnType()), String.valueOf(args.getApnProtocol()), args.getProxy(), args.getPort());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onNetworkFlowNty(final NetworkFlow flow) {
            Log.w(OtaTboxUpdateMananger.TAG, "onNetworkFlowNty <-> onGetTrafficFromTbox");
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$BCaTCYIUVsWMnDH26Y2NG-ibSlI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onNetworkFlowNty$15$OtaTboxUpdateMananger$TboxListener(flow);
                }
            });
        }

        public /* synthetic */ void lambda$onNetworkFlowNty$15$OtaTboxUpdateMananger$TboxListener(NetworkFlow flow) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onGetTrafficFromTbox(flow.getApnType(), flow.getIccid(), flow.getEndTime(), Long.parseLong(flow.getUploadToday()), Long.parseLong(flow.getDownloadToday()), Long.parseLong(flow.getUploadMonth()), Long.parseLong(flow.getDownloadMonth()));
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }

        @Override // com.engine.tbox.iface.ITboxListener
        public void onTboxNetworkState(final int apn, final int state) {
            Log.w(OtaTboxUpdateMananger.TAG, "onTboxNetworkState <-> onGetTBoxNetworkState apn : " + apn + ", state : " + state);
            OtaTboxUpdateMananger.this.mHandler.post(new Runnable() { // from class: com.ts.ota.otasdkmgr.manager.-$$Lambda$OtaTboxUpdateMananger$TboxListener$z1BCrljZUgShwjMZrZnOzq1pVXg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTboxNetworkState$16$OtaTboxUpdateMananger$TboxListener(apn, state);
                }
            });
        }

        public /* synthetic */ void lambda$onTboxNetworkState$16$OtaTboxUpdateMananger$TboxListener(int apn, int state) {
            int size = OtaTboxUpdateMananger.this.mTestCallback.beginBroadcast();
            for (int i = 0; i < size; i++) {
                try {
                    ((ITestCallback) OtaTboxUpdateMananger.this.mTestCallback.getBroadcastItem(i)).onGetTboxNetworkState(apn, state);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            OtaTboxUpdateMananger.this.mTestCallback.finishBroadcast();
        }
    }
}
