package com.amazon.avod.media.playback;

import com.google.common.base.Strings;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MimeTypes {
    public static boolean isAudio(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        return str.startsWith("audio/");
    }

    public static boolean isVideo(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        return str.startsWith("video/");
    }
}
