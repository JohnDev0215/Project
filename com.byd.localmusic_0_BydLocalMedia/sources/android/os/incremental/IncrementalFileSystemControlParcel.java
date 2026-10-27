package android.os.incremental;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class IncrementalFileSystemControlParcel implements Parcelable {
    public static final Parcelable.Creator<IncrementalFileSystemControlParcel> CREATOR = new Parcelable.Creator<IncrementalFileSystemControlParcel>() { // from class: android.os.incremental.IncrementalFileSystemControlParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IncrementalFileSystemControlParcel createFromParcel(Parcel parcel) {
            IncrementalFileSystemControlParcel incrementalFileSystemControlParcel = new IncrementalFileSystemControlParcel();
            incrementalFileSystemControlParcel.readFromParcel(parcel);
            return incrementalFileSystemControlParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IncrementalFileSystemControlParcel[] newArray(int i) {
            return new IncrementalFileSystemControlParcel[i];
        }
    };
    public ParcelFileDescriptor cmd;
    public ParcelFileDescriptor log;
    public ParcelFileDescriptor pendingReads;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        if (this.cmd != null) {
            parcel.writeInt(1);
            this.cmd.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.pendingReads != null) {
            parcel.writeInt(1);
            this.pendingReads.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.log != null) {
            parcel.writeInt(1);
            this.log.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
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
            if (parcel.readInt() != 0) {
                this.cmd = ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
            } else {
                this.cmd = null;
            }
            if (parcel.dataPosition() - iDataPosition < i) {
                if (parcel.readInt() != 0) {
                    this.pendingReads = ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                } else {
                    this.pendingReads = null;
                }
                if (parcel.dataPosition() - iDataPosition < i) {
                    if (parcel.readInt() != 0) {
                        this.log = ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    } else {
                        this.log = null;
                    }
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
