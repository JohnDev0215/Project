package android.telephony;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.net.LinkProperties;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.data.ApnSetting;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PreciseDataConnectionState implements Parcelable {
    public static final Parcelable.Creator<PreciseDataConnectionState> CREATOR = new Parcelable.Creator<PreciseDataConnectionState>() { // from class: android.telephony.PreciseDataConnectionState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PreciseDataConnectionState createFromParcel(Parcel parcel) {
            return new PreciseDataConnectionState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PreciseDataConnectionState[] newArray(int i) {
            return new PreciseDataConnectionState[i];
        }
    };
    private static final long GET_DATA_CONNECTION_STATE_R_VERSION = 148535736;
    private String mApn;
    private ApnSetting mApnSetting;
    private int mApnTypes;
    private int mFailCause;
    private LinkProperties mLinkProperties;
    private int mNetworkType;
    private int mState;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public PreciseDataConnectionState(int i, int i2, int i3, String str, LinkProperties linkProperties, int i4) {
        this(i, i2, i3, str, linkProperties, i4, null);
    }

    public PreciseDataConnectionState(int i, int i2, int i3, String str, LinkProperties linkProperties, int i4, ApnSetting apnSetting) {
        this.mState = -1;
        this.mNetworkType = 0;
        this.mFailCause = 0;
        this.mApnTypes = 0;
        this.mApn = "";
        this.mLinkProperties = null;
        this.mApnSetting = null;
        this.mState = i;
        this.mNetworkType = i2;
        this.mApnTypes = i3;
        this.mApn = str;
        this.mLinkProperties = linkProperties;
        this.mFailCause = i4;
        this.mApnSetting = apnSetting;
    }

    public PreciseDataConnectionState() {
        this.mState = -1;
        this.mNetworkType = 0;
        this.mFailCause = 0;
        this.mApnTypes = 0;
        this.mApn = "";
        this.mLinkProperties = null;
        this.mApnSetting = null;
    }

    private PreciseDataConnectionState(Parcel parcel) {
        this.mState = -1;
        this.mNetworkType = 0;
        this.mFailCause = 0;
        this.mApnTypes = 0;
        this.mApn = "";
        this.mLinkProperties = null;
        this.mApnSetting = null;
        this.mState = parcel.readInt();
        this.mNetworkType = parcel.readInt();
        this.mApnTypes = parcel.readInt();
        this.mApn = parcel.readString();
        this.mLinkProperties = (LinkProperties) parcel.readParcelable(null);
        this.mFailCause = parcel.readInt();
        this.mApnSetting = (ApnSetting) parcel.readParcelable(null);
    }

    @SystemApi
    @Deprecated
    public int getDataConnectionState() {
        if (this.mState != 4 || Compatibility.isChangeEnabled(GET_DATA_CONNECTION_STATE_R_VERSION)) {
            return this.mState;
        }
        return 2;
    }

    public int getState() {
        return this.mState;
    }

    @SystemApi
    @Deprecated
    public int getDataConnectionNetworkType() {
        return this.mNetworkType;
    }

    public int getNetworkType() {
        return this.mNetworkType;
    }

    @SystemApi
    @Deprecated
    public int getDataConnectionApnTypeBitMask() {
        return this.mApnTypes;
    }

    @SystemApi
    @Deprecated
    public String getDataConnectionApn() {
        return this.mApn;
    }

    @SystemApi
    @Deprecated
    public LinkProperties getDataConnectionLinkProperties() {
        return this.mLinkProperties;
    }

    public LinkProperties getLinkProperties() {
        return this.mLinkProperties;
    }

    @SystemApi
    @Deprecated
    public int getDataConnectionFailCause() {
        return this.mFailCause;
    }

    public int getLastCauseCode() {
        return this.mFailCause;
    }

    public ApnSetting getApnSetting() {
        return this.mApnSetting;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mState);
        parcel.writeInt(this.mNetworkType);
        parcel.writeInt(this.mApnTypes);
        parcel.writeString(this.mApn);
        parcel.writeParcelable(this.mLinkProperties, i);
        parcel.writeInt(this.mFailCause);
        parcel.writeParcelable(this.mApnSetting, i);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mState), Integer.valueOf(this.mNetworkType), Integer.valueOf(this.mApnTypes), this.mApn, this.mLinkProperties, Integer.valueOf(this.mFailCause), this.mApnSetting);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PreciseDataConnectionState)) {
            return false;
        }
        PreciseDataConnectionState preciseDataConnectionState = (PreciseDataConnectionState) obj;
        return Objects.equals(this.mApn, preciseDataConnectionState.mApn) && this.mApnTypes == preciseDataConnectionState.mApnTypes && this.mFailCause == preciseDataConnectionState.mFailCause && Objects.equals(this.mLinkProperties, preciseDataConnectionState.mLinkProperties) && this.mNetworkType == preciseDataConnectionState.mNetworkType && this.mState == preciseDataConnectionState.mState && Objects.equals(this.mApnSetting, preciseDataConnectionState.mApnSetting);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data Connection state: " + this.mState);
        sb.append(", Network type: " + this.mNetworkType);
        sb.append(", APN types: " + ApnSetting.getApnTypesStringFromBitmask(this.mApnTypes));
        sb.append(", APN: " + this.mApn);
        sb.append(", Link properties: " + this.mLinkProperties);
        sb.append(", Fail cause: " + DataFailCause.toString(this.mFailCause));
        sb.append(", Apn Setting: " + this.mApnSetting);
        return sb.toString();
    }
}
