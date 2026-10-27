package com.android.car.developeroptions.wifi.dpp;

import android.app.ActionBar;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.InstrumentedActivity;
import com.android.car.developeroptions.wifi.dpp.WifiDppAddDeviceFragment;
import com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeGeneratorFragment;
import com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeScannerFragment;
import com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig;
import com.android.car.developeroptions.wifi.dpp.WifiNetworkListFragment;

/* JADX INFO: loaded from: classes.dex */
public class WifiDppConfiguratorActivity extends InstrumentedActivity implements WifiNetworkConfig.Retriever, WifiDppQrCodeGeneratorFragment.OnQrCodeGeneratorFragmentAddButtonClickedListener, WifiDppQrCodeScannerFragment.OnScanWifiDppSuccessListener, WifiDppAddDeviceFragment.OnClickChooseDifferentNetworkListener, WifiNetworkListFragment.OnChooseNetworkListener {
    private FragmentManager mFragmentManager;
    private boolean mIsTest;
    private WifiQrCode mWifiDppQrCode;
    private WifiNetworkConfig mWifiNetworkConfig;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1595;
    }

    @Override // com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_dpp_activity);
        this.mFragmentManager = getSupportFragmentManager();
        if (bundle != null) {
            this.mWifiDppQrCode = WifiQrCode.getValidWifiDppQrCodeOrNull(bundle.getString("key_qr_code"));
            this.mWifiNetworkConfig = WifiNetworkConfig.getValidConfigOrNull(bundle.getString("key_wifi_security"), bundle.getString("key_wifi_ssid"), bundle.getString("key_wifi_preshared_key"), bundle.getBoolean("key_wifi_hidden_ssid"), bundle.getInt("key_wifi_network_id"), bundle.getBoolean("key_is_hotspot"));
        } else {
            handleIntent(getIntent());
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0.0f);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleIntent(android.content.Intent r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.getAction()
            int r1 = r0.hashCode()
            r2 = -902592152(0xffffffffca338968, float:-2941530.0)
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == r2) goto L2f
            r2 = 360935630(0x158370ce, float:5.308845E-26)
            if (r1 == r2) goto L25
            r2 = 1361318585(0x512412b9, float:4.4043047E10)
            if (r1 == r2) goto L1b
            goto L39
        L1b:
            java.lang.String r1 = "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_SCANNER"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = r5
            goto L3a
        L25:
            java.lang.String r1 = "android.settings.WIFI_DPP_CONFIGURATOR_QR_CODE_GENERATOR"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = r4
            goto L3a
        L2f:
            java.lang.String r1 = "android.settings.PROCESS_WIFI_EASY_CONNECT_URI"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L39
            r0 = r3
            goto L3a
        L39:
            r0 = -1
        L3a:
            if (r0 == 0) goto L98
            if (r0 == r4) goto L8b
            java.lang.String r1 = "WifiDppConfiguratorActivity"
            if (r0 == r3) goto L48
            java.lang.String r7 = "Launch with an invalid action"
            android.util.Log.e(r1, r7)
            goto La5
        L48:
            android.net.Uri r0 = r7.getData()
            if (r0 != 0) goto L50
            r0 = 0
            goto L54
        L50:
            java.lang.String r0 = r0.toString()
        L54:
            java.lang.String r2 = "test"
            boolean r7 = r7.getBooleanExtra(r2, r5)
            r6.mIsTest = r7
            com.android.car.developeroptions.wifi.dpp.WifiQrCode r7 = com.android.car.developeroptions.wifi.dpp.WifiQrCode.getValidWifiDppQrCodeOrNull(r0)
            r6.mWifiDppQrCode = r7
            boolean r7 = com.android.car.developeroptions.wifi.dpp.WifiDppUtils.isWifiDppEnabled(r6)
            if (r7 != 0) goto L6d
            java.lang.String r0 = "Device doesn't support Wifi DPP"
            android.util.Log.d(r1, r0)
        L6d:
            com.android.car.developeroptions.wifi.dpp.WifiQrCode r0 = r6.mWifiDppQrCode
            if (r0 == 0) goto La5
            if (r7 != 0) goto L74
            goto La5
        L74:
            com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig r7 = r6.getConnectedWifiNetworkConfigOrNull()
            if (r7 == 0) goto L87
            boolean r0 = r7.isSupportWifiDpp(r6)
            if (r0 != 0) goto L81
            goto L87
        L81:
            r6.mWifiNetworkConfig = r7
            r6.showAddDeviceFragment(r5)
            goto La4
        L87:
            r6.showChooseSavedWifiNetworkFragment(r5)
            goto La4
        L8b:
            com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig r7 = com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig.getValidConfigOrNull(r7)
            if (r7 != 0) goto L92
            goto La5
        L92:
            r6.mWifiNetworkConfig = r7
            r6.showQrCodeGeneratorFragment()
            goto La4
        L98:
            com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig r7 = com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig.getValidConfigOrNull(r7)
            if (r7 != 0) goto L9f
            goto La5
        L9f:
            r6.mWifiNetworkConfig = r7
            r6.showQrCodeScannerFragment(r5)
        La4:
            r4 = r5
        La5:
            if (r4 == 0) goto Laa
            r6.finish()
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.wifi.dpp.WifiDppConfiguratorActivity.handleIntent(android.content.Intent):void");
    }

    private void showQrCodeScannerFragment(boolean z) {
        WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment = (WifiDppQrCodeScannerFragment) this.mFragmentManager.findFragmentByTag("qr_code_scanner_fragment");
        if (wifiDppQrCodeScannerFragment == null) {
            WifiDppQrCodeScannerFragment wifiDppQrCodeScannerFragment2 = new WifiDppQrCodeScannerFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.fragment_container, wifiDppQrCodeScannerFragment2, "qr_code_scanner_fragment");
            if (z) {
                fragmentTransactionBeginTransaction.addToBackStack(null);
            }
            fragmentTransactionBeginTransaction.commit();
            return;
        }
        if (wifiDppQrCodeScannerFragment.isVisible()) {
            return;
        }
        this.mFragmentManager.popBackStackImmediate();
    }

    private void showQrCodeGeneratorFragment() {
        WifiDppQrCodeGeneratorFragment wifiDppQrCodeGeneratorFragment = (WifiDppQrCodeGeneratorFragment) this.mFragmentManager.findFragmentByTag("qr_code_generator_fragment");
        if (wifiDppQrCodeGeneratorFragment == null) {
            WifiDppQrCodeGeneratorFragment wifiDppQrCodeGeneratorFragment2 = new WifiDppQrCodeGeneratorFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.fragment_container, wifiDppQrCodeGeneratorFragment2, "qr_code_generator_fragment");
            fragmentTransactionBeginTransaction.commit();
            return;
        }
        if (wifiDppQrCodeGeneratorFragment.isVisible()) {
            return;
        }
        this.mFragmentManager.popBackStackImmediate();
    }

    private void showChooseSavedWifiNetworkFragment(boolean z) {
        WifiDppChooseSavedWifiNetworkFragment wifiDppChooseSavedWifiNetworkFragment = (WifiDppChooseSavedWifiNetworkFragment) this.mFragmentManager.findFragmentByTag("choose_saved_wifi_network_fragment");
        if (wifiDppChooseSavedWifiNetworkFragment == null) {
            WifiDppChooseSavedWifiNetworkFragment wifiDppChooseSavedWifiNetworkFragment2 = new WifiDppChooseSavedWifiNetworkFragment();
            if (this.mIsTest) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("test", true);
                wifiDppChooseSavedWifiNetworkFragment2.setArguments(bundle);
            }
            FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.fragment_container, wifiDppChooseSavedWifiNetworkFragment2, "choose_saved_wifi_network_fragment");
            if (z) {
                fragmentTransactionBeginTransaction.addToBackStack(null);
            }
            fragmentTransactionBeginTransaction.commit();
            return;
        }
        if (wifiDppChooseSavedWifiNetworkFragment.isVisible()) {
            return;
        }
        this.mFragmentManager.popBackStackImmediate();
    }

    private void showAddDeviceFragment(boolean z) {
        WifiDppAddDeviceFragment wifiDppAddDeviceFragment = (WifiDppAddDeviceFragment) this.mFragmentManager.findFragmentByTag("add_device_fragment");
        if (wifiDppAddDeviceFragment == null) {
            WifiDppAddDeviceFragment wifiDppAddDeviceFragment2 = new WifiDppAddDeviceFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
            fragmentTransactionBeginTransaction.replace(R.id.fragment_container, wifiDppAddDeviceFragment2, "add_device_fragment");
            if (z) {
                fragmentTransactionBeginTransaction.addToBackStack(null);
            }
            fragmentTransactionBeginTransaction.commit();
            return;
        }
        if (wifiDppAddDeviceFragment.isVisible()) {
            return;
        }
        this.mFragmentManager.popBackStackImmediate();
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiNetworkConfig.Retriever
    public WifiNetworkConfig getWifiNetworkConfig() {
        return this.mWifiNetworkConfig;
    }

    public WifiQrCode getWifiDppQrCode() {
        return this.mWifiDppQrCode;
    }

    boolean setWifiNetworkConfig(WifiNetworkConfig wifiNetworkConfig) {
        if (!WifiNetworkConfig.isValidConfig(wifiNetworkConfig)) {
            return false;
        }
        this.mWifiNetworkConfig = new WifiNetworkConfig(wifiNetworkConfig);
        return true;
    }

    boolean setWifiDppQrCode(WifiQrCode wifiQrCode) {
        if (wifiQrCode == null || !"DPP".equals(wifiQrCode.getScheme())) {
            return false;
        }
        this.mWifiDppQrCode = new WifiQrCode(wifiQrCode.getQrCode());
        return true;
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        Fragment fragmentFindFragmentById = this.mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentFindFragmentById instanceof WifiDppQrCodeGeneratorFragment) {
            finish();
            return true;
        }
        if (!(fragmentFindFragmentById instanceof WifiDppQrCodeScannerFragment)) {
            return false;
        }
        this.mFragmentManager.popBackStackImmediate();
        return false;
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppQrCodeScannerFragment.OnScanWifiDppSuccessListener
    public void onScanWifiDppSuccess(WifiQrCode wifiQrCode) {
        this.mWifiDppQrCode = wifiQrCode;
        showAddDeviceFragment(true);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiDppAddDeviceFragment.OnClickChooseDifferentNetworkListener
    public void onClickChooseDifferentNetwork() {
        showChooseSavedWifiNetworkFragment(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        WifiQrCode wifiQrCode = this.mWifiDppQrCode;
        if (wifiQrCode != null) {
            bundle.putString("key_qr_code", wifiQrCode.getQrCode());
        }
        WifiNetworkConfig wifiNetworkConfig = this.mWifiNetworkConfig;
        if (wifiNetworkConfig != null) {
            bundle.putString("key_wifi_security", wifiNetworkConfig.getSecurity());
            bundle.putString("key_wifi_ssid", this.mWifiNetworkConfig.getSsid());
            bundle.putString("key_wifi_preshared_key", this.mWifiNetworkConfig.getPreSharedKey());
            bundle.putBoolean("key_wifi_hidden_ssid", this.mWifiNetworkConfig.getHiddenSsid());
            bundle.putInt("key_wifi_network_id", this.mWifiNetworkConfig.getNetworkId());
            bundle.putBoolean("key_is_hotspot", this.mWifiNetworkConfig.isHotspot());
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // com.android.car.developeroptions.wifi.dpp.WifiNetworkListFragment.OnChooseNetworkListener
    public void onChooseNetwork(WifiNetworkConfig wifiNetworkConfig) {
        this.mWifiNetworkConfig = new WifiNetworkConfig(wifiNetworkConfig);
        showAddDeviceFragment(true);
    }

    private WifiNetworkConfig getConnectedWifiNetworkConfigOrNull() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) getSystemService(WifiManager.class);
        if (!wifiManager.isWifiEnabled() || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return null;
        }
        int networkId = connectionInfo.getNetworkId();
        for (WifiConfiguration wifiConfiguration : wifiManager.getConfiguredNetworks()) {
            if (wifiConfiguration.networkId == networkId) {
                return WifiNetworkConfig.getValidConfigOrNull(WifiDppUtils.getSecurityString(wifiConfiguration), wifiConfiguration.getPrintableSsid(), wifiConfiguration.preSharedKey, false, wifiConfiguration.networkId, false);
            }
        }
        return null;
    }
}
