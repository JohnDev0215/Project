package com.amplifyframework.hub;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AWSHubPlugin extends HubPlugin<Void> {
    private final Set<Subscription> subscriptions = new HashSet();
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override // com.amplifyframework.core.plugin.Plugin
    public void configure(JSONObject jSONObject, @NonNull Context context) {
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @Nullable
    public Void getEscapeHatch() {
        return null;
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getPluginKey() {
        return "awsHubPlugin";
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getVersion() {
        return "1.37.11";
    }

    @Override // com.amplifyframework.hub.HubCategoryBehavior
    public <T> void publish(@NonNull final HubChannel hubChannel, @NonNull final HubEvent<T> hubEvent) {
        Objects.requireNonNull(hubChannel);
        Objects.requireNonNull(hubEvent);
        this.executorService.execute(new Runnable() { // from class: com.amplifyframework.hub.AWSHubPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$publish$1(hubChannel, hubEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$publish$1(HubChannel hubChannel, final HubEvent hubEvent) {
        synchronized (this.subscriptions) {
            for (final Subscription subscription : this.subscriptions) {
                if (subscription.getHubChannel().equals(hubChannel) && subscription.getHubEventFilter().filter(hubEvent)) {
                    this.executorService.execute(new Runnable() { // from class: com.amplifyframework.hub.AWSHubPlugin$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            AWSHubPlugin.lambda$null$0(subscription, hubEvent);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$0(Subscription subscription, HubEvent hubEvent) {
        subscription.getHubSubscriber().onEvent(hubEvent);
    }

    @Override // com.amplifyframework.hub.HubCategoryBehavior
    @NonNull
    public SubscriptionToken subscribe(@NonNull HubChannel hubChannel, @NonNull HubSubscriber hubSubscriber) {
        return subscribe(hubChannel, HubEventFilters.always(), hubSubscriber);
    }

    @Override // com.amplifyframework.hub.HubCategoryBehavior
    @NonNull
    public SubscriptionToken subscribe(@NonNull HubChannel hubChannel, @NonNull HubEventFilter hubEventFilter, @NonNull HubSubscriber hubSubscriber) {
        Objects.requireNonNull(hubChannel);
        Objects.requireNonNull(hubEventFilter);
        Objects.requireNonNull(hubSubscriber);
        SubscriptionToken subscriptionTokenCreate = SubscriptionToken.create();
        synchronized (this.subscriptions) {
            this.subscriptions.add(new Subscription(subscriptionTokenCreate, hubChannel, hubEventFilter, hubSubscriber));
        }
        return subscriptionTokenCreate;
    }

    @Override // com.amplifyframework.hub.HubCategoryBehavior
    public void unsubscribe(@NonNull SubscriptionToken subscriptionToken) {
        Objects.requireNonNull(subscriptionToken);
        synchronized (this.subscriptions) {
            Iterator<Subscription> it = this.subscriptions.iterator();
            while (it.hasNext()) {
                if (it.next().getSubscriptionToken().equals(subscriptionToken)) {
                    it.remove();
                }
            }
        }
    }

    static final class Subscription {
        private final HubChannel channel;
        private final HubEventFilter hubEventFilter;
        private final HubSubscriber hubSubscriber;
        private final SubscriptionToken subscriptionToken;

        Subscription(@NonNull SubscriptionToken subscriptionToken, @NonNull HubChannel hubChannel, @NonNull HubEventFilter hubEventFilter, @NonNull HubSubscriber hubSubscriber) {
            Objects.requireNonNull(subscriptionToken);
            this.subscriptionToken = subscriptionToken;
            Objects.requireNonNull(hubChannel);
            this.channel = hubChannel;
            Objects.requireNonNull(hubEventFilter);
            this.hubEventFilter = hubEventFilter;
            Objects.requireNonNull(hubSubscriber);
            this.hubSubscriber = hubSubscriber;
        }

        SubscriptionToken getSubscriptionToken() {
            return this.subscriptionToken;
        }

        HubChannel getHubChannel() {
            return this.channel;
        }

        HubEventFilter getHubEventFilter() {
            return this.hubEventFilter;
        }

        HubSubscriber getHubSubscriber() {
            return this.hubSubscriber;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Subscription.class != obj.getClass()) {
                return false;
            }
            Subscription subscription = (Subscription) obj;
            if (ObjectsCompat.equals(this.subscriptionToken, subscription.subscriptionToken) && this.channel == subscription.channel && ObjectsCompat.equals(this.hubEventFilter, subscription.hubEventFilter)) {
                return ObjectsCompat.equals(this.hubSubscriber, subscription.hubSubscriber);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.subscriptionToken.hashCode() * 31) + this.channel.hashCode()) * 31) + this.hubEventFilter.hashCode()) * 31) + this.hubSubscriber.hashCode();
        }

        public String toString() {
            return "Subscription{subscriptionToken=" + this.subscriptionToken + ", channel=" + this.channel + ", hubEventFilter=" + this.hubEventFilter + ", hubSubscriber=" + this.hubSubscriber + '}';
        }
    }
}
