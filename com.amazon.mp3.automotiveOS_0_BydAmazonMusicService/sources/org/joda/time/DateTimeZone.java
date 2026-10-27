package org.joda.time;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.joda.convert.FromString;
import org.joda.convert.ToString;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.field.FieldUtils;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.FormatUtils;
import org.joda.time.tz.DefaultNameProvider;
import org.joda.time.tz.FixedDateTimeZone;
import org.joda.time.tz.NameProvider;
import org.joda.time.tz.Provider;
import org.joda.time.tz.UTCProvider;
import org.joda.time.tz.ZoneInfoProvider;

/* JADX INFO: loaded from: classes6.dex */
public abstract class DateTimeZone implements Serializable {
    private final String iID;
    public static final DateTimeZone UTC = UTCDateTimeZone.INSTANCE;
    private static final AtomicReference<Provider> cProvider = new AtomicReference<>();
    private static final AtomicReference<NameProvider> cNameProvider = new AtomicReference<>();
    private static final AtomicReference<DateTimeZone> cDefault = new AtomicReference<>();

    public abstract boolean equals(Object obj);

    public abstract String getNameKey(long j);

    public abstract int getOffset(long j);

    public abstract int getStandardOffset(long j);

    public abstract boolean isFixed();

    public abstract long nextTransition(long j);

    public abstract long previousTransition(long j);

    public static DateTimeZone getDefault() {
        DateTimeZone dateTimeZoneForTimeZone = cDefault.get();
        if (dateTimeZoneForTimeZone != null) {
            return dateTimeZoneForTimeZone;
        }
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.Timezone");
            if (property != null) {
                dateTimeZoneForTimeZone = forID(property);
            }
        } catch (RuntimeException unused) {
        }
        if (dateTimeZoneForTimeZone == null) {
            try {
                dateTimeZoneForTimeZone = forTimeZone(TimeZone.getDefault());
            } catch (IllegalArgumentException unused2) {
            }
        }
        if (dateTimeZoneForTimeZone == null) {
            dateTimeZoneForTimeZone = UTC;
        }
        AtomicReference<DateTimeZone> atomicReference = cDefault;
        return !CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, dateTimeZoneForTimeZone) ? atomicReference.get() : dateTimeZoneForTimeZone;
    }

    @FromString
    public static DateTimeZone forID(String str) {
        if (str == null) {
            return getDefault();
        }
        if (str.equals("UTC")) {
            return UTC;
        }
        DateTimeZone zone = getProvider().getZone(str);
        if (zone != null) {
            return zone;
        }
        if (str.startsWith(MqttTopic.SINGLE_LEVEL_WILDCARD) || str.startsWith("-")) {
            int offset = parseOffset(str);
            if (offset == 0) {
                return UTC;
            }
            return fixedOffsetZone(printOffset(offset), offset);
        }
        throw new IllegalArgumentException("The datetime zone id '" + str + "' is not recognised");
    }

    public static DateTimeZone forOffsetHours(int i) throws IllegalArgumentException {
        return forOffsetHoursMinutes(i, 0);
    }

    public static DateTimeZone forOffsetHoursMinutes(int i, int i2) throws IllegalArgumentException {
        if (i == 0 && i2 == 0) {
            return UTC;
        }
        if (i < -23 || i > 23) {
            throw new IllegalArgumentException("Hours out of range: " + i);
        }
        if (i2 < -59 || i2 > 59) {
            throw new IllegalArgumentException("Minutes out of range: " + i2);
        }
        if (i > 0 && i2 < 0) {
            throw new IllegalArgumentException("Positive hours must not have negative minutes: " + i2);
        }
        int i3 = i * 60;
        try {
            return forOffsetMillis(FieldUtils.safeMultiply(i3 < 0 ? i3 - Math.abs(i2) : i3 + i2, HardCodedRefreshStrategy.MS_IN_MINUTE));
        } catch (ArithmeticException unused) {
            throw new IllegalArgumentException("Offset is too large");
        }
    }

    public static DateTimeZone forOffsetMillis(int i) {
        if (i < -86399999 || i > 86399999) {
            throw new IllegalArgumentException("Millis out of range: " + i);
        }
        return fixedOffsetZone(printOffset(i), i);
    }

    public static DateTimeZone forTimeZone(TimeZone timeZone) {
        char cCharAt;
        if (timeZone == null) {
            return getDefault();
        }
        String id = timeZone.getID();
        if (id == null) {
            throw new IllegalArgumentException("The TimeZone id must not be null");
        }
        if (id.equals("UTC")) {
            return UTC;
        }
        String convertedId = getConvertedId(id);
        Provider provider = getProvider();
        DateTimeZone zone = convertedId != null ? provider.getZone(convertedId) : null;
        if (zone == null) {
            zone = provider.getZone(id);
        }
        if (zone != null) {
            return zone;
        }
        if (convertedId == null && (id.startsWith("GMT+") || id.startsWith("GMT-"))) {
            String strSubstring = id.substring(3);
            if (strSubstring.length() > 2 && (cCharAt = strSubstring.charAt(1)) > '9' && Character.isDigit(cCharAt)) {
                strSubstring = convertToAsciiNumber(strSubstring);
            }
            int offset = parseOffset(strSubstring);
            if (offset == 0) {
                return UTC;
            }
            return fixedOffsetZone(printOffset(offset), offset);
        }
        throw new IllegalArgumentException("The datetime zone id '" + id + "' is not recognised");
    }

    private static String convertToAsciiNumber(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            int iDigit = Character.digit(sb.charAt(i), 10);
            if (iDigit >= 0) {
                sb.setCharAt(i, (char) (iDigit + 48));
            }
        }
        return sb.toString();
    }

    private static DateTimeZone fixedOffsetZone(String str, int i) {
        if (i == 0) {
            return UTC;
        }
        return new FixedDateTimeZone(str, null, i, i);
    }

    public static Set<String> getAvailableIDs() {
        return getProvider().getAvailableIDs();
    }

    public static Provider getProvider() {
        AtomicReference<Provider> atomicReference = cProvider;
        Provider provider = atomicReference.get();
        if (provider != null) {
            return provider;
        }
        Provider defaultProvider = getDefaultProvider();
        return !CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, defaultProvider) ? atomicReference.get() : defaultProvider;
    }

    private static Provider validateProvider(Provider provider) {
        Set<String> availableIDs = provider.getAvailableIDs();
        if (availableIDs == null || availableIDs.size() == 0) {
            throw new IllegalArgumentException("The provider doesn't have any available ids");
        }
        if (!availableIDs.contains("UTC")) {
            throw new IllegalArgumentException("The provider doesn't support UTC");
        }
        if (UTC.equals(provider.getZone("UTC"))) {
            return provider;
        }
        throw new IllegalArgumentException("Invalid UTC zone provided");
    }

    private static Provider getDefaultProvider() {
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.Provider");
            if (property != null) {
                try {
                    Class<?> cls = Class.forName(property, false, DateTimeZone.class.getClassLoader());
                    if (!Provider.class.isAssignableFrom(cls)) {
                        throw new IllegalArgumentException("System property referred to class that does not implement " + Provider.class);
                    }
                    return validateProvider((Provider) cls.asSubclass(Provider.class).getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SecurityException unused) {
        }
        try {
            String property2 = System.getProperty("org.joda.time.DateTimeZone.Folder");
            if (property2 != null) {
                try {
                    return validateProvider(new ZoneInfoProvider(new File(property2)));
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
        } catch (SecurityException unused2) {
        }
        try {
            return validateProvider(new ZoneInfoProvider("org/joda/time/tz/data"));
        } catch (Exception e3) {
            e3.printStackTrace();
            return new UTCProvider();
        }
    }

    public static NameProvider getNameProvider() {
        AtomicReference<NameProvider> atomicReference = cNameProvider;
        NameProvider nameProvider = atomicReference.get();
        if (nameProvider != null) {
            return nameProvider;
        }
        NameProvider defaultNameProvider = getDefaultNameProvider();
        return !CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, defaultNameProvider) ? atomicReference.get() : defaultNameProvider;
    }

    private static NameProvider getDefaultNameProvider() {
        NameProvider nameProvider = null;
        try {
            String property = System.getProperty("org.joda.time.DateTimeZone.NameProvider");
            if (property != null) {
                try {
                    Class<?> cls = Class.forName(property, false, DateTimeZone.class.getClassLoader());
                    if (!NameProvider.class.isAssignableFrom(cls)) {
                        throw new IllegalArgumentException("System property referred to class that does not implement " + NameProvider.class);
                    }
                    nameProvider = (NameProvider) cls.asSubclass(NameProvider.class).getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SecurityException unused) {
        }
        return nameProvider == null ? new DefaultNameProvider() : nameProvider;
    }

    private static String getConvertedId(String str) {
        return LazyInit.CONVERSION_MAP.get(str);
    }

    private static int parseOffset(String str) {
        return -((int) LazyInit.OFFSET_FORMATTER.parseMillis(str));
    }

    private static String printOffset(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (i >= 0) {
            stringBuffer.append('+');
        } else {
            stringBuffer.append('-');
            i = -i;
        }
        int i2 = i / 3600000;
        FormatUtils.appendPaddedInteger(stringBuffer, i2, 2);
        int i3 = i - (i2 * 3600000);
        int i4 = i3 / HardCodedRefreshStrategy.MS_IN_MINUTE;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i4, 2);
        int i5 = i3 - (i4 * HardCodedRefreshStrategy.MS_IN_MINUTE);
        if (i5 == 0) {
            return stringBuffer.toString();
        }
        int i6 = i5 / 1000;
        stringBuffer.append(':');
        FormatUtils.appendPaddedInteger(stringBuffer, i6, 2);
        int i7 = i5 - (i6 * 1000);
        if (i7 == 0) {
            return stringBuffer.toString();
        }
        stringBuffer.append('.');
        FormatUtils.appendPaddedInteger(stringBuffer, i7, 3);
        return stringBuffer.toString();
    }

    protected DateTimeZone(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        this.iID = str;
    }

    @ToString
    public final String getID() {
        return this.iID;
    }

    public String getShortName(long j, Locale locale) {
        String shortName;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            shortName = ((DefaultNameProvider) nameProvider).getShortName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            shortName = nameProvider.getShortName(locale, this.iID, nameKey);
        }
        return shortName != null ? shortName : printOffset(getOffset(j));
    }

    public String getName(long j, Locale locale) {
        String name;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        String nameKey = getNameKey(j);
        if (nameKey == null) {
            return this.iID;
        }
        NameProvider nameProvider = getNameProvider();
        if (nameProvider instanceof DefaultNameProvider) {
            name = ((DefaultNameProvider) nameProvider).getName(locale, this.iID, nameKey, isStandardOffset(j));
        } else {
            name = nameProvider.getName(locale, this.iID, nameKey);
        }
        return name != null ? name : printOffset(getOffset(j));
    }

    public boolean isStandardOffset(long j) {
        return getOffset(j) == getStandardOffset(j);
    }

    public int getOffsetFromLocal(long j) {
        int offset = getOffset(j);
        long j2 = j - ((long) offset);
        int offset2 = getOffset(j2);
        if (offset != offset2) {
            if (offset - offset2 < 0) {
                long jNextTransition = nextTransition(j2);
                if (jNextTransition == j2) {
                    jNextTransition = Long.MAX_VALUE;
                }
                long j3 = j - ((long) offset2);
                long jNextTransition2 = nextTransition(j3);
                if (jNextTransition != (jNextTransition2 != j3 ? jNextTransition2 : Long.MAX_VALUE)) {
                    return offset;
                }
            }
        } else if (offset >= 0) {
            long jPreviousTransition = previousTransition(j2);
            if (jPreviousTransition < j2) {
                int offset3 = getOffset(jPreviousTransition);
                if (j2 - jPreviousTransition <= offset3 - offset) {
                    return offset3;
                }
            }
        }
        return offset2;
    }

    public long convertUTCToLocal(long j) {
        long offset = getOffset(j);
        long j2 = j + offset;
        if ((j ^ j2) >= 0 || (j ^ offset) < 0) {
            return j2;
        }
        throw new ArithmeticException("Adding time zone offset caused overflow");
    }

    public long convertLocalToUTC(long j, boolean z, long j2) {
        int offset = getOffset(j2);
        long j3 = j - ((long) offset);
        return getOffset(j3) == offset ? j3 : convertLocalToUTC(j, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long convertLocalToUTC(long r11, boolean r13) {
        /*
            r10 = this;
            int r0 = r10.getOffset(r11)
            long r1 = (long) r0
            long r1 = r11 - r1
            int r3 = r10.getOffset(r1)
            if (r0 == r3) goto L3d
            if (r13 != 0) goto L11
            if (r0 >= 0) goto L3d
        L11:
            long r4 = r10.nextTransition(r1)
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            if (r1 != 0) goto L1f
            r4 = r6
        L1f:
            long r1 = (long) r3
            long r1 = r11 - r1
            long r8 = r10.nextTransition(r1)
            int r1 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r1 != 0) goto L2b
            goto L2c
        L2b:
            r6 = r8
        L2c:
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 == 0) goto L3d
            if (r13 != 0) goto L33
            goto L3e
        L33:
            org.joda.time.IllegalInstantException r13 = new org.joda.time.IllegalInstantException
            java.lang.String r0 = r10.getID()
            r13.<init>(r11, r0)
            throw r13
        L3d:
            r0 = r3
        L3e:
            long r0 = (long) r0
            long r2 = r11 - r0
            long r4 = r11 ^ r2
            r6 = 0
            int r13 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r13 >= 0) goto L57
            long r11 = r11 ^ r0
            int r11 = (r11 > r6 ? 1 : (r11 == r6 ? 0 : -1))
            if (r11 < 0) goto L4f
            goto L57
        L4f:
            java.lang.ArithmeticException r11 = new java.lang.ArithmeticException
            java.lang.String r12 = "Subtracting time zone offset caused overflow"
            r11.<init>(r12)
            throw r11
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.DateTimeZone.convertLocalToUTC(long, boolean):long");
    }

    public long getMillisKeepLocal(DateTimeZone dateTimeZone, long j) {
        if (dateTimeZone == null) {
            dateTimeZone = getDefault();
        }
        DateTimeZone dateTimeZone2 = dateTimeZone;
        return dateTimeZone2 == this ? j : dateTimeZone2.convertLocalToUTC(convertUTCToLocal(j), false, j);
    }

    public TimeZone toTimeZone() {
        return TimeZone.getTimeZone(this.iID);
    }

    public int hashCode() {
        return getID().hashCode() + 57;
    }

    public String toString() {
        return getID();
    }

    static final class LazyInit {
        static final Map<String, String> CONVERSION_MAP = buildMap();
        static final DateTimeFormatter OFFSET_FORMATTER = buildFormatter();

        LazyInit() {
        }

        private static DateTimeFormatter buildFormatter() {
            return new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter().withChronology(new BaseChronology() { // from class: org.joda.time.DateTimeZone.LazyInit.1
                @Override // org.joda.time.Chronology
                public DateTimeZone getZone() {
                    return null;
                }

                @Override // org.joda.time.Chronology
                public Chronology withUTC() {
                    return this;
                }

                @Override // org.joda.time.Chronology
                public Chronology withZone(DateTimeZone dateTimeZone) {
                    return this;
                }

                @Override // org.joda.time.Chronology
                public String toString() {
                    return AnonymousClass1.class.getName();
                }
            });
        }

        private static Map<String, String> buildMap() {
            HashMap map = new HashMap();
            map.put("GMT", "UTC");
            map.put("WET", "WET");
            map.put("CET", "CET");
            map.put("MET", "CET");
            map.put("ECT", "CET");
            map.put("EET", "EET");
            map.put("MIT", "Pacific/Apia");
            map.put("HST", "Pacific/Honolulu");
            map.put("AST", "America/Anchorage");
            map.put("PST", "America/Los_Angeles");
            map.put("MST", "America/Denver");
            map.put("PNT", "America/Phoenix");
            map.put("CST", "America/Chicago");
            map.put("EST", "America/New_York");
            map.put("IET", "America/Indiana/Indianapolis");
            map.put("PRT", "America/Puerto_Rico");
            map.put("CNT", "America/St_Johns");
            map.put("AGT", "America/Argentina/Buenos_Aires");
            map.put("BET", "America/Sao_Paulo");
            map.put("ART", "Africa/Cairo");
            map.put("CAT", "Africa/Harare");
            map.put("EAT", "Africa/Addis_Ababa");
            map.put("NET", "Asia/Yerevan");
            map.put("PLT", "Asia/Karachi");
            map.put("IST", "Asia/Kolkata");
            map.put("BST", "Asia/Dhaka");
            map.put("VST", "Asia/Ho_Chi_Minh");
            map.put("CTT", "Asia/Shanghai");
            map.put("JST", "Asia/Tokyo");
            map.put("ACT", "Australia/Darwin");
            map.put("AET", "Australia/Sydney");
            map.put("SST", "Pacific/Guadalcanal");
            map.put("NST", "Pacific/Auckland");
            return Collections.unmodifiableMap(map);
        }
    }
}
