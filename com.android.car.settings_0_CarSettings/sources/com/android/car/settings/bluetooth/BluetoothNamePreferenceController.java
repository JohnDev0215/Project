package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothNamePreferenceController extends BluetoothPreferenceController<Preference> {
    private final IntentFilter mIntentFilter;
    private final BroadcastReceiver mReceiver;

    public BluetoothNamePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mIntentFilter = new IntentFilter("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.BluetoothNamePreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                BluetoothNamePreferenceController.this.refreshUi();
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        super.onStartInternal();
        getContext().registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        super.onStopInternal();
        getContext().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSelectable(!getUserManager().hasUserRestriction("no_config_bluetooth"));
        preference.setSummary(BluetoothAdapter.getDefaultAdapter().getName());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        getFragmentController().showDialog(new LocalRenameDialogFragment(), LocalRenameDialogFragment.TAG);
        return true;
    }
}
