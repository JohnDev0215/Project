package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PackageChangeEvent implements Parcelable {
    public static final Parcelable.Creator<PackageChangeEvent> CREATOR = new Parcelable.Creator<PackageChangeEvent>() { // from class: android.content.pm.PackageChangeEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageChangeEvent createFromParcel(Parcel parcel) {
            PackageChangeEvent packageChangeEvent = new PackageChangeEvent();
            packageChangeEvent.readFromParcel(parcel);
            return packageChangeEvent;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PackageChangeEvent[] newArray(int i) {
            return new PackageChangeEvent[i];
        }
    };
    public boolean dataRemoved;
    public boolean isDeleted;
    public long lastUpdateTimeMillis;
    public boolean newInstalled;
    public String packageName;
    public long version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.packageName);
        parcel.writeLong(this.version);
        parcel.writeLong(this.lastUpdateTimeMillis);
        parcel.writeInt(this.newInstalled ? 1 : 0);
        parcel.writeInt(this.dataRemoved ? 1 : 0);
        parcel.writeInt(this.isDeleted ? 1 : 0);
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
            this.packageName = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.version = parcel.readLong();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.lastUpdateTimeMillis = parcel.readLong();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        boolean z = true;
                        this.newInstalled = parcel.readInt() != 0;
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.dataRemoved = parcel.readInt() != 0;
                            if (parcel.dataPosition() - iDataPosition < i) {
                                if (parcel.readInt() == 0) {
                                    z = false;
                                }
                                this.isDeleted = z;
                                if (parcel.dataPosition() - iDataPosition < i) {
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
