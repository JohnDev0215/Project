package com.amazon.music.nautilus;

import java.util.EnumSet;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class UpdateServiceRunnable implements Runnable {
    private final MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener;
    private final SubscriptionCacheSaver subscriptionCacheSaver;
    private final SubscriptionManagementService subscriptionManagementService;

    public UpdateServiceRunnable(SubscriptionCacheSaver subscriptionCacheSaver, SubscriptionManagementService subscriptionManagementService, MusicSubscriptionStateChangeListener musicSubscriptionStateChangeListener) {
        this.subscriptionManagementService = (SubscriptionManagementService) Validate.notNull(subscriptionManagementService);
        this.subscriptionCacheSaver = (SubscriptionCacheSaver) Validate.notNull(subscriptionCacheSaver);
        this.musicSubscriptionStateChangeListener = (MusicSubscriptionStateChangeListener) Validate.notNull(musicSubscriptionStateChangeListener);
    }

    @Override // java.lang.Runnable
    public void run() {
        EnumSet enumSetNoneOf = EnumSet.noneOf(MusicSubscriptionStateField.class);
        enumSetNoneOf.addAll(this.subscriptionCacheSaver.saveSubscriptionOffers(this.subscriptionManagementService.getSubscriptionOffersFromService()));
        if (Thread.interrupted()) {
            return;
        }
        enumSetNoneOf.addAll(this.subscriptionCacheSaver.saveSubscription(this.subscriptionManagementService.getSubscriptionFromService()));
        if (Thread.interrupted() || enumSetNoneOf.isEmpty()) {
            return;
        }
        this.musicSubscriptionStateChangeListener.onMusicSubscriptionStateChange(enumSetNoneOf);
    }
}
