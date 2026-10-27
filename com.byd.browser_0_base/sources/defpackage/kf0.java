package defpackage;

import android.R;
import android.view.View;
import org.chromium.chrome.browser.omnibox.suggestions.base.b;
import org.chromium.chrome.browser.omnibox.suggestions.editurl.EditUrlSuggestionView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: EditUrlSuggestionViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kf0 implements j.a<PropertyModel, EditUrlSuggestionView, v62> {
    public final b<View> a = new b<>(new zf(17));

    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        EditUrlSuggestionView editUrlSuggestionView = (EditUrlSuggestionView) obj;
        v62 v62Var = (v62) obj2;
        this.a.b(propertyModel, editUrlSuggestionView.getBaseSuggestionView(), v62Var);
        PropertyModel.p pVar = bw2.a;
        if (pVar == v62Var) {
            editUrlSuggestionView.getDivider().setBackground(rr1.d(editUrlSuggestionView.getContext(), propertyModel.h(pVar), R.attr.listDivider));
        } else if (zd0.a == v62Var) {
            editUrlSuggestionView.getDivider().setVisibility(8);
        }
    }
}
