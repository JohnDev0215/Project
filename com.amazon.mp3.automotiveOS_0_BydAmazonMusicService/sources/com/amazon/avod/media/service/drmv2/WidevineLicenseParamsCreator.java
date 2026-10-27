package com.amazon.avod.media.service.drmv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineLicenseParamsCreator implements ResourceParamsCreator {
    private final String mLicenseChallenge;
    private final String mPlaybackEnvelope;

    public WidevineLicenseParamsCreator(@Nonnull String str, @Nonnull String str2) {
        this.mLicenseChallenge = (String) Preconditions.checkNotNull(str, "licenseChallenge");
        this.mPlaybackEnvelope = (String) Preconditions.checkNotNull(str2, "playbackEnvelope");
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    public ResourceParams create() throws ContentException {
        return new WidevineLicenseParams(this.mLicenseChallenge, this.mPlaybackEnvelope);
    }

    static class WidevineLicenseParams implements ResourceParams {
        private final String mLicenseChallenge;
        private final String mPlaybackEnvelope;

        WidevineLicenseParams(@Nonnull String str, @Nonnull String str2) {
            this.mLicenseChallenge = (String) Preconditions.checkNotNull(str, "licenseChallenge");
            this.mPlaybackEnvelope = (String) Preconditions.checkNotNull(str2, "playbackEnvelope");
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
    }
}
