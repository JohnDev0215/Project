package defpackage;

import android.app.Activity;
import android.view.ViewGroup;
import defpackage.nr2;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: ExploreSurfaceCoordinatorFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ri0 {
    public final Activity a;
    public final nw2<Tab> b;
    public final wp2 c;
    public final nw2<ck2> d;
    public final WindowAndroid e;
    public final y13 f;
    public final ji g;
    public final uf2 h;
    public final nw2<qb3> i;
    public final long j;
    public final FeedSwipeRefreshLayout k;
    public final ViewGroup l;

    public ri0(Activity activity, ViewGroup viewGroup, PropertyModel propertyModel, ji jiVar, nw2 nw2Var, nr2.a aVar, wp2 wp2Var, nw2 nw2Var2, WindowAndroid windowAndroid, y13 y13Var, nw2 nw2Var3, long j, FeedSwipeRefreshLayout feedSwipeRefreshLayout) {
        this.a = activity;
        this.l = viewGroup;
        this.b = nw2Var;
        this.c = wp2Var;
        this.d = nw2Var2;
        this.e = windowAndroid;
        this.f = y13Var;
        this.g = jiVar;
        this.h = aVar;
        this.i = nw2Var3;
        this.j = j;
        this.k = feedSwipeRefreshLayout;
        j.b(propertyModel, viewGroup, new ag(21));
    }
}
