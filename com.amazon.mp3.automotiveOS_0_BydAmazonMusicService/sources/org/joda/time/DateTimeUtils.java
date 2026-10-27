package org.joda.time;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import java.text.DateFormatSymbols;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.joda.time.chrono.ISOChronology;

/* JADX INFO: loaded from: classes6.dex */
public class DateTimeUtils {
    public static final MillisProvider SYSTEM_MILLIS_PROVIDER;
    private static volatile MillisProvider cMillisProvider;
    private static final AtomicReference<Map<String, DateTimeZone>> cZoneNames;

    public interface MillisProvider {
        long getMillis();
    }

    static {
        SystemMillisProvider systemMillisProvider = new SystemMillisProvider();
        SYSTEM_MILLIS_PROVIDER = systemMillisProvider;
        cMillisProvider = systemMillisProvider;
        cZoneNames = new AtomicReference<>();
    }

    protected DateTimeUtils() {
    }

    public static final long currentTimeMillis() {
        return cMillisProvider.getMillis();
    }

    public static final long getInstantMillis(ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return currentTimeMillis();
        }
        return readableInstant.getMillis();
    }

    public static final Chronology getInstantChronology(ReadableInstant readableInstant) {
        if (readableInstant == null) {
            return ISOChronology.getInstance();
        }
        Chronology chronology = readableInstant.getChronology();
        return chronology == null ? ISOChronology.getInstance() : chronology;
    }

    public static final Chronology getIntervalChronology(ReadableInterval readableInterval) {
        if (readableInterval == null) {
            return ISOChronology.getInstance();
        }
        Chronology chronology = readableInterval.getChronology();
        return chronology == null ? ISOChronology.getInstance() : chronology;
    }

    public static final Chronology getChronology(Chronology chronology) {
        return chronology == null ? ISOChronology.getInstance() : chronology;
    }

    public static final DateTimeZone getZone(DateTimeZone dateTimeZone) {
        return dateTimeZone == null ? DateTimeZone.getDefault() : dateTimeZone;
    }

    public static final PeriodType getPeriodType(PeriodType periodType) {
        return periodType == null ? PeriodType.standard() : periodType;
    }

    public static final DateFormatSymbols getDateFormatSymbols(Locale locale) {
        try {
            return (DateFormatSymbols) DateFormatSymbols.class.getMethod("getInstance", Locale.class).invoke(null, locale);
        } catch (Exception unused) {
            return new DateFormatSymbols(locale);
        }
    }

    public static final Map<String, DateTimeZone> getDefaultTimeZoneNames() {
        AtomicReference<Map<String, DateTimeZone>> atomicReference = cZoneNames;
        Map<String, DateTimeZone> map = atomicReference.get();
        if (map != null) {
            return map;
        }
        Map<String, DateTimeZone> mapBuildDefaultTimeZoneNames = buildDefaultTimeZoneNames();
        return !CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, mapBuildDefaultTimeZoneNames) ? atomicReference.get() : mapBuildDefaultTimeZoneNames;
    }

    private static Map<String, DateTimeZone> buildDefaultTimeZoneNames() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        DateTimeZone dateTimeZone = DateTimeZone.UTC;
        linkedHashMap.put("UT", dateTimeZone);
        linkedHashMap.put("UTC", dateTimeZone);
        linkedHashMap.put("GMT", dateTimeZone);
        put(linkedHashMap, "EST", "America/New_York");
        put(linkedHashMap, "EDT", "America/New_York");
        put(linkedHashMap, "CST", "America/Chicago");
        put(linkedHashMap, "CDT", "America/Chicago");
        put(linkedHashMap, "MST", "America/Denver");
        put(linkedHashMap, "MDT", "America/Denver");
        put(linkedHashMap, "PST", "America/Los_Angeles");
        put(linkedHashMap, "PDT", "America/Los_Angeles");
        return Collections.unmodifiableMap(linkedHashMap);
    }

    private static void put(Map<String, DateTimeZone> map, String str, String str2) {
        try {
            map.put(str, DateTimeZone.forID(str2));
        } catch (RuntimeException unused) {
        }
    }

    static class SystemMillisProvider implements MillisProvider {
        SystemMillisProvider() {
        }

        @Override // org.joda.time.DateTimeUtils.MillisProvider
        public long getMillis() {
            return System.currentTimeMillis();
        }
    }
}
