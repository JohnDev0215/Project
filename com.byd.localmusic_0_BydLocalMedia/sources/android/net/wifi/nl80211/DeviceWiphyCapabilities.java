package android.net.wifi.nl80211;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class DeviceWiphyCapabilities implements Parcelable {
    public static final Parcelable.Creator<DeviceWiphyCapabilities> CREATOR = new Parcelable.Creator<DeviceWiphyCapabilities>() { // from class: android.net.wifi.nl80211.DeviceWiphyCapabilities.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceWiphyCapabilities createFromParcel(Parcel parcel) {
            DeviceWiphyCapabilities deviceWiphyCapabilities = new DeviceWiphyCapabilities();
            deviceWiphyCapabilities.m80211nSupported = parcel.readBoolean();
            deviceWiphyCapabilities.m80211acSupported = parcel.readBoolean();
            deviceWiphyCapabilities.m80211axSupported = parcel.readBoolean();
            deviceWiphyCapabilities.mChannelWidth160MhzSupported = parcel.readBoolean();
            deviceWiphyCapabilities.mChannelWidth80p80MhzSupported = parcel.readBoolean();
            deviceWiphyCapabilities.mMaxNumberTxSpatialStreams = parcel.readInt();
            deviceWiphyCapabilities.mMaxNumberRxSpatialStreams = parcel.readInt();
            return deviceWiphyCapabilities;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceWiphyCapabilities[] newArray(int i) {
            return new DeviceWiphyCapabilities[i];
        }
    };
    private static final String TAG = "DeviceWiphyCapabilities";
    private boolean m80211nSupported = false;
    private boolean m80211acSupported = false;
    private boolean m80211axSupported = false;
    private boolean mChannelWidth160MhzSupported = false;
    private boolean mChannelWidth80p80MhzSupported = false;
    private int mMaxNumberTxSpatialStreams = 1;
    private int mMaxNumberRxSpatialStreams = 1;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isWifiStandardSupported(int i) {
        if (i == 1) {
            return true;
        }
        if (i == 4) {
            return this.m80211nSupported;
        }
        if (i == 5) {
            return this.m80211acSupported;
        }
        if (i == 6) {
            return this.m80211axSupported;
        }
        Log.e(TAG, "isWifiStandardSupported called with invalid standard: " + i);
        return false;
    }

    public void setWifiStandardSupport(int i, boolean z) {
        if (i == 4) {
            this.m80211nSupported = z;
            return;
        }
        if (i == 5) {
            this.m80211acSupported = z;
            return;
        }
        if (i == 6) {
            this.m80211axSupported = z;
            return;
        }
        Log.e(TAG, "setWifiStandardSupport called with invalid standard: " + i);
    }

    public boolean isChannelWidthSupported(int i) {
        if (i == 0) {
            return true;
        }
        if (i == 1) {
            return this.m80211nSupported || this.m80211acSupported || this.m80211axSupported;
        }
        if (i == 2) {
            return this.m80211acSupported || this.m80211axSupported;
        }
        if (i == 3) {
            return this.mChannelWidth160MhzSupported;
        }
        if (i == 4) {
            return this.mChannelWidth80p80MhzSupported;
        }
        Log.e(TAG, "isChannelWidthSupported called with invalid channel width: " + i);
        return false;
    }

    public void setChannelWidthSupported(int i, boolean z) {
        if (i == 3) {
            this.mChannelWidth160MhzSupported = z;
            return;
        }
        if (i == 4) {
            this.mChannelWidth80p80MhzSupported = z;
            return;
        }
        Log.e(TAG, "setChannelWidthSupported called with Invalid channel width: " + i);
    }

    public int getMaxNumberTxSpatialStreams() {
        return this.mMaxNumberTxSpatialStreams;
    }

    public void setMaxNumberTxSpatialStreams(int i) {
        this.mMaxNumberTxSpatialStreams = i;
    }

    public int getMaxNumberRxSpatialStreams() {
        return this.mMaxNumberRxSpatialStreams;
    }

    public void setMaxNumberRxSpatialStreams(int i) {
        this.mMaxNumberRxSpatialStreams = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceWiphyCapabilities)) {
            return false;
        }
        DeviceWiphyCapabilities deviceWiphyCapabilities = (DeviceWiphyCapabilities) obj;
        return this.m80211nSupported == deviceWiphyCapabilities.m80211nSupported && this.m80211acSupported == deviceWiphyCapabilities.m80211acSupported && this.m80211axSupported == deviceWiphyCapabilities.m80211axSupported && this.mChannelWidth160MhzSupported == deviceWiphyCapabilities.mChannelWidth160MhzSupported && this.mChannelWidth80p80MhzSupported == deviceWiphyCapabilities.mChannelWidth80p80MhzSupported && this.mMaxNumberTxSpatialStreams == deviceWiphyCapabilities.mMaxNumberTxSpatialStreams && this.mMaxNumberRxSpatialStreams == deviceWiphyCapabilities.mMaxNumberRxSpatialStreams;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.m80211nSupported), Boolean.valueOf(this.m80211acSupported), Boolean.valueOf(this.m80211axSupported), Boolean.valueOf(this.mChannelWidth160MhzSupported), Boolean.valueOf(this.mChannelWidth80p80MhzSupported), Integer.valueOf(this.mMaxNumberTxSpatialStreams), Integer.valueOf(this.mMaxNumberRxSpatialStreams));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.m80211nSupported);
        parcel.writeBoolean(this.m80211acSupported);
        parcel.writeBoolean(this.m80211axSupported);
        parcel.writeBoolean(this.mChannelWidth160MhzSupported);
        parcel.writeBoolean(this.mChannelWidth80p80MhzSupported);
        parcel.writeInt(this.mMaxNumberTxSpatialStreams);
        parcel.writeInt(this.mMaxNumberRxSpatialStreams);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("m80211nSupported:");
        sb.append(this.m80211nSupported ? "Yes" : "No");
        sb.append("m80211acSupported:");
        sb.append(this.m80211acSupported ? "Yes" : "No");
        sb.append("m80211axSupported:");
        sb.append(this.m80211axSupported ? "Yes" : "No");
        sb.append("mChannelWidth160MhzSupported: ");
        sb.append(this.mChannelWidth160MhzSupported ? "Yes" : "No");
        sb.append("mChannelWidth80p80MhzSupported: ");
        sb.append(this.mChannelWidth80p80MhzSupported ? "Yes" : "No");
        sb.append("mMaxNumberTxSpatialStreams: ");
        sb.append(this.mMaxNumberTxSpatialStreams);
        sb.append("mMaxNumberRxSpatialStreams: ");
        sb.append(this.mMaxNumberRxSpatialStreams);
        return sb.toString();
    }
}
