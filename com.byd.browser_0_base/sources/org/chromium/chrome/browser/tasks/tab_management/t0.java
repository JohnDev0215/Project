package org.chromium.chrome.browser.tasks.tab_management;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.ct3;
import defpackage.cy0;
import defpackage.ij3;
import defpackage.oj2;
import defpackage.rb2;
import defpackage.v62;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TabListViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t0 {
    public static void a(PropertyModel propertyModel, ViewGroup viewGroup, v62 v62Var) {
        PropertyModel.r<String> rVar = u0.j;
        if (rVar == v62Var) {
            ((TextView) viewGroup.findViewById(R$id.title)).setText((String) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<h0.c> rVar2 = u0.d;
        if (rVar2 == v62Var) {
            if (oj2.C(viewGroup.getContext())) {
                return;
            }
            b(viewGroup, ((h0.c) propertyModel.j(rVar2)).a);
            return;
        }
        PropertyModel.r<h0.d> rVar3 = u0.f;
        int i = 2;
        if (rVar3 == v62Var) {
            if (oj2.C(viewGroup.getContext())) {
                h0.d dVar = (h0.d) propertyModel.j(rVar3);
                if (dVar == null) {
                    b(viewGroup, null);
                    return;
                } else {
                    dVar.a(new ct3(dVar, i, propertyModel, viewGroup));
                    return;
                }
            }
            return;
        }
        PropertyModel.r<j0.l> rVar4 = u0.c;
        if (rVar4 == v62Var) {
            if (propertyModel.j(rVar4) == null) {
                viewGroup.findViewById(R$id.end_button).setOnClickListener(null);
                return;
            } else {
                viewGroup.findViewById(R$id.end_button).setOnClickListener(new cy0(2, propertyModel));
                return;
            }
        }
        PropertyModel.n nVar = u0.k;
        if (nVar == v62Var) {
            int iH = propertyModel.h(u0.r);
            Resources resources = viewGroup.getResources();
            Resources.Theme theme = viewGroup.getContext().getTheme();
            ThreadLocal<TypedValue> threadLocal = rb2.a;
            viewGroup.setForeground(propertyModel.k(nVar) ? new InsetDrawable(resources.getDrawable(iH, theme), (int) resources.getDimension(R$dimen.tab_list_selected_inset_low_end)) : null);
            return;
        }
        PropertyModel.r<j0.l> rVar5 = u0.b;
        if (rVar5 == v62Var) {
            if (propertyModel.j(rVar5) == null) {
                viewGroup.setOnClickListener(null);
                return;
            } else {
                viewGroup.setOnClickListener(new ij3(4, propertyModel));
                return;
            }
        }
        PropertyModel.r<String> rVar6 = u0.v;
        if (rVar6 == v62Var) {
            ((TextView) viewGroup.findViewById(R$id.description)).setText((String) propertyModel.j(rVar6));
        }
    }

    public static void b(View view, Drawable drawable) {
        ImageView imageView = (ImageView) view.findViewById(R$id.start_icon);
        imageView.setBackgroundResource(R$drawable.list_item_icon_modern_bg);
        imageView.setImageDrawable(drawable);
    }
}
