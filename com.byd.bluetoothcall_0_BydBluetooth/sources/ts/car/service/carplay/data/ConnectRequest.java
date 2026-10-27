package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean isUserRequest;
    private String mDeviceId;

    public int getConnectType() {
        return this.connectType;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public boolean isUserRequest() {
        return this.isUserRequest;
    }

    public void setConnectType(int i) {
        this.connectType = i;
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
    }

    public void setUserRequest(boolean z) {
        this.isUserRequest = z;
    }
}
