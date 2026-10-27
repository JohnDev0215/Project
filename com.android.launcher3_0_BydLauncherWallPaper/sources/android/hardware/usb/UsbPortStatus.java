package android.hardware.usb;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class UsbPortStatus implements Parcelable {
    public static final int CONTAMINANT_DETECTION_DETECTED = 3;
    public static final int CONTAMINANT_DETECTION_DISABLED = 1;
    public static final int CONTAMINANT_DETECTION_NOT_DETECTED = 2;
    public static final int CONTAMINANT_DETECTION_NOT_SUPPORTED = 0;
    public static final int CONTAMINANT_PROTECTION_DISABLED = 8;
    public static final int CONTAMINANT_PROTECTION_FORCE_DISABLE = 4;
    public static final int CONTAMINANT_PROTECTION_NONE = 0;
    public static final int CONTAMINANT_PROTECTION_SINK = 1;
    public static final int CONTAMINANT_PROTECTION_SOURCE = 2;
    public static final Parcelable.Creator<UsbPortStatus> CREATOR = new Parcelable.Creator<UsbPortStatus>() { // from class: android.hardware.usb.UsbPortStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UsbPortStatus createFromParcel(Parcel parcel) {
            return new UsbPortStatus(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UsbPortStatus[] newArray(int i) {
            return new UsbPortStatus[i];
        }
    };
    public static final int DATA_ROLE_DEVICE = 2;
    public static final int DATA_ROLE_HOST = 1;
    public static final int DATA_ROLE_NONE = 0;
    public static final int MODE_AUDIO_ACCESSORY = 4;
    public static final int MODE_DEBUG_ACCESSORY = 8;
    public static final int MODE_DFP = 2;
    public static final int MODE_DUAL = 3;
    public static final int MODE_NONE = 0;
    public static final int MODE_UFP = 1;
    public static final int POWER_ROLE_NONE = 0;
    public static final int POWER_ROLE_SINK = 2;
    public static final int POWER_ROLE_SOURCE = 1;
    private final int mContaminantDetectionStatus;
    private final int mContaminantProtectionStatus;
    private final int mCurrentDataRole;
    private final int mCurrentMode;
    private final int mCurrentPowerRole;
    private final int mSupportedRoleCombinations;

    @Retention(RetentionPolicy.SOURCE)
    @interface ContaminantDetectionStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface ContaminantProtectionStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface UsbDataRole {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface UsbPortMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface UsbPowerRole {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UsbPortStatus(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mCurrentMode = i;
        this.mCurrentPowerRole = i2;
        this.mCurrentDataRole = i3;
        this.mSupportedRoleCombinations = i4;
        this.mContaminantProtectionStatus = i5;
        this.mContaminantDetectionStatus = i6;
    }

    public boolean isConnected() {
        return this.mCurrentMode != 0;
    }

    public int getCurrentMode() {
        return this.mCurrentMode;
    }

    public int getCurrentPowerRole() {
        return this.mCurrentPowerRole;
    }

    public int getCurrentDataRole() {
        return this.mCurrentDataRole;
    }

    public boolean isRoleCombinationSupported(int i, int i2) {
        return (this.mSupportedRoleCombinations & UsbPort.combineRolesAsBit(i, i2)) != 0;
    }

    public int getSupportedRoleCombinations() {
        return this.mSupportedRoleCombinations;
    }

    public int getContaminantDetectionStatus() {
        return this.mContaminantDetectionStatus;
    }

    public int getContaminantProtectionStatus() {
        return this.mContaminantProtectionStatus;
    }

    public String toString() {
        return "UsbPortStatus{connected=" + isConnected() + ", currentMode=" + UsbPort.modeToString(this.mCurrentMode) + ", currentPowerRole=" + UsbPort.powerRoleToString(this.mCurrentPowerRole) + ", currentDataRole=" + UsbPort.dataRoleToString(this.mCurrentDataRole) + ", supportedRoleCombinations=" + UsbPort.roleCombinationsToString(this.mSupportedRoleCombinations) + ", contaminantDetectionStatus=" + getContaminantDetectionStatus() + ", contaminantProtectionStatus=" + getContaminantProtectionStatus() + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCurrentMode);
        parcel.writeInt(this.mCurrentPowerRole);
        parcel.writeInt(this.mCurrentDataRole);
        parcel.writeInt(this.mSupportedRoleCombinations);
        parcel.writeInt(this.mContaminantProtectionStatus);
        parcel.writeInt(this.mContaminantDetectionStatus);
    }
}
