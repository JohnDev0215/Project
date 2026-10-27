package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class SubtitleTextElement implements Comparable<Long> {
    private final List<SubtitleAnnotationElement> mAnnotationElements;
    private final long mBeginTimeMillis;
    private final long mEndTimeMillis;
    private final SubtitleFormat mFormat;
    private final SubtitleRegionElement mRegionElement;
    private final SubtitleStyleElement mStyleElement;
    private final String mSubtitleText;
    private final List<SubtitleTextStyle> mTextStyles;

    private SubtitleTextElement(@Nonnull String str, @Nonnull SubtitleFormat subtitleFormat, long j, long j2, @Nullable SubtitleRegionElement subtitleRegionElement, @Nullable SubtitleStyleElement subtitleStyleElement, @Nonnull List<SubtitleTextStyle> list, @Nonnull List<SubtitleAnnotationElement> list2) {
        this.mSubtitleText = (String) Preconditions.checkNotNull(str, "subtitleText");
        this.mFormat = (SubtitleFormat) Preconditions.checkNotNull(subtitleFormat, "format");
        this.mBeginTimeMillis = j;
        this.mEndTimeMillis = j2;
        this.mRegionElement = subtitleRegionElement;
        this.mStyleElement = subtitleStyleElement;
        this.mTextStyles = (List) Preconditions.checkNotNull(list, "textStyles");
        this.mAnnotationElements = (List) Preconditions.checkNotNull(list2, "annotationElements");
    }

    @Nonnull
    static Builder newBuilder() {
        return new Builder();
    }

    public SubtitleFormat getFormat() {
        return this.mFormat;
    }

    public long getBeginTimeStamp() {
        return this.mBeginTimeMillis;
    }

    public long getEndTimeStamp() {
        return this.mEndTimeMillis;
    }

    @Nonnull
    public String getSubtitleText() {
        return this.mSubtitleText;
    }

    @Nullable
    public String getRegionId() {
        SubtitleRegionElement subtitleRegionElement = this.mRegionElement;
        if (subtitleRegionElement == null) {
            return null;
        }
        return subtitleRegionElement.getId();
    }

    @Nullable
    public SubtitleRegionElement getRegionElement() {
        return this.mRegionElement;
    }

    @Nullable
    public SubtitleStyleElement getStyleElement() {
        return this.mStyleElement;
    }

    @Nullable
    public String getStyleId() {
        SubtitleStyleElement subtitleStyleElement = this.mStyleElement;
        if (subtitleStyleElement == null) {
            return null;
        }
        return subtitleStyleElement.getId();
    }

    @Nonnull
    public List<SubtitleTextStyle> getTextStyles() {
        return this.mTextStyles;
    }

    @Nonnull
    public List<SubtitleAnnotationElement> getAnnotationElements() {
        return this.mAnnotationElements;
    }

    public boolean isVertical() {
        SubtitleRegionElement subtitleRegionElement = this.mRegionElement;
        return subtitleRegionElement != null && subtitleRegionElement.isVertical();
    }

    static class Builder {
        private List<SubtitleAnnotationElement> mAnnotationElements;
        private long mBeginTime = -1;
        private long mEndTime = -1;
        private SubtitleFormat mFormat;
        private SubtitleRegionElement mRegionElement;
        private SubtitleStyleElement mStyleElement;
        private String mSubtitleText;
        private List<SubtitleTextStyle> mTextStyles;

        Builder() {
        }

        Builder setFormat(SubtitleFormat subtitleFormat) {
            this.mFormat = (SubtitleFormat) Preconditions.checkNotNull(subtitleFormat, "format");
            return this;
        }

        Builder setBeginTime(long j) {
            Preconditions.checkArgument(j >= 0, "Cannot set a negative begin time");
            this.mBeginTime = j;
            return this;
        }

        Builder setEndTime(long j) {
            Preconditions.checkArgument(j >= 0, "Cannot set a negative end time");
            this.mEndTime = j;
            return this;
        }

        Builder setSubtitleText(@Nonnull String str) {
            this.mSubtitleText = (String) Preconditions.checkNotNull(str);
            return this;
        }

        Builder setRegion(@Nullable SubtitleRegionElement subtitleRegionElement) {
            this.mRegionElement = subtitleRegionElement;
            return this;
        }

        Builder setStyle(@Nullable SubtitleStyleElement subtitleStyleElement) {
            this.mStyleElement = subtitleStyleElement;
            return this;
        }

        Builder setTextStyles(@Nonnull List<SubtitleTextStyle> list) {
            this.mTextStyles = list;
            return this;
        }

        Builder setAnnotationElements(@Nonnull List<SubtitleAnnotationElement> list) {
            this.mAnnotationElements = list;
            return this;
        }

        SubtitleTextElement build() {
            Preconditions.checkNotNull(this.mSubtitleText, "mSubtitleText");
            Preconditions.checkNotNull(this.mFormat, "mFormat");
            Preconditions.checkState(this.mBeginTime != -1, "Begin time is required");
            Preconditions.checkState(this.mEndTime != -1, "End time is required");
            long j = this.mBeginTime;
            long j2 = this.mEndTime;
            Preconditions.checkState(j < j2, "Begin time %d must be less than end time %d", j, j2);
            Preconditions.checkNotNull(this.mTextStyles, "text styles");
            Preconditions.checkNotNull(this.mAnnotationElements, "Subtitle Annotations");
            return new SubtitleTextElement(this.mSubtitleText, this.mFormat, this.mBeginTime, this.mEndTime, this.mRegionElement, this.mStyleElement, this.mTextStyles, this.mAnnotationElements);
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Long l) {
        if (l.longValue() < this.mBeginTimeMillis) {
            return 1;
        }
        return l.longValue() > this.mEndTimeMillis ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SubtitleTextElement)) {
            return false;
        }
        SubtitleTextElement subtitleTextElement = (SubtitleTextElement) obj;
        return Objects.equal(Long.valueOf(this.mBeginTimeMillis), Long.valueOf(subtitleTextElement.mBeginTimeMillis)) && Objects.equal(Long.valueOf(this.mEndTimeMillis), Long.valueOf(subtitleTextElement.mEndTimeMillis)) && Objects.equal(this.mSubtitleText, subtitleTextElement.mSubtitleText);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mBeginTimeMillis), Long.valueOf(this.mEndTimeMillis), this.mSubtitleText);
    }
}
