package ts.car.service.dcm.data;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionLinkParameter {
    private static final int ALWAYS_POS = 2;
    private static final int AUTOCONN_POS = 1;
    private static final int FIRST_POS = 3;
    private static final int FORCEPROJECTION_POS = 5;
    private static final int FROMUSER_POS = 0;
    private static final int ISCHECKCAPABILITY_POS = 4;
    private int mCapability;
    private String mDevId;
    private boolean mFromUser = false;
    private boolean mAutoConnectDevice = false;
    private boolean mAlways = false;
    private boolean mFirst = false;
    private boolean mIsCapabilityChecked = false;
    private boolean mForceProjection = false;

    public ProjectionLinkParameter(String devId, int capa) {
        this.mDevId = devId;
        this.mCapability = capa;
    }

    public String getDevId() {
        return this.mDevId;
    }

    public void setDevId(String devId) {
        this.mDevId = devId;
    }

    public int getCapability() {
        return this.mCapability;
    }

    public void setCapability(int capa) {
        this.mCapability = capa;
    }

    public boolean getFromUser() {
        return this.mFromUser;
    }

    public void setFromUser(boolean fromUser) {
        this.mFromUser = fromUser;
    }

    public boolean getAutoConnectDevice() {
        return this.mAutoConnectDevice;
    }

    public void setAutoConnectDevice(boolean auto) {
        this.mAutoConnectDevice = auto;
    }

    public boolean getAlways() {
        return this.mAlways;
    }

    public void setAlways(boolean always) {
        this.mAlways = always;
    }

    public boolean getFirst() {
        return this.mFirst;
    }

    public void setFirst(boolean first) {
        this.mFirst = first;
    }

    public boolean isCapabilityAlreadyChecked() {
        return this.mIsCapabilityChecked;
    }

    public void setCapabilityAlreadyChecked(boolean isCapabilityChecked) {
        this.mIsCapabilityChecked = isCapabilityChecked;
    }

    public boolean getForceProjection() {
        return this.mForceProjection;
    }

    public void setForceProjection(boolean forceProjection) {
        this.mForceProjection = forceProjection;
    }

    public String toString() {
        return "ProjectionLinkParamter {mDevId='" + LogEncrypt.IpAddressEncrypt(this.mDevId) + "', mCapability='" + this.mCapability + "', mFromUser=" + this.mFromUser + ", mFirst=" + this.mFirst + ", mAutoConnectDevice=" + this.mAutoConnectDevice + ", mAlways=" + this.mAlways + ", mIsCapabilityChecked=" + this.mIsCapabilityChecked + ", mForceProjection=" + this.mForceProjection + '}';
    }
}
