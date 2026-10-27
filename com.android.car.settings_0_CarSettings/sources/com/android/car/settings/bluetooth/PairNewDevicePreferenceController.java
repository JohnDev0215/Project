package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import androidx.lifecycle.LifecycleObserver;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class PairNewDevicePreferenceController extends PreferenceController<Preference> implements LifecycleObserver {
    private final IntentFilter mIntentFilter;
    private final BroadcastReceiver mReceiver;
    private final UserManager mUserManager;

    public PairNewDevicePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mIntentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.PairNewDevicePreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                PairNewDevicePreferenceController.this.refreshUi();
            }
        };
        this.mUserManager = UserManager.get(context);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void checkInitialized() {
        if (getPreference().getIntent() == null && getPreference().getFragment() == null) {
            throw new IllegalStateException("Preference should declare fragment or intent for page to pair new devices");
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        if (getContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth")) {
            return isUserRestricted() ? 3 : 0;
        }
        return 2;
    }

    private boolean isUserRestricted() {
        return this.mUserManager.hasUserRestriction("no_bluetooth") || this.mUserManager.hasUserRestriction("no_config_bluetooth");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(BluetoothAdapter.getDefaultAdapter().isEnabled() ? "" : getContext().getString(R.string.bluetooth_pair_new_device_summary));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        BluetoothAdapter.getDefaultAdapter().enable();
        return false;
    }
}
