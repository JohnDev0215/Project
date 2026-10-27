package com.android.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public final class ProviderProperties implements Parcelable {
    public static final Parcelable.Creator<ProviderProperties> CREATOR = new Parcelable.Creator<ProviderProperties>() { // from class: com.android.internal.location.ProviderProperties.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProviderProperties createFromParcel(Parcel parcel) {
            return new ProviderProperties(parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProviderProperties[] newArray(int i) {
            return new ProviderProperties[i];
        }
    };
    public final int mAccuracy;
    public final boolean mHasMonetaryCost;
    public final int mPowerRequirement;
    public final boolean mRequiresCell;
    public final boolean mRequiresNetwork;
    public final boolean mRequiresSatellite;
    public final boolean mSupportsAltitude;
    public final boolean mSupportsBearing;
    public final boolean mSupportsSpeed;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Accuracy {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerRequirement {
    }

    private static String accuracyToString(int i) {
        return i != 1 ? i != 2 ? "???" : "Coarse" : "Fine";
    }

    private static String powerToString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "???" : "High" : "Medium" : "Low";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProviderProperties(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, int i2) {
        this.mRequiresNetwork = z;
        this.mRequiresSatellite = z2;
        this.mRequiresCell = z3;
        this.mHasMonetaryCost = z4;
        this.mSupportsAltitude = z5;
        this.mSupportsSpeed = z6;
        this.mSupportsBearing = z7;
        this.mPowerRequirement = Preconditions.checkArgumentInRange(i, 1, 3, "powerRequirement");
        this.mAccuracy = Preconditions.checkArgumentInRange(i2, 1, 2, "accuracy");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRequiresNetwork ? 1 : 0);
        parcel.writeInt(this.mRequiresSatellite ? 1 : 0);
        parcel.writeInt(this.mRequiresCell ? 1 : 0);
        parcel.writeInt(this.mHasMonetaryCost ? 1 : 0);
        parcel.writeInt(this.mSupportsAltitude ? 1 : 0);
        parcel.writeInt(this.mSupportsSpeed ? 1 : 0);
        parcel.writeInt(this.mSupportsBearing ? 1 : 0);
        parcel.writeInt(this.mPowerRequirement);
        parcel.writeInt(this.mAccuracy);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ProviderProperties[");
        sb.append("power=").append(powerToString(this.mPowerRequirement)).append(", ");
        sb.append("accuracy=").append(accuracyToString(this.mAccuracy));
        if (this.mRequiresNetwork || this.mRequiresSatellite || this.mRequiresCell) {
            sb.append(", requires=");
            if (this.mRequiresNetwork) {
                sb.append("network,");
            }
            if (this.mRequiresSatellite) {
                sb.append("satellite,");
            }
            if (this.mRequiresCell) {
                sb.append("cell,");
            }
            sb.setLength(sb.length() - 1);
        }
        if (this.mHasMonetaryCost) {
            sb.append(", hasMonetaryCost");
        }
        if (this.mSupportsBearing || this.mSupportsSpeed || this.mSupportsAltitude) {
            sb.append(", supports=[");
            if (this.mSupportsBearing) {
                sb.append("bearing, ");
            }
            if (this.mSupportsSpeed) {
                sb.append("speed, ");
            }
            if (this.mSupportsAltitude) {
                sb.append("altitude, ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("]");
        }
        sb.append("]");
        return sb.toString();
    }
}
