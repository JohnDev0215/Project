package com.amazon.avod.playbackclient.subtitle.internal;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleElement {
    private final List<SubtitleAnnotationElement> mAfterAnnotationElements;
    private final List<SubtitleAnnotationElement> mBeforeAnnotationElements;
    private final int mEndIndex;
    private final int mStartIndex;
    private final List<SubtitleTextStyle> mSubtitleTextCombineStyleElements;
    private final String mText;

    public SubtitleElement(int i, int i2, @Nonnull String str, @Nonnull List<SubtitleAnnotationElement> list, @Nonnull List<SubtitleAnnotationElement> list2, @Nonnull List<SubtitleTextStyle> list3) {
        this.mStartIndex = i;
        this.mEndIndex = i2;
        this.mText = (String) Preconditions.checkNotNull(str, "text");
        this.mBeforeAnnotationElements = new ArrayList((Collection) Preconditions.checkNotNull(list, "beforeAnnotationElements"));
        this.mAfterAnnotationElements = new ArrayList((Collection) Preconditions.checkNotNull(list2, "afterAnnotationElements"));
        this.mSubtitleTextCombineStyleElements = (List) Preconditions.checkNotNull(list3, "subtitleStyleElements");
    }

    public int getStartIndex() {
        return this.mStartIndex;
    }

    public int getEndIndex() {
        return this.mEndIndex;
    }

    @Nonnull
    public String getText() {
        return this.mText;
    }

    @Nonnull
    public List<SubtitleAnnotationElement> getBeforeAnnotationElements() {
        return this.mBeforeAnnotationElements;
    }

    @Nonnull
    public List<SubtitleAnnotationElement> getAfterAnnotationElements() {
        return this.mAfterAnnotationElements;
    }

    public boolean hasTextCombineElements() {
        return this.mSubtitleTextCombineStyleElements.size() > 0;
    }

    @Nonnull
    public List<SubtitleTextStyle> getSubtitleTextCombineStyleElements() {
        return this.mSubtitleTextCombineStyleElements;
    }
}
