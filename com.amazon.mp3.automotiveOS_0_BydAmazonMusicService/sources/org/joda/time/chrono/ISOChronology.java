package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology;
import org.joda.time.field.DividedDateTimeField;
import org.joda.time.field.RemainderDateTimeField;

/* JADX INFO: loaded from: classes6.dex */
public final class ISOChronology extends AssembledChronology {
    private static final ISOChronology INSTANCE_UTC;
    private static final ConcurrentHashMap<DateTimeZone, ISOChronology> cCache;

    static {
        ConcurrentHashMap<DateTimeZone, ISOChronology> concurrentHashMap = new ConcurrentHashMap<>();
        cCache = concurrentHashMap;
        ISOChronology iSOChronology = new ISOChronology(GregorianChronology.getInstanceUTC());
        INSTANCE_UTC = iSOChronology;
        concurrentHashMap.put(DateTimeZone.UTC, iSOChronology);
    }

    public static ISOChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static ISOChronology getInstance() {
        return getInstance(DateTimeZone.getDefault());
    }

    public static ISOChronology getInstance(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        ConcurrentHashMap<DateTimeZone, ISOChronology> concurrentHashMap = cCache;
        ISOChronology iSOChronology = concurrentHashMap.get(dateTimeZone);
        if (iSOChronology != null) {
            return iSOChronology;
        }
        ISOChronology iSOChronology2 = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, dateTimeZone));
        ISOChronology iSOChronologyPutIfAbsent = concurrentHashMap.putIfAbsent(dateTimeZone, iSOChronology2);
        return iSOChronologyPutIfAbsent != null ? iSOChronologyPutIfAbsent : iSOChronology2;
    }

    private ISOChronology(Chronology chronology) {
        super(chronology, null);
    }

    @Override // org.joda.time.Chronology
    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    @Override // org.joda.time.Chronology
    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    @Override // org.joda.time.Chronology
    public String toString() {
        DateTimeZone zone = getZone();
        if (zone == null) {
            return "ISOChronology";
        }
        return "ISOChronology[" + zone.getID() + ']';
    }

    @Override // org.joda.time.chrono.AssembledChronology
    protected void assemble(AssembledChronology.Fields fields) {
        if (getBase().getZone() == DateTimeZone.UTC) {
            DividedDateTimeField dividedDateTimeField = new DividedDateTimeField(ISOYearOfEraDateTimeField.INSTANCE, DateTimeFieldType.centuryOfEra(), 100);
            fields.centuryOfEra = dividedDateTimeField;
            fields.centuries = dividedDateTimeField.getDurationField();
            fields.yearOfCentury = new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra, DateTimeFieldType.yearOfCentury());
            fields.weekyearOfCentury = new RemainderDateTimeField((DividedDateTimeField) fields.centuryOfEra, fields.weekyears, DateTimeFieldType.weekyearOfCentury());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ISOChronology) {
            return getZone().equals(((ISOChronology) obj).getZone());
        }
        return false;
    }

    public int hashCode() {
        return 800855 + getZone().hashCode();
    }
}
