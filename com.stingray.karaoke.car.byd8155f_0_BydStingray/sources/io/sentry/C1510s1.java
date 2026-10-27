package io.sentry;

import java.util.Date;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.s1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1510s1 extends AbstractC1453b1 {
    public final Date l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f16885m;

    public C1510s1(Date date, long j3) {
        this.l = date;
        this.f16885m = j3;
    }

    @Override // io.sentry.AbstractC1453b1, java.lang.Comparable
    /* JADX INFO: renamed from: a */
    public final int compareTo(AbstractC1453b1 abstractC1453b1) {
        if (!(abstractC1453b1 instanceof C1510s1)) {
            return super.compareTo(abstractC1453b1);
        }
        C1510s1 c1510s1 = (C1510s1) abstractC1453b1;
        long time = this.l.getTime();
        long time2 = c1510s1.l.getTime();
        return time == time2 ? Long.valueOf(this.f16885m).compareTo(Long.valueOf(c1510s1.f16885m)) : Long.valueOf(time).compareTo(Long.valueOf(time2));
    }

    @Override // io.sentry.AbstractC1453b1
    public final long b(AbstractC1453b1 abstractC1453b1) {
        return abstractC1453b1 instanceof C1510s1 ? this.f16885m - ((C1510s1) abstractC1453b1).f16885m : super.b(abstractC1453b1);
    }

    @Override // io.sentry.AbstractC1453b1
    public final long c(AbstractC1453b1 abstractC1453b1) {
        if (abstractC1453b1 == null || !(abstractC1453b1 instanceof C1510s1)) {
            return super.c(abstractC1453b1);
        }
        C1510s1 c1510s1 = (C1510s1) abstractC1453b1;
        int iCompareTo = compareTo(abstractC1453b1);
        long j3 = this.f16885m;
        long j10 = c1510s1.f16885m;
        if (iCompareTo < 0) {
            return d() + (j10 - j3);
        }
        return c1510s1.d() + (j3 - j10);
    }

    @Override // io.sentry.AbstractC1453b1
    public final long d() {
        return this.l.getTime() * 1000000;
    }

    public C1510s1() {
        this(AbstractC1985B.f(), System.nanoTime());
    }
}
