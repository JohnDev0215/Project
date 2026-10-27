package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum PaginationType implements NamedEnum {
    WIDGETS("widgets"),
    ITEMS(ContextMappingConstants.ITEMS);

    private final String strValue;

    PaginationType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static PaginationType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (PaginationType paginationType : values()) {
            if (paginationType.strValue.equals(str)) {
                return paginationType;
            }
        }
        return null;
    }
}
