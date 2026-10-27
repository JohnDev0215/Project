package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class AccessoryWiFiConfiguration {
    private int mChannel;
    private String mPassphrase;
    private int mSecurity;
    private String mSsid;

    public AccessoryWiFiConfiguration(String ssid, String passphrase, int security, int channel) {
        this.mSsid = ssid;
        this.mPassphrase = passphrase;
        this.mSecurity = security;
        this.mChannel = channel;
    }

    public String getSsid() {
        return this.mSsid;
    }

    public void setSsid(String ssid) {
        this.mSsid = ssid;
    }

    public String getPassphrase() {
        return this.mPassphrase;
    }

    public void setPassphrase(String passphrase) {
        this.mPassphrase = passphrase;
    }

    public int getSecurity() {
        return this.mSecurity;
    }

    public void setSecurity(int security) {
        this.mSecurity = security;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public void setChannel(int channel) {
        this.mChannel = channel;
    }
}
