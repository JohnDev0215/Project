package com.amazon.music.inappmessaging.external.events;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.definition.dynamicmessaging.DynamicMessagingInteractionEvent;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageEventSender {
    private DynamicMessageEventSender() {
    }

    public static class Builder {
        private Long apiLatencyMillis;
        private DynamicMessagingErrorCode errorCode;
        private String errorMessage;
        private String event;
        private DynamicMessagingInteractionType interactionType;
        private Long renderLatencyMillis;
        private String template;

        private Builder(final DynamicMessagingInteractionType interactionType) {
            this.interactionType = interactionType;
        }

        public Builder withInteractionType(final DynamicMessagingInteractionType interactionType) {
            this.interactionType = interactionType;
            return this;
        }

        public Builder withErrorCode(final DynamicMessagingErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder withErrorMessage(final String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder withRenderLatencyMillis(final Long renderLatencyMillis) {
            this.renderLatencyMillis = renderLatencyMillis;
            return this;
        }

        public Builder withApiLatencyMillis(final Long apiLatencyMillis) {
            this.apiLatencyMillis = apiLatencyMillis;
            return this;
        }

        public Builder withTemplate(final String template) {
            this.template = template;
            return this;
        }

        public Builder withTrigger(final String trigger) {
            this.event = trigger;
            return this;
        }

        public Builder withEvent(final String event) {
            this.event = event;
            return this;
        }

        public MTSEvent send() {
            MTSEvent mTSEventCreateBaseEvent;
            DynamicMessagingInteractionType dynamicMessagingInteractionType = this.interactionType;
            if (dynamicMessagingInteractionType == DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR) {
                mTSEventCreateBaseEvent = createErrorEvent();
            } else if (dynamicMessagingInteractionType == DynamicMessagingInteractionType.DYNAMIC_MESSAGING_API_LATENCY) {
                mTSEventCreateBaseEvent = createApiLatencyEvent();
            } else if (dynamicMessagingInteractionType == DynamicMessagingInteractionType.DYNAMIC_MESSAGING_RENDER_LATENCY) {
                mTSEventCreateBaseEvent = createRenderLatencyEvent();
            } else {
                mTSEventCreateBaseEvent = createBaseEvent();
            }
            MetricsHolder.getManager().handleEvent(mTSEventCreateBaseEvent);
            return mTSEventCreateBaseEvent;
        }

        private MTSEvent createBaseEvent() {
            return DynamicMessagingInteractionEvent.builder(this.interactionType).build();
        }

        private MTSEvent createErrorEvent() {
            return DynamicMessagingInteractionEvent.builder(this.interactionType).withErrorCode(this.errorCode).withErrorMessage(this.errorMessage).withTrigger(this.event).build();
        }

        private MTSEvent createApiLatencyEvent() {
            return DynamicMessagingInteractionEvent.builder(this.interactionType).withApiLatencyMillis(this.apiLatencyMillis).build();
        }

        private MTSEvent createRenderLatencyEvent() {
            return DynamicMessagingInteractionEvent.builder(this.interactionType).withRenderLatencyMillis(this.renderLatencyMillis).withTemplateRendered(this.template).build();
        }
    }

    public static Builder withInteractionType(final DynamicMessagingInteractionType interactionType) {
        return new Builder(interactionType);
    }
}
