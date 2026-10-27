package k3;

import android.util.Log;
import l3.C1736A;
import l3.C1754j;

/* JADX INFO: renamed from: k3.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1637e0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1650j0 f17940m;

    public /* synthetic */ RunnableC1637e0(C1650j0 c1650j0, int i7) {
        this.l = i7;
        this.f17940m = c1650j0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                C1650j0 c1650j0 = this.f17940m;
                if (((C1736A) c1650j0.f18004h.f16569m).f18508e.a() == null) {
                    c1650j0.b1();
                    break;
                }
                break;
            default:
                C1650j0 c1650j02 = this.f17940m;
                c1650j02.getClass();
                C1754j c1754j = new C1754j(c1650j02.f17999a, c1650j02.c.f17758a.o(), new h3.y(c1650j02), c1650j02.f18000b.c.Q0());
                c1650j02.f18005i = c1754j;
                Log.d("MediaBrowserCompat", "Connecting to a MediaBrowserService.");
                c1754j.f18578a.f18571b.connect();
                break;
        }
    }
}
