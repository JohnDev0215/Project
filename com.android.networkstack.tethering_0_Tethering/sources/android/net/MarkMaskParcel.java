package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MarkMaskParcel implements Parcelable {
    public static final Parcelable.Creator<MarkMaskParcel> CREATOR = new Parcelable.Creator<MarkMaskParcel>() { // from class: android.net.MarkMaskParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarkMaskParcel createFromParcel(Parcel parcel) {
            MarkMaskParcel markMaskParcel = new MarkMaskParcel();
            markMaskParcel.readFromParcel(parcel);
            return markMaskParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MarkMaskParcel[] newArray(int i) {
            return new MarkMaskParcel[i];
        }
    };
    public int mark;
    public int mask;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.mark);
        parcel.writeInt(this.mask);
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
            this.mark = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.mask = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
