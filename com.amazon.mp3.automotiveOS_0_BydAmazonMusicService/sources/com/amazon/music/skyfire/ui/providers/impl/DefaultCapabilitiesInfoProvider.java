package com.amazon.music.skyfire.ui.providers.impl;

import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: DefaultCapabilitiesInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultCapabilitiesInfoProvider;", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "()V", "featureFlags", "", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultCapabilitiesInfoProvider implements CapabilitiesInfo {
    @Override // com.amazon.music.skyfire.core.network.CapabilitiesInfo
    public List<String> featureFlags() {
        return CollectionsKt__CollectionsKt.emptyList();
    }
}
