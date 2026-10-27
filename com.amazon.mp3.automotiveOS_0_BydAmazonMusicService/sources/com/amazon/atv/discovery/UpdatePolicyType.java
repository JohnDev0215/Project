package com.amazon.atv.discovery;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum UpdatePolicyType implements NamedEnum {
    STALE_WHILE_REFRESH("staleWhileRefresh"),
    NEVER_STALE("neverStale"),
    STALE_IF_ERROR("staleIfError");

    private final String strValue;

    UpdatePolicyType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static UpdatePolicyType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (UpdatePolicyType updatePolicyType : values()) {
            if (updatePolicyType.strValue.equals(str)) {
                return updatePolicyType;
            }
        }
        return null;
    }
}
