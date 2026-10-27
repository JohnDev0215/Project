package defpackage;

import android.view.View;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.search_resumption.SearchResumptionModuleView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: SearchResumptionModuleViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pg2 implements j.a<PropertyModel, View, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        v62 v62Var = (v62) obj2;
        SearchResumptionModuleView searchResumptionModuleView = (SearchResumptionModuleView) ((View) obj);
        PropertyModel.n nVar = og2.a;
        if (nVar == v62Var) {
            searchResumptionModuleView.setVisibility(propertyModel.k(nVar) ? 0 : 8);
            return;
        }
        PropertyModel.r<Callback<Boolean>> rVar = og2.b;
        if (rVar == v62Var) {
            searchResumptionModuleView.setExpandCollapseCallback((Callback) propertyModel.j(rVar));
        }
    }
}
