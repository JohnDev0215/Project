package com.amazon.music.nautilus;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
class SubscriptionCache {
    private static AtomicReference<SubscriptionOffers> cachedSubscriptionOffers = new AtomicReference<>();
    private static AtomicReference<Subscription> cachedSubscription = new AtomicReference<>();

    SubscriptionCache() {
    }

    public SubscriptionOffers getCachedSubscriptionOffers() {
        return cachedSubscriptionOffers.get();
    }

    public void setCachedSubscriptionOffers(SubscriptionOffers subscriptionOffers) {
        cachedSubscriptionOffers.set(subscriptionOffers);
    }

    public Subscription getCachedSubscription() {
        return cachedSubscription.get();
    }

    public void setCachedSubscription(Subscription subscription) {
        cachedSubscription.set(subscription);
    }
}
