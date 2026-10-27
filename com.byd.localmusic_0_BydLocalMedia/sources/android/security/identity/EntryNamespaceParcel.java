package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class EntryNamespaceParcel implements Parcelable {
    public static final Parcelable.Creator<EntryNamespaceParcel> CREATOR = new Parcelable.Creator<EntryNamespaceParcel>() { // from class: android.security.identity.EntryNamespaceParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntryNamespaceParcel createFromParcel(Parcel parcel) {
            EntryNamespaceParcel entryNamespaceParcel = new EntryNamespaceParcel();
            entryNamespaceParcel.readFromParcel(parcel);
            return entryNamespaceParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EntryNamespaceParcel[] newArray(int i) {
            return new EntryNamespaceParcel[i];
        }
    };
    public EntryParcel[] entries;
    public String namespaceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.namespaceName);
        parcel.writeTypedArray(this.entries, 0);
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
            this.namespaceName = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.entries = (EntryParcel[]) parcel.createTypedArray(EntryParcel.CREATOR);
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
