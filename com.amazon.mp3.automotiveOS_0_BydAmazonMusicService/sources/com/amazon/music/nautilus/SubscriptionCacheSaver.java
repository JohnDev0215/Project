package com.amazon.music.nautilus;

import java.util.Set;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class SubscriptionCacheSaver {
    private final MusicSubscriptionStateComparator musicSubscriptionStateComparator;
    private final SubscriptionCache subscriptionCache;

    public SubscriptionCacheSaver(SubscriptionCache subscriptionCache, MusicSubscriptionStateComparator musicSubscriptionStateComparator) {
        this.subscriptionCache = (SubscriptionCache) Validate.notNull(subscriptionCache);
        this.musicSubscriptionStateComparator = (MusicSubscriptionStateComparator) Validate.notNull(musicSubscriptionStateComparator);
    }

    public Set<MusicSubscriptionStateField> saveSubscriptionOffers(SubscriptionOffers subscriptionOffers) {
        SubscriptionOffers cachedSubscriptionOffers = this.subscriptionCache.getCachedSubscriptionOffers();
        this.subscriptionCache.setCachedSubscriptionOffers(subscriptionOffers);
        return this.musicSubscriptionStateComparator.getDifferences(subscriptionOffers, cachedSubscriptionOffers);
    }

    public Set<MusicSubscriptionStateField> saveSubscription(Subscription subscription) {
        Subscription cachedSubscription = this.subscriptionCache.getCachedSubscription();
        this.subscriptionCache.setCachedSubscription(subscription);
        return this.musicSubscriptionStateComparator.getDifferences(subscription, cachedSubscription);
    }
}
