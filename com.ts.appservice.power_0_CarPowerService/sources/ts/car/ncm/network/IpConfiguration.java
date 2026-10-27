package ts.car.ncm.network;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class IpConfiguration implements Parcelable {
    public static final Parcelable.Creator<IpConfiguration> CREATOR = new Parcelable.Creator<IpConfiguration>() { // from class: ts.car.ncm.network.IpConfiguration.1
        @Override // android.os.Parcelable.Creator
        public IpConfiguration createFromParcel(Parcel in) {
            IpConfiguration config = new IpConfiguration();
            config.ipAssignment = IpAssignment.valueOf(in.readString());
            config.staticIpConfiguration = (StaticIpConfiguration) in.readParcelable(StaticIpConfiguration.class.getClassLoader());
            return config;
        }

        @Override // android.os.Parcelable.Creator
        public IpConfiguration[] newArray(int size) {
            return new IpConfiguration[size];
        }
    };
    private static final int HASH_CONSTANT_1 = 13;
    private static final int HASH_CONSTANT_2 = 17;
    private static final String TAG = "IpConfiguration";
    public IpAssignment ipAssignment;
    public StaticIpConfiguration staticIpConfiguration;

    public enum IpAssignment {
        STATIC,
        DHCP,
        UNASSIGNED
    }

    public enum ProxySettings {
        NONE,
        STATIC,
        UNASSIGNED,
        PAC
    }

    private void init(IpAssignment ipAssignment, StaticIpConfiguration staticIpConfiguration) {
        this.ipAssignment = ipAssignment;
        this.staticIpConfiguration = staticIpConfiguration == null ? null : new StaticIpConfiguration(staticIpConfiguration);
    }

    public IpConfiguration() {
        init(IpAssignment.UNASSIGNED, null);
    }

    public IpConfiguration(IpAssignment ipAssignment, StaticIpConfiguration staticIpConfiguration) {
        init(ipAssignment, staticIpConfiguration);
    }

    public IpConfiguration(IpConfiguration source) {
        this();
        if (source != null) {
            init(source.ipAssignment, source.staticIpConfiguration);
        }
    }

    public IpAssignment getIpAssignment() {
        return this.ipAssignment;
    }

    public void setIpAssignment(IpAssignment ipAssignment) {
        this.ipAssignment = ipAssignment;
    }

    public StaticIpConfiguration getStaticIpConfiguration() {
        return this.staticIpConfiguration;
    }

    public void setStaticIpConfiguration(StaticIpConfiguration staticIpConfiguration) {
        this.staticIpConfiguration = staticIpConfiguration;
    }

    public String toString() {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("IP assignment: " + this.ipAssignment.toString());
        sbuf.append("\n");
        if (this.staticIpConfiguration != null) {
            sbuf.append("Static configuration: " + this.staticIpConfiguration.toString());
            sbuf.append("\n");
        }
        return sbuf.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof IpConfiguration)) {
            return false;
        }
        IpConfiguration other = (IpConfiguration) o;
        return this.ipAssignment == other.ipAssignment && Objects.equals(this.staticIpConfiguration, other.staticIpConfiguration);
    }

    public int hashCode() {
        StaticIpConfiguration staticIpConfiguration = this.staticIpConfiguration;
        return (staticIpConfiguration != null ? staticIpConfiguration.hashCode() : 0) + 13 + (this.ipAssignment.ordinal() * 17);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ipAssignment.name());
        dest.writeParcelable(this.staticIpConfiguration, flags);
    }
}
