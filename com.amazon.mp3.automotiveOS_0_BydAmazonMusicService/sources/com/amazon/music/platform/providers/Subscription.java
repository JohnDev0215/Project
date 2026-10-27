package com.amazon.music.platform.providers;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import kotlin.Metadata;

/* JADX INFO: compiled from: SubscriptionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0004H&R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/providers/Subscription;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/providers/SubscriptionTracking;", "tracking", "Lcom/amazon/music/platform/providers/SubscriptionRequest;", "requests", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "getType", "()Lcom/amazon/music/platform/data/subscription/SubscriptionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface Subscription extends InterfaceProvider {
    SubscriptionType getType();

    SubscriptionRequest requests();

    SubscriptionTracking tracking();
}
