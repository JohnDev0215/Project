package com.amazon.music.events;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ContentViewedAppEvent extends AppEvent {
    private final List<Map<String, String>> contentInfo;
    private final boolean emittedOnView;

    private ContentViewedAppEvent(Builder builder) {
        setEventTime(builder.eventTime);
        this.contentInfo = builder.contentInfo;
        this.emittedOnView = builder.emittedOnView;
    }

    public static boolean isDuplicate(ContentViewedAppEvent contentViewedAppEvent, ContentViewedAppEvent contentViewedAppEvent2) {
        if (contentViewedAppEvent == null || contentViewedAppEvent2 == null) {
            return false;
        }
        List<Map<String, String>> list = contentViewedAppEvent.contentInfo;
        boolean z = list == null && contentViewedAppEvent2.contentInfo == null;
        boolean z2 = (list == null || contentViewedAppEvent2.contentInfo == null) ? false : true;
        if (contentViewedAppEvent.emittedOnView == contentViewedAppEvent2.emittedOnView) {
            return (z2 && list.equals(contentViewedAppEvent2.contentInfo)) || z;
        }
        return false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Map<String, String>> contentInfo;
        private long eventTime = System.currentTimeMillis();
        private boolean emittedOnView = false;

        public Builder withContentInfo(List<Map<String, String>> list) {
            this.contentInfo = list;
            return this;
        }

        public ContentViewedAppEvent build() {
            return new ContentViewedAppEvent(this);
        }

        public void publish() {
            AppEvent.appEventPublisher.publish(build());
        }
    }

    public List<Map<String, String>> getContentInfo() {
        return this.contentInfo;
    }

    public boolean getEmittedOnView() {
        return this.emittedOnView;
    }
}
