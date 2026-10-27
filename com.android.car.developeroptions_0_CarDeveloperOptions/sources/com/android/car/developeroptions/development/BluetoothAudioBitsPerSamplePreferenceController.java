package com.android.car.developeroptions.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAudioBitsPerSamplePreferenceController extends AbstractBluetoothA2dpPreferenceController {
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getDefaultIndex() {
        return 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_bits_per_sample";
    }

    public BluetoothAudioBitsPerSamplePreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListValues() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_bits_per_sample_values);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_bits_per_sample_summaries);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected void writeConfigurationValues(Object obj) {
        int iFindIndexOfValue = ((AbstractBluetoothA2dpPreferenceController) this).mPreference.findIndexOfValue(obj.toString());
        int i = 2;
        if (iFindIndexOfValue == 1) {
            i = 1;
        } else if (iFindIndexOfValue != 2) {
            i = iFindIndexOfValue != 3 ? 0 : 4;
        }
        this.mBluetoothA2dpConfigStore.setBitsPerSample(i);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int bitsPerSample = bluetoothCodecConfig.getBitsPerSample();
        if (bitsPerSample == 1) {
            return 1;
        }
        if (bitsPerSample != 2) {
            return bitsPerSample != 4 ? 0 : 3;
        }
        return 2;
    }
}
