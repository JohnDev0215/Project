package ts.car.dcm.common.data;

import android.net.wifi.WifiConfiguration;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class WifiConfigInfo implements Parcelable {
    public static final Parcelable.Creator<WifiConfigInfo> CREATOR = new Parcelable.Creator<WifiConfigInfo>() { // from class: ts.car.dcm.common.data.WifiConfigInfo.1
        @Override // android.os.Parcelable.Creator
        public WifiConfigInfo createFromParcel(Parcel source) {
            WifiConfigInfo config = new WifiConfigInfo(source);
            return config;
        }

        @Override // android.os.Parcelable.Creator
        public WifiConfigInfo[] newArray(int size) {
            return new WifiConfigInfo[size];
        }
    };
    private static final String TAG = "WifiConfigInfo";
    WifiConfiguration mWifiConfig;

    public WifiConfigInfo() {
    }

    public WifiConfigInfo(Parcel in) {
        this.mWifiConfig = (WifiConfiguration) in.readParcelable(WifiConfiguration.class.getClassLoader());
    }

    public WifiConfiguration getWifiConfiguration() {
        return this.mWifiConfig;
    }

    public void setWifiConfiguration(WifiConfiguration configuration) {
        this.mWifiConfig = configuration;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mWifiConfig, flags);
    }

    public String toString() {
        return "WifiConfigInfo{mWifiConfig=" + this.mWifiConfig + '}';
    }
}
