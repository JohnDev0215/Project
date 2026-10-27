package com.amazon.avod.content.image;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class TrickplayImage {
    private final String mFilepath;
    private final long mTimecodeMillis;

    public TrickplayImage(long j, @Nonnull String str) {
        Preconditions.checkArgument(j >= 0, "Timecode cannot be negative. Received %d", j);
        this.mTimecodeMillis = j;
        this.mFilepath = (String) Preconditions.checkNotNull(str);
    }

    public long getTimecodeMillis() {
        return this.mTimecodeMillis;
    }

    @Nonnull
    public String getImageFilepath() {
        return this.mFilepath;
    }
}
