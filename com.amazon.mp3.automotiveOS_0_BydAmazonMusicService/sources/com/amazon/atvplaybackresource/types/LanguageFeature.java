package com.amazon.atvplaybackresource.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum LanguageFeature implements NamedEnum {
    MLFv2("MLFv2");

    private final String strValue;

    LanguageFeature(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LanguageFeature forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LanguageFeature languageFeature : values()) {
            if (languageFeature.strValue.equals(str)) {
                return languageFeature;
            }
        }
        return null;
    }
}
