package com.amazon.av.clientdownloadservice;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OwnerApplicationType implements NamedEnum {
    PVVR_ANDROID("PVVR_ANDROID"),
    DEFAULT_ANDROID("DEFAULT_ANDROID"),
    DEFAULT_IOS("DEFAULT_IOS"),
    KFT_ANDROID("KFT_ANDROID"),
    WINDOWS_APP("WINDOWS_APP"),
    FTV_ANDROID("FTV_ANDROID"),
    MACOS_APP("MACOS_APP"),
    READY_NOW_ANDROID("READY_NOW_ANDROID"),
    UNKNOWN_ANDROID("UNKNOWN_ANDROID");

    private final String strValue;

    OwnerApplicationType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OwnerApplicationType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OwnerApplicationType ownerApplicationType : values()) {
            if (ownerApplicationType.strValue.equals(str)) {
                return ownerApplicationType;
            }
        }
        return null;
    }
}
