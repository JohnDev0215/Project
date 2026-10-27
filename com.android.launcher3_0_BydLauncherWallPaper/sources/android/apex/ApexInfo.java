package android.apex;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ApexInfo implements Parcelable {
    public static final Parcelable.Creator<ApexInfo> CREATOR = new Parcelable.Creator<ApexInfo>() { // from class: android.apex.ApexInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexInfo createFromParcel(Parcel parcel) {
            ApexInfo apexInfo = new ApexInfo();
            apexInfo.readFromParcel(parcel);
            return apexInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ApexInfo[] newArray(int i) {
            return new ApexInfo[i];
        }
    };
    public boolean isActive;
    public boolean isFactory;
    public String moduleName;
    public String modulePath;
    public String preinstalledModulePath;
    public long versionCode;
    public String versionName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.moduleName);
        parcel.writeString(this.modulePath);
        parcel.writeString(this.preinstalledModulePath);
        parcel.writeLong(this.versionCode);
        parcel.writeString(this.versionName);
        parcel.writeInt(this.isFactory ? 1 : 0);
        parcel.writeInt(this.isActive ? 1 : 0);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.moduleName = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.modulePath = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.preinstalledModulePath = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.versionCode = parcel.readLong();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.versionName = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                boolean z = true;
                                this.isFactory = parcel.readInt() != 0;
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    if (parcel.readInt() == 0) {
                                        z = false;
                                    }
                                    this.isActive = z;
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
