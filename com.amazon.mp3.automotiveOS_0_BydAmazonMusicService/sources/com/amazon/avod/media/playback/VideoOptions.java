package com.amazon.avod.media.playback;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class VideoOptions {
    private final String mOfferType;
    private final Map<String, String> mSessionContext;

    private VideoOptions(@Nullable String str, @Nonnull Map<String, String> map) {
        this.mOfferType = str;
        this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
    }

    @Nullable
    public String getOfferType() {
        return this.mOfferType;
    }

    public String toString() {
        return MoreObjects.toStringHelper(getClass()).add("mOfferType", this.mOfferType).add("mSessionContext", this.mSessionContext).toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VideoOptions)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        VideoOptions videoOptions = (VideoOptions) obj;
        return Objects.equal(this.mOfferType, videoOptions.mOfferType) && Objects.equal(this.mSessionContext, videoOptions.mSessionContext);
    }

    public int hashCode() {
        return Objects.hashCode(this.mOfferType, this.mSessionContext);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    public static final class Builder {
        private String mOfferType;
        private Map<String, String> mSessionContext;

        private Builder() {
            this.mOfferType = null;
            this.mSessionContext = Collections.emptyMap();
        }

        @Nonnull
        public Builder setSessionContext(@Nonnull Map<String, String> map) {
            this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
            return this;
        }

        @Nonnull
        public VideoOptions build() {
            return new VideoOptions(this.mOfferType, this.mSessionContext);
        }
    }
}
