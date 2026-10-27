package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum AudioFormat implements NamedEnum {
    AC3("AC3"),
    STEREO("STEREO");

    private final String strValue;

    AudioFormat(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static AudioFormat forValue(String str) {
        Preconditions.checkNotNull(str);
        for (AudioFormat audioFormat : values()) {
            if (audioFormat.strValue.equals(str)) {
                return audioFormat;
            }
        }
        return null;
    }
}
