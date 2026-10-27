package com.amazon.mp3.curate.provider;

import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.subscription.UserSubscription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CurateSubscriptionProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateSubscriptionProvider;", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "()V", "getContentSubscriptionMode", "", "getUserSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateSubscriptionProvider implements SubscriptionProvider {
    @Override // com.amazon.music.curate.provider.SubscriptionProvider
    public String getContentSubscriptionMode() {
        return UserSubscriptionUtil.getSubscription().requests().requestedContentSubscriptionModeForMuPS();
    }

    @Override // com.amazon.music.curate.provider.SubscriptionProvider
    public UserSubscription getUserSubscription() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return userSubscription;
    }
}
