package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothAddressPreferenceController extends BluetoothPreferenceController<Preference> {
    public BluetoothAddressPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        String address = BluetoothAdapter.getDefaultAdapter().getAddress();
        String formattedAddress = getContext().getString(R.string.bluetooth_vehicle_mac_address, address);
        preference.setTitle(formattedAddress);
    }
}
