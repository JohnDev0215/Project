package com.amazon.avod.media.playback.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class CodecConfig {
    private final String mFourCC;

    public CodecConfig(String str) {
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(str.length() == 4, "fourCC is not 4 characters long.");
        Preconditions.checkArgument(CharMatcher.ascii().matchesAllOf(str), "fourCC is not in the ASCII range.");
        this.mFourCC = str;
    }

    @Nonnull
    public String getFourCC() {
        return this.mFourCC;
    }
}
