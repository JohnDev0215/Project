package com.android.networkstack.tethering;

import android.app.usage.NetworkStatsManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothPan;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.EthernetManager;
import android.net.IIntResultListener;
import android.net.INetd;
import android.net.ITetheringEventCallback;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TetherStatesParcel;
import android.net.TetheredClient;
import android.net.TetheringCallbackStartedParcel;
import android.net.TetheringConfigurationParcel;
import android.net.TetheringRequestParcel;
import android.net.ip.IpServer;
import android.net.shared.NetdUtils;
import android.net.util.BaseNetdUnsolicitedEventListener;
import android.net.util.InterfaceSet;
import android.net.util.PrefixUtils;
import android.net.util.SharedLog;
import android.net.util.TetheringUtils;
import android.net.util.VersionedBroadcastListener;
import android.net.wifi.WifiClient;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.ServiceSpecificException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import com.android.networkstack.tethering.BpfCoordinator;
import com.android.networkstack.tethering.EntitlementManager;
import com.android.networkstack.tethering.OffloadController;
import com.android.networkstack.tethering.util.IndentingPrintWriter;
import com.android.networkstack.tethering.util.MessageUtils;
import com.android.networkstack.tethering.util.State;
import com.android.networkstack.tethering.util.StateMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class Tethering {
    private static final String TAG = "Tethering";
    private static final SparseArray<String> sMagicDecoderRing;
    private static final Class[] sMessageClasses;
    private final ActiveDataSubIdListener mActiveDataSubIdListener;
    private final BpfCoordinator mBpfCoordinator;
    private final VersionedBroadcastListener mCarrierConfigChange;
    private volatile TetheringConfiguration mConfig;
    private String mConfiguredEthernetIface;
    private final ConnectedClientsTracker mConnectedClientsTracker;
    private final Context mContext;
    private InterfaceSet mCurrentUpstreamIfaceSet;
    private final TetheringDependencies mDeps;
    private final EntitlementManager mEntitlementMgr;
    private EthernetCallback mEthernetCallback;
    private EthernetManager.TetheredInterfaceRequest mEthernetIfaceRequest;
    private final TetheringThreadExecutor mExecutor;
    private final LinkedHashSet<IpServer> mForwardedDownstreams;
    private final Handler mHandler;
    private final Looper mLooper;
    private final INetd mNetd;
    private final NetdCallback mNetdCallback;
    private final TetheringNotificationUpdater mNotificationUpdater;
    private final OffloadController mOffloadController;
    private final PrivateAddressCoordinator mPrivateAddressCoordinator;
    private final Object mPublicSync;
    private boolean mRndisEnabled;
    private final BroadcastReceiver mStateReceiver;
    private final StateMachine mTetherMasterSM;
    private final ArrayMap<String, TetherState> mTetherStates;
    private TetherStatesParcel mTetherStatesParcel;
    private Network mTetherUpstream;
    private final UserRestrictionActionListener mTetheringRestriction;
    private final UpstreamNetworkMonitor mUpstreamNetworkMonitor;
    private final UserManager mUserManager;
    private boolean mWifiTetherRequested;
    private final SharedLog mLog = new SharedLog(TAG);
    private final RemoteCallbackList<ITetheringEventCallback> mTetheringEventCallbacks = new RemoteCallbackList<>();
    private final SparseArray<TetheringRequestParcel> mActiveTetheringRequests = new SparseArray<>();
    private int mActiveDataSubId = -1;
    private boolean mDataSaverEnabled = false;
    private String mWifiP2pTetherInterface = null;
    private int mOffloadStatus = 0;

    static {
        Class[] clsArr = {Tethering.class, TetherMasterSM.class, IpServer.class};
        sMessageClasses = clsArr;
        sMagicDecoderRing = MessageUtils.findMessageNames(clsArr);
    }

    private static class TetherState {
        public final IpServer ipServer;
        public int lastState = 1;
        public int lastError = 0;

        TetherState(IpServer ipServer) {
            this.ipServer = ipServer;
        }

        public boolean isCurrentlyServing() {
            int i = this.lastState;
            return i == 2 || i == 3;
        }
    }

    private static class CallbackCookie {
        public final boolean hasListClientsPermission;

        private CallbackCookie(boolean z) {
            this.hasListClientsPermission = z;
        }
    }

    public Tethering(TetheringDependencies tetheringDependencies) {
        this.mLog.mark("Tethering.constructed");
        this.mDeps = tetheringDependencies;
        Context context = tetheringDependencies.getContext();
        this.mContext = context;
        this.mNetd = this.mDeps.getINetd(context);
        Looper tetheringLooper = this.mDeps.getTetheringLooper();
        this.mLooper = tetheringLooper;
        this.mNotificationUpdater = this.mDeps.getNotificationUpdater(this.mContext, tetheringLooper);
        this.mPublicSync = new Object();
        this.mTetherStates = new ArrayMap<>();
        this.mConnectedClientsTracker = new ConnectedClientsTracker();
        TetherMasterSM tetherMasterSM = new TetherMasterSM("TetherMaster", this.mLooper, tetheringDependencies);
        this.mTetherMasterSM = tetherMasterSM;
        tetherMasterSM.start();
        Handler handler = this.mTetherMasterSM.getHandler();
        this.mHandler = handler;
        this.mOffloadController = this.mDeps.getOffloadController(handler, this.mLog, new OffloadController.Dependencies() { // from class: com.android.networkstack.tethering.Tethering.1
            @Override // com.android.networkstack.tethering.OffloadController.Dependencies
            public TetheringConfiguration getTetherConfig() {
                return Tethering.this.mConfig;
            }
        });
        this.mUpstreamNetworkMonitor = this.mDeps.getUpstreamNetworkMonitor(this.mContext, this.mTetherMasterSM, this.mLog, 5);
        this.mForwardedDownstreams = new LinkedHashSet<>();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        EntitlementManager entitlementManager = this.mDeps.getEntitlementManager(this.mContext, this.mHandler, this.mLog, new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$kFbDg5fumH6uWvWOX-JoDKX9Zq4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$Tethering();
            }
        });
        this.mEntitlementMgr = entitlementManager;
        entitlementManager.setOnUiEntitlementFailedListener(new EntitlementManager.OnUiEntitlementFailedListener() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$QFNaW1CjiNjZ1tucwWM4UmgI89c
            @Override // com.android.networkstack.tethering.EntitlementManager.OnUiEntitlementFailedListener
            public final void onUiEntitlementFailed(int i) {
                this.f$0.lambda$new$1$Tethering(i);
            }
        });
        this.mEntitlementMgr.setTetheringConfigurationFetcher(new EntitlementManager.TetheringConfigurationFetcher() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$8-Ib-Vl0fbKc9GA57Tv_pqRMDOs
            @Override // com.android.networkstack.tethering.EntitlementManager.TetheringConfigurationFetcher
            public final TetheringConfiguration fetchTetheringConfiguration() {
                return this.f$0.lambda$new$2$Tethering();
            }
        });
        this.mCarrierConfigChange = new VersionedBroadcastListener("CarrierConfigChangeListener", this.mContext, this.mHandler, intentFilter, new Consumer() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$bXlvpAMLdjY8zDVZz31dr37qjos
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$new$3$Tethering((Intent) obj);
            }
        });
        this.mStateReceiver = new StateReceiver();
        UserManager userManager = (UserManager) this.mContext.getSystemService("user");
        this.mUserManager = userManager;
        this.mTetheringRestriction = new UserRestrictionActionListener(userManager, this, this.mNotificationUpdater);
        this.mExecutor = new TetheringThreadExecutor(this, this.mHandler);
        this.mActiveDataSubIdListener = new ActiveDataSubIdListener(this.mExecutor);
        this.mNetdCallback = new NetdCallback();
        this.mPrivateAddressCoordinator = new PrivateAddressCoordinator(this.mContext);
        updateConfiguration();
        this.mBpfCoordinator = this.mDeps.getBpfCoordinator(new BpfCoordinator.Dependencies() { // from class: com.android.networkstack.tethering.Tethering.2
            @Override // com.android.networkstack.tethering.BpfCoordinator.Dependencies
            public Handler getHandler() {
                return Tethering.this.mHandler;
            }

            @Override // com.android.networkstack.tethering.BpfCoordinator.Dependencies
            public INetd getNetd() {
                return Tethering.this.mNetd;
            }

            @Override // com.android.networkstack.tethering.BpfCoordinator.Dependencies
            public NetworkStatsManager getNetworkStatsManager() {
                return (NetworkStatsManager) Tethering.this.mContext.getSystemService(NetworkStatsManager.class);
            }

            @Override // com.android.networkstack.tethering.BpfCoordinator.Dependencies
            public SharedLog getSharedLog() {
                return Tethering.this.mLog;
            }

            @Override // com.android.networkstack.tethering.BpfCoordinator.Dependencies
            public TetheringConfiguration getTetherConfig() {
                return Tethering.this.mConfig;
            }
        });
        startStateMachineUpdaters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$Tethering() {
        this.mTetherMasterSM.sendMessage(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$Tethering(int i) {
        this.mLog.log("OBSERVED UiEnitlementFailed");
        stopTethering(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ TetheringConfiguration lambda$new$2$Tethering() {
        return this.mConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$3$Tethering(Intent intent) {
        this.mLog.log("OBSERVED carrier config change");
        updateConfiguration();
        this.mEntitlementMgr.reevaluateSimCardProvisioning(this.mConfig);
    }

    private void startStateMachineUpdaters() {
        try {
            this.mNetd.registerUnsolicitedEventListener(this.mNetdCallback);
        } catch (RemoteException unused) {
            this.mLog.e("Unable to register netd UnsolicitedEventListener");
        }
        this.mCarrierConfigChange.startListening();
        ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).listen(this.mActiveDataSubIdListener, 4194304);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_STATE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.os.action.USER_RESTRICTIONS_CHANGED");
        intentFilter.addAction("android.net.conn.RESTRICT_BACKGROUND_CHANGED");
        this.mContext.registerReceiver(this.mStateReceiver, intentFilter, null, this.mHandler);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.android.server.connectivity.tethering.DISABLE_TETHERING");
        this.mContext.registerReceiver(this.mStateReceiver, intentFilter2, "android.permission.MAINLINE_NETWORK_STACK", this.mHandler);
        WifiManager wifiManager = getWifiManager();
        if (wifiManager != null) {
            wifiManager.registerSoftApCallback(this.mExecutor, new TetheringSoftApCallback());
        }
        startTrackDefaultNetwork();
    }

    private class TetheringThreadExecutor implements Executor {
        private final Handler mTetherHandler;

        TetheringThreadExecutor(Tethering tethering, Handler handler) {
            this.mTetherHandler = handler;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (this.mTetherHandler.post(runnable)) {
                return;
            }
            throw new RejectedExecutionException(this.mTetherHandler + " is shutting down");
        }
    }

    private class ActiveDataSubIdListener extends PhoneStateListener {
        ActiveDataSubIdListener(Executor executor) {
            super(executor);
        }

        @Override // android.telephony.PhoneStateListener
        public void onActiveDataSubscriptionIdChanged(int i) {
            Tethering.this.mLog.log("OBSERVED active data subscription change, from " + Tethering.this.mActiveDataSubId + " to " + i);
            if (i == Tethering.this.mActiveDataSubId) {
                return;
            }
            Tethering.this.mActiveDataSubId = i;
            Tethering.this.updateConfiguration();
            Tethering.this.mNotificationUpdater.onActiveDataSubscriptionIdChanged(i);
            if (Tethering.this.mEntitlementMgr.getCarrierConfig(Tethering.this.mConfig) != null) {
                Tethering.this.mEntitlementMgr.reevaluateSimCardProvisioning(Tethering.this.mConfig);
            } else {
                Tethering.this.mLog.log("IGNORED reevaluate provisioning, no carrier config loaded");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiManager getWifiManager() {
        return (WifiManager) this.mContext.getSystemService("wifi");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConfiguration() {
        this.mConfig = this.mDeps.generateTetheringConfiguration(this.mContext, this.mLog, this.mActiveDataSubId);
        this.mUpstreamNetworkMonitor.updateMobileRequiresDun(this.mConfig.isDunRequired);
        reportConfigurationChanged(this.mConfig.toStableParcelable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeDunSettingChanged() {
        if (TetheringConfiguration.checkDunRequired(this.mContext) == this.mConfig.isDunRequired) {
            return;
        }
        updateConfiguration();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class NetdCallback extends BaseNetdUnsolicitedEventListener {
        private NetdCallback() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onInterfaceChanged$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onInterfaceChanged$0$Tethering$NetdCallback(String str, boolean z) {
            Tethering.this.interfaceStatusChanged(str, z);
        }

        @Override // android.net.INetdUnsolicitedEventListener
        public void onInterfaceChanged(final String str, final boolean z) {
            Tethering.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$NetdCallback$9EMToFiBFk1XuFac_6siBCknizM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInterfaceChanged$0$Tethering$NetdCallback(str, z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onInterfaceLinkStateChanged$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onInterfaceLinkStateChanged$1$Tethering$NetdCallback(String str, boolean z) {
            Tethering.this.interfaceLinkStateChanged(str, z);
        }

        @Override // android.net.INetdUnsolicitedEventListener
        public void onInterfaceLinkStateChanged(final String str, final boolean z) {
            Tethering.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$NetdCallback$qioUsjppIUILOvZaWfcg8daqCUI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInterfaceLinkStateChanged$1$Tethering$NetdCallback(str, z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onInterfaceAdded$2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onInterfaceAdded$2$Tethering$NetdCallback(String str) {
            Tethering.this.interfaceAdded(str);
        }

        @Override // android.net.INetdUnsolicitedEventListener
        public void onInterfaceAdded(final String str) {
            Tethering.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$NetdCallback$w5cU4M7bVmpOdkxlaGVqVWmPUpo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInterfaceAdded$2$Tethering$NetdCallback(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onInterfaceRemoved$3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onInterfaceRemoved$3$Tethering$NetdCallback(String str) {
            Tethering.this.interfaceRemoved(str);
        }

        @Override // android.net.INetdUnsolicitedEventListener
        public void onInterfaceRemoved(final String str) {
            Tethering.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$NetdCallback$4yJM5VqssdV7NB9Z2GTDoZgJSuU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInterfaceRemoved$3$Tethering$NetdCallback(str);
                }
            });
        }
    }

    private class TetheringSoftApCallback implements WifiManager.SoftApCallback {
        public void onStateChanged(int i, int i2) {
        }

        private TetheringSoftApCallback() {
        }

        public void onConnectedClientsChanged(List<WifiClient> list) {
            Tethering.this.updateConnectedClients(list);
        }
    }

    void interfaceStatusChanged(String str, boolean z) {
        synchronized (this.mPublicSync) {
            if (z) {
                maybeTrackNewInterfaceLocked(str);
            } else if (ifaceNameToType(str) == 2 || ifaceNameToType(str) == 6) {
                stopTrackingInterfaceLocked(str);
            }
        }
    }

    void interfaceLinkStateChanged(String str, boolean z) {
        interfaceStatusChanged(str, z);
    }

    private int ifaceNameToType(String str) {
        TetheringConfiguration tetheringConfiguration = this.mConfig;
        if (tetheringConfiguration.isWifi(str)) {
            return 0;
        }
        if (tetheringConfiguration.isWigig(str)) {
            return 6;
        }
        if (tetheringConfiguration.isWifiP2p(str)) {
            return 3;
        }
        if (tetheringConfiguration.isUsb(str)) {
            return 1;
        }
        if (tetheringConfiguration.isBluetooth(str)) {
            return 2;
        }
        return tetheringConfiguration.isNcm(str) ? 4 : -1;
    }

    void interfaceAdded(String str) {
        synchronized (this.mPublicSync) {
            maybeTrackNewInterfaceLocked(str);
        }
    }

    void interfaceRemoved(String str) {
        synchronized (this.mPublicSync) {
            stopTrackingInterfaceLocked(str);
        }
    }

    void startTethering(final TetheringRequestParcel tetheringRequestParcel, final IIntResultListener iIntResultListener) {
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$Fm5pFNINSlkx4_WZtZinHgNIvls
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startTethering$4$Tethering(tetheringRequestParcel, iIntResultListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$startTethering$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$startTethering$4$Tethering(TetheringRequestParcel tetheringRequestParcel, IIntResultListener iIntResultListener) {
        TetheringRequestParcel tetheringRequestParcel2 = this.mActiveTetheringRequests.get(tetheringRequestParcel.tetheringType);
        if (tetheringRequestParcel2 != null && !TetheringUtils.isTetheringRequestEquals(tetheringRequestParcel2, tetheringRequestParcel)) {
            enableTetheringInternal(tetheringRequestParcel.tetheringType, false, null);
            this.mEntitlementMgr.stopProvisioningIfNeeded(tetheringRequestParcel.tetheringType);
        }
        this.mActiveTetheringRequests.put(tetheringRequestParcel.tetheringType, tetheringRequestParcel);
        if (tetheringRequestParcel.exemptFromEntitlementCheck) {
            this.mEntitlementMgr.setExemptedDownstreamType(tetheringRequestParcel.tetheringType);
        } else {
            this.mEntitlementMgr.startProvisioningIfNeeded(tetheringRequestParcel.tetheringType, tetheringRequestParcel.showProvisioningUi);
        }
        enableTetheringInternal(tetheringRequestParcel.tetheringType, true, iIntResultListener);
    }

    void stopTethering(final int i) {
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$Ow2XCVxK4blS8jtJvaKHvDXGQfk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$stopTethering$5$Tethering(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$stopTethering$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$stopTethering$5$Tethering(int i) {
        this.mActiveTetheringRequests.remove(i);
        enableTetheringInternal(i, false, null);
        this.mEntitlementMgr.stopProvisioningIfNeeded(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableTetheringInternal(int i, boolean z, IIntResultListener iIntResultListener) {
        int wifiTethering;
        if (i == 0) {
            wifiTethering = setWifiTethering(z);
        } else if (i == 1) {
            wifiTethering = setUsbTethering(z);
        } else if (i == 2) {
            setBluetoothTethering(z, iIntResultListener);
            wifiTethering = 0;
        } else if (i == 4) {
            wifiTethering = setNcmTethering(z);
        } else if (i == 5) {
            wifiTethering = setEthernetTethering(z);
        } else {
            Log.w(TAG, "Invalid tether type.");
            wifiTethering = 16;
        }
        if (i != 2) {
            sendTetherResult(iIntResultListener, wifiTethering, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTetherResult(IIntResultListener iIntResultListener, int i, int i2) {
        if (iIntResultListener != null) {
            try {
                iIntResultListener.onResult(i);
            } catch (RemoteException unused) {
            }
        }
        if (i != 0) {
            this.mActiveTetheringRequests.remove(i2);
        }
    }

    private int setWifiTethering(boolean z) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            synchronized (this.mPublicSync) {
                WifiManager wifiManager = getWifiManager();
                if (wifiManager == null) {
                    this.mLog.e("setWifiTethering: failed to get WifiManager!");
                    return 2;
                }
                if (!(z && wifiManager.startTetheredHotspot(null)) && (z || !wifiManager.stopSoftAp())) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return 5;
                }
                this.mWifiTetherRequested = z;
                return 0;
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private void setBluetoothTethering(final boolean z, final IIntResultListener iIntResultListener) {
        final BluetoothAdapter bluetoothAdapter = this.mDeps.getBluetoothAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Tried to enable bluetooth tethering with null or disabled adapter. null: ");
            sb.append(bluetoothAdapter == null);
            Log.w(str, sb.toString());
            sendTetherResult(iIntResultListener, 2, 2);
            return;
        }
        bluetoothAdapter.getProfileProxy(this.mContext, new BluetoothProfile.ServiceListener() { // from class: com.android.networkstack.tethering.Tethering.3
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    ((BluetoothPan) bluetoothProfile).setBluetoothTethering(z);
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Tethering.this.sendTetherResult(iIntResultListener, ((BluetoothPan) bluetoothProfile).isTetheringOn() == z ? 0 : 5, 2);
                    bluetoothAdapter.closeProfileProxy(5, bluetoothProfile);
                } catch (Throwable th) {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    throw th;
                }
            }
        }, 5);
    }

    private int setEthernetTethering(boolean z) {
        EthernetManager ethernetManager = (EthernetManager) this.mContext.getSystemService("ethernet");
        synchronized (this.mPublicSync) {
            if (z) {
                if (this.mEthernetCallback != null) {
                    Log.d(TAG, "Ethernet tethering already started");
                    return 0;
                }
                EthernetCallback ethernetCallback = new EthernetCallback();
                this.mEthernetCallback = ethernetCallback;
                this.mEthernetIfaceRequest = ethernetManager.requestTetheredInterface(this.mExecutor, ethernetCallback);
            } else {
                stopEthernetTetheringLocked();
            }
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopEthernetTetheringLocked() {
        String str = this.mConfiguredEthernetIface;
        if (str != null) {
            stopTrackingInterfaceLocked(str);
            this.mConfiguredEthernetIface = null;
        }
        if (this.mEthernetCallback != null) {
            this.mEthernetIfaceRequest.release();
            this.mEthernetCallback = null;
            this.mEthernetIfaceRequest = null;
        }
    }

    private class EthernetCallback implements EthernetManager.TetheredInterfaceCallback {
        private EthernetCallback() {
        }

        public void onAvailable(String str) {
            synchronized (Tethering.this.mPublicSync) {
                if (this != Tethering.this.mEthernetCallback) {
                    return;
                }
                Tethering.this.maybeTrackNewInterfaceLocked(str, 5);
                Tethering.this.changeInterfaceState(str, 2);
                Tethering.this.mConfiguredEthernetIface = str;
            }
        }

        public void onUnavailable() {
            synchronized (Tethering.this.mPublicSync) {
                if (this != Tethering.this.mEthernetCallback) {
                    return;
                }
                Tethering.this.stopEthernetTetheringLocked();
            }
        }
    }

    int tether(String str) {
        return tether(str, 2);
    }

    private int tether(String str, int i) {
        synchronized (this.mPublicSync) {
            TetherState tetherState = this.mTetherStates.get(str);
            if (tetherState == null) {
                Log.e(TAG, "Tried to Tether an unknown iface: " + str + ", ignoring");
                return 1;
            }
            if (tetherState.lastState != 1) {
                Log.e(TAG, "Tried to Tether an unavailable iface: " + str + ", ignoring");
                return 4;
            }
            int iInterfaceType = tetherState.ipServer.interfaceType();
            TetheringRequestParcel tetheringRequestParcel = this.mActiveTetheringRequests.get(iInterfaceType, null);
            if (tetheringRequestParcel != null) {
                this.mActiveTetheringRequests.delete(iInterfaceType);
            }
            tetherState.ipServer.sendMessage(IpServer.CMD_TETHER_REQUESTED, i, 0, tetheringRequestParcel);
            return 0;
        }
    }

    int untether(String str) {
        synchronized (this.mPublicSync) {
            TetherState tetherState = this.mTetherStates.get(str);
            if (tetherState == null) {
                Log.e(TAG, "Tried to Untether an unknown iface :" + str + ", ignoring");
                return 1;
            }
            if (!tetherState.isCurrentlyServing()) {
                Log.e(TAG, "Tried to untether an inactive iface :" + str + ", ignoring");
                return 4;
            }
            tetherState.ipServer.sendMessage(IpServer.CMD_TETHER_UNREQUESTED);
            return 0;
        }
    }

    void untetherAll() {
        stopTethering(0);
        stopTethering(3);
        stopTethering(1);
        stopTethering(2);
        stopTethering(5);
    }

    private boolean isProvisioningNeededButUnavailable() {
        return isTetherProvisioningRequired() && !doesEntitlementPackageExist();
    }

    boolean isTetherProvisioningRequired() {
        return this.mEntitlementMgr.isTetherProvisioningRequired(this.mConfig);
    }

    private boolean doesEntitlementPackageExist() {
        if (this.mConfig.provisioningApp.length != 2) {
            return false;
        }
        try {
            this.mContext.getPackageManager().getPackageInfo(this.mConfig.provisioningApp[0], 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void sendTetherStateChangedBroadcast() {
        int i;
        int i2;
        if (isTetheringSupported()) {
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> arrayList2 = new ArrayList<>();
            ArrayList<String> arrayList3 = new ArrayList<>();
            ArrayList<String> arrayList4 = new ArrayList<>();
            ArrayList arrayList5 = new ArrayList();
            TetheringConfiguration tetheringConfiguration = this.mConfig;
            this.mTetherStatesParcel = new TetherStatesParcel();
            synchronized (this.mPublicSync) {
                i2 = 0;
                for (int i3 = 0; i3 < this.mTetherStates.size(); i3++) {
                    TetherState tetherStateValueAt = this.mTetherStates.valueAt(i3);
                    String strKeyAt = this.mTetherStates.keyAt(i3);
                    if (tetherStateValueAt.lastError != 0) {
                        arrayList4.add(strKeyAt);
                        arrayList5.add(Integer.valueOf(tetherStateValueAt.lastError));
                    } else if (tetherStateValueAt.lastState == 1) {
                        arrayList.add(strKeyAt);
                    } else if (tetherStateValueAt.lastState == 3) {
                        arrayList3.add(strKeyAt);
                    } else if (tetherStateValueAt.lastState == 2) {
                        if (tetheringConfiguration.isUsb(strKeyAt)) {
                            i2 |= 2;
                        } else if (tetheringConfiguration.isWifi(strKeyAt)) {
                            i2 |= 1;
                        } else if (tetheringConfiguration.isBluetooth(strKeyAt)) {
                            i2 |= 4;
                        }
                        arrayList2.add(strKeyAt);
                    }
                }
            }
            this.mTetherStatesParcel.availableList = (String[]) arrayList.toArray(new String[0]);
            this.mTetherStatesParcel.tetheredList = (String[]) arrayList2.toArray(new String[0]);
            this.mTetherStatesParcel.localOnlyList = (String[]) arrayList3.toArray(new String[0]);
            this.mTetherStatesParcel.erroredIfaceList = (String[]) arrayList4.toArray(new String[0]);
            this.mTetherStatesParcel.lastErrorList = new int[arrayList5.size()];
            Iterator it = arrayList5.iterator();
            for (i = 0; i < arrayList5.size(); i++) {
                this.mTetherStatesParcel.lastErrorList[i] = ((Integer) it.next()).intValue();
            }
            reportTetherStateChanged(this.mTetherStatesParcel);
            Intent intent = new Intent("android.net.conn.TETHER_STATE_CHANGED");
            intent.addFlags(536870912);
            intent.putStringArrayListExtra("availableArray", arrayList);
            intent.putStringArrayListExtra("android.net.extra.ACTIVE_LOCAL_ONLY", arrayList3);
            intent.putStringArrayListExtra("tetherArray", arrayList2);
            intent.putStringArrayListExtra("erroredArray", arrayList4);
            this.mContext.sendStickyBroadcastAsUser(intent, UserHandle.ALL);
            this.mNotificationUpdater.onDownstreamChanged(i2);
        }
    }

    private class StateReceiver extends BroadcastReceiver {
        private StateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            if (action.equals("android.hardware.usb.action.USB_STATE")) {
                handleUsbAction(intent);
                return;
            }
            if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                handleConnectivityAction(intent);
                return;
            }
            if (action.equals("android.net.wifi.WIFI_AP_STATE_CHANGED")) {
                handleWifiApAction(intent);
                return;
            }
            if (action.equals("android.net.wifi.p2p.CONNECTION_STATE_CHANGE")) {
                handleWifiP2pAction(intent);
                return;
            }
            if (action.equals("android.intent.action.CONFIGURATION_CHANGED")) {
                Tethering.this.mLog.log("OBSERVED configuration changed");
                Tethering.this.updateConfiguration();
                return;
            }
            if (action.equals("android.os.action.USER_RESTRICTIONS_CHANGED")) {
                Tethering.this.mLog.log("OBSERVED user restrictions changed");
                handleUserRestrictionAction();
            } else if (action.equals("android.net.conn.RESTRICT_BACKGROUND_CHANGED")) {
                Tethering.this.mLog.log("OBSERVED data saver changed");
                handleDataSaverChanged();
            } else if (action.equals("com.android.server.connectivity.tethering.DISABLE_TETHERING")) {
                Tethering.this.untetherAll();
            }
        }

        private void handleConnectivityAction(Intent intent) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo == null || networkInfo.getDetailedState() == NetworkInfo.DetailedState.FAILED) {
                return;
            }
            Tethering.this.mTetherMasterSM.sendMessage(3);
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0061  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void handleUsbAction(android.content.Intent r11) {
            /*
                r10 = this;
                java.lang.String r0 = "connected"
                r1 = 0
                boolean r0 = r11.getBooleanExtra(r0, r1)
                java.lang.String r2 = "configured"
                boolean r2 = r11.getBooleanExtra(r2, r1)
                java.lang.String r3 = "rndis"
                boolean r3 = r11.getBooleanExtra(r3, r1)
                java.lang.String r4 = "ncm"
                boolean r11 = r11.getBooleanExtra(r4, r1)
                com.android.networkstack.tethering.Tethering r4 = com.android.networkstack.tethering.Tethering.this
                android.net.util.SharedLog r4 = com.android.networkstack.tethering.Tethering.access$600(r4)
                r5 = 3
                java.lang.Object[] r6 = new java.lang.Object[r5]
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)
                r6[r1] = r7
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)
                r8 = 1
                r6[r8] = r7
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r3)
                r9 = 2
                r6[r9] = r7
                java.lang.String r7 = "USB bcast connected:%s configured:%s rndis:%s"
                java.lang.String r6 = java.lang.String.format(r7, r6)
                r4.log(r6)
                com.android.networkstack.tethering.Tethering r4 = com.android.networkstack.tethering.Tethering.this
                java.lang.Object r4 = com.android.networkstack.tethering.Tethering.access$1500(r4)
                monitor-enter(r4)
                if (r0 != 0) goto L61
                com.android.networkstack.tethering.Tethering r6 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                boolean r6 = com.android.networkstack.tethering.Tethering.access$2200(r6)     // Catch: java.lang.Throwable -> L5f
                if (r6 == 0) goto L61
                com.android.networkstack.tethering.Tethering r11 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                com.android.networkstack.tethering.Tethering.access$2300(r11, r8, r8)     // Catch: java.lang.Throwable -> L5f
                com.android.networkstack.tethering.Tethering r11 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                com.android.networkstack.tethering.EntitlementManager r11 = com.android.networkstack.tethering.Tethering.access$1100(r11)     // Catch: java.lang.Throwable -> L5f
                r11.stopProvisioningIfNeeded(r8)     // Catch: java.lang.Throwable -> L5f
                goto L75
            L5f:
                r10 = move-exception
                goto L81
            L61:
                if (r2 == 0) goto L6b
                if (r3 == 0) goto L6b
                com.android.networkstack.tethering.Tethering r11 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                com.android.networkstack.tethering.Tethering.access$2300(r11, r9, r8)     // Catch: java.lang.Throwable -> L5f
                goto L75
            L6b:
                if (r0 == 0) goto L75
                if (r11 == 0) goto L75
                com.android.networkstack.tethering.Tethering r11 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                r0 = 4
                com.android.networkstack.tethering.Tethering.access$2300(r11, r5, r0)     // Catch: java.lang.Throwable -> L5f
            L75:
                com.android.networkstack.tethering.Tethering r10 = com.android.networkstack.tethering.Tethering.this     // Catch: java.lang.Throwable -> L5f
                if (r2 == 0) goto L7c
                if (r3 == 0) goto L7c
                r1 = r8
            L7c:
                com.android.networkstack.tethering.Tethering.access$2202(r10, r1)     // Catch: java.lang.Throwable -> L5f
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L5f
                return
            L81:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L5f
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.networkstack.tethering.Tethering.StateReceiver.handleUsbAction(android.content.Intent):void");
        }

        private void handleWifiApAction(Intent intent) {
            int intExtra = intent.getIntExtra("wifi_state", 11);
            String stringExtra = intent.getStringExtra("android.net.wifi.extra.WIFI_AP_INTERFACE_NAME");
            int intExtra2 = intent.getIntExtra("android.net.wifi.extra.WIFI_AP_MODE", -1);
            synchronized (Tethering.this.mPublicSync) {
                try {
                    if (intExtra != 10 && intExtra != 12) {
                        if (intExtra != 13) {
                            Tethering.this.disableWifiIpServingLocked(stringExtra, intExtra);
                        } else {
                            Tethering.this.enableWifiIpServingLocked(stringExtra, intExtra2);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        private boolean isGroupOwner(WifiP2pGroup wifiP2pGroup) {
            return (wifiP2pGroup == null || !wifiP2pGroup.isGroupOwner() || TextUtils.isEmpty(wifiP2pGroup.getInterface())) ? false : true;
        }

        private void handleWifiP2pAction(Intent intent) {
            if (Tethering.this.mConfig.isWifiP2pLegacyTetheringMode()) {
                return;
            }
            WifiP2pInfo wifiP2pInfo = (WifiP2pInfo) intent.getParcelableExtra("wifiP2pInfo");
            WifiP2pGroup wifiP2pGroup = (WifiP2pGroup) intent.getParcelableExtra("p2pGroupInfo");
            synchronized (Tethering.this.mPublicSync) {
                if (wifiP2pInfo != null) {
                    if (wifiP2pInfo.groupFormed) {
                        if (isGroupOwner(wifiP2pGroup)) {
                            if (wifiP2pGroup.getInterface().equals(Tethering.this.mWifiP2pTetherInterface)) {
                                return;
                            }
                            if (!TextUtils.isEmpty(Tethering.this.mWifiP2pTetherInterface)) {
                                Tethering.this.mLog.w("P2P tethered interface " + Tethering.this.mWifiP2pTetherInterface + "is different from current interface " + wifiP2pGroup.getInterface() + ", re-tether it");
                                Tethering.this.disableWifiP2pIpServingLockedIfNeeded(Tethering.this.mWifiP2pTetherInterface);
                            }
                            Tethering.this.mWifiP2pTetherInterface = wifiP2pGroup.getInterface();
                            Tethering.this.enableWifiIpServingLocked(Tethering.this.mWifiP2pTetherInterface, 2);
                            return;
                        }
                        return;
                    }
                }
                Tethering.this.disableWifiP2pIpServingLockedIfNeeded(Tethering.this.mWifiP2pTetherInterface);
                Tethering.this.mWifiP2pTetherInterface = null;
            }
        }

        private void handleUserRestrictionAction() {
            Tethering.this.mTetheringRestriction.onUserRestrictionsChanged();
        }

        private void handleDataSaverChanged() {
            boolean z = ((ConnectivityManager) Tethering.this.mContext.getSystemService("connectivity")).getRestrictBackgroundStatus() != 1;
            if (Tethering.this.mDataSaverEnabled == z) {
                return;
            }
            Tethering.this.mDataSaverEnabled = z;
            if (Tethering.this.mDataSaverEnabled) {
                Tethering.this.untetherAll();
            }
        }
    }

    boolean isTetheringActive() {
        return this.mActiveTetheringRequests.size() > 0;
    }

    protected static class UserRestrictionActionListener {
        public boolean mDisallowTethering = false;
        private final TetheringNotificationUpdater mNotificationUpdater;
        private final UserManager mUserMgr;
        private final Tethering mWrapper;

        public UserRestrictionActionListener(UserManager userManager, Tethering tethering, TetheringNotificationUpdater tetheringNotificationUpdater) {
            this.mUserMgr = userManager;
            this.mWrapper = tethering;
            this.mNotificationUpdater = tetheringNotificationUpdater;
        }

        public void onUserRestrictionsChanged() {
            boolean z = this.mUserMgr.getUserRestrictions().getBoolean("no_config_tethering");
            boolean z2 = this.mDisallowTethering;
            this.mDisallowTethering = z;
            if (z != z2) {
                if (!z) {
                    this.mNotificationUpdater.tetheringRestrictionLifted();
                } else if (this.mWrapper.isTetheringActive()) {
                    this.mNotificationUpdater.notifyTetheringDisabledByRestriction();
                    this.mWrapper.untetherAll();
                }
            }
        }
    }

    private void disableWifiIpServingLockedCommon(int i, String str, int i2) {
        TetherState tetherState;
        this.mLog.log("Canceling WiFi tethering request - type=" + i + " interface=" + str + " state=" + i2);
        if (!TextUtils.isEmpty(str) && (tetherState = this.mTetherStates.get(str)) != null) {
            tetherState.ipServer.unwanted();
            return;
        }
        for (int i3 = 0; i3 < this.mTetherStates.size(); i3++) {
            IpServer ipServer = this.mTetherStates.valueAt(i3).ipServer;
            if (ipServer.interfaceType() == i) {
                ipServer.unwanted();
                return;
            }
        }
        SharedLog sharedLog = this.mLog;
        StringBuilder sb = new StringBuilder();
        sb.append("Error disabling Wi-Fi IP serving; ");
        sb.append(TextUtils.isEmpty(str) ? "no interface name specified" : "specified interface: " + str);
        sharedLog.log(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableWifiIpServingLocked(String str, int i) {
        this.mWifiTetherRequested = false;
        disableWifiIpServingLockedCommon(0, str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableWifiP2pIpServingLockedIfNeeded(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        disableWifiIpServingLockedCommon(3, str, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableWifiIpServingLocked(String str, int i) {
        int i2 = 2;
        if (i != 1) {
            if (i != 2) {
                this.mLog.e("Cannot enable IP serving in unknown WiFi mode: " + i);
                return;
            }
            i2 = 3;
        }
        if (!TextUtils.isEmpty(str)) {
            maybeTrackNewInterfaceLocked(str);
            changeInterfaceState(str, i2);
        } else {
            this.mLog.e(String.format("Cannot enable IP serving in mode %s on missing interface name", Integer.valueOf(i2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tetherMatchingInterfaces(int i, int i2) {
        try {
            String[] strArrInterfaceGetList = this.mNetd.interfaceGetList();
            String str = null;
            if (strArrInterfaceGetList != null) {
                int length = strArrInterfaceGetList.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    String str2 = strArrInterfaceGetList[i3];
                    if (ifaceNameToType(str2) == i2) {
                        str = str2;
                        break;
                    }
                    i3++;
                }
            }
            if (str == null) {
                Log.e(TAG, "could not find iface of type " + i2);
                return;
            }
            changeInterfaceState(str, i);
        } catch (RemoteException | ServiceSpecificException e) {
            Log.e(TAG, "Error listing Interfaces", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeInterfaceState(String str, int i) {
        int iUntether;
        if (i == 0 || i == 1) {
            iUntether = untether(str);
        } else if (i == 2 || i == 3) {
            iUntether = tether(str, i);
        } else {
            Log.wtf(TAG, "Unknown interface state: " + i);
            return;
        }
        if (iUntether != 0) {
            Log.e(TAG, "unable start or stop tethering on iface " + str);
        }
    }

    boolean hasTetherableConfiguration() {
        TetheringConfiguration tetheringConfiguration = this.mConfig;
        return (tetheringConfiguration.tetherableUsbRegexs.length != 0 || tetheringConfiguration.tetherableWifiRegexs.length != 0 || tetheringConfiguration.tetherableBluetoothRegexs.length != 0) && (!tetheringConfiguration.preferredUpstreamIfaceTypes.isEmpty() || tetheringConfiguration.chooseUpstreamAutomatically);
    }

    int setUsbTethering(boolean z) {
        UsbManager usbManager = (UsbManager) this.mContext.getSystemService("usb");
        if (usbManager == null) {
            this.mLog.e("setUsbTethering: failed to get UsbManager!");
            return 2;
        }
        synchronized (this.mPublicSync) {
            usbManager.setCurrentFunctions(z ? 32L : 0L);
        }
        return 0;
    }

    private int setNcmTethering(boolean z) {
        UsbManager usbManager = (UsbManager) this.mContext.getSystemService("usb");
        synchronized (this.mPublicSync) {
            usbManager.setCurrentFunctions(z ? 1024L : 0L);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMessage(State state, int i) {
        this.mLog.log(state.getName() + " got " + sMagicDecoderRing.get(i, Integer.toString(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean upstreamWanted() {
        boolean z;
        if (!this.mForwardedDownstreams.isEmpty()) {
            return true;
        }
        synchronized (this.mPublicSync) {
            z = this.mWifiTetherRequested;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pertainsToCurrentUpstream(UpstreamNetworkState upstreamNetworkState) {
        LinkProperties linkProperties;
        if (upstreamNetworkState == null || (linkProperties = upstreamNetworkState.linkProperties) == null || this.mCurrentUpstreamIfaceSet == null) {
            return false;
        }
        Iterator it = linkProperties.getAllInterfaceNames().iterator();
        while (it.hasNext()) {
            if (this.mCurrentUpstreamIfaceSet.ifnames.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    class TetherMasterSM extends StateMachine {
        static final int CMD_CLEAR_ERROR = 6;
        static final int CMD_RETRY_UPSTREAM = 4;
        static final int CMD_UPSTREAM_CHANGED = 3;
        static final int EVENT_IFACE_SERVING_STATE_ACTIVE = 1;
        static final int EVENT_IFACE_SERVING_STATE_INACTIVE = 2;
        static final int EVENT_IFACE_UPDATE_LINKPROPERTIES = 7;
        static final int EVENT_UPSTREAM_CALLBACK = 5;
        static final int EVENT_UPSTREAM_PERMISSION_CHANGED = 8;
        private final IPv6TetheringCoordinator mIPv6TetheringCoordinator;
        private final State mInitialState;
        private final ArrayList<IpServer> mNotifyList;
        private final OffloadWrapper mOffload;
        private final State mSetDnsForwardersErrorState;
        private final State mSetIpForwardingDisabledErrorState;
        private final State mSetIpForwardingEnabledErrorState;
        private final State mStartTetheringErrorState;
        private final State mStopTetheringErrorState;
        private final State mTetherModeAliveState;

        TetherMasterSM(String str, Looper looper, TetheringDependencies tetheringDependencies) {
            super(str, looper);
            this.mInitialState = new InitialState();
            this.mTetherModeAliveState = new TetherModeAliveState();
            this.mSetIpForwardingEnabledErrorState = new SetIpForwardingEnabledErrorState(this);
            this.mSetIpForwardingDisabledErrorState = new SetIpForwardingDisabledErrorState(this);
            this.mStartTetheringErrorState = new StartTetheringErrorState();
            this.mStopTetheringErrorState = new StopTetheringErrorState();
            this.mSetDnsForwardersErrorState = new SetDnsForwardersErrorState();
            addState(this.mInitialState);
            addState(this.mTetherModeAliveState);
            addState(this.mSetIpForwardingEnabledErrorState);
            addState(this.mSetIpForwardingDisabledErrorState);
            addState(this.mStartTetheringErrorState);
            addState(this.mStopTetheringErrorState);
            addState(this.mSetDnsForwardersErrorState);
            ArrayList<IpServer> arrayList = new ArrayList<>();
            this.mNotifyList = arrayList;
            this.mIPv6TetheringCoordinator = tetheringDependencies.getIPv6TetheringCoordinator(arrayList, Tethering.this.mLog);
            this.mOffload = new OffloadWrapper();
            setInitialState(this.mInitialState);
        }

        class InitialState extends State {
            InitialState() {
            }

            @Override // com.android.networkstack.tethering.util.State
            public boolean processMessage(Message message) {
                Tethering.this.logMessage(this, message.what);
                int i = message.what;
                if (i == TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE) {
                    TetherMasterSM.this.handleInterfaceServingStateActive(message.arg1, (IpServer) message.obj);
                    TetherMasterSM tetherMasterSM = TetherMasterSM.this;
                    tetherMasterSM.transitionTo(tetherMasterSM.mTetherModeAliveState);
                } else if (i == TetherMasterSM.EVENT_IFACE_SERVING_STATE_INACTIVE) {
                    TetherMasterSM.this.handleInterfaceServingStateInactive((IpServer) message.obj);
                } else if (i != TetherMasterSM.EVENT_IFACE_UPDATE_LINKPROPERTIES) {
                    return false;
                }
                return true;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        protected boolean turnOnMasterTetherSettings() throws ServiceSpecificException {
            TetheringConfiguration tetheringConfiguration = Tethering.this.mConfig;
            try {
                Tethering.this.mNetd.ipfwdEnableForwarding(Tethering.TAG);
                String[] strArr = tetheringConfiguration.enableLegacyDhcpServer ? tetheringConfiguration.legacyDhcpRanges : new String[0];
                try {
                    try {
                        NetdUtils.tetherStart(Tethering.this.mNetd, true, strArr);
                    } catch (RemoteException | ServiceSpecificException e) {
                        Tethering.this.mLog.e(e);
                        transitionTo(this.mStartTetheringErrorState);
                        return false;
                    }
                } catch (RemoteException | ServiceSpecificException unused) {
                    Tethering.this.mNetd.tetherStop();
                    NetdUtils.tetherStart(Tethering.this.mNetd, true, strArr);
                }
                Tethering.this.mLog.log("SET master tether settings: ON");
                return true;
            } catch (RemoteException | ServiceSpecificException e2) {
                Tethering.this.mLog.e(e2);
                transitionTo(this.mSetIpForwardingEnabledErrorState);
                return false;
            }
        }

        protected boolean turnOffMasterTetherSettings() {
            try {
                Tethering.this.mNetd.tetherStop();
                try {
                    Tethering.this.mNetd.ipfwdDisableForwarding(Tethering.TAG);
                    transitionTo(this.mInitialState);
                    Tethering.this.mLog.log("SET master tether settings: OFF");
                    return true;
                } catch (RemoteException | ServiceSpecificException e) {
                    Tethering.this.mLog.e(e);
                    transitionTo(this.mSetIpForwardingDisabledErrorState);
                    return false;
                }
            } catch (RemoteException | ServiceSpecificException e2) {
                Tethering.this.mLog.e(e2);
                transitionTo(this.mStopTetheringErrorState);
                return false;
            }
        }

        protected void chooseUpstreamType(boolean z) {
            Tethering.this.maybeDunSettingChanged();
            TetheringConfiguration tetheringConfiguration = Tethering.this.mConfig;
            UpstreamNetworkState currentPreferredUpstream = tetheringConfiguration.chooseUpstreamAutomatically ? Tethering.this.mUpstreamNetworkMonitor.getCurrentPreferredUpstream() : Tethering.this.mUpstreamNetworkMonitor.selectPreferredUpstreamType(tetheringConfiguration.preferredUpstreamIfaceTypes);
            if (currentPreferredUpstream == null) {
                if (z) {
                    Tethering.this.mUpstreamNetworkMonitor.registerMobileNetworkRequest();
                } else {
                    sendMessageDelayed(CMD_RETRY_UPSTREAM, 10000L);
                }
            }
            setUpstreamNetwork(currentPreferredUpstream);
            Network network = currentPreferredUpstream != null ? currentPreferredUpstream.network : null;
            if (Tethering.this.mTetherUpstream != network) {
                Tethering.this.mTetherUpstream = network;
                Tethering.this.mUpstreamNetworkMonitor.setCurrentUpstream(Tethering.this.mTetherUpstream);
                Tethering.this.reportUpstreamChanged(currentPreferredUpstream);
            }
        }

        protected void setUpstreamNetwork(UpstreamNetworkState upstreamNetworkState) {
            InterfaceSet tetheringInterfaces;
            if (upstreamNetworkState != null) {
                Tethering.this.mLog.i("Looking for default routes on: " + upstreamNetworkState.linkProperties);
                tetheringInterfaces = TetheringInterfaceUtils.getTetheringInterfaces(upstreamNetworkState);
                Tethering.this.mLog.i("Found upstream interface(s): " + tetheringInterfaces);
            } else {
                tetheringInterfaces = null;
            }
            if (tetheringInterfaces != null) {
                setDnsForwarders(upstreamNetworkState.network, upstreamNetworkState.linkProperties);
            }
            notifyDownstreamsOfNewUpstreamIface(tetheringInterfaces);
            if (upstreamNetworkState == null || !Tethering.this.pertainsToCurrentUpstream(upstreamNetworkState)) {
                if (Tethering.this.mCurrentUpstreamIfaceSet == null) {
                    handleNewUpstreamNetworkState(null);
                    return;
                }
                return;
            }
            handleNewUpstreamNetworkState(upstreamNetworkState);
        }

        protected void setDnsForwarders(Network network, LinkProperties linkProperties) {
            String[] strArr;
            List<InetAddress> dnsServers = linkProperties.getDnsServers();
            if (dnsServers == null || dnsServers.isEmpty()) {
                strArr = Tethering.this.mConfig.defaultIPv4DNS;
            } else {
                strArr = new String[dnsServers.size()];
                int i = 0;
                for (InetAddress inetAddress : dnsServers) {
                    int i2 = i + EVENT_IFACE_SERVING_STATE_ACTIVE;
                    strArr[i] = inetAddress.getHostAddress();
                    i = i2;
                }
            }
            try {
                Tethering.this.mNetd.tetherDnsSet(network != null ? network.getNetId() : 0, strArr);
                Tethering.this.mLog.log(String.format("SET DNS forwarders: network=%s dnsServers=%s", network, Arrays.toString(strArr)));
            } catch (RemoteException | ServiceSpecificException e) {
                Tethering.this.mLog.e("setting DNS forwarders failed, " + e);
                transitionTo(this.mSetDnsForwardersErrorState);
            }
        }

        protected void notifyDownstreamsOfNewUpstreamIface(InterfaceSet interfaceSet) {
            Tethering.this.mCurrentUpstreamIfaceSet = interfaceSet;
            Iterator<IpServer> it = this.mNotifyList.iterator();
            while (it.hasNext()) {
                it.next().sendMessage(IpServer.CMD_TETHER_CONNECTION_CHANGED, interfaceSet);
            }
        }

        protected void handleNewUpstreamNetworkState(UpstreamNetworkState upstreamNetworkState) {
            this.mIPv6TetheringCoordinator.updateUpstreamNetworkState(upstreamNetworkState);
            this.mOffload.updateUpstreamNetworkState(upstreamNetworkState);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleInterfaceServingStateActive(int i, IpServer ipServer) {
            if (this.mNotifyList.indexOf(ipServer) < 0) {
                this.mNotifyList.add(ipServer);
                this.mIPv6TetheringCoordinator.addActiveDownstream(ipServer, i);
            }
            if (i == EVENT_IFACE_SERVING_STATE_INACTIVE) {
                Tethering.this.mForwardedDownstreams.add(ipServer);
            } else {
                this.mOffload.excludeDownstreamInterface(ipServer.interfaceName());
                Tethering.this.mForwardedDownstreams.remove(ipServer);
            }
            if (ipServer.interfaceType() == 0) {
                WifiManager wifiManager = Tethering.this.getWifiManager();
                String strInterfaceName = ipServer.interfaceName();
                if (i == EVENT_IFACE_SERVING_STATE_INACTIVE) {
                    wifiManager.updateInterfaceIpState(strInterfaceName, EVENT_IFACE_SERVING_STATE_ACTIVE);
                    return;
                }
                if (i != CMD_UPSTREAM_CHANGED) {
                    Log.wtf(Tethering.TAG, "Unknown active serving mode: " + i);
                    return;
                }
                wifiManager.updateInterfaceIpState(strInterfaceName, EVENT_IFACE_SERVING_STATE_INACTIVE);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleInterfaceServingStateInactive(IpServer ipServer) {
            this.mNotifyList.remove(ipServer);
            this.mIPv6TetheringCoordinator.removeActiveDownstream(ipServer);
            this.mOffload.excludeDownstreamInterface(ipServer.interfaceName());
            Tethering.this.mForwardedDownstreams.remove(ipServer);
            Tethering.this.updateConnectedClients(null);
            if (ipServer.interfaceType() == 0) {
                if (ipServer.lastError() != 0) {
                    Tethering.this.getWifiManager().updateInterfaceIpState(ipServer.interfaceName(), 0);
                } else {
                    Tethering.this.getWifiManager().updateInterfaceIpState(ipServer.interfaceName(), -1);
                }
            }
        }

        private void addUpstreamPrefixes(UpstreamNetworkState upstreamNetworkState) {
            Tethering.this.mPrivateAddressCoordinator.updateUpstreamPrefix(upstreamNetworkState.network, upstreamNetworkState.linkProperties);
        }

        private void removeUpstreamPrefixes(UpstreamNetworkState upstreamNetworkState) {
            Tethering.this.mPrivateAddressCoordinator.removeUpstreamPrefix(upstreamNetworkState.network);
        }

        void handleUpstreamNetworkMonitorCallback(int i, Object obj) {
            if (i == 10) {
                this.mOffload.sendOffloadExemptPrefixes((Set) obj);
                return;
            }
            UpstreamNetworkState upstreamNetworkState = (UpstreamNetworkState) obj;
            if (i == EVENT_IFACE_SERVING_STATE_INACTIVE) {
                addUpstreamPrefixes(upstreamNetworkState);
            } else if (i == CMD_UPSTREAM_CHANGED) {
                removeUpstreamPrefixes(upstreamNetworkState);
            }
            if (upstreamNetworkState == null || !Tethering.this.pertainsToCurrentUpstream(upstreamNetworkState)) {
                if (Tethering.this.mCurrentUpstreamIfaceSet == null) {
                    chooseUpstreamType(false);
                    return;
                }
                return;
            }
            if (i == EVENT_IFACE_SERVING_STATE_ACTIVE) {
                if (upstreamNetworkState.network.equals(Tethering.this.mTetherUpstream)) {
                    Tethering.this.mNotificationUpdater.onUpstreamCapabilitiesChanged(upstreamNetworkState.networkCapabilities);
                }
                handleNewUpstreamNetworkState(upstreamNetworkState);
            } else {
                if (i == EVENT_IFACE_SERVING_STATE_INACTIVE) {
                    chooseUpstreamType(false);
                    return;
                }
                if (i != CMD_UPSTREAM_CHANGED) {
                    Tethering.this.mLog.e("Unknown arg1 value: " + i);
                    return;
                }
                handleNewUpstreamNetworkState(null);
            }
        }

        class TetherModeAliveState extends State {
            boolean mUpstreamWanted = false;
            boolean mTryCell = true;

            TetherModeAliveState() {
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                if (TetherMasterSM.this.turnOnMasterTetherSettings()) {
                    Tethering.this.mUpstreamNetworkMonitor.startObserveAllNetworks();
                    if (Tethering.this.upstreamWanted()) {
                        this.mUpstreamWanted = true;
                        TetherMasterSM.this.mOffload.start();
                        TetherMasterSM.this.chooseUpstreamType(true);
                        this.mTryCell = false;
                    }
                    Tethering.this.mBpfCoordinator.startPolling();
                }
            }

            @Override // com.android.networkstack.tethering.util.State
            public void exit() {
                TetherMasterSM.this.mOffload.stop();
                Tethering.this.mUpstreamNetworkMonitor.stop();
                TetherMasterSM.this.notifyDownstreamsOfNewUpstreamIface(null);
                TetherMasterSM.this.handleNewUpstreamNetworkState(null);
                if (Tethering.this.mTetherUpstream != null) {
                    Tethering.this.mTetherUpstream = null;
                    Tethering.this.reportUpstreamChanged(null);
                }
                Tethering.this.mBpfCoordinator.stopPolling();
            }

            private boolean updateUpstreamWanted() {
                boolean z = this.mUpstreamWanted;
                boolean zUpstreamWanted = Tethering.this.upstreamWanted();
                this.mUpstreamWanted = zUpstreamWanted;
                if (zUpstreamWanted != z) {
                    if (zUpstreamWanted) {
                        TetherMasterSM.this.mOffload.start();
                    } else {
                        TetherMasterSM.this.mOffload.stop();
                    }
                }
                return z;
            }

            @Override // com.android.networkstack.tethering.util.State
            public boolean processMessage(Message message) {
                Tethering.this.logMessage(this, message.what);
                switch (message.what) {
                    case TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE /* 1 */:
                        IpServer ipServer = (IpServer) message.obj;
                        TetherMasterSM.this.handleInterfaceServingStateActive(message.arg1, ipServer);
                        ipServer.sendMessage(IpServer.CMD_TETHER_CONNECTION_CHANGED, Tethering.this.mCurrentUpstreamIfaceSet);
                        if (!updateUpstreamWanted() && this.mUpstreamWanted) {
                            TetherMasterSM.this.chooseUpstreamType(true);
                        }
                        break;
                    case TetherMasterSM.EVENT_IFACE_SERVING_STATE_INACTIVE /* 2 */:
                        TetherMasterSM.this.handleInterfaceServingStateInactive((IpServer) message.obj);
                        if (TetherMasterSM.this.mNotifyList.isEmpty()) {
                            TetherMasterSM.this.turnOffMasterTetherSettings();
                        } else if (updateUpstreamWanted() && !this.mUpstreamWanted) {
                            Tethering.this.mUpstreamNetworkMonitor.releaseMobileNetworkRequest();
                        }
                        break;
                    case TetherMasterSM.CMD_UPSTREAM_CHANGED /* 3 */:
                    case TetherMasterSM.EVENT_UPSTREAM_PERMISSION_CHANGED /* 8 */:
                        updateUpstreamWanted();
                        if (this.mUpstreamWanted) {
                            TetherMasterSM.this.chooseUpstreamType(true);
                            this.mTryCell = false;
                        }
                        break;
                    case TetherMasterSM.CMD_RETRY_UPSTREAM /* 4 */:
                        updateUpstreamWanted();
                        if (this.mUpstreamWanted) {
                            TetherMasterSM.this.chooseUpstreamType(this.mTryCell);
                            this.mTryCell ^= TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE;
                        }
                        break;
                    case TetherMasterSM.EVENT_UPSTREAM_CALLBACK /* 5 */:
                        updateUpstreamWanted();
                        if (this.mUpstreamWanted) {
                            TetherMasterSM.this.handleUpstreamNetworkMonitorCallback(message.arg1, message.obj);
                        }
                        break;
                    case TetherMasterSM.CMD_CLEAR_ERROR /* 6 */:
                    default:
                        return false;
                    case TetherMasterSM.EVENT_IFACE_UPDATE_LINKPROPERTIES /* 7 */:
                        LinkProperties linkProperties = (LinkProperties) message.obj;
                        if (message.arg1 == TetherMasterSM.EVENT_IFACE_SERVING_STATE_INACTIVE) {
                            TetherMasterSM.this.mOffload.updateDownstreamLinkProperties(linkProperties);
                        } else {
                            TetherMasterSM.this.mOffload.excludeDownstreamInterface(linkProperties.getInterfaceName());
                        }
                        break;
                }
                return true;
            }
        }

        class ErrorState extends State {
            private int mErrorNotification;

            ErrorState() {
            }

            @Override // com.android.networkstack.tethering.util.State
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE) {
                    ((IpServer) message.obj).sendMessage(this.mErrorNotification);
                } else {
                    if (i != TetherMasterSM.CMD_CLEAR_ERROR) {
                        return false;
                    }
                    this.mErrorNotification = 0;
                    TetherMasterSM tetherMasterSM = TetherMasterSM.this;
                    tetherMasterSM.transitionTo(tetherMasterSM.mInitialState);
                }
                return true;
            }

            void notify(int i) {
                this.mErrorNotification = i;
                Iterator it = TetherMasterSM.this.mNotifyList.iterator();
                while (it.hasNext()) {
                    ((IpServer) it.next()).sendMessage(i);
                }
            }
        }

        class SetIpForwardingEnabledErrorState extends ErrorState {
            SetIpForwardingEnabledErrorState(TetherMasterSM tetherMasterSM) {
                super();
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                Log.e(Tethering.TAG, "Error in setIpForwardingEnabled");
                notify(IpServer.CMD_IP_FORWARDING_ENABLE_ERROR);
            }
        }

        class SetIpForwardingDisabledErrorState extends ErrorState {
            SetIpForwardingDisabledErrorState(TetherMasterSM tetherMasterSM) {
                super();
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                Log.e(Tethering.TAG, "Error in setIpForwardingDisabled");
                notify(IpServer.CMD_IP_FORWARDING_DISABLE_ERROR);
            }
        }

        class StartTetheringErrorState extends ErrorState {
            StartTetheringErrorState() {
                super();
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                Log.e(Tethering.TAG, "Error in startTethering");
                notify(IpServer.CMD_START_TETHERING_ERROR);
                try {
                    Tethering.this.mNetd.ipfwdDisableForwarding(Tethering.TAG);
                } catch (RemoteException | ServiceSpecificException unused) {
                }
            }
        }

        class StopTetheringErrorState extends ErrorState {
            StopTetheringErrorState() {
                super();
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                Log.e(Tethering.TAG, "Error in stopTethering");
                notify(IpServer.CMD_STOP_TETHERING_ERROR);
                try {
                    Tethering.this.mNetd.ipfwdDisableForwarding(Tethering.TAG);
                } catch (RemoteException | ServiceSpecificException unused) {
                }
            }
        }

        class SetDnsForwardersErrorState extends ErrorState {
            SetDnsForwardersErrorState() {
                super();
            }

            @Override // com.android.networkstack.tethering.util.State
            public void enter() {
                Log.e(Tethering.TAG, "Error in setDnsForwarders");
                notify(IpServer.CMD_SET_DNS_FORWARDERS_ERROR);
                try {
                    Tethering.this.mNetd.tetherStop();
                } catch (RemoteException | ServiceSpecificException unused) {
                }
                try {
                    Tethering.this.mNetd.ipfwdDisableForwarding(Tethering.TAG);
                } catch (RemoteException | ServiceSpecificException unused2) {
                }
            }
        }

        class OffloadWrapper {
            OffloadWrapper() {
            }

            public void start() {
                updateOffloadStatus(Tethering.this.mOffloadController.start() ? TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE : TetherMasterSM.EVENT_IFACE_SERVING_STATE_INACTIVE);
                sendOffloadExemptPrefixes();
            }

            public void stop() {
                Tethering.this.mOffloadController.stop();
                updateOffloadStatus(0);
            }

            public void updateUpstreamNetworkState(UpstreamNetworkState upstreamNetworkState) {
                Tethering.this.mOffloadController.setUpstreamLinkProperties(upstreamNetworkState != null ? upstreamNetworkState.linkProperties : null);
            }

            public void updateDownstreamLinkProperties(LinkProperties linkProperties) {
                sendOffloadExemptPrefixes();
                Tethering.this.mOffloadController.notifyDownstreamLinkProperties(linkProperties);
            }

            public void excludeDownstreamInterface(String str) {
                sendOffloadExemptPrefixes();
                Tethering.this.mOffloadController.removeDownstreamInterface(str);
            }

            public void sendOffloadExemptPrefixes() {
                sendOffloadExemptPrefixes(Tethering.this.mUpstreamNetworkMonitor.getLocalPrefixes());
            }

            public void sendOffloadExemptPrefixes(Set<IpPrefix> set) {
                PrefixUtils.addNonForwardablePrefixes(set);
                set.add(PrefixUtils.DEFAULT_WIFI_P2P_PREFIX);
                for (IpServer ipServer : TetherMasterSM.this.mNotifyList) {
                    LinkProperties linkProperties = ipServer.linkProperties();
                    int iServingMode = ipServer.servingMode();
                    if (iServingMode != 0 && iServingMode != TetherMasterSM.EVENT_IFACE_SERVING_STATE_ACTIVE) {
                        if (iServingMode == TetherMasterSM.EVENT_IFACE_SERVING_STATE_INACTIVE) {
                            Iterator it = linkProperties.getAllLinkAddresses().iterator();
                            while (it.hasNext()) {
                                InetAddress address = ((LinkAddress) it.next()).getAddress();
                                if (!address.isLinkLocalAddress()) {
                                    set.add(PrefixUtils.ipAddressAsPrefix(address));
                                }
                            }
                        } else if (iServingMode == TetherMasterSM.CMD_UPSTREAM_CHANGED) {
                            set.addAll(PrefixUtils.localPrefixesFrom(linkProperties));
                        }
                    }
                }
                Tethering.this.mOffloadController.setLocalPrefixes(set);
            }

            private void updateOffloadStatus(int i) {
                if (i == Tethering.this.mOffloadStatus) {
                    return;
                }
                Tethering.this.mOffloadStatus = i;
                Tethering tethering = Tethering.this;
                tethering.reportOffloadStatusChanged(tethering.mOffloadStatus);
            }
        }
    }

    private void startTrackDefaultNetwork() {
        this.mUpstreamNetworkMonitor.startTrackDefaultNetwork(this.mDeps.getDefaultNetworkRequest(), this.mEntitlementMgr);
    }

    void requestLatestTetheringEntitlementResult(final int i, final ResultReceiver resultReceiver, final boolean z) {
        if (resultReceiver == null) {
            return;
        }
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$HISJkjHE0qZeNl0LP0Yk72J8ovw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$requestLatestTetheringEntitlementResult$6$Tethering(i, resultReceiver, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$requestLatestTetheringEntitlementResult$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$requestLatestTetheringEntitlementResult$6$Tethering(int i, ResultReceiver resultReceiver, boolean z) {
        this.mEntitlementMgr.requestLatestTetheringEntitlementResult(i, resultReceiver, z);
    }

    void registerTetheringEventCallback(final ITetheringEventCallback iTetheringEventCallback) {
        final boolean z = hasCallingPermission("android.permission.NETWORK_SETTINGS") || hasCallingPermission("android.permission.MAINLINE_NETWORK_STACK") || hasCallingPermission("android.permission.NETWORK_STACK");
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$DGxfWbpNWUA-W_mdtgZ-PbdnlHA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$registerTetheringEventCallback$7$Tethering(iTetheringEventCallback, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$registerTetheringEventCallback$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$registerTetheringEventCallback$7$Tethering(ITetheringEventCallback iTetheringEventCallback, boolean z) {
        List<TetheredClient> listEmptyList;
        this.mTetheringEventCallbacks.register(iTetheringEventCallback, new CallbackCookie(z));
        TetheringCallbackStartedParcel tetheringCallbackStartedParcel = new TetheringCallbackStartedParcel();
        tetheringCallbackStartedParcel.tetheringSupported = isTetheringSupported();
        tetheringCallbackStartedParcel.upstreamNetwork = this.mTetherUpstream;
        tetheringCallbackStartedParcel.config = this.mConfig.toStableParcelable();
        TetherStatesParcel tetherStatesParcelEmptyTetherStatesParcel = this.mTetherStatesParcel;
        if (tetherStatesParcelEmptyTetherStatesParcel == null) {
            tetherStatesParcelEmptyTetherStatesParcel = emptyTetherStatesParcel();
        }
        tetheringCallbackStartedParcel.states = tetherStatesParcelEmptyTetherStatesParcel;
        if (z) {
            listEmptyList = this.mConnectedClientsTracker.getLastTetheredClients();
        } else {
            listEmptyList = Collections.emptyList();
        }
        tetheringCallbackStartedParcel.tetheredClients = listEmptyList;
        tetheringCallbackStartedParcel.offloadStatus = this.mOffloadStatus;
        try {
            iTetheringEventCallback.onCallbackStarted(tetheringCallbackStartedParcel);
        } catch (RemoteException unused) {
        }
    }

    private TetherStatesParcel emptyTetherStatesParcel() {
        TetherStatesParcel tetherStatesParcel = new TetherStatesParcel();
        tetherStatesParcel.availableList = new String[0];
        tetherStatesParcel.tetheredList = new String[0];
        tetherStatesParcel.localOnlyList = new String[0];
        tetherStatesParcel.erroredIfaceList = new String[0];
        tetherStatesParcel.lastErrorList = new int[0];
        return tetherStatesParcel;
    }

    private boolean hasCallingPermission(String str) {
        return this.mContext.checkCallingPermission(str) == 0;
    }

    void unregisterTetheringEventCallback(final ITetheringEventCallback iTetheringEventCallback) {
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$Tethering$yCxF-4RObpOgwyP8kIAttsvKAR8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$unregisterTetheringEventCallback$8$Tethering(iTetheringEventCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$unregisterTetheringEventCallback$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$unregisterTetheringEventCallback$8$Tethering(ITetheringEventCallback iTetheringEventCallback) {
        this.mTetheringEventCallbacks.unregister(iTetheringEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportUpstreamChanged(UpstreamNetworkState upstreamNetworkState) {
        int iBeginBroadcast = this.mTetheringEventCallbacks.beginBroadcast();
        Network network = upstreamNetworkState != null ? upstreamNetworkState.network : null;
        NetworkCapabilities networkCapabilities = upstreamNetworkState != null ? upstreamNetworkState.networkCapabilities : null;
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.mTetheringEventCallbacks.getBroadcastItem(i).onUpstreamChanged(network);
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mTetheringEventCallbacks.finishBroadcast();
                throw th;
            }
        }
        this.mTetheringEventCallbacks.finishBroadcast();
        this.mNotificationUpdater.onUpstreamCapabilitiesChanged(networkCapabilities);
    }

    private void reportConfigurationChanged(TetheringConfigurationParcel tetheringConfigurationParcel) {
        int iBeginBroadcast = this.mTetheringEventCallbacks.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.mTetheringEventCallbacks.getBroadcastItem(i).onConfigurationChanged(tetheringConfigurationParcel);
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mTetheringEventCallbacks.finishBroadcast();
                throw th;
            }
        }
        this.mTetheringEventCallbacks.finishBroadcast();
    }

    private void reportTetherStateChanged(TetherStatesParcel tetherStatesParcel) {
        int iBeginBroadcast = this.mTetheringEventCallbacks.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                this.mTetheringEventCallbacks.getBroadcastItem(i).onTetherStatesChanged(tetherStatesParcel);
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mTetheringEventCallbacks.finishBroadcast();
                throw th;
            }
        }
        this.mTetheringEventCallbacks.finishBroadcast();
    }

    private void reportTetherClientsChanged(List<TetheredClient> list) {
        int iBeginBroadcast = this.mTetheringEventCallbacks.beginBroadcast();
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                if (((CallbackCookie) this.mTetheringEventCallbacks.getBroadcastCookie(i)).hasListClientsPermission) {
                    this.mTetheringEventCallbacks.getBroadcastItem(i).onTetherClientsChanged(list);
                }
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mTetheringEventCallbacks.finishBroadcast();
                throw th;
            }
        }
        this.mTetheringEventCallbacks.finishBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportOffloadStatusChanged(int i) {
        int iBeginBroadcast = this.mTetheringEventCallbacks.beginBroadcast();
        for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
            try {
                this.mTetheringEventCallbacks.getBroadcastItem(i2).onOffloadStatusChanged(i);
            } catch (RemoteException unused) {
            } catch (Throwable th) {
                this.mTetheringEventCallbacks.finishBroadcast();
                throw th;
            }
        }
        this.mTetheringEventCallbacks.finishBroadcast();
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isTetheringSupported() {
        return ((Settings.Global.getInt(this.mContext.getContentResolver(), "tether_supported", !this.mDeps.isTetheringDenied() ? 1 : 0) != 0) != false && !this.mUserManager.hasUserRestriction("no_config_tethering")) == true && hasTetherableConfiguration() && !isProvisioningNeededButUnavailable();
    }

    void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        if (this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
            indentingPrintWriter.println("Permission Denial: can't dump.");
            return;
        }
        indentingPrintWriter.println("Tethering:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("Configuration:");
        indentingPrintWriter.increaseIndent();
        this.mConfig.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Entitlement:");
        indentingPrintWriter.increaseIndent();
        this.mEntitlementMgr.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        synchronized (this.mPublicSync) {
            indentingPrintWriter.println("Tether state:");
            indentingPrintWriter.increaseIndent();
            for (int i = 0; i < this.mTetherStates.size(); i++) {
                String strKeyAt = this.mTetherStates.keyAt(i);
                TetherState tetherStateValueAt = this.mTetherStates.valueAt(i);
                indentingPrintWriter.print(strKeyAt + " - ");
                int i2 = tetherStateValueAt.lastState;
                if (i2 == 0) {
                    indentingPrintWriter.print("UnavailableState");
                } else if (i2 == 1) {
                    indentingPrintWriter.print("AvailableState");
                } else if (i2 == 2) {
                    indentingPrintWriter.print("TetheredState");
                } else if (i2 == 3) {
                    indentingPrintWriter.print("LocalHotspotState");
                } else {
                    indentingPrintWriter.print("UnknownState");
                }
                indentingPrintWriter.println(" - lastError = " + tetherStateValueAt.lastError);
            }
            indentingPrintWriter.println("Upstream wanted: " + upstreamWanted());
            indentingPrintWriter.println("Current upstream interface(s): " + this.mCurrentUpstreamIfaceSet);
            indentingPrintWriter.decreaseIndent();
        }
        indentingPrintWriter.println("Hardware offload:");
        indentingPrintWriter.increaseIndent();
        this.mOffloadController.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("BPF offload:");
        indentingPrintWriter.increaseIndent();
        this.mBpfCoordinator.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Private address coordinator:");
        indentingPrintWriter.increaseIndent();
        this.mPrivateAddressCoordinator.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Log:");
        indentingPrintWriter.increaseIndent();
        if (argsContain(strArr, "--short")) {
            indentingPrintWriter.println("<log removed for brevity>");
        } else {
            this.mLog.dump(fileDescriptor, indentingPrintWriter, strArr);
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.decreaseIndent();
    }

    private static boolean argsContain(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectedClients(List<WifiClient> list) {
        if (this.mConnectedClientsTracker.updateConnectedClients(this.mForwardedDownstreams, list)) {
            reportTetherClientsChanged(this.mConnectedClientsTracker.getLastTetheredClients());
        }
    }

    private IpServer.Callback makeControlCallback() {
        return new IpServer.Callback() { // from class: com.android.networkstack.tethering.Tethering.4
            @Override // android.net.ip.IpServer.Callback
            public void updateInterfaceState(IpServer ipServer, int i, int i2) {
                Tethering.this.notifyInterfaceStateChange(ipServer, i, i2);
            }

            @Override // android.net.ip.IpServer.Callback
            public void updateLinkProperties(IpServer ipServer, LinkProperties linkProperties) {
                Tethering.this.notifyLinkPropertiesChanged(ipServer, linkProperties);
            }

            @Override // android.net.ip.IpServer.Callback
            public void dhcpLeasesChanged() {
                Tethering.this.updateConnectedClients(null);
            }

            @Override // android.net.ip.IpServer.Callback
            public void requestEnableTethering(int i, boolean z) {
                Tethering.this.enableTetheringInternal(i, z, null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyInterfaceStateChange(IpServer ipServer, int i, int i2) {
        String strInterfaceName = ipServer.interfaceName();
        synchronized (this.mPublicSync) {
            TetherState tetherState = this.mTetherStates.get(strInterfaceName);
            if (tetherState != null && tetherState.ipServer.equals(ipServer)) {
                tetherState.lastState = i;
                tetherState.lastError = i2;
            }
        }
        int i3 = 1;
        this.mLog.log(String.format("OBSERVED iface=%s state=%s error=%s", strInterfaceName, Integer.valueOf(i), Integer.valueOf(i2)));
        if (i2 == 5) {
            this.mTetherMasterSM.sendMessage(6, ipServer);
        }
        if (i == 0 || i == 1) {
            i3 = 2;
        } else if (i != 2 && i != 3) {
            Log.wtf(TAG, "Unknown interface state: " + i);
            return;
        }
        this.mTetherMasterSM.sendMessage(i3, i, 0, ipServer);
        sendTetherStateChangedBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLinkPropertiesChanged(IpServer ipServer, LinkProperties linkProperties) {
        String strInterfaceName = ipServer.interfaceName();
        synchronized (this.mPublicSync) {
            TetherState tetherState = this.mTetherStates.get(strInterfaceName);
            if (tetherState != null && tetherState.ipServer.equals(ipServer)) {
                int i = tetherState.lastState;
                this.mLog.log(String.format("OBSERVED LinkProperties update iface=%s state=%s lp=%s", strInterfaceName, IpServer.getStateString(i), linkProperties));
                this.mTetherMasterSM.sendMessage(7, i, 0, linkProperties);
            } else {
                this.mLog.log("got notification from stale iface " + strInterfaceName);
            }
        }
    }

    private void maybeTrackNewInterfaceLocked(String str) {
        int iIfaceNameToType = ifaceNameToType(str);
        if (iIfaceNameToType == -1) {
            this.mLog.log(str + " is not a tetherable iface, ignoring");
            return;
        }
        maybeTrackNewInterfaceLocked(str, iIfaceNameToType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeTrackNewInterfaceLocked(String str, int i) {
        if (this.mTetherStates.containsKey(str)) {
            this.mLog.log("active iface (" + str + ") reported as added, ignoring");
            return;
        }
        this.mLog.log("adding TetheringInterfaceStateMachine for: " + str);
        TetherState tetherState = new TetherState(new IpServer(str, this.mLooper, i, this.mLog, this.mNetd, this.mBpfCoordinator, makeControlCallback(), this.mConfig.enableLegacyDhcpServer, this.mConfig.isBpfOffloadEnabled(), this.mPrivateAddressCoordinator, this.mDeps.getIpServerDependencies()));
        this.mTetherStates.put(str, tetherState);
        tetherState.ipServer.start();
    }

    private void stopTrackingInterfaceLocked(String str) {
        TetherState tetherState = this.mTetherStates.get(str);
        if (tetherState == null) {
            this.mLog.log("attempting to remove unknown iface (" + str + "), ignoring");
            return;
        }
        tetherState.ipServer.stop();
        this.mLog.log("removing TetheringInterfaceStateMachine for: " + str);
        this.mTetherStates.remove(str);
    }
}
