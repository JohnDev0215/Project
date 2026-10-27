package com.byd.vrdownload.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ArbitrationProfile implements Parcelable {
    public static final Parcelable.Creator<ArbitrationProfile> CREATOR = new Parcelable.Creator<ArbitrationProfile>() { // from class: com.byd.vrdownload.bean.ArbitrationProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ArbitrationProfile createFromParcel(Parcel parcel) {
            return new ArbitrationProfile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ArbitrationProfile[] newArray(int i) {
            return new ArbitrationProfile[i];
        }
    };
    String profileKey;
    String profileMd5;
    String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArbitrationProfile() {
    }

    protected ArbitrationProfile(Parcel parcel) {
        this.profileKey = parcel.readString();
        this.version = parcel.readString();
        this.profileMd5 = parcel.readString();
    }

    public String getProfileKey() {
        return this.profileKey;
    }

    public void setProfileKey(String str) {
        this.profileKey = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getProfileMd5() {
        return this.profileMd5;
    }

    public void setProfileMd5(String str) {
        this.profileMd5 = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.profileKey);
        parcel.writeString(this.version);
        parcel.writeString(this.profileMd5);
    }

    public String toString() {
        return "ArbitrationProfile{profileKey='" + this.profileKey + "', version='" + this.version + "', profileMd5='" + this.profileMd5 + "'}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ArbitrationProfile arbitrationProfile = (ArbitrationProfile) obj;
        return this.version.equals(arbitrationProfile.version) && this.profileKey.equals(arbitrationProfile.profileKey);
    }

    public int hashCode() {
        return Objects.hash(this.version, this.profileKey);
    }
}
