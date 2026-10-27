package com.amazon.atvplaybackdevice.types;

import PlaybackInterface.v1_0.AudioStreamFormat;
import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum StreamingTechnology implements NamedEnum {
    MP4("MP4"),
    WMV("WMV"),
    MPEGPS("MPEGPS"),
    SmoothStreaming("SmoothStreaming"),
    MPEGTS("MPEGTS"),
    DASH("DASH"),
    HLS(AudioStreamFormat.HLS);

    private final String strValue;

    StreamingTechnology(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static StreamingTechnology forValue(String str) {
        Preconditions.checkNotNull(str);
        for (StreamingTechnology streamingTechnology : values()) {
            if (streamingTechnology.strValue.equals(str)) {
                return streamingTechnology;
            }
        }
        return null;
    }
}
