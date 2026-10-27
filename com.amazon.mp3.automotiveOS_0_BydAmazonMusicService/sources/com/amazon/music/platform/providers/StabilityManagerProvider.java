package com.amazon.music.platform.providers;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: StabilityManagerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/providers/StabilityManagerProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "", "eventName", "", "attributes", "", "leaveMetricBreadcrumbs", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface StabilityManagerProvider extends InterfaceProvider {
    void leaveMetricBreadcrumbs(String eventName, Map<String, String> attributes);
}
