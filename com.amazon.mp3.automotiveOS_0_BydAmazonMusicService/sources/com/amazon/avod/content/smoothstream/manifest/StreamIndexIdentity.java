package com.amazon.avod.content.smoothstream.manifest;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StreamIndexIdentity {
    private final String mFourCC;
    private final String mLanguage;
    private final StreamType mStreamType;

    public StreamIndexIdentity(@Nonnull StreamType streamType, @Nullable String str, @Nonnull String str2) {
        this.mStreamType = (StreamType) Preconditions.checkNotNull(streamType, "streamType");
        this.mLanguage = str;
        this.mFourCC = (String) Preconditions.checkNotNull(str2, "fourCC");
    }

    @Nonnull
    public StreamType getStreamType() {
        return this.mStreamType;
    }

    @Nullable
    public String getLanguage() {
        return this.mLanguage;
    }

    @Nonnull
    public String getFourCC() {
        return this.mFourCC;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StreamIndexIdentity streamIndexIdentity = (StreamIndexIdentity) obj;
        return this.mStreamType == streamIndexIdentity.mStreamType && Objects.equal(this.mLanguage, streamIndexIdentity.mLanguage) && Objects.equal(this.mFourCC, streamIndexIdentity.mFourCC);
    }

    public int hashCode() {
        return Objects.hashCode(this.mStreamType, this.mLanguage, this.mFourCC);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mStreamType", this.mStreamType).add("mLanguage", this.mLanguage).add("mFourCC", this.mFourCC).toString();
    }
}
