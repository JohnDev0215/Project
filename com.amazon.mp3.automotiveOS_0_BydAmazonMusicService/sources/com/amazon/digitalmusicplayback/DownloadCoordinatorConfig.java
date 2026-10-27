package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class DownloadCoordinatorConfig {
    final ArrayList<String> AdditionalDownloadStoragePathsCatalog;
    final int ConcurrentBackgroundDownloads;
    final int ConcurrentForegroundDownloads;
    final Long DownloadProgressNotificationPeriod;
    final String DownloadStoragePathOwned;
    final boolean FriendlyName;
    final long MaximumStorageSize;
    final long MinimumFreeSpace;
    final Integer PageSize;
    final String PrimaryDownloadStoragePathCatalog;
    final QualitySetting QualitySetting;
    final String QuickplayDownloadStoragePath;

    public DownloadCoordinatorConfig(String str, String str2, String str3, ArrayList<String> arrayList, Long l, boolean z, int i, int i2, long j, long j2, Integer num, QualitySetting qualitySetting) {
        this.DownloadStoragePathOwned = str;
        this.PrimaryDownloadStoragePathCatalog = str2;
        this.QuickplayDownloadStoragePath = str3;
        this.AdditionalDownloadStoragePathsCatalog = arrayList;
        this.DownloadProgressNotificationPeriod = l;
        this.FriendlyName = z;
        this.ConcurrentForegroundDownloads = i;
        this.ConcurrentBackgroundDownloads = i2;
        this.MaximumStorageSize = j;
        this.MinimumFreeSpace = j2;
        this.PageSize = num;
        this.QualitySetting = qualitySetting;
    }

    public String getDownloadStoragePathOwned() {
        return this.DownloadStoragePathOwned;
    }

    public String getPrimaryDownloadStoragePathCatalog() {
        return this.PrimaryDownloadStoragePathCatalog;
    }

    public String getQuickplayDownloadStoragePath() {
        return this.QuickplayDownloadStoragePath;
    }

    public ArrayList<String> getAdditionalDownloadStoragePathsCatalog() {
        return this.AdditionalDownloadStoragePathsCatalog;
    }

    public Long getDownloadProgressNotificationPeriod() {
        return this.DownloadProgressNotificationPeriod;
    }

    public boolean getFriendlyName() {
        return this.FriendlyName;
    }

    public int getConcurrentForegroundDownloads() {
        return this.ConcurrentForegroundDownloads;
    }

    public int getConcurrentBackgroundDownloads() {
        return this.ConcurrentBackgroundDownloads;
    }

    public long getMaximumStorageSize() {
        return this.MaximumStorageSize;
    }

    public long getMinimumFreeSpace() {
        return this.MinimumFreeSpace;
    }

    public Integer getPageSize() {
        return this.PageSize;
    }

    public QualitySetting getQualitySetting() {
        return this.QualitySetting;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DownloadCoordinatorConfig)) {
            return false;
        }
        DownloadCoordinatorConfig downloadCoordinatorConfig = (DownloadCoordinatorConfig) obj;
        if (!this.DownloadStoragePathOwned.equals(downloadCoordinatorConfig.DownloadStoragePathOwned) || !this.PrimaryDownloadStoragePathCatalog.equals(downloadCoordinatorConfig.PrimaryDownloadStoragePathCatalog)) {
            return false;
        }
        String str = this.QuickplayDownloadStoragePath;
        if (!(str == null && downloadCoordinatorConfig.QuickplayDownloadStoragePath == null) && (str == null || !str.equals(downloadCoordinatorConfig.QuickplayDownloadStoragePath))) {
            return false;
        }
        ArrayList<String> arrayList = this.AdditionalDownloadStoragePathsCatalog;
        if (!(arrayList == null && downloadCoordinatorConfig.AdditionalDownloadStoragePathsCatalog == null) && (arrayList == null || !arrayList.equals(downloadCoordinatorConfig.AdditionalDownloadStoragePathsCatalog))) {
            return false;
        }
        Long l = this.DownloadProgressNotificationPeriod;
        if (((l != null || downloadCoordinatorConfig.DownloadProgressNotificationPeriod != null) && (l == null || !l.equals(downloadCoordinatorConfig.DownloadProgressNotificationPeriod))) || this.FriendlyName != downloadCoordinatorConfig.FriendlyName || this.ConcurrentForegroundDownloads != downloadCoordinatorConfig.ConcurrentForegroundDownloads || this.ConcurrentBackgroundDownloads != downloadCoordinatorConfig.ConcurrentBackgroundDownloads || this.MaximumStorageSize != downloadCoordinatorConfig.MaximumStorageSize || this.MinimumFreeSpace != downloadCoordinatorConfig.MinimumFreeSpace) {
            return false;
        }
        Integer num = this.PageSize;
        return ((num == null && downloadCoordinatorConfig.PageSize == null) || (num != null && num.equals(downloadCoordinatorConfig.PageSize))) && this.QualitySetting == downloadCoordinatorConfig.QualitySetting;
    }

    public int hashCode() {
        int iHashCode = (((527 + this.DownloadStoragePathOwned.hashCode()) * 31) + this.PrimaryDownloadStoragePathCatalog.hashCode()) * 31;
        String str = this.QuickplayDownloadStoragePath;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        ArrayList<String> arrayList = this.AdditionalDownloadStoragePathsCatalog;
        int iHashCode3 = (iHashCode2 + (arrayList == null ? 0 : arrayList.hashCode())) * 31;
        Long l = this.DownloadProgressNotificationPeriod;
        int iHashCode4 = (((((((iHashCode3 + (l == null ? 0 : l.hashCode())) * 31) + (this.FriendlyName ? 1 : 0)) * 31) + this.ConcurrentForegroundDownloads) * 31) + this.ConcurrentBackgroundDownloads) * 31;
        long j = this.MaximumStorageSize;
        int i = (iHashCode4 + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.MinimumFreeSpace;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        Integer num = this.PageSize;
        return ((i2 + (num != null ? num.hashCode() : 0)) * 31) + this.QualitySetting.hashCode();
    }

    public String toString() {
        return "DownloadCoordinatorConfig{DownloadStoragePathOwned=" + this.DownloadStoragePathOwned + ",PrimaryDownloadStoragePathCatalog=" + this.PrimaryDownloadStoragePathCatalog + ",QuickplayDownloadStoragePath=" + this.QuickplayDownloadStoragePath + ",AdditionalDownloadStoragePathsCatalog=" + this.AdditionalDownloadStoragePathsCatalog + ",DownloadProgressNotificationPeriod=" + this.DownloadProgressNotificationPeriod + ",FriendlyName=" + this.FriendlyName + ",ConcurrentForegroundDownloads=" + this.ConcurrentForegroundDownloads + ",ConcurrentBackgroundDownloads=" + this.ConcurrentBackgroundDownloads + ",MaximumStorageSize=" + this.MaximumStorageSize + ",MinimumFreeSpace=" + this.MinimumFreeSpace + ",PageSize=" + this.PageSize + ",QualitySetting=" + this.QualitySetting + "}";
    }
}
