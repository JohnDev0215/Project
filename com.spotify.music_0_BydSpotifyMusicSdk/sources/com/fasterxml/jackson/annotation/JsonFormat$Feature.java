package com.fasterxml.jackson.annotation;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonFormat$Feature {
    public static final /* synthetic */ JsonFormat$Feature[] c = {new JsonFormat$Feature("ACCEPT_SINGLE_VALUE_AS_ARRAY", 0), new JsonFormat$Feature("ACCEPT_CASE_INSENSITIVE_PROPERTIES", 1), new JsonFormat$Feature("ACCEPT_CASE_INSENSITIVE_VALUES", 2), new JsonFormat$Feature("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS", 3), new JsonFormat$Feature("WRITE_DATES_WITH_ZONE_ID", 4), new JsonFormat$Feature("WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED", 5), new JsonFormat$Feature("WRITE_SORTED_MAP_ENTRIES", 6), new JsonFormat$Feature("ADJUST_DATES_TO_CONTEXT_TIME_ZONE", 7)};

    /* JADX INFO: Fake field, exist only in values array */
    JsonFormat$Feature EF5;

    public static JsonFormat$Feature valueOf(String str) {
        return (JsonFormat$Feature) Enum.valueOf(JsonFormat$Feature.class, str);
    }

    public static JsonFormat$Feature[] values() {
        return (JsonFormat$Feature[]) c.clone();
    }
}
