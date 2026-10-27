package defpackage;

import J.N;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import defpackage.qh3;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.suggestions.mostvisited.MostVisitedSitesBridge;
import org.chromium.chrome.browser.suggestions.tile.MostVisitedTilesCarouselLayout;
import org.chromium.chrome.browser.suggestions.tile.MostVisitedTilesGridLayout;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: MostVisitedTilesCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class si1 implements bx {
    public final Activity e;
    public final q2 f;
    public final ti1 g;
    public final WindowAndroid h;
    public final qh3 i;
    public ka3 j;
    public tz k;
    public OfflinePageBridge l;

    public si1(Activity activity, q2 q2Var, View view, WindowAndroid windowAndroid, boolean z, boolean z2, int i) {
        this.e = activity;
        this.f = q2Var;
        this.h = windowAndroid;
        ((ViewStub) view.findViewById(z2 ? R$id.mv_tiles_carousel_stub : R$id.mv_tiles_grid_stub)).inflate();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R$id.mv_tiles_layout);
        if (!z2) {
            MostVisitedTilesGridLayout mostVisitedTilesGridLayout = (MostVisitedTilesGridLayout) viewGroup;
            mostVisitedTilesGridLayout.setMaxColumns(4);
            mostVisitedTilesGridLayout.setMaxRows(i);
        }
        qh3 qh3Var = new qh3(viewGroup);
        this.i = qh3Var;
        PropertyModel propertyModel = new PropertyModel(xi1.f);
        j.b(propertyModel, new yi1(view, viewGroup), new zf(22));
        qh3.b bVar = qh3Var.a;
        this.j = new ka3(activity, bVar.a == 0 || bVar.b == 0 ? 2 : 1, null);
        this.g = new ti1(activity.getResources(), qh3Var, viewGroup, (ViewStub) view.findViewById(R$id.tile_grid_placeholder_stub), this.j, propertyModel, z, z2, DeviceFormFactor.a(activity));
    }

    public final void a() {
        this.f.e(this);
        if (this.l != null) {
            this.l = null;
        }
        if (this.j != null) {
            this.j = null;
        }
        WindowAndroid windowAndroid = this.h;
        if (windowAndroid != null) {
            windowAndroid.removeContextMenuCloseListener(this.k);
            this.k = null;
        }
        ti1 ti1Var = this.g;
        if (ti1Var != null) {
            ViewGroup viewGroup = ti1Var.g;
            if (viewGroup != null && ti1Var.j) {
                MostVisitedTilesCarouselLayout mostVisitedTilesCarouselLayout = (MostVisitedTilesCarouselLayout) viewGroup;
                for (int i = 0; i < mostVisitedTilesCarouselLayout.getChildCount(); i++) {
                    View childAt = mostVisitedTilesCarouselLayout.getChildAt(i);
                    childAt.setOnClickListener(null);
                    childAt.setOnCreateContextMenuListener(null);
                }
                mostVisitedTilesCarouselLayout.removeAllViews();
            }
            aa3 aa3Var = ti1Var.r;
            if (aa3Var != null) {
                aa3Var.g.onDestroy();
                ti1Var.r = null;
            }
            TemplateUrlService templateUrlService = ti1Var.u;
            if (templateUrlService != null) {
                templateUrlService.i(ti1Var);
            }
        }
    }

    public final void b(qi1 qi1Var, ca3 ca3Var, yd3 yd3Var) {
        this.f.f(this);
        Profile profileD = Profile.d();
        ka3 ka3Var = this.j;
        Activity activity = this.e;
        if (ka3Var == null) {
            qh3.b bVar = this.i.a;
            this.j = new ka3(activity, bVar.a == 0 || bVar.b == 0 ? 2 : 1, qi1Var.d);
        } else {
            ka3Var.c = qi1Var.d;
        }
        this.j.h = true;
        hw2 hw2Var = qi1Var.b;
        Objects.requireNonNull(activity);
        tz tzVar = new tz(hw2Var, yd3Var, new d92(5, activity));
        this.k = tzVar;
        this.h.addContextMenuCloseListener(tzVar);
        Object obj = ThreadUtils.a;
        if (d70.b == null) {
            d70.b = new d70();
        }
        d70.b.getClass();
        OfflinePageBridge offlinePageBridgeA = OfflinePageBridge.a(profileD);
        this.l = offlinePageBridgeA;
        tz tzVar2 = this.k;
        ka3 ka3Var2 = this.j;
        ti1 ti1Var = this.g;
        ti1Var.q = ka3Var2;
        aa3 aa3Var = new aa3(ka3Var2, qi1Var, tzVar2, ca3Var, ti1Var, offlinePageBridgeA);
        ti1Var.r = aa3Var;
        aa3Var.f.add(1);
        MostVisitedSitesBridge mostVisitedSitesBridge = ((ca3) aa3Var.c).d;
        mostVisitedSitesBridge.b = aa3Var;
        N.MsZWK0fV(mostVisitedSitesBridge.a, mostVisitedSitesBridge, mostVisitedSitesBridge, 12);
        TemplateUrlService templateUrlServiceQ = oj2.q(Profile.d());
        ti1Var.u = templateUrlServiceQ;
        templateUrlServiceQ.a(ti1Var);
        ti1Var.c();
        ti1Var.s = true;
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        ti1 ti1Var = this.g;
        ti1Var.b();
        ti1Var.e();
        this.i.c();
    }
}
