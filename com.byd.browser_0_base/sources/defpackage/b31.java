package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ItemDividerBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b31 extends RecyclerView.l {
    public final Context a;

    public b31(Context context) {
        this.a = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        int iK = recyclerView.getAdapter().k();
        if (iK > 1 && l(recyclerView.getAdapter().m(iK - 2))) {
            iK--;
        }
        if (iK > 0) {
            int i = iK - 1;
            if (l(recyclerView.getAdapter().m(i))) {
                iK = i;
            }
        }
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int I = RecyclerView.I(childAt);
            if (!l(recyclerView.getAdapter().m(I))) {
                childAt.setBackground(AppCompatResources.a(this.a, k(I, iK, j(recyclerView))));
            }
        }
    }

    public abstract boolean j(RecyclerView recyclerView);

    public abstract int k(int i, int i2, boolean z);

    public abstract boolean l(int i);
}
