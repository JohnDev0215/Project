package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSSubscriptionManager {
    private MSOSScheduleCache cache = new MSOSScheduleCache();
    private MSOSConfiguration msosConfig;
    private MSOSResponseCache msosResponseCache;
    private MusicSubscriptionOfferService musicSubscriptionOfferService;
    private MSOSUpdateService updateService;

    public MSOSSubscriptionManager(MSOSConfiguration mSOSConfiguration) {
        this.msosConfig = mSOSConfiguration;
        this.musicSubscriptionOfferService = new MusicSubscriptionOfferService(mSOSConfiguration);
        MSOSResponseCache mSOSResponseCache = new MSOSResponseCache();
        this.msosResponseCache = mSOSResponseCache;
        this.updateService = new MSOSUpdateService(this.musicSubscriptionOfferService, this.cache, mSOSResponseCache);
    }

    public void update() throws MalformedMSOSResponseException {
        this.updateService.updateOffers();
    }

    public RetrieveEligibleSubscriptionOffersResponse getMsosResponseCache() {
        return this.msosResponseCache.getCachedMsosResponse();
    }
}
