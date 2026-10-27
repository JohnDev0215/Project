package com.ts.car.sdk.dm.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectRequest implements Parcelable {
    public static final Parcelable.Creator<ConnectRequest> CREATOR = new Parcelable.Creator<ConnectRequest>() { // from class: com.ts.car.sdk.dm.data.ConnectRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConnectRequest createFromParcel(Parcel in) {
            return new ConnectRequest(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ConnectRequest[] newArray(int size) {
            return new ConnectRequest[size];
        }
    };
    private int mCapabilityType;
    private int mConnectionType;
    private DmDevice mDevice;

    public ConnectRequest() {
    }

    protected ConnectRequest(Parcel in) {
        this.mDevice = (DmDevice) in.readParcelable(DmDevice.class.getClassLoader());
        this.mCapabilityType = in.readInt();
        this.mConnectionType = in.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mDevice, flags);
        dest.writeInt(this.mCapabilityType);
        dest.writeInt(this.mConnectionType);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DmDevice getDevice() {
        return this.mDevice;
    }

    public void setDevice(DmDevice device) {
        this.mDevice = device;
    }

    public int getCapabilityType() {
        return this.mCapabilityType;
    }

    public void setCapabilityType(int capabilityType) {
        this.mCapabilityType = capabilityType;
    }

    public int getConnectionType() {
        return this.mConnectionType;
    }

    public void setConnectType(int connectionType) {
        this.mConnectionType = connectionType;
    }

    public String toString() {
        return "ConnectRequest{mDevice=" + this.mDevice + ", mCapabilityType=" + this.mCapabilityType + ", mConnectionType=" + this.mConnectionType + '}';
    }
}
