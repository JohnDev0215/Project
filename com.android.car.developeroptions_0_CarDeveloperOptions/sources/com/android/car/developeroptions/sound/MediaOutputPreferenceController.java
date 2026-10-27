package com.android.car.developeroptions.sound;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.Utils;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.HearingAidProfile;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaOutputPreferenceController extends AudioSwitchPreferenceController {
    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onAclConnectionStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onConnectionStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        super.onDeviceBondStateChanged(cachedBluetoothDevice, i);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        super.onDeviceDeleted(cachedBluetoothDevice);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public /* bridge */ /* synthetic */ void onScanningStateChanged(boolean z) {
        super.onScanningStateChanged(z);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public MediaOutputPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        CharSequence alias;
        if (preference == null) {
            return;
        }
        boolean z = false;
        if (isStreamFromOutputDevice(3, 32768)) {
            this.mPreference.setVisible(false);
            preference.setSummary(this.mContext.getText(R.string.media_output_summary_unavailable));
            return;
        }
        if (Utils.isAudioModeOngoingCall(this.mContext)) {
            this.mPreference.setVisible(false);
            preference.setSummary(this.mContext.getText(R.string.media_out_summary_ongoing_call_state));
            return;
        }
        BluetoothDevice bluetoothDeviceFindActiveDevice = null;
        List<BluetoothDevice> connectableA2dpDevices = getConnectableA2dpDevices();
        List<BluetoothDevice> connectableHearingAidDevices = getConnectableHearingAidDevices();
        if (this.mAudioManager.getMode() == 0 && ((connectableA2dpDevices != null && !connectableA2dpDevices.isEmpty()) || (connectableHearingAidDevices != null && !connectableHearingAidDevices.isEmpty()))) {
            z = true;
            bluetoothDeviceFindActiveDevice = findActiveDevice();
        }
        this.mPreference.setVisible(z);
        Preference preference2 = this.mPreference;
        if (bluetoothDeviceFindActiveDevice == null) {
            alias = this.mContext.getText(R.string.media_output_default_summary);
        } else {
            alias = bluetoothDeviceFindActiveDevice.getAlias();
        }
        preference2.setSummary(alias);
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController
    public BluetoothDevice findActiveDevice() {
        BluetoothDevice bluetoothDeviceFindActiveHearingAidDevice = findActiveHearingAidDevice();
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        return (bluetoothDeviceFindActiveHearingAidDevice != null || a2dpProfile == null) ? bluetoothDeviceFindActiveHearingAidDevice : a2dpProfile.getActiveDevice();
    }

    @Override // com.android.car.developeroptions.sound.AudioSwitchPreferenceController
    protected BluetoothDevice findActiveHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile == null) {
            return null;
        }
        for (BluetoothDevice bluetoothDevice : hearingAidProfile.getActiveDevices()) {
            if (bluetoothDevice != null) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }
        this.mContext.startActivity(new Intent().setAction("com.android.settings.panel.action.MEDIA_OUTPUT").setFlags(268435456));
        return true;
    }
}
