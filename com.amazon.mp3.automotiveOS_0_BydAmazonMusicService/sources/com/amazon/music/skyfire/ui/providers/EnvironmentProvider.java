package com.amazon.music.skyfire.ui.providers;

import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: compiled from: EnvironmentProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Deprecated(message = "This provider has been superseded by SkyFireConfigurationProvider")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "", "endpointOverride", "Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "getEndpointOverride", "()Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface EnvironmentProvider {
    ServiceStage getEndpointOverride();
}
