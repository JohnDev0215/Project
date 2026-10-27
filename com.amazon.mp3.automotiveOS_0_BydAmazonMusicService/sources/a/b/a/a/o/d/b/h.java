package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;
import a.b.a.a.o.d.b.a.h;

/* JADX INFO: loaded from: classes.dex */
public final class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f117a;

    public h(a aVar) {
        this.f117a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar = this.f117a;
        aVar.c.a(aVar.b, "Stop called externally");
        a aVar2 = this.f117a;
        aVar2.c.a(aVar2.b, "enterStoppedState: begin");
        a.e eVar = aVar2.d;
        if (eVar == a.e.TERMINATED) {
            aVar2.c.e(aVar2.b, "Unable to stop an TERMINATED instance");
            return;
        }
        a.e eVar2 = a.e.STOPPED;
        if (eVar != eVar2) {
            aVar2.a(eVar2);
            aVar2.g.getAndSet(0);
            aVar2.a();
            aVar2.q = new j(aVar2.s, aVar2.new h());
            aVar2.c.a(aVar2.b, "enterStoppedState: end");
            return;
        }
        aVar2.c.a(aVar2.b, "Attempting to enter already current state: STOPPED");
    }
}
