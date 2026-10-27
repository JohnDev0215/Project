package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum XrayQuestionItemState implements NamedEnum {
    CANCELLED("cancelled"),
    ACTIVE(AppStateModule.APP_STATE_ACTIVE),
    INACTIVE("inactive"),
    RESOLVED("resolved");

    private final String strValue;

    XrayQuestionItemState(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static XrayQuestionItemState forValue(String str) {
        Preconditions.checkNotNull(str);
        for (XrayQuestionItemState xrayQuestionItemState : values()) {
            if (xrayQuestionItemState.strValue.equals(str)) {
                return xrayQuestionItemState;
            }
        }
        return null;
    }
}
