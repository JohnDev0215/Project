package com.amazon.music.platform.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: NestedEligibilitiesProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0001H&¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/platform/providers/NestedEligibilitiesProvider;", "Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "getParentEligibilities", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface NestedEligibilitiesProvider extends EntityEligibilitiesProvider {
    EntityEligibilitiesProvider getParentEligibilities();
}
