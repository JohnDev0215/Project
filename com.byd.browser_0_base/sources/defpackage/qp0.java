package defpackage;

import android.view.View;
import android.widget.FrameLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/* JADX INFO: compiled from: FragmentStateAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class qp0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ FrameLayout e;
    public final /* synthetic */ dq0 f;
    public final /* synthetic */ FragmentStateAdapter g;

    public qp0(FragmentStateAdapter fragmentStateAdapter, FrameLayout frameLayout, dq0 dq0Var) {
        this.g = fragmentStateAdapter;
        this.e = frameLayout;
        this.f = dq0Var;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        FrameLayout frameLayout = this.e;
        if (frameLayout.getParent() != null) {
            frameLayout.removeOnLayoutChangeListener(this);
            this.g.I(this.f);
        }
    }
}
