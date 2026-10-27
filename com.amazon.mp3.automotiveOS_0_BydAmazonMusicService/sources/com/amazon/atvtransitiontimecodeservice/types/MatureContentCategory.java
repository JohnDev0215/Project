package com.amazon.atvtransitiontimecodeservice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum MatureContentCategory implements NamedEnum {
    VIOLENCE("VIOLENCE"),
    PROFANITY("PROFANITY"),
    NUDITY("NUDITY"),
    SUBSTANCE_USE("SUBSTANCE_USE");

    private final String strValue;

    MatureContentCategory(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static MatureContentCategory forValue(String str) {
        Preconditions.checkNotNull(str);
        for (MatureContentCategory matureContentCategory : values()) {
            if (matureContentCategory.strValue.equals(str)) {
                return matureContentCategory;
            }
        }
        return null;
    }
}
