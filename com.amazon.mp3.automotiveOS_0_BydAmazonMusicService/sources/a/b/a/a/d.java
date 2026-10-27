package a.b.a.a;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f44a;

    public d(a aVar) {
        this.f44a = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Iterator<a.b.a.a.o.e.b> itB = this.f44a.f.b();
        while (itB.hasNext()) {
            a.b.a.a.o.e.b next = itB.next();
            a aVar = this.f44a;
            aVar.d.a(aVar.c, "containing view should be visible");
            next.getContainingView().setAlpha(1.0f);
        }
    }
}
