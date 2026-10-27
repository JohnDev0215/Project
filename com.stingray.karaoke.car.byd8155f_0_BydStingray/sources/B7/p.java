package b7;

import android.os.IBinder;
import android.os.Messenger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import l3.AbstractServiceC1765v;
import l3.C1756l;
import l3.C1764u;
import org.json.JSONArray;
import q.C2147d;
import q.MenuC2151h;
import q.MenuItemC2152i;
import q.ViewOnKeyListenerC2148e;

/* JADX INFO: loaded from: classes.dex */
public final class p implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f12308m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f12309n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f12310p;

    public /* synthetic */ p(Object obj, Object obj2, Object obj3, Object obj4, int i7) {
        this.l = i7;
        this.f12310p = obj;
        this.f12308m = obj2;
        this.f12309n = obj3;
        this.o = obj4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                JSONArray jSONArray = new JSONArray();
                jSONArray.put((String) this.f12308m);
                Object[] objArr = (Object[]) this.f12309n;
                if (objArr != null) {
                    for (Object obj : objArr) {
                        jSONArray.put(obj);
                    }
                }
                j7.d dVar = new j7.d(2, jSONArray);
                if (((q) this.o) != null) {
                    int i7 = ((r) this.f12310p).o;
                    r.f12313x.fine(String.format("emitting packet with ack id %d", Integer.valueOf(i7)));
                    ((r) this.f12310p).f12318r.put(Integer.valueOf(i7), (q) this.o);
                    r rVar = (r) this.f12310p;
                    int i10 = rVar.o;
                    rVar.o = i10 + 1;
                    dVar.f17067b = i10;
                }
                if (((r) this.f12310p).f12315n) {
                    ((r) this.f12310p).i1(dVar);
                    return;
                } else {
                    ((r) this.f12310p).f12321u.add(dVar);
                    return;
                }
            case 1:
                IBinder binder = ((Messenger) ((C1764u) this.f12309n).f18608m).getBinder();
                C1764u c1764u = (C1764u) this.f12310p;
                C1756l c1756l = (C1756l) ((AbstractServiceC1765v) c1764u.f18608m).f18612p.get(binder);
                String str = (String) this.f12308m;
                if (c1756l == null) {
                    h6.n.t("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + str);
                    return;
                }
                AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) c1764u.f18608m;
                abstractServiceC1765v.getClass();
                HashMap map = c1756l.f18583f;
                IBinder iBinder = (IBinder) this.o;
                boolean z6 = false;
                try {
                    if (iBinder != null) {
                        List list = (List) map.get(str);
                        if (list != null) {
                            Iterator it = list.iterator();
                            while (it.hasNext()) {
                                if (iBinder == ((E1.b) it.next()).f1715a) {
                                    it.remove();
                                    z6 = true;
                                }
                            }
                            if (list.size() == 0) {
                                map.remove(str);
                            }
                        }
                    } else if (map.remove(str) != null) {
                        z6 = true;
                    }
                    if (z6) {
                        return;
                    }
                    h6.n.t("MBServiceCompat", "removeSubscription called for " + str + " which is not subscribed");
                    return;
                } finally {
                    abstractServiceC1765v.f18613q = null;
                }
            default:
                C2147d c2147d = (C2147d) this.f12308m;
                if (c2147d != null) {
                    C1764u c1764u2 = (C1764u) this.f12310p;
                    ((ViewOnKeyListenerC2148e) c1764u2.f18608m).f21030K = true;
                    c2147d.f21020b.c(false);
                    ((ViewOnKeyListenerC2148e) c1764u2.f18608m).f21030K = false;
                }
                MenuItemC2152i menuItemC2152i = (MenuItemC2152i) this.f12309n;
                if (menuItemC2152i.isEnabled() && menuItemC2152i.hasSubMenu()) {
                    ((MenuC2151h) this.o).p(menuItemC2152i, null, 4);
                    return;
                }
                return;
        }
    }

    public p(C1764u c1764u, C1764u c1764u2, String str, IBinder iBinder) {
        this.l = 1;
        this.f12310p = c1764u;
        this.f12309n = c1764u2;
        this.f12308m = str;
        this.o = iBinder;
    }
}
