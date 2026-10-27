package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean forceProjection;
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

    public boolean isForceProjection() {
        return this.forceProjection;
    }

    public void setForceProjection(boolean forceProjection) {
        this.forceProjection = forceProjection;
    }
}
