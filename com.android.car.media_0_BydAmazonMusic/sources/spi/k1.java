package spi;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: CarUiLinearLayoutStyle.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k1 implements j1 {
    public int a = 1;
    public boolean b = false;

    @Nullable
    public static k1 a(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return null;
        }
        if (!(layoutManager instanceof LinearLayoutManager)) {
            throw new AssertionError("LinearLayoutManager required.");
        }
        k1 k1Var = new k1();
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int orientation = linearLayoutManager.getOrientation();
        if (orientation == 0) {
            k1Var.a = 0;
        } else if (orientation == 1) {
            k1Var.a = 1;
        }
        k1Var.b = linearLayoutManager.getReverseLayout();
        return k1Var;
    }

    @Override // spi.j1
    public final int getLayoutType() {
        return 0;
    }

    @Override // spi.j1
    public final int getOrientation() {
        return this.a;
    }

    @Override // spi.j1
    public final boolean getReverseLayout() {
        return this.b;
    }

    @Override // spi.j1
    public final int getSpanCount() {
        return 1;
    }
}
