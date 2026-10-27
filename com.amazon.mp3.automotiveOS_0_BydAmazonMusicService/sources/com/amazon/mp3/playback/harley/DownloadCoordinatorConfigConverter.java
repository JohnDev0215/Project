package com.amazon.mp3.playback.harley;

import com.amazon.digitalmusicplayback.DownloadCoordinatorConfig;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadCoordinatorConfigConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/harley/DownloadCoordinatorConfigConverter;", "", "()V", "getConfigFromDownloadProperties", "Lcom/amazon/digitalmusicplayback/DownloadCoordinatorConfig;", "downloadProperties", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadCoordinatorConfigConverter {
    public static final DownloadCoordinatorConfigConverter INSTANCE = new DownloadCoordinatorConfigConverter();

    private DownloadCoordinatorConfigConverter() {
    }

    public final DownloadCoordinatorConfig getConfigFromDownloadProperties(DownloadProperties downloadProperties) {
        Intrinsics.checkNotNullParameter(downloadProperties, "downloadProperties");
        return new DownloadCoordinatorConfig(downloadProperties.getPurchasedContentDownloadDirectory(), downloadProperties.getCatalogContentDownloadDirectory(), "", CollectionsKt__CollectionsKt.arrayListOf(downloadProperties.getAdditionalDownloadStoragePathForCatalog()), Long.valueOf(downloadProperties.getDownloadProgressNotificationInterval()), downloadProperties.getShouldStorePurchasedContentWithFriendlyName(), downloadProperties.getMaxNumConcurrentDownload(), downloadProperties.getMaxNumConcurrentDownload(), downloadProperties.getMaximumStorageSize(), downloadProperties.getMinimumFreeSpace(), 100, downloadProperties.getDownloadQualityPreference().convertToQualitySetting());
    }
}
