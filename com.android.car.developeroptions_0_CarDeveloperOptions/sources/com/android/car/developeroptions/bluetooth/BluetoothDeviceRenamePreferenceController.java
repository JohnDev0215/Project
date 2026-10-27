package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceRenamePreferenceController extends BluetoothDeviceNamePreferenceController {
    private Fragment mFragment;
    private MetricsFeatureProvider mMetricsFeatureProvider;

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BluetoothDeviceRenamePreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController
    protected void updatePreferenceState(Preference preference) {
        preference.setSummary(getSummary());
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        preference.setVisible(bluetoothAdapter != null && bluetoothAdapter.isEnabled());
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothDeviceNamePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return getDeviceName();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(getPreferenceKey(), preference.getKey()) || this.mFragment == null) {
            return false;
        }
        this.mMetricsFeatureProvider.action(this.mContext, 161, new Pair[0]);
        LocalDeviceNameDialogFragment.newInstance().show(this.mFragment.getFragmentManager(), "LocalAdapterName");
        return true;
    }
}
