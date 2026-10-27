package com.amazon.music.skyfire.ui.providers.impl;

import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.core.network.BuildInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultBuildInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultBuildInfoProvider;", "Lcom/amazon/music/skyfire/core/network/BuildInfo;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;Lcom/amazon/music/platform/providers/BuildInfoProvider;)V", "isDebug", "", "osVersion", "", "versionName", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultBuildInfoProvider implements BuildInfo {
    private final BuildInfoProvider buildInfoProvider;
    private final DeviceInfoProvider deviceInfoProvider;

    public DefaultBuildInfoProvider(DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider) {
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
        Intrinsics.checkNotNullParameter(buildInfoProvider, "buildInfoProvider");
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
    }

    @Override // com.amazon.music.skyfire.core.network.BuildInfo
    public boolean isDebug() {
        return this.buildInfoProvider.getIsDebug();
    }

    @Override // com.amazon.music.skyfire.core.network.BuildInfo
    public String versionName() {
        return this.buildInfoProvider.getAppVersion();
    }

    @Override // com.amazon.music.skyfire.core.network.BuildInfo
    public String osVersion() {
        return this.deviceInfoProvider.getOsVersion();
    }
}
