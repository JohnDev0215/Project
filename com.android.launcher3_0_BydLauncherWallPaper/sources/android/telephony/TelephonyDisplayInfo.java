package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import android.security.keystore.KeyProperties;
import com.android.internal.telephony.DctConstants;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyDisplayInfo implements Parcelable {
    public static final Parcelable.Creator<TelephonyDisplayInfo> CREATOR = new Parcelable.Creator<TelephonyDisplayInfo>() { // from class: android.telephony.TelephonyDisplayInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyDisplayInfo createFromParcel(Parcel parcel) {
            return new TelephonyDisplayInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TelephonyDisplayInfo[] newArray(int i) {
            return new TelephonyDisplayInfo[i];
        }
    };
    public static final int OVERRIDE_NETWORK_TYPE_LTE_ADVANCED_PRO = 2;
    public static final int OVERRIDE_NETWORK_TYPE_LTE_CA = 1;
    public static final int OVERRIDE_NETWORK_TYPE_NONE = 0;
    public static final int OVERRIDE_NETWORK_TYPE_NR_NSA = 3;
    public static final int OVERRIDE_NETWORK_TYPE_NR_NSA_MMWAVE = 4;
    private final int mNetworkType;
    private final int mOverrideNetworkType;

    public static String overrideNetworkTypeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : DctConstants.RAT_NAME_NR_NSA_MMWAVE : DctConstants.RAT_NAME_NR_NSA : "LTE_ADV_PRO" : "LTE_CA" : KeyProperties.DIGEST_NONE;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TelephonyDisplayInfo(int i, int i2) {
        this.mNetworkType = i;
        this.mOverrideNetworkType = i2;
    }

    public TelephonyDisplayInfo(Parcel parcel) {
        this.mNetworkType = parcel.readInt();
        this.mOverrideNetworkType = parcel.readInt();
    }

    public int getNetworkType() {
        return this.mNetworkType;
    }

    public int getOverrideNetworkType() {
        return this.mOverrideNetworkType;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mNetworkType);
        parcel.writeInt(this.mOverrideNetworkType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TelephonyDisplayInfo telephonyDisplayInfo = (TelephonyDisplayInfo) obj;
        return this.mNetworkType == telephonyDisplayInfo.mNetworkType && this.mOverrideNetworkType == telephonyDisplayInfo.mOverrideNetworkType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mNetworkType), Integer.valueOf(this.mOverrideNetworkType));
    }

    public String toString() {
        return "TelephonyDisplayInfo {network=" + TelephonyManager.getNetworkTypeName(this.mNetworkType) + ", override=" + overrideNetworkTypeToString(this.mOverrideNetworkType) + "}";
    }
}
