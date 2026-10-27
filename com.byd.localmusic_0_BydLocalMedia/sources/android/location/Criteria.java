package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class Criteria implements Parcelable {
    public static final int ACCURACY_COARSE = 2;
    public static final int ACCURACY_FINE = 1;
    public static final int ACCURACY_HIGH = 3;
    public static final int ACCURACY_LOW = 1;
    public static final int ACCURACY_MEDIUM = 2;
    public static final Parcelable.Creator<Criteria> CREATOR = new Parcelable.Creator<Criteria>() { // from class: android.location.Criteria.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Criteria createFromParcel(Parcel parcel) {
            Criteria criteria = new Criteria();
            criteria.mHorizontalAccuracy = parcel.readInt();
            criteria.mVerticalAccuracy = parcel.readInt();
            criteria.mSpeedAccuracy = parcel.readInt();
            criteria.mBearingAccuracy = parcel.readInt();
            criteria.mPowerRequirement = parcel.readInt();
            criteria.mAltitudeRequired = parcel.readInt() != 0;
            criteria.mBearingRequired = parcel.readInt() != 0;
            criteria.mSpeedRequired = parcel.readInt() != 0;
            criteria.mCostAllowed = parcel.readInt() != 0;
            return criteria;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Criteria[] newArray(int i) {
            return new Criteria[i];
        }
    };
    public static final int NO_REQUIREMENT = 0;
    public static final int POWER_HIGH = 3;
    public static final int POWER_LOW = 1;
    public static final int POWER_MEDIUM = 2;
    private boolean mAltitudeRequired;
    private int mBearingAccuracy;
    private boolean mBearingRequired;
    private boolean mCostAllowed;
    private int mHorizontalAccuracy;
    private int mPowerRequirement;
    private int mSpeedAccuracy;
    private boolean mSpeedRequired;
    private int mVerticalAccuracy;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccuracyRequirement {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LocationAccuracyRequirement {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerRequirement {
    }

    private static String requirementToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "???" : "High" : "Medium" : "Low" : "None";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Criteria() {
        this.mHorizontalAccuracy = 0;
        this.mVerticalAccuracy = 0;
        this.mSpeedAccuracy = 0;
        this.mBearingAccuracy = 0;
        this.mPowerRequirement = 0;
        this.mAltitudeRequired = false;
        this.mBearingRequired = false;
        this.mSpeedRequired = false;
        this.mCostAllowed = false;
    }

    public Criteria(Criteria criteria) {
        this.mHorizontalAccuracy = 0;
        this.mVerticalAccuracy = 0;
        this.mSpeedAccuracy = 0;
        this.mBearingAccuracy = 0;
        this.mPowerRequirement = 0;
        this.mAltitudeRequired = false;
        this.mBearingRequired = false;
        this.mSpeedRequired = false;
        this.mCostAllowed = false;
        this.mHorizontalAccuracy = criteria.mHorizontalAccuracy;
        this.mVerticalAccuracy = criteria.mVerticalAccuracy;
        this.mSpeedAccuracy = criteria.mSpeedAccuracy;
        this.mBearingAccuracy = criteria.mBearingAccuracy;
        this.mPowerRequirement = criteria.mPowerRequirement;
        this.mAltitudeRequired = criteria.mAltitudeRequired;
        this.mBearingRequired = criteria.mBearingRequired;
        this.mSpeedRequired = criteria.mSpeedRequired;
        this.mCostAllowed = criteria.mCostAllowed;
    }

    public void setHorizontalAccuracy(int i) {
        this.mHorizontalAccuracy = Preconditions.checkArgumentInRange(i, 0, 3, "accuracy");
    }

    public int getHorizontalAccuracy() {
        return this.mHorizontalAccuracy;
    }

    public void setVerticalAccuracy(int i) {
        this.mVerticalAccuracy = Preconditions.checkArgumentInRange(i, 0, 3, "accuracy");
    }

    public int getVerticalAccuracy() {
        return this.mVerticalAccuracy;
    }

    public void setSpeedAccuracy(int i) {
        this.mSpeedAccuracy = Preconditions.checkArgumentInRange(i, 0, 3, "accuracy");
    }

    public int getSpeedAccuracy() {
        return this.mSpeedAccuracy;
    }

    public void setBearingAccuracy(int i) {
        this.mBearingAccuracy = Preconditions.checkArgumentInRange(i, 0, 3, "accuracy");
    }

    public int getBearingAccuracy() {
        return this.mBearingAccuracy;
    }

    public void setAccuracy(int i) {
        Preconditions.checkArgumentInRange(i, 0, 2, "accuracy");
        if (i == 0) {
            setHorizontalAccuracy(0);
        } else if (i == 1) {
            setHorizontalAccuracy(3);
        } else {
            if (i != 2) {
                return;
            }
            setHorizontalAccuracy(1);
        }
    }

    public int getAccuracy() {
        return this.mHorizontalAccuracy >= 3 ? 1 : 2;
    }

    public void setPowerRequirement(int i) {
        this.mPowerRequirement = Preconditions.checkArgumentInRange(i, 0, 3, "powerRequirement");
    }

    public int getPowerRequirement() {
        return this.mPowerRequirement;
    }

    public void setCostAllowed(boolean z) {
        this.mCostAllowed = z;
    }

    public boolean isCostAllowed() {
        return this.mCostAllowed;
    }

    public void setAltitudeRequired(boolean z) {
        this.mAltitudeRequired = z;
    }

    public boolean isAltitudeRequired() {
        return this.mAltitudeRequired;
    }

    public void setSpeedRequired(boolean z) {
        this.mSpeedRequired = z;
    }

    public boolean isSpeedRequired() {
        return this.mSpeedRequired;
    }

    public void setBearingRequired(boolean z) {
        this.mBearingRequired = z;
    }

    public boolean isBearingRequired() {
        return this.mBearingRequired;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mHorizontalAccuracy);
        parcel.writeInt(this.mVerticalAccuracy);
        parcel.writeInt(this.mSpeedAccuracy);
        parcel.writeInt(this.mBearingAccuracy);
        parcel.writeInt(this.mPowerRequirement);
        parcel.writeInt(this.mAltitudeRequired ? 1 : 0);
        parcel.writeInt(this.mBearingRequired ? 1 : 0);
        parcel.writeInt(this.mSpeedRequired ? 1 : 0);
        parcel.writeInt(this.mCostAllowed ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Criteria[");
        sb.append("power=");
        sb.append(requirementToString(this.mPowerRequirement));
        sb.append(", ");
        sb.append("accuracy=");
        sb.append(requirementToString(this.mHorizontalAccuracy));
        if (this.mVerticalAccuracy != 0) {
            sb.append(", verticalAccuracy=");
            sb.append(requirementToString(this.mVerticalAccuracy));
        }
        if (this.mSpeedAccuracy != 0) {
            sb.append(", speedAccuracy=");
            sb.append(requirementToString(this.mSpeedAccuracy));
        }
        if (this.mBearingAccuracy != 0) {
            sb.append(", bearingAccuracy=");
            sb.append(requirementToString(this.mBearingAccuracy));
        }
        if (this.mAltitudeRequired || this.mBearingRequired || this.mSpeedRequired) {
            sb.append(", required=[");
            if (this.mAltitudeRequired) {
                sb.append("altitude, ");
            }
            if (this.mBearingRequired) {
                sb.append("bearing, ");
            }
            if (this.mSpeedRequired) {
                sb.append("speed, ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("]");
        }
        if (this.mCostAllowed) {
            sb.append(", costAllowed");
        }
        sb.append(']');
        return sb.toString();
    }
}
