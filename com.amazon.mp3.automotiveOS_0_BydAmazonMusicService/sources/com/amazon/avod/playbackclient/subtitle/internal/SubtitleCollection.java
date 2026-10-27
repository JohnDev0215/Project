package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.core.subtitle.SubtitleShowBackgroundStatus;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class SubtitleCollection {
    private final SubtitleShowBackgroundStatus mBackgroundStatus;
    private final String mDefaultRegion;
    private final String mDefaultStyle;
    private final String mFontSize;
    private final boolean mIsForced;
    private final String mLineHeight;
    private final Map<String, SubtitleRegionElement> mRegionMap;
    private final Map<String, SubtitleStyleElement> mStyleMap;
    private final ImmutableList<SubtitleTextElement> mSubtitleList;

    SubtitleCollection(@Nonnull ImmutableList<SubtitleTextElement> immutableList, @Nonnull Map<String, SubtitleStyleElement> map, @Nonnull Map<String, SubtitleRegionElement> map2, @Nullable String str, @Nullable String str2, boolean z, @Nullable String str3, @Nullable String str4, @Nonnull SubtitleShowBackgroundStatus subtitleShowBackgroundStatus) {
        this.mSubtitleList = (ImmutableList) Preconditions.checkNotNull(immutableList);
        this.mStyleMap = (Map) Preconditions.checkNotNull(map);
        this.mRegionMap = (Map) Preconditions.checkNotNull(map2);
        this.mDefaultRegion = str;
        this.mDefaultStyle = str2;
        this.mIsForced = z;
        this.mFontSize = str3;
        this.mLineHeight = str4;
        this.mBackgroundStatus = (SubtitleShowBackgroundStatus) Preconditions.checkNotNull(subtitleShowBackgroundStatus, "BackgroundStatus");
    }

    public boolean isEmpty() {
        return this.mSubtitleList.isEmpty();
    }

    public boolean isForced() {
        return this.mIsForced;
    }

    @Nullable
    public SubtitleStyleElement getStyleForElement(@Nonnull SubtitleTextElement subtitleTextElement) {
        String styleId;
        SubtitleRegionElement subtitleRegionElement;
        Preconditions.checkNotNull(subtitleTextElement, "element");
        if (subtitleTextElement.getFormat() == SubtitleFormat.DFXP) {
            String regionId = subtitleTextElement.getRegionId();
            if (this.mRegionMap.containsKey(regionId)) {
                subtitleRegionElement = this.mRegionMap.get(regionId);
            } else {
                subtitleRegionElement = this.mRegionMap.get(this.mDefaultRegion);
            }
            styleId = subtitleRegionElement != null ? subtitleRegionElement.getStyleId() : null;
        } else {
            styleId = subtitleTextElement.getStyleId();
        }
        if (this.mStyleMap.containsKey(styleId)) {
            return this.mStyleMap.get(styleId);
        }
        return this.mStyleMap.get(this.mDefaultStyle);
    }

    @Nullable
    public SubtitleRegionElement getRegionForElement(@Nonnull SubtitleTextElement subtitleTextElement) {
        Preconditions.checkNotNull(subtitleTextElement, "element");
        String regionId = subtitleTextElement.getRegionId();
        if (this.mRegionMap.containsKey(regionId)) {
            return this.mRegionMap.get(regionId);
        }
        return this.mRegionMap.get(this.mDefaultRegion);
    }

    @Nonnull
    public ImmutableList<SubtitleTextElement> subtitleSearch(long j) {
        int iBinarySearch = Collections.binarySearch(this.mSubtitleList, Long.valueOf(j));
        if (iBinarySearch < 0) {
            DLog.devf("No subtitle could be found for current playback time: %s", Long.valueOf(j));
            return ImmutableList.of();
        }
        return getAllSubtitlesForTimeCode(this.mSubtitleList, iBinarySearch, j);
    }

    private boolean isContainedInTimeSpan(int i, long j) {
        if (i < 0 || i >= this.mSubtitleList.size()) {
            return false;
        }
        SubtitleTextElement subtitleTextElement = this.mSubtitleList.get(i);
        return j >= subtitleTextElement.getBeginTimeStamp() && j <= subtitleTextElement.getEndTimeStamp();
    }

    private ImmutableList<SubtitleTextElement> getAllSubtitlesForTimeCode(ImmutableList<SubtitleTextElement> immutableList, int i, long j) {
        if (i < 0 || i > immutableList.size()) {
            return ImmutableList.of();
        }
        int i2 = i - 1;
        while (isContainedInTimeSpan(i2, j) && i2 >= 0) {
            i2--;
        }
        int i3 = i2 + 1;
        do {
            i++;
            if (!isContainedInTimeSpan(i, j)) {
                break;
            }
        } while (i < immutableList.size());
        return immutableList.subList(i3, i);
    }
}
