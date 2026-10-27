package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NetworkTestResultParcelable implements Parcelable {
    public static final Parcelable.Creator<NetworkTestResultParcelable> CREATOR = new Parcelable.Creator<NetworkTestResultParcelable>() { // from class: android.net.NetworkTestResultParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTestResultParcelable createFromParcel(Parcel parcel) {
            NetworkTestResultParcelable networkTestResultParcelable = new NetworkTestResultParcelable();
            networkTestResultParcelable.readFromParcel(parcel);
            return networkTestResultParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkTestResultParcelable[] newArray(int i) {
            return new NetworkTestResultParcelable[i];
        }
    };
    public int probesAttempted;
    public int probesSucceeded;
    public String redirectUrl;
    public int result;
    public long timestampMillis;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeLong(this.timestampMillis);
        parcel.writeInt(this.result);
        parcel.writeInt(this.probesSucceeded);
        parcel.writeInt(this.probesAttempted);
        parcel.writeString(this.redirectUrl);
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
            this.timestampMillis = parcel.readLong();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.result = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.probesSucceeded = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.probesAttempted = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.redirectUrl = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
