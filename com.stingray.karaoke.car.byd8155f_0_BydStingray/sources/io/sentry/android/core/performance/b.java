package io.sentry.android.core.performance;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Comparable {
    public final f l = new f();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f f16272m = new f();

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        b bVar = (b) obj;
        int iCompare = Long.compare(this.l.f16288n, bVar.l.f16288n);
        return iCompare == 0 ? Long.compare(this.f16272m.f16288n, bVar.f16272m.f16288n) : iCompare;
    }
}
