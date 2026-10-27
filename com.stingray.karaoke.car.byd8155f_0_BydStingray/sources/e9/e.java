package e9;

import A7.m;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import r9.J;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Closeable {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f14503m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f14504n;
    public final /* synthetic */ g o;

    public e(g gVar, String str, long j3, ArrayList arrayList, long[] jArr) {
        m.f(str, "key");
        m.f(jArr, "lengths");
        this.o = gVar;
        this.l = str;
        this.f14503m = j3;
        this.f14504n = arrayList;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Iterator it = this.f14504n.iterator();
        while (it.hasNext()) {
            d9.b.c((J) it.next());
        }
    }
}
