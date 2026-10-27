package ts.car.ncm.network;

import android.net.LinkAddress;
import android.os.Parcel;
import android.os.Parcelable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class StaticIpConfiguration implements Parcelable {
    public static Parcelable.Creator<StaticIpConfiguration> CREATOR = new Parcelable.Creator<StaticIpConfiguration>() { // from class: ts.car.ncm.network.StaticIpConfiguration.1
        @Override // android.os.Parcelable.Creator
        public StaticIpConfiguration createFromParcel(Parcel in) {
            StaticIpConfiguration sip = new StaticIpConfiguration();
            StaticIpConfiguration.readFromParcel(sip, in);
            return sip;
        }

        @Override // android.os.Parcelable.Creator
        public StaticIpConfiguration[] newArray(int size) {
            return new StaticIpConfiguration[size];
        }
    };
    private static final int HASH_CONSTANT_1 = 13;
    private static final int HASH_CONSTANT_2 = 47;
    public final ArrayList<InetAddress> dnsServers;
    public String domains;
    public InetAddress gateway;
    public LinkAddress ipAddress;

    public StaticIpConfiguration() {
        this.dnsServers = new ArrayList<>();
    }

    public StaticIpConfiguration(StaticIpConfiguration source) {
        this();
        if (source != null) {
            this.ipAddress = source.ipAddress;
            this.gateway = source.gateway;
            this.dnsServers.addAll(source.dnsServers);
            this.domains = source.domains;
        }
    }

    public LinkAddress getIpAddress() {
        return this.ipAddress;
    }

    public InetAddress getGateway() {
        return this.gateway;
    }

    public ArrayList<InetAddress> getDnsServers() {
        return this.dnsServers;
    }

    public String getDomains() {
        return this.domains;
    }

    public void clear() {
        this.ipAddress = null;
        this.gateway = null;
        this.dnsServers.clear();
        this.domains = null;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("IP address ");
        LinkAddress linkAddress = this.ipAddress;
        if (linkAddress != null) {
            str.append(linkAddress);
            str.append(" ");
        }
        str.append("Gateway ");
        InetAddress inetAddress = this.gateway;
        if (inetAddress != null) {
            str.append(inetAddress.getHostAddress());
            str.append(" ");
        }
        str.append(" DNS servers: [");
        for (InetAddress dnsServer : this.dnsServers) {
            str.append(" ");
            str.append(dnsServer.getHostAddress());
        }
        str.append(" ] Domains ");
        String str2 = this.domains;
        if (str2 != null) {
            str.append(str2);
        }
        return str.toString();
    }

    public int hashCode() {
        int i = 13 * 47;
        LinkAddress linkAddress = this.ipAddress;
        int result = i + (linkAddress == null ? 0 : linkAddress.hashCode());
        int result2 = result * 47;
        InetAddress inetAddress = this.gateway;
        int result3 = (result2 + (inetAddress == null ? 0 : inetAddress.hashCode())) * 47;
        String str = this.domains;
        int result4 = (result3 + (str == null ? 0 : str.hashCode())) * 47;
        ArrayList<InetAddress> arrayList = this.dnsServers;
        return result4 + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticIpConfiguration)) {
            return false;
        }
        StaticIpConfiguration other = (StaticIpConfiguration) obj;
        return Objects.equals(this.ipAddress, other.ipAddress) && Objects.equals(this.gateway, other.gateway) && this.dnsServers.equals(other.dnsServers) && Objects.equals(this.domains, other.domains);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.ipAddress, flags);
        parcelInetAddress(dest, this.gateway, flags);
        dest.writeInt(this.dnsServers.size());
        for (InetAddress dnsServer : this.dnsServers) {
            parcelInetAddress(dest, dnsServer, flags);
        }
        dest.writeString(this.domains);
    }

    public static void readFromParcel(StaticIpConfiguration sip, Parcel in) {
        sip.ipAddress = (LinkAddress) in.readParcelable(LinkAddress.class.getClassLoader());
        sip.gateway = unparcelInetAddress(in);
        sip.dnsServers.clear();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            sip.dnsServers.add(unparcelInetAddress(in));
        }
        sip.domains = in.readString();
    }

    protected static void parcelInetAddress(Parcel parcel, InetAddress address, int flags) {
        byte[] addressArray = address != null ? address.getAddress() : null;
        parcel.writeByteArray(addressArray);
    }

    protected static InetAddress unparcelInetAddress(Parcel in) {
        byte[] addressArray = in.createByteArray();
        if (addressArray == null) {
            return null;
        }
        try {
            return InetAddress.getByAddress(addressArray);
        } catch (UnknownHostException e) {
            return null;
        }
    }
}
