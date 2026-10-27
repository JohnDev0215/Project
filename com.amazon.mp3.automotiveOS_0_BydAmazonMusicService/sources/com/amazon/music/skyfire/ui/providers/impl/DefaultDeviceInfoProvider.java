package com.amazon.music.skyfire.ui.providers.impl;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.core.network.DeviceInfo;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultDeviceInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultDeviceInfoProvider;", "Lcom/amazon/music/skyfire/core/network/DeviceInfo;", "provider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "deviceTypeId", "", "family", "getUserAgent", "id", Splash.PARAMS_LOCALE, "manufacturer", "model", "screenHeight", "screenScale", "screenWidth", "timeZone", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultDeviceInfoProvider implements DeviceInfo {
    private static final String DEVICE_FAMILY = "MobileAndroid";
    private final DeviceInfoProvider provider;

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String family() {
        return DEVICE_FAMILY;
    }

    public DefaultDeviceInfoProvider(DeviceInfoProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.provider = provider;
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String manufacturer() {
        return this.provider.getManufacturer();
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String model() {
        return this.provider.getModel();
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String locale() {
        String str = (String) CollectionsKt___CollectionsKt.firstOrNull((List) this.provider.getLocales());
        return str == null ? "" : str;
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String screenWidth() {
        return String.valueOf(this.provider.getScreenWidth());
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String screenHeight() {
        return String.valueOf(this.provider.getScreenHeight());
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String screenScale() {
        return String.valueOf(this.provider.getScreenDensity());
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String id() {
        return this.provider.getDeviceIdentity().getDeviceId();
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String deviceTypeId() {
        return this.provider.getDeviceIdentity().getDeviceTypeId();
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String timeZone() {
        return this.provider.getTimeZone();
    }

    @Override // com.amazon.music.skyfire.core.network.DeviceInfo
    public String getUserAgent() {
        return this.provider.getUserAgent();
    }
}
