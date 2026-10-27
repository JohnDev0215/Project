package androidx.viewpager2.adapter;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import i0.u;

/* JADX INFO: compiled from: FragmentViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends RecyclerView.c0 {
    private a(FrameLayout frameLayout) {
        super(frameLayout);
    }

    static a M(ViewGroup viewGroup) {
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout.setId(u.h());
        frameLayout.setSaveEnabled(false);
        return new a(frameLayout);
    }

    FrameLayout N() {
        return (FrameLayout) this.f2857a;
    }
}
