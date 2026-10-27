package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.b;

/* JADX INFO: compiled from: RecyclerView.java */
/* JADX INFO: loaded from: classes.dex */
public final class v implements b.InterfaceC0025b {
    public final /* synthetic */ RecyclerView a;

    public v(RecyclerView recyclerView) {
        this.a = recyclerView;
    }

    public final int a() {
        return this.a.getChildCount();
    }

    public final void b(int i) {
        RecyclerView recyclerView = this.a;
        View childAt = recyclerView.getChildAt(i);
        if (childAt != null) {
            recyclerView.o(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i);
    }
}
