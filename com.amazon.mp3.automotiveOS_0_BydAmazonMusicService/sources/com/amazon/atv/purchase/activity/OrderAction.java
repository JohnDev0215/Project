package com.amazon.atv.purchase.activity;

import com.amazon.avod.util.json.NamedEnum;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum OrderAction implements NamedEnum {
    POLL("POLL"),
    ERROR(MediaError.ERROR_TYPE_ERROR),
    AUTH_AND_POLL("AUTH_AND_POLL"),
    OK("OK");

    private final String strValue;

    OrderAction(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static OrderAction forValue(String str) {
        Preconditions.checkNotNull(str);
        for (OrderAction orderAction : values()) {
            if (orderAction.strValue.equals(str)) {
                return orderAction;
            }
        }
        return null;
    }
}
