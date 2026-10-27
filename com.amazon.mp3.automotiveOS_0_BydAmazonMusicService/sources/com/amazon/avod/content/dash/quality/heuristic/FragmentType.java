package com.amazon.avod.content.dash.quality.heuristic;

import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public enum FragmentType {
    DEFAULT(0),
    BASELINE(1),
    UPGRADE(2);

    private final int mValue;

    public static FragmentType valueOf(int i) {
        for (FragmentType fragmentType : values()) {
            if (fragmentType.mValue == i) {
                return fragmentType;
            }
        }
        throw new IllegalArgumentException(String.format(Locale.US, "There is no fragment type with value %s", Integer.valueOf(i)));
    }

    FragmentType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
