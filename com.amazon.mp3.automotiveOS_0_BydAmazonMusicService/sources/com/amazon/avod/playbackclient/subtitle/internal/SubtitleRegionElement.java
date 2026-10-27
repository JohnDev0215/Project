package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.subtitle.SubtitleDisplayAlignment;
import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.core.subtitle.SubtitleRegionWritingMode;
import com.amazon.avod.core.subtitle.SubtitleTextAlignmentType;
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
public abstract class SubtitleRegionElement {
    public abstract int getDisplayAlignGravity();

    @Nullable
    public abstract Dimension getExtent();

    @Nonnull
    public abstract SubtitleFormat getFormat();

    @Nonnull
    public abstract String getId();

    @Nullable
    public abstract Dimension getOrigin();

    @Nullable
    public abstract String getPosition();

    @Nullable
    public abstract String getStyleId();

    public abstract int getTextAlignGravity();

    @Nullable
    public abstract SubtitleRegionWritingMode getWriteMode();

    public abstract boolean isVertical();

    static class RegionElementV1 extends SubtitleRegionElement {
        private final String mId;
        private final String mStyleId;

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public int getDisplayAlignGravity() {
            return 0;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public Dimension getExtent() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public Dimension getOrigin() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public String getPosition() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public int getTextAlignGravity() {
            return 0;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public SubtitleRegionWritingMode getWriteMode() {
            return null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public boolean isVertical() {
            return false;
        }

        /* synthetic */ RegionElementV1(String str, String str2, AnonymousClass1 anonymousClass1) {
            this(str, str2);
        }

        private RegionElementV1(@Nonnull String str, @Nonnull String str2) {
            this.mId = (String) Preconditions.checkNotNull(str, "id");
            this.mStyleId = (String) Preconditions.checkNotNull(str2, "styleId");
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nonnull
        public SubtitleFormat getFormat() {
            return SubtitleFormat.DFXP;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("id", this.mId).add("styleId", this.mStyleId).toString();
        }

        @Nonnull
        static Builder newBuilder() {
            return new Builder();
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nonnull
        public String getId() {
            return this.mId;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nonnull
        public String getStyleId() {
            return this.mStyleId;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RegionElementV1)) {
                return false;
            }
            RegionElementV1 regionElementV1 = (RegionElementV1) obj;
            return Objects.equal(this.mId, regionElementV1.mId) && Objects.equal(this.mStyleId, regionElementV1.mStyleId);
        }

        public int hashCode() {
            return Objects.hashCode(this.mId, this.mStyleId);
        }

        static class Builder {
            private String mId;
            private String mStyleId;

            Builder() {
            }

            Builder setId(@Nonnull String str) {
                this.mId = (String) Preconditions.checkNotNull(str, "id");
                return this;
            }

            Builder setStyleId(@Nonnull String str) {
                this.mStyleId = (String) Preconditions.checkNotNull(str, "styleId");
                return this;
            }

            SubtitleRegionElement build() {
                Preconditions.checkNotNull(this.mId, "mId");
                Preconditions.checkNotNull(this.mStyleId, "mStyleId");
                return new RegionElementV1(this.mId, this.mStyleId, null);
            }
        }
    }

    static class RegionElementV2 extends SubtitleRegionElement {
        private final int mDisplayAlignGravity;
        private final SubtitleDisplayAlignment mDisplayAlignment;
        private final Dimension mExtent;
        private final String mId;
        private final Dimension mOrigin;
        private final Overflow mOverflow;
        private final String mPosition;
        private final int mTextAlignGravity;
        private final SubtitleTextAlignmentType mTextAlignment;
        private final SubtitleRegionWritingMode mWriteMode;

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public String getStyleId() {
            return null;
        }

        /* synthetic */ RegionElementV2(String str, Dimension dimension, Dimension dimension2, Overflow overflow, int i, int i2, SubtitleDisplayAlignment subtitleDisplayAlignment, String str2, SubtitleRegionWritingMode subtitleRegionWritingMode, SubtitleTextAlignmentType subtitleTextAlignmentType, AnonymousClass1 anonymousClass1) {
            this(str, dimension, dimension2, overflow, i, i2, subtitleDisplayAlignment, str2, subtitleRegionWritingMode, subtitleTextAlignmentType);
        }

        private RegionElementV2(@Nonnull String str, @Nullable Dimension dimension, @Nullable Dimension dimension2, @Nullable Overflow overflow, int i, int i2, @Nullable SubtitleDisplayAlignment subtitleDisplayAlignment, @Nullable String str2, @Nullable SubtitleRegionWritingMode subtitleRegionWritingMode, @Nullable SubtitleTextAlignmentType subtitleTextAlignmentType) {
            this.mId = (String) Preconditions.checkNotNull(str);
            this.mExtent = dimension;
            this.mOrigin = dimension2;
            this.mOverflow = overflow;
            this.mTextAlignGravity = i;
            this.mDisplayAlignGravity = i2;
            this.mDisplayAlignment = subtitleDisplayAlignment;
            this.mPosition = str2;
            this.mWriteMode = subtitleRegionWritingMode;
            this.mTextAlignment = subtitleTextAlignmentType;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nonnull
        public SubtitleFormat getFormat() {
            return SubtitleFormat.TTMLv2;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("id", this.mId).add("extent", this.mExtent).add("origin", this.mOrigin).add("overflow", this.mOverflow).add("textAlignGravity", this.mTextAlignGravity).add("displayAlignGravity", this.mDisplayAlignGravity).add("textAlignment", this.mTextAlignment).add("position", this.mPosition).add("writeMode", this.mWriteMode).add("textAlignment", this.mTextAlignment).toString();
        }

        @Nonnull
        static Builder newBuilder() {
            return new Builder();
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nonnull
        public String getId() {
            return this.mId;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public Dimension getExtent() {
            return this.mExtent;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public Dimension getOrigin() {
            return this.mOrigin;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public int getTextAlignGravity() {
            return this.mTextAlignGravity;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public int getDisplayAlignGravity() {
            return this.mDisplayAlignGravity;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public SubtitleRegionWritingMode getWriteMode() {
            return this.mWriteMode;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        @Nullable
        public String getPosition() {
            return this.mPosition;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement
        public boolean isVertical() {
            SubtitleRegionWritingMode subtitleRegionWritingMode = this.mWriteMode;
            if (subtitleRegionWritingMode == null) {
                return false;
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[subtitleRegionWritingMode.ordinal()];
            return i == 1 || i == 2 || i == 3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RegionElementV2)) {
                return false;
            }
            RegionElementV2 regionElementV2 = (RegionElementV2) obj;
            return Objects.equal(this.mId, regionElementV2.mId) && Objects.equal(this.mExtent, regionElementV2.mExtent) && Objects.equal(this.mOrigin, regionElementV2.mOrigin) && Objects.equal(this.mOverflow, regionElementV2.mOverflow) && Objects.equal(Integer.valueOf(this.mTextAlignGravity), Integer.valueOf(regionElementV2.mTextAlignGravity)) && Objects.equal(Integer.valueOf(this.mDisplayAlignGravity), Integer.valueOf(regionElementV2.mDisplayAlignGravity)) && Objects.equal(this.mDisplayAlignment, regionElementV2.mDisplayAlignment) && Objects.equal(this.mPosition, regionElementV2.mPosition) && Objects.equal(this.mWriteMode, regionElementV2.mWriteMode) && Objects.equal(this.mTextAlignment, regionElementV2.mTextAlignment);
        }

        public int hashCode() {
            return Objects.hashCode(this.mId, this.mExtent, this.mOrigin, this.mOverflow, Integer.valueOf(this.mTextAlignGravity), Integer.valueOf(this.mDisplayAlignGravity));
        }

        static class Builder {
            private int mDisplayAlignGravity;
            private SubtitleDisplayAlignment mDisplayAlignment;
            private Dimension mExtent;
            private String mId;
            private Dimension mOrigin;
            private Overflow mOverflow;
            private String mPosition;
            private int mTextAlignGravity;
            private SubtitleTextAlignmentType mTextAlignment;
            private SubtitleRegionWritingMode mWriteMode;

            Builder() {
            }

            Builder setId(@Nonnull String str) {
                this.mId = (String) Preconditions.checkNotNull(str);
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

            Builder setDisplayAlignment(String str) {
                this.mDisplayAlignment = SubtitleDisplayAlignment.findMatch(str);
                return this;
            }

            Builder setPosition(String str) {
                this.mPosition = str;
                return this;
            }

            Builder setWriteMode(String str) {
                this.mWriteMode = SubtitleRegionWritingMode.findMatch(str);
                return this;
            }

            Builder setTextAlignment(String str) {
                this.mTextAlignment = SubtitleTextAlignmentType.findMatch(str);
                return this;
            }

            SubtitleRegionElement build() {
                Preconditions.checkNotNull(this.mId, "mId");
                return new RegionElementV2(this.mId, this.mExtent, this.mOrigin, this.mOverflow, this.mTextAlignGravity, this.mDisplayAlignGravity, this.mDisplayAlignment, this.mPosition, this.mWriteMode, this.mTextAlignment, null);
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode;

        static {
            int[] iArr = new int[SubtitleRegionWritingMode.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode = iArr;
            try {
                iArr[SubtitleRegionWritingMode.TB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBLR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBRL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.LRTB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.LR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.RLTB.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.RL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }
}
