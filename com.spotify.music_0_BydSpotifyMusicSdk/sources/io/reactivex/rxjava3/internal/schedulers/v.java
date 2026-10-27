package io.reactivex.rxjava3.internal.schedulers;

/* JADX INFO: loaded from: classes.dex */
public final class v implements Comparable {
    public final Runnable c;
    public final long e;
    public final int k;
    public volatile boolean l;

    public v(Runnable runnable, Long l, int i) {
        this.c = runnable;
        this.e = l.longValue();
        this.k = i;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        v vVar = (v) obj;
        int iCompare = Long.compare(this.e, vVar.e);
        return iCompare == 0 ? Integer.compare(this.k, vVar.k) : iCompare;
    }
}
