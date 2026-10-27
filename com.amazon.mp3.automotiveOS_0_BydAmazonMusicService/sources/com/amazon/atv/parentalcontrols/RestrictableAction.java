package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum RestrictableAction implements NamedEnum {
    PLAYBACK("playback"),
    CONTENT_DISCOVERY("contentDiscovery"),
    PURCHASE("purchase");

    private final String strValue;

    RestrictableAction(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static RestrictableAction forValue(String str) {
        Preconditions.checkNotNull(str);
        for (RestrictableAction restrictableAction : values()) {
            if (restrictableAction.strValue.equals(str)) {
                return restrictableAction;
            }
        }
        return null;
    }
}
