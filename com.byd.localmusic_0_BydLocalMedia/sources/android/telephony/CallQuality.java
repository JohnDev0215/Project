package android.telephony;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public final class CallQuality implements Parcelable {
    public static final int CALL_QUALITY_BAD = 4;
    public static final int CALL_QUALITY_EXCELLENT = 0;
    public static final int CALL_QUALITY_FAIR = 2;
    public static final int CALL_QUALITY_GOOD = 1;
    public static final int CALL_QUALITY_NOT_AVAILABLE = 5;
    public static final int CALL_QUALITY_POOR = 3;
    public static final Parcelable.Creator<CallQuality> CREATOR = new Parcelable.Creator() { // from class: android.telephony.CallQuality.1
        @Override // android.os.Parcelable.Creator
        public CallQuality createFromParcel(Parcel parcel) {
            return new CallQuality(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CallQuality[] newArray(int i) {
            return new CallQuality[i];
        }
    };
    private int mAverageRelativeJitter;
    private int mAverageRoundTripTime;
    private int mCallDuration;
    private int mCodecType;
    private int mDownlinkCallQualityLevel;
    private int mMaxRelativeJitter;
    private int mNumRtpPacketsNotReceived;
    private int mNumRtpPacketsReceived;
    private int mNumRtpPacketsTransmitted;
    private int mNumRtpPacketsTransmittedLost;
    private boolean mRtpInactivityDetected;
    private boolean mRxSilenceDetected;
    private boolean mTxSilenceDetected;
    private int mUplinkCallQualityLevel;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallQualityLevel {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CallQuality(Parcel parcel) {
        this.mDownlinkCallQualityLevel = parcel.readInt();
        this.mUplinkCallQualityLevel = parcel.readInt();
        this.mCallDuration = parcel.readInt();
        this.mNumRtpPacketsTransmitted = parcel.readInt();
        this.mNumRtpPacketsReceived = parcel.readInt();
        this.mNumRtpPacketsTransmittedLost = parcel.readInt();
        this.mNumRtpPacketsNotReceived = parcel.readInt();
        this.mAverageRelativeJitter = parcel.readInt();
        this.mMaxRelativeJitter = parcel.readInt();
        this.mAverageRoundTripTime = parcel.readInt();
        this.mCodecType = parcel.readInt();
        this.mRtpInactivityDetected = parcel.readBoolean();
        this.mRxSilenceDetected = parcel.readBoolean();
        this.mTxSilenceDetected = parcel.readBoolean();
    }

    public CallQuality() {
    }

    public CallQuality(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, false, false, false);
    }

    public CallQuality(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, boolean z, boolean z2, boolean z3) {
        this.mDownlinkCallQualityLevel = i;
        this.mUplinkCallQualityLevel = i2;
        this.mCallDuration = i3;
        this.mNumRtpPacketsTransmitted = i4;
        this.mNumRtpPacketsReceived = i5;
        this.mNumRtpPacketsTransmittedLost = i6;
        this.mNumRtpPacketsNotReceived = i7;
        this.mAverageRelativeJitter = i8;
        this.mMaxRelativeJitter = i9;
        this.mAverageRoundTripTime = i10;
        this.mCodecType = i11;
        this.mRtpInactivityDetected = z;
        this.mRxSilenceDetected = z2;
        this.mTxSilenceDetected = z3;
    }

    public int getDownlinkCallQualityLevel() {
        return this.mDownlinkCallQualityLevel;
    }

    public int getUplinkCallQualityLevel() {
        return this.mUplinkCallQualityLevel;
    }

    public int getCallDuration() {
        return this.mCallDuration;
    }

    public int getNumRtpPacketsTransmitted() {
        return this.mNumRtpPacketsTransmitted;
    }

    public int getNumRtpPacketsReceived() {
        return this.mNumRtpPacketsReceived;
    }

    public int getNumRtpPacketsTransmittedLost() {
        return this.mNumRtpPacketsTransmittedLost;
    }

    public int getNumRtpPacketsNotReceived() {
        return this.mNumRtpPacketsNotReceived;
    }

    public int getAverageRelativeJitter() {
        return this.mAverageRelativeJitter;
    }

    public int getMaxRelativeJitter() {
        return this.mMaxRelativeJitter;
    }

    public int getAverageRoundTripTime() {
        return this.mAverageRoundTripTime;
    }

    public boolean isRtpInactivityDetected() {
        return this.mRtpInactivityDetected;
    }

    public boolean isIncomingSilenceDetectedAtCallSetup() {
        return this.mRxSilenceDetected;
    }

    public boolean isOutgoingSilenceDetectedAtCallSetup() {
        return this.mTxSilenceDetected;
    }

    public int getCodecType() {
        return this.mCodecType;
    }

    public String toString() {
        return "CallQuality: {downlinkCallQualityLevel=" + this.mDownlinkCallQualityLevel + " uplinkCallQualityLevel=" + this.mUplinkCallQualityLevel + " callDuration=" + this.mCallDuration + " numRtpPacketsTransmitted=" + this.mNumRtpPacketsTransmitted + " numRtpPacketsReceived=" + this.mNumRtpPacketsReceived + " numRtpPacketsTransmittedLost=" + this.mNumRtpPacketsTransmittedLost + " numRtpPacketsNotReceived=" + this.mNumRtpPacketsNotReceived + " averageRelativeJitter=" + this.mAverageRelativeJitter + " maxRelativeJitter=" + this.mMaxRelativeJitter + " averageRoundTripTime=" + this.mAverageRoundTripTime + " codecType=" + this.mCodecType + " rtpInactivityDetected=" + this.mRtpInactivityDetected + " txSilenceDetected=" + this.mRxSilenceDetected + " rxSilenceDetected=" + this.mTxSilenceDetected + "}";
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mDownlinkCallQualityLevel), Integer.valueOf(this.mUplinkCallQualityLevel), Integer.valueOf(this.mCallDuration), Integer.valueOf(this.mNumRtpPacketsTransmitted), Integer.valueOf(this.mNumRtpPacketsReceived), Integer.valueOf(this.mNumRtpPacketsTransmittedLost), Integer.valueOf(this.mNumRtpPacketsNotReceived), Integer.valueOf(this.mAverageRelativeJitter), Integer.valueOf(this.mMaxRelativeJitter), Integer.valueOf(this.mAverageRoundTripTime), Integer.valueOf(this.mCodecType), Boolean.valueOf(this.mRtpInactivityDetected), Boolean.valueOf(this.mRxSilenceDetected), Boolean.valueOf(this.mTxSilenceDetected));
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CallQuality) || hashCode() != obj.hashCode()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        CallQuality callQuality = (CallQuality) obj;
        return this.mDownlinkCallQualityLevel == callQuality.mDownlinkCallQualityLevel && this.mUplinkCallQualityLevel == callQuality.mUplinkCallQualityLevel && this.mCallDuration == callQuality.mCallDuration && this.mNumRtpPacketsTransmitted == callQuality.mNumRtpPacketsTransmitted && this.mNumRtpPacketsReceived == callQuality.mNumRtpPacketsReceived && this.mNumRtpPacketsTransmittedLost == callQuality.mNumRtpPacketsTransmittedLost && this.mNumRtpPacketsNotReceived == callQuality.mNumRtpPacketsNotReceived && this.mAverageRelativeJitter == callQuality.mAverageRelativeJitter && this.mMaxRelativeJitter == callQuality.mMaxRelativeJitter && this.mAverageRoundTripTime == callQuality.mAverageRoundTripTime && this.mCodecType == callQuality.mCodecType && this.mRtpInactivityDetected == callQuality.mRtpInactivityDetected && this.mRxSilenceDetected == callQuality.mRxSilenceDetected && this.mTxSilenceDetected == callQuality.mTxSilenceDetected;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mDownlinkCallQualityLevel);
        parcel.writeInt(this.mUplinkCallQualityLevel);
        parcel.writeInt(this.mCallDuration);
        parcel.writeInt(this.mNumRtpPacketsTransmitted);
        parcel.writeInt(this.mNumRtpPacketsReceived);
        parcel.writeInt(this.mNumRtpPacketsTransmittedLost);
        parcel.writeInt(this.mNumRtpPacketsNotReceived);
        parcel.writeInt(this.mAverageRelativeJitter);
        parcel.writeInt(this.mMaxRelativeJitter);
        parcel.writeInt(this.mAverageRoundTripTime);
        parcel.writeInt(this.mCodecType);
        parcel.writeBoolean(this.mRtpInactivityDetected);
        parcel.writeBoolean(this.mRxSilenceDetected);
        parcel.writeBoolean(this.mTxSilenceDetected);
    }
}
