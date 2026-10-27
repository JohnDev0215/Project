package android.car.drivingstate;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import spi.c;

/* JADX INFO: loaded from: classes.dex */
public final class XCarUxRestrictions implements Parcelable {
    public static final Parcelable.Creator<XCarUxRestrictions> CREATOR = new Parcelable.Creator<XCarUxRestrictions>() { // from class: android.car.drivingstate.XCarUxRestrictions.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XCarUxRestrictions createFromParcel(Parcel parcel) {
            return new XCarUxRestrictions(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public XCarUxRestrictions[] newArray(int i) {
            return new XCarUxRestrictions[i];
        }
    };
    private static final int DEFAULT_MAX_CONTENT_DEPTH = 3;
    private static final int DEFAULT_MAX_CUMULATIVE_ITEMS = 21;
    private static final int DEFAULT_MAX_LENGTH = 120;
    public static final int UX_RESTRICTIONS_BASELINE = 0;
    public static final int UX_RESTRICTIONS_FULLY_RESTRICTED = 511;
    public static final int UX_RESTRICTIONS_LIMIT_CONTENT = 32;
    public static final int UX_RESTRICTIONS_LIMIT_STRING_LENGTH = 4;
    public static final int UX_RESTRICTIONS_NO_DIALPAD = 1;
    public static final int UX_RESTRICTIONS_NO_FILTERING = 2;
    public static final int UX_RESTRICTIONS_NO_KEYBOARD = 8;
    public static final int UX_RESTRICTIONS_NO_SETUP = 64;
    public static final int UX_RESTRICTIONS_NO_TEXT_MESSAGE = 128;
    public static final int UX_RESTRICTIONS_NO_VIDEO = 16;
    public static final int UX_RESTRICTIONS_NO_VOICE_TRANSCRIPTION = 256;
    private final int mActiveRestrictions;
    private final int mMaxContentDepth;
    private final int mMaxCumulativeContentItems;
    private final int mMaxStringLength;
    private final boolean mRequiresDistractionOptimization;
    private final long mTimeStamp;

    public static class Builder {
        private final int mActiveRestrictions;
        private int mMaxContentDepth = 3;
        private int mMaxCumulativeContentItems = 21;
        private int mMaxStringLength = 120;
        public final boolean mRequiresDistractionOptimization;
        private final long mTimeStamp;

        public Builder(boolean z, int i, long j) {
            this.mRequiresDistractionOptimization = z;
            this.mActiveRestrictions = i;
            this.mTimeStamp = j;
        }

        public XCarUxRestrictions build() {
            return new XCarUxRestrictions(this);
        }

        public Builder setMaxContentDepth(int i) {
            this.mMaxContentDepth = i;
            return this;
        }

        public Builder setMaxCumulativeContentItems(int i) {
            this.mMaxCumulativeContentItems = i;
            return this;
        }

        public Builder setMaxStringLength(int i) {
            this.mMaxStringLength = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarUxRestrictionsInfo {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getActiveRestrictions() {
        return this.mActiveRestrictions;
    }

    public int getMaxContentDepth() {
        return this.mMaxContentDepth;
    }

    public int getMaxCumulativeContentItems() {
        return this.mMaxCumulativeContentItems;
    }

    public int getMaxRestrictedStringLength() {
        return this.mMaxStringLength;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean isRequiresDistractionOptimization() {
        return this.mRequiresDistractionOptimization;
    }

    public boolean isSameRestrictions(XCarUxRestrictions xCarUxRestrictions) {
        if (xCarUxRestrictions == null) {
            return false;
        }
        if (xCarUxRestrictions == this) {
            return true;
        }
        return xCarUxRestrictions.mRequiresDistractionOptimization == this.mRequiresDistractionOptimization && xCarUxRestrictions.mActiveRestrictions == this.mActiveRestrictions;
    }

    @NonNull
    public String toString() {
        StringBuilder sbK = c.k("DO: ");
        sbK.append(this.mRequiresDistractionOptimization);
        sbK.append(" UxR: ");
        sbK.append(this.mActiveRestrictions);
        sbK.append(" time: ");
        sbK.append(this.mTimeStamp);
        return sbK.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mActiveRestrictions);
        parcel.writeLong(this.mTimeStamp);
        parcel.writeInt(this.mRequiresDistractionOptimization ? 1 : 0);
        parcel.writeInt(this.mMaxStringLength);
        parcel.writeInt(this.mMaxCumulativeContentItems);
        parcel.writeInt(this.mMaxContentDepth);
    }

    public XCarUxRestrictions(XCarUxRestrictions xCarUxRestrictions) {
        this.mTimeStamp = xCarUxRestrictions.getTimeStamp();
        this.mRequiresDistractionOptimization = xCarUxRestrictions.isRequiresDistractionOptimization();
        this.mActiveRestrictions = xCarUxRestrictions.getActiveRestrictions();
        this.mMaxStringLength = xCarUxRestrictions.mMaxStringLength;
        this.mMaxCumulativeContentItems = xCarUxRestrictions.mMaxCumulativeContentItems;
        this.mMaxContentDepth = xCarUxRestrictions.mMaxContentDepth;
    }

    private XCarUxRestrictions(Builder builder) {
        this.mTimeStamp = builder.mTimeStamp;
        this.mActiveRestrictions = builder.mActiveRestrictions;
        this.mRequiresDistractionOptimization = builder.mRequiresDistractionOptimization;
        this.mMaxStringLength = builder.mMaxStringLength;
        this.mMaxCumulativeContentItems = builder.mMaxCumulativeContentItems;
        this.mMaxContentDepth = builder.mMaxContentDepth;
    }

    private XCarUxRestrictions(Parcel parcel) {
        this.mActiveRestrictions = parcel.readInt();
        this.mTimeStamp = parcel.readLong();
        this.mRequiresDistractionOptimization = parcel.readInt() != 0;
        this.mMaxStringLength = parcel.readInt();
        this.mMaxCumulativeContentItems = parcel.readInt();
        this.mMaxContentDepth = parcel.readInt();
    }
}
