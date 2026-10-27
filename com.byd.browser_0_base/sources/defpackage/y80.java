package defpackage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.ui.fast_checkout.R$drawable;

/* JADX INFO: compiled from: DetailItemDecoration.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y80 extends RecyclerView.l {
    public final int a;

    public y80(int i) {
        this.a = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
        recyclerView.getClass();
        rect.top = RecyclerView.I(view) != 0 ? this.a : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            int I = RecyclerView.I(childAt);
            childAt.setBackground(AppCompatResources.a(recyclerView.getContext(), I == 0 ? R$drawable.fast_checkout_background_top : I == recyclerView.getAdapter().k() + (-1) ? R$drawable.fast_checkout_background_bottom : R$drawable.fast_checkout_background_middle));
        }
    }
}
