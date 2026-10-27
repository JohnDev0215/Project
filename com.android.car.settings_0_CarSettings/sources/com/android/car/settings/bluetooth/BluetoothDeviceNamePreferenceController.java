package com.android.car.settings.bluetooth;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.preference.Preference;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDeviceNamePreferenceController extends BluetoothDevicePreferenceController<Preference> {
    public BluetoothDeviceNamePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        CachedBluetoothDevice cachedDevice = getCachedDevice();
        Pair<Drawable, String> pair = com.android.settingslib.bluetooth.BluetoothUtils.getBtClassDrawableWithDescription(getContext(), cachedDevice);
        StringJoiner summaryJoiner = new StringJoiner(System.lineSeparator());
        summaryJoiner.setEmptyValue("");
        String summaryText = cachedDevice.getCarConnectionSummary();
        if (!TextUtils.isEmpty(summaryText)) {
            summaryJoiner.add(summaryText);
        }
        String pairDeviceSummary = getBluetoothManager().getCachedDeviceManager().getSubDeviceSummary(cachedDevice);
        if (!TextUtils.isEmpty(pairDeviceSummary)) {
            summaryJoiner.add(pairDeviceSummary);
        }
        preference.setTitle(cachedDevice.getName());
        preference.setIcon((Drawable) pair.first);
        preference.getIcon().setTintList(Themes.getAttrColorStateList(getContext(), R.attr.iconColor));
        preference.setSummary(summaryJoiner.toString());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        getFragmentController().showDialog(RemoteRenameDialogFragment.newInstance(getCachedDevice()), RemoteRenameDialogFragment.TAG);
        return true;
    }
}
