package org.chromium.chrome.browser.history_clusters;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import defpackage.ao3;
import defpackage.ho;
import defpackage.mo;
import defpackage.n3;
import defpackage.v62;
import defpackage.wu0;
import defpackage.xa1;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: HistoryClustersViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(PropertyModel propertyModel, View view, v62 v62Var) {
        final HistoryClustersRelatedSearchesChipLayout historyClustersRelatedSearchesChipLayout = (HistoryClustersRelatedSearchesChipLayout) view;
        PropertyModel.r<Callback<String>> rVar = wu0.a;
        if (v62Var == rVar) {
            historyClustersRelatedSearchesChipLayout.h = (Callback) propertyModel.j(rVar);
            return;
        }
        PropertyModel.n nVar = wu0.d;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            historyClustersRelatedSearchesChipLayout.e.b(zK);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) historyClustersRelatedSearchesChipLayout.f.a.getLayoutParams();
            if (zK) {
                layoutParams.bottomMargin = historyClustersRelatedSearchesChipLayout.getResources().getDimensionPixelSize(R$dimen.divider_margin);
            } else {
                layoutParams.bottomMargin = 0;
            }
            ao3.e(historyClustersRelatedSearchesChipLayout, "HistoryClustersRelatedSearchesChipLayout.setHasThickDivider");
            return;
        }
        PropertyModel.r<Boolean> rVar2 = wu0.e;
        if (v62Var == rVar2) {
            historyClustersRelatedSearchesChipLayout.e.setVisibility(((Boolean) propertyModel.j(rVar2)).booleanValue() ? 0 : 8);
            return;
        }
        PropertyModel.r<List<String>> rVar3 = wu0.i;
        if (v62Var != rVar3) {
            PropertyModel.p pVar = wu0.l;
            if (v62Var == pVar) {
                historyClustersRelatedSearchesChipLayout.setVisibility(propertyModel.h(pVar));
                return;
            }
            return;
        }
        List list = (List) propertyModel.j(rVar3);
        historyClustersRelatedSearchesChipLayout.getClass();
        while (i < list.size()) {
            final String str = (String) list.get(i);
            xa1 xa1VarA = mo.a(i, R$drawable.ic_search, str, new Callback() { // from class: org.chromium.chrome.browser.history_clusters.c
                @Override // org.chromium.base.Callback
                public final void onResult(Object obj) {
                    historyClustersRelatedSearchesChipLayout.h.onResult(str);
                }
            });
            xa1VarA.b.l(ho.c, true);
            historyClustersRelatedSearchesChipLayout.g.t(xa1VarA);
            i++;
        }
    }

    public static void b(PropertyModel propertyModel, View view, v62 v62Var) {
        HistoryClustersItemView historyClustersItemView = (HistoryClustersItemView) view;
        PropertyModel.r<View.OnClickListener> rVar = wu0.b;
        if (v62Var == rVar) {
            historyClustersItemView.setOnClickListener((View.OnClickListener) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<ClusterVisit> rVar2 = wu0.c;
        if (v62Var == rVar2) {
            historyClustersItemView.setItem((ClusterVisit) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.n nVar = wu0.d;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            historyClustersItemView.y.b(zK);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) historyClustersItemView.p.getLayoutParams();
            if (zK) {
                layoutParams.bottomMargin = historyClustersItemView.getResources().getDimensionPixelSize(R$dimen.divider_margin);
            } else {
                layoutParams.bottomMargin = 0;
            }
            ao3.e(historyClustersItemView, "HistoryClustersItemView.setHasThickDivider");
            return;
        }
        PropertyModel.r<Boolean> rVar3 = wu0.e;
        if (v62Var == rVar3) {
            historyClustersItemView.y.setVisibility(((Boolean) propertyModel.j(rVar3)).booleanValue() ? 0 : 8);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar4 = wu0.f;
        if (v62Var == rVar4) {
            historyClustersItemView.setEndButtonClickHandler((View.OnClickListener) propertyModel.j(rVar4));
            return;
        }
        PropertyModel.n nVar2 = wu0.g;
        if (v62Var == nVar2) {
            historyClustersItemView.r.setVisibility(propertyModel.k(nVar2) ? 0 : 8);
            return;
        }
        PropertyModel.r<Drawable> rVar5 = wu0.h;
        if (v62Var == rVar5) {
            historyClustersItemView.q((Drawable) propertyModel.j(rVar5));
            return;
        }
        PropertyModel.r<CharSequence> rVar6 = wu0.j;
        if (v62Var == rVar6) {
            CharSequence charSequence = (CharSequence) propertyModel.j(rVar6);
            historyClustersItemView.s.setText(charSequence);
            n3.h1(historyClustersItemView.getContext(), historyClustersItemView.r, charSequence.toString(), 1);
            return;
        }
        PropertyModel.r<CharSequence> rVar7 = wu0.k;
        if (v62Var == rVar7) {
            historyClustersItemView.t.setText((CharSequence) propertyModel.j(rVar7));
            return;
        }
        PropertyModel.p pVar = wu0.l;
        if (v62Var == pVar) {
            historyClustersItemView.setVisibility(propertyModel.h(pVar));
        }
    }
}
