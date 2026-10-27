package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import defpackage.um1;
import defpackage.wu1;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.creator.CreatorCoordinator;
import org.chromium.chrome.browser.download.e;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.b;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.dom_distiller.core.DomDistillerService;
import org.chromium.components.translate.TranslateMessage;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class yo implements nw2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ yo(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // defpackage.nw2
    public final Object get() {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                return (wu1.a) ((nw2) obj).get();
            case 1:
                return ((yc3) obj).n.getResourceManager();
            case 2:
                return ((CreatorCoordinator) obj).n;
            case 3:
                return Integer.valueOf(((aw1) obj).K);
            case 4:
                return Integer.valueOf(((ow1) obj).d());
            case 5:
            default:
                long j = ((TranslateMessage) obj).d;
                if (j != 0) {
                    N.M_ai6ZnE(j);
                }
                return 0;
            case 6:
                b<DomDistillerService> bVar = ob0.a;
                return (DomDistillerService) N.M2UAkcn4((Profile) obj);
            case 7:
                ((Callback) obj).onResult(Boolean.TRUE);
                e.k(1);
                return 1;
            case 8:
                return Boolean.valueOf(((c61) obj).d() == 2);
            case 9:
                return new hq(((gd1) obj).k);
            case 10:
                lk1 lk1Var = (lk1) obj;
                if (lk1Var.n == null) {
                    boolean asBoolean = lk1Var.k.getAsBoolean();
                    Activity activity = lk1Var.a;
                    Objects.requireNonNull(activity);
                    hs hsVar = new hs(7, activity);
                    y13 y13Var = lk1Var.g;
                    um1 um1Var = new um1(y13Var, lk1Var.j, asBoolean, hsVar);
                    lk1Var.n = um1Var;
                    um1.a aVar = new um1.a();
                    um1Var.e = aVar;
                    y13Var.m(aVar);
                }
                return lk1Var.n;
            case 11:
                return ((ge0) obj).g;
            case 12:
                return ((WindowAndroid) obj).n();
            case 13:
                return ((SettingsActivity) obj).L;
            case 14:
                return ((g63) obj).x.h();
            case 15:
                return ((bc3) obj).d();
            case 16:
                return Boolean.valueOf(!((t6) obj).m);
            case 17:
                return ((vl3) obj).b;
            case 18:
                nr2 nr2Var = (nr2) obj;
                if (nr2Var.y.c() != null && nr2Var.y.c().get().booleanValue()) {
                    return Boolean.TRUE;
                }
                g73 g73Var = nr2Var.z;
                return (g73Var == null || g73Var.c() == null || !nr2Var.z.c().get().booleanValue()) ? Boolean.FALSE : Boolean.TRUE;
            case 19:
                return wt1.d((Intent) obj, 65536);
        }
    }
}
