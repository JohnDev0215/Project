package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import g.C1273d;

/* JADX INFO: renamed from: l3.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1762s implements Runnable {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1764u f18602m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f18603n;
    public final /* synthetic */ C1273d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1764u f18604p;

    public RunnableC1762s(C1764u c1764u, C1764u c1764u2, String str, C1273d c1273d) {
        this.f18604p = c1764u;
        this.f18602m = c1764u2;
        this.f18603n = str;
        this.o = c1273d;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                IBinder binder = ((Messenger) this.f18602m.f18608m).getBinder();
                C1764u c1764u = this.f18604p;
                C1756l c1756l = (C1756l) ((AbstractServiceC1765v) c1764u.f18608m).f18612p.get(binder);
                if (c1756l != null) {
                    AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1764u.f18608m;
                    abstractServiceC1765v.getClass();
                    C1273d c1273d = this.o;
                    abstractServiceC1765v.f18613q = c1756l;
                    if ((2 & 2) != 0) {
                        c1273d.d(-1, null);
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("media_item", null);
                        c1273d.d(0, bundle);
                    }
                    abstractServiceC1765v.f18613q = null;
                } else {
                    h6.n.t("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + this.f18603n);
                }
                break;
            default:
                IBinder binder2 = ((Messenger) this.f18602m.f18608m).getBinder();
                C1764u c1764u2 = this.f18604p;
                C1756l c1756l2 = (C1756l) ((AbstractServiceC1765v) c1764u2.f18608m).f18612p.get(binder2);
                if (c1756l2 != null) {
                    AbstractServiceC1765v abstractServiceC1765v2 = (AbstractServiceC1765v) c1764u2.f18608m;
                    abstractServiceC1765v2.getClass();
                    C1273d c1273d2 = this.o;
                    abstractServiceC1765v2.f18613q = c1756l2;
                    c1273d2.d(-1, null);
                    abstractServiceC1765v2.f18613q = null;
                } else {
                    h6.n.t("MBServiceCompat", "search for callback that isn't registered query=" + this.f18603n);
                }
                break;
        }
    }

    public RunnableC1762s(C1764u c1764u, C1764u c1764u2, String str, Bundle bundle, C1273d c1273d) {
        this.f18604p = c1764u;
        this.f18602m = c1764u2;
        this.f18603n = str;
        this.o = c1273d;
    }
}
