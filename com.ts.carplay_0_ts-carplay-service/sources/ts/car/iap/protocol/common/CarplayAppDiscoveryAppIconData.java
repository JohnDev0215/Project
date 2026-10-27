package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CarplayAppDiscoveryAppIconData {
    private String appBundleID;
    private boolean appBundleIDValid;
    private byte[] iconData;
    private int iconSize;
    private int transportID;

    public void setIconSize(int size) {
        this.iconSize = size;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public void setIconData(byte[] data) {
        this.iconData = data;
    }

    public byte[] getIconData() {
        return this.iconData;
    }

    public void setTransportID(int id) {
        this.transportID = id;
    }

    public int getTransportID() {
        return this.transportID;
    }

    public boolean isAppBundleIDValid() {
        return this.appBundleIDValid;
    }

    public void setAppBundleID(String id) {
        this.appBundleID = id;
        this.appBundleIDValid = true;
    }

    public String getAppBundleID() {
        return this.appBundleID;
    }
}
