package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.AbstractInstant;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormatter;

/* JADX INFO: loaded from: classes6.dex */
public final class Instant extends AbstractInstant implements Serializable {
    public static final Instant EPOCH = new Instant(0);
    private final long iMillis;

    @Override // org.joda.time.base.AbstractInstant, org.joda.time.ReadableInstant
    public Instant toInstant() {
        return this;
    }

    public static Instant now() {
        return new Instant();
    }

    public static Instant parse(String str, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.parseDateTime(str).toInstant();
    }

    public Instant() {
        this.iMillis = DateTimeUtils.currentTimeMillis();
    }

    public Instant(long j) {
        this.iMillis = j;
    }

    @Override // org.joda.time.ReadableInstant
    public long getMillis() {
        return this.iMillis;
    }

    @Override // org.joda.time.ReadableInstant
    public Chronology getChronology() {
        return ISOChronology.getInstanceUTC();
    }
}
