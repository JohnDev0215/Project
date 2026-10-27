package com.amazon.atv.cuepoint;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum CardType implements NamedEnum {
    Ad("Ad"),
    Aux("Aux");

    private final String strValue;

    CardType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static CardType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (CardType cardType : values()) {
            if (cardType.strValue.equals(str)) {
                return cardType;
            }
        }
        return null;
    }
}
