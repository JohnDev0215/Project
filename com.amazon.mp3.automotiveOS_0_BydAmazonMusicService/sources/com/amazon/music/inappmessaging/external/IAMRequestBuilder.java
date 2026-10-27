package com.amazon.music.inappmessaging.external;

/* JADX INFO: loaded from: classes4.dex */
public final class IAMRequestBuilder {
    private String campaignId;
    private String category;
    private boolean throttle;
    private String trigger;

    public IAMRequestBuilder shouldThrottle(final boolean throttle) {
        this.throttle = throttle;
        return this;
    }

    public IAMRequestBuilder withTrigger(final String trigger) {
        this.trigger = trigger;
        return this;
    }

    public IAMRequestBuilder withCampaignId(final String campaignId) {
        this.campaignId = campaignId;
        return this;
    }

    public IAMRequestBuilder withCategory(final String category) {
        this.category = category;
        return this;
    }

    public IAMRequest build() {
        return new IAMRequest(this.throttle, this.trigger, this.campaignId, this.category);
    }
}
