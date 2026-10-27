package com.amazon.avod.xray;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum FragmentVersion implements NamedEnum {
    LEGACY("Legacy"),
    SWIFT("Swift"),
    LIVE_SWIFT("LiveSwift");

    private final String strValue;

    FragmentVersion(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static FragmentVersion forValue(String str) {
        Preconditions.checkNotNull(str);
        for (FragmentVersion fragmentVersion : values()) {
            if (fragmentVersion.strValue.equals(str)) {
                return fragmentVersion;
            }
        }
        return null;
    }
}
