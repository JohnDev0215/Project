package com.amazon.video.sdk.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackEnvelopeData implements PlaybackEnvelope {

    @JsonProperty("correlationId")
    private String mCorrelationId;

    @JsonProperty("envelope")
    private String mEnvelope;

    @JsonProperty("expiryTimeMs")
    private Long mExpiryTimeMs;

    @JsonProperty("titleId")
    private String mTitleId;

    @Override // com.amazon.video.sdk.player.PlaybackEnvelope
    @Nonnull
    public String getEnvelope() {
        return this.mEnvelope;
    }

    @Override // com.amazon.video.sdk.player.PlaybackEnvelope
    @Nonnull
    public String getCorrelationId() {
        return this.mCorrelationId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaybackEnvelopeData)) {
            return false;
        }
        PlaybackEnvelopeData playbackEnvelopeData = (PlaybackEnvelopeData) obj;
        return Objects.equal(this.mTitleId, playbackEnvelopeData.mTitleId) && Objects.equal(this.mExpiryTimeMs, playbackEnvelopeData.mExpiryTimeMs) && Objects.equal(this.mCorrelationId, playbackEnvelopeData.mCorrelationId) && Objects.equal(this.mEnvelope, playbackEnvelopeData.mEnvelope);
    }

    public int hashCode() {
        return Objects.hashCode(this.mTitleId, this.mEnvelope, this.mExpiryTimeMs, this.mCorrelationId);
    }
}
