package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class InstallationFileParcel implements Parcelable {
    public static final Parcelable.Creator<InstallationFileParcel> CREATOR = new Parcelable.Creator<InstallationFileParcel>() { // from class: android.content.pm.InstallationFileParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallationFileParcel createFromParcel(Parcel parcel) {
            InstallationFileParcel installationFileParcel = new InstallationFileParcel();
            installationFileParcel.readFromParcel(parcel);
            return installationFileParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstallationFileParcel[] newArray(int i) {
            return new InstallationFileParcel[i];
        }
    };
    public int location;
    public byte[] metadata;
    public String name;
    public byte[] signature;
    public long size;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.name);
        parcel.writeInt(this.location);
        parcel.writeLong(this.size);
        parcel.writeByteArray(this.metadata);
        parcel.writeByteArray(this.signature);
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
            this.name = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.location = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.size = parcel.readLong();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.metadata = parcel.createByteArray();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.signature = parcel.createByteArray();
                            if (parcel.dataPosition() - iDataPosition < i) {
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
