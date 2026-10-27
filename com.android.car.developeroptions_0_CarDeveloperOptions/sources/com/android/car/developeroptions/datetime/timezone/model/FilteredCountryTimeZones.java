package com.android.car.developeroptions.datetime.timezone.model;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import libcore.timezone.CountryTimeZones;

/* JADX INFO: loaded from: classes.dex */
public class FilteredCountryTimeZones {
    private final CountryTimeZones mCountryTimeZones;
    private final List<String> mTimeZoneIds;

    public FilteredCountryTimeZones(CountryTimeZones countryTimeZones) {
        this.mCountryTimeZones = countryTimeZones;
        this.mTimeZoneIds = Collections.unmodifiableList((List) countryTimeZones.getTimeZoneMappings().stream().filter(new Predicate() { // from class: com.android.car.developeroptions.datetime.timezone.model.-$$Lambda$FilteredCountryTimeZones$Uz4t2rMOcK32cGjFIU8Hu-eJvlw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return FilteredCountryTimeZones.lambda$new$0((CountryTimeZones.TimeZoneMapping) obj);
            }
        }).map(new Function() { // from class: com.android.car.developeroptions.datetime.timezone.model.-$$Lambda$FilteredCountryTimeZones$AwiPED6C26yA4li8JWXzu2qC4U4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CountryTimeZones.TimeZoneMapping) obj).getTimeZoneId();
            }
        }).collect(Collectors.toList()));
    }

    static /* synthetic */ boolean lambda$new$0(CountryTimeZones.TimeZoneMapping timeZoneMapping) {
        return timeZoneMapping.isShownInPicker() && (timeZoneMapping.getNotUsedAfter() == null || timeZoneMapping.getNotUsedAfter().longValue() >= 1546300800000L);
    }

    public List<String> getTimeZoneIds() {
        return this.mTimeZoneIds;
    }

    public String getRegionId() {
        return TimeZoneData.normalizeRegionId(this.mCountryTimeZones.getCountryIso());
    }
}
