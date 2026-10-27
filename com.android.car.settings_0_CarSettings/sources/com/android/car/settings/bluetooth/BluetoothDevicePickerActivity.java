package com.android.car.settings.bluetooth;

import androidx.fragment.app.Fragment;
import com.android.car.settings.common.BaseCarSettingsActivity;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDevicePickerActivity extends BaseCarSettingsActivity {
    @Override // com.android.car.settings.common.BaseCarSettingsActivity
    protected Fragment getInitialFragment() {
        return new BluetoothDevicePickerFragment();
    }
}
