package android.net.ipmemorystore;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class SameL3NetworkResponseParcelable implements Parcelable {
    public static final Parcelable.Creator<SameL3NetworkResponseParcelable> CREATOR = new Parcelable.Creator<SameL3NetworkResponseParcelable>() { // from class: android.net.ipmemorystore.SameL3NetworkResponseParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SameL3NetworkResponseParcelable createFromParcel(Parcel parcel) {
            SameL3NetworkResponseParcelable sameL3NetworkResponseParcelable = new SameL3NetworkResponseParcelable();
            sameL3NetworkResponseParcelable.readFromParcel(parcel);
            return sameL3NetworkResponseParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SameL3NetworkResponseParcelable[] newArray(int i) {
            return new SameL3NetworkResponseParcelable[i];
        }
    };
    public float confidence;
    public String l2Key1;
    public String l2Key2;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.l2Key1);
        parcel.writeString(this.l2Key2);
        parcel.writeFloat(this.confidence);
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
            this.l2Key1 = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.l2Key2 = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.confidence = parcel.readFloat();
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
