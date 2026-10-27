package com.amazon.avod.playbackclient.subtitle.internal;

import android.annotation.SuppressLint;
import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleAttribute;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeSubtitleElement {
    public static final CompositeSubtitleElement NO_CAPTION = new CompositeSubtitleElement("", null, null);
    private final Dimension mHorizontalSubtitleExtent;
    private final boolean mIsForcedNarrative;
    private final SubtitleRegionElement mRegion;
    private final SubtitleStyleElement mStyle;
    private final Dimension mSubtitleLeftOrigin;
    private final Dimension mSubtitleRightOrigin;
    private final ImmutableList<SubtitleTextElement> mSubtitleTextElements;
    private final Dimension mSubtitleTopOrigin;
    private final String mText;
    private final Dimension mVerticalSubtitleExtent;

    public CompositeSubtitleElement(@Nonnull String str, @Nullable SubtitleRegionElement subtitleRegionElement, @Nullable SubtitleStyleElement subtitleStyleElement) {
        this(str, subtitleRegionElement, subtitleStyleElement, ImmutableList.of());
    }

    public CompositeSubtitleElement(@Nonnull String str, @Nullable SubtitleRegionElement subtitleRegionElement, @Nullable SubtitleStyleElement subtitleStyleElement, @Nonnull ImmutableList<SubtitleTextElement> immutableList) {
        this(str, subtitleRegionElement, subtitleStyleElement, immutableList, SubtitleConfig.getInstance(), false);
    }

    public CompositeSubtitleElement(@Nonnull String str, @Nullable SubtitleRegionElement subtitleRegionElement, @Nullable SubtitleStyleElement subtitleStyleElement, @Nonnull ImmutableList<SubtitleTextElement> immutableList, boolean z) {
        this(str, subtitleRegionElement, subtitleStyleElement, immutableList, SubtitleConfig.getInstance(), z);
    }

    private CompositeSubtitleElement(@Nonnull String str, @Nullable SubtitleRegionElement subtitleRegionElement, @Nullable SubtitleStyleElement subtitleStyleElement, @Nonnull ImmutableList<SubtitleTextElement> immutableList, @Nonnull SubtitleConfig subtitleConfig, boolean z) {
        Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        this.mText = (String) Preconditions.checkNotNull(str, "text");
        this.mRegion = subtitleRegionElement;
        this.mStyle = subtitleStyleElement;
        this.mSubtitleTextElements = (ImmutableList) Preconditions.checkNotNull(immutableList, "subtitleTextElements");
        this.mHorizontalSubtitleExtent = Dimension.from(subtitleConfig.getHorizontalSubtitleExtent());
        this.mVerticalSubtitleExtent = Dimension.from(subtitleConfig.getVerticalSubtitleExtent());
        this.mSubtitleTopOrigin = Dimension.from(subtitleConfig.getSubtitleTopOrigin());
        this.mSubtitleLeftOrigin = Dimension.from(subtitleConfig.getSubtitleLeftOrigin());
        this.mSubtitleRightOrigin = Dimension.from(subtitleConfig.getSubtitleRightOrigin());
        this.mIsForcedNarrative = z;
    }

    @Nonnull
    public String getText() {
        return this.mText;
    }

    @Nonnull
    public Dimension getExtent() {
        SubtitleRegionElement subtitleRegionElement = this.mRegion;
        Dimension extent = null;
        if (subtitleRegionElement != null) {
            if (subtitleRegionElement.getFormat() != SubtitleFormat.DFXP) {
                extent = this.mRegion.getExtent();
            } else {
                SubtitleStyleElement subtitleStyleElement = this.mStyle;
                if (subtitleStyleElement != null) {
                    extent = subtitleStyleElement.getExtent();
                }
            }
        }
        return (extent == null || extent.equals(Dimension.AUTO)) ? SubtitleAttribute.Defaults.EXTENT : extent;
    }

    public Dimension getExtent(boolean z) {
        Dimension extent = getExtent();
        return (extent.equals(Dimension.AUTO) || extent.equals(SubtitleAttribute.Defaults.EXTENT)) ? z ? this.mVerticalSubtitleExtent : this.mHorizontalSubtitleExtent : extent;
    }

    @Nonnull
    public Dimension getOrigin() {
        SubtitleRegionElement subtitleRegionElement = this.mRegion;
        Dimension origin = null;
        if (subtitleRegionElement != null) {
            if (subtitleRegionElement.getFormat() != SubtitleFormat.DFXP) {
                origin = this.mRegion.getOrigin();
            } else {
                SubtitleStyleElement subtitleStyleElement = this.mStyle;
                if (subtitleStyleElement != null) {
                    origin = subtitleStyleElement.getOrigin();
                }
            }
        }
        return (origin == null || origin.equals(Dimension.AUTO)) ? SubtitleAttribute.Defaults.ORIGIN : origin;
    }

    public Dimension getOrigin(int i) {
        Dimension origin = getOrigin();
        return (origin.equals(Dimension.AUTO) || origin.equals(SubtitleAttribute.Defaults.ORIGIN)) ? getDefaultOrigin(i) : origin;
    }

    @SuppressLint({"RtlHardcoded"})
    private Dimension getDefaultOrigin(int i) {
        if (i == 19) {
            return this.mSubtitleLeftOrigin;
        }
        if (i == 21) {
            return this.mSubtitleRightOrigin;
        }
        if (i == 49) {
            return this.mSubtitleTopOrigin;
        }
        return SubtitleAttribute.Defaults.ORIGIN;
    }

    public int getGravity() {
        return getTextAlignGravity() | getDisplayAlignGravity();
    }

    private int getTextAlignGravity() {
        SubtitleRegionElement subtitleRegionElement = this.mRegion;
        if (subtitleRegionElement == null) {
            return 1;
        }
        if (subtitleRegionElement.getFormat() == SubtitleFormat.TTMLv2) {
            return this.mRegion.getTextAlignGravity();
        }
        SubtitleStyleElement subtitleStyleElement = this.mStyle;
        if (subtitleStyleElement != null) {
            return subtitleStyleElement.getTextAlignGravity();
        }
        return 1;
    }

    private int getDisplayAlignGravity() {
        SubtitleRegionElement subtitleRegionElement = this.mRegion;
        if (subtitleRegionElement == null) {
            return 48;
        }
        if (subtitleRegionElement.getFormat() == SubtitleFormat.TTMLv2) {
            return this.mRegion.getDisplayAlignGravity();
        }
        SubtitleStyleElement subtitleStyleElement = this.mStyle;
        if (subtitleStyleElement != null) {
            return subtitleStyleElement.getDisplayAlignGravity();
        }
        return 48;
    }

    @Nonnull
    public ImmutableList<SubtitleTextElement> getSubtitleTextElements() {
        return this.mSubtitleTextElements;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompositeSubtitleElement)) {
            return false;
        }
        CompositeSubtitleElement compositeSubtitleElement = (CompositeSubtitleElement) obj;
        return Objects.equal(this.mText, compositeSubtitleElement.mText) && Objects.equal(this.mRegion, compositeSubtitleElement.mRegion) && Objects.equal(this.mStyle, compositeSubtitleElement.mStyle) && Objects.equal(this.mSubtitleTextElements, compositeSubtitleElement.mSubtitleTextElements);
    }

    public int hashCode() {
        return Objects.hashCode(this.mText, this.mRegion, this.mStyle, this.mSubtitleTextElements);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("text", this.mText).add("region", this.mRegion).add("style", this.mStyle).toString();
    }
}
