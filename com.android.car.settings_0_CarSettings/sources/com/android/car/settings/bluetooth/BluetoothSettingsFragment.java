package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.UserManager;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.bluetooth_settings_fragment, "android.settings.BLUETOOTH_SETTINGS");
    private MenuItem mBluetoothSwitch;
    private LocalBluetoothManager mLocalBluetoothManager;
    private UserManager mUserManager;
    private final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final IntentFilter mIntentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.BluetoothSettingsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            BluetoothSettingsFragment.this.handleStateChanged(state);
        }
    };
    private final MenuItem.OnClickListener mBluetoothSwitchListener = new MenuItem.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothSettingsFragment$k_Q4rezRn8NoLMmABLIi-QR-1QY
        @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
        public final void onClick(MenuItem menuItem) {
            this.f$0.lambda$new$0$BluetoothSettingsFragment(menuItem);
        }
    };

    public /* synthetic */ void lambda$new$0$BluetoothSettingsFragment(MenuItem item) {
        item.setEnabled(false);
        if (item.isChecked()) {
            this.mBluetoothAdapter.enable();
        } else {
            this.mBluetoothAdapter.disable();
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mBluetoothSwitch);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBluetoothSwitch = new MenuItem.Builder(getContext()).setCheckable().setOnClickListener(this.mBluetoothSwitchListener).build();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mUserManager = UserManager.get(context);
        LocalBluetoothManager localBtManager = BluetoothUtils.getLocalBtManager(context);
        this.mLocalBluetoothManager = localBtManager;
        if (localBtManager == null) {
            goBack();
        }
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        requireContext().registerReceiver(this.mReceiver, this.mIntentFilter);
        this.mLocalBluetoothManager.setForegroundActivity(requireActivity());
        handleStateChanged(this.mBluetoothAdapter.getState());
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        requireContext().unregisterReceiver(this.mReceiver);
        this.mLocalBluetoothManager.setForegroundActivity(null);
    }

    private boolean isUserRestricted() {
        return this.mUserManager.hasUserRestriction("no_bluetooth");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStateChanged(int state) {
        this.mBluetoothSwitch.setOnClickListener(null);
        switch (state) {
            case 11:
                this.mBluetoothSwitch.setEnabled(false);
                this.mBluetoothSwitch.setChecked(true);
                break;
            case 12:
                this.mBluetoothSwitch.setEnabled(!isUserRestricted());
                this.mBluetoothSwitch.setChecked(true);
                break;
            case 13:
                this.mBluetoothSwitch.setEnabled(false);
                this.mBluetoothSwitch.setChecked(false);
                break;
            default:
                this.mBluetoothSwitch.setEnabled(true ^ isUserRestricted());
                this.mBluetoothSwitch.setChecked(false);
                break;
        }
        this.mBluetoothSwitch.setOnClickListener(this.mBluetoothSwitchListener);
    }
}
