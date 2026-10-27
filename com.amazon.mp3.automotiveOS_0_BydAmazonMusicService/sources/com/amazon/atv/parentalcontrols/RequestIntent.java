package com.amazon.atv.parentalcontrols;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum RequestIntent implements NamedEnum {
    DETAILS_PAGE_DECORATION("details_page_decoration"),
    PLAYBACK("playback"),
    PURCHASE("purchase");

    private final String strValue;

    RequestIntent(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static RequestIntent forValue(String str) {
        Preconditions.checkNotNull(str);
        for (RequestIntent requestIntent : values()) {
            if (requestIntent.strValue.equals(str)) {
                return requestIntent;
            }
        }
        return null;
    }
}
