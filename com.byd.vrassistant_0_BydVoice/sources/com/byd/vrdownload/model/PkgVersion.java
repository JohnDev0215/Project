package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PkgVersion implements IPkgVersion, Parcelable {
    public static final Parcelable.Creator<PkgVersion> CREATOR = new Parcelable.Creator<PkgVersion>() { // from class: com.byd.vrdownload.model.PkgVersion.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PkgVersion createFromParcel(Parcel parcel) {
            return new PkgVersion(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PkgVersion[] newArray(int i) {
            return new PkgVersion[i];
        }
    };
    String language;
    String pkgVersion;
    String upgradeDate;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PkgVersion() {
    }

    protected PkgVersion(Parcel parcel) {
        this.pkgVersion = parcel.readString();
        this.language = parcel.readString();
        this.upgradeDate = parcel.readString();
    }

    @Override // com.byd.vrdownload.model.IPkgVersion
    public String getPkgVersion() {
        return this.pkgVersion;
    }

    public void setPkgVersion(String str) {
        this.pkgVersion = str;
    }

    @Override // com.byd.vrdownload.model.IPkgVersion
    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    @Override // com.byd.vrdownload.model.IPkgVersion
    public String getUpgradeDate() {
        return this.upgradeDate;
    }

    public void setUpgradeDate(String str) {
        this.upgradeDate = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.pkgVersion);
        parcel.writeString(this.language);
        parcel.writeString(this.upgradeDate);
    }

    public String toString() {
        return "PkgVersion{pkgVersion='" + this.pkgVersion + "', language='" + this.language + "', upgradeDate='" + this.upgradeDate + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PkgVersion pkgVersion = (PkgVersion) obj;
        return this.pkgVersion.equals(pkgVersion.pkgVersion) && this.language.equals(pkgVersion.language);
    }

    public int hashCode() {
        return Objects.hash(this.pkgVersion, this.language);
    }
}
