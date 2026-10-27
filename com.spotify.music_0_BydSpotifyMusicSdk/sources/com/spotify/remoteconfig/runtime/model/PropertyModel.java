package com.spotify.remoteconfig.runtime.model;

/* JADX INFO: loaded from: classes.dex */
public interface PropertyModel {

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class PropertyModelType {
        public static final /* synthetic */ PropertyModelType[] c = {new PropertyModelType("INTEGER", 0), new PropertyModelType("BOOL", 1), new PropertyModelType("ENUM", 2)};

        /* JADX INFO: Fake field, exist only in values array */
        PropertyModelType EF5;

        public static PropertyModelType valueOf(String str) {
            return (PropertyModelType) Enum.valueOf(PropertyModelType.class, str);
        }

        public static PropertyModelType[] values() {
            return (PropertyModelType[]) c.clone();
        }
    }
}
