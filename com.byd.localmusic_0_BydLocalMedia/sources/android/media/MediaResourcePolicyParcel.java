package android.media;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaResourcePolicyParcel implements Parcelable {
    public static final Parcelable.Creator<MediaResourcePolicyParcel> CREATOR = new Parcelable.Creator<MediaResourcePolicyParcel>() { // from class: android.media.MediaResourcePolicyParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaResourcePolicyParcel createFromParcel(Parcel parcel) {
            MediaResourcePolicyParcel mediaResourcePolicyParcel = new MediaResourcePolicyParcel();
            mediaResourcePolicyParcel.readFromParcel(parcel);
            return mediaResourcePolicyParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaResourcePolicyParcel[] newArray(int i) {
            return new MediaResourcePolicyParcel[i];
        }
    };
    public String type;
    public String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.type);
        parcel.writeString(this.value);
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
            this.type = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.value = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
