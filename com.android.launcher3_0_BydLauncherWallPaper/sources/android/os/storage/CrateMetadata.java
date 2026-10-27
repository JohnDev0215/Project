package android.os.storage;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class CrateMetadata implements Parcelable {
    public static final Parcelable.Creator<CrateMetadata> CREATOR = new Parcelable.Creator<CrateMetadata>() { // from class: android.os.storage.CrateMetadata.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CrateMetadata createFromParcel(Parcel parcel) {
            CrateMetadata crateMetadata = new CrateMetadata();
            crateMetadata.readFromParcel(parcel);
            return crateMetadata;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CrateMetadata[] newArray(int i) {
            return new CrateMetadata[i];
        }
    };
    public String id;
    public String packageName;
    public int uid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.uid);
        parcel.writeString(this.packageName);
        parcel.writeString(this.id);
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
            this.uid = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.packageName = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.id = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
