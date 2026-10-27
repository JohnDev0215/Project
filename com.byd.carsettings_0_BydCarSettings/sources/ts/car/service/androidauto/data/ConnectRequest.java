package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes3.dex */
public final class ConnectRequest {
    private int connectType;
    private boolean forceProjection;
    private boolean isUserRequest;
    private String mDeviceId;

    public int getConnectType() {
        return this.connectType;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public boolean isForceProjection() {
        return this.forceProjection;
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

    public void setForceProjection(boolean z) {
        this.forceProjection = z;
    }

    public void setUserRequest(boolean z) {
        this.isUserRequest = z;
    }
}
