package com.amazon.avod.content.dash.quality.heuristic;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public enum StreamBehavior {
    DEFAULT(0),
    DUAL_REQUEST(1);

    private final int mValue;

    public static StreamBehavior valueOf(int i) {
        for (StreamBehavior streamBehavior : values()) {
            if (streamBehavior.mValue == i) {
                return streamBehavior;
            }
        }
        throw new IllegalArgumentException(String.format(Locale.US, "There is no stream behavior with value %s", Integer.valueOf(i)));
    }

    StreamBehavior(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
