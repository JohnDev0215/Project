package com.amazon.avod.media.playback;

import com.amazon.avod.media.DataRate;
import com.amazon.avod.media.VideoResolution;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class VideoAttributes {
    private final DataRate mBitrate;
    private final VideoResolution mResolution;

    public static Builder newBuilder() {
        return new Builder();
    }

    private VideoAttributes(VideoResolution videoResolution, DataRate dataRate) {
        this.mResolution = videoResolution;
        this.mBitrate = dataRate;
    }

    @Nullable
    public VideoResolution getResolution() {
        return this.mResolution;
    }

    @Nullable
    public DataRate getBitrate() {
        return this.mBitrate;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("resolution", getResolution()).add("bitrate", getBitrate()).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VideoAttributes)) {
            return false;
        }
        VideoAttributes videoAttributes = (VideoAttributes) obj;
        return Objects.equal(getBitrate(), videoAttributes.getBitrate()) && Objects.equal(getResolution(), videoAttributes.getResolution());
    }

    public int hashCode() {
        return Objects.hashCode(getBitrate(), getResolution());
    }

    public static final class Builder {
        private DataRate mBitrate;
        private VideoResolution mResolution;

        private Builder() {
        }

        public Builder setResolution(VideoResolution videoResolution) {
            this.mResolution = videoResolution;
            return this;
        }

        public Builder setBitrate(DataRate dataRate) {
            this.mBitrate = dataRate;
            return this;
        }

        public VideoAttributes build() {
            return new VideoAttributes(this.mResolution, this.mBitrate);
        }
    }
}
