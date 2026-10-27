package android.media;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaResourceParcel implements Parcelable {
    public static final Parcelable.Creator<MediaResourceParcel> CREATOR = new Parcelable.Creator<MediaResourceParcel>() { // from class: android.media.MediaResourceParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaResourceParcel createFromParcel(Parcel parcel) {
            MediaResourceParcel mediaResourceParcel = new MediaResourceParcel();
            mediaResourceParcel.readFromParcel(parcel);
            return mediaResourceParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaResourceParcel[] newArray(int i) {
            return new MediaResourceParcel[i];
        }
    };
    public byte[] id;
    public int subType;
    public int type;
    public long value = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.type);
        parcel.writeInt(this.subType);
        parcel.writeByteArray(this.id);
        parcel.writeLong(this.value);
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
            this.type = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.subType = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.id = parcel.createByteArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.value = parcel.readLong();
                        if (parcel.dataPosition() - iDataPosition < i) {
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
