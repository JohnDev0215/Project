package org.chromium.chrome.browser.tasks.tab_management;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import defpackage.bz;
import defpackage.cy0;
import defpackage.d03;
import defpackage.ij3;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.rb2;
import defpackage.v62;
import java.util.WeakHashMap;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* JADX INFO: compiled from: TabStripViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h1 {
    public static void a(PropertyModel propertyModel, ViewGroup viewGroup, v62 v62Var) {
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) viewGroup;
        if (v62Var == null) {
            for (v62 v62Var2 : u0.D) {
                a(propertyModel, viewLookupCachingFrameLayout, v62Var2);
            }
            return;
        }
        PropertyModel.n nVar = u0.k;
        Drawable drawable = null;
        if (nVar == v62Var) {
            ImageButton imageButton = (ImageButton) viewLookupCachingFrameLayout.j(R$id.tab_strip_item_button);
            int i = propertyModel.k(u0.q) ? R$drawable.tab_strip_selected_ring_incognito : R$drawable.tab_strip_selected_ring;
            if (propertyModel.k(nVar)) {
                Resources resources = viewLookupCachingFrameLayout.getResources();
                Resources.Theme theme = viewLookupCachingFrameLayout.getContext().getTheme();
                ThreadLocal<TypedValue> threadLocal = rb2.a;
                drawable = resources.getDrawable(i, theme);
            }
            viewLookupCachingFrameLayout.setForeground(drawable);
            String str = (String) propertyModel.j(u0.j);
            if (propertyModel.k(nVar)) {
                imageButton.setOnClickListener(new cy0(3, propertyModel));
                imageButton.setContentDescription(viewLookupCachingFrameLayout.getContext().getString(R$string.accessibility_tabstrip_btn_close_tab, str));
                imageButton.getBackground().setAlpha(0);
                return;
            } else {
                imageButton.setOnClickListener(new ij3(5, propertyModel));
                imageButton.setContentDescription(viewLookupCachingFrameLayout.getContext().getString(R$string.accessibility_tabstrip_tab, str));
                imageButton.getBackground().setAlpha(255);
                return;
            }
        }
        PropertyModel.r<h0.c> rVar = u0.d;
        if (rVar == v62Var) {
            if (oj2.C(viewLookupCachingFrameLayout.getContext())) {
                return;
            }
            b(viewLookupCachingFrameLayout, propertyModel, ((h0.c) propertyModel.j(rVar)).a);
            return;
        }
        PropertyModel.r<h0.d> rVar2 = u0.f;
        if (rVar2 == v62Var && oj2.C(viewLookupCachingFrameLayout.getContext())) {
            PropertyModel.n nVar2 = u0.e;
            propertyModel.l(nVar2, false);
            h0.d dVar = (h0.d) propertyModel.j(rVar2);
            if (dVar != null) {
                dVar.a(new d03(dVar, propertyModel, viewLookupCachingFrameLayout, 1));
            } else {
                b(viewLookupCachingFrameLayout, propertyModel, null);
                propertyModel.l(nVar2, true);
            }
        }
    }

    public static void b(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, PropertyModel propertyModel, Drawable drawable) {
        ImageButton imageButton = (ImageButton) viewLookupCachingFrameLayout.j(R$id.tab_strip_item_button);
        imageButton.setBackgroundResource(R$drawable.tabstrip_favicon_background);
        ColorStateList colorStateListB = bz.b(viewLookupCachingFrameLayout.getContext(), propertyModel.h(u0.s));
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.i.j(imageButton, colorStateListB);
        if (propertyModel.k(u0.k)) {
            imageButton.getBackground().setAlpha(0);
        } else {
            imageButton.getBackground().setAlpha(255);
        }
        imageButton.setImageDrawable(drawable);
    }
}
