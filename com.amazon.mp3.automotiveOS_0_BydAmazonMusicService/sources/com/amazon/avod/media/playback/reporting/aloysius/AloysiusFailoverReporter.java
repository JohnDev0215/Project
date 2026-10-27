package com.amazon.avod.media.playback.reporting.aloysius;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.event.CdnSwitchedEvent;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.eventbus.Subscribe;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusFailoverReporter implements MediaEventReporter {

    @Nullable
    private final ContentManagementEventBus mEventBus;

    @Nonnull
    private final MediaEventQueue mMediaEventQueue;

    public AloysiusFailoverReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nullable ContentManagementEventBus contentManagementEventBus) {
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mEventBus = contentManagementEventBus;
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.registerEventBusHandler(this);
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        ContentManagementEventBus contentManagementEventBus = this.mEventBus;
        if (contentManagementEventBus != null) {
            contentManagementEventBus.unregisterEventBusHandler(this);
        }
    }

    @Subscribe
    public synchronized void handleCdnSwitchedEvent(CdnSwitchedEvent cdnSwitchedEvent) {
        this.mMediaEventQueue.add(createMediaEvent(cdnSwitchedEvent.getFailoverRuleId(), cdnSwitchedEvent.getOldContentUrl().getSessionId(), cdnSwitchedEvent.getNewContentUrl().getSessionId()));
    }

    @Subscribe
    public synchronized void handleLiveStreamingRestartEvent(LiveStreamingRestartEvent liveStreamingRestartEvent) {
        this.mMediaEventQueue.add(createMediaEvent(liveStreamingRestartEvent.getFailoverRuleId(), liveStreamingRestartEvent.getOldUrlSetId(), liveStreamingRestartEvent.getContentUrlSetId()));
    }

    @VisibleForTesting
    MediaEvent createMediaEvent(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        return new FailoverMediaEvent.Builder().ruleId(str).fromConsumptionId(str2).toConsumptionId(str3).build();
    }

    @VisibleForTesting
    static class FailoverMediaEvent implements MediaEvent {
        private final String mFromConsumptionId;
        private final String mRuleId;
        private final String mToConsumptionId;

        private FailoverMediaEvent(@Nonnull Builder builder) {
            this.mRuleId = ((Builder) Preconditions.checkNotNull(builder, "builder")).mRuleId;
            this.mFromConsumptionId = builder.mFromConsumptionId;
            this.mToConsumptionId = builder.mToConsumptionId;
        }

        @Nonnull
        public String getRuleId() {
            return Strings.nullToEmpty(this.mRuleId);
        }

        @Nonnull
        public String getFromConsumptionId() {
            return Strings.nullToEmpty(this.mFromConsumptionId);
        }

        @Nonnull
        public String getToConsumptionId() {
            return Strings.nullToEmpty(this.mToConsumptionId);
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        @Nonnull
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Failover;
        }

        @VisibleForTesting
        static class Builder {
            public String mFromConsumptionId;
            public String mRuleId;
            public String mToConsumptionId;

            Builder() {
            }

            public Builder ruleId(@Nullable String str) {
                this.mRuleId = str;
                return this;
            }

            public Builder fromConsumptionId(@Nullable String str) {
                this.mFromConsumptionId = str;
                return this;
            }

            public Builder toConsumptionId(@Nullable String str) {
                this.mToConsumptionId = str;
                return this;
            }

            @VisibleForTesting
            MediaEvent build() {
                return new FailoverMediaEvent(this);
            }
        }
    }
}
