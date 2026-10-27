package com.android.car.developeroptions.development;

import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.settingslib.core.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothAudioCodecPreferenceController extends AbstractBluetoothA2dpPreferenceController {
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getDefaultIndex() {
        return 0;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "bluetooth_select_a2dp_codec";
    }

    public BluetoothAudioCodecPreferenceController(Context context, Lifecycle lifecycle, BluetoothA2dpConfigStore bluetoothA2dpConfigStore) {
        super(context, lifecycle, bluetoothA2dpConfigStore);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListValues() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_values);
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected String[] getListSummaries() {
        return this.mContext.getResources().getStringArray(R.array.bluetooth_a2dp_codec_summaries);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected void writeConfigurationValues(Object obj) {
        BluetoothDevice activeDevice;
        BluetoothDevice activeDevice2;
        int i = 4;
        int i2 = 0;
        switch (((AbstractBluetoothA2dpPreferenceController) this).mPreference.findIndexOfValue(obj.toString())) {
            case 0:
                int iFindIndexOfValue = ((AbstractBluetoothA2dpPreferenceController) this).mPreference.findIndexOfValue(((AbstractBluetoothA2dpPreferenceController) this).mPreference.getValue());
                if (iFindIndexOfValue == 1) {
                    i = 0;
                } else if (iFindIndexOfValue == 2) {
                    i = 1;
                } else if (iFindIndexOfValue == 3) {
                    i = 2;
                } else if (iFindIndexOfValue == 4) {
                    i = 3;
                } else if (iFindIndexOfValue != 5) {
                    i = 0;
                }
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case 1:
                i = 0;
                i2 = 1000000;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case 2:
                i = 1;
                i2 = 1000000;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case 3:
                i = 2;
                i2 = 1000000;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case 4:
                i = 3;
                i2 = 1000000;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case 5:
                i2 = 1000000;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                synchronized (this.mBluetoothA2dpConfigStore) {
                    if (this.mBluetoothA2dp != null && (activeDevice = this.mBluetoothA2dp.getActiveDevice()) != null) {
                        this.mBluetoothA2dp.enableOptionalCodecs(activeDevice);
                    }
                    break;
                }
                return;
            case 7:
                synchronized (this.mBluetoothA2dpConfigStore) {
                    if (this.mBluetoothA2dp != null && (activeDevice2 = this.mBluetoothA2dp.getActiveDevice()) != null) {
                        this.mBluetoothA2dp.disableOptionalCodecs(activeDevice2);
                    }
                    break;
                }
                return;
            default:
                i = 0;
                this.mBluetoothA2dpConfigStore.setCodecType(i);
                this.mBluetoothA2dpConfigStore.setCodecPriority(i2);
                return;
        }
    }

    @Override // com.android.car.developeroptions.development.AbstractBluetoothA2dpPreferenceController
    protected int getCurrentA2dpSettingIndex(BluetoothCodecConfig bluetoothCodecConfig) {
        int codecType = bluetoothCodecConfig.getCodecType();
        if (codecType == 0) {
            return 1;
        }
        if (codecType == 1) {
            return 2;
        }
        if (codecType == 2) {
            return 3;
        }
        if (codecType != 3) {
            return codecType != 4 ? 0 : 5;
        }
        return 4;
    }
}
