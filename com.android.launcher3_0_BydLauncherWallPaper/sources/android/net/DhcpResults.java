package android.net;

import android.net.StaticIpConfiguration;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.android.net.module.util.InetAddressUtils;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class DhcpResults implements Parcelable {
    public static final Parcelable.Creator<DhcpResults> CREATOR = new Parcelable.Creator<DhcpResults>() { // from class: android.net.DhcpResults.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResults createFromParcel(Parcel parcel) {
            return DhcpResults.readFromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResults[] newArray(int i) {
            return new DhcpResults[i];
        }
    };
    private static final String TAG = "DhcpResults";
    public String captivePortalApiUrl;
    public final ArrayList<InetAddress> dnsServers;
    public String domains;
    public InetAddress gateway;
    public LinkAddress ipAddress;
    public int leaseDuration;
    public int mtu;
    public Inet4Address serverAddress;
    public String serverHostName;
    public String vendorInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DhcpResults() {
        this.dnsServers = new ArrayList<>();
    }

    public StaticIpConfiguration toStaticIpConfiguration() {
        return new StaticIpConfiguration.Builder().setIpAddress(this.ipAddress).setGateway(this.gateway).setDnsServers(this.dnsServers).setDomains(this.domains).build();
    }

    public DhcpResults(StaticIpConfiguration staticIpConfiguration) {
        ArrayList<InetAddress> arrayList = new ArrayList<>();
        this.dnsServers = arrayList;
        if (staticIpConfiguration != null) {
            this.ipAddress = staticIpConfiguration.getIpAddress();
            this.gateway = staticIpConfiguration.getGateway();
            arrayList.addAll(staticIpConfiguration.getDnsServers());
            this.domains = staticIpConfiguration.getDomains();
        }
    }

    public DhcpResults(DhcpResults dhcpResults) {
        this(dhcpResults == null ? null : dhcpResults.toStaticIpConfiguration());
        if (dhcpResults != null) {
            this.serverAddress = dhcpResults.serverAddress;
            this.vendorInfo = dhcpResults.vendorInfo;
            this.leaseDuration = dhcpResults.leaseDuration;
            this.mtu = dhcpResults.mtu;
            this.serverHostName = dhcpResults.serverHostName;
            this.captivePortalApiUrl = dhcpResults.captivePortalApiUrl;
        }
    }

    public List<RouteInfo> getRoutes(String str) {
        return toStaticIpConfiguration().getRoutes(str);
    }

    public boolean hasMeteredHint() {
        String str = this.vendorInfo;
        if (str != null) {
            return str.contains("ANDROID_METERED");
        }
        return false;
    }

    public void clear() {
        this.ipAddress = null;
        this.gateway = null;
        this.dnsServers.clear();
        this.domains = null;
        this.serverAddress = null;
        this.vendorInfo = null;
        this.leaseDuration = 0;
        this.mtu = 0;
        this.serverHostName = null;
        this.captivePortalApiUrl = null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(super.toString());
        stringBuffer.append(" DHCP server ").append(this.serverAddress);
        stringBuffer.append(" Vendor info ").append(this.vendorInfo);
        stringBuffer.append(" lease ").append(this.leaseDuration).append(" seconds");
        if (this.mtu != 0) {
            stringBuffer.append(" MTU ").append(this.mtu);
        }
        stringBuffer.append(" Servername ").append(this.serverHostName);
        if (this.captivePortalApiUrl != null) {
            stringBuffer.append(" CaptivePortalApiUrl ").append(this.captivePortalApiUrl);
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DhcpResults)) {
            return false;
        }
        DhcpResults dhcpResults = (DhcpResults) obj;
        return toStaticIpConfiguration().equals(dhcpResults.toStaticIpConfiguration()) && Objects.equals(this.serverAddress, dhcpResults.serverAddress) && Objects.equals(this.vendorInfo, dhcpResults.vendorInfo) && Objects.equals(this.serverHostName, dhcpResults.serverHostName) && this.leaseDuration == dhcpResults.leaseDuration && this.mtu == dhcpResults.mtu && Objects.equals(this.captivePortalApiUrl, dhcpResults.captivePortalApiUrl);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        toStaticIpConfiguration().writeToParcel(parcel, i);
        parcel.writeInt(this.leaseDuration);
        parcel.writeInt(this.mtu);
        InetAddressUtils.parcelInetAddress(parcel, this.serverAddress, i);
        parcel.writeString(this.vendorInfo);
        parcel.writeString(this.serverHostName);
        parcel.writeString(this.captivePortalApiUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DhcpResults readFromParcel(Parcel parcel) {
        DhcpResults dhcpResults = new DhcpResults(StaticIpConfiguration.CREATOR.createFromParcel(parcel));
        dhcpResults.leaseDuration = parcel.readInt();
        dhcpResults.mtu = parcel.readInt();
        dhcpResults.serverAddress = (Inet4Address) InetAddressUtils.unparcelInetAddress(parcel);
        dhcpResults.vendorInfo = parcel.readString();
        dhcpResults.serverHostName = parcel.readString();
        dhcpResults.captivePortalApiUrl = parcel.readString();
        return dhcpResults;
    }

    public boolean setIpAddress(String str, int i) {
        try {
            this.ipAddress = new LinkAddress((Inet4Address) InetAddresses.parseNumericAddress(str), i);
            return false;
        } catch (ClassCastException | IllegalArgumentException unused) {
            Log.e(TAG, "setIpAddress failed with addrString " + str + "/" + i);
            return true;
        }
    }

    public boolean setGateway(String str) {
        try {
            this.gateway = InetAddresses.parseNumericAddress(str);
            return false;
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "setGateway failed with addrString " + str);
            return true;
        }
    }

    public boolean addDns(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            this.dnsServers.add(InetAddresses.parseNumericAddress(str));
            return false;
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "addDns failed with addrString " + str);
            return true;
        }
    }

    public LinkAddress getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(LinkAddress linkAddress) {
        this.ipAddress = linkAddress;
    }

    public InetAddress getGateway() {
        return this.gateway;
    }

    public void setGateway(InetAddress inetAddress) {
        this.gateway = inetAddress;
    }

    public List<InetAddress> getDnsServers() {
        return this.dnsServers;
    }

    public void addDnsServer(InetAddress inetAddress) {
        this.dnsServers.add(inetAddress);
    }

    public String getDomains() {
        return this.domains;
    }

    public void setDomains(String str) {
        this.domains = str;
    }

    public Inet4Address getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(Inet4Address inet4Address) {
        this.serverAddress = inet4Address;
    }

    public int getLeaseDuration() {
        return this.leaseDuration;
    }

    public void setLeaseDuration(int i) {
        this.leaseDuration = i;
    }

    public String getVendorInfo() {
        return this.vendorInfo;
    }

    public void setVendorInfo(String str) {
        this.vendorInfo = str;
    }

    public int getMtu() {
        return this.mtu;
    }

    public void setMtu(int i) {
        this.mtu = i;
    }

    public String getCaptivePortalApiUrl() {
        return this.captivePortalApiUrl;
    }

    public void setCaptivePortalApiUrl(String str) {
        this.captivePortalApiUrl = str;
    }
}
