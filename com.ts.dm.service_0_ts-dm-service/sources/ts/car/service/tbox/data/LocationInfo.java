package ts.car.service.tbox.data;

import android.os.Bundle;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class LocationInfo {
    public double altitude;
    public double direction;
    public double hdop;
    public boolean inertialNavigation;
    public double latitude;
    public double longtitude;
    public double mBearing;
    public double mBearingAccuracyDegrees;
    public long mElapsedRealtimeNanos;
    public Bundle mExtras;
    public double mHorizontalAccuracyMeters;
    public double mSpeedAccuracyMetersPerSecond;
    public double mVerticalAccuracyMeters;
    public byte satNum;
    public double speed;
    public int state;
    public double tdop;
    public long time;
    public double vdop;

    public LocationInfo() {
        this.mElapsedRealtimeNanos = 0L;
        this.mBearing = 0.0d;
        this.mHorizontalAccuracyMeters = 0.0d;
        this.mVerticalAccuracyMeters = 0.0d;
        this.mSpeedAccuracyMetersPerSecond = 0.0d;
        this.mBearingAccuracyDegrees = 0.0d;
        this.mExtras = null;
        this.mExtras = new Bundle();
    }

    protected LocationInfo(Parcel in) {
        this.mElapsedRealtimeNanos = 0L;
        this.mBearing = 0.0d;
        this.mHorizontalAccuracyMeters = 0.0d;
        this.mVerticalAccuracyMeters = 0.0d;
        this.mSpeedAccuracyMetersPerSecond = 0.0d;
        this.mBearingAccuracyDegrees = 0.0d;
        this.mExtras = null;
        this.latitude = in.readDouble();
        this.longtitude = in.readDouble();
        this.altitude = in.readDouble();
        this.direction = in.readDouble();
        this.time = in.readLong();
        this.speed = in.readDouble();
        this.state = in.readInt();
        this.inertialNavigation = in.readByte() != 0;
        this.satNum = in.readByte();
        this.hdop = in.readDouble();
        this.vdop = in.readDouble();
        this.tdop = in.readDouble();
        this.mElapsedRealtimeNanos = in.readLong();
        this.mBearing = in.readDouble();
        this.mHorizontalAccuracyMeters = in.readDouble();
        this.mVerticalAccuracyMeters = in.readDouble();
        this.mSpeedAccuracyMetersPerSecond = in.readDouble();
        this.mBearingAccuracyDegrees = in.readDouble();
        this.mExtras = in.readBundle();
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public void setExtras(Bundle extras) {
        this.mExtras = extras;
    }

    public String toString() {
        return "LocationInfo{latitude=" + this.latitude + ", longtitude=" + this.longtitude + ", altitude=" + this.altitude + ", direction=" + this.direction + ", time=" + this.time + ", speed=" + this.speed + ", state=" + this.state + ", inertialNavigation=" + this.inertialNavigation + ", satNum=" + ((int) this.satNum) + ", hdop=" + this.hdop + ", vdop=" + this.vdop + ", tdop=" + this.tdop + ", mElapsedRealtimeNanos=" + this.mElapsedRealtimeNanos + ", mBearing=" + this.mBearing + ", mHorizontalAccuracyMeters=" + this.mHorizontalAccuracyMeters + ", mVerticalAccuracyMeters=" + this.mVerticalAccuracyMeters + ", mSpeedAccuracyMetersPerSecond=" + this.mSpeedAccuracyMetersPerSecond + ", mBearingAccuracyDegrees=" + this.mBearingAccuracyDegrees + ", mExtras=" + this.mExtras + '}';
    }
}
