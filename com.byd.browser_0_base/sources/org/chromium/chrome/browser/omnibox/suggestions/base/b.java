package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.b2;
import defpackage.bw2;
import defpackage.bz;
import defpackage.cg;
import defpackage.d2;
import defpackage.ea;
import defpackage.i2;
import defpackage.in3;
import defpackage.la1;
import defpackage.nd3;
import defpackage.om3;
import defpackage.pr1;
import defpackage.sc2;
import defpackage.v62;
import defpackage.vt;
import defpackage.ya1;
import defpackage.yp;
import defpackage.z7;
import defpackage.za1;
import defpackage.zd0;
import java.util.List;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.omnibox.suggestions.base.SuggestionLayout;
import org.chromium.chrome.browser.omnibox.suggestions.base.d;
import org.chromium.components.browser_ui.styles.R$color;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: BaseSuggestionViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b<T extends View> implements j.a<PropertyModel, BaseSuggestionView<T>, v62> {
    public static int b;
    public static boolean c;
    public final j.a<PropertyModel, T, v62> a;

    public b(j.a<PropertyModel, T, v62> aVar) {
        this.a = aVar;
    }

    public static void e(View view, PropertyModel propertyModel) {
        boolean zK = propertyModel.k(zd0.a);
        boolean zK2 = propertyModel.k(zd0.b);
        if (!zK && !zK2) {
            view.setClipToOutline(false);
            return;
        }
        sc2 sc2Var = new sc2(view.getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_bg_round_corner_radius));
        sc2Var.b = true;
        sc2Var.c = zK;
        sc2Var.d = true;
        sc2Var.e = zK2;
        view.setOutlineProvider(sc2Var);
        view.setClipToOutline(true);
    }

    public static void f(ImageView imageView, e eVar, int i) {
        imageView.setVisibility(eVar == null ? 8 : 0);
        if (eVar == null) {
            imageView.setImageDrawable(null);
            return;
        }
        ColorStateList colorStateListB = eVar.b ? bz.b(imageView.getContext(), i) : null;
        imageView.setImageDrawable(eVar.a);
        imageView.setImageTintList(colorStateListB);
    }

    public static <T extends View> void g(PropertyModel propertyModel, BaseSuggestionView<T> baseSuggestionView) {
        ImageView suggestionImageView = baseSuggestionView.getSuggestionImageView();
        e eVar = (e) propertyModel.j(d.a);
        if (eVar != null) {
            Resources resources = suggestionImageView.getContext().getResources();
            boolean zE = pr1.e(suggestionImageView.getContext());
            suggestionImageView.setLayoutParams(new SuggestionLayout.a(resources.getDimensionPixelSize(zE ? R$dimen.omnibox_suggestion_icon_area_size_modern : R$dimen.omnibox_suggestion_icon_area_size), 1));
            boolean z = eVar.d;
            int dimensionPixelSize = resources.getDimensionPixelSize(z ? R$dimen.omnibox_suggestion_36dp_icon_margin_start : zE ? R$dimen.omnibox_suggestion_24dp_icon_margin_start_modern : R$dimen.omnibox_suggestion_24dp_icon_margin_start);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(z ? R$dimen.omnibox_suggestion_36dp_icon_margin_end : R$dimen.omnibox_suggestion_24dp_icon_margin_end);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(z ? R$dimen.omnibox_suggestion_36dp_icon_size : R$dimen.omnibox_suggestion_24dp_icon_size);
            suggestionImageView.setPadding(dimensionPixelSize, 0, dimensionPixelSize2, 0);
            suggestionImageView.setMinimumHeight(dimensionPixelSize3);
            suggestionImageView.setClipToOutline(eVar.c);
        }
        boolean z2 = propertyModel.h(bw2.a) == 2;
        String str = yp.a;
        f(suggestionImageView, eVar, z2 ? R$color.default_icon_color_secondary_light_tint_list : R$color.default_icon_color_secondary_tint_list);
    }

    @Override // org.chromium.ui.modelutil.j.a
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void b(PropertyModel propertyModel, BaseSuggestionView<T> baseSuggestionView, v62 v62Var) {
        int i;
        b2 b2Var;
        this.a.b(propertyModel, baseSuggestionView.getContentView(), v62Var);
        ActionChipsView actionChipsView = baseSuggestionView.getActionChipsView();
        PropertyModel.r<ya1> rVar = i2.a;
        if (rVar == v62Var) {
            final boolean z = propertyModel.h(bw2.a) == 2;
            ya1 ya1Var = (ya1) propertyModel.j(rVar);
            if (ya1Var != null) {
                b2Var = new b2(ya1Var);
                b2Var.H(0, new ea(4), d2.a);
                b2Var.H(1, new za1() { // from class: c2
                    @Override // defpackage.za1
                    public final View a(ViewGroup viewGroup) {
                        return new ChipView(viewGroup.getContext(), z ? R$style.OmniboxIncognitoActionChipThemeOverlay : R$style.OmniboxActionChipThemeOverlay);
                    }
                }, new cg(13));
                i = 0;
            } else {
                i = 8;
                b2Var = null;
            }
            actionChipsView.setAdapter(b2Var);
            actionChipsView.setVisibility(i);
        }
        if (d.a == v62Var) {
            g(propertyModel, baseSuggestionView);
            return;
        }
        PropertyModel.p pVar = bw2.b;
        if (pVar == v62Var) {
            int iH = propertyModel.h(pVar);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.h(baseSuggestionView, iH);
            return;
        }
        PropertyModel.p pVar2 = bw2.a;
        if (pVar2 == v62Var) {
            int iH2 = propertyModel.h(pVar2);
            boolean zI = z7.I(baseSuggestionView.getContext());
            if (iH2 != b || zI != c) {
                b = iH2;
                c = zI;
            }
            g(propertyModel, baseSuggestionView);
            baseSuggestionView.setBackgroundResource(R$drawable.main_history_bg);
            List list = (List) propertyModel.j(d.b);
            if (list == null) {
                return;
            }
            List<ImageView> actionButtons = baseSuggestionView.getActionButtons();
            for (int i2 = 0; i2 < actionButtons.size(); i2++) {
                ImageView imageView = actionButtons.get(i2);
                imageView.setBackgroundResource(R$drawable.main_history_bg);
                e eVar = ((d.a) list.get(i2)).a;
                boolean z2 = propertyModel.h(bw2.a) == 2;
                String str = yp.a;
                f(imageView, eVar, z2 ? R$color.default_icon_color_light_tint_list : R$color.default_icon_color_tint_list);
            }
            return;
        }
        if (zd0.b == v62Var) {
            e(baseSuggestionView, propertyModel);
            return;
        }
        if (zd0.a == v62Var) {
            e(baseSuggestionView, propertyModel);
            return;
        }
        PropertyModel.p pVar3 = zd0.c;
        if (pVar3 == v62Var) {
            ViewGroup.LayoutParams layoutParams = baseSuggestionView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
            }
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                int iH3 = propertyModel.h(pVar3);
                int iH4 = propertyModel.h(zd0.d);
                int dimensionPixelOffset = baseSuggestionView.getContext().getResources().getDimensionPixelOffset(R$dimen.omnibox_suggestion_side_spacing);
                ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(dimensionPixelOffset, iH3, dimensionPixelOffset, iH4);
            }
            baseSuggestionView.setLayoutParams(layoutParams);
            return;
        }
        PropertyModel.r<List<d.a>> rVar2 = d.b;
        if (rVar2 == v62Var) {
            List list2 = (List) propertyModel.j(rVar2);
            int size = list2 != null ? list2.size() : 0;
            baseSuggestionView.setActionButtonsCount(size);
            List<ImageView> actionButtons2 = baseSuggestionView.getActionButtons();
            for (int i3 = 0; i3 < size; i3++) {
                ImageView imageView2 = actionButtons2.get(i3);
                d.a aVar = (d.a) list2.get(i3);
                imageView2.setOnClickListener(new vt(16, aVar));
                imageView2.setContentDescription(aVar.c);
                imageView2.setBackgroundResource(R$drawable.main_history_bg);
                boolean z3 = propertyModel.h(bw2.a) == 2;
                String str2 = yp.a;
                f(imageView2, aVar.a, z3 ? R$color.default_icon_color_light_tint_list : R$color.default_icon_color_tint_list);
                imageView2.setAccessibilityDelegate(new a(aVar, imageView2));
            }
            return;
        }
        PropertyModel.r<Runnable> rVar3 = d.c;
        if (rVar3 == v62Var) {
            baseSuggestionView.setOnFocusViaSelectionListener((Runnable) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.r<Runnable> rVar4 = d.d;
        if (rVar4 == v62Var) {
            Runnable runnable = (Runnable) propertyModel.j(rVar4);
            if (runnable == null) {
                baseSuggestionView.setOnClickListener(null);
                return;
            } else {
                baseSuggestionView.setOnClickListener(new nd3(6, runnable));
                return;
            }
        }
        PropertyModel.r<Runnable> rVar5 = d.e;
        if (rVar5 == v62Var) {
            Runnable runnable2 = (Runnable) propertyModel.j(rVar5);
            if (runnable2 == null) {
                baseSuggestionView.setOnLongClickListener(null);
            } else {
                baseSuggestionView.setOnLongClickListener(new la1(1, runnable2));
            }
        }
    }
}
