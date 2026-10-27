package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes7.dex */
enum TextProperties$AlignmentBaseline {
    baseline("baseline"),
    textBottom("text-bottom"),
    alphabetic("alphabetic"),
    ideographic("ideographic"),
    middle("middle"),
    central("central"),
    mathematical("mathematical"),
    textTop("text-top"),
    bottom("bottom"),
    center("center"),
    top("top"),
    textBeforeEdge("text-before-edge"),
    textAfterEdge("text-after-edge"),
    beforeEdge("before-edge"),
    afterEdge("after-edge"),
    hanging("hanging");

    private static final Map<String, TextProperties$AlignmentBaseline> alignmentToEnum = new HashMap();
    private final String alignment;

    static {
        for (TextProperties$AlignmentBaseline textProperties$AlignmentBaseline : values()) {
            alignmentToEnum.put(textProperties$AlignmentBaseline.alignment, textProperties$AlignmentBaseline);
        }
    }

    TextProperties$AlignmentBaseline(String str) {
        this.alignment = str;
    }

    static TextProperties$AlignmentBaseline getEnum(String str) {
        Map<String, TextProperties$AlignmentBaseline> map = alignmentToEnum;
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }
        return map.get(str);
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.alignment;
    }
}
