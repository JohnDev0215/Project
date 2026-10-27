package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum SegmentType implements NamedEnum {
    OTHER("OTHER"),
    VIOLENCE("VIOLENCE"),
    NUDITY("NUDITY"),
    SIMULATED_SEX("SIMULATED_SEX"),
    SPOILER("SPOILER"),
    STUDIO_ASK("STUDIO_ASK"),
    BLOCKED("BLOCKED"),
    ADULT("ADULT"),
    EXPLICIT("EXPLICIT");

    private final String strValue;

    SegmentType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static SegmentType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (SegmentType segmentType : values()) {
            if (segmentType.strValue.equals(str)) {
                return segmentType;
            }
        }
        return null;
    }
}
