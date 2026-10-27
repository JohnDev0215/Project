package com.amazon.music.metrics.mts.event.definition.dynamicmessaging;

import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessagingInteractionEvent extends MTSEvent {
    private DynamicMessagingInteractionEvent(Builder builder) {
        super("dynamicMessagingInteraction", 1L);
        if (builder.errorCode != null) {
            addAttribute("dynamicMessagingErrorCode", builder.errorCode);
        }
        if (builder.errorMessage != null) {
            addAttribute("dynamicMessagingErrorMessage", builder.errorMessage);
        }
        if (builder.trigger != null) {
            addAttribute("dynamicMessagingTrigger", builder.trigger);
        }
        if (builder.apiLatencyMillis != null) {
            addAttribute("dynamicMessagingApiLatencyMillis", builder.apiLatencyMillis);
        }
        if (builder.renderLatencyMillis != null) {
            addAttribute("dynamicMessagingRenderLatencyMillis", builder.renderLatencyMillis);
        }
        if (builder.templateRendered != null) {
            addAttribute("dynamicMessageTemplateRendered", builder.templateRendered);
        }
        addAttribute("dynamicMessagingInteractionType", builder.interactionType);
    }

    public static Builder builder(DynamicMessagingInteractionType dynamicMessagingInteractionType) {
        return new Builder(dynamicMessagingInteractionType);
    }

    public static class Builder {
        private Long apiLatencyMillis;
        private String errorCode;
        private String errorMessage;
        private String interactionType;
        private Long renderLatencyMillis;
        private String templateRendered;
        private String trigger;

        private Builder(DynamicMessagingInteractionType dynamicMessagingInteractionType) {
            this.interactionType = dynamicMessagingInteractionType.getMetricValue();
        }

        public Builder withErrorMessage(String str) {
            this.errorMessage = str;
            return this;
        }

        public Builder withErrorCode(DynamicMessagingErrorCode dynamicMessagingErrorCode) {
            this.errorCode = dynamicMessagingErrorCode.getMetricValue();
            return this;
        }

        public Builder withTrigger(String str) {
            this.trigger = str;
            return this;
        }

        public Builder withApiLatencyMillis(Long l) {
            this.apiLatencyMillis = l;
            return this;
        }

        public Builder withRenderLatencyMillis(Long l) {
            this.renderLatencyMillis = l;
            return this;
        }

        public Builder withTemplateRendered(String str) {
            this.templateRendered = str;
            return this;
        }

        public DynamicMessagingInteractionEvent build() {
            return new DynamicMessagingInteractionEvent(this);
        }
    }
}
