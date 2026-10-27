package l3;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import g.C1273d;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class r implements Runnable {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1764u f18598m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f18599n;
    public final /* synthetic */ Bundle o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1764u f18600p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f18601q;

    public r(C1764u c1764u, C1764u c1764u2, String str, IBinder iBinder, Bundle bundle) {
        this.f18600p = c1764u;
        this.f18598m = c1764u2;
        this.f18599n = str;
        this.f18601q = iBinder;
        this.o = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                IBinder binder = ((Messenger) this.f18598m.f18608m).getBinder();
                C1764u c1764u = this.f18600p;
                C1756l c1756l = (C1756l) ((AbstractServiceC1765v) c1764u.f18608m).f18612p.get(binder);
                if (c1756l == null) {
                    h6.n.t("MBServiceCompat", "addSubscription for callback that isn't registered id=" + this.f18599n);
                    return;
                }
                AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1764u.f18608m;
                abstractServiceC1765v.getClass();
                HashMap map = c1756l.f18583f;
                String str = this.f18599n;
                List arrayList = (List) map.get(str);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    IBinder iBinder = (IBinder) this.f18601q;
                    Bundle bundle = this.o;
                    if (!zHasNext) {
                        arrayList.add(new E1.b(iBinder, bundle));
                        map.put(str, arrayList);
                        F3.j jVar = new F3.j(abstractServiceC1765v, str, c1756l, str, bundle);
                        abstractServiceC1765v.f18613q = c1756l;
                        if (bundle == null) {
                            jVar.c();
                        } else {
                            jVar.f2405b = 1;
                            jVar.c();
                        }
                        abstractServiceC1765v.f18613q = null;
                        if (jVar.f2404a) {
                            abstractServiceC1765v.f18613q = null;
                            return;
                        }
                        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + c1756l.f18580a + " id=" + str);
                    }
                    E1.b bVar = (E1.b) it.next();
                    if (iBinder == bVar.f1715a) {
                        Bundle bundle2 = (Bundle) bVar.f1716b;
                        if (bundle == bundle2) {
                            return;
                        }
                        if (bundle == null) {
                            AbstractC1346c.k(bundle2);
                            if (bundle2.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                                return;
                            }
                        } else if (bundle2 == null) {
                            if (bundle.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) {
                                return;
                            }
                        } else if (bundle.getInt("android.media.browse.extra.PAGE", -1) == bundle2.getInt("android.media.browse.extra.PAGE", -1) && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == bundle2.getInt("android.media.browse.extra.PAGE_SIZE", -1)) {
                            return;
                        }
                    }
                }
                break;
            default:
                IBinder binder2 = ((Messenger) this.f18598m.f18608m).getBinder();
                C1764u c1764u2 = this.f18600p;
                C1756l c1756l2 = (C1756l) ((AbstractServiceC1765v) c1764u2.f18608m).f18612p.get(binder2);
                Bundle bundle3 = this.o;
                if (c1756l2 == null) {
                    h6.n.t("MBServiceCompat", "sendCustomAction for callback that isn't registered action=" + this.f18599n + ", extras=" + bundle3);
                    return;
                }
                AbstractServiceC1765v abstractServiceC1765v2 = (AbstractServiceC1765v) c1764u2.f18608m;
                abstractServiceC1765v2.f18613q = c1756l2;
                if (bundle3 == null) {
                    Bundle bundle4 = Bundle.EMPTY;
                }
                ((C1273d) this.f18601q).d(-1, null);
                abstractServiceC1765v2.f18613q = null;
                return;
        }
    }

    public r(C1764u c1764u, C1764u c1764u2, String str, Bundle bundle, C1273d c1273d) {
        this.f18600p = c1764u;
        this.f18598m = c1764u2;
        this.f18599n = str;
        this.o = bundle;
        this.f18601q = c1273d;
    }
}
