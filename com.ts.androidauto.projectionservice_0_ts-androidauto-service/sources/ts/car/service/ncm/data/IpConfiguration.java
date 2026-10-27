package ts.car.service.ncm.data;

/* JADX INFO: loaded from: classes2.dex */
public class IpConfiguration {
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
}
