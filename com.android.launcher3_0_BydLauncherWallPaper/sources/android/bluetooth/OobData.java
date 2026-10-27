package android.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class OobData implements Parcelable {
    public static final Parcelable.Creator<OobData> CREATOR = new Parcelable.Creator<OobData>() { // from class: android.bluetooth.OobData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OobData createFromParcel(Parcel parcel) {
            return new OobData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OobData[] newArray(int i) {
            return new OobData[i];
        }
    };
    private byte[] mC192;
    private byte[] mC256;
    private byte[] mLeBluetoothDeviceAddress;
    private byte[] mLeSecureConnectionsConfirmation;
    private byte[] mLeSecureConnectionsRandom;
    private byte[] mR192;
    private byte[] mR256;
    private byte[] mSecurityManagerTk;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte[] getLeBluetoothDeviceAddress() {
        return this.mLeBluetoothDeviceAddress;
    }

    public void setLeBluetoothDeviceAddress(byte[] bArr) {
        this.mLeBluetoothDeviceAddress = bArr;
    }

    public byte[] getSecurityManagerTk() {
        return this.mSecurityManagerTk;
    }

    public void setSecurityManagerTk(byte[] bArr) {
        this.mSecurityManagerTk = bArr;
    }

    public byte[] getLeSecureConnectionsConfirmation() {
        return this.mLeSecureConnectionsConfirmation;
    }

    public void setLeSecureConnectionsConfirmation(byte[] bArr) {
        this.mLeSecureConnectionsConfirmation = bArr;
    }

    public byte[] getLeSecureConnectionsRandom() {
        return this.mLeSecureConnectionsRandom;
    }

    public void setLeSecureConnectionsRandom(byte[] bArr) {
        this.mLeSecureConnectionsRandom = bArr;
    }

    public byte[] getC192() {
        return this.mC192;
    }

    public void setC192(byte[] bArr) {
        this.mC192 = bArr;
    }

    public byte[] getR192() {
        return this.mR192;
    }

    public void setR192(byte[] bArr) {
        this.mR192 = bArr;
    }

    public byte[] getC256() {
        return this.mC256;
    }

    public void setC256(byte[] bArr) {
        this.mC256 = bArr;
    }

    public byte[] getR256() {
        return this.mR256;
    }

    public void setR256(byte[] bArr) {
        this.mR256 = bArr;
    }

    public OobData() {
    }

    private OobData(Parcel parcel) {
        this.mLeBluetoothDeviceAddress = parcel.createByteArray();
        this.mSecurityManagerTk = parcel.createByteArray();
        this.mLeSecureConnectionsConfirmation = parcel.createByteArray();
        this.mLeSecureConnectionsRandom = parcel.createByteArray();
        this.mC192 = parcel.createByteArray();
        this.mR192 = parcel.createByteArray();
        this.mC256 = parcel.createByteArray();
        this.mR256 = parcel.createByteArray();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.mLeBluetoothDeviceAddress);
        parcel.writeByteArray(this.mSecurityManagerTk);
        parcel.writeByteArray(this.mLeSecureConnectionsConfirmation);
        parcel.writeByteArray(this.mLeSecureConnectionsRandom);
        parcel.writeByteArray(this.mC192);
        parcel.writeByteArray(this.mR192);
        parcel.writeByteArray(this.mC256);
        parcel.writeByteArray(this.mR256);
    }
}
