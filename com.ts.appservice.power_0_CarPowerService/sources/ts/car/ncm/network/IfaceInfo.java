package ts.car.ncm.network;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class IfaceInfo implements Parcelable {
    public static final Parcelable.Creator<IfaceInfo> CREATOR = new Parcelable.Creator<IfaceInfo>() { // from class: ts.car.ncm.network.IfaceInfo.1
        @Override // android.os.Parcelable.Creator
        public IfaceInfo createFromParcel(Parcel in) {
            return new IfaceInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public IfaceInfo[] newArray(int size) {
            return new IfaceInfo[size];
        }
    };
    private static final int HASH_CONSTANT_1 = 13;
    private static final int HASH_CONSTANT_2 = 47;
    public String ifaceName = "";
    public int ifaceType = -1;
    public IfaceState ifaceState = IfaceState.UNKNOWN;
    public IpConfiguration ipConfig = new IpConfiguration();

    public enum IfaceState {
        UNKNOWN,
        UP,
        DOWN
    }

    public IfaceInfo() {
    }

    public IfaceInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setIfaceName(String ifaceName) {
        this.ifaceName = ifaceName;
    }

    public void setIfaceType(int ifaceType) {
        this.ifaceType = ifaceType;
    }

    public void setIfaceState(IfaceState ifaceState) {
        this.ifaceState = ifaceState;
    }

    public void setIpConfig(IpConfiguration ipConfig) {
        this.ipConfig = ipConfig;
    }

    public String getIfaceName() {
        return this.ifaceName;
    }

    public int getIfaceType() {
        return this.ifaceType;
    }

    public IfaceState getIfaceState() {
        return this.ifaceState;
    }

    public IpConfiguration getIpConfig() {
        return this.ipConfig;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ifaceName);
        dest.writeInt(this.ifaceType);
        dest.writeString(this.ifaceState.name());
        dest.writeParcelable(this.ipConfig, flags);
    }

    public void readFromParcel(Parcel in) {
        this.ifaceName = in.readString();
        this.ifaceType = in.readInt();
        this.ifaceState = IfaceState.valueOf(in.readString());
        this.ipConfig = (IpConfiguration) in.readParcelable(IpConfiguration.class.getClassLoader());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IfaceInfo)) {
            return false;
        }
        IfaceInfo other = (IfaceInfo) obj;
        return this.ifaceName.equals(other.ifaceName) && this.ifaceType == other.ifaceType && this.ifaceState == other.ifaceState && Objects.equals(this.ipConfig, other.ipConfig);
    }

    public int hashCode() {
        int i = 13 * 47;
        String str = this.ifaceName;
        int result = i + (str == null ? 0 : str.hashCode());
        int result2 = ((result * 47) + this.ifaceType) * 47;
        IfaceState ifaceState = this.ifaceState;
        int result3 = (result2 + (ifaceState == null ? 0 : ifaceState.hashCode())) * 47;
        IpConfiguration ipConfiguration = this.ipConfig;
        return result3 + (ipConfiguration != null ? ipConfiguration.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("ifaceName: " + this.ifaceName + "\n");
        sbuf.append("ifaceType: " + this.ifaceType + "\n");
        sbuf.append("ifaceState: " + this.ifaceState.name() + "\n");
        sbuf.append("ipConfig: " + this.ipConfig + "\n");
        return sbuf.toString();
    }
}
