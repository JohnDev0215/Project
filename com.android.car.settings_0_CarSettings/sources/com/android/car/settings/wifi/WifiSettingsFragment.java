package com.android.car.settings.wifi;

import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.settings.wifi.CarWifiManager;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.ProgressBarController;
import com.android.settingslib.wifi.AccessPoint;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class WifiSettingsFragment extends SettingsFragment implements CarWifiManager.Listener {
    private static final String EXTRA_CONNECTED_ACCESS_POINT_KEY = "connected_access_point_key";
    private static final int SEARCHING_DELAY_MILLIS = 1700;
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.wifi_list_fragment, "android.settings.WIFI_SETTINGS");
    private CarWifiManager mCarWifiManager;
    private String mConnectedAccessPointKey;
    private ProgressBarController mProgressBar;
    private MenuItem mWifiSwitch;

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mWifiSwitch);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_list_fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCarWifiManager = new CarWifiManager(getContext());
        this.mWifiSwitch = new MenuItem.Builder(getContext()).setCheckable().setChecked(this.mCarWifiManager.isWifiEnabled()).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiSettingsFragment$pbGE2bVF7WJqdu2pdMfLrPRCJ_8
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$WifiSettingsFragment(menuItem);
            }
        }).build();
        if (savedInstanceState != null) {
            this.mConnectedAccessPointKey = savedInstanceState.getString(EXTRA_CONNECTED_ACCESS_POINT_KEY);
        }
    }

    public /* synthetic */ void lambda$onCreate$0$WifiSettingsFragment(MenuItem i) {
        if (this.mWifiSwitch.isChecked() != this.mCarWifiManager.isWifiEnabled()) {
            this.mCarWifiManager.setWifiEnabled(this.mWifiSwitch.isChecked());
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_CONNECTED_ACCESS_POINT_KEY, this.mConnectedAccessPointKey);
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mProgressBar = getToolbar().getProgressBar();
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mCarWifiManager.addListener(this);
        this.mCarWifiManager.start();
        onWifiStateChanged(this.mCarWifiManager.getWifiState());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mCarWifiManager.removeListener(this);
        this.mCarWifiManager.stop();
        this.mProgressBar.setVisible(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mCarWifiManager.destroy();
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onAccessPointsChanged() {
        this.mProgressBar.setVisible(true);
        getView().postDelayed(new Runnable() { // from class: com.android.car.settings.wifi.-$$Lambda$WifiSettingsFragment$2HKigaR4VB6UzUNoIZ3kjAEcREM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAccessPointsChanged$1$WifiSettingsFragment();
            }
        }, 1700L);
        AccessPoint connectedAccessPoint = this.mCarWifiManager.getConnectedAccessPoint();
        if (connectedAccessPoint != null) {
            String connectedAccessPointKey = connectedAccessPoint.getKey();
            if (!connectedAccessPointKey.equals(this.mConnectedAccessPointKey)) {
                scrollToPreference(connectedAccessPointKey);
                this.mConnectedAccessPointKey = connectedAccessPointKey;
                return;
            }
            return;
        }
        this.mConnectedAccessPointKey = null;
    }

    public /* synthetic */ void lambda$onAccessPointsChanged$1$WifiSettingsFragment() {
        this.mProgressBar.setVisible(false);
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        this.mWifiSwitch.setChecked(state == 3 || state == 2);
        if (state == 2) {
            this.mProgressBar.setVisible(true);
        } else {
            this.mProgressBar.setVisible(false);
        }
    }
}
