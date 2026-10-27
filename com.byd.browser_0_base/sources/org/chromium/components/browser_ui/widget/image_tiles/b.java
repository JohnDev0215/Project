package org.chromium.components.browser_ui.widget.image_tiles;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.da3;
import defpackage.ea3;
import defpackage.es3;
import defpackage.fa3;
import defpackage.ga3;
import defpackage.gx0;
import defpackage.hx0;
import defpackage.l51;
import defpackage.ma3;
import defpackage.wd2;
import org.chromium.components.browser_ui.widget.R$anim;
import org.chromium.components.browser_ui.widget.R$dimen;
import org.chromium.components.browser_ui.widget.R$id;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.l;

/* JADX INFO: compiled from: TileListView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public final da3 a;
    public final org.chromium.components.browser_ui.widget.image_tiles.a b;
    public final l<ma3, Void> c;
    public final LayoutAnimationController d;
    public final TileSizeSupplier e;

    /* JADX INFO: compiled from: TileListView.java */
    public class a extends RecyclerView.l {
        public final int a;

        public a(Context context) {
            this.a = context.getResources().getDimensionPixelOffset(R$dimen.tile_grid_inter_tile_padding);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            recyclerView.getClass();
            int I = RecyclerView.I(view);
            int i = this.a;
            if (I != 0) {
                rect.left = i / 2;
            }
            if (I != b.this.a.size() - 1) {
                rect.right = i / 2;
            }
        }
    }

    /* JADX INFO: renamed from: org.chromium.components.browser_ui.widget.image_tiles.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TileListView.java */
    public static class C0127b extends org.chromium.ui.modelutil.a<Void> implements l.a<ma3, Void> {
        public final da3 f;

        public C0127b(da3 da3Var) {
            this.f = da3Var;
            da3Var.g(this);
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final void a(ma3 ma3Var, int i, Void r5) {
            ma3 ma3Var2 = ma3Var;
            da3 da3Var = this.f;
            PropertyModel propertyModel = da3Var.g;
            gx0 gx0Var = da3Var.get(i);
            int i2 = R$id.title;
            View view = ma3Var2.e;
            ((TextView) view.findViewById(i2)).setText(gx0Var.b);
            view.setOnClickListener(new wd2(propertyModel, 10, gx0Var));
            ma3Var2.u(null);
            ((hx0) propertyModel.j(ea3.b)).a(gx0Var, new l51(11, ma3Var2));
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final void c(ma3 ma3Var) {
            ma3Var.getClass();
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final int d() {
            return this.f.size();
        }

        @Override // org.chromium.ui.modelutil.l.a
        public final int getItemViewType(int i) {
            return 0;
        }
    }

    public b(Context context, da3 da3Var) {
        this.a = da3Var;
        org.chromium.components.browser_ui.widget.image_tiles.a aVar = new org.chromium.components.browser_ui.widget.image_tiles.a(this, context);
        this.b = aVar;
        aVar.setHasFixedSize(true);
        aVar.setLayoutManager(new LinearLayoutManager(0));
        aVar.g(new a(context));
        aVar.setItemAnimator(null);
        this.d = AnimationUtils.loadLayoutAnimation(context, R$anim.image_grid_enter);
        aVar.setLayoutAnimationListener(new ga3(this));
        TileSizeSupplier tileSizeSupplier = new TileSizeSupplier(context);
        this.e = tileSizeSupplier;
        j.b(da3Var.g, aVar, new fa3());
        l<ma3, Void> lVar = new l<>(new C0127b(da3Var), new c(tileSizeSupplier));
        this.c = lVar;
        aVar.setAdapter(lVar);
        aVar.post(new es3(6, lVar));
    }
}
