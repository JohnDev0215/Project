package c.a.g.h;

import f.p;
import java.io.IOException;

/* JADX INFO: compiled from: CountingSink.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends f.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2689d;

    public a(p pVar) {
        super(pVar);
        this.f2689d = 0L;
    }

    @Override // f.f, f.p
    public void h(f.c cVar, long j) throws IOException {
        super.h(cVar, j);
        this.f2689d += j;
    }
}
