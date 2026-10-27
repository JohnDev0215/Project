package com.android.car.settings.wifi.details;

import android.content.Context;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.wifi.WifiUtil;
import com.android.car.settings.wifi.details.WifiInfoProvider;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.wifi.AccessPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WifiDetailsFragment extends SettingsFragment implements WifiInfoProvider.Listener {
    private static final String EXTRA_AP_STATE = "extra_ap_state";
    private static final Logger LOG = new Logger((Class<?>) WifiDetailsFragment.class);
    private AccessPoint mAccessPoint;
    private MenuItem mConnectButton;
    private List<WifiDetailsBasePreferenceController> mControllers = new ArrayList();
    private MenuItem mForgetButton;
    private WifiInfoProvider mWifiInfoProvider;
    private WifiManager mWifiManager;

    private class ActionFailListener implements WifiManager.ActionListener {
        private final int mMessageResId;

        ActionFailListener(int messageResId) {
            this.mMessageResId = messageResId;
        }

        public void onSuccess() {
        }

        public void onFailure(int reason) {
        }
    }

    public static WifiDetailsFragment getInstance(AccessPoint accessPoint) {
        WifiDetailsFragment wifiDetailsFragment = new WifiDetailsFragment();
        Bundle bundle = new Bundle();
        Bundle accessPointState = new Bundle();
        accessPoint.saveWifiState(accessPointState);
        bundle.putBundle(EXTRA_AP_STATE, accessPointState);
        wifiDetailsFragment.setArguments(bundle);
        return wifiDetailsFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mForgetButton, this.mConnectButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mForgetButton = new MenuItem.Builder(getContext()).setTitle(R.string.forget).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.wifi.details.-$$Lambda$WifiDetailsFragment$tBzV-r9FeeQr9gzmYwJuJpEC_X8
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$WifiDetailsFragment(menuItem);
            }
        }).build();
        this.mConnectButton = new MenuItem.Builder(getContext()).setTitle(R.string.wifi_setup_connect).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.wifi.details.-$$Lambda$WifiDetailsFragment$KwUdP3n1FEaTsJZyGj2hKROKcFo
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$WifiDetailsFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$WifiDetailsFragment(MenuItem i) {
        WifiUtil.forget(getContext(), this.mAccessPoint);
        goBack();
    }

    public /* synthetic */ void lambda$onCreate$1$WifiDetailsFragment(MenuItem i) {
        this.mWifiManager.connect(this.mAccessPoint.getConfig(), new ActionFailListener(R.string.wifi_failed_connect_message));
        goBack();
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_detail_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAccessPoint = new AccessPoint(getContext(), getArguments().getBundle(EXTRA_AP_STATE));
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        LOG.d("Creating WifiInfoProvider for " + this.mAccessPoint);
        if (this.mWifiInfoProvider == null) {
            this.mWifiInfoProvider = new WifiInfoProvider(getContext(), this.mAccessPoint);
        }
        getLifecycle().addObserver(this.mWifiInfoProvider);
        LOG.d("Creating WifiInfoProvider.Listeners.");
        this.mControllers.add(((WifiSignalStrengthPreferenceController) use(WifiSignalStrengthPreferenceController.class, R.string.pk_wifi_signal_strength)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiFrequencyPreferenceController) use(WifiFrequencyPreferenceController.class, R.string.pk_wifi_frequency)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiSecurityPreferenceController) use(WifiSecurityPreferenceController.class, R.string.pk_wifi_security)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiMacAddressPreferenceController) use(WifiMacAddressPreferenceController.class, R.string.pk_wifi_mac_address)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiIpAddressPreferenceController) use(WifiIpAddressPreferenceController.class, R.string.pk_wifi_ip)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiGatewayPreferenceController) use(WifiGatewayPreferenceController.class, R.string.pk_wifi_gateway)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiSubnetPreferenceController) use(WifiSubnetPreferenceController.class, R.string.pk_wifi_subnet_mask)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiDnsPreferenceController) use(WifiDnsPreferenceController.class, R.string.pk_wifi_dns)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiLinkSpeedPreferenceController) use(WifiLinkSpeedPreferenceController.class, R.string.pk_wifi_link_speed)).init(this.mAccessPoint, this.mWifiInfoProvider));
        this.mControllers.add(((WifiIpv6AddressPreferenceController) use(WifiIpv6AddressPreferenceController.class, R.string.pk_wifi_ipv6)).init(this.mAccessPoint, this.mWifiInfoProvider));
        LOG.d("Done init.");
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getToolbar().setTitle(this.mAccessPoint.getSsid());
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enableRotaryScroll();
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mWifiInfoProvider.addListener(this);
        updateUi();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mWifiInfoProvider.removeListener(this);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        getLifecycle().removeObserver(this.mWifiInfoProvider);
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onWifiChanged(NetworkInfo networkInfo, WifiInfo wifiInfo) {
        updateUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        updateUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        updateUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onLost(Network network) {
        updateUi();
    }

    @Override // com.android.car.settings.wifi.details.WifiInfoProvider.Listener
    public void onWifiConfigurationChanged(WifiConfiguration wifiConfiguration, NetworkInfo networkInfo, WifiInfo wifiInfo) {
        updateUi();
    }

    private void updateUi() {
        LOG.d("updating.");
        if (this.mWifiInfoProvider.getNetwork() == null || this.mWifiInfoProvider.getNetworkInfo() == null || this.mWifiInfoProvider.getWifiInfo() == null) {
            LOG.d("WIFI not available.");
            this.mForgetButton.setVisible(false);
            this.mConnectButton.setVisible(false);
        } else {
            this.mForgetButton.setVisible(canForgetNetwork());
            this.mConnectButton.setVisible(needConnect());
            LOG.d("updated.");
        }
    }

    private boolean needConnect() {
        return this.mAccessPoint.isSaved() && !this.mAccessPoint.isActive();
    }

    private boolean canForgetNetwork() {
        return (this.mWifiInfoProvider.getWifiInfo() != null && this.mWifiInfoProvider.getWifiInfo().isEphemeral()) || canModifyNetwork();
    }

    private boolean canModifyNetwork() {
        WifiConfiguration wifiConfig = this.mWifiInfoProvider.getNetworkConfiguration();
        LOG.d("wifiConfig is: " + wifiConfig);
        return (wifiConfig == null || WifiUtil.isNetworkLockedDown(getContext(), wifiConfig)) ? false : true;
    }
}
