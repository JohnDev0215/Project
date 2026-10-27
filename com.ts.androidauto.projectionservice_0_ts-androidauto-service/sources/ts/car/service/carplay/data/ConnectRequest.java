package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean isUserRequest;
    private String mDeviceId;

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public boolean isUserRequest() {
        return this.isUserRequest;
    }

    public void setUserRequest(boolean userRequest) {
        this.isUserRequest = userRequest;
    }

    public int getConnectType() {
        return this.connectType;
    }

    public void setConnectType(int connectType) {
        this.connectType = connectType;
    }
}
