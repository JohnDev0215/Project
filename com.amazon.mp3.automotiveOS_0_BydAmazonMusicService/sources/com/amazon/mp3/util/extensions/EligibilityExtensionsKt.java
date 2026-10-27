package com.amazon.mp3.util.extensions;

import com.amazon.music.platform.data.subscription.SubscriptionType;
import java.util.HashSet;
import kotlin.Metadata;

/* JADX INFO: compiled from: EligibilityExtensions.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a&\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000¨\u0006\u0007"}, d2 = {"", "isSonicRushOnDemand", "isFreeOnDemand", "Ljava/util/HashSet;", "Lcom/amazon/music/platform/data/subscription/SubscriptionType;", "Lkotlin/collections/HashSet;", "onDemandTiers", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class EligibilityExtensionsKt {
    public static final HashSet<SubscriptionType> onDemandTiers(boolean z, boolean z2) {
        HashSet<SubscriptionType> hashSet = new HashSet<>();
        if (z) {
            hashSet.add(SubscriptionType.SONIC_RUSH);
        }
        if (z2) {
            hashSet.add(SubscriptionType.FREE);
        }
        return hashSet;
    }
}
