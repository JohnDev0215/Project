package android.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import android.util.TimeUtils;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class LocationRequest implements Parcelable {
    public static final int ACCURACY_BLOCK = 102;
    public static final int ACCURACY_CITY = 104;
    public static final int ACCURACY_FINE = 100;
    public static final Parcelable.Creator<LocationRequest> CREATOR = new Parcelable.Creator<LocationRequest>() { // from class: android.location.LocationRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationRequest createFromParcel(Parcel parcel) {
            return new LocationRequest(parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readBoolean(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readFloat(), parcel.readBoolean(), parcel.readBoolean(), parcel.readBoolean(), (WorkSource) parcel.readTypedObject(WorkSource.CREATOR));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LocationRequest[] newArray(int i) {
            return new LocationRequest[i];
        }
    };
    private static final long DEFAULT_INTERVAL_MS = 3600000;
    private static final double FASTEST_INTERVAL_FACTOR = 6.0d;
    public static final int POWER_HIGH = 203;
    public static final int POWER_LOW = 201;
    public static final int POWER_NONE = 200;
    private long mExpireAt;
    private long mExpireIn;
    private boolean mExplicitFastestInterval;
    private long mFastestInterval;
    private boolean mHideFromAppOps;
    private long mInterval;
    private boolean mLocationSettingsIgnored;
    private boolean mLowPowerMode;
    private int mNumUpdates;
    private String mProvider;
    private int mQuality;
    private float mSmallestDisplacement;
    private WorkSource mWorkSource;

    public static String qualityToString(int i) {
        return i != 100 ? i != 102 ? i != 104 ? i != 203 ? i != 200 ? i != 201 ? "???" : "POWER_LOW" : "POWER_NONE" : "POWER_HIGH" : "ACCURACY_CITY" : "ACCURACY_BLOCK" : "ACCURACY_FINE";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static LocationRequest create() {
        return new LocationRequest();
    }

    @SystemApi
    public static LocationRequest createFromDeprecatedProvider(String str, long j, float f, boolean z) {
        int i;
        Preconditions.checkArgument(str != null, "invalid null provider");
        if (j < 0) {
            j = 0;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (LocationManager.PASSIVE_PROVIDER.equals(str)) {
            i = 200;
        } else {
            i = LocationManager.GPS_PROVIDER.equals(str) ? 100 : 201;
        }
        LocationRequest smallestDisplacement = new LocationRequest().setProvider(str).setQuality(i).setInterval(j).setFastestInterval(j).setSmallestDisplacement(f);
        if (z) {
            smallestDisplacement.setNumUpdates(1);
        }
        return smallestDisplacement;
    }

    @SystemApi
    public static LocationRequest createFromDeprecatedCriteria(Criteria criteria, long j, float f, boolean z) {
        int i;
        Preconditions.checkArgument(criteria != null, "invalid null criteria");
        if (j < 0) {
            j = 0;
        }
        if (f < 0.0f) {
            f = 0.0f;
        }
        int accuracy = criteria.getAccuracy();
        if (accuracy == 1) {
            i = 100;
        } else if (accuracy != 2) {
            i = criteria.getPowerRequirement() == 3 ? 203 : 201;
        } else {
            i = 102;
        }
        LocationRequest smallestDisplacement = new LocationRequest().setQuality(i).setInterval(j).setFastestInterval(j).setSmallestDisplacement(f);
        if (z) {
            smallestDisplacement.setNumUpdates(1);
        }
        return smallestDisplacement;
    }

    public LocationRequest() {
        this(LocationManager.FUSED_PROVIDER, 201, 3600000L, 600000L, false, Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE, 0.0f, false, false, false, null);
    }

    public LocationRequest(LocationRequest locationRequest) {
        this(locationRequest.mProvider, locationRequest.mQuality, locationRequest.mInterval, locationRequest.mFastestInterval, locationRequest.mExplicitFastestInterval, locationRequest.mExpireAt, locationRequest.mExpireIn, locationRequest.mNumUpdates, locationRequest.mSmallestDisplacement, locationRequest.mHideFromAppOps, locationRequest.mLocationSettingsIgnored, locationRequest.mLowPowerMode, locationRequest.mWorkSource);
    }

    private LocationRequest(String str, int i, long j, long j2, boolean z, long j3, long j4, int i2, float f, boolean z2, boolean z3, boolean z4, WorkSource workSource) {
        Preconditions.checkArgument(str != null, "invalid provider: null");
        checkQuality(i);
        this.mProvider = str;
        this.mQuality = i;
        this.mInterval = j;
        this.mFastestInterval = j2;
        this.mExplicitFastestInterval = z;
        this.mExpireAt = j3;
        this.mExpireIn = j4;
        this.mNumUpdates = i2;
        this.mSmallestDisplacement = Preconditions.checkArgumentInRange(f, 0.0f, Float.MAX_VALUE, "smallestDisplacementM");
        this.mHideFromAppOps = z2;
        this.mLowPowerMode = z4;
        this.mLocationSettingsIgnored = z3;
        this.mWorkSource = workSource;
    }

    public LocationRequest setQuality(int i) {
        checkQuality(i);
        this.mQuality = i;
        return this;
    }

    public int getQuality() {
        return this.mQuality;
    }

    public LocationRequest setInterval(long j) {
        Preconditions.checkArgument(j >= 0, "invalid interval: + millis");
        this.mInterval = j;
        if (!this.mExplicitFastestInterval) {
            this.mFastestInterval = (long) (j / 6.0d);
        }
        return this;
    }

    public long getInterval() {
        return this.mInterval;
    }

    public LocationRequest setLowPowerMode(boolean z) {
        this.mLowPowerMode = z;
        return this;
    }

    public boolean isLowPowerMode() {
        return this.mLowPowerMode;
    }

    public LocationRequest setLocationSettingsIgnored(boolean z) {
        this.mLocationSettingsIgnored = z;
        return this;
    }

    public boolean isLocationSettingsIgnored() {
        return this.mLocationSettingsIgnored;
    }

    public LocationRequest setFastestInterval(long j) {
        Preconditions.checkArgument(j >= 0, "invalid interval: + millis");
        this.mExplicitFastestInterval = true;
        this.mFastestInterval = j;
        return this;
    }

    public long getFastestInterval() {
        return this.mFastestInterval;
    }

    @Deprecated
    public LocationRequest setExpireAt(long j) {
        this.mExpireAt = Math.max(j, 0L);
        return this;
    }

    @Deprecated
    public long getExpireAt() {
        return this.mExpireAt;
    }

    public LocationRequest setExpireIn(long j) {
        this.mExpireIn = j;
        return this;
    }

    public long getExpireIn() {
        return this.mExpireIn;
    }

    public long getExpirationRealtimeMs(long j) {
        long j2 = this.mExpireIn;
        return Math.min(j2 <= Long.MAX_VALUE - j ? j + j2 : Long.MAX_VALUE, this.mExpireAt);
    }

    public LocationRequest setNumUpdates(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + i);
        }
        this.mNumUpdates = i;
        return this;
    }

    public int getNumUpdates() {
        return this.mNumUpdates;
    }

    public void decrementNumUpdates() {
        int i = this.mNumUpdates;
        if (i != Integer.MAX_VALUE) {
            this.mNumUpdates = i - 1;
        }
        if (this.mNumUpdates < 0) {
            this.mNumUpdates = 0;
        }
    }

    public LocationRequest setProvider(String str) {
        Preconditions.checkArgument(str != null, "invalid provider: null");
        this.mProvider = str;
        return this;
    }

    @SystemApi
    public String getProvider() {
        return this.mProvider;
    }

    @SystemApi
    public LocationRequest setSmallestDisplacement(float f) {
        this.mSmallestDisplacement = Preconditions.checkArgumentInRange(f, 0.0f, Float.MAX_VALUE, "smallestDisplacementM");
        return this;
    }

    @SystemApi
    public float getSmallestDisplacement() {
        return this.mSmallestDisplacement;
    }

    @SystemApi
    public void setWorkSource(WorkSource workSource) {
        this.mWorkSource = workSource;
    }

    @SystemApi
    public WorkSource getWorkSource() {
        return this.mWorkSource;
    }

    @SystemApi
    public void setHideFromAppOps(boolean z) {
        this.mHideFromAppOps = z;
    }

    @SystemApi
    public boolean getHideFromAppOps() {
        return this.mHideFromAppOps;
    }

    private static void checkQuality(int i) {
        if (i == 100 || i == 102 || i == 104 || i == 203 || i == 200 || i == 201) {
            return;
        }
        throw new IllegalArgumentException("invalid quality: " + i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mProvider);
        parcel.writeInt(this.mQuality);
        parcel.writeLong(this.mInterval);
        parcel.writeLong(this.mFastestInterval);
        parcel.writeBoolean(this.mExplicitFastestInterval);
        parcel.writeLong(this.mExpireAt);
        parcel.writeLong(this.mExpireIn);
        parcel.writeInt(this.mNumUpdates);
        parcel.writeFloat(this.mSmallestDisplacement);
        parcel.writeBoolean(this.mHideFromAppOps);
        parcel.writeBoolean(this.mLocationSettingsIgnored);
        parcel.writeBoolean(this.mLowPowerMode);
        parcel.writeTypedObject(this.mWorkSource, 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request[");
        sb.append(qualityToString(this.mQuality));
        sb.append(" ");
        sb.append(this.mProvider);
        if (this.mQuality != 200) {
            sb.append(" interval=");
            TimeUtils.formatDuration(this.mInterval, sb);
            if (this.mExplicitFastestInterval) {
                sb.append(" fastestInterval=");
                TimeUtils.formatDuration(this.mFastestInterval, sb);
            }
        }
        if (this.mExpireAt != Long.MAX_VALUE) {
            sb.append(" expireAt=");
            sb.append(TimeUtils.formatRealtime(this.mExpireAt));
        }
        if (this.mExpireIn != Long.MAX_VALUE) {
            sb.append(" expireIn=");
            TimeUtils.formatDuration(this.mExpireIn, sb);
        }
        if (this.mNumUpdates != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.mNumUpdates);
        }
        if (this.mLowPowerMode) {
            sb.append(" lowPowerMode");
        }
        if (this.mLocationSettingsIgnored) {
            sb.append(" locationSettingsIgnored");
        }
        sb.append(']');
        return sb.toString();
    }
}
