package com.amazon.atv.schedule.distribution;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum LinearProgramType implements NamedEnum {
    OTHER("Other"),
    SPORTS("Sports"),
    SERIES("Series"),
    MOVIES("Movies"),
    EPISODE("Episode"),
    SHOW("Show"),
    SEASON("Season");

    private final String strValue;

    LinearProgramType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static LinearProgramType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (LinearProgramType linearProgramType : values()) {
            if (linearProgramType.strValue.equals(str)) {
                return linearProgramType;
            }
        }
        return null;
    }
}
