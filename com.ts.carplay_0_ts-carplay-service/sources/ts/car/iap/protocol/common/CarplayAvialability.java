package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class CarplayAvialability {
    private String mBluetoothTransportIdentifier;
    private boolean mBluetoothTransportIdentifierValid;
    private String mUsbTransportIdentifier;
    private boolean mUsbTransportIdentifierValid;
    private boolean mWiredAvailable;
    private boolean mWiredValid;
    private boolean mWirelessAvailable;
    private boolean mWirelessValid;

    public boolean isWiredValid() {
        return this.mWiredValid;
    }

    public void setWiredValid(boolean wiredValid) {
        this.mWiredValid = wiredValid;
    }

    public boolean isWiredAvailable() {
        return this.mWiredAvailable;
    }

    public void setWiredAvailable(boolean wiredAvailable) {
        this.mWiredAvailable = wiredAvailable;
    }

    public boolean isUsbTransportIdentifierValid() {
        return this.mUsbTransportIdentifierValid;
    }

    public void setUsbTransportIdentifierValid(boolean usbTransportIdentifierValid) {
        this.mUsbTransportIdentifierValid = usbTransportIdentifierValid;
    }

    public String getUsbTransportIdentifier() {
        return this.mUsbTransportIdentifier;
    }

    public void setUsbTransportIdentifier(String usbTransportIdentifier) {
        this.mUsbTransportIdentifier = usbTransportIdentifier;
        this.mUsbTransportIdentifierValid = true;
    }

    public boolean isWirelessValid() {
        return this.mWirelessValid;
    }

    public void setWirelessValid(boolean wirelessValid) {
        this.mWirelessValid = wirelessValid;
    }

    public boolean isWirelessAvailable() {
        return this.mWirelessAvailable;
    }

    public void setWirelessAvailable(boolean wirelessAvailable) {
        this.mWirelessAvailable = wirelessAvailable;
    }

    public boolean isBluetoothTransportIdentifierValid() {
        return this.mBluetoothTransportIdentifierValid;
    }

    public void setBluetoothTransportIdentifierValid(boolean bluetoothTransportIdentifierValid) {
        this.mBluetoothTransportIdentifierValid = bluetoothTransportIdentifierValid;
    }

    public String getBluetoothTransportIdentifier() {
        return this.mBluetoothTransportIdentifier;
    }

    public void setBluetoothTransportIdentifier(String bluetoothTransportIdentifier) {
        this.mBluetoothTransportIdentifier = bluetoothTransportIdentifier;
        this.mBluetoothTransportIdentifierValid = true;
    }
}
