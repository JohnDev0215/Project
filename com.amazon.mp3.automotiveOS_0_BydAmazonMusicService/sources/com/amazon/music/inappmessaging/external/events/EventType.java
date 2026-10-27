package com.amazon.music.inappmessaging.external.events;

/* JADX INFO: loaded from: classes4.dex */
public enum EventType {
    CAMPAIGN_RECEIVED("campaign.received"),
    CAMPAIGN_DISPLAYED("campaign.displayed"),
    CAMPAIGN_DISMISSED("campaign.dismissed"),
    CAMPAIGN_CLICK("campaign.click"),
    CAMPAIGN_EXPIRED("campaign.expired"),
    CAMPAIGN_INELIGIBLE("campaign.ineligible"),
    ERROR_EXCEPTION("error.exception"),
    ERROR_TIMEOUT("error.timeout"),
    ERROR_MISSING_CREDENTIALS("error.missing_credentials"),
    ERROR_OTHER("error.other"),
    LATENCY_API("latency.api"),
    LATENCY_RENDER("latency.render"),
    LATENCY_TOTAL("latency.total"),
    CLIENT_INITIALIZE("client.initialize"),
    CLIENT_REQUEST("client.request"),
    CLIENT_SUPPRESSED("client.suppressed"),
    BEHAVIORAL_ADS_CLICK("behavioral.ads.campaign.click"),
    BEHAVIORAL_ADS_DISPLAYED("behavioral.ads.displayed");

    private final String eventName;

    EventType(final String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return this.eventName;
    }
}
