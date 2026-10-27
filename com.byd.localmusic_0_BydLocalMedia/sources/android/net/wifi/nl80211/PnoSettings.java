package android.net.wifi.nl80211;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class PnoSettings implements Parcelable {
    public static final Parcelable.Creator<PnoSettings> CREATOR = new Parcelable.Creator<PnoSettings>() { // from class: android.net.wifi.nl80211.PnoSettings.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PnoSettings createFromParcel(Parcel parcel) {
            PnoSettings pnoSettings = new PnoSettings();
            pnoSettings.mIntervalMs = parcel.readLong();
            pnoSettings.mMin2gRssi = parcel.readInt();
            pnoSettings.mMin5gRssi = parcel.readInt();
            pnoSettings.mMin6gRssi = parcel.readInt();
            pnoSettings.mPnoNetworks = new ArrayList();
            parcel.readTypedList(pnoSettings.mPnoNetworks, PnoNetwork.CREATOR);
            return pnoSettings;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PnoSettings[] newArray(int i) {
            return new PnoSettings[i];
        }
    };
    private long mIntervalMs;
    private int mMin2gRssi;
    private int mMin5gRssi;
    private int mMin6gRssi;
    private List<PnoNetwork> mPnoNetworks;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getIntervalMillis() {
        return this.mIntervalMs;
    }

    public void setIntervalMillis(long j) {
        this.mIntervalMs = j;
    }

    public int getMin2gRssiDbm() {
        return this.mMin2gRssi;
    }

    public void setMin2gRssiDbm(int i) {
        this.mMin2gRssi = i;
    }

    public int getMin5gRssiDbm() {
        return this.mMin5gRssi;
    }

    public void setMin5gRssiDbm(int i) {
        this.mMin5gRssi = i;
    }

    public int getMin6gRssiDbm() {
        return this.mMin6gRssi;
    }

    public void setMin6gRssiDbm(int i) {
        this.mMin6gRssi = i;
    }

    public List<PnoNetwork> getPnoNetworks() {
        return this.mPnoNetworks;
    }

    public void setPnoNetworks(List<PnoNetwork> list) {
        this.mPnoNetworks = list;
    }

    public boolean equals(Object obj) {
        PnoSettings pnoSettings;
        if (this == obj) {
            return true;
        }
        if ((obj instanceof PnoSettings) && (pnoSettings = (PnoSettings) obj) != null) {
            return this.mIntervalMs == pnoSettings.mIntervalMs && this.mMin2gRssi == pnoSettings.mMin2gRssi && this.mMin5gRssi == pnoSettings.mMin5gRssi && this.mMin6gRssi == pnoSettings.mMin6gRssi && this.mPnoNetworks.equals(pnoSettings.mPnoNetworks);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mIntervalMs), Integer.valueOf(this.mMin2gRssi), Integer.valueOf(this.mMin5gRssi), Integer.valueOf(this.mMin6gRssi), this.mPnoNetworks);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mIntervalMs);
        parcel.writeInt(this.mMin2gRssi);
        parcel.writeInt(this.mMin5gRssi);
        parcel.writeInt(this.mMin6gRssi);
        parcel.writeTypedList(this.mPnoNetworks);
    }
}
