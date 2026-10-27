package com.amazon.atvtransitiontimecodeservice.types;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum ActionToPerform implements NamedEnum {
    MUTE("MUTE"),
    SEEK("SEEK"),
    MASK("MASK");

    private final String strValue;

    ActionToPerform(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static ActionToPerform forValue(String str) {
        Preconditions.checkNotNull(str);
        for (ActionToPerform actionToPerform : values()) {
            if (actionToPerform.strValue.equals(str)) {
                return actionToPerform;
            }
        }
        return null;
    }
}
