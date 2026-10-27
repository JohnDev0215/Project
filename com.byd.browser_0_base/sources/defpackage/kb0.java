package defpackage;

import android.view.ContextThemeWrapper;
import android.view.View;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: DividerLineMenuItemViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class kb0 implements m50 {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        View view = (View) obj;
        v62 v62Var = (v62) obj2;
        r7.a(propertyModel, view, v62Var);
        PropertyModel.p pVar = j7.a;
        if (v62Var == pVar) {
            view.setId(propertyModel.h(pVar));
            view.setEnabled(false);
        }
    }

    @Override // defpackage.m50
    public final int c(int i) {
        if (i == 0) {
            return R$layout.divider_line_menu_item;
        }
        return -1;
    }

    @Override // defpackage.m50
    public final int d(ContextThemeWrapper contextThemeWrapper) {
        return (contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.overflow_menu_divider_line_padding) * 2) + contextThemeWrapper.getResources().getDimensionPixelSize(R$dimen.divider_height);
    }

    @Override // defpackage.m50
    public final int getItemViewType(int i) {
        return (i == R$id.divider_line_id || i == R$id.managed_by_divider_line_id) ? 0 : -1;
    }

    @Override // defpackage.m50
    public final void getViewTypeCount() {
    }
}
