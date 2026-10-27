package com.android.car.developeroptions.bluetooth;

import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.settingslib.widget.FooterPreference;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDetailsMacAddressController extends BluetoothDetailsController {
    private FooterPreference mFooterPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "device_details_footer";
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDetailsController
    protected void init(PreferenceScreen preferenceScreen) {
        FooterPreference footerPreference = (FooterPreference) preferenceScreen.findPreference("device_details_footer");
        this.mFooterPreference = footerPreference;
        footerPreference.setTitle(((BluetoothDetailsController) this).mContext.getString(R.string.bluetooth_device_mac_address, this.mCachedDevice.getAddress()));
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDetailsController
    protected void refresh() {
        this.mFooterPreference.setTitle(((BluetoothDetailsController) this).mContext.getString(R.string.bluetooth_device_mac_address, this.mCachedDevice.getAddress()));
    }
}
