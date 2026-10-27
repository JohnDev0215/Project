package androidx.preference;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
@Deprecated
public class e extends l {
    final RecyclerView f;
    final androidx.core.view.a g;
    final androidx.core.view.a h;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends androidx.core.view.a {
        a() {
        }

        @Override // androidx.core.view.a
        public void g(View view, androidx.core.view.accessibility.b bVar) {
            Preference preferenceF;
            e.this.g.g(view, bVar);
            int iD0 = e.this.f.d0(view);
            RecyclerView.Adapter adapterA0 = e.this.f.a0();
            if ((adapterA0 instanceof c) && (preferenceF = ((c) adapterA0).F(iD0)) != null) {
                preferenceF.R(bVar);
            }
        }

        @Override // androidx.core.view.a
        public boolean j(View view, int i, Bundle bundle) {
            return e.this.g.j(view, i, bundle);
        }
    }

    public e(RecyclerView recyclerView) {
        super(recyclerView);
        this.g = super.n();
        this.h = new a();
        this.f = recyclerView;
    }

    @Override // androidx.recyclerview.widget.l
    @NonNull
    public androidx.core.view.a n() {
        return this.h;
    }
}
