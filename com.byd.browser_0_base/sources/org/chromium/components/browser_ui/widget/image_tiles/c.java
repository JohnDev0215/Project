package org.chromium.components.browser_ui.widget.image_tiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ma3;
import defpackage.nw2;
import org.chromium.components.browser_ui.widget.R$layout;
import org.chromium.components.browser_ui.widget.image_tiles.TileSizeSupplier;
import org.chromium.ui.modelutil.l;

/* JADX INFO: compiled from: TileViewHolderFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements l.b<ma3> {
    public final nw2<TileSizeSupplier.a> e;

    public c(nw2<TileSizeSupplier.a> nw2Var) {
        this.e = nw2Var;
    }

    @Override // org.chromium.ui.modelutil.l.b
    public final Object j(int i, RecyclerView recyclerView) {
        View viewInflate = LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.query_tile_view, (ViewGroup) recyclerView, false);
        ViewGroup.LayoutParams layoutParams = viewInflate.getLayoutParams();
        nw2<TileSizeSupplier.a> nw2Var = this.e;
        layoutParams.width = nw2Var.get().a;
        viewInflate.getLayoutParams().height = nw2Var.get().a;
        return new ma3(viewInflate);
    }
}
