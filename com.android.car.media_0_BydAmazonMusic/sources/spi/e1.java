package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: CarUiGridLayoutStyle.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e1 implements j1 {
    public int a = 1;
    public int b = 1;
    public boolean c = false;

    @NonNull
    public GridLayoutManager.SpanSizeLookup d = new GridLayoutManager.DefaultSpanSizeLookup();

    @Nullable
    public static e1 a(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return null;
        }
        if (!(layoutManager instanceof GridLayoutManager)) {
            throw new AssertionError("GridLayoutManager required.");
        }
        e1 e1Var = new e1();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        int spanCount = gridLayoutManager.getSpanCount();
        if (spanCount <= 0) {
            throw new AssertionError("Span count must be bigger than 0");
        }
        e1Var.a = spanCount;
        e1Var.c = gridLayoutManager.getReverseLayout();
        e1Var.d = gridLayoutManager.getSpanSizeLookup();
        return e1Var;
    }

    @Override // spi.j1
    public final int getLayoutType() {
        return 1;
    }

    @Override // spi.j1
    public final int getOrientation() {
        return this.b;
    }

    @Override // spi.j1
    public final boolean getReverseLayout() {
        return this.c;
    }

    @Override // spi.j1
    public final int getSpanCount() {
        return this.a;
    }
}
