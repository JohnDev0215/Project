package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class AudioTrackMetadata {
    private final String mAudioTrackId;
    private final String mDisplayName;
    private final String mIndex;
    private final boolean mIsDefaultTrack;
    private final String mLanguageCode;
    private final String mSubtype;
    private final String mTrackGroupId;

    @JsonCreator
    public AudioTrackMetadata(@JsonProperty("audioTrackId") @Nullable String str, @JsonProperty("trackGroupId") @Nullable String str2, @JsonProperty("displayName") @Nullable String str3, @JsonProperty("languageCode") @Nullable String str4, @JsonProperty("isDefaultAudioTrack") boolean z, @JsonProperty("subtype") @Nullable String str5, @JsonProperty("index") @Nullable String str6) {
        this.mAudioTrackId = Strings.nullToEmpty(str);
        this.mTrackGroupId = Strings.nullToEmpty(str2);
        this.mDisplayName = Strings.nullToEmpty(str3);
        this.mLanguageCode = Strings.nullToEmpty(str4);
        this.mIsDefaultTrack = z;
        this.mSubtype = Strings.nullToEmpty(str5);
        this.mIndex = Strings.nullToEmpty(str6);
    }

    @Nonnull
    public String getAudioTrackId() {
        return this.mAudioTrackId;
    }

    @Nonnull
    public String getTrackGroupId() {
        return this.mTrackGroupId;
    }

    @Nonnull
    public String getDisplayName() {
        return this.mDisplayName;
    }

    @Nonnull
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    public boolean getIsDefaultAudioTrack() {
        return this.mIsDefaultTrack;
    }

    @Nonnull
    public String getSubtype() {
        return this.mSubtype;
    }

    @Nonnull
    public String getIndex() {
        return this.mIndex;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioTrackMetadata)) {
            return false;
        }
        AudioTrackMetadata audioTrackMetadata = (AudioTrackMetadata) obj;
        return Objects.equal(this.mAudioTrackId, audioTrackMetadata.mAudioTrackId) && Objects.equal(this.mTrackGroupId, audioTrackMetadata.mTrackGroupId) && Objects.equal(this.mDisplayName, audioTrackMetadata.mDisplayName) && Objects.equal(this.mLanguageCode, audioTrackMetadata.mLanguageCode) && Objects.equal(Boolean.valueOf(this.mIsDefaultTrack), Boolean.valueOf(audioTrackMetadata.mIsDefaultTrack)) && Objects.equal(this.mSubtype, audioTrackMetadata.mSubtype) && Objects.equal(this.mIndex, audioTrackMetadata.mIndex);
    }

    public int hashCode() {
        return Objects.hashCode(this.mAudioTrackId, this.mTrackGroupId, this.mDisplayName, this.mLanguageCode, Boolean.valueOf(this.mIsDefaultTrack), this.mSubtype, this.mIndex);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("audioTrackId", getAudioTrackId()).add("displayName", getDisplayName()).add("isDefaultTrack", getIsDefaultAudioTrack()).add("trackGroupId", getTrackGroupId()).add("languageCode", getLanguageCode()).add("subtype", getSubtype()).add("index", getIndex()).toString();
    }
}
