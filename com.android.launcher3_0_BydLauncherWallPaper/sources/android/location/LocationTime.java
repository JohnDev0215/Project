package android.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class LocationTime implements Parcelable {
    public static final Parcelable.Creator<LocationTime> CREATOR = new Parcelable.Creator<LocationTime>() { // from class: android.location.LocationTime.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationTime createFromParcel(Parcel parcel) {
            return new LocationTime(parcel.readLong(), parcel.readLong());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationTime[] newArray(int i) {
            return new LocationTime[i];
        }
    };
    private final long mElapsedRealtimeNanos;
    private final long mTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LocationTime(long j, long j2) {
        this.mTime = j;
        this.mElapsedRealtimeNanos = j2;
    }

    public long getTime() {
        return this.mTime;
    }

    public long getElapsedRealtimeNanos() {
        return this.mElapsedRealtimeNanos;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mTime);
        parcel.writeLong(this.mElapsedRealtimeNanos);
    }
}
