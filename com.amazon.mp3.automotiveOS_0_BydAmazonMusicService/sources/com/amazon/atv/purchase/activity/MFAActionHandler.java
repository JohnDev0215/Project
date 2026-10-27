package com.amazon.atv.purchase.activity;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum MFAActionHandler implements NamedEnum {
    IN_APP("IN_APP"),
    OUT_OF_BAND("OUT_OF_BAND");

    private final String strValue;

    MFAActionHandler(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static MFAActionHandler forValue(String str) {
        Preconditions.checkNotNull(str);
        for (MFAActionHandler mFAActionHandler : values()) {
            if (mFAActionHandler.strValue.equals(str)) {
                return mFAActionHandler;
            }
        }
        return null;
    }
}
