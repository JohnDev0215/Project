package org.threeten.bp;

import com.neusoft.na.common.FileUtils;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Marker;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.Temporal;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjuster;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalQueries;
import org.threeten.bp.temporal.TemporalQuery;
import org.threeten.bp.temporal.UnsupportedTemporalTypeException;
import org.threeten.bp.temporal.ValueRange;
import org.threeten.bp.zone.ZoneRules;

/* JADX INFO: loaded from: classes4.dex */
public final class ZoneOffset extends ZoneId implements TemporalAccessor, TemporalAdjuster, Comparable<ZoneOffset>, Serializable {
    private static final int MINUTES_PER_HOUR = 60;
    private static final int SECONDS_PER_HOUR = 3600;
    private static final int SECONDS_PER_MINUTE = 60;
    private static final long serialVersionUID = 2357656521762053153L;
    private final transient String id;
    private final int totalSeconds;
    public static final TemporalQuery<ZoneOffset> FROM = new TemporalQuery<ZoneOffset>() { // from class: org.threeten.bp.ZoneOffset.1
        @Override // org.threeten.bp.temporal.TemporalQuery
        public ZoneOffset queryFrom(TemporalAccessor temporalAccessor) {
            return ZoneOffset.from(temporalAccessor);
        }
    };
    private static final ConcurrentMap<Integer, ZoneOffset> SECONDS_CACHE = new ConcurrentHashMap(16, 0.75f, 4);
    private static final ConcurrentMap<String, ZoneOffset> ID_CACHE = new ConcurrentHashMap(16, 0.75f, 4);
    public static final ZoneOffset UTC = ofTotalSeconds(0);
    public static final ZoneOffset MIN = ofTotalSeconds(-64800);
    private static final int MAX_SECONDS = 64800;
    public static final ZoneOffset MAX = ofTotalSeconds(MAX_SECONDS);

    private static int totalSeconds(int i, int i2, int i3) {
        return (i * 3600) + (i2 * 60) + i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.threeten.bp.ZoneOffset of(java.lang.String r7) {
        /*
            java.lang.String r0 = "offsetId"
            org.threeten.bp.jdk8.Jdk8Methods.requireNonNull(r7, r0)
            java.util.concurrent.ConcurrentMap<java.lang.String, org.threeten.bp.ZoneOffset> r0 = org.threeten.bp.ZoneOffset.ID_CACHE
            java.lang.Object r0 = r0.get(r7)
            org.threeten.bp.ZoneOffset r0 = (org.threeten.bp.ZoneOffset) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            int r0 = r7.length()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L6c
            r1 = 3
            if (r0 == r1) goto L8b
            r4 = 5
            if (r0 == r4) goto L62
            r5 = 6
            r6 = 4
            if (r0 == r5) goto L59
            r5 = 7
            if (r0 == r5) goto L4c
            r1 = 9
            if (r0 != r1) goto L37
            int r0 = parseNumber(r7, r2, r3)
            int r1 = parseNumber(r7, r6, r2)
            int r2 = parseNumber(r7, r5, r2)
            goto L91
        L37:
            org.threeten.bp.DateTimeException r0 = new org.threeten.bp.DateTimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid ID for ZoneOffset, invalid format: "
            r1.<init>(r2)
            java.lang.StringBuilder r7 = r1.append(r7)
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            throw r0
        L4c:
            int r0 = parseNumber(r7, r2, r3)
            int r1 = parseNumber(r7, r1, r3)
            int r2 = parseNumber(r7, r4, r3)
            goto L91
        L59:
            int r0 = parseNumber(r7, r2, r3)
            int r1 = parseNumber(r7, r6, r2)
            goto L6a
        L62:
            int r0 = parseNumber(r7, r2, r3)
            int r1 = parseNumber(r7, r1, r3)
        L6a:
            r2 = r3
            goto L91
        L6c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            char r1 = r7.charAt(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "0"
            java.lang.StringBuilder r0 = r0.append(r1)
            char r7 = r7.charAt(r2)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r7 = r7.toString()
        L8b:
            int r0 = parseNumber(r7, r2, r3)
            r1 = r3
            r2 = r1
        L91:
            char r3 = r7.charAt(r3)
            r4 = 43
            r5 = 45
            if (r3 == r4) goto Lb3
            if (r3 != r5) goto L9e
            goto Lb3
        L9e:
            org.threeten.bp.DateTimeException r0 = new org.threeten.bp.DateTimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid ID for ZoneOffset, plus/minus not found when expected: "
            r1.<init>(r2)
            java.lang.StringBuilder r7 = r1.append(r7)
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            throw r0
        Lb3:
            if (r3 != r5) goto Lbd
            int r7 = -r0
            int r0 = -r1
            int r1 = -r2
            org.threeten.bp.ZoneOffset r7 = ofHoursMinutesSeconds(r7, r0, r1)
            return r7
        Lbd:
            org.threeten.bp.ZoneOffset r7 = ofHoursMinutesSeconds(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.ZoneOffset.of(java.lang.String):org.threeten.bp.ZoneOffset");
    }

    private static int parseNumber(CharSequence charSequence, int i, boolean z) {
        if (z && charSequence.charAt(i - 1) != ':') {
            throw new DateTimeException("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) charSequence));
        }
        char cCharAt = charSequence.charAt(i);
        char cCharAt2 = charSequence.charAt(i + 1);
        if (cCharAt < '0' || cCharAt > '9' || cCharAt2 < '0' || cCharAt2 > '9') {
            throw new DateTimeException("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) charSequence));
        }
        return ((cCharAt - '0') * 10) + (cCharAt2 - '0');
    }

    public static ZoneOffset ofHours(int i) {
        return ofHoursMinutesSeconds(i, 0, 0);
    }

    public static ZoneOffset ofHoursMinutes(int i, int i2) {
        return ofHoursMinutesSeconds(i, i2, 0);
    }

    public static ZoneOffset ofHoursMinutesSeconds(int i, int i2, int i3) {
        validate(i, i2, i3);
        return ofTotalSeconds(totalSeconds(i, i2, i3));
    }

    public static ZoneOffset from(TemporalAccessor temporalAccessor) {
        ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.query(TemporalQueries.offset());
        if (zoneOffset != null) {
            return zoneOffset;
        }
        throw new DateTimeException("Unable to obtain ZoneOffset from TemporalAccessor: " + temporalAccessor + ", type " + temporalAccessor.getClass().getName());
    }

    private static void validate(int i, int i2, int i3) {
        if (i < -18 || i > 18) {
            throw new DateTimeException("Zone offset hours not in valid range: value " + i + " is not in the range -18 to 18");
        }
        if (i > 0) {
            if (i2 < 0 || i3 < 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be positive because hours is positive");
            }
        } else if (i < 0) {
            if (i2 > 0 || i3 > 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be negative because hours is negative");
            }
        } else if ((i2 > 0 && i3 < 0) || (i2 < 0 && i3 > 0)) {
            throw new DateTimeException("Zone offset minutes and seconds must have the same sign");
        }
        if (Math.abs(i2) > 59) {
            throw new DateTimeException("Zone offset minutes not in valid range: abs(value) " + Math.abs(i2) + " is not in the range 0 to 59");
        }
        if (Math.abs(i3) > 59) {
            throw new DateTimeException("Zone offset seconds not in valid range: abs(value) " + Math.abs(i3) + " is not in the range 0 to 59");
        }
        if (Math.abs(i) == 18) {
            if (Math.abs(i2) > 0 || Math.abs(i3) > 0) {
                throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
            }
        }
    }

    public static ZoneOffset ofTotalSeconds(int i) {
        if (Math.abs(i) > MAX_SECONDS) {
            throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
        }
        if (i % 900 == 0) {
            Integer numValueOf = Integer.valueOf(i);
            ConcurrentMap<Integer, ZoneOffset> concurrentMap = SECONDS_CACHE;
            ZoneOffset zoneOffset = concurrentMap.get(numValueOf);
            if (zoneOffset != null) {
                return zoneOffset;
            }
            concurrentMap.putIfAbsent(numValueOf, new ZoneOffset(i));
            ZoneOffset zoneOffset2 = concurrentMap.get(numValueOf);
            ID_CACHE.putIfAbsent(zoneOffset2.getId(), zoneOffset2);
            return zoneOffset2;
        }
        return new ZoneOffset(i);
    }

    private ZoneOffset(int i) {
        this.totalSeconds = i;
        this.id = buildId(i);
    }

    private static String buildId(int i) {
        if (i == 0) {
            return "Z";
        }
        int iAbs = Math.abs(i);
        StringBuilder sb = new StringBuilder();
        int i2 = iAbs / 3600;
        int i3 = (iAbs / 60) % 60;
        sb.append(i < 0 ? "-" : Marker.ANY_NON_NULL_MARKER).append(i2 < 10 ? FileUtils.DEFAULT_VERSION : "").append(i2).append(i3 < 10 ? ":0" : ":").append(i3);
        int i4 = iAbs % 60;
        if (i4 != 0) {
            sb.append(i4 >= 10 ? ":" : ":0").append(i4);
        }
        return sb.toString();
    }

    public int getTotalSeconds() {
        return this.totalSeconds;
    }

    @Override // org.threeten.bp.ZoneId
    public String getId() {
        return this.id;
    }

    @Override // org.threeten.bp.ZoneId
    public ZoneRules getRules() {
        return ZoneRules.of(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public boolean isSupported(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? temporalField == ChronoField.OFFSET_SECONDS : temporalField != null && temporalField.isSupportedBy(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public ValueRange range(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return temporalField.range();
        }
        if (temporalField instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return temporalField.rangeRefinedBy(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public int get(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return this.totalSeconds;
        }
        if (temporalField instanceof ChronoField) {
            throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
        }
        return range(temporalField).checkValidIntValue(getLong(temporalField), temporalField);
    }

    @Override // org.threeten.bp.temporal.TemporalAccessor
    public long getLong(TemporalField temporalField) {
        if (temporalField == ChronoField.OFFSET_SECONDS) {
            return this.totalSeconds;
        }
        if (temporalField instanceof ChronoField) {
            throw new DateTimeException("Unsupported field: " + temporalField);
        }
        return temporalField.getFrom(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.threeten.bp.temporal.TemporalAccessor
    public <R> R query(TemporalQuery<R> temporalQuery) {
        if (temporalQuery == TemporalQueries.offset() || temporalQuery == TemporalQueries.zone()) {
            return this;
        }
        if (temporalQuery == TemporalQueries.localDate() || temporalQuery == TemporalQueries.localTime() || temporalQuery == TemporalQueries.precision() || temporalQuery == TemporalQueries.chronology() || temporalQuery == TemporalQueries.zoneId()) {
            return null;
        }
        return temporalQuery.queryFrom(this);
    }

    @Override // org.threeten.bp.temporal.TemporalAdjuster
    public Temporal adjustInto(Temporal temporal) {
        return temporal.with(ChronoField.OFFSET_SECONDS, this.totalSeconds);
    }

    @Override // java.lang.Comparable
    public int compareTo(ZoneOffset zoneOffset) {
        return zoneOffset.totalSeconds - this.totalSeconds;
    }

    @Override // org.threeten.bp.ZoneId
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZoneOffset) && this.totalSeconds == ((ZoneOffset) obj).totalSeconds;
    }

    @Override // org.threeten.bp.ZoneId
    public int hashCode() {
        return this.totalSeconds;
    }

    @Override // org.threeten.bp.ZoneId
    public String toString() {
        return this.id;
    }

    private Object writeReplace() {
        return new Ser((byte) 8, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // org.threeten.bp.ZoneId
    void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(8);
        writeExternal(dataOutput);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        int i = this.totalSeconds;
        int i2 = i % 900 == 0 ? i / 900 : 127;
        dataOutput.writeByte(i2);
        if (i2 == 127) {
            dataOutput.writeInt(i);
        }
    }

    static ZoneOffset readExternal(DataInput dataInput) throws IOException {
        byte b = dataInput.readByte();
        return b == 127 ? ofTotalSeconds(dataInput.readInt()) : ofTotalSeconds(b * 900);
    }
}
