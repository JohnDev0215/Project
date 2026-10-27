package com.amazon.avod.content.smoothstream.manifest;

/* JADX INFO: loaded from: classes2.dex */
public enum StreamType {
    UNSUPPORTED,
    AUDIO,
    VIDEO,
    SUBTITLES,
    IMAGE;

    public static StreamType fromString(String str) {
        for (StreamType streamType : values()) {
            if (str.trim().equalsIgnoreCase(streamType.toString())) {
                return streamType;
            }
        }
        return UNSUPPORTED;
    }

    public static boolean isEssential(StreamType streamType) {
        return streamType == VIDEO || streamType == AUDIO;
    }
}
