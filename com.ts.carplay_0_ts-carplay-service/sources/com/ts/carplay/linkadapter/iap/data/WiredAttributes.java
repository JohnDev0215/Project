package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class WiredAttributes {
    private boolean mValid = false;
    private boolean mAvailable = false;
    private String mUsbTransportIdentifier = null;

    public boolean isWiredValid() {
        return this.mValid;
    }

    public void setWiredValid(boolean wiredValid) {
        this.mValid = wiredValid;
    }

    public boolean isWiredAvailable() {
        return this.mAvailable;
    }

    public void setWiredAvailable(boolean wiredAvailable) {
        this.mAvailable = wiredAvailable;
    }

    public String getUsbTransportIdentifier() {
        return this.mUsbTransportIdentifier;
    }

    public void setUsbTransportIdentifier(String usbTransportIdentifier) {
        if (usbTransportIdentifier == null) {
            this.mUsbTransportIdentifier = usbTransportIdentifier;
        } else {
            this.mUsbTransportIdentifier = usbTransportIdentifier.replace("-", "");
        }
    }

    public String toString() {
        return "WiredAttributes{mValid='" + this.mValid + "', mAvailable='" + this.mAvailable + "', mUsbTransportIdentifier='" + this.mUsbTransportIdentifier + "'}";
    }
}
