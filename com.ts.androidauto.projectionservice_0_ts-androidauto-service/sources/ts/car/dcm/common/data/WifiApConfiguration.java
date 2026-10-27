package ts.car.dcm.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class WifiApConfiguration implements Parcelable {
    public static final Parcelable.Creator<WifiApConfiguration> CREATOR = new Parcelable.Creator<WifiApConfiguration>() { // from class: ts.car.dcm.common.data.WifiApConfiguration.1
        @Override // android.os.Parcelable.Creator
        public WifiApConfiguration createFromParcel(Parcel in) {
            return new WifiApConfiguration(in);
        }

        @Override // android.os.Parcelable.Creator
        public WifiApConfiguration[] newArray(int size) {
            return new WifiApConfiguration[size];
        }
    };
    private int mApBand;
    private int mApChannel;

    private WifiApConfiguration(Parcel in) {
        this(in.readInt(), in.readInt());
    }

    public WifiApConfiguration(int band, int channel) {
        this.mApBand = band;
        this.mApChannel = channel;
    }

    public int getWifiApBand() {
        return this.mApBand;
    }

    public int getWifiApChannel() {
        return this.mApChannel;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getWifiApBand());
        dest.writeInt(getWifiApChannel());
    }
}
