package com.android.car.developeroptions.wifi.details;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.core.text.BidiFormatter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.datausage.WifiDataUsageSummaryPreferenceController;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.car.developeroptions.wifi.WifiDialog;
import com.android.car.developeroptions.wifi.WifiUtils;
import com.android.car.developeroptions.wifi.dpp.WifiDppUtils;
import com.android.car.developeroptions.wifi.savedaccesspoints.SavedAccessPointsWifiSettings;
import com.android.settingslib.Utils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.widget.ActionButtonsPreference;
import com.android.settingslib.widget.LayoutPreference;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.WifiTracker;
import com.android.settingslib.wifi.WifiTrackerFactory;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class WifiDetailPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, WifiDialog.WifiDialogListener, LifecycleObserver, OnPause, OnResume {
    static final String KEY_BUTTONS_PREF = "buttons";
    static final String KEY_DATA_USAGE_HEADER = "status_header";
    static final String KEY_DNS_PREF = "dns";
    static final String KEY_FREQUENCY_PREF = "frequency";
    static final String KEY_GATEWAY_PREF = "gateway";
    static final String KEY_HEADER = "connection_header";
    static final String KEY_IPV6_ADDRESSES_PREF = "ipv6_addresses";
    static final String KEY_IPV6_CATEGORY = "ipv6_category";
    static final String KEY_IP_ADDRESS_PREF = "ip_address";
    static final String KEY_MAC_ADDRESS_PREF = "mac_address";
    static final String KEY_RX_LINK_SPEED = "rx_link_speed";
    static final String KEY_SECURITY_PREF = "security";
    static final String KEY_SIGNAL_STRENGTH_PREF = "signal_strength";
    static final String KEY_SSID_PREF = "ssid";
    static final String KEY_SUBNET_MASK_PREF = "subnet_mask";
    static final String KEY_TX_LINK_SPEED = "tx_link_speed";
    private static CountDownTimer mTimer;
    private AccessPoint mAccessPoint;
    private ActionButtonsPreference mButtonsPref;
    private WifiManager.ActionListener mConnectListener;
    private boolean mConnected;
    private int mConnectingState;
    private final ConnectivityManager mConnectivityManager;
    Preference mDataUsageSummaryPref;
    private Preference mDnsPref;
    private EntityHeaderController mEntityHeaderController;
    private final IntentFilter mFilter;
    private final Fragment mFragment;
    private Preference mFrequencyPref;
    private Preference mGatewayPref;
    private final Handler mHandler;
    private final IconInjector mIconInjector;
    private Preference mIpAddressPref;
    private Preference mIpv6AddressPref;
    private PreferenceCategory mIpv6Category;
    private boolean mIsEphemeral;
    private boolean mIsOutOfRange;
    private Lifecycle mLifecycle;
    private LinkProperties mLinkProperties;
    private Preference mMacAddressPref;
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private Network mNetwork;
    private final ConnectivityManager.NetworkCallback mNetworkCallback;
    private NetworkCapabilities mNetworkCapabilities;
    private NetworkInfo mNetworkInfo;
    private final NetworkRequest mNetworkRequest;
    private final BroadcastReceiver mReceiver;
    private int mRssiSignalLevel;
    private Preference mRxLinkSpeedPref;
    private Preference mSecurityPref;
    private String[] mSignalStr;
    private Preference mSignalStrengthPref;
    private Preference mSsidPref;
    private Preference mSubnetPref;
    WifiDataUsageSummaryPreferenceController mSummaryHeaderController;
    private Preference mTxLinkSpeedPref;
    private WifiConfiguration mWifiConfig;
    private WifiInfo mWifiInfo;
    private final WifiTracker.WifiListener mWifiListener;
    private final WifiManager mWifiManager;
    private final WifiTracker mWifiTracker;
    private static final boolean DEBUG = Log.isLoggable("WifiDetailsPrefCtrl", 3);
    private static final long TIMEOUT = Duration.ofSeconds(10).toMillis();

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public static WifiDetailPreferenceController newInstance(AccessPoint accessPoint, ConnectivityManager connectivityManager, Context context, Fragment fragment, Handler handler, Lifecycle lifecycle, WifiManager wifiManager, MetricsFeatureProvider metricsFeatureProvider) {
        return new WifiDetailPreferenceController(accessPoint, connectivityManager, context, fragment, handler, lifecycle, wifiManager, metricsFeatureProvider, new IconInjector(context));
    }

    WifiDetailPreferenceController(AccessPoint accessPoint, ConnectivityManager connectivityManager, Context context, Fragment fragment, Handler handler, Lifecycle lifecycle, WifiManager wifiManager, MetricsFeatureProvider metricsFeatureProvider, IconInjector iconInjector) {
        super(context);
        this.mRssiSignalLevel = -1;
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.1
            /* JADX WARN: Removed duplicated region for block: B:18:0x0038  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r4, android.content.Intent r5) {
                /*
                    r3 = this;
                    java.lang.String r4 = r5.getAction()
                    int r5 = r4.hashCode()
                    r0 = -385684331(0xffffffffe902ec95, float:-9.892349E24)
                    r1 = 2
                    r2 = 1
                    if (r5 == r0) goto L2e
                    r0 = -343630553(0xffffffffeb849d27, float:-3.2064068E26)
                    if (r5 == r0) goto L24
                    r0 = 1625920338(0x60e99352, float:1.3464709E20)
                    if (r5 == r0) goto L1a
                    goto L38
                L1a:
                    java.lang.String r5 = "android.net.wifi.CONFIGURED_NETWORKS_CHANGE"
                    boolean r4 = r4.equals(r5)
                    if (r4 == 0) goto L38
                    r4 = 0
                    goto L39
                L24:
                    java.lang.String r5 = "android.net.wifi.STATE_CHANGE"
                    boolean r4 = r4.equals(r5)
                    if (r4 == 0) goto L38
                    r4 = r2
                    goto L39
                L2e:
                    java.lang.String r5 = "android.net.wifi.RSSI_CHANGED"
                    boolean r4 = r4.equals(r5)
                    if (r4 == 0) goto L38
                    r4 = r1
                    goto L39
                L38:
                    r4 = -1
                L39:
                    if (r4 == 0) goto L40
                    if (r4 == r2) goto L43
                    if (r4 == r1) goto L43
                    goto L48
                L40:
                    r3.updateMatchingWifiConfig()
                L43:
                    com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController r3 = com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.this
                    com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.access$000(r3)
                L48:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }

            private void updateMatchingWifiConfig() {
                for (WifiConfiguration wifiConfiguration : WifiDetailPreferenceController.this.mWifiManager.getPrivilegedConfiguredNetworks()) {
                    if (WifiDetailPreferenceController.this.mAccessPoint.matches(wifiConfiguration)) {
                        WifiDetailPreferenceController.this.mWifiConfig = wifiConfiguration;
                        return;
                    }
                }
            }
        };
        this.mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addTransportType(1).build();
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                if (!network.equals(WifiDetailPreferenceController.this.mNetwork) || linkProperties.equals(WifiDetailPreferenceController.this.mLinkProperties)) {
                    return;
                }
                WifiDetailPreferenceController.this.mLinkProperties = linkProperties;
                WifiDetailPreferenceController.this.refreshIpLayerInfo();
            }

            private boolean hasCapabilityChanged(NetworkCapabilities networkCapabilities, int i) {
                return WifiDetailPreferenceController.this.mNetworkCapabilities == null || WifiDetailPreferenceController.this.mNetworkCapabilities.hasCapability(i) != networkCapabilities.hasCapability(i);
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                if (!network.equals(WifiDetailPreferenceController.this.mNetwork) || networkCapabilities.equals(WifiDetailPreferenceController.this.mNetworkCapabilities)) {
                    return;
                }
                if (hasCapabilityChanged(networkCapabilities, 16) || hasCapabilityChanged(networkCapabilities, 17) || hasCapabilityChanged(networkCapabilities, 24)) {
                    WifiDetailPreferenceController.this.mAccessPoint.update(WifiDetailPreferenceController.this.mWifiConfig, WifiDetailPreferenceController.this.mWifiInfo, WifiDetailPreferenceController.this.mNetworkInfo);
                    WifiDetailPreferenceController.this.refreshEntityHeader();
                }
                WifiDetailPreferenceController.this.mNetworkCapabilities = networkCapabilities;
                WifiDetailPreferenceController.this.refreshButtons();
                WifiDetailPreferenceController.this.refreshIpLayerInfo();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                if (network.equals(WifiDetailPreferenceController.this.mNetwork)) {
                    if (!SavedAccessPointsWifiSettings.usingDetailsFragment(((AbstractPreferenceController) WifiDetailPreferenceController.this).mContext) || WifiDetailPreferenceController.this.mIsEphemeral) {
                        WifiDetailPreferenceController.this.exitActivity();
                    }
                }
            }
        };
        this.mWifiListener = new WifiTracker.WifiListener() { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.3
            @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
            public void onWifiStateChanged(int i) {
                Log.d("WifiDetailsPrefCtrl", "onWifiStateChanged(" + i + ")");
                if (WifiDetailPreferenceController.this.mConnectingState != 2 || i != 3) {
                    if (WifiDetailPreferenceController.this.mConnectingState == 1 || i != 1) {
                        return;
                    }
                    WifiDetailPreferenceController.this.updateConnectingState(8);
                    return;
                }
                WifiDetailPreferenceController.this.updateConnectingState(4);
            }

            @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
            public void onConnectedChanged() {
                WifiDetailPreferenceController.this.updateAccessPointFromScannedList();
                if (WifiDetailPreferenceController.this.mConnected != WifiDetailPreferenceController.this.mAccessPoint.isActive()) {
                    Log.d("WifiDetailsPrefCtrl", "Connection state changed!");
                    WifiDetailPreferenceController wifiDetailPreferenceController = WifiDetailPreferenceController.this;
                    wifiDetailPreferenceController.mConnected = wifiDetailPreferenceController.mAccessPoint.isActive();
                    if (WifiDetailPreferenceController.this.mAccessPoint.isActive()) {
                        WifiDetailPreferenceController.this.updateConnectingState(5);
                    } else {
                        WifiDetailPreferenceController.this.updateConnectingState(8);
                    }
                }
            }

            @Override // com.android.settingslib.wifi.WifiTracker.WifiListener
            public void onAccessPointsChanged() {
                WifiDetailPreferenceController.this.refreshPage();
            }
        };
        this.mAccessPoint = accessPoint;
        this.mConnectivityManager = connectivityManager;
        this.mFragment = fragment;
        this.mHandler = handler;
        this.mSignalStr = context.getResources().getStringArray(R.array.wifi_signal);
        this.mWifiConfig = accessPoint.getConfig();
        this.mWifiManager = wifiManager;
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mIconInjector = iconInjector;
        IntentFilter intentFilter = new IntentFilter();
        this.mFilter = intentFilter;
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        this.mLifecycle = lifecycle;
        lifecycle.addObserver(this);
        if (SavedAccessPointsWifiSettings.usingDetailsFragment(this.mContext)) {
            this.mWifiTracker = WifiTrackerFactory.create(this.mFragment.getActivity(), this.mWifiListener, this.mLifecycle, true, true);
        } else {
            this.mWifiTracker = null;
        }
        this.mConnected = this.mAccessPoint.isActive();
        this.mIsEphemeral = this.mAccessPoint.isEphemeral();
        this.mConnectingState = 1;
        this.mConnectListener = new WifiManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.4
            public void onSuccess() {
            }

            public void onFailure(int i) {
                WifiDetailPreferenceController.this.updateConnectingState(6);
            }
        };
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        setupEntityHeader(preferenceScreen);
        ActionButtonsPreference actionButtonsPreference = (ActionButtonsPreference) preferenceScreen.findPreference(KEY_BUTTONS_PREF);
        actionButtonsPreference.setButton1Text(R.string.forget);
        actionButtonsPreference.setButton1Icon(R.drawable.ic_settings_delete);
        actionButtonsPreference.setButton1OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$WcvZNzpUryGRAETLv0au4UXQw7w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$0$WifiDetailPreferenceController(view);
            }
        });
        actionButtonsPreference.setButton2Text(R.string.wifi_sign_in_button_text);
        actionButtonsPreference.setButton2Icon(R.drawable.ic_settings_sign_in);
        actionButtonsPreference.setButton2OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$SLKZsl0N-2RiBD-5xfBZyG5Mzfw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$1$WifiDetailPreferenceController(view);
            }
        });
        actionButtonsPreference.setButton3Text(R.string.wifi_connect);
        actionButtonsPreference.setButton3Icon(R.drawable.ic_settings_wireless);
        actionButtonsPreference.setButton3OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$vY1zjt7dGf_d-7taNl8AlF9uyD8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$2$WifiDetailPreferenceController(view);
            }
        });
        actionButtonsPreference.setButton3Enabled(true);
        actionButtonsPreference.setButton4Text(R.string.share);
        actionButtonsPreference.setButton4Icon(R.drawable.ic_qrcode_24dp);
        actionButtonsPreference.setButton4OnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$WwBgnqsZsdtVSBMP4IRpQaa5e60
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$3$WifiDetailPreferenceController(view);
            }
        });
        actionButtonsPreference.setButton4Visible(WifiDppUtils.isSupportConfiguratorQrCodeGenerator(this.mContext, this.mAccessPoint));
        this.mButtonsPref = actionButtonsPreference;
        this.mSignalStrengthPref = preferenceScreen.findPreference(KEY_SIGNAL_STRENGTH_PREF);
        this.mTxLinkSpeedPref = preferenceScreen.findPreference(KEY_TX_LINK_SPEED);
        this.mRxLinkSpeedPref = preferenceScreen.findPreference(KEY_RX_LINK_SPEED);
        this.mFrequencyPref = preferenceScreen.findPreference(KEY_FREQUENCY_PREF);
        this.mSecurityPref = preferenceScreen.findPreference(KEY_SECURITY_PREF);
        this.mSsidPref = preferenceScreen.findPreference(KEY_SSID_PREF);
        this.mMacAddressPref = preferenceScreen.findPreference(KEY_MAC_ADDRESS_PREF);
        this.mIpAddressPref = preferenceScreen.findPreference(KEY_IP_ADDRESS_PREF);
        this.mGatewayPref = preferenceScreen.findPreference(KEY_GATEWAY_PREF);
        this.mSubnetPref = preferenceScreen.findPreference(KEY_SUBNET_MASK_PREF);
        this.mDnsPref = preferenceScreen.findPreference(KEY_DNS_PREF);
        this.mIpv6Category = (PreferenceCategory) preferenceScreen.findPreference(KEY_IPV6_CATEGORY);
        this.mIpv6AddressPref = preferenceScreen.findPreference(KEY_IPV6_ADDRESSES_PREF);
        this.mSecurityPref.setSummary(this.mAccessPoint.getSecurityString(false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$WifiDetailPreferenceController(View view) {
        forgetNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$1$WifiDetailPreferenceController(View view) {
        signIntoNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$2$WifiDetailPreferenceController(View view) {
        connectNetwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$3$WifiDetailPreferenceController(View view) {
        shareNetwork();
    }

    private void setupEntityHeader(PreferenceScreen preferenceScreen) {
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(KEY_HEADER);
        if (usingDataUsageHeader(this.mContext)) {
            layoutPreference.setVisible(false);
            Preference preferenceFindPreference = preferenceScreen.findPreference(KEY_DATA_USAGE_HEADER);
            this.mDataUsageSummaryPref = preferenceFindPreference;
            preferenceFindPreference.setVisible(true);
            this.mSummaryHeaderController = new WifiDataUsageSummaryPreferenceController(this.mFragment.getActivity(), this.mLifecycle, (PreferenceFragmentCompat) this.mFragment, this.mAccessPoint.getSsid());
            return;
        }
        this.mEntityHeaderController = EntityHeaderController.newInstance(this.mFragment.getActivity(), this.mFragment, layoutPreference.findViewById(R.id.entity_header));
        ((ImageView) layoutPreference.findViewById(R.id.entity_header_icon)).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.mEntityHeaderController.setLabel(this.mAccessPoint.getTitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshEntityHeader() {
        if (usingDataUsageHeader(this.mContext)) {
            this.mSummaryHeaderController.updateState(this.mDataUsageSummaryPref);
            return;
        }
        EntityHeaderController entityHeaderController = this.mEntityHeaderController;
        entityHeaderController.setSummary(this.mAccessPoint.getSettingsSummary());
        entityHeaderController.done((Activity) this.mFragment.getActivity(), true);
    }

    private void updateNetworkInfo() {
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        this.mNetwork = currentNetwork;
        this.mLinkProperties = this.mConnectivityManager.getLinkProperties(currentNetwork);
        this.mNetworkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mNetwork);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        updateNetworkInfo();
        refreshPage();
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mHandler);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mNetwork = null;
        this.mLinkProperties = null;
        this.mNetworkCapabilities = null;
        this.mNetworkInfo = null;
        this.mWifiInfo = null;
        this.mContext.unregisterReceiver(this.mReceiver);
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPage() {
        if (updateAccessPoint()) {
            Log.d("WifiDetailsPrefCtrl", "Update UI!");
            refreshEntityHeader();
            refreshButtons();
            refreshRssiViews();
            refreshFrequency();
            refreshTxSpeed();
            refreshRxSpeed();
            refreshIpLayerInfo();
            refreshSsid();
            refreshMacAddress();
        }
    }

    private boolean updateAccessPoint() {
        boolean z;
        NetworkInfo networkInfo;
        if (this.mWifiTracker != null) {
            boolean z2 = this.mIsOutOfRange;
            updateAccessPointFromScannedList();
            z = (z2 != this.mIsOutOfRange) | (this.mRssiSignalLevel != this.mAccessPoint.getLevel());
        } else {
            z = false;
        }
        if (!this.mAccessPoint.isActive()) {
            return z;
        }
        if (this.mNetwork == null) {
            updateNetworkInfo();
        }
        this.mNetworkInfo = this.mConnectivityManager.getNetworkInfo(this.mNetwork);
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        this.mWifiInfo = connectionInfo;
        if (this.mNetwork != null && (networkInfo = this.mNetworkInfo) != null && connectionInfo != null) {
            return this.mWifiTracker == null || z || this.mAccessPoint.update(this.mWifiConfig, connectionInfo, networkInfo);
        }
        if (SavedAccessPointsWifiSettings.usingDetailsFragment(this.mContext)) {
            return false;
        }
        exitActivity();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAccessPointFromScannedList() {
        if (this.mWifiTracker == null) {
            return;
        }
        this.mIsOutOfRange = true;
        if (this.mAccessPoint.getConfig() == null) {
            return;
        }
        for (AccessPoint accessPoint : this.mWifiTracker.getAccessPoints()) {
            if (accessPoint.getConfig() != null && this.mAccessPoint.matches(accessPoint.getConfig())) {
                this.mAccessPoint = accessPoint;
                this.mIsOutOfRange = true ^ accessPoint.isReachable();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exitActivity() {
        if (DEBUG) {
            Log.d("WifiDetailsPrefCtrl", "Exiting the WifiNetworkDetailsPage");
        }
        this.mFragment.getActivity().finish();
    }

    private void refreshRssiViews() {
        int level = this.mAccessPoint.getLevel();
        if (this.mIsOutOfRange) {
            this.mSignalStrengthPref.setVisible(false);
            this.mRssiSignalLevel = -1;
            return;
        }
        if (this.mRssiSignalLevel == level) {
            return;
        }
        this.mRssiSignalLevel = level;
        Drawable icon = this.mIconInjector.getIcon(level);
        EntityHeaderController entityHeaderController = this.mEntityHeaderController;
        if (entityHeaderController != null) {
            entityHeaderController.setIcon(redrawIconForHeader(icon));
            entityHeaderController.done((Activity) this.mFragment.getActivity(), true);
        }
        Drawable drawableMutate = icon.getConstantState().newDrawable().mutate();
        drawableMutate.setTintList(Utils.getColorAttr(this.mContext, android.R.attr.colorControlNormal));
        this.mSignalStrengthPref.setIcon(drawableMutate);
        this.mSignalStrengthPref.setSummary(this.mSignalStr[this.mRssiSignalLevel]);
        this.mSignalStrengthPref.setVisible(true);
    }

    private Drawable redrawIconForHeader(Drawable drawable) {
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.wifi_detail_page_header_image_size);
        int minimumWidth = drawable.getMinimumWidth();
        int minimumHeight = drawable.getMinimumHeight();
        if ((minimumWidth == dimensionPixelSize && minimumHeight == dimensionPixelSize) || !VectorDrawable.class.isInstance(drawable)) {
            return drawable;
        }
        drawable.setTintList(null);
        BitmapDrawable bitmapDrawable = new BitmapDrawable((Resources) null, com.android.car.developeroptions.Utils.createBitmap(drawable, dimensionPixelSize, dimensionPixelSize));
        bitmapDrawable.setTintList(Utils.getColorAttr(this.mContext, android.R.attr.textColorPrimary));
        return bitmapDrawable;
    }

    private void refreshFrequency() {
        String string;
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mFrequencyPref.setVisible(false);
            return;
        }
        int frequency = wifiInfo.getFrequency();
        if (frequency >= 2400 && frequency < 2500) {
            string = this.mContext.getResources().getString(R.string.wifi_band_24ghz);
        } else if (frequency >= 4900 && frequency < 5900) {
            string = this.mContext.getResources().getString(R.string.wifi_band_5ghz);
        } else {
            Log.e("WifiDetailsPrefCtrl", "Unexpected frequency " + frequency);
            if (this.mConnectingState == 4) {
                this.mFrequencyPref.setVisible(false);
                return;
            }
            return;
        }
        this.mFrequencyPref.setSummary(string);
        this.mFrequencyPref.setVisible(true);
    }

    private void refreshTxSpeed() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mTxLinkSpeedPref.setVisible(false);
            return;
        }
        this.mTxLinkSpeedPref.setVisible(wifiInfo.getTxLinkSpeedMbps() >= 0);
        this.mTxLinkSpeedPref.setSummary(this.mContext.getString(R.string.tx_link_speed, Integer.valueOf(this.mWifiInfo.getTxLinkSpeedMbps())));
    }

    private void refreshRxSpeed() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            this.mRxLinkSpeedPref.setVisible(false);
            return;
        }
        this.mRxLinkSpeedPref.setVisible(wifiInfo.getRxLinkSpeedMbps() >= 0);
        this.mRxLinkSpeedPref.setSummary(this.mContext.getString(R.string.rx_link_speed, Integer.valueOf(this.mWifiInfo.getRxLinkSpeedMbps())));
    }

    private void refreshSsid() {
        if (this.mAccessPoint.isPasspoint() || this.mAccessPoint.isOsuProvider()) {
            this.mSsidPref.setVisible(true);
            this.mSsidPref.setSummary(this.mAccessPoint.getSsidStr());
        } else {
            this.mSsidPref.setVisible(false);
        }
    }

    private void refreshMacAddress() {
        String macAddress = getMacAddress();
        if (macAddress == null) {
            this.mMacAddressPref.setVisible(false);
        } else {
            this.mMacAddressPref.setVisible(true);
            this.mMacAddressPref.setSummary(macAddress);
        }
    }

    private String getMacAddress() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null && wifiConfiguration.macRandomizationSetting == 1) {
            return wifiConfiguration.getRandomizedMacAddress().toString();
        }
        String[] factoryMacAddresses = this.mWifiManager.getFactoryMacAddresses();
        if (factoryMacAddresses != null && factoryMacAddresses.length > 0) {
            return factoryMacAddresses[0];
        }
        Log.e("WifiDetailsPrefCtrl", "Can't get device MAC address!");
        return null;
    }

    private void updatePreference(Preference preference, String str) {
        if (!TextUtils.isEmpty(str)) {
            preference.setSummary(str);
            preference.setVisible(true);
        } else {
            preference.setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshButtons() {
        this.mButtonsPref.setButton1Text(this.mIsEphemeral ? R.string.wifi_disconnect_button_text : R.string.forget);
        this.mButtonsPref.setButton1Visible(canForgetNetwork());
        this.mButtonsPref.setButton2Visible(canSignIntoNetwork());
        this.mButtonsPref.setButton3Visible(canConnectNetwork());
        this.mButtonsPref.setButton4Visible(canShareNetwork());
        this.mButtonsPref.setVisible(canSignIntoNetwork() || canForgetNetwork() || canShareNetwork() || canConnectNetwork());
    }

    private boolean canConnectNetwork() {
        return SavedAccessPointsWifiSettings.usingDetailsFragment(this.mContext) && !this.mAccessPoint.isActive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshIpLayerInfo() {
        if (!this.mAccessPoint.isActive() || this.mNetwork == null || this.mLinkProperties == null) {
            this.mIpAddressPref.setVisible(false);
            this.mSubnetPref.setVisible(false);
            this.mGatewayPref.setVisible(false);
            this.mDnsPref.setVisible(false);
            this.mIpv6Category.setVisible(false);
            return;
        }
        StringJoiner stringJoiner = new StringJoiner("\n");
        String hostAddress = null;
        String hostAddress2 = null;
        String strIpv4PrefixLengthToSubnetMask = null;
        for (LinkAddress linkAddress : this.mLinkProperties.getLinkAddresses()) {
            if (linkAddress.getAddress() instanceof Inet4Address) {
                hostAddress2 = linkAddress.getAddress().getHostAddress();
                strIpv4PrefixLengthToSubnetMask = ipv4PrefixLengthToSubnetMask(linkAddress.getPrefixLength());
            } else if (linkAddress.getAddress() instanceof Inet6Address) {
                stringJoiner.add(linkAddress.getAddress().getHostAddress());
            }
        }
        Iterator<RouteInfo> it = this.mLinkProperties.getRoutes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RouteInfo next = it.next();
            if (next.isIPv4Default() && next.hasGateway()) {
                hostAddress = next.getGateway().getHostAddress();
                break;
            }
        }
        String str = (String) this.mLinkProperties.getDnsServers().stream().map(new Function() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$XZAGhHrbkIDyusER4MAM6luKcT0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InetAddress) obj).getHostAddress();
            }
        }).collect(Collectors.joining("\n"));
        updatePreference(this.mIpAddressPref, hostAddress2);
        updatePreference(this.mSubnetPref, strIpv4PrefixLengthToSubnetMask);
        updatePreference(this.mGatewayPref, hostAddress);
        updatePreference(this.mDnsPref, str);
        if (stringJoiner.length() > 0) {
            this.mIpv6AddressPref.setSummary(BidiFormatter.getInstance().unicodeWrap(stringJoiner.toString()));
            this.mIpv6Category.setVisible(true);
        } else {
            this.mIpv6Category.setVisible(false);
        }
    }

    private static String ipv4PrefixLengthToSubnetMask(int i) {
        try {
            return NetworkUtils.getNetworkPart(InetAddress.getByAddress(new byte[]{-1, -1, -1, -1}), i).getHostAddress();
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private boolean canForgetNetwork() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return (wifiInfo != null && wifiInfo.isEphemeral()) || canModifyNetwork();
    }

    public boolean canModifyNetwork() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return (wifiConfiguration == null || WifiUtils.isNetworkLockedDown(this.mContext, wifiConfiguration)) ? false : true;
    }

    private boolean canSignIntoNetwork() {
        return this.mAccessPoint.isActive() && WifiUtils.canSignIntoNetwork(this.mNetworkCapabilities);
    }

    private boolean canShareNetwork() {
        return this.mAccessPoint.getConfig() != null;
    }

    private void forgetNetwork() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo != null && wifiInfo.isEphemeral()) {
            this.mWifiManager.disableEphemeralNetwork(this.mWifiInfo.getSSID());
        } else {
            WifiConfiguration wifiConfiguration = this.mWifiConfig;
            if (wifiConfiguration != null) {
                if (wifiConfiguration.isPasspoint()) {
                    if (FeatureFlagPersistent.isEnabled(this.mContext, "settings_network_and_internet_v2")) {
                        showConfirmForgetDialog();
                        return;
                    }
                    this.mWifiManager.removePasspointConfiguration(this.mWifiConfig.FQDN);
                } else {
                    this.mWifiManager.forget(this.mWifiConfig.networkId, null);
                }
            }
        }
        this.mMetricsFeatureProvider.action(this.mFragment.getActivity(), 137, new Pair[0]);
        this.mFragment.getActivity().finish();
    }

    protected void showConfirmForgetDialog() {
        new AlertDialog.Builder(this.mContext).setPositiveButton(R.string.forget, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$1xnFP7WkptEl8oJJ2PHsg0goH2w
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showConfirmForgetDialog$4$WifiDetailPreferenceController(dialogInterface, i);
            }
        }).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setTitle(R.string.wifi_forget_dialog_title).setMessage(R.string.forget_passpoint_dialog_message).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showConfirmForgetDialog$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$showConfirmForgetDialog$4$WifiDetailPreferenceController(DialogInterface dialogInterface, int i) {
        this.mWifiManager.removePasspointConfiguration(this.mWifiConfig.FQDN);
        this.mMetricsFeatureProvider.action(this.mFragment.getActivity(), 137, new Pair[0]);
        this.mFragment.getActivity().finish();
    }

    /* JADX INFO: renamed from: launchWifiDppConfiguratorActivity, reason: merged with bridge method [inline-methods] */
    public void lambda$shareNetwork$5$WifiDetailPreferenceController() {
        Intent configuratorQrCodeGeneratorIntentOrNull = WifiDppUtils.getConfiguratorQrCodeGeneratorIntentOrNull(this.mContext, this.mWifiManager, this.mAccessPoint);
        if (configuratorQrCodeGeneratorIntentOrNull == null) {
            Log.e("WifiDetailsPrefCtrl", "Launch Wi-Fi DPP QR code generator with a wrong Wi-Fi network!");
        } else {
            this.mContext.startActivity(configuratorQrCodeGeneratorIntentOrNull);
        }
    }

    private void shareNetwork() {
        WifiDppUtils.showLockScreen(this.mContext, new Runnable() { // from class: com.android.car.developeroptions.wifi.details.-$$Lambda$WifiDetailPreferenceController$FdUZLuCnAyVO9y91lF7Zbs2fCD4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$shareNetwork$5$WifiDetailPreferenceController();
            }
        });
    }

    private void signIntoNetwork() {
        this.mMetricsFeatureProvider.action(this.mFragment.getActivity(), 1008, new Pair[0]);
        this.mConnectivityManager.startCaptivePortalApp(this.mNetwork);
    }

    @Override // com.android.car.developeroptions.wifi.WifiDialog.WifiDialogListener
    public void onSubmit(WifiDialog wifiDialog) {
        if (wifiDialog.getController() != null) {
            this.mWifiManager.save(wifiDialog.getController().getConfig(), new WifiManager.ActionListener() { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.5
                public void onSuccess() {
                }

                public void onFailure(int i) {
                    FragmentActivity activity = WifiDetailPreferenceController.this.mFragment.getActivity();
                    if (activity != null) {
                        Toast.makeText(activity, R.string.wifi_failed_save_message, 0).show();
                    }
                }
            });
        }
    }

    static class IconInjector {
        private final Context mContext;

        public IconInjector(Context context) {
            this.mContext = context;
        }

        public Drawable getIcon(int i) {
            return this.mContext.getDrawable(Utils.getWifiIconResource(i)).mutate();
        }
    }

    private boolean usingDataUsageHeader(Context context) {
        return FeatureFlagUtils.isEnabled(context, "settings_wifi_details_datausage_header");
    }

    private void connectNetwork() {
        FragmentActivity activity = this.mFragment.getActivity();
        if (this.mWifiConfig == null) {
            Toast.makeText(activity, R.string.wifi_failed_connect_message, 0).show();
            return;
        }
        this.mConnectingState = 1;
        if (this.mWifiManager.isWifiEnabled()) {
            updateConnectingState(4);
        } else {
            updateConnectingState(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectingState(int i) {
        FragmentActivity activity = this.mFragment.getActivity();
        Log.d("WifiDetailsPrefCtrl", "updateConnectingState from " + this.mConnectingState + " to " + i);
        int i2 = this.mConnectingState;
        if (i2 == 1 || i2 == 2) {
            if (i == 2) {
                Log.d("WifiDetailsPrefCtrl", "Turn on Wi-Fi automatically!");
                updateConnectedButton(2);
                Toast.makeText(activity, R.string.wifi_turned_on_message, 0).show();
                this.mWifiManager.setWifiEnabled(true);
                startTimer();
            } else if (i == 4) {
                Log.d("WifiDetailsPrefCtrl", "connecting...");
                updateConnectedButton(4);
                this.mWifiManager.connect(this.mWifiConfig.networkId, this.mConnectListener);
                startTimer();
            } else if (i == 3) {
                Log.e("WifiDetailsPrefCtrl", "Wi-Fi failed to enable network!");
                stopTimer();
                Toast.makeText(activity, R.string.wifi_failed_connect_message, 0).show();
                updateConnectedButton(3);
                i = 1;
            }
        } else {
            if (i2 == 4) {
                if (i == 5) {
                    Log.d("WifiDetailsPrefCtrl", "connected");
                    stopTimer();
                    updateConnectedButton(5);
                    Toast.makeText(activity, this.mContext.getString(R.string.wifi_connected_to_message, this.mAccessPoint.getTitle()), 0).show();
                    updateNetworkInfo();
                    refreshPage();
                } else {
                    if (i == 7) {
                        Log.d("WifiDetailsPrefCtrl", "AP not in range");
                        stopTimer();
                        Toast.makeText(activity, R.string.wifi_not_in_range_message, 0).show();
                        updateConnectedButton(7);
                    } else if (i == 6) {
                        Log.d("WifiDetailsPrefCtrl", "failed");
                        stopTimer();
                        Toast.makeText(activity, R.string.wifi_failed_connect_message, 0).show();
                        updateConnectedButton(6);
                    }
                    i = 1;
                }
                this.mConnectingState = i;
            }
            if (i2 != 5) {
                Log.e("WifiDetailsPrefCtrl", "Invalid state : " + this.mConnectingState);
                return;
            }
        }
        if (i == 8) {
            Log.d("WifiDetailsPrefCtrl", "disconnected");
            updateConnectedButton(8);
            refreshPage();
            this.mWifiInfo = null;
            i = 1;
        }
        this.mConnectingState = i;
    }

    private void updateConnectedButton(int i) {
        switch (i) {
            case 2:
            case 4:
                ActionButtonsPreference actionButtonsPreference = this.mButtonsPref;
                actionButtonsPreference.setButton3Text(R.string.wifi_connecting);
                actionButtonsPreference.setButton3Enabled(false);
                break;
            case 3:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
            case 7:
            case 8:
                ActionButtonsPreference actionButtonsPreference2 = this.mButtonsPref;
                actionButtonsPreference2.setButton3Text(R.string.wifi_connect);
                actionButtonsPreference2.setButton3Icon(R.drawable.ic_settings_wireless);
                actionButtonsPreference2.setButton3Enabled(true);
                actionButtonsPreference2.setButton3Visible(true);
                break;
            case 5:
                this.mButtonsPref.setButton3Visible(false);
                break;
            default:
                Log.e("WifiDetailsPrefCtrl", "Invalid connect button state : " + i);
                break;
        }
    }

    private void startTimer() {
        if (mTimer != null) {
            stopTimer();
        }
        long j = TIMEOUT;
        CountDownTimer countDownTimer = new CountDownTimer(j, j + 1) { // from class: com.android.car.developeroptions.wifi.details.WifiDetailPreferenceController.6
            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Log.e("WifiDetailsPrefCtrl", "Timeout for state:" + WifiDetailPreferenceController.this.mConnectingState);
                if (WifiDetailPreferenceController.this.mConnectingState == 2) {
                    WifiDetailPreferenceController.this.updateConnectingState(3);
                    return;
                }
                if (WifiDetailPreferenceController.this.mConnectingState == 4) {
                    WifiDetailPreferenceController.this.updateAccessPointFromScannedList();
                    if (WifiDetailPreferenceController.this.mIsOutOfRange) {
                        WifiDetailPreferenceController.this.updateConnectingState(7);
                    } else {
                        WifiDetailPreferenceController.this.updateConnectingState(6);
                    }
                }
            }
        };
        mTimer = countDownTimer;
        countDownTimer.start();
    }

    private void stopTimer() {
        CountDownTimer countDownTimer = mTimer;
        if (countDownTimer == null) {
            return;
        }
        countDownTimer.cancel();
        mTimer = null;
    }
}
