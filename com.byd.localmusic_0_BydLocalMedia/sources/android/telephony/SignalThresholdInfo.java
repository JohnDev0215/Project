package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class SignalThresholdInfo implements Parcelable {
    public static final Parcelable.Creator<SignalThresholdInfo> CREATOR = new Parcelable.Creator<SignalThresholdInfo>() { // from class: android.telephony.SignalThresholdInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignalThresholdInfo createFromParcel(Parcel parcel) {
            return new SignalThresholdInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SignalThresholdInfo[] newArray(int i) {
            return new SignalThresholdInfo[i];
        }
    };
    public static final int HYSTERESIS_DB_DISABLED = 0;
    public static final int HYSTERESIS_MS_DISABLED = 0;
    public static final int SIGNAL_RSCP = 2;
    public static final int SIGNAL_RSRP = 3;
    public static final int SIGNAL_RSRQ = 4;
    public static final int SIGNAL_RSSI = 1;
    public static final int SIGNAL_RSSNR = 5;
    public static final int SIGNAL_SSRSRP = 6;
    public static final int SIGNAL_SSRSRQ = 7;
    public static final int SIGNAL_SSSINR = 8;
    private int mHysteresisDb;
    private int mHysteresisMs;
    private boolean mIsEnabled;
    private int mSignalMeasurement;
    private int[] mThresholds;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SignalMeasurementType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SignalThresholdInfo(int i, int i2, int i3, int[] iArr, boolean z) {
        this.mThresholds = null;
        this.mIsEnabled = true;
        this.mSignalMeasurement = i;
        this.mHysteresisMs = i2 < 0 ? 0 : i2;
        this.mHysteresisDb = i3 < 0 ? 0 : i3;
        this.mThresholds = iArr != null ? (int[]) iArr.clone() : null;
        this.mIsEnabled = z;
    }

    public int getSignalMeasurement() {
        return this.mSignalMeasurement;
    }

    public int getHysteresisMs() {
        return this.mHysteresisMs;
    }

    public int getHysteresisDb() {
        return this.mHysteresisDb;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public int[] getThresholds() {
        int[] iArr = this.mThresholds;
        if (iArr == null) {
            return null;
        }
        return (int[]) iArr.clone();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mSignalMeasurement);
        parcel.writeInt(this.mHysteresisMs);
        parcel.writeInt(this.mHysteresisDb);
        parcel.writeIntArray(this.mThresholds);
        parcel.writeBoolean(this.mIsEnabled);
    }

    private SignalThresholdInfo(Parcel parcel) {
        this.mThresholds = null;
        this.mIsEnabled = true;
        this.mSignalMeasurement = parcel.readInt();
        this.mHysteresisMs = parcel.readInt();
        this.mHysteresisDb = parcel.readInt();
        this.mThresholds = parcel.createIntArray();
        this.mIsEnabled = parcel.readBoolean();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SignalThresholdInfo)) {
            return false;
        }
        SignalThresholdInfo signalThresholdInfo = (SignalThresholdInfo) obj;
        return this.mSignalMeasurement == signalThresholdInfo.mSignalMeasurement && this.mHysteresisMs == signalThresholdInfo.mHysteresisMs && this.mHysteresisDb == signalThresholdInfo.mHysteresisDb && Arrays.equals(this.mThresholds, signalThresholdInfo.mThresholds) && this.mIsEnabled == signalThresholdInfo.mIsEnabled;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mSignalMeasurement), Integer.valueOf(this.mHysteresisMs), Integer.valueOf(this.mHysteresisDb), this.mThresholds, Boolean.valueOf(this.mIsEnabled));
    }

    public String toString() {
        return "SignalThresholdInfo{mSignalMeasurement=" + this.mSignalMeasurement + "mHysteresisMs=" + this.mSignalMeasurement + "mHysteresisDb=" + this.mHysteresisDb + "mThresholds=" + Arrays.toString(this.mThresholds) + "mIsEnabled=" + this.mIsEnabled + "}";
    }
}
