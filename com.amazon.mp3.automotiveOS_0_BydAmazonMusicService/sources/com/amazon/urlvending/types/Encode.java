package com.amazon.urlvending.types;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum Encode implements NamedEnum {
    H265(ConfigurablePlaybackSupportEvaluator.PRS_HEVC_OVERRIDE),
    H264(ConfigurablePlaybackSupportEvaluator.PRS_AVC_OVERRIDE),
    NONE(PlaybackMode.NONE);

    private final String strValue;

    Encode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static Encode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (Encode encode : values()) {
            if (encode.strValue.equals(str)) {
                return encode;
            }
        }
        return null;
    }
}
