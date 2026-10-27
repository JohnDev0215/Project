package android.net;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class NetworkAgentConfig implements Parcelable {
    public static final Parcelable.Creator<NetworkAgentConfig> CREATOR = new Parcelable.Creator<NetworkAgentConfig>() { // from class: android.net.NetworkAgentConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkAgentConfig createFromParcel(Parcel parcel) {
            NetworkAgentConfig networkAgentConfig = new NetworkAgentConfig();
            networkAgentConfig.allowBypass = parcel.readInt() != 0;
            networkAgentConfig.explicitlySelected = parcel.readInt() != 0;
            networkAgentConfig.acceptUnvalidated = parcel.readInt() != 0;
            networkAgentConfig.acceptPartialConnectivity = parcel.readInt() != 0;
            networkAgentConfig.subscriberId = parcel.readString();
            networkAgentConfig.provisioningNotificationDisabled = parcel.readInt() != 0;
            networkAgentConfig.skip464xlat = parcel.readInt() != 0;
            networkAgentConfig.legacyType = parcel.readInt();
            networkAgentConfig.legacyTypeName = parcel.readString();
            networkAgentConfig.mLegacyExtraInfo = parcel.readString();
            return networkAgentConfig;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkAgentConfig[] newArray(int i) {
            return new NetworkAgentConfig[i];
        }
    };
    public boolean acceptPartialConnectivity;
    public boolean acceptUnvalidated;
    public boolean allowBypass;
    public boolean explicitlySelected;
    public transient boolean hasShownBroken;
    public int legacyType;
    public String legacyTypeName;
    private String mLegacyExtraInfo;
    public boolean provisioningNotificationDisabled;
    public boolean skip464xlat;
    public String subscriberId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isExplicitlySelected() {
        return this.explicitlySelected;
    }

    public boolean isUnvalidatedConnectivityAcceptable() {
        return this.acceptUnvalidated;
    }

    public boolean isPartialConnectivityAcceptable() {
        return this.acceptPartialConnectivity;
    }

    public boolean isProvisioningNotificationEnabled() {
        return !this.provisioningNotificationDisabled;
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public boolean isNat64DetectionEnabled() {
        return !this.skip464xlat;
    }

    public int getLegacyType() {
        return this.legacyType;
    }

    public String getLegacyTypeName() {
        return this.legacyTypeName;
    }

    public String getLegacyExtraInfo() {
        return this.mLegacyExtraInfo;
    }

    public NetworkAgentConfig() {
        this.legacyType = -1;
        this.legacyTypeName = "";
        this.mLegacyExtraInfo = "";
    }

    public NetworkAgentConfig(NetworkAgentConfig networkAgentConfig) {
        this.legacyType = -1;
        this.legacyTypeName = "";
        this.mLegacyExtraInfo = "";
        if (networkAgentConfig != null) {
            this.allowBypass = networkAgentConfig.allowBypass;
            this.explicitlySelected = networkAgentConfig.explicitlySelected;
            this.acceptUnvalidated = networkAgentConfig.acceptUnvalidated;
            this.acceptPartialConnectivity = networkAgentConfig.acceptPartialConnectivity;
            this.subscriberId = networkAgentConfig.subscriberId;
            this.provisioningNotificationDisabled = networkAgentConfig.provisioningNotificationDisabled;
            this.skip464xlat = networkAgentConfig.skip464xlat;
            this.legacyType = networkAgentConfig.legacyType;
            this.legacyTypeName = networkAgentConfig.legacyTypeName;
            this.mLegacyExtraInfo = networkAgentConfig.mLegacyExtraInfo;
        }
    }

    public static final class Builder {
        private final NetworkAgentConfig mConfig = new NetworkAgentConfig();

        public Builder setExplicitlySelected(boolean z) {
            this.mConfig.explicitlySelected = z;
            return this;
        }

        public Builder setUnvalidatedConnectivityAcceptable(boolean z) {
            this.mConfig.acceptUnvalidated = z;
            return this;
        }

        public Builder setPartialConnectivityAcceptable(boolean z) {
            this.mConfig.acceptPartialConnectivity = z;
            return this;
        }

        public Builder setSubscriberId(String str) {
            this.mConfig.subscriberId = str;
            return this;
        }

        public Builder disableNat64Detection() {
            this.mConfig.skip464xlat = true;
            return this;
        }

        public Builder disableProvisioningNotification() {
            this.mConfig.provisioningNotificationDisabled = true;
            return this;
        }

        public Builder setLegacyType(int i) {
            this.mConfig.legacyType = i;
            return this;
        }

        public Builder setLegacyTypeName(String str) {
            this.mConfig.legacyTypeName = str;
            return this;
        }

        public Builder setLegacyExtraInfo(String str) {
            this.mConfig.mLegacyExtraInfo = str;
            return this;
        }

        public NetworkAgentConfig build() {
            return this.mConfig;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NetworkAgentConfig networkAgentConfig = (NetworkAgentConfig) obj;
        return this.allowBypass == networkAgentConfig.allowBypass && this.explicitlySelected == networkAgentConfig.explicitlySelected && this.acceptUnvalidated == networkAgentConfig.acceptUnvalidated && this.acceptPartialConnectivity == networkAgentConfig.acceptPartialConnectivity && this.provisioningNotificationDisabled == networkAgentConfig.provisioningNotificationDisabled && this.skip464xlat == networkAgentConfig.skip464xlat && this.legacyType == networkAgentConfig.legacyType && Objects.equals(this.subscriberId, networkAgentConfig.subscriberId) && Objects.equals(this.legacyTypeName, networkAgentConfig.legacyTypeName) && Objects.equals(this.mLegacyExtraInfo, networkAgentConfig.mLegacyExtraInfo);
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.allowBypass), Boolean.valueOf(this.explicitlySelected), Boolean.valueOf(this.acceptUnvalidated), Boolean.valueOf(this.acceptPartialConnectivity), Boolean.valueOf(this.provisioningNotificationDisabled), this.subscriberId, Boolean.valueOf(this.skip464xlat), Integer.valueOf(this.legacyType), this.legacyTypeName, this.mLegacyExtraInfo);
    }

    public String toString() {
        return "NetworkAgentConfig { allowBypass = " + this.allowBypass + ", explicitlySelected = " + this.explicitlySelected + ", acceptUnvalidated = " + this.acceptUnvalidated + ", acceptPartialConnectivity = " + this.acceptPartialConnectivity + ", provisioningNotificationDisabled = " + this.provisioningNotificationDisabled + ", subscriberId = '" + this.subscriberId + DateFormat.QUOTE + ", skip464xlat = " + this.skip464xlat + ", legacyType = " + this.legacyType + ", hasShownBroken = " + this.hasShownBroken + ", legacyTypeName = '" + this.legacyTypeName + DateFormat.QUOTE + ", legacyExtraInfo = '" + this.mLegacyExtraInfo + DateFormat.QUOTE + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.allowBypass ? 1 : 0);
        parcel.writeInt(this.explicitlySelected ? 1 : 0);
        parcel.writeInt(this.acceptUnvalidated ? 1 : 0);
        parcel.writeInt(this.acceptPartialConnectivity ? 1 : 0);
        parcel.writeString(this.subscriberId);
        parcel.writeInt(this.provisioningNotificationDisabled ? 1 : 0);
        parcel.writeInt(this.skip464xlat ? 1 : 0);
        parcel.writeInt(this.legacyType);
        parcel.writeString(this.legacyTypeName);
        parcel.writeString(this.mLegacyExtraInfo);
    }
}
