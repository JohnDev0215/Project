package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum Codec implements NamedEnum {
    H265(ConfigurablePlaybackSupportEvaluator.PRS_HEVC_OVERRIDE),
    H264(ConfigurablePlaybackSupportEvaluator.PRS_AVC_OVERRIDE),
    AV1(ConfigurablePlaybackSupportEvaluator.PRS_AV1_OVERRIDE);

    private final String strValue;

    Codec(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Codec forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Codec codec : values()) {
            if (codec.strValue.equals(str)) {
                return codec;
            }
        }
        return null;
    }
}
