package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.chad.library.BuildConfig;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class LanguageOTA extends LanguageInstalled implements ILanguageOTA, Parcelable {
    public static final Parcelable.Creator<LanguageOTA> CREATOR = new Parcelable.Creator<LanguageOTA>() { // from class: com.byd.vrdownload.model.LanguageOTA.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageOTA createFromParcel(Parcel parcel) {
            return new LanguageOTA(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageOTA[] newArray(int i) {
            return new LanguageOTA[i];
        }
    };
    String originalSize;
    String patchPkgKey;
    String patchPkgMd5;
    String patchPkgSize;
    String patchPkgVersion;
    String pkgDesc;
    String pkgKey;
    String pkgMd5;
    String pkgSize;
    String pkgVersion;
    Long timestamp;

    @Override // com.byd.vrdownload.model.LanguageInstalled, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.byd.vrdownload.model.ILanguageOTA
    public String getPkgVersion() {
        return this.pkgVersion;
    }

    public void setPkgVersion(String str) {
        this.pkgVersion = str;
    }

    public LanguageOTA() {
    }

    public void setPkgKey(String str) {
        this.pkgKey = str;
    }

    public void setTimeStamp(Long l) {
        this.timestamp = l;
    }

    public void setPkgMd5(String str) {
        this.pkgMd5 = str;
    }

    public void setPkgSize(String str) {
        this.pkgSize = str;
    }

    public String getOriginalSize() {
        return this.originalSize;
    }

    public void setOriginalSize(String str) {
        this.originalSize = str;
    }

    @Override // com.byd.vrdownload.model.ILanguageOTA
    public String getPkgMd5() {
        return this.pkgMd5;
    }

    @Override // com.byd.vrdownload.model.ILanguageOTA
    public String getPkgSize() {
        return this.pkgSize;
    }

    @Override // com.byd.vrdownload.model.ILanguageOTA
    public String getPkgKey() {
        return this.pkgKey;
    }

    @Override // com.byd.vrdownload.model.ILanguageOTA
    public Long getTimeStamp() {
        return this.timestamp;
    }

    @Override // com.byd.vrdownload.model.LanguageInstalled, com.byd.vrdownload.model.ILanguageInstalled
    public String getApkVersion() {
        return this.apkVersion;
    }

    public String getPkgDesc() {
        return this.pkgDesc;
    }

    public void setPkgDesc(String str) {
        this.pkgDesc = str;
    }

    public String getPatchPkgKey() {
        return this.patchPkgKey;
    }

    public void setPatchPkgKey(String str) {
        this.patchPkgKey = str;
    }

    public String getPatchPkgVersion() {
        return this.patchPkgVersion;
    }

    public void setPatchPkgVersion(String str) {
        this.patchPkgVersion = str;
    }

    public String getPatchPkgSize() {
        return this.patchPkgSize;
    }

    public void setPatchPkgSize(String str) {
        this.patchPkgSize = str;
    }

    public String getPatchPkgMd5() {
        return this.patchPkgMd5;
    }

    public void setPatchPkgMd5(String str) {
        this.patchPkgMd5 = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.language.equals(((LanguageOTA) obj).language);
    }

    public int hashCode() {
        return Objects.hash(this.language);
    }

    @Override // com.byd.vrdownload.model.LanguageInstalled, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.language);
        parcel.writeString(this.currentVersion);
        parcel.writeString(this.apkVersion);
        parcel.writeString(this.pkgVersion);
        parcel.writeString(this.pkgKey);
        parcel.writeString(this.pkgMd5);
        parcel.writeString(this.pkgSize);
        Long l = this.timestamp;
        parcel.writeLong(l == null ? 0L : l.longValue());
        parcel.writeString(this.pkgDesc);
        parcel.writeString(this.patchPkgKey);
        parcel.writeString(this.patchPkgVersion);
        parcel.writeString(this.patchPkgSize);
        parcel.writeString(this.patchPkgMd5);
        parcel.writeString(this.originalSize);
    }

    protected LanguageOTA(Parcel parcel) {
        this.language = parcel.readString();
        this.currentVersion = parcel.readString();
        this.apkVersion = parcel.readString();
        this.pkgVersion = parcel.readString();
        this.pkgKey = parcel.readString();
        this.pkgMd5 = parcel.readString();
        this.pkgSize = parcel.readString();
        this.timestamp = Long.valueOf(parcel.readLong());
        this.pkgDesc = parcel.readString();
        this.patchPkgKey = parcel.readString();
        this.patchPkgVersion = parcel.readString();
        this.patchPkgSize = parcel.readString();
        this.patchPkgMd5 = parcel.readString();
        this.originalSize = parcel.readString();
    }

    public LanguageOTA(LanguageInstalled languageInstalled) {
        super(languageInstalled);
        this.pkgVersion = BuildConfig.FLAVOR;
        this.pkgMd5 = BuildConfig.FLAVOR;
        this.pkgSize = BuildConfig.FLAVOR;
        this.timestamp = 0L;
        this.pkgKey = BuildConfig.FLAVOR;
    }

    public String toString() {
        return "LanguageOTA{ language='" + this.language + "', currentVersion='" + this.currentVersion + "', apkVersion='" + this.apkVersion + "', pkgMd5='" + this.pkgMd5 + "', pkgSize='" + this.pkgSize + "', pkgKey='" + this.pkgKey + "', timestamp=" + this.timestamp + ", pkgDesc='" + this.pkgDesc + "', patchPkgKey='" + this.patchPkgKey + "', patchPkgVersion='" + this.patchPkgVersion + "', patchPkgSize='" + this.patchPkgSize + "', patchPkgMd5='" + this.patchPkgMd5 + "', originalSize='" + this.originalSize + "'}";
    }
}
