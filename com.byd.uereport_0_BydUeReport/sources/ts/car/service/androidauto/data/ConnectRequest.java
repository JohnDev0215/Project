package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
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

    public void setConnectType(int i8) {
        this.connectType = i8;
    }

    public void setDeviceId(String str) {
        this.mDeviceId = str;
    }

    public void setForceProjection(boolean z7) {
        this.forceProjection = z7;
    }

    public void setUserRequest(boolean z7) {
        this.isUserRequest = z7;
    }
}
