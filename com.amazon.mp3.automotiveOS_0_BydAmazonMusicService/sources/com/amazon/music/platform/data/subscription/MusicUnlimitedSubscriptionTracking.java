package com.amazon.music.platform.data.subscription;

import com.amazon.music.platform.providers.SubscriptionTracking;
import kotlin.Metadata;

/* JADX INFO: compiled from: MusicUnlimitedSubscriptionTracking.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/data/subscription/MusicUnlimitedSubscriptionTracking;", "Lcom/amazon/music/platform/providers/SubscriptionTracking;", "", "contentSubscriptionMode", "subContentSubscriptionMode", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class MusicUnlimitedSubscriptionTracking implements SubscriptionTracking {
    @Override // com.amazon.music.platform.providers.SubscriptionTracking
    public String contentSubscriptionMode() {
        return SubscriptionType.MUSIC_UNLIMITED.contentSubscriptionMode();
    }

    @Override // com.amazon.music.platform.providers.SubscriptionTracking
    public String subContentSubscriptionMode() {
        return SubscriptionType.MUSIC_UNLIMITED.subContentSubscriptionMode();
    }
}
