package com.android.car.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.TetheringManager;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.network.NetworkUtils;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.ui.toolbar.MenuItem;
import com.android.internal.util.ConcurrentUtils;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WifiTetherFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.wifi_tether_fragment, CarSettingActivities.WifiTetherActivity.class) { // from class: com.android.car.settings.wifi.WifiTetherFragment.4
        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider
        public boolean isPageSearchEnabled(Context context) {
            return NetworkUtils.hasMobileNetwork((ConnectivityManager) context.getSystemService(ConnectivityManager.class));
        }
    };
    private CarWifiManager mCarWifiManager;
    private MenuItem mTetherSwitch;
    private TetheringManager mTetheringManager;
    private boolean mRestartBooked = false;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.WifiTetherFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra("wifi_state", 14);
            WifiTetherFragment.this.handleWifiApStateChanged(state);
        }
    };
    private final BroadcastReceiver mRestartReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.WifiTetherFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (WifiTetherFragment.this.mCarWifiManager != null && WifiTetherFragment.this.mCarWifiManager.isWifiApEnabled()) {
                WifiTetherFragment.this.restartTethering();
            }
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mTetherSwitch);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mTetherSwitch = new MenuItem.Builder(getContext()).setCheckable().setChecked(this.mCarWifiManager.isWifiApEnabled()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiTetherFragment$P1foTySWes8KkUzlQoYxw_fjLSc
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$WifiTetherFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$WifiTetherFragment(MenuItem i) {
        if (!this.mTetherSwitch.isChecked()) {
            stopTethering();
        } else {
            startTethering();
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_tether_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mCarWifiManager = new CarWifiManager(context);
        this.mTetheringManager = (TetheringManager) getContext().getSystemService(TetheringManager.class);
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getContext().registerReceiver(this.mReceiver, new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED"));
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mRestartReceiver, new IntentFilter(WifiTetherBasePreferenceController.ACTION_RESTART_WIFI_TETHERING));
        this.mCarWifiManager.start();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mCarWifiManager.stop();
        getContext().unregisterReceiver(this.mReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mRestartReceiver);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCarWifiManager.destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleWifiApStateChanged(int state) {
        switch (state) {
            case 10:
                this.mTetherSwitch.setEnabled(false);
                if (this.mTetherSwitch.isChecked()) {
                    this.mTetherSwitch.setChecked(false);
                }
                break;
            case 11:
                this.mTetherSwitch.setChecked(false);
                this.mTetherSwitch.setEnabled(true);
                if (this.mRestartBooked) {
                    this.mTetherSwitch.setEnabled(false);
                    startTethering();
                    this.mRestartBooked = false;
                }
                break;
            case 12:
                this.mTetherSwitch.setEnabled(false);
                break;
            case 13:
                this.mTetherSwitch.setEnabled(true);
                if (!this.mTetherSwitch.isChecked()) {
                    this.mTetherSwitch.setChecked(true);
                }
                break;
            default:
                this.mTetherSwitch.setChecked(false);
                this.mTetherSwitch.setEnabled(true);
                break;
        }
    }

    private void startTethering() {
        this.mTetheringManager.startTethering(0, ConcurrentUtils.DIRECT_EXECUTOR, new TetheringManager.StartTetheringCallback() { // from class: com.android.car.settings.wifi.WifiTetherFragment.3
            public void onTetheringFailed(int result) {
                WifiTetherFragment.this.mTetherSwitch.setChecked(false);
                WifiTetherFragment.this.mTetherSwitch.setEnabled(true);
            }
        });
    }

    private void stopTethering() {
        this.mTetheringManager.stopTethering(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restartTethering() {
        stopTethering();
        this.mRestartBooked = true;
    }
}
