package X1;

import androidx.lifecycle.A;
import androidx.lifecycle.K;

/* JADX INFO: loaded from: classes.dex */
public final class d implements K {
    public final /* synthetic */ e l;

    public d(e eVar) {
        this.l = eVar;
    }

    @Override // androidx.lifecycle.K
    public final void a(Object obj) {
        if (((A) obj) != null) {
            e eVar = this.l;
            if (eVar.f9974w) {
                eVar.getClass();
                throw new IllegalStateException("Fragment " + eVar + " did not return a View from onCreateView() or this was called before onCreateView().");
            }
        }
    }
}
