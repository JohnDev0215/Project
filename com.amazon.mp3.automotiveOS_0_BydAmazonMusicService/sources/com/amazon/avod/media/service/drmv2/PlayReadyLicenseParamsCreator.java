package com.amazon.avod.media.service.drmv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyLicenseParamsCreator implements ResourceParamsCreator {
    private final boolean mIsDash;
    private final String mKeyId;
    private final String mLicenseChallenge;
    private final String mPlaybackEnvelope;

    public PlayReadyLicenseParamsCreator(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, boolean z) {
        this.mLicenseChallenge = (String) Preconditions.checkNotNull(str, "licenseChallenge");
        this.mPlaybackEnvelope = (String) Preconditions.checkNotNull(str2, "playbackEnvelope");
        this.mKeyId = (String) Preconditions.checkNotNull(str3, "keyId");
        this.mIsDash = z;
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    public ResourceParams create() throws ContentException {
        return new PlayReadyParams(this.mLicenseChallenge, this.mPlaybackEnvelope, this.mKeyId, this.mIsDash ? "MPEG_DASH" : "SMOOTH_STREAMING");
    }

    static class PlayReadyParams implements ResourceParams {
        private final String mKeyId;
        private final String mLicenseChallenge;
        private final String mPackagingFormat;
        private final String mPlaybackEnvelope;

        PlayReadyParams(@Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4) {
            this.mLicenseChallenge = (String) Preconditions.checkNotNull(str, "licenseChallenge");
            this.mPlaybackEnvelope = (String) Preconditions.checkNotNull(str2, "playbackEnvelope");
            this.mKeyId = (String) Preconditions.checkNotNull(str3, "keyId");
            this.mPackagingFormat = (String) Preconditions.checkNotNull(str4, "packagingFormat");
        }

        @JsonProperty("licenseChallenge")
        @Nonnull
        public String getLicenseChallenge() {
            return this.mLicenseChallenge;
        }

        @JsonProperty("playbackEnvelope")
        @Nonnull
        public String getPlaybackEnvelope() {
            return this.mPlaybackEnvelope;
        }

        @JsonProperty("keyId")
        @Nonnull
        public String getKeyId() {
            return this.mKeyId;
        }

        @JsonProperty("packagingFormat")
        @Nonnull
        public String getPackagingFormat() {
            return this.mPackagingFormat;
        }
    }
}
