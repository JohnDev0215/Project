package ts.car.dcm.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import ts.car.dcm.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class ProjectionLinkParameter implements Parcelable {
    private static final int ALWAYS_POS = 2;
    private static final int AUTOCONN_POS = 1;
    public static final Parcelable.Creator<ProjectionLinkParameter> CREATOR = new Parcelable.Creator<ProjectionLinkParameter>() { // from class: ts.car.dcm.common.data.ProjectionLinkParameter.1
        @Override // android.os.Parcelable.Creator
        public ProjectionLinkParameter createFromParcel(Parcel in) {
            return new ProjectionLinkParameter(in);
        }

        @Override // android.os.Parcelable.Creator
        public ProjectionLinkParameter[] newArray(int size) {
            return new ProjectionLinkParameter[size];
        }
    };
    private static final int FIRST_POS = 3;
    private static final int FORCEPROJECTION_POS = 5;
    private static final int FROMUSER_POS = 0;
    private static final int ISCHECKCAPABILITY_POS = 4;
    private boolean mAlways;
    private boolean mAutoConnectDevice;
    private int mCapability;
    private String mDevId;
    private boolean mFirst;
    private boolean mForceProjection;
    private boolean mFromUser;
    private boolean mIsCapabilityChecked;

    private ProjectionLinkParameter(Parcel in) {
        this.mDevId = in.readString();
        this.mCapability = in.readInt();
        boolean[] bools = in.createBooleanArray();
        this.mFromUser = bools[0];
        this.mAutoConnectDevice = bools[1];
        this.mAlways = bools[2];
        this.mFirst = bools[3];
        this.mIsCapabilityChecked = bools[4];
        this.mForceProjection = bools[5];
    }

    public ProjectionLinkParameter(String devId, int capa) {
        this.mDevId = devId;
        this.mCapability = capa;
        this.mFromUser = false;
        this.mAutoConnectDevice = false;
        this.mAlways = false;
        this.mFirst = false;
        this.mIsCapabilityChecked = false;
        this.mForceProjection = false;
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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDevId);
        dest.writeInt(this.mCapability);
        dest.writeBooleanArray(new boolean[]{this.mFromUser, this.mAutoConnectDevice, this.mAlways, this.mFirst, this.mIsCapabilityChecked, this.mForceProjection});
    }

    public String toString() {
        return "ProjectionLinkParamter {mDevId='" + LogEncrypt.MacAddressEncrypt(this.mDevId) + "', mCapability='" + this.mCapability + "', mFromUser=" + this.mFromUser + ", mFirst=" + this.mFirst + ", mAutoConnectDevice=" + this.mAutoConnectDevice + ", mAlways=" + this.mAlways + ", mIsCapabilityChecked=" + this.mIsCapabilityChecked + ", mForceProjection=" + this.mForceProjection + '}';
    }
}
