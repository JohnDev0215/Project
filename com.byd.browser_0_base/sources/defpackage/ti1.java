package defpackage;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import defpackage.aa3;
import defpackage.qh3;
import java.io.IOException;
import java.util.ArrayList;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.suggestions.tile.MostVisitedTilesGridLayout;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MostVisitedTilesMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ti1 implements aa3.c, TemplateUrlService.c {
    public final Resources e;
    public final qh3 f;
    public final ViewGroup g;
    public final ViewStub h;
    public final PropertyModel i;
    public final boolean j;
    public final boolean k;
    public final int l;
    public final int m;
    public int p;
    public ka3 q;
    public aa3 r;
    public boolean s;
    public TemplateUrlService u;
    public boolean t = true;
    public final Runnable n = null;
    public final Runnable o = null;

    public ti1(Resources resources, qh3 qh3Var, ViewGroup viewGroup, ViewStub viewStub, ka3 ka3Var, PropertyModel propertyModel, boolean z, boolean z2, boolean z3) {
        this.e = resources;
        this.f = qh3Var;
        this.q = ka3Var;
        this.i = propertyModel;
        this.j = z2;
        this.k = z3;
        this.g = viewGroup;
        this.h = viewStub;
        this.l = resources.getDimensionPixelSize(R$dimen.tile_view_padding_landscape);
        this.m = resources.getDimensionPixelSize(R$dimen.tile_view_padding_edge_portrait);
        b();
        if (!z || this.s) {
            return;
        }
        try {
            Object obj = pi1.c;
            ku2 ku2VarC = ku2.C();
            try {
                ArrayList arrayListE = pi1.e();
                ku2VarC.close();
                if (arrayListE == null) {
                    return;
                }
                this.q.c(arrayListE, viewGroup, null);
                e();
            } catch (Throwable th) {
                try {
                    ku2VarC.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException unused2) {
            Log.i("cr_TopSites", "No cached MV tiles file.");
        }
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        c();
    }

    public final SuggestionsTileView a(ao2 ao2Var) {
        ViewGroup viewGroup = this.g;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            SuggestionsTileView suggestionsTileView = (SuggestionsTileView) viewGroup.getChildAt(i);
            if (ao2Var.equals(suggestionsTileView.getData())) {
                return suggestionsTileView;
            }
        }
        return null;
    }

    public final void b() {
        if (this.j) {
            Resources resources = this.e;
            if (resources.getConfiguration().orientation == 2 || this.p != 0) {
                return;
            }
            boolean z = this.k;
            int i = this.m;
            if (z) {
                this.p = i;
                return;
            }
            qh3.b bVar = this.f.a;
            boolean z2 = bVar.a == 0 || bVar.b == 0;
            this.p = Integer.max(-resources.getDimensionPixelOffset(R$dimen.tile_view_padding), (int) ((((double) (resources.getDisplayMetrics().widthPixels - i)) - (((double) resources.getDimensionPixelOffset(z2 ? R$dimen.tile_view_width_condensed : R$dimen.tile_view_width)) * 4.5d)) / 4.0d));
        }
    }

    public final void c() {
        boolean zB = this.u.b();
        if (this.t == zB) {
            return;
        }
        this.t = zB;
        d();
        if (this.j) {
            return;
        }
        ViewGroup viewGroup = this.g;
        ((MostVisitedTilesGridLayout) viewGroup).setSearchProviderHasLogo(this.t);
        ao3.e(viewGroup, "MostVisitedTilesMediator.onSearchEngineHasLogoChanged");
    }

    public final void d() {
        boolean z;
        aa3 aa3Var = this.r;
        if (aa3Var == null) {
            return;
        }
        boolean z2 = false;
        if (aa3Var.l) {
            int i = 0;
            while (true) {
                if (i >= aa3Var.h.size()) {
                    z = true;
                    break;
                } else {
                    if (!aa3Var.h.valueAt(i).isEmpty()) {
                        z = false;
                        break;
                    }
                    i++;
                }
            }
            if (z && !this.t) {
                z2 = true;
            }
        }
        PropertyModel propertyModel = this.i;
        if (z2) {
            PropertyModel.r<View> rVar = xi1.c;
            if (propertyModel.j(rVar) == null) {
                propertyModel.p(rVar, this.h.inflate());
            }
        }
        propertyModel.l(xi1.b, !z2);
    }

    public final void e() {
        if (!this.j || this.g.getChildCount() < 1) {
            return;
        }
        int i = this.e.getConfiguration().orientation;
        PropertyModel propertyModel = this.i;
        if (i != 2) {
            propertyModel.p(xi1.e, Integer.valueOf(this.m));
            propertyModel.p(xi1.d, Integer.valueOf(this.p));
        } else {
            PropertyModel.r<Integer> rVar = xi1.e;
            int i2 = this.l;
            propertyModel.p(rVar, Integer.valueOf(i2));
            propertyModel.p(xi1.d, Integer.valueOf(i2));
        }
    }
}
