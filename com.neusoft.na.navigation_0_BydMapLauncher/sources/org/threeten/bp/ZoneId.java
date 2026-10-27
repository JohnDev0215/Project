package org.threeten.bp;

import android.hardware.bydauto.adas.BYDAutoADASDevice;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.slf4j.Marker;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.TextStyle;
import org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ZoneId implements Serializable {
    public static final TemporalQuery<ZoneId> FROM = new TemporalQuery<ZoneId>() { // from class: org.threeten.bp.ZoneId.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        public ZoneId queryFrom(TemporalAccessor temporalAccessor) {
            return ZoneId.from(temporalAccessor);
        }
    };
    public static final Map<String, String> SHORT_IDS;
    private static final long serialVersionUID = 8352817235686L;

    public abstract String getId();

    public abstract ZoneRules getRules();

    abstract void write(DataOutput dataOutput) throws IOException;

    static {
        HashMap map = new HashMap();
        map.put("ACT", "Australia/Darwin");
        map.put("AET", "Australia/Sydney");
        map.put("AGT", "America/Argentina/Buenos_Aires");
        map.put("ART", "Africa/Cairo");
        map.put("AST", "America/Anchorage");
        map.put("BET", "America/Sao_Paulo");
        map.put("BST", "Asia/Dhaka");
        map.put("CAT", "Africa/Harare");
        map.put("CNT", "America/St_Johns");
        map.put(BYDAutoADASDevice.FEATURE_ADAS_CST, "America/Chicago");
        map.put("CTT", "Asia/Shanghai");
        map.put("EAT", "Africa/Addis_Ababa");
        map.put("ECT", "Europe/Paris");
        map.put("IET", "America/Indiana/Indianapolis");
        map.put("IST", "Asia/Kolkata");
        map.put("JST", "Asia/Tokyo");
        map.put("MIT", "Pacific/Apia");
        map.put("NET", "Asia/Yerevan");
        map.put("NST", "Pacific/Auckland");
        map.put("PLT", "Asia/Karachi");
        map.put("PNT", "America/Phoenix");
        map.put("PRT", "America/Puerto_Rico");
        map.put("PST", "America/Los_Angeles");
        map.put("SST", "Pacific/Guadalcanal");
        map.put("VST", "Asia/Ho_Chi_Minh");
        map.put("EST", "-05:00");
        map.put("MST", "-07:00");
        map.put("HST", "-10:00");
        SHORT_IDS = Collections.unmodifiableMap(map);
    }

    public static ZoneId systemDefault() {
        return of(TimeZone.getDefault().getID(), SHORT_IDS);
    }

    public static Set<String> getAvailableZoneIds() {
        return ZoneRulesProvider.getAvailableZoneIds();
    }

    public static ZoneId of(String str, Map<String, String> map) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        Jdk8Methods.requireNonNull(map, "aliasMap");
        String str2 = map.get(str);
        if (str2 != null) {
            str = str2;
        }
        return of(str);
    }

    public static ZoneId of(String str) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        if (str.equals("Z")) {
            return ZoneOffset.UTC;
        }
        if (str.length() == 1) {
            throw new DateTimeException("Invalid zone: " + str);
        }
        if (str.startsWith(Marker.ANY_NON_NULL_MARKER) || str.startsWith("-")) {
            return ZoneOffset.of(str);
        }
        if (str.equals("UTC") || str.equals("GMT") || str.equals("UT")) {
            return new ZoneRegion(str, ZoneOffset.UTC.getRules());
        }
        if (str.startsWith("UTC+") || str.startsWith("GMT+") || str.startsWith("UTC-") || str.startsWith("GMT-")) {
            ZoneOffset zoneOffsetOf = ZoneOffset.of(str.substring(3));
            if (zoneOffsetOf.getTotalSeconds() == 0) {
                return new ZoneRegion(str.substring(0, 3), zoneOffsetOf.getRules());
            }
            return new ZoneRegion(str.substring(0, 3) + zoneOffsetOf.getId(), zoneOffsetOf.getRules());
        }
        if (str.startsWith("UT+") || str.startsWith("UT-")) {
            ZoneOffset zoneOffsetOf2 = ZoneOffset.of(str.substring(2));
            if (zoneOffsetOf2.getTotalSeconds() == 0) {
                return new ZoneRegion("UT", zoneOffsetOf2.getRules());
            }
            return new ZoneRegion("UT" + zoneOffsetOf2.getId(), zoneOffsetOf2.getRules());
        }
        return ZoneRegion.ofId(str, true);
    }

    public static ZoneId ofOffset(String str, ZoneOffset zoneOffset) {
        Jdk8Methods.requireNonNull(str, "prefix");
        Jdk8Methods.requireNonNull(zoneOffset, "offset");
        if (str.length() == 0) {
            return zoneOffset;
        }
        if (str.equals("GMT") || str.equals("UTC") || str.equals("UT")) {
            if (zoneOffset.getTotalSeconds() == 0) {
                return new ZoneRegion(str, zoneOffset.getRules());
            }
            return new ZoneRegion(str + zoneOffset.getId(), zoneOffset.getRules());
        }
        throw new IllegalArgumentException("Invalid prefix, must be GMT, UTC or UT: " + str);
    }

    public static ZoneId from(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.query(TemporalQueries.zone());
        if (zoneId != null) {
            return zoneId;
        }
        throw new DateTimeException("Unable to obtain ZoneId from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != ZoneRegion.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public String getDisplayName(TextStyle textStyle, Locale locale) {
        return new DateTimeFormatterBuilder().appendZoneText(textStyle).toFormatter(locale).format(new DefaultInterfaceTemporalAccessor() { // from class: org.threeten.bp.ZoneId.2
            @Override // org.threeten.bp.temporal.TemporalAccessor
            public boolean isSupported(TemporalField temporalField) {
                return false;
            }

            @Override // org.threeten.bp.temporal.TemporalAccessor
            public long getLong(TemporalField temporalField) {
                throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
            }

            @Override // org.threeten.bp.jdk8.DefaultInterfaceTemporalAccessor, org.threeten.bp.temporal.TemporalAccessor
            public <R> R query(TemporalQuery<R> temporalQuery) {
                if (temporalQuery == TemporalQueries.zoneId()) {
                    return (R) ZoneId.this;
                }
                return (R) super.query(temporalQuery);
            }
        });
    }

    public ZoneId normalized() {
        try {
            ZoneRules rules = getRules();
            if (rules.isFixedOffset()) {
                return rules.getOffset(Instant.EPOCH);
            }
        } catch (ZoneRulesException unused) {
        }
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return getId().equals(((ZoneId) obj).getId());
        }
        return false;
    }

    public int hashCode() {
        return getId().hashCode();
    }

    public String toString() {
        return getId();
    }
}
