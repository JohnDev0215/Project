package l2;

import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TreeSet f18492a = new TreeSet(new p());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18493b;

    public final void a(InterfaceC1735b interfaceC1735b, long j3) {
        while (this.f18493b + j3 > 52428800 && !this.f18492a.isEmpty()) {
            i iVar = (i) this.f18492a.first();
            t tVar = (t) interfaceC1735b;
            synchronized (tVar) {
                tVar.k(iVar);
            }
        }
    }

    public final void b(t tVar, u uVar) {
        this.f18492a.add(uVar);
        this.f18493b += uVar.f18479n;
        a(tVar, 0L);
    }
}
