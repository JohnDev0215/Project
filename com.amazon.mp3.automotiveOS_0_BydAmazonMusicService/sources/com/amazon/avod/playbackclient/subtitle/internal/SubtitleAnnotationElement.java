package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleAnnotationTextPosition;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleAnnotationElement {
    private final String mAnnotationText;
    private final int mEnd;
    private final int mStart;
    private final SubtitleAnnotationTextPosition mTextPosition;

    private SubtitleAnnotationElement(int i, int i2, @Nonnull String str, @Nonnull SubtitleAnnotationTextPosition subtitleAnnotationTextPosition) {
        this.mStart = i;
        this.mEnd = i2;
        this.mAnnotationText = str;
        this.mTextPosition = subtitleAnnotationTextPosition;
    }

    public int getStart() {
        return this.mStart;
    }

    public int getEnd() {
        return this.mEnd;
    }

    @Nonnull
    public String getAnnotationText() {
        return this.mAnnotationText;
    }

    @Nonnull
    public SubtitleAnnotationTextPosition getTextPosition() {
        return this.mTextPosition;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("Start", this.mStart).add("End", this.mEnd).add("AnnotationText", this.mAnnotationText).add("TextPosition", this.mTextPosition).toString();
    }

    @Nonnull
    static Builder newBuilder() {
        return new Builder();
    }

    static class Builder {
        private String mAnnotationText;
        private int mEnd;
        private int mStart;
        private SubtitleAnnotationTextPosition mTextPosition;

        Builder() {
        }

        Builder setStart(int i) {
            this.mStart = i;
            return this;
        }

        Builder setEnd(int i) {
            this.mEnd = i;
            return this;
        }

        Builder setAnnotationText(@Nonnull String str) {
            this.mAnnotationText = (String) Preconditions.checkNotNull(str, "annotationText");
            return this;
        }

        Builder setTextPosition(@Nonnull SubtitleAnnotationTextPosition subtitleAnnotationTextPosition) {
            this.mTextPosition = (SubtitleAnnotationTextPosition) Preconditions.checkNotNull(subtitleAnnotationTextPosition, "textPosition");
            return this;
        }

        SubtitleAnnotationElement build() {
            Preconditions.checkNotNull(this.mAnnotationText, "AnnotationText");
            return new SubtitleAnnotationElement(this.mStart, this.mEnd, this.mAnnotationText, this.mTextPosition);
        }
    }
}
