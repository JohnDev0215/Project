package a.b.a.a.o.d.b;

import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f129a;
    public final /* synthetic */ long b;
    public final /* synthetic */ long c;

    public o(m mVar, long j, long j2) {
        this.f129a = mVar;
        this.b = j;
        this.c = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Pair<Long, Long> pair;
        Pair<Long, Long>[] pairArr = this.f129a.f;
        int length = pairArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                pair = null;
                break;
            }
            pair = pairArr[i];
            if (pair.getFirst().longValue() == this.b) {
                break;
            } else {
                i++;
            }
        }
        if (pair != null) {
            this.f129a.h.a(Math.max(0, Math.min((int) (this.c - pair.getSecond().longValue()), m.f125a)));
        }
    }
}
