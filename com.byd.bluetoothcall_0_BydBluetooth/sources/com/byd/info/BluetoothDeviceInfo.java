package com.byd.info;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceInfo implements Parcelable {
    public static final Parcelable.Creator<BluetoothDeviceInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BluetoothDevice f3546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3547c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3548d;

    class a implements Parcelable.Creator<BluetoothDeviceInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BluetoothDeviceInfo createFromParcel(Parcel parcel) {
            return new BluetoothDeviceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BluetoothDeviceInfo[] newArray(int i) {
            return new BluetoothDeviceInfo[i];
        }
    }

    public BluetoothDeviceInfo(BluetoothDevice bluetoothDevice, int i, int i2) {
        this.f3548d = -1;
        this.f3546b = bluetoothDevice;
        this.f3547c = i;
        this.f3548d = i2;
    }

    public int a() {
        return this.f3548d;
    }

    public int c() {
        return this.f3547c;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BluetoothDevice f() {
        return this.f3546b;
    }

    public void i(int i) {
        this.f3548d = i;
    }

    public void j(int i) {
        this.f3547c = i;
    }

    public String toString() {
        return "BluetoothDeviceInfo{device=" + this.f3546b + ", connect_state=" + this.f3547c + ", battery=" + this.f3548d + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f3546b, i);
        parcel.writeInt(this.f3547c);
        parcel.writeInt(this.f3548d);
    }

    protected BluetoothDeviceInfo(Parcel parcel) {
        this.f3548d = -1;
        this.f3546b = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        this.f3547c = parcel.readInt();
        this.f3548d = parcel.readInt();
    }
}
