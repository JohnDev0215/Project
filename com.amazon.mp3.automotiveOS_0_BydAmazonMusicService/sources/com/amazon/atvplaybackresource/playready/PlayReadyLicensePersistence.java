package com.amazon.atvplaybackresource.playready;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayReadyLicensePersistence implements NamedEnum {
    PERSISTENT("Persistent"),
    NON_PERSISTENT("Non-persistent");

    private final String strValue;

    PlayReadyLicensePersistence(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static PlayReadyLicensePersistence forValue(String str) {
        Preconditions.checkNotNull(str);
        for (PlayReadyLicensePersistence playReadyLicensePersistence : values()) {
            if (playReadyLicensePersistence.strValue.equals(str)) {
                return playReadyLicensePersistence;
            }
        }
        return null;
    }
}
