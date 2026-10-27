package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CarplayAppDiscoveryAppIcon {
    private boolean bAppBundleIDValid;
    private boolean bAppIconAvailableValid;
    private boolean bAppIconFileTransferIDValid;
    private boolean bAppIconIdentifierValid;
    private String sAppBundleID;
    private int uAppIconAvailable;
    private int uAppIconFileTransferID;
    private String uAppIconIdentifier;

    public boolean isAppBundleIDValid() {
        return this.bAppBundleIDValid;
    }

    public void setAppBundleID(String id) {
        this.sAppBundleID = id;
        this.bAppBundleIDValid = true;
    }

    public String getAppBundleID() {
        return this.sAppBundleID;
    }

    public boolean isAppIconAvailableValid() {
        return this.bAppIconAvailableValid;
    }

    public void setAppIconAvailable(int available) {
        this.uAppIconAvailable = available;
        this.bAppIconAvailableValid = true;
    }

    public int getAppIconAvailable() {
        return this.uAppIconAvailable;
    }

    public boolean isAppIconFileTransferIDValid() {
        return this.bAppIconFileTransferIDValid;
    }

    public void setAppIconFileTransferID(int id) {
        this.uAppIconFileTransferID = id;
        this.bAppIconFileTransferIDValid = true;
    }

    public int getAppIconFileTransferID() {
        return this.uAppIconFileTransferID;
    }

    public boolean isAppIconIdentifierValid() {
        return this.bAppIconIdentifierValid;
    }

    public void setAppIconIdentifier(String id) {
        this.uAppIconIdentifier = id;
        this.bAppIconIdentifierValid = true;
    }

    public String getAppIconIdentifier() {
        return this.uAppIconIdentifier;
    }
}
