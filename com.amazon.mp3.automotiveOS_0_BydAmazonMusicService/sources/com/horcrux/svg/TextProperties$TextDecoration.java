package com.horcrux.svg;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes7.dex */
enum TextProperties$TextDecoration {
    None("none"),
    Underline("underline"),
    Overline("overline"),
    LineThrough("line-through"),
    Blink("blink");

    private static final Map<String, TextProperties$TextDecoration> decorationToEnum = new HashMap();
    private final String decoration;

    static {
        for (TextProperties$TextDecoration textProperties$TextDecoration : values()) {
            decorationToEnum.put(textProperties$TextDecoration.decoration, textProperties$TextDecoration);
        }
    }

    TextProperties$TextDecoration(String str) {
        this.decoration = str;
    }

    static TextProperties$TextDecoration getEnum(String str) {
        Map<String, TextProperties$TextDecoration> map = decorationToEnum;
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }
        return map.get(str);
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return this.decoration;
    }
}
