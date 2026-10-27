package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.Dumpable;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.util.StringUtil;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NetworkControllerImpl extends BroadcastReceiver implements NetworkController, Dumpable {
    static final boolean DEBUG = true;
    public static final String NONE = "<unknown ssid>";
    public static final String NONE_BSSID = "02:00:00:00:00:00";
    public static String SYS_PRO_MOBILE_SWITCH = "persist.mobile.network.switch";
    static final String TAG = "NetworkControllerImpl";
    private final CallbackHandler mCallbackHandler;
    private final ConnectivityManager mConnectivityManager;
    private final ContentObserver mContentObserver;
    private final Context mContext;
    private int mCurrentUserId;
    boolean mListening;
    private Locale mLocale;
    private final Handler mReceiverHandler;
    private final Runnable mRegisterListeners;
    private final TBoxSignalController mTBoxSignalController;
    private final CurrentUserTracker mUserTracker;
    private final WifiManager mWifiManager;
    final WifiSignalController mWifiSignalController;
    private final BitSet mConnectedTransports = new BitSet();
    private final BitSet mValidatedTransports = new BitSet();

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    public NetworkControllerImpl(Context context, Looper bgLooper) {
        this.mLocale = null;
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.NetworkControllerImpl.4
            @Override // java.lang.Runnable
            public void run() {
                NetworkControllerImpl.this.registerListeners();
            }
        };
        this.mRegisterListeners = runnable;
        ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: com.android.systemui.statusbar.policy.NetworkControllerImpl.5
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                Log.i(NetworkControllerImpl.TAG, "dataEnable onchange selfChange:" + selfChange);
                if (selfChange) {
                    return;
                }
                String string = Settings.Global.getString(NetworkControllerImpl.this.mContext.getContentResolver(), NetworkControllerImpl.SYS_PRO_MOBILE_SWITCH);
                Log.i(NetworkControllerImpl.TAG, "dataEnable onchange s:" + string);
                NetworkControllerImpl.this.mCallbackHandler.setMobileDataEnabled(TextUtils.isEmpty(string) ? true : TextUtils.equals(Constant.CarType.CAR_TYPE_FUEL, string));
            }
        };
        this.mContentObserver = contentObserver;
        this.mContext = context;
        Handler handler = new Handler(bgLooper);
        this.mReceiverHandler = handler;
        CallbackHandler callbackHandler = new CallbackHandler();
        this.mCallbackHandler = callbackHandler;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mConnectivityManager = connectivityManager;
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.mWifiManager = wifiManager;
        this.mLocale = context.getResources().getConfiguration().locale;
        this.mWifiSignalController = new WifiSignalController(context, callbackHandler, this, wifiManager);
        CurrentUserTracker currentUserTracker = new CurrentUserTracker(context) { // from class: com.android.systemui.statusbar.policy.NetworkControllerImpl.1
            @Override // com.android.systemui.settings.CurrentUserTracker
            public void onUserSwitched(int newUserId) {
                Log.i(NetworkControllerImpl.TAG, "newUserId:" + newUserId);
                NetworkControllerImpl.this.onUserSwitched(newUserId);
            }
        };
        this.mUserTracker = currentUserTracker;
        currentUserTracker.startTracking();
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.systemui.statusbar.policy.NetworkControllerImpl.2
            private Network mLastNetwork;
            private NetworkCapabilities mLastNetworkCapabilities;

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                NetworkCapabilities networkCapabilities2 = this.mLastNetworkCapabilities;
                boolean z = networkCapabilities2 != null && networkCapabilities2.hasCapability(16);
                boolean zHasCapability = networkCapabilities.hasCapability(16);
                if (network.equals(this.mLastNetwork) && networkCapabilities.equalsTransportTypes(this.mLastNetworkCapabilities) && zHasCapability == z) {
                    return;
                }
                this.mLastNetwork = network;
                this.mLastNetworkCapabilities = networkCapabilities;
                NetworkControllerImpl.this.updateConnectivity();
            }
        };
        if (context.getContentResolver() != null) {
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(SYS_PRO_MOBILE_SWITCH), false, contentObserver);
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback, handler);
        this.mTBoxSignalController = new TBoxSignalController(context, callbackHandler, this);
        handler.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerListeners() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.conn.INET_CONDITION_ACTION");
        intentFilter.addAction("android.telephony.action.CARRIER_CONFIG_CHANGED");
        this.mContext.registerReceiver(this, intentFilter, null, this.mReceiverHandler);
        this.mListening = true;
        this.mTBoxSignalController.startUp();
        this.mReceiverHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$NetworkControllerImpl$4MbNXZj4hp-woNLdmnPepod-zks
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updateConnectivity();
            }
        });
        Handler handler = this.mReceiverHandler;
        final WifiSignalController wifiSignalController = this.mWifiSignalController;
        Objects.requireNonNull(wifiSignalController);
        handler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$m5COZu8KGjEIVIK38eHP2TsQeGY
            @Override // java.lang.Runnable
            public final void run() {
                wifiSignalController.fetchInitialState();
            }
        });
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.android.systemui.statusbar.policy.NetworkController, com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(NetworkController.SignalCallback cb) {
        this.mCallbackHandler.setListening(cb, true);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.android.systemui.statusbar.policy.NetworkController, com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(NetworkController.SignalCallback cb) {
        this.mCallbackHandler.setListening(cb, false);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.statusbar.policy.NetworkControllerImpl$3] */
    @Override // com.android.systemui.statusbar.policy.NetworkController
    public void setWifiEnabled(final boolean enabled) {
        new AsyncTask<Void, Void, Void>() { // from class: com.android.systemui.statusbar.policy.NetworkControllerImpl.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... args) {
                BydLogHelper.logD("setWifiEnabled = " + enabled);
                NetworkControllerImpl.this.mWifiManager.setWifiEnabled(enabled);
                return null;
            }
        }.execute(new Void[0]);
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public void setMobileDataEnabled(boolean z) {
        BydLogHelper.logD("enabled:" + z);
        Settings.Global.putString(this.mContext.getContentResolver(), SYS_PRO_MOBILE_SWITCH, (!z ? 1 : 0) + "");
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isDataEnabled() {
        boolean zIsDataEnabled;
        String string = Settings.Global.getString(this.mContext.getContentResolver(), SYS_PRO_MOBILE_SWITCH);
        Log.i(TAG, "isDataEnabled s : " + string);
        if (!TextUtils.isEmpty(string)) {
            return TextUtils.equals(Constant.CarType.CAR_TYPE_FUEL, string);
        }
        TBoxSignalController tBoxSignalController = this.mTBoxSignalController;
        if (tBoxSignalController != null) {
            zIsDataEnabled = tBoxSignalController.isDataEnabled();
        } else {
            BydLogHelper.logD("mTBoxSignalController is null");
            zIsDataEnabled = true;
        }
        BydLogHelper.logD("state:" + zIsDataEnabled);
        return zIsDataEnabled;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isDataEnabledFormSetting() {
        String string = Settings.Global.getString(this.mContext.getContentResolver(), SYS_PRO_MOBILE_SWITCH);
        Log.i(TAG, "isDataEnabledFormSetting s : " + string);
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return TextUtils.equals(Constant.CarType.CAR_TYPE_FUEL, string);
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isWifiEnabled() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isWifiConnected() {
        WifiManager wifiManager = this.mWifiManager;
        boolean z = false;
        if (wifiManager == null) {
            BydLogHelper.logD(TAG, "mWifiManager is null");
            return false;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String str = TAG;
        BydLogHelper.logD(str, "--isConnected--SSID：" + StringUtil.desensitizeString(connectionInfo.getSSID(), 0) + ", networkId:" + connectionInfo.getNetworkId());
        if (connectionInfo != null && !NONE.equals(connectionInfo.getSSID()) && connectionInfo.getNetworkId() != -1 && !TextUtils.isEmpty(connectionInfo.getSSID()) && !NONE_BSSID.equals(connectionInfo.getBSSID()) && connectionInfo.getLinkSpeed() >= 0) {
            z = true;
        }
        BydLogHelper.logD(str, "--isConnected--connected：" + z);
        return z;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public String getWifiConnectedName() {
        WifiManager wifiManager = this.mWifiManager;
        if (wifiManager == null) {
            BydLogHelper.logD(TAG, "mWifiManager is null");
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        return connectionInfo == null ? "" : removeDoubleQuotes(connectionInfo.getSSID());
    }

    private String removeDoubleQuotes(String str) {
        if (str == null) {
            return "";
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public void getState() {
        BydLogHelper.logD();
        this.mWifiSignalController.notifyListeners();
        this.mTBoxSignalController.notifyListeners();
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public SignalController.State getTBoxState() {
        TBoxSignalController tBoxSignalController = this.mTBoxSignalController;
        if (tBoxSignalController != null) {
            return tBoxSignalController.getState();
        }
        return null;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isOnline() {
        TBoxSignalController tBoxSignalController = this.mTBoxSignalController;
        if (tBoxSignalController != null) {
            return tBoxSignalController.isOnline();
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.policy.NetworkController
    public boolean isShowTelephoneData() {
        TBoxSignalController tBoxSignalController = this.mTBoxSignalController;
        if (tBoxSignalController == null) {
            return false;
        }
        String externalIccid = tBoxSignalController.getExternalIccid();
        BydLogHelper.logD(TAG, "isShowTelephoneData externalIccid : " + StringUtil.desensitizeString(externalIccid, 0));
        return (externalIccid == null || externalIccid.length() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onUserSwitched(int newUserId) {
        this.mCurrentUserId = newUserId;
        updateConnectivity();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logD("action = " + action);
        action.hashCode();
        switch (action) {
            case "android.net.conn.CONNECTIVITY_CHANGE":
            case "android.net.conn.INET_CONDITION_ACTION":
                updateConnectivity();
                break;
            case "android.telephony.action.CARRIER_CONFIG_CHANGED":
                this.mReceiverHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$jKvTXurg-JOZO6Evg8o1_GZzndE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.handleConfigurationChanged();
                    }
                });
                break;
            default:
                this.mWifiSignalController.handleBroadcast(intent);
                break;
        }
    }

    void handleConfigurationChanged() {
        refreshLocale();
    }

    private void refreshLocale() {
        Locale locale = this.mContext.getResources().getConfiguration().locale;
        if (locale.equals(this.mLocale)) {
            return;
        }
        this.mLocale = locale;
        notifyAllListeners();
    }

    private void notifyAllListeners() {
        this.mWifiSignalController.notifyListeners();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectivity() {
        this.mConnectedTransports.clear();
        this.mValidatedTransports.clear();
        for (NetworkCapabilities networkCapabilities : this.mConnectivityManager.getDefaultNetworkCapabilitiesForUser(this.mCurrentUserId)) {
            for (int i : networkCapabilities.getTransportTypes()) {
                BydLogHelper.logD("transportType:" + i);
                this.mConnectedTransports.set(i);
                BydLogHelper.logD("hasCapability:" + networkCapabilities.hasCapability(16));
                if (networkCapabilities.hasCapability(16)) {
                    this.mValidatedTransports.set(i);
                }
            }
        }
        pushConnectivityToSignals();
    }

    private void pushConnectivityToSignals() {
        this.mWifiSignalController.updateConnectivity(this.mConnectedTransports, this.mValidatedTransports);
    }
}
