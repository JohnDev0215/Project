package com.amazon.music.inappmessaging.external.events;

import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.MTSEvent;
import com.amazon.music.metrics.mts.RealTimeMTSEvent;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes4.dex */
public class IAMEventSender {
    private static final String FIRST_TIME_USER = "firstTimeUser";
    public static final String IAM_EVENT_NAME = "inAppMessaging";
    public static final String IAM_LATENCY_EVENT_NAME = "inAppMessagingLatency";

    private IAMEventSender() {
    }

    public static class Builder {
        private ActionType actionType;
        private String clickId;
        private String description;
        private final EventType eventType;
        private String iamRef;
        private Boolean isFTU;
        private Float latency;
        private PageType pageType;

        private Builder(final EventType eventType) {
            this.eventType = eventType;
        }

        public Builder withIamRef(final String iamRef) {
            this.iamRef = iamRef;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withFTU(final Boolean isFTU) {
            this.isFTU = isFTU;
            return this;
        }

        public Builder withLatency(final Float latency) {
            this.latency = latency;
            return this;
        }

        public Builder withClickId(final String clickId) {
            this.clickId = clickId;
            return this;
        }

        public Builder withPageType(final PageType pageType) {
            this.pageType = pageType;
            return this;
        }

        public Builder withActionType(final ActionType actionType) {
            this.actionType = actionType;
            return this;
        }

        public MTSEvent send() {
            MTSEvent mTSEventCreateUiPageViewEvent;
            EventType eventType = this.eventType;
            if (eventType == EventType.CAMPAIGN_DISPLAYED) {
                Boolean bool = this.isFTU;
                if (bool != null && bool.booleanValue()) {
                    mTSEventCreateUiPageViewEvent = createUiPageViewEventWithPageSubType(IAMEventSender.FIRST_TIME_USER);
                } else {
                    mTSEventCreateUiPageViewEvent = createUiPageViewEvent();
                }
                MetricsHolder.getManager().handleEvent(new RealTimeMTSEvent(mTSEventCreateUiPageViewEvent));
                MetricsHolder.getManager().handleEvent(createFlexEvent());
                return mTSEventCreateUiPageViewEvent;
            }
            if (eventType == EventType.LATENCY_API || eventType == EventType.LATENCY_RENDER || eventType == EventType.LATENCY_TOTAL) {
                MTSEvent mTSEventCreateLatencyFlexEvent = createLatencyFlexEvent();
                MetricsHolder.getManager().handleEvent(mTSEventCreateLatencyFlexEvent);
                return mTSEventCreateLatencyFlexEvent;
            }
            if (eventType == EventType.BEHAVIORAL_ADS_DISPLAYED) {
                MTSEvent mTSEventCreateUiPageViewEvent2 = createUiPageViewEvent();
                MetricsHolder.getManager().handleEvent(mTSEventCreateUiPageViewEvent2);
                return mTSEventCreateUiPageViewEvent2;
            }
            if (eventType == EventType.BEHAVIORAL_ADS_CLICK) {
                MTSEvent mTSEventCreateUIClickEvent = createUIClickEvent();
                MetricsHolder.getManager().handleEvent(mTSEventCreateUIClickEvent);
                return mTSEventCreateUIClickEvent;
            }
            MTSEvent mTSEventCreateFlexEvent = createFlexEvent();
            MetricsHolder.getManager().handleEvent(mTSEventCreateFlexEvent);
            return mTSEventCreateFlexEvent;
        }

        private MTSEvent createUiPageViewEvent() {
            return UiPageViewEvent.builder(IAMEventSender.IAM_EVENT_NAME).withExternalReference(this.iamRef).build();
        }

        private MTSEvent createUiPageViewEventWithPageSubType(final String pageSubType) {
            return UiPageViewEvent.builder(IAMEventSender.IAM_EVENT_NAME).withExternalReference(this.iamRef).withPageSubType(pageSubType).build();
        }

        private MTSEvent createLatencyFlexEvent() {
            return FlexEvent.builder(IAMEventSender.IAM_LATENCY_EVENT_NAME).withHcFlexStr1(this.eventType.getEventName()).withHcFlexStr2(this.iamRef).withFlexNum1(this.latency).build();
        }

        private MTSEvent createFlexEvent() {
            return FlexEvent.builder(IAMEventSender.IAM_EVENT_NAME).withHcFlexStr1(this.eventType.getEventName()).withHcFlexStr2(this.iamRef).withHcFlexStr3(this.description).withHcFlexStr4(this.clickId).withFlexNum1(this.latency).build();
        }

        private MTSEvent createUIClickEvent() {
            return UiClickEvent.builder(this.actionType).withPageType(this.pageType).withRefMarker(this.iamRef).build();
        }
    }

    public static Builder withType(final EventType eventType) {
        return new Builder(eventType);
    }
}
