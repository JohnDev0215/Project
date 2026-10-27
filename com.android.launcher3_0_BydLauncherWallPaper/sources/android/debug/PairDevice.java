package android.debug;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public class PairDevice implements Parcelable {
    public static final Parcelable.Creator<PairDevice> CREATOR = new Parcelable.Creator<PairDevice>() { // from class: android.debug.PairDevice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PairDevice createFromParcel(Parcel parcel) {
            return new PairDevice(parcel.readString(), parcel.readString(), parcel.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PairDevice[] newArray(int i) {
            return new PairDevice[i];
        }
    };
    private final boolean mConnected;
    private final String mGuid;
    private final String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PairDevice(String str, String str2, boolean z) {
        Preconditions.checkStringNotEmpty(str);
        Preconditions.checkStringNotEmpty(str2);
        this.mName = str;
        this.mGuid = str2;
        this.mConnected = z;
    }

    public String getDeviceName() {
        return this.mName;
    }

    public String getGuid() {
        return this.mGuid;
    }

    public boolean isConnected() {
        return this.mConnected;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mGuid);
        parcel.writeBoolean(this.mConnected);
    }

    public String toString() {
        return "\n" + this.mName + "\n" + this.mGuid + "\n" + this.mConnected;
    }
}
