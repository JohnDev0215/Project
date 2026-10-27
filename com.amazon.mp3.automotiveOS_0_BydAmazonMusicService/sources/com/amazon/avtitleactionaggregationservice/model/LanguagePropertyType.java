package com.amazon.avtitleactionaggregationservice.model;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum LanguagePropertyType implements NamedEnum {
    Commentary("Commentary"),
    Descriptive("Descriptive"),
    Dialog("Dialog"),
    NoDialog("NoDialog");

    private final String strValue;

    LanguagePropertyType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LanguagePropertyType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LanguagePropertyType languagePropertyType : values()) {
            if (languagePropertyType.strValue.equals(str)) {
                return languagePropertyType;
            }
        }
        return null;
    }
}
