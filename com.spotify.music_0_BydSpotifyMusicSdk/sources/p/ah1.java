package p;

import android.os.Bundle;
import android.os.IBinder;
import com.spotify.automotive.base.service.AAMMediaBrowserService;
import com.spotify.base.java.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ah1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ wg1 e;
    public final /* synthetic */ String k;
    public final /* synthetic */ Bundle l;
    public final /* synthetic */ Object m;
    public final /* synthetic */ wg1 n;

    public /* synthetic */ ah1(wg1 wg1Var, wg1 wg1Var2, String str, Bundle bundle, b52 b52Var, int i) {
        this.c = i;
        this.n = wg1Var;
        this.e = wg1Var2;
        this.k = str;
        this.l = bundle;
        this.m = b52Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        int i2 = 1;
        Bundle bundle = this.l;
        String str = this.k;
        Object obj = this.m;
        wg1 wg1Var = this.n;
        wg1 wg1Var2 = this.e;
        switch (i) {
            case 0:
                if (((jg1) ((bh1) wg1Var.a).m.get(wg1Var2.a())) == null) {
                    return;
                }
                bh1 bh1Var = (bh1) wg1Var.a;
                bh1Var.getClass();
                gg1 gg1Var = new gg1(str, (b52) obj, 1);
                bh1Var.c(bundle, gg1Var, str);
                if (!gg1Var.b()) {
                    throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=".concat(str));
                }
                return;
            case 1:
                if (((jg1) ((bh1) wg1Var.a).m.get(wg1Var2.a())) == null) {
                    Objects.toString(bundle);
                    return;
                }
                bh1 bh1Var2 = (bh1) wg1Var.a;
                bh1Var2.getClass();
                gg1 gg1Var2 = new gg1(str, (b52) obj, 2);
                com.spotify.automotive.base.service.a aVar = ((AAMMediaBrowserService) bh1Var2).q;
                aVar.getClass();
                v03.j(str, "action");
                yj yjVar = aVar.d;
                if (aVar.j == null && yjVar != null) {
                    fk fkVar = ((ek) yjVar).b;
                    aVar.j = new v4(fkVar.l, fkVar.y);
                }
                v4 v4Var = aVar.j;
                int i3 = 0;
                if (v4Var != null) {
                    try {
                        switch (str) {
                            case "collection-add":
                                v4Var.B(new o(v4Var, i3), bundle, gg1Var2);
                                break;
                            case "collection-remove":
                                v4Var.B(new o(v4Var, i2), bundle, gg1Var2);
                                break;
                            case "collection-get":
                                ju juVar = (ju) v4Var.e;
                                Objects.requireNonNull(juVar);
                                v4Var.D(new com.spotify.automotive.auth.a(i2, juVar), bundle, gg1Var2);
                                break;
                            case "playbackSpeed-getList":
                                v4.C(gg1Var2);
                                break;
                            case "play":
                            case "pause":
                                v4Var.E(bundle, gg1Var2);
                                break;
                            case "shuffle":
                                v4Var.F(bundle, gg1Var2, Boolean.TRUE);
                                break;
                            case "disable-shuffle":
                                v4Var.F(bundle, gg1Var2, Boolean.FALSE);
                                break;
                            default:
                                throw new IllegalArgumentException(String.format("Unknown custom action \"%s\".", str));
                        }
                    } catch (Exception e) {
                        Logger.c(e, "Exception while handling custom action.", new Object[0]);
                        gg1Var2.f();
                    }
                } else {
                    Logger.b("Called onCustomAction before binder is ready.", new Object[0]);
                    gg1Var2.f();
                }
                if (gg1Var2.b()) {
                    return;
                }
                throw new IllegalStateException("onCustomAction must call detach() or sendResult() or sendError() before returning for action=" + str + " extras=" + bundle);
            default:
                jg1 jg1Var = (jg1) ((bh1) wg1Var.a).m.get(wg1Var2.a());
                if (jg1Var == null) {
                    return;
                }
                bh1 bh1Var3 = (bh1) wg1Var.a;
                IBinder iBinder = (IBinder) obj;
                bh1Var3.getClass();
                HashMap map = jg1Var.e;
                List<pr1> arrayList = (List) map.get(str);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                for (pr1 pr1Var : arrayList) {
                    if (iBinder == pr1Var.a) {
                        Bundle bundle2 = (Bundle) pr1Var.b;
                        if (bundle == bundle2) {
                            return;
                        }
                        if (bundle == null) {
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
                arrayList.add(new pr1(iBinder, bundle));
                map.put(str, arrayList);
                bh1Var3.d(str, jg1Var, bundle, null);
                return;
        }
    }

    public ah1(wg1 wg1Var, wg1 wg1Var2, String str, IBinder iBinder, Bundle bundle) {
        this.c = 2;
        this.n = wg1Var;
        this.e = wg1Var2;
        this.k = str;
        this.m = iBinder;
        this.l = bundle;
    }
}
