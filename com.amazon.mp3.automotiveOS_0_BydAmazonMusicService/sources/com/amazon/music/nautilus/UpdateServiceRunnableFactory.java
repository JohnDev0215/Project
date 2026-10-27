package com.amazon.music.nautilus;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateServiceRunnableFactory {
    private final MusicSubscriptionServiceConfiguration musicSubscriptionServiceConfiguration;
    private final MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener;
    private final SubscriptionCache subscriptionCache;

    public UpdateServiceRunnableFactory(SubscriptionCache subscriptionCache, MusicSubscriptionServiceConfiguration musicSubscriptionServiceConfiguration, MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.subscriptionCache = (SubscriptionCache) Validate.notNull(subscriptionCache);
        this.musicSubscriptionServiceConfiguration = (MusicSubscriptionServiceConfiguration) Validate.notNull(musicSubscriptionServiceConfiguration);
        this.musicSubscriptionStateChangeListener = (MusicSubscriptionStateChangeListener) Validate.notNull(musicSubscriptionStateChangeListener);
    }

    public UpdateServiceRunnable buildUpdateServiceRunnable() {
        return new UpdateServiceRunnable(new SubscriptionCacheSaver(this.subscriptionCache, new MusicSubscriptionStateComparator()), new SubscriptionManagementService(new MusicSubscriptionService(this.musicSubscriptionServiceConfiguration), new SubscriptionManagementServiceResponseParser(), new SubscriptionManagmentServiceRequestBuilder(this.musicSubscriptionServiceConfiguration)), this.musicSubscriptionStateChangeListener);
    }
}
