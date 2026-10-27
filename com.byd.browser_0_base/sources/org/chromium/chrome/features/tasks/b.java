package org.chromium.chrome.features.tasks;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import defpackage.aq3;
import defpackage.bq;
import defpackage.jn2;
import defpackage.kn2;
import defpackage.ky2;
import defpackage.nw2;
import defpackage.ty2;
import defpackage.v62;
import defpackage.y13;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SingleTabSwitcherCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements n1 {
    public final jn2 e;
    public final h0 f;
    public final a g;
    public final y13 h;

    public b(Activity activity, ViewGroup viewGroup, y13 y13Var) {
        this.h = y13Var;
        PropertyModel propertyModel = new PropertyModel(kn2.e);
        SingleTabView singleTabView = (SingleTabView) LayoutInflater.from(activity).inflate(R$layout.single_tab_view_layout, viewGroup, false);
        viewGroup.addView(singleTabView);
        j.b(propertyModel, singleTabView, new j.a() { // from class: org.chromium.chrome.features.tasks.a
            @Override // org.chromium.ui.modelutil.j.a
            public final void b(k kVar, Object obj, Object obj2) {
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                SingleTabView singleTabView2 = (SingleTabView) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.r<View.OnClickListener> rVar = kn2.a;
                if (v62Var == rVar) {
                    singleTabView2.setOnClickListener((View.OnClickListener) propertyModel2.j(rVar));
                    return;
                }
                PropertyModel.r<Drawable> rVar2 = kn2.b;
                if (v62Var == rVar2) {
                    singleTabView2.e.setImageDrawable((Drawable) propertyModel2.j(rVar2));
                    return;
                }
                PropertyModel.n nVar = kn2.c;
                if (v62Var == nVar) {
                    singleTabView2.setVisibility(propertyModel2.k(nVar) ? 0 : 8);
                    return;
                }
                PropertyModel.r<String> rVar3 = kn2.d;
                if (v62Var == rVar3) {
                    singleTabView2.f.setText((String) propertyModel2.j(rVar3));
                }
            }
        });
        h0 h0Var = new h0(activity, false);
        this.f = h0Var;
        this.e = new jn2(activity, propertyModel, y13Var, h0Var);
        if (bq.F.a()) {
            new ky2(y13Var);
        }
        this.g = new a();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final boolean a() {
        return false;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final void d() {
        TabModel tabModelG;
        int iIndex;
        this.f.f(this.h.g(false).f());
        jn2 jn2Var = this.e;
        if (jn2Var.q) {
            return;
        }
        y13 y13Var = jn2Var.f;
        if (y13Var.t() && (iIndex = (tabModelG = y13Var.g(false)).index()) != -1) {
            GURL url = tabModelG.getTabAt(iIndex).getUrl();
            aq3 aq3Var = new aq3(1, jn2Var);
            h0 h0Var = jn2Var.h;
            h0Var.getClass();
            h0Var.c(url, false, new ty2(aq3Var, 4));
            jn2Var.q = true;
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final n1.a e() {
        return this.e;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final nw2<Boolean> k() {
        return null;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final p1 m() {
        return null;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public final n1.c s() {
        return this.g;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1
    public void setOnTabSelectingListener(n1.b bVar) {
        this.e.setOnTabSelectingListener(bVar);
    }

    /* JADX INFO: compiled from: SingleTabSwitcherCoordinator.java */
    public class a implements n1.c {
        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final long b() {
            return 0L;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final int c() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final Rect n(boolean z) {
            return null;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final int r() {
            return 0;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final boolean t() {
            return true;
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final void p() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.n1.c
        public final void g(Runnable runnable) {
        }
    }
}
