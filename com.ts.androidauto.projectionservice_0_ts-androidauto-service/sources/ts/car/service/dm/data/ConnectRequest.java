package ts.car.service.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectRequest {
    private int mCapabilityType;
    private int mConnectionType;
    private UnifiedDevice mDevice;

    public UnifiedDevice getDevice() {
        return this.mDevice;
    }

    public void setDevice(UnifiedDevice device) {
        this.mDevice = device;
    }

    public int getCapabilityType() {
        return this.mCapabilityType;
    }

    public void setCapabilityType(int capabilityType) {
        this.mCapabilityType = capabilityType;
    }

    public int getConnectionType() {
        return this.mConnectionType;
    }

    public void setConnectType(int connectionType) {
        this.mConnectionType = connectionType;
    }
}
