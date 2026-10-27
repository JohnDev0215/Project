package com.amazon.atvplaybackdevice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum AudioQuality implements NamedEnum {
    AC3("AC3"),
    Stereo("Stereo");

    private final String strValue;

    AudioQuality(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AudioQuality forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AudioQuality audioQuality : values()) {
            if (audioQuality.strValue.equals(str)) {
                return audioQuality;
            }
        }
        return null;
    }
}
