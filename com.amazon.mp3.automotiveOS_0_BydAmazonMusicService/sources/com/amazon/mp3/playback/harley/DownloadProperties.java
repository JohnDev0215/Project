package com.amazon.mp3.playback.harley;

import com.amazon.mp3.download.DownloadQualityPreferences;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadProperties.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b0\u00101J\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u001c\u0010\b\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\u000b\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007R\u001c\u0010\u000e\u001a\u00020\u00028&@&X¦\u000e¢\u0006\f\u001a\u0004\b\f\u0010\u0005\"\u0004\b\r\u0010\u0007R\u001c\u0010\u0014\u001a\u00020\u000f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u001a\u001a\u00020\u00158&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010 \u001a\u00020\u001b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010#\u001a\u00020\u000f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b!\u0010\u0011\"\u0004\b\"\u0010\u0013R\u001c\u0010&\u001a\u00020\u000f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0011\"\u0004\b%\u0010\u0013R\u001c\u0010)\u001a\u00020\u000f8&@&X¦\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0011\"\u0004\b(\u0010\u0013R\u001c\u0010/\u001a\u00020*8&@&X¦\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00062"}, d2 = {"Lcom/amazon/mp3/playback/harley/DownloadProperties;", "", "", "toString", "getPurchasedContentDownloadDirectory", "()Ljava/lang/String;", "setPurchasedContentDownloadDirectory", "(Ljava/lang/String;)V", "purchasedContentDownloadDirectory", "getCatalogContentDownloadDirectory", "setCatalogContentDownloadDirectory", "catalogContentDownloadDirectory", "getAdditionalDownloadStoragePathForCatalog", "setAdditionalDownloadStoragePathForCatalog", "additionalDownloadStoragePathForCatalog", "", "getDownloadProgressNotificationInterval", "()J", "setDownloadProgressNotificationInterval", "(J)V", "downloadProgressNotificationInterval", "", "getShouldStorePurchasedContentWithFriendlyName", "()Z", "setShouldStorePurchasedContentWithFriendlyName", "(Z)V", "shouldStorePurchasedContentWithFriendlyName", "", "getMaxNumConcurrentDownload", "()I", "setMaxNumConcurrentDownload", "(I)V", "maxNumConcurrentDownload", "getMaximumStorageSize", "setMaximumStorageSize", "maximumStorageSize", "getMinimumFreeSpace", "setMinimumFreeSpace", "minimumFreeSpace", "getMaximumCacheSize", "setMaximumCacheSize", "maximumCacheSize", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "getDownloadQualityPreference", "()Lcom/amazon/mp3/download/DownloadQualityPreferences;", "setDownloadQualityPreference", "(Lcom/amazon/mp3/download/DownloadQualityPreferences;)V", "downloadQualityPreference", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class DownloadProperties {
    public abstract String getAdditionalDownloadStoragePathForCatalog();

    public abstract String getCatalogContentDownloadDirectory();

    public abstract long getDownloadProgressNotificationInterval();

    public abstract DownloadQualityPreferences getDownloadQualityPreference();

    public abstract int getMaxNumConcurrentDownload();

    public abstract long getMaximumCacheSize();

    public abstract long getMaximumStorageSize();

    public abstract long getMinimumFreeSpace();

    public abstract String getPurchasedContentDownloadDirectory();

    public abstract boolean getShouldStorePurchasedContentWithFriendlyName();

    public abstract void setMaxNumConcurrentDownload(int i);

    public String toString() {
        return "downloadProgressNotificationInterval: " + getDownloadProgressNotificationInterval() + ", purchasedContentDownloadDirectory: " + getPurchasedContentDownloadDirectory() + ", catalogContentDownloadDirectory: " + getCatalogContentDownloadDirectory() + ", additionalDownloadStoragePathForCatalog: " + getAdditionalDownloadStoragePathForCatalog() + ", shouldStorePurchasedContentWithFriendlyName: " + getShouldStorePurchasedContentWithFriendlyName() + ", maximumStorageSize: " + getMaximumStorageSize() + " MB, minimumFreeSpace: " + getMinimumFreeSpace() + " MB, maximumCacheSize: " + getMaximumCacheSize() + " MB, downloadQualitySetting : " + getDownloadQualityPreference() + ", maxNumConcurrentDownload: " + getMaxNumConcurrentDownload() + '.';
    }
}
