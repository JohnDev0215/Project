package android.hardware.usb;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class ParcelableUsbPort implements Parcelable {
    public static final Parcelable.Creator<ParcelableUsbPort> CREATOR = new Parcelable.Creator<ParcelableUsbPort>() { // from class: android.hardware.usb.ParcelableUsbPort.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableUsbPort createFromParcel(Parcel parcel) {
            return new ParcelableUsbPort(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readBoolean(), parcel.readBoolean());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableUsbPort[] newArray(int i) {
            return new ParcelableUsbPort[i];
        }
    };
    private final String mId;
    private final int mSupportedContaminantProtectionModes;
    private final int mSupportedModes;
    private final boolean mSupportsEnableContaminantPresenceDetection;
    private final boolean mSupportsEnableContaminantPresenceProtection;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ParcelableUsbPort(String str, int i, int i2, boolean z, boolean z2) {
        this.mId = str;
        this.mSupportedModes = i;
        this.mSupportedContaminantProtectionModes = i2;
        this.mSupportsEnableContaminantPresenceProtection = z;
        this.mSupportsEnableContaminantPresenceDetection = z2;
    }

    public static ParcelableUsbPort of(UsbPort usbPort) {
        return new ParcelableUsbPort(usbPort.getId(), usbPort.getSupportedModes(), usbPort.getSupportedContaminantProtectionModes(), usbPort.supportsEnableContaminantPresenceProtection(), usbPort.supportsEnableContaminantPresenceDetection());
    }

    public UsbPort getUsbPort(UsbManager usbManager) {
        return new UsbPort(usbManager, this.mId, this.mSupportedModes, this.mSupportedContaminantProtectionModes, this.mSupportsEnableContaminantPresenceProtection, this.mSupportsEnableContaminantPresenceDetection);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeInt(this.mSupportedModes);
        parcel.writeInt(this.mSupportedContaminantProtectionModes);
        parcel.writeBoolean(this.mSupportsEnableContaminantPresenceProtection);
        parcel.writeBoolean(this.mSupportsEnableContaminantPresenceDetection);
    }
}
