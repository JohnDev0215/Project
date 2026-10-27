package org.chromium.chrome.browser.download.home.empty;

import defpackage.ih0;
import defpackage.v62;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.LoadingView;

/* JADX INFO: compiled from: EmptyViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements j.a<PropertyModel, EmptyView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        EmptyView emptyView = (EmptyView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.p pVar = ih0.a;
        if (v62Var != pVar) {
            PropertyModel.p pVar2 = ih0.b;
            if (v62Var == pVar2) {
                emptyView.c.setText(propertyModel.h(pVar2));
                return;
            }
            return;
        }
        int iH = propertyModel.h(pVar);
        emptyView.getClass();
        emptyView.b.setVisibility(iH == 1 ? 0 : 4);
        LoadingView loadingView = emptyView.d;
        if (iH == 0) {
            loadingView.e();
        } else {
            loadingView.c();
        }
    }
}
