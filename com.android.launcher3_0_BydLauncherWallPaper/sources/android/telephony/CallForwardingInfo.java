package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CallForwardingInfo implements Parcelable {
    public static final Parcelable.Creator<CallForwardingInfo> CREATOR = new Parcelable.Creator<CallForwardingInfo>() { // from class: android.telephony.CallForwardingInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CallForwardingInfo createFromParcel(Parcel parcel) {
            return new CallForwardingInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CallForwardingInfo[] newArray(int i) {
            return new CallForwardingInfo[i];
        }
    };
    public static final int REASON_ALL = 4;
    public static final int REASON_ALL_CONDITIONAL = 5;
    public static final int REASON_BUSY = 1;
    public static final int REASON_NOT_REACHABLE = 3;
    public static final int REASON_NO_REPLY = 2;
    public static final int REASON_UNCONDITIONAL = 0;
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_FDN_CHECK_FAILURE = 2;
    public static final int STATUS_INACTIVE = 0;
    public static final int STATUS_NOT_SUPPORTED = 4;
    public static final int STATUS_UNKNOWN_ERROR = 3;
    private static final String TAG = "CallForwardingInfo";
    private String mNumber;
    private int mReason;
    private int mStatus;
    private int mTimeSeconds;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallForwardingReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallForwardingStatus {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CallForwardingInfo(int i, int i2, String str, int i3) {
        this.mStatus = i;
        this.mReason = i2;
        this.mNumber = str;
        this.mTimeSeconds = i3;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public int getReason() {
        return this.mReason;
    }

    public String getNumber() {
        return this.mNumber;
    }

    public int getTimeoutSeconds() {
        return this.mTimeSeconds;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mNumber);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.mReason);
        parcel.writeInt(this.mTimeSeconds);
    }

    private CallForwardingInfo(Parcel parcel) {
        this.mNumber = parcel.readString();
        this.mStatus = parcel.readInt();
        this.mReason = parcel.readInt();
        this.mTimeSeconds = parcel.readInt();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CallForwardingInfo)) {
            return false;
        }
        CallForwardingInfo callForwardingInfo = (CallForwardingInfo) obj;
        return this.mStatus == callForwardingInfo.mStatus && this.mNumber == callForwardingInfo.mNumber && this.mReason == callForwardingInfo.mReason && this.mTimeSeconds == callForwardingInfo.mTimeSeconds;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mStatus), this.mNumber, Integer.valueOf(this.mReason), Integer.valueOf(this.mTimeSeconds));
    }

    public String toString() {
        return "[CallForwardingInfo: status=" + this.mStatus + ", reason= " + this.mReason + ", timeSec= " + this.mTimeSeconds + " seconds, number=" + com.android.telephony.Rlog.pii(TAG, this.mNumber) + "]";
    }
}
