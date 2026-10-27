package com.android.car.developeroptions.development;

import android.bluetooth.BluetoothCodecConfig;
import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAudioSampleRatePreferenceController extends AbstractBluetoothA2dpPreferenceController {
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getDefaultIndex() {
        return 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_sample_rate";
    }

    public BluetoothAudioSampleRatePreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListValues() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_sample_rate_values);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_sample_rate_summaries);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0019  */
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void writeConfigurationValues(java.lang.Object r5) {
        /*
            r4 = this;
            androidx.preference.ListPreference r0 = r4.mPreference
            java.lang.String r5 = r5.toString()
            int r5 = r0.findIndexOfValue(r5)
            r0 = 4
            r1 = 2
            r2 = 1
            r3 = 0
            if (r5 == 0) goto L19
            if (r5 == r2) goto L20
            if (r5 == r1) goto L1e
            r1 = 3
            if (r5 == r1) goto L21
            if (r5 == r0) goto L1b
        L19:
            r0 = r3
            goto L21
        L1b:
            r0 = 8
            goto L21
        L1e:
            r0 = r1
            goto L21
        L20:
            r0 = r2
        L21:
            com.android.car.developeroptions.development.BluetoothA2dpConfigStore r4 = r4.mBluetoothA2dpConfigStore
            r4.setSampleRate(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.development.BluetoothAudioSampleRatePreferenceController.writeConfigurationValues(java.lang.Object):void");
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int sampleRate = bluetoothCodecConfig.getSampleRate();
        if (sampleRate == 1) {
            return 1;
        }
        if (sampleRate == 2) {
            return 2;
        }
        if (sampleRate != 4) {
            return sampleRate != 8 ? 0 : 4;
        }
        return 3;
    }
}
