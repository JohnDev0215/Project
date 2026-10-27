package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum SubtitleRepresentation {
    NotInManifestNorStream("NotInManifestNorStream"),
    SeparateStreamInManifest("SeparateStreamInManifest"),
    EmbeddedInStream("EmbeddedInStream"),
    BurnedIn("BurnedIn"),
    Unknown("Unknown");

    private final String strValue;

    SubtitleRepresentation(String str) {
        this.strValue = str;
    }

    @JsonValue
    public String getValue() {
        return this.strValue;
    }

    @JsonCreator
    @Nullable
    public static SubtitleRepresentation forValue(String str) {
        if (str == null) {
            return null;
        }
        for (SubtitleRepresentation subtitleRepresentation : values()) {
            if (str.equalsIgnoreCase(subtitleRepresentation.getValue())) {
                return subtitleRepresentation;
            }
        }
        return null;
    }
}
