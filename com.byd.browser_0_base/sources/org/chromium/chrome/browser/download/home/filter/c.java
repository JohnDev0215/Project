package org.chromium.chrome.browser.download.home.filter;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import defpackage.sm0;
import defpackage.v62;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: FilterViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements j.a<PropertyModel, FilterView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        FilterView filterView = (FilterView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.r<View> rVar = sm0.a;
        if (v62Var == rVar) {
            View view = (View) propertyModel.j(rVar);
            ViewGroup viewGroup = filterView.c;
            viewGroup.removeAllViews();
            if (view != null) {
                viewGroup.addView(view, new ViewGroup.LayoutParams(-1, -2));
                return;
            }
            return;
        }
        PropertyModel.p pVar = sm0.b;
        if (v62Var == pVar) {
            int iH = propertyModel.h(pVar);
            filterView.getClass();
            int i = iH != 0 ? 1 : 0;
            TabLayout tabLayout = filterView.b;
            if (tabLayout.getSelectedTabPosition() == i) {
                return;
            }
            tabLayout.k(i).b();
            return;
        }
        PropertyModel.r<Callback<Integer>> rVar2 = sm0.c;
        if (v62Var == rVar2) {
            filterView.d = (Callback) propertyModel.j(rVar2);
            return;
        }
        PropertyModel.n nVar = sm0.d;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            filterView.getClass();
            filterView.b.setVisibility(zK ? 0 : 8);
        }
    }
}
