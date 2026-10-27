package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothCardEntry implements Parcelable {
    public static final Parcelable.Creator<BluetoothCardEntry> CREATOR = new Parcelable.Creator<BluetoothCardEntry>() { // from class: android.bluetooth.BluetoothCardEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothCardEntry createFromParcel(Parcel parcel) {
            return new BluetoothCardEntry(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BluetoothCardEntry[] newArray(int i) {
            return new BluetoothCardEntry[i];
        }
    };
    private long mDateTime;
    private String mDisplayName;
    private String mNumber;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public void setNumber(String str) {
        this.mNumber = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public long getDateTime() {
        return this.mDateTime;
    }

    public void setDateTime(long j) {
        this.mDateTime = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDisplayName);
        parcel.writeString(this.mNumber);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mDateTime);
    }

    public void readFromParcel(Parcel parcel) {
        this.mDisplayName = parcel.readString();
        this.mNumber = parcel.readString();
        this.mType = parcel.readInt();
        this.mDateTime = parcel.readLong();
    }

    public BluetoothCardEntry() {
    }

    protected BluetoothCardEntry(String str, String str2, int i, long j) {
        this.mDisplayName = str;
        this.mNumber = str2;
        this.mType = i;
        this.mDateTime = j;
    }
}
