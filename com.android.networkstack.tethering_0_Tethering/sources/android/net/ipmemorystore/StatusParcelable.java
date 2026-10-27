package android.net.ipmemorystore;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class StatusParcelable implements Parcelable {
    public static final Parcelable.Creator<StatusParcelable> CREATOR = new Parcelable.Creator<StatusParcelable>() { // from class: android.net.ipmemorystore.StatusParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusParcelable createFromParcel(Parcel parcel) {
            StatusParcelable statusParcelable = new StatusParcelable();
            statusParcelable.readFromParcel(parcel);
            return statusParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StatusParcelable[] newArray(int i) {
            return new StatusParcelable[i];
        }
    };
    public int resultCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.resultCode);
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
            this.resultCode = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition >= i) {
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
