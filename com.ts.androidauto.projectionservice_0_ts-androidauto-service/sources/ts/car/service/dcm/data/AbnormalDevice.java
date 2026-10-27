package ts.car.service.dcm.data;

/* JADX INFO: loaded from: classes2.dex */
public class AbnormalDevice extends Device {
    private int mAbnormalType;
    private int mConnType;

    public AbnormalDevice(String deviceId) {
        setDeviceId(deviceId);
    }

    public int getConnType() {
        return this.mConnType;
    }

    public void setConnType(int connType) {
        this.mConnType = connType;
    }

    public int getAbnormalType() {
        return this.mAbnormalType;
    }

    public void setAbnormalType(int abnormalType) {
        this.mAbnormalType = abnormalType;
    }

    @Override // ts.car.service.dcm.data.Device
    public String toString() {
        return "AbnormalDevice{mDevId='" + LogEncrypt.IpAddressEncrypt(getDeviceId()) + "', mConnType=" + this.mConnType + ", mAbnormalType=" + this.mAbnormalType + '}';
    }
}
