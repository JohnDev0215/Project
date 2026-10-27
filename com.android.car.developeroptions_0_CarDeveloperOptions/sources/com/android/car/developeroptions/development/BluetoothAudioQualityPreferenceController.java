package com.android.car.developeroptions.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAudioQualityPreferenceController extends AbstractBluetoothA2dpPreferenceController {
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getDefaultIndex() {
        return 3;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_ldac_playback_quality";
    }

    public BluetoothAudioQualityPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListValues() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_ldac_playback_quality_values);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_ldac_playback_quality_summaries);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected void writeConfigurationValues(Object obj) {
        int iFindIndexOfValue = ((AbstractBluetoothA2dpPreferenceController) this).mPreference.findIndexOfValue(obj.toString());
        this.mBluetoothA2dpConfigStore.setCodecSpecific1Value((iFindIndexOfValue == 0 || iFindIndexOfValue == 1 || iFindIndexOfValue == 2 || iFindIndexOfValue == 3) ? iFindIndexOfValue + 1000 : 0);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int codecSpecific1 = (int) bluetoothCodecConfig.getCodecSpecific1();
        int i = codecSpecific1 > 0 ? codecSpecific1 % 10 : 3;
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            return i;
        }
        return 3;
    }
}
