package com.amazon.music.platform.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: SubscriptionRequestProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/platform/providers/SubscriptionRequest;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "", "eligibilityTierForUsherService", "requestedContentSubscriptionModeForMuPS", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface SubscriptionRequest extends InterfaceProvider {
    String eligibilityTierForUsherService();

    String requestedContentSubscriptionModeForMuPS();
}
