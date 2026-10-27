package com.amazon.avod.location;

import android.location.Location;
import android.os.SystemClock;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLocationResolver implements LocationResolver {
    private final LocationAwarenessConfig mLocationAwarenessConfig;

    public DefaultLocationResolver() {
        this(LocationAwarenessConfig.getInstance());
    }

    DefaultLocationResolver(@Nonnull LocationAwarenessConfig locationAwarenessConfig) {
        this.mLocationAwarenessConfig = (LocationAwarenessConfig) Preconditions.checkNotNull(locationAwarenessConfig, "locationAwarenessConfig");
    }

    @Override // com.amazon.avod.location.LocationResolver
    public boolean isLocationValid(@Nullable Location location) {
        return location != null && getLocationAge(location) <= this.mLocationAwarenessConfig.getLocationMaxAgeMillis();
    }

    private long getLocationAge(@Nonnull Location location) {
        return SystemClock.elapsedRealtime() - TimeUnit.NANOSECONDS.toMillis(location.getElapsedRealtimeNanos());
    }
}
