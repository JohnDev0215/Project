package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PhysicalChannelConfig implements Parcelable {
    public static final int CONNECTION_PRIMARY_SERVING = 1;
    public static final int CONNECTION_SECONDARY_SERVING = 2;
    public static final int CONNECTION_UNKNOWN = Integer.MAX_VALUE;
    public static final Parcelable.Creator<PhysicalChannelConfig> CREATOR = new Parcelable.Creator<PhysicalChannelConfig>() { // from class: android.telephony.PhysicalChannelConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhysicalChannelConfig createFromParcel(Parcel parcel) {
            return new PhysicalChannelConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhysicalChannelConfig[] newArray(int i) {
            return new PhysicalChannelConfig[i];
        }
    };
    private int mCellBandwidthDownlinkKhz;
    private int mCellConnectionStatus;
    private int mChannelNumber;
    private int[] mContextIds;
    private int mFrequencyRange;
    private int mPhysicalCellId;
    private int mRat;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionStatus {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCellConnectionStatus);
        parcel.writeInt(this.mCellBandwidthDownlinkKhz);
        parcel.writeInt(this.mRat);
        parcel.writeInt(this.mChannelNumber);
        parcel.writeInt(this.mFrequencyRange);
        parcel.writeIntArray(this.mContextIds);
        parcel.writeInt(this.mPhysicalCellId);
    }

    public int getCellBandwidthDownlink() {
        return this.mCellBandwidthDownlinkKhz;
    }

    public int[] getContextIds() {
        return this.mContextIds;
    }

    public int getFrequencyRange() {
        return this.mFrequencyRange;
    }

    public int getChannelNumber() {
        return this.mChannelNumber;
    }

    public int getPhysicalCellId() {
        return this.mPhysicalCellId;
    }

    public int getRat() {
        return this.mRat;
    }

    public int getConnectionStatus() {
        return this.mCellConnectionStatus;
    }

    private String getConnectionStatusString() {
        int i = this.mCellConnectionStatus;
        if (i == 1) {
            return "PrimaryServing";
        }
        if (i == 2) {
            return "SecondaryServing";
        }
        if (i == Integer.MAX_VALUE) {
            return "Unknown";
        }
        return "Invalid(" + this.mCellConnectionStatus + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhysicalChannelConfig)) {
            return false;
        }
        PhysicalChannelConfig physicalChannelConfig = (PhysicalChannelConfig) obj;
        return this.mCellConnectionStatus == physicalChannelConfig.mCellConnectionStatus && this.mCellBandwidthDownlinkKhz == physicalChannelConfig.mCellBandwidthDownlinkKhz && this.mRat == physicalChannelConfig.mRat && this.mFrequencyRange == physicalChannelConfig.mFrequencyRange && this.mChannelNumber == physicalChannelConfig.mChannelNumber && this.mPhysicalCellId == physicalChannelConfig.mPhysicalCellId && Arrays.equals(this.mContextIds, physicalChannelConfig.mContextIds);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mCellConnectionStatus), Integer.valueOf(this.mCellBandwidthDownlinkKhz), Integer.valueOf(this.mRat), Integer.valueOf(this.mFrequencyRange), Integer.valueOf(this.mChannelNumber), Integer.valueOf(this.mPhysicalCellId), this.mContextIds);
    }

    public String toString() {
        return "{mConnectionStatus=" + getConnectionStatusString() + ",mCellBandwidthDownlinkKhz=" + this.mCellBandwidthDownlinkKhz + ",mRat=" + TelephonyManager.getNetworkTypeName(this.mRat) + ",mFrequencyRange=" + ServiceState.frequencyRangeToString(this.mFrequencyRange) + ",mChannelNumber=" + this.mChannelNumber + ",mContextIds=" + Arrays.toString(this.mContextIds) + ",mPhysicalCellId=" + this.mPhysicalCellId + "}";
    }

    private PhysicalChannelConfig(Parcel parcel) {
        this.mCellConnectionStatus = parcel.readInt();
        this.mCellBandwidthDownlinkKhz = parcel.readInt();
        this.mRat = parcel.readInt();
        this.mChannelNumber = parcel.readInt();
        this.mFrequencyRange = parcel.readInt();
        this.mContextIds = parcel.createIntArray();
        this.mPhysicalCellId = parcel.readInt();
    }

    private PhysicalChannelConfig(Builder builder) {
        this.mCellConnectionStatus = builder.mCellConnectionStatus;
        this.mCellBandwidthDownlinkKhz = builder.mCellBandwidthDownlinkKhz;
        this.mRat = builder.mRat;
        this.mChannelNumber = builder.mChannelNumber;
        this.mFrequencyRange = builder.mFrequencyRange;
        this.mContextIds = builder.mContextIds;
        this.mPhysicalCellId = builder.mPhysicalCellId;
    }

    public static final class Builder {
        private int mRat = 0;
        private int mFrequencyRange = 0;
        private int mChannelNumber = Integer.MAX_VALUE;
        private int mCellBandwidthDownlinkKhz = 0;
        private int mCellConnectionStatus = Integer.MAX_VALUE;
        private int[] mContextIds = new int[0];
        private int mPhysicalCellId = Integer.MAX_VALUE;

        public PhysicalChannelConfig build() {
            return new PhysicalChannelConfig(this);
        }

        public Builder setRat(int i) {
            this.mRat = i;
            return this;
        }

        public Builder setFrequencyRange(int i) {
            this.mFrequencyRange = i;
            return this;
        }

        public Builder setChannelNumber(int i) {
            this.mChannelNumber = i;
            return this;
        }

        public Builder setCellBandwidthDownlinkKhz(int i) {
            this.mCellBandwidthDownlinkKhz = i;
            return this;
        }

        public Builder setCellConnectionStatus(int i) {
            this.mCellConnectionStatus = i;
            return this;
        }

        public Builder setContextIds(int[] iArr) {
            if (iArr != null) {
                Arrays.sort(iArr);
            }
            this.mContextIds = iArr;
            return this;
        }

        public Builder setPhysicalCellId(int i) {
            this.mPhysicalCellId = i;
            return this;
        }
    }
}
