package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.playback.event.playback.AdTrackingAuditPingEvent;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusAdIdentifierReporterImpl implements AloysiusAdIdentifierReporter {
    private final MediaEventQueue mMediaEventQueue;

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter, com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter, com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
    }

    public AloysiusAdIdentifierReporterImpl(@Nonnull MediaEventQueue mediaEventQueue) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
    }

    @Override // com.amazon.avod.media.playback.reporting.aloysius.AloysiusAdIdentifierReporter
    public void onAdTrackingAuditPingEvent(@Nonnull AdTrackingAuditPingEvent adTrackingAuditPingEvent) {
        addEventToQueue(adTrackingAuditPingEvent.getConsumptionId(), adTrackingAuditPingEvent.getAdAgencyType(), adTrackingAuditPingEvent.getSessionId(), adTrackingAuditPingEvent.getStreamId());
    }

    private void addEventToQueue(String str, String str2, String str3, String str4) {
        this.mMediaEventQueue.add(createMediaEvent(str, str2, str3, str4));
    }

    private MediaEvent createMediaEvent(String str, String str2, String str3, String str4) {
        return new AdIdentifierMediaEvent.Builder().consumptionId(str).adId(str2, str3, str4).build();
    }

    @VisibleForTesting
    static class AdIdentifierMediaEvent implements MediaEvent {

        @Nonnull
        private final AdId mAdId;

        @Nonnull
        private final String mConsumptionId;

        private AdIdentifierMediaEvent(@Nonnull Builder builder) {
            Preconditions.checkNotNull(builder, "builder");
            this.mConsumptionId = builder.mConsumptionId;
            this.mAdId = builder.mAdId;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.AdIdentifier;
        }

        @Nonnull
        public String getConsumptionId() {
            return this.mConsumptionId;
        }

        @Nonnull
        public AdId getAdId() {
            return this.mAdId;
        }

        @VisibleForTesting
        static class Builder {
            private AdId mAdId;
            private String mConsumptionId;

            Builder() {
            }

            public Builder consumptionId(String str) {
                this.mConsumptionId = str;
                return this;
            }

            public Builder adId(String str, String str2, String str3) {
                this.mAdId = new AdId(str, str2, str3);
                return this;
            }

            public MediaEvent build() {
                return new AdIdentifierMediaEvent(this);
            }
        }
    }

    @VisibleForTesting
    static class AdId {

        @Nonnull
        private final String mSessionId;

        @Nonnull
        private final String mStreamId;

        @Nonnull
        private final String mType;

        private AdId(@Nonnull String str, @Nonnull String str2, @Nonnull String str3) {
            this.mType = (String) Preconditions.checkNotNull(str, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
            this.mSessionId = (String) Preconditions.checkNotNull(str2, Splash.PARAMS_SESSION_ID);
            this.mStreamId = (String) Preconditions.checkNotNull(str3, "streamId");
        }

        @Nonnull
        public String get_type() {
            return this.mType;
        }

        @Nonnull
        public String getSessionId() {
            return this.mSessionId;
        }

        @Nonnull
        public String getStreamId() {
            return this.mStreamId;
        }
    }
}
