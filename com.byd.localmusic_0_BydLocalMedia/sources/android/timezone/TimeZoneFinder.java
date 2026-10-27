package android.timezone;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TimeZoneFinder {
    private static TimeZoneFinder sInstance;
    private static final Object sLock = new Object();
    private final libcore.timezone.TimeZoneFinder mDelegate;

    public static TimeZoneFinder getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new TimeZoneFinder(libcore.timezone.TimeZoneFinder.getInstance());
            }
        }
        return sInstance;
    }

    private TimeZoneFinder(libcore.timezone.TimeZoneFinder timeZoneFinder) {
        this.mDelegate = (libcore.timezone.TimeZoneFinder) Objects.requireNonNull(timeZoneFinder);
    }

    public String getIanaVersion() {
        return this.mDelegate.getIanaVersion();
    }

    public CountryTimeZones lookupCountryTimeZones(String str) {
        libcore.timezone.CountryTimeZones countryTimeZonesLookupCountryTimeZones = this.mDelegate.lookupCountryTimeZones(str);
        if (countryTimeZonesLookupCountryTimeZones == null) {
            return null;
        }
        return new CountryTimeZones(countryTimeZonesLookupCountryTimeZones);
    }
}
