package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean isUserRequest;
    private String mDeviceId;

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
    }

    public boolean isUserRequest() {
        return this.isUserRequest;
    }

    public void setUserRequest(boolean z) {
        this.isUserRequest = z;
    }

    public int getConnectType() {
        return this.connectType;
    }

    public void setConnectType(int i) {
        this.connectType = i;
    }
}
