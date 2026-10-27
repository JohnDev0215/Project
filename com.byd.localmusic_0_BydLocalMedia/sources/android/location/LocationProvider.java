package android.location;

import com.android.internal.location.ProviderProperties;

/* JADX INFO: loaded from: classes2.dex */
public class LocationProvider {

    @Deprecated
    public static final int AVAILABLE = 2;

    @Deprecated
    public static final int OUT_OF_SERVICE = 0;

    @Deprecated
    public static final int TEMPORARILY_UNAVAILABLE = 1;
    private final String mName;
    private final ProviderProperties mProperties;

    LocationProvider(String str, ProviderProperties providerProperties) {
        this.mName = str;
        this.mProperties = providerProperties;
    }

    public String getName() {
        return this.mName;
    }

    public boolean meetsCriteria(Criteria criteria) {
        return propertiesMeetCriteria(this.mName, this.mProperties, criteria);
    }

    public static boolean propertiesMeetCriteria(String str, ProviderProperties providerProperties, Criteria criteria) {
        if (LocationManager.PASSIVE_PROVIDER.equals(str) || providerProperties == null) {
            return false;
        }
        if (criteria.getAccuracy() != 0 && criteria.getAccuracy() < providerProperties.mAccuracy) {
            return false;
        }
        if (criteria.getPowerRequirement() != 0 && criteria.getPowerRequirement() < providerProperties.mPowerRequirement) {
            return false;
        }
        if (criteria.isAltitudeRequired() && !providerProperties.mSupportsAltitude) {
            return false;
        }
        if (criteria.isSpeedRequired() && !providerProperties.mSupportsSpeed) {
            return false;
        }
        if (!criteria.isBearingRequired() || providerProperties.mSupportsBearing) {
            return criteria.isCostAllowed() || !providerProperties.mHasMonetaryCost;
        }
        return false;
    }

    public boolean requiresNetwork() {
        return this.mProperties.mRequiresNetwork;
    }

    public boolean requiresSatellite() {
        return this.mProperties.mRequiresSatellite;
    }

    public boolean requiresCell() {
        return this.mProperties.mRequiresCell;
    }

    public boolean hasMonetaryCost() {
        return this.mProperties.mHasMonetaryCost;
    }

    public boolean supportsAltitude() {
        return this.mProperties.mSupportsAltitude;
    }

    public boolean supportsSpeed() {
        return this.mProperties.mSupportsSpeed;
    }

    public boolean supportsBearing() {
        return this.mProperties.mSupportsBearing;
    }

    public int getPowerRequirement() {
        return this.mProperties.mPowerRequirement;
    }

    public int getAccuracy() {
        return this.mProperties.mAccuracy;
    }
}
