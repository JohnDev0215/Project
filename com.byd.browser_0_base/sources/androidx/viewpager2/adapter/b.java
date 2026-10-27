package androidx.viewpager2.adapter;

import androidx.viewpager2.adapter.FragmentStateAdapter;

/* JADX INFO: compiled from: FragmentStateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends FragmentStateAdapter.a {
    public final /* synthetic */ FragmentStateAdapter.c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(FragmentStateAdapter.c cVar) {
        super(0);
        this.a = cVar;
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter.a, androidx.recyclerview.widget.RecyclerView.g
    public final void a() {
        this.a.b(true);
    }
}
