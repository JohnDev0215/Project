package defpackage;

import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: AppLanguagePromoDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class p6 extends RecyclerView.q {
    public final /* synthetic */ ImageView e;
    public final /* synthetic */ ImageView f;

    public p6(ImageView imageView, ImageView imageView2) {
        this.e = imageView;
        this.f = imageView2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void b(RecyclerView recyclerView, int i, int i2) {
        boolean zCanScrollVertically = recyclerView.canScrollVertically(-1);
        ImageView imageView = this.e;
        if (zCanScrollVertically) {
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
        }
        boolean zCanScrollVertically2 = recyclerView.canScrollVertically(1);
        ImageView imageView2 = this.f;
        if (zCanScrollVertically2) {
            imageView2.setVisibility(0);
        } else {
            imageView2.setVisibility(8);
        }
    }
}
