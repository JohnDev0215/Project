package io.sentry;

/* JADX INFO: renamed from: io.sentry.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1453b1 implements Comparable {
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC1453b1 abstractC1453b1) {
        return Long.valueOf(d()).compareTo(Long.valueOf(abstractC1453b1.d()));
    }

    public long b(AbstractC1453b1 abstractC1453b1) {
        return d() - abstractC1453b1.d();
    }

    public long c(AbstractC1453b1 abstractC1453b1) {
        return (abstractC1453b1 == null || compareTo(abstractC1453b1) >= 0) ? d() : abstractC1453b1.d();
    }

    public abstract long d();
}
