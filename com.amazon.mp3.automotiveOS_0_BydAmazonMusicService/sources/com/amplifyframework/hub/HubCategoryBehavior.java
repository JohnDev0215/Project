package com.amplifyframework.hub;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public interface HubCategoryBehavior {
    <T> void publish(@NonNull HubChannel hubChannel, @NonNull HubEvent<T> hubEvent) throws RuntimeException;

    @NonNull
    SubscriptionToken subscribe(@NonNull HubChannel hubChannel, @NonNull HubEventFilter hubEventFilter, @NonNull HubSubscriber hubSubscriber);

    @NonNull
    SubscriptionToken subscribe(@NonNull HubChannel hubChannel, @NonNull HubSubscriber hubSubscriber);

    void unsubscribe(@NonNull SubscriptionToken subscriptionToken);
}
