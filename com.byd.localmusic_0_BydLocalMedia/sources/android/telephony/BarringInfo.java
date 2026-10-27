package android.telephony;

import android.annotation.SystemApi;
import android.hardware.radio.V1_5.BarringInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BarringInfo implements Parcelable {
    public static final int BARRING_SERVICE_TYPE_CS_FALLBACK = 5;
    public static final int BARRING_SERVICE_TYPE_CS_SERVICE = 0;
    public static final int BARRING_SERVICE_TYPE_CS_VOICE = 2;
    public static final int BARRING_SERVICE_TYPE_EMERGENCY = 8;
    public static final int BARRING_SERVICE_TYPE_MMTEL_VIDEO = 7;
    public static final int BARRING_SERVICE_TYPE_MMTEL_VOICE = 6;
    public static final int BARRING_SERVICE_TYPE_MO_DATA = 4;
    public static final int BARRING_SERVICE_TYPE_MO_SIGNALLING = 3;
    public static final int BARRING_SERVICE_TYPE_PS_SERVICE = 1;
    public static final int BARRING_SERVICE_TYPE_SMS = 9;
    private SparseArray<BarringServiceInfo> mBarringServiceInfos;
    private CellIdentity mCellIdentity;
    private static final BarringServiceInfo BARRING_SERVICE_INFO_UNKNOWN = new BarringServiceInfo(-1);
    private static final BarringServiceInfo BARRING_SERVICE_INFO_UNBARRED = new BarringServiceInfo(0);
    public static final Parcelable.Creator<BarringInfo> CREATOR = new Parcelable.Creator<BarringInfo>() { // from class: android.telephony.BarringInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarringInfo createFromParcel(Parcel parcel) {
            return new BarringInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BarringInfo[] newArray(int i) {
            return new BarringInfo[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface BarringServiceType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static final class BarringServiceInfo implements Parcelable {
        public static final int BARRING_TYPE_CONDITIONAL = 1;
        public static final int BARRING_TYPE_NONE = 0;
        public static final int BARRING_TYPE_UNCONDITIONAL = 2;
        public static final int BARRING_TYPE_UNKNOWN = -1;
        public static final Parcelable.Creator<BarringServiceInfo> CREATOR = new Parcelable.Creator<BarringServiceInfo>() { // from class: android.telephony.BarringInfo.BarringServiceInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BarringServiceInfo createFromParcel(Parcel parcel) {
                return new BarringServiceInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public BarringServiceInfo[] newArray(int i) {
                return new BarringServiceInfo[i];
            }
        };
        private final int mBarringType;
        private final int mConditionalBarringFactor;
        private final int mConditionalBarringTimeSeconds;
        private final boolean mIsConditionallyBarred;

        @Retention(RetentionPolicy.SOURCE)
        public @interface BarringType {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public BarringServiceInfo(int i) {
            this(i, false, 0, 0);
        }

        public BarringServiceInfo(int i, boolean z, int i2, int i3) {
            this.mBarringType = i;
            this.mIsConditionallyBarred = z;
            this.mConditionalBarringFactor = i2;
            this.mConditionalBarringTimeSeconds = i3;
        }

        public int getBarringType() {
            return this.mBarringType;
        }

        public boolean isConditionallyBarred() {
            return this.mIsConditionallyBarred;
        }

        public int getConditionalBarringFactor() {
            return this.mConditionalBarringFactor;
        }

        public int getConditionalBarringTimeSeconds() {
            return this.mConditionalBarringTimeSeconds;
        }

        public boolean isBarred() {
            int i = this.mBarringType;
            if (i != 2) {
                return i == 1 && this.mIsConditionallyBarred;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.mBarringType), Boolean.valueOf(this.mIsConditionallyBarred), Integer.valueOf(this.mConditionalBarringFactor), Integer.valueOf(this.mConditionalBarringTimeSeconds));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BarringServiceInfo)) {
                return false;
            }
            BarringServiceInfo barringServiceInfo = (BarringServiceInfo) obj;
            return this.mBarringType == barringServiceInfo.mBarringType && this.mIsConditionallyBarred == barringServiceInfo.mIsConditionallyBarred && this.mConditionalBarringFactor == barringServiceInfo.mConditionalBarringFactor && this.mConditionalBarringTimeSeconds == barringServiceInfo.mConditionalBarringTimeSeconds;
        }

        public BarringServiceInfo(Parcel parcel) {
            this.mBarringType = parcel.readInt();
            this.mIsConditionallyBarred = parcel.readBoolean();
            this.mConditionalBarringFactor = parcel.readInt();
            this.mConditionalBarringTimeSeconds = parcel.readInt();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mBarringType);
            parcel.writeBoolean(this.mIsConditionallyBarred);
            parcel.writeInt(this.mConditionalBarringFactor);
            parcel.writeInt(this.mConditionalBarringTimeSeconds);
        }
    }

    @SystemApi
    public BarringInfo() {
        this.mBarringServiceInfos = new SparseArray<>();
    }

    public BarringInfo(CellIdentity cellIdentity, SparseArray<BarringServiceInfo> sparseArray) {
        this.mCellIdentity = cellIdentity;
        this.mBarringServiceInfos = sparseArray;
    }

    public static BarringInfo create(android.hardware.radio.V1_5.CellIdentity cellIdentity, List<android.hardware.radio.V1_5.BarringInfo> list) {
        CellIdentity cellIdentityCreate = CellIdentity.create(cellIdentity);
        SparseArray sparseArray = new SparseArray();
        for (android.hardware.radio.V1_5.BarringInfo barringInfo : list) {
            if (barringInfo.barringType == 1) {
                if (barringInfo.barringTypeSpecificInfo.getDiscriminator() == 1) {
                    BarringInfo.BarringTypeSpecificInfo.Conditional conditional = barringInfo.barringTypeSpecificInfo.conditional();
                    sparseArray.put(barringInfo.serviceType, new BarringServiceInfo(barringInfo.barringType, conditional.isBarred, conditional.factor, conditional.timeSeconds));
                }
            } else {
                sparseArray.put(barringInfo.serviceType, new BarringServiceInfo(barringInfo.barringType, false, 0, 0));
            }
        }
        return new BarringInfo(cellIdentityCreate, sparseArray);
    }

    public BarringServiceInfo getBarringServiceInfo(int i) {
        BarringServiceInfo barringServiceInfo = this.mBarringServiceInfos.get(i);
        return barringServiceInfo != null ? barringServiceInfo : this.mBarringServiceInfos.size() > 0 ? BARRING_SERVICE_INFO_UNBARRED : BARRING_SERVICE_INFO_UNKNOWN;
    }

    @SystemApi
    public BarringInfo createLocationInfoSanitizedCopy() {
        return this.mCellIdentity == null ? this : new BarringInfo(this.mCellIdentity.sanitizeLocationInfo(), this.mBarringServiceInfos);
    }

    public BarringInfo(Parcel parcel) {
        this.mCellIdentity = (CellIdentity) parcel.readParcelable(CellIdentity.class.getClassLoader());
        this.mBarringServiceInfos = parcel.readSparseArray(BarringServiceInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCellIdentity, i);
        parcel.writeSparseArray(this.mBarringServiceInfos);
    }

    public int hashCode() {
        CellIdentity cellIdentity = this.mCellIdentity;
        int iHashCode = cellIdentity != null ? cellIdentity.hashCode() : 7;
        for (int i = 0; i < this.mBarringServiceInfos.size(); i++) {
            iHashCode = iHashCode + (this.mBarringServiceInfos.keyAt(i) * 15) + (this.mBarringServiceInfos.valueAt(i).hashCode() * 31);
        }
        return iHashCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BarringInfo)) {
            return false;
        }
        BarringInfo barringInfo = (BarringInfo) obj;
        if (hashCode() != barringInfo.hashCode() || this.mBarringServiceInfos.size() != barringInfo.mBarringServiceInfos.size()) {
            return false;
        }
        for (int i = 0; i < this.mBarringServiceInfos.size(); i++) {
            if (this.mBarringServiceInfos.keyAt(i) != barringInfo.mBarringServiceInfos.keyAt(i) || !Objects.equals(this.mBarringServiceInfos.valueAt(i), barringInfo.mBarringServiceInfos.valueAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        return "BarringInfo {mCellIdentity=" + this.mCellIdentity + ", mBarringServiceInfos=" + this.mBarringServiceInfos + "}";
    }
}
