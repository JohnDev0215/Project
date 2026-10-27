package com.amazon.mp3.dmsfcore.providers;

import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.subscription.UserSubscription;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFUserSubscriptionProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFUserSubscriptionProvider;", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "()V", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "getUserSubscription", "()Lcom/amazon/music/subscription/UserSubscription;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFUserSubscriptionProvider implements UserSubscriptionProvider {
    @Override // com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider
    public UserSubscription getUserSubscription() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        return userSubscription;
    }
}
