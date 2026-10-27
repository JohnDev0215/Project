package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f118a;

    public i(a aVar) {
        this.f118a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar = this.f118a;
        aVar.c.a(aVar.b, "Teardown called externally");
        a aVar2 = this.f118a;
        aVar2.c.a(aVar2.b, "enterTerminatedState: begin");
        a.e eVar = aVar2.d;
        a.e eVar2 = a.e.TERMINATED;
        if (eVar == eVar2) {
            aVar2.c.a(aVar2.b, "Attempting to enter already current state: TERMINATED");
            return;
        }
        aVar2.a(eVar2);
        aVar2.a();
        aVar2.p.quitSafely();
        aVar2.o.shutdownNow();
        aVar2.n.shutdownNow();
        l lVar = aVar2.r;
        if (lVar != null) {
            lVar.b.d.sendEmptyMessage(2);
        }
        aVar2.c.a(aVar2.b, "enterTerminatedState: end");
    }
}
