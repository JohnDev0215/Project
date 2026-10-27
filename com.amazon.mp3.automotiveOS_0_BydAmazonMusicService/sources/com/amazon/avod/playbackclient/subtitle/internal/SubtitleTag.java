package com.amazon.avod.playbackclient.subtitle.internal;

/* JADX INFO: loaded from: classes2.dex */
enum SubtitleTag {
    BODY("body"),
    BR("br"),
    DIV("div"),
    HEAD("head"),
    INITIAL("initial"),
    METADATA("metadata"),
    P("p"),
    SPAN("span"),
    TT("tt"),
    STYLE("style"),
    STYLING("styling"),
    LAYOUT("layout"),
    REGION("region"),
    INFORMATION("information"),
    UNRECOGNIZED(null);

    private String mTag;

    SubtitleTag(String str) {
        this.mTag = str;
    }

    public static SubtitleTag findMatch(String str) {
        if (str == null) {
            return UNRECOGNIZED;
        }
        for (SubtitleTag subtitleTag : values()) {
            if (str.equalsIgnoreCase(subtitleTag.mTag)) {
                return subtitleTag;
            }
        }
        return UNRECOGNIZED;
    }
}
