package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ResultEntryParcel implements Parcelable {
    public static final Parcelable.Creator<ResultEntryParcel> CREATOR = new Parcelable.Creator<ResultEntryParcel>() { // from class: android.security.identity.ResultEntryParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultEntryParcel createFromParcel(Parcel parcel) {
            ResultEntryParcel resultEntryParcel = new ResultEntryParcel();
            resultEntryParcel.readFromParcel(parcel);
            return resultEntryParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultEntryParcel[] newArray(int i) {
            return new ResultEntryParcel[i];
        }
    };
    public String name;
    public int status;
    public byte[] value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.status);
        parcel.writeString(this.name);
        parcel.writeByteArray(this.value);
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
            this.status = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.name = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.value = parcel.createByteArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
