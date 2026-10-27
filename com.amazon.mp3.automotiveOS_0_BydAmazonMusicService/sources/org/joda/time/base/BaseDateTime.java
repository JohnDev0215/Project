package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.InstantConverter;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseDateTime extends AbstractDateTime implements Serializable {
    private volatile Chronology iChronology;
    private volatile long iMillis;

    protected long checkInstant(long j, Chronology chronology) {
        return j;
    }

    public BaseDateTime() {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance());
    }

    public BaseDateTime(DateTimeZone dateTimeZone) {
        this(DateTimeUtils.currentTimeMillis(), ISOChronology.getInstance(dateTimeZone));
    }

    public BaseDateTime(long j) {
        this(j, ISOChronology.getInstance());
    }

    public BaseDateTime(long j, DateTimeZone dateTimeZone) {
        this(j, ISOChronology.getInstance(dateTimeZone));
    }

    public BaseDateTime(long j, Chronology chronology) {
        this.iChronology = checkChronology(chronology);
        this.iMillis = checkInstant(j, this.iChronology);
        adjustForMinMax();
    }

    public BaseDateTime(Object obj, DateTimeZone dateTimeZone) {
        InstantConverter instantConverter = ConverterManager.getInstance().getInstantConverter(obj);
        Chronology chronologyCheckChronology = checkChronology(instantConverter.getChronology(obj, dateTimeZone));
        this.iChronology = chronologyCheckChronology;
        this.iMillis = checkInstant(instantConverter.getInstantMillis(obj, chronologyCheckChronology), chronologyCheckChronology);
        adjustForMinMax();
    }

    public BaseDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, DateTimeZone dateTimeZone) {
        this(i, i2, i3, i4, i5, i6, i7, ISOChronology.getInstance(dateTimeZone));
    }

    public BaseDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7, Chronology chronology) {
        this.iChronology = checkChronology(chronology);
        this.iMillis = checkInstant(this.iChronology.getDateTimeMillis(i, i2, i3, i4, i5, i6, i7), this.iChronology);
        adjustForMinMax();
    }

    private void adjustForMinMax() {
        if (this.iMillis == Long.MIN_VALUE || this.iMillis == Long.MAX_VALUE) {
            this.iChronology = this.iChronology.withUTC();
        }
    }

    protected Chronology checkChronology(Chronology chronology) {
        return DateTimeUtils.getChronology(chronology);
    }

    @Override // org.joda.time.ReadableInstant
    public long getMillis() {
        return this.iMillis;
    }

    @Override // org.joda.time.ReadableInstant
    public Chronology getChronology() {
        return this.iChronology;
    }

    protected void setMillis(long j) {
        this.iMillis = checkInstant(j, this.iChronology);
    }

    protected void setChronology(Chronology chronology) {
        this.iChronology = checkChronology(chronology);
    }
}
