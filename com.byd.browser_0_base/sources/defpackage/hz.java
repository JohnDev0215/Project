package defpackage;

import org.chromium.ui.modelutil.f;

/* JADX INFO: compiled from: ContextMenuCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class hz extends f {
    public hz(ya1 ya1Var) {
        super(ya1Var);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return false;
    }

    @Override // org.chromium.ui.modelutil.f, android.widget.Adapter
    public final long getItemId(int i) {
        if (getItemViewType(i) == 2 || getItemViewType(i) == 3) {
            return ((xa1) getItem(i)).b.h(qz.b);
        }
        return -1L;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return getItemViewType(i) == 2 || getItemViewType(i) == 3;
    }
}
