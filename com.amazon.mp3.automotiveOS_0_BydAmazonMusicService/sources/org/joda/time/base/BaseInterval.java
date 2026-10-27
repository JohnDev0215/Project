package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseInterval extends AbstractInterval implements Serializable {
    private volatile Chronology iChronology;
    private volatile long iEndMillis;
    private volatile long iStartMillis;

    protected BaseInterval(long j, long j2, Chronology chronology) {
        this.iChronology = DateTimeUtils.getChronology(chronology);
        checkInterval(j, j2);
        this.iStartMillis = j;
        this.iEndMillis = j2;
    }

    protected BaseInterval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        if (readableInstant == null && readableInstant2 == null) {
            long jCurrentTimeMillis = DateTimeUtils.currentTimeMillis();
            this.iEndMillis = jCurrentTimeMillis;
            this.iStartMillis = jCurrentTimeMillis;
            this.iChronology = ISOChronology.getInstance();
            return;
        }
        this.iChronology = DateTimeUtils.getInstantChronology(readableInstant);
        this.iStartMillis = DateTimeUtils.getInstantMillis(readableInstant);
        this.iEndMillis = DateTimeUtils.getInstantMillis(readableInstant2);
        checkInterval(this.iStartMillis, this.iEndMillis);
    }

    protected BaseInterval(ReadableInstant readableInstant, ReadablePeriod readablePeriod) {
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        this.iChronology = instantChronology;
        this.iStartMillis = DateTimeUtils.getInstantMillis(readableInstant);
        if (readablePeriod == null) {
            this.iEndMillis = this.iStartMillis;
        } else {
            this.iEndMillis = instantChronology.add(readablePeriod, this.iStartMillis, 1);
        }
        checkInterval(this.iStartMillis, this.iEndMillis);
    }

    protected BaseInterval(ReadablePeriod readablePeriod, ReadableInstant readableInstant) {
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        this.iChronology = instantChronology;
        this.iEndMillis = DateTimeUtils.getInstantMillis(readableInstant);
        if (readablePeriod == null) {
            this.iStartMillis = this.iEndMillis;
        } else {
            this.iStartMillis = instantChronology.add(readablePeriod, this.iEndMillis, -1);
        }
        checkInterval(this.iStartMillis, this.iEndMillis);
    }

    @Override // org.joda.time.ReadableInterval
    public Chronology getChronology() {
        return this.iChronology;
    }

    @Override // org.joda.time.ReadableInterval
    public long getStartMillis() {
        return this.iStartMillis;
    }

    @Override // org.joda.time.ReadableInterval
    public long getEndMillis() {
        return this.iEndMillis;
    }
}
