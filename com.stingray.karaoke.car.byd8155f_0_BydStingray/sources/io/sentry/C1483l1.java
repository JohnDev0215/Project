package io.sentry;

import java.time.Instant;

/* JADX INFO: renamed from: io.sentry.l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1483l1 extends AbstractC1453b1 {
    public final Instant l = Instant.now();

    @Override // io.sentry.AbstractC1453b1
    public final long d() {
        Instant instant = this.l;
        return (instant.getEpochSecond() * 1000000000) + ((long) instant.getNano());
    }
}
