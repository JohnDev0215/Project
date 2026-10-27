package org.chromium.components.browser_ui.widget.image_tiles;

import android.content.Context;
import android.content.res.Configuration;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: TileListView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends RecyclerView {
    public final /* synthetic */ b P0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar, Context context) {
        super(context);
        this.P0 = bVar;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setAdapter(null);
        b bVar = this.P0;
        setAdapter(bVar.c);
        bVar.e.a();
    }
}
