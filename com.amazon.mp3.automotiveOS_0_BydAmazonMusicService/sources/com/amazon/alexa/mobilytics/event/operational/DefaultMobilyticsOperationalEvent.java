package com.amazon.alexa.mobilytics.event.operational;

import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultMobilyticsOperationalEvent extends DefaultMobilyticsEvent<DefaultMobilyticsOperationalEvent> {
    private Long eventNumericValue;
    protected String operationalEventType;

    public DefaultMobilyticsOperationalEvent(String str, String str2, String str3, String str4, String str5) {
        super(str, str3, str4, str5);
        this.eventNumericValue = null;
        this.operationalEventType = str2;
    }

    public DefaultMobilyticsOperationalEvent withEventNumericValue(Long l) {
        this.eventNumericValue = l;
        return this;
    }
}
