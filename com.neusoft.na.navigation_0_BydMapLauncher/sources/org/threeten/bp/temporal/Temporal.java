package org.threeten.bp.temporal;

/* JADX INFO: loaded from: classes4.dex */
public interface Temporal extends TemporalAccessor {
    boolean isSupported(TemporalUnit temporalUnit);

    Temporal minus(long j, TemporalUnit temporalUnit);

    Temporal minus(TemporalAmount temporalAmount);

    Temporal plus(long j, TemporalUnit temporalUnit);

    Temporal plus(TemporalAmount temporalAmount);

    long until(Temporal temporal, TemporalUnit temporalUnit);

    Temporal with(TemporalAdjuster temporalAdjuster);

    Temporal with(TemporalField temporalField, long j);
}
