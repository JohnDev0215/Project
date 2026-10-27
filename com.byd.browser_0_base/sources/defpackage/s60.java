package defpackage;

import defpackage.r60;
import org.chromium.ui.modelutil.l;

/* JADX INFO: compiled from: DateOrderedListViewAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class s60 extends l<i81, Void> {
    public final g70 j;

    public s60(g70 g70Var, r60.c cVar, wd0 wd0Var) {
        super(cVar, wd0Var);
        this.j = g70Var;
        B(true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final long l(int i) {
        if (this.f) {
            return this.j.get(i).a;
        }
        return -1L;
    }
}
