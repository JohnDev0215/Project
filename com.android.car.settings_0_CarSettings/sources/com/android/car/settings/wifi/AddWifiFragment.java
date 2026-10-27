package com.android.car.settings.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AddWifiFragment extends SettingsFragment {
    private static final String KEY_NETWORK_NAME = "network_name";
    private static final String KEY_SECURITY_TYPE = "security_type";
    private static final Logger LOG = new Logger((Class<?>) AddWifiFragment.class);
    private static final int SHARED_SECURITY_TYPE_UNSET = -1;
    private MenuItem mAddWifiButton;
    private String mNetworkName;
    private final BroadcastReceiver mNameChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.AddWifiFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AddWifiFragment.this.mNetworkName = intent.getStringExtra("network_name");
            AddWifiFragment.this.setButtonEnabledState();
        }
    };
    private final BroadcastReceiver mSecurityChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.wifi.AddWifiFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AddWifiFragment.this.mSecurityType = intent.getIntExtra("security_type", 0);
            AddWifiFragment.this.setButtonEnabledState();
        }
    };
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());
    private final WifiManager.ActionListener mConnectionListener = new AnonymousClass3();
    private int mSecurityType = 0;

    /* JADX INFO: renamed from: com.android.car.settings.wifi.AddWifiFragment$3, reason: invalid class name */
    class AnonymousClass3 implements WifiManager.ActionListener {
        AnonymousClass3() {
        }

        public void onSuccess() {
            AddWifiFragment.LOG.d("connected to network");
            AddWifiFragment.this.mUiHandler.post(new Runnable() { // from class: com.android.car.settings.wifi.-$$Lambda$AddWifiFragment$3$7NQagQVLXMMqUr_8yDD2wUHFvHk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSuccess$0$AddWifiFragment$3();
                }
            });
        }

        public /* synthetic */ void lambda$onSuccess$0$AddWifiFragment$3() {
            AddWifiFragment.this.goBack();
        }

        public void onFailure(int reason) {
            AddWifiFragment.LOG.d("Failed to connect to network. Failure code: " + reason);
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mAddWifiButton);
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.add_wifi_fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mNetworkName = savedInstanceState.getString("network_name");
            this.mSecurityType = savedInstanceState.getInt("security_type", 0);
        }
        this.mAddWifiButton = new MenuItem.Builder(getContext()).setTitle(R.string.wifi_setup_connect).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$AddWifiFragment$POqBx0pP5iaW4qj1pceJv_ZQcy4
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$AddWifiFragment(menuItem);
            }
        }).build();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mNameChangeReceiver, new IntentFilter(NetworkNamePreferenceController.ACTION_NAME_CHANGE));
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.mSecurityChangeReceiver, new IntentFilter(NetworkSecurityPreferenceController.ACTION_SECURITY_CHANGE));
    }

    public /* synthetic */ void lambda$onCreate$0$AddWifiFragment(MenuItem i) {
        WifiUtil.connectToAccessPoint(getContext(), this.mNetworkName, this.mSecurityType, null, true, this.mConnectionListener);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mNameChangeReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mSecurityChangeReceiver);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("network_name", this.mNetworkName);
        outState.putInt("security_type", this.mSecurityType);
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("com.android.car.settings.wifi.NetworkPasswordPreferenceController", 0);
        int newSecurityType = sharedPreferences.getInt("com.android.car.settings.wifi.SHARED_SECURITY_TYPE", -1);
        if (this.mSecurityType != newSecurityType && newSecurityType != -1) {
            this.mSecurityType = newSecurityType;
        }
        setButtonEnabledState();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        MenuItem menuItem = this.mAddWifiButton;
        if (menuItem != null) {
            menuItem.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setButtonEnabledState() {
        MenuItem menuItem = this.mAddWifiButton;
        if (menuItem != null) {
            menuItem.setEnabled(!TextUtils.isEmpty(this.mNetworkName) && WifiUtil.isOpenNetwork(this.mSecurityType));
        }
    }
}
