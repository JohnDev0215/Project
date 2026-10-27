package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BasePartial extends AbstractPartial implements Serializable {
    private final Chronology iChronology;
    private final int[] iValues;

    protected BasePartial() {
        this(DateTimeUtils.currentTimeMillis(), (Chronology) null);
    }

    protected BasePartial(long j, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iChronology = chronology2.withUTC();
        this.iValues = chronology2.get(this, j);
    }

    protected BasePartial(int[] iArr, Chronology chronology) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iChronology = chronology2.withUTC();
        chronology2.validate(this, iArr);
        this.iValues = iArr;
    }

    @Override // org.joda.time.ReadablePartial
    public int getValue(int i) {
        return this.iValues[i];
    }

    @Override // org.joda.time.ReadablePartial
    public Chronology getChronology() {
        return this.iChronology;
    }
}
