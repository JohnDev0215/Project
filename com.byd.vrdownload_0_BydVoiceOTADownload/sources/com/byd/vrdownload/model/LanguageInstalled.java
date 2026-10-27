package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class LanguageInstalled implements ILanguageInstalled, Parcelable {
    public static final Parcelable.Creator<LanguageInstalled> CREATOR = new Parcelable.Creator<LanguageInstalled>() { // from class: com.byd.vrdownload.model.LanguageInstalled.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageInstalled createFromParcel(Parcel parcel) {
            return new LanguageInstalled(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LanguageInstalled[] newArray(int i) {
            return new LanguageInstalled[i];
        }
    };
    String apkVersion;
    String currentVersion;
    String language;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public LanguageInstalled() {
    }

    @Override // com.byd.vrdownload.model.ILanguageInstalled
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setApkVersion(String str) {
        this.apkVersion = str;
    }

    public void setCurrentVersion(String str) {
        this.currentVersion = str;
    }

    @Override // com.byd.vrdownload.model.ILanguageInstalled
    public String getCurrentVersion() {
        return this.currentVersion;
    }

    @Override // com.byd.vrdownload.model.ILanguageInstalled
    public String getApkVersion() {
        return this.apkVersion;
    }

    protected LanguageInstalled(LanguageInstalled languageInstalled) {
        this.currentVersion = languageInstalled.currentVersion;
        this.language = languageInstalled.language;
        this.apkVersion = languageInstalled.apkVersion;
    }

    protected LanguageInstalled(Parcel parcel) {
    }
}
