package defpackage;

import J.N;
import android.R;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import defpackage.wm3;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: compiled from: IncognitoMenuItemViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class dy0 implements m50 {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        View view = (View) obj;
        v62 v62Var = (v62) obj2;
        r7.a(propertyModel, view, v62Var);
        PropertyModel.p pVar = j7.a;
        if (v62Var == pVar) {
            view.setId(propertyModel.h(pVar));
            if (N.MRzPUMq7()) {
                ChromeImageView chromeImageView = (ChromeImageView) view.findViewById(R$id.trailing_icon);
                chromeImageView.setImageResource(R$drawable.ic_business);
                chromeImageView.setVisibility(0);
                return;
            }
            return;
        }
        PropertyModel.r<CharSequence> rVar = j7.b;
        if (v62Var == rVar) {
            ((TextViewWithCompoundDrawables) view.findViewById(R$id.title)).setText((CharSequence) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<CharSequence> rVar2 = j7.c;
        if (v62Var == rVar2) {
            view.findViewById(R$id.title).setContentDescription((CharSequence) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<Drawable> rVar3 = j7.h;
        if (v62Var == rVar3) {
            ((TextViewWithCompoundDrawables) view.findViewById(R$id.title)).setCompoundDrawablesRelative((Drawable) propertyModel.j(rVar3), null, null, null);
            return;
        }
        PropertyModel.n nVar = j7.d;
        if (v62Var == nVar) {
            boolean zK = propertyModel.k(nVar);
            TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) view.findViewById(R$id.title);
            textViewWithCompoundDrawables.setEnabled(zK);
            textViewWithCompoundDrawables.setFocusable(false);
            view.setFocusable(zK);
            return;
        }
        PropertyModel.n nVar2 = j7.e;
        if (v62Var != nVar2) {
            if (v62Var == j7.l) {
                view.setOnClickListener(new cy0(0, propertyModel));
            }
        } else if (propertyModel.k(nVar2)) {
            wm3.b(view, new wm3.a(2));
        } else {
            wm3.a(view);
        }
    }

    @Override // defpackage.m50
    public final int c(int i) {
        if (i == 0) {
            return R$layout.custom_view_menu_item;
        }
        return -1;
    }

    @Override // defpackage.m50
    public final int d(ContextThemeWrapper contextThemeWrapper) {
        return contextThemeWrapper.obtainStyledAttributes(new int[]{R.attr.listPreferredItemHeightSmall}).getDimensionPixelSize(0, 0);
    }

    @Override // defpackage.m50
    public final int getItemViewType(int i) {
        return i == R$id.new_incognito_tab_menu_id ? 0 : -1;
    }

    @Override // defpackage.m50
    public final void getViewTypeCount() {
    }
}
