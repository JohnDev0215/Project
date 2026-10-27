package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.R$id;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: ListPropertyViewBinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class u81 implements j.a<PropertyModel, RecyclerView, v62> {
    @Override // org.chromium.ui.modelutil.j.a
    public final void b(k kVar, Object obj, Object obj2) {
        PropertyModel propertyModel = (PropertyModel) kVar;
        RecyclerView recyclerView = (RecyclerView) obj;
        v62 v62Var = (v62) obj2;
        PropertyModel.n nVar = t81.m;
        if (v62Var == nVar) {
            if (!propertyModel.k(nVar)) {
                if (recyclerView.getItemAnimator() != null) {
                    recyclerView.setTag(R$id.item_animator, recyclerView.getItemAnimator());
                    recyclerView.setItemAnimator(null);
                    return;
                }
                return;
            }
            if (recyclerView.getItemAnimator() == null) {
                int i = R$id.item_animator;
                recyclerView.setItemAnimator((RecyclerView.j) recyclerView.getTag(i));
                recyclerView.setTag(i, null);
                return;
            }
            return;
        }
        if (v62Var == t81.n || v62Var == t81.o || v62Var == t81.p || v62Var == t81.q || v62Var == t81.r || v62Var == t81.s || v62Var == t81.u || v62Var == t81.w || v62Var == t81.t || v62Var == t81.x) {
            RecyclerView.e adapter = recyclerView.getAdapter();
            adapter.e.d(0, recyclerView.getAdapter().k(), null);
        }
    }
}
