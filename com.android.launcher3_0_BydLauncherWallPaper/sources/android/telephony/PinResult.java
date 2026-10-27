package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class PinResult implements Parcelable {
    public static final int PIN_RESULT_TYPE_FAILURE = 2;
    public static final int PIN_RESULT_TYPE_INCORRECT = 1;
    public static final int PIN_RESULT_TYPE_SUCCESS = 0;
    private final int mAttemptsRemaining;
    private final int mType;
    private static final PinResult sFailedResult = new PinResult(2, -1);
    public static final Parcelable.Creator<PinResult> CREATOR = new Parcelable.Creator<PinResult>() { // from class: android.telephony.PinResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PinResult createFromParcel(Parcel parcel) {
            return new PinResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PinResult[] newArray(int i) {
            return new PinResult[i];
        }
    };

    public @interface PinResultType {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.mType;
    }

    public int getAttemptsRemaining() {
        return this.mAttemptsRemaining;
    }

    public static PinResult getDefaultFailedResult() {
        return sFailedResult;
    }

    public PinResult(int i, int i2) {
        this.mType = i;
        this.mAttemptsRemaining = i2;
    }

    private PinResult(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mAttemptsRemaining = parcel.readInt();
    }

    public String toString() {
        return "type: " + getType() + ", attempts remaining: " + getAttemptsRemaining();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mAttemptsRemaining);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mAttemptsRemaining), Integer.valueOf(this.mType));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PinResult pinResult = (PinResult) obj;
        return this.mType == pinResult.mType && this.mAttemptsRemaining == pinResult.mAttemptsRemaining;
    }
}
