package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes5.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean forceProjection;
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

    public boolean isForceProjection() {
        return this.forceProjection;
    }

    public void setForceProjection(boolean z) {
        this.forceProjection = z;
    }
}
