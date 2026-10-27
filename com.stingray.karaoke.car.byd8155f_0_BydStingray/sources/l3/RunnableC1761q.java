package l3;

import android.os.IBinder;
import android.os.Messenger;

/* JADX INFO: renamed from: l3.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1761q implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1764u f18596m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1764u f18597n;

    public /* synthetic */ RunnableC1761q(C1764u c1764u, C1764u c1764u2, int i7) {
        this.l = i7;
        this.f18597n = c1764u;
        this.f18596m = c1764u2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                C1756l c1756l = (C1756l) ((AbstractServiceC1765v) this.f18597n.f18608m).f18612p.remove(((Messenger) this.f18596m.f18608m).getBinder());
                if (c1756l != null) {
                    C1764u c1764u = c1756l.f18582e;
                    c1764u.getClass();
                    ((Messenger) c1764u.f18608m).getBinder().unlinkToDeath(c1756l, 0);
                }
                break;
            default:
                IBinder binder = ((Messenger) this.f18596m.f18608m).getBinder();
                C1756l c1756l2 = (C1756l) ((AbstractServiceC1765v) this.f18597n.f18608m).f18612p.remove(binder);
                if (c1756l2 != null) {
                    binder.unlinkToDeath(c1756l2, 0);
                }
                break;
        }
    }
}
