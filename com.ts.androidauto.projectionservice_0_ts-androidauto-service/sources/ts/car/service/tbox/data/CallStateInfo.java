package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes2.dex */
public final class CallStateInfo {
    public CallDirection callDirection;
    public int callStatus;
    public int callType;
    public Bundle mBundler;
    public String phoneNumber;

    public CallStateInfo() {
        this.mBundler = new Bundle();
    }

    protected CallStateInfo(Parcel in) {
        this.phoneNumber = in.readString();
        this.callType = in.readInt();
        this.callStatus = in.readInt();
        this.callDirection = CallDirection.toCallDirection(in.readInt());
        this.mBundler = in.readBundle();
    }

    public CallDirection getCallDirection() {
        return this.callDirection;
    }

    public void setCallDirection(CallDirection callDirection) {
        this.callDirection = callDirection;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCallType() {
        return this.callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public int getCallStatus() {
        return this.callStatus;
    }

    public void setCallStatus(int callStatus) {
        this.callStatus = callStatus;
    }

    public Bundle getmBundler() {
        return this.mBundler;
    }

    public void setmBundler(Bundle bundler) {
        this.mBundler = bundler;
    }

    public String toString() {
        return "CallStateInfo{callDirection=" + this.callDirection + ", phoneNumber='" + this.phoneNumber + "', callType=" + this.callType + ", callStatus=" + this.callStatus + ", mBundler=" + this.mBundler + '}';
    }

    public static String toString(CallStateInfo info) {
        if (info == null) {
            return "CallStateInfo is null";
        }
        return info.toString();
    }
}
