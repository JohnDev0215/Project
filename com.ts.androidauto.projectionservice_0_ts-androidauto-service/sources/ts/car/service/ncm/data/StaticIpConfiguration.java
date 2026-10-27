package ts.car.service.ncm.data;

import android.net.LinkAddress;
import java.net.InetAddress;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class StaticIpConfiguration {
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

    public void setIpAddress(LinkAddress ipaddress) {
        this.ipAddress = ipaddress;
    }

    public void setGateway(InetAddress gateway) {
        this.gateway = gateway;
    }

    public void setDnsServers(ArrayList<InetAddress> dnsServers) {
        this.dnsServers.addAll(dnsServers);
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public void clear() {
        this.ipAddress = null;
        this.gateway = null;
        this.dnsServers.clear();
        this.domains = null;
    }
}
