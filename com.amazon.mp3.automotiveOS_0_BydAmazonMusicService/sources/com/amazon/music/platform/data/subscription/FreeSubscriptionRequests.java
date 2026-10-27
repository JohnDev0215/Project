package com.amazon.music.platform.data.subscription;

import UpsellInterface.v1_0.PlaybackMode;
import com.amazon.music.platform.providers.SubscriptionRequest;
import kotlin.Metadata;

/* JADX INFO: compiled from: FreeSubscriptionRequests.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/data/subscription/FreeSubscriptionRequests;", "Lcom/amazon/music/platform/providers/SubscriptionRequest;", "", "eligibilityTierForUsherService", "requestedContentSubscriptionModeForMuPS", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class FreeSubscriptionRequests implements SubscriptionRequest {
    @Override // com.amazon.music.platform.providers.SubscriptionRequest
    public String eligibilityTierForUsherService() {
        return "AMF";
    }

    @Override // com.amazon.music.platform.providers.SubscriptionRequest
    public String requestedContentSubscriptionModeForMuPS() {
        return PlaybackMode.NONE;
    }
}
