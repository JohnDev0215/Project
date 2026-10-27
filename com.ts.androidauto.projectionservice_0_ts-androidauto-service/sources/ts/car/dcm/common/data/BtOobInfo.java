package ts.car.dcm.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class BtOobInfo implements Parcelable {
    public static final Parcelable.Creator<BtOobInfo> CREATOR = new Parcelable.Creator<BtOobInfo>() { // from class: ts.car.dcm.common.data.BtOobInfo.1
        @Override // android.os.Parcelable.Creator
        public BtOobInfo createFromParcel(Parcel in) {
            return new BtOobInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public BtOobInfo[] newArray(int size) {
            return new BtOobInfo[size];
        }
    };
    private int mDeviceClass;
    private String mMacAddress;
    private String mName;

    private BtOobInfo(Parcel in) {
        this(in.readString(), in.readString(), in.readInt());
    }

    public BtOobInfo(String name, String mac, int deviceClass) {
        this.mName = name;
        this.mMacAddress = mac;
        this.mDeviceClass = deviceClass;
    }

    public String getName() {
        return this.mName;
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public int getDeviceClass() {
        return this.mDeviceClass;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getMacAddress());
        dest.writeInt(getDeviceClass());
    }
}
