package com.byd.vrdownload.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class LanguageUpdate extends LanguageInstalled {
    public static final String APK_VERSION = "apkVersion";
    public static final Parcelable.Creator<LanguageUpdate> CREATOR = new Parcelable.Creator<LanguageUpdate>() { // from class: com.byd.vrdownload.model.LanguageUpdate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageUpdate createFromParcel(Parcel parcel) {
            return new LanguageUpdate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageUpdate[] newArray(int i) {
            return new LanguageUpdate[i];
        }
    };
    public static final String CURRENT_VERSION = "currentVersion";
    public static final String DOWNLOADING_APK_VERSION = "downloadingApkVersion";
    public static final String DOWNLOADING_PATCH_PKG_KEY = "downloadingPatchPkgKey";
    public static final String DOWNLOADING_PATCH_PKG_VERSION = "downloadingPatchPkgVersion";
    public static final String DOWNLOADING_PATH = "downloadingPath";
    public static final String DOWNLOADING_PKGMd5 = "downloadingPkgMd5";
    public static final String DOWNLOADING_PKG_DESC = "downloadingPkgDesc";
    public static final String DOWNLOADING_PKG_KEY = "downloadingPkgKey";
    public static final String DOWNLOADING_PKG_SIZE = "downloadingPkgSize";
    public static final String DOWNLOADING_PKG_VERSION = "downloadingPkgVersion";
    public static final String DOWNLOADING_URL = "downloadingUrl";
    public static final String ID = "id";
    public static final String LANGUAGE = "language";
    public static final String LATEST_APK_VERSION = "latestApkVersion";
    public static final String LATEST_PATCH_PKG_KEY = "latestPatchPkgKey";
    public static final String LATEST_PATCH_PKG_MD5 = "latestPatchPkgMd5";
    public static final String LATEST_PATCH_PKG_SIZE = "latestPatchPkgSize";
    public static final String LATEST_PATCH_PKG_VERSION = "latestPatchPkgVersion";
    public static final String LATEST_PKGMd5 = "latestPkgMd5";
    public static final String LATEST_PKG_DESC = "latestPkgDesc";
    public static final String LATEST_PKG_KEY = "latestPkgKey";
    public static final String LATEST_PKG_SIZE = "latestPkgSize";
    public static final String LATEST_PKG_VERSION = "latestPkgVersion";
    public static final String LATEST_URL = "latestUrl";
    private static final String TAG = "LanguageUpdate";
    String downloadingApkVersion;
    String downloadingPatchPkgVersion;
    String downloadingPath;
    String downloadingPkgDesc;
    String downloadingPkgKey;
    String downloadingPkgMd5;
    String downloadingPkgSize;
    String downloadingPkgUrl;
    String downloadingPkgVersion;
    int id;
    String latestApkVersion;
    String latestPatchPkgKey;
    String latestPatchPkgMd5;
    String latestPatchPkgSize;
    String latestPatchPkgVersion;
    String latestPkgDesc;
    String latestPkgKey;
    String latestPkgMd5;
    String latestPkgSize;
    String latestPkgUrl;
    String latestPkgVersion;

    public LanguageUpdate() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDownloadingPkgVersion() {
        return this.downloadingPkgVersion;
    }

    public void setDownloadingPkgVersion(String str) {
        this.downloadingPkgVersion = str;
    }

    public String getDownloadingPkgUrl() {
        return this.downloadingPkgUrl;
    }

    public void setDownloadingPkgUrl(String str) {
        this.downloadingPkgUrl = str;
    }

    public String getDownloadingPkgMd5() {
        return this.downloadingPkgMd5;
    }

    public void setDownloadingPkgMd5(String str) {
        this.downloadingPkgMd5 = str;
    }

    public String getDownloadingPkgSize() {
        return this.downloadingPkgSize;
    }

    public void setDownloadingPkgSize(String str) {
        this.downloadingPkgSize = str;
    }

    public String getDownloadingPkgKey() {
        return this.downloadingPkgKey;
    }

    public void setDownloadingPkgKey(String str) {
        this.downloadingPkgKey = str;
    }

    public String getDownloadingApkVersion() {
        return this.downloadingApkVersion;
    }

    public void setDownloadingApkVersion(String str) {
        this.downloadingApkVersion = str;
    }

    public String getLatestApkVersion() {
        return this.latestApkVersion;
    }

    public void setLatestApkVersion(String str) {
        this.latestApkVersion = str;
    }

    public String getLatestPkgVersion() {
        return this.latestPkgVersion;
    }

    public void setLatestPkgVersion(String str) {
        this.latestPkgVersion = str;
    }

    public String getLatestPkgUrl() {
        return this.latestPkgUrl;
    }

    public void setLatestPkgUrl(String str) {
        this.latestPkgUrl = str;
    }

    public String getLatestPkgMd5() {
        return this.latestPkgMd5;
    }

    public void setLatestPkgMd5(String str) {
        this.latestPkgMd5 = str;
    }

    public String getLatestPkgSize() {
        return this.latestPkgSize;
    }

    public void setLatestPkgSize(String str) {
        this.latestPkgSize = str;
    }

    public String getLatestPkgKey() {
        return this.latestPkgKey;
    }

    public void setLatestPkgKey(String str) {
        this.latestPkgKey = str;
    }

    public String getDownloadingPkgDesc() {
        return this.downloadingPkgDesc;
    }

    public void setDownloadingPkgDesc(String str) {
        this.downloadingPkgDesc = str;
    }

    public String getLatestPkgDesc() {
        return this.latestPkgDesc;
    }

    public void setLatestPkgDesc(String str) {
        this.latestPkgDesc = str;
    }

    public String getDownloadingPatchPkgVersion() {
        return this.downloadingPatchPkgVersion;
    }

    public void setDownloadingPatchPkgVersion(String str) {
        this.downloadingPatchPkgVersion = str;
    }

    public String getLatestPatchPkgKey() {
        return this.latestPatchPkgKey;
    }

    public void setLatestPatchPkgKey(String str) {
        this.latestPatchPkgKey = str;
    }

    public String getLatestPatchPkgVersion() {
        return this.latestPatchPkgVersion;
    }

    public void setLatestPatchPkgVersion(String str) {
        this.latestPatchPkgVersion = str;
    }

    public String getLatestPatchPkgMd5() {
        return this.latestPatchPkgMd5;
    }

    public void setLatestPatchPkgMd5(String str) {
        this.latestPatchPkgMd5 = str;
    }

    public String getLatestPatchPkgSize() {
        return this.latestPatchPkgSize;
    }

    public void setLatestPatchPkgSize(String str) {
        this.latestPatchPkgSize = str;
    }

    public String getDownloadingPath() {
        return this.downloadingPath;
    }

    public void setDownloadingPath(String str) {
        this.downloadingPath = str;
    }

    public String getPkgKeyToDownload() {
        Log.i(TAG, "getPkgKeyToDownload: latestPatchPkgVersion=" + this.latestPatchPkgVersion + ",currentVersion=" + this.currentVersion);
        String str = this.latestPatchPkgVersion;
        if (str != null && str.equals(this.currentVersion)) {
            return this.latestPatchPkgKey;
        }
        return this.latestPkgKey;
    }

    public LanguageUpdate startDownload(String str) {
        if (str.equals(this.downloadingPkgKey)) {
            Log.i(TAG, "startDownload: 已进入下载队列");
            return this;
        }
        Log.i(TAG, "startDownload: 更新下载状态");
        if (str.equals(this.latestPatchPkgKey)) {
            Log.i(TAG, "startDownload: 差分更新");
            this.downloadingPkgKey = str;
            this.downloadingPkgSize = this.latestPatchPkgSize;
            this.downloadingPkgMd5 = this.latestPatchPkgMd5;
            this.downloadingPatchPkgVersion = this.currentVersion;
        } else if (str.equals(this.latestPkgKey)) {
            Log.i(TAG, "startDownload: 全量更新");
            this.downloadingPkgKey = str;
            this.downloadingPkgSize = this.latestPkgSize;
            this.downloadingPkgMd5 = this.latestPkgMd5;
        }
        this.downloadingApkVersion = this.latestApkVersion;
        this.downloadingPkgVersion = this.latestPkgVersion;
        this.downloadingPkgDesc = this.latestPkgDesc;
        return this;
    }

    public boolean isDifferentialUpdate() {
        return getPkgKeyToDownload().endsWith(".d");
    }

    public String toString() {
        return "LanguageUpdate{id=" + this.id + ", language='" + this.language + "', currentVersion='" + this.currentVersion + "', apkVersion='" + this.apkVersion + "', downloadingApkVersion='" + this.downloadingApkVersion + "', downloadingPkgVersion='" + this.downloadingPkgVersion + "', downloadingPkgUrl='" + this.downloadingPkgUrl + "', downloadingPatchPkgVersion='" + this.downloadingPatchPkgVersion + "', downloadingPkgMd5='" + this.downloadingPkgMd5 + "', downloadingPkgSize='" + this.downloadingPkgSize + "', downloadingPkgKey='" + this.downloadingPkgKey + "', downloadingPkgDesc='" + this.downloadingPkgDesc + "', latestApkVersion='" + this.latestApkVersion + "', latestPkgVersion='" + this.latestPkgVersion + "', latestPkgUrl='" + this.latestPkgUrl + "', latestPkgDesc='" + this.latestPkgDesc + "', latestPkgMd5='" + this.latestPkgMd5 + "', latestPkgSize='" + this.latestPkgSize + "', latestPkgKey='" + this.latestPkgKey + "', latestPatchPkgKey='" + this.latestPatchPkgKey + "', latestPatchPkgVersion='" + this.latestPatchPkgVersion + "', latestPatchPkgMd5='" + this.latestPatchPkgMd5 + "', latestPatchPkgSize='" + this.latestPatchPkgSize + "', downloadingPath='" + this.downloadingPath + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.language.equals(((LanguageUpdate) obj).language);
    }

    public int hashCode() {
        return Objects.hash(this.language);
    }

    @Override // com.byd.vrdownload.model.LanguageInstalled, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.language);
        parcel.writeString(this.currentVersion);
        parcel.writeString(this.apkVersion);
        parcel.writeString(this.downloadingApkVersion);
        parcel.writeString(this.downloadingPkgVersion);
        parcel.writeString(this.downloadingPkgUrl);
        parcel.writeString(this.downloadingPkgKey);
        parcel.writeString(this.downloadingPkgSize);
        parcel.writeString(this.downloadingPkgMd5);
        parcel.writeString(this.latestApkVersion);
        parcel.writeString(this.latestPkgVersion);
        parcel.writeString(this.latestPkgUrl);
        parcel.writeString(this.latestPkgKey);
        parcel.writeString(this.latestPkgSize);
        parcel.writeString(this.latestPkgMd5);
        parcel.writeString(this.downloadingPkgDesc);
        parcel.writeString(this.latestPkgDesc);
        parcel.writeString(this.downloadingPatchPkgVersion);
        parcel.writeString(this.latestPatchPkgKey);
        parcel.writeString(this.latestPatchPkgVersion);
        parcel.writeString(this.latestPatchPkgMd5);
        parcel.writeString(this.latestPatchPkgSize);
        parcel.writeString(this.downloadingPath);
    }

    protected LanguageUpdate(Parcel parcel) {
        this.language = parcel.readString();
        this.currentVersion = parcel.readString();
        this.apkVersion = parcel.readString();
        this.downloadingApkVersion = parcel.readString();
        this.downloadingPkgVersion = parcel.readString();
        this.downloadingPkgUrl = parcel.readString();
        this.downloadingPkgKey = parcel.readString();
        this.downloadingPkgSize = parcel.readString();
        this.downloadingPkgMd5 = parcel.readString();
        this.latestApkVersion = parcel.readString();
        this.latestPkgVersion = parcel.readString();
        this.latestPkgUrl = parcel.readString();
        this.latestPkgKey = parcel.readString();
        this.latestPkgSize = parcel.readString();
        this.latestPkgMd5 = parcel.readString();
        this.downloadingPkgDesc = parcel.readString();
        this.latestPkgDesc = parcel.readString();
        this.downloadingPatchPkgVersion = parcel.readString();
        this.latestPatchPkgKey = parcel.readString();
        this.latestPatchPkgVersion = parcel.readString();
        this.latestPatchPkgMd5 = parcel.readString();
        this.latestPatchPkgSize = parcel.readString();
        this.downloadingPath = parcel.readString();
    }

    public LanguageUpdate(LanguageInstalled languageInstalled) {
        super(languageInstalled);
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(this.id));
        contentValues.put(LANGUAGE, this.language);
        contentValues.put(APK_VERSION, this.apkVersion);
        contentValues.put(CURRENT_VERSION, this.currentVersion);
        contentValues.put(DOWNLOADING_APK_VERSION, this.downloadingApkVersion);
        contentValues.put(DOWNLOADING_PKG_VERSION, getDownloadingPkgVersion());
        contentValues.put(DOWNLOADING_PKG_KEY, getDownloadingPkgKey());
        contentValues.put(DOWNLOADING_URL, getDownloadingPkgUrl());
        contentValues.put(DOWNLOADING_PKG_SIZE, getDownloadingPkgSize());
        contentValues.put(DOWNLOADING_PKGMd5, getDownloadingPkgMd5());
        contentValues.put(DOWNLOADING_PKG_DESC, getDownloadingPkgDesc());
        contentValues.put(LATEST_APK_VERSION, getLatestApkVersion());
        contentValues.put(LATEST_PKG_VERSION, getLatestPkgVersion());
        contentValues.put(LATEST_PKG_KEY, getLatestPkgKey());
        contentValues.put(LATEST_URL, getLatestPkgUrl());
        contentValues.put(LATEST_PKG_SIZE, getLatestPkgSize());
        contentValues.put(LATEST_PKGMd5, getLatestPkgMd5());
        contentValues.put(LATEST_PKG_DESC, getLatestPkgDesc());
        contentValues.put(DOWNLOADING_PATCH_PKG_KEY, getDownloadingPatchPkgVersion());
        contentValues.put(DOWNLOADING_PATCH_PKG_VERSION, getDownloadingPatchPkgVersion());
        contentValues.put(LATEST_PATCH_PKG_KEY, getLatestPatchPkgKey());
        contentValues.put(LATEST_PATCH_PKG_VERSION, getLatestPatchPkgVersion());
        contentValues.put(LATEST_PATCH_PKG_SIZE, getLatestPatchPkgSize());
        contentValues.put(LATEST_PATCH_PKG_MD5, getLatestPatchPkgMd5());
        contentValues.put(DOWNLOADING_PATH, getDownloadingPath());
        return contentValues;
    }
}
