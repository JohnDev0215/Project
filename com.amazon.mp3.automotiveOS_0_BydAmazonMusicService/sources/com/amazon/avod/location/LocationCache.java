package com.amazon.avod.location;

import android.location.Location;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class LocationCache {

    @GuardedBy("mLock")
    private Location mLocation;
    private final LocationResolver mLocationResolver;
    private final Object mLock;

    public LocationCache() {
        this(new DefaultLocationResolver());
    }

    LocationCache(@Nonnull LocationResolver locationResolver) {
        this.mLock = new Object();
        this.mLocationResolver = (LocationResolver) Preconditions.checkNotNull(locationResolver, "locationResolver");
    }

    @Nonnull
    Optional<Location> getLastLocation() {
        synchronized (this.mLock) {
            if (!this.mLocationResolver.isLocationValid(this.mLocation)) {
                return Optional.absent();
            }
            return Optional.fromNullable(this.mLocation);
        }
    }
}
