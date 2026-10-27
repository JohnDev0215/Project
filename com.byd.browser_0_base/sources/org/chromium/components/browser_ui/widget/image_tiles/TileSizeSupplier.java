package org.chromium.components.browser_ui.widget.image_tiles;

import android.content.Context;
import android.content.res.Resources;
import defpackage.nw2;
import org.chromium.components.browser_ui.widget.R$dimen;

/* JADX INFO: loaded from: classes2.dex */
class TileSizeSupplier implements nw2<a> {
    public final a e = new a();
    public final Resources f;
    public final int g;
    public final int h;
    public final int i;

    public static class a {
        public int a;
    }

    public TileSizeSupplier(Context context) {
        Resources resources = context.getResources();
        this.f = resources;
        this.g = resources.getDimensionPixelOffset(R$dimen.tile_ideal_width);
        int i = R$dimen.tile_grid_inter_tile_padding;
        this.h = resources.getDimensionPixelOffset(i);
        this.i = resources.getDimensionPixelOffset(i);
        a();
    }

    public final void a() {
        Resources resources = this.f;
        int i = resources.getDisplayMetrics().widthPixels;
        int i2 = this.i;
        int i3 = this.h;
        double d = ((double) ((i - (i2 * 2)) + i3)) / ((double) (this.g + i3));
        double dFloor = Math.floor(d) + Math.max(0.3d, Math.min(0.7d, d - Math.floor(d)));
        double d2 = resources.getDisplayMetrics().widthPixels - (i2 * 2);
        double dFloor2 = Math.floor(dFloor);
        a aVar = this.e;
        aVar.getClass();
        aVar.a = (int) ((d2 - (dFloor2 * ((double) i3))) / dFloor);
    }

    @Override // defpackage.nw2
    public final a get() {
        return this.e;
    }
}
