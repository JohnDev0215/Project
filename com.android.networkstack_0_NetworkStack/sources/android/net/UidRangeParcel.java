package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class UidRangeParcel implements Parcelable {
    public static final Parcelable.Creator<UidRangeParcel> CREATOR = new Parcelable.Creator<UidRangeParcel>() { // from class: android.net.UidRangeParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidRangeParcel createFromParcel(Parcel parcel) {
            UidRangeParcel uidRangeParcel = new UidRangeParcel();
            uidRangeParcel.readFromParcel(parcel);
            return uidRangeParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UidRangeParcel[] newArray(int i) {
            return new UidRangeParcel[i];
        }
    };
    public int start;
    public int stop;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.start);
        parcel.writeInt(this.stop);
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
            this.start = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.stop = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
