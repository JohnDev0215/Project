package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class InformationElementParcelable implements Parcelable {
    public static final Parcelable.Creator<InformationElementParcelable> CREATOR = new Parcelable.Creator<InformationElementParcelable>() { // from class: android.net.InformationElementParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InformationElementParcelable createFromParcel(Parcel parcel) {
            InformationElementParcelable informationElementParcelable = new InformationElementParcelable();
            informationElementParcelable.readFromParcel(parcel);
            return informationElementParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InformationElementParcelable[] newArray(int i) {
            return new InformationElementParcelable[i];
        }
    };
    public int id;
    public byte[] payload;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.id);
        parcel.writeByteArray(this.payload);
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
            this.id = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.payload = parcel.createByteArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
