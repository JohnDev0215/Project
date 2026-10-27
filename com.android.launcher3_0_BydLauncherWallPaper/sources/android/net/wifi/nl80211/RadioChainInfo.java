package android.net.wifi.nl80211;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class RadioChainInfo implements Parcelable {
    public static final Parcelable.Creator<RadioChainInfo> CREATOR = new Parcelable.Creator<RadioChainInfo>() { // from class: android.net.wifi.nl80211.RadioChainInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioChainInfo createFromParcel(Parcel parcel) {
            return new RadioChainInfo(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RadioChainInfo[] newArray(int i) {
            return new RadioChainInfo[i];
        }
    };
    private static final String TAG = "RadioChainInfo";
    public int chainId;
    public int level;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getChainId() {
        return this.chainId;
    }

    public int getLevelDbm() {
        return this.level;
    }

    public RadioChainInfo(int i, int i2) {
        this.chainId = i;
        this.level = i2;
    }

    public boolean equals(Object obj) {
        RadioChainInfo radioChainInfo;
        if (this == obj) {
            return true;
        }
        if ((obj instanceof RadioChainInfo) && (radioChainInfo = (RadioChainInfo) obj) != null) {
            return this.chainId == radioChainInfo.chainId && this.level == radioChainInfo.level;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.chainId), Integer.valueOf(this.level));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.chainId);
        parcel.writeInt(this.level);
    }
}
