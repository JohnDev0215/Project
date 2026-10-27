package ts.car.service.ipod.data;

/* JADX INFO: loaded from: classes.dex */
public class IpodDevice {
    private String devId;
    private String deviceName;

    public IpodDevice() {
    }

    public IpodDevice(String devId, String deviceName) {
        this.devId = devId;
        this.deviceName = deviceName;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String toString() {
        return "IpodDevice{devId='" + this.devId + "', deviceName='" + this.deviceName + "'}";
    }
}
