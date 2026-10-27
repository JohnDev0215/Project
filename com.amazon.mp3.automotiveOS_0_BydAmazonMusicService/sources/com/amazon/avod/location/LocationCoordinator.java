package com.amazon.avod.location;

import android.location.Location;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LocationCoordinator {
    private LocationCache mLocationCache;
    final InitializationLatch mInitializationLatch = new InitializationLatch(this);
    private Optional<Location> mMockLocation = Optional.absent();

    private static class SingletonHolder {
        private static volatile LocationCoordinator sInstance = new LocationCoordinator();

        private SingletonHolder() {
        }
    }

    @Nonnull
    public static LocationCoordinator getInstance() {
        return SingletonHolder.sInstance;
    }

    LocationCoordinator() {
    }

    public Optional<Location> getLastLocation() {
        if (this.mMockLocation.isPresent()) {
            return this.mMockLocation;
        }
        if (!this.mInitializationLatch.isInitialized()) {
            return Optional.absent();
        }
        return this.mLocationCache.getLastLocation();
    }
}
