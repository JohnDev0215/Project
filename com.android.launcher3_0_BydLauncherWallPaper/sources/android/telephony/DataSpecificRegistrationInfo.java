package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class DataSpecificRegistrationInfo implements Parcelable {
    public static final Parcelable.Creator<DataSpecificRegistrationInfo> CREATOR = new Parcelable.Creator<DataSpecificRegistrationInfo>() { // from class: android.telephony.DataSpecificRegistrationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataSpecificRegistrationInfo createFromParcel(Parcel parcel) {
            return new DataSpecificRegistrationInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DataSpecificRegistrationInfo[] newArray(int i) {
            return new DataSpecificRegistrationInfo[i];
        }
    };
    public final boolean isDcNrRestricted;
    public final boolean isEnDcAvailable;
    public final boolean isNrAvailable;
    public boolean mIsUsingCarrierAggregation;
    private final LteVopsSupportInfo mLteVopsSupportInfo;
    public final int maxDataCalls;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    DataSpecificRegistrationInfo(int i, boolean z, boolean z2, boolean z3, LteVopsSupportInfo lteVopsSupportInfo, boolean z4) {
        this.maxDataCalls = i;
        this.isDcNrRestricted = z;
        this.isNrAvailable = z2;
        this.isEnDcAvailable = z3;
        this.mLteVopsSupportInfo = lteVopsSupportInfo;
        this.mIsUsingCarrierAggregation = z4;
    }

    DataSpecificRegistrationInfo(DataSpecificRegistrationInfo dataSpecificRegistrationInfo) {
        this.maxDataCalls = dataSpecificRegistrationInfo.maxDataCalls;
        this.isDcNrRestricted = dataSpecificRegistrationInfo.isDcNrRestricted;
        this.isNrAvailable = dataSpecificRegistrationInfo.isNrAvailable;
        this.isEnDcAvailable = dataSpecificRegistrationInfo.isEnDcAvailable;
        this.mLteVopsSupportInfo = dataSpecificRegistrationInfo.mLteVopsSupportInfo;
        this.mIsUsingCarrierAggregation = dataSpecificRegistrationInfo.mIsUsingCarrierAggregation;
    }

    private DataSpecificRegistrationInfo(Parcel parcel) {
        this.maxDataCalls = parcel.readInt();
        this.isDcNrRestricted = parcel.readBoolean();
        this.isNrAvailable = parcel.readBoolean();
        this.isEnDcAvailable = parcel.readBoolean();
        this.mLteVopsSupportInfo = LteVopsSupportInfo.CREATOR.createFromParcel(parcel);
        this.mIsUsingCarrierAggregation = parcel.readBoolean();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.maxDataCalls);
        parcel.writeBoolean(this.isDcNrRestricted);
        parcel.writeBoolean(this.isNrAvailable);
        parcel.writeBoolean(this.isEnDcAvailable);
        this.mLteVopsSupportInfo.writeToParcel(parcel, i);
        parcel.writeBoolean(this.mIsUsingCarrierAggregation);
    }

    public String toString() {
        return getClass().getName() + " :{" + (" maxDataCalls = " + this.maxDataCalls) + (" isDcNrRestricted = " + this.isDcNrRestricted) + (" isNrAvailable = " + this.isNrAvailable) + (" isEnDcAvailable = " + this.isEnDcAvailable) + (" " + this.mLteVopsSupportInfo.toString()) + (" mIsUsingCarrierAggregation = " + this.mIsUsingCarrierAggregation) + " }";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.maxDataCalls), Boolean.valueOf(this.isDcNrRestricted), Boolean.valueOf(this.isNrAvailable), Boolean.valueOf(this.isEnDcAvailable), this.mLteVopsSupportInfo, Boolean.valueOf(this.mIsUsingCarrierAggregation));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataSpecificRegistrationInfo)) {
            return false;
        }
        DataSpecificRegistrationInfo dataSpecificRegistrationInfo = (DataSpecificRegistrationInfo) obj;
        return this.maxDataCalls == dataSpecificRegistrationInfo.maxDataCalls && this.isDcNrRestricted == dataSpecificRegistrationInfo.isDcNrRestricted && this.isNrAvailable == dataSpecificRegistrationInfo.isNrAvailable && this.isEnDcAvailable == dataSpecificRegistrationInfo.isEnDcAvailable && this.mLteVopsSupportInfo.equals(dataSpecificRegistrationInfo.mLteVopsSupportInfo) && this.mIsUsingCarrierAggregation == dataSpecificRegistrationInfo.mIsUsingCarrierAggregation;
    }

    public LteVopsSupportInfo getLteVopsSupportInfo() {
        return this.mLteVopsSupportInfo;
    }

    public void setIsUsingCarrierAggregation(boolean z) {
        this.mIsUsingCarrierAggregation = z;
    }

    public boolean isUsingCarrierAggregation() {
        return this.mIsUsingCarrierAggregation;
    }
}
