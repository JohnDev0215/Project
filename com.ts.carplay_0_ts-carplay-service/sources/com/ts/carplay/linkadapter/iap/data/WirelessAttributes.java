package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class WirelessAttributes {
    private boolean mValid = false;
    private boolean mAvailable = false;
    private String mBluetoothTransportIdentifier = null;

    public boolean isWirelessValid() {
        return this.mValid;
    }

    public void setWirelessValid(boolean wirelessValid) {
        this.mValid = wirelessValid;
    }

    public boolean isWirelessAvailable() {
        return this.mAvailable;
    }

    public void setWirelessAvailable(boolean wirelessAvailable) {
        this.mAvailable = wirelessAvailable;
    }

    public String getBluetoothTransportIdentifier() {
        return this.mBluetoothTransportIdentifier;
    }

    public void setBluetoothTransportIdentifier(String bluetoothTransportIdentifier) {
        this.mBluetoothTransportIdentifier = bluetoothTransportIdentifier;
    }

    public String toString() {
        return "WirelessAttributes{mValid='" + this.mValid + "', mAvailable='" + this.mAvailable + "', mBluetoothTransportIdentifier='" + this.mBluetoothTransportIdentifier + "'}";
    }
}
