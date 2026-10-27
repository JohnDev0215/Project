package org.joda.time;

/* JADX INFO: loaded from: classes6.dex */
public interface ReadableInstant extends Comparable<ReadableInstant> {
    Chronology getChronology();

    long getMillis();

    boolean isBefore(ReadableInstant readableInstant);

    Instant toInstant();
}
