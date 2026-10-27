package android.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class GnssReflectingPlane implements Parcelable {
    public static final Parcelable.Creator<GnssReflectingPlane> CREATOR = new Parcelable.Creator<GnssReflectingPlane>() { // from class: android.location.GnssReflectingPlane.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssReflectingPlane createFromParcel(Parcel parcel) {
            return new Builder().setLatitudeDegrees(parcel.readDouble()).setLongitudeDegrees(parcel.readDouble()).setAltitudeMeters(parcel.readDouble()).setAzimuthDegrees(parcel.readDouble()).build();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GnssReflectingPlane[] newArray(int i) {
            return new GnssReflectingPlane[i];
        }
    };
    private final double mAltitudeMeters;
    private final double mAzimuthDegrees;
    private final double mLatitudeDegrees;
    private final double mLongitudeDegrees;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private GnssReflectingPlane(Builder builder) {
        this.mLatitudeDegrees = builder.mLatitudeDegrees;
        this.mLongitudeDegrees = builder.mLongitudeDegrees;
        this.mAltitudeMeters = builder.mAltitudeMeters;
        this.mAzimuthDegrees = builder.mAzimuthDegrees;
    }

    public double getLatitudeDegrees() {
        return this.mLatitudeDegrees;
    }

    public double getLongitudeDegrees() {
        return this.mLongitudeDegrees;
    }

    public double getAltitudeMeters() {
        return this.mAltitudeMeters;
    }

    public double getAzimuthDegrees() {
        return this.mAzimuthDegrees;
    }

    public String toString() {
        return "ReflectingPlane:\n" + String.format("   %-29s = %s\n", "LatitudeDegrees = ", Double.valueOf(this.mLatitudeDegrees)) + String.format("   %-29s = %s\n", "LongitudeDegrees = ", Double.valueOf(this.mLongitudeDegrees)) + String.format("   %-29s = %s\n", "AltitudeMeters = ", Double.valueOf(this.mAltitudeMeters)) + String.format("   %-29s = %s\n", "AzimuthDegrees = ", Double.valueOf(this.mAzimuthDegrees));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatitudeDegrees);
        parcel.writeDouble(this.mLongitudeDegrees);
        parcel.writeDouble(this.mAltitudeMeters);
        parcel.writeDouble(this.mAzimuthDegrees);
    }

    public static final class Builder {
        private double mAltitudeMeters;
        private double mAzimuthDegrees;
        private double mLatitudeDegrees;
        private double mLongitudeDegrees;

        public Builder setLatitudeDegrees(double d) {
            this.mLatitudeDegrees = d;
            return this;
        }

        public Builder setLongitudeDegrees(double d) {
            this.mLongitudeDegrees = d;
            return this;
        }

        public Builder setAltitudeMeters(double d) {
            this.mAltitudeMeters = d;
            return this;
        }

        public Builder setAzimuthDegrees(double d) {
            this.mAzimuthDegrees = d;
            return this;
        }

        public GnssReflectingPlane build() {
            return new GnssReflectingPlane(this);
        }
    }
}
