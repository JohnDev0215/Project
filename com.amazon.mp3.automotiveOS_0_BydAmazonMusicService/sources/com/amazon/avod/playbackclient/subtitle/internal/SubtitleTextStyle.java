package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleTextStyle {
    private int mEnd;
    private boolean mHasTextCombineTag;
    private int mStart;
    private String mStyleId;

    public void setStart(int i) {
        this.mStart = i;
    }

    public void setEnd(int i) {
        this.mEnd = i;
    }

    public void setStyleId(@Nonnull String str) {
        this.mStyleId = (String) Preconditions.checkNotNull(str, "annotationText");
    }

    public void setHasTextCombineTag(boolean z) {
        this.mHasTextCombineTag = z;
    }

    public int getStart() {
        return this.mStart;
    }

    public int getEnd() {
        return this.mEnd;
    }

    @Nonnull
    public String getStyleId() {
        return this.mStyleId;
    }

    public boolean hasTextCombineTag() {
        return this.mHasTextCombineTag;
    }
}
