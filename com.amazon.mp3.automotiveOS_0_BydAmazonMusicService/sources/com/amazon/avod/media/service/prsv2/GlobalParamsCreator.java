package com.amazon.avod.media.service.prsv2;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.prs.ResourceParams;
import com.amazon.avod.prs.ResourceParamsCreator;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class GlobalParamsCreator implements ResourceParamsCreator {
    private final String mDeviceCapabilityFamily;
    private final PlaybackEnvelope mPlaybackEnvelope;

    public GlobalParamsCreator(@Nonnull PlaybackEnvelope playbackEnvelope) {
        this(playbackEnvelope, "AndroidPlayer");
    }

    GlobalParamsCreator(@Nonnull PlaybackEnvelope playbackEnvelope, @Nonnull String str) {
        this.mPlaybackEnvelope = (PlaybackEnvelope) Preconditions.checkNotNull(playbackEnvelope, "playbackEnvelope");
        this.mDeviceCapabilityFamily = (String) Preconditions.checkNotNull(str, "deviceCapabilityFamily");
    }

    @Override // com.amazon.avod.prs.ResourceParamsCreator
    @Nonnull
    public ResourceParams create() throws ContentException {
        String envelope = this.mPlaybackEnvelope.getEnvelope();
        if (envelope == null) {
            throw new ContentException(ContentException.ContentError.MISSING_PLAYBACK_ENVELOPE);
        }
        return new GlobalParams(envelope, this.mDeviceCapabilityFamily);
    }

    static class GlobalParams implements ResourceParams {
        private final String mDeviceCapabilityFamily;
        private final String mPlaybackEnvelope;

        GlobalParams(@Nonnull String str, @Nonnull String str2) {
            this.mPlaybackEnvelope = (String) Preconditions.checkNotNull(str, "playbackEnvelope");
            this.mDeviceCapabilityFamily = (String) Preconditions.checkNotNull(str2, "deviceCapabilityFamily");
        }

        @JsonProperty("playbackEnvelope")
        @Nonnull
        public String getPlaybackEnvelope() {
            return this.mPlaybackEnvelope;
        }

        @JsonProperty("deviceCapabilityFamily")
        @Nonnull
        public String getDeviceCapabilityFamily() {
            return this.mDeviceCapabilityFamily;
        }
    }
}
