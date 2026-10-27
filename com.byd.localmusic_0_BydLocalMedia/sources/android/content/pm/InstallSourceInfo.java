package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class InstallSourceInfo implements Parcelable {
    public static final Parcelable.Creator<InstallSourceInfo> CREATOR = new Parcelable.Creator<InstallSourceInfo>() { // from class: android.content.pm.InstallSourceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallSourceInfo createFromParcel(Parcel parcel) {
            return new InstallSourceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallSourceInfo[] newArray(int i) {
            return new InstallSourceInfo[i];
        }
    };
    private final String mInitiatingPackageName;
    private final SigningInfo mInitiatingPackageSigningInfo;
    private final String mInstallingPackageName;
    private final String mOriginatingPackageName;

    public InstallSourceInfo(String str, SigningInfo signingInfo, String str2, String str3) {
        this.mInitiatingPackageName = str;
        this.mInitiatingPackageSigningInfo = signingInfo;
        this.mOriginatingPackageName = str2;
        this.mInstallingPackageName = str3;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        SigningInfo signingInfo = this.mInitiatingPackageSigningInfo;
        if (signingInfo == null) {
            return 0;
        }
        return signingInfo.describeContents();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mInitiatingPackageName);
        parcel.writeParcelable(this.mInitiatingPackageSigningInfo, i);
        parcel.writeString(this.mOriginatingPackageName);
        parcel.writeString(this.mInstallingPackageName);
    }

    private InstallSourceInfo(Parcel parcel) {
        this.mInitiatingPackageName = parcel.readString();
        this.mInitiatingPackageSigningInfo = (SigningInfo) parcel.readParcelable(SigningInfo.class.getClassLoader());
        this.mOriginatingPackageName = parcel.readString();
        this.mInstallingPackageName = parcel.readString();
    }

    public String getInitiatingPackageName() {
        return this.mInitiatingPackageName;
    }

    public SigningInfo getInitiatingPackageSigningInfo() {
        return this.mInitiatingPackageSigningInfo;
    }

    public String getOriginatingPackageName() {
        return this.mOriginatingPackageName;
    }

    public String getInstallingPackageName() {
        return this.mInstallingPackageName;
    }
}
