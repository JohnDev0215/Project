package com.android.bluetooth.a2dp;

import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.util.Log;
import com.android.bluetooth.R;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
class A2dpCodecConfig {
    private static final boolean DBG = true;
    private static final String TAG = "A2dpCodecConfig";
    private A2dpNativeInterface mA2dpNativeInterface;
    private BluetoothCodecConfig[] mCodecConfigOffloading;
    private Context mContext;
    private int mA2dpSourceCodecPrioritySbc = 0;
    private int mA2dpSourceCodecPriorityAac = 0;
    private int mA2dpSourceCodecPriorityAptx = 0;
    private int mA2dpSourceCodecPriorityAptxHd = 0;
    private int mA2dpSourceCodecPriorityLdac = 0;
    private BluetoothCodecConfig[] mCodecConfigPriorities = assignCodecConfigPriorities();

    A2dpCodecConfig(Context context, A2dpNativeInterface a2dpNativeInterface) {
        this.mCodecConfigOffloading = new BluetoothCodecConfig[0];
        this.mContext = context;
        this.mA2dpNativeInterface = a2dpNativeInterface;
        AudioManager audioManager = (AudioManager) this.mContext.getSystemService("audio");
        if (audioManager == null) {
            Log.w(TAG, "Can't obtain the codec offloading prefernece from null AudioManager");
        } else {
            this.mCodecConfigOffloading = (BluetoothCodecConfig[]) audioManager.getHwOffloadEncodingFormatsSupportedForA2DP().toArray(this.mCodecConfigOffloading);
        }
    }

    BluetoothCodecConfig[] codecConfigPriorities() {
        return this.mCodecConfigPriorities;
    }

    BluetoothCodecConfig[] codecConfigOffloading() {
        return this.mCodecConfigOffloading;
    }

    void setCodecConfigPreference(BluetoothDevice device, BluetoothCodecStatus codecStatus, BluetoothCodecConfig newCodecConfig) {
        Objects.requireNonNull(codecStatus);
        BluetoothCodecConfig[] selectableCodecs = codecStatus.getCodecsSelectableCapabilities();
        if (!Arrays.asList(selectableCodecs).stream().anyMatch(new Predicate() { // from class: com.android.bluetooth.a2dp.-$$Lambda$A2dpCodecConfig$Z31bjDKQd5WGjL5Ubtje4JP3xHE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((BluetoothCodecConfig) obj).isMandatoryCodec();
            }
        })) {
            Log.w(TAG, "setCodecConfigPreference: must have mandatory codec before changing.");
            return;
        }
        if (!codecStatus.isCodecConfigSelectable(newCodecConfig)) {
            Log.w(TAG, "setCodecConfigPreference: invalid codec " + Objects.toString(newCodecConfig));
            return;
        }
        int prioritizedCodecType = getPrioitizedCodecType(newCodecConfig, selectableCodecs);
        BluetoothCodecConfig currentCodecConfig = codecStatus.getCodecConfig();
        if (prioritizedCodecType == currentCodecConfig.getCodecType() && (prioritizedCodecType != newCodecConfig.getCodecType() || (currentCodecConfig.similarCodecFeedingParameters(newCodecConfig) && currentCodecConfig.sameCodecSpecificParameters(newCodecConfig)))) {
            Log.w(TAG, "setCodecConfigPreference: codec not changed.");
        } else {
            BluetoothCodecConfig[] codecConfigArray = {newCodecConfig};
            this.mA2dpNativeInterface.setCodecConfigPreference(device, codecConfigArray);
        }
    }

    void enableOptionalCodecs(BluetoothDevice device, BluetoothCodecConfig currentCodecConfig) {
        if (currentCodecConfig != null && !currentCodecConfig.isMandatoryCodec()) {
            Log.i(TAG, "enableOptionalCodecs: already using optional codec " + currentCodecConfig.getCodecName());
            return;
        }
        BluetoothCodecConfig[] codecConfigArray = assignCodecConfigPriorities();
        if (codecConfigArray == null) {
            return;
        }
        for (int i = 0; i < codecConfigArray.length; i++) {
            BluetoothCodecConfig codecConfig = codecConfigArray[i];
            if (!codecConfig.isMandatoryCodec()) {
                codecConfigArray[i] = null;
            }
        }
        this.mA2dpNativeInterface.setCodecConfigPreference(device, codecConfigArray);
    }

    void disableOptionalCodecs(BluetoothDevice device, BluetoothCodecConfig currentCodecConfig) {
        if (currentCodecConfig != null && currentCodecConfig.isMandatoryCodec()) {
            Log.i(TAG, "disableOptionalCodecs: already using mandatory codec.");
            return;
        }
        BluetoothCodecConfig[] codecConfigArray = assignCodecConfigPriorities();
        if (codecConfigArray == null) {
            return;
        }
        for (int i = 0; i < codecConfigArray.length; i++) {
            BluetoothCodecConfig codecConfig = codecConfigArray[i];
            if (codecConfig.isMandatoryCodec()) {
                codecConfig.setCodecPriority(1000000);
            } else {
                codecConfigArray[i] = null;
            }
        }
        this.mA2dpNativeInterface.setCodecConfigPreference(device, codecConfigArray);
    }

    private int getPrioitizedCodecType(BluetoothCodecConfig codecConfig, BluetoothCodecConfig[] selectableCodecs) {
        BluetoothCodecConfig prioritizedCodecConfig = codecConfig;
        for (BluetoothCodecConfig config : selectableCodecs) {
            if (prioritizedCodecConfig == null) {
                prioritizedCodecConfig = config;
            }
            if (config.getCodecPriority() > prioritizedCodecConfig.getCodecPriority()) {
                prioritizedCodecConfig = config;
            }
        }
        return prioritizedCodecConfig.getCodecType();
    }

    private BluetoothCodecConfig[] assignCodecConfigPriorities() {
        int value;
        int value2;
        int value3;
        int value4;
        int value5;
        Resources resources = this.mContext.getResources();
        if (resources == null) {
            return null;
        }
        try {
            int value6 = resources.getInteger(R.integer.a2dp_source_codec_priority_sbc);
            value = value6;
        } catch (Resources.NotFoundException e) {
            value = 0;
        }
        if (value >= -1 && value < 1000000) {
            this.mA2dpSourceCodecPrioritySbc = value;
        }
        try {
            int value7 = resources.getInteger(R.integer.a2dp_source_codec_priority_aac);
            value2 = value7;
        } catch (Resources.NotFoundException e2) {
            value2 = 0;
        }
        if (value2 >= -1 && value2 < 1000000) {
            this.mA2dpSourceCodecPriorityAac = value2;
        }
        try {
            int value8 = resources.getInteger(R.integer.a2dp_source_codec_priority_aptx);
            value3 = value8;
        } catch (Resources.NotFoundException e3) {
            value3 = 0;
        }
        if (value3 >= -1 && value3 < 1000000) {
            this.mA2dpSourceCodecPriorityAptx = value3;
        }
        try {
            int value9 = resources.getInteger(R.integer.a2dp_source_codec_priority_aptx_hd);
            value4 = value9;
        } catch (Resources.NotFoundException e4) {
            value4 = 0;
        }
        if (value4 >= -1 && value4 < 1000000) {
            this.mA2dpSourceCodecPriorityAptxHd = value4;
        }
        try {
            value5 = resources.getInteger(R.integer.a2dp_source_codec_priority_ldac);
        } catch (Resources.NotFoundException e5) {
            value5 = 0;
        }
        if (value5 >= -1 && value5 < 1000000) {
            this.mA2dpSourceCodecPriorityLdac = value5;
        }
        BluetoothCodecConfig codecConfig = new BluetoothCodecConfig(0, this.mA2dpSourceCodecPrioritySbc, 0, 0, 0, 0L, 0L, 0L, 0L);
        BluetoothCodecConfig codecConfig2 = new BluetoothCodecConfig(1, this.mA2dpSourceCodecPriorityAac, 0, 0, 0, 0L, 0L, 0L, 0L);
        BluetoothCodecConfig codecConfig3 = new BluetoothCodecConfig(2, this.mA2dpSourceCodecPriorityAptx, 0, 0, 0, 0L, 0L, 0L, 0L);
        BluetoothCodecConfig codecConfig4 = new BluetoothCodecConfig(3, this.mA2dpSourceCodecPriorityAptxHd, 0, 0, 0, 0L, 0L, 0L, 0L);
        BluetoothCodecConfig codecConfig5 = new BluetoothCodecConfig(4, this.mA2dpSourceCodecPriorityLdac, 0, 0, 0, 0L, 0L, 0L, 0L);
        BluetoothCodecConfig[] codecConfigArray = {codecConfig, codecConfig2, codecConfig3, codecConfig4, codecConfig5};
        return codecConfigArray;
    }
}
