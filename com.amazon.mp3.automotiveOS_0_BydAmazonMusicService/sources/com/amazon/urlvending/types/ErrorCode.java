package com.amazon.urlvending.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public enum ErrorCode implements NamedEnum {
    NoInterstitialIdMatch("NoInterstitialIdMatch"),
    NoAssetsFound("NoAssetsFound");

    private final String strValue;

    ErrorCode(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ErrorCode forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ErrorCode errorCode : values()) {
            if (errorCode.strValue.equals(str)) {
                return errorCode;
            }
        }
        return null;
    }
}
