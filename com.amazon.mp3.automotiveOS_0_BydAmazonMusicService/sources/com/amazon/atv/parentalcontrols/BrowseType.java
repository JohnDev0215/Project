package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum BrowseType implements NamedEnum {
    BROWSE("browse"),
    CATEGORY_STRUCTURE("categoryStructure"),
    SIMILARITIES("similarities"),
    SEARCH("search"),
    RECOMMENDATIONS("recommendations");

    private final String strValue;

    BrowseType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static BrowseType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (BrowseType browseType : values()) {
            if (browseType.strValue.equals(str)) {
                return browseType;
            }
        }
        return null;
    }
}
