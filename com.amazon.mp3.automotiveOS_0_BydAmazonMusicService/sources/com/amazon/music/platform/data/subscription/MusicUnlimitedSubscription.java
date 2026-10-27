package com.amazon.music.platform.data.subscription;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.providers.Subscription;
import com.amazon.music.platform.providers.SubscriptionRequest;
import com.amazon.music.platform.providers.SubscriptionTracking;
import kotlin.Metadata;

/* JADX INFO: compiled from: MusicUnlimitedSubscription.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/amazon/music/platform/data/subscription/MusicUnlimitedSubscription;", "Lcom/amazon/music/platform/providers/Subscription;", "Lcom/amazon/music/platform/providers/SubscriptionTracking;", "tracking", "Lcom/amazon/music/platform/providers/SubscriptionRequest;", "requests", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "getType", "()Lcom/amazon/music/platform/data/subscription/SubscriptionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class MusicUnlimitedSubscription implements Subscription {
    @Override // com.amazon.music.platform.providers.Subscription
    public SubscriptionType getType() {
        return SubscriptionType.MUSIC_UNLIMITED;
    }

    @Override // com.amazon.music.platform.providers.Subscription
    public SubscriptionTracking tracking() {
        return new MusicUnlimitedSubscriptionTracking();
    }

    @Override // com.amazon.music.platform.providers.Subscription
    public SubscriptionRequest requests() {
        return new MusicUnlimitedSubscriptionRequests();
    }
}
