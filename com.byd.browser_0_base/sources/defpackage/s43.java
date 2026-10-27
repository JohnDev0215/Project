package defpackage;

import android.content.res.ColorStateList;
import android.view.View;
import org.chromium.chrome.browser.toolbar.TabSwitcherButtonView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: TabSwitcherButtonViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s43 implements j.a<PropertyModel, TabSwitcherButtonView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        TabSwitcherButtonView tabSwitcherButtonView = (TabSwitcherButtonView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.p pVar = r43.a;
        if (pVar == v62Var) {
            tabSwitcherButtonView.r.e(propertyModel.h(pVar), false);
            return;
        }
        PropertyModel.r<View.OnClickListener> rVar = r43.b;
        if (rVar == v62Var) {
            tabSwitcherButtonView.setOnClickListener((View.OnClickListener) propertyModel.j(rVar));
            return;
        }
        PropertyModel.r<View.OnLongClickListener> rVar2 = r43.c;
        if (rVar2 == v62Var) {
            tabSwitcherButtonView.setOnLongClickListener((View.OnLongClickListener) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.r<ColorStateList> rVar3 = r43.d;
        if (rVar3 == v62Var) {
            tabSwitcherButtonView.setTint((ColorStateList) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.n nVar = r43.e;
        if (nVar == v62Var) {
            tabSwitcherButtonView.setEnabled(propertyModel.k(nVar));
        }
    }
}
