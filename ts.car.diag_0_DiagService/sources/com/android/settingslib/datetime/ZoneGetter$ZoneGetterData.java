package com.android.settingslib.datetime;

import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import libcore.timezone.CountryTimeZones;
import libcore.timezone.TimeZoneFinder;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
public final class ZoneGetter$ZoneGetterData {
    private static List<String> a(List<CountryTimeZones.TimeZoneMapping> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<CountryTimeZones.TimeZoneMapping> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getTimeZoneId());
        }
        return Collections.unmodifiableList(arrayList);
    }

    @VisibleForTesting
    public List<String> lookupTimeZoneIdsByCountry(String str) {
        CountryTimeZones countryTimeZonesLookupCountryTimeZones = TimeZoneFinder.getInstance().lookupCountryTimeZones(str);
        if (countryTimeZonesLookupCountryTimeZones == null) {
            return null;
        }
        return a(countryTimeZonesLookupCountryTimeZones.getTimeZoneMappings());
    }
}
