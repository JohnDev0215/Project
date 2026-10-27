package com.amazon.atvplaybackresource.playready;

import com.amazon.avod.util.json.NamedEnum;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum StatusCode implements NamedEnum {
    DENIED("DENIED"),
    DEGRADED("DEGRADED"),
    ERROR(MediaError.ERROR_TYPE_ERROR),
    GRANTED("GRANTED");

    private final String strValue;

    StatusCode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static StatusCode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (StatusCode statusCode : values()) {
            if (statusCode.strValue.equals(str)) {
                return statusCode;
            }
        }
        return null;
    }
}
