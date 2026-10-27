package android.location;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes2.dex */
public final class GnssCapabilities {
    public static final long ANTENNA_INFO = 512;
    public static final long GEOFENCING = 4;
    public static final long INVALID_CAPABILITIES = -1;
    public static final long LOW_POWER_MODE = 1;
    public static final long MEASUREMENTS = 8;
    public static final long MEASUREMENT_CORRECTIONS = 32;
    public static final long MEASUREMENT_CORRECTIONS_EXCESS_PATH_LENGTH = 128;
    public static final long MEASUREMENT_CORRECTIONS_LOS_SATS = 64;
    public static final long MEASUREMENT_CORRECTIONS_REFLECTING_PLANE = 256;
    public static final long NAV_MESSAGES = 16;
    public static final long SATELLITE_BLACKLIST = 2;
    private final long mGnssCapabilities;

    public static GnssCapabilities of(long j) {
        return new GnssCapabilities(j);
    }

    private GnssCapabilities(long j) {
        this.mGnssCapabilities = j;
    }

    @SystemApi
    public boolean hasLowPowerMode() {
        return hasCapability(1L);
    }

    @SystemApi
    public boolean hasSatelliteBlacklist() {
        return hasCapability(2L);
    }

    @SystemApi
    public boolean hasGeofencing() {
        return hasCapability(4L);
    }

    @SystemApi
    public boolean hasMeasurements() {
        return hasCapability(8L);
    }

    @SystemApi
    public boolean hasNavMessages() {
        return hasCapability(16L);
    }

    @SystemApi
    public boolean hasMeasurementCorrections() {
        return hasCapability(32L);
    }

    @SystemApi
    public boolean hasMeasurementCorrectionsLosSats() {
        return hasCapability(64L);
    }

    @SystemApi
    public boolean hasMeasurementCorrectionsExcessPathLength() {
        return hasCapability(128L);
    }

    @SystemApi
    public boolean hasMeasurementCorrectionsReflectingPane() {
        return hasCapability(256L);
    }

    public boolean hasGnssAntennaInfo() {
        return hasCapability(512L);
    }

    private boolean hasCapability(long j) {
        return (this.mGnssCapabilities & j) == j;
    }
}
