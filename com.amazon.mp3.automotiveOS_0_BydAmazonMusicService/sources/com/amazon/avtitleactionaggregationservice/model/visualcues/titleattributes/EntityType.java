package com.amazon.avtitleactionaggregationservice.model.visualcues.titleattributes;

import com.amazon.avod.util.json.NamedEnum;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public enum EntityType implements NamedEnum {
    PARTICIPANT("PARTICIPANT"),
    SERIES("SERIES"),
    STATION("STATION"),
    SHOULDER_CONTENT("SHOULDER_CONTENT"),
    SPORT("SPORT"),
    TOURNAMENT("TOURNAMENT"),
    EPISODE("EPISODE"),
    SEASON("SEASON"),
    EVENT("EVENT"),
    MOVIE("MOVIE"),
    LEAGUE("LEAGUE"),
    PROMOTION("PROMOTION"),
    BROADCAST("BROADCAST"),
    REPLAY("REPLAY"),
    UNKNOWN("UNKNOWN");

    private final String strValue;

    EntityType(String str) {
        this.strValue = str;
    }

    @Override // com.amazon.avod.util.json.NamedEnum
    public String getValue() {
        return this.strValue;
    }

    public static EntityType forValue(String str) {
        Preconditions.checkNotNull(str);
        for (EntityType entityType : values()) {
            if (entityType.strValue.equals(str)) {
                return entityType;
            }
        }
        return null;
    }
}
