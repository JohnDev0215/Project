package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleRubyPosition;
import com.amazon.avod.core.subtitle.SubtitleTextAlignmentType;
import com.amazon.avod.core.subtitle.SubtitleTextCombineType;
import com.amazon.avod.core.subtitle.SubtitleTextStylingType;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Overflow;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public abstract class SubtitleStyleElement {
    public abstract int getDisplayAlignGravity();

    @Nullable
    public abstract Dimension getExtent();

    @Nullable
    public abstract String getFontShear();

    @Nullable
    public abstract String getFontStyle();

    @Nonnull
    public abstract String getId();

    @Nullable
    public abstract Dimension getOrigin();

    @Nonnull
    public abstract SubtitleTextStylingType getRuby();

    @Nonnull
    public abstract SubtitleRubyPosition getRubyPosition();

    @Nullable
    public abstract SubtitleTextAlignmentType getTextAlign();

    public abstract int getTextAlignGravity();

    @Nullable
    public abstract SubtitleTextCombineType getTextCombine();

    static class StyleElementV1 extends SubtitleStyleElement {
        private final String mColor;
        private final int mDisplayAlignGravity;
        private final Dimension mExtent;
        private final String mFontFamily;
        private final String mFontSize;
        private final String mFontStyle;
        private final String mFontWeight;
        private final String mId;
        private final Dimension mOrigin;
        private final Overflow mOverflow;
        private final int mTextAlignGravity;

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public String getFontShear() {
            return null;
        }

        private StyleElementV1(@Nonnull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Dimension dimension, @Nullable Dimension dimension2, @Nullable Overflow overflow, int i, int i2) {
            this.mId = (String) Preconditions.checkNotNull(str);
            this.mFontFamily = str2;
            this.mFontWeight = str3;
            this.mFontStyle = str4;
            this.mFontSize = str5;
            this.mColor = str6;
            this.mExtent = dimension;
            this.mOrigin = dimension2;
            this.mOverflow = overflow;
            this.mTextAlignGravity = i;
            this.mDisplayAlignGravity = i2;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("id", this.mId).add("fontFamily", this.mFontFamily).add("fontWeight", this.mFontWeight).add("fontStyle", this.mFontStyle).add("fontSize", this.mFontSize).add("color", this.mColor).add("extent", this.mExtent).add("origin", this.mOrigin).add("overflow", this.mOverflow).add("textAlignGravity", this.mTextAlignGravity).add("displayAlignGravity", this.mDisplayAlignGravity).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StyleElementV1)) {
                return false;
            }
            StyleElementV1 styleElementV1 = (StyleElementV1) obj;
            return Objects.equal(this.mId, styleElementV1.mId) && Objects.equal(this.mFontFamily, styleElementV1.mFontFamily) && Objects.equal(this.mFontWeight, styleElementV1.mFontWeight) && Objects.equal(this.mFontStyle, styleElementV1.mFontStyle) && Objects.equal(this.mFontSize, styleElementV1.mFontSize) && Objects.equal(this.mColor, styleElementV1.mColor) && Objects.equal(this.mExtent, styleElementV1.mExtent) && Objects.equal(this.mOrigin, styleElementV1.mOrigin) && Objects.equal(this.mOverflow, styleElementV1.mOverflow) && Objects.equal(Integer.valueOf(this.mTextAlignGravity), Integer.valueOf(styleElementV1.mTextAlignGravity)) && Objects.equal(Integer.valueOf(this.mDisplayAlignGravity), Integer.valueOf(styleElementV1.mDisplayAlignGravity));
        }

        public int hashCode() {
            return Objects.hashCode(this.mId, this.mFontFamily, this.mFontWeight, this.mFontStyle, this.mFontSize, this.mColor, this.mExtent, this.mOrigin, this.mOverflow, Integer.valueOf(this.mTextAlignGravity), Integer.valueOf(this.mDisplayAlignGravity));
        }

        @Nonnull
        static Builder newBuilder() {
            return new Builder();
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nonnull
        public String getId() {
            return this.mId;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public String getFontStyle() {
            return this.mFontStyle;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public Dimension getExtent() {
            return this.mExtent;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public Dimension getOrigin() {
            return this.mOrigin;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public int getTextAlignGravity() {
            return this.mTextAlignGravity;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public int getDisplayAlignGravity() {
            return this.mDisplayAlignGravity;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextStylingType getRuby() {
            return SubtitleTextStylingType.UNRECOGNIZED;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleRubyPosition getRubyPosition() {
            return SubtitleRubyPosition.UNRECOGNIZED;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextCombineType getTextCombine() {
            return SubtitleTextCombineType.UNRECOGNIZED;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextAlignmentType getTextAlign() {
            return SubtitleTextAlignmentType.UNRECOGNIZED;
        }

        static class Builder {
            private String mColor;
            private int mDisplayAlignGravity;
            private Dimension mExtent;
            private String mFontFamily;
            private String mFontSize;
            private String mFontStyle;
            private String mFontWeight;
            private String mId;
            private Dimension mOrigin;
            private Overflow mOverflow;
            private int mTextAlignGravity;

            Builder() {
            }

            Builder setId(@Nonnull String str) {
                this.mId = (String) Preconditions.checkNotNull(str);
                return this;
            }

            Builder setFontFamily(@Nullable String str) {
                this.mFontFamily = str;
                return this;
            }

            Builder setFontWeight(@Nullable String str) {
                this.mFontWeight = str;
                return this;
            }

            Builder setFontStyle(@Nullable String str) {
                this.mFontStyle = str;
                return this;
            }

            Builder setFontSize(@Nullable String str) {
                this.mFontSize = str;
                return this;
            }

            Builder setColor(@Nullable String str) {
                this.mColor = str;
                return this;
            }

            Builder setExtent(@Nullable Dimension dimension) {
                this.mExtent = dimension;
                return this;
            }

            Builder setOrigin(@Nullable Dimension dimension) {
                this.mOrigin = dimension;
                return this;
            }

            Builder setOverflow(@Nullable Overflow overflow) {
                this.mOverflow = overflow;
                return this;
            }

            Builder setTextAlignGravity(int i) {
                this.mTextAlignGravity = i;
                return this;
            }

            Builder setDisplayAlignGravity(int i) {
                this.mDisplayAlignGravity = i;
                return this;
            }

            StyleElementV1 build() {
                Preconditions.checkNotNull(this.mId, "mId");
                return new StyleElementV1(this.mId, this.mFontFamily, this.mFontWeight, this.mFontStyle, this.mFontSize, this.mColor, this.mExtent, this.mOrigin, this.mOverflow, this.mTextAlignGravity, this.mDisplayAlignGravity);
            }
        }
    }

    static class StyleElementV2 extends SubtitleStyleElement {
        private final String mColor;
        private final String mFontFamily;
        private final String mFontShear;
        private final String mFontSize;
        private final String mFontStyle;
        private final String mFontWeight;
        private final String mId;
        private final SubtitleTextStylingType mRuby;
        private final SubtitleRubyPosition mRubyPosition;
        private final SubtitleTextAlignmentType mTextAlignment;
        private final SubtitleTextCombineType mTextCombine;
        private final String mTextEmphasis;

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public int getDisplayAlignGravity() {
            return 0;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public Dimension getExtent() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public Dimension getOrigin() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public int getTextAlignGravity() {
            return 0;
        }

        private StyleElementV2(@Nonnull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable SubtitleTextCombineType subtitleTextCombineType, @Nullable String str8, @Nullable SubtitleTextStylingType subtitleTextStylingType, @Nullable SubtitleRubyPosition subtitleRubyPosition, @Nullable SubtitleTextAlignmentType subtitleTextAlignmentType) {
            this.mId = (String) Preconditions.checkNotNull(str);
            this.mFontFamily = str2;
            this.mFontWeight = str3;
            this.mFontStyle = str4;
            this.mFontSize = str5;
            this.mColor = str6;
            this.mFontShear = str7;
            this.mTextCombine = subtitleTextCombineType;
            this.mTextEmphasis = str8;
            this.mRuby = subtitleTextStylingType;
            this.mRubyPosition = subtitleRubyPosition;
            this.mTextAlignment = subtitleTextAlignmentType;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("id", this.mId).add("fontFamily", this.mFontFamily).add("fontWeight", this.mFontWeight).add("fontStyle", this.mFontStyle).add("fontSize", this.mFontSize).add("color", this.mColor).add("fontShear", this.mFontShear).add("textCombine", this.mTextCombine).add("textEmphasis", this.mTextEmphasis).add("ruby", this.mRuby).add("rubyPosition", this.mRubyPosition).add("textAlignment", this.mTextAlignment).toString();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StyleElementV2)) {
                return false;
            }
            StyleElementV2 styleElementV2 = (StyleElementV2) obj;
            return Objects.equal(this.mId, styleElementV2.mId) && Objects.equal(this.mFontFamily, styleElementV2.mFontFamily) && Objects.equal(this.mFontWeight, styleElementV2.mFontWeight) && Objects.equal(this.mFontStyle, styleElementV2.mFontStyle) && Objects.equal(this.mFontSize, styleElementV2.mFontSize) && Objects.equal(this.mColor, styleElementV2.mColor) && Objects.equal(this.mFontShear, styleElementV2.mFontShear) && Objects.equal(this.mTextCombine, styleElementV2.mTextCombine) && Objects.equal(this.mTextEmphasis, styleElementV2.mTextEmphasis) && Objects.equal(this.mRuby, styleElementV2.mRuby) && Objects.equal(this.mRubyPosition, styleElementV2.mRubyPosition) && Objects.equal(this.mTextAlignment, styleElementV2.mTextAlignment);
        }

        public int hashCode() {
            return Objects.hashCode(this.mId, this.mFontFamily, this.mFontWeight, this.mFontStyle, this.mFontSize, this.mColor, this.mFontShear, this.mTextCombine, this.mTextEmphasis, this.mRuby, this.mRubyPosition, this.mTextAlignment);
        }

        @Nonnull
        static Builder newBuilder() {
            return new Builder();
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nonnull
        public String getId() {
            return this.mId;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        @Nullable
        public String getFontStyle() {
            return this.mFontStyle;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public String getFontShear() {
            return this.mFontShear;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextStylingType getRuby() {
            return this.mRuby;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleRubyPosition getRubyPosition() {
            return this.mRubyPosition;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextCombineType getTextCombine() {
            return this.mTextCombine;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement
        public SubtitleTextAlignmentType getTextAlign() {
            return this.mTextAlignment;
        }

        static class Builder {
            private String mColor;
            private String mFontFamily;
            private String mFontShear;
            private String mFontSize;
            private String mFontStyle;
            private String mFontWeight;
            private String mId;
            private SubtitleTextStylingType mRuby;
            private SubtitleRubyPosition mRubyPosition;
            private SubtitleTextAlignmentType mTextAlignment;
            private SubtitleTextCombineType mTextCombine;
            private String mTextEmphasis;

            Builder() {
            }

            Builder setId(@Nonnull String str) {
                this.mId = (String) Preconditions.checkNotNull(str);
                return this;
            }

            Builder setFontFamily(@Nullable String str) {
                this.mFontFamily = str;
                return this;
            }

            Builder setFontWeight(@Nullable String str) {
                this.mFontWeight = str;
                return this;
            }

            Builder setFontStyle(@Nullable String str) {
                this.mFontStyle = str;
                return this;
            }

            Builder setFontSize(@Nullable String str) {
                this.mFontSize = str;
                return this;
            }

            Builder setColor(@Nullable String str) {
                this.mColor = str;
                return this;
            }

            Builder setFontShear(@Nullable String str) {
                this.mFontShear = str;
                return this;
            }

            Builder setTextCombine(@Nullable String str) {
                this.mTextCombine = SubtitleTextCombineType.findMatch(str);
                return this;
            }

            Builder setTextEmphasis(@Nullable String str) {
                this.mTextEmphasis = str;
                return this;
            }

            Builder setRuby(@Nullable String str) {
                this.mRuby = SubtitleTextStylingType.findMatch(str);
                return this;
            }

            Builder setRubyPosition(@Nullable String str) {
                this.mRubyPosition = SubtitleRubyPosition.findMatch(str);
                return this;
            }

            Builder setTextAlignment(@Nullable String str) {
                this.mTextAlignment = SubtitleTextAlignmentType.findMatch(str);
                return this;
            }

            StyleElementV2 build() {
                Preconditions.checkNotNull(this.mId, "mId");
                return new StyleElementV2(this.mId, this.mFontFamily, this.mFontWeight, this.mFontStyle, this.mFontSize, this.mColor, this.mFontShear, this.mTextCombine, this.mTextEmphasis, this.mRuby, this.mRubyPosition, this.mTextAlignment);
            }
        }
    }
}
