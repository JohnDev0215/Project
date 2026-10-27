package com.amazon.avod.media.playback;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum ContentType {
    Feature,
    Trailer,
    LiveStreaming,
    External;

    public static boolean isLive(@Nonnull ContentType contentType) {
        return contentType.equals(LiveStreaming) || contentType.equals(External);
    }

    @Nonnull
    public static ContentType fromString(@Nullable String str) {
        for (ContentType contentType : values()) {
            if (contentType.name().equals(str)) {
                return contentType;
            }
        }
        return Feature;
    }
}
