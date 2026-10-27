package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ts.carplay.common.util.LogEncrypt;

/* JADX INFO: loaded from: classes.dex */
public class ConnectRequest implements Parcelable {
    public static final Parcelable.Creator<ConnectRequest> CREATOR = new Parcelable.Creator<ConnectRequest>() { // from class: com.ts.carplay.common.data.ConnectRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConnectRequest createFromParcel(Parcel source) {
            ConnectRequest object = new ConnectRequest(source);
            return object;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConnectRequest[] newArray(int size) {
            return new ConnectRequest[size];
        }
    };
    private int connectType;
    private boolean isUserRequest;
    private String mDeviceId;

    public ConnectRequest() {
    }

    public ConnectRequest(Parcel in) {
        this.mDeviceId = in.readString();
        this.isUserRequest = in.readBoolean();
        this.connectType = in.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mDeviceId);
        dest.writeBoolean(this.isUserRequest);
        dest.writeInt(this.connectType);
    }

    public void clear() {
        this.mDeviceId = null;
        this.isUserRequest = false;
        this.connectType = 0;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public boolean isUserRequest() {
        return this.isUserRequest;
    }

    public int getConnectType() {
        return this.connectType;
    }

    public void setDeviceId(String deviceId) {
        this.mDeviceId = deviceId;
    }

    public void setUserRequest(boolean userRequest) {
        this.isUserRequest = userRequest;
    }

    public void setConnectType(int connectType) {
        this.connectType = connectType;
    }

    public String toString() {
        return "ConnectRequest{mDeviceId=" + LogEncrypt.MacAddressEncrypt(this.mDeviceId) + ", isUserRequest=" + this.isUserRequest + ", connectType=" + this.connectType + '}';
    }
}
