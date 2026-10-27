package com.android.internal.location;

import android.location.LocationRequest;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.WorkSource;
import android.util.TimeUtils;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class ProviderRequest implements Parcelable {
    public final long interval;
    public final List<LocationRequest> locationRequests;
    public final boolean locationSettingsIgnored;
    public final boolean lowPowerMode;
    public final boolean reportLocation;
    public final WorkSource workSource;
    public static final ProviderRequest EMPTY_REQUEST = new ProviderRequest(false, Long.MAX_VALUE, false, false, Collections.emptyList(), new WorkSource());
    public static final Parcelable.Creator<ProviderRequest> CREATOR = new Parcelable.Creator<ProviderRequest>() { // from class: com.android.internal.location.ProviderRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProviderRequest createFromParcel(Parcel parcel) {
            return new ProviderRequest(parcel.readBoolean(), parcel.readLong(), parcel.readBoolean(), parcel.readBoolean(), parcel.createTypedArrayList(LocationRequest.CREATOR), (WorkSource) parcel.readTypedObject(WorkSource.CREATOR));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProviderRequest[] newArray(int i) {
            return new ProviderRequest[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ProviderRequest(boolean z, long j, boolean z2, boolean z3, List<LocationRequest> list, WorkSource workSource) {
        this.reportLocation = z;
        this.interval = j;
        this.lowPowerMode = z2;
        this.locationSettingsIgnored = z3;
        this.locationRequests = (List) Objects.requireNonNull(list);
        this.workSource = (WorkSource) Objects.requireNonNull(workSource);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.reportLocation);
        parcel.writeLong(this.interval);
        parcel.writeBoolean(this.lowPowerMode);
        parcel.writeBoolean(this.locationSettingsIgnored);
        parcel.writeTypedList(this.locationRequests);
        parcel.writeTypedObject(this.workSource, i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProviderRequest[");
        if (this.reportLocation) {
            sb.append("interval=");
            TimeUtils.formatDuration(this.interval, sb);
            if (this.lowPowerMode) {
                sb.append(", lowPowerMode");
            }
            if (this.locationSettingsIgnored) {
                sb.append(", locationSettingsIgnored");
            }
        } else {
            sb.append("OFF");
        }
        sb.append(']');
        return sb.toString();
    }

    public static class Builder {
        private boolean mLocationSettingsIgnored;
        private boolean mLowPowerMode;
        private long mInterval = Long.MAX_VALUE;
        private List<LocationRequest> mLocationRequests = Collections.emptyList();
        private WorkSource mWorkSource = new WorkSource();

        public long getInterval() {
            return this.mInterval;
        }

        public Builder setInterval(long j) {
            this.mInterval = j;
            return this;
        }

        public boolean isLowPowerMode() {
            return this.mLowPowerMode;
        }

        public Builder setLowPowerMode(boolean z) {
            this.mLowPowerMode = z;
            return this;
        }

        public boolean isLocationSettingsIgnored() {
            return this.mLocationSettingsIgnored;
        }

        public Builder setLocationSettingsIgnored(boolean z) {
            this.mLocationSettingsIgnored = z;
            return this;
        }

        public List<LocationRequest> getLocationRequests() {
            return this.mLocationRequests;
        }

        public Builder setLocationRequests(List<LocationRequest> list) {
            this.mLocationRequests = (List) Objects.requireNonNull(list);
            return this;
        }

        public WorkSource getWorkSource() {
            return this.mWorkSource;
        }

        public Builder setWorkSource(WorkSource workSource) {
            this.mWorkSource = (WorkSource) Objects.requireNonNull(workSource);
            return this;
        }

        public ProviderRequest build() {
            if (this.mInterval == Long.MAX_VALUE) {
                return ProviderRequest.EMPTY_REQUEST;
            }
            return new ProviderRequest(true, this.mInterval, this.mLowPowerMode, this.mLocationSettingsIgnored, this.mLocationRequests, this.mWorkSource);
        }
    }
}
