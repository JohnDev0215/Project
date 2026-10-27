package com.amazon.music.nautilus;

import com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersResponse;

/* JADX INFO: loaded from: classes4.dex */
public class MSOSUpdateService {
    private MSOSScheduleCache cache;
    private MSOSResponseCache mMSOSResponseCache;
    private MusicSubscriptionOfferService musicSubscriptionOfferService;

    public MSOSUpdateService(MusicSubscriptionOfferService musicSubscriptionOfferService, MSOSScheduleCache mSOSScheduleCache, MSOSResponseCache mSOSResponseCache) {
        this.musicSubscriptionOfferService = musicSubscriptionOfferService;
        this.cache = mSOSScheduleCache;
        this.mMSOSResponseCache = mSOSResponseCache;
    }

    public void updateOffers() throws MalformedMSOSResponseException {
        RetrieveEligibleSubscriptionOffersResponse subscriptions = this.musicSubscriptionOfferService.getSubscriptions();
        this.cache.setCachedSchedule(new MSOSScheduleBuilder().buildSchedule(subscriptions));
        this.mMSOSResponseCache.setCachedMsosResponse(subscriptions);
    }
}
